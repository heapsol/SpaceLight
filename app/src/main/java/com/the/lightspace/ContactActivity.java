package com.the.lightspace;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.the.lightspace.Activites.MainActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ContactActivity extends AppCompatActivity {
	LinearLayout email,website;
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);

		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		
		adView.loadAd(adRequest);


		findViewById(R.id.icBack).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		email = (LinearLayout)findViewById(R.id.email_layout);
		website = (LinearLayout)findViewById(R.id.website_layout);
		email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
			    emailIntent.setData(Uri.parse("mailto:" + "lightspaceofficial@gmail.com")); 
			        startActivity(emailIntent);
			    

			}
		});
		website.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri webpage = Uri.parse("http://thelightspace.net");
			    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
			    if (intent.resolveActivity(getPackageManager()) != null) {
			        startActivity(intent);
			    }

			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.right_in, R.anim.right_out);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(ContactActivity.this,
					MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
