package com.elbek.twitchviewer.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.elbek.twitchviewer.model.GameOverview;

import java.util.List;

@Dao
public interface GameOverviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GameOverview article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GameOverview> items);

    @Delete
    void delete(GameOverview article);

    @Query("SELECT * FROM streams")
    List<GameOverview> getAllArticles();
}
