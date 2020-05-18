package com.elbek.twitchviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopGamesResponse {

    @SerializedName("top")
    private List<GameOverview> gameOverview = null;

    public List<GameOverview> getGameOverview() {
        return gameOverview;
    }

    public void setGameOverview(List<GameOverview> gameOverview) {
        this.gameOverview = gameOverview;
    }
}
