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


        Spinner dropdown = (Spinner)findViewById(R.id.dropdownparcelas);
        String[] items = new String[]{"1", "2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown1 = (Spinner)findViewById(R.id.estadoSpinner);
        String[] items1 = new String[]{"Abóbora", "Alface", "Alho", "Arroz", "Batata",
                                        "Beringela", "Cebola", "Cereais", "Feijão", "Melancia", "Melão",
                                        "Milho", "Morango", "Pepino", "Pêssego", "Pimento", "Tomate"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button button = (Button) findViewById(R.id.registarCamp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner parc = (Spinner)findViewById(R.id.dropdownparcelas);
                Spinner cult = (Spinner)findViewById(R.id.estadoSpinner);
                TextView textView = (TextView)findViewById(R.id.dataRegistarEstado);
                String parcela = parc.getSelectedItem().toString();
                String cultura = cult.getSelectedItem().toString();
                String data = textView.getText().toString();
                Map<String, Object> childUpdates = new HashMap<>();
                Map<String, String> dados = new HashMap<String, String>();
                dados.put("Data_de_Plantacao", data);
                dados.put("Parcela", parcela);
                dados.put("Cultura", cultura);
                childUpdates.put("FieldNote/campanhas/"+parcela+"/", dados);
                mDatabase.updateChildren(childUpdates);
                startActivity(new Intent(getApplicationContext(), CampanhasActivity.class));
            }
        });
    }}
