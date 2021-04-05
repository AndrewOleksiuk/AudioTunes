package edu.lnu.audiotunes.data.network.features.audiobooks;

import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobooksFeed;
import io.reactivex.Single;
import retrofit2.http.GET;


public interface GetAudiobooksService {

    @GET("audiobooks/top-audiobooks/all/25/non-explicit.json")
    Single<AudiobooksFeed> getAudiobooks();
}
