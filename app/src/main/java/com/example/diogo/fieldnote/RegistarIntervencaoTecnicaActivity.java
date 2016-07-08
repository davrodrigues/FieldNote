package com.example.diogo.fieldnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistarIntervencaoTecnicaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_intervencao_tecnica);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i = 0;
                //spinner zonas
                int capacity = (int) dataSnapshot.child("zonas").getChildrenCount();
                String[] items = new String[capacity];
                for (DataSnapshot postSnapshot : dataSnapshot.child("zonas").getChildren()) {
                    String chave = postSnapshot.getKey();
                    items[i++] = chave;
                }
                Spinner dropdown = (Spinner) findViewById(R.id.spinner_zone);

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


        final Spinner dropdown = (Spinner) findViewById(R.id.spinner_zone);
        Button adicionar = (Button) findViewById(R.id.intervencaoButton);
        adicionar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> childUpdates = new HashMap<>();
                        Map<String, Object> dados = new HashMap<String, Object>();

                        //data
                        TextView data = (TextView) findViewById(R.id.dataObs);
                        String date = data.getText().toString();

                        //zona selecionada do spinner
                        String spin_zona = dropdown.getSelectedItem().toString();

                        //justificação

                        EditText motivo = (EditText)findViewById(R.id.edit1);
                        EditText quantificadorJustificacao = (EditText)findViewById(R.id.edit2);
                        String mot = motivo.getText().toString();
                        String qJustificacao = quantificadorJustificacao.getText().toString();
                        mot = mot.isEmpty()==true? " ":mot;
                        mot = qJustificacao.isEmpty()==true? " ":qJustificacao;

                        //Estimativa

                        EditText praga = (EditText)findViewById(R.id.edit3);
                        EditText quantificadorRisco = (EditText)findViewById(R.id.edit4);
                        String prag = praga.getText().toString();
                        String quantificadorR = quantificadorRisco.getText().toString();
                        prag = prag.isEmpty()==true? " ":prag;
                        quantificadorR  = quantificadorR.isEmpty()==true? " ":quantificadorR;

                        //OperCultural

                        EditText tipo = (EditText) findViewById(R.id.edit5);
                        EditText equipamento = (EditText)findViewById(R.id.edit6);
                        String type = tipo.getText().toString();
                        String equip = equipamento.getText().toString();
                        type = type.isEmpty()==true? " ":type;
                        equip = equip.isEmpty()==true? " ":equip;

                        //Irrigacao

                        EditText debito = (EditText) findViewById(R.id.edit7);
                        EditText fertilizante = (EditText) findViewById(R.id.edit8);
                        String debit = debito.getText().toString();
                        String ferti = fertilizante.getText().toString();
                        debit = debit.isEmpty()==true? " ":debit;
                        ferti = ferti.isEmpty()==true? " ":ferti;

                        //Fertilização

                        EditText adubo = (EditText) findViewById(R.id.edit9);
                        EditText especies = (EditText) findViewById(R.id.edit10);
                        String adu = adubo.getText().toString();
                        String espec = especies.getText().toString();
                        adu = adu.isEmpty()==true? " ":adu;
                        espec = espec.isEmpty()==true? " ":espec;

                        //tratamento fito...

                        EditText meio = (EditText) findViewById(R.id.edit11);
                        EditText quantificadorTratamento = (EditText) findViewById(R.id.edit12);
                        String mei = meio.getText().toString();
                        String quantificadorT = quantificadorTratamento.getText().toString();
                        mei = mei.isEmpty()==true? " ":mei;
                        quantificadorT = quantificadorT.isEmpty()==true? " ":quantificadorT;

                        //producao vendas

                        EditText colheita = (EditText) findViewById(R.id.edit13);
                        EditText quantificadorProducao = (EditText)findViewById(R.id.edit14);
                        String colhe = colheita.getText().toString();
                        String quantificadorP = quantificadorProducao.getText().toString();
                        colhe = colhe.isEmpty()==true? " ":colhe;
                        quantificadorP = quantificadorP.isEmpty()==true? " ":quantificadorP;


                        //Visitas
                        EditText operador = (EditText)findViewById(R.id.edit15);
                        EditText area = (EditText)findViewById(R.id.edit16);
                        String oper = operador.getText().toString();
                        String ar = area.getText().toString();
                        ar = ar.isEmpty()==true? " ":ar;

                        if(oper.isEmpty()){
                            operador.setError("Obrigatório Indicar o Operador");
                        }
                       else if(date.isEmpty()){
                            data.setError("Obrigatório Indicar a Data");
                        }
                        else {
                            dados.put("zona", spin_zona);
                            dados.put("data",date);
                            dados.put("motivo1",mot);
                            dados.put("quantificadorJustificacao", qJustificacao);
                            dados.put("praga",prag);
                            dados.put("quantificadorRisco", quantificadorR);
                            dados.put("tipo",type);
                            dados.put("equipamento",equip);
                            dados.put("debito",debit);
                            dados.put("fertilizante",ferti);
                            dados.put("adubo",adu);
                            dados.put("especies",espec);
                            dados.put("meio",mei);
                            dados.put("quantificadorTratamento",quantificadorT);
                            dados.put("colheita",colhe);
                            dados.put("quantificadorProducao",quantificadorP);
                            dados.put("operador",oper);
                            dados.put("area",ar);

                            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQ"+date +" - "+ oper+" - " + spin_zona + "/");
                            childUpdates.put("FieldNote/Intervencoes/" + date +" - "+ oper+" - " + spin_zona + "/", dados);
                            mDatabase.updateChildren(childUpdates);
                            finish();
                            startActivity(new Intent(getApplicationContext(), IntervencaoTecnicaActivity.class));
                        }




                    }

                });


    }
}