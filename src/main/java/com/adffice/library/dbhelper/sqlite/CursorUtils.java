package com.adffice.library.dbhelper.sqlite;

import android.database.Cursor;
import android.util.Log;
import com.adffice.library.dbhelper.table.Property;
import com.adffice.library.dbhelper.table.TableInfo;
import java.util.Date;

public class CursorUtils {
    public static <T> T getEntity(Cursor cursor, TableInfo tableInfo) {
        if (cursor != null) {
            try {
                if (cursor.getColumnCount() > 0) {
                    T newInstance = tableInfo.getEntityClazz().newInstance();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        String columnName = cursor.getColumnName(i);
                        Property property = tableInfo.propertyMap.get(columnName);
                        if (property == null && tableInfo.getId().getColumn().equals(columnName)) {
                            property = tableInfo.getId();
                        }
                        if (property != null) {
                            if (property.getDataType().equals(String.class)) {
                                property.setValue(newInstance, cursor.getString(i));
                            } else {
                                if (!property.getDataType().equals(Integer.class)) {
                                    if (!property.getDataType().equals(Integer.TYPE)) {
                                        if (!property.getDataType().equals(Byte.class)) {
                                            if (!property.getDataType().equals(Byte.TYPE)) {
                                                if (!property.getDataType().equals(Long.class)) {
                                                    if (!property.getDataType().equals(Long.TYPE)) {
                                                        if (!property.getDataType().equals(Double.class)) {
                                                            if (!property.getDataType().equals(Double.TYPE)) {
                                                                if (!property.getDataType().equals(Float.class)) {
                                                                    if (!property.getDataType().equals(Float.TYPE)) {
                                                                        if (!property.getDataType().equals(Short.class)) {
                                                                            if (!property.getDataType().equals(Short.TYPE)) {
                                                                                if (!property.getDataType().equals(Byte[].class)) {
                                                                                    if (!property.getDataType().equals(byte[].class)) {
                                                                                        if (!property.getDataType().equals(Boolean.class)) {
                                                                                            if (!property.getDataType().equals(Boolean.TYPE)) {
                                                                                                if (!property.getDataType().equals(Character.class)) {
                                                                                                    if (!property.getDataType().equals(Character.TYPE)) {
                                                                                                        if (property.getDataType().equals(Date.class)) {
                                                                                                            property.setValue(newInstance, cursor.getLong(i) == 0 ? null : new Date(cursor.getLong(i)));
                                                                                                        } else {
                                                                                                            Log.e("dbhelper", "nonsupport data type " + property.getDataType());
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                property.setValue(newInstance, cursor.getString(i) == null ? null : Character.valueOf(cursor.getString(i).charAt(0)));
                                                                                            }
                                                                                        }
                                                                                        boolean z = true;
                                                                                        if (cursor.getInt(i) != 1) {
                                                                                            z = false;
                                                                                        }
                                                                                        property.setValue(newInstance, Boolean.valueOf(z));
                                                                                    }
                                                                                }
                                                                                property.setValue(newInstance, cursor.getBlob(i));
                                                                            }
                                                                        }
                                                                        property.setValue(newInstance, Short.valueOf(cursor.getShort(i)));
                                                                    }
                                                                }
                                                                property.setValue(newInstance, Float.valueOf(cursor.getFloat(i)));
                                                            }
                                                        }
                                                        property.setValue(newInstance, Double.valueOf(cursor.getDouble(i)));
                                                    }
                                                }
                                                property.setValue(newInstance, Long.valueOf(cursor.getLong(i)));
                                            }
                                        }
                                        property.setValue(newInstance, Byte.valueOf((byte) cursor.getInt(i)));
                                    }
                                }
                                property.setValue(newInstance, Integer.valueOf(cursor.getInt(i)));
                            }
                        }
                    }
                    return newInstance;
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
