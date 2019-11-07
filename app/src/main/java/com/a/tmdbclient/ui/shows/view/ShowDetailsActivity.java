package com.a.tmdbclient.ui.shows.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.shows.ShowDetails;
import com.a.tmdbclient.ui.shows.ShowsPresenter;
import com.bumptech.glide.Glide;

public class ShowDetailsActivity extends AppCompatActivity {

    private int id;
    private ShowDetails details;
    private TextView title;
    private TextView tag;
    private TextView descr;
    private ImageView poster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        id = getIntent().getIntExtra("id",0);
        ShowsPresenter.getShowsDetails(id,this);
        title = findViewById(R.id.show_details_title);
        tag = findViewById(R.id.show_details_tag);
        descr = findViewById(R.id.show_details_descr);
        poster = findViewById(R.id.show_details_poster);
        progressBar = findViewById(R.id.show_details_progress_bar);
    }

    public void setDetails(ShowDetails details){
        this.details = details;
        progressBar.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText(details.getName());
        tag.setVisibility(View.VISIBLE);
        tag.setText(details.getStatus());
        descr.setVisibility(View.VISIBLE);
        descr.setText(details.getOverview());
        poster.setVisibility(View.VISIBLE);
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+details.getPosterPath()).into(poster);
    }
}
