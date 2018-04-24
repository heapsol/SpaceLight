package com.the.lightspace.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.the.lightspace.Activites.YouTubePlayerFragmentActivity;
import com.the.lightspace.Models.CategoriesModel;
import com.the.lightspace.R;
import com.the.lightspace.Util.ItemClickListener;

import java.util.ArrayList;

/**
 * Created by Cool Programmer on 4/24/2018.
 */

public class AdapterCategories extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<CategoriesModel> list;
    private Activity mContext;

    public AdapterCategories(ArrayList<CategoriesModel> list, Activity context) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.e("value", " " + list.get(position).getVideoID());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent i = new Intent(mContext, YouTubePlayerFragmentActivity.class);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

}

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ItemClickListener clickListener;

    public ViewHolder(View view) {
        super(view);

        view.setTag(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
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
