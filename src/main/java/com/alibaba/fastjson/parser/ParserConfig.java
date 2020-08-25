package com.alibaba.fastjson.parser;

import anetwork.channel.util.RequestConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONPDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.NumberDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeDeserializer;
import com.alibaba.fastjson.serializer.AtomicCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.CharArrayCodec;
import com.alibaba.fastjson.serializer.CharacterCodec;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.ReferenceCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import javax.xml.datatype.XMLGregorianCalendar;

public class ParserConfig {
    public static final String AUTOTYPE_ACCEPT = "fastjson.parser.autoTypeAccept";
    public static final String AUTOTYPE_SUPPORT_PROPERTY = "fastjson.parser.autoTypeSupport";
    public static final boolean AUTO_SUPPORT = RequestConstant.TRUE.equals(IOUtils.getStringProperty(AUTOTYPE_SUPPORT_PROPERTY));
    private static final String[] AUTO_TYPE_ACCEPT_LIST;
    public static final String[] DENYS = splitItemsFormProperty(IOUtils.getStringProperty(DENY_PROPERTY));
    public static final String DENY_PROPERTY = "fastjson.parser.deny";
    private static boolean awtError = false;
    public static ParserConfig global = new ParserConfig();
    private static boolean jdk8Error = false;
    private long[] acceptHashCodes;
    private boolean asmEnable;
    protected ASMDeserializerFactory asmFactory;
    private boolean autoTypeSupport;
    public boolean compatibleWithJavaBean;
    protected ClassLoader defaultClassLoader;
    private long[] denyHashCodes;
    private final IdentityHashMap<Type, ObjectDeserializer> deserializers;
    public final boolean fieldBased;
    public PropertyNamingStrategy propertyNamingStrategy;
    public final SymbolTable symbolTable;

    static {
        String[] splitItemsFormProperty = splitItemsFormProperty(IOUtils.getStringProperty(AUTOTYPE_ACCEPT));
        if (splitItemsFormProperty == null) {
            splitItemsFormProperty = new String[0];
        }
        AUTO_TYPE_ACCEPT_LIST = splitItemsFormProperty;
    }

    public static ParserConfig getGlobalInstance() {
        return global;
    }

    public ParserConfig() {
        this(false);
    }

    public ParserConfig(boolean z) {
        this((ASMDeserializerFactory) null, (ClassLoader) null, z);
    }

    public ParserConfig(ClassLoader classLoader) {
        this((ASMDeserializerFactory) null, classLoader, false);
    }

    public ParserConfig(ASMDeserializerFactory aSMDeserializerFactory) {
        this(aSMDeserializerFactory, (ClassLoader) null, false);
    }

    private ParserConfig(ASMDeserializerFactory aSMDeserializerFactory, ClassLoader classLoader, boolean z) {
        this.deserializers = new IdentityHashMap<>();
        this.asmEnable = !ASMUtils.IS_ANDROID;
        this.symbolTable = new SymbolTable(4096);
        this.autoTypeSupport = AUTO_SUPPORT;
        this.compatibleWithJavaBean = TypeUtils.compatibleWithJavaBean;
        this.denyHashCodes = new long[]{-8720046426850100497L, -8165637398350707645L, -8109300701639721088L, -8083514888460375884L, -7966123100503199569L, -7921218830998286408L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6179589609550493385L, -5194641081268104286L, -4837536971810737970L, -4082057040235125754L, -3935185854875733362L, -2753427844400776271L, -2364987994247679115L, -2262244760619952081L, -1872417015366588117L, -1589194880214235129L, -254670111376247151L, -190281065685395680L, 33238344207745342L, 313864100207897507L, 1073634739308289776L, 1203232727967308606L, 1502845958873959152L, 3547627781654598988L, 3730752432285826863L, 3794316665763266033L, 4147696707147271408L, 5347909877633654828L, 5450448828334921485L, 5688200883751798389L, 5751393439502795295L, 5944107969236155580L, 6742705432718011780L, 7017492163108594270L, 7179336928365889465L, 7442624256860549330L, 8389032537095247355L, 8838294710098435315L};
        long[] jArr = new long[AUTO_TYPE_ACCEPT_LIST.length];
        for (int i = 0; i < AUTO_TYPE_ACCEPT_LIST.length; i++) {
            jArr[i] = TypeUtils.fnv1a_64(AUTO_TYPE_ACCEPT_LIST[i]);
        }
        Arrays.sort(jArr);
        this.acceptHashCodes = jArr;
        this.fieldBased = z;
        if (aSMDeserializerFactory == null && !ASMUtils.IS_ANDROID) {
            if (classLoader == null) {
                try {
                    aSMDeserializerFactory = new ASMDeserializerFactory(new ASMClassLoader());
                } catch (ExceptionInInitializerError | NoClassDefFoundError | AccessControlException unused) {
                }
            } else {
                aSMDeserializerFactory = new ASMDeserializerFactory(classLoader);
            }
        }
        this.asmFactory = aSMDeserializerFactory;
        if (aSMDeserializerFactory == null) {
            this.asmEnable = false;
        }
        initDeserializers();
        addItemsToDeny(DENYS);
        addItemsToAccept(AUTO_TYPE_ACCEPT_LIST);
    }

    private void initDeserializers() {
        this.deserializers.put(SimpleDateFormat.class, MiscCodec.instance);
        this.deserializers.put(Timestamp.class, SqlDateDeserializer.instance_timestamp);
        this.deserializers.put(Date.class, SqlDateDeserializer.instance);
        this.deserializers.put(Time.class, TimeDeserializer.instance);
        this.deserializers.put(java.util.Date.class, DateCodec.instance);
        this.deserializers.put(Calendar.class, CalendarCodec.instance);
        this.deserializers.put(XMLGregorianCalendar.class, CalendarCodec.instance);
        this.deserializers.put(JSONObject.class, MapDeserializer.instance);
        this.deserializers.put(JSONArray.class, CollectionCodec.instance);
        this.deserializers.put(Map.class, MapDeserializer.instance);
        this.deserializers.put(HashMap.class, MapDeserializer.instance);
        this.deserializers.put(LinkedHashMap.class, MapDeserializer.instance);
        this.deserializers.put(TreeMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentHashMap.class, MapDeserializer.instance);
        this.deserializers.put(Collection.class, CollectionCodec.instance);
        this.deserializers.put(List.class, CollectionCodec.instance);
        this.deserializers.put(ArrayList.class, CollectionCodec.instance);
        this.deserializers.put(Object.class, JavaObjectDeserializer.instance);
        this.deserializers.put(String.class, StringCodec.instance);
        this.deserializers.put(StringBuffer.class, StringCodec.instance);
        this.deserializers.put(StringBuilder.class, StringCodec.instance);
        this.deserializers.put(Character.TYPE, CharacterCodec.instance);
        this.deserializers.put(Character.class, CharacterCodec.instance);
        this.deserializers.put(Byte.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Byte.class, NumberDeserializer.instance);
        this.deserializers.put(Short.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Short.class, NumberDeserializer.instance);
        this.deserializers.put(Integer.TYPE, IntegerCodec.instance);
        this.deserializers.put(Integer.class, IntegerCodec.instance);
        this.deserializers.put(Long.TYPE, LongCodec.instance);
        this.deserializers.put(Long.class, LongCodec.instance);
        this.deserializers.put(BigInteger.class, BigIntegerCodec.instance);
        this.deserializers.put(BigDecimal.class, BigDecimalCodec.instance);
        this.deserializers.put(Float.TYPE, FloatCodec.instance);
        this.deserializers.put(Float.class, FloatCodec.instance);
        this.deserializers.put(Double.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Double.class, NumberDeserializer.instance);
        this.deserializers.put(Boolean.TYPE, BooleanCodec.instance);
        this.deserializers.put(Boolean.class, BooleanCodec.instance);
        this.deserializers.put(Class.class, MiscCodec.instance);
        this.deserializers.put(char[].class, new CharArrayCodec());
        this.deserializers.put(AtomicBoolean.class, BooleanCodec.instance);
        this.deserializers.put(AtomicInteger.class, IntegerCodec.instance);
        this.deserializers.put(AtomicLong.class, LongCodec.instance);
        this.deserializers.put(AtomicReference.class, ReferenceCodec.instance);
        this.deserializers.put(WeakReference.class, ReferenceCodec.instance);
        this.deserializers.put(SoftReference.class, ReferenceCodec.instance);
        this.deserializers.put(UUID.class, MiscCodec.instance);
        this.deserializers.put(TimeZone.class, MiscCodec.instance);
        this.deserializers.put(Locale.class, MiscCodec.instance);
        this.deserializers.put(Currency.class, MiscCodec.instance);
        this.deserializers.put(InetAddress.class, MiscCodec.instance);
        this.deserializers.put(Inet4Address.class, MiscCodec.instance);
        this.deserializers.put(Inet6Address.class, MiscCodec.instance);
        this.deserializers.put(InetSocketAddress.class, MiscCodec.instance);
        this.deserializers.put(File.class, MiscCodec.instance);
        this.deserializers.put(URI.class, MiscCodec.instance);
        this.deserializers.put(URL.class, MiscCodec.instance);
        this.deserializers.put(Pattern.class, MiscCodec.instance);
        this.deserializers.put(Charset.class, MiscCodec.instance);
        this.deserializers.put(JSONPath.class, MiscCodec.instance);
        this.deserializers.put(Number.class, NumberDeserializer.instance);
        this.deserializers.put(AtomicIntegerArray.class, AtomicCodec.instance);
        this.deserializers.put(AtomicLongArray.class, AtomicCodec.instance);
        this.deserializers.put(StackTraceElement.class, StackTraceElementDeserializer.instance);
        this.deserializers.put(Serializable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Cloneable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Comparable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Closeable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(JSONPObject.class, new JSONPDeserializer());
    }

    private static String[] splitItemsFormProperty(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(",");
    }

    public void configFromPropety(Properties properties) {
        addItemsToDeny(splitItemsFormProperty(properties.getProperty(DENY_PROPERTY)));
        addItemsToAccept(splitItemsFormProperty(properties.getProperty(AUTOTYPE_ACCEPT)));
        String property = properties.getProperty(AUTOTYPE_SUPPORT_PROPERTY);
        if (RequestConstant.TRUE.equals(property)) {
            this.autoTypeSupport = true;
        } else if (RequestConstant.FALSE.equals(property)) {
            this.autoTypeSupport = false;
        }
    }

    private void addItemsToDeny(String[] strArr) {
        if (strArr != null) {
            for (String addDeny : strArr) {
                addDeny(addDeny);
            }
        }
    }

    private void addItemsToAccept(String[] strArr) {
        if (strArr != null) {
            for (String addAccept : strArr) {
                addAccept(addAccept);
            }
        }
    }

    public boolean isAutoTypeSupport() {
        return this.autoTypeSupport;
    }

    public void setAutoTypeSupport(boolean z) {
        this.autoTypeSupport = z;
    }

    public boolean isAsmEnable() {
        return this.asmEnable;
    }

    public void setAsmEnable(boolean z) {
        this.asmEnable = z;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDeserializers() {
        return this.deserializers;
    }

    public ObjectDeserializer getDeserializer(Type type) {
        ObjectDeserializer objectDeserializer = this.deserializers.get(type);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        if (type instanceof Class) {
            return getDeserializer((Class) type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return getDeserializer((Class) rawType, type);
            }
            return getDeserializer(rawType);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getDeserializer(upperBounds[0]);
            }
        }
        return JavaObjectDeserializer.instance;
    }

    /* JADX WARNING: type inference failed for: r22v0, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0140 A[Catch:{ Exception -> 0x0160 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x016c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x016d  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alibaba.fastjson.parser.deserializer.ObjectDeserializer getDeserializer(java.lang.Class<?> r21, java.lang.reflect.Type r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers
            java.lang.Object r3 = r3.get(r2)
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r3
            if (r3 == 0) goto L_0x0011
            return r3
        L_0x0011:
            if (r2 != 0) goto L_0x0014
            r2 = r1
        L_0x0014:
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers
            java.lang.Object r3 = r3.get(r2)
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r3
            if (r3 == 0) goto L_0x001f
            return r3
        L_0x001f:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r4 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r4 = com.alibaba.fastjson.util.TypeUtils.getAnnotation(r1, r4)
            com.alibaba.fastjson.annotation.JSONType r4 = (com.alibaba.fastjson.annotation.JSONType) r4
            if (r4 == 0) goto L_0x0036
            java.lang.Class r4 = r4.mappingTo()
            java.lang.Class<java.lang.Void> r5 = java.lang.Void.class
            if (r4 == r5) goto L_0x0036
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r0.getDeserializer(r4, r4)
            return r1
        L_0x0036:
            boolean r4 = r2 instanceof java.lang.reflect.WildcardType
            if (r4 != 0) goto L_0x0042
            boolean r4 = r2 instanceof java.lang.reflect.TypeVariable
            if (r4 != 0) goto L_0x0042
            boolean r4 = r2 instanceof java.lang.reflect.ParameterizedType
            if (r4 == 0) goto L_0x004a
        L_0x0042:
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers
            java.lang.Object r3 = r3.get(r1)
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r3
        L_0x004a:
            if (r3 == 0) goto L_0x004d
            return r3
        L_0x004d:
            java.lang.String r4 = r21.getName()
            r5 = 36
            r6 = 46
            java.lang.String r4 = r4.replace(r5, r6)
            java.lang.String r5 = "java.awt."
            boolean r5 = r4.startsWith(r5)
            r6 = 0
            r7 = 1
            if (r5 == 0) goto L_0x0098
            boolean r5 = com.alibaba.fastjson.serializer.AwtCodec.support(r21)
            if (r5 == 0) goto L_0x0098
            boolean r5 = awtError
            if (r5 != 0) goto L_0x0098
            java.lang.String r3 = "java.awt.Point"
            java.lang.String r5 = "java.awt.Font"
            java.lang.String r8 = "java.awt.Rectangle"
            java.lang.String r9 = "java.awt.Color"
            java.lang.String[] r3 = new java.lang.String[]{r3, r5, r8, r9}
            int r5 = r3.length     // Catch:{ Throwable -> 0x0094 }
            r8 = 0
        L_0x007b:
            if (r8 >= r5) goto L_0x0096
            r9 = r3[r8]     // Catch:{ Throwable -> 0x0094 }
            boolean r10 = r9.equals(r4)     // Catch:{ Throwable -> 0x0094 }
            if (r10 == 0) goto L_0x0091
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers     // Catch:{ Throwable -> 0x0094 }
            java.lang.Class r5 = java.lang.Class.forName(r9)     // Catch:{ Throwable -> 0x0094 }
            com.alibaba.fastjson.serializer.AwtCodec r8 = com.alibaba.fastjson.serializer.AwtCodec.instance     // Catch:{ Throwable -> 0x0094 }
            r3.put(r5, r8)     // Catch:{ Throwable -> 0x0094 }
            return r8
        L_0x0091:
            int r8 = r8 + 1
            goto L_0x007b
        L_0x0094:
            awtError = r7
        L_0x0096:
            com.alibaba.fastjson.serializer.AwtCodec r3 = com.alibaba.fastjson.serializer.AwtCodec.instance
        L_0x0098:
            boolean r5 = jdk8Error
            if (r5 != 0) goto L_0x010c
            java.lang.String r5 = "java.time."
            boolean r5 = r4.startsWith(r5)     // Catch:{ Throwable -> 0x010a }
            if (r5 == 0) goto L_0x00da
            java.lang.String r8 = "java.time.LocalDateTime"
            java.lang.String r9 = "java.time.LocalDate"
            java.lang.String r10 = "java.time.LocalTime"
            java.lang.String r11 = "java.time.ZonedDateTime"
            java.lang.String r12 = "java.time.OffsetDateTime"
            java.lang.String r13 = "java.time.OffsetTime"
            java.lang.String r14 = "java.time.ZoneOffset"
            java.lang.String r15 = "java.time.ZoneRegion"
            java.lang.String r16 = "java.time.ZoneId"
            java.lang.String r17 = "java.time.Period"
            java.lang.String r18 = "java.time.Duration"
            java.lang.String r19 = "java.time.Instant"
            java.lang.String[] r5 = new java.lang.String[]{r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19}     // Catch:{ Throwable -> 0x010a }
            int r8 = r5.length     // Catch:{ Throwable -> 0x010a }
        L_0x00c1:
            if (r6 >= r8) goto L_0x010c
            r9 = r5[r6]     // Catch:{ Throwable -> 0x010a }
            boolean r10 = r9.equals(r4)     // Catch:{ Throwable -> 0x010a }
            if (r10 == 0) goto L_0x00d7
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r5 = r0.deserializers     // Catch:{ Throwable -> 0x010a }
            java.lang.Class r6 = java.lang.Class.forName(r9)     // Catch:{ Throwable -> 0x010a }
            com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec r8 = com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec.instance     // Catch:{ Throwable -> 0x010a }
            r5.put(r6, r8)     // Catch:{ Throwable -> 0x0105 }
            return r8
        L_0x00d7:
            int r6 = r6 + 1
            goto L_0x00c1
        L_0x00da:
            java.lang.String r5 = "java.util.Optional"
            boolean r5 = r4.startsWith(r5)     // Catch:{ Throwable -> 0x010a }
            if (r5 == 0) goto L_0x010c
            java.lang.String r5 = "java.util.Optional"
            java.lang.String r8 = "java.util.OptionalDouble"
            java.lang.String r9 = "java.util.OptionalInt"
            java.lang.String r10 = "java.util.OptionalLong"
            java.lang.String[] r5 = new java.lang.String[]{r5, r8, r9, r10}     // Catch:{ Throwable -> 0x010a }
            int r8 = r5.length     // Catch:{ Throwable -> 0x010a }
        L_0x00ef:
            if (r6 >= r8) goto L_0x010c
            r9 = r5[r6]     // Catch:{ Throwable -> 0x010a }
            boolean r10 = r9.equals(r4)     // Catch:{ Throwable -> 0x010a }
            if (r10 == 0) goto L_0x0107
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r5 = r0.deserializers     // Catch:{ Throwable -> 0x010a }
            java.lang.Class r6 = java.lang.Class.forName(r9)     // Catch:{ Throwable -> 0x010a }
            com.alibaba.fastjson.parser.deserializer.OptionalCodec r8 = com.alibaba.fastjson.parser.deserializer.OptionalCodec.instance     // Catch:{ Throwable -> 0x010a }
            r5.put(r6, r8)     // Catch:{ Throwable -> 0x0105 }
            return r8
        L_0x0105:
            r3 = r8
            goto L_0x010a
        L_0x0107:
            int r6 = r6 + 1
            goto L_0x00ef
        L_0x010a:
            jdk8Error = r7
        L_0x010c:
            java.lang.String r5 = "java.nio.file.Path"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x011c
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers
            com.alibaba.fastjson.serializer.MiscCodec r4 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r3.put(r1, r4)
            r3 = r4
        L_0x011c:
            java.lang.Class<java.util.Map$Entry> r4 = java.util.Map.Entry.class
            if (r1 != r4) goto L_0x0128
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers
            com.alibaba.fastjson.serializer.MiscCodec r4 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r3.put(r1, r4)
            r3 = r4
        L_0x0128:
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r4 = r4.getContextClassLoader()
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer> r5 = com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer.class
            java.util.Set r4 = com.alibaba.fastjson.util.ServiceLoader.load(r5, (java.lang.ClassLoader) r4)     // Catch:{ Exception -> 0x0160 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x0160 }
        L_0x013a:
            boolean r5 = r4.hasNext()     // Catch:{ Exception -> 0x0160 }
            if (r5 == 0) goto L_0x0160
            java.lang.Object r5 = r4.next()     // Catch:{ Exception -> 0x0160 }
            com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer r5 = (com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer) r5     // Catch:{ Exception -> 0x0160 }
            java.util.Set r6 = r5.getAutowiredFor()     // Catch:{ Exception -> 0x0160 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x0160 }
        L_0x014e:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0160 }
            if (r7 == 0) goto L_0x013a
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0160 }
            java.lang.reflect.Type r7 = (java.lang.reflect.Type) r7     // Catch:{ Exception -> 0x0160 }
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r8 = r0.deserializers     // Catch:{ Exception -> 0x0160 }
            r8.put(r7, r5)     // Catch:{ Exception -> 0x0160 }
            goto L_0x014e
        L_0x0160:
            if (r3 != 0) goto L_0x016a
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r3 = r0.deserializers
            java.lang.Object r3 = r3.get(r2)
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r3
        L_0x016a:
            if (r3 == 0) goto L_0x016d
            return r3
        L_0x016d:
            boolean r3 = r21.isEnum()
            if (r3 == 0) goto L_0x0193
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r3 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r3 = r1.getAnnotation(r3)
            com.alibaba.fastjson.annotation.JSONType r3 = (com.alibaba.fastjson.annotation.JSONType) r3
            if (r3 == 0) goto L_0x018d
            java.lang.Class r3 = r3.deserializer()
            java.lang.Object r3 = r3.newInstance()     // Catch:{ Throwable -> 0x018d }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r3     // Catch:{ Throwable -> 0x018d }
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r4 = r0.deserializers     // Catch:{ Throwable -> 0x018d }
            r4.put(r1, r3)     // Catch:{ Throwable -> 0x018d }
            return r3
        L_0x018d:
            com.alibaba.fastjson.parser.deserializer.EnumDeserializer r3 = new com.alibaba.fastjson.parser.deserializer.EnumDeserializer
            r3.<init>(r1)
            goto L_0x01ea
        L_0x0193:
            boolean r3 = r21.isArray()
            if (r3 == 0) goto L_0x019c
            com.alibaba.fastjson.serializer.ObjectArrayCodec r3 = com.alibaba.fastjson.serializer.ObjectArrayCodec.instance
            goto L_0x01ea
        L_0x019c:
            java.lang.Class<java.util.Set> r3 = java.util.Set.class
            if (r1 == r3) goto L_0x01e8
            java.lang.Class<java.util.HashSet> r3 = java.util.HashSet.class
            if (r1 == r3) goto L_0x01e8
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            if (r1 == r3) goto L_0x01e8
            java.lang.Class<java.util.List> r3 = java.util.List.class
            if (r1 == r3) goto L_0x01e8
            java.lang.Class<java.util.ArrayList> r3 = java.util.ArrayList.class
            if (r1 != r3) goto L_0x01b1
            goto L_0x01e8
        L_0x01b1:
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x01bc
            com.alibaba.fastjson.serializer.CollectionCodec r3 = com.alibaba.fastjson.serializer.CollectionCodec.instance
            goto L_0x01ea
        L_0x01bc:
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x01c7
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r3 = com.alibaba.fastjson.parser.deserializer.MapDeserializer.instance
            goto L_0x01ea
        L_0x01c7:
            java.lang.Class<java.lang.Throwable> r3 = java.lang.Throwable.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x01d5
            com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer r3 = new com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer
            r3.<init>(r0, r1)
            goto L_0x01ea
        L_0x01d5:
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.PropertyProcessable> r3 = com.alibaba.fastjson.parser.deserializer.PropertyProcessable.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x01e3
            com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer r3 = new com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer
            r3.<init>(r1)
            goto L_0x01ea
        L_0x01e3:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = r0.createJavaBeanDeserializer(r1, r2)
            goto L_0x01ea
        L_0x01e8:
            com.alibaba.fastjson.serializer.CollectionCodec r3 = com.alibaba.fastjson.serializer.CollectionCodec.instance
        L_0x01ea:
            r0.putDeserializer(r2, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.ParserConfig.getDeserializer(java.lang.Class, java.lang.reflect.Type):com.alibaba.fastjson.parser.deserializer.ObjectDeserializer");
    }

    public void initJavaBeanDeserializers(Class<?>... clsArr) {
        if (clsArr != null) {
            for (Class<?> cls : clsArr) {
                if (cls != null) {
                    putDeserializer(cls, createJavaBeanDeserializer(cls, cls));
                }
            }
        }
    }

    public ObjectDeserializer createJavaBeanDeserializer(Class<?> cls, Type type) {
        JSONField annotation;
        boolean z = this.asmEnable & (!this.fieldBased);
        if (z) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
            if (jSONType != null) {
                Class<?> deserializer = jSONType.deserializer();
                if (deserializer != Void.class) {
                    try {
                        Object newInstance = deserializer.newInstance();
                        if (newInstance instanceof ObjectDeserializer) {
                            return (ObjectDeserializer) newInstance;
                        }
                    } catch (Throwable unused) {
                    }
                }
                z = jSONType.asm();
            }
            if (z) {
                Class<?> builderClass = JavaBeanInfo.getBuilderClass(cls, jSONType);
                if (builderClass == null) {
                    builderClass = cls;
                }
                while (true) {
                    if (Modifier.isPublic(builderClass.getModifiers())) {
                        builderClass = builderClass.getSuperclass();
                        if (builderClass != Object.class) {
                            if (builderClass == null) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            }
        }
        if (cls.getTypeParameters().length != 0) {
            z = false;
        }
        if (z && this.asmFactory != null && this.asmFactory.classLoader.isExternalClass(cls)) {
            z = false;
        }
        if (z) {
            z = ASMUtils.checkName(cls.getSimpleName());
        }
        if (z) {
            if (cls.isInterface()) {
                z = false;
            }
            JavaBeanInfo build = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy);
            if (z && build.fields.length > 200) {
                z = false;
            }
            Constructor<?> constructor = build.defaultConstructor;
            if (z && constructor == null && !cls.isInterface()) {
                z = false;
            }
            FieldInfo[] fieldInfoArr = build.fields;
            int length = fieldInfoArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                FieldInfo fieldInfo = fieldInfoArr[i];
                if (!fieldInfo.getOnly) {
                    Class<?> cls2 = fieldInfo.fieldClass;
                    if (!Modifier.isPublic(cls2.getModifiers()) || ((cls2.isMemberClass() && !Modifier.isStatic(cls2.getModifiers())) || ((fieldInfo.getMember() != null && !ASMUtils.checkName(fieldInfo.getMember().getName())) || (((annotation = fieldInfo.getAnnotation()) != null && (!ASMUtils.checkName(annotation.name()) || annotation.format().length() != 0 || annotation.deserializeUsing() != Void.class || annotation.unwrapped())) || ((fieldInfo.method != null && fieldInfo.method.getParameterTypes().length > 1) || (cls2.isEnum() && !(getDeserializer((Type) cls2) instanceof EnumDeserializer))))))) {
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            z = false;
        }
        if (z && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            z = false;
        }
        if (!z) {
            return new JavaBeanDeserializer(this, cls, type);
        }
        JavaBeanInfo build2 = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy);
        try {
            return this.asmFactory.createJavaBeanDeserializer(this, build2);
        } catch (NoSuchMethodException unused2) {
            return new JavaBeanDeserializer(this, cls, type);
        } catch (JSONException unused3) {
            return new JavaBeanDeserializer(this, build2);
        } catch (Exception e) {
            throw new JSONException("create asm deserializer error, " + cls.getName(), e);
        }
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, FieldInfo fieldInfo) {
        Class<?> deserializeUsing;
        Class<?> cls = javaBeanInfo.clazz;
        Class<?> cls2 = fieldInfo.fieldClass;
        JSONField annotation = fieldInfo.getAnnotation();
        Class<?> cls3 = null;
        if (!(annotation == null || (deserializeUsing = annotation.deserializeUsing()) == Void.class)) {
            cls3 = deserializeUsing;
        }
        if (cls3 == null && (cls2 == List.class || cls2 == ArrayList.class)) {
            return new ArrayListTypeFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return new DefaultFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public void putDeserializer(Type type, ObjectDeserializer objectDeserializer) {
        this.deserializers.put(type, objectDeserializer);
    }

    public ObjectDeserializer getDeserializer(FieldInfo fieldInfo) {
        return getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
    }

    public boolean isPrimitive(Class<?> cls) {
        return isPrimitive2(cls);
    }

    public static boolean isPrimitive2(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == String.class || cls == java.util.Date.class || cls == Date.class || cls == Time.class || cls == Timestamp.class || cls.isEnum();
    }

    public static void parserAllFieldToCache(Class<?> cls, Map<String, Field> map) {
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getName();
            if (!map.containsKey(name)) {
                map.put(name, field);
            }
        }
        if (cls.getSuperclass() != null && cls.getSuperclass() != Object.class) {
            parserAllFieldToCache(cls.getSuperclass(), map);
        }
    }

    public static Field getFieldFromCache(String str, Map<String, Field> map) {
        Field field = map.get(str);
        if (field == null) {
            field = map.get("_" + str);
        }
        if (field == null) {
            field = map.get("m_" + str);
        }
        if (field != null) {
            return field;
        }
        char charAt = str.charAt(0);
        if (charAt >= 'a' && charAt <= 'z') {
            char[] charArray = str.toCharArray();
            charArray[0] = (char) (charArray[0] - ' ');
            field = map.get(new String(charArray));
        }
        if (str.length() <= 2) {
            return field;
        }
        char charAt2 = str.charAt(1);
        if (str.length() <= 2 || charAt < 'a' || charAt > 'z' || charAt2 < 'A' || charAt2 > 'Z') {
            return field;
        }
        for (Map.Entry next : map.entrySet()) {
            if (str.equalsIgnoreCase((String) next.getKey())) {
                return (Field) next.getValue();
            }
        }
        return field;
    }

    public ClassLoader getDefaultClassLoader() {
        return this.defaultClassLoader;
    }

    public void setDefaultClassLoader(ClassLoader classLoader) {
        this.defaultClassLoader = classLoader;
    }

    public void addDeny(String str) {
        if (str != null && str.length() != 0) {
            long fnv1a_64 = TypeUtils.fnv1a_64(str);
            if (Arrays.binarySearch(this.denyHashCodes, fnv1a_64) < 0) {
                long[] jArr = new long[(this.denyHashCodes.length + 1)];
                jArr[jArr.length - 1] = fnv1a_64;
                System.arraycopy(this.denyHashCodes, 0, jArr, 0, this.denyHashCodes.length);
                Arrays.sort(jArr);
                this.denyHashCodes = jArr;
            }
        }
    }

    public void addAccept(String str) {
        if (str != null && str.length() != 0) {
            long fnv1a_64 = TypeUtils.fnv1a_64(str);
            if (Arrays.binarySearch(this.acceptHashCodes, fnv1a_64) < 0) {
                long[] jArr = new long[(this.acceptHashCodes.length + 1)];
                jArr[jArr.length - 1] = fnv1a_64;
                System.arraycopy(this.acceptHashCodes, 0, jArr, 0, this.acceptHashCodes.length);
                Arrays.sort(jArr);
                this.acceptHashCodes = jArr;
            }
        }
    }

    public Class<?> checkAutoType(String str, Class<?> cls) {
        return checkAutoType(str, cls, JSON.DEFAULT_PARSER_FEATURE);
    }

    public Class<?> checkAutoType(String str, Class<?> cls, int i) {
        Class<?> cls2;
        String str2 = str;
        Class<?> cls3 = cls;
        if (str2 == null) {
            return null;
        }
        if (str.length() < 128) {
            int i2 = 3;
            if (str.length() >= 3) {
                String replace = str2.replace('$', '.');
                long charAt = (((long) replace.charAt(0)) ^ -3750763034362895579L) * 1099511628211L;
                if (charAt == -5808493101479473382L) {
                    throw new JSONException("autoType is not support. " + str2);
                } else if ((charAt ^ ((long) replace.charAt(replace.length() - 1))) * 1099511628211L != 655701488918567152L) {
                    long charAt2 = (((((((long) replace.charAt(0)) ^ -3750763034362895579L) * 1099511628211L) ^ ((long) replace.charAt(1))) * 1099511628211L) ^ ((long) replace.charAt(2))) * 1099511628211L;
                    if (this.autoTypeSupport || cls3 != null) {
                        Class<?> cls4 = null;
                        long j = charAt2;
                        int i3 = 3;
                        while (i3 < replace.length()) {
                            Class<?> cls5 = cls4;
                            j = (((long) replace.charAt(i3)) ^ j) * 1099511628211L;
                            if (Arrays.binarySearch(this.acceptHashCodes, j) >= 0) {
                                cls4 = TypeUtils.loadClass(str2, this.defaultClassLoader, false);
                                if (cls4 != null) {
                                    return cls4;
                                }
                            } else {
                                cls4 = cls5;
                            }
                            if (Arrays.binarySearch(this.denyHashCodes, j) < 0 || TypeUtils.getClassFromMapping(str) != null) {
                                i3++;
                            } else {
                                throw new JSONException("autoType is not support. " + str2);
                            }
                        }
                        cls2 = cls4;
                    } else {
                        cls2 = null;
                    }
                    if (cls2 == null) {
                        cls2 = TypeUtils.getClassFromMapping(str);
                    }
                    if (cls2 == null) {
                        cls2 = this.deserializers.findClass(str2);
                    }
                    Class<?> cls6 = cls2;
                    if (cls6 == null) {
                        if (!this.autoTypeSupport) {
                            while (i2 < replace.length()) {
                                charAt2 = (charAt2 ^ ((long) replace.charAt(i2))) * 1099511628211L;
                                if (Arrays.binarySearch(this.denyHashCodes, charAt2) >= 0) {
                                    throw new JSONException("autoType is not support. " + str2);
                                } else if (Arrays.binarySearch(this.acceptHashCodes, charAt2) >= 0) {
                                    if (cls6 == null) {
                                        cls6 = TypeUtils.loadClass(str2, this.defaultClassLoader, false);
                                    }
                                    if (cls3 == null || !cls3.isAssignableFrom(cls6)) {
                                        return cls6;
                                    }
                                    throw new JSONException("type not match. " + str2 + " -> " + cls.getName());
                                } else {
                                    i2++;
                                }
                            }
                        }
                        if (cls6 == null) {
                            cls6 = TypeUtils.loadClass(str2, this.defaultClassLoader, false);
                        }
                        if (cls6 != null) {
                            if (TypeUtils.getAnnotation(cls6, JSONType.class) != null) {
                                return cls6;
                            }
                            if (ClassLoader.class.isAssignableFrom(cls6) || DataSource.class.isAssignableFrom(cls6)) {
                                throw new JSONException("autoType is not support. " + str2);
                            } else if (cls3 != null) {
                                if (cls3.isAssignableFrom(cls6)) {
                                    return cls6;
                                }
                                throw new JSONException("type not match. " + str2 + " -> " + cls.getName());
                            } else if (JavaBeanInfo.build(cls6, cls6, this.propertyNamingStrategy).creatorConstructor != null && this.autoTypeSupport) {
                                throw new JSONException("autoType is not support. " + str2);
                            }
                        }
                        int i4 = Feature.SupportAutoType.mask;
                        if ((!this.autoTypeSupport && (i & i4) == 0 && (i4 & JSON.DEFAULT_PARSER_FEATURE) == 0) ? false : true) {
                            return cls6;
                        }
                        throw new JSONException("autoType is not support. " + str2);
                    } else if (cls3 == null || cls6 == HashMap.class || cls3.isAssignableFrom(cls6)) {
                        return cls6;
                    } else {
                        throw new JSONException("type not match. " + str2 + " -> " + cls.getName());
                    }
                } else {
                    throw new JSONException("autoType is not support. " + str2);
                }
            }
        }
        throw new JSONException("autoType is not support. " + str2);
    }

    public void clearDeserializers() {
        this.deserializers.clear();
        initDeserializers();
    }
}
