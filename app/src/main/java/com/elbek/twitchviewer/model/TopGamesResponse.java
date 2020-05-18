package com.elbek.twitchviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopGamesResponse {

    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("top")
    @Expose
    private List<GameOverview> top = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<GameOverview> getTop() {
        return top;
    }

    public void setTop(List<GameOverview> top) {
        this.top = top;
    }

    /*@SerializedName("gameOverview")
    @Expose
    private List<GameOverview> gameOverview = null;

    public List<GameOverview> getGameOverview() {
        return gameOverview;
    }

    public void setGameOverview(List<GameOverview> gameOverview) {
        this.gameOverview = gameOverview;
    }*/
}
