package com.alibaba.fastjson;

import anetwork.channel.util.RequestConstant;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class JSONPath implements JSONAware {
    static final long LENGTH = -1580386065683472715L;
    static final long SIZE = 5614464919154503228L;
    private static ConcurrentMap<String, JSONPath> pathCache = new ConcurrentHashMap(128, 0.75f, 1);
    private ParserConfig parserConfig;
    private final String path;
    private Segement[] segments;
    private SerializeConfig serializeConfig;

    interface Filter {
        boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN
    }

    interface Segement {
        Object eval(JSONPath jSONPath, Object obj, Object obj2);
    }

    public JSONPath(String str) {
        this(str, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance());
    }

    public JSONPath(String str, SerializeConfig serializeConfig2, ParserConfig parserConfig2) {
        if (str == null || str.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.path = str;
        this.serializeConfig = serializeConfig2;
        this.parserConfig = parserConfig2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (this.segments == null) {
            if ("*".equals(this.path)) {
                this.segments = new Segement[]{WildCardSegement.instance};
                return;
            }
            this.segments = new JSONPathParser(this.path).explain();
        }
    }

    public Object eval(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        Object obj2 = obj;
        for (Segement eval : this.segments) {
            obj2 = eval.eval(this, obj, obj2);
        }
        return obj2;
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = obj;
        for (Segement eval : this.segments) {
            obj2 = eval.eval(this, obj, obj2);
            if (obj2 == null) {
                return false;
            }
        }
        return true;
    }

    public boolean containsValue(Object obj, Object obj2) {
        Object eval = eval(obj);
        if (eval == obj2) {
            return true;
        }
        if (eval == null) {
            return false;
        }
        if (!(eval instanceof Iterable)) {
            return eq(eval, obj2);
        }
        for (Object eq : (Iterable) eval) {
            if (eq(eq, obj2)) {
                return true;
            }
        }
        return false;
    }

    public int size(Object obj) {
        if (obj == null) {
            return -1;
        }
        init();
        Object obj2 = obj;
        for (Segement eval : this.segments) {
            obj2 = eval.eval(this, obj, obj2);
        }
        return evalSize(obj2);
    }

    public void arrayAdd(Object obj, Object... objArr) {
        if (objArr != null && objArr.length != 0 && obj != null) {
            init();
            int i = 0;
            Object obj2 = obj;
            Object obj3 = null;
            for (int i2 = 0; i2 < this.segments.length; i2++) {
                if (i2 == this.segments.length - 1) {
                    obj3 = obj2;
                }
                obj2 = this.segments[i2].eval(this, obj, obj2);
            }
            if (obj2 == null) {
                throw new JSONPathException("value not found in path " + this.path);
            } else if (obj2 instanceof Collection) {
                Collection collection = (Collection) obj2;
                int length = objArr.length;
                while (i < length) {
                    collection.add(objArr[i]);
                    i++;
                }
            } else {
                Class<?> cls = obj2.getClass();
                if (cls.isArray()) {
                    int length2 = Array.getLength(obj2);
                    Object newInstance = Array.newInstance(cls.getComponentType(), objArr.length + length2);
                    System.arraycopy(obj2, 0, newInstance, 0, length2);
                    while (i < objArr.length) {
                        Array.set(newInstance, length2 + i, objArr[i]);
                        i++;
                    }
                    Segement segement = this.segments[this.segments.length - 1];
                    if (segement instanceof PropertySegement) {
                        ((PropertySegement) segement).setValue(this, obj3, newInstance);
                    } else if (segement instanceof ArrayAccessSegement) {
                        ((ArrayAccessSegement) segement).setValue(this, obj3, newInstance);
                    } else {
                        throw new UnsupportedOperationException();
                    }
                } else {
                    throw new JSONException("unsupported array put operation. " + cls);
                }
            }
        }
    }

    public boolean remove(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        init();
        Collection<Object> collection = null;
        Object obj2 = obj;
        int i = 0;
        while (true) {
            if (i >= this.segments.length) {
                break;
            } else if (i == this.segments.length - 1) {
                collection = obj2;
                break;
            } else {
                obj2 = this.segments[i].eval(this, obj, obj2);
                if (obj2 == null) {
                    break;
                }
                i++;
            }
        }
        if (collection == null) {
            return false;
        }
        Segement segement = this.segments[this.segments.length - 1];
        if (segement instanceof PropertySegement) {
            PropertySegement propertySegement = (PropertySegement) segement;
            if ((collection instanceof Collection) && this.segments.length > 1) {
                Segement segement2 = this.segments[this.segments.length - 2];
                if ((segement2 instanceof RangeSegement) || (segement2 instanceof MultiIndexSegement)) {
                    for (Object remove : collection) {
                        if (propertySegement.remove(this, remove)) {
                            z = true;
                        }
                    }
                    return z;
                }
            }
            return propertySegement.remove(this, collection);
        } else if (segement instanceof ArrayAccessSegement) {
            return ((ArrayAccessSegement) segement).remove(this, collection);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean set(Object obj, Object obj2) {
        return set(obj, obj2, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean set(java.lang.Object r9, java.lang.Object r10, boolean r11) {
        /*
            r8 = this;
            r11 = 0
            if (r9 != 0) goto L_0x0004
            return r11
        L_0x0004:
            r8.init()
            r0 = 0
            r2 = r9
            r3 = r0
            r1 = 0
        L_0x000b:
            com.alibaba.fastjson.JSONPath$Segement[] r4 = r8.segments
            int r4 = r4.length
            r5 = 1
            if (r1 >= r4) goto L_0x008a
            com.alibaba.fastjson.JSONPath$Segement[] r3 = r8.segments
            r3 = r3[r1]
            java.lang.Object r4 = r3.eval(r8, r9, r2)
            if (r4 != 0) goto L_0x0085
            com.alibaba.fastjson.JSONPath$Segement[] r4 = r8.segments
            int r4 = r4.length
            int r4 = r4 - r5
            if (r1 >= r4) goto L_0x0028
            com.alibaba.fastjson.JSONPath$Segement[] r4 = r8.segments
            int r6 = r1 + 1
            r4 = r4[r6]
            goto L_0x0029
        L_0x0028:
            r4 = r0
        L_0x0029:
            boolean r6 = r4 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r6 == 0) goto L_0x0065
            boolean r4 = r3 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r4 == 0) goto L_0x004f
            r4 = r3
            com.alibaba.fastjson.JSONPath$PropertySegement r4 = (com.alibaba.fastjson.JSONPath.PropertySegement) r4
            java.lang.String r4 = r4.propertyName
            java.lang.Class r6 = r2.getClass()
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r6 = r8.getJavaBeanDeserializer(r6)
            if (r6 == 0) goto L_0x004f
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r4 = r6.getFieldDeserializer((java.lang.String) r4)
            com.alibaba.fastjson.util.FieldInfo r4 = r4.fieldInfo
            java.lang.Class<?> r4 = r4.fieldClass
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r6 = r8.getJavaBeanDeserializer(r4)
            goto L_0x0051
        L_0x004f:
            r4 = r0
            r6 = r4
        L_0x0051:
            if (r6 == 0) goto L_0x005f
            com.alibaba.fastjson.util.JavaBeanInfo r7 = r6.beanInfo
            java.lang.reflect.Constructor<?> r7 = r7.defaultConstructor
            if (r7 == 0) goto L_0x005e
            java.lang.Object r4 = r6.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r0, (java.lang.reflect.Type) r4)
            goto L_0x0070
        L_0x005e:
            return r11
        L_0x005f:
            com.alibaba.fastjson.JSONObject r4 = new com.alibaba.fastjson.JSONObject
            r4.<init>()
            goto L_0x0070
        L_0x0065:
            boolean r4 = r4 instanceof com.alibaba.fastjson.JSONPath.ArrayAccessSegement
            if (r4 == 0) goto L_0x006f
            com.alibaba.fastjson.JSONArray r4 = new com.alibaba.fastjson.JSONArray
            r4.<init>()
            goto L_0x0070
        L_0x006f:
            r4 = r0
        L_0x0070:
            if (r4 == 0) goto L_0x008b
            boolean r6 = r3 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r6 == 0) goto L_0x007c
            com.alibaba.fastjson.JSONPath$PropertySegement r3 = (com.alibaba.fastjson.JSONPath.PropertySegement) r3
            r3.setValue(r8, r2, r4)
            goto L_0x0085
        L_0x007c:
            boolean r6 = r3 instanceof com.alibaba.fastjson.JSONPath.ArrayAccessSegement
            if (r6 == 0) goto L_0x008b
            com.alibaba.fastjson.JSONPath$ArrayAccessSegement r3 = (com.alibaba.fastjson.JSONPath.ArrayAccessSegement) r3
            r3.setValue(r8, r2, r4)
        L_0x0085:
            int r1 = r1 + 1
            r3 = r2
            r2 = r4
            goto L_0x000b
        L_0x008a:
            r2 = r3
        L_0x008b:
            if (r2 != 0) goto L_0x008e
            return r11
        L_0x008e:
            com.alibaba.fastjson.JSONPath$Segement[] r9 = r8.segments
            com.alibaba.fastjson.JSONPath$Segement[] r11 = r8.segments
            int r11 = r11.length
            int r11 = r11 - r5
            r9 = r9[r11]
            boolean r11 = r9 instanceof com.alibaba.fastjson.JSONPath.PropertySegement
            if (r11 == 0) goto L_0x00a0
            com.alibaba.fastjson.JSONPath$PropertySegement r9 = (com.alibaba.fastjson.JSONPath.PropertySegement) r9
            r9.setValue(r8, r2, r10)
            return r5
        L_0x00a0:
            boolean r11 = r9 instanceof com.alibaba.fastjson.JSONPath.ArrayAccessSegement
            if (r11 == 0) goto L_0x00ab
            com.alibaba.fastjson.JSONPath$ArrayAccessSegement r9 = (com.alibaba.fastjson.JSONPath.ArrayAccessSegement) r9
            boolean r9 = r9.setValue(r8, r2, r10)
            return r9
        L_0x00ab:
            java.lang.UnsupportedOperationException r9 = new java.lang.UnsupportedOperationException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.JSONPath.set(java.lang.Object, java.lang.Object, boolean):boolean");
    }

    public static Object eval(Object obj, String str) {
        return compile(str).eval(obj);
    }

    public static int size(Object obj, String str) {
        JSONPath compile = compile(str);
        return compile.evalSize(compile.eval(obj));
    }

    public static boolean contains(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        return compile(str).contains(obj);
    }

    public static boolean containsValue(Object obj, String str, Object obj2) {
        return compile(str).containsValue(obj, obj2);
    }

    public static void arrayAdd(Object obj, String str, Object... objArr) {
        compile(str).arrayAdd(obj, objArr);
    }

    public static boolean set(Object obj, String str, Object obj2) {
        return compile(str).set(obj, obj2);
    }

    public static boolean remove(Object obj, String str) {
        return compile(str).remove(obj);
    }

    public static JSONPath compile(String str) {
        if (str != null) {
            JSONPath jSONPath = (JSONPath) pathCache.get(str);
            if (jSONPath != null) {
                return jSONPath;
            }
            JSONPath jSONPath2 = new JSONPath(str);
            if (pathCache.size() >= 1024) {
                return jSONPath2;
            }
            pathCache.putIfAbsent(str, jSONPath2);
            return (JSONPath) pathCache.get(str);
        }
        throw new JSONPathException("jsonpath can not be null");
    }

    public static Object read(String str, String str2) {
        return compile(str2).eval(JSON.parse(str));
    }

    public static Map<String, Object> paths(Object obj) {
        return paths(obj, SerializeConfig.globalInstance);
    }

    public static Map<String, Object> paths(Object obj, SerializeConfig serializeConfig2) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        HashMap hashMap = new HashMap();
        paths(identityHashMap, hashMap, "/", obj, serializeConfig2);
        return hashMap;
    }

    private static void paths(Map<Object, String> map, Map<String, Object> map2, String str, Object obj, SerializeConfig serializeConfig2) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        if (obj != null) {
            int i = 0;
            if (map.put(obj, str) != null) {
                if (!((obj instanceof String) || (obj instanceof Number) || (obj instanceof Date) || (obj instanceof UUID))) {
                    return;
                }
            }
            map2.put(str, obj);
            if (obj instanceof Map) {
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    if (key instanceof String) {
                        if (str.equals("/")) {
                            sb4 = new StringBuilder();
                        } else {
                            sb4 = new StringBuilder();
                            sb4.append(str);
                        }
                        sb4.append("/");
                        sb4.append(key);
                        paths(map, map2, sb4.toString(), entry.getValue(), serializeConfig2);
                    }
                }
            } else if (obj instanceof Collection) {
                for (Object next : (Collection) obj) {
                    if (str.equals("/")) {
                        sb3 = new StringBuilder();
                    } else {
                        sb3 = new StringBuilder();
                        sb3.append(str);
                    }
                    sb3.append("/");
                    sb3.append(i);
                    paths(map, map2, sb3.toString(), next, serializeConfig2);
                    i++;
                }
            } else {
                Class<?> cls = obj.getClass();
                if (cls.isArray()) {
                    int length = Array.getLength(obj);
                    while (i < length) {
                        Object obj2 = Array.get(obj, i);
                        if (str.equals("/")) {
                            sb2 = new StringBuilder();
                        } else {
                            sb2 = new StringBuilder();
                            sb2.append(str);
                        }
                        sb2.append("/");
                        sb2.append(i);
                        paths(map, map2, sb2.toString(), obj2, serializeConfig2);
                        i++;
                    }
                } else if (!ParserConfig.isPrimitive2(cls) && !cls.isEnum()) {
                    ObjectSerializer objectWriter = serializeConfig2.getObjectWriter(cls);
                    if (objectWriter instanceof JavaBeanSerializer) {
                        try {
                            for (Map.Entry next2 : ((JavaBeanSerializer) objectWriter).getFieldValuesMap(obj).entrySet()) {
                                String str2 = (String) next2.getKey();
                                if (str2 instanceof String) {
                                    if (str.equals("/")) {
                                        sb = new StringBuilder();
                                        sb.append("/");
                                        sb.append(str2);
                                    } else {
                                        sb = new StringBuilder();
                                        sb.append(str);
                                        sb.append("/");
                                        sb.append(str2);
                                    }
                                    paths(map, map2, sb.toString(), next2.getValue(), serializeConfig2);
                                }
                            }
                        } catch (Exception e) {
                            throw new JSONException("toJSON error", e);
                        }
                    }
                }
            }
        }
    }

    private static void paths(Map<Object, String> map, String str, Object obj, SerializeConfig serializeConfig2) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        if (obj != null && !map.containsKey(obj)) {
            map.put(obj, str);
            if (obj instanceof Map) {
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    if (key instanceof String) {
                        if (str.equals("/")) {
                            sb4 = new StringBuilder();
                        } else {
                            sb4 = new StringBuilder();
                            sb4.append(str);
                        }
                        sb4.append("/");
                        sb4.append(key);
                        paths(map, sb4.toString(), entry.getValue(), serializeConfig2);
                    }
                }
                return;
            }
            int i = 0;
            if (obj instanceof Collection) {
                for (Object next : (Collection) obj) {
                    if (str.equals("/")) {
                        sb3 = new StringBuilder();
                    } else {
                        sb3 = new StringBuilder();
                        sb3.append(str);
                    }
                    sb3.append("/");
                    sb3.append(i);
                    paths(map, sb3.toString(), next, serializeConfig2);
                    i++;
                }
                return;
            }
            Class<?> cls = obj.getClass();
            if (cls.isArray()) {
                int length = Array.getLength(obj);
                while (i < length) {
                    Object obj2 = Array.get(obj, i);
                    if (str.equals("/")) {
                        sb2 = new StringBuilder();
                    } else {
                        sb2 = new StringBuilder();
                        sb2.append(str);
                    }
                    sb2.append("/");
                    sb2.append(i);
                    paths(map, sb2.toString(), obj2, serializeConfig2);
                    i++;
                }
            } else if (!ParserConfig.isPrimitive2(cls) && !cls.isEnum()) {
                ObjectSerializer objectWriter = serializeConfig2.getObjectWriter(cls);
                if (objectWriter instanceof JavaBeanSerializer) {
                    try {
                        for (Map.Entry next2 : ((JavaBeanSerializer) objectWriter).getFieldValuesMap(obj).entrySet()) {
                            String str2 = (String) next2.getKey();
                            if (str2 instanceof String) {
                                if (str.equals("/")) {
                                    sb = new StringBuilder();
                                    sb.append("/");
                                    sb.append(str2);
                                } else {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("/");
                                    sb.append(str2);
                                }
                                paths(map, sb.toString(), next2.getValue(), serializeConfig2);
                            }
                        }
                    } catch (Exception e) {
                        throw new JSONException("toJSON error", e);
                    }
                }
            }
        }
    }

    public String getPath() {
        return this.path;
    }

    static class JSONPathParser {
        private char ch;
        private int level;
        private final String path;
        private int pos;

        static boolean isDigitFirst(char c2) {
            return c2 == '-' || c2 == '+' || (c2 >= '0' && c2 <= '9');
        }

        public JSONPathParser(String str) {
            this.path = str;
            next();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            String str = this.path;
            int i = this.pos;
            this.pos = i + 1;
            this.ch = str.charAt(i);
        }

        /* access modifiers changed from: package-private */
        public boolean isEOF() {
            return this.pos >= this.path.length();
        }

        /* access modifiers changed from: package-private */
        public Segement readSegement() {
            boolean z = true;
            if (this.level == 0 && this.path.length() == 1) {
                if (isDigitFirst(this.ch)) {
                    return new ArrayAccessSegement(this.ch - '0');
                }
                if ((this.ch >= 'a' && this.ch <= 'z') || (this.ch >= 'A' && this.ch <= 'Z')) {
                    return new PropertySegement(Character.toString(this.ch), false);
                }
            }
            while (!isEOF()) {
                skipWhitespace();
                if (this.ch == '$') {
                    next();
                } else if (this.ch == '.' || this.ch == '/') {
                    char c2 = this.ch;
                    next();
                    if (c2 == '.' && this.ch == '.') {
                        next();
                        if (this.path.length() > this.pos + 3 && this.ch == '[' && this.path.charAt(this.pos) == '*' && this.path.charAt(this.pos + 1) == ']' && this.path.charAt(this.pos + 2) == '.') {
                            next();
                            next();
                            next();
                            next();
                        }
                    } else {
                        z = false;
                    }
                    if (this.ch == '*') {
                        if (!isEOF()) {
                            next();
                        }
                        return WildCardSegement.instance;
                    } else if (isDigitFirst(this.ch)) {
                        return parseArrayAccess(false);
                    } else {
                        String readName = readName();
                        if (this.ch != '(') {
                            return new PropertySegement(readName, z);
                        }
                        next();
                        if (this.ch == ')') {
                            if (!isEOF()) {
                                next();
                            }
                            if ("size".equals(readName) || "length".equals(readName)) {
                                return SizeSegement.instance;
                            }
                            throw new JSONPathException("not support jsonpath : " + this.path);
                        }
                        throw new JSONPathException("not support jsonpath : " + this.path);
                    }
                } else if (this.ch == '[') {
                    return parseArrayAccess(true);
                } else {
                    if (this.level == 0) {
                        return new PropertySegement(readName(), false);
                    }
                    throw new JSONPathException("not support jsonpath : " + this.path);
                }
            }
            return null;
        }

        public final void skipWhitespace() {
            while (this.ch <= ' ') {
                if (this.ch == ' ' || this.ch == 13 || this.ch == 10 || this.ch == 9 || this.ch == 12 || this.ch == 8) {
                    next();
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Segement parseArrayAccess(boolean z) {
            boolean z2;
            String[] strArr;
            String str;
            String str2;
            int i;
            if (z) {
                accept('[');
            }
            int i2 = 0;
            if (this.ch == '?') {
                next();
                accept('(');
                if (this.ch == '@') {
                    next();
                    accept('.');
                }
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 || IOUtils.firstIdentifier(this.ch)) {
                String readName = readName();
                skipWhitespace();
                if (z2 && this.ch == ')') {
                    next();
                    if (z) {
                        accept(']');
                    }
                    return new FilterSegement(new NotNullSegement(readName));
                } else if (!z || this.ch != ']') {
                    Operator readOp = readOp();
                    skipWhitespace();
                    if (readOp == Operator.BETWEEN || readOp == Operator.NOT_BETWEEN) {
                        boolean z3 = readOp == Operator.NOT_BETWEEN;
                        Object readValue = readValue();
                        if ("and".equalsIgnoreCase(readName())) {
                            Object readValue2 = readValue();
                            if (readValue == null || readValue2 == null) {
                                throw new JSONPathException(this.path);
                            } else if (JSONPath.isInt(readValue.getClass()) && JSONPath.isInt(readValue2.getClass())) {
                                return new FilterSegement(new IntBetweenSegement(readName, ((Number) readValue).longValue(), ((Number) readValue2).longValue(), z3));
                            } else {
                                throw new JSONPathException(this.path);
                            }
                        } else {
                            throw new JSONPathException(this.path);
                        }
                    } else if (readOp == Operator.IN || readOp == Operator.NOT_IN) {
                        boolean z4 = readOp == Operator.NOT_IN;
                        accept('(');
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.add(readValue());
                        while (true) {
                            skipWhitespace();
                            if (this.ch != ',') {
                                break;
                            }
                            next();
                            jSONArray.add(readValue());
                        }
                        accept(')');
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        boolean z5 = true;
                        boolean z6 = true;
                        boolean z7 = true;
                        for (Object next : jSONArray) {
                            if (next != null) {
                                Class<?> cls = next.getClass();
                                if (!(!z5 || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class)) {
                                    z5 = false;
                                    z7 = false;
                                }
                                if (z6 && cls != String.class) {
                                    z6 = false;
                                }
                            } else if (z5) {
                                z5 = false;
                            }
                        }
                        if (jSONArray.size() == 1 && jSONArray.get(0) == null) {
                            if (z4) {
                                return new FilterSegement(new NotNullSegement(readName));
                            }
                            return new FilterSegement(new NullSegement(readName));
                        } else if (z5) {
                            if (jSONArray.size() == 1) {
                                return new FilterSegement(new IntOpSegement(readName, ((Number) jSONArray.get(0)).longValue(), z4 ? Operator.NE : Operator.EQ));
                            }
                            long[] jArr = new long[jSONArray.size()];
                            while (i2 < jArr.length) {
                                jArr[i2] = ((Number) jSONArray.get(i2)).longValue();
                                i2++;
                            }
                            return new FilterSegement(new IntInSegement(readName, jArr, z4));
                        } else if (z6) {
                            if (jSONArray.size() == 1) {
                                return new FilterSegement(new StringOpSegement(readName, (String) jSONArray.get(0), z4 ? Operator.NE : Operator.EQ));
                            }
                            String[] strArr2 = new String[jSONArray.size()];
                            jSONArray.toArray(strArr2);
                            return new FilterSegement(new StringInSegement(readName, strArr2, z4));
                        } else if (z7) {
                            Long[] lArr = new Long[jSONArray.size()];
                            while (i2 < lArr.length) {
                                Number number = (Number) jSONArray.get(i2);
                                if (number != null) {
                                    lArr[i2] = Long.valueOf(number.longValue());
                                }
                                i2++;
                            }
                            return new FilterSegement(new IntObjInSegement(readName, lArr, z4));
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    } else if (this.ch == '\'' || this.ch == '\"') {
                        String readString = readString();
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        if (readOp == Operator.RLIKE) {
                            return new FilterSegement(new RlikeSegement(readName, readString, false));
                        }
                        if (readOp == Operator.NOT_RLIKE) {
                            return new FilterSegement(new RlikeSegement(readName, readString, true));
                        }
                        if (readOp == Operator.LIKE || readOp == Operator.NOT_LIKE) {
                            while (readString.indexOf("%%") != -1) {
                                readString = readString.replaceAll("%%", "%");
                            }
                            boolean z8 = readOp == Operator.NOT_LIKE;
                            int indexOf = readString.indexOf(37);
                            if (indexOf != -1) {
                                String[] split = readString.split("%");
                                if (indexOf == 0) {
                                    if (readString.charAt(readString.length() - 1) == '%') {
                                        String[] strArr3 = new String[(split.length - 1)];
                                        System.arraycopy(split, 1, strArr3, 0, strArr3.length);
                                        strArr = strArr3;
                                    } else {
                                        String str3 = split[split.length - 1];
                                        if (split.length > 2) {
                                            String[] strArr4 = new String[(split.length - 2)];
                                            System.arraycopy(split, 1, strArr4, 0, strArr4.length);
                                            str = str3;
                                            strArr = strArr4;
                                            str2 = null;
                                        } else {
                                            str = str3;
                                            str2 = null;
                                            strArr = null;
                                        }
                                        return new FilterSegement(new MatchSegement(readName, str2, str, strArr, z8));
                                    }
                                } else if (readString.charAt(readString.length() - 1) == '%') {
                                    strArr = split;
                                } else {
                                    if (split.length == 1) {
                                        str2 = split[0];
                                        str = null;
                                        strArr = null;
                                    } else if (split.length == 2) {
                                        str2 = split[0];
                                        str = split[1];
                                        strArr = null;
                                    } else {
                                        String str4 = split[0];
                                        String str5 = split[split.length - 1];
                                        String[] strArr5 = new String[(split.length - 2)];
                                        System.arraycopy(split, 1, strArr5, 0, strArr5.length);
                                        str2 = str4;
                                        str = str5;
                                        strArr = strArr5;
                                    }
                                    return new FilterSegement(new MatchSegement(readName, str2, str, strArr, z8));
                                }
                                str2 = null;
                                str = null;
                                return new FilterSegement(new MatchSegement(readName, str2, str, strArr, z8));
                            } else if (readOp == Operator.LIKE) {
                                readOp = Operator.EQ;
                            } else {
                                readOp = Operator.NE;
                            }
                        }
                        return new FilterSegement(new StringOpSegement(readName, readString, readOp));
                    } else if (isDigitFirst(this.ch)) {
                        long readLongValue = readLongValue();
                        double readDoubleValue = this.ch == '.' ? readDoubleValue(readLongValue) : 0.0d;
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        if (readDoubleValue == 0.0d) {
                            return new FilterSegement(new IntOpSegement(readName, readLongValue, readOp));
                        }
                        return new FilterSegement(new DoubleOpSegement(readName, readDoubleValue, readOp));
                    } else {
                        if (this.ch == 'n') {
                            if ("null".equals(readName())) {
                                if (z2) {
                                    accept(')');
                                }
                                accept(']');
                                if (readOp == Operator.EQ) {
                                    return new FilterSegement(new NullSegement(readName));
                                }
                                if (readOp == Operator.NE) {
                                    return new FilterSegement(new NotNullSegement(readName));
                                }
                                throw new UnsupportedOperationException();
                            }
                        } else if (this.ch == 't') {
                            if (RequestConstant.TRUE.equals(readName())) {
                                if (z2) {
                                    accept(')');
                                }
                                accept(']');
                                if (readOp == Operator.EQ) {
                                    return new FilterSegement(new ValueSegment(readName, Boolean.TRUE, true));
                                }
                                if (readOp == Operator.NE) {
                                    return new FilterSegement(new ValueSegment(readName, Boolean.TRUE, false));
                                }
                                throw new UnsupportedOperationException();
                            }
                        } else if (this.ch == 'f' && RequestConstant.FALSE.equals(readName())) {
                            if (z2) {
                                accept(')');
                            }
                            accept(']');
                            if (readOp == Operator.EQ) {
                                return new FilterSegement(new ValueSegment(readName, Boolean.FALSE, true));
                            }
                            if (readOp == Operator.NE) {
                                return new FilterSegement(new ValueSegment(readName, Boolean.FALSE, false));
                            }
                            throw new UnsupportedOperationException();
                        }
                        throw new UnsupportedOperationException();
                    }
                } else {
                    next();
                    return new FilterSegement(new NotNullSegement(readName));
                }
            } else {
                int i3 = this.pos - 1;
                while (this.ch != ']' && this.ch != '/' && !isEOF() && (this.ch != '.' || z2 || z2)) {
                    if (this.ch == '\\') {
                        next();
                    }
                    next();
                }
                if (z) {
                    i = this.pos - 1;
                } else if (this.ch == '/' || this.ch == '.') {
                    i = this.pos - 1;
                } else {
                    i = this.pos;
                }
                String substring = this.path.substring(i3, i);
                if (substring.indexOf("\\.") != -1) {
                    String replaceAll = substring.replaceAll("\\\\\\.", "\\.");
                    if (replaceAll.indexOf("\\-") != -1) {
                        replaceAll = replaceAll.replaceAll("\\\\-", "-");
                    }
                    return new PropertySegement(replaceAll, false);
                }
                Segement buildArraySegement = buildArraySegement(substring);
                if (z && !isEOF()) {
                    accept(']');
                }
                return buildArraySegement;
            }
        }

        /* access modifiers changed from: protected */
        public long readLongValue() {
            int i = this.pos - 1;
            if (this.ch == '+' || this.ch == '-') {
                next();
            }
            while (this.ch >= '0' && this.ch <= '9') {
                next();
            }
            return Long.parseLong(this.path.substring(i, this.pos - 1));
        }

        /* access modifiers changed from: protected */
        public double readDoubleValue(long j) {
            int i = this.pos - 1;
            next();
            while (this.ch >= '0' && this.ch <= '9') {
                next();
            }
            double parseDouble = Double.parseDouble(this.path.substring(i, this.pos - 1));
            double d = (double) j;
            Double.isNaN(d);
            return parseDouble + d;
        }

        /* access modifiers changed from: protected */
        public Object readValue() {
            skipWhitespace();
            if (isDigitFirst(this.ch)) {
                return Long.valueOf(readLongValue());
            }
            if (this.ch == '\"' || this.ch == '\'') {
                return readString();
            }
            if (this.ch != 'n') {
                throw new UnsupportedOperationException();
            } else if ("null".equals(readName())) {
                return null;
            } else {
                throw new JSONPathException(this.path);
            }
        }

        /* access modifiers changed from: protected */
        public Operator readOp() {
            Operator operator;
            if (this.ch == '=') {
                next();
                operator = Operator.EQ;
            } else if (this.ch == '!') {
                next();
                accept('=');
                operator = Operator.NE;
            } else if (this.ch == '<') {
                next();
                if (this.ch == '=') {
                    next();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (this.ch == '>') {
                next();
                if (this.ch == '=') {
                    next();
                    operator = Operator.GE;
                } else {
                    operator = Operator.GT;
                }
            } else {
                operator = null;
            }
            if (operator != null) {
                return operator;
            }
            String readName = readName();
            if ("not".equalsIgnoreCase(readName)) {
                skipWhitespace();
                String readName2 = readName();
                if ("like".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_LIKE;
                }
                if ("rlike".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_RLIKE;
                }
                if ("in".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_IN;
                }
                if ("between".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_BETWEEN;
                }
                throw new UnsupportedOperationException();
            } else if ("like".equalsIgnoreCase(readName)) {
                return Operator.LIKE;
            } else {
                if ("rlike".equalsIgnoreCase(readName)) {
                    return Operator.RLIKE;
                }
                if ("in".equalsIgnoreCase(readName)) {
                    return Operator.IN;
                }
                if ("between".equalsIgnoreCase(readName)) {
                    return Operator.BETWEEN;
                }
                throw new UnsupportedOperationException();
            }
        }

        /* access modifiers changed from: package-private */
        public String readName() {
            skipWhitespace();
            if (this.ch == '\\' || IOUtils.firstIdentifier(this.ch)) {
                StringBuilder sb = new StringBuilder();
                while (!isEOF()) {
                    if (this.ch == '\\') {
                        next();
                        sb.append(this.ch);
                        if (isEOF()) {
                            break;
                        }
                        next();
                    } else if (!IOUtils.isIdent(this.ch)) {
                        break;
                    } else {
                        sb.append(this.ch);
                        next();
                    }
                }
                if (isEOF() && IOUtils.isIdent(this.ch)) {
                    sb.append(this.ch);
                }
                return sb.toString();
            }
            throw new JSONPathException("illeal jsonpath syntax. " + this.path);
        }

        /* access modifiers changed from: package-private */
        public String readString() {
            char c2 = this.ch;
            next();
            int i = this.pos - 1;
            while (this.ch != c2 && !isEOF()) {
                next();
            }
            String substring = this.path.substring(i, isEOF() ? this.pos : this.pos - 1);
            accept(c2);
            return substring;
        }

        /* access modifiers changed from: package-private */
        public void accept(char c2) {
            if (this.ch != c2) {
                throw new JSONPathException("expect '" + c2 + ", but '" + this.ch + "'");
            } else if (!isEOF()) {
                next();
            }
        }

        public Segement[] explain() {
            if (this.path == null || this.path.length() == 0) {
                throw new IllegalArgumentException();
            }
            Segement[] segementArr = new Segement[8];
            while (true) {
                Segement readSegement = readSegement();
                if (readSegement == null) {
                    break;
                }
                if (this.level == segementArr.length) {
                    Segement[] segementArr2 = new Segement[((this.level * 3) / 2)];
                    System.arraycopy(segementArr, 0, segementArr2, 0, this.level);
                    segementArr = segementArr2;
                }
                int i = this.level;
                this.level = i + 1;
                segementArr[i] = readSegement;
            }
            if (this.level == segementArr.length) {
                return segementArr;
            }
            Segement[] segementArr3 = new Segement[this.level];
            System.arraycopy(segementArr, 0, segementArr3, 0, this.level);
            return segementArr3;
        }

        /* access modifiers changed from: package-private */
        public Segement buildArraySegement(String str) {
            int length = str.length();
            int i = 0;
            char charAt = str.charAt(0);
            int i2 = 1;
            int i3 = length - 1;
            char charAt2 = str.charAt(i3);
            int indexOf = str.indexOf(44);
            int i4 = -1;
            if (str.length() <= 2 || charAt != '\'' || charAt2 != '\'') {
                int indexOf2 = str.indexOf(58);
                if (indexOf == -1 && indexOf2 == -1) {
                    if (!TypeUtils.isNumber(str)) {
                        return new PropertySegement(str, false);
                    }
                    try {
                        return new ArrayAccessSegement(Integer.parseInt(str));
                    } catch (NumberFormatException unused) {
                        return new PropertySegement(str, false);
                    }
                } else if (indexOf != -1) {
                    String[] split = str.split(",");
                    int[] iArr = new int[split.length];
                    while (i < split.length) {
                        iArr[i] = Integer.parseInt(split[i]);
                        i++;
                    }
                    return new MultiIndexSegement(iArr);
                } else if (indexOf2 != -1) {
                    String[] split2 = str.split(":");
                    int[] iArr2 = new int[split2.length];
                    for (int i5 = 0; i5 < split2.length; i5++) {
                        String str2 = split2[i5];
                        if (str2.length() != 0) {
                            iArr2[i5] = Integer.parseInt(str2);
                        } else if (i5 == 0) {
                            iArr2[i5] = 0;
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }
                    int i6 = iArr2[0];
                    if (iArr2.length > 1) {
                        i4 = iArr2[1];
                    }
                    if (iArr2.length == 3) {
                        i2 = iArr2[2];
                    }
                    if (i4 >= 0 && i4 < i6) {
                        throw new UnsupportedOperationException("end must greater than or equals start. start " + i6 + ",  end " + i4);
                    } else if (i2 > 0) {
                        return new RangeSegement(i6, i4, i2);
                    } else {
                        throw new UnsupportedOperationException("step must greater than zero : " + i2);
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            } else if (indexOf == -1) {
                return new PropertySegement(str.substring(1, i3), false);
            } else {
                String[] split3 = str.split(",");
                String[] strArr = new String[split3.length];
                while (i < split3.length) {
                    String str3 = split3[i];
                    strArr[i] = str3.substring(1, str3.length() - 1);
                    i++;
                }
                return new MultiPropertySegement(strArr);
            }
        }
    }

    static class SizeSegement implements Segement {
        public static final SizeSegement instance = new SizeSegement();

        SizeSegement() {
        }

        public Integer eval(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.evalSize(obj2));
        }
    }

    static class PropertySegement implements Segement {
        private final boolean deep;
        /* access modifiers changed from: private */
        public final String propertyName;
        private final long propertyNameHash;

        public PropertySegement(String str, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.deep = z;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.deep) {
                return jSONPath.getPropertyValue(obj2, this.propertyName, this.propertyNameHash);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.deepScan(obj2, this.propertyName, arrayList);
            return arrayList;
        }

        public void setValue(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                jSONPath.deepSet(obj, this.propertyName, this.propertyNameHash, obj2);
                return;
            }
            jSONPath.setPropertyValue(obj, this.propertyName, this.propertyNameHash, obj2);
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removePropertyValue(obj, this.propertyName);
        }
    }

    static class MultiPropertySegement implements Segement {
        private final String[] propertyNames;
        private final long[] propertyNamesHash;

        public MultiPropertySegement(String[] strArr) {
            this.propertyNames = strArr;
            this.propertyNamesHash = new long[strArr.length];
            for (int i = 0; i < this.propertyNamesHash.length; i++) {
                this.propertyNamesHash[i] = TypeUtils.fnv1a_64(strArr[i]);
            }
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.propertyNames.length);
            for (int i = 0; i < this.propertyNames.length; i++) {
                arrayList.add(jSONPath.getPropertyValue(obj2, this.propertyNames[i], this.propertyNamesHash[i]));
            }
            return arrayList;
        }
    }

    static class WildCardSegement implements Segement {
        public static WildCardSegement instance = new WildCardSegement();

        WildCardSegement() {
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getPropertyValues(obj2);
        }
    }

    static class ArrayAccessSegement implements Segement {
        private final int index;

        public ArrayAccessSegement(int i) {
            this.index = i;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getArrayItem(obj2, this.index);
        }

        public boolean setValue(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.setArrayItem(jSONPath, obj, this.index, obj2);
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removeArrayItem(jSONPath, obj, this.index);
        }
    }

    static class MultiIndexSegement implements Segement {
        private final int[] indexes;

        public MultiIndexSegement(int[] iArr) {
            this.indexes = iArr;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.indexes.length);
            for (int arrayItem : this.indexes) {
                arrayList.add(jSONPath.getArrayItem(obj2, arrayItem));
            }
            return arrayList;
        }
    }

    static class RangeSegement implements Segement {
        private final int end;
        private final int start;
        private final int step;

        public RangeSegement(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.step = i3;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            int intValue = SizeSegement.instance.eval(jSONPath, obj, obj2).intValue();
            int i = this.start >= 0 ? this.start : this.start + intValue;
            int i2 = this.end >= 0 ? this.end : this.end + intValue;
            int i3 = ((i2 - i) / this.step) + 1;
            if (i3 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i3);
            while (i <= i2 && i < intValue) {
                arrayList.add(jSONPath.getArrayItem(obj2, i));
                i += this.step;
            }
            return arrayList;
        }
    }

    static class NotNullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NotNullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) != null;
        }
    }

    static class NullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) == null;
        }
    }

    static class ValueSegment implements Filter {
        private boolean eq = true;
        private final String propertyName;
        private final long propertyNameHash;
        private final Object value;

        public ValueSegment(String str, Object obj, boolean z) {
            if (obj != null) {
                this.propertyName = str;
                this.propertyNameHash = TypeUtils.fnv1a_64(str);
                this.value = obj;
                this.eq = z;
                return;
            }
            throw new IllegalArgumentException("value is null");
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean equals = this.value.equals(jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash));
            return !this.eq ? !equals : equals;
        }
    }

    static class IntInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long[] values;

        public IntInSegement(String str, long[] jArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = jArr;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                for (long j : this.values) {
                    if (j == longValue) {
                        return !this.not;
                    }
                }
            }
            return this.not;
        }
    }

    static class IntBetweenSegement implements Filter {
        private final long endValue;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long startValue;

        public IntBetweenSegement(String str, long j, long j2, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startValue = j;
            this.endValue = j2;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                if (longValue >= this.startValue && longValue <= this.endValue) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class IntObjInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final Long[] values;

        public IntObjInSegement(String str, Long[] lArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = lArr;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            int i = 0;
            if (propertyValue == null) {
                Long[] lArr = this.values;
                int length = lArr.length;
                while (i < length) {
                    if (lArr[i] == null) {
                        return !this.not;
                    }
                    i++;
                }
                return this.not;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                Long[] lArr2 = this.values;
                int length2 = lArr2.length;
                while (i < length2) {
                    Long l = lArr2[i];
                    if (l != null && l.longValue() == longValue) {
                        return !this.not;
                    }
                    i++;
                }
            }
            return this.not;
        }
    }

    static class StringInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String[] values;

        public StringInSegement(String str, String[] strArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = strArr;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            for (String str : this.values) {
                if (str == propertyValue) {
                    return !this.not;
                }
                if (str != null && str.equals(propertyValue)) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class IntOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final long value;

        public IntOpSegement(String str, long j, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = j;
            this.op = operator;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            long longValue = ((Number) propertyValue).longValue();
            if (this.op == Operator.EQ) {
                if (longValue == this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.NE) {
                if (longValue != this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.GE) {
                if (longValue >= this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.GT) {
                if (longValue > this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.LE) {
                if (longValue <= this.value) {
                    return true;
                }
                return false;
            } else if (this.op != Operator.LT || longValue >= this.value) {
                return false;
            } else {
                return true;
            }
        }
    }

    static class DoubleOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final double value;

        public DoubleOpSegement(String str, double d, Operator operator) {
            this.propertyName = str;
            this.value = d;
            this.op = operator;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            double doubleValue = ((Number) propertyValue).doubleValue();
            if (this.op == Operator.EQ) {
                if (doubleValue == this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.NE) {
                if (doubleValue != this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.GE) {
                if (doubleValue >= this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.GT) {
                if (doubleValue > this.value) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.LE) {
                if (doubleValue <= this.value) {
                    return true;
                }
                return false;
            } else if (this.op != Operator.LT || doubleValue >= this.value) {
                return false;
            } else {
                return true;
            }
        }
    }

    static class MatchSegement implements Filter {
        private final String[] containsValues;
        private final String endsWithValue;
        private final int minLength;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String startsWithValue;

        public MatchSegement(String str, String str2, String str3, String[] strArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startsWithValue = str2;
            this.endsWithValue = str3;
            this.containsValues = strArr;
            this.not = z;
            int length = str2 != null ? str2.length() + 0 : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String length2 : strArr) {
                    length += length2.length();
                }
            }
            this.minLength = length;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            int i;
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            String obj4 = propertyValue.toString();
            if (obj4.length() < this.minLength) {
                return this.not;
            }
            if (this.startsWithValue == null) {
                i = 0;
            } else if (!obj4.startsWith(this.startsWithValue)) {
                return this.not;
            } else {
                i = this.startsWithValue.length() + 0;
            }
            if (this.containsValues != null) {
                for (String str : this.containsValues) {
                    int indexOf = obj4.indexOf(str, i);
                    if (indexOf == -1) {
                        return this.not;
                    }
                    i = indexOf + str.length();
                }
            }
            if (this.endsWithValue == null || obj4.endsWith(this.endsWithValue)) {
                return !this.not;
            }
            return this.not;
        }
    }

    static class RlikeSegement implements Filter {
        private final boolean not;
        private final Pattern pattern;
        private final String propertyName;
        private final long propertyNameHash;

        public RlikeSegement(String str, String str2, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.pattern = Pattern.compile(str2);
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            boolean matches = this.pattern.matcher(propertyValue.toString()).matches();
            return this.not ? !matches : matches;
        }
    }

    static class StringOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long propertyNameHash;
        private final String value;

        public StringOpSegement(String str, String str2, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = str2;
            this.op = operator;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (this.op == Operator.EQ) {
                return this.value.equals(propertyValue);
            }
            if (this.op == Operator.NE) {
                return !this.value.equals(propertyValue);
            }
            if (propertyValue == null) {
                return false;
            }
            int compareTo = this.value.compareTo(propertyValue.toString());
            if (this.op == Operator.GE) {
                if (compareTo <= 0) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.GT) {
                if (compareTo < 0) {
                    return true;
                }
                return false;
            } else if (this.op == Operator.LE) {
                if (compareTo >= 0) {
                    return true;
                }
                return false;
            } else if (this.op != Operator.LT || compareTo <= 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static class FilterSegement implements Segement {
        private final Filter filter;

        public FilterSegement(Filter filter2) {
            this.filter = filter2;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (obj2 instanceof Iterable) {
                for (Object next : (Iterable) obj2) {
                    if (this.filter.apply(jSONPath, obj, obj2, next)) {
                        jSONArray.add(next);
                    }
                }
                return jSONArray;
            } else if (this.filter.apply(jSONPath, obj, obj2, obj2)) {
                return obj2;
            } else {
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object getArrayItem(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                if (i < list.size()) {
                    return list.get(i);
                }
                return null;
            } else if (Math.abs(i) <= list.size()) {
                return list.get(list.size() + i);
            } else {
                return null;
            }
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    return Array.get(obj, i);
                }
                return null;
            } else if (Math.abs(i) <= length) {
                return Array.get(obj, length + i);
            } else {
                return null;
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(Integer.valueOf(i));
            return obj2 == null ? map.get(Integer.toString(i)) : obj2;
        } else if (obj instanceof Collection) {
            int i2 = 0;
            for (Object next : (Collection) obj) {
                if (i2 == i) {
                    return next;
                }
                i2++;
            }
            return null;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean setArrayItem(JSONPath jSONPath, Object obj, int i, Object obj2) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                list.set(i, obj2);
            } else {
                list.set(list.size() + i, obj2);
            }
            return true;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    Array.set(obj, i, obj2);
                }
            } else if (Math.abs(i) <= length) {
                Array.set(obj, length + i, obj2);
            }
            return true;
        }
        throw new JSONPathException("unsupported set operation." + cls);
    }

    public boolean removeArrayItem(JSONPath jSONPath, Object obj, int i) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i < 0) {
                int size = list.size() + i;
                if (size < 0) {
                    return false;
                }
                list.remove(size);
                return true;
            } else if (i >= list.size()) {
                return false;
            } else {
                list.remove(i);
                return true;
            }
        } else {
            Class<?> cls = obj.getClass();
            throw new JSONPathException("unsupported set operation." + cls);
        }
    }

    /* access modifiers changed from: protected */
    public Collection<Object> getPropertyValues(Object obj) {
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValues(obj);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        } else if (obj instanceof Map) {
            return ((Map) obj).values();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    static boolean eq(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (obj.getClass() == obj2.getClass()) {
            return obj.equals(obj2);
        }
        if (!(obj instanceof Number)) {
            return obj.equals(obj2);
        }
        if (obj2 instanceof Number) {
            return eqNotNull((Number) obj, (Number) obj2);
        }
        return false;
    }

    static boolean eqNotNull(Number number, Number number2) {
        Class<?> cls = number.getClass();
        boolean isInt = isInt(cls);
        Class<?> cls2 = number2.getClass();
        boolean isInt2 = isInt(cls2);
        if (number instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) number;
            if (isInt2) {
                return bigDecimal.equals(BigDecimal.valueOf(number2.longValue()));
            }
        }
        if (isInt) {
            if (isInt2) {
                if (number.longValue() == number2.longValue()) {
                    return true;
                }
                return false;
            } else if (number2 instanceof BigInteger) {
                return BigInteger.valueOf(number.longValue()).equals((BigInteger) number);
            }
        }
        if (isInt2 && (number instanceof BigInteger)) {
            return ((BigInteger) number).equals(BigInteger.valueOf(number2.longValue()));
        }
        boolean isDouble = isDouble(cls);
        boolean isDouble2 = isDouble(cls2);
        if (((!isDouble || !isDouble2) && ((!isDouble || !isInt2) && (!isDouble2 || !isInt))) || number.doubleValue() != number2.doubleValue()) {
            return false;
        }
        return true;
    }

    protected static boolean isDouble(Class<?> cls) {
        return cls == Float.class || cls == Double.class;
    }

    protected static boolean isInt(Class<?> cls) {
        return cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class;
    }

    /* access modifiers changed from: protected */
    public Object getPropertyValue(Object obj, String str, long j) {
        Object obj2 = obj;
        String str2 = str;
        long j2 = j;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object obj3 = map.get(str);
            if (obj3 == null) {
                return (SIZE == j2 || LENGTH == j2) ? Integer.valueOf(map.size()) : obj3;
            }
            return obj3;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValue(obj, str, j, false);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
            }
        } else if (obj2 instanceof List) {
            List list = (List) obj2;
            if (SIZE == j2 || LENGTH == j2) {
                return Integer.valueOf(list.size());
            }
            JSONArray jSONArray = new JSONArray(list.size());
            for (int i = 0; i < list.size(); i++) {
                Object propertyValue = getPropertyValue(list.get(i), str, j2);
                if (propertyValue instanceof Collection) {
                    jSONArray.addAll((Collection) propertyValue);
                } else if (propertyValue != null) {
                    jSONArray.add(propertyValue);
                }
            }
            return jSONArray;
        } else {
            if (obj2 instanceof Enum) {
                Enum enumR = (Enum) obj2;
                if (-4270347329889690746L == j2) {
                    return enumR.name();
                }
                if (-1014497654951707614L == j2) {
                    return Integer.valueOf(enumR.ordinal());
                }
            }
            if (obj2 instanceof Calendar) {
                Calendar calendar = (Calendar) obj2;
                if (8963398325558730460L == j2) {
                    return Integer.valueOf(calendar.get(1));
                }
                if (-811277319855450459L == j2) {
                    return Integer.valueOf(calendar.get(2));
                }
                if (-3851359326990528739L == j2) {
                    return Integer.valueOf(calendar.get(5));
                }
                if (4647432019745535567L == j2) {
                    return Integer.valueOf(calendar.get(11));
                }
                if (6607618197526598121L == j2) {
                    return Integer.valueOf(calendar.get(12));
                }
                if (-6586085717218287427L == j2) {
                    return Integer.valueOf(calendar.get(13));
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void deepScan(Object obj, String str, List<Object> list) {
        if (obj != null) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (map.containsKey(str)) {
                    list.add(map.get(str));
                    return;
                }
                for (Object deepScan : map.values()) {
                    deepScan(deepScan, str, list);
                }
                return;
            }
            JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
            if (javaBeanSerializer != null) {
                try {
                    FieldSerializer fieldSerializer = javaBeanSerializer.getFieldSerializer(str);
                    if (fieldSerializer != null) {
                        list.add(fieldSerializer.getPropertyValueDirect(obj));
                        return;
                    }
                    for (Object deepScan2 : javaBeanSerializer.getFieldValues(obj)) {
                        deepScan(deepScan2, str, list);
                    }
                } catch (InvocationTargetException e) {
                    throw new JSONException("getFieldValue error." + str, e);
                } catch (IllegalAccessException e2) {
                    throw new JSONException("getFieldValue error." + str, e2);
                } catch (Exception e3) {
                    throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e3);
                }
            } else if (obj instanceof List) {
                List list2 = (List) obj;
                for (int i = 0; i < list2.size(); i++) {
                    deepScan(list2.get(i), str, list);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void deepSet(Object obj, String str, long j, Object obj2) {
        if (obj != null) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (map.containsKey(str)) {
                    map.get(str);
                    map.put(str, obj2);
                    return;
                }
                for (Object deepSet : map.values()) {
                    deepSet(deepSet, str, j, obj2);
                }
                return;
            }
            Class<?> cls = obj.getClass();
            JavaBeanDeserializer javaBeanDeserializer = getJavaBeanDeserializer(cls);
            if (javaBeanDeserializer != null) {
                try {
                    FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
                    if (fieldDeserializer != null) {
                        fieldDeserializer.setValue(obj, obj2);
                        return;
                    }
                    for (Object deepSet2 : getJavaBeanSerializer(cls).getObjectFieldValues(obj)) {
                        deepSet(deepSet2, str, j, obj2);
                    }
                } catch (Exception e) {
                    throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                for (int i = 0; i < list.size(); i++) {
                    deepSet(list.get(i), str, j, obj2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean setPropertyValue(Object obj, String str, long j, Object obj2) {
        if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
            return true;
        } else if (obj instanceof List) {
            for (Object next : (List) obj) {
                if (next != null) {
                    setPropertyValue(next, str, j, obj2);
                }
            }
            return true;
        } else {
            ObjectDeserializer deserializer = this.parserConfig.getDeserializer((Type) obj.getClass());
            JavaBeanDeserializer javaBeanDeserializer = null;
            if (deserializer instanceof JavaBeanDeserializer) {
                javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
            }
            if (javaBeanDeserializer != null) {
                FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(j);
                if (fieldDeserializer == null) {
                    return false;
                }
                fieldDeserializer.setValue(obj, obj2);
                return true;
            }
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: protected */
    public boolean removePropertyValue(Object obj, String str) {
        if (!(obj instanceof Map)) {
            ObjectDeserializer deserializer = this.parserConfig.getDeserializer((Type) obj.getClass());
            JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
            if (javaBeanDeserializer != null) {
                FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
                if (fieldDeserializer == null) {
                    return false;
                }
                fieldDeserializer.setValue(obj, (String) null);
                return true;
            }
            throw new UnsupportedOperationException();
        } else if (((Map) obj).remove(str) != null) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public JavaBeanSerializer getJavaBeanSerializer(Class<?> cls) {
        ObjectSerializer objectWriter = this.serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            return (JavaBeanSerializer) objectWriter;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaBeanDeserializer getJavaBeanDeserializer(Class<?> cls) {
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer((Type) cls);
        if (deserializer instanceof JavaBeanDeserializer) {
            return (JavaBeanDeserializer) deserializer;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int evalSize(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        if (obj instanceof Map) {
            int i = 0;
            for (Object obj2 : ((Map) obj).values()) {
                if (obj2 != null) {
                    i++;
                }
            }
            return i;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            return -1;
        }
        try {
            return javaBeanSerializer.getSize(obj);
        } catch (Exception e) {
            throw new JSONPathException("evalSize error : " + this.path, e);
        }
    }

    public String toJSONString() {
        return JSON.toJSONString(this.path);
    }
}
