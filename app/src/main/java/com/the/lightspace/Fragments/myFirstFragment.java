package com.the.lightspace.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.lightspace.Adapters.AdapterCategories;
import com.the.lightspace.BaseClasses.BaseFragment;
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


    private View view;
    private ProgressDialog progress;
    private RecyclerView rvCategories;
    public ArrayList<VideoEntry> list;
    private String playlistId;
    private static myFirstFragment fragment;

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
        for (int k = 0; k < allVideosResponses.getItems().size(); k++) {

            VideoEntry model = new VideoEntry();
            model.setTitle(allVideosResponses.getItems().get(k).snippet.getTitle());
//            Log.e("Video Ids", " "+allVideosResponses.getItems().get(k).snippet.resourceId.getVideoId());
            model.setVideoId(allVideosResponses.getItems().get(k).snippet.resourceId.getVideoId());
            model.setThumbnailsMedium(allVideosResponses.getItems().get(k).snippet.thumbnails.medium.getUrl());
            model.setPublishedAt(allVideosResponses.getItems().get(k).snippet.getPublishedAt());
            model.setDescription(allVideosResponses.getItems().get(k).snippet.getDescription());
            list.add(model);
        }
        progress.dismiss();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        rvCategories.setLayoutManager(manager);
        rvCategories.addItemDecoration(new LinearDividerItemDecoration(getContext(), getResources().getColor(R.color.colorScreenBackground), 20));
        AdapterCategories adapter = new AdapterCategories(list, getActivity());
        rvCategories.setNestedScrollingEnabled(false);
        rvCategories.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {
        Log.e("error", " " + error);
    }


}

