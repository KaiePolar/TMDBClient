package com.a.tmdbclient.ui.movies;

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
import com.a.tmdbclient.api.NetworkManager;
import com.a.tmdbclient.api.movie.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        progressBar = root.findViewById(R.id.movies_progress_bar);
        recyclerView = root.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MoviesRecyclerViewAdapter();

        NetworkManager.getPopularMovies(1, new NetworkManager.LoadCallback() {
            @Override
            public void onLoadFail(Call call) {
                Log.d("Fail",call.toString());
            }

            @Override
            public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                Log.d("Success", String.valueOf(response.code()));
                adapter.loadMeasurements(movieModels);
                setAdapater(adapter);
            }

        });

        return root;
    }

    private void setAdapater(MoviesRecyclerViewAdapter adapater){
        recyclerView.setAdapter(adapater);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    };
}