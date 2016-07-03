package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EstadoFenologico est = dataSnapshot.child("estados").child(id).getValue(EstadoFenologico.class);
                TextView data = (TextView) findViewById(R.id.dataEstado);
                data.setText(est.getData());
                TextView estadoAtual = (TextView) findViewById(R.id.estadoAtual);
                estadoAtual.setText(est.getEstado());
                TextView parcelaEstado = (TextView) findViewById(R.id.parcelaEstado);
                parcelaEstado.setText("Estado atual da parcela "
                        + est.getParcela()+ " - " +est.getCampanha());

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


        String[] estados = {"2 - exemplo", "1 - exemplo"};
        ListAdapter estadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, estados);
        ListView estadosView = (ListView) findViewById(R.id.estadosView);
        estadosView.setAdapter(estadosAdapter);

        String[] datas = {"11/09/2016", "21/08/2016"};
        ListAdapter datasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        ListView datasView = (ListView) findViewById(R.id.datesView2);
        datasView.setAdapter(datasAdapter);
    }
}
