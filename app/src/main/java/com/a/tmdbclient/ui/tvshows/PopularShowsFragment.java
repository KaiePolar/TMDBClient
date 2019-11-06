package com.a.tmdbclient.ui.tvshows;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.shows.ShowModel;
import com.a.tmdbclient.api.shows.ShowNetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PopularShowsFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ShowRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shows, container, false);

        progressBar = root.findViewById(R.id.shows_progress_bar);
        recyclerView = root.findViewById(R.id.shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShowRecyclerViewAdapter();

        ShowNetworkManager.getPopularShows(1, new NetworkUtils.ShowLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
                Log.d("Fail", call.toString());
            }

            @Override
            public void onLoadSuccess(Response response, List<ShowModel> movieModels) {
                Log.d("Success", String.valueOf(response.code()));
                adapter.loadData(movieModels);
                setAdapter(adapter);
            }

        });

        return root;
    }

    private void setAdapter(ShowRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}