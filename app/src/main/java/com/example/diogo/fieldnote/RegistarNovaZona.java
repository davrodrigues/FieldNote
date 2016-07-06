package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistarNovaZona extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_nova_zona);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.smaller);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button submeter = (Button) findViewById(R.id.zsubmeter);
        submeter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> dados = new HashMap<String, Object>();

                        //nome da zona
                        EditText nome = (EditText)findViewById(R.id.z_nome);
                        String znome = nome.getText().toString();

                        //localidade
                        EditText localidade = (EditText)findViewById(R.id.z_local) ;
                        String loca = localidade.getText().toString();
                        loca = loca.isEmpty()==true?"empty":loca;

                        //modo de producao
                        EditText modo = (EditText)findViewById(R.id.z_modo) ;
                        String zmodo =modo.getText().toString();
                        zmodo= zmodo.isEmpty()==true?"empty":zmodo;

                        //textura do solo
                        EditText solo = (EditText)findViewById(R.id.z_solo) ;
                        String zsolo = solo.getText().toString();
                        zsolo = zsolo.isEmpty()==true?"empty":zsolo;

                        //fitossanidade
                        EditText fito = (EditText)findViewById(R.id.z_fito) ;
                        String zfito =  fito.getText().toString();
                        zfito = zfito.isEmpty()==true?"empty":zfito;



                        dados.put("nomezona", znome);
                        dados.put("localização", loca);
                        dados.put("modoprodução", zmodo);
                        dados.put("solo", zsolo);
                        dados.put("fitossanidade", zfito);


                        childUpdates.put("FieldNote/zonas/" + znome + "/", dados);
                        mDatabase.updateChildren(childUpdates);


                        finish();
                        startActivity(new Intent(getApplicationContext(), ZonasActivity.class));

                    }

                });
    }
}
