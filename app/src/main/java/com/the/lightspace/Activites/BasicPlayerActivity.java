package com.the.lightspace.Activites;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.the.lightspace.R;

/**
 * Created by npanigrahy on 04/07/2016.
 */
//public class YouTubePlayerFragmentActivity extends YouTubeBaseActivity {
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube_player_fragment);
//
//        final String VIDEO_ID = getIntent().getStringExtra("videoID");
//        //initializing and adding YouTubePlayerFragment
//        FragmentManager fm = getFragmentManager();
//        String tag = YouTubePlayerFragment.class.getSimpleName();
//        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
//        if (playerFragment == null) {
//            FragmentTransaction ft = fm.beginTransaction();
//            playerFragment = YouTubePlayerFragment.newInstance();
//            ft.add(android.R.id.content, playerFragment, tag);
//            ft.commit();
//        }
//
//        playerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
////                youTubePlayer.cueVideo(VIDEO_ID);
//                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
//                    @Override
//                    public void onPlaying() {
//
//                    }
//
//                    @Override
//                    public void onPaused() {
//
//                    }
//
//                    @Override
//                    public void onStopped() {
//
//                    }
//
//                    @Override
//                    public void onBuffering(boolean b) {
//
//
//                    }
//
//                    @Override
//                    public void onSeekTo(int i) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(YouTubePlayerFragmentActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}

public class BasicPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";
    String VIDEO_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_basic_player);
        VIDEO_ID = getIntent().getStringExtra("videoID");
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

}