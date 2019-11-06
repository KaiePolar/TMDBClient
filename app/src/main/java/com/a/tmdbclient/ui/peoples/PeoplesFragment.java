package com.a.tmdbclient.ui.peoples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.a.tmdbclient.api.peoples.PeopleNetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PeoplesFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private PeopleRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_peoples, container, false);


        progressBar = root.findViewById(R.id.peoples_progress_bar);
        recyclerView = root.findViewById(R.id.peoples_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PeopleRecyclerViewAdapter();

        PeopleNetworkManager.getPopularPeoples(1, new NetworkUtils.PeopleLoadCallback() {
            @Override
            public void onLoadFail(Call call) {

            }

            @Override
            public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                adapter.loadData(peopleModels);
                setAdapter(adapter);
            }
        });
        return root;
    }

    private void setAdapter(PeopleRecyclerViewAdapter adapter){
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

}