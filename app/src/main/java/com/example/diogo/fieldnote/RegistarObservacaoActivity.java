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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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

        Button fab = (Button) findViewById(R.id.testeView);
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
                String pragav1 = praga1.getText().toString();
                TextView praga2 = (TextView)findViewById(R.id.praga2);
                String pragav2 = praga2.getText().toString();
                TextView praga3 = (TextView)findViewById(R.id.praga3);
                String pragav3 = praga3.getText().toString();

                TextView doencas1 = (TextView)findViewById(R.id.doencas1);
                String doencasv1 = doencas1.getText().toString();
                TextView doencas2 = (TextView)findViewById(R.id.doencas2);
                String doencasv2 = doencas2.getText().toString();
                TextView doencas3 = (TextView)findViewById(R.id.doencas3);
                String doencasv3 = doencas3.getText().toString();

                TextView auxiliares1 = (TextView)findViewById(R.id.auxiliares1);
                String auxiliaresv1 = auxiliares1.getText().toString();
                TextView auxiliares2 = (TextView)findViewById(R.id.auxiliares2);
                String auxiliaresv2 = auxiliares2.getText().toString();
                TextView auxiliares3 = (TextView)findViewById(R.id.auxiliares3);
                String auxiliaresv3 = auxiliares3.getText().toString();

                TextView obser = (TextView)findViewById(R.id.observações);
                String obs = obser.getText().toString();

                pragas.put(pragav1, pragav1);
                pragas.put(pragav2, pragav2);
                pragas.put(pragav3, pragav3);

                doencas.put(doencasv1, doencasv1);
                doencas.put(doencasv2, doencasv2);
                doencas.put(doencasv3, doencasv3);

                auxiliares.put(auxiliaresv1, auxiliaresv1);
                auxiliares.put(auxiliaresv2, auxiliaresv2);
                auxiliares.put(auxiliaresv3, auxiliaresv3);

                dados.put("Pragas", pragas);
                dados.put("Doencas", doencas);
                dados.put("Auxiliares", auxiliares);
                dados.put("Observacoes", obs);
                dados.put("Data", data );
                dados.put("Parcela", parc);
                childUpdates.put("FieldNote/observações/" + parc + " - " + data + "/", dados);
                mDatabase.updateChildren(childUpdates);
                startActivity(new Intent(getApplicationContext(), OrganismosActivity.class));
            }
        });


        Spinner dropdown = (Spinner)findViewById(R.id.parcelasdrop);
        String[] items = new String[]{"1", "2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}
