package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewReviewTitle.setText(review.getTitle());
        holder.textViewReview.setText(review.getReview());

        int color = R.color.review_type_grey;
        if (review.getType() != null) {
            switch (review.getType()) {
                case "Позитивный":
                    color = R.color.review_type_green;
                    break;
                case "Нейтральный":
                    color = R.color.review_type_grey;
                    break;
                default:
                    color = R.color.review_type_red;
            }
        }

        holder.itemView.setBackgroundResource(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewAuthor;
        private TextView textViewReviewTitle;
        private TextView textViewReview;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewReviewTitle = itemView.findViewById(R.id.textViewReviewTitle);
            textViewReview = itemView.findViewById(R.id.textViewReview);
        }
    }
}
