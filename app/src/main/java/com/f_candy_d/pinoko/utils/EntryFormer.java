package com.f_candy_d.pinoko.utils;


import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.Savable;

import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 *
 * When add new a new column to the assignment table...
 *
 * 1. Update {@link AssignmentFormer#toContentValues(boolean)}
 *    and {@link AssignmentFormer#formatAttributes()}
 *
 * 2. Create getter & setter methods for that column.
 *
 * 3. Update {@link AssignmentFormer#isSavable()} if you need.
 *
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
