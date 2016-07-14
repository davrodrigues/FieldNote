package com.example.diogo.fieldnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MostrarEstadoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_estado);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent myIntent = getIntent();
        final String param = myIntent.getStringExtra("id");

        getSupportActionBar().setTitle("Parcela " + param);

        final TextView data = (TextView) findViewById(R.id.dataEstado);
        final TextView estadoAtual = (TextView) findViewById(R.id.estadoAtual);
        final TextView parcelaEstado = (TextView) findViewById(R.id.parcelaEstado);
        final ListView campanhasView = (ListView) findViewById(R.id.estadosView);
        final ListView parcelasView = (ListView) findViewById(R.id.datesView2);
        final ListView cruzesView = (ListView) findViewById(R.id.apagarEstado);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i = 0;
                int x = (int) dataSnapshot.child("estados").child(param).getChildrenCount();
                if (x > 0) {
                    String[] estados = new String[x ];
                    String[] datas = new String[x];
                    String[] cruzes = new String[x];
                    for (DataSnapshot postSnapshot : dataSnapshot.child("estados").child(param).getChildren()) {
                        if (i == dataSnapshot.child("estados").child(param).getChildrenCount() - 1) {
                            EstadoFenologico est = postSnapshot.getValue(EstadoFenologico.class);
                            data.setText(est.getData());
                            estadoAtual.setText(est.getEstado());
                            parcelaEstado.setText("Estado atual da parcela "
                                    + est.getParcela() + " - " + est.getCampanha());
                            estados[i] = est.getEstado();
                            cruzes[i] = "X";
                            datas[i++] = est.getData();

                        } else {
                            EstadoFenologico est = postSnapshot.getValue(EstadoFenologico.class);
                            estados[i] = est.getEstado();
                            cruzes[i] = "X";
                            datas[i++] = est.getData();
                        }
                    }
                    ListAdapter campanhasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, estados);
                    campanhasView.setAdapter(campanhasAdapter);
                    ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, datas);
                    parcelasView.setAdapter(parcelasAdapter);
                    ListAdapter cruzesAdapter = new ArrayAdapter<String>(getApplication(), R.layout.red_center_list, cruzes);
                    cruzesView.setAdapter(cruzesAdapter);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed");
            }


        });

        cruzesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final String obj1 = campanhasView.getAdapter().getItem(position).toString();

                new AlertDialog.Builder(MostrarEstadoActivity.this)
                        .setTitle("Apagar registo")
                        .setMessage("Tem a certeza que pretende apagar este registo??")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mDatabase.child("FieldNote/estados/"+param+"/"+obj1).removeValue();

                                Toast.makeText(getApplicationContext(),
                                        "Registo removido com sucesso.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), MostrarEstadoActivity.class);
                                intent.putExtra("id", param);
                                finish();
                                startActivity(intent);                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //nao faz nada
                            }
                        })
                        .setIcon(R.drawable.alert_smallest)
                        .show();

            }
        });
    }
}
