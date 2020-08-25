package com.adffice.library.dbhelper.utils;

import com.adffice.library.dbhelper.annotation.Id;
import com.adffice.library.dbhelper.annotation.Property;
import com.adffice.library.dbhelper.annotation.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FieldUtils {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Method getFieldGetterMethod(Class<?> cls, String str) {
        if (cls == null || str == null || str.trim().length() == 0) {
            return null;
        }
        return getFieldGetterMethod(cls, getFieldByName(cls, str));
    }

    public static Method getFieldSetterMethod(Class<?> cls, String str) {
        if (cls == null || str == null || str.trim().length() == 0) {
            return null;
        }
        return getFieldSetterMethod(cls, getFieldByName(cls, str));
    }

    public static Method getFieldGetterMethod(Class<?> cls, Field field) {
        Method method = null;
        if (cls == null || field == null) {
            return null;
        }
        String name = field.getName();
        String str = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            method = getBooleanFieldGetterMethod(cls, field);
        }
        if (method == null) {
            try {
                return cls.getMethod(str, new Class[0]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    public static Method getFieldSetterMethod(Class<?> cls, Field field) {
        Method method = null;
        if (cls == null || field == null) {
            return null;
        }
        String name = field.getName();
        String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            method = getBooleanFieldSetterMethod(cls, field);
        }
        if (method == null) {
            try {
                return cls.getMethod(str, new Class[]{field.getType()});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    private static Method getBooleanFieldGetterMethod(Class<?> cls, Field field) {
        if (cls == null || field == null) {
            return null;
        }
        String name = field.getName();
        String str = "is" + name.substring(0, 1).toUpperCase() + name.substring(1);
        if (!startWithIs(name)) {
            name = str;
        }
        try {
            return cls.getMethod(name, new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Method getBooleanFieldSetterMethod(Class<?> cls, Field field) {
        if (cls == null || field == null) {
            return null;
        }
        String name = field.getName();
        String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        if (startWithIs(name)) {
            str = "set" + name.substring(2, 3).toUpperCase() + name.substring(3);
        }
        try {
            return cls.getMethod(str, new Class[]{field.getType()});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValue(Object obj, Field field) {
        return invoke(obj, getFieldGetterMethod(obj.getClass(), field));
    }

    public static Object getFieldValue(Object obj, String str) {
        return invoke(obj, getFieldGetterMethod(obj.getClass(), str));
    }

    public static void setFieldValue(Object obj, Field field, Object obj2) {
        if (obj != null && field != null) {
            try {
                Method fieldSetterMethod = getFieldSetterMethod(obj.getClass(), field);
                if (fieldSetterMethod != null) {
                    fieldSetterMethod.setAccessible(true);
                    Class<?> type = field.getType();
                    Object obj3 = null;
                    if (type == String.class) {
                        Object[] objArr = new Object[1];
                        if (obj2 != null) {
                            obj3 = obj2.toString();
                        }
                        objArr[0] = obj3;
                        fieldSetterMethod.invoke(obj, objArr);
                        return;
                    }
                    if (type != Integer.TYPE) {
                        if (type != Integer.class) {
                            if (type != Float.TYPE) {
                                if (type != Float.class) {
                                    if (type != Long.TYPE) {
                                        if (type != Long.class) {
                                            if (type != Date.class) {
                                                if (type != java.sql.Date.class) {
                                                    fieldSetterMethod.invoke(obj, new Object[]{obj2});
                                                    return;
                                                }
                                            }
                                            Object[] objArr2 = new Object[1];
                                            if (obj2 != null) {
                                                obj3 = stringToDateTime(obj2.toString());
                                            }
                                            objArr2[0] = obj3;
                                            fieldSetterMethod.invoke(obj, objArr2);
                                            return;
                                        }
                                    }
                                    Object[] objArr3 = new Object[1];
                                    objArr3[0] = Long.valueOf(obj2 == null ? 0 : Long.parseLong(obj2.toString()));
                                    fieldSetterMethod.invoke(obj, objArr3);
                                    return;
                                }
                            }
                            Object[] objArr4 = new Object[1];
                            objArr4[0] = Float.valueOf(obj2 == null ? 0.0f : Float.parseFloat(obj2.toString()));
                            fieldSetterMethod.invoke(obj, objArr4);
                            return;
                        }
                    }
                    Object[] objArr5 = new Object[1];
                    objArr5[0] = Integer.valueOf(obj2 == null ? 0 : Integer.parseInt(obj2.toString()));
                    fieldSetterMethod.invoke(obj, objArr5);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        if (obj != null && str != null) {
            setFieldValue(obj, getFieldByName(obj.getClass(), str), obj2);
        }
    }

    public static Field getFieldByName(Class<?> cls, String str) {
        if (!(cls == null || str == null)) {
            try {
                return cls.getDeclaredField(str);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getColumnByField(Field field) {
        if (field == null) {
            return null;
        }
        Property property = (Property) field.getAnnotation(Property.class);
        if (property != null && property.column().trim().length() != 0) {
            return property.column();
        }
        Id id = (Id) field.getAnnotation(Id.class);
        if (id == null || id.column().trim().length() == 0) {
            return field.getName();
        }
        return id.column();
    }

    public static String getPropertyDefaultValue(Field field) {
        Property property = (Property) field.getAnnotation(Property.class);
        if (property == null || property.defaultValue().trim().length() == 0) {
            return null;
        }
        return property.defaultValue();
    }

    public static boolean isStaticField(Field field) {
        return field != null && Modifier.isStatic(field.getModifiers());
    }

    public static boolean isTransientField(Field field) {
        return (field == null || field.getAnnotation(Transient.class) == null) ? false : true;
    }

    public static boolean isPrimaryKeyField(Field field) {
        if (field != null) {
            return field.getAnnotation(Id.class) != null || field.getName().equals("_id");
        }
        return false;
    }

    public static boolean isBaseDateType(Field field) {
        Class<?> type = field.getType();
        return type.equals(String.class) || type.equals(Integer.class) || type.equals(Integer.TYPE) || type.equals(Byte.class) || type.equals(Byte.TYPE) || type.equals(Long.class) || type.equals(Long.TYPE) || type.equals(Double.class) || type.equals(Double.TYPE) || type.equals(Float.class) || type.equals(Float.TYPE) || type.equals(Short.class) || type.equals(Short.TYPE) || type.equals(Byte[].class) || type.equals(byte[].class) || type.equals(Boolean.class) || type.equals(Boolean.TYPE) || type.equals(Character.class) || type.equals(Character.TYPE) || type.equals(Date.class);
    }

    private static Object invoke(Object obj, Method method) {
        if (obj == null || method == null) {
            return null;
        }
        try {
            return method.invoke(obj, new Object[0]);
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

    private static boolean startWithIs(String str) {
        return str != null && str.startsWith("is") && Character.isUpperCase(str.charAt(2));
    }

    public static Date stringToDateTime(String str) {
        if (str == null) {
            return null;
        }
        try {
            return SDF.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Field> getAllDeclaredFields(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        if (cls == null || cls.getName().equals("java.lang.Object")) {
            return arrayList;
        }
        Collections.addAll(arrayList, cls.getDeclaredFields());
        arrayList.addAll(getAllDeclaredFields(cls.getSuperclass()));
        return arrayList;
    }
}
