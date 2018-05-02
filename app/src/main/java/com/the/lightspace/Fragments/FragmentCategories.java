package com.the.lightspace.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.lightspace.Adapters.AdapterCategories;
import com.the.lightspace.BaseClasses.BaseFragment;
import com.the.lightspace.Network.Api;
import com.the.lightspace.Network.api.AllVideos.AllVideosApi;
import com.the.lightspace.Network.api.AllVideos.AllVideosResponse;
import com.the.lightspace.R;
import com.the.lightspace.Util.Constant;
import com.the.lightspace.Util.LinearDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Cool Programmer on 10/19/2017.
 */

public class FragmentCategories extends BaseFragment implements AllVideosApi.AllVideosCallbackListener {


    private View view;
    ProgressDialog progress;
    RecyclerView rvCategories;
    public ArrayList<VideoEntry> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_my_collection, container, false);


        init(view);
        clickListeners();
        fetchData();
        return view;
    }

    private void init(View view) {
        rvCategories = (RecyclerView) view.findViewById(R.id.rvCategories);
        list = new ArrayList<VideoEntry>();

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading Videos... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

    }

    private void clickListeners() {

    }

    public void fetchData() {
        Api.allVideosApi.getVideos(Constant.apiKey, Constant.channelId,
                "snippet,id", "date", "20", this);
    }

    @Override
    public void onVideosRetrieve(AllVideosResponse allVideosResponses) {
        Log.e("item data", " " + allVideosResponses.getNextPageToken());
        for (int k = 0; k < allVideosResponses.getItems().size(); k++) {

            VideoEntry model = new VideoEntry();
            model.setTitle(allVideosResponses.getItems().get(k).snippet.getTitle());
            model.setVideoId(allVideosResponses.getItems().get(k).ids.getVideoId());
            model.setThumbnailsMedium(allVideosResponses.getItems().get(k).snippet.thumbnails.medium.getUrl());
            model.setPublishedAt(allVideosResponses.getItems().get(k).snippet.getPublishedAt());
            model.setDescription(allVideosResponses.getItems().get(k).snippet.getDescription());
            list.add(model);
        }
        progress.dismiss();
//        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvCategories.addItemDecoration(new LinearDividerItemDecoration(getContext(), getResources().getColor(R.color.colorScreenBackground), 20));
//        AdapterCategories adapter = new AdapterCategories(list, getActivity());
//        rvCategories.setNestedScrollingEnabled(false);
//        rvCategories.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {
        Log.e("error", " " + error);
    }

    public static class VideoEntry {
        private String title, thumbnailSmall, thumbnailsMedium, thumbnailsLarge, publishedAt, description;
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

//

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

//    public void hitURL(final String url) {
//
//        try {
//            String jsonString = postLogin(url);
//            if (jsonString != null || jsonString == "") {
//
//                Log.e("json string", " " + jsonString.toString());
//                JSONObject object = new JSONObject(jsonString.toString());
//
//
//                JSONArray itemsJsonArray = object.getJSONArray("items");
//                Log.e("list size", " " + itemsJsonArray.length());
//                for (int i = 0; i < itemsJsonArray.length() - 1; i++) {
//                    Log.e("jsonobjects", " " + itemsJsonArray.getJSONObject(i).length());
//
//
////                    for (int j = 0; i < itemsJsonArray.length(); i++) {
//                    JSONObject d = itemsJsonArray.getJSONObject(i);
//
//                    String id = d.getJSONObject("id").getString("videoId");
//                    String title = d.getJSONObject("snippet").getString("title");
//                    String urlNew = d.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");
//
//                    Log.e("id", id + "");
//                    Log.e("title", title + "");
//                    Log.e("urlNew", urlNew + "");
//
//                    VideoEntry model = new VideoEntry();
//                    model.setVideoId(id);
//                    model.setTitle(title);
//                    model.setThumbnailsMedium(urlNew);
//                    list.add(model);
//                }
//                Log.e("list", list.size() + "");
//
//
//            }
//        } catch (SocketTimeoutException e) {
////            getActivity().this.runOnUiThread(new Runnable() {
////                public void run() {
////                    Toast.makeText(MainActivity.this, "Timeout due to Network Connection, Try  Again !!", Toast.LENGTH_SHORT).show();
////                }
////            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    String postLogin(String url) throws IOException {
////        FormBody.Builder formBuilder = new FormBody.Builder();
//
//        OkHttpClient client = new OkHttpClient();
//
////        params.put("user_email", email);
////        params.put("user_pass", pwd);
//
//////      dynamically add more parameter like this:
////        formBuilder.add("__a", "1");
////        formBuilder.add("user[password]", password);
//
////        RequestBody formBody = formBuilder.build();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        Response response = client.newCall(request).execute();
//
//        return response.body().string();
//    }

//    public class getData extends AsyncTask<String, Void, Boolean> {
//        ProgressDialog progress;
//
//
//        public getData() {
//            progress = new ProgressDialog(getActivity());
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progress.setMessage("Loading Videos... ");
//            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progress.setIndeterminate(true);
//            progress.setCancelable(false);
//            progress.show();
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            super.onPostExecute(success);
//            progress.dismiss();
//            rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
//            rvCategories.addItemDecoration(new LinearDividerItemDecoration(getContext(), getResources().getColor(R.color.colorScreenBackground), 20));
//            AdapterCategories adapter = new AdapterCategories(list, getActivity());
//            rvCategories.setNestedScrollingEnabled(false);
//            rvCategories.setAdapter(adapter);
//        }
//
//        @Override
//        protected Boolean doInBackground(final String... args) {
//
//            hitURL(BaseActivity.API_URL);
//            return true;
//        }
//    }

}
