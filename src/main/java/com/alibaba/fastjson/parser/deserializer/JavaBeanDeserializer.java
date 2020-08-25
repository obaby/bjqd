package com.alibaba.fastjson.parser.deserializer;

import cn.xports.qd2.entity.K;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap hashMap = null;
        int i = 0;
        while (i < length) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            HashMap hashMap2 = hashMap;
            for (String str : fieldInfo.alternateNames) {
                if (hashMap2 == null) {
                    hashMap2 = new HashMap();
                }
                hashMap2.put(str, createFieldDeserializer);
            }
            i++;
            hashMap = hashMap2;
        }
        this.alterNameFieldDeserializers = hashMap;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, (int[]) null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo > 0) {
                length = i2 - 1;
            } else if (isSetFlag(i2, iArr)) {
                return null;
            } else {
                return this.sortedFieldDeserializers[i2];
            }
        }
        if (this.alterNameFieldDeserializers != null) {
            return this.alterNameFieldDeserializers.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            for (int i = 0; i < this.sortedFieldDeserializers.length; i++) {
                jArr[i] = TypeUtils.fnv1a_64(this.sortedFieldDeserializers[i].fieldInfo.name);
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int binarySearch = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, -1);
            for (int i2 = 0; i2 < this.sortedFieldDeserializers.length; i2++) {
                int binarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(this.sortedFieldDeserializers[i2].fieldInfo.name));
                if (binarySearch2 >= 0) {
                    sArr[binarySearch2] = (short) i2;
                }
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i2 = i / 32;
        int i3 = i % 32;
        if (i2 < iArr.length) {
            if (((1 << i3) & iArr[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object obj;
        if (!(type instanceof Class) || !this.clazz.isInterface()) {
            Object obj2 = null;
            if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
                return null;
            }
            if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
                return null;
            }
            try {
                Constructor<?> constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize != 0) {
                    ParseContext context = defaultJSONParser.getContext();
                    if (context == null || context.object == null) {
                        throw new JSONException("can't create non-static inner class instance.");
                    } else if (type instanceof Class) {
                        String name = ((Class) type).getName();
                        String substring = name.substring(0, name.lastIndexOf(36));
                        Object obj3 = context.object;
                        String name2 = obj3.getClass().getName();
                        if (!name2.equals(substring)) {
                            ParseContext parseContext = context.parent;
                            if (!(parseContext == null || parseContext.object == null || ((!"java.util.ArrayList".equals(name2) && !"java.util.List".equals(name2) && !"java.util.Collection".equals(name2) && !"java.util.Map".equals(name2) && !"java.util.HashMap".equals(name2)) || !parseContext.object.getClass().getName().equals(substring)))) {
                                obj2 = parseContext.object;
                            }
                            obj3 = obj2;
                        }
                        if (obj3 != null) {
                            obj = constructor.newInstance(new Object[]{obj3});
                        } else {
                            throw new JSONException("can't create non-static inner class instance.");
                        }
                    } else {
                        throw new JSONException("can't create non-static inner class instance.");
                    }
                } else if (constructor != null) {
                    obj = constructor.newInstance(new Object[0]);
                } else {
                    obj = this.beanInfo.factoryMethod.invoke((Object) null, new Object[0]);
                }
                if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                    for (FieldInfo fieldInfo : this.beanInfo.fields) {
                        if (fieldInfo.fieldClass == String.class) {
                            try {
                                fieldInfo.set(obj, "");
                            } catch (Exception e) {
                                throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                            }
                        }
                    }
                }
                return obj;
            } catch (JSONException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
            }
        } else {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return deserialze(defaultJSONParser, type, obj, (Object) null, i, (int[]) null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> enumR;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 14) {
            T createInstance = createInstance(defaultJSONParser, type);
            int i = 0;
            int length = this.sortedFieldDeserializers.length;
            while (true) {
                int i2 = 16;
                if (i >= length) {
                    break;
                }
                char c2 = i == length + -1 ? ']' : ',';
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
                Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
                if (cls == Integer.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanInt(c2));
                } else if (cls == String.class) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanString(c2));
                } else if (cls == Long.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanLong(c2));
                } else if (cls.isEnum()) {
                    char current = jSONLexer.getCurrent();
                    if (current == '\"' || current == 'n') {
                        enumR = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c2);
                    } else if (current < '0' || current > '9') {
                        enumR = scanEnum(jSONLexer, c2);
                    } else {
                        enumR = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c2));
                    }
                    fieldDeserializer.setValue((Object) createInstance, (Object) enumR);
                } else if (cls == Boolean.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanBoolean(c2));
                } else if (cls == Float.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) Float.valueOf(jSONLexer.scanFloat(c2)));
                } else if (cls == Double.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) Double.valueOf(jSONLexer.scanDouble(c2)));
                } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                    fieldDeserializer.setValue((Object) createInstance, (Object) new Date(jSONLexer.scanLong(c2)));
                } else if (cls == BigDecimal.class) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) jSONLexer.scanDecimal(c2));
                } else {
                    jSONLexer.nextToken(14);
                    fieldDeserializer.setValue((Object) createInstance, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, (Object) fieldDeserializer.fieldInfo.name));
                    if (jSONLexer.token() == 15) {
                        break;
                    }
                    if (c2 == ']') {
                        i2 = 15;
                    }
                    check(jSONLexer, i2);
                }
                i++;
            }
            jSONLexer.nextToken(16);
            return createInstance;
        }
        throw new JSONException(K.error);
    }

    /* access modifiers changed from: protected */
    public void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    /* access modifiers changed from: protected */
    public Enum<?> scanEnum(JSONLexer jSONLexer, char c2) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x02c7, code lost:
        if (r11.matchStat == -2) goto L_0x02c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0308, code lost:
        r11.nextTokenWithColon(4);
        r2 = r11.token();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0310, code lost:
        if (r2 != 4) goto L_0x0399;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0312, code lost:
        r0 = r11.stringVal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x031c, code lost:
        if ("@".equals(r0) == false) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x031e, code lost:
        r0 = r15.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0320, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0328, code lost:
        if ("..".equals(r0) == false) goto L_0x0340;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x032a, code lost:
        r2 = r15.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x032e, code lost:
        if (r2.object == null) goto L_0x0333;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0330, code lost:
        r0 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0333, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r0));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x033e, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x0346, code lost:
        if ("$".equals(r0) == false) goto L_0x0363;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x0348, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x034b, code lost:
        if (r2.parent == null) goto L_0x0350;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x034d, code lost:
        r2 = r2.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0352, code lost:
        if (r2.object == null) goto L_0x0357;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x0354, code lost:
        r0 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x0357, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r0));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0363, code lost:
        r2 = r9.resolveReference(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x0367, code lost:
        if (r2 == null) goto L_0x036b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x0369, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x036b, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r15, r0));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x0376, code lost:
        r11.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x037f, code lost:
        if (r11.token() != 13) goto L_0x0391;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x0381, code lost:
        r11.nextToken(16);
        r9.setContext(r15, r1, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0389, code lost:
        if (r6 == null) goto L_0x038d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x038b, code lost:
        r6.object = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x038d, code lost:
        r9.setContext(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x0390, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0398, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x03b3, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x042d, code lost:
        r14 = r6;
        r0 = r18;
        r28 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:501:0x068e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x06b5, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r2 + ", " + r8.beanInfo.creatorConstructor.toGenericString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:510:0x06c7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:513:0x06e6, code lost:
        throw new com.alibaba.fastjson.JSONException("create factory method error, " + r8.beanInfo.factoryMethod.toString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:528:0x0706, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:531:0x070e, code lost:
        throw new com.alibaba.fastjson.JSONException("build object error", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:532:0x070f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:542:0x0756, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r11.token()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:552:0x076e, code lost:
        r6.object = r1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:406:0x054c, B:488:0x0656, B:507:0x06bc, B:522:0x06f8] */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x02d4 A[Catch:{ all -> 0x0765 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x03cd A[Catch:{ all -> 0x0765 }] */
    /* JADX WARNING: Removed duplicated region for block: B:336:0x0451 A[Catch:{ all -> 0x0765 }] */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x045c A[ADDED_TO_REGION, Catch:{ all -> 0x0765 }] */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x0495  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0071 A[Catch:{ all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x04f6  */
    /* JADX WARNING: Removed duplicated region for block: B:399:0x053a A[Catch:{ all -> 0x075f }] */
    /* JADX WARNING: Removed duplicated region for block: B:400:0x053b A[Catch:{ all -> 0x075f }] */
    /* JADX WARNING: Removed duplicated region for block: B:552:0x076e  */
    /* JADX WARNING: Removed duplicated region for block: B:568:0x0446 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r30, java.lang.reflect.Type r31, java.lang.Object r32, java.lang.Object r33, int r34, int[] r35) {
        /*
            r29 = this;
            r8 = r29
            r9 = r30
            r0 = r31
            r10 = r32
            java.lang.Class<com.alibaba.fastjson.JSON> r2 = com.alibaba.fastjson.JSON.class
            if (r0 == r2) goto L_0x0774
            java.lang.Class<com.alibaba.fastjson.JSONObject> r2 = com.alibaba.fastjson.JSONObject.class
            if (r0 != r2) goto L_0x0012
            goto L_0x0774
        L_0x0012:
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer
            r11 = r2
            com.alibaba.fastjson.parser.JSONLexerBase r11 = (com.alibaba.fastjson.parser.JSONLexerBase) r11
            com.alibaba.fastjson.parser.ParserConfig r12 = r30.getConfig()
            int r2 = r11.token()
            r3 = 8
            r13 = 16
            r14 = 0
            if (r2 != r3) goto L_0x002a
            r11.nextToken(r13)
            return r14
        L_0x002a:
            com.alibaba.fastjson.parser.ParseContext r3 = r30.getContext()
            if (r33 == 0) goto L_0x0034
            if (r3 == 0) goto L_0x0034
            com.alibaba.fastjson.parser.ParseContext r3 = r3.parent
        L_0x0034:
            r15 = r3
            r7 = 13
            if (r2 != r7) goto L_0x004f
            r11.nextToken(r13)     // Catch:{ all -> 0x0049 }
            if (r33 != 0) goto L_0x0043
            java.lang.Object r0 = r29.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r30, (java.lang.reflect.Type) r31)     // Catch:{ all -> 0x0049 }
            goto L_0x0045
        L_0x0043:
            r0 = r33
        L_0x0045:
            r9.setContext(r15)
            return r0
        L_0x0049:
            r0 = move-exception
            r1 = r33
        L_0x004c:
            r6 = r14
            goto L_0x076c
        L_0x004f:
            r3 = 14
            r6 = 0
            if (r2 != r3) goto L_0x0079
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0049 }
            int r4 = r4.mask     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.util.JavaBeanInfo r5 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            int r5 = r5.parserFeatures     // Catch:{ all -> 0x0049 }
            r5 = r5 & r4
            if (r5 != 0) goto L_0x006e
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0049 }
            boolean r5 = r11.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0049 }
            if (r5 != 0) goto L_0x006e
            r4 = r34 & r4
            if (r4 == 0) goto L_0x006c
            goto L_0x006e
        L_0x006c:
            r4 = 0
            goto L_0x006f
        L_0x006e:
            r4 = 1
        L_0x006f:
            if (r4 == 0) goto L_0x0079
            java.lang.Object r0 = r29.deserialzeArrayMapping(r30, r31, r32, r33)     // Catch:{ all -> 0x0049 }
            r9.setContext(r15)
            return r0
        L_0x0079:
            r4 = 12
            r5 = 4
            if (r2 == r4) goto L_0x011a
            if (r2 == r13) goto L_0x011a
            boolean r0 = r11.isBlankInput()     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x008a
            r9.setContext(r15)
            return r14
        L_0x008a:
            if (r2 != r5) goto L_0x00c3
            java.lang.String r0 = r11.stringVal()     // Catch:{ all -> 0x0049 }
            int r4 = r0.length()     // Catch:{ all -> 0x0049 }
            if (r4 != 0) goto L_0x009d
            r11.nextToken()     // Catch:{ all -> 0x0049 }
            r9.setContext(r15)
            return r14
        L_0x009d:
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.annotation.JSONType r4 = r4.jsonType     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x00c9
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.annotation.JSONType r4 = r4.jsonType     // Catch:{ all -> 0x0049 }
            java.lang.Class[] r4 = r4.seeAlso()     // Catch:{ all -> 0x0049 }
            int r5 = r4.length     // Catch:{ all -> 0x0049 }
        L_0x00ac:
            if (r6 >= r5) goto L_0x00c9
            r7 = r4[r6]     // Catch:{ all -> 0x0049 }
            java.lang.Class<java.lang.Enum> r12 = java.lang.Enum.class
            boolean r12 = r12.isAssignableFrom(r7)     // Catch:{ all -> 0x0049 }
            if (r12 == 0) goto L_0x00c0
            java.lang.Enum r7 = java.lang.Enum.valueOf(r7, r0)     // Catch:{ IllegalArgumentException -> 0x00c0 }
            r9.setContext(r15)
            return r7
        L_0x00c0:
            int r6 = r6 + 1
            goto L_0x00ac
        L_0x00c3:
            r0 = 5
            if (r2 != r0) goto L_0x00c9
            r11.getCalendar()     // Catch:{ all -> 0x0049 }
        L_0x00c9:
            if (r2 != r3) goto L_0x00dd
            char r0 = r11.getCurrent()     // Catch:{ all -> 0x0049 }
            r2 = 93
            if (r0 != r2) goto L_0x00dd
            r11.next()     // Catch:{ all -> 0x0049 }
            r11.nextToken()     // Catch:{ all -> 0x0049 }
            r9.setContext(r15)
            return r14
        L_0x00dd:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ all -> 0x0049 }
            r0.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "syntax error, expect {, actual "
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = r11.tokenName()     // Catch:{ all -> 0x0049 }
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = ", pos "
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            int r2 = r11.pos()     // Catch:{ all -> 0x0049 }
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            boolean r2 = r10 instanceof java.lang.String     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0106
            java.lang.String r2 = ", fieldName "
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            r0.append(r10)     // Catch:{ all -> 0x0049 }
        L_0x0106:
            java.lang.String r2 = ", fastjson-version "
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "1.2.46"
            r0.append(r2)     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r0)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x011a:
            int r2 = r9.resolveStatus     // Catch:{ all -> 0x0767 }
            r3 = 2
            if (r2 != r3) goto L_0x0121
            r9.resolveStatus = r6     // Catch:{ all -> 0x0049 }
        L_0x0121:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0767 }
            java.lang.String r4 = r2.typeKey     // Catch:{ all -> 0x0767 }
            r1 = r33
            r2 = r35
            r6 = r14
            r18 = r6
            r3 = 0
        L_0x012d:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r14 = r8.sortedFieldDeserializers     // Catch:{ all -> 0x0765 }
            int r14 = r14.length     // Catch:{ all -> 0x0765 }
            if (r3 >= r14) goto L_0x014c
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r14 = r8.sortedFieldDeserializers     // Catch:{ all -> 0x0765 }
            r14 = r14[r3]     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.util.FieldInfo r5 = r14.fieldInfo     // Catch:{ all -> 0x0765 }
            java.lang.Class<?> r13 = r5.fieldClass     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.annotation.JSONField r20 = r5.getAnnotation()     // Catch:{ all -> 0x0765 }
            if (r20 == 0) goto L_0x014a
            boolean r7 = r14 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer     // Catch:{ all -> 0x0765 }
            if (r7 == 0) goto L_0x014a
            r7 = r14
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r7 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r7     // Catch:{ all -> 0x0765 }
            boolean r7 = r7.customDeserilizer     // Catch:{ all -> 0x0765 }
            goto L_0x0152
        L_0x014a:
            r7 = 0
            goto L_0x0152
        L_0x014c:
            r5 = 0
            r7 = 0
            r13 = 0
            r14 = 0
            r20 = 0
        L_0x0152:
            if (r14 == 0) goto L_0x02cd
            r22 = r3
            char[] r3 = r5.name_chars     // Catch:{ all -> 0x0765 }
            if (r7 == 0) goto L_0x0164
            boolean r7 = r11.matchField(r3)     // Catch:{ all -> 0x0765 }
            if (r7 == 0) goto L_0x0164
        L_0x0160:
            r0 = 0
            r3 = 1
            goto L_0x02d1
        L_0x0164:
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0765 }
            r0 = -2
            if (r13 == r7) goto L_0x02b5
            java.lang.Class<java.lang.Integer> r7 = java.lang.Integer.class
            if (r13 != r7) goto L_0x016f
            goto L_0x02b5
        L_0x016f:
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x0765 }
            if (r13 == r7) goto L_0x02a2
            java.lang.Class<java.lang.Long> r7 = java.lang.Long.class
            if (r13 != r7) goto L_0x0179
            goto L_0x02a2
        L_0x0179:
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r13 != r7) goto L_0x0191
            java.lang.String r3 = r11.scanFieldString(r3)     // Catch:{ all -> 0x0765 }
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 <= 0) goto L_0x0188
        L_0x0185:
            r0 = r3
            goto L_0x02c1
        L_0x0188:
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 != r0) goto L_0x018e
            goto L_0x02c9
        L_0x018e:
            r0 = r3
            goto L_0x02d0
        L_0x0191:
            java.lang.Class<java.util.Date> r7 = java.util.Date.class
            if (r13 != r7) goto L_0x01a8
            java.lang.String r7 = r5.format     // Catch:{ all -> 0x0765 }
            if (r7 != 0) goto L_0x01a8
            java.util.Date r3 = r11.scanFieldDate(r3)     // Catch:{ all -> 0x0765 }
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 <= 0) goto L_0x01a2
            goto L_0x0185
        L_0x01a2:
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 != r0) goto L_0x018e
            goto L_0x02c9
        L_0x01a8:
            java.lang.Class<java.math.BigDecimal> r7 = java.math.BigDecimal.class
            if (r13 != r7) goto L_0x01bb
            java.math.BigDecimal r3 = r11.scanFieldDecimal(r3)     // Catch:{ all -> 0x0765 }
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 <= 0) goto L_0x01b5
            goto L_0x0185
        L_0x01b5:
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 != r0) goto L_0x018e
            goto L_0x02c9
        L_0x01bb:
            java.lang.Class<java.math.BigInteger> r7 = java.math.BigInteger.class
            if (r13 != r7) goto L_0x01ce
            java.math.BigInteger r3 = r11.scanFieldBigInteger(r3)     // Catch:{ all -> 0x0765 }
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 <= 0) goto L_0x01c8
            goto L_0x0185
        L_0x01c8:
            int r7 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r7 != r0) goto L_0x018e
            goto L_0x02c9
        L_0x01ce:
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0765 }
            if (r13 == r7) goto L_0x028f
            java.lang.Class<java.lang.Boolean> r7 = java.lang.Boolean.class
            if (r13 != r7) goto L_0x01d8
            goto L_0x028f
        L_0x01d8:
            java.lang.Class r7 = java.lang.Float.TYPE     // Catch:{ all -> 0x0765 }
            if (r13 == r7) goto L_0x027c
            java.lang.Class<java.lang.Float> r7 = java.lang.Float.class
            if (r13 != r7) goto L_0x01e2
            goto L_0x027c
        L_0x01e2:
            java.lang.Class r7 = java.lang.Double.TYPE     // Catch:{ all -> 0x0765 }
            if (r13 == r7) goto L_0x0269
            java.lang.Class<java.lang.Double> r7 = java.lang.Double.class
            if (r13 != r7) goto L_0x01ec
            goto L_0x0269
        L_0x01ec:
            boolean r7 = r13.isEnum()     // Catch:{ all -> 0x0765 }
            if (r7 == 0) goto L_0x0222
            com.alibaba.fastjson.parser.ParserConfig r7 = r30.getConfig()     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r7 = r7.getDeserializer((java.lang.reflect.Type) r13)     // Catch:{ all -> 0x0765 }
            boolean r7 = r7 instanceof com.alibaba.fastjson.parser.deserializer.EnumDeserializer     // Catch:{ all -> 0x0765 }
            if (r7 == 0) goto L_0x0222
            if (r20 == 0) goto L_0x0208
            java.lang.Class r7 = r20.deserializeUsing()     // Catch:{ all -> 0x0765 }
            java.lang.Class<java.lang.Void> r0 = java.lang.Void.class
            if (r7 != r0) goto L_0x0222
        L_0x0208:
            boolean r0 = r14 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer     // Catch:{ all -> 0x0765 }
            if (r0 == 0) goto L_0x02cf
            r0 = r14
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r0 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r0     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r0 = r0.fieldValueDeserilizer     // Catch:{ all -> 0x0765 }
            java.lang.Enum r0 = r8.scanEnum(r11, r3, r0)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x021b
            goto L_0x02c1
        L_0x021b:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x0222:
            java.lang.Class<int[]> r0 = int[].class
            if (r13 != r0) goto L_0x0237
            int[] r0 = r11.scanFieldIntArray(r3)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x0230
            goto L_0x02c1
        L_0x0230:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x0237:
            java.lang.Class<float[]> r0 = float[].class
            if (r13 != r0) goto L_0x024c
            float[] r0 = r11.scanFieldFloatArray(r3)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x0245
            goto L_0x02c1
        L_0x0245:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x024c:
            java.lang.Class<float[][]> r0 = float[][].class
            if (r13 != r0) goto L_0x0261
            float[][] r0 = r11.scanFieldFloatArray2(r3)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x025a
            goto L_0x02c1
        L_0x025a:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x0261:
            boolean r0 = r11.matchField(r3)     // Catch:{ all -> 0x0765 }
            if (r0 == 0) goto L_0x02c9
            goto L_0x0160
        L_0x0269:
            double r23 = r11.scanFieldDouble(r3)     // Catch:{ all -> 0x0765 }
            java.lang.Double r0 = java.lang.Double.valueOf(r23)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x0276
            goto L_0x02c1
        L_0x0276:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x027c:
            float r0 = r11.scanFieldFloat(r3)     // Catch:{ all -> 0x0765 }
            java.lang.Float r0 = java.lang.Float.valueOf(r0)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x0289
            goto L_0x02c1
        L_0x0289:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x028f:
            boolean r0 = r11.scanFieldBoolean(r3)     // Catch:{ all -> 0x0765 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x029c
            goto L_0x02c1
        L_0x029c:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x02a2:
            long r23 = r11.scanFieldLong(r3)     // Catch:{ all -> 0x0765 }
            java.lang.Long r0 = java.lang.Long.valueOf(r23)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x02af
            goto L_0x02c1
        L_0x02af:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
            goto L_0x02c9
        L_0x02b5:
            int r0 = r11.scanFieldInt(r3)     // Catch:{ all -> 0x0765 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0765 }
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            if (r3 <= 0) goto L_0x02c4
        L_0x02c1:
            r3 = 1
            r7 = 1
            goto L_0x02d2
        L_0x02c4:
            int r3 = r11.matchStat     // Catch:{ all -> 0x0765 }
            r7 = -2
            if (r3 != r7) goto L_0x02d0
        L_0x02c9:
            r5 = 13
            goto L_0x0434
        L_0x02cd:
            r22 = r3
        L_0x02cf:
            r0 = 0
        L_0x02d0:
            r3 = 0
        L_0x02d1:
            r7 = 0
        L_0x02d2:
            if (r3 != 0) goto L_0x0451
            r25 = r13
            com.alibaba.fastjson.parser.SymbolTable r13 = r9.symbolTable     // Catch:{ all -> 0x0765 }
            java.lang.String r13 = r11.scanSymbol(r13)     // Catch:{ all -> 0x0765 }
            if (r13 != 0) goto L_0x02fe
            r26 = r0
            int r0 = r11.token()     // Catch:{ all -> 0x0765 }
            r27 = r5
            r5 = 13
            if (r0 != r5) goto L_0x02f1
            r5 = 16
            r11.nextToken(r5)     // Catch:{ all -> 0x0765 }
            goto L_0x042d
        L_0x02f1:
            r5 = 16
            if (r0 != r5) goto L_0x0302
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0765 }
            boolean r0 = r11.isEnabled((com.alibaba.fastjson.parser.Feature) r0)     // Catch:{ all -> 0x0765 }
            if (r0 == 0) goto L_0x0302
            goto L_0x02c9
        L_0x02fe:
            r26 = r0
            r27 = r5
        L_0x0302:
            java.lang.String r0 = "$ref"
            if (r0 != r13) goto L_0x03b4
            if (r15 == 0) goto L_0x03b4
            r0 = 4
            r11.nextTokenWithColon(r0)     // Catch:{ all -> 0x0765 }
            int r2 = r11.token()     // Catch:{ all -> 0x0765 }
            if (r2 != r0) goto L_0x0399
            java.lang.String r0 = r11.stringVal()     // Catch:{ all -> 0x0765 }
            java.lang.String r2 = "@"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0765 }
            if (r2 == 0) goto L_0x0322
            java.lang.Object r0 = r15.object     // Catch:{ all -> 0x0765 }
        L_0x0320:
            r1 = r0
            goto L_0x0376
        L_0x0322:
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0765 }
            if (r2 == 0) goto L_0x0340
            com.alibaba.fastjson.parser.ParseContext r2 = r15.parent     // Catch:{ all -> 0x0765 }
            java.lang.Object r3 = r2.object     // Catch:{ all -> 0x0765 }
            if (r3 == 0) goto L_0x0333
            java.lang.Object r0 = r2.object     // Catch:{ all -> 0x0765 }
            goto L_0x0320
        L_0x0333:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0765 }
            r3.<init>(r2, r0)     // Catch:{ all -> 0x0765 }
            r9.addResolveTask(r3)     // Catch:{ all -> 0x0765 }
            r0 = 1
            r9.resolveStatus = r0     // Catch:{ all -> 0x0765 }
        L_0x033e:
            r0 = r1
            goto L_0x0320
        L_0x0340:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0765 }
            if (r2 == 0) goto L_0x0363
            r2 = r15
        L_0x0349:
            com.alibaba.fastjson.parser.ParseContext r3 = r2.parent     // Catch:{ all -> 0x0765 }
            if (r3 == 0) goto L_0x0350
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent     // Catch:{ all -> 0x0765 }
            goto L_0x0349
        L_0x0350:
            java.lang.Object r3 = r2.object     // Catch:{ all -> 0x0765 }
            if (r3 == 0) goto L_0x0357
            java.lang.Object r0 = r2.object     // Catch:{ all -> 0x0765 }
            goto L_0x0320
        L_0x0357:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0765 }
            r3.<init>(r2, r0)     // Catch:{ all -> 0x0765 }
            r9.addResolveTask(r3)     // Catch:{ all -> 0x0765 }
            r0 = 1
            r9.resolveStatus = r0     // Catch:{ all -> 0x0765 }
            goto L_0x033e
        L_0x0363:
            java.lang.Object r2 = r9.resolveReference(r0)     // Catch:{ all -> 0x0765 }
            if (r2 == 0) goto L_0x036b
            r1 = r2
            goto L_0x0376
        L_0x036b:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0765 }
            r2.<init>(r15, r0)     // Catch:{ all -> 0x0765 }
            r9.addResolveTask(r2)     // Catch:{ all -> 0x0765 }
            r0 = 1
            r9.resolveStatus = r0     // Catch:{ all -> 0x0765 }
        L_0x0376:
            r0 = 13
            r11.nextToken(r0)     // Catch:{ all -> 0x0765 }
            int r2 = r11.token()     // Catch:{ all -> 0x0765 }
            if (r2 != r0) goto L_0x0391
            r0 = 16
            r11.nextToken(r0)     // Catch:{ all -> 0x0765 }
            r9.setContext(r15, r1, r10)     // Catch:{ all -> 0x0765 }
            if (r6 == 0) goto L_0x038d
            r6.object = r1
        L_0x038d:
            r9.setContext(r15)
            return r1
        L_0x0391:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0765 }
            java.lang.String r2 = "illegal ref"
            r0.<init>(r2)     // Catch:{ all -> 0x0765 }
            throw r0     // Catch:{ all -> 0x0765 }
        L_0x0399:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0765 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0765 }
            r3.<init>()     // Catch:{ all -> 0x0765 }
            java.lang.String r4 = "illegal ref, "
            r3.append(r4)     // Catch:{ all -> 0x0765 }
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)     // Catch:{ all -> 0x0765 }
            r3.append(r2)     // Catch:{ all -> 0x0765 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0765 }
            r0.<init>(r2)     // Catch:{ all -> 0x0765 }
            throw r0     // Catch:{ all -> 0x0765 }
        L_0x03b4:
            if (r4 == 0) goto L_0x03bf
            boolean r0 = r4.equals(r13)     // Catch:{ all -> 0x0765 }
            if (r0 != 0) goto L_0x03bd
            goto L_0x03bf
        L_0x03bd:
            r0 = 4
            goto L_0x03c4
        L_0x03bf:
            java.lang.String r0 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0765 }
            if (r0 != r13) goto L_0x044e
            goto L_0x03bd
        L_0x03c4:
            r11.nextTokenWithColon(r0)     // Catch:{ all -> 0x0765 }
            int r3 = r11.token()     // Catch:{ all -> 0x0765 }
            if (r3 != r0) goto L_0x0446
            java.lang.String r0 = r11.stringVal()     // Catch:{ all -> 0x0765 }
            r3 = 16
            r11.nextToken(r3)     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r8.beanInfo     // Catch:{ all -> 0x0765 }
            java.lang.String r3 = r3.typeName     // Catch:{ all -> 0x0765 }
            boolean r3 = r0.equals(r3)     // Catch:{ all -> 0x0765 }
            if (r3 != 0) goto L_0x0422
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.IgnoreAutoType     // Catch:{ all -> 0x0765 }
            boolean r3 = r9.isEnabled(r3)     // Catch:{ all -> 0x0765 }
            if (r3 == 0) goto L_0x03e9
            goto L_0x0422
        L_0x03e9:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r2 = r8.getSeeAlso(r12, r2, r0)     // Catch:{ all -> 0x0765 }
            if (r2 != 0) goto L_0x0406
            java.lang.Class r2 = com.alibaba.fastjson.util.TypeUtils.getClass(r31)     // Catch:{ all -> 0x0765 }
            int r3 = r11.getFeatures()     // Catch:{ all -> 0x0765 }
            java.lang.Class r14 = r12.checkAutoType(r0, r2, r3)     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.parser.ParserConfig r2 = r30.getConfig()     // Catch:{ all -> 0x0765 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.getDeserializer((java.lang.reflect.Type) r14)     // Catch:{ all -> 0x0765 }
            goto L_0x0407
        L_0x0406:
            r14 = 0
        L_0x0407:
            java.lang.Object r3 = r2.deserialze(r9, r14, r10)     // Catch:{ all -> 0x0765 }
            boolean r5 = r2 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ all -> 0x0765 }
            if (r5 == 0) goto L_0x041a
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r2 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r2     // Catch:{ all -> 0x0765 }
            if (r4 == 0) goto L_0x041a
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r2.getFieldDeserializer((java.lang.String) r4)     // Catch:{ all -> 0x0765 }
            r2.setValue((java.lang.Object) r3, (java.lang.String) r0)     // Catch:{ all -> 0x0765 }
        L_0x041a:
            if (r6 == 0) goto L_0x041e
            r6.object = r1
        L_0x041e:
            r9.setContext(r15)
            return r3
        L_0x0422:
            int r0 = r11.token()     // Catch:{ all -> 0x0765 }
            r5 = 13
            if (r0 != r5) goto L_0x0434
            r11.nextToken()     // Catch:{ all -> 0x0765 }
        L_0x042d:
            r14 = r6
            r0 = r18
            r28 = 0
            goto L_0x0548
        L_0x0434:
            r13 = r4
            r0 = r18
            r16 = r22
            r3 = 0
            r4 = 0
            r5 = 1
            r14 = 13
            r21 = 4
            r18 = r2
            r2 = 16
            goto L_0x0727
        L_0x0446:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0765 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0765 }
            throw r0     // Catch:{ all -> 0x0765 }
        L_0x044e:
            r5 = 13
            goto L_0x045a
        L_0x0451:
            r26 = r0
            r27 = r5
            r25 = r13
            r5 = 13
            r13 = 0
        L_0x045a:
            if (r1 != 0) goto L_0x048c
            if (r18 != 0) goto L_0x048c
            java.lang.Object r5 = r29.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r30, (java.lang.reflect.Type) r31)     // Catch:{ all -> 0x0765 }
            if (r5 != 0) goto L_0x0471
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x046d }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r1 = r8.fieldDeserializers     // Catch:{ all -> 0x046d }
            int r1 = r1.length     // Catch:{ all -> 0x046d }
            r0.<init>(r1)     // Catch:{ all -> 0x046d }
            goto L_0x0473
        L_0x046d:
            r0 = move-exception
        L_0x046e:
            r1 = r5
            goto L_0x076c
        L_0x0471:
            r0 = r18
        L_0x0473:
            com.alibaba.fastjson.parser.ParseContext r1 = r9.setContext(r15, r5, r10)     // Catch:{ all -> 0x046d }
            if (r2 != 0) goto L_0x0486
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r2 = r8.fieldDeserializers     // Catch:{ all -> 0x0483 }
            int r2 = r2.length     // Catch:{ all -> 0x0483 }
            int r2 = r2 / 32
            r6 = 1
            int r2 = r2 + r6
            int[] r2 = new int[r2]     // Catch:{ all -> 0x0483 }
            goto L_0x0486
        L_0x0483:
            r0 = move-exception
            r6 = r1
            goto L_0x046e
        L_0x0486:
            r20 = r1
            r18 = r2
            r6 = r5
            goto L_0x0493
        L_0x048c:
            r20 = r6
            r0 = r18
            r6 = r1
            r18 = r2
        L_0x0493:
            if (r3 == 0) goto L_0x04f6
            if (r7 != 0) goto L_0x04ad
            r7 = r31
            r14.parseField(r9, r6, r7, r0)     // Catch:{ all -> 0x04a9 }
        L_0x049c:
            r13 = r4
            r17 = r6
            r16 = r22
            r14 = 13
            r21 = 4
            r28 = 0
            goto L_0x0532
        L_0x04a9:
            r0 = move-exception
            r1 = r6
            goto L_0x0762
        L_0x04ad:
            r7 = r31
            if (r6 != 0) goto L_0x04bb
            r5 = r27
            java.lang.String r1 = r5.name     // Catch:{ all -> 0x04a9 }
            r3 = r26
            r0.put(r1, r3)     // Catch:{ all -> 0x04a9 }
            goto L_0x04dc
        L_0x04bb:
            r3 = r26
            if (r3 != 0) goto L_0x04d9
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ all -> 0x04a9 }
            r13 = r25
            if (r13 == r1) goto L_0x04dc
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x04a9 }
            if (r13 == r1) goto L_0x04dc
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch:{ all -> 0x04a9 }
            if (r13 == r1) goto L_0x04dc
            java.lang.Class r1 = java.lang.Double.TYPE     // Catch:{ all -> 0x04a9 }
            if (r13 == r1) goto L_0x04dc
            java.lang.Class r1 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x04a9 }
            if (r13 == r1) goto L_0x04dc
            r14.setValue((java.lang.Object) r6, (java.lang.Object) r3)     // Catch:{ all -> 0x04a9 }
            goto L_0x04dc
        L_0x04d9:
            r14.setValue((java.lang.Object) r6, (java.lang.Object) r3)     // Catch:{ all -> 0x04a9 }
        L_0x04dc:
            if (r18 == 0) goto L_0x04eb
            int r3 = r22 / 32
            int r1 = r22 % 32
            r2 = r18[r3]     // Catch:{ all -> 0x04a9 }
            r5 = 1
            int r1 = r5 >> r1
            r1 = r1 | r2
            r18[r3] = r1     // Catch:{ all -> 0x04a9 }
            goto L_0x04ec
        L_0x04eb:
            r5 = 1
        L_0x04ec:
            int r1 = r11.matchStat     // Catch:{ all -> 0x04a9 }
            r14 = 4
            if (r1 != r14) goto L_0x049c
            r17 = r6
            r28 = 0
            goto L_0x0544
        L_0x04f6:
            r5 = 1
            r7 = r31
            r14 = 4
            r1 = r29
            r2 = r30
            r16 = r22
            r3 = r13
            r13 = r4
            r4 = r6
            r14 = 1
            r19 = 13
            r21 = 4
            r5 = r31
            r17 = r6
            r28 = 0
            r6 = r0
            r14 = 13
            r7 = r18
            boolean r1 = r1.parseField(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x075f }
            if (r1 != 0) goto L_0x052a
            int r1 = r11.token()     // Catch:{ all -> 0x075f }
            if (r1 != r14) goto L_0x0523
            r11.nextToken()     // Catch:{ all -> 0x075f }
            goto L_0x0544
        L_0x0523:
            r2 = 16
        L_0x0525:
            r3 = 0
            r4 = 0
            r5 = 1
            goto L_0x0723
        L_0x052a:
            int r1 = r11.token()     // Catch:{ all -> 0x075f }
            r2 = 17
            if (r1 == r2) goto L_0x0757
        L_0x0532:
            int r1 = r11.token()     // Catch:{ all -> 0x075f }
            r2 = 16
            if (r1 != r2) goto L_0x053b
            goto L_0x0525
        L_0x053b:
            int r1 = r11.token()     // Catch:{ all -> 0x075f }
            if (r1 != r14) goto L_0x0712
            r11.nextToken(r2)     // Catch:{ all -> 0x075f }
        L_0x0544:
            r1 = r17
            r14 = r20
        L_0x0548:
            if (r1 != 0) goto L_0x06e9
            if (r0 != 0) goto L_0x0564
            java.lang.Object r2 = r29.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r30, (java.lang.reflect.Type) r31)     // Catch:{ all -> 0x070f }
            if (r14 != 0) goto L_0x055c
            com.alibaba.fastjson.parser.ParseContext r0 = r9.setContext(r15, r2, r10)     // Catch:{ all -> 0x0558 }
            r14 = r0
            goto L_0x055c
        L_0x0558:
            r0 = move-exception
            r1 = r2
            goto L_0x004c
        L_0x055c:
            if (r14 == 0) goto L_0x0560
            r14.object = r2
        L_0x0560:
            r9.setContext(r15)
            return r2
        L_0x0564:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.String[] r2 = r2.creatorConstructorParameters     // Catch:{ all -> 0x070f }
            r5 = 0
            r6 = 0
            if (r2 == 0) goto L_0x05db
            int r10 = r2.length     // Catch:{ all -> 0x070f }
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ all -> 0x070f }
            r11 = 0
        L_0x0571:
            int r12 = r2.length     // Catch:{ all -> 0x070f }
            if (r11 >= r12) goto L_0x0650
            r12 = r2[r11]     // Catch:{ all -> 0x070f }
            java.lang.Object r12 = r0.remove(r12)     // Catch:{ all -> 0x070f }
            if (r12 != 0) goto L_0x05d6
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.reflect.Type[] r13 = r13.creatorConstructorParameterTypes     // Catch:{ all -> 0x070f }
            r13 = r13[r11]     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r8.beanInfo     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.util.FieldInfo[] r3 = r3.fields     // Catch:{ all -> 0x070f }
            r3 = r3[r11]     // Catch:{ all -> 0x070f }
            java.lang.Class r4 = java.lang.Byte.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x0591
            java.lang.Byte r12 = java.lang.Byte.valueOf(r28)     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x0591:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x059a
            java.lang.Short r12 = java.lang.Short.valueOf(r28)     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x059a:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x05a3
            java.lang.Integer r12 = java.lang.Integer.valueOf(r28)     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x05a3:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x05ac
            java.lang.Long r12 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x05ac:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x05b5
            java.lang.Float r12 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x05b5:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x05c0
            r16 = 0
            java.lang.Double r12 = java.lang.Double.valueOf(r16)     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x05c0:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x070f }
            if (r13 != r4) goto L_0x05c7
            java.lang.Boolean r12 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x070f }
            goto L_0x05d6
        L_0x05c7:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r13 != r4) goto L_0x05d6
            int r3 = r3.parserFeatures     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty     // Catch:{ all -> 0x070f }
            int r4 = r4.mask     // Catch:{ all -> 0x070f }
            r3 = r3 & r4
            if (r3 == 0) goto L_0x05d6
            java.lang.String r12 = ""
        L_0x05d6:
            r10[r11] = r12     // Catch:{ all -> 0x070f }
            int r11 = r11 + 1
            goto L_0x0571
        L_0x05db:
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r8.beanInfo     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.util.FieldInfo[] r3 = r3.fields     // Catch:{ all -> 0x070f }
            int r4 = r3.length     // Catch:{ all -> 0x070f }
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch:{ all -> 0x070f }
            r11 = 0
        L_0x05e3:
            if (r11 >= r4) goto L_0x0650
            r12 = r3[r11]     // Catch:{ all -> 0x070f }
            java.lang.String r13 = r12.name     // Catch:{ all -> 0x070f }
            java.lang.Object r13 = r0.get(r13)     // Catch:{ all -> 0x070f }
            if (r13 != 0) goto L_0x05f9
            java.lang.reflect.Type r5 = r12.fieldType     // Catch:{ all -> 0x070f }
            java.lang.Class r6 = java.lang.Byte.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r6) goto L_0x05fd
            java.lang.Byte r13 = java.lang.Byte.valueOf(r28)     // Catch:{ all -> 0x070f }
        L_0x05f9:
            r6 = 0
        L_0x05fa:
            r16 = 0
            goto L_0x0648
        L_0x05fd:
            java.lang.Class r6 = java.lang.Short.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r6) goto L_0x0606
            java.lang.Short r13 = java.lang.Short.valueOf(r28)     // Catch:{ all -> 0x070f }
            goto L_0x05f9
        L_0x0606:
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r6) goto L_0x060f
            java.lang.Integer r13 = java.lang.Integer.valueOf(r28)     // Catch:{ all -> 0x070f }
            goto L_0x05f9
        L_0x060f:
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r6) goto L_0x061a
            r6 = 0
            java.lang.Long r13 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x070f }
            goto L_0x05f9
        L_0x061a:
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r6) goto L_0x0624
            r6 = 0
            java.lang.Float r13 = java.lang.Float.valueOf(r6)     // Catch:{ all -> 0x070f }
            goto L_0x05fa
        L_0x0624:
            r6 = 0
            java.lang.Class r7 = java.lang.Double.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r7) goto L_0x0630
            r16 = 0
            java.lang.Double r13 = java.lang.Double.valueOf(r16)     // Catch:{ all -> 0x070f }
            goto L_0x0648
        L_0x0630:
            r16 = 0
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x070f }
            if (r5 != r7) goto L_0x0639
            java.lang.Boolean r13 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x070f }
            goto L_0x0648
        L_0x0639:
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r5 != r7) goto L_0x0648
            int r5 = r12.parserFeatures     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty     // Catch:{ all -> 0x070f }
            int r7 = r7.mask     // Catch:{ all -> 0x070f }
            r5 = r5 & r7
            if (r5 == 0) goto L_0x0648
            java.lang.String r13 = ""
        L_0x0648:
            r10[r11] = r13     // Catch:{ all -> 0x070f }
            int r11 = r11 + 1
            r5 = 0
            r6 = 0
            goto L_0x05e3
        L_0x0650:
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.reflect.Constructor<?> r3 = r3.creatorConstructor     // Catch:{ all -> 0x070f }
            if (r3 == 0) goto L_0x06b6
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r8.beanInfo     // Catch:{ Exception -> 0x068e }
            java.lang.reflect.Constructor<?> r3 = r3.creatorConstructor     // Catch:{ Exception -> 0x068e }
            java.lang.Object r3 = r3.newInstance(r10)     // Catch:{ Exception -> 0x068e }
            if (r2 == 0) goto L_0x068c
            java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x0688 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0688 }
        L_0x0668:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0688 }
            if (r1 == 0) goto L_0x068c
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0688 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0688 }
            java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x0688 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0688 }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r8.getFieldDeserializer((java.lang.String) r2)     // Catch:{ all -> 0x0688 }
            if (r2 == 0) goto L_0x0668
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0688 }
            r2.setValue((java.lang.Object) r3, (java.lang.Object) r1)     // Catch:{ all -> 0x0688 }
            goto L_0x0668
        L_0x0688:
            r0 = move-exception
            r1 = r3
            goto L_0x004c
        L_0x068c:
            r1 = r3
            goto L_0x06e7
        L_0x068e:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r3 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x070f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x070f }
            r4.<init>()     // Catch:{ all -> 0x070f }
            java.lang.String r5 = "create instance error, "
            r4.append(r5)     // Catch:{ all -> 0x070f }
            r4.append(r2)     // Catch:{ all -> 0x070f }
            java.lang.String r2 = ", "
            r4.append(r2)     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.reflect.Constructor<?> r2 = r2.creatorConstructor     // Catch:{ all -> 0x070f }
            java.lang.String r2 = r2.toGenericString()     // Catch:{ all -> 0x070f }
            r4.append(r2)     // Catch:{ all -> 0x070f }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x070f }
            r3.<init>(r2, r0)     // Catch:{ all -> 0x070f }
            throw r3     // Catch:{ all -> 0x070f }
        L_0x06b6:
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.reflect.Method r0 = r0.factoryMethod     // Catch:{ all -> 0x070f }
            if (r0 == 0) goto L_0x06e7
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r8.beanInfo     // Catch:{ Exception -> 0x06c7 }
            java.lang.reflect.Method r0 = r0.factoryMethod     // Catch:{ Exception -> 0x06c7 }
            r3 = 0
            java.lang.Object r0 = r0.invoke(r3, r10)     // Catch:{ Exception -> 0x06c7 }
            r1 = r0
            goto L_0x06e7
        L_0x06c7:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x070f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x070f }
            r3.<init>()     // Catch:{ all -> 0x070f }
            java.lang.String r4 = "create factory method error, "
            r3.append(r4)     // Catch:{ all -> 0x070f }
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.reflect.Method r4 = r4.factoryMethod     // Catch:{ all -> 0x070f }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x070f }
            r3.append(r4)     // Catch:{ all -> 0x070f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x070f }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x070f }
            throw r2     // Catch:{ all -> 0x070f }
        L_0x06e7:
            r14.object = r1     // Catch:{ all -> 0x070f }
        L_0x06e9:
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r8.beanInfo     // Catch:{ all -> 0x070f }
            java.lang.reflect.Method r0 = r0.buildMethod     // Catch:{ all -> 0x070f }
            if (r0 != 0) goto L_0x06f7
            if (r14 == 0) goto L_0x06f3
            r14.object = r1
        L_0x06f3:
            r9.setContext(r15)
            return r1
        L_0x06f7:
            r4 = 0
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0706 }
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch:{ Exception -> 0x0706 }
            if (r14 == 0) goto L_0x0702
            r14.object = r1
        L_0x0702:
            r9.setContext(r15)
            return r0
        L_0x0706:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x070f }
            java.lang.String r3 = "build object error"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x070f }
            throw r2     // Catch:{ all -> 0x070f }
        L_0x070f:
            r0 = move-exception
            goto L_0x004c
        L_0x0712:
            r3 = 0
            r4 = 0
            int r1 = r11.token()     // Catch:{ all -> 0x075f }
            r5 = 18
            if (r1 == r5) goto L_0x0738
            int r1 = r11.token()     // Catch:{ all -> 0x075f }
            r5 = 1
            if (r1 == r5) goto L_0x0738
        L_0x0723:
            r1 = r17
            r6 = r20
        L_0x0727:
            int r7 = r16 + 1
            r3 = r7
            r4 = r13
            r2 = r18
            r5 = 4
            r7 = 13
            r13 = 16
            r18 = r0
            r0 = r31
            goto L_0x012d
        L_0x0738:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x075f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x075f }
            r1.<init>()     // Catch:{ all -> 0x075f }
            java.lang.String r2 = "syntax error, unexpect token "
            r1.append(r2)     // Catch:{ all -> 0x075f }
            int r2 = r11.token()     // Catch:{ all -> 0x075f }
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)     // Catch:{ all -> 0x075f }
            r1.append(r2)     // Catch:{ all -> 0x075f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x075f }
            r0.<init>(r1)     // Catch:{ all -> 0x075f }
            throw r0     // Catch:{ all -> 0x075f }
        L_0x0757:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x075f }
            java.lang.String r1 = "syntax error, unexpect token ':'"
            r0.<init>(r1)     // Catch:{ all -> 0x075f }
            throw r0     // Catch:{ all -> 0x075f }
        L_0x075f:
            r0 = move-exception
            r1 = r17
        L_0x0762:
            r6 = r20
            goto L_0x076c
        L_0x0765:
            r0 = move-exception
            goto L_0x076c
        L_0x0767:
            r0 = move-exception
            r3 = r14
            r1 = r33
            r6 = r3
        L_0x076c:
            if (r6 == 0) goto L_0x0770
            r6.object = r1
        L_0x0770:
            r9.setContext(r15)
            throw r0
        L_0x0774:
            java.lang.Object r0 = r30.parse()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long scanFieldSymbol = jSONLexerBase.scanFieldSymbol(cArr);
        if (jSONLexerBase.matchStat > 0) {
            return enumDeserializer.getEnumByHashCode(scanFieldSymbol);
        }
        return null;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, (int[]) null);
    }

    /* JADX WARNING: type inference failed for: r17v0, types: [boolean] */
    /* JADX WARNING: type inference failed for: r17v1 */
    /* JADX WARNING: type inference failed for: r17v3 */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            r11 = r23
            r12 = r24
            r13 = r25
            r14 = r26
            r15 = r27
            com.alibaba.fastjson.parser.JSONLexer r10 = r0.lexer
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.DisableFieldSmartMatch
            int r2 = r2.mask
            boolean r3 = r10.isEnabled((int) r2)
            if (r3 != 0) goto L_0x0027
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r1.beanInfo
            int r3 = r3.parserFeatures
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.smartMatch(r11, r15)
            goto L_0x002b
        L_0x0027:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.getFieldDeserializer((java.lang.String) r11)
        L_0x002b:
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.SupportNonPublicField
            int r3 = r3.mask
            r16 = 0
            r9 = 1
            if (r2 != 0) goto L_0x0094
            boolean r4 = r10.isEnabled((int) r3)
            if (r4 != 0) goto L_0x0041
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r1.beanInfo
            int r4 = r4.parserFeatures
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0094
        L_0x0041:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            if (r3 != 0) goto L_0x0085
            java.util.concurrent.ConcurrentHashMap r3 = new java.util.concurrent.ConcurrentHashMap
            r4 = 1061158912(0x3f400000, float:0.75)
            r3.<init>(r9, r4, r9)
            java.lang.Class<?> r4 = r1.clazz
        L_0x004e:
            if (r4 == 0) goto L_0x0083
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            if (r4 == r5) goto L_0x0083
            java.lang.reflect.Field[] r5 = r4.getDeclaredFields()
            int r6 = r5.length
            r7 = 0
        L_0x005a:
            if (r7 >= r6) goto L_0x007d
            r8 = r5[r7]
            java.lang.String r9 = r8.getName()
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r18 = r1.getFieldDeserializer((java.lang.String) r9)
            if (r18 == 0) goto L_0x0069
            goto L_0x0079
        L_0x0069:
            int r18 = r8.getModifiers()
            r19 = r18 & 16
            if (r19 != 0) goto L_0x0079
            r18 = r18 & 8
            if (r18 == 0) goto L_0x0076
            goto L_0x0079
        L_0x0076:
            r3.put(r9, r8)
        L_0x0079:
            int r7 = r7 + 1
            r9 = 1
            goto L_0x005a
        L_0x007d:
            java.lang.Class r4 = r4.getSuperclass()
            r9 = 1
            goto L_0x004e
        L_0x0083:
            r1.extraFieldDeserializers = r3
        L_0x0085:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            java.lang.Object r3 = r3.get(r11)
            if (r3 == 0) goto L_0x0094
            boolean r2 = r3 instanceof com.alibaba.fastjson.parser.deserializer.FieldDeserializer
            if (r2 == 0) goto L_0x0098
            r2 = r3
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = (com.alibaba.fastjson.parser.deserializer.FieldDeserializer) r2
        L_0x0094:
            r15 = r10
            r17 = 1
            goto L_0x00d6
        L_0x0098:
            r7 = r3
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            r9 = 1
            r7.setAccessible(r9)
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.Class r4 = r7.getDeclaringClass()
            java.lang.Class r5 = r7.getType()
            java.lang.reflect.Type r6 = r7.getGenericType()
            r17 = 0
            r18 = 0
            r19 = 0
            r2 = r8
            r3 = r23
            r20 = r8
            r8 = r17
            r17 = 1
            r9 = r18
            r15 = r10
            r10 = r19
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r2 = new com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
            com.alibaba.fastjson.parser.ParserConfig r3 = r22.getConfig()
            java.lang.Class<?> r4 = r1.clazz
            r5 = r20
            r2.<init>(r3, r4, r5)
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            r3.put(r11, r2)
        L_0x00d6:
            if (r2 != 0) goto L_0x01b5
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.IgnoreNotMatch
            boolean r2 = r15.isEnabled((com.alibaba.fastjson.parser.Feature) r2)
            if (r2 == 0) goto L_0x0190
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r2 = r1.sortedFieldDeserializers
            int r3 = r2.length
            r4 = 0
        L_0x00e4:
            if (r4 >= r3) goto L_0x018c
            r5 = r2[r4]
            com.alibaba.fastjson.util.FieldInfo r6 = r5.fieldInfo
            boolean r7 = r6.unwrapped
            if (r7 == 0) goto L_0x0188
            boolean r7 = r5 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
            if (r7 == 0) goto L_0x0188
            java.lang.reflect.Field r7 = r6.field
            if (r7 == 0) goto L_0x0162
            r7 = r5
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r7 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r7
            com.alibaba.fastjson.parser.ParserConfig r8 = r22.getConfig()
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r8 = r7.getFieldValueDeserilizer(r8)
            boolean r9 = r8 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer
            if (r9 == 0) goto L_0x0135
            r9 = r8
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r9 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r9
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r9 = r9.getFieldDeserializer((java.lang.String) r11)
            if (r9 == 0) goto L_0x0188
            java.lang.reflect.Field r2 = r6.field     // Catch:{ Exception -> 0x012c }
            java.lang.Object r2 = r2.get(r12)     // Catch:{ Exception -> 0x012c }
            if (r2 != 0) goto L_0x0121
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r8 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r8     // Catch:{ Exception -> 0x012c }
            java.lang.reflect.Type r2 = r6.fieldType     // Catch:{ Exception -> 0x012c }
            java.lang.Object r2 = r8.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r0, (java.lang.reflect.Type) r2)     // Catch:{ Exception -> 0x012c }
            r5.setValue((java.lang.Object) r12, (java.lang.Object) r2)     // Catch:{ Exception -> 0x012c }
        L_0x0121:
            int r3 = r7.getFastMatchToken()     // Catch:{ Exception -> 0x012c }
            r15.nextTokenWithColon(r3)     // Catch:{ Exception -> 0x012c }
            r9.parseField(r0, r2, r13, r14)     // Catch:{ Exception -> 0x012c }
            return r17
        L_0x012c:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.String r3 = "parse unwrapped field error."
            r2.<init>(r3, r0)
            throw r2
        L_0x0135:
            boolean r7 = r8 instanceof com.alibaba.fastjson.parser.deserializer.MapDeserializer
            if (r7 == 0) goto L_0x0188
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r8 = (com.alibaba.fastjson.parser.deserializer.MapDeserializer) r8
            java.lang.reflect.Field r2 = r6.field     // Catch:{ Exception -> 0x0159 }
            java.lang.Object r2 = r2.get(r12)     // Catch:{ Exception -> 0x0159 }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ Exception -> 0x0159 }
            if (r2 != 0) goto L_0x014e
            java.lang.reflect.Type r2 = r6.fieldType     // Catch:{ Exception -> 0x0159 }
            java.util.Map r2 = r8.createMap(r2)     // Catch:{ Exception -> 0x0159 }
            r5.setValue((java.lang.Object) r12, (java.lang.Object) r2)     // Catch:{ Exception -> 0x0159 }
        L_0x014e:
            r15.nextTokenWithColon()     // Catch:{ Exception -> 0x0159 }
            java.lang.Object r0 = r22.parse(r23)     // Catch:{ Exception -> 0x0159 }
            r2.put(r11, r0)     // Catch:{ Exception -> 0x0159 }
            return r17
        L_0x0159:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.String r3 = "parse unwrapped field error."
            r2.<init>(r3, r0)
            throw r2
        L_0x0162:
            java.lang.reflect.Method r5 = r6.method
            java.lang.Class[] r5 = r5.getParameterTypes()
            int r5 = r5.length
            r7 = 2
            if (r5 != r7) goto L_0x0188
            r15.nextTokenWithColon()
            java.lang.Object r0 = r22.parse(r23)
            java.lang.reflect.Method r2 = r6.method     // Catch:{ Exception -> 0x017f }
            java.lang.Object[] r3 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x017f }
            r3[r16] = r11     // Catch:{ Exception -> 0x017f }
            r3[r17] = r0     // Catch:{ Exception -> 0x017f }
            r2.invoke(r12, r3)     // Catch:{ Exception -> 0x017f }
            return r17
        L_0x017f:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.String r3 = "parse unwrapped field error."
            r2.<init>(r3, r0)
            throw r2
        L_0x0188:
            int r4 = r4 + 1
            goto L_0x00e4
        L_0x018c:
            r0.parseExtra(r12, r11)
            return r16
        L_0x0190:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "setter not found, class "
            r2.append(r3)
            java.lang.Class<?> r3 = r1.clazz
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r3 = ", property "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x01b5:
            r3 = 0
        L_0x01b6:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r1.sortedFieldDeserializers
            int r4 = r4.length
            r5 = -1
            if (r3 >= r4) goto L_0x01c6
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r1.sortedFieldDeserializers
            r4 = r4[r3]
            if (r4 != r2) goto L_0x01c3
            goto L_0x01c7
        L_0x01c3:
            int r3 = r3 + 1
            goto L_0x01b6
        L_0x01c6:
            r3 = -1
        L_0x01c7:
            if (r3 == r5) goto L_0x01e0
            r5 = r15
            r4 = r27
            if (r4 == 0) goto L_0x01e1
            java.lang.String r6 = "_"
            boolean r6 = r11.startsWith(r6)
            if (r6 == 0) goto L_0x01e1
            boolean r3 = isSetFlag(r3, r4)
            if (r3 == 0) goto L_0x01e1
            r0.parseExtra(r12, r11)
            return r16
        L_0x01e0:
            r5 = r15
        L_0x01e1:
            int r3 = r2.getFastMatchToken()
            r5.nextTokenWithColon(r3)
            r2.parseField(r0, r12, r13, r14)
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, (int[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alibaba.fastjson.parser.deserializer.FieldDeserializer smartMatch(java.lang.String r10, int[] r11) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r1 = r9.getFieldDeserializer(r10, r11)
            if (r1 != 0) goto L_0x00b3
            long r2 = com.alibaba.fastjson.util.TypeUtils.fnv1a_64_lower(r10)
            long[] r4 = r9.smartMatchHashArray
            r5 = 0
            if (r4 != 0) goto L_0x0034
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r9.sortedFieldDeserializers
            int r4 = r4.length
            long[] r4 = new long[r4]
            r6 = 0
        L_0x0019:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r7 = r9.sortedFieldDeserializers
            int r7 = r7.length
            if (r6 >= r7) goto L_0x002f
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r7 = r9.sortedFieldDeserializers
            r7 = r7[r6]
            com.alibaba.fastjson.util.FieldInfo r7 = r7.fieldInfo
            java.lang.String r7 = r7.name
            long r7 = com.alibaba.fastjson.util.TypeUtils.fnv1a_64_lower(r7)
            r4[r6] = r7
            int r6 = r6 + 1
            goto L_0x0019
        L_0x002f:
            java.util.Arrays.sort(r4)
            r9.smartMatchHashArray = r4
        L_0x0034:
            long[] r4 = r9.smartMatchHashArray
            int r2 = java.util.Arrays.binarySearch(r4, r2)
            if (r2 >= 0) goto L_0x0054
            java.lang.String r3 = "is"
            boolean r3 = r10.startsWith(r3)
            if (r3 == 0) goto L_0x0055
            r2 = 2
            java.lang.String r10 = r10.substring(r2)
            long r6 = com.alibaba.fastjson.util.TypeUtils.fnv1a_64_lower(r10)
            long[] r10 = r9.smartMatchHashArray
            int r2 = java.util.Arrays.binarySearch(r10, r6)
            goto L_0x0055
        L_0x0054:
            r3 = 0
        L_0x0055:
            if (r2 < 0) goto L_0x0096
            short[] r10 = r9.smartMatchHashArrayMapping
            r4 = -1
            if (r10 != 0) goto L_0x0085
            long[] r10 = r9.smartMatchHashArray
            int r10 = r10.length
            short[] r10 = new short[r10]
            java.util.Arrays.fill(r10, r4)
        L_0x0064:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r6 = r9.sortedFieldDeserializers
            int r6 = r6.length
            if (r5 >= r6) goto L_0x0083
            long[] r6 = r9.smartMatchHashArray
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r7 = r9.sortedFieldDeserializers
            r7 = r7[r5]
            com.alibaba.fastjson.util.FieldInfo r7 = r7.fieldInfo
            java.lang.String r7 = r7.name
            long r7 = com.alibaba.fastjson.util.TypeUtils.fnv1a_64_lower(r7)
            int r6 = java.util.Arrays.binarySearch(r6, r7)
            if (r6 < 0) goto L_0x0080
            short r7 = (short) r5
            r10[r6] = r7
        L_0x0080:
            int r5 = r5 + 1
            goto L_0x0064
        L_0x0083:
            r9.smartMatchHashArrayMapping = r10
        L_0x0085:
            short[] r10 = r9.smartMatchHashArrayMapping
            short r10 = r10[r2]
            if (r10 == r4) goto L_0x0096
            boolean r11 = isSetFlag(r10, r11)
            if (r11 != 0) goto L_0x0096
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r11 = r9.sortedFieldDeserializers
            r10 = r11[r10]
            goto L_0x0097
        L_0x0096:
            r10 = r1
        L_0x0097:
            if (r10 == 0) goto L_0x00b4
            com.alibaba.fastjson.util.FieldInfo r11 = r10.fieldInfo
            int r1 = r11.parserFeatures
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.DisableFieldSmartMatch
            int r2 = r2.mask
            r1 = r1 & r2
            if (r1 == 0) goto L_0x00a5
            return r0
        L_0x00a5:
            java.lang.Class<?> r11 = r11.fieldClass
            if (r3 == 0) goto L_0x00b4
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r11 == r1) goto L_0x00b4
            java.lang.Class<java.lang.Boolean> r1 = java.lang.Boolean.class
            if (r11 == r1) goto L_0x00b4
            r10 = r0
            goto L_0x00b4
        L_0x00b3:
            r10 = r1
        L_0x00b4:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.smartMatch(java.lang.String, int[]):com.alibaba.fastjson.parser.deserializer.FieldDeserializer");
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Integer num;
        Object obj;
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.factoryMethod == null) {
            Object createInstance = createInstance((DefaultJSONParser) null, (Type) this.clazz);
            for (Map.Entry next : map.entrySet()) {
                Object value = next.getValue();
                FieldDeserializer smartMatch = smartMatch((String) next.getKey());
                if (smartMatch != null) {
                    FieldInfo fieldInfo = smartMatch.fieldInfo;
                    Type type = fieldInfo.fieldType;
                    String str = fieldInfo.format;
                    if (str == null || type != Date.class) {
                        obj = TypeUtils.cast(value, type, parserConfig);
                    } else {
                        obj = TypeUtils.castToDate(value, str);
                    }
                    smartMatch.setValue(createInstance, obj);
                }
            }
            if (this.beanInfo.buildMethod == null) {
                return createInstance;
            }
            try {
                return this.beanInfo.buildMethod.invoke(createInstance, new Object[0]);
            } catch (Exception e) {
                throw new JSONException("build object error", e);
            }
        } else {
            FieldInfo[] fieldInfoArr = this.beanInfo.fields;
            int length = fieldInfoArr.length;
            Object[] objArr = new Object[length];
            HashMap hashMap = null;
            for (int i = 0; i < length; i++) {
                FieldInfo fieldInfo2 = fieldInfoArr[i];
                boolean z = map.get(fieldInfo2.name);
                if (z == null) {
                    Class<?> cls = fieldInfo2.fieldClass;
                    if (cls == Integer.TYPE) {
                        z = 0;
                    } else if (cls == Long.TYPE) {
                        z = 0L;
                    } else if (cls == Short.TYPE) {
                        z = (short) 0;
                    } else if (cls == Byte.TYPE) {
                        z = (byte) 0;
                    } else if (cls == Float.TYPE) {
                        z = Float.valueOf(0.0f);
                    } else if (cls == Double.TYPE) {
                        z = Double.valueOf(0.0d);
                    } else if (cls == Character.TYPE) {
                        z = '0';
                    } else if (cls == Boolean.TYPE) {
                        z = false;
                    }
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(fieldInfo2.name, Integer.valueOf(i));
                }
                objArr[i] = z;
            }
            if (hashMap != null) {
                for (Map.Entry next2 : map.entrySet()) {
                    Object value2 = next2.getValue();
                    FieldDeserializer smartMatch2 = smartMatch((String) next2.getKey());
                    if (!(smartMatch2 == null || (num = (Integer) hashMap.get(smartMatch2.fieldInfo.name)) == null)) {
                        objArr[num.intValue()] = value2;
                    }
                }
            }
            if (this.beanInfo.creatorConstructor != null) {
                try {
                    return this.beanInfo.creatorConstructor.newInstance(objArr);
                } catch (Exception e2) {
                    throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e2);
                }
            } else if (this.beanInfo.factoryMethod == null) {
                return null;
            } else {
                try {
                    return this.beanInfo.factoryMethod.invoke((Object) null, objArr);
                } catch (Exception e3) {
                    throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e3);
                }
            }
        }
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    /* access modifiers changed from: protected */
    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    /* access modifiers changed from: protected */
    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    /* access modifiers changed from: protected */
    public JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class deserializer : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer2 = parserConfig.getDeserializer((Type) deserializer);
            if (deserializer2 instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer2;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            } else if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
            return;
        }
        jSONLexerBase.nextToken(16);
    }
}
