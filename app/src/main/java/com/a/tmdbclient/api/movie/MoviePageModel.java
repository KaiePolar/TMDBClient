package com.a.tmdbclient.api.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MoviePageModel implements Serializable {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private ArrayList<MovieModel> movieModel;

    public MoviePageModel(int page, int totalResults, int totalPages, ArrayList<MovieModel> movieModel) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.movieModel = movieModel;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MovieModel> getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(ArrayList<MovieModel> movieModel) {
        this.movieModel = movieModel;
    }

}

