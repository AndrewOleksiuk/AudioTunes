package edu.lnu.audiotunes.data.network.features.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesCollection {
    @SerializedName("results")
    ArrayList<MovieModel> movies;

    public MoviesCollection(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }

    public ArrayList<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }
}
