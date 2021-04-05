package edu.lnu.audiotunes.data.network.features.info.audiobookInfo

import edu.lnu.audiotunes.data.network.features.info.audiobookInfo.models.AudiobookInfoModel
import edu.lnu.audiotunes.data.network.features.info.audiobookInfo.models.AudiobooksResultsInfo
import edu.lnu.audiotunes.data.network.services.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AudiobookInfoProvider {
    @JvmStatic
    fun provide(id: Int):Single<AudiobookInfoModel>  {
        val service = RetrofitClient.getRetrofitInfoInstance().create(AudiobookInfoService::class.java)
        return service.getInfo(id)
                .subscribeOn(Schedulers.io())
                .map { it.audiobooksResults[0] }
    }
}