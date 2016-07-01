package com.example.diogo.fieldnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegistarEstadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_activity_registar_estado);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dropdown = (Spinner)findViewById(R.id.campanhaSpinner);
        String[] items = new String[]{"Batata - 1", "Pessegueiro - 2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner dropdown = (Spinner)findViewById(R.id.campanhaSpinner);
                if(dropdown.getSelectedItem().toString().contains("Batata")) {
                    Spinner estados = (Spinner) findViewById(R.id.estadoSpinner);
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
}}
