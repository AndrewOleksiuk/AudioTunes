package com.stochanskyi.itunesloader.presentation.activities.mainActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stochanskyi.itunesloader.R;
import com.stochanskyi.itunesloader.data.DB.DBClient;
import com.stochanskyi.itunesloader.presentation.activities.mainActivity.navigation.CustomBackStack;
import com.stochanskyi.itunesloader.presentation.activities.mainActivity.navigation.Screen;
import com.stochanskyi.itunesloader.presentation.activities.mainActivity.presentation.IMainActivityView;
import com.stochanskyi.itunesloader.presentation.activities.mainActivity.presentation.MainActivityPresenter;
import com.stochanskyi.itunesloader.presentation.base.BaseActivity;
import com.stochanskyi.itunesloader.presentation.base.BaseFragment;
import com.stochanskyi.itunesloader.presentation.fragments.audiobooks.AudiobooksFragment;
import com.stochanskyi.itunesloader.presentation.fragments.favourites.FavouritesFragment;
import com.stochanskyi.itunesloader.presentation.fragments.movies.MoviesFragment;
import com.stochanskyi.itunesloader.presentation.fragments.podcasts.PodcastsFragment;

public class MainActivity extends BaseActivity<MainActivityPresenter, IMainActivityView>
        implements IMainActivityView {
    private static final String AUDIOBOOKS_FRAGMENT_KEY = "audiobooks_fragment_key";
    private static final String MOVIES_FRAGMENT_KEY = "movies_fragment_key";
    private static final String PODCASTS_FRAGMENT_KEY = "podcasts_fragment_key";
    private static final String FAVOURITES_FRAGMENT_KEY = "favourites_fragment_key";
    private static final String BACKSTACK_KEY = "backstack_key";

    BottomNavigationView navigationView;
    ActionBar actionBar;
    CustomBackStack backStack;
    AudiobooksFragment audiobooksFragment = null;
    MoviesFragment moviesFragment = null;
    PodcastsFragment podcastsFragment = null;
    FavouritesFragment favouritesFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        backStack = new CustomBackStack();
        if (savedInstanceState == null) {
            initPages();
            this.toAudiobooks(true);
        } else {
            audiobooksFragment = (AudiobooksFragment) getSupportFragmentManager().findFragmentByTag(AUDIOBOOKS_FRAGMENT_KEY);
            moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentByTag(MOVIES_FRAGMENT_KEY);
            podcastsFragment = (PodcastsFragment) getSupportFragmentManager().findFragmentByTag(PODCASTS_FRAGMENT_KEY);
            favouritesFragment = (FavouritesFragment) getSupportFragmentManager().findFragmentByTag(FAVOURITES_FRAGMENT_KEY);
            backStack = savedInstanceState.getParcelable(BACKSTACK_KEY);
        }
        DBClient.initDB(getApplicationContext());

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(BACKSTACK_KEY, backStack);

        super.onSaveInstanceState(outState);
    }






    @Override
    public void onBackPressed() {
        BaseFragment infoFragment = (BaseFragment) fromScreen(backStack.getLastScreen()).getChildFragmentManager().findFragmentByTag("Info fragment");
        if (infoFragment != null) {
            fromScreen(backStack.getLastScreen()).getChildFragmentManager().beginTransaction().remove(infoFragment).commit();
            return;
        }

        Screen screen = backStack.pop();

        if (screen == null) {
            super.onBackPressed();
            return;
        }

        switch (screen) {
            case AUDIO_BOOKS:
                navigationView.setSelectedItemId(R.id.audiobooks);
                toAudiobooks(false);
                break;
            case MOVIES:
                navigationView.setSelectedItemId(R.id.movies);
                toMovies(false);
                break;
            case PODCASTS:
                navigationView.setSelectedItemId(R.id.podcasts);
                toPodcasts(false);
                break;
            case FAVOURITES:
                navigationView.setSelectedItemId(R.id.favourites);
                toFavourites(false);
                break;
        }
    }

    private void initViews() {
        actionBar = getSupportActionBar();
        navigationView = findViewById(R.id.navigationBar);
        navigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private void initPages() {
        audiobooksFragment = AudiobooksFragment.newInstance();
        moviesFragment = MoviesFragment.newInstance();
        podcastsFragment = PodcastsFragment.newInstance();
        favouritesFragment = FavouritesFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.contentFrame, audiobooksFragment, AUDIOBOOKS_FRAGMENT_KEY);
        fragmentTransaction.add(R.id.contentFrame, moviesFragment, MOVIES_FRAGMENT_KEY);
        fragmentTransaction.add(R.id.contentFrame, podcastsFragment, PODCASTS_FRAGMENT_KEY);
        fragmentTransaction.add(R.id.contentFrame, favouritesFragment, FAVOURITES_FRAGMENT_KEY);

        fragmentTransaction.hide(audiobooksFragment);
        fragmentTransaction.hide(moviesFragment);
        fragmentTransaction.hide(podcastsFragment);
        fragmentTransaction.hide(favouritesFragment);
        fragmentTransaction.commit();
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.audiobooks:
                presenter.onAudiobooksClicked();
                break;
            case R.id.movies:
                presenter.onMovieButtonClicked();
                break;
            case R.id.podcasts:
                presenter.onPodcastsClicked();
                break;
            case R.id.favourites:
                presenter.onFavouritesClicked();
                break;
        }
        return true;
    }

    @Override
    public void createPresenterInstance() {
        presenter = new MainActivityPresenter();
    }

    @Override
    public void initPresenter() {
        presenter.attachView(this);
    }


    @Override
    public void toAudiobooks(boolean addToBackStack) {
        actionBar.setTitle("Audiobooks");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(audiobooksFragment);

        fragmentTransaction.hide(moviesFragment);
        fragmentTransaction.hide(podcastsFragment);
        fragmentTransaction.hide(favouritesFragment);

        fragmentTransaction.commit();
        if (addToBackStack)
            backStack.add(Screen.AUDIO_BOOKS);
    }

    @Override
    public void toMovies(boolean addToBackStack) {
        actionBar.setTitle("Movies");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(moviesFragment);

        fragmentTransaction.hide(audiobooksFragment);
        fragmentTransaction.hide(podcastsFragment);
        fragmentTransaction.hide(favouritesFragment);

        fragmentTransaction.commit();
        if (addToBackStack)
            backStack.add(Screen.MOVIES);
    }

    @Override
    public void toPodcasts(boolean addToBackStack) {
        actionBar.setTitle("Podcasts");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(podcastsFragment);

        fragmentTransaction.hide(audiobooksFragment);
        fragmentTransaction.hide(moviesFragment);
        fragmentTransaction.hide(favouritesFragment);

        fragmentTransaction.commit();
        if (addToBackStack)
            backStack.add(Screen.PODCASTS);
    }

    @Override
    public void toFavourites(boolean addToBackStack) {
        actionBar.setTitle("Favourites");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.show(favouritesFragment);

        fragmentTransaction.hide(audiobooksFragment);
        fragmentTransaction.hide(moviesFragment);
        fragmentTransaction.hide(podcastsFragment);

        fragmentTransaction.commit();
        if (addToBackStack)
            backStack.add(Screen.FAVOURITES);
    }

    @Override
    public void initViews(View view) {

    }

    private BaseFragment fromScreen(Screen screen) {
        switch (screen) {
            case AUDIO_BOOKS:
                return audiobooksFragment;
            case MOVIES:
                return moviesFragment;
            case PODCASTS:
                return podcastsFragment;
            case FAVOURITES:
                return favouritesFragment;
            default:
                return null;
        }
    }

    private Screen ofFragment(BaseFragment fragment) {
        if (fragment instanceof AudiobooksFragment) return Screen.AUDIO_BOOKS;
        else if (fragment instanceof MoviesFragment) return Screen.MOVIES;
        else if (fragment instanceof PodcastsFragment) return Screen.PODCASTS;
        else if (fragment instanceof FavouritesFragment) return Screen.FAVOURITES;
        else return null;
    }
}
