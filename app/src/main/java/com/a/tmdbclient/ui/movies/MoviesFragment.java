package com.a.tmdbclient.ui.movies;

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

public class MoviesFragment extends Fragment {

    private MoviesViewModel mMoviesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mMoviesViewModel =
                ViewModelProviders.of(this).get(MoviesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        final TextView textView = root.findViewById(R.id.text_movies);
        mMoviesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}