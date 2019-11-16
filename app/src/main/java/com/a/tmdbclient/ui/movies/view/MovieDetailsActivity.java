package com.a.tmdbclient.ui.movies.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.a.tmdbclient.App;
import com.a.tmdbclient.R;
import com.a.tmdbclient.data.NetworkUtils;
import com.a.tmdbclient.data.movie.pojo.MovieDetails;
import com.a.tmdbclient.ui.movies.MoviesPresenter;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView tag;
    private TextView description;
    private ImageView poster;
    private ProgressBar progressBar;
    @Inject
    MoviesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        App.getAppComponent().inject(this);
        init();

        int id = getIntent().getIntExtra("id", 0);
        presenter.getMovieDetails(id,this);
    }

    private void init() {
        title = findViewById(R.id.movie_details_title);
        tag = findViewById(R.id.movie_details_tag);
        description = findViewById(R.id.movie_details_description);
        poster = findViewById(R.id.movie_details_poster);
        progressBar = findViewById(R.id.movie_details_progress_bar);
    }

    public void setDetails(MovieDetails details) {
        progressBar.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText(details.getTitle());
        tag.setVisibility(View.VISIBLE);
        tag.setText(details.getTagline());
        description.setVisibility(View.VISIBLE);
        description.setText(details.getOverview());
        poster.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(NetworkUtils.IMG_BIG_SIZE_URL.concat(details.getPosterPath()))
                .into(poster);
    }

}
