package com.buildappwithpaolo.bawp.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buildappwithpaolo.bawp.R;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {
    @NonNull
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View courseRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_row, viewGroup, false);
        return new ViewHolder(courseRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
