package com.the.lightspace.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.the.lightspace.R;

/**
 * Created by Cool Programmer on 5/25/2018.
 */

public class LoginDFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_dialog_fragment, container,
                false);


        return view;
    }
}