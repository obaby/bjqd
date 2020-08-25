package com.adffice.library.dbhelper.table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Property {
    private String mColumn;
    private Class<?> mDataType;
    private String mDefaultVal;
    private Field mField;
    private String mFieldName;
    private Method mGetMethod;
    private Method mSetMethod;

    public void setValue(Object obj, Object obj2) {
        if (this.mSetMethod != null) {
            try {
                this.mSetMethod.invoke(obj, new Object[]{obj2});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                this.mField.setAccessible(true);
                this.mField.set(obj, obj2);
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
        }
    }

    public Object getValue(Object obj) {
        if (obj == null || this.mGetMethod == null) {
            return null;
        }
        try {
            return this.mGetMethod.invoke(obj, new Object[0]);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public String getFieldName() {
        return this.mFieldName;
    }

    public void setFieldName(String str) {
        this.mFieldName = str;
    }

    public String getColumn() {
        return this.mColumn;
    }

    public void setColumn(String str) {
        this.mColumn = str;
    }

    public String getDefaultVal() {
        return this.mDefaultVal;
    }

    public void setDefaultVal(String str) {
        this.mDefaultVal = str;
    }

    public Class<?> getDataType() {
        return this.mDataType;
    }

    public void setDataType(Class<?> cls) {
        this.mDataType = cls;
    }

    public Field getmField() {
        return this.mField;
    }

    public void setField(Field field) {
        this.mField = field;
    }

    public Method getGetMethod() {
        return this.mGetMethod;
    }

    public void setGetterMethod(Method method) {
        this.mGetMethod = method;
    }

    public Method getSetMethod() {
        return this.mSetMethod;
    }

    public void setSetterMethod(Method method) {
        this.mSetMethod = method;
    }
}
