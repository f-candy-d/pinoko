package com.f_candy_d.pinoko.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.f_candy_d.pinoko.R;

/**
 * Created by daichi on 8/11/17.
 *
 * This class depends on Pinoko/app/src/main/res/layout/config_contents_date_and_time.xml.
 */

public class ConfigDateAndTimeContents extends LayoutContents {

    // UI
    private Button mBeginDateButton;
    private Button mEndDateButton;
    private Button mBeginTimeButton;
    private Button mEndTimeButton;

    public ConfigDateAndTimeContents() {}

    public ConfigDateAndTimeContents(@NonNull final ViewGroup container) {
        super(container);
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.config_contents_date_and_time;
    }

    @Override
    protected void initUI(@NonNull View view) {
        mBeginDateButton = (Button) view.findViewById(R.id.date_begin_ccdt);
        mEndDateButton = (Button) view.findViewById(R.id.date_end_ccdt);
        mBeginTimeButton = (Button) view.findViewById(R.id.time_begin_ccdt);
        mEndTimeButton = (Button) view.findViewById(R.id.time_end_ccdt);
    }

    public Button getBeginDateButton() {
        return mBeginDateButton;
    }

    public Button getEndDateButton() {
        return mEndDateButton;
    }

    public Button getBeginTimeButton() {
        return mBeginTimeButton;
    }

    public Button getEndTimeButton() {
        return mEndTimeButton;
    }

    @Override
    protected void onDeinflated() {
        mBeginDateButton = null;
        mEndDateButton = null;
        mBeginTimeButton = null;
        mEndTimeButton = null;
    }
}
