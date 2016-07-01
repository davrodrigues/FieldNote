package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class OrganismosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organismos);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarObservacaoActivity.class));
            }
        });

        String[] dates = {"22/11/2015", "28/11/2015", "30/11/2015"};
        ListAdapter datesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dates);
        ListView datesView = (ListView) findViewById(R.id.datesView);
        datesView.setAdapter(datesAdapter);

        String[] parcelas = {"1", "2", "3"};
        ListAdapter parcelasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parcelas);
        ListView parcelasView = (ListView) findViewById(R.id.parcelasView);
        parcelasView.setAdapter(parcelasAdapter);
    }

}
