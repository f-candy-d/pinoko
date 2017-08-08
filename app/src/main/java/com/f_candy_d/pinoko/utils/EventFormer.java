package com.f_candy_d.pinoko.utils;


import android.content.ContentValues;

import com.f_candy_d.pinoko.model.Entry;

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
    public String getIdColumnName() {
        return DBContract.EventEntry.ATTR_ID;
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
        EntryHelper.setEventId(getEntry(), id);
        return this;
    }

    public EventFormer setName(final String name) {
        EntryHelper.setEventName(getEntry(), name);
        return this;
    }

    public EventFormer setLocationID(final long locationID) {
        EntryHelper.setEventLocationId(getEntry(), locationID);
        return this;
    }

    public EventFormer setNote(final String note) {
        EntryHelper.setEventNote(getEntry(), note);
        return this;
    }

    public long getID() {
        return EntryHelper.getEventId(getEntry(), DBContract.NULL_ID);
    }

    public String getName() {
        return EntryHelper.getEventName(getEntry(), null);
    }

    public long getLocationID() {
        return EntryHelper.getEventLocationId(getEntry(), DBContract.NULL_ID);
    }

    public String getNote() {
        return EntryHelper.getEventNote(getEntry(), null);
    }
}
