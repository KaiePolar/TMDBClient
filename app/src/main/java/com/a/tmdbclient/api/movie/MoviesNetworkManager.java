package com.a.tmdbclient.api.movie;

import android.os.AsyncTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNetworkManager {

    private static final MoviesNetworkManager ourInstance = new MoviesNetworkManager();
    private static final String BASE_URL = "https://api.themoviedb.org/3/" ;
    private static Retrofit mRetrofit;

    public static MoviesNetworkManager getInstance() {
        return ourInstance;
    }

    private MoviesNetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static MovieApiService getAPI(){
        return mRetrofit.create(MovieApiService.class);
    }

    public static AsyncTask<Void, Void, Void> getPopularMovies(int page, LoadCallback callback){
        return new PopularMoviesTask(page, callback).execute();
    }

    private static class PopularMoviesTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private LoadCallback mCallback;

        public PopularMoviesTask(Integer page, LoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPopularMovies(mPage,MovieApiService.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(Call<MoviePageModel> call, Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getMovieModel());
                }

                @Override
                public void onFailure(Call<MoviePageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getUpcomingMovies(int page, LoadCallback callback){
        return new UpcomingMoviesTask(page, callback).execute();
    }

    private static class UpcomingMoviesTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private LoadCallback mCallback;

        public UpcomingMoviesTask(Integer page, LoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getUpcomingMovies(mPage,MovieApiService.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(Call<MoviePageModel> call, Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getMovieModel());
                }

                @Override
                public void onFailure(Call<MoviePageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getTopRatedMovies(int page, LoadCallback callback){
        return new TopRatedMoviesTask(page, callback).execute();
    }

    private static class TopRatedMoviesTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private LoadCallback mCallback;

        public TopRatedMoviesTask(Integer page, LoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getTopRatedMovies(mPage,MovieApiService.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(Call<MoviePageModel> call, Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getMovieModel());
                }

                @Override
                public void onFailure(Call<MoviePageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getNowPlayingMovies(int page, LoadCallback callback){
        return new NowPlayingMoviesTask(page, callback).execute();
    }

    private static class NowPlayingMoviesTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private LoadCallback mCallback;

        public NowPlayingMoviesTask(Integer page, LoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getUpcomingMovies(mPage,MovieApiService.API_KEY).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(Call<MoviePageModel> call, Response<MoviePageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getMovieModel());
                }

                @Override
                public void onFailure(Call<MoviePageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }


    public interface LoadCallback{
        void onLoadFail(Call call);
        void onLoadSuccess(Response response,List<MovieModel> movieModels);
    }
}
