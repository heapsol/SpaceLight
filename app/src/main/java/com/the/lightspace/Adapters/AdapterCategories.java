package com.the.lightspace.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.the.lightspace.Activites.YouTubePlayerFragmentActivity;
import com.the.lightspace.Fragments.FragmentCategories;
import com.the.lightspace.R;
import com.the.lightspace.Util.ItemClickListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cool Programmer on 4/24/2018.
 */

public class AdapterCategories extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<FragmentCategories.VideoEntry> list;
    private Activity mContext;

    public AdapterCategories(ArrayList<FragmentCategories.VideoEntry> list, Activity context) {
        this.list = list;
        mContext = context;
        Log.e("value", " " + list.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_thumbnail_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Log.e("value", " " + list.get(position).getVideoId());

        holder.tvTitle.setText(list.get(position).getTitle());
        Glide.with(mContext)
                .load(list.get(position).getThumbnailsMedium())
                .into((holder.ivThumbnail));
        if (list.get(position).getDescription().equals("")) {
            holder.tvDescription.setVisibility(View.GONE);
            holder.v.setVisibility(View.GONE);
        } else {
            holder.tvDescription.setText(list.get(position).getDescription());
        }
            holder.tvPublishedAt.setText(list.get(position).getPublishedAt());        //holder.tvPublishedAt.setText(Instant.parse(list.get(position).getPublishedAt()).toString());


        holder.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, YouTubePlayerFragmentActivity.class);
                i.putExtra("videoID", list.get(position).getVideoId().toString());
                i.putExtra("position", position);
                i.putExtra("title", list.get(position).getTitle().toString());
                i.putExtra("description", list.get(position).getDescription().toString());
                mContext.startActivity(i);
            }
        });
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                Intent i = new Intent(mContext, YouTubePlayerFragmentActivity.class);
//                i.putExtra("videoID", list.get(position).getVideoId().toString());
//                i.putExtra("position", position);
//                i.putExtra("title", list.get(position).getTitle().toString());
//                i.putExtra("description", list.get(position).getDescription().toString());
//                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    public String convertFormate(String time) {

        //first you need to use proper date formatter
        DateFormat df = new SimpleDateFormat("MMM dd yyyy hh:mmaa");
        Date date = null;// converting String to date
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(df.format(date));
        return date.toString();
    }

}

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ItemClickListener clickListener;
    public ImageView ivThumbnail, ivPlay;
    public TextView tvTitle, tvPublishedAt, tvDescription;
    public View v;


    public ViewHolder(View view) {
        super(view);

        view.setTag(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        this.ivThumbnail = (ImageView) view.findViewById(R.id.ivThumbnail);
        this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        this.tvPublishedAt = (TextView) view.findViewById(R.id.tvPublishedAt);
        this.tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        this.v = (View) view.findViewById(R.id.vi);
        this.ivPlay = (ImageView) view.findViewById(R.id.ivPlay);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        clickListener.onClick(view, getPosition(), true);
        return true;
    }
}