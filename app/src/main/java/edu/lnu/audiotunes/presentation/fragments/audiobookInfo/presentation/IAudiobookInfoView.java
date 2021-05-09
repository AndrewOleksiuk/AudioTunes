package edu.lnu.audiotunes.presentation.fragments.audiobookInfo.presentation;

import edu.lnu.audiotunes.data.network.features.info.audiobookInfo.models.AudiobookInfoModel;
import edu.lnu.audiotunes.presentation.base.MVP.IBaseView;

public interface IAudiobookInfoView extends IBaseView {
    void setData(AudiobookInfoModel model);
}
