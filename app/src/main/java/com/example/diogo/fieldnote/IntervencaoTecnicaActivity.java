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
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        final ListView dataView = (ListView) findViewById(R.id.DataView);
        final ListView zonasView = (ListView) findViewById(R.id.zonasView);
        final ListView operadorView = (ListView) findViewById(R.id.operadorView);
        final Spinner filtro = (Spinner) findViewById(R.id.filtro);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                final DataSnapshot dataSnap = dataSnapshot;
                List<String> filtros = new ArrayList<String>();
                filtros.add("Todos");
                for (DataSnapshot postSnapshot: dataSnapshot.child("zonas").getChildren()) {
                    filtros.add(postSnapshot.getKey());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, filtros);
                filtro.setAdapter(adapter);

                filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        List<String> datas = new ArrayList<String>();
                        List<String> zonas = new ArrayList<String>();
                        List<String> operadores = new ArrayList<String>();
                        for (DataSnapshot postSnapshot: dataSnap.child("Intervencoes").getChildren()) {
                            IntervencaoTecnica inter = postSnapshot.getValue(IntervencaoTecnica.class);
                            String zona  = inter.getZona();
                            if(filtro.getSelectedItem().toString().equalsIgnoreCase("todos")){
                                zonas.add(inter.getZona());
                                datas.add(inter.getData());
                                operadores.add(inter.getOperador());
                            }
                            else if (zona.equalsIgnoreCase(filtro.getSelectedItem().toString())) {
                                zonas.add(inter.getZona());
                                datas.add(inter.getData());
                                operadores.add(inter.getOperador());
                            }
                        }
                        ListAdapter datasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, datas);
                        ListAdapter zonasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_list, zonas);
                        ListAdapter operadoresAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, operadores);
                        dataView.setAdapter(datasAdapter);
                        zonasView.setAdapter(zonasAdapter);
                        operadorView.setAdapter(operadoresAdapter);
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
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

        dataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), MostrarIntervencoesActivity.class);
                Object obj = dataView.getAdapter().getItem(position);
                Object obj2 = zonasView.getAdapter().getItem(position);
                Object obj3 = operadorView.getAdapter().getItem(position);
                intent.putExtra("id", obj.toString()+ " - "+ obj3.toString()+" - " + obj2.toString());
                startActivity(intent);
            }
        });
    }

}
