package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class LocationFormer extends EntryFormer {

    public LocationFormer() {}

    public LocationFormer(final Entry entry) {
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
            contentValues.put(DBContract.LocationEntry._ID, getID());
        }
        contentValues.put(DBContract.LocationEntry.COL_NAME, getName());
        contentValues.put(DBContract.LocationEntry.COL_NOTE, getNote());

        return contentValues;
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public String getTableName() {
        return DBContract.LocationEntry.TABLE_NAME;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.LocationEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.LocationEntry.COL_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.LocationEntry.COL_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.LocationEntry.getColumnNames()));
    }

    public LocationFormer setID(final int id) {
        getEntry().set(DBContract.LocationEntry._ID, id);
        return this;
    }

    public LocationFormer setName(final String name) {
        getEntry().set(DBContract.LocationEntry.COL_NAME, name);
        return this;
    }

    public LocationFormer setNote(final String note) {
        getEntry().set(DBContract.LocationEntry.COL_NOTE, note);
        return this;
    }

    public int getID() {
        return getEntry().getInt(DBContract.LocationEntry._ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.LocationEntry.COL_NAME);
    }

    public String getNote() {
        return getEntry().getString(DBContract.LocationEntry.COL_NOTE);
    }
}
