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

public class EstadosFenologicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estados_fenologicos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarEstadoActivity.class));
            }
        });

        String[] campanhas = {"Batata", "Pessegueiro", "Melão"};
        ListAdapter campanhasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, campanhas);
        ListView campanhasView = (ListView) findViewById(R.id.campanhasView);
        campanhasView.setAdapter(campanhasAdapter);

        String[] parcelas = {"1", "2", "3"};
        ListAdapter parcelasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parcelas);
        ListView parcelasView = (ListView) findViewById(R.id.parcelas2View);
        parcelasView.setAdapter(parcelasAdapter);


    }

}
