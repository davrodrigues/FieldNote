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

public class IntervencaoTecnicaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervencao_tecnica);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i=0;
                int x= ((int) dataSnapshot.child("Intervencoes").getChildrenCount());
                String[]datas = new String[x];
                String[]zonas= new String[x];
                String[]operadores= new String[x];

                for(DataSnapshot post: dataSnapshot.child("Intervencoes").getChildren()){
                    IntervencaoTecnica intervencaoTecnica = post.getValue(IntervencaoTecnica.class);
                    datas[i]=intervencaoTecnica.getData();
                    operadores[i]= intervencaoTecnica.getOperador();
                    zonas[i++]=intervencaoTecnica.getZona();
                }
                ListAdapter dataAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list,datas);
                ListView dataView = (ListView) findViewById(R.id.DataView);
                dataView.setAdapter(dataAdapter);
                ListAdapter zonasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list,zonas);
                ListView zonasView = (ListView) findViewById(R.id.zonasView);
                zonasView.setAdapter(zonasAdapter);
                ListAdapter operadorAdapter = new ArrayAdapter<String>(getApplication(),R.layout.black_list,operadores);
                ListView operadorView = (ListView) findViewById(R.id.operadorView);
                operadorView.setAdapter(operadorAdapter);
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
        //botao adicionar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarIntervencaoTecnicaActivity.class));
            }
        });

        final ListView datasView = (ListView)findViewById(R.id.DataView);
        final ListView zonasView = (ListView) findViewById(R.id.zonasView);
        final ListView operadorView = (ListView) findViewById(R.id.operadorView);
        datasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarIntervencoesActivity.class);
                Object obj = datasView.getAdapter().getItem(position);
                Object obj2 = zonasView.getAdapter().getItem(position);
                Object obj3 = operadorView.getAdapter().getItem(position);
                intent.putExtra("id", obj.toString()+ " - "+ obj3.toString()+" - " + obj2.toString());
                startActivity(intent);
            }
        });
    }

}
