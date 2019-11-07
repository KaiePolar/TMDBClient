package com.a.tmdbclient.api.peoples;

import android.os.AsyncTask;

import com.a.tmdbclient.api.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleNetworkManager {
    private static final PeopleNetworkManager ourInstance = new PeopleNetworkManager();
    private static Retrofit mRetrofit;

    public static PeopleNetworkManager getInstance() {
        return ourInstance;
    }

    private PeopleNetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PeoplesApiService getAPI(){
        return mRetrofit.create(PeoplesApiService.class);
    }

    public static AsyncTask<Void, Void, Void> getPopularPeoples(int page, NetworkUtils.PeopleLoadCallback callback){
        return new PeopleNetworkManager.PopularPeoplesTask(page, callback).execute();
    }

    private static class PopularPeoplesTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.PeopleLoadCallback mCallback;

        public PopularPeoplesTask(Integer page, NetworkUtils.PeopleLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPopularPeoples(mPage,NetworkUtils.API_KEY).enqueue(new Callback<PeoplePageModel>() {
                @Override
                public void onResponse(Call<PeoplePageModel> call, Response<PeoplePageModel> response) {
                    mCallback.onLoadSuccess(response,response.body().getResults());
                }

                @Override
                public void onFailure(Call<PeoplePageModel> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public static AsyncTask<Void, Void, Void> getPeopleDetails(int page, NetworkUtils.PeopleDetailsLoadCallback callback){
        return new PeopleDetailsTask(page, callback).execute();
    }

    private static class PeopleDetailsTask extends AsyncTask<Void,Void, Void>{

        private Integer mPage;
        private NetworkUtils.PeopleDetailsLoadCallback mCallback;

        public PeopleDetailsTask(Integer page, NetworkUtils.PeopleDetailsLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPeopleDetails(mPage,NetworkUtils.API_KEY).enqueue(new Callback<PeopleDetails>() {
                @Override
                public void onResponse(Call<PeopleDetails> call, Response<PeopleDetails> response) {
                    mCallback.onLoadSuccess(response,response.body());
                }

                @Override
                public void onFailure(Call<PeopleDetails> call, Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }
}
