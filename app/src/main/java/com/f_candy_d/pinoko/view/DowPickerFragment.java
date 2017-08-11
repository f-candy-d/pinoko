package com.f_candy_d.pinoko.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.f_candy_d.pinoko.DayOfWeek;

/**
 * Created by daichi on 8/11/17.
 */

public class DowPickerFragment extends DialogFragment {

    public interface EventListener {
        void onDayOfWeekSelected(final DowPickerFragment picker);
    }

    private static final String ARG_DEFAULT_DOW = "default_dow";

    private DayOfWeek mSelectedDow;
    private EventListener mEventListener;

    static public DowPickerFragment newInstance(@NonNull final DayOfWeek defaultDow) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DEFAULT_DOW, defaultDow);
        DowPickerFragment fragment = new DowPickerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public DowPickerFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSelectedDow = (DayOfWeek) getArguments().getSerializable(ARG_DEFAULT_DOW);
        }
    }

    public DayOfWeek getSelectedDayOfWeek() {
        return mSelectedDow;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        // Except NULL_VALUE
        String[] strings = new String[dayOfWeeks.length - 1];
        for (int i = 1; i < dayOfWeeks.length; ++i) {
            strings[i - 1] = dayOfWeeks[i].toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a day of the week")
                .setSingleChoiceItems(strings, mSelectedDow.ordinal() - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectedDow = dayOfWeeks[which + 1];
                    }
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mEventListener != null) {
                            mEventListener.onDayOfWeekSelected(DowPickerFragment.this);
                        }
                    }
                });

        return builder.create();
    }
}
