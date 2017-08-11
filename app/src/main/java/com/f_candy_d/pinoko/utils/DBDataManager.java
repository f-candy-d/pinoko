package com.f_candy_d.pinoko.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.Savable;
import com.f_candy_d.pinoko.model.Entry;

import java.util.ArrayList;
import java.util.Collection;

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

    public void openAsReadable() {
        open(Mode.READ);
    }

    public void openAsWritableAppend() {
        open(Mode.WRITE_APPEND);
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
            return mDatabase.insert(entry.getTableName(), null, entry.toContentValues(false));
        }

        return DBContract.NULL_ID;
    }

    public long[] insert(final Collection<Savable> entries) {
        if (!isOpen() || (mMode != Mode.WRITE_APPEND && mMode != Mode.WRITE_TRUNCATE)) {
            throw new IllegalStateException("DB is not open or its mode is not a write-mode");
        }

        final long[] ids = new long[entries.size()];
        int index = 0;

        mDatabase.beginTransaction();
        try {
            for (Savable entry : entries) {
                if (entry.isSavable()) {
                    ids[index] = mDatabase.insert(entry.getTableName(), null, entry.toContentValues(false));
                } else {
                    ids[index] = DBContract.NULL_ID;
                }
                ++index;
            }
            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }

        return ids;
    }

    public ArrayList<Entry> selectAllOf(@NonNull final String table) {
        if (!isOpen()) {
            throw new IllegalStateException("DB is not open");
        }

        SQLQuery query = new SQLQuery();
        query.putTables(table);
        return select(query, table);
    }

    public ArrayList<Entry> select(@NonNull final SQLQuery query, @NonNull final String entryAffiliation) {
        if (!isOpen()) {
            throw new IllegalStateException("DB is not open");
        }

        ArrayList<Entry> results = new ArrayList<>();
        Cursor cursor = mDatabase.query(
                query.distinct(),
                query.tables(),
                query.columns(),
                query.selection(),
                query.selectionArgs(),
                query.groupBy(),
                query.having(),
                query.orderBy(),
                query.limit());

        boolean isEOF = cursor.moveToFirst();
        String[] requests = query.columns();
        Entry entry;
        JSQLValueTypeMap.JavaValueType type;

        if (requests == null) {
            requests = cursor.getColumnNames();
        }

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
                        break;
                    }

                    case TB_CATEGORY: {
                        final int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, TimeBlockFormer.Category.from(categoryId));
                        break;
                    }

                    case NOTIFI_TYPE: {
                        final int typeId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, NotificationFormer.Type.from(typeId));
                        break;
                    }

                    case NOTIFI_CATEGORY: {
                        final int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, NotificationFormer.Category.from(categoryId));
                        break;
                    }

                    case DAY_OF_WEEK: {
                        final int dayOfWeekId = cursor.getInt(cursor.getColumnIndexOrThrow(request));
                        entry.set(request, DayOfWeek.from(dayOfWeekId));
                        break;
                    }
                }
            }
            results.add(entry);
            isEOF = cursor.moveToNext();
        }

        cursor.close();
        return results;
    }

    public Entry selectWhereIdIs(@NonNull String table, long id, @NonNull String entryAffiliation) {
        String[] columnNames = DBContract.getColumnNamesOf(table);
        if (columnNames != null) {
            SQLQuery query = new SQLQuery();
            query.putTables(table);
            query.setLimit(1);
            SQLWhere.CondExpr idIs = new SQLWhere.CondExpr();

            // DBContract.getColumnNamesOf(table)[0] is must be a id column
            idIs.l(columnNames[0]).equalTo().r(id);

            query.setSelection(idIs);
            ArrayList<Entry> results = select(query, entryAffiliation);
            if (results.size() == 1) {
                return results.get(0);
            }
        }

        return null;
    }

    public ArrayList<Entry> selectWhereIdIsIn(@NonNull String table, @NonNull long[] ids, @NonNull String entryAffiliation) {
        String[] columnNames = DBContract.getColumnNamesOf(table);
        if (columnNames != null) {
            SQLQuery query = new SQLQuery();
            query.putTables(table);
            query.setLimit(1);
            SQLWhere.SpecExpr idIsIn = new SQLWhere.SpecExpr();

            // DBContract.getColumnNamesOf(table)[0] is must be a id column
            idIsIn.in(columnNames[0], ids);

            query.setSelection(idIsIn);
            return select(query, entryAffiliation);
        }

        return null;
    }

    /**
     * Generate a simple selection query like: SELECT * FROM table WHERE column = is;
     */
    public ArrayList<Entry> selectWhereColumnIs(@NonNull String table, @NonNull String column, int is, String entryAffiliation) {
        SQLQuery query = new SQLQuery();
        query.putTables(table);
        SQLWhere.CondExpr columnIs = new SQLWhere.CondExpr(column, SQLWhere.CondExpr.CondOp.EQ, is);
        query.setSelection(columnIs);
        if (entryAffiliation == null) {
            entryAffiliation = table;
        }
        return select(query, entryAffiliation);
    }

    public ArrayList<Entry> selectWhereColumnIs(@NonNull String table, @NonNull String column, String is, String entryAffiliation) {
        SQLQuery query = new SQLQuery();
        query.putTables(table);
        SQLWhere.CondExpr columnIs = new SQLWhere.CondExpr(column, SQLWhere.CondExpr.CondOp.EQ, is);
        query.setSelection(columnIs);
        if (entryAffiliation == null) {
            entryAffiliation = table;
        }
        return select(query, entryAffiliation);
    }

    public ArrayList<Entry> selectWhereColumnIs(@NonNull String table, @NonNull String column, long is, String entryAffiliation) {
        SQLQuery query = new SQLQuery();
        query.putTables(table);
        SQLWhere.CondExpr columnIs = new SQLWhere.CondExpr(column, SQLWhere.CondExpr.CondOp.EQ, is);
        query.setSelection(columnIs);
        if (entryAffiliation == null) {
            entryAffiliation = table;
        }
        return select(query, entryAffiliation);
    }

    public boolean update(@NonNull final Savable entry) {
        if (!isOpen()) {
            throw new IllegalStateException("DB is not open");
        }

        if (entry.isSavable()) {
            ContentValues contentValues = entry.toContentValues(true);
            SQLWhere.CondExpr equalToId = new SQLWhere.CondExpr();
            equalToId.l(entry.getIdColumnName()).equalTo().r(contentValues.getAsLong(entry.getIdColumnName()));

            if (entry.isSavable()) {
                mDatabase.update(entry.getTableName(), contentValues, equalToId.toString(), null);
                return true;
            }
        }

        return false;
    }

}
