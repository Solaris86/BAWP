package com.buildappwithpaolo.bawp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.buildappwithpaolo.bawp.R;
import com.buildappwithpaolo.bawp.data.CourseListAdapter;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CourseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        adapter = new CourseListAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        
        adapter.setOnClickListener(new CourseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DashboardActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
