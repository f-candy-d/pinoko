package com.f_candy_d.pinoko.utils;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by daichi on 17/08/05.
 */

public class JSQLValueTypeMap {

    public enum SqlValueType {
        INTEGER("INTEGER"),
        INTEGER_PK("INTEGER PRIMARY KEY"),
        TEXT("TEXT"),
        TEXT_NOT_NULL("TEXT NOT NULL"),
        INTEGER_NOT_NULL("INTEGER NOT NULL");

        private String mString;

        SqlValueType(final String string) {
            mString = string;
        }

        @Override
        public String toString() {
            return mString;
        }
    }

    public enum JavaValueType {
        STRING,
        INT,
        LONG,
        BOOLEAN,
        TB_TYPE,
        TB_CATEGORY,
        NOTIF_TYPE,
        NOTIF_CATEGORY
    }

    private Map<String, Pair<JavaValueType, SqlValueType>> mTypeMap;

    public JSQLValueTypeMap() {
        mTypeMap = new HashMap<>();
    }

    public JSQLValueTypeMap(final int capacity) {
        mTypeMap = new HashMap<>(capacity);
    }

    public SqlValueType getSqlValueType(final String key) {
        if (mTypeMap.containsKey(key)) {
            return mTypeMap.get(key).second;
        }
        return null;
    }

    public JavaValueType getJavaValueType(final String key) {
        if (mTypeMap.containsKey(key)) {
            return mTypeMap.get(key).first;
        }
        return null;
    }

    public JSQLValueTypeMap put(final String key, final JavaValueType javaType, final SqlValueType sqlType) {
        mTypeMap.put(key, new Pair<JavaValueType, SqlValueType>(javaType, sqlType));
        return this;
    }

    public Set<String> keySet() {
        return mTypeMap.keySet();
    }
}
