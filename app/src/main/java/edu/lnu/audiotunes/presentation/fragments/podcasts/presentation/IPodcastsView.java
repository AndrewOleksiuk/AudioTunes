package edu.lnu.audiotunes.presentation.fragments.podcasts.presentation;

import edu.lnu.audiotunes.data.network.features.podcasts.models.PodcastModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

import java.util.ArrayList;

public interface IPodcastsView extends IBaseView {
    void updateData(ArrayList<PodcastModel> data);
    void toPodcastInfo(int id);
    void onRefreshFinished();
}
