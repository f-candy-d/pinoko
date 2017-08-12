package com.f_candy_d.pinoko.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Assignment;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditAssignmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAssignmentFragment extends EditEntryObjectFragment<Assignment> {

    private enum FieldErrorCode {
        ASSIGNMENT_NAME_IS_EMPTY
    }

    private static final String ARG_TIME_TABLE_ID = "time_table_id";

    // UI
    private EditText mInputNameBox;
    private EditText mInputNoteBox;

    // Field
    private Calendar mDeadline;

    // Misc
    private Assignment mContent;
    private int mTimeTableId;

    public EditAssignmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment EditAssignmentFragment.
     */
    public static EditAssignmentFragment
    newInstance(final int timeTableId, final Assignment assignment) {
        EditAssignmentFragment fragment = new EditAssignmentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TIME_TABLE_ID, timeTableId);
        args.putParcelable(ARG_CONTENT, assignment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTimeTableId = getArguments().getInt(ARG_TIME_TABLE_ID);
            mContent = getArguments().getParcelable(ARG_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_assignment, container, false);
        init();
        initUI(view);
        return view;
    }

    private void init() {
        mDeadline = Calendar.getInstance();
        if (mContent == null) {
            mContent = new Assignment();
        } else {
            mDeadline.setTimeInMillis(mContent.getDeadline());
        }
    }

    private void initUI(@NonNull final View view) {
        mInputNameBox = (EditText) view.findViewById(R.id.input_assignment_name_box_fea);
        mInputNoteBox = (EditText) view.findViewById(R.id.input_assignment_note_box_fea);

        // Cancel button
        Button cancelButton = (Button) view.findViewById(R.id.cancel_button_fea);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(true);
            }
        });

        // OK button
        Button okButton = (Button) view.findViewById(R.id.ok_button_fea);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(false);
            }
        });

        // Select date button
        final Button selectDeadlineDateButton = (Button) view.findViewById(R.id.select_deadline_date_button_fea);
        selectDeadlineDateButton.setText(formatDate(
                mDeadline.get(Calendar.YEAR),
                mDeadline.get(Calendar.MONTH),
                mDeadline.get(Calendar.DAY_OF_MONTH)));
        selectDeadlineDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment pickerFragment = DatePickerFragment.newInstance(
                        mDeadline.get(Calendar.YEAR),
                        mDeadline.get(Calendar.MONTH),
                        mDeadline.get(Calendar.DAY_OF_MONTH));
                pickerFragment.setEventListener(new DatePickerFragment.EventListener() {
                    @Override
                    public void onDateSelected(DatePickerFragment picker) {
                        int y = picker.getYear();
                        int m = picker.getMonth();
                        int d = picker.getDay();
                        String message = formatDate(y, m, d);

                        mDeadline.set(Calendar.YEAR, y);
                        mDeadline.set(Calendar.MONTH, m);
                        mDeadline.set(Calendar.DAY_OF_MONTH, d);
                        selectDeadlineDateButton.setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select end time button
        final Button selectDeadlineTimeButton = (Button) view.findViewById(R.id.select_deadline_time_button_fea);
        selectDeadlineTimeButton.setText(formatTime(
                mDeadline.get(Calendar.HOUR_OF_DAY),
                mDeadline.get(Calendar.MINUTE)));
        selectDeadlineTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDeadline.get(Calendar.HOUR_OF_DAY),
                        mDeadline.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = formatTime(h, m);

                        mDeadline.set(Calendar.HOUR_OF_DAY, h);
                        mDeadline.set(Calendar.MINUTE, m);
                        selectDeadlineTimeButton.setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        final Button selectTbButton = (Button) view.findViewById(R.id.select_time_block_button_fea);
        selectTbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeBlockPickerFragment pickerFragment = TimeBlockPickerFragment.newInstance(mTimeTableId);
                pickerFragment.setEventListener(new TimeBlockPickerFragment.EventListener() {
                    @Override
                    public void onTimeBlockSelected(@NonNull TimeBlockPickerFragment picker) {
                        MergeableTimeBlock<?> selectedTimeBlock = picker.getSelectedTimeBlock();
                        if (selectedTimeBlock != null) {
                            mContent.setTimeBlockId(selectedTimeBlock.getId());
                            selectTbButton.setText("selected id is " + mContent.getId());
                        }
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });
    }

    private void finishEditing(final boolean isCanceled) {
        if (!isCanceled) {
            FieldErrorCode[] errorCodes = isFieldValid();
            if (errorCodes.length == 0) {
                mContent.setName(mInputNameBox.getText().toString());
                mContent.setNote(mInputNoteBox.getText().toString());
                mContent.setDeadline(mDeadline.getTimeInMillis());
                mContent.setDone(false);

                getMessageListener().onFinishEditing(mContent, false);

            } else {
                // TODO; When the field is invalid...
                Toast.makeText(getActivity(), "error # " + TextUtils.join(",", errorCodes), Toast.LENGTH_LONG).show();
            }

        } else {
            getMessageListener().onFinishEditing(mContent, true);
        }
    }

    private FieldErrorCode[] isFieldValid() {
        ArrayList<FieldErrorCode> errorCodes = new ArrayList<>();

        if (mInputNameBox.getText().toString().length() == 0) {
            errorCodes.add(FieldErrorCode.ASSIGNMENT_NAME_IS_EMPTY);
        }

        return errorCodes.toArray(new FieldErrorCode[]{});
    }

    /**
     * TODO; REMOVE THIS METHOD. THIS IS TEST CODE.
     */
    private String formatTime(int hour, int minute) {
        String h = String.valueOf(hour);
        String m = String.valueOf(minute);

        if (h.length() == 1) {
            h = "0" + h;
        }

        if (m.length() == 1) {
            m = "0" + m;
        }

        return h + " : " + m;
    }

    /**
     * TODO; REMOVE THIS METHOD. THIS IS TEST CODE.
     */
    private String formatDate(int year, int month, int day) {
        String y = String.valueOf(year);
        String m = String.valueOf(month);
        String d = String.valueOf(day);

        if (m.length() == 1) {
            m = "0" + m;
        }

        if (d.length() == 1) {
            d = "0" + d;
        }

        return y + "/" + m + "/" + d;
    }
}
