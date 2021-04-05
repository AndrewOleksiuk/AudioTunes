package edu.lnu.audiotunes.data.network.features.audiobooks.models;

import com.google.gson.annotations.SerializedName;

public class AudiobooksFeed {
    @SerializedName("feed")
    private AudiobooksCollection audiobooksCollection;

    public AudiobooksFeed(AudiobooksCollection audiobooksCollection) {
        this.audiobooksCollection = audiobooksCollection;
    }

    public void setAudiobooksCollection(AudiobooksCollection audiobooksCollection) {
        this.audiobooksCollection = audiobooksCollection;
    }

    public AudiobooksCollection getAudiobooksCollection() {
        return audiobooksCollection;
    }
}
