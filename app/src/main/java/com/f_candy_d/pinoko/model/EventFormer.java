package com.f_candy_d.pinoko.model;


import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class EventFormer extends EntryFormer {

    public static EventFormer createWithEntry() {
        return new EventFormer(new Entry(DBContract.EventEntry.TABLE_NAME));
    }

    public EventFormer() {}

    public EventFormer(final Entry entry) {
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
            contentValues.put(DBContract.EventEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.EventEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.EventEntry.ATTR_LOCATION_ID, getLocationID());
        contentValues.put(DBContract.EventEntry.ATTR_NOTE, getNote());

        return contentValues;
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public String getTableName() {
        return DBContract.EventEntry.TABLE_NAME;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.EventEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.EventEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.EventEntry.ATTR_LOCATION_ID)) {
            setLocationID(DBContract.NULL_ID);
        }
        if (!has(DBContract.EventEntry.ATTR_NOTE)) {
            setNote(getEntry().getDefaultStringValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.EventEntry.getColumnNames()));
    }

    public EventFormer setID(final long id) {
        getEntry().set(DBContract.EventEntry.ATTR_ID, id);
        return this;
    }

    public EventFormer setName(final String name) {
        getEntry().set(DBContract.EventEntry.ATTR_NAME, name);
        return this;
    }

    public EventFormer setLocationID(final long locationID) {
        getEntry().set(DBContract.EventEntry.ATTR_LOCATION_ID, locationID);
        return this;
    }

    public EventFormer setNote(final String note) {
        getEntry().set(DBContract.EventEntry.ATTR_NOTE, note);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.EventEntry.ATTR_ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.EventEntry.ATTR_NAME);
    }

    public long getLocationID() {
        return getEntry().getLong(DBContract.EventEntry.ATTR_LOCATION_ID);
    }

    public String getNote() {
        return getEntry().getString(DBContract.EventEntry.ATTR_NOTE);
    }
}
