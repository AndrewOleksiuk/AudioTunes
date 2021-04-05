package edu.lnu.audiotunes.data.DB;

import android.app.Activity;
import android.content.Context;

import androidx.room.Room;

public class DBClient {
    private static AppDatabase db = null;
    public static void initDB(Context context) {
        createInstance(context);
    }
    public static AppDatabase getInstance() {
        return db;
    }
    private static void createInstance(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "database").allowMainThreadQueries().build();
    }
}
