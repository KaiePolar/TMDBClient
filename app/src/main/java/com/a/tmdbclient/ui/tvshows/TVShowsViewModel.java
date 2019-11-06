package com.a.tmdbclient.ui.tvshows;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TVShowsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TVShowsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is TV Shows fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}