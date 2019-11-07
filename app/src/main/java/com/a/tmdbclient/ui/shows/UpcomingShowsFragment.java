package com.a.tmdbclient.ui.shows;

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
import com.a.tmdbclient.api.shows.ShowModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

public class UpcomingShowsFragment extends Fragment implements ShowView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ShowRecyclerViewAdapter adapter;
    private ShowsPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int dataPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shows, container, false);
        init(root);
        presenter = new ShowsPresenter(this, getContext());
        presenter.getUpcomingShows(dataPage);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d("new data", "on load");
                presenter.getUpcomingShows(++dataPage);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        return root;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.shows_progress_bar);
        recyclerView = view.findViewById(R.id.shows_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShowRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.loadData(new ArrayList<ShowModel>());
        internetErrorTextView = view.findViewById(R.id.show_internet_error);
    }

    @Override
    public void setAdapterData(List<ShowModel> data) {
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
