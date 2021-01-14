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

public class NewsAdapter extends ArrayAdapter<News> {

    private Context mContext;
    private List<News> newsList = new ArrayList<>();

    public NewsAdapter(Context context, ArrayList<News> list) {
        super(context, 0 , list);
        mContext = context;
        newsList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_news,parent,false);

        News currentNew = newsList.get(position);

        TextView titre = (TextView) listItem.findViewById(R.id.titre);
        titre.setText(currentNew.getTitre());

        TextView auteur = (TextView) listItem.findViewById(R.id.auteur);
        auteur.setText(currentNew.getAuteur());

        TextView date = (TextView) listItem.findViewById(R.id.date);
        date.setText(currentNew.getDate());

        TextView contenu = (TextView) listItem.findViewById(R.id.contenu);
        contenu.setText(currentNew.getContenu());

        return listItem;
    }
}
