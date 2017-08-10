package com.f_candy_d.pinoko.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by daichi on 8/10/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface EventListener {
        void onDateSelected(final DatePickerFragment picker);
    }

    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";

    private int mYear;
    private int mMonth;
    private int mDay;
    private EventListener mEventListener = null;

    public static DatePickerFragment newInstance(final int year, final int month, final int day) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_YEAR, year);
        bundle.putInt(ARG_MONTH, month);
        bundle.putInt(ARG_DAY, day);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public static DatePickerFragment newInstance() {
        return new DatePickerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }

    public int getDayOfWeek() {
        return getAsCalendar().get(Calendar.DAY_OF_WEEK);
    }

    public Calendar getAsCalendar() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay);
        return calendar;
    }

    private void init() {
        if (getArguments() != null) {
            mYear = getArguments().getInt(ARG_YEAR);
            mMonth = getArguments().getInt(ARG_MONTH);
            mDay = getArguments().getInt(ARG_DAY);
        } else {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
        }
    }

    /**
     * region; DatePickerDialog.OnDateSetListener implementation
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mYear = year;
        mMonth = month;
        mDay = dayOfMonth;

        if (mEventListener != null) {
            mEventListener.onDateSelected(DatePickerFragment.this);
        }
    }
}
