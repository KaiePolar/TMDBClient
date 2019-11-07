package com.a.tmdbclient.ui.shows;

import android.content.Context;

import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.shows.ShowDetails;
import com.a.tmdbclient.api.shows.ShowModel;
import com.a.tmdbclient.api.shows.ShowNetworkManager;
import com.a.tmdbclient.ui.shows.view.ShowDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ShowsPresenter {

    private ShowView mView;
    private Context mContext;

    public ShowsPresenter(ShowView view, Context context) {
        mView = view;
        mContext = context;
    }

    public static void getShowsDetails(int id, final ShowDetailsActivity activity) {
        ShowNetworkManager.getShowDetails(id, new NetworkUtils.ShowDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, ShowDetails showDetails) {
                activity.setDetails(showDetails);
            }
        });
    }

    public void getPopularShows(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            ShowNetworkManager.getPopularShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mView.setAdapterData(showModels);
                }
            });
        }
    }

    public void getBestShows(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            ShowNetworkManager.getTopRatedShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mView.setAdapterData(showModels);
                }
            });
        }
    }

    public void getUpcomingShows(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            ShowNetworkManager.getUpcomingShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mView.setAdapterData(showModels);
                }
            });
        }
    }

    public void getNowPlayingShows(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            ShowNetworkManager.getNowPlayingShows(page, new NetworkUtils.ShowLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<ShowModel> showModels) {
                    mView.setAdapterData(showModels);
                }
            });
        }
    }

}
