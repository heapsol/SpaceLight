package com.the.lightspace.BaseClasses;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by Cool Programmer on 10/19/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public final static String API_URL = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyDnbadjPHRq1xodGmL9IHfG6Ul5A6RP7Bc&channelId=UCYNaV3joVzSAp4IOvR3Y2yg&part=snippet,id&order=date&maxResults=15";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
