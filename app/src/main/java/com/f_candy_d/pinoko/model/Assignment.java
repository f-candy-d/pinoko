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

public class Assignment extends Entry<Assignment> implements Savable {

    public Assignment() {
        this(null);
    }

    public Assignment(final Bundle bundle) {
        super(DBContract.AssignmentEntry.TABLE_NAME, bundle);
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
    public String getTableName() {
        return DBContract.AssignmentEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.AssignmentEntry.getColumnNames()));
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.AssignmentEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AssignmentEntry.COL_NAME)) {
            setName(getDefaultStringValue());
        }
        if (!has(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID)) {
            setTimeBlockID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AssignmentEntry.COL_NOTE)) {
            setNote(getDefaultStringValue());
        }
        if (!has(DBContract.AssignmentEntry.COL_IS_DONE)) {
            setIsDone(getDefaultBoolValue());
        }
    }

    public Assignment setID(final int id) {
        return set(DBContract.AssignmentEntry._ID, id);
    }

    public Assignment setName(final String name) {
        return set(DBContract.AssignmentEntry.COL_NAME, name);
    }

    public Assignment setTimeBlockID(final int id) {
        return set(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID, id);
    }

    public Assignment setNote(final String note) {
        return set(DBContract.AssignmentEntry.COL_NOTE, note);
    }

    public Assignment setIsDone(final boolean isDone) {
        return set(DBContract.AssignmentEntry.COL_IS_DONE, isDone);
    }

    public int getID() {
        return getInt(DBContract.AssignmentEntry._ID);
    }

    public String getName() {
        return getString(DBContract.AssignmentEntry.COL_NAME);
    }

    public int getTimeBlockID() {
        return getInt(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID);
    }

    public String getNote() {
        return getString(DBContract.AssignmentEntry.COL_NOTE);
    }

    public boolean getIsDone() {
        return getBool(DBContract.AssignmentEntry.COL_IS_DONE);
    }
}
