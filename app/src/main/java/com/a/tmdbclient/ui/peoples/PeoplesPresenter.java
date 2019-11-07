package com.a.tmdbclient.ui.peoples;

import android.content.Context;

import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeopleDetails;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.a.tmdbclient.api.peoples.PeopleNetworkManager;
import com.a.tmdbclient.ui.peoples.view.PeopleDetailsActivity;

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

    public void getPopularPeoples(int page) {
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

    public static void getPeopleDetails(int id, final PeopleDetailsActivity activity) {
        PeopleNetworkManager.getPeopleDetails(id, new NetworkUtils.PeopleDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, PeopleDetails peopleDetails) {
                activity.setDetails(peopleDetails);
            }
        });
    }

}
