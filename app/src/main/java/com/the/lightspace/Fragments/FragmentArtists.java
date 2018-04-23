package com.the.lightspace.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.lightspace.BaseClasses.BaseFragment;
import com.the.lightspace.R;

public class FragmentArtists extends BaseFragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.fragment_artist, container, false);


        init();


        return view;
    }

    public void init() {

    }

}