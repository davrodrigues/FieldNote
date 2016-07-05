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

        //up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Spinner dropdown = (Spinner)findViewById(R.id.dropdownparcelas);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner dropdown = (Spinner)findViewById(R.id.dropdownparcelas);
                String[] values =null;
                if(dropdown.getSelectedItem().toString().contains("Batata")) {
                    values = new String[] {"1 - Tubérculo", "2 - Rebentos","3 - Inicio da formação de raízes","4 - Emergência","5 - Desenvolvimento das folhas","6 - Desenvolvimento das partes vegetativas","7 - Botões florais visiveis", "8 - Pétalas visiveis","9 - Floração","10 - Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Milho")) {
                    values =  new String[] {"1 - Pré-emergência","2 - Emergência","3 - 1 Folha","4 - 2 Folhas","5 - 3 Folhas","6 - 5 Folhas","7 - 4º Nó","8 - Fase joalheiro","9 - Floração feminina","10 - Maturação"};
                }
                if(dropdown.getSelectedItem().toString().contains("Pimento")) {
                    values = new String[] {"1 - Emergência","2 - Cotilédones completamente desenvolvidos","3 - 2ª Folha","4 - Desenvolvimento das restantes folhas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Tomate")) {
                    values =new String[] {"1 - Emergência","2 - Cotilédones completamente desenvolvidos","3 - 2ª Folha","4 - Desenvolvimento das restantes folhas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Beringela")) {
                    values = new String[] {"1 - Emergência","2 - Cotilédones completamente desenvolvidos","3 - 2ª Folha","4 - Desenvolvimento das restantes folhas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Morango")) {
                    values =new String[] {"Desenvolvimento das folhas","Inicio da formação do estolho","1º Filho","1ºs Primórdios florais","Floração","Maturação do fruto","Senescência e inicio do repouso vegetativo"};
                }
                if(dropdown.getSelectedItem().toString().contains("Feijão")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Aparecimento do orgão floral","4 - Floração","5 - Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Pepino")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Abóbora")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Melão")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Melancia")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Formação dos rebentos laterais","4 - Floração","5 - Formação do fruto","6 - Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Alface")) {
                    values =new String[] {"1 - Germinação","2 - Desenvolvimento das folhas","3 - Desenvolvimento das partes vegetativas","4 - Aparecimento do orgão floral"};
                }
                if(dropdown.getSelectedItem().toString().contains("Cebola")) {
                    values =new String[] {"1 - Germinação", "2 - Rebentação", "3 - Desenvolvimento das Folhas","4 - Desenvolvimento das partes vegetativas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do Fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Alho")) {
                    values =new String[] {"1 - Germinação", "2 - Rebentação", "3 - Desenvolvimento das Folhas","4 - Desenvolvimento das partes vegetativas","5 - Aparecimento do orgão floral","6 - Floração","7 - Formação do Fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Cereais")) {
                    values =new String[]{"1 - Emergência","2 - 1 Folha","3 - 2 Folhas","4 - 3 Folhas","5 - Afilhamento do primeiro filho","6 - Afilhamento de segundo filho","7 - Encanamento","8 - Emborrachamento","9 - Espigamento","10 - Maturação"};
                }
                if(dropdown.getSelectedItem().toString().contains("Arroz")) {
                    values =new String[] {"1 - Emergência","2 - Pós-emergência","3 - 1 Folha","4 - 2 Folhas","5 - 3 Folhas","6 - 4 Folhas","7 - 1º Filho","8 - Inicio do Afilhamento","9 - Aparecimento das panículas","10 - Maturação"};
                }
                if(dropdown.getSelectedItem().toString().contains("Pêssego")) {
                    values =new String[] {"1 - Repouso", "2 - Abrolhamento", "3 - Botão Rosa", "4 - Floração", "5 - Queda das Pétalas", "6 - Vingamento", "7 - Frutos em desenvolvimento", "8 - Pré-Colheita"};
                }
                Spinner estados = (Spinner)findViewById(R.id.estadoSpinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistarEstadoActivity.this, R.layout.black_spinner, values);
                estados.setAdapter(adapter);
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
                Spinner dropdown = (Spinner)findViewById(R.id.dropdownparcelas);
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

        Button button = (Button) findViewById(R.id.estadoButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner campanha = (Spinner)findViewById(R.id.dropdownparcelas);
                Spinner estado = (Spinner)findViewById(R.id.estadoSpinner);
                TextView textView = (TextView)findViewById(R.id.dataRegistarEstado);
                String camp = campanha.getSelectedItem().toString();
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
                childUpdates.put("FieldNote/estados/"+camp+"/"+estado1, dados);
                mDatabase.updateChildren(childUpdates);
                startActivity(new Intent(getApplicationContext(), EstadosFenologicosActivity.class));
            }
        });
}}
