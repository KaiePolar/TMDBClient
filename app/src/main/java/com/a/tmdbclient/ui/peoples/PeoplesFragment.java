package com.a.tmdbclient.ui.peoples;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.a.tmdbclient.R;

public class PeoplesFragment extends Fragment {

    private PeoplesViewModel mPeoplesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPeoplesViewModel =
                ViewModelProviders.of(this).get(PeoplesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_peoples, container, false);
        final TextView textView = root.findViewById(R.id.text_peoples);
        mPeoplesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}