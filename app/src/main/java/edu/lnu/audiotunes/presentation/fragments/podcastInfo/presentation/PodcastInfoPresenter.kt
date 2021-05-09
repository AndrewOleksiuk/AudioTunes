package com.itunesloader.presentation.fragments.podcastInfo.presentation

import edu.lnu.audiotunes.data.network.features.info.podcastInfo.PodcastInfoProvider
import edu.lnu.audiotunes.data.network.features.info.podcastInfo.models.PodcastInfoModel
import edu.lnu.audiotunes.presentation.base.MVP.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class PodcastInfoPresenter : BasePresenter<IPodcastInfoView?>() {

    private var podcastId = 0
    private lateinit var model: PodcastInfoModel

    private lateinit var disposable: Disposable

    override fun onStart() {
        disposable = PodcastInfoProvider.provide(podcastId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            model = it
                            view?.setData(model)
                        }
                )
    }

    fun initArtworkId(artworkId: Int) {
        this.podcastId = artworkId
    }

    override fun release() {
        disposable.dispose()
        super.release()
    }
}