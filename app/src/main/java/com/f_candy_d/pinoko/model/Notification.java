package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.Savable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 7/30/17.
 */

public class Notification extends Entry<Notification> implements Savable {

    public enum Category {
        NULL_CATEGORY(0),
        WHOLE(1),
        EVENT(2),
        ASSIGNMENT(3),
        LOCATION(4),
        COURSE(5),
        INSTRUCTOR(6),
        TIME_BLOCK(7),
        ATTENDANCE(8);

        private final int mId;

        Category(final int id) {
            mId = id;
        }

        public int toInt() {
            return mId;
        }

        public static Category from(final int id) {
            Category[] categories = values();
            for (Category category : categories) {
                if (category.toInt() == id) {
                    return category;
                }
            }
            return NULL_CATEGORY;
        }
    }

    public enum Type {
        NULL_TYPE(0),
        GENERAL(1),
        CANCELLATION(2),
        CHANGE(3),
        SUPPLEMENT(4);

        private final int mId;

        Type(final int id) {
            mId = id;
        }

        public int toInt() {
            return mId;
        }

        public static Type from(final int id) {
            Type[] types = values();
            for (Type type : types) {
                if (type.toInt() == id) {
                    return type;
                }
            }
            return NULL_TYPE;
        }
    }

    public Notification() {
        this(null, false);
    }

    public Notification(final Bundle bundle) {
        this(bundle, false);
    }

    public Notification(final Bundle bundle, final boolean copyOnlyReference) {
        super(DBContract.NotificationEntry.TABLE_NAME, bundle, copyOnlyReference);
    }

    @Override
    public boolean isSavable() {
        return (getName() != null &&
                getCategory() != Category.NULL_CATEGORY &&
                getType() != Type.NULL_TYPE &&
                0 < getDatetimeBegin() &&
                getDatetimeBegin() <= getDatetimeEnd());
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.NotificationEntry._ID, getID());
        }
        contentValues.put(DBContract.NotificationEntry.COL_NAME, getName());
        contentValues.put(DBContract.NotificationEntry.COL_NOTE, getNote());
        contentValues.put(DBContract.NotificationEntry.COL_CATEGORY, getCategory().toInt());
        contentValues.put(DBContract.NotificationEntry.COL_TARGET_ID, getTargetID());
        contentValues.put(DBContract.NotificationEntry.COL_TYPE, getType().toInt());
        contentValues.put(DBContract.NotificationEntry.COL_IS_DONE, getIsDone());
        contentValues.put(DBContract.NotificationEntry.COL_DATETIME_BEGIN, getDatetimeBegin());
        contentValues.put(DBContract.NotificationEntry.COL_DATETIME_END, getDatetimeEnd());

        return contentValues;
    }

    @Override
    public String getTableName() {
        return DBContract.NotificationEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.NotificationEntry.getColumnNames()));
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.NotificationEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.NotificationEntry.COL_NAME)) {
            setName(getDefaultStringValue());
        }
        if (!has(DBContract.NotificationEntry.COL_CATEGORY)) {
            setCategory(Category.NULL_CATEGORY);
        }
        if (!has(DBContract.NotificationEntry.COL_TARGET_ID)) {
            setTargetID(DBContract.NULL_ID);
        }
        if (!has(DBContract.NotificationEntry.COL_TYPE)) {
            setType(Type.NULL_TYPE);
        }
        if (!has(DBContract.NotificationEntry.COL_IS_DONE)) {
            setIsDone(getDefaultBoolValue());
        }
        if (!has(DBContract.NotificationEntry.COL_DATETIME_BEGIN)) {
            setDatetimeBegin(getDefaultLongValue());
        }
        if (!has(DBContract.NotificationEntry.COL_DATETIME_END)) {
            setDatetimeEnd(getDefaultLongValue());
        }
    }

    public FlexibleEntry castTo() {
        return new FlexibleEntry(getAffiliation(), getAttributes(), true);
    }

    static public Notification castFrom(@NonNull FlexibleEntry flexibleEntry) {
        return new Notification(flexibleEntry.getAttributes(), true);
    }

    public Notification setID(final int id) {
        return set(DBContract.NotificationEntry._ID, id);
    }

    public Notification setName(final String name) {
        return set(DBContract.NotificationEntry.COL_NAME, name);
    }

    public Notification setNote(final String note) {
        return set(DBContract.NotificationEntry.COL_NOTE, note);
    }

    public Notification setCategory(final Category category) {
        return set(DBContract.NotificationEntry.COL_CATEGORY, category.toInt());
    }

    public Notification setTargetID(final int targetID) {
        return set(DBContract.NotificationEntry.COL_TARGET_ID, targetID);
    }

    public Notification setType(final Type type) {
        return set(DBContract.NotificationEntry.COL_TYPE, type.toInt());
    }

    public Notification setIsDone(final boolean isDone) {
        return set(DBContract.NotificationEntry.COL_IS_DONE, isDone);
    }

    public Notification setDatetimeBegin(final long datetimeBegin) {
        return set(DBContract.NotificationEntry.COL_DATETIME_BEGIN, datetimeBegin);
    }

    public Notification setDatetimeEnd(final long datetimeEnd) {
        return set(DBContract.NotificationEntry.COL_DATETIME_END, datetimeEnd);
    }

    public int getID() {
        return getInt(DBContract.NotificationEntry._ID);
    }

    public String getName() {
        return getString(DBContract.NotificationEntry.COL_NAME);
    }

    public String getNote() {
        return getString(DBContract.NotificationEntry.COL_NOTE);
    }

    public Category getCategory() {
        return Category.from(getInt(DBContract.NotificationEntry.COL_CATEGORY));
    }

    public int getTargetID() {
        return getInt(DBContract.NotificationEntry.COL_TARGET_ID);
    }

    public Type getType() {
        return Type.from(getInt(DBContract.NotificationEntry.COL_TYPE));
    }

    public boolean getIsDone() {
        return getBool(DBContract.NotificationEntry.COL_IS_DONE);
    }

    public long getDatetimeBegin() {
        return getLong(DBContract.NotificationEntry.COL_DATETIME_BEGIN);
    }

    public long getDatetimeEnd() {
        return getLong(DBContract.NotificationEntry.COL_DATETIME_END);
    }
}
