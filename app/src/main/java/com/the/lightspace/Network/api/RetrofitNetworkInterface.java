package com.the.lightspace.Network.api;

import com.the.lightspace.Network.api.AllVideos.AllVideosResponse;
import com.the.lightspace.Util.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sidhu on 1/25/2018.
 */

public interface RetrofitNetworkInterface{

    // @TODO have to add all call methods here

    @GET(Constant.API_SEARCH)
    Call<AllVideosResponse> getAllVideos(@Query("key") String key,
                                         @Query("channelId") String channelId,
                                         @Query("part") String part,
                                         @Query("order") String order,
                                         @Query("maxResults") String maxResults);
}
