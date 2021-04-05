package edu.lnu.audiotunes.data.network.features.podcasts.models;

import com.google.gson.annotations.SerializedName;

public class PodcastsFeed {

    @SerializedName("feed")
    private PodcastsCollection feed;

    public PodcastsFeed(PodcastsCollection feed) {
        this.feed = feed;
    }

    public void setFeed(PodcastsCollection feed) {
        this.feed = feed;
    }

    public PodcastsCollection getFeed() {
        return feed;
    }
}
