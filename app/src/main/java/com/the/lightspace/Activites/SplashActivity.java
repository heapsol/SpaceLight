package com.the.lightspace.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.the.lightspace.R;

public class SplashActivity extends Activity {

	private static int SPLASH_TIME_OUT = 2000; // SplashPage will be visible for 2s
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
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if(isOnline(SplashActivity.this)){
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				finish();
				}
				// close this activity
				
			}
		}, SPLASH_TIME_OUT);
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

}
