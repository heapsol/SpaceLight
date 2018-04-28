package com.the.lightspace.Activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.the.lightspace.Network.Api;
import com.the.lightspace.Network.api.Statistics.VideosStatisticsApi;
import com.the.lightspace.Network.api.Statistics.VideosStatisticsResponse;
import com.the.lightspace.R;
import com.the.lightspace.Util.Constant;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * Created by npanigrahy on 04/07/2016.
 */

public class BasicPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, VideosStatisticsApi.VideosStatisticsCallbackListener {

    private static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";
    private String VIDEO_ID = "";
    private int POSITION;
    private String DESCRIPTION, TITLE;
    private TextView tvDescription, tvViews, tvTitle;
    private ImageView ivShowDescription;
    private ExpandableLayout elDescription;
    private RelativeLayout rlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_basic_player);
        VIDEO_ID = getIntent().getStringExtra("videoID");
        POSITION = getIntent().getIntExtra("position", 0);
        DESCRIPTION = getIntent().getStringExtra("description");
        TITLE = getIntent().getStringExtra("title");

        init();
        fetchData();
        clickListener();
        expansionUpdateListeners();

        // Initializing YouTube player view
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (null == player) return;

        // Start buffering
        if (!wasRestored) {
            player.loadVideo(VIDEO_ID);
        }

        // Add listeners to YouTubePlayer instance
        player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onAdStarted() {
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason arg0) {
            }

            @Override
            public void onLoaded(String arg0) {
            }

            @Override
            public void onLoading() {
            }

            @Override
            public void onVideoEnded() {
            }

            @Override
            public void onVideoStarted() {
            }
        });


        player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onBuffering(boolean arg0) {
            }

            @Override
            public void onPaused() {
            }

            @Override
            public void onPlaying() {
            }

            @Override
            public void onSeekTo(int arg0) {
            }

            @Override
            public void onStopped() {
            }
        });
    }

    private void init() {
        elDescription = (ExpandableLayout) findViewById(R.id.elDescription);
        ivShowDescription = (ImageView) findViewById(R.id.ivShowDescription);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvViews = (TextView) findViewById(R.id.tvViews);
        rlTitle = (RelativeLayout) findViewById(R.id.rlTitle);
    }

    private void clickListener() {
        ivShowDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elDescription.isExpanded()) {
                    elDescription.collapse();
                } else {
                    elDescription.expand();
                }
            }
        });
        rlTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elDescription.isExpanded()) {
                    elDescription.collapse();
                } else {
                    elDescription.expand();
                }
            }
        });
    }

    private void expansionUpdateListeners() {
        elDescription.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                ivShowDescription.setRotation(expansionFraction * 180);
            }
        });
    }

    public void fetchData() {
        Api.videosStatisticsApi.getVideosStatistics("statistics", VIDEO_ID, Constant.apiKey, this);
    }

    @Override
    public void onVideosStatisticsRetrieve(VideosStatisticsResponse videosStatisticsResponses) {
//        Log.e("statistics_data", " " + videosStatisticsResponses.getItems().get(POSITION).getStatistics().getCommentCount());
        tvViews.setText(videosStatisticsResponses.getItems().get(POSITION).getStatistics().getViewCount() + " views");
        tvTitle.setText(TITLE);
        tvDescription.setText(DESCRIPTION);
    }

    @Override
    public void onError(String error) {
        Log.e("statics_error", " " + error);
    }
}