package com.the.lightspace.Network.api.AllVideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/27/18.
 */

public class PageInfo {

    @SerializedName("totalResults")
    @Expose
    private int totalResults;

    @SerializedName("resultsPerPage")
    @Expose
    private int resultsPerPage;

    public void setTotalResults(int totalResults){this.totalResults=totalResults;}
    public int getTotalResults(){return totalResults;}

    public void setResultsPerPage(int resultsPerPage){this.resultsPerPage=resultsPerPage;}
    public int getResultsPerPage(){return resultsPerPage;}

}
