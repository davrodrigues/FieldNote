package com.example.diogo.fieldnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MostrarEstadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_estado);

        String[] estados = {"2 - exemplo", "1 - exemplo"};
        ListAdapter estadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, estados);
        ListView estadosView = (ListView) findViewById(R.id.estadosView);
        estadosView.setAdapter(estadosAdapter);

        String[] datas = {"11/09/2016", "21/08/2016"};
        ListAdapter datasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        ListView datasView = (ListView) findViewById(R.id.datesView2);
        datasView.setAdapter(datasAdapter);
    }
}
