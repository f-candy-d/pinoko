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
    public String getIdColumnName() {
        return DBContract.AssignmentEntry.ATTR_NAME;
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
            contentValues.put(DBContract.AssignmentEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.AssignmentEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID, getTimeBlockID());
        contentValues.put(DBContract.AssignmentEntry.ATTR_NOTE, getNote());
        contentValues.put(DBContract.AssignmentEntry.ATTR_IS_DONE, getIsDone());
        contentValues.put(DBContract.AssignmentEntry.ATTR_DEADLINE, getDeadline());

        return contentValues;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.AssignmentEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AssignmentEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID)) {
            setTimeBlockID(DBContract.NULL_ID);
        }
        if (!has(DBContract.AssignmentEntry.ATTR_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.AssignmentEntry.ATTR_IS_DONE)) {
            setIsDone(getEntry().getDefaultBoolValue());
        }
        if (!has(DBContract.AssignmentEntry.ATTR_DEADLINE)) {
            setDeadline(getEntry().getDefaultLongValue());
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
        EntryHelper.setAssignmentId(getEntry(), id);
        return this;
    }

    public AssignmentFormer setName(final String name) {
        EntryHelper.setAssignmentName(getEntry(), name);
        return this;
    }

    public AssignmentFormer setTimeBlockID(final long id) {
        EntryHelper.setAssignmentTimeBlockId(getEntry(), id);
        return this;
    }

    public AssignmentFormer setNote(final String note) {
        EntryHelper.setAssignmentNote(getEntry(), note);
        return this;
    }

    public AssignmentFormer setIsDone(final boolean isDone) {
        EntryHelper.setAssignmentIsDone(getEntry(), isDone);
        return this;
    }

    public AssignmentFormer setDeadline(final long deadline) {
        EntryHelper.setAssignmentDeadline(getEntry(), deadline);
        return this;
    }

    public long getID() {
        return EntryHelper.getAssignmentId(getEntry(), DBContract.NULL_ID);
    }

    public String getName() {
        return EntryHelper.getAssignmentName(getEntry(), null);
    }

    public long getTimeBlockID() {
        return EntryHelper.getAssignmentTimeBlockId(getEntry(), DBContract.NULL_ID);
    }

    public String getNote() {
        return EntryHelper.getAssignmentNote(getEntry(), null);
    }

    public boolean getIsDone() {
        return EntryHelper.getAssignmentIsDone(getEntry(), false);
    }

    public long getDeadline() {
        return EntryHelper.getAssignmentDeadline(getEntry(), 0);
    }
}
