package com.elbek.twitchviewer.model;

import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Game {

    @SerializedName("name")
    @Expose
    private String name;
    @Embedded
    @SerializedName("box")
    @Expose
    private Box box;
    @Embedded(prefix = "logo")
    @SerializedName("logo")
    @Expose
    private Logo logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public static class Box {

        @SerializedName("large")
        @Expose
        private String large;

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

    }

    public static class Logo {

        @SerializedName("large")
        @Expose
        private String large;

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }
    }

}
