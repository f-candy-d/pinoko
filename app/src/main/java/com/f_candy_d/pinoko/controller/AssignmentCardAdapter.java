package com.f_candy_d.pinoko.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Assignment;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.view.MyCH;

import java.util.ArrayList;

/**
 * Created by daichi on 8/12/17.
 */

public class AssignmentCardAdapter extends CardAdapter {

    private final Context mContext;
    private ArrayList<Assignment> mAssignments;

    public AssignmentCardAdapter
            (@NonNull final Context context, @NonNull final ArrayList<Assignment> assignments) {
        mContext = context;
        mAssignments = assignments;
    }

    @Override
    public RecyclerView.LayoutManager getParentLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void insertData(@NonNull EntryObject content) {

    }

    @Override
    public MyCH.BaseCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card, parent, false);
        return new MyCH.AssignmentCardHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCH.BaseCardHolder holder, int position) {
        final int adpPos = holder.getAdapterPosition();
        ((MyCH.AssignmentCardHolder) holder).bind(adpPos, null, mAssignments.get(adpPos));
    }

    @Override
    public int getItemCount() {
        return mAssignments.size();
    }
}
