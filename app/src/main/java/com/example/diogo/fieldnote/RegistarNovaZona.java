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
                        TextView znome = (TextView)findViewById(R.id.z_nome);
                        String nome_z = znome.getText().toString();

                        //localidade
                        EditText localidade = (EditText)findViewById(R.id.z_local) ;
                        String loca = localidade.getText().toString();

                        //modo de producao
                        EditText nome_fornecedor = (EditText)findViewById(R.id.z_modo) ;
                        String nforn =nome_fornecedor.getText().toString();
                        nforn= nforn.isEmpty()==true?"empty":nforn;

                        //textura do solo
                        EditText nome_fabricante = (EditText)findViewById(R.id.z_solo) ;
                        String nfabr = nome_fabricante.getText().toString();
                        nfabr = nfabr.isEmpty()==true?"empty":nfabr;

                        //fitossanidade
                        EditText quantidade = (EditText)findViewById(R.id.z_fito) ;
                        String quant =  quantidade.getText().toString();
                        quant = quant.isEmpty()==true?"0":quant;

/*

                        dados.put("nomezona", nome_z);
                        dados.put("fabricante", nfabr);
                        dados.put("fornecedor", nforn);
                        dados.put("observações", obs);
                        dados.put("localização", loca);
                        dados.put("quantidade", quant);
*/
                        childUpdates.put("FieldNote/entradas/" + loca + " - " + nome_z + "/", dados);
                        mDatabase.updateChildren(childUpdates);


                        finish();
                        startActivity(new Intent(getApplicationContext(), RegistoEntradasActivity.class));

                    }

                });
    }
}
