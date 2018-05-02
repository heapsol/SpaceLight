package com.the.lightspace.Network.api.AllPlaylists;


import com.the.lightspace.Network.api.RetrofitApiClient;
import com.the.lightspace.Network.api.Statistics.VideosStatisticsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ahmad on 4/27/18.
 */

public class AllPlaylistsApi {

    /**
     * Get Tattoos by category and send objects in response or error
     * Welcome Api
     *
     * @param mlistener
     */
    public void getAllPlaylists(String part,
                                String channelId,
                                String key,
                                final AllPlaylistsCallbackListener mlistener) {

        Call<AllPlaylistsResponse> call = RetrofitApiClient.getInstance(true).getAPI().getAllPlaylists(part, channelId, key);

        call.enqueue(new Callback<AllPlaylistsResponse>() {
            @Override
            public void onResponse(Call<AllPlaylistsResponse> call, Response<AllPlaylistsResponse> response) {

                if (response.body() == null) {
                    mlistener.onError("No Playlist!");
                } else if (response.body().getItems() != null) {
                    mlistener.onAllPlaylistsRetrieve(response.body());
                } else {
                    mlistener.onError(response.body().error.getMessage());
                }

            }

            @Override
            public void onFailure(Call<AllPlaylistsResponse> call, Throwable t) {
                mlistener.onError(t.getMessage());

            }
        });
    }

    public interface AllPlaylistsCallbackListener {
        void onAllPlaylistsRetrieve(AllPlaylistsResponse allPlaylistsResponse);

        void onError(String error);
    }
}
