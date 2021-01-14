package com.example.meteo2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ListView lv;

    ArrayList<News> newsList;
    private NewsAdapter mAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent mainIntent = new Intent(NewsActivity.this,Main2Activity.class);
                    startActivity(mainIntent);
                    NewsActivity.this.finish();
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_notifications);

        newsList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        mAdapter = new NewsAdapter(getApplicationContext(), newsList);
        lv.setAdapter(mAdapter);

        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        newsList.clear();
        if(isConnected)
        {
            new AsyncTaskNews().execute(newsList,mAdapter);
        }
        else{
            Toast.makeText(NewsActivity.this, "Pas de connexion à Internet !", Toast.LENGTH_LONG).show();
        }

        //https://newsapi.org/v2/top-headlines?country=fr&apiKey=fe3fb3c20fd14a8da23fd82131fec41a
    }

}
