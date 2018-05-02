package com.the.lightspace.Network.api.AllPlaylists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.lightspace.Network.api.AllVideos.PageInfo;
import com.the.lightspace.Network.api.AllVideos.Snippet;
import com.the.lightspace.Network.api.BaseResponse;
import com.the.lightspace.Network.api.Statistics.Statistic;

import java.util.List;

/**
 * Created by ahmad on 4/27/18.
 */

public class AllPlaylistsResponse extends BaseResponse {

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

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }
    }

}
