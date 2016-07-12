package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.StringTokenizer;

public class MostrarIntervencoesActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_intervencao_tecnica);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");
        final String parseme = id;
       getSupportActionBar().setTitle("Intervenção Técnica");


        final TextView data = (TextView) findViewById(R.id.data);
        final TextView inter = (TextView) findViewById(R.id.zonaT);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                IntervencaoTecnica intervencaoTecnica = dataSnapshot.child("Intervencoes").child(id).getValue(IntervencaoTecnica.class);

               //passar a data ao textview
                data.setText(intervencaoTecnica.getData());

                //passar a zona ao textview
                inter.setText(intervencaoTecnica.getZona());

                int j = 0;
                Group group = new Group("Justificação da Intervenção");
                    group.children.add(intervencaoTecnica.getMotivo1());
                    group.children.add(intervencaoTecnica.getQuantificadorJustificacao());
                groups.append(j++, group);

                group = new Group("Estimativa de Risco");
                    group.children.add(intervencaoTecnica.getPraga());
                    group.children.add(intervencaoTecnica.getQuantificadorRisco());
                groups.append(j++, group);

                group = new Group("Oper. Cultural Cont.Infestantes");
                    group.children.add(intervencaoTecnica.getTipo());
                    group.children.add(intervencaoTecnica.getEquipamento());
                groups.append(j++, group);

                group = new Group("Irrigação Fertirrigação");
                    group.children.add(intervencaoTecnica.getDebito());
                    group.children.add(intervencaoTecnica.getFertilizante());
                groups.append(j++, group);

                group = new Group("Fertilização");
                    group.children.add(intervencaoTecnica.getAdubo());
                    group.children.add(intervencaoTecnica.getEspecies());
                groups.append(j++, group);

                group = new Group("Tratamento Fitossanitário");
                    group.children.add(intervencaoTecnica.getMeio());
                    group.children.add(intervencaoTecnica.getQuantificadorTratamento());
                groups.append(j++, group);

                group = new Group("Produção e Vendas");
                    group.children.add(intervencaoTecnica.getColheita());
                    group.children.add(intervencaoTecnica.getQuantificadorProducao());
                groups.append(j++, group);

                group = new Group("Visitas e Intervenientes");
                    group.children.add(intervencaoTecnica.getOperador());
                    group.children.add(intervencaoTecnica.getArea());
                groups.append(j++, group);

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
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);

        //botao editar intervencao
        Button edit_interv = (Button) findViewById(R.id.edit_intervencao);
        edit_interv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(), EditarIntervencaoTecnicaActivity.class);
                        intent.putExtra("id", parseme );
                        //TODO remove-me
                        System.out.println("parseme:"+parseme);
                        finish();
                        startActivity(intent);
                    }
                });
    }

}
