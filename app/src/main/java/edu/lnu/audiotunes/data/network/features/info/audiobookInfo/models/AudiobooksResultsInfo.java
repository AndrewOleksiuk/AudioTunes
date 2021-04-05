package edu.lnu.audiotunes.data.network.features.info.audiobookInfo.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AudiobooksResultsInfo {
    @SerializedName("results")
    private ArrayList<AudiobookInfoModel> audiobooksResults;

    public AudiobooksResultsInfo(ArrayList<AudiobookInfoModel> audiobooksResults) {
        this.audiobooksResults = audiobooksResults;
    }

    public ArrayList<AudiobookInfoModel> getAudiobooksResults() {
        return audiobooksResults;
    }

    public void setAudiobooksResults(ArrayList<AudiobookInfoModel> audiobooksResults) {
        this.audiobooksResults = audiobooksResults;
    }
}
