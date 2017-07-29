package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class Entry {

    private final String mAffiliation;
    private Bundle mAttributes;
    private String mDefaultStringValue = null;
    private int mDefaultIntValue = 0;
    private boolean mDefaultBoolValue = false;

    public Entry(@NonNull final String affiliation) {
        mAffiliation = affiliation;
        mAttributes = new Bundle();
    }

    public String getAffiliation() {
        return mAffiliation;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        Object value;

        for (String attr : mAttributes.keySet()) {
            value = mAttributes.get(attr);

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

    public Bundle toBundle() {
        return new Bundle(mAttributes);
    }

    @Override
    public String toString() {
        String string = super.toString() + "\n";
        ArrayList<String> values = new ArrayList<>();
        for (String attr : getAttributes()) {
            values.add("#" + attr + " :: " + mAttributes.get(attr).toString());
        }

        return string + TextUtils.join("\n", values);
    }

    public boolean has(@NonNull final String attr) {
        return mAttributes.containsKey(attr);
    }

    public Set<String> getAttributes() {
        return mAttributes.keySet();
    }

    public String getDefaultStringValue() {
        return mDefaultStringValue;
    }

    public void setDefaultStringValue(String defaultStringValue) {
        mDefaultStringValue = defaultStringValue;
    }

    public int getDefaultIntValue() {
        return mDefaultIntValue;
    }

    public void setDefaultIntValue(int defaultIntValue) {
        mDefaultIntValue = defaultIntValue;
    }

    public Entry set(final String attr, final int value) {
        mAttributes.putInt(attr, value);
        return this;
    }

    public Entry set(final String attr, final String value) {
        mAttributes.putString(attr, value);
        return this;
    }

    public Entry set(final String attr, final boolean value) {
        mAttributes.putBoolean(attr, value);
        return this;
    }

    public String getString(final String attr) {
        return mAttributes.getString(attr, mDefaultStringValue);
    }

    public int getInt(final String attr) {
        return mAttributes.getInt(attr, mDefaultIntValue);
    }

    public boolean getBool(final String attr) {
        return mAttributes.getBoolean(attr, mDefaultBoolValue);
    }
}
