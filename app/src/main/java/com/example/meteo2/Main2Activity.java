package com.example.meteo2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private TextView mTextMessage;


    private ListView lv;

    ArrayList<Meteo> meteoList;
    private MeteoAdapter mAdapter;
    String str;
    EditText edStr;
    int nbJr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Drawable dw;

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_dashboard:
                        break;
                    case R.id.navigation_notifications:
                        Intent mainIntent = new Intent(Main2Activity.this,NewsActivity.class);
                        startActivity(mainIntent);
                        Main2Activity.this.finish();
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        meteoList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        mAdapter = new MeteoAdapter(getApplicationContext(), meteoList);
        lv.setAdapter(mAdapter);
        edStr = findViewById(R.id.searchbar);
        str = edStr.getText().toString();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Meteo meteo = meteoList.get(position);
                ImageView image = (ImageView)findViewById(R.id.logo);
                Picasso.get().load("http://openweathermap.org/img/w/"+meteo.getWeatherIcon()+".png").into(image);

                TextView temp = (TextView)findViewById(R.id.temper);
                temp.setText(meteo.getTemp()+"°C");

                TextView ville = (TextView)findViewById(R.id.cityV);
                ville.setText("Ville : "+meteo.getVille());

                TextView humi = (TextView)findViewById(R.id.humi);
                humi.setText("Humidité : "+meteo.getHumi());

                TextView wind = (TextView)findViewById(R.id.wind);
                wind.setText(meteo.getWind());
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Meteo meteo = meteoList.get(position);
                SharedPreferences sharedPref = Main2Activity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("ville", meteo.getVille());
                editor.commit();
                Toast.makeText(Main2Activity.this, "Ville ajoutée aux favoris !", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        final ImageButton button = findViewById(R.id.SrcBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                edStr = findViewById(R.id.searchbar);
                str = edStr.getText().toString();
                Log.d("Message", ""+str);
                SharedPreferences sharedPref = Main2Activity.this.getPreferences(Context.MODE_PRIVATE);
                int intPref = Integer.parseInt(sharedPref.getString("affichage","1"));
                meteoList.clear();
                if(isConnected)
                {
                    new MyAsyncTask().execute(meteoList,mAdapter, str, intPref);
                }
                else{
                    Toast.makeText(Main2Activity.this, "Pas de connexion à Internet !", Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageButton button2 = findViewById(R.id.params);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                CustomDialogClass cdd=new CustomDialogClass(Main2Activity.this);
                cdd.show();
                SharedPreferences sharedPref = Main2Activity.this.getPreferences(Context.MODE_PRIVATE);
                String defaultValue = "";
                String strVille = sharedPref.getString("ville",defaultValue);
                int intPref = Integer.parseInt(sharedPref.getString("affichage","1"));
                if(strVille != null)
                {
                    meteoList.clear();
                    if(isConnected)
                    {
                        new MyAsyncTask().execute(meteoList,mAdapter, strVille, intPref);
                    }
                    else{
                        Toast.makeText(Main2Activity.this, "Pas de connexion à Internet !", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Main2Activity.this, "Pas de favoris ! Restez appuyé sur une prévision météo pour en ajouter un.", Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageButton button1 = findViewById(R.id.fav);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SharedPreferences sharedPref = Main2Activity.this.getPreferences(Context.MODE_PRIVATE);
                String defaultValue = "";
                String strVille = sharedPref.getString("ville",defaultValue);
                int intPref = Integer.parseInt(sharedPref.getString("affichage","1"));
                if(strVille != null)
                {
                    meteoList.clear();
                    if(isConnected)
                    {
                        new MyAsyncTask().execute(meteoList,mAdapter, strVille, intPref);
                    }
                    else{
                        Toast.makeText(Main2Activity.this, "Pas de connexion à Internet !", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Main2Activity.this, "Pas de favoris ! Restez appuyé sur une prévision météo pour en ajouter un.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Toast.makeText(Main2Activity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        if(isConnected)
        {
            SharedPreferences sharedPref = Main2Activity.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("affichage", "1");
            editor.commit();
            int intPref = Integer.parseInt(sharedPref.getString("affichage","1"));
            new MyAsyncTask().execute(meteoList,mAdapter, str, intPref);
            ImageView image = (ImageView)findViewById(R.id.iv);
        }else{
            Toast.makeText(Main2Activity.this, "Pas de connexion à Internet !", Toast.LENGTH_LONG).show();
        }
    }
}
