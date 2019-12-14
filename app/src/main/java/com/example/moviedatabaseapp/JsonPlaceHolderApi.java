package com.example.moviedatabaseapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("/")
    Call<Movie> getPost(
            @Query("apikey") String apikey,
            @Query("s") String s
    );

    @GET("/")
    Call<MovieDetails> getComment(
            @Query("apikey") String apikey,
            @Query("i") String i
    );

}
