package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.Savable;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

// TODO; remove 'implements Savable'
public class Entry extends RestrictedEntry<Entry> implements Savable {

    public Entry(@NonNull final String affiliation) {
        this(affiliation, null);
    }

    public Entry(@NonNull final String affiliation, final Bundle bundle) {
        super(affiliation, bundle);
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        Object value;

        for (String attr : getAttributeNames()) {
            value = getAttributes().get(attr);

            if (value instanceof Integer) {
                contentValues.put(attr, (Integer) value);
            } else if (value instanceof String) {
                contentValues.put(attr, (String) value);
            } else if (value instanceof Boolean) {
                contentValues.put(attr, (Boolean) value);
            }
        }

        return contentValues;
    }

    @Override
    protected void shapeAttributes() {
        // Nothing to do in this method
    }

    @Override
    public boolean isSavable() {
        return false;
    }

    @Override
    public Set<String> getAttributeNames() {
        return getAttributes().keySet();
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public Entry set(String attr, int value) {
        return super.set(attr, value);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public Entry set(String attr, String value) {
        return super.set(attr, value);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public Entry set(String attr, boolean value) {
        return super.set(attr, value);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public int getInt(String attr) {
        return super.getInt(attr);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public String getString(String attr) {
        return super.getString(attr);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public boolean getBool(String attr) {
        return super.getBool(attr);
    }
}
