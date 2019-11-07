package com.a.tmdbclient.api.movie;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.a.tmdbclient.api.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNetworkManager {

    private static final MoviesNetworkManager ourInstance = new MoviesNetworkManager();
    private static Retrofit mRetrofit;

    public static MoviesNetworkManager getInstance() {
        return ourInstance;
    }

    private MoviesNetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static MovieApiService getAPI() {
        return mRetrofit.create(MovieApiService.class);
    }

    public static AsyncTask<Void, Void, Void> getPopularMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        return new PopularMoviesTask(page, callback).execute();
    }

    private static class PopularMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        PopularMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPopularMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
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

    public static AsyncTask<Void, Void, Void> getUpcomingMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        return new UpcomingMoviesTask(page, callback).execute();
    }

    private static class UpcomingMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        UpcomingMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getUpcomingMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
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

    public static AsyncTask<Void, Void, Void> getTopRatedMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        return new TopRatedMoviesTask(page, callback).execute();
    }

    private static class TopRatedMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        TopRatedMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getTopRatedMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
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

    public static AsyncTask<Void, Void, Void> getNowPlayingMovies(int page, NetworkUtils.MovieListLoadCallback callback) {
        return new NowPlayingMoviesTask(page, callback).execute();
    }

    private static class NowPlayingMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.MovieListLoadCallback mCallback;

        public NowPlayingMoviesTask(int page, NetworkUtils.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getNowPlayingMovies(mPage, NetworkUtils.API_KEY).enqueue(new Callback<MoviePageModel>() {
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

    public static AsyncTask<Void, Void, Void> getMovieDetails(int id, NetworkUtils.MovieDetailsLoadCallback callback) {
        return new MovieDetailsTask(id, callback).execute();
    }

    private static class MovieDetailsTask extends AsyncTask<Void, Void, Void> {

        private int mId;
        private NetworkUtils.MovieDetailsLoadCallback mCallback;

        MovieDetailsTask(int id, NetworkUtils.MovieDetailsLoadCallback callback) {
            mId = id;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getMovieDetails(mId, NetworkUtils.API_KEY).enqueue(new Callback<MovieDetails>() {
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
