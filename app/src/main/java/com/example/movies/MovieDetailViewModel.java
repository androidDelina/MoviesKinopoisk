package com.example.movies;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailViewModel";
    private MutableLiveData<List<Review>> listReviews = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LiveData<List<Review>> getListReviews(int movieId) {
        Log.d(TAG, movieId + " getListReviews");
        return listReviews;
    }

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void loadReviews(int movieId) {
        Log.d(TAG, movieId + " loadReviews");
        Disposable disposable = ApiFactory.apiService.getReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReviewResponse>() {
                    @Override
                    public void accept(ReviewResponse reviewResponse) throws Throwable {
                        Log.d(TAG, reviewResponse.toString());
                        listReviews.setValue(reviewResponse.getReviewList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
