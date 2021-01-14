package com.example.meteo2;

import android.os.AsyncTask;
import android.os.Debug;
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

public class MyAsyncTask extends AsyncTask<Object, Void, Void> {

    MeteoAdapter mAdapter;
    ArrayList<Meteo> meteoList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Object... arg) {
            // Making a request to url and getting response
            try {
                String urlstr = "https://api.openweathermap.org/data/2.5/forecast?q=" + arg[2] + "&units=metric&APPID=2853f7eab535addbe8c85ff31ac65544"; //2853f7eab535addbe8c85ff31ac65544
                URL url = null;
                try {
                    url = new URL(urlstr);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mAdapter = (MeteoAdapter) arg[1];
                meteoList = (ArrayList<Meteo>) arg[0];

                Log.d("Message", ""+arg[3].toString());

                HttpURLConnection urlConnec = (HttpURLConnection) url.openConnection();
                if (urlConnec.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(urlConnec.getInputStream());
                    BufferedReader input = new BufferedReader(isr);
                    String jsonStr = input.readLine();

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray meteos = jsonObj.getJSONArray("list");

                    // looping through All Contacts
                    for (int i = 0; i < meteos.length(); i = i + (int)arg[3]) {
                        // tmp hash map for single meteo
                        Meteo meteo = new Meteo();
                        JSONObject c = meteos.getJSONObject(i);
                        String date = c.getString("dt_txt");
                        // Main node is JSON Object
                        JSONObject main = c.getJSONObject("main");
                        String temp = main.getString("temp");
                        String humi = main.getString("humidity");
                        JSONObject wind = c.getJSONObject("wind");
                        String windSpeed = wind.getString("speed");
                        String windDeg = wind.getString("deg");
                        JSONArray weather = c.getJSONArray("weather");
                        for (int j = 0; j < weather.length(); j++) {
                            JSONObject d = weather.getJSONObject(j);
                            String weatherTitre = d.getString("main");
                            String weatherDesc = d.getString("description");
                            String weatherIcon = d.getString("icon");
                            meteo.setWeatherTitre(weatherTitre);
                            meteo.setWeatherDesc(weatherDesc);
                            meteo.setWeatherIcon(weatherIcon);
                        }

                        // adding each child node to HashMap key => value
                        meteo.setTemp(temp);
                        meteo.setDate(date);
                        meteo.setVille(arg[2].toString());
                        meteo.setHumi(humi);
                        meteo.setWind("Speed : "+windSpeed+" | "+"Orientation : "+windDeg);

                        // adding contact to contact list
                        meteoList.add(meteo);
                        Log.d("Message", "Finish loading");
                    }
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
