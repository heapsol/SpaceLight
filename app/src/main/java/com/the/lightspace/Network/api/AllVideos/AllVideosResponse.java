package com.the.lightspace.Network.api.AllVideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.lightspace.Network.api.BaseResponse;

import java.util.List;

/**
 * Created by ahmad on 4/27/18.
 */

public class AllVideosResponse extends BaseResponse {

    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("regionCode")
    @Expose
    private String regionCode;

    @SerializedName("pageInfo")
    @Expose
    public PageInfo pageInfo = new PageInfo();

    @SerializedName("items")
    @Expose
    public List<Items> items;

    public void setNextPageToken(String nextPageToken){this.nextPageToken=nextPageToken;}
    public String getNextPageToken(){return nextPageToken;}

    public void setRegionCode(String regionCode){this.regionCode=regionCode;}
    public String getRegionCode(){return regionCode;}

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public class Items{

        @SerializedName("etag")
        @Expose
        private String etag;

        @SerializedName("id")
        @Expose
        public Id ids = new Id();

        @SerializedName("snippet")
        @Expose
        public Snippet snippet = new Snippet();

    }

}
