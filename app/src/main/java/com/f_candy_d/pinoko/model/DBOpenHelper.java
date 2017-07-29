package com.f_candy_d.pinoko.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.f_candy_d.pinoko.utils.DBContract;

/**
 * Created by daichi on 7/30/17.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(final Context context) {
        super(context, DBContract.DATABASE_NAME, null, DBContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTables(final SQLiteDatabase db) {
        db.execSQL(DBContract.NotificationEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.EventEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.AssignmentEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.LocationEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.CourseEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.InstructorEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.TimeBlockEntry.SQL_CREATE_TABLE);
        db.execSQL(DBContract.AttendanceEntry.SQL_CREATE_TABLE);
    }

    public void createTables() {
        createTables(getWritableDatabase());
    }

    public void deleteTables(final SQLiteDatabase db) {
        final String sqlDropTable = "DROP TABLE IF EXISTS ";
        for (String tableName : DBContract.getTableNames()) {
            db.execSQL(sqlDropTable + tableName);
        }
    }

    public void deleteTables() {
        SQLiteDatabase db = getWritableDatabase();
        deleteTables(db);
    }

    public void initTables() {
        SQLiteDatabase db = getWritableDatabase();
        deleteTables(db);
        createTables(db);
    }
}
