package com.a.tmdbclient.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.a.tmdbclient.api.movie.MovieModel;
import com.a.tmdbclient.api.peoples.PeopleModel;
import com.a.tmdbclient.api.shows.ShowModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NetworkUtils {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "9bd3b90bce7cf27fc87c740e0442a798";

    public interface MovieLoadCallback {
        void onLoadFail(Call call);
        void onLoadSuccess(Response response, List<MovieModel> movieModels);
    }

    public interface PeopleLoadCallback {
        void onLoadFail(Call call);
        void onLoadSuccess(Response response, List<PeopleModel> peopleModels);
    }

    public interface ShowLoadCallback {
        void onLoadFail(Call call);
        void onLoadSuccess(Response response, List<ShowModel> showModels);
    }

    public static boolean isInternetUnavailable(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return true;
        }
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo == null || !networkInfo.isConnected();
    }

}
