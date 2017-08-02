package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class InstructorFormer extends EntryFormer {

    public static InstructorFormer createWithEntry() {
        return new InstructorFormer(new Entry(DBContract.InstructorEntry.TABLE_NAME));
    }

    public InstructorFormer() {}

    public InstructorFormer(final Entry entry) {
        super(entry);
    }

    @Override
    public boolean isSavable() {
        return (getName() != null);
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.InstructorEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.InstructorEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.InstructorEntry.ATTR_LAB, getLab());
        contentValues.put(DBContract.InstructorEntry.ATTR_MAIL, getMail());
        contentValues.put(DBContract.InstructorEntry.ATTR_PHONE_NUMBER, getPhoneNumber());
        contentValues.put(DBContract.InstructorEntry.ATTR_NOTE, getNote());

        return contentValues;
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public String getTableName() {
        return DBContract.InstructorEntry.TABLE_NAME;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.InstructorEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.InstructorEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.ATTR_LAB)) {
            setLab(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.ATTR_MAIL)) {
            setMail(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.ATTR_PHONE_NUMBER)) {
            setPhoneNumber(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.InstructorEntry.ATTR_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.InstructorEntry.getColumnNames()));
    }

    public InstructorFormer setID(final long id) {
        getEntry().set(DBContract.InstructorEntry.ATTR_ID, id);
        return this;
    }

    public InstructorFormer setName(final String name) {
        getEntry().set(DBContract.InstructorEntry.ATTR_NAME, name);
        return this;
    }

    public InstructorFormer setLab(final String lab) {
        getEntry().set(DBContract.InstructorEntry.ATTR_LAB, lab);
        return this;
    }

    public InstructorFormer setMail(final String mail) {
        getEntry().set(DBContract.InstructorEntry.ATTR_MAIL, mail);
        return this;
    }

    public InstructorFormer setPhoneNumber(final String phoneNumber) {
        getEntry().set(DBContract.InstructorEntry.ATTR_PHONE_NUMBER, phoneNumber);
        return this;
    }

    public InstructorFormer setNote(final String note) {
        getEntry().set(DBContract.InstructorEntry.ATTR_NOTE, note);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.InstructorEntry.ATTR_ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.InstructorEntry.ATTR_NAME);
    }

    public String getLab() {
        return getEntry().getString(DBContract.InstructorEntry.ATTR_LAB);
    }

    public String getMail() {
        return getEntry().getString(DBContract.InstructorEntry.ATTR_MAIL);
    }

    public String getPhoneNumber() {
        return getEntry().getString(DBContract.InstructorEntry.ATTR_PHONE_NUMBER);
    }

    public String getNote() {
        return getEntry().getString(DBContract.InstructorEntry.ATTR_NOTE);
    }
}
