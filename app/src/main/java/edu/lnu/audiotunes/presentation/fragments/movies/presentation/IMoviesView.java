package edu.lnu.audiotunes.presentation.fragments.movies.presentation;

import edu.lnu.audiotunes.data.network.features.movies.models.MovieModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

import java.util.ArrayList;

public interface IMoviesView extends IBaseView {
    void updateData(ArrayList<MovieModel> movies);
    void toMovieInfo(int id);
    void onRefreshFinished();
}
