package com.a.tmdbclient.ui.movies.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.movie.MovieDetails;
import com.a.tmdbclient.ui.movies.MoviesPresenter;
import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private int id;
    private MoviesPresenter presenter;
    private MovieDetails details;
    private TextView title;
    private TextView tag;
    private TextView descr;
    private ImageView poster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        id = getIntent().getIntExtra("id",0);
        MoviesPresenter.getMovieDetails(id,this);
        title = findViewById(R.id.movie_details_title);
        tag = findViewById(R.id.movie_details_tag);
        descr = findViewById(R.id.movie_details_descr);
        poster = findViewById(R.id.movie_details_poster);
        progressBar = findViewById(R.id.movie_details_progress_bar);
    }

    public void setDetails(MovieDetails details){
        this.details = details;
        progressBar.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText(details.getTitle());
        tag.setVisibility(View.VISIBLE);
        tag.setText(details.getTagline());
        descr.setVisibility(View.VISIBLE);
        descr.setText(details.getOverview());
        poster.setVisibility(View.VISIBLE);
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+details.getPosterPath()).into(poster);
    }
}
