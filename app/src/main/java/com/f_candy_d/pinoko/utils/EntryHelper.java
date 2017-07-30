package com.f_candy_d.pinoko.utils;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.Assignment;
import com.f_candy_d.pinoko.model.Attendance;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.FlexibleEntry;
import com.f_candy_d.pinoko.model.Instructor;
import com.f_candy_d.pinoko.model.Location;
import com.f_candy_d.pinoko.model.Notification;
import com.f_candy_d.pinoko.model.TimeBlock;

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

    public static Course makeCourseEntry(@NonNull final Cursor cursor) {
        Course course = new Course();
        course.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_NAME)));
        course.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_NOTE)));
        course.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry._ID)));
        course.setLocationIDA(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_LOCATION_ID_A)));
        course.setLocationIDB(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_LOCATION_ID_B)));
        course.setInstructorIDA(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_INSTRUCTOR_ID_A)));
        course.setInstructorIDB(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_INSTRUCTOR_ID_B)));
        course.setInstructorIDC(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_INSTRUCTOR_ID_C)));
        course.setLength(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.COL_LENGTH)));

        return course;
    }

    public static Location makeLocationEntry(@NonNull final Cursor cursor) {
        Location location = new Location();
        location.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.COL_NAME)));
        location.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.COL_NOTE)));
        location.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.LocationEntry._ID)));

        return location;
    }

    public static Instructor makeInstructorEntry(@NonNull final Cursor cursor) {
        Instructor instructor = new Instructor();
        instructor.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_NAME)));
        instructor.setLab(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_LAB)));
        instructor.setMail(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_MAIL)));
        instructor.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_PHONE_NUMBER)));
        instructor.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.COL_NOTE)));
        instructor.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry._ID)));

        return instructor;
    }

    public static TimeBlock makeTimeBlockEntry(@NonNull final Cursor cursor) {
        TimeBlock timeBlock = new TimeBlock();
        timeBlock.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry._ID)));
        timeBlock.setType(TimeBlock.Type.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_TYPE))));
        timeBlock.setCategory(TimeBlock.Category.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_CATEGORY))));
        timeBlock.setTargetID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_TARGET_ID)));
        timeBlock.setDatetimeBegin(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_DATETIME_BEGIN)));
        timeBlock.setDatetimeEnd(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_DATETIME_END)));
        timeBlock.setTimeTableID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.COL_TIME_TABLE_ID)));

        return timeBlock;
    }

    public static Assignment makeAssignmentEntry(@NonNull final Cursor cursor) {
        Assignment assignment = new Assignment();
        assignment.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_NAME)));
        assignment.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_NOTE)));
        assignment.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry._ID)));
        assignment.setTimeBlockID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_TIME_BLOCK_ID)));
        final int isDone = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.COL_IS_DONE));
        assignment.setIsDone(isDone != 0);

        return assignment;
    }

    public static Event makeEventEntry(@NonNull final Cursor cursor) {
        Event event = new Event();
        event.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EventEntry.COL_NAME)));
        event.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EventEntry.COL_NOTE)));
        event.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.EventEntry._ID)));
        event.setLocationID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.EventEntry.COL_LOCATION_ID)));

        return event;
    }

    public static Notification makeNotificationEntry(@NonNull final Cursor cursor) {
        Notification notification = new Notification();
        notification.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_NAME)));
        notification.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_NOTE)));
        notification.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry._ID)));
        notification.setCategory(Notification.Category.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_CATEGORY))));
        notification.setTargetID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_TARGET_ID)));
        notification.setType(Notification.Type.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_TYPE))));
        notification.setDatetimeBegin(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_DATETIME_BEGIN)));
        notification.setDatetimeEnd(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_DATETIME_END)));
        final int isDone = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.COL_IS_DONE));
        notification.setIsDone(isDone != 0);

        return notification;
    }

    public static Attendance makeAttendanceEntry(@NonNull final Cursor cursor) {
        Attendance attendance = new Attendance();
        attendance.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_NOTE)));
        attendance.setID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry._ID)));
        attendance.setTimeBlockID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID)));
        attendance.setPresent(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_PRESENT)));
        attendance.setLate(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_LATE)));
        attendance.setAbsent(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.COL_ABSENT)));

        return attendance;
    }
}
