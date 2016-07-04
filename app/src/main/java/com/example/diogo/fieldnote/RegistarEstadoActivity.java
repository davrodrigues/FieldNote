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
                    values = new String[] {"Tubérculo", "Rebentos","Inicio da formação de raízes","Emergência","Desenvolvimento das folhas","Desenvolvimento das partes vegetativas","Botões florais visiveis", "Pétalas visiveis","Floração","Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Milho")) {
                    values =  new String[] {"Pré-emergência","Emergência","1 Folha","2 Folhas","3 Folhas","5 Folhas","4º Nó","Fase joalheiro","Floração feminina","Maturação"};
                }
                if(dropdown.getSelectedItem().toString().contains("Pimento")) {
                    values = new String[] {"Emergência","cotilédones completamente desenvolvidos","2ª Folha","Desenvolvimento das restantes folhas","Aparecimento do orgão floral","Floração","Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Tomate")) {
                    values =new String[] {"Emergência","cotilédones completamente desenvolvidos","2ª Folha","Desenvolvimento das restantes folhas","Aparecimento do orgão floral","Floração","Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Beringela")) {
                    values = new String[] {"Emergência","cotilédones completamente desenvolvidos","2ª Folha","Desenvolvimento das restantes folhas","Aparecimento do orgão floral","Floração","Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Morango")) {
                    values =new String[] {"Desenvolvimento das folhas","Inicio da formação do estolho","1º Filho","1ºs Primórdios florais","Floração","Maturação do fruto","Senescência e inicio do repouso vegetativo"};
                }
                if(dropdown.getSelectedItem().toString().contains("Feijão")) {
                    values =new String[] {"Germinação","Desenvolvimento das folhas","Aparecimento do orgão floral","Floração","Formação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Pepino")) {
                    values =new String[] {"Germinação","Desenvolvimento das folhas","Formação dos rebentos laterais","Floração","Formação do fruto","Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Abóbora")) {
                    values =new String[] {"Germinação","Desenvolvimento das folhas","Formação dos rebentos laterais","Floração","Formação do fruto","Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Melão")) {
                    values =new String[] {"Germinação","Desenvolvimento das folhas","Formação dos rebentos laterais","Floração","Formação do fruto","Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Melancia")) {
                    values =new String[] {"Germinação","Desenvolvimento das folhas","Formação dos rebentos laterais","Floração","Formação do fruto","Maturação do fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Alface")) {
                    values =new String[] {"Germinação","Desenvolvimento das folhas","Desenvolvimento das partes vegetativas","Aparecimento do orgão floral"};
                }
                if(dropdown.getSelectedItem().toString().contains("Cebola")) {
                    values =new String[] {"Germinação", "Rebentação", "Desenvolvimento das Folhas","Desenvolvimento das partes vegetativas","Aparecimento do orgão floral","Floração","Formação do Fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Alho")) {
                    values =new String[] {"Germinação", "Rebentação", "Desenvolvimento das Folhas","Desenvolvimento das partes vegetativas","Aparecimento do orgão floral","Floração","Formação do Fruto"};
                }
                if(dropdown.getSelectedItem().toString().contains("Cereais")) {
                    values =new String[]{"Emergência","1 Folha","2 Folhas","3 Folhas","Afilhamento do primeiro filho","Afilhamento de segundo filho","Encanamento","Emborrachamento","Espigamento","Maturação"};
                }
                if(dropdown.getSelectedItem().toString().contains("Arroz")) {
                    values =new String[] {"Emergência","Pós-emergência","1 Folha","2 Folhas","3 Folhas","4 Folhas","1º Filho","Inicio do Afilhamento","Aparecimento das panículas","Maturação"};
                }
                if(dropdown.getSelectedItem().toString().contains("Pêssego")) {
                    values =new String[] {"Repouso", "Abrolhamento", "Botão Rosa", "Floração", "Queda das Pétalas", "Vingamento", "Frutos  em desenvolvimento", "Pré-Colheita"};
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
                childUpdates.put("FieldNote/estados/"+camp+"/", dados);
                mDatabase.updateChildren(childUpdates);
                startActivity(new Intent(getApplicationContext(), EstadosFenologicosActivity.class));
            }
        });
}}
