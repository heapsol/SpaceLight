package com.the.lightspace.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.lightspace.BaseClasses.BaseFragment;
import com.the.lightspace.R;

public class FragmentSearch extends BaseFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.fragment_search, container, false);
        init(view);


        return view;
    }


    private void init(View view) {

    }

    private void clickListeners() {

    }
}