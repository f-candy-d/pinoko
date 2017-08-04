package com.f_candy_d.pinoko.utils;

import android.app.FragmentTransaction;

import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.EventFormer;
import com.f_candy_d.pinoko.model.NotificationFormer;
import com.f_candy_d.pinoko.model.TimeBlockFormer;

/**
 * Created by daichi on 17/08/04.
 * Helper methods for getting or setting an attribute
 */

public class EntryHelper {

    /**
     * For Course Entry
     */
    public static long getCourseId(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_ID);
    }

    public static long getCourseId(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_ID, def);
    }

    public static String getCourseName(final Entry entry) {
        return entry.getString(DBContract.CourseEntry.ATTR_NAME);
    }

    public static String getCourseName(final Entry entry, final String def) {
        return entry.getString(DBContract.CourseEntry.ATTR_NAME, def);
    }

    public static long getCourseLocationIdA(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_LOCATION_ID_A);
    }

    public static long getCourseLocationIdA(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_LOCATION_ID_A, def);
    }

    public static long getCourseLocationIdB(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_LOCATION_ID_B);
    }

    public static long getCourseLocationIdB(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_LOCATION_ID_B, def);
    }

    public static long getCourseInstructorIdA(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_A);
    }

    public static long getCourseInstructorIdA(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_A);
    }

    public static long getCourseInstructorIdB(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_B);
    }

    public static long getCourseInstructorIdB(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_B);
    }

    public static long getCourseInstructorIdC(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_C);
    }

    public static long getCourseInstructorIdC(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID_C);
    }

    public static int getCourseLength(final Entry entry) {
        return entry.getInt(DBContract.CourseEntry.ATTR_LENGTH);
    }

    public static int getCourseLength(final Entry entry, final int def) {
        return entry.getInt(DBContract.CourseEntry.ATTR_LENGTH, def);
    }

    public static String getCourseNote(final Entry entry) {
        return entry.getString(DBContract.CourseEntry.ATTR_NOTE);
    }

    public static String getCourseNote(final Entry entry, final String def) {
        return entry.getString(DBContract.CourseEntry.ATTR_NOTE, def);
    }

    /**
     * For Location Entry
     */
    public static long getLocationId(final Entry entry) {
        return entry.getLong(DBContract.LocationEntry.ATTR_ID);
    }

    public static long getLocationId(final Entry entry, final long def) {
        return entry.getLong(DBContract.LocationEntry.ATTR_ID, def);
    }

    public static String getLocationName(final Entry entry) {
        return entry.getString(DBContract.LocationEntry.ATTR_NAME);
    }

    public static String getLocationName(final Entry entry, final String def) {
        return entry.getString(DBContract.LocationEntry.ATTR_NAME, def);
    }

    public static String getLocationNote(final Entry entry) {
        return entry.getString(DBContract.LocationEntry.ATTR_NOTE);
    }

    public static String getLocationNote(final Entry entry, final String def) {
        return entry.getString(DBContract.LocationEntry.ATTR_NOTE, def);
    }

    /**
     * For TimeBlock Entry
     */

    public static long getTimeBlockId(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_ID);
    }

    public static long getTimeBlockId(final Entry entry, final long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_ID, def);
    }

    public static TimeBlockFormer.Type getTimeBlockType(final Entry entry) {
        return TimeBlockFormer.Type.from(entry.getInt(DBContract.TimeBlockEntry.ATTR_TYPE));
    }

    public static TimeBlockFormer.Type getTimeBlockType(final Entry entry, final TimeBlockFormer.Type def) {
        return TimeBlockFormer.Type.from(entry.getInt(DBContract.TimeBlockEntry.ATTR_TYPE, def.toInt()));
    }

    public static TimeBlockFormer.Category getTimeBlockCategory(final Entry entry) {
        return TimeBlockFormer.Category.from(entry.getInt(DBContract.TimeBlockEntry.ATTR_CATEGORY));
    }

    public static TimeBlockFormer.Category getTimeBlockCategory(final Entry entry, final TimeBlockFormer.Category def) {
        return TimeBlockFormer.Category.from(entry.getInt(DBContract.TimeBlockEntry.ATTR_CATEGORY, def.toInt()));
    }

    public static long getTimeBlockTargetId(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_TARGET_ID);
    }

    public static long getTimeBlockTargetId(final Entry entry, final long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_TARGET_ID, def);
    }

    public static long getTimeBlockDatetimeBegin(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN);
    }

    public static long getTimeBlockDatetimeBegin(final Entry entry, final  long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN, def);
    }

    public static long getTimeBlockDatetimeEnd(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_END);
    }

    public static long getTimeBlockDatetimeEnd(final Entry entry, final  long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_END, def);
    }

    public static int getTimeBlockTimeTableId(final Entry entry) {
        return entry.getInt(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID);
    }

    public static int getTimeBlockTimeTableId(final Entry entry, final int def) {
        return entry.getInt(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID, def);
    }

    /**
     * For assignment entry
     */
    public static long getAssignmentId(final Entry entry) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_ID);
    }

    public static long getAssignmentId(final Entry entry, final long def) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_ID, def);
    }

    public static String getAssignmentName(final Entry entry) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NAME);
    }

    public static String getAssignmentName(final Entry entry, final String def) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NAME, def);
    }

    public static long getAssignmentTimeBlockId(final Entry entry) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID);
    }

    public static long getAssignmentTimeBlockId(final Entry entry, final long def) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID, def);
    }

    public static String getAssignmentNote(final Entry entry) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NOTE);
    }

    public static String getAssignmentNote(final Entry entry, final String def) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NOTE, def);
    }

    public static boolean getAssignmentIsDone(final Entry entry) {
        return entry.getBool(DBContract.AssignmentEntry.ATTR_IS_DONE);
    }

    public static boolean getAssignmentIsDone(final Entry entry, final boolean def) {
        return entry.getBool(DBContract.AssignmentEntry.ATTR_IS_DONE, def);
    }

    /**
     * For event entry
     */
    public static long getEventId(final Entry entry) {
        return entry.getLong(DBContract.EventEntry.ATTR_ID);
    }

    public static long getEventId(final Entry entry, final long def) {
        return entry.getLong(DBContract.EventEntry.ATTR_ID, def);
    }

    public static String getEventName(final Entry entry) {
        return entry.getString(DBContract.EventEntry.ATTR_NAME);
    }

    public static String getEventName(final Entry entry, final String def) {
        return entry.getString(DBContract.EventEntry.ATTR_NAME, def);
    }

    public static long getEventLocationId(final Entry entry) {
        return entry.getLong(DBContract.EventEntry.ATTR_LOCATION_ID);
    }

    public static long getEventLocationId(final Entry entry, final long def) {
        return entry.getLong(DBContract.EventEntry.ATTR_LOCATION_ID, def);
    }

    public static String getEventNote(final Entry entry) {
        return entry.getString(DBContract.EventEntry.ATTR_NOTE);
    }

    public static String getEventNote(final Entry entry, final String note) {
        return entry.getString(DBContract.EventEntry.ATTR_NOTE, note);
    }

    /**
     * For notification entry
     */
    public static long getNotificationId(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_ID, def);
    }

    public static long getNotificationId(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_ID);
    }

    public static String getNotificationName(final Entry entry, final String def) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NAME, def);
    }

    public static String getNotificationName(final Entry entry) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NAME);
    }

    public static String getNotificationNote(final Entry entry, final String def) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NOTE, def);
    }

    public static String getNotificationNote(final Entry entry) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NOTE);
    }

    public static NotificationFormer.Category getNotificationCategory(final Entry entry, final NotificationFormer.Category def) {
        return NotificationFormer.Category.from(entry.getInt(DBContract.NotificationEntry.ATTR_CATEGORY, def.toInt()));
    }

    public static NotificationFormer.Category getNotificationCategory(final Entry entry) {
        return NotificationFormer.Category.from(entry.getInt(DBContract.NotificationEntry.ATTR_CATEGORY));
    }

    public static long getNotificationTargetId(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_TARGET_ID, def);
    }

    public static long getNotificationTargetId(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_TARGET_ID);
    }

    public static NotificationFormer.Type getNotificationType(final Entry entry, final NotificationFormer.Type def) {
        return NotificationFormer.Type.from(entry.getInt(DBContract.NotificationEntry.ATTR_TYPE, def.toInt()));
    }

    public static NotificationFormer.Type getNotificationType(final Entry entry) {
        return NotificationFormer.Type.from(entry.getInt(DBContract.NotificationEntry.ATTR_TYPE));
    }

    public static boolean getNotificationIsDone(final Entry entry, final boolean def) {
        return entry.getBool(DBContract.NotificationEntry.ATTR_IS_DONE, def);
    }

    public static boolean getNotificationIsDone(final Entry entry) {
        return entry.getBool(DBContract.NotificationEntry.ATTR_IS_DONE);
    }

    public static long getNotificationDatetimeBegin(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN, def);
    }

    public static long getNotificationDatetimeBegin(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN);
    }

    public static long getNotificationDatetimeEnd(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN, def);
    }

    public static long getNotificationDatetimeEnd(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN);
    }

    /**
     * For attendance entry
     */
    public static long getAttendanceId(final Entry entry, final long def) {
        return entry.getLong(DBContract.AttendanceEntry.ATTR_ID, def);
    }

    public static long getAttendanceId(final Entry entry) {
        return entry.getLong(DBContract.AttendanceEntry.ATTR_ID);
    }

    public static long getAttendanceTimeBlockId(final Entry entry, final long def) {
        return entry.getLong(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID, def);
    }

    public static long getAttendanceTimeBlockId(final Entry entry) {
        return entry.getLong(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID);
    }

    public static int getAttendancePresent(final Entry entry, final int def) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_PRESENT, def);
    }

    public static int getAttendancePresent(final Entry entry) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_PRESENT);
    }

    public static int getAttendanceLate(final Entry entry, final int def) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_LATE, def);
    }

    public static int getAttendanceLate(final Entry entry) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_LATE);
    }

    public static int getAttendanceAbsent(final Entry entry, final int def) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_ABSENT, def);
    }

    public static int getAttendanceAbsent(final Entry entry) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_ABSENT);
    }

    public static String getAttendanceNote(final Entry entry, final String def) {
        return entry.getString(DBContract.AttendanceEntry.ATTR_NOTE, def);
    }

    public static String getAttendanceNote(final Entry entry) {
        return entry.getString(DBContract.AttendanceEntry.ATTR_NOTE);
    }
}
