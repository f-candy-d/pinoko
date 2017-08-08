package com.f_candy_d.pinoko.model;

import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/9/17.
 */

abstract public class EntryObject {

    public EntryObject() {}

    abstract protected void construct(@NonNull final Entry entry);

    protected void throwExceptionForExpectedAttributeIsMissing(final String expectedAttr) {
        throw new IllegalArgumentException("Expected attribute is missing : " + expectedAttr);
    }
}
