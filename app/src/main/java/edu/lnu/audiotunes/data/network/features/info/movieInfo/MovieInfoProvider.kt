package edu.lnu.audiotunes.data.network.features.info.movieInfo

import edu.lnu.audiotunes.data.network.features.info.movieInfo.models.MovieInfoModel
import edu.lnu.audiotunes.data.network.features.info.movieInfo.models.MoviesResultsInfo
import edu.lnu.audiotunes.data.network.services.RetrofitClient
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieInfoProvider {
    @JvmStatic
    fun provide(id: Int): Single<MovieInfoModel> {

        val service = RetrofitClient.getRetrofitInfoInstance().create(MovieInfoService::class.java)

        return service.getInfo(id)
                .subscribeOn(Schedulers.io())
                .map { it.movieModels[0] }

    }
}