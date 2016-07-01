package com.example.diogo.fieldnote;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class IntervencaoTecnicaFragment extends ListFragment {

    public IntervencaoTecnicaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intervencao_tecnica, container, false);


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        String[] values = new String[] { "01/06/2016", "01/05/2016", "01/04/2016", "01/03/2016", "01/02/2016", "01/01/2016"  };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        String[] parcelas = {"1", "2", "3", "4", "5", "6"};
        ListAdapter parcelasAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, parcelas);

        ListView parcelasView = (ListView) getView().findViewById(R.id.parcelasView);
        parcelasView.setAdapter(parcelasAdapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
    }

}
