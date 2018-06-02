package com.the.lightspace.Fragments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.the.lightspace.Adapters.AdapterCategories;
import com.the.lightspace.BaseClasses.BaseFragment;
import com.the.lightspace.DatabaseHandler.DatabaseHandler;
import com.the.lightspace.Models.VideoEntry;
import com.the.lightspace.Network.Api;
import com.the.lightspace.Network.api.AllVideos.AllVideosApi;
import com.the.lightspace.Network.api.AllVideos.AllVideosResponse;
import com.the.lightspace.R;
import com.the.lightspace.Util.Constant;
import com.the.lightspace.Util.LinearDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Cool Programmer on 5/2/2018.
 */

public class myFirstFragment extends BaseFragment implements AllVideosApi.AllVideosCallbackListener {

    private LocalBroadcastManager localBroadcastManager;
    private View view;
    private ProgressDialog progress;
    private RecyclerView rvCategories;
    public ArrayList<VideoEntry> list;
    private String playlistId;
    private static myFirstFragment fragment;
    DatabaseHandler db;
    AdapterCategories adapter;

    public static myFirstFragment newInstance(String PlaylistID) {
        Bundle args = new Bundle();
        args.putString("PlaylistID", PlaylistID);
        fragment = new myFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_my_collection, container, false);


        init(view);
        clickListeners();
        fetchData();
        return view;
    }

    private void init(View view) {
        rvCategories = (RecyclerView) view.findViewById(R.id.rvCategories);
        list = new ArrayList<VideoEntry>();

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading Videos... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(mReceiver, new IntentFilter("FavBroadCast"));

    }

    private void clickListeners() {

    }

    public void fetchData() {
        Log.e("PlaylistID", fragment.getArguments().getString("PlaylistID"));
        Api.allVideosApi.getVideos("snippet", "50", fragment.getArguments().getString("PlaylistID"),
                Constant.apiKey, this);
    }

    @Override
    public void onVideosRetrieve(AllVideosResponse allVideosResponses) {
//        Log.e("item data", " " + allVideosResponses.())
//        Log.e("itemSize", allVideosResponses.getItems().size()+"");
//        for(int i = 0; i< allVideosResponses.getItems().size(); i++){
//            Log.e("Pub@Con", allVideosResponses.getItems().get(i).snippet.getPublishedAt());
//        }
        for (int k = 0; k < allVideosResponses.getItems().size(); k++) {

            VideoEntry model = new VideoEntry();
            model.setTitle(allVideosResponses.getItems().get(k).snippet.getTitle());
//            Log.e("Video Ids", " "+allVideosResponses.getItems().get(k).snippet.resourceId.getVideoId());
            model.setVideoId(allVideosResponses.getItems().get(k).snippet.resourceId.getVideoId());

            if (allVideosResponses.getItems().get(k).snippet.thumbnails.standard.getUrl() == null) {
                model.setThumbnailsMedium(allVideosResponses.getItems().get(k).snippet.thumbnails.medium.getUrl());
                Log.e("Medium", allVideosResponses.getItems().get(k).snippet.thumbnails.medium.getUrl() + "");
            } else {
                model.setThumbnailsMedium(allVideosResponses.getItems().get(k).snippet.thumbnails.standard.getUrl());
                Log.e("Standard", allVideosResponses.getItems().get(k).snippet.thumbnails.standard.getUrl() + "");
            }
            model.setPublishedAt(allVideosResponses.getItems().get(k).snippet.getPublishedAt());
            Log.e("published", allVideosResponses.getItems().get(k).snippet.getPublishedAt() + "");
            model.setDescription(allVideosResponses.getItems().get(k).snippet.getDescription());
            list.add(model);
        }
        progress.dismiss();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        rvCategories.setLayoutManager(manager);
        rvCategories.addItemDecoration(new LinearDividerItemDecoration(getContext(), getResources().getColor(R.color.colorScreenBackground), 20));

        adapter = new AdapterCategories(list, getActivity());
        rvCategories.setNestedScrollingEnabled(false);
        rvCategories.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {
        Log.e("error", " " + error);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
//            category = intent.getStringExtra("Notification");
            Log.e(" I am in Receiver", "" + intent.getPackage());
            String status = "null";
            status = intent.getStringExtra("fav");
            Log.e("status", "" + status);
            if(status.equals("removed")){
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(mReceiver);
    }
}

