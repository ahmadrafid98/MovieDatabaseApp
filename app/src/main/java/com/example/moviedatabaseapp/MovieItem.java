package com.example.moviedatabaseapp;

import java.io.Serializable;

public class MovieItem implements Serializable {
    String title, Poster, imdbID;

    public MovieItem(String title, String Poster, String imdbID ) {
        this.title = title;
        this.Poster = Poster;
        this.imdbID = imdbID;
    }


    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbId() {
        return imdbID;
    }
}
