package com.the.lightspace.Network;

import com.the.lightspace.Network.api.AllPlaylists.AllPlaylistsApi;
import com.the.lightspace.Network.api.AllVideos.AllVideosApi;
import com.the.lightspace.Network.api.Statistics.VideosStatisticsApi;

/**
 * Created by ahmad on 4/27/18.
 */

public class Api {
    public static AllVideosApi allVideosApi = new AllVideosApi();
    public static VideosStatisticsApi videosStatisticsApi = new VideosStatisticsApi();
    public static AllPlaylistsApi allPlaylistsApi = new AllPlaylistsApi();
}
