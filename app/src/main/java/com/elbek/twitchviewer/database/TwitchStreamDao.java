package com.elbek.twitchviewer.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.elbek.twitchviewer.model.TwitchStream;

import java.util.List;

@Dao
public interface TwitchStreamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TwitchStream article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TwitchStream> items);

    @Query("DELETE FROM twitchStreams")
    void deleteAll();

    @Query("SELECT * FROM twitchStreams")
    List<TwitchStream> getAllArticles();

}
