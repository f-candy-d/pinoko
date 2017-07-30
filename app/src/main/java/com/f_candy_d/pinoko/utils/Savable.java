package com.f_candy_d.pinoko.utils;

import android.content.ContentValues;

/**
 * Created by daichi on 7/30/17.
 *
 * A class which implements this interface has to ensure that it is able to be saved/inserted
 * to the SQLite DataBase.
 */

public interface Savable {
    boolean isSavable();
    ContentValues toContentValues(final boolean withId);
}
