package com.the.lightspace.BaseClasses;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.the.lightspace.Network.api.AllPlaylists.AllPlaylistsResponse;

import java.util.ArrayList;


/**
 * Created by Cool Programmer on 10/19/2017.
 */

public class BaseActivity extends AppCompatActivity {

//    public int numberOfTabs = 0;
//    public String tabTitles[];

//    public ArrayList<AllPlaylistsResponse> list;

    public BaseApplication baseApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseApplication = (BaseApplication) getApplicationContext();


    }
}
