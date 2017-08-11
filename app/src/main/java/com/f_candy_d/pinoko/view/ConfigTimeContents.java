package com.f_candy_d.pinoko.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.f_candy_d.pinoko.R;

/**
 * Created by daichi on 8/11/17.
 *
 * This class depends on Pinoko/app/src/main/res/layout/config_contents_time.xml.
 */

public class ConfigTimeContents extends LayoutContents {

    // UI
    private Button mBeginTimeButton;
    private Button mEndTimeButton;
    public ConfigTimeContents() {}

    public ConfigTimeContents(@NonNull final ViewGroup container) {
        super(container);
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.config_contents_time;
    }

    @Override
    protected void initUI(@NonNull View view) {
        mBeginTimeButton = (Button) view.findViewById(R.id.time_begin_cct);
        mEndTimeButton = (Button) view.findViewById(R.id.time_end_cct);
    }

    public Button getBeginTimeButton() {
        return mBeginTimeButton;
    }

    public Button getEndTimeButton() {
        return mEndTimeButton;
    }

    @Override
    protected void onDeinflated() {
        mBeginTimeButton = null;
        mEndTimeButton = null;
    }
}
