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

    public static AttendanceFormer createWithEntry() {
        return new AttendanceFormer(new Entry(DBContract.AttendanceEntry.TABLE_NAME));
    }

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
            contentValues.put(DBContract.AttendanceEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID, getTimeBlockID());
        contentValues.put(DBContract.AttendanceEntry.ATTR_PRESENT, getPresent());
        contentValues.put(DBContract.AttendanceEntry.ATTR_LATE, getLate());
        contentValues.put(DBContract.AttendanceEntry.ATTR_ABSENT, getAbsent());
        contentValues.put(DBContract.AttendanceEntry.ATTR_NOTE, getNote());

        return contentValues;
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.AttendanceEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID)) {
            setTimeBlockID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AttendanceEntry.ATTR_PRESENT)) {
            setPresent(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.ATTR_LATE)) {
            setLate(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.ATTR_ABSENT)) {
            setAbsent(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.AttendanceEntry.ATTR_NOTE)) {
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

    public AttendanceFormer setID(final long id) {
        getEntry().set(DBContract.AttendanceEntry.ATTR_ID, id);
        return this;
    }

    public AttendanceFormer setTimeBlockID(final long timeBlockID) {
        getEntry().set(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID, timeBlockID);
        return this;
    }

    public AttendanceFormer setPresent(final int present) {
        getEntry().set(DBContract.AttendanceEntry.ATTR_PRESENT, present);
        return this;
    }

    public AttendanceFormer setLate(final int late) {
        getEntry().set(DBContract.AttendanceEntry.ATTR_LATE, late);
        return this;
    }

    public AttendanceFormer setAbsent(final int absent) {
        getEntry().set(DBContract.AttendanceEntry.ATTR_ABSENT, absent);
        return this;
    }

    public AttendanceFormer setNote(final String note) {
        getEntry().set(DBContract.AttendanceEntry.ATTR_NOTE, note);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.AttendanceEntry.ATTR_ID);
    }

    public long getTimeBlockID() {
        return getEntry().getLong(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID);
    }

    public int getPresent() {
        return getEntry().getInt(DBContract.AttendanceEntry.ATTR_PRESENT);
    }

    public int getLate() {
        return getEntry().getInt(DBContract.AttendanceEntry.ATTR_LATE);
    }

    public int getAbsent() {
        return getEntry().getInt(DBContract.AttendanceEntry.ATTR_ABSENT);
    }

    public String getNote() {
        return getEntry().getString(DBContract.AttendanceEntry.ATTR_NOTE);
    }
}
