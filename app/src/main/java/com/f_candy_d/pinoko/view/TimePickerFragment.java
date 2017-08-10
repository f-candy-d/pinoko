package com.f_candy_d.pinoko.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by daichi on 8/10/17.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public interface EventListener {
        void onTimeSelected(final TimePickerFragment picker);
    }

    private static final String ARG_HOUR = "hour";
    private static final String ARG_MINUTE = "minute";
    private static final String ARG_IS_24HOUR_FORMAT = "is24HourFormat";

    private int mHour;
    private int mMinute;
    private boolean mIs24HourFormat;
    private EventListener mEventListener = null;

    public static TimePickerFragment newInstance(final int hour, final int minute, final boolean is24HourFormat) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_HOUR, hour);
        bundle.putInt(ARG_MINUTE, minute);
        bundle.putBoolean(ARG_IS_24HOUR_FORMAT, is24HourFormat);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public static TimePickerFragment newInstance(final boolean is24HourFormat) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_IS_24HOUR_FORMAT, is24HourFormat);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (getArguments() != null) {
            mHour = getArguments().getInt(ARG_HOUR, -1);
            mMinute = getArguments().getInt(ARG_MINUTE, -1);
            mIs24HourFormat = getArguments().getBoolean(ARG_IS_24HOUR_FORMAT, true);
        }

        if (mHour < 0 || mMinute < 0) {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, mHour, mMinute, mIs24HourFormat);
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    public int getHour() {
        return mHour;
    }

    public int getMinute() {
        return mMinute;
    }

    /**
     * region; TimePickerDialog.OnTimeSetListener implementation
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;

        if (mEventListener != null) {
            mEventListener.onTimeSelected(TimePickerFragment.this);
        }
    }
}
