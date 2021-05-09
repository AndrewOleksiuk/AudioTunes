package edu.lnu.audiotunes.presentation.fragments.movieInfo.presentation;

import edu.lnu.audiotunes.data.network.features.info.movieInfo.models.MovieInfoModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

public interface IMovieInfoView extends IBaseView {
    void setData(MovieInfoModel model);
}
