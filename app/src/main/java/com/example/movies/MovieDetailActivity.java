package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailActivity";

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewReviews;
    private RecyclerView recyclerViewWatchResource;

    private static final String EXTRA_MOVIE = "movie";

    private MovieDetailViewModel viewModel;
    private ReviewAdapter reviewAdapter;
    private WatchResourceAdapter watchResourceAdapter;

    private List<WatchResource> watchResourceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Log.d(TAG, movie.getId() + "");
        viewModel.loadReviews(movie.getId());

        watchResourceList = movie.getWatchabilityResponse().getWatchResourceList();
        Log.d(TAG, watchResourceList.toString());

        Glide.with(this).load(movie.getPoster().getUrl()).into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);
        viewModel.getListReviews(movie.getId()).observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                Log.d(TAG, reviews.toString());
                reviewAdapter.setReviews(reviews);
                watchResourceAdapter.setWatchResourceList(watchResourceList);
                Log.e(TAG, watchResourceList.toString());

            }
        });

        watchResourceAdapter = new WatchResourceAdapter();
        watchResourceAdapter.setOnWatchResourceClickListener(new WatchResourceAdapter.OnWatchResourceClickListener() {
            @Override
            public void onWatchResourceClick(WatchResource watchResource) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(watchResource.getUrl()));
                startActivity(intent);
            }
        });
        recyclerViewWatchResource.setAdapter(watchResourceAdapter);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);

        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        recyclerViewWatchResource = findViewById(R.id.recyclerViewWatchResource);
    }
}