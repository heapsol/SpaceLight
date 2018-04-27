package com.the.lightspace.Network.api.AllVideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/27/18.
 */

public class PageInfo {

    @SerializedName("totalResults")
    @Expose
    private String totalResults;

    @SerializedName("resultsPerPage")
    @Expose
    private String resultsPerPage;

    public void setTotalResults(String totalResults){this.totalResults=totalResults;}
    public String getTotalResults(){return totalResults;}

    public void setResultsPerPage(String resultsPerPage){this.resultsPerPage=resultsPerPage;}
    public String getResultsPerPage(){return resultsPerPage;}

}
