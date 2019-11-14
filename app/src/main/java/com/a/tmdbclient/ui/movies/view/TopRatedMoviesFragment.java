package com.a.tmdbclient.ui.movies.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.App;
import com.a.tmdbclient.R;
import com.a.tmdbclient.api.movie.pojo.MovieModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.movies.MovieView;
import com.a.tmdbclient.ui.movies.MoviesPresenter;
import com.a.tmdbclient.ui.movies.MoviesRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class TopRatedMoviesFragment extends Fragment implements MovieView {

    @Inject
    MoviesPresenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesRecyclerViewAdapter adapter;
    private EditText searchEditText;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private String searchQuery;
    private int dataPage = 1;
    private int searchPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        App.getAppComponent().inject(this);
        init(root);

        presenter.setView(this);
        presenter.setAdapter(adapter);
        presenter.getTopRatedMovies(dataPage,getContext());

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getTopRatedMovies(++dataPage,getContext());
            }
        };
        recyclerView.addOnScrollListener(endlessScrollListener);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchQuery = s.toString().trim();
                if (!searchQuery.isEmpty()) {
                    presenter.searchMovies(searchQuery, searchPage, getContext());
                    adapter.setSearchDataMain(true);
                } else {
                    adapter.setSearchDataMain(false);
                }
            }

        });

        return root;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.movies_progress_bar);
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        searchEditText = view.findViewById(R.id.movie_search_edit_text);
        internetErrorTextView = view.findViewById(R.id.movies_internet_error);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MoviesRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addData(new ArrayList<MovieModel>());
    }

    @Override
    public void setProgressBarVisibility(boolean visibility) {
        if (visibility) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            searchEditText.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            searchEditText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setSearchProgressBarVisibility(boolean visibility) {

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