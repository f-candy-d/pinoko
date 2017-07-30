package com.f_candy_d.pinoko.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

abstract public class Entry<T extends Entry<T>> {

    private final String mAffiliation;
    private Bundle mAttributes;
    private String mDefaultStringValue = null;
    private int mDefaultIntValue = 0;
    private boolean mDefaultBoolValue = false;
    private long mDefaultLongValue = 0L;

    /**
     * Abstract methods
     */
//    abstract public ContentValues toContentValues(final boolean withIdIfExists);
    abstract public Set<String> getAttributeNames();
    abstract protected void shapeAttributes();
//    abstract public boolean isSavable();

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
        shapeAttributes();
    }

    final public String getAffiliation() {
        return mAffiliation;
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

    protected T set(final String attr, final int value) {
        mAttributes.putInt(attr, value);
        return (T) this;
    }

    protected T set(final String attr, final String value) {
        mAttributes.putString(attr, value);
        return (T) this;
    }

    protected T set(final String attr, final boolean value) {
        mAttributes.putBoolean(attr, value);
        return (T) this;
    }

    protected T set(final String attr, final long value) {
        mAttributes.putLong(attr, value);
        return (T) this;
    }

    protected String getString(final String attr) {
        return mAttributes.getString(attr, mDefaultStringValue);
    }

    protected int getInt(final String attr) {
        return mAttributes.getInt(attr, mDefaultIntValue);
    }

    protected boolean getBool(final String attr) {
        return mAttributes.getBoolean(attr, mDefaultBoolValue);
    }

    protected long getLong(final String attr) {
        return mAttributes.getLong(attr, mDefaultLongValue);
    }
}
