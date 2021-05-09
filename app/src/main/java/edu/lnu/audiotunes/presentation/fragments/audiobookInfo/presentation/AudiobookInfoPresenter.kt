package edu.lnu.audiotunes.presentation.fragments.audiobookInfo.presentation

import edu.lnu.audiotunes.data.network.features.info.audiobookInfo.AudiobookInfoProvider
import edu.lnu.audiotunes.data.network.features.info.audiobookInfo.models.AudiobookInfoModel
import edu.lnu.audiotunes.presentation.base.MVP.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.io.IOException

class AudiobookInfoPresenter : BasePresenter<IAudiobookInfoView?>() {

    private var audiobookID = 0
    private lateinit var model: AudiobookInfoModel

    private lateinit var disposable: Disposable

    override fun onStart() {
        disposable = AudiobookInfoProvider.provide(audiobookID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                onSuccess = {
                    model = it
                    view?.setData(model)
                },
                onError = {
                    if (it is IOException) {
                        //No network connection
                    }
                }
        )
    }

    override fun release() {
        disposable.dispose()
        super.release()
    }

    fun initAudiobookID(audiobookID: Int) {
        this.audiobookID = audiobookID
    }
}