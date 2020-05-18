package com.elbek.twitchviewer.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elbek.twitchviewer.model.GameOverview;

@Database(entities = GameOverview.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GameOverviewDao getArticleDao();

}