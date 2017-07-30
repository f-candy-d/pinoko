package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.Savable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class Attendance extends RestrictedEntry<Attendance> implements Savable {

    public Attendance() {
        this(null);
    }

    public Attendance(final Bundle bundle) {
        super(DBContract.AttendanceEntry.TABLE_NAME, bundle);
    }

    @Override
    public boolean isSavable() {
        return (DBContract.MIN_AVAILABLE_ID <= getTimeBlockID() &&
                0 <= getPresent() &&
                0 <= getLate() &&
                0 <= getAbsent());
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.AttendanceEntry._ID, getID());
        }
        contentValues.put(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID, getTimeBlockID());
        contentValues.put(DBContract.AttendanceEntry.COL_PRESENT, getPresent());
        contentValues.put(DBContract.AttendanceEntry.COL_LATE, getLate());
        contentValues.put(DBContract.AttendanceEntry.COL_ABSENT, getAbsent());
        contentValues.put(DBContract.AttendanceEntry.COL_NOTE, getNote());

        return contentValues;
    }

    @Override
    public String getTableName() {
        return DBContract.AttendanceEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.AttendanceEntry.getColumnNames()));
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.AttendanceEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID)) {
            setTimeBlockID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AttendanceEntry.COL_PRESENT)) {
            setPresent(getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.COL_LATE)) {
            setLate(getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.COL_ABSENT)) {
            setAbsent(getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.COL_NOTE)) {
            setNote(getDefaultStringValue());
        }
    }

    public Attendance setID(final int id) {
        return set(DBContract.AttendanceEntry._ID, id);
    }

    public Attendance setTimeBlockID(final int timeBlockID) {
        return set(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID, timeBlockID);
    }

    public Attendance setPresent(final int present) {
        return set(DBContract.AttendanceEntry.COL_PRESENT, present);
    }

    public Attendance setLate(final int late) {
        return set(DBContract.AttendanceEntry.COL_LATE, late);
    }

    public Attendance setAbsent(final int absent) {
        return set(DBContract.AttendanceEntry.COL_ABSENT, absent);
    }

    public Attendance setNote(final String note) {
        return set(DBContract.AttendanceEntry.COL_NOTE, note);
    }

    public int getID() {
        return getInt(DBContract.AttendanceEntry._ID);
    }

    public int getTimeBlockID() {
        return getInt(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID);
    }

    public int getPresent() {
        return getInt(DBContract.AttendanceEntry.COL_PRESENT);
    }

    public int getLate() {
        return getInt(DBContract.AttendanceEntry.COL_LATE);
    }

    public int getAbsent() {
        return getInt(DBContract.AttendanceEntry.COL_ABSENT);
    }

    public String getNote() {
        return getString(DBContract.AttendanceEntry.COL_NOTE);
    }
}
