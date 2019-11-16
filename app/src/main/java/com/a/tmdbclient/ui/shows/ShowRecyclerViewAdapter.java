package com.a.tmdbclient.ui.shows;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.data.NetworkUtils;
import com.a.tmdbclient.data.shows.pojo.ShowModel;
import com.a.tmdbclient.ui.shows.view.ShowDetailsActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShowRecyclerViewAdapter extends RecyclerView.Adapter<ShowRecyclerViewAdapter.ViewHolder> {

    private List<ShowModel> mCategoryData;
    private List<ShowModel> mSearchData;
    private List<ShowModel> mData;
    private boolean isSearchDataMain;
    private Context mContext;

    public ShowRecyclerViewAdapter() {
        mCategoryData = new ArrayList<>();
        mSearchData = new ArrayList<>();
        mData = mCategoryData;
    }

    public void addData(List<ShowModel> data) {
        mCategoryData.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<ShowModel> data) {
        mCategoryData.clear();
        mCategoryData.addAll(data);
        notifyDataSetChanged();
    }

    public void addSearchData(List<ShowModel> data) {
        if (!mSearchData.containsAll(data)) {
            mSearchData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void setSearchData(List<ShowModel> data) {
        mSearchData.clear();
        mSearchData.addAll(data);
        notifyDataSetChanged();
    }

    public boolean isSearchDataMain() {
        return isSearchDataMain;
    }

    public void setSearchDataMain(boolean b) {
        isSearchDataMain = b;
        if (isSearchDataMain) {
            mData = mSearchData;
        } else {
            mData = mCategoryData;
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, ShowDetailsActivity.class);
            intent.putExtra("id", mData.get(getAdapterPosition()).getId());
            mContext.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ShowRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShowRecyclerViewAdapter.ViewHolder viewHolder = new ShowRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false));
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ShowRecyclerViewAdapter.ViewHolder holder, final int position) {
        ShowModel item = mData.get(position);
        holder.titleTextView.setText(item.getName());
        holder.releaseTextView.setText(item.getFirstAirDate());
        holder.descriptionTextView.setText(item.getOverview());
        Glide.with(mContext)
                .load(NetworkUtils.IMG_BASE_URL+item.getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}

