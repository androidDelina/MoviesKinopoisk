package com.example.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM favorite_movie_bd")
    LiveData<List<Movie>> getAllSavedMoves();

    @Query("SELECT * FROM favorite_movie_bd WHERE id = :movieId")
    LiveData<Movie> getMovieById(int movieId);

    @Insert
    Completable addMovie(Movie movie);

    @Query("DELETE FROM favorite_movie_bd WHERE id = :movieId")
    Completable removeMovie(int movieId);
}
