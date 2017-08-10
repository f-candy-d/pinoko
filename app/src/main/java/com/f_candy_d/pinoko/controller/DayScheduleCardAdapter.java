package com.f_candy_d.pinoko.controller;

import android.content.Context;
import android.opengl.ETC1Util;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.model.OneDayTimeTable;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;
import com.f_candy_d.pinoko.view.MyCH;

/**
 * Created by daichi on 8/9/17.
 */

public class DayScheduleCardAdapter extends CardAdapter {

    private final Context mContext;
    private final OneDayTimeTable mDayTimeTable;

    public DayScheduleCardAdapter(@NonNull final OneDayTimeTable dayTimeTable,
                                  @NonNull final Context context) {
        mContext = context;
        mDayTimeTable = dayTimeTable;
    }

    @Override
    public RecyclerView.LayoutManager getParentLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        return mDayTimeTable.getTimeBlocks().get(position).getCategory().toInt();
    }

    @Override
    public MyCH.BaseCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TimeBlockFormer.Category.COURSE.toInt()) {
            View view = inflater.inflate(R.layout.course_card, parent, false);
            return new MyCH.CourseCardHolder(view);

        } else if (viewType == TimeBlockFormer.Category.EVENT.toInt()) {
            View view = inflater.inflate(R.layout.event_card, parent, false);
            return new MyCH.EventCardHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyCH.BaseCardHolder holder, int position) {
        final int viewType = getItemViewType(position);
        if (viewType == TimeBlockFormer.Category.COURSE.toInt()) {
            MyCH.CourseCardHolder courseCardHolder = (MyCH.CourseCardHolder) holder;
            if (mDayTimeTable.getTimeBlocks().get(position).hasContent()) {
                if (mDayTimeTable.getTimeBlocks().get(position).getContent() instanceof Course) {
                    courseCardHolder.bind(position, null, mDayTimeTable.getTimeBlocks().get(position));
                }
            }

        } else if (viewType == TimeBlockFormer.Category.EVENT.toInt()) {
            MyCH.EventCardHolder eventCardHolder = (MyCH.EventCardHolder) holder;
            if (mDayTimeTable.getTimeBlocks().get(position).hasContent()) {
                if (mDayTimeTable.getTimeBlocks().get(position).getContent() instanceof Event) {
                    eventCardHolder.bind(position, null, mDayTimeTable.getTimeBlocks().get(position));
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mDayTimeTable.getTimeBlocks().size();
    }

    public OneDayTimeTable getDayTimeTable() {
        return mDayTimeTable;
    }

    public void insertNewTimeBlock(@NonNull final MergeableTimeBlock timeBlock) {
        int index = mDayTimeTable.getTimeBlocks().indexOf(timeBlock);
        // Check whether the object is stored or not
        if (index == -1) {
            index = mDayTimeTable.addTimeBlock(timeBlock);
            notifyItemInserted(index);
            Log.d("mylog", "insertNewTimeBlock()");
        }
    }
}
