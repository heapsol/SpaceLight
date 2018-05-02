package com.the.lightspace.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import com.the.lightspace.BaseClasses.BaseActivity;
import com.the.lightspace.Network.Api;
import com.the.lightspace.Network.api.AllPlaylists.AllPlaylistsApi;
import com.the.lightspace.Network.api.AllPlaylists.AllPlaylistsResponse;
import com.the.lightspace.Network.api.AllVideos.AllVideosApi;
import com.the.lightspace.R;

public class SplashActivity extends BaseActivity implements AllPlaylistsApi.AllPlaylistsCallbackListener {

    static final int DIALOG_ERROR_CONNECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        if (!isOnline(this)) {
            showDialog(DIALOG_ERROR_CONNECTION); //displaying the created dialog.
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashpage);
        Api.allPlaylistsApi.getAllPlaylists("snippet", "UCYNaV3joVzSAp4IOvR3Y2yg", "AIzaSyDnbadjPHRq1xodGmL9IHfG6Ul5A6RP7Bc", SplashActivity.this);
    }

    public boolean isOnline(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected())
            return true;
        else
            return false;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        Dialog dialog = null;
        switch (id) {
            case DIALOG_ERROR_CONNECTION:
                AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
                errorDialog.setTitle("Internet Connection Error");
                errorDialog.setMessage("You must connect to a Wi-Fi or cellular data network");
                errorDialog.setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                android.os.Process.killProcess(android.os.Process.myPid());

                                SplashActivity.this.finish();
                            }
                        });

                AlertDialog errorAlert = errorDialog.create();
                return errorAlert;
            default:
                break;
        }
        return dialog;
    }

    @Override
    public void onAllPlaylistsRetrieve(AllPlaylistsResponse allPlaylistsResponse) {
        Log.e("playlists data", " " + allPlaylistsResponse.getItems() + "");
        baseApplication.myAllPlaylistsResponse = allPlaylistsResponse;
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onError(String error) {
        Log.e("error", " " + error);
    }
}
