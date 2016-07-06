package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        final String id = myIntent.getStringExtra("id");

        getSupportActionBar().setTitle("Parcela " + id);

        final TextView data = (TextView) findViewById(R.id.dataEstado);
        final TextView estadoAtual = (TextView) findViewById(R.id.estadoAtual);
        final TextView parcelaEstado = (TextView) findViewById(R.id.parcelaEstado);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i = 0;
                int x = (int) dataSnapshot.child("estados").child(id).getChildrenCount();
                if (x > 0) {
                    String[] estados = new String[x - 1];
                    String[] datas = new String[x - 1];
                    for (DataSnapshot postSnapshot : dataSnapshot.child("estados").child(id).getChildren()) {
                        if (i == dataSnapshot.child("estados").child(id).getChildrenCount() - 1) {
                            EstadoFenologico est = postSnapshot.getValue(EstadoFenologico.class);
                            data.setText(est.getData());
                            estadoAtual.setText(est.getEstado());
                            parcelaEstado.setText("Estado atual da parcela "
                                    + est.getParcela() + " - " + est.getCampanha());
                            i++;
                        } else {
                            EstadoFenologico est = postSnapshot.getValue(EstadoFenologico.class);
                            estados[i] = est.getEstado();
                            datas[i++] = est.getData();
                        }
                    }
                    ListAdapter campanhasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, estados);
                    ListView campanhasView = (ListView) findViewById(R.id.estadosView);
                    campanhasView.setAdapter(campanhasAdapter);
                    ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, datas);
                    ListView parcelasView = (ListView) findViewById(R.id.datesView2);
                    parcelasView.setAdapter(parcelasAdapter);
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
    }
}
