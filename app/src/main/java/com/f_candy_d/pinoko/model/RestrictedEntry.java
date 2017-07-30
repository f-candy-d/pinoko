package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

abstract public class RestrictedEntry<T extends RestrictedEntry<T>> {

    private final String mAffiliation;
    private Bundle mAttributes;
    private String mDefaultStringValue = null;
    private int mDefaultIntValue = 0;
    private boolean mDefaultBoolValue = false;

    /**
     * Abstract methods
     */
    abstract public Bundle toBundle();
    abstract public ContentValues toContentValues();
    abstract public Set<String> getAttributeNames();

    public RestrictedEntry(@NonNull final String affiliation) {
        this(affiliation, null);
    }

    public RestrictedEntry(@NonNull final String affiliation, final Bundle bundle) {
        mAffiliation = affiliation;
        if (bundle != null) {
            mAttributes = new Bundle(bundle);
        } else {
            mAttributes = new Bundle();
        }
    }

    public String getAffiliation() {
        return mAffiliation;
    }

    @Override
    public String toString() {
        String string = super.toString() + "\n";
        ArrayList<String> values = new ArrayList<>();
        for (String attr : getAttributeNames()) {
            values.add("#" + attr + " :: " + mAttributes.get(attr).toString());
        }

        return string + TextUtils.join("\n", values);
    }

    public boolean has(@NonNull final String attr) {
        return mAttributes.containsKey(attr);
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

    public boolean isDefaultBoolValue() {
        return mDefaultBoolValue;
    }

    public void setDefaultBoolValue(boolean defaultBoolValue) {
        mDefaultBoolValue = defaultBoolValue;
    }

    protected Bundle getAttributes() {
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

    protected String getString(final String attr) {
        return mAttributes.getString(attr, mDefaultStringValue);
    }

    protected int getInt(final String attr) {
        return mAttributes.getInt(attr, mDefaultIntValue);
    }

    protected boolean getBool(final String attr) {
        return mAttributes.getBoolean(attr, mDefaultBoolValue);
    }
}
