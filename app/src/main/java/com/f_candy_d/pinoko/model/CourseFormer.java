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

public class CourseFormer extends EntryFormer {

    public static CourseFormer createWithEntry() {
        return new CourseFormer(new Entry(DBContract.CourseEntry.TABLE_NAME));
    }

    public CourseFormer() {
        this(null);
    }

    public CourseFormer(final Entry entry) {
        super(entry);
    }

    @Override
    public boolean isSavable() {
        return (getName() != null &&
                0 <= getLength());
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.CourseEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.CourseEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.CourseEntry.ATTR_LOCATION_ID, getLocationId());
        contentValues.put(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID, getInstructorId());
        contentValues.put(DBContract.CourseEntry.ATTR_LENGTH, getLength());
        contentValues.put(DBContract.CourseEntry.ATTR_NOTE, getNote());

        return contentValues;
    }

    @Override
    public String getIdColumnName() {
        return DBContract.CourseEntry.ATTR_ID;
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
        if (!has(DBContract.CourseEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.CourseEntry.ATTR_LOCATION_ID)) {
            setLocationId(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID)) {
            setInstructorId(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_LENGTH)) {
            setLength(getEntry().getDefaultIntValue());
        }
        if (!has(DBContract.CourseEntry.ATTR_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.CourseEntry.getColumnNames()));
    }

    public CourseFormer setID(final long id) {
        EntryHelper.setCourseId(getEntry(), id);
        return this;
    }

    public CourseFormer setName(final String name) {
        EntryHelper.setCourseName(getEntry(), name);
        return this;
    }

    public CourseFormer setLocationId(final long id) {
        EntryHelper.setCourseLocationId(getEntry(), id);
        return this;
    }

    public CourseFormer setInstructorId(final long  id) {
        EntryHelper.setCourseInstructorId(getEntry(), id);
        return this;
    }

    public CourseFormer setLength(final int length) {
        EntryHelper.setCourseLength(getEntry(), length);
        return this;
    }

    public CourseFormer setNote(final String note) {
        EntryHelper.setCourseNote(getEntry(), note);
        return this;
    }

    public long getID() {
        return EntryHelper.getCourseId(getEntry(), DBContract.NULL_ID);
    }

    public String getName() {
        return EntryHelper.getCourseName(getEntry(), null);
    }

    public long getLocationId() {
        return EntryHelper.getCourseLocationId(getEntry(), DBContract.NULL_ID);
    }

    public long getInstructorId() {
        return EntryHelper.getCourseInstructorId(getEntry(), DBContract.NULL_ID);
    }

    public int getLength() {
        return EntryHelper.getCourseLength(getEntry(), 0);
    }

    public String getNote() {
        return EntryHelper.getCourseNote(getEntry(), null);
    }
}
