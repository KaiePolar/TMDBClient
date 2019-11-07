package com.a.tmdbclient.ui.shows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.shows.ShowModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShowRecyclerViewAdapter extends RecyclerView.Adapter<ShowRecyclerViewAdapter.ViewHolder>{

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

    private List<ShowModel> mData;
    private Context context;

    public ShowRecyclerViewAdapter() {
        mData = new ArrayList<>();
    }

    public void loadData(List<ShowModel> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ShowRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShowRecyclerViewAdapter.ViewHolder viewHolder = new ShowRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false));
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ShowRecyclerViewAdapter.ViewHolder holder, final int position) {
        ShowModel item = mData.get(position);
        holder.titleTextView.setText(item.getName());
        holder.releaseTextView.setText(item.getFirstAirDate());
        holder.descriptionTextView.setText(item.getOverview());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + item.getPosterPath())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

