package com.example.meteo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MeteoAdapter extends ArrayAdapter<Meteo> {
    private Context mContext;
    private List<Meteo> meteosList = new ArrayList<>();

    public MeteoAdapter(Context context, ArrayList<Meteo> list) {
        super(context, 0 , list);
        mContext = context;
        meteosList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Meteo currentMeteo = meteosList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.iv);
        Picasso.get().load("http://openweathermap.org/img/w/"+currentMeteo.getWeatherIcon()+".png").into(image);

        TextView date = (TextView) listItem.findViewById(R.id.date);
        date.setText(currentMeteo.getDate());

        TextView temp = (TextView) listItem.findViewById(R.id.temp);
        temp.setText("Température : "+currentMeteo.getTemp()+"°C");

        TextView ville = (TextView) listItem.findViewById(R.id.ville);
        ville.setText(currentMeteo.getVille());

        return listItem;
    }
}
