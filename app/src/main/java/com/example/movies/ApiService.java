package com.example.movies;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1.3/movie?token=GDGXRP0-WQ8MM17-HVK3DDN-C4K57JB&limit=40&rating.imdb=7-10&sortField=rating.kp&sortType=-1&poster.url=%21null")
    Single<MovieResponse> getMovies(@Query("page") int page);

    @GET("v1/review?page=1&limit=10&token=GDGXRP0-WQ8MM17-HVK3DDN-C4K57JB")
    Single<ReviewResponse> getReviews(@Query("movieId") int movieId);
}
