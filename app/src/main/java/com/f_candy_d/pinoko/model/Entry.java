package com.f_candy_d.pinoko.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class Entry {

    private String mAffiliation;
    private Bundle mAttributes;
    private String mDefaultStringValue = null;
    private int mDefaultIntValue = 0;
    private boolean mDefaultBoolValue = false;
    private long mDefaultLongValue = 0L;

    public Entry(@NonNull final String affiliation) {
        this(affiliation, null);
    }

    public Entry(@NonNull final String affiliation, final Bundle bundle) {
        mAffiliation = affiliation;
        if (bundle != null) {
            mAttributes = new Bundle(bundle);
        } else {
            mAttributes = new Bundle();
        }
    }

    public Set<String> getAttributeNames() {
        return getAttributes().keySet();
    }

    final public String getAffiliation() {
        return mAffiliation;
    }

    final public void setAffiliation(final String affiliation) {
        mAffiliation = affiliation;
    }

    final public Bundle toBundle() {
        return new Bundle(mAttributes);
    }

    @Override
    public String toString() {
        String string = super.toString() + "\n";
        ArrayList<String> values = new ArrayList<>();
        for (String attr : mAttributes.keySet()) {
            values.add("#" + attr + " :: " + mAttributes.get(attr).toString());
        }

        return string + TextUtils.join("\n", values);
    }

    final public boolean has(@NonNull final String attr) {
        return mAttributes.containsKey(attr);
    }


    final public String getDefaultStringValue() {
        return mDefaultStringValue;
    }

    final public void setDefaultStringValue(String defaultStringValue) {
        mDefaultStringValue = defaultStringValue;
    }

    final public int getDefaultIntValue() {
        return mDefaultIntValue;
    }

    final public void setDefaultIntValue(int defaultIntValue) {
        mDefaultIntValue = defaultIntValue;
    }

    final public boolean getDefaultBoolValue() {
        return mDefaultBoolValue;
    }

    final public void setDefaultBoolValue(boolean defaultBoolValue) {
        mDefaultBoolValue = defaultBoolValue;
    }

    public long getDefaultLongValue() {
        return mDefaultLongValue;
    }

    public void setDefaultLongValue(long defaultLongValue) {
        mDefaultLongValue = defaultLongValue;
    }

    final protected Bundle getAttributes() {
        return mAttributes;
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

    public Entry set(final String attr, final long value) {
        mAttributes.putLong(attr, value);
        return this;
    }

    public Entry set(final String attr, final Serializable value) {
        mAttributes.putSerializable(attr,value);
        return this;
    }

    public String getString(final String attr) {
        return mAttributes.getString(attr, mDefaultStringValue);
    }

    public String getString(final String attr, final String def) {
        return mAttributes.getString(attr, def);
    }

    public int getInt(final String attr) {
        return mAttributes.getInt(attr, mDefaultIntValue);
    }

    public int getInt(final String attr, final int def) {
        return mAttributes.getInt(attr, def);
    }

    public boolean getBool(final String attr) {
        return mAttributes.getBoolean(attr, mDefaultBoolValue);
    }

    public boolean getBool(final String attr, final boolean def) {
        return mAttributes.getBoolean(attr, def);
    }

    public long getLong(final String attr) {
        return mAttributes.getLong(attr, mDefaultLongValue);
    }

    public long getLong(final String attr, final long def) {
        return mAttributes.getLong(attr, def);
    }

    public Serializable getEnum(final String attr) {
        return mAttributes.getSerializable(attr);
    }
}
