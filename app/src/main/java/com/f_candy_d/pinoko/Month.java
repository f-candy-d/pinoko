package com.f_candy_d.pinoko;

import java.io.CharArrayReader;
import java.util.Calendar;

/**
 * Created by daichi on 8/9/17.
 */

public enum Month {
    JANUARY(Calendar.JANUARY),
    FEBRUARY(Calendar.FEBRUARY),
    MARCH(Calendar.MARCH),
    APRIL(Calendar.APRIL),
    MAY(Calendar.MAY),
    JUNE(Calendar.JUNE),
    JULY(Calendar.JULY),
    AUGUST(Calendar.AUGUST),
    SEPTEMBER(Calendar.SEPTEMBER),
    OCTOBAR(Calendar.OCTOBER),
    NOVEMBER(Calendar.NOVEMBER),
    DECEMBER(Calendar.DECEMBER);

    private final int mValue;

    Month(final int value) {
        mValue = value;
    }

    public int toInt() {
        return mValue;
    }

    public static Month from(final int value) {
        Month[] months = values();
        for (Month month : months) {
            if (month.toInt() == value) {
                return month;
            }
        }

        return null;
    }
}
