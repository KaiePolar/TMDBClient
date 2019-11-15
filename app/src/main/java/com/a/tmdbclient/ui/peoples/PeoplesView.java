package com.a.tmdbclient.ui.peoples;

import android.view.View;

public interface PeoplesView {
    void init(View view);
    void setProgressBarVisibility(boolean visibility);
    void setSearchProgressBarVisibility(boolean visibility);
    void showNoInternetError();
    void showApiError(String error);
}
