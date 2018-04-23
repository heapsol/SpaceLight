package com.the.lightspace.BaseClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Cool Programmer on 10/4/2017.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

    }

    public void addFragmentWithBackstack(int containerId, Fragment fragment, String tag){
        getActivity().getSupportFragmentManager().beginTransaction().
                add(containerId,fragment,tag)
                .addToBackStack(tag).commit();
    }

    public void addFragment(int containerId, Fragment fragment, String tag){
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(containerId,fragment,tag).commit();
    }

    public void replaceFragmentWithBackstack(int containerId, Fragment fragment, String tag) {
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(containerId, fragment, tag)
                .addToBackStack(tag).commit();
    }

    public void replaceFragment(int containerId, Fragment fragment, String tag){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(containerId,fragment,tag).commit();
    }

}
