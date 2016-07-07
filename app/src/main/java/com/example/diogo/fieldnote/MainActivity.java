package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

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
                startActivity(new Intent(getApplicationContext(), RegistoEntradas.class));
            }
        });

        ImageButton intervTecnicas = (ImageButton) findViewById(R.id.intervencoes_tecnicas_botao);
        intervTecnicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), IntervencaoTecnicaActivity.class));
            }
        });

        ImageButton estatistitcas = (ImageButton) findViewById(R.id.estatistitcas_botao);
        estatistitcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Estatistitcas.class));
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


    //meus_campos_botao
}
