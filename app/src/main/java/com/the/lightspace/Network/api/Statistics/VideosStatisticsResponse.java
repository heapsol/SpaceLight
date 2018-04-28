package com.the.lightspace.Network.api.Statistics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.lightspace.Network.api.AllVideos.Id;
import com.the.lightspace.Network.api.AllVideos.PageInfo;
import com.the.lightspace.Network.api.BaseResponse;

import java.util.List;

/**
 * Created by ahmad on 4/27/18.
 */

public class VideosStatisticsResponse extends BaseResponse {

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

        @SerializedName("statistics")
        @Expose
        public Statistic statistics = new Statistic();

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

        public Statistic getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistic statistics) {
            this.statistics = statistics;
        }
    }

}
