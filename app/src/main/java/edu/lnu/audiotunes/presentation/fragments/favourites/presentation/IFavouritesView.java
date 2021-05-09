package edu.lnu.audiotunes.presentation.fragments.favourites.presentation;

import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobookModel;
import edu.lnu.audiotunes.data.network.features.movies.models.MovieModel;
import edu.lnu.audiotunes.data.network.features.podcasts.models.PodcastModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

import java.util.ArrayList;

public interface IFavouritesView extends IBaseView {
    void toAudiobookInfo(int id);
    void toMovieInfo(int id);
    void toPodcastInfo(int id);
    void updateData(ArrayList<AudiobookModel> audiobookModels, ArrayList<MovieModel> movieModels, ArrayList<PodcastModel> podcastModels);
}
