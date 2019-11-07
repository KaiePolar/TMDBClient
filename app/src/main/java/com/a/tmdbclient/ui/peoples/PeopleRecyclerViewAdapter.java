package com.a.tmdbclient.ui.peoples;

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
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.a.tmdbclient.ui.peoples.view.PeopleDetailsActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleRecyclerViewAdapter.ViewHolder> {

    private List<PeopleModel> mData;
    private Context mContext;

    public PeopleRecyclerViewAdapter() {
        mData = new ArrayList<>();
    }

    public void loadData(List<PeopleModel> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTextView;
        TextView birthTextView;
        TextView descriptionTextView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.people_item_name);
            birthTextView = itemView.findViewById(R.id.people_item_birthday);
            imageView = itemView.findViewById(R.id.people_item_photo);
            descriptionTextView = itemView.findViewById(R.id.people_item_biography);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, PeopleDetailsActivity.class);
            intent.putExtra("id", mData.get(getAdapterPosition()).getId());
            mContext.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public PeopleRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PeopleRecyclerViewAdapter.ViewHolder viewHolder = new PeopleRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_people_item, parent, false));
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PeopleRecyclerViewAdapter.ViewHolder holder, final int position) {
        PeopleModel item = mData.get(position);
        holder.nameTextView.setText(item.getName());
        holder.birthTextView.setText(item.getKnownForDepartment());
        holder.descriptionTextView.setText("TMDb popularity - " + item.getPopularity());
        Glide.with(mContext)
                .load(NetworkUtils.IMG_BASE_URL+item.getProfilePath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
