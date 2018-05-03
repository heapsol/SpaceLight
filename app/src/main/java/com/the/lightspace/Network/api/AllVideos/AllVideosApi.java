package com.the.lightspace.Network.api.AllVideos;

import com.the.lightspace.Network.api.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by ahmad on 4/27/18.
 */

public class AllVideosApi {

    /**
     * Get Tattoos by category and send objects in response or error
     * Welcome Api
     *
     * @param mlistener
     */

    public void getVideos(String part,
                          String maxResults,
                          String playlistId,
                          String key,
                          final AllVideosCallbackListener mlistener) {

        Call<AllVideosResponse> call = RetrofitApiClient.getInstance(true).getAPI().getAllVideos(part, maxResults, playlistId, key);

        call.enqueue(new Callback<AllVideosResponse>() {
            @Override
            public void onResponse(Call<AllVideosResponse> call, Response<AllVideosResponse> response) {

                if (response.body() == null) {
                    mlistener.onError("No Video!");
                } else if (response.body() != null) {
                    mlistener.onVideosRetrieve(response.body());
                } else {
                    mlistener.onError(response.body().error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AllVideosResponse> call, Throwable t) {
                mlistener.onError(t.getMessage());
            }
        });
    }

    public interface AllVideosCallbackListener {
        void onVideosRetrieve(AllVideosResponse allVideosResponses);

        void onError(String error);
    }
}
