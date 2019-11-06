package com.a.tmdbclient.api.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    String API_KEY = "9bd3b90bce7cf27fc87c740e0442a798";

    @GET("movie/popular")
    Call<MoviePageModel> getPopularMovies(@Query("page") int page, @Query("api_key") String userKey);

    @GET("movie/top_rated")
    Call<MoviePageModel> getTopRatedMovies(@Query("page") int page, @Query("api_key") String userKey);

    @GET("movie/upcoming")
    Call<MoviePageModel> getUpcomingMovies(@Query("page") int page, @Query("api_key") String userKey);

    @GET("movie/now_playing")
    Call<MoviePageModel> getNowPlayingMovies(@Query("page") int page, @Query("api_key") String userKey);

}
