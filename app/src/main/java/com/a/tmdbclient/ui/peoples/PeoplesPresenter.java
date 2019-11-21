package com.a.tmdbclient.ui.peoples;

import android.content.Context;

import com.a.tmdbclient.App;
import com.a.tmdbclient.data.NetworkUtils;
import com.a.tmdbclient.data.peoples.PeoplesRepository;
import com.a.tmdbclient.data.peoples.pojo.PeopleDetails;
import com.a.tmdbclient.data.peoples.pojo.PeopleModel;
import com.a.tmdbclient.ui.peoples.view.PeoplesDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class PeoplesPresenter {

    @Inject
    PeoplesRepository repository;
    private PeoplesView mView;
    private Context mContext;
    private PeoplesRecyclerViewAdapter mAdapter;
    private String searchQuery;
    private int searchPage = 1;

    public PeoplesPresenter() {
        App.getAppComponent().inject(this);
    }

    public void setView(PeoplesView view, Context context) {
        mView = view;
        mContext = context;
    }

    public void setAdapter(PeoplesRecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    public void getPeopleDetails(int id, final PeoplesDetailsActivity activity) {
        repository.getPeopleDetails(id, new NetworkUtils.PeopleDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, PeopleDetails peopleDetails) {
                activity.setDetails(peopleDetails);
            }
        });
    }

    public void addPopularPeoples(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            repository.getPopularPeoples(page, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError(call.toString());
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                    mAdapter.addData(peopleModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void setPopularPeoples(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            repository.getPopularPeoples(page, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError(call.toString());
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                    mAdapter.setData(peopleModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void searchPeoples(String query, int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            mView.setSearchProgressBarVisibility(true);
            searchQuery = query;
            searchPage = page;
            repository.searchPeoples(query, page, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.setSearchProgressBarVisibility(false);
                    mView.setProgressBarVisibility(false);
                    mView.showApiError(call.toString());
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> movieModels) {
                    mView.setSearchProgressBarVisibility(false);
                    mView.setProgressBarVisibility(false);
                    mAdapter.setSearchData(movieModels);
                }
            });
        }
    }

    public void searchMorePeoples() {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            repository.searchPeoples(searchQuery, ++searchPage, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError(call.toString());
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                    mAdapter.addSearchData(peopleModels);
                }
            });
        }
    }

}
