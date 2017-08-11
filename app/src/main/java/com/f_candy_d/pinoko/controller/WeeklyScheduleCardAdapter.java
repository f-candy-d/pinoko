package com.f_candy_d.pinoko.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.view.MyCH;

/**
 * Created by daichi on 17/08/07.
 */

public class WeeklyScheduleCardAdapter extends CardAdapter {

    public WeeklyScheduleCardAdapter() {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public MyCH.BaseCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_course_card, parent, false);
        return new MyCH.MiniCourseCardHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCH.BaseCardHolder holder, int position) {

    }

    @Override
    public RecyclerView.LayoutManager getParentLayoutManager() {
        return new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void insertData(@NonNull EntryObject content) {

    }
}
