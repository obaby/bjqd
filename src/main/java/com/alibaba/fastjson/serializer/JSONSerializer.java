package com.alibaba.fastjson.serializer;

import anet.channel.util.HttpConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.GZIPOutputStream;

public class JSONSerializer extends SerializeFilterable {
    protected final SerializeConfig config;
    protected SerialContext context;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private String indent;
    private int indentCount;
    protected Locale locale;
    public final SerializeWriter out;
    protected IdentityHashMap<Object, SerialContext> references;
    protected TimeZone timeZone;

    public JSONSerializer() {
        this(new SerializeWriter(), SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeWriter serializeWriter) {
        this(serializeWriter, SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeConfig serializeConfig) {
        this(new SerializeWriter(), serializeConfig);
    }

    public JSONSerializer(SerializeWriter serializeWriter, SerializeConfig serializeConfig) {
        this.indentCount = 0;
        this.indent = "\t";
        this.references = null;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.out = serializeWriter;
        this.config = serializeConfig;
    }

    public String getDateFormatPattern() {
        if (this.dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) this.dateFormat).toPattern();
        }
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null && this.dateFormatPattern != null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.locale);
            this.dateFormat.setTimeZone(this.timeZone);
        }
        return this.dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat2) {
        this.dateFormat = dateFormat2;
        if (this.dateFormatPattern != null) {
            this.dateFormatPattern = null;
        }
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        if (this.dateFormat != null) {
            this.dateFormat = null;
        }
    }

    public SerialContext getContext() {
        return this.context;
    }

    public void setContext(SerialContext serialContext) {
        this.context = serialContext;
    }

    public void setContext(SerialContext serialContext, Object obj, Object obj2, int i) {
        setContext(serialContext, obj, obj2, i, 0);
    }

    public void setContext(SerialContext serialContext, Object obj, Object obj2, int i, int i2) {
        if (!this.out.disableCircularReferenceDetect) {
            this.context = new SerialContext(serialContext, obj, obj2, i, i2);
            if (this.references == null) {
                this.references = new IdentityHashMap<>();
            }
            this.references.put(obj, this.context);
        }
    }

    public void setContext(Object obj, Object obj2) {
        setContext(this.context, obj, obj2, 0);
    }

    public void popContext() {
        if (this.context != null) {
            this.context = this.context.parent;
        }
    }

    public final boolean isWriteClassName(Type type, Object obj) {
        return this.out.isEnabled(SerializerFeature.WriteClassName) && !(type == null && this.out.isEnabled(SerializerFeature.NotWriteRootClassName) && (this.context == null || this.context.parent == null));
    }

    public boolean containsReference(Object obj) {
        SerialContext serialContext;
        if (this.references == null || (serialContext = this.references.get(obj)) == null) {
            return false;
        }
        Object obj2 = serialContext.fieldName;
        if (obj2 == null || (obj2 instanceof Integer) || (obj2 instanceof String)) {
            return true;
        }
        return false;
    }

    public void writeReference(Object obj) {
        SerialContext serialContext = this.context;
        if (obj == serialContext.object) {
            this.out.write("{\"$ref\":\"@\"}");
            return;
        }
        SerialContext serialContext2 = serialContext.parent;
        if (serialContext2 == null || obj != serialContext2.object) {
            while (serialContext.parent != null) {
                serialContext = serialContext.parent;
            }
            if (obj == serialContext.object) {
                this.out.write("{\"$ref\":\"$\"}");
                return;
            }
            this.out.write("{\"$ref\":\"");
            this.out.write(this.references.get(obj).toString());
            this.out.write("\"}");
            return;
        }
        this.out.write("{\"$ref\":\"..\"}");
    }

    public boolean checkValue(SerializeFilterable serializeFilterable) {
        return (this.valueFilters != null && this.valueFilters.size() > 0) || (this.contextValueFilters != null && this.contextValueFilters.size() > 0) || ((serializeFilterable.valueFilters != null && serializeFilterable.valueFilters.size() > 0) || ((serializeFilterable.contextValueFilters != null && serializeFilterable.contextValueFilters.size() > 0) || this.out.writeNonStringValueAsString));
    }

    public boolean hasNameFilters(SerializeFilterable serializeFilterable) {
        return (this.nameFilters != null && this.nameFilters.size() > 0) || (serializeFilterable.nameFilters != null && serializeFilterable.nameFilters.size() > 0);
    }

    public boolean hasPropertyFilters(SerializeFilterable serializeFilterable) {
        return (this.propertyFilters != null && this.propertyFilters.size() > 0) || (serializeFilterable.propertyFilters != null && serializeFilterable.propertyFilters.size() > 0);
    }

    public int getIndentCount() {
        return this.indentCount;
    }

    public void incrementIndent() {
        this.indentCount++;
    }

    public void decrementIdent() {
        this.indentCount--;
    }

    public void println() {
        this.out.write(10);
        for (int i = 0; i < this.indentCount; i++) {
            this.out.write(this.indent);
        }
    }

    public SerializeWriter getWriter() {
        return this.out;
    }

    public String toString() {
        return this.out.toString();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.out.config(serializerFeature, z);
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return this.out.isEnabled(serializerFeature);
    }

    public void writeNull() {
        this.out.writeNull();
    }

    public SerializeConfig getMapping() {
        return this.config;
    }

    public static void write(Writer writer, Object obj) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).write(obj);
            serializeWriter.writeTo(writer);
            serializeWriter.close();
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        } catch (Throwable th) {
            serializeWriter.close();
            throw th;
        }
    }

    public static void write(SerializeWriter serializeWriter, Object obj) {
        new JSONSerializer(serializeWriter).write(obj);
    }

    public final void write(Object obj) {
        if (obj == null) {
            this.out.writeNull();
            return;
        }
        try {
            getObjectWriter(obj.getClass()).write(this, obj, (Object) null, (Type) null, 0);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void writeWithFieldName(Object obj, Object obj2) {
        writeWithFieldName(obj, obj2, (Type) null, 0);
    }

    /* access modifiers changed from: protected */
    public final void writeKeyValue(char c2, String str, Object obj) {
        if (c2 != 0) {
            this.out.write((int) c2);
        }
        this.out.writeFieldName(str);
        write(obj);
    }

    public final void writeWithFieldName(Object obj, Object obj2, Type type, int i) {
        if (obj == null) {
            try {
                this.out.writeNull();
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        } else {
            getObjectWriter(obj.getClass()).write(this, obj, obj2, type, i);
        }
    }

    public final void writeWithFormat(Object obj, String str) {
        GZIPOutputStream gZIPOutputStream;
        if (obj instanceof Date) {
            DateFormat dateFormat2 = getDateFormat();
            if (dateFormat2 == null) {
                dateFormat2 = new SimpleDateFormat(str, this.locale);
                dateFormat2.setTimeZone(this.timeZone);
            }
            this.out.writeString(dateFormat2.format((Date) obj));
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (HttpConstant.GZIP.equals(str) || "gzip,base64".equals(str)) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    if (bArr.length < 512) {
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream, bArr.length);
                    } else {
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    }
                    GZIPOutputStream gZIPOutputStream2 = gZIPOutputStream;
                    gZIPOutputStream2.write(bArr);
                    gZIPOutputStream2.finish();
                    this.out.writeByteArray(byteArrayOutputStream.toByteArray());
                    IOUtils.close(gZIPOutputStream2);
                } catch (IOException e) {
                    throw new JSONException("write gzipBytes error", e);
                } catch (Throwable th) {
                    IOUtils.close((Closeable) null);
                    throw th;
                }
            } else if ("hex".equals(str)) {
                this.out.writeHex(bArr);
            } else {
                this.out.writeByteArray(bArr);
            }
        } else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            Iterator it = collection.iterator();
            this.out.write(91);
            for (int i = 0; i < collection.size(); i++) {
                Object next = it.next();
                if (i != 0) {
                    this.out.write(44);
                }
                writeWithFormat(next, str);
            }
            this.out.write(93);
        } else {
            write(obj);
        }
    }

    public final void write(String str) {
        StringCodec.instance.write(this, str);
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return this.config.getObjectWriter(cls);
    }

    public void close() {
        this.out.close();
    }
}
