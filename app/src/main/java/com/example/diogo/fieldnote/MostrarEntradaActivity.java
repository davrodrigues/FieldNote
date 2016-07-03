package com.example.diogo.fieldnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MostrarEntradaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_entrada);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");


        getSupportActionBar().setTitle(id);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entrada ent = dataSnapshot.child("entradas").child(id).getValue(Entrada.class);

                TextView data = (TextView) findViewById(R.id.data_entry);
                data.setText(ent.getData());

                TextView Produto = (TextView) findViewById(R.id.nome_produto);
                Produto.setText(ent.getProduto());

                TextView Fornecedor = (TextView) findViewById(R.id.forn);
                Fornecedor.setText(ent.getFornecedor());

                TextView Fabricante = (TextView) findViewById(R.id.fabr);
                Fabricante.setText(ent.getFabricante());

                TextView Quantidade = (TextView) findViewById(R.id.qx);
                Quantidade.setText(""+ent.getQuantidade());

                TextView Observacoes = (TextView) findViewById(R.id.obs);
                Observacoes.setText(ent.getObservações());


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
