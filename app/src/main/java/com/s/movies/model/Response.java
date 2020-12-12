package com.s.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {

    @SerializedName("results")
    ArrayList<Movies> moviesArrayList;

    public ArrayList<Movies> getMoviesArrayList() {
        return moviesArrayList;
    }

    public void setMoviesArrayList(ArrayList<Movies> moviesArrayList) {
        this.moviesArrayList = moviesArrayList;
    }
}
