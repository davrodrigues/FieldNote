package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParcelasActivity extends AppCompatActivity {

    //db
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelas);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // mDatabase.child(id).removeValue(); TODO remover-me
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int i =0;
                int x = ((int) dataSnapshot.child("parcelas").getChildrenCount());
                String[] datas = new String[x];
                String[] nome_parcelas = new String[x];


                for (DataSnapshot postSnapshot: dataSnapshot.child("parcelas").getChildren()) {
                    Parcela parc = postSnapshot.getValue(Parcela.class);
                    datas[i] = parc.getData_plantação();
                    nome_parcelas[i] = postSnapshot.getKey();
                    i++;
                }
                ListAdapter datasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, datas);
                ListView datasView = (ListView) findViewById(R.id.datasView);
                datasView.setAdapter(datasAdapter);

                ListAdapter parcsAdapater = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nome_parcelas);
                ListView parcsView = (ListView) findViewById(R.id.parcsView);
                parcsView.setAdapter(parcsAdapater);



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





        final ListView datasView = (ListView) findViewById(R.id.datasView);
        final ListView parcsView = (ListView) findViewById(R.id.parcsView);


        //Mostrar produto ao clicar numa linha da lista de Datas
        datasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarParcelaActivity.class);

                Object obj = parcsView.getAdapter().getItem(position);
                intent.putExtra("id", obj.toString());
                startActivity(intent);
            }
        });

        /*
        //Mostrar produto ao clicar numa linha da lista de Produtos
        produtosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarEntradaActivity.class);
                Object obj = datasView.getAdapter().getItem(position);
                Object obj2 = produtosView.getAdapter().getItem(position);
                finish();
                intent.putExtra("id", obj2.toString() + " - " + obj.toString() );
                startActivity(intent);
            }
        });

        //botões de remoção
        remover.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String obj1 = produtosView.getAdapter().getItem(position).toString();
                String obj2 = datasView.getAdapter().getItem(position).toString();

                mDatabase.child("FieldNote/entradas/"+obj1+" - "+obj2).removeValue();

                Toast.makeText(getApplicationContext(),
                        "Produto: "+obj1+" removido com sucesso.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), RegistoEntradasActivity.class);

                finish();
                startActivity(intent);
            }
        });
    */

    }

}