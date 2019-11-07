package com.a.tmdbclient.ui.peoples;

import android.content.Context;

import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.a.tmdbclient.api.peoples.PeopleNetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PeoplesPresenter {

    private PeoplesView mView;
    private Context mContext;

    public PeoplesPresenter(PeoplesView view, Context context) {
        mView = view;
        mContext = context;
    }

    void getPopularPeoples(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            PeopleNetworkManager.getPopularPeoples(page, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                    mView.setAdapterData(peopleModels);
                }
            });
        }
    }
}
