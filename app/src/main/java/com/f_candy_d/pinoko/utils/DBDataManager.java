package com.f_candy_d.pinoko.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.FlexibleEntry;

import java.util.ArrayList;

/**
 * Created by daichi on 7/30/17.
 */

public class DBDataManager {

    public enum Mode {
        APPEND,
        TRUNCATE
    }

    @NonNull private Context mContext;

    public DBDataManager(@NonNull final Context context) {
        this(context, Mode.APPEND);
    }

    public DBDataManager(@NonNull final Context context, @NonNull final Mode mode) {
        mContext = context;

        if (mode == Mode.TRUNCATE) {
            DBOpenHelper helper = new DBOpenHelper(mContext);
            helper.initTables();
        }
    }

    public long insert(final Savable entry) {
        if (entry.isSavable()) {
            final DBOpenHelper helper = new DBOpenHelper(mContext);
            final SQLiteDatabase database = helper.getWritableDatabase();
            final long id = database.insert(entry.getTableName(), null, entry.toContentValues(false));
            database.close();
            return id;
        }

        return DBContract.NULL_ID;
    }

    public long[] insert(final ArrayList<Savable> entries) {
        final DBOpenHelper helper = new DBOpenHelper(mContext);
        final SQLiteDatabase database = helper.getWritableDatabase();
        final long[] ids = new long[entries.size()];

        Savable entry;
        for (int i = 0; i < entries.size(); ++i) {
            entry = entries.get(i);
            if (entry.isSavable()) {
                ids[i] = database.insert(entry.getTableName(), null, entry.toContentValues(false));
            } else {
                ids[i] = DBContract.NULL_ID;
            }
        }

        database.close();
        return ids;
    }

    public ArrayList<Entry> getAllOf(final String tableName) {
        final ArrayList<Entry> entries = new ArrayList<>();
        // Select all rows in the database
        final String select = "SELECT * FROM " + tableName + ";";
        final DBOpenHelper helper = new DBOpenHelper(mContext);
        final SQLiteDatabase database = helper.getReadableDatabase();
        final Cursor cursor = database.rawQuery(select, null);
        boolean isEOF = cursor.moveToFirst();
        Entry entry;

        while (isEOF) {
            entry = EntryHelper.makeEntry(tableName, cursor);
            if (entry != null) {
                entries.add(entry);
            }
            isEOF = cursor.moveToNext();
        }

        cursor.close();
        database.close();
        return entries;
    }
}
