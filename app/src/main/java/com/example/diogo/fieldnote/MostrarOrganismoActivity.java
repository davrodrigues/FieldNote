package com.example.diogo.fieldnote;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

public class MostrarOrganismoActivity extends Activity {

    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_organismo);
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 4; j++) {
            if (j==0) {
                Group group = new Group("Pragas");
                for (int i = 0; i < 3; i++) {
                    group.children.add("Mosca Branca");
                }
                groups.append(j, group);
            }
            if (j==1) {
                Group group = new Group("Doenças");
                for (int i = 0; i < 3; i++) {
                    group.children.add("Folha negra");
                }
                groups.append(j, group);
            }
            if (j==2) {
                Group group = new Group("Auxiliares");
                for (int i = 0; i < 3; i++) {
                    group.children.add("Joaninha");
                }
                groups.append(j, group);
            }
            if (j==3) {
                Group group = new Group("Observações");
                group.children.add("50% das folhas afetadas");
                groups.append(j, group);
            }

        }
    }

}
