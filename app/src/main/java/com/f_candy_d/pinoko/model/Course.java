package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.Savable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class Course extends Entry<Course> implements Savable {

    public Course() {
        this(null, false);
    }

    public Course(final Bundle bundle) {
        this(bundle, false);
    }

    public Course(final Bundle bundle, final boolean copyOnlyReference) {
        super(DBContract.CourseEntry.TABLE_NAME, bundle, copyOnlyReference);
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.CourseEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.COL_NAME)) {
            setName(getDefaultStringValue());
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
            setLength(getDefaultIntValue());
        }
        if (!has(DBContract.CourseEntry.COL_NOTE)) {
            setNote(getDefaultStringValue());
        }
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
    public String getTableName() {
        return DBContract.CourseEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.CourseEntry.getColumnNames()));
    }

    public FlexibleEntry castTo() {
        return new FlexibleEntry(getAffiliation(), getAttributes(), true);
    }

    static public Course castFrom(@NonNull FlexibleEntry flexibleEntry) {
        return new Course(flexibleEntry.getAttributes(), true);
    }


    public Course setID(final int id) {
        return set(DBContract.CourseEntry._ID, id);
    }

    public Course setName(final String name) {
        return set(DBContract.CourseEntry.COL_NAME, name);
    }

    public Course setLocationIDA(final int id) {
        return set(DBContract.CourseEntry.COL_LOCATION_ID_A, id);
    }

    public Course setLocationIDB(final int id) {
        return set(DBContract.CourseEntry.COL_LOCATION_ID_B, id);
    }

    public Course setInstructorIDA(final int id) {
        return set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A, id);
    }

    public Course setInstructorIDB(final int id) {
        return set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B, id);
    }

    public Course setInstructorIDC(final int id) {
        return set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C, id);
    }

    public Course setLength(final int length) {
        return set(DBContract.CourseEntry.COL_LENGTH, length);
    }

    public Course setNote(final String note) {
        return set(DBContract.CourseEntry.COL_NOTE, note);
    }

    public int getID() {
        return getInt(DBContract.CourseEntry._ID);
    }

    public String getName() {
        return getString(DBContract.CourseEntry.COL_NAME);
    }

    public int getLocationIDA() {
        return getInt(DBContract.CourseEntry.COL_LOCATION_ID_A);
    }

    public int getLocationIDB() {
        return getInt(DBContract.CourseEntry.COL_LOCATION_ID_B);
    }

    public int getInstructorIDA() {
        return getInt(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A);
    }

    public int getInstructorIDB() {
        return getInt(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B);
    }

    public int getInstructorIDC() {
        return getInt(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C);
    }

    public int getLength() {
        return getInt(DBContract.CourseEntry.COL_LENGTH);
    }

    public String getNote() {
        return getString(DBContract.CourseEntry.COL_NOTE);
    }
}
