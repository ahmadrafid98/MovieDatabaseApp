package com.example.moviedatabaseapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("Search")
    private List<MovieList> movies = null;

    public List<MovieList> getMovies() {
        return movies;
    }
}
