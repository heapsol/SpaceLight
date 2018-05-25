package com.the.lightspace.DatabaseHandler;

/**
 * Created by Cool Programmer on 5/21/2018.
 */

public class DbModel {

    String title, thumbnailUrl, description, pubishedAt, videoID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubishedAt() {
        return pubishedAt;
    }

    public void setPubishedAt(String pubishedAt) {
        this.pubishedAt = pubishedAt;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }
}
