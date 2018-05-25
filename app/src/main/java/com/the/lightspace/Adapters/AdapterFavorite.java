package com.the.lightspace.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.the.lightspace.Activites.YouTubePlayerFragmentActivity;
import com.the.lightspace.DatabaseHandler.DatabaseHandler;
import com.the.lightspace.DatabaseHandler.DbModel;
import com.the.lightspace.R;
import com.the.lightspace.Util.ItemClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cool Programmer on 5/22/2018.
 */

public class AdapterFavorite extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<DbModel> list;
    private Activity mContext;
    private DatabaseHandler db;
    private TextView tvNoFav;

    public AdapterFavorite(ArrayList<DbModel> list, Activity context, TextView tvNoFav) {
        this.list = list;
        mContext = context;
        Log.e("FavList", " " + list.size());
        db = new DatabaseHandler(mContext);
        this.tvNoFav = tvNoFav;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_thumbnail_row_new, null);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        Glide.with(mContext)
                .load(list.get(position).getThumbnailUrl())
                .into((holder.ivThumbnail));
        if (list.get(position).getDescription().equals("")) {
            holder.tvDescription.setVisibility(View.GONE);
            holder.v.setVisibility(View.GONE);
        } else {
            holder.tvDescription.setText(list.get(position).getDescription());
        }


        if (db.checkDuplicate(list.get(position).getVideoID()) == 0) {
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
                db.delete(list.get(position).getVideoID());
                Log.e("Removed Item", list.get(position).getVideoID());
                holder.tvFav.setText("Remove Favorite");
                list.remove(position);
                if (list.size() > 0) {
                    notifyDataSetChanged();
                } else {
                    tvNoFav.setVisibility(View.VISIBLE);
                }
                Intent intent = new Intent("FavBroadCast");
                intent.putExtra("fav", "removed");
                LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(mContext);
                broadcastManager.sendBroadcast(intent);
            }
        });
//        holder.ivUnfav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DbModel model = new DbModel();
//                model.setVideoID(list.get(position).getVideoID());
//                model.setTitle(list.get(position).getTitle());
//                model.setThumbnailUrl(list.get(position).getThumbnailUrl());
//                model.setDescription("myDescription");  //list.get(position).getDescription()
//                model.setPubishedAt("data");    //out.format(date)
//
//                holder.ivUnfav.setVisibility(View.GONE);
//                holder.ivFav.setVisibility(View.VISIBLE);
//                db.add(model);
//                Log.e("Added Item", list.get(position).getVideoID());
//            }
//        });
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //nothing to do
            }
        });
        holder.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, YouTubePlayerFragmentActivity.class);
                i.putExtra("videoID", list.get(position).getVideoID().toString());
                i.putExtra("position", position);
                i.putExtra("title", list.get(position).getTitle().toString());
                i.putExtra("description", list.get(position).getDescription().toString());
                mContext.startActivity(i);
            }
        });
        holder.tvPublishedAt.setText(list.get(position).getPubishedAt().toString());
    }

    @Override
    public int getItemCount() {

        return (null != list ? list.size() : 0);
    }


}

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ItemClickListener clickListener;
    public ImageView ivThumbnail, ivPlay;
    public TextView tvTitle, tvPublishedAt, tvDescription, tvFav;
    public View v;
    ImageView ivFav, ivUnfav;


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