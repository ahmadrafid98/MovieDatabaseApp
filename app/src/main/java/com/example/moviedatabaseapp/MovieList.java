package com.example.moviedatabaseapp;

import com.google.gson.annotations.SerializedName;

public class MovieList {

    @SerializedName("Title")
    private String Title;
    @SerializedName("Year")
    private String Year;
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("Type")
    private String Type;
    @SerializedName("Poster")
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

}
