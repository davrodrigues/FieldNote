package com.example.diogo.fieldnote;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashMap;

public class RegistoEntradasActivity extends AppCompatActivity {
    //db
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_entradas);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //botao adicionar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistarNovaEntrada.class));
            }
        });

        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i =0;
                int x = ((int) dataSnapshot.child("entradas").getChildrenCount());
                String[] datas = new String[x];
                String[] produto = new String[x];
                for (DataSnapshot postSnapshot: dataSnapshot.child("entradas").getChildren()) {
                    Entrada ent = postSnapshot.getValue(Entrada.class);
                    datas[i] = ent.getData();
                    produto[i] = ent.getProduto();
                    i++;
                }
                ListAdapter datasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, datas);
                ListView datasView = (ListView) findViewById(R.id.datasView);
                datasView.setAdapter(datasAdapter);
                ListAdapter produtosAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, produto);
                ListView produtosView = (ListView) findViewById(R.id.produtosView);
                produtosView.setAdapter(produtosAdapter);
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
        final ListView produtosView = (ListView) findViewById(R.id.produtosView);

        //id: produto - data
       datasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarEntradaActivity.class);
                Object obj = datasView.getAdapter().getItem(position);
                Object obj2 = produtosView.getAdapter().getItem(position);
                intent.putExtra("id", obj2.toString() + " - " + obj.toString() );
                startActivity(intent);
            }
        });

    }
}


