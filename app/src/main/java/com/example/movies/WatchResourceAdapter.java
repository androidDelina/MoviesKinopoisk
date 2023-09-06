package com.example.movies;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WatchResourceAdapter extends RecyclerView.Adapter<WatchResourceAdapter.WatchResourceViewHolder> {

    private List<WatchResource> watchResourceList = new ArrayList<>();

    private OnWatchResourceClickListener onWatchResourceClickListener = null;
    public void setWatchResourceList(List<WatchResource> watchResourceList) {
        this.watchResourceList = watchResourceList;
        notifyDataSetChanged();
    }

    public void setOnWatchResourceClickListener(OnWatchResourceClickListener onWatchResourceClickListener) {
        this.onWatchResourceClickListener = onWatchResourceClickListener;
    }

    @NonNull
    @Override
    public WatchResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.watch_resource_item,
                parent,
                false
        );
        return new WatchResourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchResourceViewHolder holder, int position) {
        WatchResource watchResource = watchResourceList.get(position);
        String url = watchResource.getLogo().getUrl();
        Log.d("TAG", url);

        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(url))
                .error(R.drawable.baseline_insert_photo_24)
                .into(holder.imageViewLogo);

        holder.textViewNameWatchRes.setText(watchResource.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWatchResourceClickListener != null) {
                    onWatchResourceClickListener.onWatchResourceClick(watchResource);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return watchResourceList.size();
    }

    class WatchResourceViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewLogo;
        private TextView textViewNameWatchRes;

        public WatchResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo);
            textViewNameWatchRes = itemView.findViewById(R.id.textViewNameWatchRes);
        }
    }

    interface OnWatchResourceClickListener {
        void onWatchResourceClick(WatchResource watchResource);
    }
}
