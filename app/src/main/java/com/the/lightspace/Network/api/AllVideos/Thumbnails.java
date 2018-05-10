package com.the.lightspace.Network.api.AllVideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/27/18.
 */

public class Thumbnails {

    @SerializedName("medium")
    @Expose
    public Medium medium = new Medium();

    public class Medium{

        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("width")
        @Expose
        private String width;

        @SerializedName("height")
        @Expose
        private String height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }

    @SerializedName("maxres")
    @Expose
    public Maxres maxres = new Maxres();

    public class Maxres {

        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("width")
        @Expose
        private String width;

        @SerializedName("height")
        @Expose
        private String height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }

    @SerializedName("standard")
    @Expose
    public Standard standard = new Standard();

    public class Standard {
        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("width")
        @Expose
        private String width;

        @SerializedName("height")
        @Expose
        private String height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }
}
