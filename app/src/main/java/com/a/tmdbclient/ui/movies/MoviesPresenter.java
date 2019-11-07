package com.a.tmdbclient.ui.movies;

import android.content.Context;
import android.util.Log;

import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.movie.MovieDetails;
import com.a.tmdbclient.api.movie.MovieModel;
import com.a.tmdbclient.api.movie.MoviesNetworkManager;
import com.a.tmdbclient.ui.movies.view.MovieDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MoviesPresenter {

    private MovieView mFragView;
    private Context mContext;

    public MoviesPresenter(MovieView view, Context context) {
        mFragView = view;
        mContext = context;
    }

    public static void getMovieDetails(int id, final MovieDetailsActivity activity){
        MoviesNetworkManager.getMovieDetails(id, new NetworkUtils.MovieDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
                Log.d("ss", call.toString());
            }

            @Override
            public void onLoadSuccess(Response response, MovieDetails movieDetails) {
                activity.setDetails(movieDetails);
            }
        });
    }

    public void getPopularMovies(int page) {
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mFragView.showNoInternetError();
        } else {
            MoviesNetworkManager.getPopularMovies(page, new NetworkUtils.MovieListLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mFragView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mFragView.setAdapterData(movieModels);
                }
            });
        }
    }

    public void getUpcomingMovies(int page){
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mFragView.showNoInternetError();
        } else {
            MoviesNetworkManager.getUpcomingMovies(page, new NetworkUtils.MovieListLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mFragView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mFragView.setAdapterData(movieModels);
                }
            });
        }
    }

    public void getTopRatedMovies(int page){
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mFragView.showNoInternetError();
        } else {
            MoviesNetworkManager.getTopRatedMovies(page, new NetworkUtils.MovieListLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mFragView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mFragView.setAdapterData(movieModels);
                }
            });
        }
    }

    public void getNowPlayingMovies(int page){
        if (NetworkUtils.isInternetUnavailable(mContext)) {
            mFragView.showNoInternetError();
        } else {
            MoviesNetworkManager.getNowPlayingMovies(page, new NetworkUtils.MovieListLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mFragView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<MovieModel> movieModels) {
                    mFragView.setAdapterData(movieModels);
                }
            });
        }
    }

}
