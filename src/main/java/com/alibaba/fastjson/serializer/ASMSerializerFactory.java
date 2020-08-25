package com.alibaba.fastjson.serializer;

import cn.qqtheme.framework.BuildConfig;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.util.h;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ASMSerializerFactory implements Opcodes {
    static final String JSONSerializer = ASMUtils.type(JSONSerializer.class);
    static final String JavaBeanSerializer = ASMUtils.type(JavaBeanSerializer.class);
    static final String JavaBeanSerializer_desc = ("L" + ASMUtils.type(JavaBeanSerializer.class) + h.f735b);
    static final String ObjectSerializer = ASMUtils.type(ObjectSerializer.class);
    static final String ObjectSerializer_desc = ("L" + ObjectSerializer + h.f735b);
    static final String SerialContext_desc = ASMUtils.desc((Class<?>) SerialContext.class);
    static final String SerializeFilterable_desc = ASMUtils.desc((Class<?>) SerializeFilterable.class);
    static final String SerializeWriter = ASMUtils.type(SerializeWriter.class);
    static final String SerializeWriter_desc = ("L" + SerializeWriter + h.f735b);
    protected final ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();

    static class Context {
        static final int features = 5;
        static int fieldName = 6;
        static final int obj = 2;
        static int original = 7;
        static final int paramFieldName = 3;
        static final int paramFieldType = 4;
        static int processValue = 8;
        static final int serializer = 1;
        /* access modifiers changed from: private */
        public final SerializeBeanInfo beanInfo;
        /* access modifiers changed from: private */
        public final String className;
        private final FieldInfo[] getters;
        /* access modifiers changed from: private */
        public final boolean nonContext;
        /* access modifiers changed from: private */
        public int variantIndex = 9;
        private Map<String, Integer> variants = new HashMap();
        /* access modifiers changed from: private */
        public final boolean writeDirect;

        public Context(FieldInfo[] fieldInfoArr, SerializeBeanInfo serializeBeanInfo, String str, boolean z, boolean z2) {
            this.getters = fieldInfoArr;
            this.className = str;
            this.beanInfo = serializeBeanInfo;
            this.writeDirect = z;
            this.nonContext = z2 || serializeBeanInfo.beanType.isEnum();
        }

        public int var(String str) {
            if (this.variants.get(str) == null) {
                Map<String, Integer> map = this.variants;
                int i = this.variantIndex;
                this.variantIndex = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return this.variants.get(str).intValue();
        }

        public int var(String str, int i) {
            if (this.variants.get(str) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return this.variants.get(str).intValue();
        }

        public int getFieldOrinal(String str) {
            int length = this.getters.length;
            for (int i = 0; i < length; i++) {
                if (this.getters[i].name.equals(str)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x04d8  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0557  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x055e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alibaba.fastjson.serializer.JavaBeanSerializer createJavaBeanSerializer(com.alibaba.fastjson.serializer.SerializeBeanInfo r30) throws java.lang.Exception {
        /*
            r29 = this;
            r0 = r29
            r7 = r30
            java.lang.Class<?> r8 = r7.beanType
            boolean r1 = r8.isPrimitive()
            if (r1 != 0) goto L_0x0621
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r1 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r1 = com.alibaba.fastjson.util.TypeUtils.getAnnotation(r8, r1)
            r9 = r1
            com.alibaba.fastjson.annotation.JSONType r9 = (com.alibaba.fastjson.annotation.JSONType) r9
            com.alibaba.fastjson.util.FieldInfo[] r10 = r7.fields
            int r1 = r10.length
            r11 = 0
            r2 = 0
        L_0x001a:
            if (r2 >= r1) goto L_0x003b
            r3 = r10[r2]
            java.lang.reflect.Field r4 = r3.field
            if (r4 != 0) goto L_0x0038
            java.lang.reflect.Method r4 = r3.method
            if (r4 == 0) goto L_0x0038
            java.lang.reflect.Method r3 = r3.method
            java.lang.Class r3 = r3.getDeclaringClass()
            boolean r3 = r3.isInterface()
            if (r3 == 0) goto L_0x0038
            com.alibaba.fastjson.serializer.JavaBeanSerializer r1 = new com.alibaba.fastjson.serializer.JavaBeanSerializer
            r1.<init>((com.alibaba.fastjson.serializer.SerializeBeanInfo) r7)
            return r1
        L_0x0038:
            int r2 = r2 + 1
            goto L_0x001a
        L_0x003b:
            com.alibaba.fastjson.util.FieldInfo[] r12 = r7.sortedFields
            com.alibaba.fastjson.util.FieldInfo[] r1 = r7.sortedFields
            com.alibaba.fastjson.util.FieldInfo[] r2 = r7.fields
            r13 = 1
            if (r1 != r2) goto L_0x0046
            r14 = 1
            goto L_0x0047
        L_0x0046:
            r14 = 0
        L_0x0047:
            int r1 = r12.length
            r2 = 256(0x100, float:3.59E-43)
            if (r1 <= r2) goto L_0x0052
            com.alibaba.fastjson.serializer.JavaBeanSerializer r1 = new com.alibaba.fastjson.serializer.JavaBeanSerializer
            r1.<init>((com.alibaba.fastjson.serializer.SerializeBeanInfo) r7)
            return r1
        L_0x0052:
            int r1 = r12.length
            r2 = 0
        L_0x0054:
            if (r2 >= r1) goto L_0x006f
            r3 = r12[r2]
            java.lang.reflect.Member r3 = r3.getMember()
            java.lang.String r3 = r3.getName()
            boolean r3 = com.alibaba.fastjson.util.ASMUtils.checkName(r3)
            if (r3 != 0) goto L_0x006c
            com.alibaba.fastjson.serializer.JavaBeanSerializer r1 = new com.alibaba.fastjson.serializer.JavaBeanSerializer
            r1.<init>((com.alibaba.fastjson.serializer.SerializeBeanInfo) r7)
            return r1
        L_0x006c:
            int r2 = r2 + 1
            goto L_0x0054
        L_0x006f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ASMSerializer_"
            r1.append(r2)
            java.util.concurrent.atomic.AtomicLong r2 = r0.seed
            long r2 = r2.incrementAndGet()
            r1.append(r2)
            java.lang.String r2 = "_"
            r1.append(r2)
            java.lang.String r2 = r8.getSimpleName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Class<com.alibaba.fastjson.serializer.ASMSerializerFactory> r2 = com.alibaba.fastjson.serializer.ASMSerializerFactory.class
            java.lang.Package r2 = r2.getPackage()
            if (r2 == 0) goto L_0x00d1
            java.lang.String r2 = r2.getName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = 46
            r5 = 47
            java.lang.String r4 = r2.replace(r4, r5)
            r3.append(r4)
            java.lang.String r4 = "/"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.String r2 = "."
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r5 = r1
            r6 = r3
            goto L_0x00d3
        L_0x00d1:
            r5 = r1
            r6 = r5
        L_0x00d3:
            java.lang.Class<com.alibaba.fastjson.serializer.ASMSerializerFactory> r1 = com.alibaba.fastjson.serializer.ASMSerializerFactory.class
            java.lang.Package r1 = r1.getPackage()
            r1.getName()
            com.alibaba.fastjson.asm.ClassWriter r4 = new com.alibaba.fastjson.asm.ClassWriter
            r4.<init>()
            r16 = 49
            r17 = 33
            java.lang.String r19 = JavaBeanSerializer
            java.lang.String[] r1 = new java.lang.String[r13]
            java.lang.String r2 = ObjectSerializer
            r1[r11] = r2
            r15 = r4
            r18 = r6
            r20 = r1
            r15.visit(r16, r17, r18, r19, r20)
            int r1 = r12.length
            r2 = 0
        L_0x00f7:
            if (r2 >= r1) goto L_0x017b
            r3 = r12[r2]
            java.lang.Class<?> r15 = r3.fieldClass
            boolean r15 = r15.isPrimitive()
            if (r15 != 0) goto L_0x0171
            java.lang.Class<?> r15 = r3.fieldClass
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            if (r15 != r11) goto L_0x010a
            goto L_0x0171
        L_0x010a:
            com.alibaba.fastjson.asm.FieldWriter r11 = new com.alibaba.fastjson.asm.FieldWriter
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r13 = r3.name
            r15.append(r13)
            java.lang.String r13 = "_asm_fieldType"
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            java.lang.String r15 = "Ljava/lang/reflect/Type;"
            r22 = r1
            r1 = 1
            r11.<init>(r4, r1, r13, r15)
            r11.visitEnd()
            java.lang.Class<java.util.List> r1 = java.util.List.class
            java.lang.Class<?> r11 = r3.fieldClass
            boolean r1 = r1.isAssignableFrom(r11)
            if (r1 == 0) goto L_0x0152
            com.alibaba.fastjson.asm.FieldWriter r1 = new com.alibaba.fastjson.asm.FieldWriter
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r13 = r3.name
            r11.append(r13)
            java.lang.String r13 = "_asm_list_item_ser_"
            r11.append(r13)
            java.lang.String r11 = r11.toString()
            java.lang.String r13 = ObjectSerializer_desc
            r15 = 1
            r1.<init>(r4, r15, r11, r13)
            r1.visitEnd()
        L_0x0152:
            com.alibaba.fastjson.asm.FieldWriter r1 = new com.alibaba.fastjson.asm.FieldWriter
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r3 = r3.name
            r11.append(r3)
            java.lang.String r3 = "_asm_ser_"
            r11.append(r3)
            java.lang.String r3 = r11.toString()
            java.lang.String r11 = ObjectSerializer_desc
            r13 = 1
            r1.<init>(r4, r13, r3, r11)
            r1.visitEnd()
            goto L_0x0173
        L_0x0171:
            r22 = r1
        L_0x0173:
            int r2 = r2 + 1
            r1 = r22
            r11 = 0
            r13 = 1
            goto L_0x00f7
        L_0x017b:
            com.alibaba.fastjson.asm.MethodWriter r1 = new com.alibaba.fastjson.asm.MethodWriter
            r17 = 1
            java.lang.String r18 = "<init>"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "("
            r2.append(r3)
            java.lang.Class<com.alibaba.fastjson.serializer.SerializeBeanInfo> r3 = com.alibaba.fastjson.serializer.SerializeBeanInfo.class
            java.lang.String r3 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r3)
            r2.append(r3)
            java.lang.String r3 = ")V"
            r2.append(r3)
            java.lang.String r19 = r2.toString()
            r20 = 0
            r21 = 0
            r15 = r1
            r16 = r4
            r15.<init>(r16, r17, r18, r19, r20, r21)
            r11 = 25
            r2 = 0
            r1.visitVarInsn(r11, r2)
            r2 = 1
            r1.visitVarInsn(r11, r2)
            java.lang.String r2 = JavaBeanSerializer
            java.lang.String r3 = "<init>"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r15 = "("
            r13.append(r15)
            java.lang.Class<com.alibaba.fastjson.serializer.SerializeBeanInfo> r15 = com.alibaba.fastjson.serializer.SerializeBeanInfo.class
            java.lang.String r15 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r15)
            r13.append(r15)
            java.lang.String r15 = ")V"
            r13.append(r15)
            java.lang.String r13 = r13.toString()
            r15 = 183(0xb7, float:2.56E-43)
            r1.visitMethodInsn(r15, r2, r3, r13)
            r2 = 0
        L_0x01d7:
            int r3 = r12.length
            if (r2 >= r3) goto L_0x0260
            r3 = r12[r2]
            java.lang.Class<?> r13 = r3.fieldClass
            boolean r13 = r13.isPrimitive()
            if (r13 != 0) goto L_0x0256
            java.lang.Class<?> r13 = r3.fieldClass
            java.lang.Class<java.lang.String> r15 = java.lang.String.class
            if (r13 != r15) goto L_0x01ef
            r24 = r4
            r15 = 183(0xb7, float:2.56E-43)
            goto L_0x0258
        L_0x01ef:
            r13 = 0
            r1.visitVarInsn(r11, r13)
            java.lang.reflect.Method r13 = r3.method
            if (r13 == 0) goto L_0x0221
            java.lang.Class<?> r13 = r3.declaringClass
            java.lang.String r13 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r13)
            com.alibaba.fastjson.asm.Type r13 = com.alibaba.fastjson.asm.Type.getType(r13)
            r1.visitLdcInsn(r13)
            java.lang.reflect.Method r13 = r3.method
            java.lang.String r13 = r13.getName()
            r1.visitLdcInsn(r13)
            r13 = 184(0xb8, float:2.58E-43)
            java.lang.Class<com.alibaba.fastjson.util.ASMUtils> r15 = com.alibaba.fastjson.util.ASMUtils.class
            java.lang.String r15 = com.alibaba.fastjson.util.ASMUtils.type(r15)
            java.lang.String r11 = "getMethodType"
            r24 = r4
            java.lang.String r4 = "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;"
            r1.visitMethodInsn(r13, r15, r11, r4)
            r15 = 183(0xb7, float:2.56E-43)
            goto L_0x023b
        L_0x0221:
            r24 = r4
            r4 = 25
            r11 = 0
            r1.visitVarInsn(r4, r11)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            r1.visitLdcInsn(r4)
            java.lang.String r4 = JavaBeanSerializer
            java.lang.String r11 = "getFieldType"
            java.lang.String r13 = "(I)Ljava/lang/reflect/Type;"
            r15 = 183(0xb7, float:2.56E-43)
            r1.visitMethodInsn(r15, r4, r11, r13)
        L_0x023b:
            r4 = 181(0xb5, float:2.54E-43)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r3 = r3.name
            r11.append(r3)
            java.lang.String r3 = "_asm_fieldType"
            r11.append(r3)
            java.lang.String r3 = r11.toString()
            java.lang.String r11 = "Ljava/lang/reflect/Type;"
            r1.visitFieldInsn(r4, r6, r3, r11)
            goto L_0x0258
        L_0x0256:
            r24 = r4
        L_0x0258:
            int r2 = r2 + 1
            r4 = r24
            r11 = 25
            goto L_0x01d7
        L_0x0260:
            r24 = r4
            r11 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r11)
            r13 = 4
            r1.visitMaxs(r13, r13)
            r1.visitEnd()
            if (r9 == 0) goto L_0x0284
            com.alibaba.fastjson.serializer.SerializerFeature[] r1 = r9.serialzeFeatures()
            int r2 = r1.length
            r3 = 0
        L_0x0276:
            if (r3 >= r2) goto L_0x0284
            r4 = r1[r3]
            com.alibaba.fastjson.serializer.SerializerFeature r15 = com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect
            if (r4 != r15) goto L_0x0281
            r22 = 1
            goto L_0x0286
        L_0x0281:
            int r3 = r3 + 1
            goto L_0x0276
        L_0x0284:
            r22 = 0
        L_0x0286:
            r15 = 0
        L_0x0287:
            r4 = 7
            r3 = 192(0xc0, float:2.69E-43)
            r2 = 180(0xb4, float:2.52E-43)
            r1 = 3
            r13 = 2
            if (r15 >= r1) goto L_0x04cd
            if (r15 != 0) goto L_0x029b
            java.lang.String r16 = "write"
            r18 = r16
            r23 = r22
            r16 = 1
            goto L_0x02af
        L_0x029b:
            r1 = 1
            if (r15 != r1) goto L_0x02a7
            java.lang.String r1 = "writeNormal"
            r18 = r1
            r23 = r22
            r16 = 0
            goto L_0x02af
        L_0x02a7:
            java.lang.String r1 = "writeDirectNonContext"
            r18 = r1
            r16 = 1
            r23 = 1
        L_0x02af:
            com.alibaba.fastjson.serializer.ASMSerializerFactory$Context r1 = new com.alibaba.fastjson.serializer.ASMSerializerFactory$Context
            r25 = r1
            r2 = r12
            r3 = r30
            r4 = r6
            r26 = r5
            r5 = r16
            r27 = r6
            r6 = r23
            r1.<init>(r2, r3, r4, r5, r6)
            com.alibaba.fastjson.asm.MethodWriter r1 = new com.alibaba.fastjson.asm.MethodWriter
            r17 = 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "(L"
            r2.append(r3)
            java.lang.String r3 = JSONSerializer
            r2.append(r3)
            java.lang.String r3 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r2.append(r3)
            java.lang.String r19 = r2.toString()
            r20 = 0
            java.lang.String r2 = "java/io/IOException"
            java.lang.String[] r21 = new java.lang.String[]{r2}
            r2 = r15
            r15 = r1
            r16 = r24
            r15.<init>(r16, r17, r18, r19, r20, r21)
            com.alibaba.fastjson.asm.Label r3 = new com.alibaba.fastjson.asm.Label
            r3.<init>()
            r4 = 25
            r1.visitVarInsn(r4, r13)
            r5 = 199(0xc7, float:2.79E-43)
            r1.visitJumpInsn(r5, r3)
            r5 = 1
            r1.visitVarInsn(r4, r5)
            java.lang.String r6 = JSONSerializer
            java.lang.String r15 = "writeNull"
            java.lang.String r13 = "()V"
            r4 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r4, r6, r15, r13)
            r1.visitInsn(r11)
            r1.visitLabel(r3)
            r3 = 25
            r1.visitVarInsn(r3, r5)
            java.lang.String r3 = JSONSerializer
            java.lang.String r5 = "out"
            java.lang.String r6 = SerializeWriter_desc
            r13 = 180(0xb4, float:2.52E-43)
            r1.visitFieldInsn(r13, r3, r5, r6)
            java.lang.String r3 = "out"
            r5 = r25
            int r3 = r5.var(r3)
            r6 = 58
            r1.visitVarInsn(r6, r3)
            if (r14 != 0) goto L_0x039e
            boolean r13 = r5.writeDirect
            if (r13 != 0) goto L_0x039e
            if (r9 == 0) goto L_0x033e
            boolean r13 = r9.alphabetic()
            if (r13 == 0) goto L_0x039e
        L_0x033e:
            com.alibaba.fastjson.asm.Label r13 = new com.alibaba.fastjson.asm.Label
            r13.<init>()
            java.lang.String r15 = "out"
            int r15 = r5.var(r15)
            r11 = 25
            r1.visitVarInsn(r11, r15)
            java.lang.String r15 = SerializeWriter
            java.lang.String r3 = "isSortField"
            java.lang.String r6 = "()Z"
            r1.visitMethodInsn(r4, r15, r3, r6)
            r3 = 154(0x9a, float:2.16E-43)
            r1.visitJumpInsn(r3, r13)
            r3 = 0
            r1.visitVarInsn(r11, r3)
            r3 = 1
            r1.visitVarInsn(r11, r3)
            r3 = 2
            r1.visitVarInsn(r11, r3)
            r15 = 3
            r1.visitVarInsn(r11, r15)
            r3 = 4
            r1.visitVarInsn(r11, r3)
            r3 = 21
            r6 = 5
            r1.visitVarInsn(r3, r6)
            java.lang.String r3 = "writeUnsorted"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r11 = "(L"
            r6.append(r11)
            java.lang.String r11 = JSONSerializer
            r6.append(r11)
            java.lang.String r11 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            r11 = r27
            r1.visitMethodInsn(r4, r11, r3, r6)
            r3 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r3)
            r1.visitLabel(r13)
            goto L_0x03a1
        L_0x039e:
            r11 = r27
            r15 = 3
        L_0x03a1:
            boolean r3 = r5.writeDirect
            if (r3 == 0) goto L_0x0490
            if (r23 != 0) goto L_0x0490
            com.alibaba.fastjson.asm.Label r3 = new com.alibaba.fastjson.asm.Label
            r3.<init>()
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            r13 = 25
            r15 = 0
            r1.visitVarInsn(r13, r15)
            r15 = 1
            r1.visitVarInsn(r13, r15)
            java.lang.String r13 = JavaBeanSerializer
            java.lang.String r15 = "writeDirect"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r28 = r9
            java.lang.String r9 = "(L"
            r4.append(r9)
            java.lang.String r9 = JSONSerializer
            r4.append(r9)
            java.lang.String r9 = ";)Z"
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r9 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r9, r13, r15, r4)
            r4 = 154(0x9a, float:2.16E-43)
            r1.visitJumpInsn(r4, r6)
            r4 = 25
            r9 = 0
            r1.visitVarInsn(r4, r9)
            r9 = 1
            r1.visitVarInsn(r4, r9)
            r9 = 2
            r1.visitVarInsn(r4, r9)
            r9 = 3
            r1.visitVarInsn(r4, r9)
            r9 = 4
            r1.visitVarInsn(r4, r9)
            r4 = 21
            r9 = 5
            r1.visitVarInsn(r4, r9)
            java.lang.String r4 = "writeNormal"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r13 = "(L"
            r9.append(r13)
            java.lang.String r13 = JSONSerializer
            r9.append(r13)
            java.lang.String r13 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            r13 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r13, r11, r4, r9)
            r4 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r4)
            r1.visitLabel(r6)
            java.lang.String r4 = "out"
            int r4 = r5.var(r4)
            r6 = 25
            r1.visitVarInsn(r6, r4)
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect
            int r4 = r4.mask
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1.visitLdcInsn(r4)
            java.lang.String r4 = SerializeWriter
            java.lang.String r9 = "isEnabled"
            java.lang.String r13 = "(I)Z"
            r15 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r15, r4, r9, r13)
            r4 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r4, r3)
            r4 = 0
            r1.visitVarInsn(r6, r4)
            r4 = 1
            r1.visitVarInsn(r6, r4)
            r4 = 2
            r1.visitVarInsn(r6, r4)
            r9 = 3
            r1.visitVarInsn(r6, r9)
            r4 = 4
            r1.visitVarInsn(r6, r4)
            r6 = 21
            r9 = 5
            r1.visitVarInsn(r6, r9)
            java.lang.String r6 = "writeDirectNonContext"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r13 = "(L"
            r9.append(r13)
            java.lang.String r13 = JSONSerializer
            r9.append(r13)
            java.lang.String r13 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            r13 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r13, r11, r6, r9)
            r6 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r6)
            r1.visitLabel(r3)
            goto L_0x0493
        L_0x0490:
            r28 = r9
            r4 = 4
        L_0x0493:
            r3 = 25
            r6 = 2
            r1.visitVarInsn(r3, r6)
            java.lang.String r3 = com.alibaba.fastjson.util.ASMUtils.type(r8)
            r15 = 192(0xc0, float:2.69E-43)
            r1.visitTypeInsn(r15, r3)
            java.lang.String r3 = "entity"
            int r3 = r5.var(r3)
            r9 = 58
            r1.visitVarInsn(r9, r3)
            r0.generateWriteMethod(r8, r1, r12, r5)
            r3 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r3)
            int r3 = r5.variantIndex
            int r3 = r3 + r6
            r6 = 7
            r1.visitMaxs(r6, r3)
            r1.visitEnd()
            int r15 = r2 + 1
            r6 = r11
            r5 = r26
            r9 = r28
            r11 = 177(0xb1, float:2.48E-43)
            r13 = 4
            goto L_0x0287
        L_0x04cd:
            r26 = r5
            r11 = r6
            r6 = 7
            r9 = 3
            r13 = 180(0xb4, float:2.52E-43)
            r15 = 192(0xc0, float:2.69E-43)
            if (r14 != 0) goto L_0x0557
            com.alibaba.fastjson.serializer.ASMSerializerFactory$Context r14 = new com.alibaba.fastjson.serializer.ASMSerializerFactory$Context
            r5 = 0
            r1 = r14
            r2 = r12
            r3 = r30
            r4 = r11
            r9 = 7
            r6 = r22
            r1.<init>(r2, r3, r4, r5, r6)
            com.alibaba.fastjson.asm.MethodWriter r1 = new com.alibaba.fastjson.asm.MethodWriter
            r17 = 1
            java.lang.String r18 = "writeUnsorted"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "(L"
            r2.append(r3)
            java.lang.String r3 = JSONSerializer
            r2.append(r3)
            java.lang.String r3 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r2.append(r3)
            java.lang.String r19 = r2.toString()
            r20 = 0
            java.lang.String r2 = "java/io/IOException"
            java.lang.String[] r21 = new java.lang.String[]{r2}
            r5 = 192(0xc0, float:2.69E-43)
            r6 = 3
            r15 = r1
            r16 = r24
            r15.<init>(r16, r17, r18, r19, r20, r21)
            r2 = 1
            r3 = 25
            r1.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONSerializer
            java.lang.String r4 = "out"
            java.lang.String r15 = SerializeWriter_desc
            r1.visitFieldInsn(r13, r2, r4, r15)
            java.lang.String r2 = "out"
            int r2 = r14.var(r2)
            r4 = 58
            r1.visitVarInsn(r4, r2)
            r2 = 2
            r1.visitVarInsn(r3, r2)
            java.lang.String r3 = com.alibaba.fastjson.util.ASMUtils.type(r8)
            r1.visitTypeInsn(r5, r3)
            java.lang.String r3 = "entity"
            int r3 = r14.var(r3)
            r1.visitVarInsn(r4, r3)
            r0.generateWriteMethod(r8, r1, r10, r14)
            r3 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r3)
            int r3 = r14.variantIndex
            int r3 = r3 + r2
            r1.visitMaxs(r9, r3)
            r1.visitEnd()
            goto L_0x055b
        L_0x0557:
            r5 = 192(0xc0, float:2.69E-43)
            r6 = 3
            r9 = 7
        L_0x055b:
            r10 = 0
        L_0x055c:
            if (r10 >= r6) goto L_0x05fd
            if (r10 != 0) goto L_0x0568
            java.lang.String r1 = "writeAsArray"
            r18 = r1
            r15 = r22
            r14 = 1
            goto L_0x0579
        L_0x0568:
            r1 = 1
            if (r10 != r1) goto L_0x0573
            java.lang.String r1 = "writeAsArrayNormal"
            r18 = r1
            r15 = r22
            r14 = 0
            goto L_0x0579
        L_0x0573:
            java.lang.String r1 = "writeAsArrayNonContext"
            r18 = r1
            r14 = 1
            r15 = 1
        L_0x0579:
            com.alibaba.fastjson.serializer.ASMSerializerFactory$Context r4 = new com.alibaba.fastjson.serializer.ASMSerializerFactory$Context
            r1 = r4
            r2 = r12
            r3 = r30
            r9 = r4
            r4 = r11
            r5 = r14
            r14 = 3
            r6 = r15
            r1.<init>(r2, r3, r4, r5, r6)
            com.alibaba.fastjson.asm.MethodWriter r1 = new com.alibaba.fastjson.asm.MethodWriter
            r17 = 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "(L"
            r2.append(r3)
            java.lang.String r3 = JSONSerializer
            r2.append(r3)
            java.lang.String r3 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r2.append(r3)
            java.lang.String r19 = r2.toString()
            r20 = 0
            java.lang.String r2 = "java/io/IOException"
            java.lang.String[] r21 = new java.lang.String[]{r2}
            r15 = r1
            r16 = r24
            r15.<init>(r16, r17, r18, r19, r20, r21)
            r2 = 1
            r3 = 25
            r1.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONSerializer
            java.lang.String r4 = "out"
            java.lang.String r5 = SerializeWriter_desc
            r1.visitFieldInsn(r13, r2, r4, r5)
            java.lang.String r2 = "out"
            int r2 = r9.var(r2)
            r4 = 58
            r1.visitVarInsn(r4, r2)
            r2 = 2
            r1.visitVarInsn(r3, r2)
            java.lang.String r5 = com.alibaba.fastjson.util.ASMUtils.type(r8)
            r6 = 192(0xc0, float:2.69E-43)
            r1.visitTypeInsn(r6, r5)
            java.lang.String r5 = "entity"
            int r5 = r9.var(r5)
            r1.visitVarInsn(r4, r5)
            r0.generateWriteAsArray(r8, r1, r12, r9)
            r5 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r5)
            int r9 = r9.variantIndex
            int r9 = r9 + r2
            r15 = 7
            r1.visitMaxs(r15, r9)
            r1.visitEnd()
            int r10 = r10 + 1
            r5 = 192(0xc0, float:2.69E-43)
            r6 = 3
            r9 = 7
            goto L_0x055c
        L_0x05fd:
            byte[] r1 = r24.toByteArray()
            com.alibaba.fastjson.util.ASMClassLoader r2 = r0.classLoader
            int r3 = r1.length
            r4 = r26
            r5 = 0
            java.lang.Class r1 = r2.defineClassPublic(r4, r1, r5, r3)
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]
            java.lang.Class<com.alibaba.fastjson.serializer.SerializeBeanInfo> r4 = com.alibaba.fastjson.serializer.SerializeBeanInfo.class
            r3[r5] = r4
            java.lang.reflect.Constructor r1 = r1.getConstructor(r3)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r5] = r7
            java.lang.Object r1 = r1.newInstance(r2)
            com.alibaba.fastjson.serializer.JavaBeanSerializer r1 = (com.alibaba.fastjson.serializer.JavaBeanSerializer) r1
            return r1
        L_0x0621:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unsupportd class "
            r2.append(r3)
            java.lang.String r3 = r8.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ASMSerializerFactory.createJavaBeanSerializer(com.alibaba.fastjson.serializer.SerializeBeanInfo):com.alibaba.fastjson.serializer.JavaBeanSerializer");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v66, resolved type: java.lang.Class<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v67, resolved type: java.lang.Class<java.lang.Object>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateWriteAsArray(java.lang.Class<?> r21, com.alibaba.fastjson.asm.MethodVisitor r22, com.alibaba.fastjson.util.FieldInfo[] r23, com.alibaba.fastjson.serializer.ASMSerializerFactory.Context r24) throws java.lang.Exception {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            r2 = r23
            r3 = r24
            com.alibaba.fastjson.asm.Label r4 = new com.alibaba.fastjson.asm.Label
            r4.<init>()
            r5 = 1
            r6 = 25
            r1.visitVarInsn(r6, r5)
            r7 = 0
            r1.visitVarInsn(r6, r7)
            java.lang.String r8 = JSONSerializer
            java.lang.String r9 = "hasPropertyFilters"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "("
            r10.append(r11)
            java.lang.String r11 = SerializeFilterable_desc
            r10.append(r11)
            java.lang.String r11 = ")Z"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r11 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r11, r8, r9, r10)
            r8 = 154(0x9a, float:2.16E-43)
            r1.visitJumpInsn(r8, r4)
            r1.visitVarInsn(r6, r7)
            r1.visitVarInsn(r6, r5)
            r8 = 2
            r1.visitVarInsn(r6, r8)
            r8 = 3
            r1.visitVarInsn(r6, r8)
            r8 = 4
            r1.visitVarInsn(r6, r8)
            r9 = 21
            r10 = 5
            r1.visitVarInsn(r9, r10)
            java.lang.String r10 = JavaBeanSerializer
            java.lang.String r12 = "writeNoneASM"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "(L"
            r13.append(r14)
            java.lang.String r14 = JSONSerializer
            r13.append(r14)
            java.lang.String r14 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r14 = 183(0xb7, float:2.56E-43)
            r1.visitMethodInsn(r14, r10, r12, r13)
            r10 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r10)
            r1.visitLabel(r4)
            java.lang.String r4 = "out"
            int r4 = r3.var(r4)
            r1.visitVarInsn(r6, r4)
            r4 = 16
            r10 = 91
            r1.visitVarInsn(r4, r10)
            java.lang.String r10 = SerializeWriter
            java.lang.String r12 = "write"
            java.lang.String r13 = "(I)V"
            r1.visitMethodInsn(r11, r10, r12, r13)
            int r10 = r2.length
            r12 = 93
            if (r10 != 0) goto L_0x00b2
            java.lang.String r2 = "out"
            int r2 = r3.var(r2)
            r1.visitVarInsn(r6, r2)
            r1.visitVarInsn(r4, r12)
            java.lang.String r2 = SerializeWriter
            java.lang.String r3 = "write"
            java.lang.String r4 = "(I)V"
            r1.visitMethodInsn(r11, r2, r3, r4)
            return
        L_0x00b2:
            r13 = 0
        L_0x00b3:
            if (r13 >= r10) goto L_0x08bf
            int r14 = r10 + -1
            if (r13 != r14) goto L_0x00bc
            r14 = 93
            goto L_0x00be
        L_0x00bc:
            r14 = 44
        L_0x00be:
            r15 = r2[r13]
            java.lang.Class<?> r12 = r15.fieldClass
            java.lang.String r5 = r15.name
            r1.visitLdcInsn(r5)
            int r5 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r9 = 58
            r1.visitVarInsn(r9, r5)
            java.lang.Class r5 = java.lang.Byte.TYPE
            r9 = 89
            if (r12 == r5) goto L_0x0874
            java.lang.Class r5 = java.lang.Short.TYPE
            if (r12 == r5) goto L_0x0874
            java.lang.Class r5 = java.lang.Integer.TYPE
            if (r12 != r5) goto L_0x00de
            goto L_0x0874
        L_0x00de:
            java.lang.Class r5 = java.lang.Long.TYPE
            if (r12 != r5) goto L_0x0117
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r1.visitInsn(r9)
            r0._get(r1, r3, r15)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "writeLong"
            java.lang.String r12 = "(J)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "write"
            java.lang.String r12 = "(I)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
        L_0x0106:
            r5 = r0
            r16 = r10
            r17 = r13
            r0 = 16
            r2 = 25
            r6 = 0
            r7 = 21
        L_0x0112:
            r8 = 1
        L_0x0113:
            r10 = 182(0xb6, float:2.55E-43)
            goto L_0x08a9
        L_0x0117:
            java.lang.Class r5 = java.lang.Float.TYPE
            if (r12 != r5) goto L_0x0143
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r1.visitInsn(r9)
            r0._get(r1, r3, r15)
            r1.visitInsn(r8)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "writeFloat"
            java.lang.String r12 = "(FZ)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "write"
            java.lang.String r12 = "(I)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            goto L_0x0106
        L_0x0143:
            java.lang.Class r5 = java.lang.Double.TYPE
            if (r12 != r5) goto L_0x016f
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r1.visitInsn(r9)
            r0._get(r1, r3, r15)
            r1.visitInsn(r8)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "writeDouble"
            java.lang.String r12 = "(DZ)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "write"
            java.lang.String r12 = "(I)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            goto L_0x0106
        L_0x016f:
            java.lang.Class r5 = java.lang.Boolean.TYPE
            if (r12 != r5) goto L_0x0199
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r1.visitInsn(r9)
            r0._get(r1, r3, r15)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "write"
            java.lang.String r12 = "(Z)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r9 = "write"
            java.lang.String r12 = "(I)V"
            r1.visitMethodInsn(r11, r5, r9, r12)
            goto L_0x0106
        L_0x0199:
            java.lang.Class r5 = java.lang.Character.TYPE
            r8 = 184(0xb8, float:2.58E-43)
            if (r12 != r5) goto L_0x01c2
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r0._get(r1, r3, r15)
            java.lang.String r5 = "java/lang/Character"
            java.lang.String r9 = "toString"
            java.lang.String r12 = "(C)Ljava/lang/String;"
            r1.visitMethodInsn(r8, r5, r9, r12)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r8 = "writeString"
            java.lang.String r9 = "(Ljava/lang/String;C)V"
            r1.visitMethodInsn(r11, r5, r8, r9)
            goto L_0x0106
        L_0x01c2:
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r12 != r5) goto L_0x01e0
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r0._get(r1, r3, r15)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r8 = "writeString"
            java.lang.String r9 = "(Ljava/lang/String;C)V"
            r1.visitMethodInsn(r11, r5, r8, r9)
            goto L_0x0106
        L_0x01e0:
            boolean r5 = r12.isEnum()
            if (r5 == 0) goto L_0x020c
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r6, r5)
            r1.visitInsn(r9)
            r0._get(r1, r3, r15)
            java.lang.String r5 = SerializeWriter
            java.lang.String r8 = "writeEnum"
            java.lang.String r9 = "(Ljava/lang/Enum;)V"
            r1.visitMethodInsn(r11, r5, r8, r9)
            r1.visitVarInsn(r4, r14)
            java.lang.String r5 = SerializeWriter
            java.lang.String r8 = "write"
            java.lang.String r9 = "(I)V"
            r1.visitMethodInsn(r11, r5, r8, r9)
            goto L_0x0106
        L_0x020c:
            java.lang.Class<java.util.List> r5 = java.util.List.class
            boolean r5 = r5.isAssignableFrom(r12)
            r8 = 199(0xc7, float:2.79E-43)
            if (r5 == 0) goto L_0x05f2
            java.lang.reflect.Type r5 = r15.fieldType
            boolean r12 = r5 instanceof java.lang.Class
            if (r12 == 0) goto L_0x021f
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            goto L_0x0227
        L_0x021f:
            java.lang.reflect.ParameterizedType r5 = (java.lang.reflect.ParameterizedType) r5
            java.lang.reflect.Type[] r5 = r5.getActualTypeArguments()
            r5 = r5[r7]
        L_0x0227:
            boolean r12 = r5 instanceof java.lang.Class
            if (r12 == 0) goto L_0x0234
            r12 = r5
            java.lang.Class r12 = (java.lang.Class) r12
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            if (r12 != r7) goto L_0x0235
            r12 = 0
            goto L_0x0235
        L_0x0234:
            r12 = 0
        L_0x0235:
            r0._get(r1, r3, r15)
            r7 = 192(0xc0, float:2.69E-43)
            java.lang.String r9 = "java/util/List"
            r1.visitTypeInsn(r7, r9)
            java.lang.String r7 = "list"
            int r7 = r3.var(r7)
            r9 = 58
            r1.visitVarInsn(r9, r7)
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r12 != r7) goto L_0x0281
            boolean r7 = r24.writeDirect
            if (r7 == 0) goto L_0x0281
            java.lang.String r4 = "out"
            int r4 = r3.var(r4)
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = "list"
            int r4 = r3.var(r4)
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = SerializeWriter
            java.lang.String r5 = "write"
            java.lang.String r7 = "(Ljava/util/List;)V"
            r1.visitMethodInsn(r11, r4, r5, r7)
            r16 = r10
            r17 = r13
            r18 = r14
            r0 = 16
            r2 = 25
            r4 = 93
            r7 = 21
            r9 = 182(0xb6, float:2.55E-43)
            goto L_0x05d2
        L_0x0281:
            com.alibaba.fastjson.asm.Label r7 = new com.alibaba.fastjson.asm.Label
            r7.<init>()
            com.alibaba.fastjson.asm.Label r9 = new com.alibaba.fastjson.asm.Label
            r9.<init>()
            java.lang.String r4 = "list"
            int r4 = r3.var(r4)
            r1.visitVarInsn(r6, r4)
            r1.visitJumpInsn(r8, r9)
            java.lang.String r4 = "out"
            int r4 = r3.var(r4)
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = SerializeWriter
            java.lang.String r8 = "writeNull"
            java.lang.String r6 = "()V"
            r1.visitMethodInsn(r11, r4, r8, r6)
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r7)
            r1.visitLabel(r9)
            java.lang.String r4 = "list"
            int r4 = r3.var(r4)
            r6 = 25
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = "java/util/List"
            java.lang.String r8 = "size"
            java.lang.String r9 = "()I"
            r11 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r11, r4, r8, r9)
            r4 = 54
            java.lang.String r8 = "size"
            int r8 = r3.var(r8)
            r1.visitVarInsn(r4, r8)
            java.lang.String r4 = "out"
            int r4 = r3.var(r4)
            r1.visitVarInsn(r6, r4)
            r4 = 91
            r6 = 16
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = SerializeWriter
            java.lang.String r6 = "write"
            java.lang.String r8 = "(I)V"
            r9 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r9, r4, r6, r8)
            com.alibaba.fastjson.asm.Label r4 = new com.alibaba.fastjson.asm.Label
            r4.<init>()
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            com.alibaba.fastjson.asm.Label r8 = new com.alibaba.fastjson.asm.Label
            r8.<init>()
            r9 = 3
            r1.visitInsn(r9)
            r9 = 54
            java.lang.String r11 = "i"
            int r11 = r3.var(r11)
            r1.visitVarInsn(r9, r11)
            r1.visitLabel(r4)
            java.lang.String r9 = "i"
            int r9 = r3.var(r9)
            r11 = 21
            r1.visitVarInsn(r11, r9)
            java.lang.String r9 = "size"
            int r9 = r3.var(r9)
            r1.visitVarInsn(r11, r9)
            r9 = 162(0xa2, float:2.27E-43)
            r1.visitJumpInsn(r9, r8)
            java.lang.String r9 = "i"
            int r9 = r3.var(r9)
            r1.visitVarInsn(r11, r9)
            r9 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r9, r6)
            java.lang.String r9 = "out"
            int r9 = r3.var(r9)
            r11 = 25
            r1.visitVarInsn(r11, r9)
            r9 = 44
            r11 = 16
            r1.visitVarInsn(r11, r9)
            java.lang.String r9 = SerializeWriter
            java.lang.String r11 = "write"
            java.lang.String r2 = "(I)V"
            r16 = r10
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r9, r11, r2)
            r1.visitLabel(r6)
            java.lang.String r2 = "list"
            int r2 = r3.var(r2)
            r6 = 25
            r1.visitVarInsn(r6, r2)
            java.lang.String r2 = "i"
            int r2 = r3.var(r2)
            r6 = 21
            r1.visitVarInsn(r6, r2)
            java.lang.String r2 = "java/util/List"
            java.lang.String r6 = "get"
            java.lang.String r9 = "(I)Ljava/lang/Object;"
            r10 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r10, r2, r6, r9)
            java.lang.String r2 = "list_item"
            int r2 = r3.var(r2)
            r6 = 58
            r1.visitVarInsn(r6, r2)
            com.alibaba.fastjson.asm.Label r2 = new com.alibaba.fastjson.asm.Label
            r2.<init>()
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            java.lang.String r9 = "list_item"
            int r9 = r3.var(r9)
            r10 = 25
            r1.visitVarInsn(r10, r9)
            r9 = 199(0xc7, float:2.79E-43)
            r1.visitJumpInsn(r9, r6)
            java.lang.String r9 = "out"
            int r9 = r3.var(r9)
            r1.visitVarInsn(r10, r9)
            java.lang.String r9 = SerializeWriter
            java.lang.String r10 = "writeNull"
            java.lang.String r11 = "()V"
            r17 = r13
            r13 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r13, r9, r10, r11)
            r9 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r9, r2)
            r1.visitLabel(r6)
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            com.alibaba.fastjson.asm.Label r9 = new com.alibaba.fastjson.asm.Label
            r9.<init>()
            if (r12 == 0) goto L_0x0527
            int r10 = r12.getModifiers()
            boolean r10 = java.lang.reflect.Modifier.isPublic(r10)
            if (r10 == 0) goto L_0x0527
            java.lang.String r10 = "list_item"
            int r10 = r3.var(r10)
            r11 = 25
            r1.visitVarInsn(r11, r10)
            java.lang.String r10 = "java/lang/Object"
            java.lang.String r11 = "getClass"
            java.lang.String r13 = "()Ljava/lang/Class;"
            r18 = r14
            r14 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r14, r10, r11, r13)
            java.lang.String r10 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            com.alibaba.fastjson.asm.Type r10 = com.alibaba.fastjson.asm.Type.getType(r10)
            r1.visitLdcInsn(r10)
            r10 = 166(0xa6, float:2.33E-43)
            r1.visitJumpInsn(r10, r9)
            r0._getListFieldItemSer(r3, r1, r15, r12)
            java.lang.String r10 = "list_item_desc"
            int r10 = r3.var(r10)
            r11 = 58
            r1.visitVarInsn(r11, r10)
            com.alibaba.fastjson.asm.Label r10 = new com.alibaba.fastjson.asm.Label
            r10.<init>()
            com.alibaba.fastjson.asm.Label r11 = new com.alibaba.fastjson.asm.Label
            r11.<init>()
            boolean r13 = r24.writeDirect
            if (r13 == 0) goto L_0x04af
            java.lang.String r13 = "list_item_desc"
            int r13 = r3.var(r13)
            r14 = 25
            r1.visitVarInsn(r14, r13)
            r13 = 193(0xc1, float:2.7E-43)
            java.lang.String r14 = JavaBeanSerializer
            r1.visitTypeInsn(r13, r14)
            r13 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r13, r10)
            java.lang.String r13 = "list_item_desc"
            int r13 = r3.var(r13)
            r14 = 25
            r1.visitVarInsn(r14, r13)
            r13 = 192(0xc0, float:2.69E-43)
            java.lang.String r0 = JavaBeanSerializer
            r1.visitTypeInsn(r13, r0)
            r0 = 1
            r1.visitVarInsn(r14, r0)
            java.lang.String r13 = "list_item"
            int r13 = r3.var(r13)
            r1.visitVarInsn(r14, r13)
            boolean r13 = r24.nonContext
            if (r13 == 0) goto L_0x0459
            r1.visitInsn(r0)
            r19 = r7
            goto L_0x0471
        L_0x0459:
            java.lang.String r0 = "i"
            int r0 = r3.var(r0)
            r13 = 21
            r1.visitVarInsn(r13, r0)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r13 = "valueOf"
            java.lang.String r14 = "(I)Ljava/lang/Integer;"
            r19 = r7
            r7 = 184(0xb8, float:2.58E-43)
            r1.visitMethodInsn(r7, r0, r13, r14)
        L_0x0471:
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r1.visitLdcInsn(r0)
            int r0 = r15.serialzeFeatures
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.visitLdcInsn(r0)
            java.lang.String r0 = JavaBeanSerializer
            java.lang.String r7 = "writeAsArrayNonContext"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "(L"
            r13.append(r14)
            java.lang.String r14 = JSONSerializer
            r13.append(r14)
            java.lang.String r14 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r14 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r14, r0, r7, r13)
            r0 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r0, r11)
            r1.visitLabel(r10)
            goto L_0x04b1
        L_0x04af:
            r19 = r7
        L_0x04b1:
            java.lang.String r0 = "list_item_desc"
            int r0 = r3.var(r0)
            r7 = 25
            r1.visitVarInsn(r7, r0)
            r0 = 1
            r1.visitVarInsn(r7, r0)
            java.lang.String r10 = "list_item"
            int r10 = r3.var(r10)
            r1.visitVarInsn(r7, r10)
            boolean r7 = r24.nonContext
            if (r7 == 0) goto L_0x04d3
            r1.visitInsn(r0)
            goto L_0x04e9
        L_0x04d3:
            java.lang.String r0 = "i"
            int r0 = r3.var(r0)
            r7 = 21
            r1.visitVarInsn(r7, r0)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r7 = "valueOf"
            java.lang.String r10 = "(I)Ljava/lang/Integer;"
            r13 = 184(0xb8, float:2.58E-43)
            r1.visitMethodInsn(r13, r0, r7, r10)
        L_0x04e9:
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r1.visitLdcInsn(r0)
            int r0 = r15.serialzeFeatures
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.visitLdcInsn(r0)
            java.lang.String r0 = ObjectSerializer
            java.lang.String r7 = "write"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "(L"
            r10.append(r13)
            java.lang.String r13 = JSONSerializer
            r10.append(r13)
            java.lang.String r13 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r10.append(r13)
            java.lang.String r10 = r10.toString()
            r13 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r13, r0, r7, r10)
            r1.visitLabel(r11)
            r0 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r0, r6)
            goto L_0x052b
        L_0x0527:
            r19 = r7
            r18 = r14
        L_0x052b:
            r1.visitLabel(r9)
            r0 = 25
            r7 = 1
            r1.visitVarInsn(r0, r7)
            java.lang.String r9 = "list_item"
            int r9 = r3.var(r9)
            r1.visitVarInsn(r0, r9)
            boolean r0 = r24.nonContext
            if (r0 == 0) goto L_0x0549
            r1.visitInsn(r7)
            r7 = 21
            goto L_0x055f
        L_0x0549:
            java.lang.String r0 = "i"
            int r0 = r3.var(r0)
            r7 = 21
            r1.visitVarInsn(r7, r0)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r9 = "valueOf"
            java.lang.String r10 = "(I)Ljava/lang/Integer;"
            r11 = 184(0xb8, float:2.58E-43)
            r1.visitMethodInsn(r11, r0, r9, r10)
        L_0x055f:
            if (r12 == 0) goto L_0x058d
            int r0 = r12.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isPublic(r0)
            if (r0 == 0) goto L_0x058d
            java.lang.Class r5 = (java.lang.Class) r5
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r5)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r1.visitLdcInsn(r0)
            int r0 = r15.serialzeFeatures
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.visitLdcInsn(r0)
            java.lang.String r0 = JSONSerializer
            java.lang.String r5 = "writeWithFieldName"
            java.lang.String r9 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r0, r5, r9)
            goto L_0x0598
        L_0x058d:
            r10 = 182(0xb6, float:2.55E-43)
            java.lang.String r0 = JSONSerializer
            java.lang.String r5 = "writeWithFieldName"
            java.lang.String r9 = "(Ljava/lang/Object;Ljava/lang/Object;)V"
            r1.visitMethodInsn(r10, r0, r5, r9)
        L_0x0598:
            r1.visitLabel(r6)
            r1.visitLabel(r2)
            java.lang.String r0 = "i"
            int r0 = r3.var(r0)
            r2 = 1
            r1.visitIincInsn(r0, r2)
            r0 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r0, r4)
            r1.visitLabel(r8)
            java.lang.String r0 = "out"
            int r0 = r3.var(r0)
            r2 = 25
            r1.visitVarInsn(r2, r0)
            r0 = 16
            r4 = 93
            r1.visitVarInsn(r0, r4)
            java.lang.String r5 = SerializeWriter
            java.lang.String r6 = "write"
            java.lang.String r8 = "(I)V"
            r9 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r9, r5, r6, r8)
            r5 = r19
            r1.visitLabel(r5)
        L_0x05d2:
            java.lang.String r5 = "out"
            int r5 = r3.var(r5)
            r1.visitVarInsn(r2, r5)
            r14 = r18
            r1.visitVarInsn(r0, r14)
            java.lang.String r0 = SerializeWriter
            java.lang.String r2 = "write"
            java.lang.String r5 = "(I)V"
            r1.visitMethodInsn(r9, r0, r2, r5)
            r0 = 16
            r2 = 25
            r5 = r20
            r6 = 0
            goto L_0x0112
        L_0x05f2:
            r16 = r10
            r17 = r13
            r4 = 93
            r7 = 21
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            com.alibaba.fastjson.asm.Label r2 = new com.alibaba.fastjson.asm.Label
            r2.<init>()
            r5 = r20
            r5._get(r1, r3, r15)
            r6 = 89
            r1.visitInsn(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "field_"
            r6.append(r8)
            java.lang.Class<?> r8 = r15.fieldClass
            java.lang.String r8 = r8.getName()
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            int r6 = r3.var(r6)
            r8 = 58
            r1.visitVarInsn(r8, r6)
            r6 = 199(0xc7, float:2.79E-43)
            r1.visitJumpInsn(r6, r2)
            java.lang.String r6 = "out"
            int r6 = r3.var(r6)
            r8 = 25
            r1.visitVarInsn(r8, r6)
            java.lang.String r6 = SerializeWriter
            java.lang.String r8 = "writeNull"
            java.lang.String r9 = "()V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r6, r8, r9)
            r6 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r6, r0)
            r1.visitLabel(r2)
            com.alibaba.fastjson.asm.Label r2 = new com.alibaba.fastjson.asm.Label
            r2.<init>()
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "field_"
            r8.append(r9)
            java.lang.Class<?> r9 = r15.fieldClass
            java.lang.String r9 = r9.getName()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            int r8 = r3.var(r8)
            r9 = 25
            r1.visitVarInsn(r9, r8)
            java.lang.String r8 = "java/lang/Object"
            java.lang.String r9 = "getClass"
            java.lang.String r10 = "()Ljava/lang/Class;"
            r11 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r11, r8, r9, r10)
            java.lang.String r8 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            com.alibaba.fastjson.asm.Type r8 = com.alibaba.fastjson.asm.Type.getType(r8)
            r1.visitLdcInsn(r8)
            r8 = 166(0xa6, float:2.33E-43)
            r1.visitJumpInsn(r8, r6)
            r5._getFieldSer(r3, r1, r15)
            java.lang.String r8 = "fied_ser"
            int r8 = r3.var(r8)
            r9 = 58
            r1.visitVarInsn(r9, r8)
            com.alibaba.fastjson.asm.Label r8 = new com.alibaba.fastjson.asm.Label
            r8.<init>()
            com.alibaba.fastjson.asm.Label r9 = new com.alibaba.fastjson.asm.Label
            r9.<init>()
            boolean r10 = r24.writeDirect
            if (r10 == 0) goto L_0x0749
            int r10 = r12.getModifiers()
            boolean r10 = java.lang.reflect.Modifier.isPublic(r10)
            if (r10 == 0) goto L_0x0749
            java.lang.String r10 = "fied_ser"
            int r10 = r3.var(r10)
            r11 = 25
            r1.visitVarInsn(r11, r10)
            r10 = 193(0xc1, float:2.7E-43)
            java.lang.String r13 = JavaBeanSerializer
            r1.visitTypeInsn(r10, r13)
            r10 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r10, r8)
            java.lang.String r10 = "fied_ser"
            int r10 = r3.var(r10)
            r1.visitVarInsn(r11, r10)
            r10 = 192(0xc0, float:2.69E-43)
            java.lang.String r13 = JavaBeanSerializer
            r1.visitTypeInsn(r10, r13)
            r10 = 1
            r1.visitVarInsn(r11, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "field_"
            r10.append(r13)
            java.lang.Class<?> r13 = r15.fieldClass
            java.lang.String r13 = r13.getName()
            r10.append(r13)
            java.lang.String r10 = r10.toString()
            int r10 = r3.var(r10)
            r1.visitVarInsn(r11, r10)
            int r10 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r1.visitVarInsn(r11, r10)
            java.lang.String r10 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            com.alibaba.fastjson.asm.Type r10 = com.alibaba.fastjson.asm.Type.getType(r10)
            r1.visitLdcInsn(r10)
            int r10 = r15.serialzeFeatures
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r1.visitLdcInsn(r10)
            java.lang.String r10 = JavaBeanSerializer
            java.lang.String r11 = "writeAsArrayNonContext"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r4 = "(L"
            r13.append(r4)
            java.lang.String r4 = JSONSerializer
            r13.append(r4)
            java.lang.String r4 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r13.append(r4)
            java.lang.String r4 = r13.toString()
            r13 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r13, r10, r11, r4)
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r9)
            r1.visitLabel(r8)
        L_0x0749:
            java.lang.String r4 = "fied_ser"
            int r4 = r3.var(r4)
            r8 = 25
            r1.visitVarInsn(r8, r4)
            r4 = 1
            r1.visitVarInsn(r8, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r10 = "field_"
            r4.append(r10)
            java.lang.Class<?> r10 = r15.fieldClass
            java.lang.String r10 = r10.getName()
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            int r4 = r3.var(r4)
            r1.visitVarInsn(r8, r4)
            int r4 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r1.visitVarInsn(r8, r4)
            java.lang.String r4 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            com.alibaba.fastjson.asm.Type r4 = com.alibaba.fastjson.asm.Type.getType(r4)
            r1.visitLdcInsn(r4)
            int r4 = r15.serialzeFeatures
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1.visitLdcInsn(r4)
            java.lang.String r4 = ObjectSerializer
            java.lang.String r8 = "write"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "(L"
            r10.append(r11)
            java.lang.String r11 = JSONSerializer
            r10.append(r11)
            java.lang.String r11 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r11 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r11, r4, r8, r10)
            r1.visitLabel(r9)
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r2)
            r1.visitLabel(r6)
            java.lang.String r4 = r15.getFormat()
            r6 = 25
            r8 = 1
            r1.visitVarInsn(r6, r8)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "field_"
            r9.append(r10)
            java.lang.Class<?> r10 = r15.fieldClass
            java.lang.String r10 = r10.getName()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            int r9 = r3.var(r9)
            r1.visitVarInsn(r6, r9)
            if (r4 == 0) goto L_0x07f5
            r1.visitLdcInsn(r4)
            java.lang.String r4 = JSONSerializer
            java.lang.String r9 = "writeWithFormat"
            java.lang.String r10 = "(Ljava/lang/Object;Ljava/lang/String;)V"
            r11 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r11, r4, r9, r10)
            r6 = 0
            goto L_0x0851
        L_0x07f5:
            int r4 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r1.visitVarInsn(r6, r4)
            java.lang.reflect.Type r4 = r15.fieldType
            boolean r4 = r4 instanceof java.lang.Class
            if (r4 == 0) goto L_0x0819
            java.lang.reflect.Type r4 = r15.fieldType
            java.lang.Class r4 = (java.lang.Class) r4
            boolean r4 = r4.isPrimitive()
            if (r4 == 0) goto L_0x0819
            java.lang.String r4 = JSONSerializer
            java.lang.String r6 = "writeWithFieldName"
            java.lang.String r9 = "(Ljava/lang/Object;Ljava/lang/Object;)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r4, r6, r9)
            r6 = 0
            r11 = 182(0xb6, float:2.55E-43)
            goto L_0x0851
        L_0x0819:
            r4 = 25
            r6 = 0
            r1.visitVarInsn(r4, r6)
            r4 = 180(0xb4, float:2.52E-43)
            java.lang.String r9 = r24.className
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = r15.name
            r10.append(r11)
            java.lang.String r11 = "_asm_fieldType"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            java.lang.String r11 = "Ljava/lang/reflect/Type;"
            r1.visitFieldInsn(r4, r9, r10, r11)
            int r4 = r15.serialzeFeatures
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1.visitLdcInsn(r4)
            java.lang.String r4 = JSONSerializer
            java.lang.String r9 = "writeWithFieldName"
            java.lang.String r10 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r11 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r11, r4, r9, r10)
        L_0x0851:
            r1.visitLabel(r2)
            r1.visitLabel(r0)
            java.lang.String r0 = "out"
            int r0 = r3.var(r0)
            r2 = 25
            r1.visitVarInsn(r2, r0)
            r0 = 16
            r1.visitVarInsn(r0, r14)
            java.lang.String r0 = SerializeWriter
            java.lang.String r4 = "write"
            java.lang.String r9 = "(I)V"
            r1.visitMethodInsn(r11, r0, r4, r9)
            r0 = 16
            goto L_0x0113
        L_0x0874:
            r5 = r0
            r16 = r10
            r17 = r13
            r2 = 25
            r6 = 0
            r7 = 21
            r8 = 1
            java.lang.String r0 = "out"
            int r0 = r3.var(r0)
            r1.visitVarInsn(r2, r0)
            r0 = 89
            r1.visitInsn(r0)
            r5._get(r1, r3, r15)
            java.lang.String r0 = SerializeWriter
            java.lang.String r4 = "writeInt"
            java.lang.String r9 = "(I)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r0, r4, r9)
            r0 = 16
            r1.visitVarInsn(r0, r14)
            java.lang.String r4 = SerializeWriter
            java.lang.String r9 = "write"
            java.lang.String r11 = "(I)V"
            r1.visitMethodInsn(r10, r4, r9, r11)
        L_0x08a9:
            int r13 = r17 + 1
            r0 = r5
            r10 = r16
            r2 = r23
            r4 = 16
            r5 = 1
            r6 = 25
            r7 = 0
            r8 = 4
            r9 = 21
            r11 = 182(0xb6, float:2.55E-43)
            r12 = 93
            goto L_0x00b3
        L_0x08bf:
            r5 = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ASMSerializerFactory.generateWriteAsArray(java.lang.Class, com.alibaba.fastjson.asm.MethodVisitor, com.alibaba.fastjson.util.FieldInfo[], com.alibaba.fastjson.serializer.ASMSerializerFactory$Context):void");
    }

    private void generateWriteMethod(Class<?> cls, MethodVisitor methodVisitor, FieldInfo[] fieldInfoArr, Context context) throws Exception {
        int i;
        Class<?> cls2 = cls;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo[] fieldInfoArr2 = fieldInfoArr;
        Context context2 = context;
        Label label = new Label();
        int length = fieldInfoArr2.length;
        if (!context.writeDirect) {
            Label label2 = new Label();
            Label label3 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitLdcInsn(Integer.valueOf(SerializerFeature.PrettyFormat.mask));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor2.visitJumpInsn(Opcodes.IFNE, label3);
            boolean z = false;
            for (FieldInfo fieldInfo : fieldInfoArr2) {
                if (fieldInfo.method != null) {
                    z = true;
                }
            }
            if (z) {
                methodVisitor2.visitVarInsn(25, context2.var("out"));
                methodVisitor2.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreErrorGetter.mask));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
                methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label2);
            } else {
                methodVisitor2.visitJumpInsn(Opcodes.GOTO, label2);
            }
            methodVisitor2.visitLabel(label3);
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(21, 5);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESPECIAL, JavaBeanSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitInsn(Opcodes.RETURN);
            methodVisitor2.visitLabel(label2);
        }
        if (!context.nonContext) {
            Label label4 = new Label();
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(21, 5);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "writeReference", "(L" + JSONSerializer + ";Ljava/lang/Object;I)Z");
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label4);
            methodVisitor2.visitInsn(Opcodes.RETURN);
            methodVisitor2.visitLabel(label4);
        }
        String str = context.writeDirect ? context.nonContext ? "writeAsArrayNonContext" : "writeAsArray" : "writeAsArrayNormal";
        if ((context.beanInfo.features & SerializerFeature.BeanToArray.mask) == 0) {
            Label label5 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitLdcInsn(Integer.valueOf(SerializerFeature.BeanToArray.mask));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label5);
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(21, 5);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, context.className, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitInsn(Opcodes.RETURN);
            methodVisitor2.visitLabel(label5);
        } else {
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(21, 5);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, context.className, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitInsn(Opcodes.RETURN);
        }
        if (!context.nonContext) {
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "getContext", "()" + SerialContext_desc);
            methodVisitor2.visitVarInsn(58, context2.var("parent"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("parent"));
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitLdcInsn(Integer.valueOf(context.beanInfo.features));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "setContext", "(" + SerialContext_desc + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean z2 = (context.beanInfo.features & SerializerFeature.WriteClassName.mask) != 0;
        if (z2 || !context.writeDirect) {
            Label label6 = new Label();
            Label label7 = new Label();
            Label label8 = new Label();
            if (!z2) {
                methodVisitor2.visitVarInsn(25, 1);
                methodVisitor2.visitVarInsn(25, 4);
                methodVisitor2.visitVarInsn(25, 2);
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label7);
            }
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor2.visitJumpInsn(Opcodes.IF_ACMPEQ, label7);
            methodVisitor2.visitLabel(label8);
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(16, BuildConfig.VERSION_CODE);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            if (context.beanInfo.typeKey != null) {
                methodVisitor2.visitLdcInsn(context.beanInfo.typeKey);
            } else {
                methodVisitor2.visitInsn(1);
            }
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "writeClassName", "(L" + JSONSerializer + ";Ljava/lang/String;Ljava/lang/Object;)V");
            methodVisitor2.visitVarInsn(16, 44);
            methodVisitor2.visitJumpInsn(Opcodes.GOTO, label6);
            methodVisitor2.visitLabel(label7);
            methodVisitor2.visitVarInsn(16, BuildConfig.VERSION_CODE);
            methodVisitor2.visitLabel(label6);
        } else {
            methodVisitor2.visitVarInsn(16, BuildConfig.VERSION_CODE);
        }
        methodVisitor2.visitVarInsn(54, context2.var("seperator"));
        if (!context.writeDirect) {
            _before(methodVisitor2, context2);
        }
        if (!context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isNotWriteDefaultValue", "()Z");
            methodVisitor2.visitVarInsn(54, context2.var("notWriteDefaultValue"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "checkValue", "(" + SerializeFilterable_desc + ")Z");
            methodVisitor2.visitVarInsn(54, context2.var("checkValue"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "hasNameFilters", "(" + SerializeFilterable_desc + ")Z");
            methodVisitor2.visitVarInsn(54, context2.var("hasNameFilters"));
        }
        int i2 = 0;
        while (i2 < length) {
            FieldInfo fieldInfo2 = fieldInfoArr2[i2];
            Class<?> cls3 = fieldInfo2.fieldClass;
            methodVisitor2.visitLdcInsn(fieldInfo2.name);
            methodVisitor2.visitVarInsn(58, Context.fieldName);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                i = i2;
                _int(cls, methodVisitor, fieldInfo2, context, context2.var(cls3.getName()), 'I');
            } else {
                if (cls3 == Long.TYPE) {
                    _long(cls2, methodVisitor2, fieldInfo2, context2);
                } else if (cls3 == Float.TYPE) {
                    _float(cls2, methodVisitor2, fieldInfo2, context2);
                } else if (cls3 == Double.TYPE) {
                    _double(cls2, methodVisitor2, fieldInfo2, context2);
                } else if (cls3 == Boolean.TYPE) {
                    i = i2;
                    _int(cls, methodVisitor, fieldInfo2, context, context2.var("boolean"), 'Z');
                } else {
                    i = i2;
                    if (cls3 == Character.TYPE) {
                        _int(cls, methodVisitor, fieldInfo2, context, context2.var("char"), 'C');
                    } else if (cls3 == String.class) {
                        _string(cls2, methodVisitor2, fieldInfo2, context2);
                    } else if (cls3 == BigDecimal.class) {
                        _decimal(cls2, methodVisitor2, fieldInfo2, context2);
                    } else if (List.class.isAssignableFrom(cls3)) {
                        _list(cls2, methodVisitor2, fieldInfo2, context2);
                    } else if (cls3.isEnum()) {
                        _enum(cls2, methodVisitor2, fieldInfo2, context2);
                    } else {
                        _object(cls2, methodVisitor2, fieldInfo2, context2);
                    }
                }
                i = i2;
            }
            i2 = i + 1;
        }
        if (!context.writeDirect) {
            _after(methodVisitor2, context2);
        }
        Label label9 = new Label();
        Label label10 = new Label();
        methodVisitor2.visitVarInsn(21, context2.var("seperator"));
        methodVisitor2.visitIntInsn(16, BuildConfig.VERSION_CODE);
        methodVisitor2.visitJumpInsn(Opcodes.IF_ICMPNE, label9);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitVarInsn(16, BuildConfig.VERSION_CODE);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        methodVisitor2.visitLabel(label9);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitVarInsn(16, 125);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        methodVisitor2.visitLabel(label10);
        methodVisitor2.visitLabel(label);
        if (!context.nonContext) {
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("parent"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "setContext", "(" + SerialContext_desc + ")V");
        }
    }

    private void _object(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("object"));
        _filters(methodVisitor, fieldInfo, context, label);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitLabel(label);
    }

    private void _enum(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label3);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Enum");
        methodVisitor.visitVarInsn(58, context.var("enum"));
        _filters(methodVisitor, fieldInfo, context, label3);
        methodVisitor.visitVarInsn(25, context.var("enum"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
        methodVisitor.visitLabel(label);
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Enum", c.e, "()Ljava/lang/String;");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitInsn(3);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label3);
    }

    private void _int(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, int i, char c2) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, i);
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(21, i);
        String str = SerializeWriter;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeFieldValue", "(CLjava/lang/String;" + c2 + ")V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _long(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(55, context.var("long", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(22, context.var("long", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;J)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _float(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(56, context.var("float"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(23, context.var("float"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;F)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _double(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(57, context.var("double", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(24, context.var("double", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;D)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _get(MethodVisitor methodVisitor, Context context, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            methodVisitor.visitVarInsn(25, context.var("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            methodVisitor.visitMethodInsn(declaringClass.isInterface() ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL, ASMUtils.type(declaringClass), method.getName(), ASMUtils.desc(method));
            if (!method.getReturnType().equals(fieldInfo.fieldClass)) {
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(fieldInfo.fieldClass));
                return;
            }
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("entity"));
        Field field = fieldInfo.field;
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.type(fieldInfo.declaringClass), field.getName(), ASMUtils.desc(field.getType()));
        if (!field.getType().equals(fieldInfo.fieldClass)) {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(fieldInfo.fieldClass));
        }
    }

    private void _decimal(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("decimal"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label4);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLabel(label);
    }

    private void _string(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        if (fieldInfo.name.equals(context.beanInfo.typeKey)) {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("string"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("string"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        methodVisitor.visitLabel(label2);
        if ("trim".equals(fieldInfo.format)) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "trim", "()Ljava/lang/String;");
            methodVisitor.visitVarInsn(58, context.var("string"));
        }
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLabel(label);
    }

    private void _list(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label;
        Label label2;
        Label label3;
        int i;
        int i2;
        int i3;
        Label label4;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Context context2 = context;
        java.lang.reflect.Type collectionItemType = TypeUtils.getCollectionItemType(fieldInfo2.fieldType);
        Class<Serializable> cls2 = collectionItemType instanceof Class ? (Class) collectionItemType : null;
        if (cls2 == Object.class || cls2 == Serializable.class) {
            cls2 = null;
        }
        Label label5 = new Label();
        Label label6 = new Label();
        Label label7 = new Label();
        _nameApply(methodVisitor2, fieldInfo2, context2, label5);
        _get(methodVisitor2, context2, fieldInfo2);
        methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, "java/util/List");
        methodVisitor2.visitVarInsn(58, context2.var("list"));
        _filters(methodVisitor2, fieldInfo2, context2, label5);
        methodVisitor2.visitVarInsn(25, context2.var("list"));
        methodVisitor2.visitJumpInsn(Opcodes.IFNONNULL, label6);
        _if_write_null(methodVisitor2, fieldInfo2, context2);
        methodVisitor2.visitJumpInsn(Opcodes.GOTO, label7);
        methodVisitor2.visitLabel(label6);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitVarInsn(21, context2.var("seperator"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor2, context2);
        methodVisitor2.visitVarInsn(25, context2.var("list"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "size", "()I");
        methodVisitor2.visitVarInsn(54, context2.var("size"));
        Label label8 = new Label();
        Label label9 = new Label();
        methodVisitor2.visitVarInsn(21, context2.var("size"));
        methodVisitor2.visitInsn(3);
        methodVisitor2.visitJumpInsn(Opcodes.IF_ICMPNE, label8);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitLdcInsn("[]");
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(Ljava/lang/String;)V");
        methodVisitor2.visitJumpInsn(Opcodes.GOTO, label9);
        methodVisitor2.visitLabel(label8);
        if (!context.nonContext) {
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (collectionItemType != String.class || !context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(16, 91);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            Label label10 = new Label();
            Label label11 = new Label();
            Label label12 = new Label();
            methodVisitor2.visitInsn(3);
            methodVisitor2.visitVarInsn(54, context2.var("i"));
            methodVisitor2.visitLabel(label10);
            methodVisitor2.visitVarInsn(21, context2.var("i"));
            methodVisitor2.visitVarInsn(21, context2.var("size"));
            methodVisitor2.visitJumpInsn(Opcodes.IF_ICMPGE, label12);
            methodVisitor2.visitVarInsn(21, context2.var("i"));
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label11);
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(16, 44);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            methodVisitor2.visitLabel(label11);
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitVarInsn(21, context2.var("i"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
            methodVisitor2.visitVarInsn(58, context2.var("list_item"));
            Label label13 = new Label();
            Label label14 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var("list_item"));
            methodVisitor2.visitJumpInsn(Opcodes.IFNONNULL, label14);
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            label3 = label5;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeNull", "()V");
            methodVisitor2.visitJumpInsn(Opcodes.GOTO, label13);
            methodVisitor2.visitLabel(label14);
            Label label15 = new Label();
            Label label16 = new Label();
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                label2 = label7;
                label4 = label12;
                label = label9;
            } else {
                methodVisitor2.visitVarInsn(25, context2.var("list_item"));
                label2 = label7;
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) cls2)));
                methodVisitor2.visitJumpInsn(Opcodes.IF_ACMPNE, label16);
                _getListFieldItemSer(context2, methodVisitor2, fieldInfo2, cls2);
                methodVisitor2.visitVarInsn(58, context2.var("list_item_desc"));
                Label label17 = new Label();
                Label label18 = new Label();
                if (context.writeDirect) {
                    String str = (!context.nonContext || !context.writeDirect) ? "write" : "writeDirectNonContext";
                    methodVisitor2.visitVarInsn(25, context2.var("list_item_desc"));
                    methodVisitor2.visitTypeInsn(Opcodes.INSTANCEOF, JavaBeanSerializer);
                    methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label17);
                    methodVisitor2.visitVarInsn(25, context2.var("list_item_desc"));
                    label = label9;
                    methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
                    methodVisitor2.visitVarInsn(25, 1);
                    methodVisitor2.visitVarInsn(25, context2.var("list_item"));
                    if (context.nonContext) {
                        methodVisitor2.visitInsn(1);
                        label4 = label12;
                    } else {
                        methodVisitor2.visitVarInsn(21, context2.var("i"));
                        label4 = label12;
                        methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) cls2)));
                    methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                    methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodVisitor2.visitJumpInsn(Opcodes.GOTO, label18);
                    methodVisitor2.visitLabel(label17);
                } else {
                    label4 = label12;
                    label = label9;
                }
                methodVisitor2.visitVarInsn(25, context2.var("list_item_desc"));
                methodVisitor2.visitVarInsn(25, 1);
                methodVisitor2.visitVarInsn(25, context2.var("list_item"));
                if (context.nonContext) {
                    methodVisitor2.visitInsn(1);
                } else {
                    methodVisitor2.visitVarInsn(21, context2.var("i"));
                    methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) cls2)));
                methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodVisitor2.visitLabel(label18);
                methodVisitor2.visitJumpInsn(Opcodes.GOTO, label15);
            }
            methodVisitor2.visitLabel(label16);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("list_item"));
            if (context.nonContext) {
                methodVisitor2.visitInsn(1);
            } else {
                methodVisitor2.visitVarInsn(21, context2.var("i"));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) (Class) collectionItemType)));
                methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
            methodVisitor2.visitLabel(label15);
            methodVisitor2.visitLabel(label13);
            methodVisitor2.visitIincInsn(context2.var("i"), 1);
            methodVisitor2.visitJumpInsn(Opcodes.GOTO, label10);
            methodVisitor2.visitLabel(label4);
            i2 = 25;
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(16, 93);
            String str2 = SerializeWriter;
            i = Opcodes.INVOKEVIRTUAL;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, "write", "(I)V");
            i3 = 1;
        } else {
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(Ljava/util/List;)V");
            label3 = label5;
            label2 = label7;
            label = label9;
            i3 = 1;
            i2 = 25;
            i = Opcodes.INVOKEVIRTUAL;
        }
        methodVisitor2.visitVarInsn(i2, i3);
        methodVisitor2.visitMethodInsn(i, JSONSerializer, "popContext", "()V");
        methodVisitor2.visitLabel(label);
        _seperator(methodVisitor2, context2);
        methodVisitor2.visitLabel(label2);
        methodVisitor2.visitLabel(label3);
    }

    private void _filters(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (fieldInfo.fieldTransient) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.SkipTransientField.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _notWriteDefault(methodVisitor, fieldInfo, context, label);
        if (!context.writeDirect) {
            _apply(methodVisitor, fieldInfo, context);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            _processKey(methodVisitor, fieldInfo, context);
            _processValue(methodVisitor, fieldInfo, context, label);
        }
    }

    private void _nameApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (!context.writeDirect) {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, Context.fieldName);
            String str = JavaBeanSerializer;
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "applyName", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            _labelApply(methodVisitor, fieldInfo, context, label);
        }
        if (fieldInfo.field == null) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreNonFieldGetter.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
    }

    private void _labelApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(fieldInfo.label);
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "applyLabel", "(L" + JSONSerializer + ";Ljava/lang/String;)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
    }

    private void _writeObject(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Context context2 = context;
        String format = fieldInfo.getFormat();
        Class<?> cls = fieldInfo2.fieldClass;
        Label label2 = new Label();
        if (context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
        } else {
            methodVisitor2.visitVarInsn(25, Context.processValue);
        }
        methodVisitor2.visitInsn(89);
        methodVisitor2.visitVarInsn(58, context2.var("object"));
        methodVisitor2.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor2.visitJumpInsn(Opcodes.GOTO, label);
        methodVisitor2.visitLabel(label2);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitVarInsn(21, context2.var("seperator"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor2, context2);
        Label label3 = new Label();
        Label label4 = new Label();
        if (Modifier.isPublic(cls.getModifiers()) && !ParserConfig.isPrimitive2(cls)) {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
            methodVisitor2.visitJumpInsn(Opcodes.IF_ACMPNE, label4);
            _getFieldSer(context2, methodVisitor2, fieldInfo2);
            methodVisitor2.visitVarInsn(58, context2.var("fied_ser"));
            Label label5 = new Label();
            Label label6 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var("fied_ser"));
            methodVisitor2.visitTypeInsn(Opcodes.INSTANCEOF, JavaBeanSerializer);
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label5);
            boolean z = (fieldInfo2.serialzeFeatures & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
            boolean z2 = (fieldInfo2.serialzeFeatures & SerializerFeature.BeanToArray.mask) != 0;
            String str = (z || (context.nonContext && context.writeDirect)) ? z2 ? "writeAsArrayNonContext" : "writeDirectNonContext" : z2 ? "writeAsArray" : "write";
            methodVisitor2.visitVarInsn(25, context2.var("fied_ser"));
            methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            methodVisitor2.visitVarInsn(25, 0);
            String access$300 = context.className;
            methodVisitor2.visitFieldInsn(Opcodes.GETFIELD, access$300, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
            String str2 = JavaBeanSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitJumpInsn(Opcodes.GOTO, label6);
            methodVisitor2.visitLabel(label5);
            methodVisitor2.visitVarInsn(25, context2.var("fied_ser"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            methodVisitor2.visitVarInsn(25, 0);
            String access$3002 = context.className;
            methodVisitor2.visitFieldInsn(Opcodes.GETFIELD, access$3002, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
            String str3 = ObjectSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, str3, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitLabel(label6);
            methodVisitor2.visitJumpInsn(Opcodes.GOTO, label3);
        }
        methodVisitor2.visitLabel(label4);
        methodVisitor2.visitVarInsn(25, 1);
        if (context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
        } else {
            methodVisitor2.visitVarInsn(25, Context.processValue);
        }
        if (format != null) {
            methodVisitor2.visitLdcInsn(format);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            if (!(fieldInfo2.fieldType instanceof Class) || !((Class) fieldInfo2.fieldType).isPrimitive()) {
                if (fieldInfo2.fieldClass == String.class) {
                    methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) String.class)));
                } else {
                    methodVisitor2.visitVarInsn(25, 0);
                    String access$3003 = context.className;
                    methodVisitor2.visitFieldInsn(Opcodes.GETFIELD, access$3003, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            } else {
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            }
        }
        methodVisitor2.visitLabel(label3);
        _seperator(methodVisitor2, context2);
    }

    private void _before(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeBefore", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _after(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeAfter", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _notWriteDefault(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (!context.writeDirect) {
            Label label2 = new Label();
            methodVisitor.visitVarInsn(21, context.var("notWriteDefaultValue"));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label2);
            Class<?> cls = fieldInfo.fieldClass;
            if (cls == Boolean.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("boolean"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Byte.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("byte"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Short.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("short"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Integer.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("int"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Long.TYPE) {
                methodVisitor.visitVarInsn(22, context.var("long"));
                methodVisitor.visitInsn(9);
                methodVisitor.visitInsn(Opcodes.LCMP);
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Float.TYPE) {
                methodVisitor.visitVarInsn(23, context.var("float"));
                methodVisitor.visitInsn(11);
                methodVisitor.visitInsn(Opcodes.FCMPL);
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Double.TYPE) {
                methodVisitor.visitVarInsn(24, context.var("double"));
                methodVisitor.visitInsn(14);
                methodVisitor.visitInsn(Opcodes.DCMPL);
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            }
            methodVisitor.visitLabel(label2);
        }
    }

    private void _apply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "apply", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    private void _processValue(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Context context2 = context;
        Label label2 = new Label();
        Class<?> cls = fieldInfo2.fieldClass;
        if (cls.isPrimitive()) {
            Label label3 = new Label();
            methodVisitor2.visitVarInsn(21, context2.var("checkValue"));
            methodVisitor2.visitJumpInsn(Opcodes.IFNE, label3);
            methodVisitor2.visitInsn(1);
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(58, Context.processValue);
            methodVisitor2.visitJumpInsn(Opcodes.GOTO, label2);
            methodVisitor2.visitLabel(label3);
        }
        methodVisitor2.visitVarInsn(25, 0);
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitVarInsn(25, 0);
        methodVisitor2.visitLdcInsn(Integer.valueOf(context2.getFieldOrinal(fieldInfo2.name)));
        String str = JavaBeanSerializer;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getBeanContext", "(I)" + ASMUtils.desc((Class<?>) BeanContext.class));
        methodVisitor2.visitVarInsn(25, 2);
        methodVisitor2.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("byte"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Short.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("short"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Integer.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("int"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Character.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("char"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Long.TYPE) {
            methodVisitor2.visitVarInsn(22, context2.var("long", 2));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Float.TYPE) {
            methodVisitor2.visitVarInsn(23, context2.var("float"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Double.TYPE) {
            methodVisitor2.visitVarInsn(24, context2.var("double", 2));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Boolean.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("boolean"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == BigDecimal.class) {
            methodVisitor2.visitVarInsn(25, context2.var("decimal"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else if (cls == String.class) {
            methodVisitor2.visitVarInsn(25, context2.var("string"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else if (cls.isEnum()) {
            methodVisitor2.visitVarInsn(25, context2.var("enum"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        }
        String str2 = JavaBeanSerializer;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, "processValue", "(L" + JSONSerializer + h.f735b + ASMUtils.desc((Class<?>) BeanContext.class) + "Ljava/lang/Object;Ljava/lang/String;" + "Ljava/lang/Object;" + ")Ljava/lang/Object;");
        methodVisitor2.visitVarInsn(58, Context.processValue);
        methodVisitor2.visitVarInsn(25, Context.original);
        methodVisitor2.visitVarInsn(25, Context.processValue);
        methodVisitor2.visitJumpInsn(Opcodes.IF_ACMPEQ, label2);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor2.visitJumpInsn(Opcodes.GOTO, label);
        methodVisitor2.visitLabel(label2);
    }

    private void _processKey(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        methodVisitor.visitVarInsn(21, context.var("hasNameFilters"));
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "processKey", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        methodVisitor.visitVarInsn(58, Context.fieldName);
        methodVisitor.visitLabel(label);
    }

    private void _if_write_null(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        int i;
        Class<?> cls = fieldInfo.fieldClass;
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label);
        JSONField annotation = fieldInfo.getAnnotation();
        int of = annotation != null ? SerializerFeature.of(annotation.serialzeFeatures()) : 0;
        JSONType jSONType = context.beanInfo.jsonType;
        if (jSONType != null) {
            of |= SerializerFeature.of(jSONType.serialzeFeatures());
        }
        if (cls == String.class) {
            i = SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullStringAsEmpty.getMask();
        } else if (Number.class.isAssignableFrom(cls)) {
            i = SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullNumberAsZero.getMask();
        } else if (Collection.class.isAssignableFrom(cls)) {
            i = SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullListAsEmpty.getMask();
        } else if (Boolean.class == cls) {
            i = SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullBooleanAsFalse.getMask();
        } else {
            i = SerializerFeature.WRITE_MAP_NULL_FEATURES;
        }
        if ((of & i) == 0) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(i));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label2);
        }
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor, context);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitLdcInsn(Integer.valueOf(of));
        if (cls == String.class || cls == Character.class) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullStringAsEmpty.mask));
        } else if (Number.class.isAssignableFrom(cls)) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullNumberAsZero.mask));
        } else if (cls == Boolean.class) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullBooleanAsFalse.mask));
        } else if (Collection.class.isAssignableFrom(cls) || cls.isArray()) {
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.WriteNullListAsEmpty.mask));
        } else {
            methodVisitor.visitLdcInsn(0);
        }
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeNull", "(II)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label4);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label4);
    }

    private void _writeFieldName(MethodVisitor methodVisitor, Context context) {
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldNameDirect", "(Ljava/lang/String;)V");
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitInsn(3);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
    }

    private void _seperator(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _getListFieldItemSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, access$300, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
        String str = JSONSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        String access$3002 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, access$3002, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        String access$3003 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, access$3003, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
    }

    private void _getFieldSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, access$300, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        String str = JSONSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        String access$3002 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, access$3002, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        String access$3003 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, access$3003, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
    }
}
