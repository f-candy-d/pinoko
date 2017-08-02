package com.f_candy_d.pinoko.utils;

import java.net.NoRouteToHostException;

/**
 * Created by daichi on 7/30/17.
 */

public class DBContract {

    public static final int NULL_ID = 0;
    public static final int MIN_AVAILABLE_ID = 1;
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "pinoko_database.db";
    public static final String SQL_CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";

    /**
     * Column names
     */
    public static final String COL_ID = "ID";
    public static final String COL_TARGET_ID = "TargetID";
    public static final String COL_TIME_TABLE_ID = "TimeTableID";
    public static final String COL_NAME = "Name";
    public static final String COL_LENGTH = "Length";
    public static final String COL_NOTE = "Note";
    public static final String COL_LAB = "Lab";
    public static final String COL_MAIL = "Mail";
    public static final String COL_PHONE_NUMBER = "PhoneNumber";
    public static final String COL_TYPE = "Type";
    public static final String COL_CATEGORY = "Category";
    public static final String COL_DATETIME_BEGIN = "DatetimeBegin";
    public static final String COL_DATETIME_END = "DateTimeEnd";
    public static final String COL_IS_DONE = "IsDone";
    public static final String COL_PRESENT = "Present";
    public static final String COL_LATE = "Late";
    public static final String COL_ABSENT = "Absent";

    /**
     * Value types of each columns
     */
    public static final String TYPE_ID_PK = " INTEGER PRIMARY KEY";
    public static final String TYPE_ID = " INTEGER";
    public static final String TYPE_NAME = " TEXT";
    public static final String TYPE_LENGTH = " INTEGER";
    public static final String TYPE_NOTE = " TEXT";
    public static final String TYPE_LAB = " TEXT";
    public static final String TYPE_MAIL = " TEXT";
    public static final String TYPE_PHONE_NUMBER = " TEXT";
    public static final String TYPE_TYPE = " INTEGER";
    public static final String TYPE_CATEGORY = " INTEGER";
    public static final String TYPE_DATETIME_BEGIN = " INTEGER";
    public static final String TYPE_DATETIME_END = " INTEGER";
    public static final String TYPE_IS_DONE = " INTEGER";
    public static final String TYPE_PRESENT = " INTEGER";
    public static final String TYPE_LATE = " INTEGER";
    public static final String TYPE_ABSENT = " INTEGER";

    /**
     * SQL grammars
     */
    public static final String NOT_NULL = " NOT NULL";
    public static final String C_S = ",";

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

    public static int getTableCount() {
        return getTableNames().length;
    }

    public static class CourseEntry {
        public static final String TABLE_NAME = "course";
        public static final String PREFIX = "cour";

        public static final String ATTR_ID              = PREFIX + COL_ID;
        public static final String ATTR_NAME            = PREFIX + COL_NAME;               // Not null
        public static final String ATTR_LOCATION_ID_A   = LocationEntry.ATTR_ID + "_A";    // Not null
        public static final String ATTR_LOCATION_ID_B   = LocationEntry.ATTR_ID + "_B";
        public static final String ATTR_INSTRUCTOR_ID_A = InstructorEntry.ATTR_ID + "_A";  // Not null
        public static final String ATTR_INSTRUCTOR_ID_B = InstructorEntry.ATTR_ID + "_B";
        public static final String ATTR_INSTRUCTOR_ID_C = InstructorEntry.ATTR_ID + "_C";
        public static final String ATTR_LENGTH          = PREFIX + COL_LENGTH;            // Not null
        public static final String ATTR_NOTE            = PREFIX + COL_NOTE;

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                + ATTR_ID              + TYPE_ID_PK             + C_S
                + ATTR_NAME            + TYPE_NAME   + NOT_NULL + C_S
                + ATTR_LOCATION_ID_A   + TYPE_ID     + NOT_NULL + C_S
                + ATTR_LOCATION_ID_B   + TYPE_ID                + C_S
                + ATTR_INSTRUCTOR_ID_A + TYPE_ID     + NOT_NULL + C_S
                + ATTR_INSTRUCTOR_ID_B + TYPE_ID                + C_S
                + ATTR_INSTRUCTOR_ID_C + TYPE_ID                + C_S
                + ATTR_LENGTH          + TYPE_LENGTH            + C_S
                + ATTR_NOTE            + TYPE_NOTE
                + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_LOCATION_ID_A,
                    ATTR_LOCATION_ID_B,
                    ATTR_INSTRUCTOR_ID_A,
                    ATTR_INSTRUCTOR_ID_B,
                    ATTR_INSTRUCTOR_ID_C,
                    ATTR_LENGTH,
                    ATTR_NOTE
            };
        }
    }

    public static class LocationEntry {
        public static final String TABLE_NAME = "location";
        public static final String PREFIX = "loc";

        public static final String ATTR_ID   = PREFIX + COL_ID;
        public static final String ATTR_NAME = PREFIX + COL_NAME; // Not null
        public static final String ATTR_NOTE = PREFIX + COL_NOTE;

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID   + TYPE_ID_PK            + C_S
                        + ATTR_NAME + TYPE_NAME  + NOT_NULL + C_S
                        + ATTR_NOTE + TYPE_NOTE
                        + ");";

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

        public static final String ATTR_ID           = PREFIX + COL_ID;
        public static final String ATTR_NAME         = PREFIX + COL_NAME;          // Not null
        public static final String ATTR_LAB          = PREFIX + COL_LAB;
        public static final String ATTR_MAIL         = PREFIX + COL_MAIL;
        public static final String ATTR_PHONE_NUMBER = PREFIX + COL_PHONE_NUMBER;
        public static final String ATTR_NOTE         = PREFIX + COL_NOTE;

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID           + TYPE_ID_PK                   + C_S
                        + ATTR_NAME         + TYPE_NAME         + NOT_NULL + C_S
                        + ATTR_LAB          + TYPE_LAB                     + C_S
                        + ATTR_MAIL         + TYPE_MAIL                    + C_S
                        + ATTR_PHONE_NUMBER + TYPE_PHONE_NUMBER            + C_S
                        + ATTR_NOTE         + TYPE_NOTE
                        + ");";

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

        public static final String ATTR_ID             = PREFIX + COL_ID;
        public static final String ATTR_TYPE           = PREFIX + COL_TYPE;           // Not null
        public static final String ATTR_CATEGORY       = PREFIX + COL_CATEGORY;       // Not null
        public static final String ATTR_TARGET_ID      = PREFIX + COL_TARGET_ID;
        public static final String ATTR_DATETIME_BEGIN = PREFIX + COL_DATETIME_BEGIN; // Not null
        public static final String ATTR_DATETIME_END   = PREFIX + COL_DATETIME_END;   // Not null
        public static final String ATTR_TIME_TABLE_ID  = PREFIX + COL_TIME_TABLE_ID;  // Not null

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID             + TYPE_ID_PK                     + C_S
                        + ATTR_TYPE           + TYPE_TYPE           + NOT_NULL + C_S
                        + ATTR_CATEGORY       + TYPE_CATEGORY       + NOT_NULL + C_S
                        + ATTR_TARGET_ID      + TYPE_ID                        + C_S
                        + ATTR_DATETIME_BEGIN + TYPE_DATETIME_BEGIN + NOT_NULL + C_S
                        + ATTR_DATETIME_END   + TYPE_DATETIME_END   + NOT_NULL + C_S
                        + ATTR_TIME_TABLE_ID  + TYPE_ID             + NOT_NULL
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_TYPE,
                    ATTR_CATEGORY,
                    ATTR_TARGET_ID,
                    ATTR_DATETIME_BEGIN,
                    ATTR_DATETIME_END,
                    ATTR_TIME_TABLE_ID
            };
        }
    }

    public static class AssignmentEntry {
        public static final String TABLE_NAME = "assignment";
        public static final String PREFIX = "ass";

        public static final String ATTR_ID            = PREFIX + COL_ID;
        public static final String ATTR_NAME          = PREFIX + COL_NAME;      // Not null
        public static final String ATTR_TIME_BLOCK_ID = TimeBlockEntry.ATTR_ID;
        public static final String ATTR_NOTE          = PREFIX + COL_NOTE;
        public static final String ATTR_IS_DONE       = PREFIX + COL_IS_DONE;   // Not null

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID            + TYPE_ID_PK              + C_S
                        + ATTR_NAME          + TYPE_NAME    + NOT_NULL + C_S
                        + ATTR_TIME_BLOCK_ID + TYPE_ID                 + C_S
                        + ATTR_NOTE          + TYPE_NOTE               + C_S
                        + ATTR_IS_DONE       + TYPE_IS_DONE + NOT_NULL
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    ATTR_ID,
                    ATTR_NAME,
                    ATTR_TIME_BLOCK_ID,
                    ATTR_NOTE,
                    ATTR_IS_DONE
            };
        }
    }

    public static class EventEntry {
        public static final String TABLE_NAME = "event";
        public static final String PREFIX = "eve";

        public static final String ATTR_ID          = PREFIX + COL_ID;
        public static final String ATTR_NAME        = PREFIX + COL_NAME;     // Not null
        public static final String ATTR_LOCATION_ID = LocationEntry.ATTR_ID;
        public static final String ATTR_NOTE        = PREFIX + COL_NOTE;

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID          + TYPE_ID_PK           + C_S
                        + ATTR_NAME        + TYPE_NAME + NOT_NULL + C_S
                        + ATTR_LOCATION_ID + TYPE_ID              + C_S
                        + ATTR_NOTE        + TYPE_NOTE
                        + ");";

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

        public static final String ATTR_ID             = PREFIX + COL_ID;
        public static final String ATTR_NAME           = PREFIX + COL_NAME;           // Not null
        public static final String ATTR_NOTE           = PREFIX + COL_NOTE;
        public static final String ATTR_CATEGORY       = PREFIX + COL_CATEGORY;       // Not null
        public static final String ATTR_TARGET_ID      = PREFIX + COL_TARGET_ID;
        public static final String ATTR_TYPE           = PREFIX + COL_TYPE;           // Not null
        public static final String ATTR_IS_DONE        = PREFIX + COL_IS_DONE;        // Not null
        public static final String ATTR_DATETIME_BEGIN = PREFIX + COL_DATETIME_BEGIN; // Not null
        public static final String ATTR_DATETIME_END   = PREFIX + COL_DATETIME_END;   // Not null

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID             + TYPE_ID_PK                     + C_S
                        + ATTR_NAME           + TYPE_NAME           + NOT_NULL + C_S
                        + ATTR_NOTE           + TYPE_NOTE                      + C_S
                        + ATTR_CATEGORY       + TYPE_CATEGORY       + NOT_NULL + C_S
                        + ATTR_TARGET_ID      + TYPE_ID                        + C_S
                        + ATTR_TYPE           + TYPE_TYPE           + NOT_NULL + C_S
                        + ATTR_IS_DONE        + TYPE_IS_DONE        + NOT_NULL + C_S
                        + ATTR_DATETIME_BEGIN + TYPE_DATETIME_BEGIN + NOT_NULL + C_S
                        + ATTR_DATETIME_END   + TYPE_DATETIME_END   + NOT_NULL
                        + ");";

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

        public static final String ATTR_ID            = PREFIX + COL_ID;
        public static final String ATTR_TIME_BLOCK_ID = TimeBlockEntry.ATTR_ID; // Not null
        public static final String ATTR_PRESENT       = PREFIX + COL_PRESENT;   // Not null
        public static final String ATTR_LATE          = PREFIX + COL_LATE;      // Not null
        public static final String ATTR_ABSENT        = PREFIX + COL_ABSENT;    // Not null
        public static final String ATTR_NOTE          = PREFIX + COL_NOTE;

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + ATTR_ID            + TYPE_ID_PK              + C_S
                        + ATTR_TIME_BLOCK_ID + TYPE_ID      + NOT_NULL + C_S
                        + ATTR_PRESENT       + TYPE_PRESENT + NOT_NULL + C_S
                        + ATTR_LATE          + TYPE_LATE    + NOT_NULL + C_S
                        + ATTR_ABSENT        + TYPE_ABSENT  + NOT_NULL + C_S
                        + ATTR_NOTE          + TYPE_NOTE
                        + ");";

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
