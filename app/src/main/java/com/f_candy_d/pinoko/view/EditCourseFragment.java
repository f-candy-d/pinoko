package com.f_candy_d.pinoko.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Instructor;
import com.f_candy_d.pinoko.model.Location;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCourseFragment extends EditEntryObjectFragment<Course> {

    private enum FieldErrorCode {
        COURSE_NAME_IS_EMPTY
    }

    // Content
    private Course mContent = null;

    // Fields
    private EditText mInputCourseNameBox;
    private EditText mInputCourseNoteBox;
    private ArrayList<Location> mLocations;
    private ArrayList<Instructor> mInstructors;

    // UI
    private Button mSelectLocationButton;
    private Button mSelectInstructorsButton;

    public EditCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditCourseFragment.
     */
    public static EditCourseFragment newInstance(final Course course) {
        EditCourseFragment fragment = new EditCourseFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTENT, course);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getParcelable(ARG_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_edit_course, container, false);
        initUI(view);
        init();
        return view;
    }

    private void init() {
        mInstructors = new ArrayList<>();
        mLocations = new ArrayList<>();

        if (mContent == null) {
            mContent = new Course();

        } else {
            mInputCourseNameBox.setText(mContent.getName());
            mInputCourseNameBox.setText(mContent.getNote());
            mInstructors.addAll(mContent.getInstructors());
            mLocations.addAll(mContent.getLocations());
        }

        invalidateField();
    }

    private void initUI(@NonNull final View view) {
        mInputCourseNameBox = (EditText) view.findViewById(R.id.input_course_name_box_fec);
        mInputCourseNoteBox = (EditText) view.findViewById(R.id.input_course_note_box_fec);

        final Button okButton = (Button) view.findViewById(R.id.ok_button_fec);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(false);
            }
        });

        final Button cancelBUtton = (Button) view.findViewById(R.id.cancel_button_fec);
        cancelBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(true);
            }
        });

        mSelectLocationButton = (Button) view.findViewById(R.id.select_locations_button_fec);
        mSelectLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationPickerFragment picker = LocationPickerFragment.newInstance(mLocations);
                picker.setEventListener(new LocationPickerFragment.EventListener() {
                    @Override
                    public void onLocationsSelected(LocationPickerFragment picker) {
                        mLocations = picker.getSelectedLocations();
                        invalidateField();
                    }
                });
                picker.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        mSelectInstructorsButton = (Button) view.findViewById(R.id.select_instructors_button_fec);
        mSelectInstructorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstructorPickerFragment picker = InstructorPickerFragment.newInstance(mInstructors);
                picker.setEventListener(new InstructorPickerFragment.EventListener() {
                    @Override
                    public void onInstructorsSelected(InstructorPickerFragment picker) {
                        mInstructors = picker.getSelectedLocations();
                        invalidateField();
                    }
                });
                picker.show(getActivity().getSupportFragmentManager(), null);
            }
        });
    }

    private void finishEditing(final boolean isCanceled) {
        if (!isCanceled) {
            FieldErrorCode[] errorCodes = isFieldValid();
            if (errorCodes.length == 0) {
                reflectFiledToContent();
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

        if (mInputCourseNameBox.getText().toString().length() == 0) {
            errorCodes.add(FieldErrorCode.COURSE_NAME_IS_EMPTY);
        }

        return errorCodes.toArray(new FieldErrorCode[]{});
    }

    private void invalidateField() {
        if (mLocations.size() != 0) {
            mSelectLocationButton.setText(mLocations.get(0).getName() + "...");
        } else {
            mSelectLocationButton.setText("Location is not selected");
        }

        if (mInstructors.size() != 0) {
            mSelectInstructorsButton.setText(mInstructors.get(0).getName() + "...");
        } else {
            mSelectInstructorsButton.setText("Instructor is not selected");
        }
    }

    private void reflectFiledToContent() {
        // Reflect field's data
        mContent.setNote(mInputCourseNoteBox.getText().toString());
        mContent.setName(mInputCourseNameBox.getText().toString());
        mContent.setLocations(mLocations);
        mContent.setInstructors(mInstructors);
    }
}
