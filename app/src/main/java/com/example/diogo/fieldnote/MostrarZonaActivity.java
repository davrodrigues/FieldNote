package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MostrarZonaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_zona);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id").toUpperCase();


        getSupportActionBar().setTitle("  Zona  "+id);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Zona zone = dataSnapshot.child("exemplo_zona").child(id).getValue(Zona.class);

                TextView nzona = (TextView) findViewById(R.id.nome_zona);
                nzona.setText(zone.getNomezona());

                TextView area = (TextView) findViewById(R.id.mostrar_area);
                area.setText(zone.getArea());

                TextView fitossanidade = (TextView) findViewById(R.id.mostrar_fito);
                fitossanidade.setText(zone.getFitossanidade());

                TextView localizacao = (TextView) findViewById(R.id.mostrar_local);
                localizacao.setText(zone.getLocalização());

                TextView modo_producao = (TextView) findViewById(R.id.mostrar_producao);
                modo_producao.setText(zone.getModoprodução());

                TextView solo = (TextView) findViewById(R.id.mostrar_solo);
                solo.setText(zone.getSolo());


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
