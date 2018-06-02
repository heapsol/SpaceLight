package com.the.lightspace.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.the.lightspace.Activites.BasicPlayerActivity;
import com.the.lightspace.BaseClasses.BaseApplication;
import com.the.lightspace.DatabaseHandler.DatabaseHandler;
import com.the.lightspace.DatabaseHandler.DbModel;
import com.the.lightspace.Models.VideoEntry;
import com.the.lightspace.R;
import com.the.lightspace.Util.ItemClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cool Programmer on 5/30/2018.
 */

public class AdapterRelatedVideos extends RecyclerView.Adapter<RelatedViewHolder> {

    private ArrayList<VideoEntry> list;
    private Activity mContext;
    private DatabaseHandler db;

    public AdapterRelatedVideos(ArrayList<VideoEntry> list, Activity context) {
        this.list = list;
        mContext = context;
        Log.e("RelatedList", " " + list.size());
        db = new DatabaseHandler(context);
    }

    @Override
    public RelatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_playlist_row, null);

        RelatedViewHolder viewHolder = new RelatedViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RelatedViewHolder holder, final int position) {


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

        final SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat out = new SimpleDateFormat("MMM dd, yyyy");
        try {
            final Date date = in.parse(splitDateAndTime(list.get(position).getPublishedAt().toString()));
            holder.tvPublishedAt.setText(out.format(date));
            Log.e("myDate", date + "");

            if (db.checkDuplicate(list.get(position).getVideoId()) == 0) {
                holder.ivUnfav.setVisibility(View.VISIBLE);
                holder.ivFav.setVisibility(View.GONE);
                holder.tvFav.setText("Add Favorite");
            } else {
                holder.ivFav.setVisibility(View.VISIBLE);
                holder.ivUnfav.setVisibility(View.GONE);
                holder.tvFav.setText("Remove Favorite");
            }

            holder.ivFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ivFav.setVisibility(View.GONE);
                    holder.ivUnfav.setVisibility(View.VISIBLE);
                    db.delete(list.get(position).getVideoId());
                    Log.e("Removed Item", list.get(position).getVideoId());
                    holder.tvFav.setText("Add Favorite");
                }
            });
            holder.ivUnfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DbModel model = new DbModel();
                    model.setVideoID(list.get(position).getVideoId());
                    model.setTitle(list.get(position).getTitle());
                    model.setThumbnailUrl(list.get(position).getThumbnailsMedium());
                    model.setDescription(list.get(position).getDescription());  //list.get(position).getDescription()
                    model.setPubishedAt(out.format(date).toString());    //out.format(date)
                    holder.tvFav.setText("Remove Favorite");
                    holder.ivUnfav.setVisibility(View.GONE);
                    holder.ivFav.setVisibility(View.VISIBLE);
                    db.add(model);
                    Log.e("Added Item", list.get(position).getVideoId());
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(int z = 0; z<list.size();z++){
//
//                }
                Log.e("listsize", ""+list.size());
                Intent i = new Intent(mContext, BasicPlayerActivity.class);
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
    public String splitDateAndTime(String s) {

        String[] separated = s.split("T");

        Log.e("separated", separated[0] + "");
        return separated[0];
    }

}

class RelatedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ItemClickListener clickListener;
    public ImageView ivThumbnail, ivPlay;
    public TextView tvTitle, tvPublishedAt, tvDescription, tvFav;
    public View v;
    ImageView ivFav, ivUnfav;


    public RelatedViewHolder(View view) {
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
        this.ivFav = (ImageView) view.findViewById(R.id.ivFav);
        this.ivUnfav = (ImageView) view.findViewById(R.id.ivUnfav);
        this.tvFav = (TextView) view.findViewById(R.id.tvFav);

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