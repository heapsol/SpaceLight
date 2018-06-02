package com.the.lightspace.Activites;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.the.lightspace.Adapters.AdapterCategories;
import com.the.lightspace.Adapters.AdapterRelatedVideos;
import com.the.lightspace.BaseClasses.BaseApplication;
import com.the.lightspace.DatabaseHandler.DatabaseHandler;
import com.the.lightspace.DatabaseHandler.DbModel;
import com.the.lightspace.Models.VideoEntry;
import com.the.lightspace.Network.Api;
import com.the.lightspace.Network.api.Statistics.VideosStatisticsApi;
import com.the.lightspace.Network.api.Statistics.VideosStatisticsResponse;
import com.the.lightspace.R;
import com.the.lightspace.Util.Constant;
import com.the.lightspace.Util.LinearDividerItemDecoration;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by npanigrahy on 04/07/2016.
 */

//public class YouTubePlayerFragmentActivity extends YouTubeBaseActivity {
//
//    private static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";
//    private String VIDEO_ID = "";
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube_player_fragment);
//
//        VIDEO_ID = getIntent().getStringExtra("videoID");
//
//
//        init();
//        clickListener();
//
//
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
//                youTubePlayer.loadVideo(VIDEO_ID);
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(YouTubePlayerFragmentActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    private void init() {
//
//    }
//
//    private void clickListener() {
//
//    }
//}


public class BasicPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, VideosStatisticsApi.VideosStatisticsCallbackListener {

    private Date date;
    private static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";
    private String VIDEO_ID = "";
    private int POSITION;
    private String DESCRIPTION, TITLE, PUBLISHEDAT, THUMBNAIL;
    private TextView tvDescription, tvViews, tvTitle;
    private ImageView ivShowDescription;
    private RelativeLayout rlFavInsidePlayer;
    private ExpandableLayout elDescription;
    private RelativeLayout rlTitle;
    private RecyclerView rvRelatedVideos;
    private AdapterRelatedVideos adapter;
    private TextView tvFav;
    private ImageView ivFav, ivUnfav;
    private DatabaseHandler db;
    private TextView tvPublishedAt;
    private ArrayList<VideoEntry> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_basic_player);
//        VIDEO_ID = getIntent().getStringExtra("videoID");
        POSITION = getIntent().getIntExtra("position", 0);
//        DESCRIPTION = getIntent().getStringExtra("description");
//        TITLE = getIntent().getStringExtra("title");
//        PUBLISHEDAT = getIntent().getStringExtra("publishedAt");
//        THUMBNAIL = getIntent().getStringExtra("thumbnail");

        VIDEO_ID = BaseApplication.relatedList.get(POSITION).getVideoId();
        DESCRIPTION = BaseApplication.relatedList.get(POSITION).getDescription();
        TITLE = BaseApplication.relatedList.get(POSITION).getTitle();
        PUBLISHEDAT = BaseApplication.relatedList.get(POSITION).getPublishedAt();

        Log.e("BaseList", BaseApplication.relatedList.size() + "");
        list = BaseApplication.relatedList;
        Log.e("recentItem", list.size() + " Published" + list.get(POSITION).getPublishedAt() + " Title : " + list.get(POSITION).getTitle());
//        Log.e("BeforeList", BaseApplication.relatedList.size() + "");
//        Log.e("AfterList", list.size() + "");
        Log.e("videoID: ", VIDEO_ID + " , Psotion: " + " , Description: " + DESCRIPTION + " , Title: " + TITLE);
        init();
        fetchData();
        clickListener();
        expansionUpdateListeners();
//        tvPublishedAt.setText("Published at : " + BaseApplication.relatedList.get(POSITION).getPublishedAt().toString());


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
                rlFavInsidePlayer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPaused() {
                rlFavInsidePlayer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPlaying() {
                rlFavInsidePlayer.setVisibility(View.GONE);
            }

            @Override
            public void onSeekTo(int arg0) {
            }

            @Override
            public void onStopped() {
                rlFavInsidePlayer.setVisibility(View.VISIBLE);
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
        rvRelatedVideos = (RecyclerView) findViewById(R.id.rvRelatedVideos);
        tvFav = (TextView) findViewById(R.id.tvFav);
        ivFav = (ImageView) findViewById(R.id.ivFav);
        ivUnfav = (ImageView) findViewById(R.id.ivUnfav);
        tvPublishedAt = (TextView) findViewById(R.id.tvPublishedAt);
        rlFavInsidePlayer = (RelativeLayout) findViewById(R.id.rlFavInsidePlayer);

        final SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat out = new SimpleDateFormat("MMM dd, yyyy");
        try {
            date = in.parse(splitDateAndTime(PUBLISHEDAT.toString()));
            tvPublishedAt.setText("Published at: "+out.format(date));
            Log.e("date", date + "");
        }catch (Exception e){
            e.printStackTrace();
        }
        list.remove(POSITION);
        LinearLayoutManager manager = new LinearLayoutManager(BasicPlayerActivity.this);
        rvRelatedVideos.setLayoutManager(manager);
//        rvRelatedVideos.addItemDecoration(new LinearDividerItemDecoration(BasicPlayerActivity.this, getResources().getColor(R.color.colorScreenBackground), 20));
        adapter = new AdapterRelatedVideos(list, BasicPlayerActivity.this);
        rvRelatedVideos.setNestedScrollingEnabled(false);
        rvRelatedVideos.setAdapter(adapter);
        db = new DatabaseHandler(BasicPlayerActivity.this);
        if (db.checkDuplicate(VIDEO_ID) == 0) {
            ivUnfav.setVisibility(View.VISIBLE);
            ivFav.setVisibility(View.GONE);
            tvFav.setText("Add Favorite");
        } else {
            ivFav.setVisibility(View.VISIBLE);
            ivUnfav.setVisibility(View.GONE);
            tvFav.setText("Remove Favorite");
        }
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
        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(VIDEO_ID);
                tvFav.setText("Add Favorite");
                ivFav.setVisibility(View.GONE);
                ivUnfav.setVisibility(View.VISIBLE);
            }
        });
        ivUnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbModel model = new DbModel();
                model.setVideoID(VIDEO_ID);
                model.setPubishedAt(PUBLISHEDAT);
                model.setDescription(DESCRIPTION);
                model.setTitle(TITLE);
                model.setThumbnailUrl(THUMBNAIL);
                db.add(model);
                tvFav.setText("Remove Favorite");
                ivUnfav.setVisibility(View.GONE);
                ivFav.setVisibility(View.VISIBLE);
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
        tvViews.setText(videosStatisticsResponses.getItems().get(0).getStatistics().getViewCount() + " views");
        tvTitle.setText(TITLE);
        tvDescription.setText(DESCRIPTION);
    }

    @Override
    public void onError(String error) {
        Log.e("statics_error", " " + error);
    }

    public String splitDateAndTime(String s) {

        String[] separated = s.split("T");

        Log.e("separated", separated[0] + "");
        return separated[0];
    }
}