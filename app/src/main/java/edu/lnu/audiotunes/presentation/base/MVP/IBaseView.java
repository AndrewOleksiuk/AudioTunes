package edu.lnu.audiotunes.presentation.base.MVP;

import android.view.View;

public interface IBaseView {
    void createPresenterInstance();
    void initPresenter();
    void initViews(View view);

}
