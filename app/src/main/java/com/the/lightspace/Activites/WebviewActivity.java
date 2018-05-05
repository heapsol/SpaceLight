package com.the.lightspace.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.the.lightspace.R;

public class WebviewActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "";
    private CardView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        url = getIntent().getExtras().getString("url");

        progressBar = (CardView) findViewById(R.id.cvProgress);
        findViewById(R.id.icBackWebView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.icShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });

        webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebviewActivity.this, "Oh no! webpage not found.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                Log.e("get info url", " " + url);
                findViewById(R.id.progress).setVisibility(View.GONE);
            }
        });

    }
    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out Space Light at: https://play.google.com/store/apps/details?id=com.the.lightspace");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
