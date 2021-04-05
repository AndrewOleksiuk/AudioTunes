package edu.lnu.audiotunes.data.DB;

import android.telecom.Call;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobookModel;
import edu.lnu.audiotunes.data.network.features.movies.models.MovieModel;
import edu.lnu.audiotunes.data.network.features.podcasts.models.PodcastModel;

import java.util.List;

@Dao
public interface DBDao {
    @Query("SELECT * FROM Movies")
    List<MovieModel> getAllMovies();
    @Query("SELECT * FROM Audiobooks")
    List<AudiobookModel> getAllAudiobooks();
    @Query("SELECT * FROM Podcasts")
    List<PodcastModel> getAllPodcasts();

    @Query("SELECT * FROM Audiobooks WHERE id = :id")
    AudiobookModel getAudiobook(int id);
    @Query("SELECT * FROM Movies WHERE id = :id")
    MovieModel getMovie(int id);
    @Query("SELECT * FROM Podcasts WHERE id = :id")
    PodcastModel getPodcast(int id);

    @Insert
    void insertAudiobook(AudiobookModel audiobookModel);
    @Insert
    void insertMovie(MovieModel movie);
    @Insert
    void insertPodcast(PodcastModel podcastModel);

    @Update
    void updateAudiobook(AudiobookModel audiobookModel);
    @Update
    void updateMovie(MovieModel movie);
    @Update
    void updatePodcast(PodcastModel podcastModel);

    @Delete
    void deleteAudiobook(AudiobookModel audiobookModel);
    @Delete
    void deleteMovie(MovieModel movieModel);
    @Delete
    void deletePodcast(PodcastModel podcastModel);
}
