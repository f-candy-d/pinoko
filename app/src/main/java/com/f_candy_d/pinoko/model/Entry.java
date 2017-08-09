package com.f_candy_d.pinoko.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class Entry {

    /**
     * Used in set(final String attr, final Entry value) & getEntry() methods
     */
    private static final String KEY_INNER_ENTRY_AFFILIATION = "keyInnerEntryAffiliation";

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
        Object value;

        for (String attr : mAttributes.keySet()) {
            value = mAttributes.get(attr);
            if (value != null) {
                values.add(attr + " :: " + value.toString());
            } else {
                values.add(attr + " :: NULL");
            }
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

    public Entry set(final String attr, final Entry value) {
        Bundle bundle = value.toBundle();
        bundle.putString(KEY_INNER_ENTRY_AFFILIATION, value.getAffiliation());
        mAttributes.putBundle(attr, value.toBundle());
        return this;
    }

    public Entry set(final String attr, final Collection<Entry> values) {
        Bundle[] bundles = new Bundle[values.size()];
        int i = 0;
        for (Entry entry : values) {
            bundles[i] = entry.toBundle();
            bundles[i].putString(KEY_INNER_ENTRY_AFFILIATION, entry.getAffiliation());
            ++i;
        }

        mAttributes.putSerializable(attr, bundles);
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

    public Serializable getSerializable(final String attr) {
        return mAttributes.getSerializable(attr);
    }

    public Entry getEntry(final String attr) {
        return getEntry(attr, null);
    }

    public Entry getEntry(final String attr, final Entry def) {
        Bundle bundle = mAttributes.getBundle(attr);
        String affiliation;
        if (bundle != null &&
                (affiliation = bundle.getString(KEY_INNER_ENTRY_AFFILIATION)) != null) {

            return new Entry(affiliation, bundle);
        }
        return def;
    }

    public ArrayList<Entry> getEntries(final String attr) {
        return getEntries(attr, null);
    }

    public ArrayList<Entry> getEntries(final String attr, final ArrayList<Entry> def) {
        Serializable value = mAttributes.getSerializable(attr);
        if (value instanceof Bundle[]) {
            Bundle[] bundles = (Bundle[]) value;
            ArrayList<Entry> entries = new ArrayList<>(bundles.length);
            Entry entry;
            String affiliation;
            for (Bundle bundle : bundles) {
                if (bundle != null
                        && (affiliation = bundle.getString(KEY_INNER_ENTRY_AFFILIATION)) != null) {
                    entry = new Entry(affiliation, bundle);
                    entries.add(entry);
                }
            }

            return entries;
        }

        return def;
    }
}
