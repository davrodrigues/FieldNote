package com.example.diogo.fieldnote;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class EstadosFenologicosActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estados_fenologicos);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i =0;
                int x = ((int) dataSnapshot.child("campanhas").getChildrenCount());
                String[] campanhas = new String[x];
                String[] parcelas = new String[x];
                for (DataSnapshot post: dataSnapshot.child("campanhas").getChildren()) {
                    Campanha cam = post.getValue(Campanha.class);
                    campanhas[i] = cam.getCultura();
                    parcelas[i++] = cam.getParcela();

                }
                ListAdapter campanhasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, campanhas);
                ListView campanhasView = (ListView) findViewById(R.id.campanhasView);
                campanhasView.setAdapter(campanhasAdapter);
                ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, parcelas);
                ListView parcelasView = (ListView) findViewById(R.id.parcelas2View);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarEstadoActivity.class));
            }
        });

        final ListView campanhasView = (ListView) findViewById(R.id.campanhasView);
        final ListView parcelasView = (ListView) findViewById(R.id.parcelas2View);
        campanhasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarEstadoActivity.class);
                Object obj = campanhasView.getAdapter().getItem(position);
                Object obj2 = parcelasView.getAdapter().getItem(position);
                intent.putExtra("id", obj2.toString() + " - " + obj.toString() );
                startActivity(intent);
            }
        });

    }

}
