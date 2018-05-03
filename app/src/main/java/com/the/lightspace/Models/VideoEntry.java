package com.the.lightspace.Models;

/**
 * Created by Cool Programmer on 5/3/2018.
 */

public class VideoEntry {
    private String title, thumbnailSmall, thumbnailsMedium, thumbnailsLarge, publishedAt, description;
    private String videoId;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnailSmall(String thumbnailSmall) {
        this.thumbnailSmall = thumbnailSmall;
    }

    public void setThumbnailsMedium(String thumbnailsMedium) {
        this.thumbnailsMedium = thumbnailsMedium;
    }

    public void setThumbnailsLarge(String thumbnailsLarge) {
        this.thumbnailsLarge = thumbnailsLarge;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailSmall() {
        return thumbnailSmall;
    }

    public String getThumbnailsMedium() {
        return thumbnailsMedium;
    }

    public String getThumbnailsLarge() {
        return thumbnailsLarge;
    }

    public String getVideoId() {
        return videoId;
    }

//

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
