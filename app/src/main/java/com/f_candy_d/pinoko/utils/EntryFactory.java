package com.f_candy_d.pinoko.utils;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.AssignmentFormer;
import com.f_candy_d.pinoko.model.AttendanceFormer;
import com.f_candy_d.pinoko.model.CourseFormer;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.EventFormer;
import com.f_candy_d.pinoko.model.InstructorFormer;
import com.f_candy_d.pinoko.model.LocationFormer;
import com.f_candy_d.pinoko.model.NotificationFormer;
import com.f_candy_d.pinoko.model.TimeBlockFormer;

/**
 * Created by daichi on 7/30/17.
 */

public class EntryFactory {

    /**
     * Create an entry object from 'cursor' which contains results of SQL:SELECT
     */

    public static Entry makeBasicEntry(@NonNull final String tableName, @NonNull final Cursor cursor) {
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
        CourseFormer course = CourseFormer.createWithEntry();
        course.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.ATTR_NAME)));
        course.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.ATTR_NOTE)));
        course.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.ATTR_ID)));
        course.setLocationId(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.ATTR_LOCATION_ID)));
        course.setInstructorId(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID)));
        course.setLength(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.CourseEntry.ATTR_LENGTH)));

        return course.getEntry();
    }

    public static Entry makeLocationEntry(@NonNull final Cursor cursor) {
        LocationFormer location = LocationFormer.createWithEntry();
        location.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.ATTR_NAME)));
        location.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.ATTR_NOTE)));
        location.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.LocationEntry.ATTR_ID)));

        return location.getEntry();
    }

    public static Entry makeInstructorEntry(@NonNull final Cursor cursor) {
        InstructorFormer instructor = InstructorFormer.createWithEntry();
        instructor.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.ATTR_NAME)));
        instructor.setLab(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.ATTR_LAB)));
        instructor.setMail(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.ATTR_MAIL)));
        instructor.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.ATTR_PHONE_NUMBER)));
        instructor.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.ATTR_NOTE)));
        instructor.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.InstructorEntry.ATTR_ID)));

        return instructor.getEntry();
    }

    public static Entry makeTimeBlockEntry(@NonNull final Cursor cursor) {
        TimeBlockFormer timeBlock = TimeBlockFormer.createWithEntry();
        timeBlock.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_ID)));
        timeBlock.setType(TimeBlockFormer.Type.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_TYPE))));
        timeBlock.setCategory(TimeBlockFormer.Category.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_CATEGORY))));
        timeBlock.setTargetID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_TARGET_ID)));
        timeBlock.setDatetimeBegin(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN)));
        timeBlock.setDatetimeEnd(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_DATETIME_END)));
        timeBlock.setTimeTableID(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID)));

        return timeBlock.getEntry();
    }

    public static Entry makeAssignmentEntry(@NonNull final Cursor cursor) {
        AssignmentFormer assignment = AssignmentFormer.createWithEntry();
        assignment.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.ATTR_NAME)));
        assignment.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.ATTR_NOTE)));
        assignment.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.ATTR_ID)));
        assignment.setTimeBlockID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID)));
        assignment.setDeadline(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.ATTR_DEADLINE)));
        final int isDone = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AssignmentEntry.ATTR_IS_DONE));
        assignment.setIsDone(isDone != 0);

        return assignment.getEntry();
    }

    public static Entry makeEventEntry(@NonNull final Cursor cursor) {
        EventFormer event = EventFormer.createWithEntry();
        event.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EventEntry.ATTR_NAME)));
        event.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EventEntry.ATTR_NOTE)));
        event.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.EventEntry.ATTR_ID)));
        event.setLocationID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.EventEntry.ATTR_LOCATION_ID)));

        return event.getEntry();
    }

    public static Entry makeNotificationEntry(@NonNull final Cursor cursor) {
        NotificationFormer notification = NotificationFormer.createWithEntry();
        notification.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_NAME)));
        notification.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_NOTE)));
        notification.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_ID)));
        notification.setCategory(NotificationFormer.Category.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_CATEGORY))));
        notification.setTargetID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_TARGET_ID)));
        notification.setType(NotificationFormer.Type.from(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_TYPE))));
        notification.setDatetimeBegin(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_DATETIME_BEGIN)));
        notification.setDatetimeEnd(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_DATETIME_END)));
        final int isDone = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.NotificationEntry.ATTR_IS_DONE));
        notification.setIsDone(isDone != 0);

        return notification.getEntry();
    }

    public static Entry makeAttendanceEntry(@NonNull final Cursor cursor) {
        AttendanceFormer attendance = AttendanceFormer.createWithEntry();
        attendance.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.ATTR_NOTE)));
        attendance.setID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.ATTR_ID)));
        attendance.setTimeBlockID(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.ATTR_TIME_BLOCK_ID)));
        attendance.setPresent(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.ATTR_PRESENT)));
        attendance.setLate(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.ATTR_LATE)));
        attendance.setAbsent(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.AttendanceEntry.ATTR_ABSENT)));

        return attendance.getEntry();
    }
}
