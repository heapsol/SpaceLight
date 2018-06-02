package com.the.lightspace.BaseClasses;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.the.lightspace.Models.VideoEntry;
import com.the.lightspace.Network.api.AllPlaylists.AllPlaylistsResponse;

import java.util.ArrayList;

/**
 * Created by Cool Programmer on 10/16/2017.
 */

public class BaseApplication extends MultiDexApplication {

    public AllPlaylistsResponse myAllPlaylistsResponse;
    public static ArrayList<VideoEntry> relatedList;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        myAllPlaylistsResponse = new AllPlaylistsResponse();
        relatedList = new ArrayList<VideoEntry>();
    }

}
