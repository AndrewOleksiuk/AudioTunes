package edu.lnu.audiotunes.presentation.fragments.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.lnu.audiotunes.R;
import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobookModel;
import edu.lnu.audiotunes.data.network.features.movies.models.MovieModel;
import edu.lnu.audiotunes.data.network.features.podcasts.models.PodcastModel;
import edu.lnu.audiotunes.presentation.base.BaseFragment;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.ArtworksAdapter;
import edu.lnu.audiotunes.presentation.fragments.adapters.artwork.holders.StarButtonListener;
import edu.lnu.audiotunes.presentation.fragments.audiobookInfo.AudiobookInfoFragment;
import edu.lnu.audiotunes.presentation.fragments.favourites.presentation.FavouritesPresenter;
import edu.lnu.audiotunes.presentation.fragments.favourites.presentation.IFavouritesView;
import edu.lnu.audiotunes.presentation.fragments.movieInfo.MovieInfoFragment;
import edu.lnu.audiotunes.presentation.fragments.podcastInfo.PodcastInfoFragment;

import java.util.ArrayList;

public class FavouritesFragment extends BaseFragment<FavouritesPresenter, IFavouritesView>
        implements IFavouritesView {
    ArtworksAdapter<AudiobookModel> audiobooksAdapter;
    ArtworksAdapter<MovieModel> moviesAdapter;
    ArtworksAdapter<PodcastModel> podcastsAdapter;

    private TextView audiobooksText;
    private TextView moviesText;
    private TextView podcastsText;

    public static FavouritesFragment newInstance() {
        Bundle args = new Bundle();
        FavouritesFragment fragment = new FavouritesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            presenter.loadFromDB();
        }
    }

    @Override
    public void createPresenterInstance() {
        presenter = new FavouritesPresenter();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
    }

    @Override
    public void initViews(View view) {
        audiobooksText = view.findViewById(R.id.audiobooksText);
        moviesText = view.findViewById(R.id.moviesText);
        podcastsText = view.findViewById(R.id.podcastsText);

        RecyclerView audiobooksRecycler = view.findViewById(R.id.audiobooksRecycler);
        RecyclerView moviesRecycler = view.findViewById(R.id.moviesRecycler);
        RecyclerView podcastsRecycler = view.findViewById(R.id.podcastsRecycler);

        audiobooksRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        moviesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        podcastsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        audiobooksAdapter = new ArtworksAdapter<>();
        moviesAdapter = new ArtworksAdapter<>();
        podcastsAdapter = new ArtworksAdapter<>();

        audiobooksAdapter.setItemClickedListener((View v, int position) -> presenter.onAudiobookClick(position));
        moviesAdapter.setItemClickedListener((View v, int position) -> presenter.onMovieClick(position));
        podcastsAdapter.setItemClickedListener((View v, int position) -> presenter.onPodcastClick(position));

        audiobooksAdapter.setStarButtonlistener(new StarButtonListener() {
            @Override
            public void onLike(int position) {

            }

            @Override
            public void onUnlike(int position) {
                presenter.onAudiobookStarButtonUnliked(position);
            }
        });
        moviesAdapter.setStarButtonlistener(new StarButtonListener() {
            @Override
            public void onLike(int position) {

            }

            @Override
            public void onUnlike(int position) {
                presenter.onMovieStarButtonUnliked(position);
            }
        });
        podcastsAdapter.setStarButtonlistener(new StarButtonListener() {
            @Override
            public void onLike(int position) {

            }

            @Override
            public void onUnlike(int position) {
                presenter.onPodcastStarButtonUnliked(position);
            }
        });
        audiobooksRecycler.setAdapter(audiobooksAdapter);
        moviesRecycler.setAdapter(moviesAdapter);
        podcastsRecycler.setAdapter(podcastsAdapter);
    }


    @Override
    public void toAudiobookInfo(int id) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, AudiobookInfoFragment.newInstance(id), "Info fragment").commit();
    }

    @Override
    public void toMovieInfo(int id) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, MovieInfoFragment.newInstance(id), "Info fragment").commit();
    }

    @Override
    public void toPodcastInfo(int id) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, PodcastInfoFragment.newInstance(id), "Info fragment").commit();
    }

    @Override
    public void updateData(ArrayList<AudiobookModel> audiobookModels, ArrayList<MovieModel> movieModels, ArrayList<PodcastModel> podcastModels) {
        if(audiobookModels == null || audiobookModels.size() == 0)
            audiobooksText.setText("No favourite audiobooks");
        else
            audiobooksText.setText("Audiobooks");
        audiobooksAdapter.setData(audiobookModels);
        if(movieModels == null || movieModels.size() == 0) {
            moviesText.setText("No favourite movies");
        }
        else
            moviesText.setText("Movies");
        moviesAdapter.setData(movieModels);
        if(podcastModels == null || podcastModels.size() == 0)
            podcastsText.setText("No favourite podcasts");
        else
            podcastsText.setText("Podcasts");
        podcastsAdapter.setData(podcastModels);
    }
}
