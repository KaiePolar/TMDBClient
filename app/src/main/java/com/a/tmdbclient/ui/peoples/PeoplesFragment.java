package com.a.tmdbclient.ui.peoples;

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
import com.a.tmdbclient.api.peoples.PeopleModel;

import java.util.List;

public class PeoplesFragment extends Fragment implements PeoplesView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private PeopleRecyclerViewAdapter adapter;
    private PeoplesPresenter presenter;
    private TextView internetErrorTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_peoples, container, false);
        init(root);
        adapter = new PeopleRecyclerViewAdapter();
        presenter = new PeoplesPresenter(this,getContext());
        presenter.getPopularPeoples(1);

        return root;
    }

    @Override
    public void init(View view){
        progressBar = view.findViewById(R.id.peoples_progress_bar);
        recyclerView = view.findViewById(R.id.peoples_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        internetErrorTextView = view.findViewById(R.id.people_internet_error);
    }

    @Override
    public void setAdapterData(List<PeopleModel> data){
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