package edu.lnu.audiotunes.presentation.fragments.podcasts;

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
import edu.lnu.audiotunes.data.network.features.podcasts.models.PodcastModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.ArtworksAdapter;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.StarButtonListener;
import edu.lnu.audiotunes.presentation.fragments.podcastInfo.PodcastInfoFragment;
import edu.lnu.audiotunes.presentation.fragments.podcasts.presentation.IPodcastsView;
import edu.lnu.audiotunes.presentation.fragments.podcasts.presentation.PodcastsPresenter;

import java.util.ArrayList;

public class PodcastsFragment extends BaseFragment<PodcastsPresenter, IPodcastsView>
        implements IPodcastsView {

    ArtworksAdapter<PodcastModel> adapter;
    SwipeRefreshLayout refreshLayout;

    public static PodcastsFragment newInstance() {
        Bundle args = new Bundle();
        PodcastsFragment fragment = new PodcastsFragment();
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
    public void createPresenterInstance() {
        presenter = PodcastsPresenter.getInstance();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
    }

    @Override
    public void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.itemsRecycler);
        adapter = new ArtworksAdapter<>();
        adapter.setItemClickedListener((View v, int position) -> presenter.onItemClick(position));
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
        refreshLayout = view.findViewById(R.id.swipeLayout);
        refreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateData(ArrayList<PodcastModel> data) {
        adapter.setData(data);
    }

    @Override
    public void toPodcastInfo(int id) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, PodcastInfoFragment.newInstance(id), "Info fragment")
                .commit();

    }

    @Override
    public void onRefreshFinished() {
        refreshLayout.setRefreshing(false);
    }
}
