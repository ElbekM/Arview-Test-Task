package com.elbek.twitchviewer.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Game {

    @NotNull
    @SerializedName("name")
    @Expose
    private String name;
    @NotNull
    @SerializedName("_id")
    @Expose
    private Integer id;
    @NotNull
    @SerializedName("giantbomb_id")
    @Expose
    private Integer giantbombId;
    @Embedded(prefix = "client_bean_")
    @SerializedName("box")
    @Expose
    private Box box;
    @Embedded
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @NotNull
    @SerializedName("localized_name")
    @Expose
    private String localizedName;
    @NotNull
    @SerializedName("locale")
    @Expose
    private String locale;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGiantbombId() {
        return giantbombId;
    }

    public void setGiantbombId(Integer giantbombId) {
        this.giantbombId = giantbombId;
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

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
