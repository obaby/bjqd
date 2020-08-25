package com.adffice.library.dbhelper.table;

import com.adffice.library.dbhelper.exception.DbException;
import com.adffice.library.dbhelper.utils.ClassUtils;
import com.adffice.library.dbhelper.utils.FieldUtils;
import java.lang.reflect.Field;
import java.util.HashMap;

public class TableInfo {
    private static final HashMap<String, TableInfo> mTableInfoMap = new HashMap<>();
    private String mClassName;
    private Class<?> mEntityClazz;
    private Property mId;
    private String mTableName;
    public final HashMap<String, Property> propertyMap = new HashMap<>();
    private boolean tableExist;

    private TableInfo() {
    }

    public static TableInfo get(Class<?> cls) {
        if (cls != null) {
            TableInfo tableInfo = mTableInfoMap.get(cls.getName());
            if (tableInfo != null) {
                return tableInfo;
            }
            TableInfo tableInfo2 = new TableInfo();
            tableInfo2.mEntityClazz = cls;
            tableInfo2.mTableName = ClassUtils.getTableName(cls);
            tableInfo2.mClassName = cls.getName();
            for (Field next : FieldUtils.getAllDeclaredFields(cls)) {
                if (!FieldUtils.isStaticField(next) && !FieldUtils.isTransientField(next) && FieldUtils.isBaseDateType(next)) {
                    Property property = new Property();
                    property.setColumn(FieldUtils.getColumnByField(next));
                    property.setFieldName(next.getName());
                    property.setDataType(next.getType());
                    property.setDefaultVal(FieldUtils.getPropertyDefaultValue(next));
                    property.setSetterMethod(FieldUtils.getFieldSetterMethod(cls, next));
                    property.setGetterMethod(FieldUtils.getFieldGetterMethod(cls, next));
                    property.setField(next);
                    if (tableInfo2.getId() != null || !FieldUtils.isPrimaryKeyField(next)) {
                        tableInfo2.propertyMap.put(property.getColumn(), property);
                    } else {
                        tableInfo2.mId = property;
                    }
                }
            }
            if (tableInfo2.getId() != null) {
                mTableInfoMap.put(cls.getName(), tableInfo2);
                return tableInfo2;
            }
            throw new DbException("the class[" + cls + "]'s idField is null , \n you can define _id property or use annotation @Id to solution this exception");
        }
        throw new DbException("table info get error, because the clazz is null");
    }

    public String getClassName() {
        return this.mClassName;
    }

    public String getTableName() {
        return this.mTableName;
    }

    public Property getId() {
        return this.mId;
    }

    public boolean isTableExist() {
        return this.tableExist;
    }

    public void setTableExist(boolean z) {
        this.tableExist = z;
    }

    public Class<?> getEntityClazz() {
        return this.mEntityClazz;
    }
}
