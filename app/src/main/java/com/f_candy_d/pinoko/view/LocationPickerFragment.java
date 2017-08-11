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
import com.f_candy_d.pinoko.model.Location;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by daichi on 8/12/17.
 */

public class LocationPickerFragment extends DialogFragment {

    public interface EventListener {
        void onLocationsSelected(final LocationPickerFragment picker);
    }

    private static final String ARG_DEFAULT_SELECTIONS = "default_selections";

    private EventListener mEventListener = null;
    private ArrayList<Location> mLocations;
    private ArrayList<Location> mSelected;
    private ArrayList<Location> mDefaultSelections;

    public static LocationPickerFragment newInstance() {
        return newInstance(null);
    }

    public static LocationPickerFragment newInstance(final ArrayList<Location> defaultSelections) {
        LocationPickerFragment fragment = new LocationPickerFragment();
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
        mLocations = new ArrayList<>();
        mSelected = new ArrayList<>();
        mSelected.addAll(mDefaultSelections);
        init();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] itemNames = new String[mLocations.size()];
        boolean[] selections = new boolean[mLocations.size()];
        for (int i = 0; i < mLocations.size(); ++i) {
            itemNames[i] = mLocations.get(i).getName();
            selections[i] = mDefaultSelections.contains(mLocations.get(i));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a Course")
                .setMultiChoiceItems(itemNames, selections, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mSelected.add(mLocations.get(which));
                        } else {
                            mSelected.remove(mLocations.get(which));
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mEventListener != null) {
                            mEventListener.onLocationsSelected(LocationPickerFragment.this);
                        }
                    }
                });

        return builder.create();
    }

    public ArrayList<Location> getSelectedLocations() {
        return mSelected;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    private void init() {
        DBDataManager dataManager = new DBDataManager(getActivity(), DBDataManager.Mode.READ);
        if (dataManager.isOpen()) {
            ArrayList<Entry> results = dataManager.selectAllOf(DBContract.LocationEntry.TABLE_NAME);
            for (Entry entry : results) {
                mLocations.add(new Location(entry));
            }
        }
    }
}
