package com.a.tmdbclient.api.peoples;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeoplePageModel {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<PeopleModel> results = null;

    public PeoplePageModel() {
    }

    public PeoplePageModel(Integer page, List<PeopleModel> results) {
        super();
        this.page = page;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<PeopleModel> getResults() {
        return results;
    }

    public void setResults(List<PeopleModel> results) {
        this.results = results;
    }

}