package com.example.diogo.fieldnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        final ListView areaView = (ListView) findViewById(R.id.areaView);
        final ListView parcsView = (ListView) findViewById(R.id.parcsView);
        final ListView zonaView = (ListView) findViewById(R.id.zonaView);
        final ListView remover = (ListView) findViewById(R.id.cruzView);
        final Spinner filtro = (Spinner) findViewById(R.id.filtroParc);

        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final DataSnapshot dataSnap = dataSnapshot;
                List<String> filtros = new ArrayList<String>();
                filtros.add("Todos");
                for (DataSnapshot postSnapshot: dataSnapshot.child("zonas").getChildren()) {
                    filtros.add(postSnapshot.getKey());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), R.layout.black_spinner, filtros);
                filtro.setAdapter(adapter);

                filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        List<String> area = new ArrayList<String>();
                        List<String> nome_parcelas = new ArrayList<String>();
                        List<String> zonas = new ArrayList<String>();
                        List<String> cruzes = new ArrayList<String>();
                        for (DataSnapshot postSnapshot: dataSnap.child("parcelas").getChildren()) {
                            Parcela parc = postSnapshot.getValue(Parcela.class);
                            String escolhido = parc.getZona();
                            if(filtro.getSelectedItem().toString().equalsIgnoreCase("todos")){
                                nome_parcelas.add(postSnapshot.getKey());
                                area.add(parc.getÁrea());
                                zonas.add(parc.getZona());
                                cruzes.add("X");
                            }
                            else if (escolhido.equalsIgnoreCase(filtro.getSelectedItem().toString())) {
                                nome_parcelas.add(postSnapshot.getKey());
                                area.add(parc.getÁrea());
                                zonas.add(parc.getZona());
                                cruzes.add("X");
                            }
                        }
                        ListAdapter areaAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, area);
                        ListAdapter parcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nome_parcelas);
                        ListAdapter zonasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, zonas);
                        ListAdapter cruzesAdapter = new ArrayAdapter<String>(getApplication(), R.layout.red_center_list, cruzes);
                        areaView.setAdapter(areaAdapter);
                        parcsView.setAdapter(parcelasAdapter);
                        zonaView.setAdapter(zonasAdapter);
                        remover.setAdapter(cruzesAdapter);
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });

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

        //botao adicionar parcela
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.adicionarParcela);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), RegistarNovaParcela.class));
            }
        });

        //Mostrar produto ao clicar numa linha da lista de area
        areaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarParcelaActivity.class);

                Object obj = parcsView.getAdapter().getItem(position);
                intent.putExtra("id", obj.toString());
                startActivity(intent);
            }
        });


        //Mostrar produto ao clicar numa linha da lista de area
        zonaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarParcelaActivity.class);

                Object obj = parcsView.getAdapter().getItem(position);
                intent.putExtra("id", obj.toString());
                startActivity(intent);
            }
        });

        //Mostrar produto ao clicar numa linha da lista de area
        parcsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), MostrarParcelaActivity.class);

                Object obj = parcsView.getAdapter().getItem(position);
                intent.putExtra("id", obj.toString());
                startActivity(intent);
            }
        });

        //botões de remoção
        remover.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
               final String obj1 = parcsView.getAdapter().getItem(position).toString();
               final String zpos = zonaView.getAdapter().getItem(position).toString();

                new AlertDialog.Builder(ParcelasActivity.this)
                        .setTitle("Apagar a Parcela?")
                        .setMessage("Tem a certeza que pretende apagar esta parcela do sistema?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //apaga a parcela na zona
                                mDatabase.child("FieldNote/zonas/"+zpos+"/parcelas/"+obj1).removeValue();

                                //apagar a parcela nó
                                mDatabase.child("FieldNote/parcelas/"+obj1).removeValue();

                                //remover a parcela da campanha
                                mDatabase.child("FieldNote/campanhas/"+obj1).removeValue();
                                Toast.makeText(getApplicationContext(),
                                        "Parcela: "+obj1+" removida com sucesso.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), ParcelasActivity.class);

                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //nao faz nada
                            }
                        })
                        .setIcon(R.drawable.alert_smallest)
                        .show();

            }
        });

    }

}
