package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class MapSerializer extends SerializeFilterable implements ObjectSerializer {
    private static final int NON_STRINGKEY_AS_STRING = SerializerFeature.of(new SerializerFeature[]{SerializerFeature.BrowserCompatible, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.BrowserSecure});
    public static MapSerializer instance = new MapSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v6, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: java.util.TreeMap} */
    /* JADX WARNING: type inference failed for: r0v8, types: [boolean] */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ea, code lost:
        if (applyName(r8, r0, (java.lang.String) r1) == false) goto L_0x00ec;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0201 A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x021e A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0256 A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x026d A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0290 A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x029d A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:222:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ab A[Catch:{ all -> 0x0056 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r23, java.lang.Object r24, java.lang.Object r25, java.lang.reflect.Type r26, int r27, boolean r28) throws java.io.IOException {
        /*
            r22 = this;
            r7 = r22
            r8 = r23
            r0 = r24
            r9 = r26
            r10 = r27
            com.alibaba.fastjson.serializer.SerializeWriter r11 = r8.out
            if (r0 != 0) goto L_0x0012
            r11.writeNull()
            return
        L_0x0012:
            r1 = r0
            java.util.Map r1 = (java.util.Map) r1
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.MapSortField
            int r2 = r2.mask
            int r3 = r11.features
            r3 = r3 & r2
            if (r3 != 0) goto L_0x0024
            r2 = r2 & r10
            if (r2 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            r12 = r1
            goto L_0x003c
        L_0x0024:
            boolean r2 = r1 instanceof com.alibaba.fastjson.JSONObject
            if (r2 == 0) goto L_0x002e
            com.alibaba.fastjson.JSONObject r1 = (com.alibaba.fastjson.JSONObject) r1
            java.util.Map r1 = r1.getInnerMap()
        L_0x002e:
            boolean r2 = r1 instanceof java.util.SortedMap
            if (r2 != 0) goto L_0x0022
            boolean r2 = r1 instanceof java.util.LinkedHashMap
            if (r2 != 0) goto L_0x0022
            java.util.TreeMap r2 = new java.util.TreeMap     // Catch:{ Exception -> 0x0022 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0022 }
            r12 = r2
        L_0x003c:
            boolean r1 = r23.containsReference(r24)
            if (r1 == 0) goto L_0x0046
            r23.writeReference(r24)
            return
        L_0x0046:
            com.alibaba.fastjson.serializer.SerialContext r13 = r8.context
            r14 = 0
            r1 = r25
            r8.setContext(r13, r0, r1, r14)
            if (r28 != 0) goto L_0x0059
            r1 = 123(0x7b, float:1.72E-43)
            r11.write((int) r1)     // Catch:{ all -> 0x0056 }
            goto L_0x0059
        L_0x0056:
            r0 = move-exception
            goto L_0x030e
        L_0x0059:
            r23.incrementIndent()     // Catch:{ all -> 0x0056 }
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ all -> 0x0056 }
            boolean r1 = r11.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ all -> 0x0056 }
            r6 = 1
            if (r1 == 0) goto L_0x0094
            com.alibaba.fastjson.serializer.SerializeConfig r1 = r8.config     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r1.typeKey     // Catch:{ all -> 0x0056 }
            java.lang.Class r2 = r12.getClass()     // Catch:{ all -> 0x0056 }
            java.lang.Class<com.alibaba.fastjson.JSONObject> r3 = com.alibaba.fastjson.JSONObject.class
            if (r2 == r3) goto L_0x0079
            java.lang.Class<java.util.HashMap> r3 = java.util.HashMap.class
            if (r2 == r3) goto L_0x0079
            java.lang.Class<java.util.LinkedHashMap> r3 = java.util.LinkedHashMap.class
            if (r2 != r3) goto L_0x0081
        L_0x0079:
            boolean r2 = r12.containsKey(r1)     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0081
            r2 = 1
            goto L_0x0082
        L_0x0081:
            r2 = 0
        L_0x0082:
            if (r2 != 0) goto L_0x0094
            r11.writeFieldName(r1)     // Catch:{ all -> 0x0056 }
            java.lang.Class r1 = r24.getClass()     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0056 }
            r11.writeString((java.lang.String) r1)     // Catch:{ all -> 0x0056 }
            r1 = 0
            goto L_0x0095
        L_0x0094:
            r1 = 1
        L_0x0095:
            java.util.Set r2 = r12.entrySet()     // Catch:{ all -> 0x0056 }
            java.util.Iterator r15 = r2.iterator()     // Catch:{ all -> 0x0056 }
            r16 = 0
            r17 = r1
            r5 = r16
            r18 = r5
        L_0x00a5:
            boolean r1 = r15.hasNext()     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x02f0
            java.lang.Object r1 = r15.next()     // Catch:{ all -> 0x0056 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0056 }
            java.lang.Object r4 = r1.getValue()     // Catch:{ all -> 0x0056 }
            java.lang.Object r1 = r1.getKey()     // Catch:{ all -> 0x0056 }
            java.util.List r2 = r8.propertyPreFilters     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x00f1
            int r2 = r2.size()     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x00f1
            if (r1 == 0) goto L_0x00e3
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x00ca
            goto L_0x00e3
        L_0x00ca:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x00d8
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x00f1
        L_0x00d8:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.applyName(r8, r0, r2)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x00f1
            goto L_0x00ec
        L_0x00e3:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.applyName(r8, r0, r2)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x00f1
        L_0x00ec:
            r21 = r5
            r0 = 1
            goto L_0x0248
        L_0x00f1:
            java.util.List r2 = r7.propertyPreFilters     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0125
            int r2 = r2.size()     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x0125
            if (r1 == 0) goto L_0x011b
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0102
            goto L_0x011b
        L_0x0102:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0110
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0125
        L_0x0110:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.applyName(r8, r0, r2)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0125
            goto L_0x00ec
        L_0x011b:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.applyName(r8, r0, r2)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0125
            goto L_0x00ec
        L_0x0125:
            java.util.List r2 = r8.propertyFilters     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0159
            int r2 = r2.size()     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x0159
            if (r1 == 0) goto L_0x014f
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0136
            goto L_0x014f
        L_0x0136:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0144
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0159
        L_0x0144:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.apply(r8, r0, r2, r4)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0159
            goto L_0x00ec
        L_0x014f:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.apply(r8, r0, r2, r4)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0159
            goto L_0x00ec
        L_0x0159:
            java.util.List r2 = r7.propertyFilters     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x018f
            int r2 = r2.size()     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x018f
            if (r1 == 0) goto L_0x0184
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x016a
            goto L_0x0184
        L_0x016a:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x0178
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x018f
        L_0x0178:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.apply(r8, r0, r2, r4)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x018f
            goto L_0x00ec
        L_0x0184:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0056 }
            boolean r2 = r7.apply(r8, r0, r2, r4)     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x018f
            goto L_0x00ec
        L_0x018f:
            java.util.List r2 = r8.nameFilters     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x01bd
            int r2 = r2.size()     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x01bd
            if (r1 == 0) goto L_0x01b7
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x01a0
            goto L_0x01b7
        L_0x01a0:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x01ae
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x01bd
        L_0x01ae:
            java.lang.String r1 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r7.processKey(r8, r0, r1, r4)     // Catch:{ all -> 0x0056 }
            goto L_0x01bd
        L_0x01b7:
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r7.processKey(r8, r0, r1, r4)     // Catch:{ all -> 0x0056 }
        L_0x01bd:
            java.util.List r2 = r7.nameFilters     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x01eb
            int r2 = r2.size()     // Catch:{ all -> 0x0056 }
            if (r2 <= 0) goto L_0x01eb
            if (r1 == 0) goto L_0x01e5
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x01ce
            goto L_0x01e5
        L_0x01ce:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x0056 }
            if (r2 != 0) goto L_0x01dc
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x01eb
        L_0x01dc:
            java.lang.String r1 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r7.processKey(r8, r0, r1, r4)     // Catch:{ all -> 0x0056 }
            goto L_0x01eb
        L_0x01e5:
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r7.processKey(r8, r0, r1, r4)     // Catch:{ all -> 0x0056 }
        L_0x01eb:
            r3 = r1
            if (r3 == 0) goto L_0x0227
            boolean r1 = r3 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x01f3
            goto L_0x0227
        L_0x01f3:
            boolean r1 = r3 instanceof java.util.Map     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x01fe
            boolean r1 = r3 instanceof java.util.Collection     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x01fc
            goto L_0x01fe
        L_0x01fc:
            r1 = 0
            goto L_0x01ff
        L_0x01fe:
            r1 = 1
        L_0x01ff:
            if (r1 != 0) goto L_0x021e
            java.lang.String r19 = com.alibaba.fastjson.JSON.toJSONString(r3)     // Catch:{ all -> 0x0056 }
            r20 = 0
            r1 = r22
            r2 = r23
            r14 = r3
            r3 = r20
            r20 = r4
            r4 = r24
            r21 = r5
            r5 = r19
            r0 = 1
            r6 = r20
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0056 }
            goto L_0x023d
        L_0x021e:
            r14 = r3
            r20 = r4
            r21 = r5
            r0 = 1
            r3 = r20
            goto L_0x023e
        L_0x0227:
            r14 = r3
            r20 = r4
            r21 = r5
            r0 = 1
            r3 = 0
            r5 = r14
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0056 }
            r1 = r22
            r2 = r23
            r4 = r24
            r6 = r20
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0056 }
        L_0x023d:
            r3 = r1
        L_0x023e:
            if (r3 != 0) goto L_0x0250
            int r1 = com.alibaba.fastjson.serializer.SerializerFeature.WRITE_MAP_NULL_FEATURES     // Catch:{ all -> 0x0056 }
            boolean r1 = r11.isEnabled((int) r1)     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x0250
        L_0x0248:
            r5 = r21
            r0 = r24
            r6 = 1
            r14 = 0
            goto L_0x00a5
        L_0x0250:
            boolean r1 = r14 instanceof java.lang.String     // Catch:{ all -> 0x0056 }
            r2 = 44
            if (r1 == 0) goto L_0x026d
            r1 = r14
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0056 }
            if (r17 != 0) goto L_0x025e
            r11.write((int) r2)     // Catch:{ all -> 0x0056 }
        L_0x025e:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ all -> 0x0056 }
            boolean r2 = r11.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0269
            r23.println()     // Catch:{ all -> 0x0056 }
        L_0x0269:
            r11.writeFieldName(r1, r0)     // Catch:{ all -> 0x0056 }
            goto L_0x028e
        L_0x026d:
            if (r17 != 0) goto L_0x0272
            r11.write((int) r2)     // Catch:{ all -> 0x0056 }
        L_0x0272:
            int r1 = NON_STRINGKEY_AS_STRING     // Catch:{ all -> 0x0056 }
            boolean r1 = r11.isEnabled((int) r1)     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0286
            boolean r1 = r14 instanceof java.lang.Enum     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x0286
            java.lang.String r1 = com.alibaba.fastjson.JSON.toJSONString(r14)     // Catch:{ all -> 0x0056 }
            r8.write((java.lang.String) r1)     // Catch:{ all -> 0x0056 }
            goto L_0x0289
        L_0x0286:
            r8.write((java.lang.Object) r14)     // Catch:{ all -> 0x0056 }
        L_0x0289:
            r1 = 58
            r11.write((int) r1)     // Catch:{ all -> 0x0056 }
        L_0x028e:
            if (r3 != 0) goto L_0x029d
            r11.writeNull()     // Catch:{ all -> 0x0056 }
            r5 = r21
        L_0x0295:
            r0 = r24
            r6 = 1
            r14 = 0
            r17 = 0
            goto L_0x00a5
        L_0x029d:
            java.lang.Class r1 = r3.getClass()     // Catch:{ all -> 0x0056 }
            r2 = r21
            if (r1 == r2) goto L_0x02ad
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = r8.getObjectWriter(r1)     // Catch:{ all -> 0x0056 }
            r17 = r1
            r6 = r2
            goto L_0x02b1
        L_0x02ad:
            r17 = r2
            r6 = r18
        L_0x02b1:
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ all -> 0x0056 }
            boolean r1 = com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r10, r1)     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x02e0
            boolean r1 = r6 instanceof com.alibaba.fastjson.serializer.JavaBeanSerializer     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x02e0
            boolean r1 = r9 instanceof java.lang.reflect.ParameterizedType     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x02d0
            r1 = r9
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1     // Catch:{ all -> 0x0056 }
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()     // Catch:{ all -> 0x0056 }
            int r2 = r1.length     // Catch:{ all -> 0x0056 }
            r4 = 2
            if (r2 != r4) goto L_0x02d0
            r1 = r1[r0]     // Catch:{ all -> 0x0056 }
            r5 = r1
            goto L_0x02d2
        L_0x02d0:
            r5 = r16
        L_0x02d2:
            r1 = r6
            com.alibaba.fastjson.serializer.JavaBeanSerializer r1 = (com.alibaba.fastjson.serializer.JavaBeanSerializer) r1     // Catch:{ all -> 0x0056 }
            r2 = r23
            r4 = r14
            r18 = r6
            r6 = r27
            r1.writeNoneASM(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0056 }
            goto L_0x02ed
        L_0x02e0:
            r18 = r6
            r5 = 0
            r1 = r18
            r2 = r23
            r4 = r14
            r6 = r27
            r1.write(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0056 }
        L_0x02ed:
            r5 = r17
            goto L_0x0295
        L_0x02f0:
            r8.context = r13
            r23.decrementIdent()
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat
            boolean r0 = r11.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r0)
            if (r0 == 0) goto L_0x0306
            int r0 = r12.size()
            if (r0 <= 0) goto L_0x0306
            r23.println()
        L_0x0306:
            if (r28 != 0) goto L_0x030d
            r0 = 125(0x7d, float:1.75E-43)
            r11.write((int) r0)
        L_0x030d:
            return
        L_0x030e:
            r8.context = r13
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.MapSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }
}
