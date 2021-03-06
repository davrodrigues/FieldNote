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

import java.util.ArrayList;
import java.util.List;

public class CampanhasActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanhas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView campanhasView = (ListView) findViewById(R.id.listaCampanhas);
        final ListView parcelasView = (ListView) findViewById(R.id.listaParcelas);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                List<String> culturas = new ArrayList<String>();
                List<String> parcelas = new ArrayList<String>();
                for (DataSnapshot postSnapshot: dataSnapshot.child("campanhas").getChildren()) {
                    Campanha est = postSnapshot.getValue(Campanha.class);
                    culturas.add(est.getCultura());
                    parcelas.add(est.getParcela());
                }
                ListAdapter campanhasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, culturas);
                ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, parcelas);
                campanhasView.setAdapter(campanhasAdapter);
                parcelasView.setAdapter(parcelasAdapter);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.adicionarCampanha);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarCampanhaActivity.class));

            }
        });

        campanhasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), MostrarCampanhaActivity.class);
                Object obj2 = parcelasView.getAdapter().getItem(position);
                intent.putExtra("id", obj2.toString());
                startActivity(intent);
            }
        });
    }

}
