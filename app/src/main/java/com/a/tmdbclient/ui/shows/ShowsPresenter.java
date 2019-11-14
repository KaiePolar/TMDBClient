package com.a.tmdbclient.ui.shows;

import android.content.Context;

import com.a.tmdbclient.App;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.shows.ShowsRepository;
import com.a.tmdbclient.api.shows.pojo.ShowDetails;
import com.a.tmdbclient.api.shows.pojo.ShowModel;
import com.a.tmdbclient.ui.shows.view.ShowDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class ShowsPresenter {

    @Inject
    ShowsRepository repository;
    private ShowView mView;
    private ShowRecyclerViewAdapter mAdapter;
    private String searchQuery;
    private int searchPage = 1;

    public ShowsPresenter(){
        App.getAppComponent().inject(this);
    }

    public void setView(ShowView view){
        mView = view;
    }

    public void setAdapter(ShowRecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    public void getShowsDetails(int id, final ShowDetailsActivity activity) {
        repository.getShowDetails(id, new NetworkUtils.ShowDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, ShowDetails showDetails) {
                activity.setDetails(showDetails);
            }
        });
    }

    public void getPopularShows(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getPopularShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mAdapter.addData(showModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void getBestShows(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getTopRatedShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mAdapter.addData(showModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void getUpcomingShows(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getUpcomingShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mAdapter.addData(showModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void getNowPlayingShows(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getNowPlayingShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mAdapter.addData(showModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void searchShows(String query,int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            mView.setSearchProgressBarVisibility(true);
            searchQuery = query;
            searchPage = page;
            repository.searchShows(query,page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.setSearchProgressBarVisibility(false);
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mView.setSearchProgressBarVisibility(false);
                    mAdapter.setSearchData(showModels);
                }
            });
        }
    }

    public void searchMoreShows(Context context){
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.searchShows(searchQuery,++searchPage,new  NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mAdapter.addSearchData(showModels);
                    mView.setProgressBarVisibility(false);
                }

            });
        }
    }

}
