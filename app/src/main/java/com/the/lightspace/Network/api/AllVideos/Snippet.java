package com.the.lightspace.Network.api.AllVideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/27/18.
 */

public class Snippet {

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @SerializedName("channelId")
    @Expose
    private String channelId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;

    @SerializedName("position")
    @Expose
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }


    @SerializedName("thumbnails")
    @Expose
    public Thumbnails thumbnails = new Thumbnails();

    @SerializedName("resourceId")
    @Expose
    public ResourceId resourceId = new ResourceId();

}
