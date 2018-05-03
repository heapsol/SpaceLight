package com.the.lightspace.Network.api.AllVideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.lightspace.Network.api.BaseResponse;

import java.util.List;

/**
 * Created by ahmad on 4/27/18.
 */

public class AllVideosResponse extends BaseResponse {

    @SerializedName("pageInfo")
    @Expose
    public PageInfo pageInfo = new PageInfo();

    @SerializedName("items")
    @Expose
    public List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public class Items {

        @SerializedName("etag")
        @Expose
        private String etag;

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("snippet")
        @Expose
        public Snippet snippet = new Snippet();

    }

}
