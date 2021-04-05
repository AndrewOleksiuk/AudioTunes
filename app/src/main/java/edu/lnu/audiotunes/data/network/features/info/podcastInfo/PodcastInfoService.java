package edu.lnu.audiotunes.data.network.features.info.podcastInfo;

import edu.lnu.audiotunes.data.network.features.info.podcastInfo.models.PodcastsResultsInfo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PodcastInfoService {
    @GET("lookup")
    Single<PodcastsResultsInfo> getInfo(@Query("id") int id);
}
