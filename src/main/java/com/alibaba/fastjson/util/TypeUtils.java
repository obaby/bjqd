package com.alibaba.fastjson.util;

import anetwork.channel.util.RequestConstant;
import cn.xports.qd2.entity.K;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TypeUtils {
    private static Class<? extends Annotation> class_ManyToMany = null;
    private static boolean class_ManyToMany_error = false;
    private static Class<? extends Annotation> class_OneToMany = null;
    private static boolean class_OneToMany_error = false;
    public static boolean compatibleWithFieldName = false;
    public static boolean compatibleWithJavaBean = false;
    private static volatile Map<Class, String[]> kotlinIgnores = null;
    private static volatile boolean kotlinIgnores_error = false;
    private static volatile boolean kotlin_class_klass_error = false;
    private static volatile boolean kotlin_error = false;
    private static volatile Constructor kotlin_kclass_constructor = null;
    private static volatile Method kotlin_kclass_getConstructors = null;
    private static volatile Method kotlin_kfunction_getParameters = null;
    private static volatile Method kotlin_kparameter_getName = null;
    private static volatile Class kotlin_metadata = null;
    private static volatile boolean kotlin_metadata_error = false;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap(16, 0.75f, 1);
    private static Method method_HibernateIsInitialized = null;
    private static boolean method_HibernateIsInitialized_error = false;
    private static Class<?> optionalClass = null;
    private static boolean optionalClassInited = false;
    private static Method oracleDateMethod = null;
    private static boolean oracleDateMethodInited = false;
    private static Method oracleTimestampMethod = null;
    private static boolean oracleTimestampMethodInited = false;
    private static Class<?> pathClass = null;
    private static boolean pathClass_error = false;
    private static boolean setAccessibleEnable = true;
    private static Class<? extends Annotation> transientClass;
    private static boolean transientClassInited;

    static {
        try {
            compatibleWithJavaBean = RequestConstant.TRUE.equals(IOUtils.getStringProperty(IOUtils.FASTJSON_COMPATIBLEWITHJAVABEAN));
            compatibleWithFieldName = RequestConstant.TRUE.equals(IOUtils.getStringProperty(IOUtils.FASTJSON_COMPATIBLEWITHFIELDNAME));
        } catch (Throwable unused) {
        }
        addBaseClassMappings();
    }

    public static String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static Character castToChar(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw new JSONException("can not cast to char, value : " + obj);
        }
        throw new JSONException("can not cast to char, value : " + obj);
    }

    public static Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static BigDecimal castToBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        if (!(obj instanceof Map) || ((Map) obj).size() != 0) {
            return new BigDecimal(obj2);
        }
        return null;
    }

    public static BigInteger castToBigInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
            return null;
        }
        return new BigInteger(obj2);
    }

    public static Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != 0) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Float.valueOf(Float.parseFloat(obj2));
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != 0) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Double.valueOf(Double.parseDouble(obj2));
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static Date castToDate(Object obj) {
        return castToDate(obj, (String) null);
    }

    public static Date castToDate(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        long j = -1;
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            JSONScanner jSONScanner = new JSONScanner(str2);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    return jSONScanner.getCalendar().getTime();
                }
                jSONScanner.close();
                if (str2.startsWith("/Date(") && str2.endsWith(")/")) {
                    str2 = str2.substring(6, str2.length() - 2);
                }
                if (str2.indexOf(45) != -1) {
                    if (str == null) {
                        if (str2.length() == JSON.DEFFAULT_DATE_FORMAT.length() || (str2.length() == 22 && JSON.DEFFAULT_DATE_FORMAT.equals("yyyyMMddHHmmssSSSZ"))) {
                            str = JSON.DEFFAULT_DATE_FORMAT;
                        } else if (str2.length() == 10) {
                            str = "yyyy-MM-dd";
                        } else {
                            str = str2.length() == "yyyy-MM-dd HH:mm:ss".length() ? "yyyy-MM-dd HH:mm:ss" : (str2.length() == 29 && str2.charAt(26) == ':' && str2.charAt(28) == '0') ? "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" : "yyyy-MM-dd HH:mm:ss.SSS";
                        }
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                    simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
                    try {
                        return simpleDateFormat.parse(str2);
                    } catch (ParseException unused) {
                        throw new JSONException("can not cast to Date, value : " + str2);
                    }
                } else if (str2.length() == 0) {
                    return null;
                } else {
                    j = Long.parseLong(str2);
                }
            } finally {
                jSONScanner.close();
            }
        }
        if (j >= 0) {
            return new Date(j);
        }
        Class<?> cls = obj.getClass();
        if ("oracle.sql.TIMESTAMP".equals(cls.getName())) {
            if (oracleTimestampMethod == null && !oracleTimestampMethodInited) {
                try {
                    oracleTimestampMethod = cls.getMethod("toJdbc", new Class[0]);
                } catch (NoSuchMethodException unused2) {
                } catch (Throwable th) {
                    oracleTimestampMethodInited = true;
                    throw th;
                }
                oracleTimestampMethodInited = true;
            }
            try {
                return (Date) oracleTimestampMethod.invoke(obj, new Object[0]);
            } catch (Exception e) {
                throw new JSONException("can not cast oracle.sql.TIMESTAMP to Date", e);
            }
        } else if ("oracle.sql.DATE".equals(cls.getName())) {
            if (oracleDateMethod == null && !oracleDateMethodInited) {
                try {
                    oracleDateMethod = cls.getMethod("toJdbc", new Class[0]);
                } catch (NoSuchMethodException unused3) {
                } catch (Throwable th2) {
                    oracleDateMethodInited = true;
                    throw th2;
                }
                oracleDateMethodInited = true;
            }
            try {
                return (Date) oracleDateMethod.invoke(obj, new Object[0]);
            } catch (Exception e2) {
                throw new JSONException("can not cast oracle.sql.DATE to Date", e2);
            }
        } else {
            throw new JSONException("can not cast to Date, value : " + obj);
        }
    }

    public static java.sql.Date castToSqlDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (isNumber(str)) {
                longValue = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    longValue = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (longValue > 0) {
            return new java.sql.Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static Time castToSqlTime(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Time) {
            return (Time) obj;
        }
        if (obj instanceof Date) {
            return new Time(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new Time(((Calendar) obj).getTimeInMillis());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equalsIgnoreCase(str)) {
                return null;
            }
            if (isNumber(str)) {
                longValue = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    longValue = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (longValue > 0) {
            return new Time(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static Timestamp castToTimestamp(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new Timestamp(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.endsWith(".000000000")) {
                str = str.substring(0, str.length() - 10);
            } else if (str.endsWith(".000000")) {
                str = str.substring(0, str.length() - 7);
            }
            if (isNumber(str)) {
                longValue = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    longValue = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (longValue > 0) {
            return new Timestamp(longValue);
        }
        throw new JSONException("can not cast to Timestamp, value : " + obj);
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '+' || charAt == '-') {
                if (i != 0) {
                    return false;
                }
            } else if (charAt < '0' || charAt > '9') {
                return false;
            }
        }
        return true;
    }

    public static Long castToLong(Object obj) {
        Calendar calendar = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.indexOf(44) != 0) {
                str = str.replaceAll(",", "");
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    calendar = jSONScanner.getCalendar();
                }
                jSONScanner.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                Iterator it = map.values().iterator();
                it.next();
                return castToLong(it.next());
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static Integer castToInt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.indexOf(44) != 0) {
                str = str.replaceAll(",", "");
            }
            return Integer.valueOf(Integer.parseInt(str));
        } else if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                    Iterator it = map.values().iterator();
                    it.next();
                    return castToInt(it.next());
                }
            }
            throw new JSONException("can not cast to int, value : " + obj);
        }
    }

    public static byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return IOUtils.decodeBase64((String) obj);
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static Boolean castToBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            boolean z = true;
            if (((Number) obj).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (RequestConstant.TRUE.equalsIgnoreCase(str) || "1".equals(str)) {
                return Boolean.TRUE;
            }
            if (RequestConstant.FALSE.equalsIgnoreCase(str) || K.k0.equals(str)) {
                return Boolean.FALSE;
            }
            if ("Y".equalsIgnoreCase(str) || "T".equals(str)) {
                return Boolean.TRUE;
            }
            if ("F".equalsIgnoreCase(str) || "N".equals(str)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to boolean, value : " + obj);
    }

    public static <T> T castToJavaBean(Object obj, Class<T> cls) {
        return cast(obj, cls, ParserConfig.getGlobalInstance());
    }

    public static <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        T t;
        int i = 0;
        if (obj == null) {
            if (cls == Integer.TYPE) {
                return 0;
            }
            if (cls == Long.TYPE) {
                return 0L;
            }
            if (cls == Short.TYPE) {
                return (short) 0;
            }
            if (cls == Byte.TYPE) {
                return (byte) 0;
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(0.0f);
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(0.0d);
            }
            if (cls == Boolean.TYPE) {
                return Boolean.FALSE;
            }
            return null;
        } else if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (cls == obj.getClass()) {
            return obj;
        } else {
            if (!(obj instanceof Map)) {
                if (cls.isArray()) {
                    if (obj instanceof Collection) {
                        Collection<Object> collection = (Collection) obj;
                        T newInstance = Array.newInstance(cls.getComponentType(), collection.size());
                        for (Object cast : collection) {
                            Array.set(newInstance, i, cast(cast, cls.getComponentType(), parserConfig));
                            i++;
                        }
                        return newInstance;
                    } else if (cls == byte[].class) {
                        return castToBytes(obj);
                    }
                }
                if (cls.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
                if (cls == Boolean.TYPE || cls == Boolean.class) {
                    return castToBoolean(obj);
                }
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return castToByte(obj);
                }
                if (cls == Character.TYPE || cls == Character.class) {
                    return castToChar(obj);
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return castToShort(obj);
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return castToInt(obj);
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return castToLong(obj);
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return castToFloat(obj);
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return castToDouble(obj);
                }
                if (cls == String.class) {
                    return castToString(obj);
                }
                if (cls == BigDecimal.class) {
                    return castToBigDecimal(obj);
                }
                if (cls == BigInteger.class) {
                    return castToBigInteger(obj);
                }
                if (cls == Date.class) {
                    return castToDate(obj);
                }
                if (cls == java.sql.Date.class) {
                    return castToSqlDate(obj);
                }
                if (cls == Time.class) {
                    return castToSqlTime(obj);
                }
                if (cls == Timestamp.class) {
                    return castToTimestamp(obj);
                }
                if (cls.isEnum()) {
                    return castToEnum(obj, cls, parserConfig);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    Date castToDate = castToDate(obj);
                    if (cls == Calendar.class) {
                        t = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    } else {
                        try {
                            t = (Calendar) cls.newInstance();
                        } catch (Exception e) {
                            throw new JSONException("can not cast to : " + cls.getName(), e);
                        }
                    }
                    t.setTime(castToDate);
                    return t;
                }
                String name = cls.getName();
                if (name.equals("javax.xml.datatype.XMLGregorianCalendar")) {
                    Date castToDate2 = castToDate(obj);
                    Calendar instance = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    instance.setTime(castToDate2);
                    return CalendarCodec.instance.createXMLGregorianCalendar(instance);
                }
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                        return null;
                    }
                    if (cls == Currency.class) {
                        return Currency.getInstance(str);
                    }
                    if (cls == Locale.class) {
                        return toLocale(str);
                    }
                    if (name.startsWith("java.time.")) {
                        return JSON.parseObject(JSON.toJSONString(str), cls);
                    }
                }
                throw new JSONException("can not cast to : " + cls.getName());
            } else if (cls == Map.class) {
                return obj;
            } else {
                Map map = (Map) obj;
                if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return castToJavaBean(map, cls, parserConfig);
                }
                return obj;
            }
        }
    }

    public static Locale toLocale(String str) {
        String[] split = str.split("_");
        if (split.length == 1) {
            return new Locale(split[0]);
        }
        if (split.length == 2) {
            return new Locale(split[0], split[1]);
        }
        return new Locale(split[0], split[1], split[2]);
    }

    public static <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                if (parserConfig == null) {
                    parserConfig = ParserConfig.getGlobalInstance();
                }
                ObjectDeserializer deserializer = parserConfig.getDeserializer((Type) cls);
                if (deserializer instanceof EnumDeserializer) {
                    return ((EnumDeserializer) deserializer).getEnumByHashCode(fnv1a_64(str));
                }
                return Enum.valueOf(cls, str);
            }
            if (obj instanceof Number) {
                int intValue = ((Number) obj).intValue();
                T[] enumConstants = cls.getEnumConstants();
                if (intValue < enumConstants.length) {
                    return enumConstants[intValue];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    public static <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return cast(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return cast(obj, (ParameterizedType) type, parserConfig);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        T t;
        Type rawType = parameterizedType.getRawType();
        if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == Collection.class || rawType == List.class || rawType == ArrayList.class) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                if (rawType == Set.class || rawType == HashSet.class) {
                    t = new HashSet();
                } else if (rawType == TreeSet.class) {
                    t = new TreeSet();
                } else {
                    t = new ArrayList();
                }
                for (Object cast : (Iterable) obj) {
                    t.add(cast(cast, type, parserConfig));
                }
                return t;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                T hashMap = new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    hashMap.put(cast(entry.getKey(), type2, parserConfig), cast(entry.getValue(), type3, parserConfig));
                }
                return hashMap;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return cast(obj, rawType, parserConfig);
        }
        if (rawType == Map.Entry.class && (obj instanceof Map)) {
            Map map = (Map) obj;
            if (map.size() == 1) {
                return (Map.Entry) map.entrySet().iterator().next();
            }
        }
        if (rawType instanceof Class) {
            if (parserConfig == null) {
                parserConfig = ParserConfig.global;
            }
            ObjectDeserializer deserializer = parserConfig.getDeserializer(rawType);
            if (deserializer != null) {
                return deserializer.deserialze(new DefaultJSONParser(JSON.toJSONString(obj), parserConfig), parameterizedType, (Object) null);
            }
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [com.alibaba.fastjson.parser.deserializer.ObjectDeserializer] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T castToJavaBean(java.util.Map<java.lang.String, java.lang.Object> r4, java.lang.Class<T> r5, com.alibaba.fastjson.parser.ParserConfig r6) {
        /*
            java.lang.Class<java.lang.StackTraceElement> r0 = java.lang.StackTraceElement.class
            r1 = 0
            if (r5 != r0) goto L_0x0032
            java.lang.String r5 = "className"
            java.lang.Object r5 = r4.get(r5)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x0131 }
            java.lang.String r6 = "methodName"
            java.lang.Object r6 = r4.get(r6)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0131 }
            java.lang.String r0 = "fileName"
            java.lang.Object r0 = r4.get(r0)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0131 }
            java.lang.String r2 = "lineNumber"
            java.lang.Object r4 = r4.get(r2)     // Catch:{ Exception -> 0x0131 }
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ Exception -> 0x0131 }
            if (r4 != 0) goto L_0x0028
            goto L_0x002c
        L_0x0028:
            int r1 = r4.intValue()     // Catch:{ Exception -> 0x0131 }
        L_0x002c:
            java.lang.StackTraceElement r4 = new java.lang.StackTraceElement     // Catch:{ Exception -> 0x0131 }
            r4.<init>(r5, r6, r0, r1)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x0032:
            java.lang.String r0 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ Exception -> 0x0131 }
            java.lang.Object r0 = r4.get(r0)     // Catch:{ Exception -> 0x0131 }
            boolean r2 = r0 instanceof java.lang.String     // Catch:{ Exception -> 0x0131 }
            r3 = 0
            if (r2 == 0) goto L_0x006b
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0131 }
            if (r6 != 0) goto L_0x0043
            com.alibaba.fastjson.parser.ParserConfig r6 = com.alibaba.fastjson.parser.ParserConfig.global     // Catch:{ Exception -> 0x0131 }
        L_0x0043:
            java.lang.Class r2 = r6.checkAutoType(r0, r3)     // Catch:{ Exception -> 0x0131 }
            if (r2 == 0) goto L_0x0054
            boolean r0 = r2.equals(r5)     // Catch:{ Exception -> 0x0131 }
            if (r0 != 0) goto L_0x006b
            java.lang.Object r4 = castToJavaBean(r4, r2, r6)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x0054:
            java.lang.ClassNotFoundException r4 = new java.lang.ClassNotFoundException     // Catch:{ Exception -> 0x0131 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0131 }
            r5.<init>()     // Catch:{ Exception -> 0x0131 }
            r5.append(r0)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r6 = " not found"
            r5.append(r6)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0131 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0131 }
            throw r4     // Catch:{ Exception -> 0x0131 }
        L_0x006b:
            boolean r0 = r5.isInterface()     // Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x00ab
            boolean r0 = r4 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x0078
            com.alibaba.fastjson.JSONObject r4 = (com.alibaba.fastjson.JSONObject) r4     // Catch:{ Exception -> 0x0131 }
            goto L_0x007e
        L_0x0078:
            com.alibaba.fastjson.JSONObject r0 = new com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x0131 }
            r0.<init>((java.util.Map<java.lang.String, java.lang.Object>) r4)     // Catch:{ Exception -> 0x0131 }
            r4 = r0
        L_0x007e:
            if (r6 != 0) goto L_0x0084
            com.alibaba.fastjson.parser.ParserConfig r6 = com.alibaba.fastjson.parser.ParserConfig.getGlobalInstance()     // Catch:{ Exception -> 0x0131 }
        L_0x0084:
            com.alibaba.fastjson.util.IdentityHashMap r6 = r6.getDeserializers()     // Catch:{ Exception -> 0x0131 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ Exception -> 0x0131 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r6 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r6     // Catch:{ Exception -> 0x0131 }
            if (r6 == 0) goto L_0x0099
            java.lang.String r4 = com.alibaba.fastjson.JSON.toJSONString(r4)     // Catch:{ Exception -> 0x0131 }
            java.lang.Object r4 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r4, r5)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x0099:
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x0131 }
            java.lang.ClassLoader r6 = r6.getContextClassLoader()     // Catch:{ Exception -> 0x0131 }
            r0 = 1
            java.lang.Class[] r0 = new java.lang.Class[r0]     // Catch:{ Exception -> 0x0131 }
            r0[r1] = r5     // Catch:{ Exception -> 0x0131 }
            java.lang.Object r4 = java.lang.reflect.Proxy.newProxyInstance(r6, r0, r4)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x00ab:
            java.lang.Class<java.util.Locale> r0 = java.util.Locale.class
            if (r5 != r0) goto L_0x00d5
            java.lang.String r0 = "language"
            java.lang.Object r0 = r4.get(r0)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r1 = "country"
            java.lang.Object r1 = r4.get(r1)     // Catch:{ Exception -> 0x0131 }
            boolean r2 = r0 instanceof java.lang.String     // Catch:{ Exception -> 0x0131 }
            if (r2 == 0) goto L_0x00d5
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0131 }
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ Exception -> 0x0131 }
            if (r2 == 0) goto L_0x00cd
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0131 }
            java.util.Locale r4 = new java.util.Locale     // Catch:{ Exception -> 0x0131 }
            r4.<init>(r0, r1)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x00cd:
            if (r1 != 0) goto L_0x00d5
            java.util.Locale r4 = new java.util.Locale     // Catch:{ Exception -> 0x0131 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x00d5:
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            if (r5 != r0) goto L_0x00e2
            boolean r0 = r4 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x00e2
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x00e2:
            java.lang.Class<java.util.LinkedHashMap> r0 = java.util.LinkedHashMap.class
            if (r5 != r0) goto L_0x00fe
            boolean r0 = r4 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x00fe
            r0 = r4
            com.alibaba.fastjson.JSONObject r0 = (com.alibaba.fastjson.JSONObject) r0     // Catch:{ Exception -> 0x0131 }
            java.util.Map r0 = r0.getInnerMap()     // Catch:{ Exception -> 0x0131 }
            boolean r1 = r0 instanceof java.util.LinkedHashMap     // Catch:{ Exception -> 0x0131 }
            if (r1 == 0) goto L_0x00f6
            return r0
        L_0x00f6:
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap     // Catch:{ Exception -> 0x0131 }
            r1.<init>()     // Catch:{ Exception -> 0x0131 }
            r1.putAll(r0)     // Catch:{ Exception -> 0x0131 }
        L_0x00fe:
            if (r6 != 0) goto L_0x0104
            com.alibaba.fastjson.parser.ParserConfig r6 = com.alibaba.fastjson.parser.ParserConfig.getGlobalInstance()     // Catch:{ Exception -> 0x0131 }
        L_0x0104:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r0 = r6.getDeserializer((java.lang.reflect.Type) r5)     // Catch:{ Exception -> 0x0131 }
            boolean r1 = r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ Exception -> 0x0131 }
            if (r1 == 0) goto L_0x010f
            r3 = r0
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r3     // Catch:{ Exception -> 0x0131 }
        L_0x010f:
            if (r3 == 0) goto L_0x0116
            java.lang.Object r4 = r3.createInstance((java.util.Map<java.lang.String, java.lang.Object>) r4, (com.alibaba.fastjson.parser.ParserConfig) r6)     // Catch:{ Exception -> 0x0131 }
            return r4
        L_0x0116:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ Exception -> 0x0131 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0131 }
            r6.<init>()     // Catch:{ Exception -> 0x0131 }
            java.lang.String r0 = "can not get javaBeanDeserializer. "
            r6.append(r0)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x0131 }
            r6.append(r5)     // Catch:{ Exception -> 0x0131 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x0131 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0131 }
            throw r4     // Catch:{ Exception -> 0x0131 }
        L_0x0131:
            r4 = move-exception
            com.alibaba.fastjson.JSONException r5 = new com.alibaba.fastjson.JSONException
            java.lang.String r6 = r4.getMessage()
            r5.<init>(r6, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.castToJavaBean(java.util.Map, java.lang.Class, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    private static void addBaseClassMappings() {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put("[B", byte[].class);
        mappings.put("[S", short[].class);
        mappings.put("[I", int[].class);
        mappings.put("[J", long[].class);
        mappings.put("[F", float[].class);
        mappings.put("[D", double[].class);
        mappings.put("[C", char[].class);
        mappings.put("[Z", boolean[].class);
        int i = 0;
        for (Class cls : new Class[]{Object.class, Cloneable.class, loadClass("java.lang.AutoCloseable"), Exception.class, RuntimeException.class, IllegalAccessError.class, IllegalAccessException.class, IllegalArgumentException.class, IllegalMonitorStateException.class, IllegalStateException.class, IllegalThreadStateException.class, IndexOutOfBoundsException.class, InstantiationError.class, InstantiationException.class, InternalError.class, InterruptedException.class, LinkageError.class, NegativeArraySizeException.class, NoClassDefFoundError.class, NoSuchFieldError.class, NoSuchFieldException.class, NoSuchMethodError.class, NoSuchMethodException.class, NullPointerException.class, NumberFormatException.class, OutOfMemoryError.class, SecurityException.class, StackOverflowError.class, StringIndexOutOfBoundsException.class, TypeNotPresentException.class, VerifyError.class, StackTraceElement.class, HashMap.class, Hashtable.class, TreeMap.class, IdentityHashMap.class, WeakHashMap.class, LinkedHashMap.class, HashSet.class, LinkedHashSet.class, TreeSet.class, TimeUnit.class, ConcurrentHashMap.class, loadClass("java.util.concurrent.ConcurrentSkipListMap"), loadClass("java.util.concurrent.ConcurrentSkipListSet"), AtomicInteger.class, AtomicLong.class, Collections.EMPTY_MAP.getClass(), BitSet.class, Calendar.class, Date.class, Locale.class, UUID.class, Time.class, java.sql.Date.class, Timestamp.class, SimpleDateFormat.class, JSONObject.class}) {
            if (cls != null) {
                mappings.put(cls.getName(), cls);
            }
        }
        for (String loadClass : new String[]{"java.awt.Rectangle", "java.awt.Point", "java.awt.Font", "java.awt.Color"}) {
            Class<?> loadClass2 = loadClass(loadClass);
            if (loadClass2 == null) {
                break;
            }
            mappings.put(loadClass2.getName(), loadClass2);
        }
        String[] strArr = {"org.springframework.util.LinkedMultiValueMap", "org.springframework.util.LinkedCaseInsensitiveMap", "org.springframework.remoting.support.RemoteInvocation", "org.springframework.remoting.support.RemoteInvocationResult", "org.springframework.security.web.savedrequest.DefaultSavedRequest", "org.springframework.security.web.savedrequest.SavedCookie", "org.springframework.security.web.csrf.DefaultCsrfToken", "org.springframework.security.web.authentication.WebAuthenticationDetails", "org.springframework.security.core.context.SecurityContextImpl", "org.springframework.security.authentication.UsernamePasswordAuthenticationToken", "org.springframework.security.core.authority.SimpleGrantedAuthority", "org.springframework.security.core.userdetails.User"};
        int length = strArr.length;
        while (i < length) {
            Class<?> loadClass3 = loadClass(strArr[i]);
            if (loadClass3 != null) {
                mappings.put(loadClass3.getName(), loadClass3);
                i++;
            } else {
                return;
            }
        }
    }

    public static void clearClassMapping() {
        mappings.clear();
        addBaseClassMappings();
    }

    public static Class<?> loadClass(String str) {
        return loadClass(str, (ClassLoader) null);
    }

    public static boolean isPath(Class<?> cls) {
        if (pathClass == null && !pathClass_error) {
            try {
                pathClass = Class.forName("java.nio.file.Path");
            } catch (Throwable unused) {
                pathClass_error = true;
            }
        }
        if (pathClass != null) {
            return pathClass.isAssignableFrom(cls);
        }
        return false;
    }

    public static Class<?> getClassFromMapping(String str) {
        return (Class) mappings.get(str);
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader) {
        return loadClass(str, classLoader, true);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:(4:17|18|(2:20|21)|23)|27|28|(3:31|(2:33|34)|37)|38|39|40|41|42) */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0082 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0079 A[SYNTHETIC, Splitter:B:33:0x0079] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<?> loadClass(java.lang.String r6, java.lang.ClassLoader r7, boolean r8) {
        /*
            if (r6 == 0) goto L_0x008e
            int r0 = r6.length()
            if (r0 != 0) goto L_0x000a
            goto L_0x008e
        L_0x000a:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r0 = mappings
            java.lang.Object r0 = r0.get(r6)
            java.lang.Class r0 = (java.lang.Class) r0
            if (r0 == 0) goto L_0x0015
            return r0
        L_0x0015:
            r1 = 0
            char r2 = r6.charAt(r1)
            r3 = 91
            r4 = 1
            if (r2 != r3) goto L_0x0030
            java.lang.String r6 = r6.substring(r4)
            java.lang.Class r6 = loadClass(r6, r7)
            java.lang.Object r6 = java.lang.reflect.Array.newInstance(r6, r1)
            java.lang.Class r6 = r6.getClass()
            return r6
        L_0x0030:
            java.lang.String r1 = "L"
            boolean r1 = r6.startsWith(r1)
            if (r1 == 0) goto L_0x004e
            java.lang.String r1 = ";"
            boolean r1 = r6.endsWith(r1)
            if (r1 == 0) goto L_0x004e
            int r8 = r6.length()
            int r8 = r8 - r4
            java.lang.String r6 = r6.substring(r4, r8)
            java.lang.Class r6 = loadClass(r6, r7)
            return r6
        L_0x004e:
            if (r7 == 0) goto L_0x0067
            java.lang.Class r1 = r7.loadClass(r6)     // Catch:{ Throwable -> 0x005f }
            if (r8 == 0) goto L_0x005e
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r0 = mappings     // Catch:{ Throwable -> 0x005c }
            r0.put(r6, r1)     // Catch:{ Throwable -> 0x005c }
            goto L_0x005e
        L_0x005c:
            r0 = move-exception
            goto L_0x0063
        L_0x005e:
            return r1
        L_0x005f:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0063:
            r0.printStackTrace()
            r0 = r1
        L_0x0067:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x0082 }
            java.lang.ClassLoader r1 = r1.getContextClassLoader()     // Catch:{ Throwable -> 0x0082 }
            if (r1 == 0) goto L_0x0082
            if (r1 == r7) goto L_0x0082
            java.lang.Class r7 = r1.loadClass(r6)     // Catch:{ Throwable -> 0x0082 }
            if (r8 == 0) goto L_0x0081
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r8 = mappings     // Catch:{ Throwable -> 0x007f }
            r8.put(r6, r7)     // Catch:{ Throwable -> 0x007f }
            goto L_0x0081
        L_0x007f:
            r0 = r7
            goto L_0x0082
        L_0x0081:
            return r7
        L_0x0082:
            java.lang.Class r7 = java.lang.Class.forName(r6)     // Catch:{ Throwable -> 0x008c }
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r8 = mappings     // Catch:{ Throwable -> 0x008d }
            r8.put(r6, r7)     // Catch:{ Throwable -> 0x008d }
            return r7
        L_0x008c:
            r7 = r0
        L_0x008d:
            return r7
        L_0x008e:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.loadClass(java.lang.String, java.lang.ClassLoader, boolean):java.lang.Class");
    }

    public static SerializeBeanInfo buildBeanInfo(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy) {
        return buildBeanInfo(cls, map, propertyNamingStrategy, false);
    }

    /* JADX WARNING: type inference failed for: r16v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.serializer.SerializeBeanInfo buildBeanInfo(java.lang.Class<?> r16, java.util.Map<java.lang.String, java.lang.String> r17, com.alibaba.fastjson.PropertyNamingStrategy r18, boolean r19) {
        /*
            r6 = r16
            r7 = r17
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r0 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r0 = getAnnotation(r6, r0)
            r9 = r0
            com.alibaba.fastjson.annotation.JSONType r9 = (com.alibaba.fastjson.annotation.JSONType) r9
            r0 = 0
            r1 = 0
            if (r9 == 0) goto L_0x008b
            java.lang.String[] r2 = r9.orders()
            java.lang.String r3 = r9.typeName()
            int r4 = r3.length()
            if (r4 != 0) goto L_0x0020
            r3 = r1
        L_0x0020:
            com.alibaba.fastjson.PropertyNamingStrategy r4 = r9.naming()
            com.alibaba.fastjson.PropertyNamingStrategy r5 = com.alibaba.fastjson.PropertyNamingStrategy.CamelCase
            if (r4 == r5) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r4 = r18
        L_0x002b:
            com.alibaba.fastjson.serializer.SerializerFeature[] r5 = r9.serialzeFeatures()
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.of(r5)
            java.lang.Class r10 = r16.getSuperclass()
            r11 = r1
        L_0x0038:
            if (r10 == 0) goto L_0x0059
            java.lang.Class<java.lang.Object> r12 = java.lang.Object.class
            if (r10 == r12) goto L_0x0059
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r12 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r12 = getAnnotation(r10, r12)
            com.alibaba.fastjson.annotation.JSONType r12 = (com.alibaba.fastjson.annotation.JSONType) r12
            if (r12 != 0) goto L_0x0049
            goto L_0x0059
        L_0x0049:
            java.lang.String r11 = r12.typeKey()
            int r12 = r11.length()
            if (r12 == 0) goto L_0x0054
            goto L_0x0059
        L_0x0054:
            java.lang.Class r10 = r10.getSuperclass()
            goto L_0x0038
        L_0x0059:
            java.lang.Class[] r10 = r16.getInterfaces()
            int r12 = r10.length
            r13 = r11
            r11 = 0
        L_0x0060:
            if (r11 >= r12) goto L_0x007c
            r14 = r10[r11]
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r15 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r14 = getAnnotation(r14, r15)
            com.alibaba.fastjson.annotation.JSONType r14 = (com.alibaba.fastjson.annotation.JSONType) r14
            if (r14 == 0) goto L_0x0079
            java.lang.String r13 = r14.typeKey()
            int r14 = r13.length()
            if (r14 == 0) goto L_0x0079
            goto L_0x007c
        L_0x0079:
            int r11 = r11 + 1
            goto L_0x0060
        L_0x007c:
            if (r13 == 0) goto L_0x0085
            int r10 = r13.length()
            if (r10 != 0) goto L_0x0085
            r13 = r1
        L_0x0085:
            r11 = r2
            r10 = r4
            r12 = r5
            r14 = r13
            r13 = r3
            goto L_0x0091
        L_0x008b:
            r10 = r18
            r11 = r1
            r13 = r11
            r14 = r13
            r12 = 0
        L_0x0091:
            java.util.HashMap r15 = new java.util.HashMap
            r15.<init>()
            com.alibaba.fastjson.parser.ParserConfig.parserAllFieldToCache(r6, r15)
            if (r19 == 0) goto L_0x00a0
            java.util.List r0 = computeGettersWithFieldBase(r6, r7, r0, r10)
            goto L_0x00ac
        L_0x00a0:
            r4 = 0
            r0 = r16
            r1 = r9
            r2 = r17
            r3 = r15
            r5 = r10
            java.util.List r0 = computeGetters(r0, r1, r2, r3, r4, r5)
        L_0x00ac:
            int r1 = r0.size()
            com.alibaba.fastjson.util.FieldInfo[] r5 = new com.alibaba.fastjson.util.FieldInfo[r1]
            r0.toArray(r5)
            if (r11 == 0) goto L_0x00d1
            int r1 = r11.length
            if (r1 == 0) goto L_0x00d1
            if (r19 == 0) goto L_0x00c3
            r0 = 1
            java.util.List r0 = computeGettersWithFieldBase(r6, r7, r0, r10)
            r7 = r5
            goto L_0x00db
        L_0x00c3:
            r4 = 1
            r0 = r16
            r1 = r9
            r2 = r17
            r3 = r15
            r7 = r5
            r5 = r10
            java.util.List r0 = computeGetters(r0, r1, r2, r3, r4, r5)
            goto L_0x00db
        L_0x00d1:
            r7 = r5
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
            java.util.Collections.sort(r1)
            r0 = r1
        L_0x00db:
            int r1 = r0.size()
            com.alibaba.fastjson.util.FieldInfo[] r1 = new com.alibaba.fastjson.util.FieldInfo[r1]
            r0.toArray(r1)
            boolean r0 = java.util.Arrays.equals(r1, r7)
            if (r0 == 0) goto L_0x00ec
            r8 = r7
            goto L_0x00ed
        L_0x00ec:
            r8 = r1
        L_0x00ed:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r10 = new com.alibaba.fastjson.serializer.SerializeBeanInfo
            r0 = r10
            r1 = r16
            r2 = r9
            r3 = r13
            r4 = r14
            r5 = r12
            r6 = r7
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.buildBeanInfo(java.lang.Class, java.util.Map, com.alibaba.fastjson.PropertyNamingStrategy, boolean):com.alibaba.fastjson.serializer.SerializeBeanInfo");
    }

    public static List<FieldInfo> computeGettersWithFieldBase(Class<?> cls, Map<String, String> map, boolean z, PropertyNamingStrategy propertyNamingStrategy) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            computeFields(cls2, map, propertyNamingStrategy, linkedHashMap, cls2.getDeclaredFields());
        }
        return getFieldInfos(cls, z, linkedHashMap);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map) {
        return computeGetters(cls, map, true);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map, boolean z) {
        HashMap hashMap = new HashMap();
        ParserConfig.parserAllFieldToCache(cls, hashMap);
        return computeGetters(cls, (JSONType) getAnnotation(cls, JSONType.class), map, hashMap, z, PropertyNamingStrategy.CamelCase);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0464, code lost:
        if (r0 == null) goto L_0x049f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x039e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03d0  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x03fd  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x041a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x021d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.alibaba.fastjson.util.FieldInfo> computeGetters(java.lang.Class<?> r39, com.alibaba.fastjson.annotation.JSONType r40, java.util.Map<java.lang.String, java.lang.String> r41, java.util.Map<java.lang.String, java.lang.reflect.Field> r42, boolean r43, com.alibaba.fastjson.PropertyNamingStrategy r44) {
        /*
            r12 = r39
            r13 = r41
            r14 = r42
            r15 = r44
            java.util.LinkedHashMap r11 = new java.util.LinkedHashMap
            r11.<init>()
            boolean r16 = isKotlin(r39)
            r17 = 0
            r0 = r17
            java.lang.annotation.Annotation[][] r0 = (java.lang.annotation.Annotation[][]) r0
            java.lang.reflect.Method[] r10 = r39.getMethods()
            int r9 = r10.length
            r3 = r0
            r0 = r17
            r1 = r0
            r2 = r1
            r7 = 0
        L_0x0022:
            if (r7 >= r9) goto L_0x04ed
            r6 = r10[r7]
            java.lang.String r5 = r6.getName()
            r18 = 0
            int r4 = r6.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x0037
            goto L_0x0087
        L_0x0037:
            java.lang.Class r4 = r6.getReturnType()
            java.lang.Class r8 = java.lang.Void.TYPE
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x0044
            goto L_0x0087
        L_0x0044:
            java.lang.Class[] r4 = r6.getParameterTypes()
            int r4 = r4.length
            if (r4 == 0) goto L_0x004c
            goto L_0x0087
        L_0x004c:
            java.lang.Class r4 = r6.getReturnType()
            java.lang.Class<java.lang.ClassLoader> r8 = java.lang.ClassLoader.class
            if (r4 != r8) goto L_0x0055
            goto L_0x0087
        L_0x0055:
            java.lang.String r4 = "getMetaClass"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x006e
            java.lang.Class r4 = r6.getReturnType()
            java.lang.String r4 = r4.getName()
            java.lang.String r8 = "groovy.lang.MetaClass"
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x006e
            goto L_0x0087
        L_0x006e:
            java.lang.String r4 = "getSuppressed"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x007f
            java.lang.Class r4 = r6.getDeclaringClass()
            java.lang.Class<java.lang.Throwable> r8 = java.lang.Throwable.class
            if (r4 != r8) goto L_0x007f
            goto L_0x0087
        L_0x007f:
            if (r16 == 0) goto L_0x0094
            boolean r4 = isKotlinIgnore(r12, r5)
            if (r4 == 0) goto L_0x0094
        L_0x0087:
            r26 = r0
            r30 = r7
            r32 = r9
            r33 = r10
            r0 = r11
            r24 = 0
            goto L_0x04e0
        L_0x0094:
            r4 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r4 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r4 = r6.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            if (r4 != 0) goto L_0x00a7
            com.alibaba.fastjson.annotation.JSONField r4 = getSuperMethodAnnotation(r12, r6)
        L_0x00a7:
            r20 = r11
            if (r4 != 0) goto L_0x016c
            if (r16 == 0) goto L_0x016c
            if (r0 != 0) goto L_0x00f2
            java.lang.reflect.Constructor[] r0 = r39.getDeclaredConstructors()
            java.lang.reflect.Constructor r21 = getKoltinConstructor(r0)
            if (r21 == 0) goto L_0x00f0
            java.lang.annotation.Annotation[][] r3 = r21.getParameterAnnotations()
            java.lang.String[] r1 = getKoltinConstructorParameters(r39)
            if (r1 == 0) goto L_0x00eb
            int r2 = r1.length
            java.lang.String[] r2 = new java.lang.String[r2]
            int r11 = r1.length
            r23 = r3
            r3 = 0
            java.lang.System.arraycopy(r1, r3, r2, r3, r11)
            java.util.Arrays.sort(r2)
            int r11 = r1.length
            short[] r11 = new short[r11]
            r25 = r0
        L_0x00d5:
            int r0 = r1.length
            if (r3 >= r0) goto L_0x00e4
            r0 = r1[r3]
            int r0 = java.util.Arrays.binarySearch(r2, r0)
            r11[r0] = r3
            int r3 = r3 + 1
            short r3 = (short) r3
            goto L_0x00d5
        L_0x00e4:
            r1 = r2
            r2 = r11
            r3 = r23
            r0 = r25
            goto L_0x00f2
        L_0x00eb:
            r25 = r0
            r23 = r3
            goto L_0x00f2
        L_0x00f0:
            r25 = r0
        L_0x00f2:
            if (r1 == 0) goto L_0x0163
            if (r2 == 0) goto L_0x0163
            java.lang.String r11 = "get"
            boolean r11 = r5.startsWith(r11)
            if (r11 == 0) goto L_0x0163
            r11 = 3
            java.lang.String r19 = r5.substring(r11)
            java.lang.String r11 = decapitalize(r19)
            int r19 = java.util.Arrays.binarySearch(r1, r11)
            if (r19 >= 0) goto L_0x0121
            r26 = r0
            r27 = r4
            r0 = 0
        L_0x0112:
            int r4 = r1.length
            if (r0 >= r4) goto L_0x0125
            r4 = r1[r0]
            boolean r4 = r11.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x011e
            goto L_0x0127
        L_0x011e:
            int r0 = r0 + 1
            goto L_0x0112
        L_0x0121:
            r26 = r0
            r27 = r4
        L_0x0125:
            r0 = r19
        L_0x0127:
            if (r0 < 0) goto L_0x0170
            short r0 = r2[r0]
            r0 = r3[r0]
            if (r0 == 0) goto L_0x014c
            int r4 = r0.length
            r28 = r1
            r1 = 0
        L_0x0133:
            if (r1 >= r4) goto L_0x014e
            r29 = r2
            r2 = r0[r1]
            r30 = r0
            boolean r0 = r2 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r0 == 0) goto L_0x0145
            r4 = r2
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            r27 = r4
            goto L_0x0150
        L_0x0145:
            int r1 = r1 + 1
            r2 = r29
            r0 = r30
            goto L_0x0133
        L_0x014c:
            r28 = r1
        L_0x014e:
            r29 = r2
        L_0x0150:
            if (r27 != 0) goto L_0x0174
            java.lang.reflect.Field r0 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r11, r14)
            if (r0 == 0) goto L_0x0174
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = r0.getAnnotation(r1)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            r27 = r0
            goto L_0x0174
        L_0x0163:
            r26 = r0
            r28 = r1
            r29 = r2
            r27 = r4
            goto L_0x0174
        L_0x016c:
            r27 = r4
            r26 = r0
        L_0x0170:
            r28 = r1
            r29 = r2
        L_0x0174:
            r19 = r3
            if (r27 == 0) goto L_0x0202
            boolean r0 = r27.serialize()
            if (r0 != 0) goto L_0x018a
        L_0x017e:
            r30 = r7
            r32 = r9
            r33 = r10
            r0 = r20
            r24 = 0
            goto L_0x04da
        L_0x018a:
            int r11 = r27.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = r27.serialzeFeatures()
            int r21 = com.alibaba.fastjson.serializer.SerializerFeature.of(r0)
            com.alibaba.fastjson.parser.Feature[] r0 = r27.parseFeatures()
            int r23 = com.alibaba.fastjson.parser.Feature.of(r0)
            java.lang.String r0 = r27.name()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x01e9
            java.lang.String r0 = r27.name()
            if (r13 == 0) goto L_0x01b7
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x01b7
            goto L_0x017e
        L_0x01b7:
            r8 = r0
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r22 = 0
            r25 = 0
            r0 = r5
            r1 = r8
            r2 = r6
            r24 = 0
            r4 = r39
            r6 = r5
            r5 = r22
            r31 = r6
            r6 = r11
            r30 = r7
            r7 = r21
            r11 = r8
            r8 = r23
            r32 = r9
            r9 = r27
            r33 = r10
            r10 = r25
            r13 = r11
            r15 = r20
            r11 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r31
            r15.put(r13, r0)
            goto L_0x0224
        L_0x01e9:
            r30 = r7
            r32 = r9
            r33 = r10
            r15 = r20
            r24 = 0
            java.lang.String r0 = r27.label()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x0211
            java.lang.String r18 = r27.label()
            goto L_0x0211
        L_0x0202:
            r30 = r7
            r32 = r9
            r33 = r10
            r15 = r20
            r24 = 0
            r11 = 0
            r21 = 0
            r23 = 0
        L_0x0211:
            java.lang.String r0 = "get"
            boolean r0 = r5.startsWith(r0)
            r13 = 102(0x66, float:1.43E-43)
            r10 = 95
            if (r0 == 0) goto L_0x039e
            int r0 = r5.length()
            r1 = 4
            if (r0 >= r1) goto L_0x0227
        L_0x0224:
            r0 = r15
            goto L_0x04d6
        L_0x0227:
            java.lang.String r0 = "getClass"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0230
            goto L_0x0224
        L_0x0230:
            java.lang.String r0 = "getDeclaringClass"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x023f
            boolean r0 = r39.isEnum()
            if (r0 == 0) goto L_0x023f
            goto L_0x0224
        L_0x023f:
            r0 = 3
            char r2 = r5.charAt(r0)
            boolean r3 = java.lang.Character.isUpperCase(r2)
            if (r3 != 0) goto L_0x0277
            r3 = 512(0x200, float:7.175E-43)
            if (r2 <= r3) goto L_0x024f
            goto L_0x0277
        L_0x024f:
            if (r2 != r10) goto L_0x0256
            java.lang.String r1 = r5.substring(r1)
            goto L_0x02a3
        L_0x0256:
            if (r2 != r13) goto L_0x025d
            java.lang.String r1 = r5.substring(r0)
            goto L_0x02a3
        L_0x025d:
            int r2 = r5.length()
            r3 = 5
            if (r2 < r3) goto L_0x0224
            char r1 = r5.charAt(r1)
            boolean r1 = java.lang.Character.isUpperCase(r1)
            if (r1 == 0) goto L_0x0224
            java.lang.String r1 = r5.substring(r0)
            java.lang.String r1 = decapitalize(r1)
            goto L_0x02a3
        L_0x0277:
            boolean r2 = compatibleWithJavaBean
            if (r2 == 0) goto L_0x0284
            java.lang.String r1 = r5.substring(r0)
            java.lang.String r1 = decapitalize(r1)
            goto L_0x029f
        L_0x0284:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            char r3 = r5.charAt(r0)
            char r3 = java.lang.Character.toLowerCase(r3)
            r2.append(r3)
            java.lang.String r1 = r5.substring(r1)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x029f:
            java.lang.String r1 = getPropertyNameByCompatibleFieldName(r14, r5, r1, r0)
        L_0x02a3:
            boolean r0 = isJSONTypeIgnore(r12, r1)
            if (r0 == 0) goto L_0x02ab
            goto L_0x0224
        L_0x02ab:
            java.lang.reflect.Field r0 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r1, r14)
            r2 = 1
            if (r0 != 0) goto L_0x02d2
            int r3 = r1.length()
            if (r3 <= r2) goto L_0x02d2
            char r3 = r1.charAt(r2)
            r4 = 65
            if (r3 < r4) goto L_0x02d2
            r4 = 90
            if (r3 > r4) goto L_0x02d2
            r9 = 3
            java.lang.String r0 = r5.substring(r9)
            java.lang.String r0 = decapitalize(r0)
            java.lang.reflect.Field r0 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r0, r14)
            goto L_0x02d3
        L_0x02d2:
            r9 = 3
        L_0x02d3:
            r3 = r0
            if (r3 == 0) goto L_0x0345
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = r3.getAnnotation(r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            if (r0 == 0) goto L_0x033d
            boolean r4 = r0.serialize()
            if (r4 != 0) goto L_0x02e8
            goto L_0x0224
        L_0x02e8:
            int r4 = r0.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r7 = r0.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r7)
            com.alibaba.fastjson.parser.Feature[] r11 = r0.parseFeatures()
            int r11 = com.alibaba.fastjson.parser.Feature.of(r11)
            java.lang.String r20 = r0.name()
            int r20 = r20.length()
            if (r20 == 0) goto L_0x0323
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            java.lang.String r2 = r0.name()
            r8 = r41
            if (r8 == 0) goto L_0x031c
            java.lang.Object r2 = r8.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L_0x031c
            r13 = r8
            goto L_0x0359
        L_0x031c:
            r38 = r8
            r8 = r1
            r1 = r2
            r2 = r38
            goto L_0x0325
        L_0x0323:
            r2 = r41
        L_0x0325:
            java.lang.String r20 = r0.label()
            int r20 = r20.length()
            if (r20 == 0) goto L_0x0333
            java.lang.String r18 = r0.label()
        L_0x0333:
            r21 = r7
            r23 = r11
            r20 = r18
            r11 = r0
            r18 = r4
            goto L_0x034d
        L_0x033d:
            r2 = r41
            r20 = r18
            r18 = r11
            r11 = r0
            goto L_0x034d
        L_0x0345:
            r2 = r41
            r20 = r18
            r18 = r11
            r11 = r17
        L_0x034d:
            if (r2 == 0) goto L_0x035c
            java.lang.Object r0 = r2.get(r1)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x035c
            r13 = r2
        L_0x0359:
            r0 = r15
            goto L_0x04d8
        L_0x035c:
            r7 = r15
            r15 = r44
            if (r15 == 0) goto L_0x036d
            boolean r0 = r8.booleanValue()
            if (r0 != 0) goto L_0x036d
            java.lang.String r0 = r15.translate(r1)
            r8 = r0
            goto L_0x036e
        L_0x036d:
            r8 = r1
        L_0x036e:
            com.alibaba.fastjson.util.FieldInfo r4 = new com.alibaba.fastjson.util.FieldInfo
            r22 = 0
            r0 = r4
            r1 = r8
            r2 = r6
            r13 = r4
            r4 = r39
            r15 = r5
            r5 = r22
            r22 = r6
            r6 = r18
            r34 = r7
            r7 = r21
            r35 = r8
            r8 = r23
            r25 = 3
            r9 = r27
            r10 = r11
            r12 = 3
            r11 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r10 = r34
            r0 = r35
            r10.put(r0, r13)
            r11 = r18
            r18 = r20
            goto L_0x03a3
        L_0x039e:
            r22 = r6
            r10 = r15
            r12 = 3
            r15 = r5
        L_0x03a3:
            java.lang.String r0 = "is"
            boolean r0 = r15.startsWith(r0)
            if (r0 == 0) goto L_0x04d3
            int r0 = r15.length()
            if (r0 >= r12) goto L_0x03b3
            goto L_0x04d3
        L_0x03b3:
            java.lang.Class r0 = r22.getReturnType()
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r0 == r1) goto L_0x03c5
            java.lang.Class r0 = r22.getReturnType()
            java.lang.Class<java.lang.Boolean> r1 = java.lang.Boolean.class
            if (r0 == r1) goto L_0x03c5
            goto L_0x04d3
        L_0x03c5:
            r0 = 2
            char r1 = r15.charAt(r0)
            boolean r2 = java.lang.Character.isUpperCase(r1)
            if (r2 == 0) goto L_0x03fd
            boolean r1 = compatibleWithJavaBean
            if (r1 == 0) goto L_0x03dd
            java.lang.String r1 = r15.substring(r0)
            java.lang.String r1 = decapitalize(r1)
            goto L_0x03f8
        L_0x03dd:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r2 = r15.charAt(r0)
            char r2 = java.lang.Character.toLowerCase(r2)
            r1.append(r2)
            java.lang.String r2 = r15.substring(r12)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x03f8:
            java.lang.String r0 = getPropertyNameByCompatibleFieldName(r14, r15, r1, r0)
            goto L_0x0405
        L_0x03fd:
            r2 = 95
            if (r1 != r2) goto L_0x0408
            java.lang.String r0 = r15.substring(r12)
        L_0x0405:
            r12 = r39
            goto L_0x0411
        L_0x0408:
            r2 = 102(0x66, float:1.43E-43)
            if (r1 != r2) goto L_0x04d3
            java.lang.String r0 = r15.substring(r0)
            goto L_0x0405
        L_0x0411:
            boolean r1 = isJSONTypeIgnore(r12, r0)
            if (r1 == 0) goto L_0x041a
        L_0x0417:
            r0 = r10
            goto L_0x04d6
        L_0x041a:
            java.lang.reflect.Field r1 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r0, r14)
            if (r1 != 0) goto L_0x0424
            java.lang.reflect.Field r1 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r15, r14)
        L_0x0424:
            r3 = r1
            if (r3 == 0) goto L_0x048c
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = r3.getAnnotation(r1)
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            if (r1 == 0) goto L_0x0483
            boolean r2 = r1.serialize()
            if (r2 != 0) goto L_0x0438
            goto L_0x0417
        L_0x0438:
            int r2 = r1.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r1.serialzeFeatures()
            int r4 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4)
            com.alibaba.fastjson.parser.Feature[] r5 = r1.parseFeatures()
            int r5 = com.alibaba.fastjson.parser.Feature.of(r5)
            java.lang.String r6 = r1.name()
            int r6 = r6.length()
            if (r6 == 0) goto L_0x0467
            java.lang.String r0 = r1.name()
            r13 = r41
            if (r13 == 0) goto L_0x0469
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0469
            goto L_0x049f
        L_0x0467:
            r13 = r41
        L_0x0469:
            java.lang.String r6 = r1.label()
            int r6 = r6.length()
            if (r6 == 0) goto L_0x047e
            java.lang.String r6 = r1.label()
            r11 = r1
            r7 = r4
            r8 = r5
            r18 = r6
            r6 = r2
            goto L_0x0495
        L_0x047e:
            r11 = r1
            r6 = r2
            r7 = r4
            r8 = r5
            goto L_0x0495
        L_0x0483:
            r13 = r41
            r6 = r11
            r7 = r21
            r8 = r23
            r11 = r1
            goto L_0x0495
        L_0x048c:
            r13 = r41
            r6 = r11
            r11 = r17
            r7 = r21
            r8 = r23
        L_0x0495:
            if (r13 == 0) goto L_0x04a1
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x04a1
        L_0x049f:
            r0 = r10
            goto L_0x04d8
        L_0x04a1:
            r15 = r44
            if (r15 == 0) goto L_0x04a9
            java.lang.String r0 = r15.translate(r0)
        L_0x04a9:
            r9 = r0
            boolean r0 = r10.containsKey(r9)
            if (r0 == 0) goto L_0x04b2
            r0 = r10
            goto L_0x04da
        L_0x04b2:
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r20 = 0
            r0 = r5
            r1 = r9
            r2 = r22
            r4 = r39
            r14 = r5
            r5 = r20
            r36 = r9
            r9 = r27
            r37 = r10
            r10 = r11
            r11 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r1 = r36
            r0 = r37
            r0.put(r1, r14)
            goto L_0x04da
        L_0x04d3:
            r0 = r10
            r12 = r39
        L_0x04d6:
            r13 = r41
        L_0x04d8:
            r15 = r44
        L_0x04da:
            r3 = r19
            r1 = r28
            r2 = r29
        L_0x04e0:
            int r7 = r30 + 1
            r11 = r0
            r0 = r26
            r9 = r32
            r10 = r33
            r14 = r42
            goto L_0x0022
        L_0x04ed:
            r0 = r11
            java.lang.reflect.Field[] r1 = r39.getFields()
            computeFields(r12, r13, r15, r0, r1)
            r1 = r43
            java.util.List r0 = getFieldInfos(r12, r1, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.computeGetters(java.lang.Class, com.alibaba.fastjson.annotation.JSONType, java.util.Map, java.util.Map, boolean, com.alibaba.fastjson.PropertyNamingStrategy):java.util.List");
    }

    private static List<FieldInfo> getFieldInfos(Class<?> cls, boolean z, Map<String, FieldInfo> map) {
        ArrayList arrayList = new ArrayList();
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        String[] orders = jSONType != null ? jSONType.orders() : null;
        if (orders == null || orders.length <= 0) {
            for (FieldInfo add : map.values()) {
                arrayList.add(add);
            }
            if (z) {
                Collections.sort(arrayList);
            }
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(arrayList.size());
            for (FieldInfo next : map.values()) {
                linkedHashMap.put(next.name, next);
            }
            for (String str : orders) {
                FieldInfo fieldInfo = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo != null) {
                    arrayList.add(fieldInfo);
                    linkedHashMap.remove(str);
                }
            }
            for (FieldInfo add2 : linkedHashMap.values()) {
                arrayList.add(add2);
            }
        }
        return arrayList;
    }

    private static void computeFields(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy, Map<String, FieldInfo> map2, Field[] fieldArr) {
        String str;
        int i;
        int i2;
        int i3;
        Map<String, String> map3 = map;
        PropertyNamingStrategy propertyNamingStrategy2 = propertyNamingStrategy;
        Map<String, FieldInfo> map4 = map2;
        for (Field field : fieldArr) {
            if (!Modifier.isStatic(field.getModifiers())) {
                JSONField jSONField = (JSONField) field.getAnnotation(JSONField.class);
                String name = field.getName();
                String str2 = null;
                if (jSONField == null) {
                    str = null;
                    i3 = 0;
                    i2 = 0;
                    i = 0;
                } else if (jSONField.serialize()) {
                    int ordinal = jSONField.ordinal();
                    int of = SerializerFeature.of(jSONField.serialzeFeatures());
                    int of2 = Feature.of(jSONField.parseFeatures());
                    if (jSONField.name().length() != 0) {
                        name = jSONField.name();
                    }
                    if (jSONField.label().length() != 0) {
                        str2 = jSONField.label();
                    }
                    str = str2;
                    i3 = ordinal;
                    i2 = of;
                    i = of2;
                }
                if (map3 == null || (name = map3.get(name)) != null) {
                    if (propertyNamingStrategy2 != null) {
                        name = propertyNamingStrategy2.translate(name);
                    }
                    String str3 = name;
                    if (!map4.containsKey(str3)) {
                        FieldInfo fieldInfo = r7;
                        FieldInfo fieldInfo2 = new FieldInfo(str3, (Method) null, field, cls, (Type) null, i3, i2, i, (JSONField) null, jSONField, str);
                        map4.put(str3, fieldInfo);
                    }
                }
            }
        }
    }

    private static String getPropertyNameByCompatibleFieldName(Map<String, Field> map, String str, String str2, int i) {
        if (!compatibleWithFieldName || map.containsKey(str2)) {
            return str2;
        }
        String substring = str.substring(i);
        return map.containsKey(substring) ? substring : str2;
    }

    public static JSONField getSuperMethodAnnotation(Class<?> cls, Method method) {
        boolean z;
        JSONField jSONField;
        boolean z2;
        JSONField jSONField2;
        Class[] interfaces = cls.getInterfaces();
        if (interfaces.length > 0) {
            Class[] parameterTypes = method.getParameterTypes();
            for (Class methods : interfaces) {
                for (Method method2 : methods.getMethods()) {
                    Class[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes2.length == parameterTypes.length && method2.getName().equals(method.getName())) {
                        int i = 0;
                        while (true) {
                            if (i >= parameterTypes.length) {
                                z2 = true;
                                break;
                            } else if (!parameterTypes2[i].equals(parameterTypes[i])) {
                                z2 = false;
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (z2 && (jSONField2 = (JSONField) method2.getAnnotation(JSONField.class)) != null) {
                            return jSONField2;
                        }
                    }
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && Modifier.isAbstract(superclass.getModifiers())) {
            Class[] parameterTypes3 = method.getParameterTypes();
            for (Method method3 : superclass.getMethods()) {
                Class[] parameterTypes4 = method3.getParameterTypes();
                if (parameterTypes4.length == parameterTypes3.length && method3.getName().equals(method.getName())) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= parameterTypes3.length) {
                            z = true;
                            break;
                        } else if (!parameterTypes4[i2].equals(parameterTypes3[i2])) {
                            z = false;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z && (jSONField = (JSONField) method3.getAnnotation(JSONField.class)) != null) {
                        return jSONField;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, String str) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType != null) {
            String[] includes = jSONType.includes();
            if (includes.length > 0) {
                for (String equals : includes) {
                    if (str.equals(equals)) {
                        return false;
                    }
                }
                return true;
            }
            String[] ignores = jSONType.ignores();
            for (String equals2 : ignores) {
                if (str.equals(equals2)) {
                    return true;
                }
            }
        }
        return (cls.getSuperclass() == Object.class || cls.getSuperclass() == null || !isJSONTypeIgnore(cls.getSuperclass(), str)) ? false : true;
    }

    public static boolean isGenericParamType(Type type) {
        Type genericSuperclass;
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class) || (genericSuperclass = ((Class) type).getGenericSuperclass()) == Object.class || !isGenericParamType(genericSuperclass)) {
            return false;
        }
        return true;
    }

    public static Type getGenericParamType(Type type) {
        return (!(type instanceof ParameterizedType) && (type instanceof Class)) ? getGenericParamType(((Class) type).getGenericSuperclass()) : type;
    }

    public static Type unwrapOptional(Type type) {
        if (!optionalClassInited) {
            try {
                optionalClass = Class.forName("java.util.Optional");
            } catch (Exception unused) {
            } catch (Throwable th) {
                optionalClassInited = true;
                throw th;
            }
            optionalClassInited = true;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (parameterizedType.getRawType() == optionalClass) {
                return parameterizedType.getActualTypeArguments()[0];
            }
        }
        return type;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            return (Class) ((TypeVariable) type).getBounds()[0];
        }
        if (!(type instanceof WildcardType)) {
            return Object.class;
        }
        Type[] upperBounds = ((WildcardType) type).getUpperBounds();
        if (upperBounds.length == 1) {
            return getClass(upperBounds[0]);
        }
        return Object.class;
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr) {
        char charAt;
        char charAt2;
        for (Field field : fieldArr) {
            String name = field.getName();
            if (str.equals(name)) {
                return field;
            }
            if (str.length() > 2 && (charAt = str.charAt(0)) >= 'a' && charAt <= 'z' && (charAt2 = str.charAt(1)) >= 'A' && charAt2 <= 'Z' && str.equalsIgnoreCase(name)) {
                return field;
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        return getField(superclass, str, superclass.getDeclaredFields());
    }

    public static int getSerializeFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return SerializerFeature.of(jSONType.serialzeFeatures());
    }

    public static int getParserFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return Feature.of(jSONType.parseFeatures());
    }

    public static String decapitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    static void setAccessible(AccessibleObject accessibleObject) {
        if (setAccessibleEnable && !accessibleObject.isAccessible()) {
            try {
                accessibleObject.setAccessible(true);
            } catch (AccessControlException unused) {
                setAccessibleEnable = false;
            }
        }
    }

    public static Type getCollectionItemType(Type type) {
        Type type2;
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
                if (upperBounds.length == 1) {
                    type2 = upperBounds[0];
                }
            }
        } else {
            if (type instanceof Class) {
                Class cls = (Class) type;
                if (!cls.getName().startsWith("java.")) {
                    type2 = getCollectionItemType(cls.getGenericSuperclass());
                }
            }
            type2 = null;
        }
        return type2 == null ? Object.class : type2;
    }

    public static Class<?> getCollectionItemClass(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
            if (upperBounds.length == 1) {
                type2 = upperBounds[0];
            }
        }
        if (type2 instanceof Class) {
            Class<?> cls = (Class) type2;
            if (Modifier.isPublic(cls.getModifiers())) {
                return cls;
            }
            throw new JSONException("can not create ASMParser");
        }
        throw new JSONException("can not create ASMParser");
    }

    public static Type checkPrimitiveArray(GenericArrayType genericArrayType) {
        Class<?> cls;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        String str = "[";
        while (genericComponentType instanceof GenericArrayType) {
            genericComponentType = ((GenericArrayType) genericComponentType).getGenericComponentType();
            str = str + str;
        }
        if (!(genericComponentType instanceof Class)) {
            return genericArrayType;
        }
        Class cls2 = (Class) genericComponentType;
        if (!cls2.isPrimitive()) {
            return genericArrayType;
        }
        try {
            if (cls2 == Boolean.TYPE) {
                cls = Class.forName(str + "Z");
            } else if (cls2 == Character.TYPE) {
                cls = Class.forName(str + "C");
            } else if (cls2 == Byte.TYPE) {
                cls = Class.forName(str + "B");
            } else if (cls2 == Short.TYPE) {
                cls = Class.forName(str + "S");
            } else if (cls2 == Integer.TYPE) {
                cls = Class.forName(str + "I");
            } else if (cls2 == Long.TYPE) {
                cls = Class.forName(str + "J");
            } else if (cls2 == Float.TYPE) {
                cls = Class.forName(str + "F");
            } else if (cls2 != Double.TYPE) {
                return genericArrayType;
            } else {
                cls = Class.forName(str + "D");
            }
            return cls;
        } catch (ClassNotFoundException unused) {
            return genericArrayType;
        }
    }

    public static Collection createCollection(Type type) {
        Type type2;
        Class<?> rawClass = getRawClass(type);
        if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (rawClass.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (rawClass.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(EnumSet.class)) {
            if (type instanceof ParameterizedType) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                type2 = Object.class;
            }
            return EnumSet.noneOf((Class) type2);
        }
        try {
            return (Collection) rawClass.newInstance();
        } catch (Exception unused) {
            throw new JSONException("create instance error, class " + rawClass.getName());
        }
    }

    public static Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass(((ParameterizedType) type).getRawType());
        }
        throw new JSONException("TODO");
    }

    public static boolean isProxy(Class<?> cls) {
        for (Class name : cls.getInterfaces()) {
            String name2 = name.getName();
            if (name2.equals("net.sf.cglib.proxy.Factory") || name2.equals("org.springframework.cglib.proxy.Factory") || name2.equals("javassist.util.proxy.ProxyObject") || name2.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTransient(Method method) {
        if (method == null) {
            return false;
        }
        if (!transientClassInited) {
            try {
                transientClass = Class.forName("java.beans.Transient");
            } catch (Exception unused) {
            } catch (Throwable th) {
                transientClassInited = true;
                throw th;
            }
            transientClassInited = true;
        }
        if (transientClass == null || method.getAnnotation(transientClass) == null) {
            return false;
        }
        return true;
    }

    public static boolean isAnnotationPresentOneToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_OneToMany == null && !class_OneToMany_error) {
            try {
                class_OneToMany = Class.forName("javax.persistence.OneToMany");
            } catch (Throwable unused) {
                class_OneToMany_error = true;
            }
        }
        if (class_OneToMany == null || !method.isAnnotationPresent(class_OneToMany)) {
            return false;
        }
        return true;
    }

    public static boolean isAnnotationPresentManyToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_ManyToMany == null && !class_ManyToMany_error) {
            try {
                class_ManyToMany = Class.forName("javax.persistence.ManyToMany");
            } catch (Throwable unused) {
                class_ManyToMany_error = true;
            }
        }
        if (class_ManyToMany == null) {
            return false;
        }
        if (method.isAnnotationPresent(class_OneToMany) || method.isAnnotationPresent(class_ManyToMany)) {
            return true;
        }
        return false;
    }

    public static boolean isHibernateInitialized(Object obj) {
        if (obj == null) {
            return false;
        }
        if (method_HibernateIsInitialized == null && !method_HibernateIsInitialized_error) {
            try {
                method_HibernateIsInitialized = Class.forName("org.hibernate.Hibernate").getMethod("isInitialized", new Class[]{Object.class});
            } catch (Throwable unused) {
                method_HibernateIsInitialized_error = true;
            }
        }
        if (method_HibernateIsInitialized != null) {
            try {
                return ((Boolean) method_HibernateIsInitialized.invoke((Object) null, new Object[]{obj})).booleanValue();
            } catch (Throwable unused2) {
            }
        }
        return true;
    }

    public static long fnv1a_64_lower(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!(charAt == '_' || charAt == '-')) {
                if (charAt >= 'A' && charAt <= 'Z') {
                    charAt = (char) (charAt + ' ');
                }
                j = (j ^ ((long) charAt)) * 1099511628211L;
            }
        }
        return j;
    }

    public static long fnv1a_64(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            j = (j ^ ((long) str.charAt(i))) * 1099511628211L;
        }
        return j;
    }

    public static boolean isKotlin(Class cls) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable unused) {
                kotlin_metadata_error = true;
            }
        }
        if (kotlin_metadata == null || !cls.isAnnotationPresent(kotlin_metadata)) {
            return false;
        }
        return true;
    }

    public static Constructor getKoltinConstructor(Constructor[] constructorArr) {
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            Class[] parameterTypes = constructor2.getParameterTypes();
            if ((parameterTypes.length <= 0 || !parameterTypes[parameterTypes.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (constructor == null || constructor.getParameterTypes().length < parameterTypes.length)) {
                constructor = constructor2;
            }
        }
        return constructor;
    }

    public static String[] getKoltinConstructorParameters(Class cls) {
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                kotlin_kclass_constructor = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getConstructor(new Class[]{Class.class});
            } catch (Throwable unused) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null) {
            return null;
        }
        if (kotlin_kclass_getConstructors == null && !kotlin_class_klass_error) {
            try {
                kotlin_kclass_getConstructors = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getMethod("getConstructors", new Class[0]);
            } catch (Throwable unused2) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kfunction_getParameters == null && !kotlin_class_klass_error) {
            try {
                kotlin_kfunction_getParameters = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", new Class[0]);
            } catch (Throwable unused3) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kparameter_getName == null && !kotlin_class_klass_error) {
            try {
                kotlin_kparameter_getName = Class.forName("kotlin.reflect.KParameter").getMethod("getName", new Class[0]);
            } catch (Throwable unused4) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_error) {
            return null;
        }
        try {
            Iterator it = ((Iterable) kotlin_kclass_getConstructors.invoke(kotlin_kclass_constructor.newInstance(new Object[]{cls}), new Object[0])).iterator();
            Object obj = null;
            while (it.hasNext()) {
                Object next = it.next();
                List list = (List) kotlin_kfunction_getParameters.invoke(next, new Object[0]);
                if (obj == null || list.size() != 0) {
                    obj = next;
                }
                it.hasNext();
            }
            List list2 = (List) kotlin_kfunction_getParameters.invoke(obj, new Object[0]);
            String[] strArr = new String[list2.size()];
            for (int i = 0; i < list2.size(); i++) {
                strArr[i] = (String) kotlin_kparameter_getName.invoke(list2.get(i), new Object[0]);
            }
            return strArr;
        } catch (Throwable th) {
            th.printStackTrace();
            kotlin_error = true;
            return null;
        }
    }

    private static boolean isKotlinIgnore(Class cls, String str) {
        String[] strArr;
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", "isEmpty"});
                kotlinIgnores = hashMap;
            } catch (Throwable unused) {
                kotlinIgnores_error = true;
            }
        }
        if (kotlinIgnores == null || (strArr = kotlinIgnores.get(cls)) == null || Arrays.binarySearch(strArr, str) < 0) {
            return false;
        }
        return true;
    }

    public static <A extends Annotation> A getAnnotation(Class<?> cls, Class<A> cls2) {
        A annotation = cls.getAnnotation(cls2);
        if (annotation != null) {
            return annotation;
        }
        if (cls.getAnnotations().length <= 0) {
            return null;
        }
        for (Annotation annotationType : cls.getAnnotations()) {
            A annotation2 = annotationType.annotationType().getAnnotation(cls2);
            if (annotation2 != null) {
                return annotation2;
            }
        }
        return null;
    }
}
