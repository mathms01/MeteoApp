package com.example.meteo2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncTaskNews extends AsyncTask<Object, Void, Void> {
    NewsAdapter mAdapter;
    ArrayList<News> newsList;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Object... arg) {
        // Making a request to url and getting response
        try {
            String urlstr = "https://newsapi.org/v2/top-headlines?country=fr&apiKey=fe3fb3c20fd14a8da23fd82131fec41a";
            URL url = null;
            try {
                url = new URL(urlstr);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mAdapter = (NewsAdapter) arg[1];
            newsList = (ArrayList<News>) arg[0];

            HttpURLConnection urlConnec = (HttpURLConnection) url.openConnection();
            if (urlConnec.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(urlConnec.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                String jsonStr = input.readLine();

                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray news = jsonObj.getJSONArray("articles");

                // looping through All Contacts
                for (int i = 0; i < news.length(); i++) {
                    // tmp hash map for single meteo
                    News newInfo = new News();
                    JSONObject c = news.getJSONObject(i);
                    String titre = c.getString("title");
                    String auteur = c.getString("author");
                    String contenu = c.getString("content");
                    String date = c.getString("publishedAt");

                    newInfo.setAuteur(auteur);
                    newInfo.setTitre(titre);
                    newInfo.setContenu(contenu);
                    newInfo.setDate(date);

                    // adding each child node to HashMap key => value

                    newsList.add(newInfo);
                }
                Log.d("Message", "Finish loading");
            }
            urlConnec.disconnect();
        } catch (final JSONException e) {
            Log.e("Error", "Json parsing error: " + e.getMessage());


            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        mAdapter.notifyDataSetChanged();
    }
}
