package com.a.tmdbclient.api.shows;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowApiService {

    @GET("tv/popular")
    Call<ShowPageModel> getPopularShows(@Query("page") int page, @Query("api_key") String userKey);

    @GET("tv/top_rated")
    Call<ShowPageModel> getTopRatedShows(@Query("page") int page, @Query("api_key") String userKey);

    @GET("tv/on_the_air")
    Call<ShowPageModel> getUpcomingShows(@Query("page") int page, @Query("api_key") String userKey);

    @GET("tv/airing_today")
    Call<ShowPageModel> getNowPlayingShows(@Query("page") int page, @Query("api_key") String userKey);
}
