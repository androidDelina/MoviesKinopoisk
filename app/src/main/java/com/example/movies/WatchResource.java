package com.example.movies;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WatchResource implements Serializable {
    @SerializedName("name")
    private String name;

    @Embedded
    @SerializedName("logo")
    private LogoWatchResurce logo;

    @SerializedName("url")
    private String url;

    public WatchResource(String name, LogoWatchResurce logo, String url) {
        this.name = name;
        this.logo = logo;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public LogoWatchResurce getLogo() {
        return logo;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "WatchResource{" +
                "name='" + name + '\'' +
                ", logo=" + logo +
                ", url='" + url + '\'' +
                '}';
    }
}
