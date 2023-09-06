package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LogoWatchResurce implements Serializable {
    @SerializedName("url")
    private String url;

    public LogoWatchResurce(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LogoWatchResurce{" +
                "url='" + url + '\'' +
                '}';
    }
}
