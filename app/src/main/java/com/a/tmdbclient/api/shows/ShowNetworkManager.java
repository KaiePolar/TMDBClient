package com.a.tmdbclient.api.shows;

import android.os.AsyncTask;

import com.a.tmdbclient.api.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowNetworkManager {
    private static final ShowNetworkManager ourInstance = new ShowNetworkManager();
    private static Retrofit mRetrofit;

    public static ShowNetworkManager getInstance() {
        return ourInstance;
    }

    private ShowNetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ShowApiService getAPI(){
        return mRetrofit.create(ShowApiService.class);
    }

    public static AsyncTask<Void, Void, Void> getPopularShows(int page, NetworkUtils.ShowLoadCallback callback){
        return new ShowNetworkManager.PopularShowTask(page, callback).execute();
    }

    private static class PopularShowTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        public PopularShowTask(Integer page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPopularShows(mPage,NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(Call<ShowPageModel> call, Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getShowModels());
                }

                @Override
                public void onFailure(Call<ShowPageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getTopRatedShows(int page, NetworkUtils.ShowLoadCallback callback){
        return new ShowNetworkManager.TopRatedShowTask(page, callback).execute();
    }

    private static class TopRatedShowTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        public TopRatedShowTask(Integer page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getTopRatedShows(mPage,NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(Call<ShowPageModel> call, Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getShowModels());
                }

                @Override
                public void onFailure(Call<ShowPageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getUpcomingShows(int page, NetworkUtils.ShowLoadCallback callback){
        return new ShowNetworkManager.UpcomingShowTask(page, callback).execute();
    }

    private static class UpcomingShowTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        public UpcomingShowTask(Integer page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getUpcomingShows(mPage,NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(Call<ShowPageModel> call, Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getShowModels());
                }

                @Override
                public void onFailure(Call<ShowPageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getNowPlayingShows(int page, NetworkUtils.ShowLoadCallback callback){
        return new ShowNetworkManager.NowPlayingShowTask(page, callback).execute();
    }

    private static class NowPlayingShowTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        public NowPlayingShowTask(Integer page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getNowPlayingShows(mPage,NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(Call<ShowPageModel> call, Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getShowModels());
                }

                @Override
                public void onFailure(Call<ShowPageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getShowDetails(int page, NetworkUtils.ShowDetailsLoadCallback callback){
        return new ShowNetworkManager.ShowDetailsTask(page, callback).execute();
    }

    private static class ShowDetailsTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.ShowDetailsLoadCallback mCallback;

        public ShowDetailsTask(Integer page, NetworkUtils.ShowDetailsLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getShowDetails(mPage,NetworkUtils.API_KEY).enqueue(new Callback<ShowDetails>() {
                @Override
                public void onResponse(Call<ShowDetails> call, Response<ShowDetails> response) {
                    mCallback.onLoadSuccess(response,response.body());
                }

                @Override
                public void onFailure(Call<ShowDetails> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }
}
