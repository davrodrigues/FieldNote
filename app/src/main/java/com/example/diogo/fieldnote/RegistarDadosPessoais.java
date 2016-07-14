package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistarDadosPessoais extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    SparseArray<Group> groups = new SparseArray<Group>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser().toString();

        final TextView nome = (TextView) findViewById(R.id.name);
        final EditText data = (EditText) findViewById(R.id.mostrardatanasc);
        final EditText morada = (EditText) findViewById(R.id.mostrarmorada);
        final EditText concelho = (EditText) findViewById(R.id.mostrarconcelho);
        final EditText freguesia = (EditText) findViewById(R.id.mostrarfreguesia);
        final EditText codpostal = (EditText) findViewById(R.id.mostrarcodpostal);
        final EditText telemovel = (EditText) findViewById(R.id.mostrartelemovel);
        final EditText telefone = (EditText) findViewById(R.id.mostrartelefone);
        final EditText email = (EditText) findViewById(R.id.mostraremail);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {


            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String [] userid = mAuth.getCurrentUser().getEmail().toString().split("[.]");
                DadosPessoais dadospessoais = dataSnapshot.child("utilizadores").child(userid[0]).getValue(DadosPessoais.class);

                email.setText(mAuth.getCurrentUser().getEmail().toString());
                nome.setText(dadospessoais.getNome());
                data.setText(dadospessoais.getDatanasc());
                morada.setText(dadospessoais.getMorada());
                concelho.setText(dadospessoais.getConcelho());
                freguesia.setText(dadospessoais.getFreguesia());
                codpostal.setText(dadospessoais.getCodpostal());
                telemovel.setText(dadospessoais.getTelemovel());
                telefone.setText(dadospessoais.getTelefone());

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

            }

        });
        Button adicionar = (Button) findViewById(R.id.dadosButton);
        adicionar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> dados = new HashMap<String, Object>();

                        //nome
                        String name = nome.getText().toString();

                        //morada
                        String mor = morada.getText().toString();
                        mor = mor.isEmpty() == true ? " " : mor;

                        //datanasc
                        String date = data.getText().toString();
                        date = date.isEmpty() == true ? " " : date;

                        //concelho
                        String conc = concelho.getText().toString();
                        conc = conc.isEmpty() == true ? " " : conc;

                        //freguesia
                        String fregu = freguesia.getText().toString();
                        fregu = fregu.isEmpty() == true ? " " : fregu;

                        //codPostal
                        String codp = codpostal.getText().toString();
                        codp = codp.isEmpty() == true ? " " : codp;

                        //telefone
                        String telef = telefone.getText().toString();
                        telef = telef.isEmpty() == true ? " " : telef;

                        //telemovel
                        String telem = telemovel.getText().toString();
                        telem = telem.isEmpty() == true ? " " : telem;

                        //email
                        String mail = email.getText().toString();

                        if (mail.isEmpty()) {
                            email.setError("Obrigatório Indicar o E-mail");
                        } else {

                            dados.put("nome", name);
                            dados.put("datanasc", date);
                            dados.put("concelho", conc);
                            dados.put("freguesia", fregu);
                            dados.put("codpostal", codp);
                            dados.put("morada", mor);
                            dados.put("telefone", telef);
                            dados.put("telemovel", telem);
                            dados.put("email", mail);

                            String [] newmail = mail.split("[.]");
                            childUpdates.put("FieldNote/utilizadores/" + newmail[0] + "/", dados);
                            mDatabase.updateChildren(childUpdates);
                            startActivity(new Intent(getApplicationContext(), RegistarDadosPessoais.class));
                            finish();
                        }

                    }
                });
    }
}
