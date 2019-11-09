package com.a.tmdbclient.api.movie;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.a.tmdbclient.App;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.movie.pojo.MovieDetails;
import com.a.tmdbclient.api.movie.pojo.MoviePageModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    @Inject
    MovieApi api;

    public MoviesRepository() {
        App.getAppComponent().inject(this);
    }

    public void getPopularMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        new PopularMoviesTask(page, callback).execute();
    }

    private class PopularMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        PopularMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getPopularMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(@NonNull Call<MoviePageModel> call, @NonNull Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getMovieModel());
                }

                @Override
                public void onFailure(@NonNull Call<MoviePageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public void getUpcomingMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        new UpcomingMoviesTask(page, callback).execute();
    }

    private class UpcomingMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        UpcomingMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getUpcomingMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(@NonNull Call<MoviePageModel> call, @NonNull Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getMovieModel());
                }

                @Override
                public void onFailure(@NonNull Call<MoviePageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public void getTopRatedMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        new TopRatedMoviesTask(page, callback).execute();
    }

    private class TopRatedMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        TopRatedMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getTopRatedMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(@NonNull Call<MoviePageModel> call, @NonNull Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getMovieModel());
                }

                @Override
                public void onFailure(@NonNull Call<MoviePageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public void getNowPlayingMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        new NowPlayingMoviesTask(page, callback).execute();
    }

    private class NowPlayingMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        NowPlayingMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getNowPlayingMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(@NonNull Call<MoviePageModel> call, @NonNull Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getMovieModel());
                }

                @Override
                public void onFailure(@NonNull Call<MoviePageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public void getMovieDetails(int id, NetworkUtils.MovieDetailsLoadCallback callback) {
        new MovieDetailsTask(id, callback).execute();
    }

    private class MovieDetailsTask extends AsyncTask<Void, Void, Void> {

        private int mId;
        private NetworkUtils.MovieDetailsLoadCallback mCallback;

        MovieDetailsTask(int id, NetworkUtils.MovieDetailsLoadCallback callback) {
            mId = id;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getMovieDetails(mId, NetworkUtils.API_KEY).enqueue(new Callback<MovieDetails>() {
                @Override
                public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                    mCallback.onLoadSuccess(response, response.body());
                }

                @Override
                public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

}
