package edu.lnu.audiotunes.presentation.fragments.movies;

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
import edu.lnu.audiotunes.data.network.features.movies.models.MovieModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.ArtworksAdapter;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.StarButtonListener;
import edu.lnu.audiotunes.presentation.fragments.movieInfo.MovieInfoFragment;
import edu.lnu.audiotunes.presentation.fragments.movies.presentation.IMoviesView;
import edu.lnu.audiotunes.presentation.fragments.movies.presentation.MoviesPresenter;

import java.util.ArrayList;

public class MoviesFragment extends BaseFragment<MoviesPresenter, IMoviesView> implements IMoviesView {

    private ArtworksAdapter<MovieModel> adapter;

    private SwipeRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items_view, container, false);
    }

    public static MoviesFragment newInstance() {
        Bundle args = new Bundle();
        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void createPresenterInstance() {
        presenter = MoviesPresenter.getInstance();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
    }

    @Override
    public void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.itemsRecycler);
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
        refreshLayout = view.findViewById(R.id.swipeLayout);
        refreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden)
            presenter.onShow();
    }

    @Override
    public void updateData(ArrayList<MovieModel> movies) {
        adapter.setData(movies);
    }

    @Override
    public void toMovieInfo(int id) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, MovieInfoFragment.newInstance(id), "Info fragment").commit();
    }

    @Override
    public void onRefreshFinished() {
        refreshLayout.setRefreshing(false);
    }
}
