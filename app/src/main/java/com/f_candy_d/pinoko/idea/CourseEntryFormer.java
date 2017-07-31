package com.f_candy_d.pinoko.idea;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.FlexibleEntry;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.Savable;

/**
 * Created by daichi on 7/31/17.
 *
 * TODO; Create the super class of formers: 'EntryFormer'
 * EntryFormer class implements Savable & has the following methods:
 *
 * + abstract protected void formatAttributes();
 * + abstract public Entry deformatAttributes();
 * + public Entry getEntry();
 * + public void setEntry();
 * + public String getEntryAffiliation();
 *
 * ## USAGE ##
 *
 * FlexibleEntry entry;
 * CourseEntryFormer course = new CourseEntryFormer(entry);
 * course.setID(10);
 *         .
 *         .
 *         .
 */

public class CourseEntryFormer implements Savable {

    private FlexibleEntry mEntry;

    public CourseEntryFormer(final FlexibleEntry entry) {
        mEntry = entry;
        mEntry.setAffiliation(DBContract.CourseEntry.TABLE_NAME);
    }

    @Override
    public boolean isSavable() {
        return false;
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    protected void formatAttributes() {
        if (!mEntry.has(DBContract.CourseEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
    }

    public CourseEntryFormer setID(final long id) {
        mEntry.set(DBContract.CourseEntry._ID, id);
        return this;
    }

    public long getID() {
        return mEntry.getLong(DBContract.CourseEntry._ID);
    }
}
