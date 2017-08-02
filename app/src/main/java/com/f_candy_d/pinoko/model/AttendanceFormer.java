package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class AttendanceFormer extends EntryFormer {

    public AttendanceFormer() {
        this(null);
    }

    public AttendanceFormer(final Entry entry) {
        super(entry);
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
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.AttendanceEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID)) {
            setTimeBlockID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AttendanceEntry.COL_PRESENT)) {
            setPresent(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.COL_LATE)) {
            setLate(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.COL_ABSENT)) {
            setAbsent(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.COL_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public String getTableName() {
        return DBContract.AttendanceEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.AttendanceEntry.getColumnNames()));
    }

    public AttendanceFormer setID(final int id) {
        getEntry().set(DBContract.AttendanceEntry._ID, id);
        return this;
    }

    public AttendanceFormer setTimeBlockID(final int timeBlockID) {
        getEntry().set(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID, timeBlockID);
        return this;
    }

    public AttendanceFormer setPresent(final int present) {
        getEntry().set(DBContract.AttendanceEntry.COL_PRESENT, present);
        return this;
    }

    public AttendanceFormer setLate(final int late) {
        getEntry().set(DBContract.AttendanceEntry.COL_LATE, late);
        return this;
    }

    public AttendanceFormer setAbsent(final int absent) {
        getEntry().set(DBContract.AttendanceEntry.COL_ABSENT, absent);
        return this;
    }

    public AttendanceFormer setNote(final String note) {
        getEntry().set(DBContract.AttendanceEntry.COL_NOTE, note);
        return this;
    }

    public int getID() {
        return getEntry().getInt(DBContract.AttendanceEntry._ID);
    }

    public int getTimeBlockID() {
        return getEntry().getInt(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID);
    }

    public int getPresent() {
        return getEntry().getInt(DBContract.AttendanceEntry.COL_PRESENT);
    }

    public int getLate() {
        return getEntry().getInt(DBContract.AttendanceEntry.COL_LATE);
    }

    public int getAbsent() {
        return getEntry().getInt(DBContract.AttendanceEntry.COL_ABSENT);
    }

    public String getNote() {
        return getEntry().getString(DBContract.AttendanceEntry.COL_NOTE);
    }
}
