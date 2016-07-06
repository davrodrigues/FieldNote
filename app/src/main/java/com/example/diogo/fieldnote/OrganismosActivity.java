package com.example.diogo.fieldnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.StringTokenizer;

public class OrganismosActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organismos);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView datasView = (ListView) findViewById(R.id.datesView);
        final ListView parcelasView = (ListView) findViewById(R.id.parcelasView);
        final ListView cruzesView = (ListView) findViewById(R.id.cruzesView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i =0;
                int x = ((int) dataSnapshot.child("observações").getChildrenCount());
                String[] datas = new String[x];
                String[] parcelas = new String[x];
                String[] cruzes = new String[x];
                for (DataSnapshot postSnapshot: dataSnapshot.child("observações").getChildren()) {
                    Observacao est = postSnapshot.getValue(Observacao.class);
                    datas[i] = est.getData();
                    cruzes[i] = "X";
                    StringTokenizer str = new StringTokenizer(est.getParcela());
                    parcelas[i++] = str.nextToken();
                }
                ListAdapter datasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, datas);
                ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, parcelas);
                ListAdapter cruzesAdapter = new ArrayAdapter<String>(getApplication(), R.layout.red_center_list, cruzes);
                datasView.setAdapter(datasAdapter);
                parcelasView.setAdapter(parcelasAdapter);
                cruzesView.setAdapter(cruzesAdapter);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarObservacaoActivity.class));
            }
        });

        //mostrar organismo
        datasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), MostrarOrganismoActivity.class);
                Object obj = datasView.getAdapter().getItem(position);
                Object obj2 = parcelasView.getAdapter().getItem(position);
                intent.putExtra("id", obj2.toString() + " - " + obj.toString() );
                startActivity(intent);
            }
        });

        cruzesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final String obj1 = parcelasView.getAdapter().getItem(position).toString();
                final String obj2 = datasView.getAdapter().getItem(position).toString();

                new AlertDialog.Builder(OrganismosActivity.this)
                        .setTitle("Apagar observação")
                        .setMessage("Tem a certeza que pretende apagar esta observação?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mDatabase.child("FieldNote/observações/"+obj1+" - "+obj2).removeValue();

                                Toast.makeText(getApplicationContext(),
                                        "Observação removida com sucesso.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), OrganismosActivity.class);

                                finish();
                                startActivity(intent);                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //nao faz nada
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
    }

}
