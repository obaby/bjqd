package com.adffice.library.dbhelper.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import com.adffice.library.dbhelper.table.Property;
import com.adffice.library.dbhelper.table.TableInfo;
import java.util.Collection;
import java.util.Date;

public class SqlBuilder {
    public static SqlInfo buildCreateTableSql(TableInfo tableInfo) {
        if (tableInfo == null) {
            return null;
        }
        Property id = tableInfo.getId();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(tableInfo.getTableName());
        sb.append(" ( ");
        Class<?> dataType = id.getDataType();
        if (dataType == Integer.TYPE || dataType == Integer.class || dataType == Long.TYPE || dataType == Long.class) {
            sb.append(id.getColumn());
            sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        } else {
            sb.append(id.getColumn());
            sb.append(" TEXT PRIMARY KEY,");
        }
        for (Property next : tableInfo.propertyMap.values()) {
            sb.append(next.getColumn());
            Class<?> dataType2 = next.getDataType();
            if (dataType2 == Integer.TYPE || dataType2 == Integer.class || dataType2 == Long.TYPE || dataType2 == Long.class) {
                sb.append(" INTEGER");
            } else if (dataType2 == Float.TYPE || dataType2 == Float.class || dataType2 == Double.TYPE || dataType2 == Double.class) {
                sb.append(" REAL");
            } else if (dataType2 == Boolean.TYPE || dataType2 == Boolean.class) {
                sb.append(" NUMERIC");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" )");
        SqlInfo sqlInfo = new SqlInfo();
        sqlInfo.setSql(sb.toString());
        return sqlInfo;
    }

    public static SqlInfo buildDropTableSql(TableInfo tableInfo) {
        if (tableInfo == null) {
            return null;
        }
        SqlInfo sqlInfo = new SqlInfo();
        sqlInfo.setSql("DROP TABLE IF EXISTS " + tableInfo.getTableName());
        return sqlInfo;
    }

    public static SqlInfo buildQuerySql(TableInfo tableInfo, boolean z, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        String buildQueryString = SQLiteQueryBuilder.buildQueryString(z, tableInfo.getTableName(), strArr, str, str2, str3, str4, str5);
        SqlInfo sqlInfo = new SqlInfo();
        sqlInfo.setSql(buildQueryString);
        String[] strArr3 = strArr2;
        sqlInfo.setBindArgs(strArr2);
        return sqlInfo;
    }

    public static ContentValues buildContentValues(TableInfo tableInfo, Object obj) {
        Collection<Property> values = tableInfo.propertyMap.values();
        ContentValues contentValues = new ContentValues();
        for (Property next : values) {
            if (next.getDataType().equals(String.class)) {
                contentValues.put(next.getColumn(), (String) next.getValue(obj));
            } else if (next.getDataType().equals(Integer.class) || next.getDataType().equals(Integer.TYPE)) {
                contentValues.put(next.getColumn(), (Integer) next.getValue(obj));
            } else if (next.getDataType().equals(Byte.class) || next.getDataType().equals(Byte.TYPE)) {
                contentValues.put(next.getColumn(), (Byte) next.getValue(obj));
            } else if (next.getDataType().equals(Long.class) || next.getDataType().equals(Long.TYPE)) {
                contentValues.put(next.getColumn(), (Long) next.getValue(obj));
            } else if (next.getDataType().equals(Double.class) || next.getDataType().equals(Double.TYPE)) {
                contentValues.put(next.getColumn(), (Double) next.getValue(obj));
            } else if (next.getDataType().equals(Float.class) || next.getDataType().equals(Float.TYPE)) {
                contentValues.put(next.getColumn(), (Float) next.getValue(obj));
            } else if (next.getDataType().equals(Short.class) || next.getDataType().equals(Short.TYPE)) {
                contentValues.put(next.getColumn(), (Short) next.getValue(obj));
            } else if (next.getDataType().equals(Byte[].class) || next.getDataType().equals(byte[].class)) {
                contentValues.put(next.getColumn(), (byte[]) next.getValue(obj));
            } else if (next.getDataType().equals(Character.class) || next.getDataType().equals(Character.TYPE)) {
                String column = next.getColumn();
                StringBuilder sb = new StringBuilder();
                sb.append((Character) next.getValue(obj));
                contentValues.put(column, sb.toString());
            } else if (next.getDataType().equals(Date.class)) {
                Date date = (Date) next.getValue(obj);
                contentValues.put(next.getColumn(), date == null ? null : Long.valueOf(date.getTime()));
            } else if (next.getDataType().equals(Boolean.class) || next.getDataType().equals(Boolean.TYPE)) {
                contentValues.put(next.getColumn(), Integer.valueOf(((Boolean) next.getValue(obj)).booleanValue() ? 1 : 0));
            } else {
                Log.e("dbhelper", "nonsupport data type " + next.getDataType());
            }
        }
        return contentValues;
    }
}
