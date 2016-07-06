package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ZonasActivity extends AppCompatActivity {

    //db
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //botao adicionar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), RegistarNovaZona.class));
            }
        });


        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int i =0;
                int x = ((int) dataSnapshot.child("zonas").getChildrenCount());
                String[] nzona = new String[x];
                String[] local = new String[x];
                String[] nparcelas = new String[x];


                for (DataSnapshot postSnapshot: dataSnapshot.child("zonas").getChildren()) {
                    Zona zone = postSnapshot.getValue(Zona.class);

                    nzona[i]=zone.getNomezona();
                    local[i] = zone.getLocalização();
                    nparcelas[i] = String.valueOf(postSnapshot.child("parcelas").getChildrenCount());

                    i++;
                }

                //lista de zonas
                ListAdapter zonasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nzona);
                ListView zonasView = (ListView) findViewById(R.id.zonasView);
                zonasView.setAdapter(zonasAdapter);

                //localização
                ListAdapter localAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, local);
                ListView localView = (ListView) findViewById(R.id.localView);
                localView.setAdapter(localAdapter);



                //numero parcelas
                ListAdapter nparcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nparcelas);
                ListView nparcelasView = (ListView) findViewById(R.id.nparcelasView);
                nparcelasView.setAdapter(nparcelasAdapter);

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

        final ListView zonasView = (ListView) findViewById(R.id.zonasView);
        final ListView localView = (ListView) findViewById(R.id.localView);
        final ListView nparcelasView = (ListView) findViewById(R.id.nparcelasView);

        //Mostrar zona ao clicar na letra da zona
        zonasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarZonaActivity.class);
                Object obj = zonasView.getAdapter().getItem(position);
                finish();
                intent.putExtra("id", obj.toString() );
                startActivity(intent);
            }
        });

        //Mostrar zona ao clicar na localização
        localView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarZonaActivity.class);
                Object obj = zonasView.getAdapter().getItem(position);
                finish();
                intent.putExtra("id", obj.toString() );
                startActivity(intent);
            }
        });

        //Mostrar zona ao clicar no #parcelas
        nparcelasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarZonaActivity.class);
                Object obj = zonasView.getAdapter().getItem(position);
                finish();
                intent.putExtra("id", obj.toString() );
                startActivity(intent);
            }
        });
    }
}
