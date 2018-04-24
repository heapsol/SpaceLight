package com.the.lightspace.Fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.the.lightspace.Adapters.AdapterCategories;
import com.the.lightspace.BaseClasses.BaseFragment;
import com.the.lightspace.Models.CategoriesModel;
import com.the.lightspace.R;
import com.the.lightspace.Util.LinearDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Cool Programmer on 10/19/2017.
 */

public class FragmentCategories extends BaseFragment {

    public static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";

    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static final String VIDEO_ID = "-m3V8w_7vhk";

    private View view;
    RecyclerView rvCategories;
    private ArrayList<CategoriesModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_my_collection, container, false);

        init(view);
        clickListeners();

        populateList();
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategories.addItemDecoration(new LinearDividerItemDecoration(getContext(), getResources().getColor(R.color.colorScreenBackground), 20));
        AdapterCategories adapter = new AdapterCategories(list, getActivity());
        rvCategories.setNestedScrollingEnabled(false);
        rvCategories.setAdapter(adapter);


        return view;
    }

    private void init(View view) {

        rvCategories = (RecyclerView) view.findViewById(R.id.rvCategories);
        list = new ArrayList<>();

    }

    private void clickListeners() {

    }

    void populateList() {
        for (int i = 0; i <= 50; i++) {
            CategoriesModel model = new CategoriesModel();
            model.setVideoID("-m3V8w_7vhk");
            list.add(model);
        }
    }
}
