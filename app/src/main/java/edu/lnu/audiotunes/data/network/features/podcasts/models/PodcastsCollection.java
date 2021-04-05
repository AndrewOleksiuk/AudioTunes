package edu.lnu.audiotunes.data.network.features.podcasts.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PodcastsCollection {
    @SerializedName("results")
    ArrayList<PodcastModel> podcasts;

    public PodcastsCollection(ArrayList<PodcastModel> podcasts) {
        this.podcasts = podcasts;
    }

    public ArrayList<PodcastModel> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<PodcastModel> podcasts) {
        this.podcasts = podcasts;
    }
}
