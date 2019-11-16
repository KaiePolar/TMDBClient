package com.a.tmdbclient.di;

import com.a.tmdbclient.data.NetworkUtils;
import com.a.tmdbclient.data.movie.MovieApi;
import com.a.tmdbclient.data.peoples.PeopleApi;
import com.a.tmdbclient.data.shows.ShowApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class RetrofitModule {

    @Provides
    MovieApi provideMovieAPi(Retrofit retrofit){
        return retrofit.create(MovieApi.class);
    }

    @Provides
    PeopleApi providePeopleAPi(Retrofit retrofit){
        return retrofit.create(PeopleApi.class);
    }

    @Provides
    ShowApi provideShowAPi(Retrofit retrofit){
        return retrofit.create(ShowApi.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

}
