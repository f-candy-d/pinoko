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

public class Event extends Entry<Event> implements Savable {

    public Event() {
        this(null, false);
    }

    public Event(final Bundle bundle) {
        this(bundle, false);
    }

    public Event(final Bundle bundle, final boolean copyOnlyReference) {
        super(DBContract.EventEntry.TABLE_NAME, bundle, copyOnlyReference);
    }

    @Override
    public boolean isSavable() {
        return (getName() != null);
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.EventEntry._ID, getID());
        }
        contentValues.put(DBContract.EventEntry.COL_NAME, getName());
        contentValues.put(DBContract.EventEntry.COL_LOCATION_ID, getLocationID());
        contentValues.put(DBContract.EventEntry.COL_NOTE, getNote());

        return contentValues;
    }

    @Override
    public String getTableName() {
        return DBContract.EventEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.EventEntry.getColumnNames()));
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.EventEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.EventEntry.COL_NAME)) {
            setName(getDefaultStringValue());
        }
        if (!has(DBContract.EventEntry.COL_LOCATION_ID)) {
            setLocationID(DBContract.NULL_ID);
        }
        if (!has(DBContract.EventEntry.COL_NOTE)) {
            setNote(getDefaultStringValue());
        }
    }

    public FlexibleEntry castTo() {
        return new FlexibleEntry(getAffiliation(), getAttributes(), true);
    }

    static public Event castFrom(@NonNull FlexibleEntry flexibleEntry) {
        return new Event(flexibleEntry.getAttributes(), true);
    }

    public Event setID(final int id) {
        return set(DBContract.EventEntry._ID, id);
    }

    public Event setName(final String name) {
        return set(DBContract.EventEntry.COL_NAME, name);
    }

    public Event setLocationID(final int locationID) {
        return set(DBContract.EventEntry.COL_LOCATION_ID, locationID);
    }

    public Event setNote(final String note) {
        return set(DBContract.EventEntry.COL_NOTE, note);
    }

    public int getID() {
        return getInt(DBContract.EventEntry._ID);
    }

    public String getName() {
        return getString(DBContract.EventEntry.COL_NAME);
    }

    public int getLocationID() {
        return getInt(DBContract.EventEntry.COL_LOCATION_ID);
    }

    public String getNote() {
        return getString(DBContract.EventEntry.COL_NOTE);
    }
}
