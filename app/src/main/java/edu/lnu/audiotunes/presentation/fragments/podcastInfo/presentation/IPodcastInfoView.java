package edu.lnu.audiotunes.presentation.fragments.podcastInfo.presentation;

import edu.lnu.audiotunes.data.network.features.info.podcastInfo.models.PodcastInfoModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

public interface IPodcastInfoView extends IBaseView {
    void setData(PodcastInfoModel model);
}
