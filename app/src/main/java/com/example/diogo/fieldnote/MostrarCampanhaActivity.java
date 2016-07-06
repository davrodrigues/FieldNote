package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        getSupportActionBar().setTitle(id);


        Button button = (Button) findViewById(R.id.registarButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarEstadoActivity.class));
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TextView cult = (TextView) findViewById(R.id.CulturaText);
                TextView parc = (TextView) findViewById(R.id.parcelaCult);
                TextView dataPlant = (TextView) findViewById(R.id.dataPlant);
                TextView estadoPlant = (TextView) findViewById(R.id.estadoPlant);
                Campanha est = dataSnapshot.child("campanhas").child(id).getValue(Campanha.class);
                cult.setText(est.getCultura());
                parc.setText(est.getParcela());
                dataPlant.setText(est.getData_de_Plantacao());
                int i = 0;
                for(DataSnapshot post: dataSnapshot.child("estados").
                        child(id+ " - "+est.getCultura()).getChildren()) {
                    if (i == dataSnapshot.child("estados").
                            child(id+ " - "+est.getCultura()).getChildrenCount() - 1) {
                        EstadoFenologico estado = post.getValue(EstadoFenologico.class);
                        if (estado != null)
                            estadoPlant.setText(estado.getEstado());
                    }
                    i++;
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
