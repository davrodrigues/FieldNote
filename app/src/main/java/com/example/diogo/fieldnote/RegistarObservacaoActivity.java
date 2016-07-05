package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RegistarObservacaoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_observacao);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button fab = (Button) findViewById(R.id.registarObsButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> childUpdates = new HashMap<>();
                Map<String, Object> dados = new HashMap<String, Object>();
                Map<String, String> pragas = new HashMap<String, String>();
                Map<String, String> doencas = new HashMap<String, String>();
                Map<String, String> auxiliares = new HashMap<String, String>();

                Spinner parcela = (Spinner) findViewById(R.id.parcelasdrop);
                String parc = parcela.getSelectedItem().toString();

                TextView textView = (TextView)findViewById(R.id.dataObs);
                String data = textView.getText().toString();

                TextView praga1 = (TextView)findViewById(R.id.praga1);
                TextView praga2 = (TextView)findViewById(R.id.praga2);
                TextView praga3 = (TextView)findViewById(R.id.praga3);
                if(!praga1.getText().toString().isEmpty())
                    pragas.put(praga1.getText().toString(),praga1.getText().toString());
                if(!praga2.getText().toString().isEmpty())
                    pragas.put(praga2.getText().toString(),praga2.getText().toString());
                if(!praga3.getText().toString().isEmpty())
                    pragas.put(praga3.getText().toString(),praga3.getText().toString());

                TextView doencas1 = (TextView)findViewById(R.id.doencas1);
                TextView doencas2 = (TextView)findViewById(R.id.doencas2);
                TextView doencas3 = (TextView)findViewById(R.id.doencas3);
                if(!doencas1.getText().toString().isEmpty())
                    doencas.put(doencas1.getText().toString(),doencas1.getText().toString());
                if(!doencas2.getText().toString().isEmpty())
                    doencas.put(doencas2.getText().toString(),doencas2.getText().toString());
                if(!doencas3.getText().toString().isEmpty())
                    doencas.put(doencas3.getText().toString(),doencas3.getText().toString());

                TextView auxiliares1 = (TextView)findViewById(R.id.auxiliares1);
                TextView auxiliares2 = (TextView)findViewById(R.id.auxiliares2);
                TextView auxiliares3 = (TextView)findViewById(R.id.auxiliares3);
                if(!auxiliares1.getText().toString().isEmpty())
                    auxiliares.put(auxiliares1.getText().toString(),auxiliares1.getText().toString());
                if(!auxiliares2.getText().toString().isEmpty())
                    auxiliares.put(auxiliares2.getText().toString(),auxiliares2.getText().toString());
                if(!auxiliares3.getText().toString().isEmpty())
                    auxiliares.put(auxiliares3.getText().toString(), auxiliares3.getText().toString());
                TextView obser = (TextView)findViewById(R.id.observações);
                if(!obser.getText().toString().isEmpty())
                    dados.put("Observacoes", obser.getText().toString());

                StringTokenizer str = new StringTokenizer(parc);

                dados.put("Pragas", pragas);
                dados.put("Doencas", doencas);
                dados.put("Auxiliares", auxiliares);
                dados.put("Data", data );
                dados.put("Parcela", parc);
                childUpdates.put("FieldNote/observações/" + str.nextToken() + " - " + data + "/", dados);
                mDatabase.updateChildren(childUpdates);
                finish();
                startActivity(new Intent(getApplicationContext(), OrganismosActivity.class));
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i = 0;
                String[] items = new String[(int)dataSnapshot.child("campanhas").getChildrenCount()];
                for (DataSnapshot postSnapshot: dataSnapshot.child("campanhas").getChildren()) {
                    Campanha est = postSnapshot.getValue(Campanha.class);
                    items[i++] = (est.getParcela() + " - " + est.getCultura());
                }
                Spinner dropdown = (Spinner)findViewById(R.id.parcelasdrop);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, items);
                dropdown.setAdapter(adapter);
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
