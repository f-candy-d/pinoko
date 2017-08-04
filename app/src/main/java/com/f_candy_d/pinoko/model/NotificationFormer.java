package com.f_candy_d.pinoko.model;

import android.content.ContentValues;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;

import java.io.Serializable;
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
            contentValues.put(DBContract.NotificationEntry.ATTR_ID, getID());
        }
        contentValues.put(DBContract.NotificationEntry.ATTR_NAME, getName());
        contentValues.put(DBContract.NotificationEntry.ATTR_NOTE, getNote());
        contentValues.put(DBContract.NotificationEntry.ATTR_CATEGORY, getCategory().toInt());
        contentValues.put(DBContract.NotificationEntry.ATTR_TARGET_ID, getTargetID());
        contentValues.put(DBContract.NotificationEntry.ATTR_TYPE, getType().toInt());
        contentValues.put(DBContract.NotificationEntry.ATTR_IS_DONE, getIsDone());
        contentValues.put(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN, getDatetimeBegin());
        contentValues.put(DBContract.NotificationEntry.ATTR_DATETIME_END, getDatetimeEnd());

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
        if (!has(DBContract.NotificationEntry.ATTR_ID)) {
            setID(DBContract.NULL_ID);
        }
        if (!has(DBContract.NotificationEntry.ATTR_NAME)) {
            setName(getEntry().getDefaultStringValue());
        }
        if (!has(DBContract.NotificationEntry.ATTR_CATEGORY)) {
            setCategory(Category.NULL_CATEGORY);
        }
        if (!has(DBContract.NotificationEntry.ATTR_TARGET_ID)) {
            setTargetID(DBContract.NULL_ID);
        }
        if (!has(DBContract.NotificationEntry.ATTR_TYPE)) {
            setType(Type.NULL_TYPE);
        }
        if (!has(DBContract.NotificationEntry.ATTR_IS_DONE)) {
            setIsDone(getEntry().getDefaultBoolValue());
        }
        if (!has(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN)) {
            setDatetimeBegin(getEntry().getDefaultLongValue());
        }
        if (!has(DBContract.NotificationEntry.ATTR_DATETIME_END)) {
            setDatetimeEnd(getEntry().getDefaultLongValue());
        }
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList(DBContract.NotificationEntry.getColumnNames()));
    }

    public NotificationFormer setID(final long id) {
        EntryHelper.setNotificationId(getEntry(), id);
        return this;
    }

    public NotificationFormer setName(final String name) {
        EntryHelper.setNotificationName(getEntry(), name);
        return this;
    }

    public NotificationFormer setNote(final String note) {
        EntryHelper.setNotificationNote(getEntry(), note);
        return this;
    }

    public NotificationFormer setCategory(final Category category) {
        EntryHelper.setNotificationCategory(getEntry(), category);
        return this;
    }

    public NotificationFormer setTargetID(final long targetID) {
        EntryHelper.setNotificationTargetId(getEntry(), targetID);
        return this;
    }

    public NotificationFormer setType(final Type type) {
        EntryHelper.setNotificationType(getEntry(), type);
        return this;
    }

    public NotificationFormer setIsDone(final boolean isDone) {
        EntryHelper.setNotificationIsDone(getEntry(), isDone);
        return this;
    }

    public NotificationFormer setDatetimeBegin(final long datetimeBegin) {
        EntryHelper.setNotificationDatetimeBegin(getEntry(), datetimeBegin);
        return this;
    }

    public NotificationFormer setDatetimeEnd(final long datetimeEnd) {
        EntryHelper.setNotificationDatetimeEnd(getEntry(), getDatetimeEnd());
        return this;
    }

    public long getID() {
        return EntryHelper.getNotificationId(getEntry(), DBContract.NULL_ID);
    }

    public String getName() {
        return EntryHelper.getNotificationName(getEntry(), null);
    }

    public String getNote() {
        return EntryHelper.getNotificationNote(getEntry(), null);
    }

    public Category getCategory() {
        return EntryHelper.getNotificationCategory(getEntry());
    }

    public long getTargetID() {
        return EntryHelper.getNotificationTargetId(getEntry(), DBContract.NULL_ID);
    }

    public Type getType() {
        return EntryHelper.getNotificationType(getEntry());
    }

    public boolean getIsDone() {
        return EntryHelper.getNotificationIsDone(getEntry(), false);
    }

    public long getDatetimeBegin() {
        return EntryHelper.getNotificationDatetimeBegin(getEntry(), 0);
    }

    public long getDatetimeEnd() {
        return EntryHelper.getNotificationDatetimeEnd(getEntry(), 0);
    }
}
