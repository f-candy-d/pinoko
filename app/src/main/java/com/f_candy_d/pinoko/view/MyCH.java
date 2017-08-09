package com.f_candy_d.pinoko.view;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Calendar;

/**
 * Created by daichi on 17/08/07.
 */

public class MyCH {

    public interface OnCardClickListener {
        void onCardClick(final int position, final EntryObject entryObject);
    }

    private MyCH() {}

    /**
     * Base view holder
     */
    public static class BaseCardHolder extends RecyclerView.ViewHolder {

        private OnCardClickListener mOnCardClickListener = null;
        private View mItemView = null;

        public BaseCardHolder(@NonNull final View view) {
            super(view);
            mItemView = view;
        }

        public void bind(final int position,
                         final OnCardClickListener clickListener,
                         final EntryObject entryObject) {
            mOnCardClickListener = clickListener;
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCardClickListener != null) {
                        mOnCardClickListener.onCardClick(position, entryObject);
                    }
                }
            });
        }

        public void bind(final int position, final OnCardClickListener clickListener) {
            bind(position, clickListener, null);
        }
    }

    /**
     * Mini course card view holder
     */
    public static class MiniCourseCardHolder extends BaseCardHolder {

        public MiniCourseCardHolder(@NonNull final View view) {
            super(view);
        }
    }

    /**
     * Course card view holder
     */
    public static class CourseCardHolder extends BaseCardHolder {

        private TextView mTextView;

        public CourseCardHolder(@NonNull final View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.meta_data_cc);
        }

        public void bind(int position,
                         OnCardClickListener clickListener,
                         MergeableTimeBlock<Course> timeBlock) {

            super.bind(position, clickListener, timeBlock);
            Course course = timeBlock.getContent();
            if (course != null) {
                String data = "id :: " + String.valueOf(course.getId()) + "\n"
                        + "name :: " + course.getName() + "\n"
                        + "note :: " + course.getNote() + "\n"
                        + "length :: " + String.valueOf(course.getLength()) + "\n"
                        + "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "begin time :: " + timeBlock.getDatetimeBeginAsString() + "\n"
                        + "end time :: " + timeBlock.getDatetimeEndAsString();
                mTextView.setText(data);
            } else {
                String data = "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "begin time :: " + timeBlock.getDatetimeBeginAsString() + "\n"
                        + "end time :: " + timeBlock.getDatetimeEndAsString();
                mTextView.setText(data);
            }
        }
    }

    /**
     * Event card view holder
     */
    public static class EventCardHolder extends BaseCardHolder {
        private TextView mTextView;

        public EventCardHolder(@NonNull final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.meta_data_ec);
        }

        public void bind(int position,
                         OnCardClickListener clickListener,
                         MergeableTimeBlock<Event> timeBlock) {

            super.bind(position, clickListener, timeBlock);
            Event event = timeBlock.getContent();
            if (event != null) {
                String data = "id :: " + String.valueOf(event.getId()) + "\n"
                        + "name :: " + event.getName() + "\n"
                        + "note :: " + event.getNote() + "\n"
                        + "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "begin time :: " + timeBlock.getDatetimeBeginAsString() + "\n"
                        + "end time :: " + timeBlock.getDatetimeEndAsString();
                mTextView.setText(data);
            } else {
                String data = "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "begin time :: " + timeBlock.getDatetimeBeginAsString() + "\n"
                        + "end time :: " + timeBlock.getDatetimeEndAsString();
                mTextView.setText(data);
            }
        }

        @Override
        public void bind(int position, OnCardClickListener clickListener) {
            bind(position, clickListener, null);
        }
    }
}
