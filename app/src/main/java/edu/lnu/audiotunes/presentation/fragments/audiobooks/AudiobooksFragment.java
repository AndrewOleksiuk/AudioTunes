package edu.lnu.audiotunes.presentation.fragments.audiobooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import edu.lnu.audiotunes.R;
import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobookModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.ArtworksAdapter;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.StarButtonListener;
import edu.lnu.audiotunes.presentation.fragments.audiobookInfo.AudiobookInfoFragment;
import edu.lnu.audiotunes.presentation.fragments.audiobooks.presentation.AudiobooksPresenter;
import edu.lnu.audiotunes.presentation.fragments.audiobooks.presentation.IAudiobooksView;

import java.util.ArrayList;

public class AudiobooksFragment extends BaseFragment<AudiobooksPresenter, IAudiobooksView>
implements IAudiobooksView{
    private ArtworksAdapter<AudiobookModel> adapter;

    private SwipeRefreshLayout refreshLayout;

    public static AudiobooksFragment newInstance(){
        Bundle args = new Bundle();
        AudiobooksFragment fragment = new AudiobooksFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items_view, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden)
            presenter.onShow();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void createPresenterInstance() {
        presenter = AudiobooksPresenter.getInstance();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
    }

    @Override
    public void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.itemsRecycler);
        refreshLayout = view.findViewById(R.id.swipeLayout);
        refreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
        adapter = new ArtworksAdapter<>();
        adapter.setItemClickedListener((View v, int position) -> presenter.onItemClicked(position));
        adapter.setStarButtonlistener(new StarButtonListener() {
            @Override
            public void onLike(int position) {
                presenter.onStarLiked(position);
            }

            @Override
            public void onUnlike(int position) {
                presenter.onStartUnliked(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateData(ArrayList<AudiobookModel> model) {
        adapter.setData(model);
    }

    @Override
    public void toAudiobookInfo(int id) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, AudiobookInfoFragment.newInstance(id), "Info fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onRefreshFinished() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
    }
}
