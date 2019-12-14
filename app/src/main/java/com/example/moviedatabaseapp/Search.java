package com.example.moviedatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity {

    ListView listView;
    private List<MovieItem> movieItemList;
    private String query;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.listView);
        movieItemList = new ArrayList<>();

        Bundle getData = getIntent().getExtras();
        query = getData.getString("KEY_QUERY");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        getPost();
    }

    private void getPost() {

        Call<Movie> call = jsonPlaceHolderApi.getPost("70546284",query);

        final ProgressDialog  progressDialog = new ProgressDialog(Search.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code :" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.hide();

                Movie movie = response.body();
                List<MovieList> movieList = movie.getMovies();

                for(MovieList result:movieList) {
                    MovieItem movieItem = new MovieItem(
                            result.getTitle() + " (" + result.getYear() + ")" ,
                            result.getPoster(),
                            result.getImdbID());

                    movieItemList.add(movieItem);
                }

                MovieAdapter adapter = new MovieAdapter(movieItemList, getApplicationContext());
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(Search.this, SearchDetail.class);
                        i.putExtra("Id", movieItemList.get(+position).imdbID);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
