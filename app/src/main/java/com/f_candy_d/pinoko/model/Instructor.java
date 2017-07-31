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

public class Instructor extends Entry<Instructor> implements Savable {

    public Instructor() {
        this(null, false);
    }

    public Instructor(final Bundle bundle) {
        this(bundle, false);
    }

    public Instructor(final Bundle bundle, final boolean copyOnlyReference) {
        super(DBContract.InstructorEntry.TABLE_NAME, bundle, copyOnlyReference);
    }

    public Instructor setID(final int id) {
        return set(DBContract.InstructorEntry._ID, id);
    }

    public Instructor setName(final String name) {
        return set(DBContract.InstructorEntry.COL_NAME, name);
    }

    public Instructor setLab(final String lab) {
        return set(DBContract.InstructorEntry.COL_LAB, lab);
    }

    public Instructor setMail(final String mail) {
        return set(DBContract.InstructorEntry.COL_MAIL, mail);
    }

    public Instructor setPhoneNumber(final String phoneNumber) {
        return set(DBContract.InstructorEntry.COL_PHONE_NUMBER, phoneNumber);
    }

    public Instructor setNote(final String note) {
        return set(DBContract.InstructorEntry.COL_NOTE, note);
    }

    public int getID() {
        return getInt(DBContract.InstructorEntry._ID);
    }

    public String getName() {
        return getString(DBContract.InstructorEntry.COL_NAME);
    }

    public String getLab() {
        return getString(DBContract.InstructorEntry.COL_LAB);
    }

    public String getMail() {
        return getString(DBContract.InstructorEntry.COL_MAIL);
    }

    public String getPhoneNumber() {
        return getString(DBContract.InstructorEntry.COL_PHONE_NUMBER);
    }

    public String getNote() {
        return getString(DBContract.InstructorEntry.COL_NOTE);
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.InstructorEntry.getColumnNames()));
    }

    @Override
    public boolean isSavable() {
        return (getName() != null);
    }

    @Override
    public String getTableName() {
        return DBContract.InstructorEntry.TABLE_NAME;
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.InstructorEntry._ID, getID());
        }
        contentValues.put(DBContract.InstructorEntry.COL_NAME, getName());
        contentValues.put(DBContract.InstructorEntry.COL_LAB, getLab());
        contentValues.put(DBContract.InstructorEntry.COL_MAIL, getMail());
        contentValues.put(DBContract.InstructorEntry.COL_PHONE_NUMBER, getPhoneNumber());
        contentValues.put(DBContract.InstructorEntry.COL_NOTE, getNote());

        return contentValues;
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.InstructorEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.InstructorEntry.COL_NAME)) {
            setName(getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.COL_LAB)) {
            setLab(getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.COL_MAIL)) {
            setMail(getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.COL_PHONE_NUMBER)) {
            setPhoneNumber(getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.COL_NOTE)) {
            setNote(getDefaultStringValue());
        }
    }

    public FlexibleEntry castTo() {
        return new FlexibleEntry(getAffiliation(), getAttributes(), true);
    }

    static public Instructor castFrom(@NonNull FlexibleEntry flexibleEntry) {
        return new Instructor(flexibleEntry.getAttributes(), true);
    }
}
