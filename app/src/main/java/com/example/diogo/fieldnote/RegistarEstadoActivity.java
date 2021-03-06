package com.example.diogo.fieldnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.*;
import com.google.firebase.auth.*;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class RegistarEstadoActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_estado);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Spinner campParcela = (Spinner)findViewById(R.id.dropdownparcelas);
        final Spinner estado = (Spinner)findViewById(R.id.estadoSpinner);
        final TextView textView = (TextView)findViewById(R.id.dataRegistarEstado);
        campParcela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] values =null;
                if(campParcela.getSelectedItem().toString().contains("Batata")) {
                    values = new String[] {"1 - Tubérculo", "2 - Rebentos","3 - Inicio da formação de raízes","4 - Emergência","5 - Desenvolvimento das folhas","6 - Desenvolvimento das partes vegetativas","7 - Botões florais visiveis", "8 - Pétalas visiveis","9 - Floração","10 - Formação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Milho")) {
                    values =  new String[] {"1 - Pré-emergência","2 - Emergência","3 - 1 Folha","4 - 2 Folhas","5 - 3 Folhas","6 - 5 Folhas","7 - 4º Nó","8 - Fase joalheiro","9 - Floração feminina","10 - Maturação"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Pimento")) {
                    values = new String[] {"1 - Emergência","2 - Cotilédones completamente desenvolvidos","3 - 2ª Folha","4 - Desenvolvimento das restantes folhas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Tomate")) {
                    values =new String[] {"1 - Emergência","2 - Cotilédones completamente desenvolvidos","3 - 2ª Folha","4 - Desenvolvimento das restantes folhas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Beringela")) {
                    values = new String[] {"1 - Emergência","2 - Cotilédones completamente desenvolvidos","3 - 2ª Folha","4 - Desenvolvimento das restantes folhas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Morango")) {
                    values =new String[] {"1 - Desenvolvimento das folhas","2 - Inicio da formação do estolho","3 - 1º Filho","4 - 1ºs Primórdios florais","5 - Floração","6 - Maturação do fruto","7 - Senescência e inicio do repouso vegetativo"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Feijão")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Aparecimento do orgão floral","4 - Floração","5 - Formação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Pepino")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Abóbora")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Melão")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Melancia")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Alface")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Desenvolvimento das partes vegetativas","4 - Aparecimento do orgão floral"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Cebola")) {
                    values =new String[] {"1 - Germinação", "2 - Rebentação", "3 - Desenvolvimento das Folhas","4 - Desenvolvimento das partes vegetativas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do Fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Alho")) {
                    values =new String[] {"1 - Germinação", "2 - Rebentação", "3 - Desenvolvimento das Folhas","4 - Desenvolvimento das partes vegetativas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do Fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Cereais")) {
                    values =new String[] {"1 - Emergência","2 - 1 Folha","3 - 2 Folhas","4 - 3 Folhas","5 - Afilhamento do primeiro filho","6 - Afilhamento de segundo filho","7 - Encanamento","8 - Emborrachamento","9 - Espigamento","10 - Maturação"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Arroz")) {
                    values =new String[] {"1 - Emergência","2 - Pós-emergência","3 - 1 Folha","4 - 2 Folhas","5 - 3 Folhas","6 - 4 Folhas","7 - 1º Filho","8 - Inicio do Afilhamento","9 - Aparecimento das panículas","10 - Maturação"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Pêssego")) {
                    values =new String[] {"1 - Repouso", "2 - Abrolhamento", "3 - Botão Rosa", "4 - Floração", "5 - Queda das Pétalas", "6 - Vingamento", "7 - Frutos em desenvolvimento", "8 - Pré-Colheita"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Maçã")) {
                    values =new String[] {"1 - Repouso", "2 - Pré-abrolhamento", "3 - Abrolhamento", "4 - Botão verde", "5 - Botão rosa", "6 - Inicio da floração", "7 - Plena floração", "8 - Queda das pétalas", "9 - Vingamento", "10 - Frutos em desenvolvimento"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Pera")) {
                    values =new String[] {"1 - Repouso", "2 - Pré-abrolhamento", "3 - Abrolhamento", "4 - Botão verde", "5 - Botão branco", "6 - Inicio da floração", "7 - Plena floração", "8 - Queda das pétalas", "9 - Vingamento", "10 - Frutos em desenvolvimento"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Citrinos")) {
                    values =new String[] {"1 - Repouso", "2 - Abrolhamento", "3 - Crescimento dos gomos", "4 - Aparecimento da corola", "5 - Estames visiveis", "6 - Primeira flor", "7 - Plena floração", "8 - Queda das pétalas", "9 - Vingamento", "10 - Mudança de cor dos frutos"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Fava")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Aparecimento do orgão floral","4 - Floração","5 - Formação do fruto", "6 - Maturação do fruto"};
                }
                else if(campParcela.getSelectedItem().toString().contains("Ameixa")) {
                    values =new String[] {"1 - Gomo de Inverno","2 - Inchamento dos gomos","3 - Floração","4 - Vingamento","5 - Frutos em desnvolvimento"};
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistarEstadoActivity.this, R.layout.black_spinner, values);
                estado.setAdapter(adapter);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i = 0;
                String[] items = new String[(int)dataSnapshot.child("campanhas").getChildrenCount()];
                for (DataSnapshot postSnapshot: dataSnapshot.child("campanhas").getChildren()) {
                    Campanha est = postSnapshot.getValue(Campanha.class);
                    items[i++] = (est.getParcela() + " - " + est.getCultura());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, items);
                campParcela.setAdapter(adapter);
                if(id!=null) {
                    int pos = getIndex(campParcela, id);
                    if (pos != 0)
                        campParcela.setSelection(pos);
                }

            }
            private int getIndex(Spinner spinner, String myString)     {
                int index = 0;
                for (int i=0;i<spinner.getCount();i++){
                    if (spinner.getItemAtPosition(i).toString().contains(myString)){
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

        Button button = (Button) findViewById(R.id.estadoButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getText().toString().isEmpty())
                    textView.setError("Introduza uma data de registo");
                else {
                    String camp = campParcela.getSelectedItem().toString();
                    String estado1 = estado.getSelectedItem().toString();
                    String data = textView.getText().toString();
                    Map<String, Object> childUpdates = new HashMap<>();
                    Map<String, String> dados = new HashMap<String, String>();
                    dados.put("Estado", estado1);
                    dados.put("Data", data);
                    StringTokenizer str = new StringTokenizer(camp);
                    dados.put("Parcela", str.nextToken());
                    str.nextToken();
                    dados.put("Campanha", str.nextToken());
                    String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    childUpdates.put("FieldNote/estados/" + camp + "/" + estado1, dados);
                    mDatabase.updateChildren(childUpdates);
                    finish();
                    startActivity(new Intent(getApplicationContext(), EstadosFenologicosActivity.class));
                }
            }
        });
}}
