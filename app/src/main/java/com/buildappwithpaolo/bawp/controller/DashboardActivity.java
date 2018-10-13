package com.buildappwithpaolo.bawp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.buildappwithpaolo.bawp.R;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }
}
