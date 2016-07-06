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
                String[] area = new String[x];
                String[] nome_parcelas = new String[x];
                String[] zona = new String[x];
                String[] vazia = new String[x];

                for (DataSnapshot postSnapshot: dataSnapshot.child("parcelas").getChildren()) {
                    Parcela parc = postSnapshot.getValue(Parcela.class);
                    area[i] = parc.getÁrea();
                    nome_parcelas[i] = postSnapshot.getKey();
                    zona[i] = parc.getZona();
                    vazia[i]= "X";
                    i++;
                }
                ListAdapter areaAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, area);
                ListView areaView = (ListView) findViewById(R.id.areaView);
                areaView.setAdapter(areaAdapter);

                ListAdapter parcsAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nome_parcelas);
                ListView parcsView = (ListView) findViewById(R.id.parcsView);
                parcsView.setAdapter(parcsAdapter);

                ListAdapter zonaAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, zona);
                ListView zonaView = (ListView) findViewById(R.id.zonaView);
                zonaView.setAdapter(zonaAdapter);

                ListAdapter removerAdapter = new ArrayAdapter<String>(getApplication(), R.layout.red_center_list, vazia);
                ListView remover = (ListView) findViewById(R.id.cruzView);
                remover.setAdapter(removerAdapter);

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

        final ListView areaView = (ListView) findViewById(R.id.areaView);
        final ListView parcsView = (ListView) findViewById(R.id.parcsView);
        final ListView zonaView = (ListView) findViewById(R.id.zonaView);
        final ListView remover = (ListView) findViewById(R.id.cruzView);

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

                String obj1 = parcsView.getAdapter().getItem(position).toString();
                String zpos = zonaView.getAdapter().getItem(position).toString();

               //apaga a parcela na zona
                mDatabase.child("FieldNote/zonas/"+zpos+"/parcelas/"+obj1).removeValue();

                //apagar a parcela nó
                mDatabase.child("FieldNote/parcelas/"+obj1).removeValue();

                Toast.makeText(getApplicationContext(),
                        "Parcela: "+obj1+" removida com sucesso.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ParcelasActivity.class);

                finish();
                startActivity(intent);
            }
        });

    }

}
