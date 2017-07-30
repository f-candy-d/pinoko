package com.f_candy_d.pinoko.utils;

import android.provider.BaseColumns;

/**
 * Created by daichi on 7/30/17.
 */

public class DBContract {

    public static final int NULL_ID = 0;
    public static final int MIN_AVAILABLE_ID = 1;
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "pinoko_database.db";
    public static final String SQL_CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";

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

    public static class CourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "course";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 9;

        public static final String COL_NAME = "couName"; // Not null
        public static final String COL_LOCATION_ID_A = "couLocIdA"; // Not null
        public static final String COL_LOCATION_ID_B = "couLocIdB";
        public static final String COL_INSTRUCTOR_ID_A = "couInsIdA"; // Not null
        public static final String COL_INSTRUCTOR_ID_B = "couInsIdB";
        public static final String COL_INSTRUCTOR_ID_C = "couInsIdC";
        public static final String COL_LENGTH = "length"; // Not null
        public static final String COL_NOTE = "couNote";

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                + _ID                 + " INTEGER PRIMARY KEY,"
                + COL_NAME            + " TEXT NOT NULL,"
                + COL_LOCATION_ID_A   + " INTEGER NOT NULL,"
                + COL_LOCATION_ID_B   + " INTEGER,"
                + COL_INSTRUCTOR_ID_A + " INTEGER NOT NULL,"
                + COL_INSTRUCTOR_ID_B + " INTEGER,"
                + COL_INSTRUCTOR_ID_C + " INTEGER,"
                + COL_LENGTH          + " INTEGER NOT NULL,"
                + COL_NOTE            + " TEXT"
                + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_NAME,
                    COL_LOCATION_ID_A,
                    COL_LOCATION_ID_B,
                    COL_INSTRUCTOR_ID_A,
                    COL_INSTRUCTOR_ID_B,
                    COL_INSTRUCTOR_ID_C,
                    COL_LENGTH,
                    COL_NOTE
            };
        }
    }

    public static class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 3;

        public static final String COL_NAME = "locName"; // Not null
        public static final String COL_NOTE = "locNote";

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID      + " INTEGER PRIMARY KEY,"
                        + COL_NAME + " TEXT NOT NULL,"
                        + COL_NOTE + " TEXT"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_NAME,
                    COL_NOTE
            };
        }
    }

    public static class InstructorEntry implements BaseColumns {
        public static final String TABLE_NAME = "instructor";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 6;

        public static final String COL_NAME = "insName"; // Not null
        public static final String COL_LAB = "lab";
        public static final String COL_MAIL = "mail";
        public static final String COL_PHONE_NUMBER = "phoneNumber";
        public static final String COL_NOTE = "insNote";

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID              + " INTEGER PRIMARY KEY,"
                        + COL_NAME         + " TEXT NOT NULL,"
                        + COL_LAB          + " TEXT,"
                        + COL_MAIL         + " TEXT,"
                        + COL_PHONE_NUMBER + " TEXT,"
                        + COL_NOTE         + " TEXT"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_NAME,
                    COL_LAB,
                    COL_MAIL,
                    COL_PHONE_NUMBER,
                    COL_NOTE
            };
        }
    }

    public static class TimeBlockEntry implements BaseColumns {
        public static final String TABLE_NAME = "timeBlock";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 7;

        public static final String COL_TYPE = "tbType"; // Not null
        public static final String COL_CATEGORY = "tbCategory"; // Not null
        public static final String COL_TARGET_ID = "tbTargetId";
        public static final String COL_DATETIME_BEGIN = "tbDatetimeBegin"; // Not null
        public static final String COL_DATETIME_END = "tbDatetimeEnd"; // Not null
        public static final String COL_TIME_TABLE_ID = "tbTimeTableId"; // Not null

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID                + " INTEGER PRIMARY KEY,"
                        + COL_TYPE           + " INTEGER NOT NULL,"
                        + COL_CATEGORY       + " INTEGER NOT NULL,"
                        + COL_TARGET_ID      + " INTEGER,"
                        + COL_DATETIME_BEGIN + " INTEGER NOT NULL,"
                        + COL_DATETIME_END   + " INTEGER NOT NULL,"
                        + COL_TIME_TABLE_ID  + " INTEGER NOT NULL"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_TYPE,
                    COL_CATEGORY,
                    COL_TARGET_ID,
                    COL_DATETIME_BEGIN,
                    COL_DATETIME_END,
                    COL_TIME_TABLE_ID
            };
        }
    }

    public static class AssignmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "assignment";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 5;

        public static final String COL_NAME = "assName"; // Not null
        public static final String COL_TIME_BLOCK_ID = "assTbId";
        public static final String COL_NOTE = "assNote";
        public static final String COL_IS_DONE = "assIsDone"; // Not null

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID               + " INTEGER PRIMARY KEY,"
                        + COL_NAME          + " TEXT NOT NULL,"
                        + COL_TIME_BLOCK_ID + " INTEGER,"
                        + COL_NOTE          + " TEXT,"
                        + COL_IS_DONE       + " INTEGER NOT NULL DEFAULT 0"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_NAME,
                    COL_TIME_BLOCK_ID,
                    COL_NOTE,
                    COL_IS_DONE
            };
        }
    }

    public static class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "event";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 4;

        public static final String COL_NAME = "eveName"; // Not null
        public static final String COL_LOCATION_ID = "eveLocId";
        public static final String COL_NOTE = "eveNote";

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID             + " INTEGER PRIMARY KEY,"
                        + COL_NAME        + " TEXT NOT NULL,"
                        + COL_LOCATION_ID + " INTEGER,"
                        + COL_NOTE        + " TEXT"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_NAME,
                    COL_LOCATION_ID,
                    COL_NOTE
            };
        }
    }

    public static class NotificationEntry implements BaseColumns {
        public static final String TABLE_NAME = "notification";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 9;

        public static final String COL_NAME = "notName"; // Not null
        public static final String COL_NOTE = "notNote";
        public static final String COL_CATEGORY = "notCategory"; // Not null
        public static final String COL_TARGET_ID = "notTargetId";
        public static final String COL_TYPE = "notType"; // Not null
        public static final String COL_IS_DONE = "notIsDone"; // Not null
        public static final String COL_DATETIME_BEGIN = "notDatetimeBegin"; // Not null
        public static final String COL_DATETIME_END = "notDatetimeEnd"; // Not null

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID                + " INTEGER PRIMARY KEY,"
                        + COL_NAME           + " TEXT NOT NULL,"
                        + COL_NOTE           + " TEXT,"
                        + COL_CATEGORY       + " INTEGER NOT NULL,"
                        + COL_TARGET_ID      + " INTEGER,"
                        + COL_TYPE           + " INTEGER NOT NULL,"
                        + COL_IS_DONE        + " INTEGER NOT NULL DEFAULT 0,"
                        + COL_DATETIME_BEGIN + " INTEGER NOT NULL,"
                        + COL_DATETIME_END   + " INTEGER NOT NULL"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_NAME,
                    COL_NOTE,
                    COL_CATEGORY,
                    COL_TARGET_ID,
                    COL_TYPE,
                    COL_IS_DONE,
                    COL_DATETIME_BEGIN,
                    COL_DATETIME_END
            };
        }
    }

    public static class AttendanceEntry implements BaseColumns {
        public static final String TABLE_NAME = "attendance";
        // This includes the column of '_id'
        public static final int NUM_COLUMNS = 6;

        public static final String COL_TIME_BLOCK_ID = "attTbId";
        public static final String COL_PRESENT = "present";
        public static final String COL_LATE = "late";
        public static final String COL_ABSENT = "absent";
        public static final String COL_NOTE = "attNote";

        public static final String SQL_CREATE_TABLE =
                SQL_CREATE_TABLE_IF_NOT_EXISTS + TABLE_NAME + " ("
                        + _ID               + " INTEGER PRIMARY KEY,"
                        + COL_TIME_BLOCK_ID + " INTEGER NOT NULL,"
                        + COL_PRESENT       + " INTEGER NOT NULL,"
                        + COL_LATE          + " INTEGER NOT NULL,"
                        + COL_ABSENT        + " INTEGER NOT NULL,"
                        + COL_NOTE          + " TEXT"
                        + ");";

        public static String[] getColumnNames() {
            return new String[] {
                    _ID,
                    COL_TIME_BLOCK_ID,
                    COL_PRESENT,
                    COL_LATE,
                    COL_ABSENT,
                    COL_NOTE
            };
        }
    }
}
