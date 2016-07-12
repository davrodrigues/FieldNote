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

public class EditarIntervencaoTecnicaActivity extends AppCompatActivity {

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
        getSupportActionBar().setTitle("Editar Intervenção Técnica");

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        //partir a string
        String partes[] = id.split(" - ");


        //data
        final String obj1 = partes[0];
        //operador
        final String obj2 = partes[1];
        //zona
        final String obj3 = partes[2];

        //declaração dos textviews

        //data
        final TextView data = (TextView) findViewById(R.id.dataObs);

        //just. motivo
        final EditText motivo = (EditText)findViewById(R.id.edit1);

        //justificação
        final EditText quantificadorJustificacao = (EditText)findViewById(R.id.edit2);

        //praga
        final EditText praga = (EditText)findViewById(R.id.edit3);

        //quantificador risco
        final EditText quantificadorRisco = (EditText)findViewById(R.id.edit4);

        //tipo rega
        final EditText tipo = (EditText) findViewById(R.id.edit5);

        //equipamento
        final EditText equipamento = (EditText)findViewById(R.id.edit6);

        // débito
        final EditText debito = (EditText) findViewById(R.id.edit7);

        //fertlizante
        final EditText fertilizante = (EditText) findViewById(R.id.edit8);

        //adubo
        final EditText adubo = (EditText) findViewById(R.id.edit9);

        //especies
        final EditText especies = (EditText) findViewById(R.id.edit10);

        //meio
        final EditText meio = (EditText) findViewById(R.id.edit11);

        //tratamento
        final EditText quantificadorTratamento = (EditText) findViewById(R.id.edit12);

        //colheita
        final EditText colheita = (EditText) findViewById(R.id.edit13);

        //quantificador produção
        final EditText quantificadorProducao = (EditText)findViewById(R.id.edit14);

        //operador
        final EditText operador = (EditText)findViewById(R.id.edit15);

        //area
        final EditText area = (EditText)findViewById(R.id.edit16);

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
                //preenche o spinner com zonas existentes
                Spinner dropdown = (Spinner) findViewById(R.id.spinner_zone);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, items);
                dropdown.setAdapter(adapter);

                //pre seleciona a zona
                dropdown.setSelection(getIndex(dropdown, obj3));

            //Pré-preencher os campos com dados anteriores
                IntervencaoTecnica itec = dataSnapshot.child("Intervencoes").child(id).getValue(IntervencaoTecnica.class);

                data.setText(itec.getData());  //passar a data ao textview

                motivo.setText(itec.getMotivo1());  //just. motivo

                quantificadorJustificacao.setText(itec.getQuantificadorJustificacao()); //justificação

                praga.setText(itec.getPraga()); //praga

                quantificadorRisco.setText(itec.getQuantificadorRisco()); //quantificador risco

                tipo.setText(itec.getTipo());//tipo rega

                equipamento.setText(itec.getEquipamento());  //equipamento

                debito.setText(itec.getDebito()); // débito

                fertilizante.setText(itec.getFertilizante());  //fertlizante

                adubo.setText(itec.getAdubo()); //adubo

                especies.setText(itec.getEspecies()); //especies

                meio.setText(itec.getMeio()); //meio

                quantificadorTratamento.setText(itec.getQuantificadorTratamento()); //tratamento

                colheita.setText(itec.getColheita()); //colheita

                quantificadorProducao.setText(itec.getQuantificadorProducao());   //quantificador produção

                operador.setText(itec.getOperador());   //operador

                area.setText(itec.getArea()); //area

            }
            //get spinner index of myString
            private int getIndex(Spinner spinner, String myString) {
                int index = 0;
                for (int i=0;i<spinner.getCount();i++){
                    if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                        index = i;
                        break;
                    }
                }
                return index;
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
                        String date = data.getText().toString();
                        //zona selecionada do spinner
                        String spin_zona = dropdown.getSelectedItem().toString();

                        //justificação
                        String mot = motivo.getText().toString();
                        String qJustificacao = quantificadorJustificacao.getText().toString();
                        mot = mot.isEmpty()==true? " ":mot;
                        mot = qJustificacao.isEmpty()==true? " ":qJustificacao;

                        //Estimativa
                        String prag = praga.getText().toString();
                        String quantificadorR = quantificadorRisco.getText().toString();
                        prag = prag.isEmpty()==true? " ":prag;
                        quantificadorR  = quantificadorR.isEmpty()==true? " ":quantificadorR;

                        //OperCultural
                        String type = tipo.getText().toString();
                        String equip = equipamento.getText().toString();
                        type = type.isEmpty()==true? " ":type;
                        equip = equip.isEmpty()==true? " ":equip;

                        //Irrigacao
                        String debit = debito.getText().toString();
                        String ferti = fertilizante.getText().toString();
                        debit = debit.isEmpty()==true? " ":debit;
                        ferti = ferti.isEmpty()==true? " ":ferti;

                        //Fertilização
                        String adu = adubo.getText().toString();
                        String espec = especies.getText().toString();
                        adu = adu.isEmpty()==true? " ":adu;
                        espec = espec.isEmpty()==true? " ":espec;

                        //tratamento fito...
                        String mei = meio.getText().toString();
                        String quantificadorT = quantificadorTratamento.getText().toString();
                        mei = mei.isEmpty()==true? " ":mei;
                        quantificadorT = quantificadorT.isEmpty()==true? " ":quantificadorT;

                        //producao vendas
                        String colhe = colheita.getText().toString();
                        String quantificadorP = quantificadorProducao.getText().toString();
                        colhe = colhe.isEmpty()==true? " ":colhe;
                        quantificadorP = quantificadorP.isEmpty()==true? " ":quantificadorP;

                        //Visitas
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

                            //se ocorreu alteração na chave apaga o nó
                            if((obj1 !=date) ||(obj2!=oper) ||(obj3!=spin_zona))
                                mDatabase.child("FieldNote/Intervencoes/" + obj1 +" - "+ obj2+" - " + obj3 + "/").removeValue();

                            //cria novo nó com as mudanças
                            childUpdates.put("FieldNote/Intervencoes/" + date +" - "+ oper+" - " + spin_zona + "/", dados);
                            mDatabase.updateChildren(childUpdates);
                            finish();
                            startActivity(new Intent(getApplicationContext(), IntervencaoTecnicaActivity.class));
                        }

                    }

                });
    }
}