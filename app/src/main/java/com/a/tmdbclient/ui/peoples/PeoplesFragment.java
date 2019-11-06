package com.a.tmdbclient.ui.peoples;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.a.tmdbclient.api.peoples.PeopleNetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PeoplesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_peoples, container, false);

        PeopleNetworkManager.getPopularPeoples(1, new NetworkUtils.PeopleLoadCallback() {
            @Override
            public void onLoadFail(Call call) {

            }

            @Override
            public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                Log.d("sd",peopleModels.get(0).getName());
            }
        });
        return root;
    }
}