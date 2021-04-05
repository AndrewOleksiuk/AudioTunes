package edu.lnu.audiotunes.data.network.features.movies;

import edu.lnu.audiotunes.data.network.features.movies.models.MoviesFeed;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMoviesService {
    @GET("movies/top-movies/all/25/non-explicit.json")
    Single<MoviesFeed> getMovies();
}
