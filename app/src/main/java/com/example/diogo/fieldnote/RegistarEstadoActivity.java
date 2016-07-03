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

import com.google.firebase.firebase_core.*;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class RegistarEstadoActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_estado);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Spinner dropdown = (Spinner)findViewById(R.id.campanhaSpinner);
        String[] items = new String[]{"1 - Pessegueiro", "2 - Batata"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner dropdown = (Spinner)findViewById(R.id.campanhaSpinner);
                if(dropdown.getSelectedItem().toString().contains("Batata")) {
                    Spinner estados = (Spinner)findViewById(R.id.estadoSpinner);
                    String[] values = new String[] {"A - batata", "B - batata", "C - batata"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistarEstadoActivity.this, android.R.layout.simple_spinner_dropdown_item, values);
                    estados.setAdapter(adapter);
                }
                if(dropdown.getSelectedItem().toString().contains("Pessegueiro")) {
                    Spinner estados = (Spinner) findViewById(R.id.estadoSpinner);
                    String[] values = new String[] {"A - pessego", "B - pessego", "C - pessego"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistarEstadoActivity.this, android.R.layout.simple_spinner_dropdown_item, values);
                    estados.setAdapter(adapter);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button button = (Button) findViewById(R.id.estadoButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner campanha = (Spinner)findViewById(R.id.campanhaSpinner);
                Spinner estado = (Spinner)findViewById(R.id.estadoSpinner);
                TextView textView = (TextView)findViewById(R.id.dataRegistarEstado);
                String camp = campanha.getSelectedItem().toString();
                String estado1 = estado.getSelectedItem().toString();
                String data = textView.getText().toString();
                Map<String, Object> childUpdates = new HashMap<>();
                Map<String, String> dados = new HashMap<String, String>();
                dados.put("Estado", estado1);
                dados.put("Data", data);
                StringTokenizer str = new StringTokenizer(camp);
                dados.put("Parcela", str.nextToken());
                str.nextToken();
                dados.put("Campanha", str.nextToken());
                childUpdates.put("FieldNote/estados/"+camp+"/", dados);
                mDatabase.updateChildren(childUpdates);
                startActivity(new Intent(getApplicationContext(), EstadosFenologicosActivity.class));
            }
        });
}}
