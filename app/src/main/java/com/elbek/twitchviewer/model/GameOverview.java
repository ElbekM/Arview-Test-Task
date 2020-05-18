package com.elbek.twitchviewer.model;

import com.google.gson.annotations.SerializedName;

public class GameOverview {

    private Integer viewers;
    private Integer channels;

    @SerializedName("game")
    public GameDetails gameDetails;

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }

    public class GameDetails {

        public String name;

        @SerializedName("box")
        public Box box;

        @SerializedName("logo")
        public Logo logo;

    }

    public class Box {

        public String large;
        public String medium;
        public String small;
        public String template;

    }

    public class Logo {

        public String large;
        public String medium;
        public String small;
        public String template;
    }

}
