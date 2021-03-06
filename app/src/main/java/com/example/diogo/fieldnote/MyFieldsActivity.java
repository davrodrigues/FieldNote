package com.example.diogo.fieldnote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

public class MyFieldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_fields);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //botão zonas
        ImageButton Zonas = (ImageButton) findViewById(R.id.zones_botao);
        Zonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ZonasActivity.class));
            }
        });

        //botão parcelas
        ImageButton Parcelas = (ImageButton) findViewById(R.id.parcelas_botao);
        Parcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ParcelasActivity.class));
            }
        });

        ImageButton campanhas = (ImageButton) findViewById(R.id.campaigns_botao);
        campanhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CampanhasActivity.class));
            }
        });

        ImageButton produzir = (ImageButton) findViewById(R.id.make_cc_botao);
        produzir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dgadr.mamaot.pt/images/docs/prod_sust/c_campo/i005874.pdf")));
            }
        });



    }

}
