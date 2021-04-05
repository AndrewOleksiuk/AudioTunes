package edu.lnu.audiotunes.data.DB;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import edu.lnu.audiotunes.data.network.features.audiobooks.models.AudiobookModel;
import edu.lnu.audiotunes.data.network.features.movies.models.MovieModel;
import edu.lnu.audiotunes.data.network.features.podcasts.models.PodcastModel;

@Database(entities = {AudiobookModel.class, MovieModel.class, PodcastModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DBDao dbDao();
}
