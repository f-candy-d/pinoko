package com.f_candy_d.pinoko.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.utils.ThrowExceptionHelper;
import com.f_candy_d.pinoko.view.MyCH;

import java.util.ArrayList;

/**
 * Created by daichi on 8/12/17.
 */

public class CourseCardAdapter extends CardAdapter {

    private Context mContext;
    private ArrayList<Course> mCourses;

    public CourseCardAdapter(@NonNull final ArrayList<Course> courses,
                             @NonNull final Context context) {
        mCourses = courses;
        mContext = context;
    }

    @Override
    public RecyclerView.LayoutManager getParentLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void insertData(@NonNull EntryObject content) {
        if (content instanceof Course) {
            mCourses.add((Course) content);
            notifyItemInserted(mCourses.size() - 1);

        } else {
            ThrowExceptionHelper.throwClassCastException(Course.class, content.getClass());
        }
    }

    @Override
    public MyCH.BaseCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.course_card, parent, false);

        return new MyCH.CourseCardHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCH.BaseCardHolder holder, int position) {
        final int adpPos = holder.getAdapterPosition();
        ((MyCH.CourseCardHolder) holder).bind(adpPos, null, mCourses.get(adpPos));
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}
