package com.f_candy_d.pinoko.model;

import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.TimeBlockFormer;

/**
 * Created by daichi on 17/08/08.
 */

public class Event implements MergeableTimeBlock.RequiresInterface {

    public Event() {}

    public Event(@NonNull final Entry event) {

    }

    @Override
    public long getDatetimeBegin() {
        return 0;
    }

    @Override
    public long getDatetimeEnd() {
        return 0;
    }

    @Override
    public TimeBlockFormer.Category getCategory() {
        return TimeBlockFormer.Category.EVENT;
    }
}
