package com.f_candy_d.pinoko.model;


import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.Savable;

import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

abstract public class EntryFormer implements Savable {

    private Entry mEntry = null;

    /**
     * Abstract methods
     */
    abstract String getEntryAffiliation();
    abstract void formatAttributes();
    abstract public Set<String> getAttributeNames();

    public EntryFormer() {}

    public EntryFormer(final Entry entry) {
        mEntry = entry;
        if (mEntry != null) {
            mEntry.setAffiliation(getEntryAffiliation());
            formatAttributes();
        }
    }

    public Entry getEntry() {
        return mEntry;
    }

    public void setEntry(Entry entry) {
        mEntry = entry;
    }

    final public boolean has(@NonNull final String attr) {
        return getEntry().has(attr);
    }
}
