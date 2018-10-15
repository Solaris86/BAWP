package com.buildappwithpaolo.bawp.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.buildappwithpaolo.bawp.R;

public class DetailsActivity extends AppCompatActivity {

    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        courseId = bundle.getInt("course_id");

        Toast.makeText(this, "course id: " + courseId, Toast.LENGTH_SHORT).show();
    }

}
