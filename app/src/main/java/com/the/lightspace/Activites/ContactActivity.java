package com.the.lightspace.Activites;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.the.lightspace.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ContactActivity extends AppCompatActivity {
    CardView email, website, facebook, twitter;
    DisplayMetrics displayMetrics;
    int height, width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact_new);
        AdView adView = (AdView) findViewById(R.id.adViewN);
        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

//        displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        height = displayMetrics.heightPixels;
//        width = displayMetrics.widthPixels;
//        Log.e("width, hight", width + ", " + height);
//        findViewById(R.id.llMAin).setMinimumHeight(width);
        findViewById(R.id.icBack).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        email = (CardView) findViewById(R.id.rlEmail);
        website = (CardView) findViewById(R.id.rlWebsite);
        facebook = (CardView) findViewById(R.id.rlFacebook);
        twitter = (CardView) findViewById(R.id.rlTwitter);
        facebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        twitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
