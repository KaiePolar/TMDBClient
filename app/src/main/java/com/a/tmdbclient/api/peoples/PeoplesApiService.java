package com.a.tmdbclient.api.peoples;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeoplesApiService {
    @GET("person/popular")
    Call<PeoplePageModel> getPopularPeoples(@Query("page") int page, @Query("api_key") String userKey);
}
