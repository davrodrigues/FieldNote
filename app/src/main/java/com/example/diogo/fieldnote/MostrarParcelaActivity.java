package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MostrarParcelaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_parcela);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");


        getSupportActionBar().setTitle(" Parcela "+id);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Parcela par = dataSnapshot.child("parcelas").child(id).getValue(Parcela.class);


                TextView Produto = (TextView) findViewById(R.id.par_zona);
                Produto.setText(par.getZona());

                TextView Fornecedor = (TextView) findViewById(R.id.par_fert);
                Fornecedor.setText(par.getFertilização());

                TextView Fabricante = (TextView) findViewById(R.id.par_rega);
                Fabricante.setText(par.getTipo_rega());

                TextView Quantidade = (TextView) findViewById(R.id.par_area);
                Quantidade.setText(par.getÁrea());


                int x = ((int) dataSnapshot.child("parcelas/"+id+"/campanhas").getChildrenCount());
                String[] data_planta = new String[x];
                String[] culturas = new String[x];
                int i =0;

                //se a parcelas tiver campanhas então lista-as
                if (x!=0) {
                    for (DataSnapshot postSnapshot : dataSnapshot.child("parcelas/" + id + "/campanhas").getChildren()) {
                        data_planta[i] = postSnapshot.child("Data_de_Plantacao").getValue().toString();
                        culturas[i] = postSnapshot.child("Cultura").getValue().toString();
                        i++;
                    }


                    // parcelas
                    ListAdapter dataAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, data_planta);
                    ListView datapView = (ListView) findViewById(R.id.datapView);
                    datapView.setAdapter(dataAdapter);

                    // culturas
                    ListAdapter culturasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, culturas);
                    ListView culturasView = (ListView) findViewById(R.id.culturaView);
                    culturasView.setAdapter(culturasAdapter);
                }
                else {
                    String[] vazio = new String[] {"vazio"};

                    // parcelas
                    ListAdapter dataAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, vazio);
                    ListView datapView = (ListView) findViewById(R.id.datapView);
                    datapView.setAdapter(dataAdapter);

                    // culturas
                    ListAdapter culturasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, vazio);
                    ListView culturasView = (ListView) findViewById(R.id.culturaView);
                    culturasView.setAdapter(culturasAdapter);

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

        /*

        final ListView culturaView = (ListView) findViewById(R.id.culturaView);
     //   final ListView localView = (ListView) findViewById(R.id.localView);
      //  final ListView nparcelasView = (ListView) findViewById(R.id.nparcelasView);

        //Mostrar zona ao clicar na letra da zona
        culturaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarCampanhaActivity.class);
                finish();
                intent.putExtra("id", ids );


                startActivity(intent);
            }
        });

        */

    }
}
