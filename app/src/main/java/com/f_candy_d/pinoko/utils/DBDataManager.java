package com.f_candy_d.pinoko.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.DBOpenHelper;
import com.f_candy_d.pinoko.model.Entry;

import java.util.ArrayList;

/**
 * Created by daichi on 7/30/17.
 */

public class DBDataManager {

    public enum Mode {
        APPEND,
        TRUNCATE
    }

    @NonNull private Context mContext;

    public DBDataManager(@NonNull final Context context, @NonNull final Mode mode) {
        mContext = context;

        if (mode == Mode.TRUNCATE) {
            DBOpenHelper helper = new DBOpenHelper(mContext);
            helper.initTables();
        }
    }

    public long insert(final Entry entry) {
        if (isValidAsBeingInserted(entry)) {
            final DBOpenHelper helper = new DBOpenHelper(mContext);
            final SQLiteDatabase database = helper.getWritableDatabase();
            final long id = database.insert(entry.getAffiliation(), null, entry.toContentValues());
            database.close();
            return id;
        }

        return DBContract.NULL_ID;
    }

    public long[] insert(final ArrayList<Entry> entries) {
        final DBOpenHelper helper = new DBOpenHelper(mContext);
        final SQLiteDatabase database = helper.getWritableDatabase();
        final long[] ids = new long[entries.size()];

        Entry entry;
        for (int i = 0; i < entries.size(); ++i) {
            entry = entries.get(i);
            if (isValidAsBeingInserted(entry)) {
                ids[i] = database.insert(entry.getAffiliation(), null, entry.toContentValues());
            } else {
                ids[i] = DBContract.NULL_ID;
            }
        }

        database.close();
        return ids;
    }

    public ArrayList<Entry> getAllOf(final String tableName) {
        final ArrayList<Entry> entries = new ArrayList<>();
        // Select all rows in the database
        final String select = "SELECT * FROM " + tableName + ";";
        final DBOpenHelper helper = new DBOpenHelper(mContext);
        final SQLiteDatabase database = helper.getReadableDatabase();
        final Cursor cursor = database.rawQuery(select, null);
        boolean isEOF = cursor.moveToFirst();
        Entry entry;

        while (isEOF) {
            entry = EntryHelper.makeEntry(tableName, cursor);
            if (entry != null) {
                entries.add(entry);
            }
            isEOF = cursor.moveToNext();
        }

        cursor.close();
        database.close();
        return entries;
    }

    private boolean isValidAsBeingInserted(final Entry entry) {
        switch (entry.getAffiliation()) {
            case DBContract.CourseEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.CourseEntry.NUM_COLUMNS &&
                        entry.has(DBContract.CourseEntry.COL_NAME) &&
                        entry.has(DBContract.CourseEntry.COL_LOCATION_ID_A) &&
                        entry.has(DBContract.CourseEntry.COL_LOCATION_ID_B) &&
                        entry.has(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A) &&
                        entry.has(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B) &&
                        entry.has(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C) &&
                        entry.has(DBContract.CourseEntry.COL_LENGTH) &&
                        entry.has(DBContract.CourseEntry.COL_NOTE));
            }

            case DBContract.LocationEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.LocationEntry.NUM_COLUMNS &&
                        entry.has(DBContract.LocationEntry.COL_NAME) &&
                        entry.has(DBContract.LocationEntry.COL_NOTE));
            }

            case DBContract.InstructorEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.InstructorEntry.NUM_COLUMNS &&
                        entry.has(DBContract.InstructorEntry.COL_NAME) &&
                        entry.has(DBContract.InstructorEntry.COL_LAB) &&
                        entry.has(DBContract.InstructorEntry.COL_MAIL) &&
                        entry.has(DBContract.InstructorEntry.COL_PHONE_NUMBER) &&
                        entry.has(DBContract.InstructorEntry.COL_NOTE));
            }
            case DBContract.TimeBlockEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.TimeBlockEntry.NUM_COLUMNS &&
                        entry.has(DBContract.TimeBlockEntry.COL_TYPE) &&
                        entry.has(DBContract.TimeBlockEntry.COL_CATEGORY) &&
                        entry.has(DBContract.TimeBlockEntry.COL_TARGET_ID) &&
                        entry.has(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN) &&
                        entry.has(DBContract.TimeBlockEntry.COL_DATETIME_END) &&
                        entry.has(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID));
            }

            case DBContract.AssignmentEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.AssignmentEntry.NUM_COLUMNS &&
                        entry.has(DBContract.AssignmentEntry.COL_NAME) &&
                        entry.has(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID) &&
                        entry.has(DBContract.AssignmentEntry.COL_NOTE) &&
                        entry.has(DBContract.AssignmentEntry.COL_IS_DONE));
            }

            case DBContract.EventEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.EventEntry.NUM_COLUMNS &&
                        entry.has(DBContract.EventEntry.COL_NAME) &&
                        entry.has(DBContract.EventEntry.COL_LOCATION_ID) &&
                        entry.has(DBContract.EventEntry.COL_NOTE));
            }

            case DBContract.NotificationEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.NotificationEntry.NUM_COLUMNS &&
                        entry.has(DBContract.NotificationEntry.COL_NAME) &&
                        entry.has(DBContract.NotificationEntry.COL_NOTE) &&
                        entry.has(DBContract.NotificationEntry.COL_CATEGORY) &&
                        entry.has(DBContract.NotificationEntry.COL_TARGET_ID) &&
                        entry.has(DBContract.NotificationEntry.COL_TYPE) &&
                        entry.has(DBContract.NotificationEntry.COL_IS_DONE) &&
                        entry.has(DBContract.NotificationEntry.COL_DATETIME_BEGIN) &&
                        entry.has(DBContract.NotificationEntry.COL_DATETIME_END));
            }
            case DBContract.AttendanceEntry.TABLE_NAME: {
                return (entry.getAttributes().size() + 1 == DBContract.AttendanceEntry.NUM_COLUMNS &&
                        entry.has(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID) &&
                        entry.has(DBContract.AttendanceEntry.COL_PRESENT) &&
                        entry.has(DBContract.AttendanceEntry.COL_LATE) &&
                        entry.has(DBContract.AttendanceEntry.COL_ABSENT) &&
                        entry.has(DBContract.AttendanceEntry.COL_NOTE));
            }

            default:
                return false;
        }
    }
}
