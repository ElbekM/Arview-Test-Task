package com.elbek.twitchviewer.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.elbek.twitchviewer.database.DateConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "streams")
public class GameOverview {

    //@TypeConverters(DateConverter.class)

    @Embedded
    @SerializedName("game")
    @Expose
    private Game game;
    @SerializedName("viewers")
    @Expose
    private Integer viewers;
    @PrimaryKey
    @NotNull
    @SerializedName("channels")
    @Expose
    private Integer channels;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getViewers() {
        return viewers;
    }

    public void setViewers(Integer viewers) {
        this.viewers = viewers;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels(Integer channels) {
        this.channels = channels;
    }

}



