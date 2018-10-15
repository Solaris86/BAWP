package com.buildappwithpaolo.bawp.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.buildappwithpaolo.bawp.R;
import com.buildappwithpaolo.bawp.data.CourseData;
import com.buildappwithpaolo.bawp.model.Course;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private int courseId;
    private Course course;
    private ImageView courseImageView;
    private TextView courseTitle;
    private InputMethodManager inputManager;
    private LinearLayout revealView;

    private boolean isEditTextVisible = false;

    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpUI();
        loadCourse();

        Toast.makeText(this, "course id: " + courseId, Toast.LENGTH_SHORT).show();
    }

    private void loadCourse() {
        course = new CourseData().courseList().get(getIntent().getExtras().getInt("course_id"));
        courseImageView.setImageResource(course.getImageResourceId(this));
        courseTitle.setText(course.getCourseName());
    }

    private void setUpUI() {
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        courseImageView = (ImageView) findViewById(R.id.detailsCourseImage);
        courseTitle = (TextView) findViewById(R.id.detailsCourseTitle);

        revealView = (LinearLayout) findViewById(R.id.revealView);
        revealView.setVisibility(View.INVISIBLE);
        isEditTextVisible = false;

        button = (FloatingActionButton) findViewById(R.id.detailsAddButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detailsAddButton:
                if (!isEditTextVisible) {
                    revealEditText(revealView);
                    button.setImageResource(R.drawable.icn_morph);
                    Animatable animatable = (Animatable) button.getDrawable();
                    animatable.start();
                } else {
                    hideEditText(revealView);
                    button.setImageResource(R.drawable.icn_morph_reverse);
                    Animatable animatable = (Animatable) button.getDrawable();
                    animatable.start();
                }
                break;
        }
    }

    private void hideEditText(final LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;
        int initialRadius = revealView.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, initialRadius, 0f);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
        isEditTextVisible = false;
        anim.start();
    }

    private void revealEditText(LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;
        int finalRadius = Math.max(revealView.getWidth(), revealView.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0f, finalRadius);
        revealView.setVisibility(View.VISIBLE);
        isEditTextVisible = true;
        anim.start();
    }
}
