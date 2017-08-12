package com.f_candy_d.pinoko.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.Instructor;
import com.f_candy_d.pinoko.model.Location;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;

import java.util.ArrayList;

/**
 * Created by daichi on 8/12/17.
 */

public class InstructorPickerFragment extends DialogFragment {


    public interface EventListener {
        void onInstructorsSelected(final InstructorPickerFragment picker);
    }

    private static final String ARG_DEFAULT_SELECTIONS = "default_selections";

    private EventListener mEventListener = null;
    private ArrayList<Instructor> mInstructors;
    private ArrayList<Instructor> mSelected;
    private ArrayList<Instructor> mDefaultSelections;

    public static InstructorPickerFragment newInstance() {
        return newInstance(null);
    }

    public static InstructorPickerFragment newInstance(final ArrayList<Instructor> defaultSelections) {
        InstructorPickerFragment fragment = new InstructorPickerFragment();
        if (defaultSelections != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(ARG_DEFAULT_SELECTIONS, defaultSelections);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDefaultSelections = getArguments().getParcelableArrayList(ARG_DEFAULT_SELECTIONS);
        }

        if (mDefaultSelections == null) {
            mDefaultSelections = new ArrayList<>(0);
        }
        mInstructors = new ArrayList<>();
        mSelected = new ArrayList<>();
        mSelected.addAll(mDefaultSelections);
        init();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] itemNames = new String[mInstructors.size()];
        boolean[] selections = new boolean[mInstructors.size()];
        for (int i = 0; i < mInstructors.size(); ++i) {
            itemNames[i] = mInstructors.get(i).getName();
            selections[i] = mDefaultSelections.contains(mInstructors.get(i));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Instructors")
                .setMultiChoiceItems(itemNames, selections, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mSelected.add(mInstructors.get(which));
                        } else {
                            mSelected.remove(mInstructors.get(which));
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mEventListener != null) {
                            mEventListener.onInstructorsSelected(InstructorPickerFragment.this);
                        }
                    }
                });

        return builder.create();
    }

    @NonNull
    public ArrayList<Instructor> getSelectedLocations() {
        return mSelected;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    private void init() {
        DBDataManager dataManager = new DBDataManager(getActivity(), DBDataManager.Mode.READ);
        if (dataManager.isOpen()) {
            ArrayList<Entry> results = dataManager.selectAllOf(DBContract.InstructorEntry.TABLE_NAME);
            for (Entry entry : results) {
                mInstructors.add(new Instructor(entry));
            }
        }
    }
}
