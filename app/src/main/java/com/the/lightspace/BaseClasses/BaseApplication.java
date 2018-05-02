package com.the.lightspace.BaseClasses;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.the.lightspace.Network.api.AllPlaylists.AllPlaylistsResponse;

/**
 * Created by Cool Programmer on 10/16/2017.
 */

public class BaseApplication extends MultiDexApplication {

    public AllPlaylistsResponse myAllPlaylistsResponse;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
