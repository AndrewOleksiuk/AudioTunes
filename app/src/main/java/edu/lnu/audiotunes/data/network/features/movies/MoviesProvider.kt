package edu.lnu.audiotunes.data.network.features.movies

import edu.lnu.audiotunes.data.network.features.movies.models.MoviesCollection
import edu.lnu.audiotunes.data.network.features.movies.models.MoviesFeed
import edu.lnu.audiotunes.data.network.services.RetrofitClient
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MoviesProvider {
    @JvmStatic
    fun provide(): Single<MoviesCollection> {
        val service = RetrofitClient.getRetrofitInstance().create(GetMoviesService::class.java)
        return service.movies
                .subscribeOn(Schedulers.io())
                .map { it.feed }
    }
}