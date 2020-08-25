package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, (PropertyNamingStrategy) null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        for (int i = 0; i < this.sortedGetters.length; i++) {
            this.sortedGetters[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
            return;
        }
        this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
        for (int i2 = 0; i2 < this.getters.length; i2++) {
            this.getters[i2] = getFieldSerializer(serializeBeanInfo.fields[i2].name);
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0149 A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x014a A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x03c0 A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x03e5 A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0405 A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x0406 A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x041f A[Catch:{ InvocationTargetException -> 0x0138, Exception -> 0x042d, all -> 0x0429 }] */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0434 A[SYNTHETIC, Splitter:B:334:0x0434] */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0455 A[Catch:{ all -> 0x048d }] */
    /* JADX WARNING: Removed duplicated region for block: B:341:0x046f A[Catch:{ all -> 0x048d }] */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x03e9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b0 A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b3 A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00bc A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bf A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c7 A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c9 A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00dc A[Catch:{ Exception -> 0x0062, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0132 A[SYNTHETIC, Splitter:B:94:0x0132] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r29, java.lang.Object r30, java.lang.Object r31, java.lang.reflect.Type r32, int r33, boolean r34) throws java.io.IOException {
        /*
            r28 = this;
            r7 = r28
            r8 = r29
            r9 = r30
            r10 = r31
            r11 = r32
            r0 = r33
            com.alibaba.fastjson.serializer.SerializeWriter r13 = r8.out
            if (r9 != 0) goto L_0x0014
            r13.writeNull()
            return
        L_0x0014:
            boolean r1 = r7.writeReference(r8, r9, r0)
            if (r1 == 0) goto L_0x001b
            return
        L_0x001b:
            boolean r1 = r13.sortField
            if (r1 == 0) goto L_0x0023
            com.alibaba.fastjson.serializer.FieldSerializer[] r1 = r7.sortedGetters
        L_0x0021:
            r14 = r1
            goto L_0x0026
        L_0x0023:
            com.alibaba.fastjson.serializer.FieldSerializer[] r1 = r7.getters
            goto L_0x0021
        L_0x0026:
            com.alibaba.fastjson.serializer.SerialContext r15 = r8.context
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r7.beanInfo
            java.lang.Class<?> r1 = r1.beanType
            boolean r1 = r1.isEnum()
            if (r1 != 0) goto L_0x0042
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r7.beanInfo
            int r5 = r1.features
            r1 = r29
            r2 = r15
            r3 = r30
            r4 = r31
            r6 = r33
            r1.setContext(r2, r3, r4, r5, r6)
        L_0x0042:
            boolean r16 = r7.isWriteAsArray(r8, r0)
            if (r16 == 0) goto L_0x004b
            r1 = 91
            goto L_0x004d
        L_0x004b:
            r1 = 123(0x7b, float:1.72E-43)
        L_0x004d:
            if (r16 == 0) goto L_0x0054
            r2 = 93
            r6 = 93
            goto L_0x0058
        L_0x0054:
            r2 = 125(0x7d, float:1.75E-43)
            r6 = 125(0x7d, float:1.75E-43)
        L_0x0058:
            if (r34 != 0) goto L_0x0066
            r13.append((char) r1)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            goto L_0x0066
        L_0x005e:
            r0 = move-exception
            r1 = r15
            goto L_0x048e
        L_0x0062:
            r0 = move-exception
            r1 = r15
            goto L_0x0430
        L_0x0066:
            int r1 = r14.length     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r1 <= 0) goto L_0x0077
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            boolean r1 = r13.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r1 == 0) goto L_0x0077
            r29.incrementIndent()     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r29.println()     // Catch:{ Exception -> 0x0062, all -> 0x005e }
        L_0x0077:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r7.beanInfo     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            int r1 = r1.features     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            int r2 = r2.mask     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r1 = r1 & r2
            r5 = 1
            if (r1 != 0) goto L_0x0090
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            int r1 = r1.mask     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0090
            boolean r0 = r8.isWriteClassName(r11, r9)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r0 == 0) goto L_0x00ab
        L_0x0090:
            java.lang.Class r0 = r30.getClass()     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r0 == r11) goto L_0x009f
            boolean r1 = r11 instanceof java.lang.reflect.WildcardType     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r1 == 0) goto L_0x009f
            java.lang.Class r1 = com.alibaba.fastjson.util.TypeUtils.getClass(r32)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            goto L_0x00a0
        L_0x009f:
            r1 = r11
        L_0x00a0:
            if (r0 == r1) goto L_0x00ab
            com.alibaba.fastjson.serializer.SerializeBeanInfo r0 = r7.beanInfo     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            java.lang.String r0 = r0.typeKey     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r7.writeClassName(r8, r0, r9)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r0 = 1
            goto L_0x00ac
        L_0x00ab:
            r0 = 0
        L_0x00ac:
            r3 = 44
            if (r0 == 0) goto L_0x00b3
            r0 = 44
            goto L_0x00b4
        L_0x00b3:
            r0 = 0
        L_0x00b4:
            boolean r1 = r13.quoteFieldNames     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r1 == 0) goto L_0x00bf
            boolean r1 = r13.useSingleQuotes     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r1 != 0) goto L_0x00bf
            r17 = 1
            goto L_0x00c1
        L_0x00bf:
            r17 = 0
        L_0x00c1:
            char r0 = r7.writeBefore(r8, r9, r0)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r0 != r3) goto L_0x00c9
            r0 = 1
            goto L_0x00ca
        L_0x00c9:
            r0 = 0
        L_0x00ca:
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.SkipTransientField     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            boolean r18 = r13.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.IgnoreNonFieldGetter     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            boolean r19 = r13.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r20 = r0
            r2 = 0
        L_0x00d9:
            int r0 = r14.length     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            if (r2 >= r0) goto L_0x03fa
            r1 = r14[r2]     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            com.alibaba.fastjson.util.FieldInfo r0 = r1.fieldInfo     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            java.lang.reflect.Field r0 = r0.field     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            com.alibaba.fastjson.util.FieldInfo r10 = r1.fieldInfo     // Catch:{ Exception -> 0x0062, all -> 0x005e }
            r21 = r15
            java.lang.String r15 = r10.name     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.Class<?> r12 = r10.fieldClass     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r18 == 0) goto L_0x00f3
            if (r0 == 0) goto L_0x00f3
            boolean r3 = r10.fieldTransient     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x00f3
            goto L_0x00f7
        L_0x00f3:
            if (r19 == 0) goto L_0x0103
            if (r0 != 0) goto L_0x0103
        L_0x00f7:
            r23 = r2
            r27 = r6
            r24 = r14
            r3 = 1
            r4 = 0
            r14 = 44
            goto L_0x03e9
        L_0x0103:
            boolean r0 = r7.applyName(r8, r9, r15)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x0114
            java.lang.String r0 = r10.label     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r0 = r7.applyLabel(r8, r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 != 0) goto L_0x0112
            goto L_0x0114
        L_0x0112:
            r0 = 0
            goto L_0x0117
        L_0x0114:
            if (r16 == 0) goto L_0x00f7
            r0 = 1
        L_0x0117:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r3 = r7.beanInfo     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.String r3 = r3.typeKey     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x012e
            com.alibaba.fastjson.serializer.SerializeBeanInfo r3 = r7.beanInfo     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.String r3 = r3.typeKey     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r3 = r15.equals(r3)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x012e
            boolean r3 = r8.isWriteClassName(r11, r9)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x012e
            goto L_0x00f7
        L_0x012e:
            r3 = 0
            if (r0 == 0) goto L_0x0132
            goto L_0x0143
        L_0x0132:
            java.lang.Object r0 = r1.getPropertyValueDirect(r9)     // Catch:{ InvocationTargetException -> 0x0138 }
            r3 = r0
            goto L_0x0143
        L_0x0138:
            r0 = move-exception
            r23 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.IgnoreErrorGetter     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r0 = r13.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x03e8
        L_0x0143:
            boolean r0 = r7.apply(r8, r9, r15, r3)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 != 0) goto L_0x014a
            goto L_0x00f7
        L_0x014a:
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            if (r12 != r0) goto L_0x0161
            java.lang.String r0 = "trim"
            java.lang.String r4 = r10.format     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x0161
            if (r3 == 0) goto L_0x0161
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.String r0 = r3.trim()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x0162
        L_0x0161:
            r0 = r3
        L_0x0162:
            java.lang.String r4 = r7.processKey(r8, r9, r15, r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.BeanContext r3 = r1.fieldContext     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r11 = r1
            r1 = r28
            r23 = r2
            r2 = r29
            r24 = r14
            r14 = 44
            r25 = r4
            r26 = 0
            r4 = r30
            r5 = r15
            r27 = r6
            r6 = r0
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r1 != 0) goto L_0x024e
            int r2 = r10.serialzeFeatures     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializeBeanInfo r3 = r7.beanInfo     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.annotation.JSONType r3 = r3.jsonType     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x0198
            com.alibaba.fastjson.serializer.SerializeBeanInfo r3 = r7.beanInfo     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.annotation.JSONType r3 = r3.jsonType     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r3.serialzeFeatures()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 | r3
        L_0x0198:
            java.lang.Class<java.lang.Boolean> r3 = java.lang.Boolean.class
            if (r12 != r3) goto L_0x01c0
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r4 = r4.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 | r3
            if (r16 != 0) goto L_0x01b2
            r5 = r2 & r4
            if (r5 != 0) goto L_0x01b2
            int r5 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 & r5
            if (r4 != 0) goto L_0x01b2
            goto L_0x0309
        L_0x01b2:
            r2 = r2 & r3
            if (r2 != 0) goto L_0x01ba
            int r2 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 & r3
            if (r2 == 0) goto L_0x024e
        L_0x01ba:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r26)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x024e
        L_0x01c0:
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r12 != r3) goto L_0x01e6
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r4 = r4.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 | r3
            if (r16 != 0) goto L_0x01da
            r5 = r2 & r4
            if (r5 != 0) goto L_0x01da
            int r5 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 & r5
            if (r4 != 0) goto L_0x01da
            goto L_0x0309
        L_0x01da:
            r2 = r2 & r3
            if (r2 != 0) goto L_0x01e2
            int r2 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 & r3
            if (r2 == 0) goto L_0x024e
        L_0x01e2:
            java.lang.String r1 = ""
            goto L_0x024e
        L_0x01e6:
            java.lang.Class<java.lang.Number> r3 = java.lang.Number.class
            boolean r3 = r3.isAssignableFrom(r12)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x0211
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r4 = r4.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 | r3
            if (r16 != 0) goto L_0x0204
            r5 = r2 & r4
            if (r5 != 0) goto L_0x0204
            int r5 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 & r5
            if (r4 != 0) goto L_0x0204
            goto L_0x0309
        L_0x0204:
            r2 = r2 & r3
            if (r2 != 0) goto L_0x020c
            int r2 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 & r3
            if (r2 == 0) goto L_0x024e
        L_0x020c:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r26)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x024e
        L_0x0211:
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            boolean r3 = r3.isAssignableFrom(r12)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x023c
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r4 = r4.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 | r3
            if (r16 != 0) goto L_0x022f
            r5 = r2 & r4
            if (r5 != 0) goto L_0x022f
            int r5 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = r4 & r5
            if (r4 != 0) goto L_0x022f
            goto L_0x0309
        L_0x022f:
            r2 = r2 & r3
            if (r2 != 0) goto L_0x0237
            int r2 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 & r3
            if (r2 == 0) goto L_0x024e
        L_0x0237:
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x024e
        L_0x023c:
            if (r16 != 0) goto L_0x024e
            boolean r2 = r11.writeNull     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != 0) goto L_0x024e
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r2 = r2.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r2 = r13.isEnabled((int) r2)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != 0) goto L_0x024e
            goto L_0x0309
        L_0x024e:
            if (r1 == 0) goto L_0x02f6
            boolean r2 = r13.notWriteDefaultValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != 0) goto L_0x0268
            int r2 = r10.serialzeFeatures     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 & r3
            if (r2 != 0) goto L_0x0268
            com.alibaba.fastjson.serializer.SerializeBeanInfo r2 = r7.beanInfo     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r2 = r2.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r2 = r2 & r3
            if (r2 == 0) goto L_0x02f6
        L_0x0268:
            java.lang.Class<?> r2 = r10.fieldClass     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.Class r3 = java.lang.Byte.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x027d
            boolean r3 = r1 instanceof java.lang.Byte     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x027d
            r3 = r1
            java.lang.Byte r3 = (java.lang.Byte) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            byte r3 = r3.byteValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 != 0) goto L_0x027d
            goto L_0x0309
        L_0x027d:
            java.lang.Class r3 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x0290
            boolean r3 = r1 instanceof java.lang.Short     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x0290
            r3 = r1
            java.lang.Short r3 = (java.lang.Short) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            short r3 = r3.shortValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 != 0) goto L_0x0290
            goto L_0x0309
        L_0x0290:
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x02a3
            boolean r3 = r1 instanceof java.lang.Integer     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x02a3
            r3 = r1
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 != 0) goto L_0x02a3
        L_0x02a1:
            goto L_0x0309
        L_0x02a3:
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x02b9
            boolean r3 = r1 instanceof java.lang.Long     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x02b9
            r3 = r1
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            long r3 = r3.longValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r5 = 0
            int r22 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r22 != 0) goto L_0x02b9
            goto L_0x0309
        L_0x02b9:
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x02ce
            boolean r3 = r1 instanceof java.lang.Float     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x02ce
            r3 = r1
            java.lang.Float r3 = (java.lang.Float) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            float r3 = r3.floatValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x02ce
            goto L_0x02a1
        L_0x02ce:
            java.lang.Class r3 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x02e4
            boolean r3 = r1 instanceof java.lang.Double     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r3 == 0) goto L_0x02e4
            r3 = r1
            java.lang.Double r3 = (java.lang.Double) r3     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            double r3 = r3.doubleValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r5 = 0
            int r22 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r22 != 0) goto L_0x02e4
            goto L_0x0309
        L_0x02e4:
            java.lang.Class r3 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != r3) goto L_0x02f6
            boolean r2 = r1 instanceof java.lang.Boolean     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 == 0) goto L_0x02f6
            r2 = r1
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != 0) goto L_0x02f6
            goto L_0x0309
        L_0x02f6:
            if (r20 == 0) goto L_0x031b
            boolean r2 = r10.unwrapped     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 == 0) goto L_0x030d
            boolean r2 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 == 0) goto L_0x030d
            r2 = r1
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r2 = r2.size()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 != 0) goto L_0x030d
        L_0x0309:
            r3 = 1
            r4 = 0
            goto L_0x03e9
        L_0x030d:
            r13.write((int) r14)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r2 = r13.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 == 0) goto L_0x031b
            r29.println()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x031b:
            r2 = r25
            if (r2 == r15) goto L_0x032d
            if (r16 != 0) goto L_0x0326
            r3 = 1
            r13.writeFieldName(r2, r3)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x0327
        L_0x0326:
            r3 = 1
        L_0x0327:
            r8.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x032a:
            r4 = 0
            goto L_0x03ae
        L_0x032d:
            r3 = 1
            if (r0 == r1) goto L_0x0339
            if (r16 != 0) goto L_0x0335
            r11.writePrefix(r8)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x0335:
            r8.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x032a
        L_0x0339:
            if (r16 != 0) goto L_0x0350
            boolean r0 = r10.unwrapped     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 != 0) goto L_0x0350
            if (r17 == 0) goto L_0x034b
            char[] r0 = r10.name_chars     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            char[] r2 = r10.name_chars     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r2 = r2.length     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r4 = 0
            r13.write((char[]) r0, (int) r4, (int) r2)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x0351
        L_0x034b:
            r4 = 0
            r11.writePrefix(r8)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x0351
        L_0x0350:
            r4 = 0
        L_0x0351:
            if (r16 != 0) goto L_0x03ab
            com.alibaba.fastjson.annotation.JSONField r0 = r10.getAnnotation()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            if (r12 != r2) goto L_0x0393
            if (r0 == 0) goto L_0x0365
            java.lang.Class r0 = r0.serializeUsing()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.lang.Class<java.lang.Void> r2 = java.lang.Void.class
            if (r0 != r2) goto L_0x0393
        L_0x0365:
            if (r1 != 0) goto L_0x0384
            int r0 = r13.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r2 = r2.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r0 = r0 & r2
            if (r0 != 0) goto L_0x037e
            int r0 = r11.features     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r2 = r2.mask     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r0 = r0 & r2
            if (r0 == 0) goto L_0x037a
            goto L_0x037e
        L_0x037a:
            r13.writeNull()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x03ae
        L_0x037e:
            java.lang.String r0 = ""
            r13.writeString((java.lang.String) r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x03ae
        L_0x0384:
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r2 = r13.useSingleQuotes     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r2 == 0) goto L_0x038f
            r13.writeStringWithSingleQuote((java.lang.String) r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x03ae
        L_0x038f:
            r13.writeStringWithDoubleQuote((java.lang.String) r0, (char) r4)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x03ae
        L_0x0393:
            boolean r0 = r10.unwrapped     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x03a7
            boolean r0 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x03a7
            r0 = r1
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r0 = r0.size()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 != 0) goto L_0x03a7
            r20 = 0
            goto L_0x03e9
        L_0x03a7:
            r11.writeValue(r8, r1)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            goto L_0x03ae
        L_0x03ab:
            r11.writeValue(r8, r1)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x03ae:
            boolean r0 = r10.unwrapped     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x03e2
            boolean r0 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x03e2
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            int r0 = r1.size()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 != 0) goto L_0x03c0
        L_0x03be:
            r0 = 1
            goto L_0x03e3
        L_0x03c0:
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r0 = r8.isEnabled(r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 != 0) goto L_0x03e2
            java.util.Collection r0 = r1.values()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x03d0:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r1 == 0) goto L_0x03de
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r1 == 0) goto L_0x03d0
            r0 = 1
            goto L_0x03df
        L_0x03de:
            r0 = 0
        L_0x03df:
            if (r0 != 0) goto L_0x03e2
            goto L_0x03be
        L_0x03e2:
            r0 = 0
        L_0x03e3:
            if (r0 != 0) goto L_0x03e9
            r20 = 1
            goto L_0x03e9
        L_0x03e8:
            throw r23     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x03e9:
            int r2 = r23 + 1
            r15 = r21
            r14 = r24
            r6 = r27
            r3 = 44
            r5 = 1
            r10 = r31
            r11 = r32
            goto L_0x00d9
        L_0x03fa:
            r27 = r6
            r24 = r14
            r21 = r15
            r4 = 0
            r14 = 44
            if (r20 == 0) goto L_0x0406
            goto L_0x0407
        L_0x0406:
            r14 = 0
        L_0x0407:
            r7.writeAfter(r8, r9, r14)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r1 = r24
            int r0 = r1.length     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 <= 0) goto L_0x041d
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            boolean r0 = r13.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r0)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            if (r0 == 0) goto L_0x041d
            r29.decrementIdent()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
            r29.println()     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x041d:
            if (r34 != 0) goto L_0x0424
            r2 = r27
            r13.append((char) r2)     // Catch:{ Exception -> 0x042d, all -> 0x0429 }
        L_0x0424:
            r1 = r21
            r8.context = r1
            return
        L_0x0429:
            r0 = move-exception
            r1 = r21
            goto L_0x048e
        L_0x042d:
            r0 = move-exception
            r1 = r21
        L_0x0430:
            java.lang.String r2 = "write javaBean error, fastjson version 1.2.46"
            if (r9 == 0) goto L_0x0450
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x048d }
            r3.<init>()     // Catch:{ all -> 0x048d }
            r3.append(r2)     // Catch:{ all -> 0x048d }
            java.lang.String r2 = ", class "
            r3.append(r2)     // Catch:{ all -> 0x048d }
            java.lang.Class r2 = r30.getClass()     // Catch:{ all -> 0x048d }
            java.lang.String r2 = r2.getName()     // Catch:{ all -> 0x048d }
            r3.append(r2)     // Catch:{ all -> 0x048d }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x048d }
        L_0x0450:
            r3 = r2
            r2 = r31
            if (r2 == 0) goto L_0x0469
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x048d }
            r4.<init>()     // Catch:{ all -> 0x048d }
            r4.append(r3)     // Catch:{ all -> 0x048d }
            java.lang.String r3 = ", fieldName : "
            r4.append(r3)     // Catch:{ all -> 0x048d }
            r4.append(r2)     // Catch:{ all -> 0x048d }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x048d }
        L_0x0469:
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x048d }
            if (r2 == 0) goto L_0x0487
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x048d }
            r2.<init>()     // Catch:{ all -> 0x048d }
            r2.append(r3)     // Catch:{ all -> 0x048d }
            java.lang.String r3 = ", "
            r2.append(r3)     // Catch:{ all -> 0x048d }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x048d }
            r2.append(r3)     // Catch:{ all -> 0x048d }
            java.lang.String r3 = r2.toString()     // Catch:{ all -> 0x048d }
        L_0x0487:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x048d }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x048d }
            throw r2     // Catch:{ all -> 0x048d }
        L_0x048d:
            r0 = move-exception
        L_0x048e:
            r8.context = r1
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String str2 = this.beanInfo.typeName;
        if (str2 == null) {
            Class cls = obj.getClass();
            if (TypeUtils.isProxy(cls)) {
                cls = cls.getSuperclass();
            }
            str2 = cls.getName();
        }
        jSONSerializer.write(str2);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    /* access modifiers changed from: protected */
    public boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer != null) {
            try {
                return fieldSerializer.getPropertyValue(obj);
            } catch (InvocationTargetException e) {
                throw new JSONException("getFieldValue error." + str, e);
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            }
        } else {
            throw new JSONException("field not found. " + str);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer != null) {
            try {
                return fieldSerializer.getPropertyValue(obj);
            } catch (InvocationTargetException e) {
                throw new JSONException("getFieldValue error." + str, e);
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            }
        } else if (!z) {
            return null;
        } else {
            throw new JSONException("field not found. " + str);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo <= 0) {
                return this.sortedGetters[i2];
            } else {
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArr;
        int binarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArr = PropertyNamingStrategy.values();
            long[] jArr = new long[(this.sortedGetters.length * propertyNamingStrategyArr.length)];
            int i = 0;
            int i2 = 0;
            while (i < this.sortedGetters.length) {
                String str = this.sortedGetters[i].fieldInfo.name;
                int i3 = i2 + 1;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                for (PropertyNamingStrategy translate : propertyNamingStrategyArr) {
                    String translate2 = translate.translate(str);
                    if (!str.equals(translate2)) {
                        jArr[i3] = TypeUtils.fnv1a_64(translate2);
                        i3++;
                    }
                }
                i++;
                i2 = i3;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArr = null;
        }
        int binarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArr == null) {
                propertyNamingStrategyArr = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, -1);
            for (int i4 = 0; i4 < this.sortedGetters.length; i4++) {
                String str2 = this.sortedGetters[i4].fieldInfo.name;
                int binarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (binarySearch3 >= 0) {
                    sArr[binarySearch3] = (short) i4;
                }
                for (PropertyNamingStrategy translate3 : propertyNamingStrategyArr) {
                    String translate4 = translate3.translate(str2);
                    if (!str2.equals(translate4) && (binarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(translate4))) >= 0) {
                        sArr[binarySearch] = (short) i4;
                    }
                }
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer propertyValue : this.sortedGetters) {
            arrayList.add(propertyValue.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer propertyValueDirect : this.sortedGetters) {
            if (propertyValueDirect.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    public BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    /* access modifiers changed from: protected */
    public Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    /* access modifiers changed from: protected */
    public char writeBefore(JSONSerializer jSONSerializer, Object obj, char c2) {
        if (jSONSerializer.beforeFilters != null) {
            for (BeforeFilter writeBefore : jSONSerializer.beforeFilters) {
                c2 = writeBefore.writeBefore(jSONSerializer, obj, c2);
            }
        }
        if (this.beforeFilters != null) {
            for (BeforeFilter writeBefore2 : this.beforeFilters) {
                c2 = writeBefore2.writeBefore(jSONSerializer, obj, c2);
            }
        }
        return c2;
    }

    /* access modifiers changed from: protected */
    public char writeAfter(JSONSerializer jSONSerializer, Object obj, char c2) {
        if (jSONSerializer.afterFilters != null) {
            for (AfterFilter writeAfter : jSONSerializer.afterFilters) {
                c2 = writeAfter.writeAfter(jSONSerializer, obj, c2);
            }
        }
        if (this.afterFilters != null) {
            for (AfterFilter writeAfter2 : this.afterFilters) {
                c2 = writeAfter2.writeAfter(jSONSerializer, obj, c2);
            }
        }
        return c2;
    }

    /* access modifiers changed from: protected */
    public boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            for (LabelFilter apply : jSONSerializer.labelFilters) {
                if (!apply.apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        for (LabelFilter apply2 : this.labelFilters) {
            if (!apply2.apply(str)) {
                return false;
            }
        }
        return true;
    }
}
