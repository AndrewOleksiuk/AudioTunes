package com.stochanskyi.itunesloader.presentation.activities.mainActivity.navigation;

public interface INavigation {
    void toAudiobooks(boolean addToBackStack);
    void toMovies(boolean addToBackStack);
    void toPodcasts(boolean addToBackStack);
    void toFavourites(boolean addToBackStack);
}
