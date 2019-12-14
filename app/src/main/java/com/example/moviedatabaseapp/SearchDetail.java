package com.example.moviedatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchDetail extends AppCompatActivity {

    private String id;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        Bundle getData = getIntent().getExtras();
        id = getData.getString("Id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        getComment();
    }

    private void getComment() {

        Call<MovieDetails> call = jsonPlaceHolderApi.getComment("70546284", id);

        final ProgressDialog progressDialog = new ProgressDialog(SearchDetail.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code :" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.hide();

                MovieDetails movie = response.body();
                TextView title = findViewById(R.id.title1);
                TextView description = findViewById(R.id.description);
                ImageView img = findViewById(R.id.image1);

                description.setText(
                        "Released : " + movie.getReleased() +
                        "\n\nGenre : " + movie.getGenre() +
                        "\n\nDirector : " + movie.getDirector() +
                        "\n\nActors : " + movie.getActors());
                title.setText(movie.getTitle() + " (" + movie.getYear() + ")");

                Picasso.get()
                        .load(movie.getPoster())
                        .into(img);

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
