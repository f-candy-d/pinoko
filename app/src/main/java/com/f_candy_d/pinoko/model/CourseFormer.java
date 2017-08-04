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
                DBContract.MIN_AVAILABLE_ID <= getLocationIDA() &&
                DBContract.MIN_AVAILABLE_ID <= getInstructorIDA() &&
                0 <= getLength());
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.CourseEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.CourseEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.CourseEntry.ATTR_LOCATION_ID_A, getLocationIDA());
        contentValues.put(DBContract.CourseEntry.ATTR_LOCATION_ID_B, getLocationIDB());
        contentValues.put(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_A, getInstructorIDA());
        contentValues.put(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_B, getInstructorIDB());
        contentValues.put(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_C, getInstructorIDC());
        contentValues.put(DBContract.CourseEntry.ATTR_LENGTH, getLength());
        contentValues.put(DBContract.CourseEntry.ATTR_NOTE, getNote());

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
        if (!has(DBContract.CourseEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.CourseEntry.ATTR_LOCATION_ID_A)) {
            setLocationIDA(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_LOCATION_ID_B)) {
            setLocationIDB(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_A)) {
            setInstructorIDA(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_B)) {
            setInstructorIDB(DBContract.NULL_ID);
        }
        if (!has(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_C)) {
            setInstructorIDC(DBContract.NULL_ID);
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

    public CourseFormer setLocationIDA(final long id) {
        EntryHelper.setCourseLocationIdA(getEntry(), id);
        return this;
    }

    public CourseFormer setLocationIDB(final long id) {
        EntryHelper.setCourseLocationIdB(getEntry(), id);
        return this;
    }

    public CourseFormer setInstructorIDA(final long  id) {
        EntryHelper.setCourseInstructorIdA(getEntry(), id);
        return this;
    }

    public CourseFormer setInstructorIDB(final long id) {
        EntryHelper.setCourseInstructorIdB(getEntry(), id);
        return this;
    }

    public CourseFormer setInstructorIDC(final long id) {
        EntryHelper.setCourseInstructorIdC(getEntry(), id);
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

    public long getLocationIDA() {
        return EntryHelper.getCourseLocationIdA(getEntry(), DBContract.NULL_ID);
    }

    public long getLocationIDB() {
        return EntryHelper.getCourseLocationIdB(getEntry(), DBContract.NULL_ID);
    }

    public long getInstructorIDA() {
        return EntryHelper.getCourseInstructorIdA(getEntry(), DBContract.NULL_ID);
    }

    public long getInstructorIDB() {
        return EntryHelper.getCourseInstructorIdB(getEntry(), DBContract.NULL_ID);
    }

    public long getInstructorIDC() {
        return EntryHelper.getCourseInstructorIdC(getEntry(), DBContract.NULL_ID);
    }

    public int getLength() {
        return EntryHelper.getCourseLength(getEntry(), 0);
    }

    public String getNote() {
        return EntryHelper.getCourseNote(getEntry(), null);
    }
}
