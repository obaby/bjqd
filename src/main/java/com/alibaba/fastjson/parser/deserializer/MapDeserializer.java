package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapDeserializer implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    public int getFastMatchToken() {
        return 12;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == JSONObject.class && defaultJSONParser.getFieldTypeResolver() == null) {
            return defaultJSONParser.parseObject();
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        Map<Object, Object> createMap = createMap(type);
        ParseContext context = defaultJSONParser.getContext();
        try {
            defaultJSONParser.setContext(context, createMap, obj);
            return deserialze(defaultJSONParser, type, obj, createMap);
        } finally {
            defaultJSONParser.setContext(context);
        }
    }

    /* access modifiers changed from: protected */
    public Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, Map map) {
        Type type2;
        if (!(type instanceof ParameterizedType)) {
            return defaultJSONParser.parseObject(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type type3 = parameterizedType.getActualTypeArguments()[0];
        if (map.getClass().getName().equals("org.springframework.util.LinkedMultiValueMap")) {
            type2 = List.class;
        } else {
            type2 = parameterizedType.getActualTypeArguments()[1];
        }
        if (String.class == type3) {
            return parseMap(defaultJSONParser, map, type2, obj);
        }
        return parseMap(defaultJSONParser, map, type3, type2, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r10 = r4.getDeserializer((java.lang.reflect.Type) r3);
        r0.nextToken(16);
        r9.setResolveStatus(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01a0, code lost:
        if (r1 == null) goto L_0x01a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a4, code lost:
        if ((r12 instanceof java.lang.Integer) != false) goto L_0x01a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01a6, code lost:
        r9.popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a9, code lost:
        r10 = (java.util.Map) r10.deserialze(r9, r3, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01af, code lost:
        r9.setContext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b2, code lost:
        return r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r9, java.util.Map<java.lang.String, java.lang.Object> r10, java.lang.reflect.Type r11, java.lang.Object r12) {
        /*
            com.alibaba.fastjson.parser.JSONLexer r0 = r9.lexer
            int r1 = r0.token()
            r2 = 0
            r3 = 12
            if (r1 == r3) goto L_0x008b
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "syntax error, expect {, actual "
            r10.append(r11)
            java.lang.String r11 = r0.tokenName()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            boolean r11 = r12 instanceof java.lang.String
            if (r11 == 0) goto L_0x0044
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            java.lang.String r10 = ", fieldName "
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            r11.append(r12)
            java.lang.String r10 = r11.toString()
        L_0x0044:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            java.lang.String r10 = ", "
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            java.lang.String r10 = r0.info()
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r11 = 4
            if (r1 == r11) goto L_0x0085
            com.alibaba.fastjson.JSONArray r11 = new com.alibaba.fastjson.JSONArray
            r11.<init>()
            r9.parseArray((java.util.Collection) r11, (java.lang.Object) r12)
            int r9 = r11.size()
            r12 = 1
            if (r9 != r12) goto L_0x0085
            java.lang.Object r9 = r11.get(r2)
            boolean r11 = r9 instanceof com.alibaba.fastjson.JSONObject
            if (r11 == 0) goto L_0x0085
            com.alibaba.fastjson.JSONObject r9 = (com.alibaba.fastjson.JSONObject) r9
            return r9
        L_0x0085:
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException
            r9.<init>(r10)
            throw r9
        L_0x008b:
            com.alibaba.fastjson.parser.ParseContext r1 = r9.getContext()
        L_0x008f:
            r0.skipWhitespace()     // Catch:{ all -> 0x0220 }
            char r3 = r0.getCurrent()     // Catch:{ all -> 0x0220 }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0220 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x0220 }
            if (r4 == 0) goto L_0x00ad
        L_0x009e:
            r4 = 44
            if (r3 != r4) goto L_0x00ad
            r0.next()     // Catch:{ all -> 0x0220 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0220 }
            char r3 = r0.getCurrent()     // Catch:{ all -> 0x0220 }
            goto L_0x009e
        L_0x00ad:
            r4 = 58
            r5 = 34
            r6 = 16
            if (r3 != r5) goto L_0x00e3
            com.alibaba.fastjson.parser.SymbolTable r3 = r9.getSymbolTable()     // Catch:{ all -> 0x0220 }
            java.lang.String r3 = r0.scanSymbol(r3, r5)     // Catch:{ all -> 0x0220 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0220 }
            char r7 = r0.getCurrent()     // Catch:{ all -> 0x0220 }
            if (r7 != r4) goto L_0x00c8
            goto L_0x014e
        L_0x00c8:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0220 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0220 }
            r11.<init>()     // Catch:{ all -> 0x0220 }
            java.lang.String r12 = "expect ':' at "
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            int r12 = r0.pos()     // Catch:{ all -> 0x0220 }
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0220 }
            r10.<init>(r11)     // Catch:{ all -> 0x0220 }
            throw r10     // Catch:{ all -> 0x0220 }
        L_0x00e3:
            r7 = 125(0x7d, float:1.75E-43)
            if (r3 != r7) goto L_0x00f4
            r0.next()     // Catch:{ all -> 0x0220 }
            r0.resetStringPosition()     // Catch:{ all -> 0x0220 }
            r0.nextToken(r6)     // Catch:{ all -> 0x0220 }
            r9.setContext(r1)
            return r10
        L_0x00f4:
            r7 = 39
            if (r3 != r7) goto L_0x0135
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0220 }
            boolean r3 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r3)     // Catch:{ all -> 0x0220 }
            if (r3 == 0) goto L_0x012d
            com.alibaba.fastjson.parser.SymbolTable r3 = r9.getSymbolTable()     // Catch:{ all -> 0x0220 }
            java.lang.String r3 = r0.scanSymbol(r3, r7)     // Catch:{ all -> 0x0220 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0220 }
            char r7 = r0.getCurrent()     // Catch:{ all -> 0x0220 }
            if (r7 != r4) goto L_0x0112
            goto L_0x014e
        L_0x0112:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0220 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0220 }
            r11.<init>()     // Catch:{ all -> 0x0220 }
            java.lang.String r12 = "expect ':' at "
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            int r12 = r0.pos()     // Catch:{ all -> 0x0220 }
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0220 }
            r10.<init>(r11)     // Catch:{ all -> 0x0220 }
            throw r10     // Catch:{ all -> 0x0220 }
        L_0x012d:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0220 }
            java.lang.String r11 = "syntax error"
            r10.<init>(r11)     // Catch:{ all -> 0x0220 }
            throw r10     // Catch:{ all -> 0x0220 }
        L_0x0135:
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0220 }
            boolean r3 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r3)     // Catch:{ all -> 0x0220 }
            if (r3 == 0) goto L_0x0218
            com.alibaba.fastjson.parser.SymbolTable r3 = r9.getSymbolTable()     // Catch:{ all -> 0x0220 }
            java.lang.String r3 = r0.scanSymbolUnQuoted(r3)     // Catch:{ all -> 0x0220 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0220 }
            char r7 = r0.getCurrent()     // Catch:{ all -> 0x0220 }
            if (r7 != r4) goto L_0x01f5
        L_0x014e:
            r0.next()     // Catch:{ all -> 0x0220 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0220 }
            r0.getCurrent()     // Catch:{ all -> 0x0220 }
            r0.resetStringPosition()     // Catch:{ all -> 0x0220 }
            java.lang.String r4 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0220 }
            r7 = 13
            r8 = 0
            if (r3 != r4) goto L_0x01b3
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0220 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x0220 }
            if (r4 != 0) goto L_0x01b3
            com.alibaba.fastjson.parser.SymbolTable r3 = r9.getSymbolTable()     // Catch:{ all -> 0x0220 }
            java.lang.String r3 = r0.scanSymbol(r3, r5)     // Catch:{ all -> 0x0220 }
            com.alibaba.fastjson.parser.ParserConfig r4 = r9.getConfig()     // Catch:{ all -> 0x0220 }
            int r5 = r0.getFeatures()     // Catch:{ all -> 0x0220 }
            java.lang.Class r3 = r4.checkAutoType(r3, r8, r5)     // Catch:{ all -> 0x0220 }
            java.lang.Class<java.util.Map> r5 = java.util.Map.class
            boolean r5 = r5.isAssignableFrom(r3)     // Catch:{ all -> 0x0220 }
            if (r5 == 0) goto L_0x0195
            r0.nextToken(r6)     // Catch:{ all -> 0x0220 }
            int r3 = r0.token()     // Catch:{ all -> 0x0220 }
            if (r3 != r7) goto L_0x01ed
            r0.nextToken(r6)     // Catch:{ all -> 0x0220 }
            r9.setContext(r1)
            return r10
        L_0x0195:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r10 = r4.getDeserializer((java.lang.reflect.Type) r3)     // Catch:{ all -> 0x0220 }
            r0.nextToken(r6)     // Catch:{ all -> 0x0220 }
            r11 = 2
            r9.setResolveStatus(r11)     // Catch:{ all -> 0x0220 }
            if (r1 == 0) goto L_0x01a9
            boolean r11 = r12 instanceof java.lang.Integer     // Catch:{ all -> 0x0220 }
            if (r11 != 0) goto L_0x01a9
            r9.popContext()     // Catch:{ all -> 0x0220 }
        L_0x01a9:
            java.lang.Object r10 = r10.deserialze(r9, r3, r12)     // Catch:{ all -> 0x0220 }
            java.util.Map r10 = (java.util.Map) r10     // Catch:{ all -> 0x0220 }
            r9.setContext(r1)
            return r10
        L_0x01b3:
            r0.nextToken()     // Catch:{ all -> 0x0220 }
            if (r2 == 0) goto L_0x01bb
            r9.setContext(r1)     // Catch:{ all -> 0x0220 }
        L_0x01bb:
            int r4 = r0.token()     // Catch:{ all -> 0x0220 }
            r5 = 8
            if (r4 != r5) goto L_0x01c7
            r0.nextToken()     // Catch:{ all -> 0x0220 }
            goto L_0x01cb
        L_0x01c7:
            java.lang.Object r8 = r9.parseObject((java.lang.reflect.Type) r11, (java.lang.Object) r3)     // Catch:{ all -> 0x0220 }
        L_0x01cb:
            r10.put(r3, r8)     // Catch:{ all -> 0x0220 }
            r9.checkMapResolve(r10, r3)     // Catch:{ all -> 0x0220 }
            r9.setContext(r1, r8, r3)     // Catch:{ all -> 0x0220 }
            r9.setContext(r1)     // Catch:{ all -> 0x0220 }
            int r3 = r0.token()     // Catch:{ all -> 0x0220 }
            r4 = 20
            if (r3 == r4) goto L_0x01f1
            r4 = 15
            if (r3 != r4) goto L_0x01e4
            goto L_0x01f1
        L_0x01e4:
            if (r3 != r7) goto L_0x01ed
            r0.nextToken()     // Catch:{ all -> 0x0220 }
            r9.setContext(r1)
            return r10
        L_0x01ed:
            int r2 = r2 + 1
            goto L_0x008f
        L_0x01f1:
            r9.setContext(r1)
            return r10
        L_0x01f5:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0220 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0220 }
            r11.<init>()     // Catch:{ all -> 0x0220 }
            java.lang.String r12 = "expect ':' at "
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            int r12 = r0.pos()     // Catch:{ all -> 0x0220 }
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            java.lang.String r12 = ", actual "
            r11.append(r12)     // Catch:{ all -> 0x0220 }
            r11.append(r7)     // Catch:{ all -> 0x0220 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0220 }
            r10.<init>(r11)     // Catch:{ all -> 0x0220 }
            throw r10     // Catch:{ all -> 0x0220 }
        L_0x0218:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0220 }
            java.lang.String r11 = "syntax error"
            r10.<init>(r11)     // Catch:{ all -> 0x0220 }
            throw r10     // Catch:{ all -> 0x0220 }
        L_0x0220:
            r10 = move-exception
            r9.setContext(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    public static Object parseMap(DefaultJSONParser defaultJSONParser, Map<Object, Object> map, Type type, Type type2, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 12 || jSONLexer.token() == 16) {
            ObjectDeserializer deserializer = defaultJSONParser.getConfig().getDeserializer(type);
            ObjectDeserializer deserializer2 = defaultJSONParser.getConfig().getDeserializer(type2);
            jSONLexer.nextToken(deserializer.getFastMatchToken());
            ParseContext context = defaultJSONParser.getContext();
            while (jSONLexer.token() != 13) {
                try {
                    Object obj2 = null;
                    if (jSONLexer.token() != 4 || !jSONLexer.isRef() || jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                        if (map.size() == 0 && jSONLexer.token() == 4 && JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal()) && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                            jSONLexer.nextTokenWithColon(4);
                            jSONLexer.nextToken(16);
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                return map;
                            }
                            jSONLexer.nextToken(deserializer.getFastMatchToken());
                        }
                        Object deserialze = deserializer.deserialze(defaultJSONParser, type, (Object) null);
                        if (jSONLexer.token() == 17) {
                            jSONLexer.nextToken(deserializer2.getFastMatchToken());
                            Object deserialze2 = deserializer2.deserialze(defaultJSONParser, type2, deserialze);
                            defaultJSONParser.checkMapResolve(map, deserialze);
                            map.put(deserialze, deserialze2);
                            if (jSONLexer.token() == 16) {
                                jSONLexer.nextToken(deserializer.getFastMatchToken());
                            }
                        } else {
                            throw new JSONException("syntax error, expect :, actual " + jSONLexer.token());
                        }
                    } else {
                        jSONLexer.nextTokenWithColon(4);
                        if (jSONLexer.token() == 4) {
                            String stringVal = jSONLexer.stringVal();
                            if ("..".equals(stringVal)) {
                                obj2 = context.parent.object;
                            } else if ("$".equals(stringVal)) {
                                ParseContext parseContext = context;
                                while (parseContext.parent != null) {
                                    parseContext = parseContext.parent;
                                }
                                obj2 = parseContext.object;
                            } else {
                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(context, stringVal));
                                defaultJSONParser.setResolveStatus(1);
                            }
                            jSONLexer.nextToken(13);
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken(16);
                                defaultJSONParser.setContext(context);
                                return obj2;
                            }
                            throw new JSONException("illegal ref");
                        }
                        throw new JSONException("illegal ref, " + JSONToken.name(jSONLexer.token()));
                    }
                } finally {
                    defaultJSONParser.setContext(context);
                }
            }
            jSONLexer.nextToken(16);
            defaultJSONParser.setContext(context);
            return map;
        }
        throw new JSONException("syntax error, expect {, actual " + jSONLexer.tokenName());
    }

    public Map<Object, Object> createMap(Type type) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (EnumMap.class.equals(rawType)) {
                return new EnumMap((Class) parameterizedType.getActualTypeArguments()[0]);
            }
            return createMap(rawType);
        }
        Class cls = (Class) type;
        if (!cls.isInterface()) {
            try {
                return (Map) cls.newInstance();
            } catch (Exception e) {
                throw new JSONException("unsupport type " + type, e);
            }
        } else {
            throw new JSONException("unsupport type " + type);
        }
    }
}
