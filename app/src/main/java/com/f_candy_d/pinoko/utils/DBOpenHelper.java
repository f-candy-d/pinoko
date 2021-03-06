package com.f_candy_d.pinoko.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String[] columnNames;
        for (String tableName : DBContract.getTableNames()) {
            columnNames = DBContract.getColumnNamesOf(tableName);
            if (columnNames != null) {
                db.execSQL(SQLGrammar.sqlCreateTable(tableName, columnNames, DBContract.ATTR_VALUE_TYPE_MAP));
            } else {
                throw new NullPointerException("Creating table(" + tableName + ") failed -> This table has no column");
            }
        }
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
