package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class RegistarCampanhaActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_campanha);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner parcelas = (Spinner)findViewById(R.id.dropdownparcelas);
        final Spinner culturas = (Spinner)findViewById(R.id.estadoSpinner);
        final TextView textView = (TextView)findViewById(R.id.dataRegistarEstado);

        String[] items1 = new String[]{"Abóbora", "Alface", "Alho", "Arroz", "Batata",
                                        "Beringela", "Cebola", "Cereais", "Feijão", "Melancia", "Melão",
                                        "Milho", "Morango", "Pepino", "Pêssego", "Pimento", "Tomate"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        culturas.setAdapter(adapter1);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i = 0;
                int capacity = (int)dataSnapshot.child("parcelas").getChildrenCount();
                String[] items = new String[capacity];
                for (DataSnapshot postSnapshot: dataSnapshot.child("parcelas").getChildren()) {
                    Parcela par = postSnapshot.getValue(Parcela.class);
                    items[i++] = (postSnapshot.getKey())+ " - " + par.getZona();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, items);
                parcelas.setAdapter(adapter);
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

        Button button = (Button) findViewById(R.id.registarCamp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textView.getText().toString().isEmpty())
                    textView.setError("Introduza uma data de plantação");
                else {
                    String parcela = parcelas.getSelectedItem().toString();
                    StringTokenizer str = new StringTokenizer(parcela);
                    String cultura = culturas.getSelectedItem().toString();
                    String data = textView.getText().toString();
                    Map<String, Object> childUpdates = new HashMap<>();
                    Map<String, String> dados = new HashMap<String, String>();
                    Map<String, String> parcChild = new HashMap<String, String>();
                    String nomeParcela = str.nextToken();
                    dados.put("Data_de_Plantacao", data);
                    dados.put("Parcela", nomeParcela);
                    str.nextToken();
                    dados.put("Cultura", cultura);
                    parcChild.put("Data_de_Plantacao", data);
                    parcChild.put("Cultura", cultura);
                    childUpdates.put("FieldNote/campanhas/" + nomeParcela + "/", dados);
                    childUpdates.put("FieldNote/parcelas/" + nomeParcela + "/campanhas/" + data, parcChild);
                    childUpdates.put("FieldNote/zonas/" + str.nextToken() + "/parcelas/" + nomeParcela, cultura);
                    mDatabase.updateChildren(childUpdates);
                    finish();
                    startActivity(new Intent(getApplicationContext(), CampanhasActivity.class));
                }
            }
        });
    }}
