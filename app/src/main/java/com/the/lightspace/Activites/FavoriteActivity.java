package com.the.lightspace.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.the.lightspace.Adapters.AdapterFavorite;
import com.the.lightspace.DatabaseHandler.DatabaseHandler;
import com.the.lightspace.DatabaseHandler.DbModel;
import com.the.lightspace.R;
import com.the.lightspace.Util.LinearDividerItemDecoration;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView rvFav;
    ArrayList<DbModel> list;
    DatabaseHandler db;
    TextView tvNoFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        init();
        clickListeners();
    }

    public void init() {
        tvNoFav = (TextView) findViewById(R.id.tvNoFav);
        rvFav = (RecyclerView) findViewById(R.id.rvFav);
        list = new ArrayList<>();
        db = new DatabaseHandler(FavoriteActivity.this);
        list = db.getAllFavorites();
        Log.e("DbList", list.size() + "");
        if (list.size() > 0) {
            rvFav.setVisibility(View.VISIBLE);
            tvNoFav.setVisibility(View.GONE);
        } else {
            rvFav.setVisibility(View.GONE);
            tvNoFav.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(FavoriteActivity.this);
        manager.setReverseLayout(false);
        rvFav.setLayoutManager(manager);
        rvFav.addItemDecoration(new LinearDividerItemDecoration(FavoriteActivity.this, getResources().getColor(R.color.colorScreenBackground), 20));
        AdapterFavorite adapter = new AdapterFavorite(list, FavoriteActivity.this, tvNoFav);
        rvFav.setNestedScrollingEnabled(false);
        rvFav.setAdapter(adapter);
    }

    public void clickListeners() {
        findViewById(R.id.icBackFav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

}
