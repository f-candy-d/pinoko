package com.f_candy_d.pinoko.utils;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.Entry;

/**
 * Created by daichi on 7/30/17.
 */

public class EntryHelper {

    public static Entry makeEntry(@NonNull final String tableName, @NonNull final Cursor cursor) {
        switch (tableName) {
            case DBContract.CourseEntry.TABLE_NAME:
                return makeCourseEntry(cursor);
            case DBContract.LocationEntry.TABLE_NAME:
                return makeLocationEntry(cursor);
            case DBContract.InstructorEntry.TABLE_NAME:
                return makeInstructorEntry(cursor);
            case DBContract.TimeBlockEntry.TABLE_NAME:
                return makeTimeBlockEntry(cursor);
            case DBContract.AssignmentEntry.TABLE_NAME:
                return makeAssignmentEntry(cursor);
            case DBContract.EventEntry.TABLE_NAME:
                return makeEventEntry(cursor);
            case DBContract.NotificationEntry.TABLE_NAME:
                return makeNotificationEntry(cursor);
            case DBContract.AttendanceEntry.TABLE_NAME:
                return makeAttendanceEntry(cursor);
            default:
                return null;
        }
    }

    public static Entry makeCourseEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.CourseEntry.TABLE_NAME);
        entry.set(DBContract.CourseEntry.COL_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_NAME)));
        entry.set(DBContract.CourseEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_NOTE)));
        entry.set(DBContract.CourseEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry._ID)));
        entry.set(DBContract.CourseEntry.COL_LOCATION_ID_A,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_LOCATION_ID_A)));
        entry.set(DBContract.CourseEntry.COL_LOCATION_ID_B,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_LOCATION_ID_B)));
        entry.set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A)));
        entry.set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B)));
        entry.set(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C)));
        entry.set(DBContract.CourseEntry.COL_LENGTH,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_LENGTH)));

        return entry;
    }

    public static Entry makeLocationEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.LocationEntry.TABLE_NAME);
        entry.set(DBContract.LocationEntry.COL_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.COL_NAME)));
        entry.set(DBContract.LocationEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.COL_NOTE)));
        entry.set(DBContract.LocationEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.LocationEntry._ID)));

        return entry;
    }

    public static Entry makeInstructorEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.InstructorEntry.TABLE_NAME);
        entry.set(DBContract.InstructorEntry.COL_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_NAME)));
        entry.set(DBContract.InstructorEntry.COL_LAB,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_LAB)));
        entry.set(DBContract.InstructorEntry.COL_MAIL,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_MAIL)));
        entry.set(DBContract.InstructorEntry.COL_PHONE_NUMBER,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_PHONE_NUMBER)));
        entry.set(DBContract.InstructorEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_NOTE)));
        entry.set(DBContract.InstructorEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry._ID)));

        return entry;
    }

    public static Entry makeTimeBlockEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.TimeBlockEntry.TABLE_NAME);
        entry.set(DBContract.TimeBlockEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry._ID)));
        entry.set(DBContract.TimeBlockEntry.COL_TYPE,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_TYPE)));
        entry.set(DBContract.TimeBlockEntry.COL_CATEGORY,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_CATEGORY)));
        entry.set(DBContract.TimeBlockEntry.COL_TARGET_ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_TARGET_ID)));
        entry.set(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN)));
        entry.set(DBContract.TimeBlockEntry.COL_DATETIME_END,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_DATETIME_END)));
        entry.set(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID)));

        return entry;
    }

    public static Entry makeAssignmentEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.AssignmentEntry.TABLE_NAME);
        entry.set(DBContract.AssignmentEntry.COL_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_NAME)));
        entry.set(DBContract.AssignmentEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_NOTE)));
        entry.set(DBContract.AssignmentEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry._ID)));
        entry.set(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID)));
        entry.set(DBContract.AssignmentEntry.COL_IS_DONE,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_IS_DONE)));

        return entry;
    }

    public static Entry makeEventEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.EventEntry.TABLE_NAME);
        entry.set(DBContract.EventEntry.COL_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EventEntry.COL_NAME)));
        entry.set(DBContract.EventEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EventEntry.COL_NOTE)));
        entry.set(DBContract.EventEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.EventEntry._ID)));
        entry.set(DBContract.EventEntry.COL_LOCATION_ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.EventEntry.COL_LOCATION_ID)));

        return entry;
    }

    public static Entry makeNotificationEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.NotificationEntry.TABLE_NAME);
        entry.set(DBContract.NotificationEntry.COL_NAME,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_NAME)));
        entry.set(DBContract.NotificationEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_NOTE)));
        entry.set(DBContract.NotificationEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry._ID)));
        entry.set(DBContract.NotificationEntry.COL_CATEGORY,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_CATEGORY)));
        entry.set(DBContract.NotificationEntry.COL_TARGET_ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_TARGET_ID)));
        entry.set(DBContract.NotificationEntry.COL_TYPE,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_TYPE)));
        entry.set(DBContract.NotificationEntry.COL_IS_DONE,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_IS_DONE)));
        entry.set(DBContract.NotificationEntry.COL_DATETIME_BEGIN,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_DATETIME_BEGIN)));
        entry.set(DBContract.NotificationEntry.COL_DATETIME_END,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_DATETIME_END)));

        return entry;
    }

    public static Entry makeAttendanceEntry(@NonNull final Cursor cursor) {
        Entry entry = new Entry(DBContract.AttendanceEntry.TABLE_NAME);
        entry.set(DBContract.AttendanceEntry.COL_NOTE,
                cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_NOTE)));
        entry.set(DBContract.AttendanceEntry._ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry._ID)));
        entry.set(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID)));
        entry.set(DBContract.AttendanceEntry.COL_PRESENT,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_PRESENT)));
        entry.set(DBContract.AttendanceEntry.COL_LATE,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_LATE)));
        entry.set(DBContract.AttendanceEntry.COL_ABSENT,
                cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_ABSENT)));

        return entry;
    }
}
