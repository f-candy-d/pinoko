package com.f_candy_d.pinoko.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;

import java.util.ArrayList;

/**
 * Created by daichi on 8/10/17.
 */

public class CoursePickerFragment extends DialogFragment {

    public interface EventListener {
        void onCourseSelected(final CoursePickerFragment picker);
    }

    private EventListener mEventListener = null;
    private ArrayList<Course> mCourses;
    private int mSelectedIndex = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCourses = new ArrayList<>();
        init();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] itemNames = new String[mCourses.size()];
        for (int i = 0; i < mCourses.size(); ++i) {
            itemNames[i] = mCourses.get(i).getName();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a Course")
                .setItems(itemNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectedIndex = which;
                        if (mEventListener != null) {
                            mEventListener.onCourseSelected(CoursePickerFragment.this);
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }

    public Course getSelectedCourse() {
        if (mSelectedIndex < 0) {
            return null;
        } else {
            return mCourses.get(mSelectedIndex);
        }
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    private void init() {
        DBDataManager dataManager = new DBDataManager(getActivity(), DBDataManager.Mode.READ);
        if (dataManager.isOpen()) {
            ArrayList<Entry> results = dataManager.selectAllOf(DBContract.CourseEntry.TABLE_NAME);
            for (Entry entry : results) {
                mCourses.add(new Course(entry, getActivity()));
            }
        }
    }
}
