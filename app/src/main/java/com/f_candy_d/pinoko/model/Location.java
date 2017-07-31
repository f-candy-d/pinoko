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

public class Location extends Entry<Location> implements Savable {

    public Location() {
        this(null, false);
    }

    public Location(final Bundle bundle) {
        this(bundle, false);
    }

    public Location(final Bundle bundle, final boolean copyOnlyReference) {
        super(DBContract.LocationEntry.TABLE_NAME, bundle, copyOnlyReference);
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.LocationEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.LocationEntry.COL_NAME)) {
            setName(getDefaultStringValue());
        }
        if (!has(DBContract.LocationEntry.COL_NOTE)) {
            setNote(getDefaultStringValue());
        }
    }

    @Override
    public String getTableName() {
        return DBContract.LocationEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.LocationEntry.getColumnNames()));
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

    public Location setID(final int id) {
        return set(DBContract.LocationEntry._ID, id);
    }

    public Location setName(final String name) {
        return set(DBContract.LocationEntry.COL_NAME, name);
    }

    public Location setNote(final String note) {
        return set(DBContract.LocationEntry.COL_NOTE, note);
    }

    public int getID() {
        return getInt(DBContract.LocationEntry._ID);
    }

    public String getName() {
        return getString(DBContract.LocationEntry.COL_NAME);
    }

    public String getNote() {
        return getString(DBContract.LocationEntry.COL_NOTE);
    }

    public FlexibleEntry castTo() {
        return new FlexibleEntry(getAffiliation(), getAttributes(), true);
    }

    static public Location castFrom(@NonNull FlexibleEntry flexibleEntry) {
        return new Location(flexibleEntry.getAttributes(), true);
    }
}
