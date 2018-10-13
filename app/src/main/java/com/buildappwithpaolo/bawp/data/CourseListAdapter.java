package com.buildappwithpaolo.bawp.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildappwithpaolo.bawp.R;
import com.buildappwithpaolo.bawp.model.Course;
import com.squareup.picasso.*;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    private CourseData courseData = new CourseData();

    @NonNull
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View courseRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_row, viewGroup, false);
        return new ViewHolder(courseRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListAdapter.ViewHolder viewHolder, int i) {
        Course course = courseData.courseList().get(i);
        viewHolder.courseTitle.setText(course.getCourseName());
        Picasso.get().load(course.getImageResourceId(viewHolder.courseTitle.getContext())).into(viewHolder.courseImageView);
        Picasso.get().load(course.getImageResourceId(viewHolder.courseTitle.getContext())).into(viewHolder.courseImageView);
    }

    @Override
    public int getItemCount() {
        return courseData.courseList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView courseTitle;
        public ImageView courseImageView, authorImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitleId);
            courseImageView = itemView.findViewById(R.id.courseImageId);
            authorImageView = itemView.findViewById(R.id.authorImageID);
        }
    }
}
