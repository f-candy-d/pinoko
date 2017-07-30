package com.f_candy_d.pinoko.model;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class FlexibleEntry extends Entry<FlexibleEntry> {

    public FlexibleEntry(@NonNull final String affiliation) {
        this(affiliation, null);
    }

    public FlexibleEntry(@NonNull final String affiliation, final Bundle bundle) {
        super(affiliation, bundle);
    }

    @Override
    protected void shapeAttributes() {
        // Nothing to do in this method
    }

    @Override
    public Set<String> getAttributeNames() {
        return getAttributes().keySet();
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public FlexibleEntry set(String attr, int value) {
        return super.set(attr, value);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public FlexibleEntry set(String attr, String value) {
        return super.set(attr, value);
    }

    /**
     * Scope changed : protected -> public
     */
    @Override
    public FlexibleEntry set(String attr, boolean value) {
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
