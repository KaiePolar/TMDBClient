package com.a.tmdbclient.ui.peoples.view;

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
import com.a.tmdbclient.api.peoples.pojo.PeopleModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.peoples.PeopleRecyclerViewAdapter;
import com.a.tmdbclient.ui.peoples.PeoplesPresenter;
import com.a.tmdbclient.ui.peoples.PeoplesView;

import java.util.ArrayList;

import javax.inject.Inject;

public class PeoplesFragment extends Fragment implements PeoplesView,SwipeRefreshLayout.OnRefreshListener {

    @Inject
    PeoplesPresenter presenter;
    private SwipeRefreshLayout swipeLayout;
    private ProgressBar searchProgressBar;
    private RecyclerView recyclerView;
    private PeopleRecyclerViewAdapter adapter;
    private EditText searchEditText;
    private LinearLayoutManager linearLayoutManager;
    private TextView internetErrorTextView;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private String searchQuery;
    private int dataPage = 1;
    private int searchPage = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_peoples, container, false);
        App.getAppComponent().inject(this);
        init(root);

        presenter.setView(this,getContext());
        presenter.setAdapter(adapter);
        presenter.getPopularPeoples(dataPage);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!adapter.isSearchDataMain()) {
                    presenter.getPopularPeoples(++dataPage);
                } else {
                    presenter.searchMorePeoples();
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
                    presenter.searchPeoples(s.toString().trim(), searchPage);
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
        searchProgressBar = view.findViewById(R.id.people_search_progress_bar);
        recyclerView = view.findViewById(R.id.peoples_recycler_view);
        searchEditText = view.findViewById(R.id.people_search_edit_text);
        internetErrorTextView = view.findViewById(R.id.peoples_internet_error);
        swipeLayout = view.findViewById(R.id.swipe_layout);
        swipeLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PeopleRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addData(new ArrayList<PeopleModel>());
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

    }

    @Override
    public void onRefresh() {
        dataPage = 1;
        searchPage = 1;
        if (!adapter.isSearchDataMain()) {
            presenter.setPopularPeoples(dataPage);
        } else {
            presenter.searchPeoples(searchQuery,searchPage);
        }
    }
}