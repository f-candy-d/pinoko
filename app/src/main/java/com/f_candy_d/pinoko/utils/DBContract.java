package com.f_candy_d.pinoko.utils;

/**
 * Created by daichi on 7/30/17.
 */

public class DBContract {

    public static final int NULL_ID = 0;
    public static final int MIN_AVAILABLE_ID = 1;
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "pinoko_database.db";

    /**
     * Mapping each column names to a value type that used in program and that used in SQLite
     */
    public static final JSQLValueTypeMap ATTR_VALUE_TYPE_MAP;
    static {
        ATTR_VALUE_TYPE_MAP = new JSQLValueTypeMap();
        // Course Entry
        ATTR_VALUE_TYPE_MAP
                .put(CourseEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(CourseEntry.ATTR_NAME, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT_NOT_NULL)
                .put(CourseEntry.ATTR_LOCATION_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER)
                .put(CourseEntry.ATTR_INSTRUCTOR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER)
                .put(CourseEntry.ATTR_LENGTH, JSQLValueTypeMap.JavaValueType.INT, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(CourseEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                // Location Entry
                .put(LocationEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(LocationEntry.ATTR_NAME, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT_NOT_NULL)
                .put(LocationEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                // Instructor Entry
                .put(InstructorEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(InstructorEntry.ATTR_NAME, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT_NOT_NULL)
                .put(InstructorEntry.ATTR_LAB, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(InstructorEntry.ATTR_MAIL, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(InstructorEntry.ATTR_PHONE_NUMBER, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(InstructorEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                // TimeBlock Entry
                .put(TimeBlockEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(TimeBlockEntry.ATTR_TYPE, JSQLValueTypeMap.JavaValueType.TB_TYPE, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(TimeBlockEntry.ATTR_CATEGORY, JSQLValueTypeMap.JavaValueType.TB_CATEGORY, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(TimeBlockEntry.ATTR_TARGET_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(TimeBlockEntry.ATTR_DATETIME_BEGIN, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(TimeBlockEntry.ATTR_DATETIME_END, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(TimeBlockEntry.ATTR_TIME_TABLE_ID, JSQLValueTypeMap.JavaValueType.INT, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(TimeBlockEntry.ATTR_DAY_OF_WEEK, JSQLValueTypeMap.JavaValueType.DAY_OF_WEEK, JSQLValueTypeMap.SqlValueType.INTEGER)
                // Assignment Entry
                .put(AssignmentEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(AssignmentEntry.ATTR_NAME, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT_NOT_NULL)
                .put(AssignmentEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(AssignmentEntry.ATTR_IS_DONE, JSQLValueTypeMap.JavaValueType.BOOLEAN, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(AssignmentEntry.ATTR_DEADLINE, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(AssignmentEntry.ATTR_TIME_BLOCK_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                // Event Entry
                .put(EventEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(EventEntry.ATTR_NAME, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT_NOT_NULL)
                .put(EventEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(EventEntry.ATTR_LOCATION_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER)
                // Notification Entry
                .put(NotificationEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(NotificationEntry.ATTR_NAME, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT_NOT_NULL)
                .put(NotificationEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.STRING, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(NotificationEntry.ATTR_CATEGORY, JSQLValueTypeMap.JavaValueType.NOTIFI_CATEGORY, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(NotificationEntry.ATTR_TARGET_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER)
                .put(NotificationEntry.ATTR_TYPE, JSQLValueTypeMap.JavaValueType.NOTIFI_TYPE, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(NotificationEntry.ATTR_IS_DONE, JSQLValueTypeMap.JavaValueType.BOOLEAN, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(NotificationEntry.ATTR_DATETIME_BEGIN, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(NotificationEntry.ATTR_DATETIME_END, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                // Attendance Entry
                .put(AttendanceEntry.ATTR_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_PK)
                .put(AttendanceEntry.ATTR_PRESENT, JSQLValueTypeMap.JavaValueType.INT, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(AttendanceEntry.ATTR_LATE, JSQLValueTypeMap.JavaValueType.INT, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(AttendanceEntry.ATTR_ABSENT, JSQLValueTypeMap.JavaValueType.INT, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL)
                .put(AttendanceEntry.ATTR_NOTE, JSQLValueTypeMap.JavaValueType.INT, JSQLValueTypeMap.SqlValueType.TEXT)
                .put(AttendanceEntry.ATTR_TIME_BLOCK_ID, JSQLValueTypeMap.JavaValueType.LONG, JSQLValueTypeMap.SqlValueType.INTEGER_NOT_NULL);
    }

    private DBContract() {}

    public static String[] getTableNames() {
        return new String[] {
                CourseEntry.TABLE_NAME,
                LocationEntry.TABLE_NAME,
                InstructorEntry.TABLE_NAME,
                TimeBlockEntry.TABLE_NAME,
                AssignmentEntry.TABLE_NAME,
                EventEntry.TABLE_NAME,
                NotificationEntry.TABLE_NAME,
                AttendanceEntry.TABLE_NAME
        };
    }

    public static String[] getColumnNamesOf(final String tableName) {
        switch (tableName) {
            case CourseEntry.TABLE_NAME:
                return CourseEntry.getColumnNames();
            case LocationEntry.TABLE_NAME:
                return LocationEntry.getColumnNames();
            case InstructorEntry.TABLE_NAME:
                return InstructorEntry.getColumnNames();
            case TimeBlockEntry.TABLE_NAME:
                return TimeBlockEntry.getColumnNames();
            case AssignmentEntry.TABLE_NAME:
                return AssignmentEntry.getColumnNames();
            case EventEntry.TABLE_NAME:
                return EventEntry.getColumnNames();
            case NotificationEntry.TABLE_NAME:
                return NotificationEntry.getColumnNames();
            case AttendanceEntry.TABLE_NAME:
                return AttendanceEntry.getColumnNames();
            default:
                return null;
        }
    }

    public static class CourseEntry {
        public static final String TABLE_NAME = "course";
        public static final String PREFIX = "cour";

        public static final String ATTR_ID              = PREFIX + "Id";
        public static final String ATTR_NAME            = PREFIX + "Name";
        public static final String ATTR_LOCATION_ID     = PREFIX + LocationEntry.ATTR_ID;
        public static final String ATTR_INSTRUCTOR_ID   = PREFIX + InstructorEntry.ATTR_ID;
        public static final String ATTR_LENGTH          = PREFIX + "Length";
        public static final String ATTR_NOTE            = PREFIX + "Note";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_LOCATION_ID,
                    ATTR_INSTRUCTOR_ID,
                    ATTR_LENGTH,
                    ATTR_NOTE
            };
        }
    }

    public static class LocationEntry {
        public static final String TABLE_NAME = "location";
        public static final String PREFIX = "loc";

        public static final String ATTR_ID   = PREFIX + "Id";
        public static final String ATTR_NAME = PREFIX + "Name";
        public static final String ATTR_NOTE = PREFIX + "Note";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_NOTE
            };
        }
    }

    public static class InstructorEntry {
        public static final String TABLE_NAME = "instructor";
        public static final String PREFIX = "inst";

        public static final String ATTR_ID           = PREFIX + "Id";
        public static final String ATTR_NAME         = PREFIX + "Name";
        public static final String ATTR_LAB          = PREFIX + "Lab";
        public static final String ATTR_MAIL         = PREFIX + "Mail";
        public static final String ATTR_PHONE_NUMBER = PREFIX + "PhoneNumber";
        public static final String ATTR_NOTE         = PREFIX + "Note";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_LAB,
                    ATTR_MAIL,
                    ATTR_PHONE_NUMBER,
                    ATTR_NOTE
            };
        }
    }

    public static class TimeBlockEntry {
        public static final String TABLE_NAME = "timeBlock";
        public static final String PREFIX = "tb";

        public static final String ATTR_ID             = PREFIX + "Id";
        public static final String ATTR_TYPE           = PREFIX + "Type";
        public static final String ATTR_CATEGORY       = PREFIX + "Category";
        public static final String ATTR_TARGET_ID      = PREFIX + "TargetId";
        public static final String ATTR_DATETIME_BEGIN = PREFIX + "DatetimeBegin";
        public static final String ATTR_DATETIME_END   = PREFIX + "DatetimeEnd";
        public static final String ATTR_TIME_TABLE_ID  = PREFIX + "TimeTableId";
        public static final String ATTR_DAY_OF_WEEK    = PREFIX + "DayOfWeek";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_TYPE,
                    ATTR_CATEGORY,
                    ATTR_TARGET_ID,
                    ATTR_DATETIME_BEGIN,
                    ATTR_DATETIME_END,
                    ATTR_TIME_TABLE_ID,
                    ATTR_DAY_OF_WEEK
            };
        }
    }

    public static class AssignmentEntry {
        public static final String TABLE_NAME = "assignment";
        public static final String PREFIX = "ass";

        public static final String ATTR_ID            = PREFIX + "Id";
        public static final String ATTR_NAME          = PREFIX + "Name";
        public static final String ATTR_TIME_BLOCK_ID = PREFIX + TimeBlockEntry.ATTR_ID;
        public static final String ATTR_NOTE          = PREFIX + "Note";
        public static final String ATTR_IS_DONE       = PREFIX + "IsDone";
        public static final String ATTR_DEADLINE      = PREFIX + "Deadline";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_TIME_BLOCK_ID,
                    ATTR_NOTE,
                    ATTR_IS_DONE,
                    ATTR_DEADLINE
            };
        }
    }

    public static class EventEntry {
        public static final String TABLE_NAME = "event";
        public static final String PREFIX = "eve";

        public static final String ATTR_ID          = PREFIX + "Id";
        public static final String ATTR_NAME        = PREFIX + "Name";
        public static final String ATTR_LOCATION_ID = PREFIX + LocationEntry.ATTR_ID;
        public static final String ATTR_NOTE        = PREFIX + "Note";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_LOCATION_ID,
                    ATTR_NOTE
            };
        }
    }

    public static class NotificationEntry {
        public static final String TABLE_NAME = "notification";
        public static final String PREFIX = "notifi";

        public static final String ATTR_ID             = PREFIX + "Id";
        public static final String ATTR_NAME           = PREFIX + "Name";
        public static final String ATTR_NOTE           = PREFIX + "Note";
        public static final String ATTR_CATEGORY       = PREFIX + "Category";
        public static final String ATTR_TARGET_ID      = PREFIX + "TargetId";
        public static final String ATTR_TYPE           = PREFIX + "Type";
        public static final String ATTR_IS_DONE        = PREFIX + "IsDone";
        public static final String ATTR_DATETIME_BEGIN = PREFIX + "DatetimeBegin";
        public static final String ATTR_DATETIME_END   = PREFIX + "DatetimeEnd";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_NOTE,
                    ATTR_CATEGORY,
                    ATTR_TARGET_ID,
                    ATTR_TYPE,
                    ATTR_IS_DONE,
                    ATTR_DATETIME_BEGIN,
                    ATTR_DATETIME_END
            };
        }
    }

    public static class AttendanceEntry {
        public static final String TABLE_NAME = "attendance";
        public static final String PREFIX = "att";

        public static final String ATTR_ID            = PREFIX + "Id";
        public static final String ATTR_TIME_BLOCK_ID = PREFIX + "TimeBlockId";
        public static final String ATTR_PRESENT       = PREFIX + "Present";
        public static final String ATTR_LATE          = PREFIX + "Late";
        public static final String ATTR_ABSENT        = PREFIX + "Absent";
        public static final String ATTR_NOTE          = PREFIX + "Note";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_TIME_BLOCK_ID,
                    ATTR_PRESENT,
                    ATTR_LATE,
                    ATTR_ABSENT,
                    ATTR_NOTE
            };
        }
    }
}
