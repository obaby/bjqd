package cn.xports.baselib.util;

import cn.xports.baselib.bean.DataMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataMapUtils {
    private DataMapUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Object getObject(Map map, Object obj) {
        if (map != null) {
            return map.get(obj);
        }
        return null;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static String getString(Map map, Object obj) {
        Object obj2;
        if (map == null || (obj2 = map.get(obj)) == null) {
            return null;
        }
        return obj2.toString();
    }

    public static Boolean getBoolean(Map map, Object obj) {
        Object obj2;
        if (map == null || (obj2 = map.get(obj)) == null) {
            return null;
        }
        if (obj2 instanceof Boolean) {
            return (Boolean) obj2;
        }
        if (obj2 instanceof String) {
            return new Boolean((String) obj2);
        }
        if (obj2 instanceof Number) {
            return ((Number) obj2).intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

    public static Number getNumber(Map map, Object obj) {
        Object obj2;
        if (map == null || (obj2 = map.get(obj)) == null) {
            return null;
        }
        if (obj2 instanceof Number) {
            return (Number) obj2;
        }
        if (!(obj2 instanceof String)) {
            return null;
        }
        try {
            return NumberFormat.getInstance().parse((String) obj2);
        } catch (ParseException unused) {
            return null;
        }
    }

    public static Byte getByte(Map map, Object obj) {
        Number number = getNumber(map, obj);
        if (number == null) {
            return null;
        }
        return number instanceof Byte ? (Byte) number : Byte.valueOf(number.byteValue());
    }

    public static Short getShort(Map map, Object obj) {
        Number number = getNumber(map, obj);
        if (number == null) {
            return null;
        }
        return number instanceof Short ? (Short) number : new Short(number.shortValue());
    }

    public static Integer getInteger(Map map, Object obj) {
        Number number = getNumber(map, obj);
        if (number == null) {
            return null;
        }
        return number instanceof Integer ? (Integer) number : Integer.valueOf(number.intValue());
    }

    public static Long getLong(Map map, Object obj) {
        Number number = getNumber(map, obj);
        if (number == null) {
            return null;
        }
        return number instanceof Long ? (Long) number : Long.valueOf(number.longValue());
    }

    public static Float getFloat(Map map, Object obj) {
        Number number = getNumber(map, obj);
        if (number == null) {
            return null;
        }
        return number instanceof Float ? (Float) number : Float.valueOf(number.floatValue());
    }

    public static Double getDouble(Map map, Object obj) {
        Number number = getNumber(map, obj);
        if (number == null) {
            return null;
        }
        return number instanceof Double ? (Double) number : Double.valueOf(number.doubleValue());
    }

    public static Map getMap(Map map, Object obj) {
        if (map == null) {
            return null;
        }
        Object obj2 = map.get(obj);
        if (obj2 instanceof Map) {
            return (Map) obj2;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r0.get(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object getObject(java.util.Map r0, java.lang.Object r1, java.lang.Object r2) {
        /*
            if (r0 == 0) goto L_0x0009
            java.lang.Object r0 = r0.get(r1)
            if (r0 == 0) goto L_0x0009
            return r0
        L_0x0009:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.baselib.util.DataMapUtils.getObject(java.util.Map, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static String getString(Map map, Object obj, String str) {
        String string = getString(map, obj);
        return string == null ? str : string;
    }

    public static Boolean getBoolean(Map map, Object obj, Boolean bool) {
        Boolean bool2 = getBoolean(map, obj);
        return bool2 == null ? bool : bool2;
    }

    public static Number getNumber(Map map, Object obj, Number number) {
        Number number2 = getNumber(map, obj);
        return number2 == null ? number : number2;
    }

    public static Byte getByte(Map map, Object obj, Byte b2) {
        Byte b3 = getByte(map, obj);
        return b3 == null ? b2 : b3;
    }

    public static Short getShort(Map map, Object obj, Short sh) {
        Short sh2 = getShort(map, obj);
        return sh2 == null ? sh : sh2;
    }

    public static Integer getInteger(Map map, Object obj, Integer num) {
        Integer integer = getInteger(map, obj);
        return integer == null ? num : integer;
    }

    public static Long getLong(Map map, Object obj, Long l) {
        Long l2 = getLong(map, obj);
        return l2 == null ? l : l2;
    }

    public static Float getFloat(Map map, Object obj, Float f) {
        Float f2 = getFloat(map, obj);
        return f2 == null ? f : f2;
    }

    public static Double getDouble(Map map, Object obj, Double d) {
        Double d2 = getDouble(map, obj);
        return d2 == null ? d : d2;
    }

    public static Map getMap(Map map, Object obj, Map map2) {
        Map map3 = getMap(map, obj);
        return map3 == null ? map2 : map3;
    }

    public static boolean getBooleanValue(Map map, Object obj) {
        Boolean bool = getBoolean(map, obj);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static byte getByteValue(Map map, Object obj) {
        Byte b2 = getByte(map, obj);
        if (b2 == null) {
            return 0;
        }
        return b2.byteValue();
    }

    public static short getShortValue(Map map, Object obj) {
        Short sh = getShort(map, obj);
        if (sh == null) {
            return 0;
        }
        return sh.shortValue();
    }

    public static int getIntValue(Map map, Object obj) {
        Integer integer = getInteger(map, obj);
        if (integer == null) {
            return 0;
        }
        return integer.intValue();
    }

    public static long getLongValue(Map map, Object obj) {
        Long l = getLong(map, obj);
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    public static float getFloatValue(Map map, Object obj) {
        Float f = getFloat(map, obj);
        if (f == null) {
            return 0.0f;
        }
        return f.floatValue();
    }

    public static double getDoubleValue(Map map, Object obj) {
        Double d = getDouble(map, obj);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    public static boolean getBooleanValue(Map map, Object obj, boolean z) {
        Boolean bool = getBoolean(map, obj);
        return bool == null ? z : bool.booleanValue();
    }

    public static byte getByteValue(Map map, Object obj, byte b2) {
        Byte b3 = getByte(map, obj);
        return b3 == null ? b2 : b3.byteValue();
    }

    public static short getShortValue(Map map, Object obj, short s) {
        Short sh = getShort(map, obj);
        return sh == null ? s : sh.shortValue();
    }

    public static int getIntValue(Map map, Object obj, int i) {
        Integer integer = getInteger(map, obj);
        return integer == null ? i : integer.intValue();
    }

    public static long getLongValue(Map map, Object obj, long j) {
        Long l = getLong(map, obj);
        return l == null ? j : l.longValue();
    }

    public static float getFloatValue(Map map, Object obj, float f) {
        Float f2 = getFloat(map, obj);
        return f2 == null ? f : f2.floatValue();
    }

    public static double getDoubleValue(Map map, Object obj, double d) {
        Double d2 = getDouble(map, obj);
        return d2 == null ? d : d2.doubleValue();
    }

    public static Gson getGson() {
        return new GsonBuilder().registerTypeAdapterFactory(DataMapAdapter.FACTORY).create();
    }

    public static DataMap fromJson(String str) {
        try {
            return (DataMap) getGson().fromJson(str, DataMap.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<DataMap> fromJsonArray(String str) {
        try {
            return (List) getGson().fromJson(str, new TypeToken<ArrayList<DataMap>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
