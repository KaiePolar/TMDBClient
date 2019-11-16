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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.a.tmdbclient.App;
import com.a.tmdbclient.R;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.movies.MovieView;
import com.a.tmdbclient.ui.movies.MoviesPresenter;
import com.a.tmdbclient.ui.movies.MoviesRecyclerViewAdapter;

import javax.inject.Inject;

public class TopRatedMoviesFragment extends Fragment implements MovieView,SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MoviesPresenter presenter;
    private SwipeRefreshLayout swipeLayout;
    private ProgressBar searchProgressBar;
    private RecyclerView recyclerView;
    private MoviesRecyclerViewAdapter adapter;
    private EditText searchEditText;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private String searchQuery;
    private int dataPage = 1;
    private int searchPage = 1;

    @Override
    public void onPause() {
        super.onPause();
        recyclerView.removeOnScrollListener(endlessScrollListener);
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);
        swipeLayout.setOnRefreshListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeLayout.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(endlessScrollListener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        presenter.setAdapter(adapter);
        presenter.setView(this, getContext());
        presenter.addTopRatedMovies(dataPage);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        App.getAppComponent().inject(this);
        init(root);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!adapter.isSearchDataMain()) {
                    presenter.addTopRatedMovies(++dataPage);
                } else {
                    presenter.searchMoreMovies();
                }
            }

        };

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setSearchProgressBarVisibility(true);
                if (!s.toString().trim().isEmpty()) {
                    searchQuery = s.toString().trim();
                    presenter.searchMovies(s.toString().trim(), searchPage);
                    adapter.setSearchDataMain(true);
                    dataPage = 1;
                } else {
                    adapter.setSearchDataMain(false);
                    searchQuery = "";
                    searchPage = 1;
                    setSearchProgressBarVisibility(false);
                }
            }
        });

        return root;
    }

    @Override
    public void init(View view) {
        searchProgressBar = view.findViewById(R.id.movies_search_progress_bar);
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        searchEditText = view.findViewById(R.id.movie_search_edit_text);
        internetErrorTextView = view.findViewById(R.id.movies_internet_error);
        swipeLayout = view.findViewById(R.id.swipe_layout);
        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MoviesRecyclerViewAdapter();
    }

    @Override
    public void setProgressBarVisibility(boolean visibility) {
        if (visibility) {
            swipeLayout.setRefreshing(true);
            recyclerView.setVisibility(View.GONE);
        } else {
            swipeLayout.setRefreshing(false);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setSearchProgressBarVisibility(boolean visibility) {
        if (visibility) {
            searchProgressBar.setVisibility(View.VISIBLE);
        } else {
            searchProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoInternetError() {
        swipeLayout.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        internetErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApiError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        dataPage = 1;
        searchPage = 1;
        if (!adapter.isSearchDataMain()) {
            presenter.setTopRatedMovies(dataPage);
        } else {
            presenter.searchMovies(searchQuery,searchPage);
        }
    }

}