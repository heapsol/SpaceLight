package com.the.lightspace.Network.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/27/18.
 */

public class BaseResponse {

    @SerializedName("error")
    @Expose
    public Error error = new Error();

    public class Error{

        @SerializedName("message")
        @Expose
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
