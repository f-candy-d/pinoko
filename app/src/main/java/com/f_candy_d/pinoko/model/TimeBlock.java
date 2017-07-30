package com.f_candy_d.pinoko.model;

import android.content.ContentValues;
import android.os.Bundle;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.Savable;

import java.sql.Time;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

/**
 * Created by daichi on 7/30/17.
 */

public class TimeBlock extends RestrictedEntry<TimeBlock> implements Savable {

    public enum Type {
        NULL_TYPE(0),
        COURSE(1),
        EVENT(2);

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
        ONE_DAY(1),
        WEEKLY(2),
        EVERYDAY(3);

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

    public TimeBlock() {
        this(null);
    }

    public TimeBlock(final Bundle bundle) {
        super(DBContract.TimeBlockEntry.TABLE_NAME, bundle);
    }

    @Override
    public boolean isSavable() {
        return (getType() != null &&
                getCategory() != null &&
                0 < getDatetimeBegin() &&
                getDatetimeBegin() <= getDatetimeEnd() &&
                getTimeTableID() != DBContract.NULL_ID);
    }

    @Override
    public ContentValues toContentValues(boolean withId) {
        ContentValues contentValues = new ContentValues();
        if (withId) {
            contentValues.put(DBContract.TimeBlockEntry._ID, getID());
        }
        contentValues.put(DBContract.TimeBlockEntry.COL_TYPE, getType().toInt());
        contentValues.put(DBContract.TimeBlockEntry.COL_CATEGORY, getCategory().toInt());
        contentValues.put(DBContract.TimeBlockEntry.COL_TARGET_ID, getTargetID());
        contentValues.put(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN, getDatetimeBegin());
        contentValues.put(DBContract.TimeBlockEntry.COL_DATETIME_END, getDatetimeEnd());
        contentValues.put(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID, getTimeTableID());

        return contentValues;
    }

    @Override
    public String getTableName() {
        return DBContract.TimeBlockEntry.TABLE_NAME;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.TimeBlockEntry.getColumnNames()));
    }

    @Override
    protected void shapeAttributes() {
        if (!has(DBContract.TimeBlockEntry._ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.TimeBlockEntry.COL_TYPE)) {
            setType(Type.NULL_TYPE);
        }
        if (!has(DBContract.TimeBlockEntry.COL_CATEGORY)) {
            setCategory(Category.NULL_CATEGORY);
        }
        if (!has(DBContract.TimeBlockEntry.COL_TARGET_ID)) {
            setTargetID(DBContract.NULL_ID);
        }
        if (!has(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN)) {
            setDatetimeBegin(getDefaultLongValue());
        }
        if (!has(DBContract.TimeBlockEntry.COL_DATETIME_END)) {
            setDatetimeEnd(getDefaultLongValue());
        }
        if (!has(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID)) {
            setTimeTableID(DBContract.NULL_ID);
        }
    }

    public TimeBlock setID(final int id) {
        return set(DBContract.TimeBlockEntry._ID, id);
    }

    public TimeBlock setType(final Type type) {
        return set(DBContract.TimeBlockEntry.COL_TYPE, type.toInt());
    }

    public TimeBlock setCategory(final Category category) {
        return set(DBContract.TimeBlockEntry.COL_CATEGORY, category.toInt());
    }

    public TimeBlock setTargetID(final int targetID) {
        return set(DBContract.TimeBlockEntry.COL_TARGET_ID, targetID);
    }

    public TimeBlock setDatetimeBegin(final long datetimeBegin) {
        return set(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN, datetimeBegin);
    }

    public TimeBlock setDatetimeEnd(final long datetimeEnd) {
        return set(DBContract.TimeBlockEntry.COL_DATETIME_END, datetimeEnd);
    }

    public TimeBlock setTimeTableID(final int timeTableID) {
        return set(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID, timeTableID);
    }

    public int getID() {
        return getInt(DBContract.TimeBlockEntry._ID);
    }

    public Type getType() {
        return Type.from(getInt(DBContract.TimeBlockEntry.COL_TYPE));
    }

    public Category getCategory() {
        return Category.from(getInt(DBContract.TimeBlockEntry.COL_CATEGORY));
    }

    public int getTargetID() {
        return getInt(DBContract.TimeBlockEntry.COL_TARGET_ID);
    }

    public long getDatetimeBegin() {
        return getLong(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN);
    }

    public long getDatetimeEnd() {
        return getLong(DBContract.TimeBlockEntry.COL_DATETIME_END);
    }

    public int getTimeTableID() {
        return getInt(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID);
    }
}
