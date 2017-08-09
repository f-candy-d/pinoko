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
import com.f_candy_d.pinoko.model.Entry;
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
            for (Object content : mDayTimeTable.getTimeBlocks().get(position).getContents()) {
                if (content instanceof Course) {
                    courseCardHolder.bind(position, null, ((Course) content));
                }
            }

        } else if (viewType == TimeBlockFormer.Category.EVENT.toInt()) {
            MyCH.EventCardHolder eventCardHolder = (MyCH.EventCardHolder) holder;
            for (Object content : mDayTimeTable.getTimeBlocks().get(position).getContents()) {
                if (content instanceof Event) {
                    eventCardHolder.bind(position, null, ((Event) content));
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mDayTimeTable.getTimeBlocks().size();
    }
}
