package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class NotificationFormer extends EntryFormer {

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

    public static NotificationFormer createWithEntry() {
        return new NotificationFormer(new Entry(DBContract.NotificationEntry.TABLE_NAME));
    }

    public NotificationFormer() {}

    public NotificationFormer(final Entry entry) {
        super(entry);
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
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public String getTableName() {
        return DBContract.NotificationEntry.TABLE_NAME;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.NotificationEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.NotificationEntry.COL_NAME)) {
            setName(getEntry().getDefaultStringValue());
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
            setIsDone(getEntry().getDefaultBoolValue());
        }
        if (!has(DBContract.NotificationEntry.COL_DATETIME_BEGIN)) {
            setDatetimeBegin(getEntry().getDefaultLongValue());
        }
        if (!has(DBContract.NotificationEntry.COL_DATETIME_END)) {
            setDatetimeEnd(getEntry().getDefaultLongValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.NotificationEntry.getColumnNames()));
    }

    public NotificationFormer setID(final long id) {
        getEntry().set(DBContract.NotificationEntry._ID, id);
        return this;
    }

    public NotificationFormer setName(final String name) {
        getEntry().set(DBContract.NotificationEntry.COL_NAME, name);
        return this;
    }

    public NotificationFormer setNote(final String note) {
        getEntry().set(DBContract.NotificationEntry.COL_NOTE, note);
        return this;
    }

    public NotificationFormer setCategory(final Category category) {
        getEntry().set(DBContract.NotificationEntry.COL_CATEGORY, category.toInt());
        return this;
    }

    public NotificationFormer setTargetID(final long targetID) {
        getEntry().set(DBContract.NotificationEntry.COL_TARGET_ID, targetID);
        return this;
    }

    public NotificationFormer setType(final Type type) {
        getEntry().set(DBContract.NotificationEntry.COL_TYPE, type.toInt());
        return this;
    }

    public NotificationFormer setIsDone(final boolean isDone) {
        getEntry().set(DBContract.NotificationEntry.COL_IS_DONE, isDone);
        return this;
    }

    public NotificationFormer setDatetimeBegin(final long datetimeBegin) {
        getEntry().set(DBContract.NotificationEntry.COL_DATETIME_BEGIN, datetimeBegin);
        return this;
    }

    public NotificationFormer setDatetimeEnd(final long datetimeEnd) {
        getEntry().set(DBContract.NotificationEntry.COL_DATETIME_END, datetimeEnd);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.NotificationEntry._ID);
    }

    public String getName() {
        return getEntry().getString(DBContract.NotificationEntry.COL_NAME);
    }

    public String getNote() {
        return getEntry().getString(DBContract.NotificationEntry.COL_NOTE);
    }

    public Category getCategory() {
        return Category.from(getEntry().getInt(DBContract.NotificationEntry.COL_CATEGORY));
    }

    public long getTargetID() {
        return getEntry().getLong(DBContract.NotificationEntry.COL_TARGET_ID);
    }

    public Type getType() {
        return Type.from(getEntry().getInt(DBContract.NotificationEntry.COL_TYPE));
    }

    public boolean getIsDone() {
        return getEntry().getBool(DBContract.NotificationEntry.COL_IS_DONE);
    }

    public long getDatetimeBegin() {
        return getEntry().getLong(DBContract.NotificationEntry.COL_DATETIME_BEGIN);
    }

    public long getDatetimeEnd() {
        return getEntry().getLong(DBContract.NotificationEntry.COL_DATETIME_END);
    }
}
