package com.the.lightspace.Activites;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

import com.the.lightspace.R;

public class AppInfoActivity extends Activity {
	
	TextView app_version;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.info);
			app_version = (TextView)findViewById(R.id.app_version);
			try {
				app_version.setText("version "+getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
		}

}
