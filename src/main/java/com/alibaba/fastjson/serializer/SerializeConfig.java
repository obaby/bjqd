package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class SerializeConfig {
    private static boolean awtError = false;
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean guavaError = false;
    private static boolean jdk8Error = false;
    private static boolean jsonnullError = false;
    private static boolean oracleJdbcError = false;
    private static boolean springfoxError = false;
    private boolean asm;
    private ASMSerializerFactory asmFactory;
    private final boolean fieldBased;
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<Type, ObjectSerializer> serializers;
    protected String typeKey;

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }

    private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        JavaBeanSerializer createJavaBeanSerializer = this.asmFactory.createJavaBeanSerializer(serializeBeanInfo);
        for (FieldSerializer fieldSerializer : createJavaBeanSerializer.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (cls.isEnum() && !(getObjectWriter(cls) instanceof EnumSerializer)) {
                createJavaBeanSerializer.writeDirect = false;
            }
        }
        return createJavaBeanSerializer;
    }

    public final ObjectSerializer createJavaBeanSerializer(Class<?> cls) {
        SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, (Map<String, String>) null, this.propertyNamingStrategy, this.fieldBased);
        if (buildBeanInfo.fields.length != 0 || !Iterable.class.isAssignableFrom(cls)) {
            return createJavaBeanSerializer(buildBeanInfo);
        }
        return MiscCodec.instance;
    }

    public ObjectSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        Method method;
        JSONType jSONType = serializeBeanInfo.jsonType;
        boolean z = false;
        boolean z2 = this.asm && !this.fieldBased;
        if (jSONType != null) {
            Class<?> serializer = jSONType.serializer();
            if (serializer != Void.class) {
                try {
                    Object newInstance = serializer.newInstance();
                    if (newInstance instanceof ObjectSerializer) {
                        return (ObjectSerializer) newInstance;
                    }
                } catch (Throwable unused) {
                }
            }
            if (!jSONType.asm()) {
                z2 = false;
            }
            SerializerFeature[] serialzeFeatures = jSONType.serialzeFeatures();
            int length = serialzeFeatures.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                SerializerFeature serializerFeature = serialzeFeatures[i];
                if (SerializerFeature.WriteNonStringValueAsString == serializerFeature || SerializerFeature.WriteEnumUsingToString == serializerFeature || SerializerFeature.NotWriteDefaultValue == serializerFeature) {
                    z2 = false;
                } else {
                    i++;
                }
            }
            z2 = false;
        }
        Class<?> cls = serializeBeanInfo.beanType;
        if (!Modifier.isPublic(serializeBeanInfo.beanType.getModifiers())) {
            return new JavaBeanSerializer(serializeBeanInfo);
        }
        if ((z2 && this.asmFactory.classLoader.isExternalClass(cls)) || cls == Serializable.class || cls == Object.class) {
            z2 = false;
        }
        if (z2 && !ASMUtils.checkName(cls.getSimpleName())) {
            z2 = false;
        }
        if (z2 && serializeBeanInfo.beanType.isInterface()) {
            z2 = false;
        }
        if (z2) {
            FieldInfo[] fieldInfoArr = serializeBeanInfo.fields;
            int length2 = fieldInfoArr.length;
            boolean z3 = z2;
            int i2 = 0;
            while (true) {
                if (i2 < length2) {
                    FieldInfo fieldInfo = fieldInfoArr[i2];
                    Field field = fieldInfo.field;
                    if ((field != null && !field.getType().equals(fieldInfo.fieldClass)) || ((method = fieldInfo.method) != null && !method.getReturnType().equals(fieldInfo.fieldClass))) {
                        break;
                    }
                    JSONField annotation = fieldInfo.getAnnotation();
                    if (annotation != null) {
                        String format = annotation.format();
                        if ((format.length() != 0 && (fieldInfo.fieldClass != String.class || !"trim".equals(format))) || !ASMUtils.checkName(annotation.name()) || annotation.jsonDirect() || annotation.serializeUsing() != Void.class || annotation.unwrapped()) {
                            break;
                        }
                        SerializerFeature[] serialzeFeatures2 = annotation.serialzeFeatures();
                        int length3 = serialzeFeatures2.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length3) {
                                break;
                            }
                            SerializerFeature serializerFeature2 = serialzeFeatures2[i3];
                            if (SerializerFeature.WriteNonStringValueAsString == serializerFeature2 || SerializerFeature.WriteEnumUsingToString == serializerFeature2 || SerializerFeature.NotWriteDefaultValue == serializerFeature2 || SerializerFeature.WriteClassName == serializerFeature2) {
                                z3 = false;
                            } else {
                                i3++;
                            }
                        }
                        z3 = false;
                        if (TypeUtils.isAnnotationPresentOneToMany(method) || TypeUtils.isAnnotationPresentManyToMany(method)) {
                            z = true;
                        }
                    }
                    i2++;
                } else {
                    z = z3;
                    break;
                }
            }
        } else {
            z = z2;
        }
        if (z) {
            try {
                JavaBeanSerializer createASMSerializer = createASMSerializer(serializeBeanInfo);
                if (createASMSerializer != null) {
                    return createASMSerializer;
                }
            } catch (ClassCastException | ClassFormatError | ClassNotFoundException unused2) {
            } catch (Throwable th) {
                throw new JSONException("create asm serializer error, class " + cls, th);
            }
        }
        return new JavaBeanSerializer(serializeBeanInfo);
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public void setAsmEnable(boolean z) {
        if (!ASMUtils.IS_ANDROID) {
            this.asm = z;
        }
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public SerializeConfig() {
        this(8192);
    }

    public SerializeConfig(boolean z) {
        this(8192, z);
    }

    public SerializeConfig(int i) {
        this(i, false);
    }

    public SerializeConfig(int i, boolean z) {
        this.asm = !ASMUtils.IS_ANDROID;
        this.typeKey = JSON.DEFAULT_TYPE_KEY;
        this.fieldBased = z;
        this.serializers = new IdentityHashMap<>(i);
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (Throwable unused) {
            this.asm = false;
        }
        initSerializers();
    }

    private void initSerializers() {
        put((Type) Boolean.class, (ObjectSerializer) BooleanCodec.instance);
        put((Type) Character.class, (ObjectSerializer) CharacterCodec.instance);
        put((Type) Byte.class, (ObjectSerializer) IntegerCodec.instance);
        put((Type) Short.class, (ObjectSerializer) IntegerCodec.instance);
        put((Type) Integer.class, (ObjectSerializer) IntegerCodec.instance);
        put((Type) Long.class, (ObjectSerializer) LongCodec.instance);
        put((Type) Float.class, (ObjectSerializer) FloatCodec.instance);
        put((Type) Double.class, (ObjectSerializer) DoubleSerializer.instance);
        put((Type) BigDecimal.class, (ObjectSerializer) BigDecimalCodec.instance);
        put((Type) BigInteger.class, (ObjectSerializer) BigIntegerCodec.instance);
        put((Type) String.class, (ObjectSerializer) StringCodec.instance);
        put((Type) byte[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) short[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) int[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) long[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) float[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) double[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) boolean[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) char[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) Object[].class, (ObjectSerializer) ObjectArrayCodec.instance);
        put((Type) Class.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) SimpleDateFormat.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Currency.class, (ObjectSerializer) new MiscCodec());
        put((Type) TimeZone.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) InetAddress.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Inet4Address.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Inet6Address.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) InetSocketAddress.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) File.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Appendable.class, (ObjectSerializer) AppendableSerializer.instance);
        put((Type) StringBuffer.class, (ObjectSerializer) AppendableSerializer.instance);
        put((Type) StringBuilder.class, (ObjectSerializer) AppendableSerializer.instance);
        put((Type) Charset.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) Pattern.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) Locale.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) URI.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) URL.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) UUID.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) AtomicBoolean.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicInteger.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicLong.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put((Type) AtomicIntegerArray.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicLongArray.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) WeakReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put((Type) SoftReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put((Type) LinkedList.class, (ObjectSerializer) CollectionCodec.instance);
    }

    public void addFilter(Class<?> cls, SerializeFilter serializeFilter) {
        ObjectSerializer objectWriter = getObjectWriter(cls);
        if (objectWriter instanceof SerializeFilterable) {
            SerializeFilterable serializeFilterable = (SerializeFilterable) objectWriter;
            if (this == globalInstance || serializeFilterable != MapSerializer.instance) {
                serializeFilterable.addFilter(serializeFilter);
                return;
            }
            MapSerializer mapSerializer = new MapSerializer();
            put((Type) cls, (ObjectSerializer) mapSerializer);
            mapSerializer.addFilter(serializeFilter);
        }
    }

    public void config(Class<?> cls, SerializerFeature serializerFeature, boolean z) {
        ObjectSerializer objectWriter = getObjectWriter(cls, false);
        if (objectWriter == null) {
            SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, (Map<String, String>) null, this.propertyNamingStrategy);
            if (z) {
                buildBeanInfo.features = serializerFeature.mask | buildBeanInfo.features;
            } else {
                buildBeanInfo.features = (serializerFeature.mask ^ -1) & buildBeanInfo.features;
            }
            put((Type) cls, createJavaBeanSerializer(buildBeanInfo));
        } else if (objectWriter instanceof JavaBeanSerializer) {
            SerializeBeanInfo serializeBeanInfo = ((JavaBeanSerializer) objectWriter).beanInfo;
            int i = serializeBeanInfo.features;
            if (z) {
                serializeBeanInfo.features = serializerFeature.mask | serializeBeanInfo.features;
            } else {
                serializeBeanInfo.features = (serializerFeature.mask ^ -1) & serializeBeanInfo.features;
            }
            if (i != serializeBeanInfo.features && objectWriter.getClass() != JavaBeanSerializer.class) {
                put((Type) cls, createJavaBeanSerializer(serializeBeanInfo));
            }
        }
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return getObjectWriter(cls, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:139:0x0296 A[Catch:{ Throwable -> 0x02f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02bd A[Catch:{ Throwable -> 0x02f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02de A[Catch:{ Throwable -> 0x02f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x030d A[Catch:{ Throwable -> 0x0324 }] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x035d A[Catch:{ ClassNotFoundException -> 0x0374 }] */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x03a0  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x03a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.alibaba.fastjson.serializer.ObjectSerializer getObjectWriter(java.lang.Class<?> r20, boolean r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.serializer.ObjectSerializer> r2 = r0.serializers
            java.lang.Object r2 = r2.get(r1)
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = (com.alibaba.fastjson.serializer.ObjectSerializer) r2
            if (r2 != 0) goto L_0x0051
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ ClassCastException -> 0x0049 }
            java.lang.ClassLoader r2 = r2.getContextClassLoader()     // Catch:{ ClassCastException -> 0x0049 }
            java.lang.Class<com.alibaba.fastjson.serializer.AutowiredObjectSerializer> r3 = com.alibaba.fastjson.serializer.AutowiredObjectSerializer.class
            java.util.Set r2 = com.alibaba.fastjson.util.ServiceLoader.load(r3, (java.lang.ClassLoader) r2)     // Catch:{ ClassCastException -> 0x0049 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ ClassCastException -> 0x0049 }
        L_0x0020:
            boolean r3 = r2.hasNext()     // Catch:{ ClassCastException -> 0x0049 }
            if (r3 == 0) goto L_0x0049
            java.lang.Object r3 = r2.next()     // Catch:{ ClassCastException -> 0x0049 }
            boolean r4 = r3 instanceof com.alibaba.fastjson.serializer.AutowiredObjectSerializer     // Catch:{ ClassCastException -> 0x0049 }
            if (r4 != 0) goto L_0x002f
            goto L_0x0020
        L_0x002f:
            com.alibaba.fastjson.serializer.AutowiredObjectSerializer r3 = (com.alibaba.fastjson.serializer.AutowiredObjectSerializer) r3     // Catch:{ ClassCastException -> 0x0049 }
            java.util.Set r4 = r3.getAutowiredFor()     // Catch:{ ClassCastException -> 0x0049 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ ClassCastException -> 0x0049 }
        L_0x0039:
            boolean r5 = r4.hasNext()     // Catch:{ ClassCastException -> 0x0049 }
            if (r5 == 0) goto L_0x0020
            java.lang.Object r5 = r4.next()     // Catch:{ ClassCastException -> 0x0049 }
            java.lang.reflect.Type r5 = (java.lang.reflect.Type) r5     // Catch:{ ClassCastException -> 0x0049 }
            r0.put((java.lang.reflect.Type) r5, (com.alibaba.fastjson.serializer.ObjectSerializer) r3)     // Catch:{ ClassCastException -> 0x0049 }
            goto L_0x0039
        L_0x0049:
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.serializer.ObjectSerializer> r2 = r0.serializers
            java.lang.Object r2 = r2.get(r1)
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = (com.alibaba.fastjson.serializer.ObjectSerializer) r2
        L_0x0051:
            if (r2 != 0) goto L_0x009e
            java.lang.Class<com.alibaba.fastjson.JSON> r3 = com.alibaba.fastjson.JSON.class
            java.lang.ClassLoader r3 = r3.getClassLoader()
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r4 = r4.getContextClassLoader()
            if (r3 == r4) goto L_0x009e
            java.lang.Class<com.alibaba.fastjson.serializer.AutowiredObjectSerializer> r2 = com.alibaba.fastjson.serializer.AutowiredObjectSerializer.class
            java.util.Set r2 = com.alibaba.fastjson.util.ServiceLoader.load(r2, (java.lang.ClassLoader) r3)     // Catch:{ ClassCastException -> 0x0096 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ ClassCastException -> 0x0096 }
        L_0x006d:
            boolean r3 = r2.hasNext()     // Catch:{ ClassCastException -> 0x0096 }
            if (r3 == 0) goto L_0x0096
            java.lang.Object r3 = r2.next()     // Catch:{ ClassCastException -> 0x0096 }
            boolean r4 = r3 instanceof com.alibaba.fastjson.serializer.AutowiredObjectSerializer     // Catch:{ ClassCastException -> 0x0096 }
            if (r4 != 0) goto L_0x007c
            goto L_0x006d
        L_0x007c:
            com.alibaba.fastjson.serializer.AutowiredObjectSerializer r3 = (com.alibaba.fastjson.serializer.AutowiredObjectSerializer) r3     // Catch:{ ClassCastException -> 0x0096 }
            java.util.Set r4 = r3.getAutowiredFor()     // Catch:{ ClassCastException -> 0x0096 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ ClassCastException -> 0x0096 }
        L_0x0086:
            boolean r5 = r4.hasNext()     // Catch:{ ClassCastException -> 0x0096 }
            if (r5 == 0) goto L_0x006d
            java.lang.Object r5 = r4.next()     // Catch:{ ClassCastException -> 0x0096 }
            java.lang.reflect.Type r5 = (java.lang.reflect.Type) r5     // Catch:{ ClassCastException -> 0x0096 }
            r0.put((java.lang.reflect.Type) r5, (com.alibaba.fastjson.serializer.ObjectSerializer) r3)     // Catch:{ ClassCastException -> 0x0096 }
            goto L_0x0086
        L_0x0096:
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.serializer.ObjectSerializer> r2 = r0.serializers
            java.lang.Object r2 = r2.get(r1)
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = (com.alibaba.fastjson.serializer.ObjectSerializer) r2
        L_0x009e:
            if (r2 != 0) goto L_0x0407
            java.lang.String r3 = r20.getName()
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x00b3
            com.alibaba.fastjson.serializer.MapSerializer r2 = com.alibaba.fastjson.serializer.MapSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x00b3:
            java.lang.Class<java.util.List> r4 = java.util.List.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x00c2
            com.alibaba.fastjson.serializer.ListSerializer r2 = com.alibaba.fastjson.serializer.ListSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x00c2:
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x00d1
            com.alibaba.fastjson.serializer.CollectionCodec r2 = com.alibaba.fastjson.serializer.CollectionCodec.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x00d1:
            java.lang.Class<java.util.Date> r4 = java.util.Date.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x00e0
            com.alibaba.fastjson.serializer.DateCodec r2 = com.alibaba.fastjson.serializer.DateCodec.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x00e0:
            java.lang.Class<com.alibaba.fastjson.JSONAware> r4 = com.alibaba.fastjson.JSONAware.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x00ef
            com.alibaba.fastjson.serializer.JSONAwareSerializer r2 = com.alibaba.fastjson.serializer.JSONAwareSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x00ef:
            java.lang.Class<com.alibaba.fastjson.serializer.JSONSerializable> r4 = com.alibaba.fastjson.serializer.JSONSerializable.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x00fe
            com.alibaba.fastjson.serializer.JSONSerializableSerializer r2 = com.alibaba.fastjson.serializer.JSONSerializableSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x00fe:
            java.lang.Class<com.alibaba.fastjson.JSONStreamAware> r4 = com.alibaba.fastjson.JSONStreamAware.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x010d
            com.alibaba.fastjson.serializer.MiscCodec r2 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x010d:
            boolean r4 = r20.isEnum()
            if (r4 == 0) goto L_0x0133
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r2 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r2 = com.alibaba.fastjson.util.TypeUtils.getAnnotation(r1, r2)
            com.alibaba.fastjson.annotation.JSONType r2 = (com.alibaba.fastjson.annotation.JSONType) r2
            if (r2 == 0) goto L_0x012c
            boolean r2 = r2.serializeEnumAsJavaBean()
            if (r2 == 0) goto L_0x012c
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = r19.createJavaBeanSerializer((java.lang.Class<?>) r20)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x012c:
            com.alibaba.fastjson.serializer.EnumSerializer r2 = com.alibaba.fastjson.serializer.EnumSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x0133:
            java.lang.Class r4 = r20.getSuperclass()
            if (r4 == 0) goto L_0x015f
            boolean r5 = r4.isEnum()
            if (r5 == 0) goto L_0x015f
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r2 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r2 = com.alibaba.fastjson.util.TypeUtils.getAnnotation(r4, r2)
            com.alibaba.fastjson.annotation.JSONType r2 = (com.alibaba.fastjson.annotation.JSONType) r2
            if (r2 == 0) goto L_0x0158
            boolean r2 = r2.serializeEnumAsJavaBean()
            if (r2 == 0) goto L_0x0158
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = r19.createJavaBeanSerializer((java.lang.Class<?>) r20)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x0158:
            com.alibaba.fastjson.serializer.EnumSerializer r2 = com.alibaba.fastjson.serializer.EnumSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x015f:
            boolean r4 = r20.isArray()
            if (r4 == 0) goto L_0x0178
            java.lang.Class r2 = r20.getComponentType()
            com.alibaba.fastjson.serializer.ObjectSerializer r3 = r0.getObjectWriter(r2)
            com.alibaba.fastjson.serializer.ArraySerializer r4 = new com.alibaba.fastjson.serializer.ArraySerializer
            r4.<init>(r2, r3)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r4)
            r2 = r4
            goto L_0x03fc
        L_0x0178:
            java.lang.Class<java.lang.Throwable> r4 = java.lang.Throwable.class
            boolean r4 = r4.isAssignableFrom(r1)
            r5 = 0
            if (r4 == 0) goto L_0x019b
            com.alibaba.fastjson.PropertyNamingStrategy r2 = r0.propertyNamingStrategy
            com.alibaba.fastjson.serializer.SerializeBeanInfo r2 = com.alibaba.fastjson.util.TypeUtils.buildBeanInfo(r1, r5, r2)
            int r3 = r2.features
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName
            int r4 = r4.mask
            r3 = r3 | r4
            r2.features = r3
            com.alibaba.fastjson.serializer.JavaBeanSerializer r3 = new com.alibaba.fastjson.serializer.JavaBeanSerializer
            r3.<init>((com.alibaba.fastjson.serializer.SerializeBeanInfo) r2)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r3)
            r2 = r3
            goto L_0x03fc
        L_0x019b:
            java.lang.Class<java.util.TimeZone> r4 = java.util.TimeZone.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 != 0) goto L_0x03f7
            java.lang.Class<java.util.Map$Entry> r4 = java.util.Map.Entry.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x01ad
            goto L_0x03f7
        L_0x01ad:
            java.lang.Class<java.lang.Appendable> r4 = java.lang.Appendable.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x01bc
            com.alibaba.fastjson.serializer.AppendableSerializer r2 = com.alibaba.fastjson.serializer.AppendableSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x01bc:
            java.lang.Class<java.nio.charset.Charset> r4 = java.nio.charset.Charset.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x01cb
            com.alibaba.fastjson.serializer.ToStringSerializer r2 = com.alibaba.fastjson.serializer.ToStringSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x01cb:
            java.lang.Class<java.util.Enumeration> r4 = java.util.Enumeration.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x01da
            com.alibaba.fastjson.serializer.EnumerationSerializer r2 = com.alibaba.fastjson.serializer.EnumerationSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x01da:
            java.lang.Class<java.util.Calendar> r4 = java.util.Calendar.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 != 0) goto L_0x03f1
            java.lang.Class<javax.xml.datatype.XMLGregorianCalendar> r4 = javax.xml.datatype.XMLGregorianCalendar.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x01ec
            goto L_0x03f1
        L_0x01ec:
            java.lang.Class<java.sql.Clob> r4 = java.sql.Clob.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x01fb
            com.alibaba.fastjson.serializer.ClobSeriliazer r2 = com.alibaba.fastjson.serializer.ClobSeriliazer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x01fb:
            boolean r4 = com.alibaba.fastjson.util.TypeUtils.isPath(r20)
            if (r4 == 0) goto L_0x0208
            com.alibaba.fastjson.serializer.ToStringSerializer r2 = com.alibaba.fastjson.serializer.ToStringSerializer.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x0208:
            java.lang.Class<java.util.Iterator> r4 = java.util.Iterator.class
            boolean r4 = r4.isAssignableFrom(r1)
            if (r4 == 0) goto L_0x0217
            com.alibaba.fastjson.serializer.MiscCodec r2 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x0217:
            java.lang.String r4 = "java.awt."
            boolean r4 = r3.startsWith(r4)
            r6 = 0
            r7 = 1
            if (r4 == 0) goto L_0x0254
            boolean r4 = com.alibaba.fastjson.serializer.AwtCodec.support(r20)
            if (r4 == 0) goto L_0x0254
            boolean r4 = awtError
            if (r4 != 0) goto L_0x0254
            java.lang.String r4 = "java.awt.Color"
            java.lang.String r8 = "java.awt.Font"
            java.lang.String r9 = "java.awt.Point"
            java.lang.String r10 = "java.awt.Rectangle"
            java.lang.String[] r4 = new java.lang.String[]{r4, r8, r9, r10}     // Catch:{ Throwable -> 0x0252 }
            int r8 = r4.length     // Catch:{ Throwable -> 0x0252 }
            r9 = 0
        L_0x0239:
            if (r9 >= r8) goto L_0x0254
            r10 = r4[r9]     // Catch:{ Throwable -> 0x0252 }
            boolean r11 = r10.equals(r3)     // Catch:{ Throwable -> 0x0252 }
            if (r11 == 0) goto L_0x024f
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ Throwable -> 0x0252 }
            com.alibaba.fastjson.serializer.AwtCodec r8 = com.alibaba.fastjson.serializer.AwtCodec.instance     // Catch:{ Throwable -> 0x0252 }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ Throwable -> 0x024d }
            return r8
        L_0x024d:
            r2 = r8
            goto L_0x0252
        L_0x024f:
            int r9 = r9 + 1
            goto L_0x0239
        L_0x0252:
            awtError = r7
        L_0x0254:
            boolean r4 = jdk8Error
            if (r4 != 0) goto L_0x02f5
            java.lang.String r4 = "java.time."
            boolean r4 = r3.startsWith(r4)
            if (r4 != 0) goto L_0x0278
            java.lang.String r4 = "java.util.Optional"
            boolean r4 = r3.startsWith(r4)
            if (r4 != 0) goto L_0x0278
            java.lang.String r4 = "java.util.concurrent.atomic.LongAdder"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0278
            java.lang.String r4 = "java.util.concurrent.atomic.DoubleAdder"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x02f5
        L_0x0278:
            java.lang.String r8 = "java.time.LocalDateTime"
            java.lang.String r9 = "java.time.LocalDate"
            java.lang.String r10 = "java.time.LocalTime"
            java.lang.String r11 = "java.time.ZonedDateTime"
            java.lang.String r12 = "java.time.OffsetDateTime"
            java.lang.String r13 = "java.time.OffsetTime"
            java.lang.String r14 = "java.time.ZoneOffset"
            java.lang.String r15 = "java.time.ZoneRegion"
            java.lang.String r16 = "java.time.Period"
            java.lang.String r17 = "java.time.Duration"
            java.lang.String r18 = "java.time.Instant"
            java.lang.String[] r4 = new java.lang.String[]{r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18}     // Catch:{ Throwable -> 0x02f3 }
            int r8 = r4.length     // Catch:{ Throwable -> 0x02f3 }
            r9 = 0
        L_0x0294:
            if (r9 >= r8) goto L_0x02ad
            r10 = r4[r9]     // Catch:{ Throwable -> 0x02f3 }
            boolean r11 = r10.equals(r3)     // Catch:{ Throwable -> 0x02f3 }
            if (r11 == 0) goto L_0x02aa
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ Throwable -> 0x02f3 }
            com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec r8 = com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec.instance     // Catch:{ Throwable -> 0x02f3 }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ Throwable -> 0x02a8 }
            return r8
        L_0x02a8:
            r2 = r8
            goto L_0x02f3
        L_0x02aa:
            int r9 = r9 + 1
            goto L_0x0294
        L_0x02ad:
            java.lang.String r4 = "java.util.Optional"
            java.lang.String r8 = "java.util.OptionalDouble"
            java.lang.String r9 = "java.util.OptionalInt"
            java.lang.String r10 = "java.util.OptionalLong"
            java.lang.String[] r4 = new java.lang.String[]{r4, r8, r9, r10}     // Catch:{ Throwable -> 0x02f3 }
            int r8 = r4.length     // Catch:{ Throwable -> 0x02f3 }
            r9 = 0
        L_0x02bb:
            if (r9 >= r8) goto L_0x02d2
            r10 = r4[r9]     // Catch:{ Throwable -> 0x02f3 }
            boolean r11 = r10.equals(r3)     // Catch:{ Throwable -> 0x02f3 }
            if (r11 == 0) goto L_0x02cf
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ Throwable -> 0x02f3 }
            com.alibaba.fastjson.parser.deserializer.OptionalCodec r8 = com.alibaba.fastjson.parser.deserializer.OptionalCodec.instance     // Catch:{ Throwable -> 0x02f3 }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ Throwable -> 0x02a8 }
            return r8
        L_0x02cf:
            int r9 = r9 + 1
            goto L_0x02bb
        L_0x02d2:
            java.lang.String r4 = "java.util.concurrent.atomic.LongAdder"
            java.lang.String r8 = "java.util.concurrent.atomic.DoubleAdder"
            java.lang.String[] r4 = new java.lang.String[]{r4, r8}     // Catch:{ Throwable -> 0x02f3 }
            int r8 = r4.length     // Catch:{ Throwable -> 0x02f3 }
            r9 = 0
        L_0x02dc:
            if (r9 >= r8) goto L_0x02f5
            r10 = r4[r9]     // Catch:{ Throwable -> 0x02f3 }
            boolean r11 = r10.equals(r3)     // Catch:{ Throwable -> 0x02f3 }
            if (r11 == 0) goto L_0x02f0
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ Throwable -> 0x02f3 }
            com.alibaba.fastjson.serializer.AdderSerializer r8 = com.alibaba.fastjson.serializer.AdderSerializer.instance     // Catch:{ Throwable -> 0x02f3 }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ Throwable -> 0x02a8 }
            return r8
        L_0x02f0:
            int r9 = r9 + 1
            goto L_0x02dc
        L_0x02f3:
            jdk8Error = r7
        L_0x02f5:
            boolean r4 = oracleJdbcError
            if (r4 != 0) goto L_0x0326
            java.lang.String r4 = "oracle.sql."
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x0326
            java.lang.String r4 = "oracle.sql.DATE"
            java.lang.String r8 = "oracle.sql.TIMESTAMP"
            java.lang.String[] r4 = new java.lang.String[]{r4, r8}     // Catch:{ Throwable -> 0x0324 }
            int r8 = r4.length     // Catch:{ Throwable -> 0x0324 }
            r9 = 0
        L_0x030b:
            if (r9 >= r8) goto L_0x0326
            r10 = r4[r9]     // Catch:{ Throwable -> 0x0324 }
            boolean r11 = r10.equals(r3)     // Catch:{ Throwable -> 0x0324 }
            if (r11 == 0) goto L_0x0321
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ Throwable -> 0x0324 }
            com.alibaba.fastjson.serializer.DateCodec r8 = com.alibaba.fastjson.serializer.DateCodec.instance     // Catch:{ Throwable -> 0x0324 }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ Throwable -> 0x031f }
            return r8
        L_0x031f:
            r2 = r8
            goto L_0x0324
        L_0x0321:
            int r9 = r9 + 1
            goto L_0x030b
        L_0x0324:
            oracleJdbcError = r7
        L_0x0326:
            boolean r4 = springfoxError
            if (r4 != 0) goto L_0x0341
            java.lang.String r4 = "springfox.documentation.spring.web.json.Json"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0341
            java.lang.String r4 = "springfox.documentation.spring.web.json.Json"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x033f }
            com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer r8 = com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer.instance     // Catch:{ ClassNotFoundException -> 0x033f }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ ClassNotFoundException -> 0x033e }
            return r8
        L_0x033e:
            r2 = r8
        L_0x033f:
            springfoxError = r7
        L_0x0341:
            boolean r4 = guavaError
            if (r4 != 0) goto L_0x0376
            java.lang.String r4 = "com.google.common.collect."
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x0376
            java.lang.String r4 = "com.google.common.collect.HashMultimap"
            java.lang.String r8 = "com.google.common.collect.LinkedListMultimap"
            java.lang.String r9 = "com.google.common.collect.ArrayListMultimap"
            java.lang.String r10 = "com.google.common.collect.TreeMultimap"
            java.lang.String[] r4 = new java.lang.String[]{r4, r8, r9, r10}     // Catch:{ ClassNotFoundException -> 0x0374 }
            int r8 = r4.length     // Catch:{ ClassNotFoundException -> 0x0374 }
            r9 = 0
        L_0x035b:
            if (r9 >= r8) goto L_0x0376
            r10 = r4[r9]     // Catch:{ ClassNotFoundException -> 0x0374 }
            boolean r11 = r10.equals(r3)     // Catch:{ ClassNotFoundException -> 0x0374 }
            if (r11 == 0) goto L_0x0371
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ ClassNotFoundException -> 0x0374 }
            com.alibaba.fastjson.serializer.GuavaCodec r8 = com.alibaba.fastjson.serializer.GuavaCodec.instance     // Catch:{ ClassNotFoundException -> 0x0374 }
            r0.put((java.lang.reflect.Type) r4, (com.alibaba.fastjson.serializer.ObjectSerializer) r8)     // Catch:{ ClassNotFoundException -> 0x036f }
            return r8
        L_0x036f:
            r2 = r8
            goto L_0x0374
        L_0x0371:
            int r9 = r9 + 1
            goto L_0x035b
        L_0x0374:
            guavaError = r7
        L_0x0376:
            boolean r4 = jsonnullError
            if (r4 != 0) goto L_0x0391
            java.lang.String r4 = "net.sf.json.JSONNull"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0391
            java.lang.String r3 = "net.sf.json.JSONNull"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x038f }
            com.alibaba.fastjson.serializer.MiscCodec r4 = com.alibaba.fastjson.serializer.MiscCodec.instance     // Catch:{ ClassNotFoundException -> 0x038f }
            r0.put((java.lang.reflect.Type) r3, (com.alibaba.fastjson.serializer.ObjectSerializer) r4)     // Catch:{ ClassNotFoundException -> 0x038e }
            return r4
        L_0x038e:
            r2 = r4
        L_0x038f:
            jsonnullError = r7
        L_0x0391:
            java.lang.Class[] r3 = r20.getInterfaces()
            int r4 = r3.length
            if (r4 != r7) goto L_0x03a3
            r4 = r3[r6]
            boolean r4 = r4.isAnnotation()
            if (r4 == 0) goto L_0x03a3
            com.alibaba.fastjson.serializer.AnnotationSerializer r1 = com.alibaba.fastjson.serializer.AnnotationSerializer.instance
            return r1
        L_0x03a3:
            boolean r4 = com.alibaba.fastjson.util.TypeUtils.isProxy(r20)
            if (r4 == 0) goto L_0x03b5
            java.lang.Class r2 = r20.getSuperclass()
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = r0.getObjectWriter(r2)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            return r2
        L_0x03b5:
            boolean r4 = java.lang.reflect.Proxy.isProxyClass(r20)
            if (r4 == 0) goto L_0x03e7
            int r4 = r3.length
            r8 = 2
            if (r4 != r8) goto L_0x03c2
            r5 = r3[r7]
            goto L_0x03dd
        L_0x03c2:
            int r4 = r3.length
            r7 = r5
        L_0x03c4:
            if (r6 >= r4) goto L_0x03dc
            r8 = r3[r6]
            java.lang.String r9 = r8.getName()
            java.lang.String r10 = "org.springframework.aop."
            boolean r9 = r9.startsWith(r10)
            if (r9 == 0) goto L_0x03d5
            goto L_0x03d9
        L_0x03d5:
            if (r7 == 0) goto L_0x03d8
            goto L_0x03dd
        L_0x03d8:
            r7 = r8
        L_0x03d9:
            int r6 = r6 + 1
            goto L_0x03c4
        L_0x03dc:
            r5 = r7
        L_0x03dd:
            if (r5 == 0) goto L_0x03e7
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = r0.getObjectWriter(r5)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            return r2
        L_0x03e7:
            if (r21 == 0) goto L_0x03fc
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = r19.createJavaBeanSerializer((java.lang.Class<?>) r20)
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x03f1:
            com.alibaba.fastjson.serializer.CalendarCodec r2 = com.alibaba.fastjson.serializer.CalendarCodec.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
            goto L_0x03fc
        L_0x03f7:
            com.alibaba.fastjson.serializer.MiscCodec r2 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r0.put((java.lang.reflect.Type) r1, (com.alibaba.fastjson.serializer.ObjectSerializer) r2)
        L_0x03fc:
            if (r2 != 0) goto L_0x0407
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.serializer.ObjectSerializer> r2 = r0.serializers
            java.lang.Object r1 = r2.get(r1)
            r2 = r1
            com.alibaba.fastjson.serializer.ObjectSerializer r2 = (com.alibaba.fastjson.serializer.ObjectSerializer) r2
        L_0x0407:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeConfig.getObjectWriter(java.lang.Class, boolean):com.alibaba.fastjson.serializer.ObjectSerializer");
    }

    public final ObjectSerializer get(Type type) {
        return this.serializers.get(type);
    }

    public boolean put(Object obj, Object obj2) {
        return put((Type) obj, (ObjectSerializer) obj2);
    }

    public boolean put(Type type, ObjectSerializer objectSerializer) {
        return this.serializers.put(type, objectSerializer);
    }

    public void configEnumAsJavaBean(Class<? extends Enum>... clsArr) {
        for (Class<? extends Enum> cls : clsArr) {
            put((Type) cls, createJavaBeanSerializer((Class<?>) cls));
        }
    }

    public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy2) {
        this.propertyNamingStrategy = propertyNamingStrategy2;
    }

    public void clearSerializers() {
        this.serializers.clear();
        initSerializers();
    }
}
