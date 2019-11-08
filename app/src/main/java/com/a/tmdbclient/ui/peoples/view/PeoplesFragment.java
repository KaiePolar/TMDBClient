package com.a.tmdbclient.ui.peoples.view;

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

import com.a.tmdbclient.App;
import com.a.tmdbclient.R;
import com.a.tmdbclient.api.peoples.pojo.PeopleModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.peoples.PeopleRecyclerViewAdapter;
import com.a.tmdbclient.ui.peoples.PeoplesPresenter;
import com.a.tmdbclient.ui.peoples.PeoplesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PeoplesFragment extends Fragment implements PeoplesView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private PeopleRecyclerViewAdapter adapter;
    @Inject
    PeoplesPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private int dataPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_peoples, container, false);
        App.getAppComponent().inject(this);
        presenter.setView(this);
        init(root);

        presenter.getPopularPeoples(dataPage,getContext());

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getPopularPeoples(++dataPage,getContext());
            }
        };
        recyclerView.addOnScrollListener(endlessScrollListener);

        return root;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.peoples_progress_bar);
        recyclerView = view.findViewById(R.id.peoples_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PeopleRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.loadData(new ArrayList<PeopleModel>());
        internetErrorTextView = view.findViewById(R.id.peoples_internet_error);
    }

    @Override
    public void setAdapterData(List<PeopleModel> data) {
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