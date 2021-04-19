package edu.lnu.audiotunes.presentation.fragments.audiobooks.presentation;

import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobookModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

import java.util.ArrayList;

public interface IAudiobooksView extends IBaseView {
    void updateData(ArrayList<AudiobookModel> model);
    void toAudiobookInfo(int id);
    void onRefreshFinished();
}
