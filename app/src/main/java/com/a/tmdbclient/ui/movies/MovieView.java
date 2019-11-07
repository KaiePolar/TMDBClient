package com.a.tmdbclient.ui.movies;

import android.view.View;

import com.a.tmdbclient.api.movie.MovieModel;

import java.util.List;

public interface MovieView {
    void init(View view);
    void setAdapterData(List<MovieModel> data);
    void showNoInternetError();
    void showApiError();
}
