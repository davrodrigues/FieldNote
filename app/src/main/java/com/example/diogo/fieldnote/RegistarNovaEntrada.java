package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistarNovaEntrada extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_nova_entrada);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.smaller);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button submeter = (Button) findViewById(R.id.submeter);
        submeter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> dados = new HashMap<String, Object>();

                        //data
                        TextView textView = (TextView)findViewById(R.id.data_entrada);
                        String data = textView.getText().toString();

                        //nome do produto
                        EditText nome_produto = (EditText)findViewById(R.id.nome_prod) ;
                        String nprod =  nome_produto.getText().toString();

                        //fornecedor
                        EditText nome_fornecedor = (EditText)findViewById(R.id.fornecedor) ;
                        String nforn =nome_fornecedor.getText().toString();
                        nforn= nforn.isEmpty()==true?"empty":nforn;

                        //fabricante
                        EditText nome_fabricante = (EditText)findViewById(R.id.fabricante) ;
                        String nfabr = nome_fabricante.getText().toString();
                        nfabr = nfabr.isEmpty()==true?"empty":nfabr;

                        //quantidade
                        EditText quantidade = (EditText)findViewById(R.id.quantidade) ;
                        String quant =  quantidade.getText().toString();
                        quant = quant.isEmpty()==true?"0":quant;

                        //observações
                        TextView obser = (TextView)findViewById(R.id.observações);
                        String obs = obser.getText().toString();
                        obs = obs.isEmpty()==true?"empty":obs;

                        dados.put("data", data);
                        dados.put("fabricante", nfabr);
                        dados.put("fornecedor", nforn);
                        dados.put("observações", obs);
                        dados.put("produto", nprod);
                        dados.put("quantidade", quant);

                        childUpdates.put("FieldNote/entradas/" + nprod + " - " + data + "/", dados);
                        mDatabase.updateChildren(childUpdates);


                        finish();
                        startActivity(new Intent(getApplicationContext(), RegistoEntradasActivity.class));

                    }

                });

    }

}
