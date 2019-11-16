package com.a.tmdbclient.di;

import com.a.tmdbclient.data.movie.MoviesRepository;
import com.a.tmdbclient.data.peoples.PeoplesRepository;
import com.a.tmdbclient.data.shows.ShowsRepository;

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
