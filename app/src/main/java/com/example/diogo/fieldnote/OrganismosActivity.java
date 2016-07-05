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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i =0;
                int x = ((int) dataSnapshot.child("observações").getChildrenCount());
                String[] datas = new String[x];
                String[] parcelas = new String[x];
                for (DataSnapshot postSnapshot: dataSnapshot.child("observações").getChildren()) {
                    Observacao est = postSnapshot.getValue(Observacao.class);
                    datas[i] = est.getData();
                    parcelas[i++] = est.getParcela();
                }
                ListAdapter datasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, datas);
                ListView datasView = (ListView) findViewById(R.id.datesView);
                datasView.setAdapter(datasAdapter);
                ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, parcelas);
                ListView parcelasView = (ListView) findViewById(R.id.parcelasView);
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
                startActivity(new Intent(getApplicationContext(), RegistarObservacaoActivity.class));
            }
        });

        final ListView datesView = (ListView) findViewById(R.id.datesView);
        final ListView parcelasView = (ListView) findViewById(R.id.parcelasView);
        datesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarOrganismoActivity.class);
                Object obj = datesView.getAdapter().getItem(position);
                Object obj2 = parcelasView.getAdapter().getItem(position);
                intent.putExtra("id", obj2.toString() + " - " + obj.toString() );
                startActivity(intent);
            }
        });

        parcelasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarOrganismoActivity.class);
                Object obj = datesView.getAdapter().getItem(position);
                Object obj2 = parcelasView.getAdapter().getItem(position);
                intent.putExtra("id", obj2.toString() + " - " + obj.toString() );
                startActivity(intent);
            }
        });

    }

}
