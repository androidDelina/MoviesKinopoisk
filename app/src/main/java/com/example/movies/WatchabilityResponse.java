package com.example.movies;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WatchabilityResponse implements Serializable {
    @SerializedName("items")
    @Embedded
    private List<WatchResource> watchResourceList;

    public WatchabilityResponse(List<WatchResource> watchResourceList) {
        this.watchResourceList = watchResourceList;
    }

    public List<WatchResource> getWatchResourceList() {
        return watchResourceList;
    }

    @Override
    public String toString() {
        return "WatchabilityResponse{" +
                "watchResourceList=" + watchResourceList +
                '}';
    }
}
