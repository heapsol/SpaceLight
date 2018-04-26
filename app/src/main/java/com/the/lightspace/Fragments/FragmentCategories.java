package com.the.lightspace.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.the.lightspace.Activites.MainActivity;
import com.the.lightspace.Adapters.AdapterCategories;
import com.the.lightspace.BaseClasses.BaseActivity;
import com.the.lightspace.BaseClasses.BaseFragment;
import com.the.lightspace.Models.CategoriesModel;
import com.the.lightspace.R;
import com.the.lightspace.Util.LinearDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Cool Programmer on 10/19/2017.
 */

public class FragmentCategories extends BaseFragment {


    private View view;

    RecyclerView rvCategories;
    public ArrayList<VideoEntry> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_my_collection, container, false);


        list = new ArrayList<VideoEntry>();
        new getData().execute();

        init(view);
        clickListeners();
//        populateList();


        return view;
    }

    private void init(View view) {

        rvCategories = (RecyclerView) view.findViewById(R.id.rvCategories);

    }

    private void clickListeners() {

    }

    public static class VideoEntry {
        private String title, thumbnailSmall, thumbnailsMedium, thumbnailsLarge;
        private String videoId;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setThumbnailSmall(String thumbnailSmall) {
            this.thumbnailSmall = thumbnailSmall;
        }

        public void setThumbnailsMedium(String thumbnailsMedium) {
            this.thumbnailsMedium = thumbnailsMedium;
        }

        public void setThumbnailsLarge(String thumbnailsLarge) {
            this.thumbnailsLarge = thumbnailsLarge;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getTitle() {
            return title;
        }

        public String getThumbnailSmall() {
            return thumbnailSmall;
        }

        public String getThumbnailsMedium() {
            return thumbnailsMedium;
        }

        public String getThumbnailsLarge() {
            return thumbnailsLarge;
        }

        public String getVideoId() {
            return videoId;
        }
//        public VideoEntry(String text, String videoId, String thumbnailSmall, String thumbnailsMedium, String thumbnailsLarge) {
//            this.thumbnailSmall = thumbnailSmall;
//            this.thumbnailsMedium = thumbnailsMedium;
//            this.thumbnailsLarge = thumbnailsLarge;
//            this.title = text;
//            this.videoId = videoId;
//        }
    }

    public void hitURL(final String url) {

        try {
            String jsonString = postLogin(url);
            if (jsonString != null || jsonString == "") {

                Log.e("json string", " " + jsonString.toString());
                JSONObject object = new JSONObject(jsonString.toString());


                JSONArray itemsJsonArray = object.getJSONArray("items");
                Log.e("list size", " " + itemsJsonArray.length());
                for (int i = 0; i < itemsJsonArray.length() - 1; i++) {
                    Log.e("jsonobjects", " " + itemsJsonArray.getJSONObject(i).length());


//                    for (int j = 0; i < itemsJsonArray.length(); i++) {
                    JSONObject d = itemsJsonArray.getJSONObject(i);

                    String id = d.getJSONObject("id").getString("videoId");
                    String title = d.getJSONObject("snippet").getString("title");
                    String urlNew = d.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");

                    Log.e("id", id + "");
                    Log.e("title", title + "");
                    Log.e("urlNew", urlNew + "");

                    VideoEntry model = new VideoEntry();
                    model.setVideoId(id);
                    model.setTitle(title);
                    model.setThumbnailsMedium(urlNew);
                    list.add(model);
                }
                Log.e("list", list.size() + "");


            }
        } catch (SocketTimeoutException e) {
//            getActivity().this.runOnUiThread(new Runnable() {
//                public void run() {
//                    Toast.makeText(MainActivity.this, "Timeout due to Network Connection, Try  Again !!", Toast.LENGTH_SHORT).show();
//                }
//            });
        } catch (JSONException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String postLogin(String url) throws IOException {
//        FormBody.Builder formBuilder = new FormBody.Builder();

        OkHttpClient client = new OkHttpClient();

//        params.put("user_email", email);
//        params.put("user_pass", pwd);

////      dynamically add more parameter like this:
//        formBuilder.add("__a", "1");
//        formBuilder.add("user[password]", password);

//        RequestBody formBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public class getData extends AsyncTask<String, Void, Boolean> {
        ProgressDialog progress;


        public getData() {
            progress = new ProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setMessage("Loading Videos... ");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);
            progress.dismiss();
            rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
            rvCategories.addItemDecoration(new LinearDividerItemDecoration(getContext(), getResources().getColor(R.color.colorScreenBackground), 20));
            AdapterCategories adapter = new AdapterCategories(list, getActivity());
            rvCategories.setNestedScrollingEnabled(false);
            rvCategories.setAdapter(adapter);
        }

        @Override
        protected Boolean doInBackground(final String... args) {

            hitURL(BaseActivity.API_URL);
            return true;
        }
    }

}
