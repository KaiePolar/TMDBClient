package com.a.tmdbclient.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.movie.MovieModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView releaseTextView;
        TextView descriptionTextView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_title);
            releaseTextView = itemView.findViewById(R.id.item_release);
            imageView = itemView.findViewById(R.id.item_photo);
            descriptionTextView = itemView.findViewById(R.id.item_description);
        }

    }

    private List<MovieModel> mData;
    private Context context;

    public MoviesRecyclerViewAdapter() {
        mData = new ArrayList<>();
    }

    public void loadMeasurements(List<MovieModel> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false));
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MovieModel item = mData.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.releaseTextView.setText(item.getReleaseDate());
        holder.descriptionTextView.setText(item.getOverview());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/"+item.getPosterPath())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
