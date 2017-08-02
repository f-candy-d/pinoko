package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class CourseFormer extends EntryFormer {

    public CourseFormer() {
        this(null);
    }

    public CourseFormer(final Entry entry) {
        super(entry);
    }

    @Override
    public boolean isSavable() {
        return (getName() != null &&
                DBContract.MIN_AVAILABLE_ID <= getLocationIDA() &&
                DBContract.MIN_AVAILABLE_ID <= getInstructorIDA() &&
                0 <= getLength());
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.CourseEntry._ID, getID());
        }
        contentValues.put(DBContract.CourseEntry.COL_NAME, getName());
        contentValues.put(DBContract.CourseEntry.COL_LOCATION_ID_A, getLocationIDA());
        contentValues.put(DBContract.CourseEntry.COL_LOCATION_ID_B, getLocationIDB());
        contentValues.put(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A, getInstructorIDA());
        contentValues.put(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B, getInstructorIDB());
        contentValues.put(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C, getInstructorIDC());
        contentValues.put(DBContract.CourseEntry.COL_LENGTH, getLength());
        contentValues.put(DBContract.CourseEntry.COL_NOTE, getNote());

        return contentValues;
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public String getTableName() {
        return DBContract.CourseEntry.TABLE_NAME;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.CourseEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.CourseEntry.COL_LOCATION_ID_A)) {
            setLocationIDA(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_LOCATION_ID_B)) {
            setLocationIDB(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A)) {
            setInstructorIDA(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B)) {
            setInstructorIDB(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C)) {
            setInstructorIDC(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_LENGTH)) {
            setLength(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.CourseEntry.COL_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.CourseEntry.getColumnNames()));
    }

    public CourseFormer setID(final int id) {
        getEntry().set(DBContract.CourseEntry._ID, id);
        return this;
    }

    public CourseFormer setName(final String name) {
        getEntry().set(DBContract.CourseEntry.COL_NAME, name);
        return this;
    }

    public CourseFormer setLocationIDA(final int id) {
        getEntry().set(DBContract.CourseEntry.COL_LOCATION_ID_A, id);
        return this;
    }

    public CourseFormer setLocationIDB(final int id) {
        getEntry().set(DBContract.CourseEntry.COL_LOCATION_ID_B, id);
        return this;
    }

    public CourseFormer setInstructorIDA(final int id) {
        getEntry().set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A, id);
        return this;
    }

    public CourseFormer setInstructorIDB(final int id) {
        getEntry().set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B, id);
        return this;
    }

    public CourseFormer setInstructorIDC(final int id) {
        getEntry().set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C, id);
        return this;
    }

    public CourseFormer setLength(final int length) {
        getEntry().set(DBContract.CourseEntry.COL_LENGTH, length);
        return this;
    }

    public CourseFormer setNote(final String note) {
        getEntry().set(DBContract.CourseEntry.COL_NOTE, note);
        return this;
    }

    public int getID() {
        return getEntry().getInt(DBContract.CourseEntry._ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.CourseEntry.COL_NAME);
    }

    public int getLocationIDA() {
        return getEntry().getInt(DBContract.CourseEntry.COL_LOCATION_ID_A);
    }

    public int getLocationIDB() {
        return getEntry().getInt(DBContract.CourseEntry.COL_LOCATION_ID_B);
    }

    public int getInstructorIDA() {
        return getEntry().getInt(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A);
    }

    public int getInstructorIDB() {
        return getEntry().getInt(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B);
    }

    public int getInstructorIDC() {
        return getEntry().getInt(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C);
    }

    public int getLength() {
        return getEntry().getInt(DBContract.CourseEntry.COL_LENGTH);
    }

    public String getNote() {
        return getEntry().getString(DBContract.CourseEntry.COL_NOTE);
    }
}
