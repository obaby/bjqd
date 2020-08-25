package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;
    public String[] orders;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;
    public final String typeKey;
    public final String typeName;

    /* JADX WARNING: Removed duplicated region for block: B:51:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JavaBeanInfo(java.lang.Class<?> r8, java.lang.Class<?> r9, java.lang.reflect.Constructor<?> r10, java.lang.reflect.Constructor<?> r11, java.lang.reflect.Method r12, java.lang.reflect.Method r13, com.alibaba.fastjson.annotation.JSONType r14, java.util.List<com.alibaba.fastjson.util.FieldInfo> r15) {
        /*
            r7 = this;
            r7.<init>()
            r7.clazz = r8
            r7.builderClass = r9
            r7.defaultConstructor = r10
            r7.creatorConstructor = r11
            r7.factoryMethod = r12
            int r9 = com.alibaba.fastjson.util.TypeUtils.getParserFeatures(r8)
            r7.parserFeatures = r9
            r7.buildMethod = r13
            r7.jsonType = r14
            r9 = 0
            if (r14 == 0) goto L_0x0046
            java.lang.String r13 = r14.typeName()
            java.lang.String r0 = r14.typeKey()
            int r1 = r0.length()
            if (r1 <= 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r0 = r9
        L_0x002a:
            r7.typeKey = r0
            int r0 = r13.length()
            if (r0 == 0) goto L_0x0035
            r7.typeName = r13
            goto L_0x003b
        L_0x0035:
            java.lang.String r13 = r8.getName()
            r7.typeName = r13
        L_0x003b:
            java.lang.String[] r13 = r14.orders()
            int r14 = r13.length
            if (r14 != 0) goto L_0x0043
            r13 = r9
        L_0x0043:
            r7.orders = r13
            goto L_0x0050
        L_0x0046:
            java.lang.String r13 = r8.getName()
            r7.typeName = r13
            r7.typeKey = r9
            r7.orders = r9
        L_0x0050:
            int r13 = r15.size()
            com.alibaba.fastjson.util.FieldInfo[] r13 = new com.alibaba.fastjson.util.FieldInfo[r13]
            r7.fields = r13
            com.alibaba.fastjson.util.FieldInfo[] r13 = r7.fields
            r15.toArray(r13)
            com.alibaba.fastjson.util.FieldInfo[] r13 = r7.fields
            int r13 = r13.length
            com.alibaba.fastjson.util.FieldInfo[] r13 = new com.alibaba.fastjson.util.FieldInfo[r13]
            java.lang.String[] r14 = r7.orders
            r0 = 0
            if (r14 == 0) goto L_0x00b6
            java.util.LinkedHashMap r14 = new java.util.LinkedHashMap
            int r15 = r15.size()
            r14.<init>(r15)
            com.alibaba.fastjson.util.FieldInfo[] r15 = r7.fields
            int r1 = r15.length
            r2 = 0
        L_0x0074:
            if (r2 >= r1) goto L_0x0080
            r3 = r15[r2]
            java.lang.String r4 = r3.name
            r14.put(r4, r3)
            int r2 = r2 + 1
            goto L_0x0074
        L_0x0080:
            java.lang.String[] r15 = r7.orders
            int r1 = r15.length
            r2 = 0
            r3 = 0
        L_0x0085:
            if (r2 >= r1) goto L_0x009c
            r4 = r15[r2]
            java.lang.Object r5 = r14.get(r4)
            com.alibaba.fastjson.util.FieldInfo r5 = (com.alibaba.fastjson.util.FieldInfo) r5
            if (r5 == 0) goto L_0x0099
            int r6 = r3 + 1
            r13[r3] = r5
            r14.remove(r4)
            r3 = r6
        L_0x0099:
            int r2 = r2 + 1
            goto L_0x0085
        L_0x009c:
            java.util.Collection r14 = r14.values()
            java.util.Iterator r14 = r14.iterator()
        L_0x00a4:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x00c1
            java.lang.Object r15 = r14.next()
            com.alibaba.fastjson.util.FieldInfo r15 = (com.alibaba.fastjson.util.FieldInfo) r15
            int r1 = r3 + 1
            r13[r3] = r15
            r3 = r1
            goto L_0x00a4
        L_0x00b6:
            com.alibaba.fastjson.util.FieldInfo[] r14 = r7.fields
            com.alibaba.fastjson.util.FieldInfo[] r15 = r7.fields
            int r15 = r15.length
            java.lang.System.arraycopy(r14, r0, r13, r0, r15)
            java.util.Arrays.sort(r13)
        L_0x00c1:
            com.alibaba.fastjson.util.FieldInfo[] r14 = r7.fields
            boolean r14 = java.util.Arrays.equals(r14, r13)
            if (r14 == 0) goto L_0x00cb
            com.alibaba.fastjson.util.FieldInfo[] r13 = r7.fields
        L_0x00cb:
            r7.sortedFields = r13
            if (r10 == 0) goto L_0x00d7
            java.lang.Class[] r10 = r10.getParameterTypes()
            int r10 = r10.length
            r7.defaultConstructorParameterSize = r10
            goto L_0x00e3
        L_0x00d7:
            if (r12 == 0) goto L_0x00e1
            java.lang.Class[] r10 = r12.getParameterTypes()
            int r10 = r10.length
            r7.defaultConstructorParameterSize = r10
            goto L_0x00e3
        L_0x00e1:
            r7.defaultConstructorParameterSize = r0
        L_0x00e3:
            if (r11 == 0) goto L_0x0154
            java.lang.Class[] r10 = r11.getParameterTypes()
            r7.creatorConstructorParameterTypes = r10
            java.lang.reflect.Type[] r10 = r7.creatorConstructorParameterTypes
            int r10 = r10.length
            com.alibaba.fastjson.util.FieldInfo[] r12 = r7.fields
            int r12 = r12.length
            if (r10 == r12) goto L_0x00f5
        L_0x00f3:
            r10 = 0
            goto L_0x010c
        L_0x00f5:
            r10 = 0
        L_0x00f6:
            java.lang.reflect.Type[] r12 = r7.creatorConstructorParameterTypes
            int r12 = r12.length
            if (r10 >= r12) goto L_0x010b
            java.lang.reflect.Type[] r12 = r7.creatorConstructorParameterTypes
            r12 = r12[r10]
            com.alibaba.fastjson.util.FieldInfo[] r13 = r7.fields
            r13 = r13[r10]
            java.lang.Class<?> r13 = r13.fieldClass
            if (r12 == r13) goto L_0x0108
            goto L_0x00f3
        L_0x0108:
            int r10 = r10 + 1
            goto L_0x00f6
        L_0x010b:
            r10 = 1
        L_0x010c:
            if (r10 != 0) goto L_0x0154
            boolean r10 = com.alibaba.fastjson.util.TypeUtils.isKotlin(r8)
            if (r10 == 0) goto L_0x014e
            java.lang.String[] r8 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r8)
            r7.creatorConstructorParameters = r8
            java.lang.annotation.Annotation[][] r8 = r11.getParameterAnnotations()
            r10 = 0
        L_0x011f:
            java.lang.String[] r11 = r7.creatorConstructorParameters
            int r11 = r11.length
            if (r10 >= r11) goto L_0x0154
            int r11 = r8.length
            if (r10 >= r11) goto L_0x0154
            r11 = r8[r10]
            int r12 = r11.length
            r13 = 0
        L_0x012b:
            if (r13 >= r12) goto L_0x013a
            r14 = r11[r13]
            boolean r15 = r14 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r15 == 0) goto L_0x0137
            r11 = r14
            com.alibaba.fastjson.annotation.JSONField r11 = (com.alibaba.fastjson.annotation.JSONField) r11
            goto L_0x013b
        L_0x0137:
            int r13 = r13 + 1
            goto L_0x012b
        L_0x013a:
            r11 = r9
        L_0x013b:
            if (r11 == 0) goto L_0x014b
            java.lang.String r11 = r11.name()
            int r12 = r11.length()
            if (r12 <= 0) goto L_0x014b
            java.lang.String[] r12 = r7.creatorConstructorParameters
            r12[r10] = r11
        L_0x014b:
            int r10 = r10 + 1
            goto L_0x011f
        L_0x014e:
            java.lang.String[] r8 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r11)
            r7.creatorConstructorParameters = r8
        L_0x0154:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.<init>(java.lang.Class, java.lang.Class, java.lang.reflect.Constructor, java.lang.reflect.Constructor, java.lang.reflect.Method, java.lang.reflect.Method, com.alibaba.fastjson.annotation.JSONType, java.util.List):void");
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo next : list) {
            if (next.name.equals(str)) {
                return next;
            }
            Field field = next.field;
            if (field != null && next.getAnnotation() != null && field.getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        FieldInfo fieldInfo2;
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            fieldInfo2 = list.get(size);
            if (!fieldInfo2.name.equals(fieldInfo.name) || (fieldInfo2.getOnly && !fieldInfo.getOnly)) {
                size--;
            }
        }
        if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass)) {
            list.remove(size);
        } else if (fieldInfo2.compareTo(fieldInfo) >= 0) {
            return false;
        } else {
            list.remove(size);
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return build(cls, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v123, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v124, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v126, resolved type: java.lang.Class<?>} */
    /* JADX WARNING: type inference failed for: r1v37, types: [java.lang.annotation.Annotation] */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r0 = r14.naming();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x087d, code lost:
        if (r1.deserialize() == false) goto L_0x087f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x047f  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x048a  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x04b5  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x06c6  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x06f6  */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x06fa  */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x0762  */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x076e  */
    /* JADX WARNING: Removed duplicated region for block: B:333:0x0775  */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x088b  */
    /* JADX WARNING: Removed duplicated region for block: B:380:0x0897  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.util.JavaBeanInfo build(java.lang.Class<?> r44, java.lang.reflect.Type r45, com.alibaba.fastjson.PropertyNamingStrategy r46, boolean r47, boolean r48) {
        /*
            r12 = r44
            r13 = r45
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r0 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation(r12, r0)
            r14 = r0
            com.alibaba.fastjson.annotation.JSONType r14 = (com.alibaba.fastjson.annotation.JSONType) r14
            if (r14 == 0) goto L_0x001b
            com.alibaba.fastjson.PropertyNamingStrategy r0 = r14.naming()
            if (r0 == 0) goto L_0x001b
            com.alibaba.fastjson.PropertyNamingStrategy r1 = com.alibaba.fastjson.PropertyNamingStrategy.CamelCase
            if (r0 == r1) goto L_0x001b
            r15 = r0
            goto L_0x001d
        L_0x001b:
            r15 = r46
        L_0x001d:
            java.lang.Class r11 = getBuilderClass(r12, r14)
            java.lang.reflect.Field[] r10 = r44.getDeclaredFields()
            java.lang.reflect.Method[] r9 = r44.getMethods()
            boolean r16 = com.alibaba.fastjson.util.TypeUtils.isKotlin(r44)
            java.lang.reflect.Constructor[] r0 = r44.getDeclaredConstructors()
            r17 = 0
            r8 = 1
            if (r16 == 0) goto L_0x003d
            int r1 = r0.length
            if (r1 != r8) goto L_0x003a
            goto L_0x003d
        L_0x003a:
            r18 = r17
            goto L_0x004f
        L_0x003d:
            if (r11 != 0) goto L_0x0046
            java.lang.reflect.Constructor r1 = getDefaultConstructor(r12, r0)
        L_0x0043:
            r18 = r1
            goto L_0x004f
        L_0x0046:
            java.lang.reflect.Constructor[] r1 = r11.getDeclaredConstructors()
            java.lang.reflect.Constructor r1 = getDefaultConstructor(r11, r1)
            goto L_0x0043
        L_0x004f:
            r19 = 0
            r20 = 0
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            if (r47 == 0) goto L_0x007c
            r0 = r12
        L_0x005b:
            if (r0 == 0) goto L_0x0069
            java.lang.reflect.Field[] r1 = r0.getDeclaredFields()
            computeFields(r12, r13, r15, r7, r1)
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x005b
        L_0x0069:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r4 = 0
            r0 = r9
            r1 = r44
            r2 = r11
            r3 = r18
            r5 = r20
            r6 = r19
            r8 = r7
            r7 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x007c:
            boolean r1 = r44.isInterface()
            if (r1 != 0) goto L_0x008f
            int r1 = r44.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isAbstract(r1)
            if (r1 == 0) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            r1 = 0
            goto L_0x0090
        L_0x008f:
            r1 = 1
        L_0x0090:
            if (r18 != 0) goto L_0x0094
            if (r11 == 0) goto L_0x0096
        L_0x0094:
            if (r1 == 0) goto L_0x037f
        L_0x0096:
            java.lang.reflect.Constructor r21 = getCreatorConstructor(r0)
            if (r21 == 0) goto L_0x0145
            if (r1 != 0) goto L_0x0145
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r21)
            java.lang.Class[] r3 = r21.getParameterTypes()
            int r0 = r3.length
            if (r0 <= 0) goto L_0x013d
            java.lang.annotation.Annotation[][] r16 = r21.getParameterAnnotations()
            r0 = r17
            r2 = 0
        L_0x00af:
            int r1 = r3.length
            if (r2 >= r1) goto L_0x013d
            r1 = r16[r2]
            int r4 = r1.length
            r5 = 0
        L_0x00b6:
            if (r5 >= r4) goto L_0x00c6
            r6 = r1[r5]
            boolean r8 = r6 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r8 == 0) goto L_0x00c2
            r1 = r6
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            goto L_0x00c8
        L_0x00c2:
            int r5 = r5 + 1
            r8 = 1
            goto L_0x00b6
        L_0x00c6:
            r1 = r17
        L_0x00c8:
            r4 = r3[r2]
            java.lang.reflect.Type[] r5 = r21.getGenericParameterTypes()
            r5 = r5[r2]
            if (r1 == 0) goto L_0x00f3
            java.lang.String r6 = r1.name()
            java.lang.reflect.Field r6 = com.alibaba.fastjson.util.TypeUtils.getField(r12, r6, r10)
            int r8 = r1.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r26 = r1.serialzeFeatures()
            int r26 = com.alibaba.fastjson.serializer.SerializerFeature.of(r26)
            com.alibaba.fastjson.parser.Feature[] r27 = r1.parseFeatures()
            int r27 = com.alibaba.fastjson.parser.Feature.of(r27)
            java.lang.String r1 = r1.name()
            goto L_0x00fb
        L_0x00f3:
            r1 = r17
            r6 = r1
            r8 = 0
            r26 = 0
            r27 = 0
        L_0x00fb:
            if (r1 == 0) goto L_0x0107
            int r28 = r1.length()
            if (r28 != 0) goto L_0x0104
            goto L_0x0107
        L_0x0104:
            r28 = r0
            goto L_0x0110
        L_0x0107:
            if (r0 != 0) goto L_0x010d
            java.lang.String[] r0 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r21)
        L_0x010d:
            r1 = r0[r2]
            goto L_0x0104
        L_0x0110:
            com.alibaba.fastjson.util.FieldInfo r0 = new com.alibaba.fastjson.util.FieldInfo
            r29 = r0
            r30 = r2
            r2 = r44
            r31 = r3
            r3 = r4
            r13 = 3
            r4 = r5
            r13 = 2
            r5 = r6
            r6 = r8
            r8 = r7
            r7 = r26
            r13 = r8
            r32 = r15
            r15 = 1
            r8 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r13, r0)
            int r2 = r30 + 1
            r7 = r13
            r0 = r28
            r3 = r31
            r15 = r32
            r8 = 1
            r13 = r45
            goto L_0x00af
        L_0x013d:
            r13 = r7
            r32 = r15
            r15 = 1
        L_0x0141:
            r34 = r9
            goto L_0x0386
        L_0x0145:
            r13 = r7
            r32 = r15
            r15 = 1
            java.lang.reflect.Method r20 = getFactoryMethod(r12, r9)
            if (r20 == 0) goto L_0x01d2
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r20)
            java.lang.Class[] r8 = r20.getParameterTypes()
            int r0 = r8.length
            if (r0 <= 0) goto L_0x0141
            java.lang.annotation.Annotation[][] r9 = r20.getParameterAnnotations()
            r15 = 0
        L_0x015e:
            int r0 = r8.length
            if (r15 >= r0) goto L_0x01c1
            r0 = r9[r15]
            int r1 = r0.length
            r2 = 0
        L_0x0165:
            if (r2 >= r1) goto L_0x0174
            r3 = r0[r2]
            boolean r4 = r3 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r4 == 0) goto L_0x0171
            r0 = r3
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            goto L_0x0176
        L_0x0171:
            int r2 = r2 + 1
            goto L_0x0165
        L_0x0174:
            r0 = r17
        L_0x0176:
            if (r0 == 0) goto L_0x01b9
            r3 = r8[r15]
            java.lang.reflect.Type[] r1 = r20.getGenericParameterTypes()
            r4 = r1[r15]
            java.lang.String r1 = r0.name()
            java.lang.reflect.Field r5 = com.alibaba.fastjson.util.TypeUtils.getField(r12, r1, r10)
            int r6 = r0.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r1 = r0.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r1)
            com.alibaba.fastjson.parser.Feature[] r1 = r0.parseFeatures()
            int r16 = com.alibaba.fastjson.parser.Feature.of(r1)
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.String r1 = r0.name()
            r0 = r2
            r33 = r9
            r9 = r2
            r2 = r44
            r18 = r8
            r8 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r13, r9)
            int r15 = r15 + 1
            r8 = r18
            r9 = r33
            goto L_0x015e
        L_0x01b9:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.String r1 = "illegal json creator"
            r0.<init>(r1)
            throw r0
        L_0x01c1:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r3 = 0
            r4 = 0
            r6 = 0
            r0 = r9
            r1 = r44
            r2 = r11
            r5 = r20
            r7 = r14
            r8 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x01d2:
            if (r1 != 0) goto L_0x0141
            java.lang.String r8 = r44.getName()
            if (r16 == 0) goto L_0x01ed
            int r1 = r0.length
            if (r1 <= 0) goto L_0x01ed
            java.lang.String[] r1 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r44)
            java.lang.reflect.Constructor r0 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructor(r0)
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0)
            r21 = r0
            r7 = r1
            goto L_0x028e
        L_0x01ed:
            int r1 = r0.length
            r3 = r17
            r2 = 0
        L_0x01f1:
            if (r2 >= r1) goto L_0x028d
            r4 = r0[r2]
            java.lang.Class[] r5 = r4.getParameterTypes()
            java.lang.String r6 = "org.springframework.security.web.authentication.WebAuthenticationDetails"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x021e
            int r6 = r5.length
            r7 = 2
            if (r6 != r7) goto L_0x021e
            r7 = 0
            r6 = r5[r7]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r6 != r7) goto L_0x021e
            r6 = r5[r15]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r6 != r7) goto L_0x021e
            r4.setAccessible(r15)
            java.lang.String[] r1 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r4)
        L_0x0219:
            r7 = r1
            r21 = r4
            goto L_0x028e
        L_0x021e:
            java.lang.String r6 = "org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x024c
            int r6 = r5.length
            r7 = 3
            if (r6 != r7) goto L_0x024c
            r6 = 0
            r7 = r5[r6]
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            if (r7 != r6) goto L_0x024c
            r6 = r5[r15]
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            if (r6 != r7) goto L_0x024c
            r6 = 2
            r7 = r5[r6]
            java.lang.Class<java.util.Collection> r6 = java.util.Collection.class
            if (r7 != r6) goto L_0x024c
            r4.setAccessible(r15)
            java.lang.String r0 = "principal"
            java.lang.String r1 = "credentials"
            java.lang.String r2 = "authorities"
            java.lang.String[] r1 = new java.lang.String[]{r0, r1, r2}
            goto L_0x0219
        L_0x024c:
            java.lang.String r6 = "org.springframework.security.core.authority.SimpleGrantedAuthority"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x0265
            int r6 = r5.length
            if (r6 != r15) goto L_0x0265
            r7 = 0
            r5 = r5[r7]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            if (r5 != r6) goto L_0x0266
            java.lang.String r0 = "authority"
            java.lang.String[] r1 = new java.lang.String[]{r0}
            goto L_0x0219
        L_0x0265:
            r7 = 0
        L_0x0266:
            int r5 = r4.getModifiers()
            r5 = r5 & r15
            if (r5 == 0) goto L_0x026f
            r5 = 1
            goto L_0x0270
        L_0x026f:
            r5 = 0
        L_0x0270:
            if (r5 != 0) goto L_0x0273
            goto L_0x0289
        L_0x0273:
            java.lang.String[] r5 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r4)
            if (r5 == 0) goto L_0x0289
            int r6 = r5.length
            if (r6 != 0) goto L_0x027d
            goto L_0x0289
        L_0x027d:
            if (r21 == 0) goto L_0x0286
            if (r3 == 0) goto L_0x0286
            int r6 = r5.length
            int r7 = r3.length
            if (r6 > r7) goto L_0x0286
            goto L_0x0289
        L_0x0286:
            r21 = r4
            r3 = r5
        L_0x0289:
            int r2 = r2 + 1
            goto L_0x01f1
        L_0x028d:
            r7 = r3
        L_0x028e:
            if (r7 == 0) goto L_0x0296
            java.lang.Class[] r0 = r21.getParameterTypes()
            r6 = r0
            goto L_0x0298
        L_0x0296:
            r6 = r17
        L_0x0298:
            if (r7 == 0) goto L_0x0368
            int r0 = r6.length
            int r1 = r7.length
            if (r0 != r1) goto L_0x0368
            java.lang.annotation.Annotation[][] r22 = r21.getParameterAnnotations()
            r5 = 0
        L_0x02a3:
            int r0 = r6.length
            if (r5 >= r0) goto L_0x0347
            r0 = r22[r5]
            r1 = r7[r5]
            int r2 = r0.length
            r3 = 0
        L_0x02ac:
            if (r3 >= r2) goto L_0x02bc
            r4 = r0[r3]
            boolean r15 = r4 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r15 == 0) goto L_0x02b8
            r0 = r4
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            goto L_0x02be
        L_0x02b8:
            int r3 = r3 + 1
            r15 = 1
            goto L_0x02ac
        L_0x02bc:
            r0 = r17
        L_0x02be:
            r3 = r6[r5]
            java.lang.reflect.Type[] r2 = r21.getGenericParameterTypes()
            r4 = r2[r5]
            java.lang.reflect.Field r15 = com.alibaba.fastjson.util.TypeUtils.getField(r12, r1, r10)
            if (r15 == 0) goto L_0x02d6
            if (r0 != 0) goto L_0x02d6
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = r15.getAnnotation(r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
        L_0x02d6:
            if (r0 != 0) goto L_0x02fa
            java.lang.String r0 = "org.springframework.security.core.userdetails.User"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x02f3
            java.lang.String r0 = "password"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x02f3
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty
            int r0 = r0.mask
            r25 = r0
            r23 = 0
            r24 = 0
            goto L_0x031f
        L_0x02f3:
            r23 = 0
            r24 = 0
            r25 = 0
            goto L_0x031f
        L_0x02fa:
            java.lang.String r2 = r0.name()
            int r23 = r2.length()
            if (r23 == 0) goto L_0x0305
            r1 = r2
        L_0x0305:
            int r2 = r0.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r23 = r0.serialzeFeatures()
            int r23 = com.alibaba.fastjson.serializer.SerializerFeature.of(r23)
            com.alibaba.fastjson.parser.Feature[] r0 = r0.parseFeatures()
            int r0 = com.alibaba.fastjson.parser.Feature.of(r0)
            r25 = r0
            r24 = r23
            r23 = r2
        L_0x031f:
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r0 = r2
            r34 = r9
            r9 = r2
            r2 = r44
            r26 = r5
            r5 = r15
            r15 = r6
            r6 = r23
            r23 = r7
            r7 = r24
            r24 = r8
            r8 = r25
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r13, r9)
            int r5 = r26 + 1
            r6 = r15
            r7 = r23
            r8 = r24
            r9 = r34
            r15 = 1
            goto L_0x02a3
        L_0x0347:
            r34 = r9
            if (r16 != 0) goto L_0x0386
            java.lang.String r0 = r44.getName()
            java.lang.String r1 = "javax.servlet.http.Cookie"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0386
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r3 = 0
            r5 = 0
            r6 = 0
            r0 = r9
            r1 = r44
            r2 = r11
            r4 = r21
            r7 = r14
            r8 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0368:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "default constructor not found. "
            r1.append(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x037f:
            r13 = r7
            r34 = r9
            r32 = r15
            r21 = r17
        L_0x0386:
            if (r18 == 0) goto L_0x038b
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r18)
        L_0x038b:
            if (r11 == 0) goto L_0x0539
            java.lang.Class<com.alibaba.fastjson.annotation.JSONPOJOBuilder> r0 = com.alibaba.fastjson.annotation.JSONPOJOBuilder.class
            java.lang.annotation.Annotation r0 = r11.getAnnotation(r0)
            com.alibaba.fastjson.annotation.JSONPOJOBuilder r0 = (com.alibaba.fastjson.annotation.JSONPOJOBuilder) r0
            if (r0 == 0) goto L_0x039c
            java.lang.String r0 = r0.withPrefix()
            goto L_0x039e
        L_0x039c:
            r0 = r17
        L_0x039e:
            if (r0 == 0) goto L_0x03a9
            int r1 = r0.length()
            if (r1 != 0) goto L_0x03a7
            goto L_0x03a9
        L_0x03a7:
            r15 = r0
            goto L_0x03ac
        L_0x03a9:
            java.lang.String r0 = "with"
            goto L_0x03a7
        L_0x03ac:
            java.lang.reflect.Method[] r9 = r11.getMethods()
            int r8 = r9.length
            r7 = 0
        L_0x03b2:
            if (r7 >= r8) goto L_0x04f0
            r2 = r9[r7]
            int r0 = r2.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 == 0) goto L_0x03d2
        L_0x03c0:
            r26 = r7
            r27 = r8
            r28 = r9
            r37 = r10
            r38 = r14
            r40 = r15
            r36 = r34
            r15 = 0
            r14 = r11
            goto L_0x04df
        L_0x03d2:
            java.lang.Class r0 = r2.getReturnType()
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x03dd
            goto L_0x03c0
        L_0x03dd:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = r2.getAnnotation(r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            if (r0 != 0) goto L_0x03eb
            com.alibaba.fastjson.annotation.JSONField r0 = com.alibaba.fastjson.util.TypeUtils.getSuperMethodAnnotation(r12, r2)
        L_0x03eb:
            r16 = r0
            if (r16 == 0) goto L_0x045c
            boolean r0 = r16.deserialize()
            if (r0 != 0) goto L_0x03f6
            goto L_0x03c0
        L_0x03f6:
            int r6 = r16.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = r16.serialzeFeatures()
            int r22 = com.alibaba.fastjson.serializer.SerializerFeature.of(r0)
            com.alibaba.fastjson.parser.Feature[] r0 = r16.parseFeatures()
            int r23 = com.alibaba.fastjson.parser.Feature.of(r0)
            java.lang.String r0 = r16.name()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x044a
            java.lang.String r1 = r16.name()
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r24 = 0
            r25 = 0
            r0 = r5
            r4 = r44
            r35 = r5
            r5 = r45
            r26 = r7
            r7 = r22
            r27 = r8
            r8 = r23
            r28 = r9
            r36 = r34
            r9 = r16
            r37 = r10
            r10 = r24
            r38 = r14
            r14 = r11
            r11 = r25
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r35
            add(r13, r0)
        L_0x0445:
            r40 = r15
            r15 = 0
            goto L_0x04df
        L_0x044a:
            r26 = r7
            r27 = r8
            r28 = r9
            r37 = r10
            r38 = r14
            r36 = r34
            r14 = r11
            r7 = r22
            r8 = r23
            goto L_0x046c
        L_0x045c:
            r26 = r7
            r27 = r8
            r28 = r9
            r37 = r10
            r38 = r14
            r36 = r34
            r14 = r11
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x046c:
            java.lang.String r0 = r2.getName()
            java.lang.String r1 = "set"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x048a
            int r1 = r0.length()
            r3 = 3
            if (r1 <= r3) goto L_0x048a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = r0.substring(r3)
            r1.<init>(r0)
        L_0x0488:
            r11 = 0
            goto L_0x04aa
        L_0x048a:
            boolean r1 = r0.startsWith(r15)
            if (r1 != 0) goto L_0x0491
        L_0x0490:
            goto L_0x0445
        L_0x0491:
            int r1 = r0.length()
            int r3 = r15.length()
            if (r1 > r3) goto L_0x049c
            goto L_0x0490
        L_0x049c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r3 = r15.length()
            java.lang.String r0 = r0.substring(r3)
            r1.<init>(r0)
            goto L_0x0488
        L_0x04aa:
            char r0 = r1.charAt(r11)
            boolean r3 = java.lang.Character.isUpperCase(r0)
            if (r3 != 0) goto L_0x04b5
            goto L_0x0490
        L_0x04b5:
            char r0 = java.lang.Character.toLowerCase(r0)
            r1.setCharAt(r11, r0)
            java.lang.String r1 = r1.toString()
            com.alibaba.fastjson.util.FieldInfo r10 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r22 = 0
            r23 = 0
            r0 = r10
            r4 = r44
            r5 = r45
            r9 = r16
            r39 = r10
            r10 = r22
            r40 = r15
            r15 = 0
            r11 = r23
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r39
            add(r13, r0)
        L_0x04df:
            int r7 = r26 + 1
            r11 = r14
            r8 = r27
            r9 = r28
            r34 = r36
            r10 = r37
            r14 = r38
            r15 = r40
            goto L_0x03b2
        L_0x04f0:
            r37 = r10
            r38 = r14
            r36 = r34
            r15 = 0
            r14 = r11
            if (r14 == 0) goto L_0x0541
            java.lang.Class<com.alibaba.fastjson.annotation.JSONPOJOBuilder> r0 = com.alibaba.fastjson.annotation.JSONPOJOBuilder.class
            java.lang.annotation.Annotation r0 = r14.getAnnotation(r0)
            com.alibaba.fastjson.annotation.JSONPOJOBuilder r0 = (com.alibaba.fastjson.annotation.JSONPOJOBuilder) r0
            if (r0 == 0) goto L_0x0509
            java.lang.String r0 = r0.buildMethod()
            goto L_0x050b
        L_0x0509:
            r0 = r17
        L_0x050b:
            if (r0 == 0) goto L_0x0513
            int r1 = r0.length()
            if (r1 != 0) goto L_0x0515
        L_0x0513:
            java.lang.String r0 = "build"
        L_0x0515:
            java.lang.Class[] r1 = new java.lang.Class[r15]     // Catch:{ NoSuchMethodException | SecurityException -> 0x051c }
            java.lang.reflect.Method r0 = r14.getMethod(r0, r1)     // Catch:{ NoSuchMethodException | SecurityException -> 0x051c }
            goto L_0x051e
        L_0x051c:
            r0 = r19
        L_0x051e:
            if (r0 != 0) goto L_0x0529
            java.lang.String r1 = "create"
            java.lang.Class[] r2 = new java.lang.Class[r15]     // Catch:{ NoSuchMethodException | SecurityException -> 0x0529 }
            java.lang.reflect.Method r1 = r14.getMethod(r1, r2)     // Catch:{ NoSuchMethodException | SecurityException -> 0x0529 }
            r0 = r1
        L_0x0529:
            if (r0 == 0) goto L_0x0531
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0)
            r19 = r0
            goto L_0x0541
        L_0x0531:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.String r1 = "buildMethod not found."
            r0.<init>(r1)
            throw r0
        L_0x0539:
            r37 = r10
            r38 = r14
            r36 = r34
            r15 = 0
            r14 = r11
        L_0x0541:
            r11 = r36
            int r10 = r11.length
            r9 = 0
        L_0x0545:
            r8 = 4
            if (r9 >= r10) goto L_0x079b
            r2 = r11[r9]
            r6 = 0
            r7 = 0
            r16 = 0
            java.lang.String r0 = r2.getName()
            int r1 = r2.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)
            if (r1 == 0) goto L_0x0570
        L_0x055c:
            r26 = r9
            r27 = r10
            r23 = r11
            r42 = r14
            r15 = r32
            r41 = r37
            r25 = 2
        L_0x056a:
            r28 = 1
            r29 = 0
            goto L_0x078c
        L_0x0570:
            java.lang.Class r1 = r2.getReturnType()
            java.lang.Class r3 = java.lang.Void.TYPE
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0587
            java.lang.Class r3 = r2.getDeclaringClass()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0587
            goto L_0x055c
        L_0x0587:
            java.lang.Class r1 = r2.getDeclaringClass()
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r1 != r3) goto L_0x0590
            goto L_0x055c
        L_0x0590:
            java.lang.Class[] r1 = r2.getParameterTypes()
            int r3 = r1.length
            if (r3 == 0) goto L_0x055c
            int r3 = r1.length
            r5 = 2
            if (r3 <= r5) goto L_0x059c
            goto L_0x055c
        L_0x059c:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = r2.getAnnotation(r3)
            r22 = r3
            com.alibaba.fastjson.annotation.JSONField r22 = (com.alibaba.fastjson.annotation.JSONField) r22
            if (r22 == 0) goto L_0x05de
            int r3 = r1.length
            if (r3 != r5) goto L_0x05de
            r3 = r1[r15]
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r3 != r4) goto L_0x05de
            r3 = 1
            r4 = r1[r3]
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r4 != r3) goto L_0x05de
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.String r1 = ""
            r3 = 0
            r23 = 0
            r24 = 0
            r0 = r8
            r4 = r44
            r25 = 2
            r5 = r45
            r15 = r8
            r8 = r16
            r26 = r9
            r9 = r22
            r27 = r10
            r10 = r23
            r23 = r11
            r11 = r24
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            add(r13, r15)
            goto L_0x05ea
        L_0x05de:
            r26 = r9
            r27 = r10
            r23 = r11
            r25 = 2
            int r3 = r1.length
            r4 = 1
            if (r3 == r4) goto L_0x05f2
        L_0x05ea:
            r42 = r14
            r15 = r32
            r41 = r37
            goto L_0x056a
        L_0x05f2:
            if (r22 != 0) goto L_0x05fa
            com.alibaba.fastjson.annotation.JSONField r3 = com.alibaba.fastjson.util.TypeUtils.getSuperMethodAnnotation(r12, r2)
            r9 = r3
            goto L_0x05fc
        L_0x05fa:
            r9 = r22
        L_0x05fc:
            if (r9 != 0) goto L_0x0605
            int r3 = r0.length()
            if (r3 >= r8) goto L_0x0605
            goto L_0x05ea
        L_0x0605:
            if (r9 == 0) goto L_0x0648
            boolean r3 = r9.deserialize()
            if (r3 != 0) goto L_0x060e
            goto L_0x05ea
        L_0x060e:
            int r6 = r9.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r9.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3)
            com.alibaba.fastjson.parser.Feature[] r3 = r9.parseFeatures()
            int r10 = com.alibaba.fastjson.parser.Feature.of(r3)
            java.lang.String r3 = r9.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0646
            java.lang.String r1 = r9.name()
            com.alibaba.fastjson.util.FieldInfo r15 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r11 = 0
            r16 = 0
            r0 = r15
            r4 = r44
            r5 = r45
            r8 = r10
            r10 = r11
            r11 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            add(r13, r15)
            goto L_0x05ea
        L_0x0646:
            r16 = r10
        L_0x0648:
            if (r9 != 0) goto L_0x0653
            java.lang.String r3 = "set"
            boolean r3 = r0.startsWith(r3)
            if (r3 != 0) goto L_0x0653
            goto L_0x05ea
        L_0x0653:
            r3 = 3
            char r4 = r0.charAt(r3)
            boolean r3 = java.lang.Character.isUpperCase(r4)
            if (r3 != 0) goto L_0x0694
            r3 = 512(0x200, float:7.175E-43)
            if (r4 <= r3) goto L_0x0663
            goto L_0x0694
        L_0x0663:
            r3 = 95
            if (r4 != r3) goto L_0x066f
            java.lang.String r0 = r0.substring(r8)
            r11 = r37
            r15 = 3
            goto L_0x06c0
        L_0x066f:
            r3 = 102(0x66, float:1.43E-43)
            if (r4 != r3) goto L_0x0679
            r15 = 3
            java.lang.String r0 = r0.substring(r15)
            goto L_0x06a1
        L_0x0679:
            r15 = 3
            int r3 = r0.length()
            r4 = 5
            if (r3 < r4) goto L_0x05ea
            char r3 = r0.charAt(r8)
            boolean r3 = java.lang.Character.isUpperCase(r3)
            if (r3 == 0) goto L_0x05ea
            java.lang.String r0 = r0.substring(r15)
            java.lang.String r0 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r0)
            goto L_0x06a1
        L_0x0694:
            r15 = 3
            boolean r3 = com.alibaba.fastjson.util.TypeUtils.compatibleWithJavaBean
            if (r3 == 0) goto L_0x06a4
            java.lang.String r0 = r0.substring(r15)
            java.lang.String r0 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r0)
        L_0x06a1:
            r11 = r37
            goto L_0x06c0
        L_0x06a4:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            char r4 = r0.charAt(r15)
            char r4 = java.lang.Character.toLowerCase(r4)
            r3.append(r4)
            java.lang.String r0 = r0.substring(r8)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            goto L_0x06a1
        L_0x06c0:
            java.lang.reflect.Field r3 = com.alibaba.fastjson.util.TypeUtils.getField(r12, r0, r11)
            if (r3 != 0) goto L_0x06f6
            r10 = 0
            r1 = r1[r10]
            java.lang.Class r4 = java.lang.Boolean.TYPE
            if (r1 != r4) goto L_0x06f4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "is"
            r1.append(r3)
            char r3 = r0.charAt(r10)
            char r3 = java.lang.Character.toUpperCase(r3)
            r1.append(r3)
            r8 = 1
            java.lang.String r3 = r0.substring(r8)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.reflect.Field r1 = com.alibaba.fastjson.util.TypeUtils.getField(r12, r1, r11)
            r3 = r1
            goto L_0x06f8
        L_0x06f4:
            r8 = 1
            goto L_0x06f8
        L_0x06f6:
            r8 = 1
            r10 = 0
        L_0x06f8:
            if (r3 == 0) goto L_0x0762
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = r3.getAnnotation(r1)
            r22 = r1
            com.alibaba.fastjson.annotation.JSONField r22 = (com.alibaba.fastjson.annotation.JSONField) r22
            if (r22 == 0) goto L_0x0757
            boolean r1 = r22.deserialize()
            if (r1 != 0) goto L_0x0714
            r41 = r11
            r42 = r14
            r15 = r32
            goto L_0x056a
        L_0x0714:
            int r6 = r22.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r1 = r22.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r1)
            com.alibaba.fastjson.parser.Feature[] r1 = r22.parseFeatures()
            int r16 = com.alibaba.fastjson.parser.Feature.of(r1)
            java.lang.String r1 = r22.name()
            int r1 = r1.length()
            if (r1 == 0) goto L_0x0757
            java.lang.String r1 = r22.name()
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r24 = 0
            r0 = r5
            r4 = r44
            r15 = r5
            r5 = r45
            r28 = 1
            r8 = r16
            r29 = 0
            r10 = r22
            r41 = r11
            r11 = r24
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            add(r13, r15)
            r42 = r14
            r15 = r32
            goto L_0x078c
        L_0x0757:
            r41 = r11
            r28 = 1
            r29 = 0
            r8 = r16
            r10 = r22
            goto L_0x076c
        L_0x0762:
            r41 = r11
            r28 = 1
            r29 = 0
            r8 = r16
            r10 = r17
        L_0x076c:
            if (r32 == 0) goto L_0x0775
            r15 = r32
            java.lang.String r0 = r15.translate(r0)
            goto L_0x0777
        L_0x0775:
            r15 = r32
        L_0x0777:
            r1 = r0
            com.alibaba.fastjson.util.FieldInfo r11 = new com.alibaba.fastjson.util.FieldInfo
            r16 = 0
            r0 = r11
            r4 = r44
            r5 = r45
            r42 = r14
            r14 = r11
            r11 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            add(r13, r14)
        L_0x078c:
            int r9 = r26 + 1
            r32 = r15
            r11 = r23
            r10 = r27
            r37 = r41
            r14 = r42
            r15 = 0
            goto L_0x0545
        L_0x079b:
            r42 = r14
            r15 = r32
            r41 = r37
            r29 = 0
            java.lang.reflect.Field[] r0 = r44.getFields()
            r11 = 3
            r14 = r45
            computeFields(r12, r14, r15, r13, r0)
            java.lang.reflect.Method[] r10 = r44.getMethods()
            int r9 = r10.length
            r7 = 0
        L_0x07b3:
            if (r7 >= r9) goto L_0x08d7
            r2 = r10[r7]
            java.lang.String r0 = r2.getName()
            int r1 = r0.length()
            if (r1 >= r8) goto L_0x07cf
        L_0x07c1:
            r29 = r7
            r22 = r9
            r16 = r10
            r26 = r41
        L_0x07c9:
            r17 = 4
            r23 = 3
            goto L_0x08cb
        L_0x07cf:
            int r1 = r2.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)
            if (r1 == 0) goto L_0x07da
            goto L_0x07c1
        L_0x07da:
            if (r42 != 0) goto L_0x07c1
            java.lang.String r1 = "get"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x07c1
            char r1 = r0.charAt(r11)
            boolean r1 = java.lang.Character.isUpperCase(r1)
            if (r1 == 0) goto L_0x07c1
            java.lang.Class[] r1 = r2.getParameterTypes()
            int r1 = r1.length
            if (r1 == 0) goto L_0x07f6
            goto L_0x07c1
        L_0x07f6:
            java.lang.Class<java.util.Collection> r1 = java.util.Collection.class
            java.lang.Class r3 = r2.getReturnType()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 != 0) goto L_0x0826
            java.lang.Class<java.util.Map> r1 = java.util.Map.class
            java.lang.Class r3 = r2.getReturnType()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 != 0) goto L_0x0826
            java.lang.Class<java.util.concurrent.atomic.AtomicBoolean> r1 = java.util.concurrent.atomic.AtomicBoolean.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 == r3) goto L_0x0826
            java.lang.Class<java.util.concurrent.atomic.AtomicInteger> r1 = java.util.concurrent.atomic.AtomicInteger.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 == r3) goto L_0x0826
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r1 = java.util.concurrent.atomic.AtomicLong.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 != r3) goto L_0x07c1
        L_0x0826:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = r2.getAnnotation(r1)
            r16 = r1
            com.alibaba.fastjson.annotation.JSONField r16 = (com.alibaba.fastjson.annotation.JSONField) r16
            if (r16 == 0) goto L_0x0839
            boolean r1 = r16.deserialize()
            if (r1 == 0) goto L_0x0839
            goto L_0x07c1
        L_0x0839:
            if (r16 == 0) goto L_0x084c
            java.lang.String r1 = r16.name()
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x084c
            java.lang.String r0 = r16.name()
            r6 = r41
            goto L_0x0889
        L_0x084c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r3 = r0.charAt(r11)
            char r3 = java.lang.Character.toLowerCase(r3)
            r1.append(r3)
            java.lang.String r0 = r0.substring(r8)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r6 = r41
            java.lang.reflect.Field r1 = com.alibaba.fastjson.util.TypeUtils.getField(r12, r0, r6)
            if (r1 == 0) goto L_0x0889
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = r1.getAnnotation(r3)
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            if (r1 == 0) goto L_0x0889
            boolean r1 = r1.deserialize()
            if (r1 != 0) goto L_0x0889
        L_0x087f:
            r26 = r6
            r29 = r7
            r22 = r9
            r16 = r10
            goto L_0x07c9
        L_0x0889:
            if (r15 == 0) goto L_0x088f
            java.lang.String r0 = r15.translate(r0)
        L_0x088f:
            r1 = r0
            com.alibaba.fastjson.util.FieldInfo r0 = getField(r13, r1)
            if (r0 == 0) goto L_0x0897
            goto L_0x087f
        L_0x0897:
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r17 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r0 = r5
            r4 = r44
            r43 = r5
            r5 = r45
            r26 = r6
            r6 = r17
            r29 = r7
            r7 = r22
            r17 = 4
            r8 = r23
            r22 = r9
            r9 = r16
            r16 = r10
            r10 = r24
            r23 = 3
            r11 = r25
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r43
            add(r13, r0)
        L_0x08cb:
            int r7 = r29 + 1
            r10 = r16
            r9 = r22
            r41 = r26
            r8 = 4
            r11 = 3
            goto L_0x07b3
        L_0x08d7:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r0 = r9
            r1 = r44
            r2 = r42
            r3 = r18
            r4 = r21
            r5 = r20
            r6 = r19
            r7 = r38
            r8 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.build(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, boolean, boolean):com.alibaba.fastjson.util.JavaBeanInfo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        if ((java.util.Map.class.isAssignableFrom(r5) || java.util.Collection.class.isAssignableFrom(r5) || java.util.concurrent.atomic.AtomicLong.class.equals(r5) || java.util.concurrent.atomic.AtomicInteger.class.equals(r5) || java.util.concurrent.atomic.AtomicBoolean.class.equals(r5)) == false) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void computeFields(java.lang.Class<?> r18, java.lang.reflect.Type r19, com.alibaba.fastjson.PropertyNamingStrategy r20, java.util.List<com.alibaba.fastjson.util.FieldInfo> r21, java.lang.reflect.Field[] r22) {
        /*
            r0 = r20
            r1 = r22
            int r2 = r1.length
            r4 = 0
        L_0x0006:
            if (r4 >= r2) goto L_0x00d2
            r8 = r1[r4]
            int r5 = r8.getModifiers()
            r6 = r5 & 8
            if (r6 == 0) goto L_0x0016
        L_0x0012:
            r5 = r21
            goto L_0x00ce
        L_0x0016:
            r5 = r5 & 16
            r6 = 1
            if (r5 == 0) goto L_0x004e
            java.lang.Class r5 = r8.getType()
            java.lang.Class<java.util.Map> r7 = java.util.Map.class
            boolean r7 = r7.isAssignableFrom(r5)
            if (r7 != 0) goto L_0x004a
            java.lang.Class<java.util.Collection> r7 = java.util.Collection.class
            boolean r7 = r7.isAssignableFrom(r5)
            if (r7 != 0) goto L_0x004a
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r7 = java.util.concurrent.atomic.AtomicLong.class
            boolean r7 = r7.equals(r5)
            if (r7 != 0) goto L_0x004a
            java.lang.Class<java.util.concurrent.atomic.AtomicInteger> r7 = java.util.concurrent.atomic.AtomicInteger.class
            boolean r7 = r7.equals(r5)
            if (r7 != 0) goto L_0x004a
            java.lang.Class<java.util.concurrent.atomic.AtomicBoolean> r7 = java.util.concurrent.atomic.AtomicBoolean.class
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            r5 = 0
            goto L_0x004b
        L_0x004a:
            r5 = 1
        L_0x004b:
            if (r5 != 0) goto L_0x004e
            goto L_0x0012
        L_0x004e:
            java.util.Iterator r5 = r21.iterator()
        L_0x0052:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x006b
            java.lang.Object r7 = r5.next()
            com.alibaba.fastjson.util.FieldInfo r7 = (com.alibaba.fastjson.util.FieldInfo) r7
            java.lang.String r7 = r7.name
            java.lang.String r9 = r8.getName()
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0052
            goto L_0x006c
        L_0x006b:
            r6 = 0
        L_0x006c:
            if (r6 == 0) goto L_0x006f
            goto L_0x0012
        L_0x006f:
            java.lang.String r5 = r8.getName()
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r6 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r6 = r8.getAnnotation(r6)
            r15 = r6
            com.alibaba.fastjson.annotation.JSONField r15 = (com.alibaba.fastjson.annotation.JSONField) r15
            if (r15 == 0) goto L_0x00ab
            boolean r6 = r15.deserialize()
            if (r6 != 0) goto L_0x0085
            goto L_0x0012
        L_0x0085:
            int r6 = r15.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r7 = r15.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r7)
            com.alibaba.fastjson.parser.Feature[] r9 = r15.parseFeatures()
            int r9 = com.alibaba.fastjson.parser.Feature.of(r9)
            java.lang.String r10 = r15.name()
            int r10 = r10.length()
            if (r10 == 0) goto L_0x00a7
            java.lang.String r5 = r15.name()
        L_0x00a7:
            r11 = r6
            r12 = r7
            r13 = r9
            goto L_0x00ae
        L_0x00ab:
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x00ae:
            if (r0 == 0) goto L_0x00b4
            java.lang.String r5 = r0.translate(r5)
        L_0x00b4:
            r6 = r5
            com.alibaba.fastjson.util.FieldInfo r14 = new com.alibaba.fastjson.util.FieldInfo
            r7 = 0
            r16 = 0
            r17 = 0
            r5 = r14
            r9 = r18
            r10 = r19
            r3 = r14
            r14 = r16
            r16 = r17
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r5 = r21
            add(r5, r3)
        L_0x00ce:
            int r4 = r4 + 1
            goto L_0x0006
        L_0x00d2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.computeFields(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, java.util.List, java.lang.reflect.Field[]):void");
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        int length = constructorArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = constructorArr[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructorArr) {
        boolean z;
        Constructor<?> constructor = null;
        for (Constructor<?> constructor2 : constructorArr) {
            if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                if (constructor == null) {
                    constructor = constructor2;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Annotation[][] parameterAnnotations = constructor3.getParameterAnnotations();
            if (parameterAnnotations.length != 0) {
                int length = parameterAnnotations.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        break;
                    }
                    Annotation[] annotationArr = parameterAnnotations[i];
                    int length2 = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = false;
                            break;
                        } else if (annotationArr[i2] instanceof JSONField) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    continue;
                } else if (constructor == null) {
                    constructor = constructor3;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        return constructor != null ? constructor : constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((JSONCreator) method2.getAnnotation(JSONCreator.class)) != null) {
                if (method == null) {
                    method = method2;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        return getBuilderClass((Class<?>) null, jSONType);
    }

    public static Class<?> getBuilderClass(Class<?> cls, JSONType jSONType) {
        Class<?> builder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (jSONType == null || (builder = jSONType.builder()) == Void.class) {
            return null;
        }
        return builder;
    }
}
