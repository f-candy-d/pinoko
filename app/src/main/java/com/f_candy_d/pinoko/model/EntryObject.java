package com.f_candy_d.pinoko.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/9/17.
 */

abstract public class EntryObject implements Parcelable {

    /**
     * region; EntryObject's methods
     */

    public EntryObject() {}

    abstract protected void construct(@NonNull final Entry entry);

    abstract public Entry toEntry();

    protected void throwExceptionForExpectedAttributeIsMissing(final String expectedAttr) {
        throw new IllegalArgumentException("Expected attribute is missing : " + expectedAttr);
    }
}
