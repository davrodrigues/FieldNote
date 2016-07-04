package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MostrarCampanhaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_campanha);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Campanha est = dataSnapshot.child("campanhas").child(id).getValue(Campanha.class);
                TextView cult = (TextView) findViewById(R.id.CulturaText);
                cult.setText(est.getCultura());
                TextView parc = (TextView) findViewById(R.id.parcelaCult);
                parc.setText(est.getParcela());
                TextView dataPlant = (TextView) findViewById(R.id.dataPlant);
                dataPlant.setText(est.getData_de_Plantacao());
                TextView estadoPlant = (TextView) findViewById(R.id.estadoPlant);
                EstadoFenologico estado = dataSnapshot.child("estados").
                        child(id+ " - "+est.getCultura()).getValue(EstadoFenologico.class);
                if(estado != null)
                    estadoPlant.setText(estado.getEstado());

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
