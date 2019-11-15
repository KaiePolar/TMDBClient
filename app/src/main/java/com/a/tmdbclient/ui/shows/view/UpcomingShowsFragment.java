package com.a.tmdbclient.ui.shows.view;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.a.tmdbclient.App;
import com.a.tmdbclient.R;
import com.a.tmdbclient.api.shows.pojo.ShowModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.shows.ShowRecyclerViewAdapter;
import com.a.tmdbclient.ui.shows.ShowView;
import com.a.tmdbclient.ui.shows.ShowsPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class UpcomingShowsFragment extends Fragment implements ShowView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ShowsPresenter presenter;
    private ProgressBar searchProgressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShowRecyclerViewAdapter adapter;
    private EditText searchEditText;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private String searchQuery;
    private int dataPage = 1;
    private int searchPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shows, container, false);
        App.getAppComponent().inject(this);
        init(root);

        presenter.setView(this, getContext());
        presenter.setAdapter(adapter);
        presenter.getUpcomingShows(dataPage);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!adapter.isSearchDataMain()) {
                    presenter.getUpcomingShows(++dataPage);
                } else {
                    presenter.searchMoreShows();
                }
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
                setSearchProgressBarVisibility(true);
                if (!s.toString().trim().isEmpty()) {
                    searchQuery = s.toString().trim();
                    presenter.searchShows(s.toString().trim(), searchPage);
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
        searchProgressBar = view.findViewById(R.id.shows_search_progress_bar);
        recyclerView = view.findViewById(R.id.shows_recycler_view);
        searchEditText = view.findViewById(R.id.shows_search_edit_text);
        internetErrorTextView = view.findViewById(R.id.shows_internet_error);
        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShowRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addData(new ArrayList<ShowModel>());
    }

    @Override
    public void setProgressBarVisibility(boolean visibility) {
        if (visibility) {
            swipeRefreshLayout.setRefreshing(true);
            recyclerView.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
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
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        internetErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApiError(String error) {

    }

    @Override
    public void onRefresh() {
        dataPage = 1;
        searchPage = 1;
        if (!adapter.isSearchDataMain()) {
            presenter.setUpcomingShows(dataPage);
        } else {
            presenter.searchShows(searchQuery,searchPage);
        }
    }

}
