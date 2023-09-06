package com.example.movies;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("year")
    private int year;

    @Embedded
    @SerializedName("poster")
    private Poster poster;

    @Embedded
    @SerializedName("rating")
    private Rating rating;

    @Embedded
    @SerializedName("watchability")
    private WatchabilityResponse watchabilityResponse;

    public Movie(int id,
                 String name,
                 String description,
                 int year,
                 Poster poster,
                 Rating rating,
                 WatchabilityResponse watchabilityResponse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
        this.watchabilityResponse = watchabilityResponse;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    public WatchabilityResponse getWatchabilityResponse() {
        return watchabilityResponse;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", rating=" + rating +
                ", watchabilityResponse=" + watchabilityResponse +
                '}';
    }
}
