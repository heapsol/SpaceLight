package com.the.lightspace.Activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.the.lightspace.BaseClasses.BaseActivity;

import com.the.lightspace.Fragments.myFifthFragment;
import com.the.lightspace.Fragments.myFirstFragment;
import com.the.lightspace.Fragments.myFourtFragment;
import com.the.lightspace.Fragments.mySecondFragment;
import com.the.lightspace.Fragments.myThirdFragment;
import com.the.lightspace.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView icShare;

    ArrayList<ThreeTabsList> newList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AdView adView = (AdView) findViewById(R.id.myAddView);
        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

        icShare = (ImageView) findViewById(R.id.icShare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        newList = new ArrayList<>();
        for (int i = 0; i < baseApplication.myAllPlaylistsResponse.getItems().size(); i++) {
            String tabName = baseApplication.myAllPlaylistsResponse.getItems().get(i).getSnippet().getTitle();
            if (tabName.equalsIgnoreCase("philosophy")
                    || tabName.equalsIgnoreCase("Let's Get Spiritual!")
                    || tabName.equalsIgnoreCase("Get Motivated!")) {
                ThreeTabsList model = new ThreeTabsList();
                model.setTabName(baseApplication.myAllPlaylistsResponse.getItems().get(i).getSnippet().getTitle());
                model.setTabId(baseApplication.myAllPlaylistsResponse.getItems().get(i).getId());
                newList.add(model);
            }
        }
        clickListeners();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void clickListeners() {
        icShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_us) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_app_info) {
            Intent i = new Intent(MainActivity.this, AppInfoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_contact_us) {
            Intent i = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            shareApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class PagerAdapter extends FragmentPagerAdapter {


        String tabTitles[];
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
            tabTitles = new String[newList.size()];
            for (int i = 0; i < tabTitles.length; i++) {
                tabTitles[i] = newList.get(i).getTabName();
            }

        }

        @Override
        public int getCount() {
            Log.e("tabCunt", tabTitles.length+"");
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new myFirstFragment().newInstance(newList.get(0).getTabId());
                case 1:
                    return new mySecondFragment().newInstance(newList.get(1).getTabId());
                case 2:
                    return new myThirdFragment().newInstance(newList.get(2).getTabId());
//                case 3:
//                    return new myFourtFragment().newInstance(baseApplication.myAllPlaylistsResponse.getItems().get(position).getId());
//                case 4:
//                    return new myFifthFragment().newInstance(baseApplication.myAllPlaylistsResponse.getItems().get(position).getId());
            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }
    }

    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out Space Light at: https://play.google.com/store/apps/details?id=com.the.lightspace");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public class ThreeTabsList {

        String tabName, tabId;

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public String getTabId() {
            return tabId;
        }

        public void setTabId(String tabId) {
            this.tabId = tabId;
        }
    }
}



