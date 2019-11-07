package com.a.tmdbclient.ui.movies;

import android.content.Context;

import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.movie.MovieModel;
import com.a.tmdbclient.api.movie.MoviesNetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MoviesPresenter {

    private MovieView mView;
    private Context mContext;

    MoviesPresenter(MovieView view, Context context) {
        mView = view;
        mContext = context;
    }

    void getPopularMovies(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            MoviesNetworkManager.getPopularMovies(page, new NetworkUtils.MovieLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mView.setAdapterData(movieModels);
                }
            });
        }
    }

    void getUpcomingMovies(int page){
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            MoviesNetworkManager.getUpcomingMovies(page, new NetworkUtils.MovieLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mView.setAdapterData(movieModels);
                }
            });
        }
    }

    void getTopRatedMovies(int page){
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            MoviesNetworkManager.getTopRatedMovies(page, new NetworkUtils.MovieLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mView.setAdapterData(movieModels);
                }
            });
        }
    }

    void getNowPlayingMovies(int page){
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mView.showNoInternetError();
        } else {
            MoviesNetworkManager.getNowPlayingMovies(page, new NetworkUtils.MovieLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mView.setAdapterData(movieModels);
                }
            });
        }
    }

}
