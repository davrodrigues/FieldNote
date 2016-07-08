package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);


        ImageButton meusCampos = (ImageButton) findViewById(R.id.meus_campos_botao);
        meusCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyFieldsActivity.class));
            }
        });

        ImageButton registoEntradas = (ImageButton) findViewById(R.id.registo_entradas_botao);
        registoEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistoEntradasActivity.class));
            }
        });

        ImageButton intervTecnicas = (ImageButton) findViewById(R.id.intervencoes_tecnicas_botao);
        intervTecnicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), IntervencaoTecnica.class));
            }
        });

        ImageButton estatisticas = (ImageButton) findViewById(R.id.estatisticas_botao);
        estatisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Estatisticas.class));
            }
        });

        ImageButton registarEstado = (ImageButton) findViewById(R.id.registo_estados_fenologicos_botao);
        registarEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EstadosFenologicosActivity.class));
            }
        });

        ImageButton organismos = (ImageButton) findViewById(R.id.observacoes_organismos_botao);
        organismos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrganismosActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        //menu overflow
        switch(item.getItemId()){

            case R.id.dados:
                Toast.makeText(getBaseContext(), "You selected dados pessoais", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tutorial:
                Toast.makeText(getBaseContext(), "You selected tutorial", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ajuda:
                Toast.makeText(getBaseContext(), "You selected ajuda", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sair:
                Toast.makeText(getBaseContext(), "You selected sair", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;

    }
}
