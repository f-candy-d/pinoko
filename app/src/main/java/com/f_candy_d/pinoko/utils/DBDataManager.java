package com.f_candy_d.pinoko.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.NotificationFormer;
import com.f_candy_d.pinoko.model.TimeBlockFormer;

import java.util.ArrayList;

/**
 * Created by daichi on 7/30/17.
 */

public class DBDataManager {

    public enum Mode {
        READ,
        WRITE_APPEND,
        WRITE_TRUNCATE
    }

    @NonNull private Context mContext;
    private SQLiteDatabase mDatabase = null;
    private Mode mMode = null;

    public DBDataManager(@NonNull final Context context) {
        mContext = context;
    }

    public DBDataManager(@NonNull final Context context, @NonNull final Mode mode) {
        mContext = context;
        open(mode);
    }

    public void open(@NonNull final Mode mode) {
        if (!isOpen()) {
            final DBOpenHelper helper = new DBOpenHelper(mContext);
            switch (mode) {
                case READ: {
                    mDatabase = helper.getReadableDatabase();
                    break;
                }

                case WRITE_APPEND: {
                    mDatabase = helper.getWritableDatabase();
                    break;
                }

                case WRITE_TRUNCATE: {
                    mDatabase = helper.getWritableDatabase();
                    helper.initTables();
                    break;
                }
            }

            if (mDatabase != null) {
                mMode = mode;
            } else {
                mMode = null;
            }
        }
    }

    public void close() {
        if (isOpen()) {
            mDatabase.close();
            mMode = null;
        }
    }

    public boolean isOpen() {
        return (mDatabase != null && mDatabase.isOpen());
    }

    public boolean isReadOnly() {
        return (mDatabase != null && mDatabase.isReadOnly());
    }

    public long insert(final Savable entry) {
        if (!isOpen() || (mMode != Mode.WRITE_APPEND && mMode != Mode.WRITE_TRUNCATE)) {
            throw new IllegalStateException("DB is not open or its mode is not a write-mode");
        }

        if (entry.isSavable()) {
            final long id = mDatabase.insert(entry.getTableName(), null, entry.toContentValues(false));
            return id;
        }

        return DBContract.NULL_ID;
    }

    public long[] insert(final ArrayList<Savable> entries) {
        if (!isOpen() || (mMode != Mode.WRITE_APPEND && mMode != Mode.WRITE_TRUNCATE)) {
            throw new IllegalStateException("DB is not open or its mode is not a write-mode");
        }

        final long[] ids = new long[entries.size()];

        Savable entry;
        for (int i = 0; i < entries.size(); ++i) {
            entry = entries.get(i);
            if (entry.isSavable()) {
                ids[i] = mDatabase.insert(entry.getTableName(), null, entry.toContentValues(false));
            } else {
                ids[i] = DBContract.NULL_ID;
            }
        }

        return ids;
    }

    public ArrayList<Entry> selectAllOf(final String tableName) {
        if (!isOpen()) {
            throw new IllegalStateException("DB is not open");
        }

        final ArrayList<Entry> results = new ArrayList<>();
        // Select all rows in the database
        final SQLQuery query = new SQLQuery(null, new String[]{tableName}, null);
        final Cursor cursor = mDatabase.rawQuery(query.toString(), null);
        boolean isEOF = cursor.moveToFirst();
        Entry entry;

        while (isEOF) {
            entry = EntryFactory.makeBasicEntry(tableName, cursor);
            if (entry != null) {
                results.add(entry);
            }
            isEOF = cursor.moveToNext();
        }

        cursor.close();
        return results;
    }

    public ArrayList<Entry> select(@NonNull final SQLQuery query, @NonNull final String entryAffiliation) {
        if (!isOpen()) {
            throw new IllegalStateException("DB is not open");
        }

        ArrayList<Entry> results = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery(query.toString(), null);
        boolean isEOF = cursor.moveToFirst();
        String[] requests = cursor.getColumnNames();
        Entry entry;
        JSQLValueTypeMap.JavaValueType type;

        while (isEOF) {
            entry = new Entry(entryAffiliation);
            for (String request : requests) {
                type = DBContract.ATTR_VALUE_TYPE_MAP.getJavaValueType(request);
                switch (type) {
                    case STRING: {
                        entry.set(request, cursor.getString(cursor.getColumnIndexOrThrow(request)));
                        break;
                    }

                    case INT: {
                        entry.set(request, cursor.getInt(cursor.getColumnIndexOrThrow(request)));
                        break;
                    }

                    case LONG: {
                        entry.set(request, cursor.getLong(cursor.getColumnIndexOrThrow(request)));
                        break;
                    }

                    case BOOLEAN: {
                        final boolean bool = (0 != cursor.getInt(cursor.getColumnIndexOrThrow(request)));
                        entry.set(request, bool);
                        break;
                    }

                    case TB_TYPE: {
                        final int typeId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, TimeBlockFormer.Type.from(typeId));
                    }

                    case TB_CATEGORY: {
                        final int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, TimeBlockFormer.Category.from(categoryId));
                    }

                    case NOTIF_TYPE: {
                        final int typeId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, NotificationFormer.Type.from(typeId));
                    }

                    case NOTIF_CATEGORY: {
                        final int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, NotificationFormer.Category.from(categoryId));
                    }
                }
            }
            results.add(entry);
            isEOF = cursor.moveToNext();
        }

        cursor.close();
        return results;
    }
}
