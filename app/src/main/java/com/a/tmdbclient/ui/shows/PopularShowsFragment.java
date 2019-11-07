package com.a.tmdbclient.ui.shows;

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
import com.a.tmdbclient.api.shows.ShowModel;

import java.util.List;

public class PopularShowsFragment extends Fragment implements ShowView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ShowRecyclerViewAdapter adapter;
    private ShowsPresenter presenter;
    private TextView internetErrorTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shows, container, false);
        init(root);
        adapter = new ShowRecyclerViewAdapter();
        presenter = new ShowsPresenter(this, getContext());
        presenter.getPopularShows(1);

        return root;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.shows_progress_bar);
        recyclerView = view.findViewById(R.id.shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        internetErrorTextView = view.findViewById(R.id.show_internet_error);
    }

    @Override
    public void setAdapterData(List<ShowModel> data) {
        adapter.loadData(data);
        recyclerView.setAdapter(adapter);
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