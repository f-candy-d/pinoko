package com.f_candy_d.pinoko.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Assignment;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;

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
     * Mini course time block card view holder
     */
    public static class MiniCourseTimeBlockCardHolder extends BaseCardHolder {

        public MiniCourseTimeBlockCardHolder(@NonNull final View view) {
            super(view);
        }
    }

    /**
     * Course time block card view holder
     */
    public static class CourseTimeBlockCardHolder extends BaseCardHolder {

        private TextView mTextView;

        public CourseTimeBlockCardHolder(@NonNull final View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.meta_data_ctbc);
        }

        public void bind(int position,
                         OnCardClickListener clickListener,
                         @NonNull MergeableTimeBlock<?> timeBlock) {

            super.bind(position, clickListener, timeBlock);
            Course course = MergeableTimeBlock.getCastedContent(timeBlock, Course.class);
            if (course != null) {
                String data = "id :: " + String.valueOf(course.getId()) + "\n"
                        + "name :: " + course.getName() + "\n"
                        + "note :: " + course.getNote() + "\n"
                        + "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "tb id :: " + String.valueOf(timeBlock.getId()) + "\n"
                        + "type :: " + timeBlock.getType().toString() + "\n"
                        + "day of week :: " + timeBlock.getDayOfWeek().toString() + "\n"
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
                         @NonNull MergeableTimeBlock<?> timeBlock) {

            super.bind(position, clickListener, timeBlock);
            Event event = MergeableTimeBlock.getCastedContent(timeBlock, Event.class);
            if (event != null) {
                String data = "id :: " + String.valueOf(event.getId()) + "\n"
                        + "name :: " + event.getName() + "\n"
                        + "note :: " + event.getNote() + "\n"
                        + "location :: " + event.getLocation().getName() + "\n"
                        + "tb id :: " + String.valueOf(timeBlock.getId()) + "\n"
                        + "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "begin time :: " + timeBlock.getDatetimeBeginAsString() + "\n"
                        + "type :: " + timeBlock.getType().toString() + "\n"
                        + "day of week :: " + timeBlock.getDayOfWeek().toString() + "\n"
                        + "end time :: " + timeBlock.getDatetimeEndAsString();
                mTextView.setText(data);
            } else {
                String data = "category :: " + timeBlock.getCategory().toString() + "\n"
                        + "begin time :: " + timeBlock.getDatetimeBeginAsString() + "\n"
                        + "type :: " + timeBlock.getType().toString() + "\n"
                        + "day of week :: " + timeBlock.getDayOfWeek().toString() + "\n"
                        + "end time :: " + timeBlock.getDatetimeEndAsString();
                mTextView.setText(data);
            }
        }
    }

    /**
     * Assignment card view holder
     */
    public static class AssignmentCardHolder extends BaseCardHolder {
        private TextView mTextView;

        public AssignmentCardHolder(@NonNull final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.meta_data_ac);
        }

        public void bind(final int position,
                         OnCardClickListener cardClickListener,
                         @NonNull Assignment assignment) {
            super.bind(position, cardClickListener, assignment);
            String data = "id :: " + String.valueOf(assignment.getId()) + "\n"
                    + "name :: " + assignment.getName() + "\n"
                    + "note :: " + assignment.getNote() + "\n"
                    + "tb id :: " + String.valueOf(assignment.getTimeBlockId()) + "\n"
                    + "deadline :: " + assignment.getDeadlineAsString()+ "\n"
                    + "is done :: " + String.valueOf(assignment.isDone());

            mTextView.setText(data);
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
                         @NonNull final Course course) {
            super.bind(position, clickListener, course);
            String data = "id :: " + String.valueOf(course.getId()) + "\n"
                    + "name :: " + course.getName() + "\n"
                    + "note :: " + course.getNote() + "\n";
            mTextView.setText(data);
        }
    }
}
