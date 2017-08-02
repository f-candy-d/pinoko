package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class AssignmentFormer extends EntryFormer {

    public static AssignmentFormer createWithEntry() {
        return new AssignmentFormer(new Entry(DBContract.CourseEntry.TABLE_NAME));
    }

    public AssignmentFormer() {
        this(null);
    }

    public AssignmentFormer(final Entry entry) {
        super(entry);
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public boolean isSavable() {
        return (getName() != null);
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.AssignmentEntry._ID, getID());
        }
        contentValues.put(DBContract.AssignmentEntry.COL_NAME, getName());
        contentValues.put(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID, getTimeBlockID());
        contentValues.put(DBContract.AssignmentEntry.COL_NOTE, getNote());
        contentValues.put(DBContract.AssignmentEntry.COL_IS_DONE, getIsDone());

        return contentValues;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.AssignmentEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AssignmentEntry.COL_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID)) {
            setTimeBlockID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AssignmentEntry.COL_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.AssignmentEntry.COL_IS_DONE)) {
            setIsDone(getEntry().getDefaultBoolValue());
        }
    }

    @Override
    public String getTableName() {
        return DBContract.AssignmentEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.AssignmentEntry.getColumnNames()));
    }

    /**
     * Getters & Setters of Entry's attributes
     */
    public AssignmentFormer setID(final long id) {
        getEntry().set(DBContract.AssignmentEntry._ID, id);
        return this;
    }

    public AssignmentFormer setName(final String name) {
        getEntry().set(DBContract.AssignmentEntry.COL_NAME, name);
        return this;
    }

    public AssignmentFormer setTimeBlockID(final long id) {
        getEntry().set(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID, id);
        return this;
    }

    public AssignmentFormer setNote(final String note) {
        getEntry().set(DBContract.AssignmentEntry.COL_NOTE, note);
        return this;
    }

    public AssignmentFormer setIsDone(final boolean isDone) {
        getEntry().set(DBContract.AssignmentEntry.COL_IS_DONE, isDone);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.AssignmentEntry._ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.AssignmentEntry.COL_NAME);
    }

    public long getTimeBlockID() {
        return getEntry().getLong(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID);
    }

    public String getNote() {
        return getEntry().getString(DBContract.AssignmentEntry.COL_NOTE);
    }

    public boolean getIsDone() {
        return getEntry().getBool(DBContract.AssignmentEntry.COL_IS_DONE);
    }
}
