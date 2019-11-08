package com.a.tmdbclient.ui.shows;

import android.view.View;

import com.a.tmdbclient.api.shows.pojo.ShowModel;

import java.util.List;

public interface ShowView {
    void init(View view);
    void setAdapterData(List<ShowModel> data);
    void showNoInternetError();
    void showApiError();
}
