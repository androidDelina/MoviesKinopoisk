package com.example.movies;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1.3/movie?token=NMEDXS5-103M670-NDE4KJN-H36F1AN&limit=40&rating.imdb=7-10&sortField=rating.kp&sortType=-1&poster.url=%21null")
    Single<MovieResponse> getMovies(@Query("page") int page);

    @GET("v1/review?page=1&limit=10&token=NMEDXS5-103M670-NDE4KJN-H36F1AN")
    Single<ReviewResponse> getReviews(@Query("movieId") int movieId);
}
