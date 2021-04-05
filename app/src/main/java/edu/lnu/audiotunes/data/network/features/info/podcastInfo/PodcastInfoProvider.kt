package edu.lnu.audiotunes.data.network.features.info.podcastInfo

import edu.lnu.audiotunes.data.network.features.info.podcastInfo.models.PodcastInfoModel
import edu.lnu.audiotunes.data.network.features.info.podcastInfo.models.PodcastsResultsInfo
import edu.lnu.audiotunes.data.network.services.RetrofitClient
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PodcastInfoProvider {
    @JvmStatic
    fun provide(id: Int): Single<PodcastInfoModel> {

        val service = RetrofitClient.getRetrofitInfoInstance().create(PodcastInfoService::class.java)

        return service.getInfo(id)
                .subscribeOn(Schedulers.io())
                .map { it.results[0] }
    }
}