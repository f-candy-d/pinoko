package com.f_candy_d.pinoko.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by daichi on 17/08/05.
 */

public class SQLGrammar {

    private SQLGrammar() {}

    public static String sqlCreateTable(@NonNull final String tableName,
                                        @NonNull final String[] columnNames,
                                        @NonNull final JSQLValueTypeMap typeMap) {

        if (tableName.isEmpty()) {
            throw new IllegalArgumentException("The table name is empty");
        }
        if (columnNames.length == 0) {
            throw new IllegalArgumentException("Table: " + tableName + " has no columns");
        }

        String createTable = "CREATE TABLE " + tableName + " (";
        JSQLValueTypeMap.SqlValueType valueTYpe;

        valueTYpe = typeMap.getSqlValueType(columnNames[0]);
        if (valueTYpe != null) {
            createTable = createTable.concat(columnNames[0] + " " + valueTYpe.toString());
        } else {
            throw new IllegalArgumentException(
                    "'The 3rd parameter 'typeMap' doesn't have a value-type pair for the key:" + columnNames[0]);
        }

        for (int i = 1; i < columnNames.length; ++i) {
            valueTYpe = typeMap.getSqlValueType(columnNames[i]);
            if (valueTYpe != null) {
                createTable = createTable.concat("," + columnNames[i] + " " + valueTYpe.toString());
            } else {
                throw new IllegalArgumentException(
                        "'The 3rd parameter 'typeMap' doesn't have a value-type pair for the key:" + columnNames[i]);
            }
        }

        return createTable + ");";
    }

    public static String sqlFrom(final String[] tables) {
        if (tables != null && tables.length != 0) {
            return "FROM " + TextUtils.join(" JOIN ", tables);
        } else {
            // Return an empty string
            return "";
        }
    }

    public static String sqlWhere(final SQLWhere sqlWhere) {
        if (sqlWhere != null) {
            final String where = sqlWhere.toString();
            if (where != null) {
                return "WHERE " + where;
            }
        }
        // Return an empty string
        return "";
    }

    public static String sqlSelect(final String[] requests,
                                   @NonNull final String[] tables,
                                   final SQLWhere where) {

        String select;
        if (requests != null && requests.length != 0) {
            select = "SELECT " + TextUtils.join(",", requests);
        } else {
            // Select all columns of tables in mFrom
            select = "SELECT *";
        }

        return select + " " + sqlFrom(tables) + " " + sqlWhere(where) + ";";
    }
}
