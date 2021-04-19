package edu.lnu.audiotunes.presentation.fragments.movieInfo.presentation

import edu.lnu.audiotunes.data.network.features.info.movieInfo.MovieInfoProvider
import edu.lnu.audiotunes.data.network.features.info.movieInfo.models.MovieInfoModel
import edu.lnu.audiotunes.presentation.base.MVP.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.io.IOException

class MovieInfoPresenter : BasePresenter<IMovieInfoView?>() {

    private var movieID = 0
    private lateinit var model: MovieInfoModel

    private lateinit var disposable: Disposable


    override fun onStart() {
        disposable = MovieInfoProvider.provide(movieID)
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

    fun initMovieID(movieID: Int) {
        this.movieID = movieID
    }
}