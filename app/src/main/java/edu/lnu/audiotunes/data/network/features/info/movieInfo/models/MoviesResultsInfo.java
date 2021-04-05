package edu.lnu.audiotunes.data.network.features.info.movieInfo.models;

import com.google.gson.annotations.SerializedName;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Dictionary;

import lombok.Getter;
import lombok.Setter;

public class MoviesResultsInfo {
    @SerializedName("results")
    ArrayList<MovieInfoModel> movieModels;

    public MoviesResultsInfo(ArrayList<MovieInfoModel> movieModels) {
        this.movieModels = movieModels;
    }

    public ArrayList<MovieInfoModel> getMovieModels() {
        return movieModels;
    }
}
