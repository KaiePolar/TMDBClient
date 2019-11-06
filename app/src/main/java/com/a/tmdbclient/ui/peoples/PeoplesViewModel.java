package com.a.tmdbclient.ui.peoples;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PeoplesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PeoplesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is peoples fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}