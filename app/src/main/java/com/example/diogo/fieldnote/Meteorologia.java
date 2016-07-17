
package com.example.diogo.fieldnote;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.diogo.fieldnote.WeatherHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.diogo.fieldnote.model.Location;
import com.example.diogo.fieldnote.model.Weather;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Meteorologia extends AppCompatActivity {

    public String temperatura;

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;

    private TextView hum;
    private ImageView imgView;

    //db
    private DatabaseReference mDatabase;

    List<Weather> listat = new ArrayList<Weather>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        imgView = (ImageView) findViewById(R.id.condIcon);


        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                int i = 0;
                int x = ((int) dataSnapshot.child("zonas").getChildrenCount());
                String[] nzona = new String[x];
                String[] local = new String[x];

                for (DataSnapshot postSnapshot : dataSnapshot.child("zonas").getChildren()) {
                    Zona zone = postSnapshot.getValue(Zona.class);

                    nzona[i] = zone.getNomezona();
                    local[i] = zone.getLocalização();
                    i++;
                }

                //lista de zonas
                ListAdapter zonasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, nzona);
                ListView zonasView = (ListView) findViewById(R.id.zonasView);
                zonasView.setAdapter(zonasAdapter);

                //localização
                ListAdapter localAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, local);
                ListView localView = (ListView) findViewById(R.id.localView);
                localView.setAdapter(localAdapter);

                //buscar e preencher o tempo em cada cidade
                new JSONWeatherTask().execute(local);

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


    }

    /*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
    private class JSONWeatherTask extends AsyncTask<String[], Void, List<Weather>> {

        @Override
        protected List<Weather> doInBackground(String[]... params) {

            Weather weather;
            for (String w1 : params[0]) {

                String data = ((new WeatherHttpClient()).getWeatherData(w1));
                try {
                    weather = JSONWeatherParser.getWeather(data);
                    // Let's retrieve the icon
                    weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
                    listat.add(weather);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return listat;
        }


        @Override
        protected void onPostExecute(List<Weather> weathers) {
            super.onPostExecute(weathers);

            List<String> tempo = new ArrayList<>();
            for (Weather wea : weathers) {
               /* if (wea.iconData != null && wea.iconData.length > 0) {
                    Bitmap img = BitmapFactory.decodeByteArray(wea.iconData, 0, wea.iconData.length);
                    imgView.setImageBitmap(img);
                }
    */
                //preencher a lista de temperaturas
                temperatura = "" + Math.round((wea.temperature.getMaxTemp())) + " ºC";
                tempo.add(temperatura);
            }

            ListAdapter nparcelasAdapter = new ArrayAdapter<String>(getApplication(), R.layout.center_list, tempo);
            ListView nparcelasView = (ListView) findViewById(R.id.nparcelasView);
            nparcelasView.setAdapter(nparcelasAdapter);


            //Mostrar dados sobre o tempo ao clicar nas temperaturas
            nparcelasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String cidade = listat.get(position).location.getCity();
                    cidade = cidade.equalsIgnoreCase("lisbon") == true ? "Lisboa" : cidade;

                    //poe o icone
                    Bitmap img = BitmapFactory.decodeByteArray(listat.get(position).iconData, 0, listat.get(position).iconData.length);
                    imgView.setImageBitmap(img);

                    cityText.setText(cidade + "," + listat.get(position).location.getCountry());
                    condDescr.setText(listat.get(position).currentCondition.getCondition() + "(" + listat.get(position).currentCondition.getDescr() + ")");
                    temp.setText("" + Math.round(listat.get(position).temperature.getMaxTemp()) + " ºC");
                    hum.setText("" + listat.get(position).currentCondition.getHumidity() + "%");
                    press.setText("" + listat.get(position).currentCondition.getPressure() + " hPa");
                }
            });
        }
    }
}
