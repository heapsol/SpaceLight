package com.the.lightspace.Network.api.Statistics;


import com.the.lightspace.Network.api.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ahmad on 4/27/18.
 */

public class VideosStatisticsApi {

    /**
     * Get Tattoos by category and send objects in response or error
     * Welcome Api
     *
     * @param mlistener
     */
    public void getVideosStatistics(String part,
                                    String id,
                                    String key,
                                    final VideosStatisticsCallbackListener mlistener) {

        Call<VideosStatisticsResponse> call = RetrofitApiClient.getInstance(true).getAPI().getVideosStatistics(part, id, key);

        call.enqueue(new Callback<VideosStatisticsResponse>() {
            @Override
            public void onResponse(Call<VideosStatisticsResponse> call, Response<VideosStatisticsResponse> response) {

                if (response.body() == null) {
                    mlistener.onError("No Video Statistic!");
                } else if (response.body().getItems() != null) {
                    mlistener.onVideosStatisticsRetrieve(response.body());
                } else {
                    mlistener.onError(response.body().error.getMessage());
                }

            }

            @Override
            public void onFailure(Call<VideosStatisticsResponse> call, Throwable t) {
                mlistener.onError(t.getMessage());

            }
        });
    }

    public interface VideosStatisticsCallbackListener {
        void onVideosStatisticsRetrieve(VideosStatisticsResponse videosStatisticsResponses);
        void onError(String error);
    }
}
