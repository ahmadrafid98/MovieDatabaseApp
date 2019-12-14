package com.example.moviedatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieItem> {
    private List<MovieItem> movieItemList;
    private Context context;

    public MovieAdapter(List<MovieItem> movieItemList, Context context) {
        super(context, R.layout.list_item, movieItemList);
        this.movieItemList = movieItemList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewTitle = listViewItem.findViewById(R.id.title);
        ImageView imgView = listViewItem.findViewById(R.id.poster);

        MovieItem movieItem = movieItemList.get(position);

        textViewTitle.setText(movieItem.getTitle());

        Picasso.get()
                .load(movieItem.getPoster())
                .into(imgView);

        return listViewItem;
    }
}
