package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daichi on 17/08/02.
 */

public class TimeBlockFormer extends EntryFormer {

    public enum Type {
        NULL_TYPE(0),
        ONE_DAY(1),
        WEEKLY(2),
        EVERYDAY(3);

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

    public enum Category {
        NULL_CATEGORY(0),
        COURSE(1),
        EVENT(2);


        private  final int mId;

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

    public static TimeBlockFormer createWithEntry() {
        return new TimeBlockFormer(new Entry(DBContract.TimeBlockEntry.TABLE_NAME));
    }

    public TimeBlockFormer() {}

    public TimeBlockFormer(final Entry entry) {
        super(entry);
    }

    @Override
    public boolean isSavable() {
        return (getType() != null &&
                getCategory() != null &&
                0 < getDatetimeBegin() &&
                getDatetimeBegin() <= getDatetimeEnd() &&
                DBContract.MIN_AVAILABLE_ID <= getTimeTableID());
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.TimeBlockEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.TimeBlockEntry.ATTR_TYPE, getType().toInt());
        contentValues.put(DBContract.TimeBlockEntry.ATTR_CATEGORY, getCategory().toInt());
        contentValues.put(DBContract.TimeBlockEntry.ATTR_TARGET_ID, getTargetID());
        contentValues.put(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN, getDatetimeBegin());
        contentValues.put(DBContract.TimeBlockEntry.ATTR_DATETIME_END, getDatetimeEnd());
        contentValues.put(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID, getTimeTableID());

        return contentValues;
    }

    @Override
    String getEntryAffiliation() {
        return getTableName();
    }

    @Override
    public String getTableName() {
        return DBContract.TimeBlockEntry.TABLE_NAME;
    }

    @Override
    void formatAttributes() {
        if (!has(DBContract.TimeBlockEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.TimeBlockEntry.ATTR_TYPE)) {
            setType(Type.NULL_TYPE);
        }
        if (!has(DBContract.TimeBlockEntry.ATTR_CATEGORY)) {
            setCategory(Category.NULL_CATEGORY);
        }
        if (!has(DBContract.TimeBlockEntry.ATTR_TARGET_ID)) {
            setTargetID(DBContract.NULL_ID);
        }
        if (!has(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN)) {
            setDatetimeBegin(getEntry().getDefaultLongValue());
        }
        if (!has(DBContract.TimeBlockEntry.ATTR_DATETIME_END)) {
            setDatetimeEnd(getEntry().getDefaultLongValue());
        }
        if (!has(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID)) {
            setTimeTableID(DBContract.NULL_ID);
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.TimeBlockEntry.getColumnNames()));
    }

    public TimeBlockFormer setID(final long id) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_ID, id);
        return this;
    }

    public TimeBlockFormer setType(final Type type) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_TYPE, type.toInt());
        return this;
    }

    public TimeBlockFormer setCategory(final Category category) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_CATEGORY, category.toInt());
        return this;
    }

    public TimeBlockFormer setTargetID(final long targetID) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_TARGET_ID, targetID);
        return this;
    }

    public TimeBlockFormer setDatetimeBegin(final long datetimeBegin) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN, datetimeBegin);
        return this;
    }

    public TimeBlockFormer setDatetimeEnd(final long datetimeEnd) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_DATETIME_END, datetimeEnd);
        return this;
    }

    public TimeBlockFormer setTimeTableID(final long timeTableID) {
        getEntry().set(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID, timeTableID);
        return this;
    }

    public long getID() {
        return getEntry().getLong(DBContract.TimeBlockEntry.ATTR_ID);
    }

    public Type getType() {
        return Type.from(getEntry().getInt(DBContract.TimeBlockEntry.ATTR_TYPE));
    }

    public Category getCategory() {
        return Category.from(getEntry().getInt(DBContract.TimeBlockEntry.ATTR_CATEGORY));
    }

    public long getTargetID() {
        return getEntry().getLong(DBContract.TimeBlockEntry.ATTR_TARGET_ID);
    }

    public long getDatetimeBegin() {
        return getEntry().getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN);
    }

    public long getDatetimeEnd() {
        return getEntry().getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_END);
    }

    public long getTimeTableID() {
        return getEntry().getLong(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID);
    }
}
