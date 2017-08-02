package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class LocationFormer extends EntryFormer {

    public static LocationFormer createWithEntry() {
        return new LocationFormer(new Entry(DBContract.LocationEntry.TABLE_NAME));
    }

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
            contentValues.put(DBContract.LocationEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.LocationEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.LocationEntry.ATTR_NOTE, getNote());

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
        if (!has(DBContract.LocationEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.LocationEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.LocationEntry.ATTR_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.LocationEntry.getColumnNames()));
    }

    public LocationFormer setID(final long id) {
        getEntry().set(DBContract.LocationEntry.ATTR_ID, id);
        return this;
    }

    public LocationFormer setName(final String name) {
        getEntry().set(DBContract.LocationEntry.ATTR_NAME, name);
        return this;
    }

    public LocationFormer setNote(final String note) {
        getEntry().set(DBContract.LocationEntry.ATTR_NOTE, note);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.LocationEntry.ATTR_ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.LocationEntry.ATTR_NAME);
    }

    public String getNote() {
        return getEntry().getString(DBContract.LocationEntry.ATTR_NOTE);
    }
}
