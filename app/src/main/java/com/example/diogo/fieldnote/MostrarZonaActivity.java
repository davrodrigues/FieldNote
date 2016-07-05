package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MostrarZonaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_zona);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");


        getSupportActionBar().setTitle("  Zona  "+id);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
             //   Zona zonb = dataSnapshot.child("zonas").child(id).getValue(Zona.class);
                Zona zone = dataSnapshot.child("zonas/"+id).getValue(Zona.class);


                TextView nzona = (TextView) findViewById(R.id.nome_zona);
                nzona.setText(zone.getNomezona());

                 System.out.println("conteudo de nzona: "+nzona.toString());


                TextView area = (TextView) findViewById(R.id.mostrar_area);
                area.setText(zone.getArea());

                TextView fitossanidade = (TextView) findViewById(R.id.mostrar_fito);
                fitossanidade.setText(zone.getFitossanidade());

                TextView localizacao = (TextView) findViewById(R.id.mostrar_local);
                localizacao.setText(zone.getLocalização());

                TextView modo_producao = (TextView) findViewById(R.id.mostrar_producao);
                modo_producao.setText(zone.getModoprodução());

                TextView solo = (TextView) findViewById(R.id.mostrar_solo);
                solo.setText(zone.getSolo());

                int x = ((int) dataSnapshot.child("zonas/"+id+"/parcelas").getChildrenCount());
                String[] nparcelas = new String[x];
                String[] culturas = new String[x];
                int i =0;


                if (x!=0){
                for (DataSnapshot postSnapshot: dataSnapshot.child("zonas/"+id+"/parcelas").getChildren()) {
                    nparcelas[i]=postSnapshot.getKey().toString();
                    culturas[i] =postSnapshot.getValue().toString();
                    i++;
                }

                // parcelas
                ListAdapter parcelsAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nparcelas);
                ListView parcelsViews = (ListView) findViewById(R.id.parcelsView);
                parcelsViews.setAdapter(parcelsAdapter);

                // culturas
                ListAdapter culturasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, culturas);
                ListView campViews = (ListView) findViewById(R.id.campView);
                campViews.setAdapter(culturasAdapter);

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed");
            }



        });

    }
}
