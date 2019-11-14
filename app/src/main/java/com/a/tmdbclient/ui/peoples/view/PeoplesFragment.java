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

import com.a.tmdbclient.App;
import com.a.tmdbclient.R;
import com.a.tmdbclient.api.peoples.pojo.PeopleModel;
import com.a.tmdbclient.ui.EndlessRecyclerViewScrollListener;
import com.a.tmdbclient.ui.peoples.PeopleRecyclerViewAdapter;
import com.a.tmdbclient.ui.peoples.PeoplesPresenter;
import com.a.tmdbclient.ui.peoples.PeoplesView;

import java.util.ArrayList;

import javax.inject.Inject;

public class PeoplesFragment extends Fragment implements PeoplesView {

    @Inject
    PeoplesPresenter presenter;
    private ProgressBar progressBar;
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

        presenter.setView(this);
        presenter.setAdapter(adapter);
        presenter.getPopularPeoples(dataPage,getContext());

        endlessScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!adapter.isSearchDataMain()) {
                    presenter.getPopularPeoples(++dataPage, getContext());
                } else {
                    presenter.searchMorePeoples(getContext());
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
                searchQuery = s.toString().trim();
                if (!searchQuery.isEmpty()) {
                    presenter.searchPeoples(searchQuery, searchPage, getContext());
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
        progressBar = view.findViewById(R.id.peoples_progress_bar);
        searchProgressBar = view.findViewById(R.id.people_search_progress_bar);
        recyclerView = view.findViewById(R.id.peoples_recycler_view);
        searchEditText = view.findViewById(R.id.people_search_edit_text);
        internetErrorTextView = view.findViewById(R.id.peoples_internet_error);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PeopleRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addData(new ArrayList<PeopleModel>());
    }

    @Override
    public void setProgressBarVisibility(boolean visibility) {
        if (visibility) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        internetErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showApiError() {

    }

}