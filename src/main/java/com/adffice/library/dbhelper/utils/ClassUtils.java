package com.adffice.library.dbhelper.utils;

import com.adffice.library.dbhelper.annotation.Table;

public class ClassUtils {
    public static String getTableName(Object obj) {
        if (obj != null) {
            return getTableName(obj.getClass());
        }
        throw new RuntimeException("No entity classes available");
    }

    public static String getTableName(Class<?> cls) {
        if (cls != null) {
            Table table = (Table) cls.getAnnotation(Table.class);
            if (table.name() == null || table.name().trim().length() == 0) {
                return table.getClass().getName().replace('.', '_');
            }
            return table.name();
        }
        throw new RuntimeException("No entity classes available");
    }
}
