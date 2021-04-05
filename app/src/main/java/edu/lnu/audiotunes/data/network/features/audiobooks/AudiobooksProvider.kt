package edu.lnu.audiotunes.data.network.features.audiobooks

import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobooksCollection
import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobooksFeed
import edu.lnu.audiotunes.data.network.services.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AudiobooksProvider {
    fun provide(): Single<AudiobooksCollection> {
        val service = RetrofitClient.getRetrofitInstance().create(GetAudiobooksService::class.java)
        return service.audiobooks
                .subscribeOn(Schedulers.io())
                .map { it.audiobooksCollection }
    }

}