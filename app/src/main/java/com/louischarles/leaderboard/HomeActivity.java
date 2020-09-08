package com.louischarles.leaderboard;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity  {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private PagerAdapter mPagerAdapter;
    private Button mSubmitPageButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle("LeaderBoard");
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        mSubmitPageButton = (Button) findViewById(R.id.action_submit);
        mSubmitPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSubmission();
            }
        });

        mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mTabLayout.setBackgroundColor(Color.TRANSPARENT);
        mTabLayout.addTab(mTabLayout.newTab().setText(R .string.learning_leaders_frag));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.skill_i_q_leaders_frag));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter);
    //incompatible types: android.widget.Toolbar cannot be converted to androidx.appcompat.Toolbar
        implementViewPager();
    }

    private void implementViewPager() {
        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {
//            moveToSubmission();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void moveToSubmission() {
        Intent intent = new Intent(HomeActivity.this, SubmitProjectActivity.class);
        startActivity(intent);
    }

}
