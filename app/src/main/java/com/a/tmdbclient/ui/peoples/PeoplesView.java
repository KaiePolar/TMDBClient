package com.a.tmdbclient.ui.peoples;

import android.view.View;

import com.a.tmdbclient.api.peoples.pojo.PeopleModel;

import java.util.List;

public interface PeoplesView {
    void init(View view);
    void setAdapterData(List<PeopleModel> data);
    void showNoInternetError();
    void showApiError();
}
