package com.elbek.twitchviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopGamesResponse {

    @SerializedName("top")
    @Expose
    private List<TwitchStream> topGames = null;

    public List<TwitchStream> getTopGames() {
        return topGames;
    }
}
