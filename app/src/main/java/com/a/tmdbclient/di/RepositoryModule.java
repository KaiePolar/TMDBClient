package com.a.tmdbclient.di;

import com.a.tmdbclient.api.movie.MoviesRepository;
import com.a.tmdbclient.api.peoples.PeoplesRepository;
import com.a.tmdbclient.api.shows.ShowsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {

    @Provides
    @Singleton
    MoviesRepository provideMoviesRepository(){
        return new MoviesRepository();
    }

    @Provides
    @Singleton
    ShowsRepository provideShowsRepository(){
        return new ShowsRepository();
    }

    @Provides
    @Singleton
    PeoplesRepository providePeoplesRepository(){
        return new PeoplesRepository();
    }

}
