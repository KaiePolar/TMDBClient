package com.a.tmdbclient.ui.movies;

import android.content.Context;

import com.a.tmdbclient.App;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.movie.MoviesRepository;
import com.a.tmdbclient.api.movie.pojo.MovieDetails;
import com.a.tmdbclient.api.movie.pojo.MovieModel;
import com.a.tmdbclient.ui.movies.view.MovieDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class MoviesPresenter {

    private MovieView mView;
    @Inject
    MoviesRepository repository;


    public MoviesPresenter(){
        App.getAppComponent().inject(this);
    }

    public void setView(MovieView view){
        mView = view;
    }

    public void getMovieDetails(int id, final MovieDetailsActivity activity) {
        repository.getMovieDetails(id, new NetworkUtils.MovieDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, MovieDetails movieDetails) {
                activity.setDetails(movieDetails);
            }
        });
    }

    public void getPopularMovies(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getPopularMovies(page, new NetworkUtils.MovieListLoadCallback() {
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

    public void getUpcomingMovies(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getUpcomingMovies(page, new NetworkUtils.MovieListLoadCallback() {
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

    public void getTopRatedMovies(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getTopRatedMovies(page, new NetworkUtils.MovieListLoadCallback() {
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

    public void getNowPlayingMovies(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getNowPlayingMovies(page, new NetworkUtils.MovieListLoadCallback() {
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
