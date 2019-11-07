package com.a.tmdbclient.ui.movies.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.movie.MovieModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.movies.MovieView;
import com.a.tmdbclient.ui.movies.MoviesPresenter;
import com.a.tmdbclient.ui.movies.MoviesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PopularMoviesFragment extends Fragment implements MovieView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesRecyclerViewAdapter adapter;
    private MoviesPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int dataPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        init(root);
        presenter = new MoviesPresenter(this, getContext());
        presenter.getPopularMovies(dataPage);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d("new data", "on load");
                presenter.getPopularMovies(++dataPage);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        return root;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.movies_progress_bar);
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MoviesRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.loadData(new ArrayList<MovieModel>());
        internetErrorTextView = view.findViewById(R.id.movie_internet_error);
    }

    @Override
    public void setAdapterData(List<MovieModel> data) {
        adapter.loadData(data);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoInternetError() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        internetErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApiError() {

    }

}