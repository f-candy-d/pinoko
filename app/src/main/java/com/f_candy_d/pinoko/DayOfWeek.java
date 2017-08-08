package com.f_candy_d.pinoko;

import java.util.Calendar;

/**
 * Created by daichi on 17/08/08.
 */

public enum DayOfWeek {

    NULL_VALUE(-1),
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY),
    SUNDAY(Calendar.SUNDAY);

    private final int mValue;

    DayOfWeek(final int value) {
        mValue = value;
    }

    public int toInt() {
        return mValue;
    }

    public static DayOfWeek from(final int value) {
        DayOfWeek[] dayOfWeeks = values();
        for (DayOfWeek dayOfWeek : dayOfWeeks) {
            if (dayOfWeek.toInt() == value) {
                return dayOfWeek;
            }
        }

        return NULL_VALUE;
    }
}
