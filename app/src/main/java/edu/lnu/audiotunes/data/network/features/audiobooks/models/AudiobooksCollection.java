package edu.lnu.audiotunes.data.network.features.audiobooks.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AudiobooksCollection {
    @SerializedName("results")
    private ArrayList<AudiobookModel> audiobooks;

    public AudiobooksCollection(ArrayList<AudiobookModel> audiobooks) {
        this.audiobooks = audiobooks;
    }

    public ArrayList<AudiobookModel> getAudiobooks() {
        return audiobooks;
    }

    public void setAudiobooks(ArrayList<AudiobookModel> audiobooks) {
        this.audiobooks = audiobooks;
    }
}
