package com.stochanskyi.itunesloader.presentation.activities.mainActivity.presentation;

import com.stochanskyi.itunesloader.presentation.base.MVP.BasePresenter;

public class MainActivityPresenter extends BasePresenter<IMainActivityView> {

    @Override
    public void onStart() {

    }
    public void onMovieButtonClicked() {
        view.toMovies(true);
    }
    public void onAudiobooksClicked(){
        view.toAudiobooks(true);
    }
    public void onPodcastsClicked(){
        view.toPodcasts(true);
    }
    public void onFavouritesClicked() {
        view.toFavourites(true);
    }
}
