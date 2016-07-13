package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

        //spinner modo de produção
        String[] modo_producao = new String[]{"Convencional","PDI",
                "Agr. Biológica", "Conversão C1", "Conversão C2", "Conversão C3"};
        final Spinner spin_modo = (Spinner)findViewById(R.id.z_modo);
        ArrayAdapter<String> modoAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, modo_producao);
        spin_modo.setAdapter(modoAdapter);

        //spinner Textura do Solo
        String[] textura_solo = new String[]{"Argiloso","Arenoso","Franco","Franco-Arenoso","Franco-Argiloso"};
        final Spinner spin_solo = (Spinner)findViewById(R.id.z_solo);
        ArrayAdapter<String> soloAdapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, textura_solo);
        spin_solo.setAdapter(soloAdapter);
        
        
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button submeter = (Button) findViewById(R.id.zsubmeter);
        submeter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //nome da zona
                        EditText nome = (EditText)findViewById(R.id.z_nome);
                        String znome = nome.getText().toString();

                        if (znome.isEmpty()){
                            nome.setError("O nome da zona é obrigatório!");

                        }
                        else {

                            Map<String, Object> childUpdates = new HashMap<>();
                            Map<String, Object> dados = new HashMap<String, Object>();

                            //localidade
                            EditText localidade = (EditText)findViewById(R.id.z_local) ;
                            String loca = localidade.getText().toString();
                            loca = loca.isEmpty()==true?"vazio":loca;

                            //fitossanidade
                            EditText fito = (EditText)findViewById(R.id.z_fito) ;
                            String zfito =  fito.getText().toString();
                            zfito = zfito.isEmpty()==true?"empty":zfito;

                            //spinners
                            String zmodo =spin_modo.getSelectedItem().toString();
                            String zsolo =spin_solo.getSelectedItem().toString();


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
                    }
                });
    }
}
