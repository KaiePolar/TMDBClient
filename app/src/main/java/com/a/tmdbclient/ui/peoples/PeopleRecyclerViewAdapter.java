package com.a.tmdbclient.ui.peoples;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleRecyclerViewAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView birthTextView;
        TextView descriptionTextView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.people_name);
            birthTextView = itemView.findViewById(R.id.people_born);
            imageView = itemView.findViewById(R.id.people_photo);
            descriptionTextView = itemView.findViewById(R.id.people_biography);
        }

    }

    private List<PeopleModel> mData;
    private Context context;

    public PeopleRecyclerViewAdapter() {
        mData = new ArrayList<>();
    }

    public void loadData(List<PeopleModel> data) {
        Log.d("new data","loaded");
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeopleRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PeopleRecyclerViewAdapter.ViewHolder viewHolder = new PeopleRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_people_item, parent, false));
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PeopleRecyclerViewAdapter.ViewHolder holder, final int position) {
        PeopleModel item = mData.get(position);
        holder.nameTextView.setText(item.getName());
        holder.birthTextView.setText(item.getKnownForDepartment());
        holder.descriptionTextView.setText(String.valueOf(item.getPopularity()));
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + item.getProfilePath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
