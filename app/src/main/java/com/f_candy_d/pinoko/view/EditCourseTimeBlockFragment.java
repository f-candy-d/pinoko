package com.f_candy_d.pinoko.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditCourseTimeBlockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCourseTimeBlockFragment extends Fragment {

    public interface MessageListener {
        void onFinishEditing(final MergeableTimeBlock<Course> timeBlock, final boolean isCanceled);
    }

    private static final String ARG_CONTENT = "content";
    private static final String ARG_TIME_TABLE_ID = "timeTableId";

    // UI
    private EditText mNote;

    // Misc
    private int mTimeTableId;
    private TimeBlockFormer.Type mType;
    private Calendar mDateBegin;
    private Calendar mDateEnd;
    private Course mCourse;
    @Nullable private MergeableTimeBlock<Course> mContent;
    private MessageListener mMessageListener = null;

    public EditCourseTimeBlockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditCourseTimeBlockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCourseTimeBlockFragment newInstance(final int timeTableId,
                                                          final MergeableTimeBlock<Course> timeBlock) {
        EditCourseTimeBlockFragment fragment = new EditCourseTimeBlockFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTENT, timeBlock);
        args.putInt(ARG_TIME_TABLE_ID, timeTableId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getParcelable(ARG_CONTENT);
            mTimeTableId = getArguments().getInt(ARG_TIME_TABLE_ID);
        }

        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_course_time_block, container, false);
        init();
        initUI(view);

        return view;
    }

    public void setMessageListener(MessageListener messageListener) {
        mMessageListener = messageListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditCourseTimeBlockFragment.MessageListener) {
            mMessageListener = (EditCourseTimeBlockFragment.MessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement EditCourseTimeBlockFragment.MessageListener");
        }
    }

    private void init() {
        mType = TimeBlockFormer.Type.NULL_TYPE;
        mDateBegin = Calendar.getInstance();
        mDateEnd = Calendar.getInstance();
    }

    private void initUI(@NonNull final View view) {
        // Select time-block type button
        final Button typeBUtton = (Button) view.findViewById(R.id.type_fectb);
        typeBUtton.setText(mType.toString());
        typeBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTimeBlockTypeDialogFragment dialogFragment =
                        SelectTimeBlockTypeDialogFragment.newInstance(mType);
                dialogFragment.setEventListener(new SelectTimeBlockTypeDialogFragment.EventListener() {
                    @Override
                    public void onNegativeButtonClick(SelectTimeBlockTypeDialogFragment dialog) {

                    }

                    @Override
                    public void onPositivbeButtonClick(SelectTimeBlockTypeDialogFragment dialog) {
                        mType = dialog.getSelectedType();
                        typeBUtton.setText(mType.toString());
                    }
                });
                dialogFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select begin date button
        final Button dateBeginButton = (Button) view.findViewById(R.id.date_begin_fectb);
        dateBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment pickerFragment = DatePickerFragment.newInstance(
                        mDateBegin.get(Calendar.YEAR),
                        mDateBegin.get(Calendar.MONTH),
                        mDateBegin.get(Calendar.DAY_OF_MONTH));
                pickerFragment.setEventListener(new DatePickerFragment.EventListener() {
                    @Override
                    public void onDateSelected(DatePickerFragment picker) {
                        int y = picker.getYear();
                        int m = picker.getMonth();
                        int d = picker.getDay();
                        String message = "[BEGIN] " + String.valueOf(y) + " - "
                                + String.valueOf(m) + " - "
                                + String.valueOf(d);

                        mDateBegin.set(Calendar.YEAR, y);
                        mDateBegin.set(Calendar.MONTH, m);
                        mDateBegin.set(Calendar.DAY_OF_MONTH, d);

                        dateBeginButton.setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select begin time button
        final Button timeBeginButton = (Button) view.findViewById(R.id.time_begin_fectb);
        timeBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDateBegin.get(Calendar.HOUR_OF_DAY),
                        mDateBegin.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = "[BEGIN] " + String.valueOf(h) + " : "
                                + String.valueOf(m);

                        mDateBegin.set(Calendar.HOUR_OF_DAY, h);
                        mDateBegin.set(Calendar.MINUTE, m);
                        timeBeginButton.setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select end date button
        final Button dateEndButton = (Button) view.findViewById(R.id.date_end_fectb);
        dateEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment pickerFragment = DatePickerFragment.newInstance(
                        mDateEnd.get(Calendar.YEAR),
                        mDateEnd.get(Calendar.MONTH),
                        mDateEnd.get(Calendar.DAY_OF_MONTH));
                pickerFragment.setEventListener(new DatePickerFragment.EventListener() {
                    @Override
                    public void onDateSelected(DatePickerFragment picker) {
                        int y = picker.getYear();
                        int m = picker.getMonth();
                        int d = picker.getDay();
                        String message = "[END] " + String.valueOf(y) + " - "
                                + String.valueOf(m) + " - "
                                + String.valueOf(d);

                        mDateEnd.set(Calendar.YEAR, y);
                        mDateEnd.set(Calendar.MONTH, m);
                        mDateEnd.set(Calendar.DAY_OF_MONTH, d);
                        dateEndButton.setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select end time button
        final Button timeEndButton = (Button) view.findViewById(R.id.time_end_fectb);
        timeEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDateEnd.get(Calendar.HOUR_OF_DAY),
                        mDateEnd.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = "[END] " + String.valueOf(h) + " : "
                                + String.valueOf(m);

                        mDateEnd.set(Calendar.HOUR_OF_DAY, h);
                        mDateEnd.set(Calendar.MINUTE, m);
                        timeEndButton.setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select course button
        final Button courseButton = (Button) view.findViewById(R.id.course_fectb);
        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoursePickerFragment pickerFragment = new CoursePickerFragment();
                pickerFragment.setEventListener(new CoursePickerFragment.EventListener() {
                    @Override
                    public void onCourseSelected(CoursePickerFragment picker) {
                        courseButton.setText(picker.getSelectedCourse().getName());
                        mCourse = picker.getSelectedCourse();
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Cancel button
        Button cancelButton = (Button) view.findViewById(R.id.cancel_button_fectb);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(true);
            }
        });

        // OK button
        Button okButton = (Button) view.findViewById(R.id.ok_button_fectb);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(false);
            }
        });

        // Edit note
        mNote = (EditText) view.findViewById(R.id.note_fectb);
    }

    private void finishEditing(final boolean isCanceled) {
        if (!isCanceled && isFieldValid()) {
            if (isFieldValid()) {
                if (mContent == null) {
                    mContent = new MergeableTimeBlock<>();
                }

                mContent.setDatetimeBegin(mDateBegin.getTimeInMillis());
                mContent.setDatetimeEnd(mDateEnd.getTimeInMillis());
                mContent.setCategory(TimeBlockFormer.Category.COURSE);
                mContent.setType(mType);
                mContent.setDayOfWeek(DayOfWeek.from(mDateBegin.get(Calendar.DAY_OF_WEEK)));
                mContent.setTargetId(mCourse.getId());
                mContent.setContent(mCourse);
                mContent.setTimeTableId(mTimeTableId);
            } else {
                // TODO; When the field is invalid...
            }
        }

        mMessageListener.onFinishEditing(mContent, isCanceled);
    }

    private boolean isFieldValid() {
        return true;
    }
}
