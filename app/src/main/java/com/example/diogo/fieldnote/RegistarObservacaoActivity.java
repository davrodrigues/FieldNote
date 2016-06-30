package com.example.diogo.fieldnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class RegistarObservacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_observacao);

        Spinner dropdown = (Spinner)findViewById(R.id.parcelasdrop);
        String[] items = new String[]{"1", "2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        String[] pragas = {"mosca branca", "peste"};
        ListAdapter pragasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pragas);
        ListView datesView = (ListView) findViewById(R.id.pragasView);
        datesView.setAdapter(pragasAdapter);
    }
}
