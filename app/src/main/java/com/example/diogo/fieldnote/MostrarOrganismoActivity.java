package com.example.diogo.fieldnote;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MostrarOrganismoActivity extends Activity {

    private DatabaseReference mDatabase;
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_organismo);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Observacao obs = dataSnapshot.child("observações").child(id).getValue(Observacao.class);
                TextView data = (TextView) findViewById(R.id.dateObs);
                data.setText(obs.getData());
                TextView parcelaEstado = (TextView) findViewById(R.id.parcelaObs);
                parcelaEstado.setText(obs.getParcela());
                int j = 0;
                Group group = new Group("Pragas");
                for (DataSnapshot postSnapshot: dataSnapshot.child("observações").child(id).
                        child("Pragas").getChildren()) {
                    String est = postSnapshot.getValue(String.class);
                    group.children.add(est);
                }
                groups.append(j++, group);
                group = new Group("Doenças");
                for (DataSnapshot postSnapshot: dataSnapshot.child("observações").child(id).
                        child("Doencas").getChildren()) {
                    String est = postSnapshot.getValue(String.class);
                    group.children.add(est);
                }
                groups.append(j++, group);
                group = new Group("Auxiliares");
                for (DataSnapshot postSnapshot: dataSnapshot.child("observações").child(id).
                        child("Auxiliares").getChildren()) {
                    String est = postSnapshot.getValue(String.class);
                    group.children.add(est);
                }
                groups.append(j++, group);
                group = new Group("Observações");
                DataSnapshot postSnapshot = dataSnapshot.child("observações").child(id).
                        child("Observacoes");
                String est = postSnapshot.getValue(String.class);
                group.children.add(est);
                groups.append(j++, group);
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
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

}
