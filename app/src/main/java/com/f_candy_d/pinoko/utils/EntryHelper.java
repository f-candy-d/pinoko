package com.f_candy_d.pinoko.utils;


import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.model.Entry;

import java.io.Serializable;

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

    public static void setCourseId(final Entry entry, final long id) {
        entry.set(DBContract.CourseEntry.ATTR_ID, id);
    }

    public static String getCourseName(final Entry entry) {
        return entry.getString(DBContract.CourseEntry.ATTR_NAME);
    }

    public static String getCourseName(final Entry entry, final String def) {
        return entry.getString(DBContract.CourseEntry.ATTR_NAME, def);
    }

    public static void setCourseName(final Entry entry, final String name) {
        entry.set(DBContract.CourseEntry.ATTR_NAME, name);
    }

    public static long getCourseLocationId(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_LOCATION_ID);
    }

    public static long getCourseLocationId(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_LOCATION_ID, def);
    }

    public static void setCourseLocationId(final Entry entry, final long locId) {
        entry.set(DBContract.CourseEntry.ATTR_LOCATION_ID, locId);
    }

    public static long getCourseInstructorId(final Entry entry) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID);
    }

    public static long getCourseInstructorId(final Entry entry, final long def) {
        return entry.getLong(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID);
    }

    public static void setCourseInstructorId(final Entry entry, final long instId) {
        entry.set(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID, instId);
    }

    public static String getCourseNote(final Entry entry) {
        return entry.getString(DBContract.CourseEntry.ATTR_NOTE);
    }

    public static String getCourseNote(final Entry entry, final String def) {
        return entry.getString(DBContract.CourseEntry.ATTR_NOTE, def);
    }

    public static void setCourseNote(final Entry entry, final String note) {
        entry.set(DBContract.CourseEntry.ATTR_NOTE, note);
    }

    /**
     * For instructor entry
     */
    public static long getInstructorId(final Entry entry) {
        return entry.getLong(DBContract.InstructorEntry.ATTR_ID);
    }

    public static long getInstructorId(final Entry entry, final long def) {
        return entry.getLong(DBContract.InstructorEntry.ATTR_ID, def);
    }

    public static void setInstructorId(final Entry entry, final long id) {
        entry.set(DBContract.InstructorEntry.ATTR_ID, id);
    }

    public static String getInstructorName(final Entry entry) {
        return entry.getString(DBContract.InstructorEntry.ATTR_NAME);
    }

    public static String getInstructorName(final Entry entry, final String def) {
        return entry.getString(DBContract.InstructorEntry.ATTR_NAME, def);
    }

    public static void setInstructorName(final Entry entry, final String name) {
        entry.set(DBContract.InstructorEntry.ATTR_NAME, name);
    }

    public static String getInstructorLab(final Entry entry) {
        return entry.getString(DBContract.InstructorEntry.ATTR_LAB);
    }

    public static String getInstructorLab(final Entry entry, final String def) {
        return entry.getString(DBContract.InstructorEntry.ATTR_LAB, def);
    }

    public static void setInstructorLab(final Entry entry, final String lab) {
        entry.set(DBContract.InstructorEntry.ATTR_LAB, lab);
    }

    public static String getInstructorMail(final Entry entry) {
        return entry.getString(DBContract.InstructorEntry.ATTR_MAIL);
    }

    public static String getInstructorMail(final Entry entry, final String def) {
        return entry.getString(DBContract.InstructorEntry.ATTR_MAIL, def);
    }

    public static void setInstructorMail(final Entry entry, final String mail) {
        entry.set(DBContract.InstructorEntry.ATTR_MAIL, mail);
    }

    public static String getInstructorPhoneNumber(final Entry entry) {
        return entry.getString(DBContract.InstructorEntry.ATTR_PHONE_NUMBER);
    }

    public static String getInstructorPhoneNumber(final Entry entry, final String def) {
        return entry.getString(DBContract.InstructorEntry.ATTR_PHONE_NUMBER, def);
    }

    public static void setInstructorPhoneNumber(final Entry entry, final String phoneNumber) {
        entry.set(DBContract.InstructorEntry.ATTR_PHONE_NUMBER, phoneNumber);
    }

    public static String getInstructorNote(final Entry entry) {
        return entry.getString(DBContract.InstructorEntry.ATTR_NOTE);
    }

    public static String getInstructorNote(final Entry entry, final String def) {
        return entry.getString(DBContract.InstructorEntry.ATTR_NOTE, def);
    }

    public static void setInstructorNote(final Entry entry, final String note) {
        entry.set(DBContract.InstructorEntry.ATTR_NOTE, note);
    }

    /**
     * For location entry
     */
    public static long getLocationId(final Entry entry) {
        return entry.getLong(DBContract.LocationEntry.ATTR_ID);
    }

    public static long getLocationId(final Entry entry, final long def) {
        return entry.getLong(DBContract.LocationEntry.ATTR_ID, def);
    }

    public static void setLocationId(final Entry entry, final long id) {
        entry.set(DBContract.LocationEntry.ATTR_ID, id);
    }

    public static String getLocationName(final Entry entry) {
        return entry.getString(DBContract.LocationEntry.ATTR_NAME);
    }

    public static String getLocationName(final Entry entry, final String def) {
        return entry.getString(DBContract.LocationEntry.ATTR_NAME, def);
    }

    public static void setLocationName(final Entry entry, final String name) {
        entry.set(DBContract.LocationEntry.ATTR_NAME, name);
    }

    public static String getLocationNote(final Entry entry) {
        return entry.getString(DBContract.LocationEntry.ATTR_NOTE);
    }

    public static String getLocationNote(final Entry entry, final String def) {
        return entry.getString(DBContract.LocationEntry.ATTR_NOTE, def);
    }

    public static void setLocationNote(final Entry entry, final String note) {
        entry.set(DBContract.LocationEntry.ATTR_NOTE, note);
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

    public static void setTimeBlockId(final Entry entry, final long id) {
        entry.set(DBContract.TimeBlockEntry.ATTR_ID, id);
    }

    public static TimeBlockFormer.Type getTimeBlockType(final Entry entry) {
        final Serializable value = entry.getSerializable(DBContract.TimeBlockEntry.ATTR_TYPE);
        if (value instanceof TimeBlockFormer.Type) {
            return (TimeBlockFormer.Type) value;
        } else {
            throw new ClassCastException("The object for key TimeBlockEntry.ATTR_TYPE is not a instance of TimeBlockFormer.Type");
        }
    }

    public static void setTimeBlockType(final Entry entry, final TimeBlockFormer.Type type) {
        entry.set(DBContract.TimeBlockEntry.ATTR_TYPE, type);
    }

    public static TimeBlockFormer.Category getTimeBlockCategory(final Entry entry) {
        final Serializable value = entry.getSerializable(DBContract.TimeBlockEntry.ATTR_CATEGORY);
        if (value instanceof TimeBlockFormer.Category) {
            return (TimeBlockFormer.Category) value;
        } else {
            throw new ClassCastException("The object for key TimeBlockEntry.ATTR_CATEGORY is not a instance of TimeBlockFormer.Category");
        }
    }

    public static void setTimeBlockCategory(final Entry entry, TimeBlockFormer.Category category) {
        entry.set(DBContract.TimeBlockEntry.ATTR_CATEGORY, category);
    }

    public static DayOfWeek getTimeBlockDayOfWeek(final Entry entry) {
        final Serializable value = entry.getSerializable(DBContract.TimeBlockEntry.ATTR_DAY_OF_WEEK);
        if (value instanceof DayOfWeek) {
            return (DayOfWeek) value;
        } else {
            throw new ClassCastException("The object for key TimeBlockEntry.ATTR_DAY_OF_WEEK is not a instance of DayOfWeek");
        }
    }

    public static void setTimeBlockDayOfWeek(final Entry entry, DayOfWeek dayOfWeek) {
        entry.set(DBContract.TimeBlockEntry.ATTR_DAY_OF_WEEK, dayOfWeek);
    }

    public static long getTimeBlockTargetId(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_TARGET_ID);
    }

    public static long getTimeBlockTargetId(final Entry entry, final long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_TARGET_ID, def);
    }

    public static void setTImeBlockTargetId(final Entry entry, final long targetId) {
        entry.set(DBContract.TimeBlockEntry.ATTR_TARGET_ID, targetId);
    }

    public static long getTimeBlockDatetimeBegin(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN);
    }

    public static long getTimeBlockDatetimeBegin(final Entry entry, final long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN, def);
    }

    public static void setTimeBlockDatetimeBegin(final Entry entry, final long datetimeBegin) {
        entry.set(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN, datetimeBegin);
    }

    public static long getTimeBlockDatetimeEnd(final Entry entry) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_END);
    }

    public static long getTimeBlockDatetimeEnd(final Entry entry, final  long def) {
        return entry.getLong(DBContract.TimeBlockEntry.ATTR_DATETIME_END, def);
    }

    public static void setTimeBlockDatetimeEnd(final Entry entry, final long datetimeEnd) {
        entry.set(DBContract.TimeBlockEntry.ATTR_DATETIME_END, datetimeEnd);
    }

    public static int getTimeBlockTimeTableId(final Entry entry) {
        return entry.getInt(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID);
    }

    public static int getTimeBlockTimeTableId(final Entry entry, final int def) {
        return entry.getInt(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID, def);
    }

    public static void setTimeBlockTimeTableId(final Entry entry, final int timetableId) {
        entry.set(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID, timetableId);
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

    public static void setAssignmentId(final Entry entry, final long id) {
        entry.set(DBContract.AssignmentEntry.ATTR_ID, id);
    }

    public static String getAssignmentName(final Entry entry) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NAME);
    }

    public static String getAssignmentName(final Entry entry, final String def) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NAME, def);
    }

    public static void setAssignmentName(final Entry entry, final String name) {
        entry.set(DBContract.AssignmentEntry.ATTR_NAME, name);
    }

    public static long getAssignmentTimeBlockId(final Entry entry) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID);
    }

    public static long getAssignmentTimeBlockId(final Entry entry, final long def) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID, def);
    }

    public static void setAssignmentTimeBlockId(final Entry entry, final long timeBlockId) {
        entry.set(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID, timeBlockId);
    }

    public static String getAssignmentNote(final Entry entry) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NOTE);
    }

    public static String getAssignmentNote(final Entry entry, final String def) {
        return entry.getString(DBContract.AssignmentEntry.ATTR_NOTE, def);
    }

    public static void setAssignmentNote(final Entry entry, final String note) {
        entry.set(DBContract.AssignmentEntry.ATTR_NOTE, note);
    }

    public static boolean getAssignmentIsDone(final Entry entry) {
        return entry.getBool(DBContract.AssignmentEntry.ATTR_IS_DONE);
    }

    public static boolean getAssignmentIsDone(final Entry entry, final boolean def) {
        return entry.getBool(DBContract.AssignmentEntry.ATTR_IS_DONE, def);
    }

    public static void setAssignmentIsDone(final Entry entry, final boolean isDone) {
        entry.set(DBContract.AssignmentEntry.ATTR_IS_DONE, isDone);
    }

    public static long getAssignmentDeadline(final Entry entry, final long def) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_DEADLINE, def);
    }

    public static long getAssignmentDeadline(final Entry entry) {
        return entry.getLong(DBContract.AssignmentEntry.ATTR_DEADLINE);
    }

    public static void setAssignmentDeadline(final Entry entry, final long deadline) {
        entry.set(DBContract.AssignmentEntry.ATTR_DEADLINE, deadline);
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

    public static void setEventId(final Entry entry, final long id) {
        entry.set(DBContract.EventEntry.ATTR_ID, id);
    }

    public static String getEventName(final Entry entry) {
        return entry.getString(DBContract.EventEntry.ATTR_NAME);
    }

    public static String getEventName(final Entry entry, final String def) {
        return entry.getString(DBContract.EventEntry.ATTR_NAME, def);
    }

    public static void setEventName(final Entry entry, final String name) {
        entry.set(DBContract.EventEntry.ATTR_NAME, name);
    }

    public static long getEventLocationId(final Entry entry) {
        return entry.getLong(DBContract.EventEntry.ATTR_LOCATION_ID);
    }

    public static long getEventLocationId(final Entry entry, final long def) {
        return entry.getLong(DBContract.EventEntry.ATTR_LOCATION_ID, def);
    }

    public static void setEventLocationId(final Entry entry, final long locationId) {
        entry.set(DBContract.EventEntry.ATTR_LOCATION_ID, locationId);
    }

    public static String getEventNote(final Entry entry) {
        return entry.getString(DBContract.EventEntry.ATTR_NOTE);
    }

    public static String getEventNote(final Entry entry, final String def) {
        return entry.getString(DBContract.EventEntry.ATTR_NOTE, def);
    }

    public static void setEventNote(final Entry entry, final String note) {
        entry.set(DBContract.EventEntry.ATTR_NOTE, note);
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

    public static void setNotificationId(final Entry entry, final long id) {
        entry.set(DBContract.NotificationEntry.ATTR_ID, id);
    }

    public static String getNotificationName(final Entry entry, final String def) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NAME, def);
    }

    public static String getNotificationName(final Entry entry) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NAME);
    }

    public static void setNotificationName(final Entry entry, final String name) {
        entry.set(DBContract.NotificationEntry.ATTR_NAME, name);
    }

    public static String getNotificationNote(final Entry entry, final String def) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NOTE, def);
    }

    public static String getNotificationNote(final Entry entry) {
        return entry.getString(DBContract.NotificationEntry.ATTR_NOTE);
    }

    public static void setNotificationNote(final Entry entry, final String note) {
        entry.set(DBContract.NotificationEntry.ATTR_NOTE, note);
    }

    public static NotificationFormer.Category getNotificationCategory(final Entry entry) {
        final Serializable value = entry.getSerializable(DBContract.NotificationEntry.ATTR_CATEGORY);
        if (value instanceof NotificationFormer.Category) {
            return (NotificationFormer.Category) value;
        } else {
            throw new ClassCastException("The object for key NotificationEntry.ATTR_CATEGORY is not an instance of NotificationFormer.Category");
        }
    }

    public static void setNotificationCategory(final Entry entry, final NotificationFormer.Category category) {
        entry.set(DBContract.NotificationEntry.ATTR_CATEGORY, category);
    }

    public static long getNotificationTargetId(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_TARGET_ID, def);
    }

    public static long getNotificationTargetId(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_TARGET_ID);
    }

    public static void setNotificationTargetId(final Entry entry, final long targetId) {
        entry.set(DBContract.NotificationEntry.ATTR_TARGET_ID, targetId);
    }

    public static NotificationFormer.Type getNotificationType(final Entry entry) {
        final Serializable value = entry.getSerializable(DBContract.NotificationEntry.ATTR_TYPE);
        if (value instanceof NotificationFormer.Type) {
            return (NotificationFormer.Type) value;
        } else {
            throw new ClassCastException("The object for key NotificationEntry.ATTR_TYPE is not an instance of NotificationFormer.Type");
        }
    }

    public static void setNotificationType(final Entry entry, NotificationFormer.Type type) {
        entry.set(DBContract.NotificationEntry.ATTR_TYPE, type);
    }

    public static boolean getNotificationIsDone(final Entry entry, final boolean def) {
        return entry.getBool(DBContract.NotificationEntry.ATTR_IS_DONE, def);
    }

    public static boolean getNotificationIsDone(final Entry entry) {
        return entry.getBool(DBContract.NotificationEntry.ATTR_IS_DONE);
    }

    public static void setNotificationIsDone(final Entry entry, final boolean isDone) {
        entry.set(DBContract.NotificationEntry.ATTR_IS_DONE, isDone);
    }

    public static long getNotificationDatetimeBegin(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN, def);
    }

    public static long getNotificationDatetimeBegin(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN);
    }

    public static void setNotificationDatetimeBegin(final Entry entry, final long datetimeBegin) {
        entry.set(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN, datetimeBegin);
    }

    public static long getNotificationDatetimeEnd(final Entry entry, final long def) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN, def);
    }

    public static long getNotificationDatetimeEnd(final Entry entry) {
        return entry.getLong(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN);
    }

    public static void setNotificationDatetimeEnd(final Entry entry, final long datetimeEnd) {
        entry.set(DBContract.NotificationEntry.ATTR_DATETIME_END, datetimeEnd);
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

    public static void setAttendanceId(final Entry entry, final long id) {
        entry.set(DBContract.AttendanceEntry.ATTR_ID, id);
    }

    public static long getAttendanceTimeBlockId(final Entry entry, final long def) {
        return entry.getLong(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID, def);
    }

    public static long getAttendanceTimeBlockId(final Entry entry) {
        return entry.getLong(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID);
    }

    public static void setAttendanceTimeBlockId(final Entry entry, final long timeBlockId) {
        entry.set(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID, timeBlockId);
    }

    public static int getAttendancePresent(final Entry entry, final int def) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_PRESENT, def);
    }

    public static int getAttendancePresent(final Entry entry) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_PRESENT);
    }

    public static void setAttendancePresent(final Entry entry, final int present) {
        entry.set(DBContract.AttendanceEntry.ATTR_PRESENT, present);
    }

    public static int getAttendanceLate(final Entry entry, final int def) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_LATE, def);
    }

    public static int getAttendanceLate(final Entry entry) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_LATE);
    }

    public static void setAttendanceLate(final Entry entry, final int late) {
        entry.set(DBContract.AttendanceEntry.ATTR_LATE, late);
    }

    public static int getAttendanceAbsent(final Entry entry, final int def) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_ABSENT, def);
    }

    public static int getAttendanceAbsent(final Entry entry) {
        return entry.getInt(DBContract.AttendanceEntry.ATTR_ABSENT);
    }

    public static void setAttendanceAbsent(final Entry entry, final int absent) {
        entry.set(DBContract.AttendanceEntry.ATTR_ABSENT, absent);
    }

    public static String getAttendanceNote(final Entry entry, final String def) {
        return entry.getString(DBContract.AttendanceEntry.ATTR_NOTE, def);
    }

    public static String getAttendanceNote(final Entry entry) {
        return entry.getString(DBContract.AttendanceEntry.ATTR_NOTE);
    }

    public static void setAttendanceNote(final Entry entry, final String note) {
        entry.set(DBContract.AttendanceEntry.ATTR_NOTE, note);
    }
}
