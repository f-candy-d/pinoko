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
    public String getIdColumnName() {
        return DBContract.InstructorEntry.ATTR_ID;
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
        EntryHelper.setInstructorId(getEntry(), id);
        return this;
    }

    public InstructorFormer setName(final String name) {
        EntryHelper.setInstructorName(getEntry(), name);
        return this;
    }

    public InstructorFormer setLab(final String lab) {
        EntryHelper.setInstructorLab(getEntry(), lab);
        return this;
    }

    public InstructorFormer setMail(final String mail) {
        EntryHelper.setInstructorMail(getEntry(), mail);
        return this;
    }

    public InstructorFormer setPhoneNumber(final String phoneNumber) {
        EntryHelper.setInstructorPhoneNumber(getEntry(), phoneNumber);
        return this;
    }

    public InstructorFormer setNote(final String note) {
        EntryHelper.setInstructorNote(getEntry(), note);
        return this;
    }

    public long getID() {
        return EntryHelper.getInstructorId(getEntry(), DBContract.NULL_ID);
    }

    public String getName() {
        return EntryHelper.getInstructorName(getEntry(), null);
    }

    public String getLab() {
        return EntryHelper.getInstructorLab(getEntry(), null);
    }

    public String getMail() {
        return EntryHelper.getInstructorMail(getEntry(), null);
    }

    public String getPhoneNumber() {
        return EntryHelper.getInstructorPhoneNumber(getEntry(), null);
    }

    public String getNote() {
        return EntryHelper.getInstructorNote(getEntry(), null);
    }
}
