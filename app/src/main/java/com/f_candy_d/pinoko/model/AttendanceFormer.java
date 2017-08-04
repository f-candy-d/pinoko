package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;

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
        EntryHelper.setAttendanceId(getEntry(), id);
        return this;
    }

    public AttendanceFormer setTimeBlockID(final long timeBlockID) {
        EntryHelper.setAttendanceTimeBlockId(getEntry(), timeBlockID);
        return this;
    }

    public AttendanceFormer setPresent(final int present) {
        EntryHelper.setAttendancePresent(getEntry(), present);
        return this;
    }

    public AttendanceFormer setLate(final int late) {
        EntryHelper.setAttendanceLate(getEntry(), late);
        return this;
    }

    public AttendanceFormer setAbsent(final int absent) {
        EntryHelper.setAttendanceAbsent(getEntry(), absent);
        return this;
    }

    public AttendanceFormer setNote(final String note) {
        EntryHelper.setAttendanceNote(getEntry(), note);
        return this;
    }

    public long getID() {
        return EntryHelper.getAttendanceId(getEntry(), DBContract.NULL_ID);
    }

    public long getTimeBlockID() {
        return EntryHelper.getAttendanceTimeBlockId(getEntry(), DBContract.NULL_ID);
    }

    public int getPresent() {
        return EntryHelper.getAttendancePresent(getEntry(), 0);
    }

    public int getLate() {
        return EntryHelper.getAttendanceLate(getEntry(), 0);
    }

    public int getAbsent() {
        return EntryHelper.getAttendanceAbsent(getEntry(), 0);
    }

    public String getNote() {
        return EntryHelper.getAttendanceNote(getEntry(), null);
    }
}
