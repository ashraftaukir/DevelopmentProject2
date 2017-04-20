package com.example.taukir.developmentproject2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.taukir.developmentproject2.R;



public class GridViewActivity extends MainActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.gridviewlayout);
       getLayoutInflater().inflate(R.layout.activity_main, contentframe);
    }

//    @Override
//    public void setContentView(@LayoutRes int layoutResID) {
//        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
//        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.content_frame);
//        getLayoutInflater().inflate(layoutResID, activityContainer, true);
//        super.setContentView(fullView);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        setTitle("Activity Title");
//    }
}
