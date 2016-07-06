package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistarNovaParcela extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_nova_parcela);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.smaller);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i=0;
                //spinner zonas
                int capacity = (int)dataSnapshot.child("zonas").getChildrenCount();
                String[] items = new String[capacity];
                for (DataSnapshot postSnapshot: dataSnapshot.child("zonas").getChildren()) {
                    String chave = postSnapshot.getKey();
                    items[i++] = chave;
                }
                Spinner dropdown = (Spinner)findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, items);
                dropdown.setAdapter(adapter);

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





        final Spinner dropdown =(Spinner) findViewById(R.id.spinner);
        Button submeter = (Button) findViewById(R.id.guardar);
        submeter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> dados = new HashMap<String, Object>();

                        //data
                        TextView p_nome = (TextView)findViewById(R.id.pnome);
                        String pnome = p_nome.getText().toString();

                        //nome do produto
                        EditText p_fertilizacao = (EditText)findViewById(R.id.pfertilizacao) ;
                        String pfert =  p_fertilizacao.getText().toString();
                        pfert = pfert.isEmpty()==true?"empty":pfert;

                        //fornecedor
                        EditText p_rega = (EditText)findViewById(R.id.prega) ;
                        String prega =p_rega.getText().toString();
                        prega= prega.isEmpty()==true?"empty":prega;

                        //fabricante
                        EditText p_area = (EditText)findViewById(R.id.parea) ;
                        String parea = p_area.getText().toString();
                        parea = parea.isEmpty()==true?"0":parea;


                        //zona selecionada do spinner
                      String spin_zona =dropdown.getSelectedItem().toString();

                        dados.put("fertilização", pfert);
                        dados.put("tipo_rega", prega);
                        dados.put("zona",spin_zona );
                        dados.put("área", parea);

                        //preencher o nó parcela
                        childUpdates.put("FieldNote/parcelas/" + pnome + "/", dados);

                        //preencher o nó zona
                        childUpdates.put("FieldNote/zonas/" + spin_zona + "/parcelas/"+pnome,"empty");
                        mDatabase.updateChildren(childUpdates);


                        finish();
                       startActivity(new Intent(getApplicationContext(), ZonasActivity.class));

                    }

                });



    }


}
