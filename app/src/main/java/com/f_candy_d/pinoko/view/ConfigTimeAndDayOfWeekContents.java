package com.f_candy_d.pinoko.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.f_candy_d.pinoko.R;

/**
 * Created by daichi on 8/11/17.
 *
 * This class depends on Pinoko/app/src/main/res/layout/config_contents_time_and_dayofweek.xml
 */

public class ConfigTimeAndDayOfWeekContents extends LayoutContents {

    private ConfigTimeContents mConfigTimeContents;
    private Button mDayOfWeekButton;

    public ConfigTimeAndDayOfWeekContents() {
        mConfigTimeContents = new ConfigTimeContents();
    }

    public ConfigTimeAndDayOfWeekContents(@NonNull final ViewGroup container) {
        super(container);
        mConfigTimeContents = new ConfigTimeContents();
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.config_contents_time_and_dayofweek;
    }

    @Override
    protected void initUI(@NonNull View view) {
        mConfigTimeContents.initUI(view);
        mDayOfWeekButton = (Button) view.findViewById(R.id.day_of_week_cctad);
    }

    @Override
    protected void onDeinflated() {
        mConfigTimeContents.onDeinflated();
        mDayOfWeekButton = null;
    }

    public Button getDayOfWeekButton() {
        return mDayOfWeekButton;
    }

    public Button getBeginTimeButton() {
        return mConfigTimeContents.getBeginTimeButton();
    }

    public Button getEndTimeButton() {
        return mConfigTimeContents.getEndTimeButton();
    }

    public ConfigTimeContents getConfigTimeContents() {
        return mConfigTimeContents;
    }
}
