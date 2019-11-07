package com.a.tmdbclient.ui.movies;

import android.os.Bundle;
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

import java.util.List;

public class PopularMoviesFragment extends Fragment implements MovieView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesRecyclerViewAdapter adapter;
    private MoviesPresenter presenter;
    private TextView internetErrorTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        init(root);
        adapter = new MoviesRecyclerViewAdapter();
        presenter = new MoviesPresenter(this,getContext());
        presenter.getPopularMovies(1);

        return root;
    }

    @Override
    public void init(View view){
        progressBar = view.findViewById(R.id.movies_progress_bar);
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        internetErrorTextView = view.findViewById(R.id.movie_internet_error);
    }

    @Override
    public void setAdapterData(List<MovieModel> data){
        adapter.loadData(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoInternetError(){
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        internetErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApiError() {

    }
}