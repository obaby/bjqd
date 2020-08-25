package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
    protected transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    static {
        for (Class add : new Class[]{Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class}) {
            primitiveClasses.add(add);
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat2) {
        this.dateFormat = dateFormat2;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this((Object) str, (JSONLexer) new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this((Object) str, (JSONLexer) new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this((Object) cArr, (JSONLexer) new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        if (this.input instanceof char[]) {
            return new String((char[]) this.input);
        }
        return this.input.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v57, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v60, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v61, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v62, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v63, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v64, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v65, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v66, resolved type: java.util.Date} */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x026b, code lost:
        r3.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0276, code lost:
        if (r3.token() != 13) goto L_0x02e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0278, code lost:
        r3.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        r0 = r1.config.getDeserializer((java.lang.reflect.Type) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0283, code lost:
        if ((r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L_0x02b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0285, code lost:
        r0 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0;
        r2 = r0.createInstance(r1, (java.lang.reflect.Type) r6);
        r3 = r8.entrySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0297, code lost:
        if (r3.hasNext() == false) goto L_0x02b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0299, code lost:
        r4 = (java.util.Map.Entry) r3.next();
        r7 = r4.getKey();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02a5, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02a7, code lost:
        r7 = r0.getFieldDeserializer((java.lang.String) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02ad, code lost:
        if (r7 == null) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02af, code lost:
        r7.setValue((java.lang.Object) r2, r4.getValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02b7, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02b8, code lost:
        if (r2 != null) goto L_0x02d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02bc, code lost:
        if (r6 != java.lang.Cloneable.class) goto L_0x02c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x02be, code lost:
        r2 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02ca, code lost:
        if ("java.util.Collections$EmptyMap".equals(r5) == false) goto L_0x02d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02cc, code lost:
        r2 = java.util.Collections.emptyMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02d1, code lost:
        r2 = r6.newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02d5, code lost:
        setContext(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02d8, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x02e2, code lost:
        setResolveStatus(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02e8, code lost:
        if (r1.context == null) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02ea, code lost:
        if (r2 == null) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02ee, code lost:
        if ((r2 instanceof java.lang.Integer) != false) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02f6, code lost:
        if ((r1.context.fieldName instanceof java.lang.Integer) != false) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02f8, code lost:
        popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x02ff, code lost:
        if (r19.size() <= 0) goto L_0x030e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0301, code lost:
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r6, r1.config);
        parseObject((java.lang.Object) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x030a, code lost:
        setContext(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x030d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:?, code lost:
        r0 = r1.config.getDeserializer((java.lang.reflect.Type) r6);
        r3 = r0.getClass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x031e, code lost:
        if (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class.isAssignableFrom(r3) == false) goto L_0x032c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0322, code lost:
        if (r3 == com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class) goto L_0x032c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0326, code lost:
        if (r3 == com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer.class) goto L_0x032c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0328, code lost:
        setResolveStatus(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x032c, code lost:
        r0 = r0.deserialze(r1, r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0330, code lost:
        setContext(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0333, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0344, code lost:
        r3.nextToken(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x034c, code lost:
        if (r3.token() != 4) goto L_0x03dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x034e, code lost:
        r0 = r3.stringVal();
        r3.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x035d, code lost:
        if ("@".equals(r0) == false) goto L_0x0379;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0361, code lost:
        if (r1.context == null) goto L_0x03c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0363, code lost:
        r0 = r1.context;
        r5 = r0.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0369, code lost:
        if ((r5 instanceof java.lang.Object[]) != false) goto L_0x03c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x036d, code lost:
        if ((r5 instanceof java.util.Collection) == false) goto L_0x0370;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0372, code lost:
        if (r0.parent == null) goto L_0x03c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0374, code lost:
        r5 = r0.parent.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x037f, code lost:
        if ("..".equals(r0) == false) goto L_0x0394;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0383, code lost:
        if (r11.object == null) goto L_0x0388;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0385, code lost:
        r5 = r11.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0388, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r11, r0));
        setResolveStatus(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x039a, code lost:
        if ("$".equals(r0) == false) goto L_0x03b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x039c, code lost:
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x039f, code lost:
        if (r2.parent == null) goto L_0x03a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x03a1, code lost:
        r2 = r2.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x03a6, code lost:
        if (r2.object == null) goto L_0x03ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x03a8, code lost:
        r5 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x03ac, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r0));
        setResolveStatus(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x03b8, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r11, r0));
        setResolveStatus(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x03c3, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x03ca, code lost:
        if (r3.token() != 13) goto L_0x03d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x03cc, code lost:
        r3.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x03d1, code lost:
        setContext(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x03d4, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x03dc, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x03fb, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r3.token()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x048a, code lost:
        if (r6 != '}') goto L_0x049c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x048c, code lost:
        r3.next();
        r3.resetStringPosition();
        r3.nextToken();
        setContext(r5, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0498, code lost:
        setContext(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x049b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x04be, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, position at " + r3.pos() + ", name " + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0106, code lost:
        if (r11 == null) goto L_0x0100;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0210 A[Catch:{ Exception -> 0x02d9, NumberFormatException -> 0x019e, all -> 0x0623 }] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0229 A[Catch:{ Exception -> 0x02d9, NumberFormatException -> 0x019e, all -> 0x0623 }] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0570 A[Catch:{ Exception -> 0x02d9, NumberFormatException -> 0x019e, all -> 0x0623 }] */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x057c A[Catch:{ Exception -> 0x02d9, NumberFormatException -> 0x019e, all -> 0x0623 }] */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0588 A[Catch:{ Exception -> 0x02d9, NumberFormatException -> 0x019e, all -> 0x0623 }] */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x059d A[SYNTHETIC, Splitter:B:331:0x059d] */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x0593 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r19, java.lang.Object r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            com.alibaba.fastjson.parser.JSONLexer r3 = r1.lexer
            int r4 = r3.token()
            r5 = 0
            r6 = 8
            if (r4 != r6) goto L_0x0015
            r3.nextToken()
            return r5
        L_0x0015:
            int r4 = r3.token()
            r6 = 13
            if (r4 != r6) goto L_0x0021
            r3.nextToken()
            return r0
        L_0x0021:
            int r4 = r3.token()
            r7 = 4
            if (r4 != r7) goto L_0x0036
            java.lang.String r4 = r3.stringVal()
            int r4 = r4.length()
            if (r4 != 0) goto L_0x0036
            r3.nextToken()
            return r0
        L_0x0036:
            int r4 = r3.token()
            r8 = 12
            r9 = 16
            if (r4 == r8) goto L_0x006e
            int r4 = r3.token()
            if (r4 != r9) goto L_0x0047
            goto L_0x006e
        L_0x0047:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "syntax error, expect {, actual "
            r2.append(r4)
            java.lang.String r4 = r3.tokenName()
            r2.append(r4)
            java.lang.String r4 = ", "
            r2.append(r4)
            java.lang.String r3 = r3.info()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x006e:
            com.alibaba.fastjson.parser.ParseContext r4 = r1.context
            boolean r8 = r0 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0625 }
            if (r8 == 0) goto L_0x007c
            r8 = r0
            com.alibaba.fastjson.JSONObject r8 = (com.alibaba.fastjson.JSONObject) r8     // Catch:{ all -> 0x0625 }
            java.util.Map r8 = r8.getInnerMap()     // Catch:{ all -> 0x0625 }
            goto L_0x007d
        L_0x007c:
            r8 = r0
        L_0x007d:
            r11 = r4
            r4 = 0
        L_0x007f:
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
            char r12 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.Feature r13 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0623 }
            boolean r13 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r13)     // Catch:{ all -> 0x0623 }
            r14 = 44
            if (r13 == 0) goto L_0x009d
        L_0x0090:
            if (r12 != r14) goto L_0x009d
            r3.next()     // Catch:{ all -> 0x0623 }
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
            char r12 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            goto L_0x0090
        L_0x009d:
            r13 = 45
            r15 = 57
            r7 = 48
            r10 = 125(0x7d, float:1.75E-43)
            r9 = 58
            r5 = 34
            r6 = 1
            if (r12 != r5) goto L_0x00e1
            com.alibaba.fastjson.parser.SymbolTable r12 = r1.symbolTable     // Catch:{ all -> 0x0623 }
            java.lang.String r12 = r3.scanSymbol(r12, r5)     // Catch:{ all -> 0x0623 }
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
            char r5 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            if (r5 != r9) goto L_0x00be
        L_0x00bb:
            r5 = 0
            goto L_0x020e
        L_0x00be:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "expect ':' at "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            int r3 = r3.pos()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            r2.append(r12)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x00e1:
            if (r12 != r10) goto L_0x010d
            r3.next()     // Catch:{ all -> 0x0623 }
            r3.resetStringPosition()     // Catch:{ all -> 0x0623 }
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            if (r4 != 0) goto L_0x0109
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0623 }
            if (r3 == 0) goto L_0x0102
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0623 }
            java.lang.Object r3 = r3.fieldName     // Catch:{ all -> 0x0623 }
            if (r2 != r3) goto L_0x0102
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0623 }
            java.lang.Object r3 = r3.object     // Catch:{ all -> 0x0623 }
            if (r0 != r3) goto L_0x0102
            com.alibaba.fastjson.parser.ParseContext r2 = r1.context     // Catch:{ all -> 0x0623 }
        L_0x0100:
            r11 = r2
            goto L_0x0109
        L_0x0102:
            com.alibaba.fastjson.parser.ParseContext r2 = r18.setContext(r19, r20)     // Catch:{ all -> 0x0623 }
            if (r11 != 0) goto L_0x0109
            goto L_0x0100
        L_0x0109:
            r1.setContext(r11)
            return r0
        L_0x010d:
            r5 = 39
            if (r12 != r5) goto L_0x014c
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0623 }
            boolean r12 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r12)     // Catch:{ all -> 0x0623 }
            if (r12 == 0) goto L_0x0144
            com.alibaba.fastjson.parser.SymbolTable r12 = r1.symbolTable     // Catch:{ all -> 0x0623 }
            java.lang.String r12 = r3.scanSymbol(r12, r5)     // Catch:{ all -> 0x0623 }
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
            char r5 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            if (r5 != r9) goto L_0x0129
            goto L_0x00bb
        L_0x0129:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "expect ':' at "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            int r3 = r3.pos()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x0144:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x014c:
            r5 = 26
            if (r12 == r5) goto L_0x061b
            if (r12 == r14) goto L_0x0613
            if (r12 < r7) goto L_0x0156
            if (r12 <= r15) goto L_0x0158
        L_0x0156:
            if (r12 != r13) goto L_0x01b9
        L_0x0158:
            r3.resetStringPosition()     // Catch:{ all -> 0x0623 }
            r3.scanNumber()     // Catch:{ all -> 0x0623 }
            int r5 = r3.token()     // Catch:{ NumberFormatException -> 0x019e }
            r12 = 2
            if (r5 != r12) goto L_0x016a
            java.lang.Number r5 = r3.integerValue()     // Catch:{ NumberFormatException -> 0x019e }
            goto L_0x016e
        L_0x016a:
            java.lang.Number r5 = r3.decimalValue(r6)     // Catch:{ NumberFormatException -> 0x019e }
        L_0x016e:
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.NonStringKeyAsString     // Catch:{ NumberFormatException -> 0x019e }
            boolean r12 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r12)     // Catch:{ NumberFormatException -> 0x019e }
            if (r12 == 0) goto L_0x017a
            java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x019e }
        L_0x017a:
            r12 = r5
            char r5 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            if (r5 != r9) goto L_0x0183
            goto L_0x00bb
        L_0x0183:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "parse number key error"
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x019e:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "parse number key error"
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = r3.info()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x01b9:
            r5 = 123(0x7b, float:1.72E-43)
            if (r12 == r5) goto L_0x0206
            r5 = 91
            if (r12 != r5) goto L_0x01c2
            goto L_0x0206
        L_0x01c2:
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0623 }
            boolean r5 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0623 }
            if (r5 == 0) goto L_0x01fe
            com.alibaba.fastjson.parser.SymbolTable r5 = r1.symbolTable     // Catch:{ all -> 0x0623 }
            java.lang.String r12 = r3.scanSymbolUnQuoted(r5)     // Catch:{ all -> 0x0623 }
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
            char r5 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            if (r5 != r9) goto L_0x01db
            goto L_0x00bb
        L_0x01db:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "expect ':' at "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            int r3 = r3.pos()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = ", actual "
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            r2.append(r5)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x01fe:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x0206:
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            java.lang.Object r12 = r18.parse()     // Catch:{ all -> 0x0623 }
            r5 = 1
        L_0x020e:
            if (r5 != 0) goto L_0x0216
            r3.next()     // Catch:{ all -> 0x0623 }
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
        L_0x0216:
            char r5 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            r3.resetStringPosition()     // Catch:{ all -> 0x0623 }
            java.lang.String r9 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0623 }
            if (r12 != r9) goto L_0x0334
            com.alibaba.fastjson.parser.Feature r9 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0623 }
            boolean r9 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r9)     // Catch:{ all -> 0x0623 }
            if (r9 != 0) goto L_0x0334
            com.alibaba.fastjson.parser.SymbolTable r5 = r1.symbolTable     // Catch:{ all -> 0x0623 }
            r6 = 34
            java.lang.String r5 = r3.scanSymbol(r5, r6)     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.IgnoreAutoType     // Catch:{ all -> 0x0623 }
            boolean r6 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x023b
            r9 = 0
            goto L_0x0263
        L_0x023b:
            if (r0 == 0) goto L_0x0251
            java.lang.Class r6 = r19.getClass()     // Catch:{ all -> 0x0623 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x0623 }
            boolean r6 = r6.equals(r5)     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x0251
            java.lang.Class r6 = r19.getClass()     // Catch:{ all -> 0x0623 }
            r9 = 0
            goto L_0x025c
        L_0x0251:
            com.alibaba.fastjson.parser.ParserConfig r6 = r1.config     // Catch:{ all -> 0x0623 }
            int r7 = r3.getFeatures()     // Catch:{ all -> 0x0623 }
            r9 = 0
            java.lang.Class r6 = r6.checkAutoType(r5, r9, r7)     // Catch:{ all -> 0x0623 }
        L_0x025c:
            if (r6 != 0) goto L_0x026b
            java.lang.String r6 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0623 }
            r8.put(r6, r5)     // Catch:{ all -> 0x0623 }
        L_0x0263:
            r5 = r9
            r6 = 13
        L_0x0266:
            r7 = 4
            r9 = 16
            goto L_0x007f
        L_0x026b:
            r4 = 16
            r3.nextToken(r4)     // Catch:{ all -> 0x0623 }
            int r7 = r3.token()     // Catch:{ all -> 0x0623 }
            r10 = 13
            if (r7 != r10) goto L_0x02e2
            r3.nextToken(r4)     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.ParserConfig r0 = r1.config     // Catch:{ Exception -> 0x02d9 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r0 = r0.getDeserializer((java.lang.reflect.Type) r6)     // Catch:{ Exception -> 0x02d9 }
            boolean r2 = r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ Exception -> 0x02d9 }
            if (r2 == 0) goto L_0x02b7
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r0 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0     // Catch:{ Exception -> 0x02d9 }
            java.lang.Object r2 = r0.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r1, (java.lang.reflect.Type) r6)     // Catch:{ Exception -> 0x02d9 }
            java.util.Set r3 = r8.entrySet()     // Catch:{ Exception -> 0x02d9 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x02d9 }
        L_0x0293:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x02d9 }
            if (r4 == 0) goto L_0x02b8
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x02d9 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Exception -> 0x02d9 }
            java.lang.Object r7 = r4.getKey()     // Catch:{ Exception -> 0x02d9 }
            boolean r8 = r7 instanceof java.lang.String     // Catch:{ Exception -> 0x02d9 }
            if (r8 == 0) goto L_0x0293
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x02d9 }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r7 = r0.getFieldDeserializer((java.lang.String) r7)     // Catch:{ Exception -> 0x02d9 }
            if (r7 == 0) goto L_0x0293
            java.lang.Object r4 = r4.getValue()     // Catch:{ Exception -> 0x02d9 }
            r7.setValue((java.lang.Object) r2, (java.lang.Object) r4)     // Catch:{ Exception -> 0x02d9 }
            goto L_0x0293
        L_0x02b7:
            r2 = r9
        L_0x02b8:
            if (r2 != 0) goto L_0x02d5
            java.lang.Class<java.lang.Cloneable> r0 = java.lang.Cloneable.class
            if (r6 != r0) goto L_0x02c4
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x02d9 }
            r2.<init>()     // Catch:{ Exception -> 0x02d9 }
            goto L_0x02d5
        L_0x02c4:
            java.lang.String r0 = "java.util.Collections$EmptyMap"
            boolean r0 = r0.equals(r5)     // Catch:{ Exception -> 0x02d9 }
            if (r0 == 0) goto L_0x02d1
            java.util.Map r2 = java.util.Collections.emptyMap()     // Catch:{ Exception -> 0x02d9 }
            goto L_0x02d5
        L_0x02d1:
            java.lang.Object r2 = r6.newInstance()     // Catch:{ Exception -> 0x02d9 }
        L_0x02d5:
            r1.setContext(r11)
            return r2
        L_0x02d9:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = "create instance error"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0623 }
            throw r2     // Catch:{ all -> 0x0623 }
        L_0x02e2:
            r3 = 2
            r1.setResolveStatus(r3)     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0623 }
            if (r3 == 0) goto L_0x02fb
            if (r2 == 0) goto L_0x02fb
            boolean r3 = r2 instanceof java.lang.Integer     // Catch:{ all -> 0x0623 }
            if (r3 != 0) goto L_0x02fb
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0623 }
            java.lang.Object r3 = r3.fieldName     // Catch:{ all -> 0x0623 }
            boolean r3 = r3 instanceof java.lang.Integer     // Catch:{ all -> 0x0623 }
            if (r3 != 0) goto L_0x02fb
            r18.popContext()     // Catch:{ all -> 0x0623 }
        L_0x02fb:
            int r3 = r19.size()     // Catch:{ all -> 0x0623 }
            if (r3 <= 0) goto L_0x030e
            com.alibaba.fastjson.parser.ParserConfig r2 = r1.config     // Catch:{ all -> 0x0623 }
            java.lang.Object r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r6, (com.alibaba.fastjson.parser.ParserConfig) r2)     // Catch:{ all -> 0x0623 }
            r1.parseObject((java.lang.Object) r0)     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r0
        L_0x030e:
            com.alibaba.fastjson.parser.ParserConfig r0 = r1.config     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r0 = r0.getDeserializer((java.lang.reflect.Type) r6)     // Catch:{ all -> 0x0623 }
            java.lang.Class r3 = r0.getClass()     // Catch:{ all -> 0x0623 }
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r4 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            boolean r4 = r4.isAssignableFrom(r3)     // Catch:{ all -> 0x0623 }
            if (r4 == 0) goto L_0x032c
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r4 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            if (r3 == r4) goto L_0x032c
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer> r4 = com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer.class
            if (r3 == r4) goto L_0x032c
            r3 = 0
            r1.setResolveStatus(r3)     // Catch:{ all -> 0x0623 }
        L_0x032c:
            java.lang.Object r0 = r0.deserialze(r1, r6, r2)     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r0
        L_0x0334:
            r16 = 0
            java.lang.String r9 = "$ref"
            if (r12 != r9) goto L_0x03fc
            if (r11 == 0) goto L_0x03fc
            com.alibaba.fastjson.parser.Feature r9 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0623 }
            boolean r9 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r9)     // Catch:{ all -> 0x0623 }
            if (r9 != 0) goto L_0x03fc
            r9 = 4
            r3.nextToken(r9)     // Catch:{ all -> 0x0623 }
            int r0 = r3.token()     // Catch:{ all -> 0x0623 }
            if (r0 != r9) goto L_0x03dd
            java.lang.String r0 = r3.stringVal()     // Catch:{ all -> 0x0623 }
            r2 = 13
            r3.nextToken(r2)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "@"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x0379
            com.alibaba.fastjson.parser.ParseContext r0 = r1.context     // Catch:{ all -> 0x0623 }
            if (r0 == 0) goto L_0x03c3
            com.alibaba.fastjson.parser.ParseContext r0 = r1.context     // Catch:{ all -> 0x0623 }
            java.lang.Object r5 = r0.object     // Catch:{ all -> 0x0623 }
            boolean r2 = r5 instanceof java.lang.Object[]     // Catch:{ all -> 0x0623 }
            if (r2 != 0) goto L_0x03c4
            boolean r2 = r5 instanceof java.util.Collection     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x0370
            goto L_0x03c4
        L_0x0370:
            com.alibaba.fastjson.parser.ParseContext r2 = r0.parent     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x03c3
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0623 }
            java.lang.Object r5 = r0.object     // Catch:{ all -> 0x0623 }
            goto L_0x03c4
        L_0x0379:
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x0394
            java.lang.Object r2 = r11.object     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x0388
            java.lang.Object r5 = r11.object     // Catch:{ all -> 0x0623 }
            goto L_0x03c4
        L_0x0388:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0623 }
            r2.<init>(r11, r0)     // Catch:{ all -> 0x0623 }
            r1.addResolveTask(r2)     // Catch:{ all -> 0x0623 }
            r1.setResolveStatus(r6)     // Catch:{ all -> 0x0623 }
            goto L_0x03c3
        L_0x0394:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x03b8
            r2 = r11
        L_0x039d:
            com.alibaba.fastjson.parser.ParseContext r4 = r2.parent     // Catch:{ all -> 0x0623 }
            if (r4 == 0) goto L_0x03a4
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent     // Catch:{ all -> 0x0623 }
            goto L_0x039d
        L_0x03a4:
            java.lang.Object r4 = r2.object     // Catch:{ all -> 0x0623 }
            if (r4 == 0) goto L_0x03ac
            java.lang.Object r0 = r2.object     // Catch:{ all -> 0x0623 }
            r5 = r0
            goto L_0x03c4
        L_0x03ac:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0623 }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x0623 }
            r1.addResolveTask(r4)     // Catch:{ all -> 0x0623 }
            r1.setResolveStatus(r6)     // Catch:{ all -> 0x0623 }
            goto L_0x03c3
        L_0x03b8:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0623 }
            r2.<init>(r11, r0)     // Catch:{ all -> 0x0623 }
            r1.addResolveTask(r2)     // Catch:{ all -> 0x0623 }
            r1.setResolveStatus(r6)     // Catch:{ all -> 0x0623 }
        L_0x03c3:
            r5 = 0
        L_0x03c4:
            int r0 = r3.token()     // Catch:{ all -> 0x0623 }
            r2 = 13
            if (r0 != r2) goto L_0x03d5
            r0 = 16
            r3.nextToken(r0)     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r5
        L_0x03d5:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x03dd:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "illegal ref, "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            int r3 = r3.token()     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = com.alibaba.fastjson.parser.JSONToken.name(r3)     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x03fc:
            r9 = 4
            if (r4 != 0) goto L_0x041d
            com.alibaba.fastjson.parser.ParseContext r6 = r1.context     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x0413
            com.alibaba.fastjson.parser.ParseContext r6 = r1.context     // Catch:{ all -> 0x0623 }
            java.lang.Object r6 = r6.fieldName     // Catch:{ all -> 0x0623 }
            if (r2 != r6) goto L_0x0413
            com.alibaba.fastjson.parser.ParseContext r6 = r1.context     // Catch:{ all -> 0x0623 }
            java.lang.Object r6 = r6.object     // Catch:{ all -> 0x0623 }
            if (r0 != r6) goto L_0x0413
            com.alibaba.fastjson.parser.ParseContext r6 = r1.context     // Catch:{ all -> 0x0623 }
            r11 = r6
            goto L_0x041d
        L_0x0413:
            com.alibaba.fastjson.parser.ParseContext r4 = r18.setContext(r19, r20)     // Catch:{ all -> 0x0623 }
            if (r11 != 0) goto L_0x041a
            goto L_0x041b
        L_0x041a:
            r4 = r11
        L_0x041b:
            r11 = r4
            r4 = 1
        L_0x041d:
            java.lang.Class r6 = r19.getClass()     // Catch:{ all -> 0x0623 }
            java.lang.Class<com.alibaba.fastjson.JSONObject> r9 = com.alibaba.fastjson.JSONObject.class
            if (r6 != r9) goto L_0x0429
            if (r12 != 0) goto L_0x0429
            java.lang.String r12 = "null"
        L_0x0429:
            r6 = 34
            if (r5 != r6) goto L_0x0456
            r3.scanString()     // Catch:{ all -> 0x0623 }
            java.lang.String r5 = r3.stringVal()     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x0623 }
            boolean r6 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x0452
            com.alibaba.fastjson.parser.JSONScanner r6 = new com.alibaba.fastjson.parser.JSONScanner     // Catch:{ all -> 0x0623 }
            r6.<init>(r5)     // Catch:{ all -> 0x0623 }
            boolean r7 = r6.scanISO8601DateIfMatch()     // Catch:{ all -> 0x0623 }
            if (r7 == 0) goto L_0x044f
            java.util.Calendar r5 = r6.getCalendar()     // Catch:{ all -> 0x0623 }
            java.util.Date r5 = r5.getTime()     // Catch:{ all -> 0x0623 }
        L_0x044f:
            r6.close()     // Catch:{ all -> 0x0623 }
        L_0x0452:
            r8.put(r12, r5)     // Catch:{ all -> 0x0623 }
            goto L_0x0478
        L_0x0456:
            if (r5 < r7) goto L_0x045a
            if (r5 <= r15) goto L_0x045c
        L_0x045a:
            if (r5 != r13) goto L_0x04bf
        L_0x045c:
            r3.scanNumber()     // Catch:{ all -> 0x0623 }
            int r5 = r3.token()     // Catch:{ all -> 0x0623 }
            r6 = 2
            if (r5 != r6) goto L_0x046b
            java.lang.Number r5 = r3.integerValue()     // Catch:{ all -> 0x0623 }
            goto L_0x0475
        L_0x046b:
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.UseBigDecimal     // Catch:{ all -> 0x0623 }
            boolean r5 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0623 }
            java.lang.Number r5 = r3.decimalValue(r5)     // Catch:{ all -> 0x0623 }
        L_0x0475:
            r8.put(r12, r5)     // Catch:{ all -> 0x0623 }
        L_0x0478:
            r3.skipWhitespace()     // Catch:{ all -> 0x0623 }
            char r6 = r3.getCurrent()     // Catch:{ all -> 0x0623 }
            if (r6 != r14) goto L_0x048a
            r3.next()     // Catch:{ all -> 0x0623 }
        L_0x0484:
            r6 = 13
            r7 = 16
            goto L_0x05ed
        L_0x048a:
            if (r6 != r10) goto L_0x049c
            r3.next()     // Catch:{ all -> 0x0623 }
            r3.resetStringPosition()     // Catch:{ all -> 0x0623 }
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            r1.setContext(r5, r12)     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r0
        L_0x049c:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "syntax error, position at "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            int r3 = r3.pos()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            r2.append(r12)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x04bf:
            r6 = 91
            if (r5 != r6) goto L_0x050a
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.JSONArray r5 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x0623 }
            r5.<init>()     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x04d3
            java.lang.Class r6 = r20.getClass()     // Catch:{ all -> 0x0623 }
            java.lang.Class<java.lang.Integer> r7 = java.lang.Integer.class
        L_0x04d3:
            if (r2 != 0) goto L_0x04d8
            r1.setContext(r11)     // Catch:{ all -> 0x0623 }
        L_0x04d8:
            r1.parseArray((java.util.Collection) r5, (java.lang.Object) r12)     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.UseObjectArray     // Catch:{ all -> 0x0623 }
            boolean r6 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x04e7
            java.lang.Object[] r5 = r5.toArray()     // Catch:{ all -> 0x0623 }
        L_0x04e7:
            r8.put(r12, r5)     // Catch:{ all -> 0x0623 }
            int r5 = r3.token()     // Catch:{ all -> 0x0623 }
            r6 = 13
            if (r5 != r6) goto L_0x04f9
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r0
        L_0x04f9:
            int r5 = r3.token()     // Catch:{ all -> 0x0623 }
            r6 = 16
            if (r5 != r6) goto L_0x0502
            goto L_0x0484
        L_0x0502:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x050a:
            r6 = 123(0x7b, float:1.72E-43)
            if (r5 != r6) goto L_0x05cc
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x051d
            java.lang.Class r5 = r20.getClass()     // Catch:{ all -> 0x0623 }
            java.lang.Class<java.lang.Integer> r6 = java.lang.Integer.class
            if (r5 != r6) goto L_0x051d
            r5 = 1
            goto L_0x051e
        L_0x051d:
            r5 = 0
        L_0x051e:
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.CustomMapDeserializer     // Catch:{ all -> 0x0623 }
            boolean r6 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x0537
            com.alibaba.fastjson.parser.ParserConfig r6 = r1.config     // Catch:{ all -> 0x0623 }
            java.lang.Class<java.util.Map> r7 = java.util.Map.class
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r6 = r6.getDeserializer((java.lang.reflect.Type) r7)     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r6 = (com.alibaba.fastjson.parser.deserializer.MapDeserializer) r6     // Catch:{ all -> 0x0623 }
            java.lang.Class<java.util.Map> r7 = java.util.Map.class
            java.util.Map r6 = r6.createMap(r7)     // Catch:{ all -> 0x0623 }
            goto L_0x0542
        L_0x0537:
            com.alibaba.fastjson.JSONObject r6 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x0623 }
            boolean r7 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r7)     // Catch:{ all -> 0x0623 }
            r6.<init>((boolean) r7)     // Catch:{ all -> 0x0623 }
        L_0x0542:
            if (r5 != 0) goto L_0x0549
            com.alibaba.fastjson.parser.ParseContext r7 = r1.setContext(r11, r6, r12)     // Catch:{ all -> 0x0623 }
            goto L_0x054a
        L_0x0549:
            r7 = 0
        L_0x054a:
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r9 = r1.fieldTypeResolver     // Catch:{ all -> 0x0623 }
            if (r9 == 0) goto L_0x056b
            if (r12 == 0) goto L_0x0555
            java.lang.String r9 = r12.toString()     // Catch:{ all -> 0x0623 }
            goto L_0x0556
        L_0x0555:
            r9 = 0
        L_0x0556:
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r10 = r1.fieldTypeResolver     // Catch:{ all -> 0x0623 }
            java.lang.reflect.Type r9 = r10.resolve(r0, r9)     // Catch:{ all -> 0x0623 }
            if (r9 == 0) goto L_0x056b
            com.alibaba.fastjson.parser.ParserConfig r10 = r1.config     // Catch:{ all -> 0x0623 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r10 = r10.getDeserializer((java.lang.reflect.Type) r9)     // Catch:{ all -> 0x0623 }
            java.lang.Object r9 = r10.deserialze(r1, r9, r12)     // Catch:{ all -> 0x0623 }
            r17 = 1
            goto L_0x056e
        L_0x056b:
            r9 = 0
            r17 = 0
        L_0x056e:
            if (r17 != 0) goto L_0x0574
            java.lang.Object r9 = r1.parseObject((java.util.Map) r6, (java.lang.Object) r12)     // Catch:{ all -> 0x0623 }
        L_0x0574:
            if (r7 == 0) goto L_0x057a
            if (r6 == r9) goto L_0x057a
            r7.object = r0     // Catch:{ all -> 0x0623 }
        L_0x057a:
            if (r12 == 0) goto L_0x0583
            java.lang.String r6 = r12.toString()     // Catch:{ all -> 0x0623 }
            r1.checkMapResolve(r0, r6)     // Catch:{ all -> 0x0623 }
        L_0x0583:
            r8.put(r12, r9)     // Catch:{ all -> 0x0623 }
            if (r5 == 0) goto L_0x058b
            r1.setContext(r9, r12)     // Catch:{ all -> 0x0623 }
        L_0x058b:
            int r6 = r3.token()     // Catch:{ all -> 0x0623 }
            r7 = 13
            if (r6 != r7) goto L_0x059d
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r0
        L_0x059d:
            int r6 = r3.token()     // Catch:{ all -> 0x0623 }
            r7 = 16
            if (r6 != r7) goto L_0x05b1
            if (r5 == 0) goto L_0x05ac
            r18.popContext()     // Catch:{ all -> 0x0623 }
            goto L_0x0484
        L_0x05ac:
            r1.setContext(r11)     // Catch:{ all -> 0x0623 }
            goto L_0x0484
        L_0x05b1:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "syntax error, "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = r3.tokenName()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x05cc:
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            java.lang.Object r5 = r18.parse()     // Catch:{ all -> 0x0623 }
            r8.put(r12, r5)     // Catch:{ all -> 0x0623 }
            int r5 = r3.token()     // Catch:{ all -> 0x0623 }
            r6 = 13
            if (r5 != r6) goto L_0x05e5
            r3.nextToken()     // Catch:{ all -> 0x0623 }
            r1.setContext(r11)
            return r0
        L_0x05e5:
            int r5 = r3.token()     // Catch:{ all -> 0x0623 }
            r7 = 16
            if (r5 != r7) goto L_0x05f0
        L_0x05ed:
            r5 = 0
            goto L_0x0266
        L_0x05f0:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0623 }
            r2.<init>()     // Catch:{ all -> 0x0623 }
            java.lang.String r4 = "syntax error, position at "
            r2.append(r4)     // Catch:{ all -> 0x0623 }
            int r3 = r3.pos()     // Catch:{ all -> 0x0623 }
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            java.lang.String r3 = ", name "
            r2.append(r3)     // Catch:{ all -> 0x0623 }
            r2.append(r12)     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0623 }
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x0613:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x061b:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0623 }
            java.lang.String r2 = "syntax error"
            r0.<init>(r2)     // Catch:{ all -> 0x0623 }
            throw r0     // Catch:{ all -> 0x0623 }
        L_0x0623:
            r0 = move-exception
            goto L_0x0627
        L_0x0625:
            r0 = move-exception
            r11 = r4
        L_0x0627:
            r1.setContext(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return parseObject((Type) cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return parseObject(type, (Object) null);
    }

    public <T> T parseObject(Type type, Object obj) {
        int i = this.lexer.token();
        if (i == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                Object bytesValue = this.lexer.bytesValue();
                this.lexer.nextToken();
                return bytesValue;
            } else if (type == char[].class) {
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return stringVal.toCharArray();
            }
        }
        try {
            return this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer objectDeserializer;
        int i = this.lexer.token();
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token();
        }
        if (i == 14) {
            if (Integer.TYPE == type) {
                objectDeserializer = IntegerCodec.instance;
                this.lexer.nextToken(2);
            } else if (String.class == type) {
                objectDeserializer = StringCodec.instance;
                this.lexer.nextToken(4);
            } else {
                objectDeserializer = this.config.getDeserializer(type);
                this.lexer.nextToken(objectDeserializer.getFastMatchToken());
            }
            ParseContext parseContext = this.context;
            setContext(collection, obj);
            int i2 = 0;
            while (true) {
                try {
                    if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (this.lexer.token() == 16) {
                            this.lexer.nextToken();
                        }
                    }
                    if (this.lexer.token() == 15) {
                        setContext(parseContext);
                        this.lexer.nextToken(16);
                        return;
                    }
                    Object obj2 = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, (Type) null, (Object) null));
                    } else if (String.class == type) {
                        if (this.lexer.token() == 4) {
                            obj2 = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object parse = parse();
                            if (parse != null) {
                                obj2 = parse.toString();
                            }
                        }
                        collection.add(obj2);
                    } else {
                        if (this.lexer.token() == 8) {
                            this.lexer.nextToken();
                        } else {
                            obj2 = objectDeserializer.deserialze(this, type, Integer.valueOf(i2));
                        }
                        collection.add(obj2);
                        checkListResolve(collection);
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(objectDeserializer.getFastMatchToken());
                    }
                    i2++;
                } catch (Throwable th) {
                    setContext(parseContext);
                    throw th;
                }
            }
        } else {
            throw new JSONException("exepct '[', but " + JSONToken.name(i) + ", " + this.lexer.info());
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object obj;
        boolean z;
        Class<?> cls;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        } else if (this.lexer.token() == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token() == 15) {
                    this.lexer.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(2);
            int i = 0;
            while (i < typeArr.length) {
                if (this.lexer.token() == 8) {
                    this.lexer.nextToken(16);
                    obj = null;
                } else {
                    Class<String> cls2 = typeArr[i];
                    if (cls2 == Integer.TYPE || cls2 == Integer.class) {
                        if (this.lexer.token() == 2) {
                            obj = Integer.valueOf(this.lexer.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            obj = TypeUtils.cast(parse(), (Type) cls2, this.config);
                        }
                    } else if (cls2 != String.class) {
                        if (i != typeArr.length - 1 || !(cls2 instanceof Class)) {
                            cls = null;
                            z = false;
                        } else {
                            Class cls3 = cls2;
                            z = cls3.isArray();
                            cls = cls3.getComponentType();
                        }
                        if (!z || this.lexer.token() == 14) {
                            obj = this.config.getDeserializer((Type) cls2).deserialze(this, cls2, Integer.valueOf(i));
                        } else {
                            ArrayList arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer((Type) cls);
                            int fastMatchToken = deserializer.getFastMatchToken();
                            if (this.lexer.token() != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, cls2, (Object) null));
                                    if (this.lexer.token() != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(fastMatchToken);
                                }
                                if (this.lexer.token() != 15) {
                                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                                }
                            }
                            obj = TypeUtils.cast((Object) arrayList, (Type) cls2, this.config);
                        }
                    } else if (this.lexer.token() == 4) {
                        obj = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        obj = TypeUtils.cast(parse(), (Type) cls2, this.config);
                    }
                }
                objArr[i] = obj;
                if (this.lexer.token() == 15) {
                    break;
                } else if (this.lexer.token() == 16) {
                    if (i == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i++;
                } else {
                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                }
            }
            if (this.lexer.token() == 15) {
                this.lexer.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error");
        } else {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        }
    }

    public void parseObject(Object obj) {
        Object obj2;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer((Type) cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() == 12 || this.lexer.token() == 16) {
            while (true) {
                String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
                if (scanSymbol == null) {
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    } else if (this.lexer.token() == 16 && this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    }
                }
                FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
                if (fieldDeserializer != null) {
                    Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                    Type type = fieldDeserializer.fieldInfo.fieldType;
                    if (cls2 == Integer.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        obj2 = IntegerCodec.instance.deserialze(this, type, (Object) null);
                    } else if (cls2 == String.class) {
                        this.lexer.nextTokenWithColon(4);
                        obj2 = StringCodec.deserialze(this);
                    } else if (cls2 == Long.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        obj2 = LongCodec.instance.deserialze(this, type, (Object) null);
                    } else {
                        ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                        this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                        obj2 = deserializer2.deserialze(this, type, (Object) null);
                    }
                    fieldDeserializer.setValue(obj, obj2);
                    if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    }
                } else if (this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    this.lexer.nextTokenWithColon();
                    parse();
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
            }
        } else {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                ArrayList arrayList = new ArrayList();
                parseArray((Class<?>) (Class) type2, (Collection) arrayList);
                return arrayList;
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                Type type3 = wildcardType.getUpperBounds()[0];
                if (!Object.class.equals(type3)) {
                    ArrayList arrayList2 = new ArrayList();
                    parseArray((Class<?>) (Class) type3, (Collection) arrayList2);
                    return arrayList2;
                } else if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
                } else {
                    throw new JSONException("not support type : " + type);
                }
            } else {
                if (type2 instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) type2;
                    Type[] bounds = typeVariable.getBounds();
                    if (bounds.length == 1) {
                        Type type4 = bounds[0];
                        if (type4 instanceof Class) {
                            ArrayList arrayList3 = new ArrayList();
                            parseArray((Class<?>) (Class) type4, (Collection) arrayList3);
                            return arrayList3;
                        }
                    } else {
                        throw new JSONException("not support : " + typeVariable);
                    }
                }
                if (type2 instanceof ParameterizedType) {
                    ArrayList arrayList4 = new ArrayList();
                    parseArray((Type) (ParameterizedType) type2, (Collection) arrayList4);
                    return arrayList4;
                }
                throw new JSONException("TODO : " + type);
            }
        } else {
            throw new JSONException("not support type " + type);
        }
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        } else if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
            }
        } else {
            throw new JSONException("type not match error");
        }
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus != 1) {
            return;
        }
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.context;
        setResolveStatus(0);
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseArray(java.util.Collection r9, java.lang.Object r10) {
        /*
            r8 = this;
            com.alibaba.fastjson.parser.JSONLexer r0 = r8.lexer
            int r1 = r0.token()
            r2 = 21
            if (r1 == r2) goto L_0x0012
            int r1 = r0.token()
            r2 = 22
            if (r1 != r2) goto L_0x0015
        L_0x0012:
            r0.nextToken()
        L_0x0015:
            int r1 = r0.token()
            r2 = 14
            if (r1 != r2) goto L_0x00f9
            r1 = 4
            r0.nextToken(r1)
            com.alibaba.fastjson.parser.ParseContext r2 = r8.context
            r8.setContext(r9, r10)
            r10 = 0
            r3 = 0
        L_0x0028:
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x00f4 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x00f4 }
            r5 = 16
            if (r4 == 0) goto L_0x003c
        L_0x0032:
            int r4 = r0.token()     // Catch:{ all -> 0x00f4 }
            if (r4 != r5) goto L_0x003c
            r0.nextToken()     // Catch:{ all -> 0x00f4 }
            goto L_0x0032
        L_0x003c:
            int r4 = r0.token()     // Catch:{ all -> 0x00f4 }
            r6 = 0
            switch(r4) {
                case 2: goto L_0x00da;
                case 3: goto L_0x00c2;
                case 4: goto L_0x009c;
                case 6: goto L_0x0096;
                case 7: goto L_0x0090;
                case 8: goto L_0x008c;
                case 12: goto L_0x0078;
                case 14: goto L_0x005e;
                case 15: goto L_0x0057;
                case 20: goto L_0x004f;
                case 23: goto L_0x004a;
                default: goto L_0x0044;
            }     // Catch:{ all -> 0x00f4 }
        L_0x0044:
            java.lang.Object r6 = r8.parse()     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x004a:
            r0.nextToken(r1)     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x004f:
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x00f4 }
            java.lang.String r10 = "unclosed jsonArray"
            r9.<init>(r10)     // Catch:{ all -> 0x00f4 }
            throw r9     // Catch:{ all -> 0x00f4 }
        L_0x0057:
            r0.nextToken(r5)     // Catch:{ all -> 0x00f4 }
            r8.setContext(r2)
            return
        L_0x005e:
            com.alibaba.fastjson.JSONArray r6 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x00f4 }
            r6.<init>()     // Catch:{ all -> 0x00f4 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00f4 }
            r8.parseArray((java.util.Collection) r6, (java.lang.Object) r4)     // Catch:{ all -> 0x00f4 }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.UseObjectArray     // Catch:{ all -> 0x00f4 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x00f4 }
            if (r4 == 0) goto L_0x00e1
            java.lang.Object[] r6 = r6.toArray()     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x0078:
            com.alibaba.fastjson.JSONObject r4 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x00f4 }
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x00f4 }
            boolean r6 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x00f4 }
            r4.<init>((boolean) r6)     // Catch:{ all -> 0x00f4 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00f4 }
            java.lang.Object r6 = r8.parseObject((java.util.Map) r4, (java.lang.Object) r6)     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x008c:
            r0.nextToken(r1)     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x0090:
            java.lang.Boolean r6 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00f4 }
            r0.nextToken(r5)     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x0096:
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00f4 }
            r0.nextToken(r5)     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x009c:
            java.lang.String r6 = r0.stringVal()     // Catch:{ all -> 0x00f4 }
            r0.nextToken(r5)     // Catch:{ all -> 0x00f4 }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x00f4 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x00f4 }
            if (r4 == 0) goto L_0x00e1
            com.alibaba.fastjson.parser.JSONScanner r4 = new com.alibaba.fastjson.parser.JSONScanner     // Catch:{ all -> 0x00f4 }
            r4.<init>(r6)     // Catch:{ all -> 0x00f4 }
            boolean r7 = r4.scanISO8601DateIfMatch()     // Catch:{ all -> 0x00f4 }
            if (r7 == 0) goto L_0x00be
            java.util.Calendar r6 = r4.getCalendar()     // Catch:{ all -> 0x00f4 }
            java.util.Date r6 = r6.getTime()     // Catch:{ all -> 0x00f4 }
        L_0x00be:
            r4.close()     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x00c2:
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.UseBigDecimal     // Catch:{ all -> 0x00f4 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x00f4 }
            if (r4 == 0) goto L_0x00d1
            r4 = 1
            java.lang.Number r4 = r0.decimalValue(r4)     // Catch:{ all -> 0x00f4 }
        L_0x00cf:
            r6 = r4
            goto L_0x00d6
        L_0x00d1:
            java.lang.Number r4 = r0.decimalValue(r10)     // Catch:{ all -> 0x00f4 }
            goto L_0x00cf
        L_0x00d6:
            r0.nextToken(r5)     // Catch:{ all -> 0x00f4 }
            goto L_0x00e1
        L_0x00da:
            java.lang.Number r6 = r0.integerValue()     // Catch:{ all -> 0x00f4 }
            r0.nextToken(r5)     // Catch:{ all -> 0x00f4 }
        L_0x00e1:
            r9.add(r6)     // Catch:{ all -> 0x00f4 }
            r8.checkListResolve(r9)     // Catch:{ all -> 0x00f4 }
            int r4 = r0.token()     // Catch:{ all -> 0x00f4 }
            if (r4 != r5) goto L_0x00f0
            r0.nextToken(r1)     // Catch:{ all -> 0x00f4 }
        L_0x00f0:
            int r3 = r3 + 1
            goto L_0x0028
        L_0x00f4:
            r9 = move-exception
            r8.setContext(r2)
            throw r9
        L_0x00f9:
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "syntax error, expect [, actual "
            r1.append(r2)
            int r2 = r0.token()
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)
            r1.append(r2)
            java.lang.String r2 = ", pos "
            r1.append(r2)
            int r0 = r0.pos()
            r1.append(r0)
            java.lang.String r0 = ", fieldName "
            r1.append(r0)
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver2) {
        this.fieldTypeResolver = fieldTypeResolver2;
    }

    public void setContext(ParseContext parseContext) {
        if (!this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = parseContext;
        }
    }

    public void popContext() {
        if (!this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = this.context.parent;
            if (this.contextArrayIndex > 0) {
                this.contextArrayIndex--;
                this.contextArray[this.contextArrayIndex] = null;
            }
        }
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.context = new ParseContext(parseContext, obj, obj2);
        addContext(this.context);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            ParseContext[] parseContextArr = new ParseContext[((this.contextArray.length * 3) / 2)];
            System.arraycopy(this.contextArray, 0, parseContextArr, 0, this.contextArray.length);
            this.contextArray = parseContextArr;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse((Object) null);
    }

    public Object parseKey() {
        if (this.lexer.token() != 18) {
            return parse((Object) null);
        }
        String stringVal = this.lexer.stringVal();
        this.lexer.nextToken(16);
        return stringVal;
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        switch (jSONLexer.token()) {
            case 2:
                Number integerValue = jSONLexer.integerValue();
                jSONLexer.nextToken();
                return integerValue;
            case 3:
                Number decimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
                jSONLexer.nextToken();
                return decimalValue;
            case 4:
                String stringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner jSONScanner = new JSONScanner(stringVal);
                    try {
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            return jSONScanner.getCalendar().getTime();
                        }
                        jSONScanner.close();
                    } finally {
                        jSONScanner.close();
                    }
                }
                return stringVal;
            case 6:
                jSONLexer.nextToken();
                return Boolean.TRUE;
            case 7:
                jSONLexer.nextToken();
                return Boolean.FALSE;
            case 8:
                jSONLexer.nextToken();
                return null;
            case 9:
                jSONLexer.nextToken(18);
                if (jSONLexer.token() == 18) {
                    jSONLexer.nextToken(10);
                    accept(10);
                    long longValue = jSONLexer.integerValue().longValue();
                    accept(2);
                    accept(11);
                    return new Date(longValue);
                }
                throw new JSONException("syntax error");
            case 12:
                return parseObject((Map) new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
            case 14:
                JSONArray jSONArray = new JSONArray();
                parseArray((Collection) jSONArray, obj);
                return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
            case 18:
                if ("NaN".equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    return null;
                }
                throw new JSONException("syntax error, " + jSONLexer.info());
            case 20:
                if (jSONLexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("unterminated json string, " + jSONLexer.info());
            case 21:
                jSONLexer.nextToken();
                HashSet hashSet = new HashSet();
                parseArray((Collection) hashSet, obj);
                return hashSet;
            case 22:
                jSONLexer.nextToken();
                TreeSet treeSet = new TreeSet();
                parseArray((Collection) treeSet, obj);
                return treeSet;
            case 23:
                jSONLexer.nextToken();
                return null;
            case 26:
                byte[] bytesValue = jSONLexer.bytesValue();
                jSONLexer.nextToken();
                return bytesValue;
            default:
                throw new JSONException("syntax error, " + jSONLexer.info());
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
        } else {
            throwException(i);
        }
    }

    public void throwException(int i) {
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource)) {
                if (jSONLexer.token() != 20) {
                    throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
                }
            }
        } finally {
            jSONLexer.close();
        }
    }

    public Object resolveReference(String str) {
        if (this.contextArray == null) {
            return null;
        }
        int i = 0;
        while (i < this.contextArray.length && i < this.contextArrayIndex) {
            ParseContext parseContext = this.contextArray[i];
            if (parseContext.toString().equals(str)) {
                return parseContext.object;
            }
            i++;
        }
        return null;
    }

    public void handleResovleTask(Object obj) {
        Object obj2;
        if (this.resolveTaskList != null) {
            int size = this.resolveTaskList.size();
            for (int i = 0; i < size; i++) {
                ResolveTask resolveTask = this.resolveTaskList.get(i);
                String str = resolveTask.referenceValue;
                Object obj3 = null;
                if (resolveTask.ownerContext != null) {
                    obj3 = resolveTask.ownerContext.object;
                }
                if (str.startsWith("$")) {
                    obj2 = getObject(str);
                    if (obj2 == null) {
                        try {
                            obj2 = JSONPath.eval(obj, str);
                        } catch (JSONPathException unused) {
                        }
                    }
                } else {
                    obj2 = resolveTask.context.object;
                }
                FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
                if (fieldDeserializer != null) {
                    if (obj2 != null && obj2.getClass() == JSONObject.class && fieldDeserializer.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeserializer.fieldInfo.fieldClass)) {
                        obj2 = JSONPath.eval(this.contextArray[0].object, str);
                    }
                    fieldDeserializer.setValue(obj3, obj2);
                }
            }
        }
    }

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public void parseExtra(Object obj, String str) {
        Object obj2;
        this.lexer.nextTokenWithColon();
        Type type = null;
        if (this.extraTypeProviders != null) {
            for (ExtraTypeProvider extraType : this.extraTypeProviders) {
                type = extraType.getExtraType(obj, str);
            }
        }
        if (type == null) {
            obj2 = parse();
        } else {
            obj2 = parseObject(type);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, obj2);
            return;
        }
        if (this.extraProcessors != null) {
            for (ExtraProcessor processExtra : this.extraProcessors) {
                processExtra.processExtra(obj, str, obj2);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r10 = r9.config.getDeserializer((java.lang.reflect.Type) r2);
        r9.lexer.nextToken(16);
        setResolveStatus(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01d9, code lost:
        if (r0 == null) goto L_0x01e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01dd, code lost:
        if ((r11 instanceof java.lang.Integer) != false) goto L_0x01e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01df, code lost:
        popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01e2, code lost:
        r10 = (java.util.Map) r10.deserialze(r9, r2, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01e8, code lost:
        setContext(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01eb, code lost:
        return r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable r10, java.lang.Object r11) {
        /*
            r9 = this;
            com.alibaba.fastjson.parser.JSONLexer r0 = r9.lexer
            int r0 = r0.token()
            r1 = 0
            r2 = 12
            if (r0 == r2) goto L_0x008c
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "syntax error, expect {, actual "
            r10.append(r0)
            com.alibaba.fastjson.parser.JSONLexer r0 = r9.lexer
            java.lang.String r0 = r0.tokenName()
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L_0x0046
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            java.lang.String r10 = ", fieldName "
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            r0.append(r11)
            java.lang.String r10 = r0.toString()
        L_0x0046:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            java.lang.String r10 = ", "
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            com.alibaba.fastjson.parser.JSONLexer r10 = r9.lexer
            java.lang.String r10 = r10.info()
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            com.alibaba.fastjson.JSONArray r0 = new com.alibaba.fastjson.JSONArray
            r0.<init>()
            r9.parseArray((java.util.Collection) r0, (java.lang.Object) r11)
            int r11 = r0.size()
            r2 = 1
            if (r11 != r2) goto L_0x0086
            java.lang.Object r11 = r0.get(r1)
            boolean r0 = r11 instanceof com.alibaba.fastjson.JSONObject
            if (r0 == 0) goto L_0x0086
            com.alibaba.fastjson.JSONObject r11 = (com.alibaba.fastjson.JSONObject) r11
            return r11
        L_0x0086:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException
            r11.<init>(r10)
            throw r11
        L_0x008c:
            com.alibaba.fastjson.parser.ParseContext r0 = r9.context
        L_0x008e:
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            r2.skipWhitespace()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            char r2 = r2.getCurrent()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0266 }
            boolean r3 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x0266 }
            if (r3 == 0) goto L_0x00b8
        L_0x00a3:
            r3 = 44
            if (r2 != r3) goto L_0x00b8
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            r2.next()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            r2.skipWhitespace()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            char r2 = r2.getCurrent()     // Catch:{ all -> 0x0266 }
            goto L_0x00a3
        L_0x00b8:
            r3 = 58
            r4 = 34
            r5 = 16
            if (r2 != r4) goto L_0x00f4
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.SymbolTable r6 = r9.symbolTable     // Catch:{ all -> 0x0266 }
            java.lang.String r2 = r2.scanSymbol(r6, r4)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r9.lexer     // Catch:{ all -> 0x0266 }
            r6.skipWhitespace()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r9.lexer     // Catch:{ all -> 0x0266 }
            char r6 = r6.getCurrent()     // Catch:{ all -> 0x0266 }
            if (r6 != r3) goto L_0x00d7
            goto L_0x0173
        L_0x00d7:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r11.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r1 = "expect ':' at "
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r1 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r1 = r1.pos()     // Catch:{ all -> 0x0266 }
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0266 }
            r10.<init>(r11)     // Catch:{ all -> 0x0266 }
            throw r10     // Catch:{ all -> 0x0266 }
        L_0x00f4:
            r6 = 125(0x7d, float:1.75E-43)
            if (r2 != r6) goto L_0x010b
            com.alibaba.fastjson.parser.JSONLexer r11 = r9.lexer     // Catch:{ all -> 0x0266 }
            r11.next()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r11 = r9.lexer     // Catch:{ all -> 0x0266 }
            r11.resetStringPosition()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r11 = r9.lexer     // Catch:{ all -> 0x0266 }
            r11.nextToken(r5)     // Catch:{ all -> 0x0266 }
            r9.setContext(r0)
            return r10
        L_0x010b:
            r6 = 39
            if (r2 != r6) goto L_0x0154
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0266 }
            boolean r2 = r2.isEnabled((com.alibaba.fastjson.parser.Feature) r7)     // Catch:{ all -> 0x0266 }
            if (r2 == 0) goto L_0x014c
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.SymbolTable r7 = r9.symbolTable     // Catch:{ all -> 0x0266 }
            java.lang.String r2 = r2.scanSymbol(r7, r6)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r9.lexer     // Catch:{ all -> 0x0266 }
            r6.skipWhitespace()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r9.lexer     // Catch:{ all -> 0x0266 }
            char r6 = r6.getCurrent()     // Catch:{ all -> 0x0266 }
            if (r6 != r3) goto L_0x012f
            goto L_0x0173
        L_0x012f:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r11.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r1 = "expect ':' at "
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r1 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r1 = r1.pos()     // Catch:{ all -> 0x0266 }
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0266 }
            r10.<init>(r11)     // Catch:{ all -> 0x0266 }
            throw r10     // Catch:{ all -> 0x0266 }
        L_0x014c:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0266 }
            java.lang.String r11 = "syntax error"
            r10.<init>(r11)     // Catch:{ all -> 0x0266 }
            throw r10     // Catch:{ all -> 0x0266 }
        L_0x0154:
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0266 }
            boolean r2 = r2.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0266 }
            if (r2 == 0) goto L_0x025e
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.SymbolTable r6 = r9.symbolTable     // Catch:{ all -> 0x0266 }
            java.lang.String r2 = r2.scanSymbolUnQuoted(r6)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r9.lexer     // Catch:{ all -> 0x0266 }
            r6.skipWhitespace()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r6 = r9.lexer     // Catch:{ all -> 0x0266 }
            char r6 = r6.getCurrent()     // Catch:{ all -> 0x0266 }
            if (r6 != r3) goto L_0x0239
        L_0x0173:
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            r3.next()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            r3.skipWhitespace()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            r3.getCurrent()     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            r3.resetStringPosition()     // Catch:{ all -> 0x0266 }
            java.lang.String r3 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0266 }
            r6 = 13
            r7 = 0
            if (r2 != r3) goto L_0x01ec
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0266 }
            boolean r3 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x0266 }
            if (r3 != 0) goto L_0x01ec
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.SymbolTable r3 = r9.symbolTable     // Catch:{ all -> 0x0266 }
            java.lang.String r2 = r2.scanSymbol(r3, r4)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.ParserConfig r3 = r9.config     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r4 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r4 = r4.getFeatures()     // Catch:{ all -> 0x0266 }
            java.lang.Class r2 = r3.checkAutoType(r2, r7, r4)     // Catch:{ all -> 0x0266 }
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r2)     // Catch:{ all -> 0x0266 }
            if (r3 == 0) goto L_0x01ca
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            r2.nextToken(r5)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r2 = r2.token()     // Catch:{ all -> 0x0266 }
            if (r2 != r6) goto L_0x0231
            com.alibaba.fastjson.parser.JSONLexer r11 = r9.lexer     // Catch:{ all -> 0x0266 }
            r11.nextToken(r5)     // Catch:{ all -> 0x0266 }
            r9.setContext(r0)
            return r10
        L_0x01ca:
            com.alibaba.fastjson.parser.ParserConfig r10 = r9.config     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r10 = r10.getDeserializer((java.lang.reflect.Type) r2)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r1 = r9.lexer     // Catch:{ all -> 0x0266 }
            r1.nextToken(r5)     // Catch:{ all -> 0x0266 }
            r1 = 2
            r9.setResolveStatus(r1)     // Catch:{ all -> 0x0266 }
            if (r0 == 0) goto L_0x01e2
            boolean r1 = r11 instanceof java.lang.Integer     // Catch:{ all -> 0x0266 }
            if (r1 != 0) goto L_0x01e2
            r9.popContext()     // Catch:{ all -> 0x0266 }
        L_0x01e2:
            java.lang.Object r10 = r10.deserialze(r9, r2, r11)     // Catch:{ all -> 0x0266 }
            java.util.Map r10 = (java.util.Map) r10     // Catch:{ all -> 0x0266 }
            r9.setContext(r0)
            return r10
        L_0x01ec:
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            r3.nextToken()     // Catch:{ all -> 0x0266 }
            if (r1 == 0) goto L_0x01f6
            r9.setContext(r0)     // Catch:{ all -> 0x0266 }
        L_0x01f6:
            java.lang.reflect.Type r3 = r10.getType(r2)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r4 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r4 = r4.token()     // Catch:{ all -> 0x0266 }
            r5 = 8
            if (r4 != r5) goto L_0x020a
            com.alibaba.fastjson.parser.JSONLexer r3 = r9.lexer     // Catch:{ all -> 0x0266 }
            r3.nextToken()     // Catch:{ all -> 0x0266 }
            goto L_0x020e
        L_0x020a:
            java.lang.Object r7 = r9.parseObject((java.lang.reflect.Type) r3, (java.lang.Object) r2)     // Catch:{ all -> 0x0266 }
        L_0x020e:
            r10.apply(r2, r7)     // Catch:{ all -> 0x0266 }
            r9.setContext(r0, r7, r2)     // Catch:{ all -> 0x0266 }
            r9.setContext(r0)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r2 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r2 = r2.token()     // Catch:{ all -> 0x0266 }
            r3 = 20
            if (r2 == r3) goto L_0x0235
            r3 = 15
            if (r2 != r3) goto L_0x0226
            goto L_0x0235
        L_0x0226:
            if (r2 != r6) goto L_0x0231
            com.alibaba.fastjson.parser.JSONLexer r11 = r9.lexer     // Catch:{ all -> 0x0266 }
            r11.nextToken()     // Catch:{ all -> 0x0266 }
            r9.setContext(r0)
            return r10
        L_0x0231:
            int r1 = r1 + 1
            goto L_0x008e
        L_0x0235:
            r9.setContext(r0)
            return r10
        L_0x0239:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0266 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0266 }
            r11.<init>()     // Catch:{ all -> 0x0266 }
            java.lang.String r1 = "expect ':' at "
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            com.alibaba.fastjson.parser.JSONLexer r1 = r9.lexer     // Catch:{ all -> 0x0266 }
            int r1 = r1.pos()     // Catch:{ all -> 0x0266 }
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            java.lang.String r1 = ", actual "
            r11.append(r1)     // Catch:{ all -> 0x0266 }
            r11.append(r6)     // Catch:{ all -> 0x0266 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0266 }
            r10.<init>(r11)     // Catch:{ all -> 0x0266 }
            throw r10     // Catch:{ all -> 0x0266 }
        L_0x025e:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0266 }
            java.lang.String r11 = "syntax error"
            r10.<init>(r11)     // Catch:{ all -> 0x0266 }
            throw r10     // Catch:{ all -> 0x0266 }
        L_0x0266:
            r10 = move-exception
            r9.setContext(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable, java.lang.Object):java.lang.Object");
    }
}
