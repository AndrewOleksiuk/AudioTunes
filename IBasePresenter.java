package edu.lnu.audiotunes.presentation.base.MVP;

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);
    void onStart();
    void release();
}
