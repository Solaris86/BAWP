package com.buildappwithpaolo.bawp.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.buildappwithpaolo.bawp.R;
import com.buildappwithpaolo.bawp.data.CourseData;
import com.buildappwithpaolo.bawp.model.Course;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private int courseId;
    private Course course;
    private ImageView courseImageView;
    private TextView courseTitle;
    private InputMethodManager inputManager;
    private LinearLayout revealView;
    private EditText commentEditText;

    private boolean isEditTextVisible = false;

    private FloatingActionButton button;
    private ArrayList<String> comments;
    private ArrayAdapter<String> commentsAdapter;
    private ListView commentsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpUI();
        setUpAdapter();
        loadCourse();
        getPhoto();

        Toast.makeText(this, "course id: " + courseId, Toast.LENGTH_SHORT).show();
    }

    private void setUpAdapter() {
        commentsListView = (ListView) findViewById(R.id.detailsCommentsListView);
        comments = new ArrayList<>();
        commentsAdapter = new ArrayAdapter<>(this, R.layout.comment_row, comments);
        commentsListView.setAdapter(commentsAdapter);
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

        commentEditText = (EditText) findViewById(R.id.detailsComments);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detailsAddButton:
                if (!isEditTextVisible) {
                    revealEditText(revealView);
                    commentEditText.requestFocus();
                    inputManager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT);
                    button.setImageResource(R.drawable.icn_morph);
                    Animatable animatable = (Animatable) button.getDrawable();
                    animatable.start();
                } else {
                    hideEditText(revealView);
                    button.setImageResource(R.drawable.icn_morph_reverse);
                    addToComment(commentEditText.getText().toString().trim());
                    commentEditText.setText("");
                    inputManager.hideSoftInputFromWindow(commentEditText.getWindowToken(), 0);
                    Animatable animatable = (Animatable) button.getDrawable();
                    animatable.start();
                }
                break;
        }
    }

    private void getPhoto() {
        Bitmap photo = BitmapFactory.decodeResource(getResources(), course.getImageResourceId(this));
        colorized(photo);
    }

    private void colorized(Bitmap photo) {
        Palette palette = Palette.from(photo).generate();
        applyPalette(palette);
    }

    private void applyPalette(Palette palette) {
        getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkMutedColor(0)));
        courseTitle.setBackgroundColor(palette.getDarkMutedColor(0));
        revealView.setBackgroundColor(palette.getLightMutedColor(0));
    }

    private void addToComment(String comment) {
        comments.add(comment);
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
