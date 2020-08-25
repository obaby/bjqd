package com.alibaba.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long serialVersionUID = 1;
    private final Map<String, Object> map;

    public JSONObject() {
        this(16, false);
    }

    public JSONObject(Map<String, Object> map2) {
        this.map = map2;
    }

    public JSONObject(boolean z) {
        this(16, z);
    }

    public JSONObject(int i) {
        this(i, false);
    }

    public JSONObject(int i, boolean z) {
        if (z) {
            this.map = new LinkedHashMap(i);
        } else {
            this.map = new HashMap(i);
        }
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public Object get(Object obj) {
        Object obj2 = this.map.get(obj);
        return (obj2 != null || !(obj instanceof Number)) ? obj2 : this.map.get(obj.toString());
    }

    public JSONObject getJSONObject(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof String) {
            return JSON.parseObject((String) obj);
        }
        return (JSONObject) toJSON(obj);
    }

    public JSONArray getJSONArray(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        if (obj instanceof String) {
            return (JSONArray) JSON.parse((String) obj);
        }
        return (JSONArray) toJSON(obj);
    }

    public <T> T getObject(String str, Class<T> cls) {
        return TypeUtils.castToJavaBean(this.map.get(str), cls);
    }

    public <T> T getObject(String str, Type type) {
        return TypeUtils.cast(this.map.get(str), type, ParserConfig.getGlobalInstance());
    }

    public <T> T getObject(String str, TypeReference typeReference) {
        T t = this.map.get(str);
        if (typeReference == null) {
            return t;
        }
        return TypeUtils.cast((Object) t, typeReference.getType(), ParserConfig.getGlobalInstance());
    }

    public Boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBoolean(obj);
    }

    public byte[] getBytes(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBytes(obj);
    }

    public boolean getBooleanValue(String str) {
        Boolean castToBoolean = TypeUtils.castToBoolean(get(str));
        if (castToBoolean == null) {
            return false;
        }
        return castToBoolean.booleanValue();
    }

    public Byte getByte(String str) {
        return TypeUtils.castToByte(get(str));
    }

    public byte getByteValue(String str) {
        Byte castToByte = TypeUtils.castToByte(get(str));
        if (castToByte == null) {
            return 0;
        }
        return castToByte.byteValue();
    }

    public Short getShort(String str) {
        return TypeUtils.castToShort(get(str));
    }

    public short getShortValue(String str) {
        Short castToShort = TypeUtils.castToShort(get(str));
        if (castToShort == null) {
            return 0;
        }
        return castToShort.shortValue();
    }

    public Integer getInteger(String str) {
        return TypeUtils.castToInt(get(str));
    }

    public int getIntValue(String str) {
        Integer castToInt = TypeUtils.castToInt(get(str));
        if (castToInt == null) {
            return 0;
        }
        return castToInt.intValue();
    }

    public Long getLong(String str) {
        return TypeUtils.castToLong(get(str));
    }

    public long getLongValue(String str) {
        Long castToLong = TypeUtils.castToLong(get(str));
        if (castToLong == null) {
            return 0;
        }
        return castToLong.longValue();
    }

    public Float getFloat(String str) {
        return TypeUtils.castToFloat(get(str));
    }

    public float getFloatValue(String str) {
        Float castToFloat = TypeUtils.castToFloat(get(str));
        if (castToFloat == null) {
            return 0.0f;
        }
        return castToFloat.floatValue();
    }

    public Double getDouble(String str) {
        return TypeUtils.castToDouble(get(str));
    }

    public double getDoubleValue(String str) {
        Double castToDouble = TypeUtils.castToDouble(get(str));
        if (castToDouble == null) {
            return 0.0d;
        }
        return castToDouble.doubleValue();
    }

    public BigDecimal getBigDecimal(String str) {
        return TypeUtils.castToBigDecimal(get(str));
    }

    public BigInteger getBigInteger(String str) {
        return TypeUtils.castToBigInteger(get(str));
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public Date getDate(String str) {
        return TypeUtils.castToDate(get(str));
    }

    public java.sql.Date getSqlDate(String str) {
        return TypeUtils.castToSqlDate(get(str));
    }

    public Timestamp getTimestamp(String str) {
        return TypeUtils.castToTimestamp(get(str));
    }

    public Object put(String str, Object obj) {
        return this.map.put(str, obj);
    }

    public JSONObject fluentPut(String str, Object obj) {
        this.map.put(str, obj);
        return this;
    }

    public void putAll(Map<? extends String, ? extends Object> map2) {
        this.map.putAll(map2);
    }

    public JSONObject fluentPutAll(Map<? extends String, ? extends Object> map2) {
        this.map.putAll(map2);
        return this;
    }

    public void clear() {
        this.map.clear();
    }

    public JSONObject fluentClear() {
        this.map.clear();
        return this;
    }

    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    public JSONObject fluentRemove(Object obj) {
        this.map.remove(obj);
        return this;
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<Object> values() {
        return this.map.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public Object clone() {
        return new JSONObject((Map<String, Object>) this.map instanceof LinkedHashMap ? new LinkedHashMap(this.map) : new HashMap(this.map));
    }

    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Class[] parameterTypes = method.getParameterTypes();
        String str = null;
        if (parameterTypes.length == 1) {
            if (method.getName().equals("equals")) {
                return Boolean.valueOf(equals(objArr[0]));
            }
            if (method.getReturnType() == Void.TYPE) {
                JSONField jSONField = (JSONField) method.getAnnotation(JSONField.class);
                String name = (jSONField == null || jSONField.name().length() == 0) ? null : jSONField.name();
                if (name == null) {
                    String name2 = method.getName();
                    if (name2.startsWith("set")) {
                        String substring = name2.substring(3);
                        if (substring.length() != 0) {
                            name = Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
                        } else {
                            throw new JSONException("illegal setter");
                        }
                    } else {
                        throw new JSONException("illegal setter");
                    }
                }
                this.map.put(name, objArr[0]);
                return null;
            }
            throw new JSONException("illegal setter");
        } else if (parameterTypes.length != 0) {
            throw new UnsupportedOperationException(method.toGenericString());
        } else if (method.getReturnType() != Void.TYPE) {
            JSONField jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
            if (!(jSONField2 == null || jSONField2.name().length() == 0)) {
                str = jSONField2.name();
            }
            if (str == null) {
                String name3 = method.getName();
                if (name3.startsWith("get")) {
                    String substring2 = name3.substring(3);
                    if (substring2.length() != 0) {
                        str = Character.toLowerCase(substring2.charAt(0)) + substring2.substring(1);
                    } else {
                        throw new JSONException("illegal getter");
                    }
                } else if (name3.startsWith("is")) {
                    String substring3 = name3.substring(2);
                    if (substring3.length() != 0) {
                        str = Character.toLowerCase(substring3.charAt(0)) + substring3.substring(1);
                    } else {
                        throw new JSONException("illegal getter");
                    }
                } else if (name3.startsWith("hashCode")) {
                    return Integer.valueOf(hashCode());
                } else {
                    if (name3.startsWith("toString")) {
                        return toString();
                    }
                    throw new JSONException("illegal getter");
                }
            }
            return TypeUtils.cast(this.map.get(str), method.getGenericReturnType(), ParserConfig.getGlobalInstance());
        } else {
            throw new JSONException("illegal getter");
        }
    }

    public Map<String, Object> getInnerMap() {
        return this.map;
    }
}
