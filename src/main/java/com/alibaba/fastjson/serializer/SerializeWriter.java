package com.alibaba.fastjson.serializer;

import anetwork.channel.util.RequestConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

public final class SerializeWriter extends Writer {
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
    private static final ThreadLocal<byte[]> bytesBufLocal = new ThreadLocal<>();
    static final int nonDirectFeatures = (((((((((SerializerFeature.UseSingleQuotes.mask | 0) | SerializerFeature.BrowserCompatible.mask) | SerializerFeature.PrettyFormat.mask) | SerializerFeature.WriteEnumUsingToString.mask) | SerializerFeature.WriteNonStringValueAsString.mask) | SerializerFeature.WriteSlashAsSpecial.mask) | SerializerFeature.IgnoreErrorGetter.mask) | SerializerFeature.WriteClassName.mask) | SerializerFeature.NotWriteDefaultValue.mask);
    protected boolean beanToArray;
    protected boolean browserSecure;
    protected char[] buf;
    protected int count;
    protected boolean disableCircularReferenceDetect;
    protected int features;
    protected char keySeperator;
    protected int maxBufSize;
    protected boolean notWriteDefaultValue;
    protected boolean quoteFieldNames;
    protected long sepcialBits;
    protected boolean sortField;
    protected boolean useSingleQuotes;
    protected boolean writeDirect;
    protected boolean writeEnumUsingName;
    protected boolean writeEnumUsingToString;
    protected boolean writeNonStringValueAsString;
    private final Writer writer;

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer2) {
        this(writer2, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this((Writer) null, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer2, SerializerFeature... serializerFeatureArr) {
        this(writer2, 0, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer2, int i, SerializerFeature... serializerFeatureArr) {
        this.maxBufSize = -1;
        this.writer = writer2;
        this.buf = bufLocal.get();
        if (this.buf != null) {
            bufLocal.set((Object) null);
        } else {
            this.buf = new char[2048];
        }
        for (SerializerFeature mask : serializerFeatureArr) {
            i |= mask.getMask();
        }
        this.features = i;
        computeFeatures();
    }

    public int getMaxBufSize() {
        return this.maxBufSize;
    }

    public void setMaxBufSize(int i) {
        if (i >= this.buf.length) {
            this.maxBufSize = i;
            return;
        }
        throw new JSONException("must > " + this.buf.length);
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public SerializeWriter(int i) {
        this((Writer) null, i);
    }

    public SerializeWriter(Writer writer2, int i) {
        this.maxBufSize = -1;
        this.writer = writer2;
        if (i > 0) {
            this.buf = new char[i];
            computeFeatures();
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i);
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.features |= serializerFeature.getMask();
            if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                this.features &= SerializerFeature.WriteEnumUsingName.getMask() ^ -1;
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.features &= SerializerFeature.WriteEnumUsingToString.getMask() ^ -1;
            }
        } else {
            this.features = (serializerFeature.getMask() ^ -1) & this.features;
        }
        computeFeatures();
    }

    /* access modifiers changed from: protected */
    public void computeFeatures() {
        boolean z = false;
        this.quoteFieldNames = (this.features & SerializerFeature.QuoteFieldNames.mask) != 0;
        this.useSingleQuotes = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
        this.sortField = (this.features & SerializerFeature.SortField.mask) != 0;
        this.disableCircularReferenceDetect = (this.features & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
        this.beanToArray = (this.features & SerializerFeature.BeanToArray.mask) != 0;
        this.writeNonStringValueAsString = (this.features & SerializerFeature.WriteNonStringValueAsString.mask) != 0;
        this.notWriteDefaultValue = (this.features & SerializerFeature.NotWriteDefaultValue.mask) != 0;
        this.writeEnumUsingName = (this.features & SerializerFeature.WriteEnumUsingName.mask) != 0;
        this.writeEnumUsingToString = (this.features & SerializerFeature.WriteEnumUsingToString.mask) != 0;
        this.writeDirect = this.quoteFieldNames && (this.features & nonDirectFeatures) == 0 && (this.beanToArray || this.writeEnumUsingName);
        this.keySeperator = this.useSingleQuotes ? '\'' : '\"';
        if ((this.features & SerializerFeature.BrowserSecure.mask) != 0) {
            z = true;
        }
        this.browserSecure = z;
        this.sepcialBits = this.browserSecure ? 5764610843043954687L : (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 21474836479L;
    }

    public boolean isSortField() {
        return this.sortField;
    }

    public boolean isNotWriteDefaultValue() {
        return this.notWriteDefaultValue;
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return (serializerFeature.mask & this.features) != 0;
    }

    public boolean isEnabled(int i) {
        return (i & this.features) != 0;
    }

    public void write(int i) {
        int i2 = this.count + 1;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                flush();
                i2 = 1;
            }
        }
        this.buf[this.count] = (char) i;
        this.count = i2;
    }

    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            int i4 = this.count + i2;
            if (i4 > this.buf.length) {
                if (this.writer == null) {
                    expandCapacity(i4);
                } else {
                    do {
                        int length = this.buf.length - this.count;
                        System.arraycopy(cArr, i, this.buf, this.count, length);
                        this.count = this.buf.length;
                        flush();
                        i2 -= length;
                        i += length;
                    } while (i2 > this.buf.length);
                    i4 = i2;
                }
            }
            System.arraycopy(cArr, i, this.buf, this.count, i2);
            this.count = i4;
        }
    }

    public void expandCapacity(int i) {
        if (this.maxBufSize == -1 || i < this.maxBufSize) {
            int length = this.buf.length + (this.buf.length >> 1) + 1;
            if (length >= i) {
                i = length;
            }
            char[] cArr = new char[i];
            System.arraycopy(this.buf, 0, cArr, 0, this.count);
            this.buf = cArr;
            return;
        }
        throw new JSONException("serialize exceeded MAX_OUTPUT_LENGTH=" + this.maxBufSize + ", minimumCapacity=" + i);
    }

    public SerializeWriter append(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "null" : charSequence.toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter append(char c2) {
        write((int) c2);
        return this;
    }

    public void write(String str, int i, int i2) {
        int i3;
        int i4 = this.count + i2;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            } else {
                while (true) {
                    int length = this.buf.length - this.count;
                    i3 = i + length;
                    str.getChars(i, i3, this.buf, this.count);
                    this.count = this.buf.length;
                    flush();
                    i2 -= length;
                    if (i2 <= this.buf.length) {
                        break;
                    }
                    i = i3;
                }
                i4 = i2;
                i = i3;
            }
        }
        str.getChars(i, i2 + i, this.buf, this.count);
        this.count = i4;
    }

    public void writeTo(Writer writer2) throws IOException {
        if (this.writer == null) {
            writer2.write(this.buf, 0, this.count);
            return;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        writeToEx(outputStream, charset);
    }

    public int writeToEx(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        } else if (charset == IOUtils.UTF8) {
            return encodeToUTF8(outputStream);
        } else {
            byte[] bytes = new String(this.buf, 0, this.count).getBytes(charset);
            outputStream.write(bytes);
            return bytes.length;
        }
    }

    public char[] toCharArray() {
        if (this.writer == null) {
            char[] cArr = new char[this.count];
            System.arraycopy(this.buf, 0, cArr, 0, this.count);
            return cArr;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public char[] toCharArrayForSpringWebSocket() {
        if (this.writer == null) {
            char[] cArr = new char[(this.count - 2)];
            System.arraycopy(this.buf, 1, cArr, 0, this.count - 2);
            return cArr;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public byte[] toBytes(String str) {
        Charset charset;
        if (str == null || "UTF-8".equals(str)) {
            charset = IOUtils.UTF8;
        } else {
            charset = Charset.forName(str);
        }
        return toBytes(charset);
    }

    public byte[] toBytes(Charset charset) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        } else if (charset == IOUtils.UTF8) {
            return encodeToUTF8Bytes();
        } else {
            return new String(this.buf, 0, this.count).getBytes(charset);
        }
    }

    private int encodeToUTF8(OutputStream outputStream) throws IOException {
        double d = (double) this.count;
        Double.isNaN(d);
        int i = (int) (d * 3.0d);
        byte[] bArr = bytesBufLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            bytesBufLocal.set(bArr);
        }
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        int encodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        outputStream.write(bArr, 0, encodeUTF8);
        return encodeUTF8;
    }

    private byte[] encodeToUTF8Bytes() {
        double d = (double) this.count;
        Double.isNaN(d);
        int i = (int) (d * 3.0d);
        byte[] bArr = bytesBufLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            bytesBufLocal.set(bArr);
        }
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        int encodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        byte[] bArr2 = new byte[encodeUTF8];
        System.arraycopy(bArr, 0, bArr2, 0, encodeUTF8);
        return bArr2;
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= 131072) {
            bufLocal.set(this.buf);
        }
        this.buf = null;
    }

    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int i2 = this.count + stringSize;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                char[] cArr = new char[stringSize];
                IOUtils.getChars(i, stringSize, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.getChars(i, i2, this.buf);
        this.count = i2;
    }

    public void writeByteArray(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (isEnabled(SerializerFeature.WriteClassName.mask)) {
            writeHex(bArr);
            return;
        }
        int length = bArr2.length;
        char c2 = this.useSingleQuotes ? '\'' : '\"';
        if (length == 0) {
            write(this.useSingleQuotes ? "''" : "\"\"");
            return;
        }
        char[] cArr = IOUtils.CA;
        int i = (length / 3) * 3;
        int i2 = length - 1;
        int i3 = this.count;
        int i4 = this.count + (((i2 / 3) + 1) << 2) + 2;
        int i5 = 0;
        if (i4 > this.buf.length) {
            if (this.writer != null) {
                write((int) c2);
                int i6 = 0;
                while (i6 < i) {
                    int i7 = i6 + 1;
                    int i8 = i7 + 1;
                    byte b2 = ((bArr2[i6] & 255) << 16) | ((bArr2[i7] & 255) << 8) | (bArr2[i8] & 255);
                    write((int) cArr[(b2 >>> 18) & 63]);
                    write((int) cArr[(b2 >>> 12) & 63]);
                    write((int) cArr[(b2 >>> 6) & 63]);
                    write((int) cArr[b2 & 63]);
                    i6 = i8 + 1;
                }
                int i9 = length - i;
                if (i9 > 0) {
                    int i10 = (bArr2[i] & 255) << 10;
                    if (i9 == 2) {
                        i5 = (bArr2[i2] & 255) << 2;
                    }
                    int i11 = i10 | i5;
                    write((int) cArr[i11 >> 12]);
                    write((int) cArr[(i11 >>> 6) & 63]);
                    write((int) i9 == 2 ? cArr[i11 & 63] : '=');
                    write(61);
                }
                write((int) c2);
                return;
            }
            expandCapacity(i4);
        }
        this.count = i4;
        int i12 = i3 + 1;
        this.buf[i3] = c2;
        int i13 = 0;
        while (i13 < i) {
            int i14 = i13 + 1;
            int i15 = i14 + 1;
            byte b3 = ((bArr2[i13] & 255) << 16) | ((bArr2[i14] & 255) << 8);
            int i16 = i15 + 1;
            byte b4 = b3 | (bArr2[i15] & 255);
            int i17 = i12 + 1;
            this.buf[i12] = cArr[(b4 >>> 18) & 63];
            int i18 = i17 + 1;
            this.buf[i17] = cArr[(b4 >>> 12) & 63];
            int i19 = i18 + 1;
            this.buf[i18] = cArr[(b4 >>> 6) & 63];
            this.buf[i19] = cArr[b4 & 63];
            i13 = i16;
            i12 = i19 + 1;
        }
        int i20 = length - i;
        if (i20 > 0) {
            int i21 = (bArr2[i] & 255) << 10;
            if (i20 == 2) {
                i5 = (bArr2[i2] & 255) << 2;
            }
            int i22 = i21 | i5;
            this.buf[i4 - 5] = cArr[i22 >> 12];
            this.buf[i4 - 4] = cArr[(i22 >>> 6) & 63];
            this.buf[i4 - 3] = i20 == 2 ? cArr[i22 & 63] : '=';
            this.buf[i4 - 2] = '=';
        }
        this.buf[i4 - 1] = c2;
    }

    public void writeHex(byte[] bArr) {
        int i = 2;
        int length = this.count + (bArr.length * 2) + 3;
        int i2 = 0;
        if (length > this.buf.length) {
            if (this.writer != null) {
                char[] cArr = new char[(bArr.length + 3)];
                cArr[0] = 'x';
                cArr[1] = '\'';
                while (i2 < bArr.length) {
                    byte b2 = bArr[i2] & 255;
                    int i3 = b2 >> 4;
                    byte b3 = b2 & 15;
                    int i4 = i + 1;
                    cArr[i] = (char) (i3 + (i3 < 10 ? 48 : 55));
                    i = i4 + 1;
                    cArr[i4] = (char) (b3 + (b3 < 10 ? (byte) 48 : 55));
                    i2++;
                }
                cArr[i] = '\'';
                try {
                    this.writer.write(cArr);
                    return;
                } catch (IOException e) {
                    throw new JSONException("writeBytes error.", e);
                }
            } else {
                expandCapacity(length);
            }
        }
        char[] cArr2 = this.buf;
        int i5 = this.count;
        this.count = i5 + 1;
        cArr2[i5] = 'x';
        char[] cArr3 = this.buf;
        int i6 = this.count;
        this.count = i6 + 1;
        cArr3[i6] = '\'';
        while (i2 < bArr.length) {
            byte b4 = bArr[i2] & 255;
            int i7 = b4 >> 4;
            byte b5 = b4 & 15;
            char[] cArr4 = this.buf;
            int i8 = this.count;
            this.count = i8 + 1;
            cArr4[i8] = (char) (i7 + (i7 < 10 ? 48 : 55));
            char[] cArr5 = this.buf;
            int i9 = this.count;
            this.count = i9 + 1;
            cArr5[i9] = (char) (b5 + (b5 < 10 ? (byte) 48 : 55));
            i2++;
        }
        char[] cArr6 = this.buf;
        int i10 = this.count;
        this.count = i10 + 1;
        cArr6[i10] = '\'';
    }

    public void writeFloat(float f, boolean z) {
        if (Float.isNaN(f) || Float.isInfinite(f)) {
            writeNull();
            return;
        }
        String f2 = Float.toString(f);
        if (isEnabled(SerializerFeature.WriteNullNumberAsZero) && f2.endsWith(".0")) {
            f2 = f2.substring(0, f2.length() - 2);
        }
        write(f2);
        if (z && isEnabled(SerializerFeature.WriteClassName)) {
            write(70);
        }
    }

    public void writeDouble(double d, boolean z) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            writeNull();
            return;
        }
        String d2 = Double.toString(d);
        if (isEnabled(SerializerFeature.WriteNullNumberAsZero) && d2.endsWith(".0")) {
            d2 = d2.substring(0, d2.length() - 2);
        }
        write(d2);
        if (z && isEnabled(SerializerFeature.WriteClassName)) {
            write(68);
        }
    }

    public void writeEnum(Enum<?> enumR) {
        if (enumR == null) {
            writeNull();
            return;
        }
        String str = null;
        if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            str = enumR.name();
        } else if (this.writeEnumUsingToString) {
            str = enumR.toString();
        }
        if (str != null) {
            int i = isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            write(i);
            write(str);
            write(i);
            return;
        }
        writeInt(enumR.ordinal());
    }

    public void writeLong(long j) {
        boolean z = isEnabled(SerializerFeature.BrowserCompatible) && !isEnabled(SerializerFeature.WriteClassName) && (j > 9007199254740991L || j < -9007199254740991L);
        if (j != Long.MIN_VALUE) {
            int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
            int i = this.count + stringSize;
            if (z) {
                i += 2;
            }
            if (i > this.buf.length) {
                if (this.writer == null) {
                    expandCapacity(i);
                } else {
                    char[] cArr = new char[stringSize];
                    IOUtils.getChars(j, stringSize, cArr);
                    if (z) {
                        write(34);
                        write(cArr, 0, cArr.length);
                        write(34);
                        return;
                    }
                    write(cArr, 0, cArr.length);
                    return;
                }
            }
            if (z) {
                this.buf[this.count] = '\"';
                int i2 = i - 1;
                IOUtils.getChars(j, i2, this.buf);
                this.buf[i2] = '\"';
            } else {
                IOUtils.getChars(j, i, this.buf);
            }
            this.count = i;
        } else if (z) {
            write("\"-9223372036854775808\"");
        } else {
            write("-9223372036854775808");
        }
    }

    public void writeNull() {
        write("null");
    }

    public void writeNull(SerializerFeature serializerFeature) {
        writeNull(0, serializerFeature.mask);
    }

    public void writeNull(int i, int i2) {
        if ((i & i2) == 0 && (this.features & i2) == 0) {
            writeNull();
        } else if (i2 == SerializerFeature.WriteNullListAsEmpty.mask) {
            write("[]");
        } else if (i2 == SerializerFeature.WriteNullStringAsEmpty.mask) {
            writeString("");
        } else if (i2 == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            write(RequestConstant.FALSE);
        } else if (i2 == SerializerFeature.WriteNullNumberAsZero.mask) {
            write(48);
        } else {
            writeNull();
        }
    }

    public void writeStringWithDoubleQuote(String str, char c2) {
        int i;
        int i2;
        String str2 = str;
        char c3 = c2;
        if (str2 == null) {
            writeNull();
            if (c3 != 0) {
                write((int) c3);
                return;
            }
            return;
        }
        int length = str.length();
        int i3 = this.count + length + 2;
        if (c3 != 0) {
            i3++;
        }
        char c4 = 8;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(34);
                for (int i4 = 0; i4 < str.length(); i4++) {
                    char charAt = str2.charAt(i4);
                    if (!isEnabled(SerializerFeature.BrowserSecure) || !(charAt == '(' || charAt == ')' || charAt == '<' || charAt == '>')) {
                        if (isEnabled(SerializerFeature.BrowserCompatible)) {
                            if (charAt == 8 || charAt == 12 || charAt == 10 || charAt == 13 || charAt == 9 || charAt == '\"' || charAt == '/' || charAt == '\\') {
                                write(92);
                                write((int) IOUtils.replaceChars[charAt]);
                            } else if (charAt < ' ') {
                                write(92);
                                write(117);
                                write(48);
                                write(48);
                                int i5 = charAt * 2;
                                write((int) IOUtils.ASCII_CHARS[i5]);
                                write((int) IOUtils.ASCII_CHARS[i5 + 1]);
                            } else if (charAt >= 127) {
                                write(92);
                                write(117);
                                write((int) IOUtils.DIGITS[(charAt >>> 12) & 15]);
                                write((int) IOUtils.DIGITS[(charAt >>> 8) & 15]);
                                write((int) IOUtils.DIGITS[(charAt >>> 4) & 15]);
                                write((int) IOUtils.DIGITS[charAt & 15]);
                            }
                        } else if ((charAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt] != 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write(92);
                            if (IOUtils.specicalFlags_doubleQuotes[charAt] == 4) {
                                write(117);
                                write((int) IOUtils.DIGITS[(charAt >>> 12) & 15]);
                                write((int) IOUtils.DIGITS[(charAt >>> 8) & 15]);
                                write((int) IOUtils.DIGITS[(charAt >>> 4) & 15]);
                                write((int) IOUtils.DIGITS[charAt & 15]);
                            } else {
                                write((int) IOUtils.replaceChars[charAt]);
                            }
                        }
                        write((int) charAt);
                    } else {
                        write(92);
                        write(117);
                        write((int) IOUtils.DIGITS[(charAt >>> 12) & 15]);
                        write((int) IOUtils.DIGITS[(charAt >>> 8) & 15]);
                        write((int) IOUtils.DIGITS[(charAt >>> 4) & 15]);
                        write((int) IOUtils.DIGITS[charAt & 15]);
                    }
                }
                write(34);
                if (c3 != 0) {
                    write((int) c3);
                    return;
                }
                return;
            }
            expandCapacity(i);
        }
        int i6 = this.count + 1;
        int i7 = i6 + length;
        this.buf[this.count] = '\"';
        str2.getChars(0, length, this.buf, i6);
        this.count = i;
        int i8 = -1;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            for (int i9 = i6; i9 < i7; i9++) {
                char c5 = this.buf[i9];
                if (c5 == '\"' || c5 == '/' || c5 == '\\') {
                    i++;
                } else if (c5 == 8 || c5 == 12 || c5 == 10 || c5 == 13 || c5 == 9) {
                    i++;
                } else if (c5 < ' ') {
                    i += 5;
                } else if (c5 >= 127) {
                    i += 5;
                }
                i8 = i9;
            }
            if (i > this.buf.length) {
                expandCapacity(i);
            }
            this.count = i;
            while (i8 >= i6) {
                char c6 = this.buf[i8];
                if (c6 == c4 || c6 == 12 || c6 == 10 || c6 == 13 || c6 == 9) {
                    int i10 = i8 + 1;
                    System.arraycopy(this.buf, i10, this.buf, i8 + 2, (i7 - i8) - 1);
                    this.buf[i8] = '\\';
                    this.buf[i10] = IOUtils.replaceChars[c6];
                    i7++;
                } else if (c6 == '\"' || c6 == '/' || c6 == '\\') {
                    int i11 = i8 + 1;
                    System.arraycopy(this.buf, i11, this.buf, i8 + 2, (i7 - i8) - 1);
                    this.buf[i8] = '\\';
                    this.buf[i11] = c6;
                    i7++;
                } else if (c6 < ' ') {
                    int i12 = i8 + 1;
                    System.arraycopy(this.buf, i12, this.buf, i8 + 6, (i7 - i8) - 1);
                    this.buf[i8] = '\\';
                    this.buf[i12] = 'u';
                    this.buf[i8 + 2] = '0';
                    this.buf[i8 + 3] = '0';
                    int i13 = c6 * 2;
                    this.buf[i8 + 4] = IOUtils.ASCII_CHARS[i13];
                    this.buf[i8 + 5] = IOUtils.ASCII_CHARS[i13 + 1];
                    i7 += 5;
                } else if (c6 >= 127) {
                    int i14 = i8 + 1;
                    System.arraycopy(this.buf, i14, this.buf, i8 + 6, (i7 - i8) - 1);
                    this.buf[i8] = '\\';
                    this.buf[i14] = 'u';
                    this.buf[i8 + 2] = IOUtils.DIGITS[(c6 >>> 12) & 15];
                    this.buf[i8 + 3] = IOUtils.DIGITS[(c6 >>> 8) & 15];
                    this.buf[i8 + 4] = IOUtils.DIGITS[(c6 >>> 4) & 15];
                    this.buf[i8 + 5] = IOUtils.DIGITS[c6 & 15];
                    i7 += 5;
                }
                i8--;
                c4 = 8;
            }
            if (c3 != 0) {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = c3;
                return;
            }
            this.buf[this.count - 1] = '\"';
            return;
        }
        int i15 = i;
        int i16 = 0;
        char c7 = 0;
        int i17 = -1;
        int i18 = -1;
        for (int i19 = i6; i19 < i7; i19++) {
            char c8 = this.buf[i19];
            if (c8 >= ']') {
                if (c8 >= 127 && (c8 == 8232 || c8 == 8233 || c8 < 160)) {
                    if (i17 == i8) {
                        i17 = i19;
                    }
                    i16++;
                    i15 += 4;
                }
            } else {
                if ((c8 < '@' && (this.sepcialBits & (1 << c8)) != 0) || c8 == '\\') {
                    i16++;
                    if (c8 == '(' || c8 == ')' || c8 == '<' || c8 == '>' || (c8 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c8] == 4)) {
                        i15 += 4;
                    }
                    i8 = -1;
                    if (i17 == -1) {
                        i17 = i19;
                        i18 = i17;
                        c7 = c8;
                    }
                } else {
                    i8 = -1;
                }
            }
            i18 = i19;
            c7 = c8;
        }
        if (i16 > 0) {
            int i20 = i15 + i16;
            if (i20 > this.buf.length) {
                expandCapacity(i20);
            }
            this.count = i20;
            if (i16 == 1) {
                if (c7 == 8232) {
                    int i21 = i18 + 1;
                    System.arraycopy(this.buf, i21, this.buf, i18 + 6, (i7 - i18) - 1);
                    this.buf[i18] = '\\';
                    this.buf[i21] = 'u';
                    int i22 = i21 + 1;
                    this.buf[i22] = '2';
                    int i23 = i22 + 1;
                    this.buf[i23] = '0';
                    int i24 = i23 + 1;
                    this.buf[i24] = '2';
                    this.buf[i24 + 1] = '8';
                } else if (c7 == 8233) {
                    int i25 = i18 + 1;
                    System.arraycopy(this.buf, i25, this.buf, i18 + 6, (i7 - i18) - 1);
                    this.buf[i18] = '\\';
                    this.buf[i25] = 'u';
                    int i26 = i25 + 1;
                    this.buf[i26] = '2';
                    int i27 = i26 + 1;
                    this.buf[i27] = '0';
                    int i28 = i27 + 1;
                    this.buf[i28] = '2';
                    this.buf[i28 + 1] = '9';
                } else if (c7 == '(' || c7 == ')' || c7 == '<' || c7 == '>') {
                    int i29 = i18 + 1;
                    System.arraycopy(this.buf, i29, this.buf, i18 + 6, (i7 - i18) - 1);
                    this.buf[i18] = '\\';
                    this.buf[i29] = 'u';
                    int i30 = i29 + 1;
                    this.buf[i30] = IOUtils.DIGITS[(c7 >>> 12) & 15];
                    int i31 = i30 + 1;
                    this.buf[i31] = IOUtils.DIGITS[(c7 >>> 8) & 15];
                    int i32 = i31 + 1;
                    this.buf[i32] = IOUtils.DIGITS[(c7 >>> 4) & 15];
                    this.buf[i32 + 1] = IOUtils.DIGITS[c7 & 15];
                } else if (c7 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c7] != 4) {
                    int i33 = i18 + 1;
                    System.arraycopy(this.buf, i33, this.buf, i18 + 2, (i7 - i18) - 1);
                    this.buf[i18] = '\\';
                    this.buf[i33] = IOUtils.replaceChars[c7];
                } else {
                    int i34 = i18 + 1;
                    System.arraycopy(this.buf, i34, this.buf, i18 + 6, (i7 - i18) - 1);
                    this.buf[i18] = '\\';
                    int i35 = i34 + 1;
                    this.buf[i34] = 'u';
                    int i36 = i35 + 1;
                    this.buf[i35] = IOUtils.DIGITS[(c7 >>> 12) & 15];
                    int i37 = i36 + 1;
                    this.buf[i36] = IOUtils.DIGITS[(c7 >>> 8) & 15];
                    this.buf[i37] = IOUtils.DIGITS[(c7 >>> 4) & 15];
                    this.buf[i37 + 1] = IOUtils.DIGITS[c7 & 15];
                }
            } else if (i16 > 1) {
                for (int i38 = i17 - i6; i38 < str.length(); i38++) {
                    char charAt2 = str2.charAt(i38);
                    if (this.browserSecure && (charAt2 == '(' || charAt2 == ')' || charAt2 == '<' || charAt2 == '>')) {
                        int i39 = i17 + 1;
                        this.buf[i17] = '\\';
                        int i40 = i39 + 1;
                        this.buf[i39] = 'u';
                        int i41 = i40 + 1;
                        this.buf[i40] = IOUtils.DIGITS[(charAt2 >>> 12) & 15];
                        int i42 = i41 + 1;
                        this.buf[i41] = IOUtils.DIGITS[(charAt2 >>> 8) & 15];
                        int i43 = i42 + 1;
                        this.buf[i42] = IOUtils.DIGITS[(charAt2 >>> 4) & 15];
                        this.buf[i43] = IOUtils.DIGITS[charAt2 & 15];
                        i17 = i43 + 1;
                    } else if ((charAt2 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt2] != 0) || (charAt2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        int i44 = i17 + 1;
                        this.buf[i17] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[charAt2] == 4) {
                            int i45 = i44 + 1;
                            this.buf[i44] = 'u';
                            int i46 = i45 + 1;
                            this.buf[i45] = IOUtils.DIGITS[(charAt2 >>> 12) & 15];
                            int i47 = i46 + 1;
                            this.buf[i46] = IOUtils.DIGITS[(charAt2 >>> 8) & 15];
                            int i48 = i47 + 1;
                            this.buf[i47] = IOUtils.DIGITS[(charAt2 >>> 4) & 15];
                            i2 = i48 + 1;
                            this.buf[i48] = IOUtils.DIGITS[charAt2 & 15];
                        } else {
                            i2 = i44 + 1;
                            this.buf[i44] = IOUtils.replaceChars[charAt2];
                        }
                        i17 = i2;
                    } else if (charAt2 == 8232 || charAt2 == 8233) {
                        int i49 = i17 + 1;
                        this.buf[i17] = '\\';
                        int i50 = i49 + 1;
                        this.buf[i49] = 'u';
                        int i51 = i50 + 1;
                        this.buf[i50] = IOUtils.DIGITS[(charAt2 >>> 12) & 15];
                        int i52 = i51 + 1;
                        this.buf[i51] = IOUtils.DIGITS[(charAt2 >>> 8) & 15];
                        int i53 = i52 + 1;
                        this.buf[i52] = IOUtils.DIGITS[(charAt2 >>> 4) & 15];
                        this.buf[i53] = IOUtils.DIGITS[charAt2 & 15];
                        i17 = i53 + 1;
                    } else {
                        this.buf[i17] = charAt2;
                        i17++;
                    }
                }
            }
        }
        if (c3 != 0) {
            this.buf[this.count - 2] = '\"';
            this.buf[this.count - 1] = c3;
            return;
        }
        this.buf[this.count - 1] = '\"';
    }

    public void writeStringWithDoubleQuote(char[] cArr, char c2) {
        int i;
        char[] cArr2 = cArr;
        char c3 = c2;
        if (cArr2 == null) {
            writeNull();
            if (c3 != 0) {
                write((int) c3);
                return;
            }
            return;
        }
        int length = cArr2.length;
        int i2 = this.count + length + 2;
        if (c3 != 0) {
            i2++;
        }
        char c4 = 8;
        char c5 = 12;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(34);
                for (char c6 : cArr2) {
                    if (!isEnabled(SerializerFeature.BrowserSecure) || !(c6 == '(' || c6 == ')' || c6 == '<' || c6 == '>')) {
                        if (isEnabled(SerializerFeature.BrowserCompatible)) {
                            if (c6 == 8 || c6 == 12 || c6 == 10 || c6 == 13 || c6 == 9 || c6 == '\"' || c6 == '/' || c6 == '\\') {
                                write(92);
                                write((int) IOUtils.replaceChars[c6]);
                            } else if (c6 < ' ') {
                                write(92);
                                write(117);
                                write(48);
                                write(48);
                                int i3 = c6 * 2;
                                write((int) IOUtils.ASCII_CHARS[i3]);
                                write((int) IOUtils.ASCII_CHARS[i3 + 1]);
                            } else if (c6 >= 127) {
                                write(92);
                                write(117);
                                write((int) IOUtils.DIGITS[(c6 >>> 12) & 15]);
                                write((int) IOUtils.DIGITS[(c6 >>> 8) & 15]);
                                write((int) IOUtils.DIGITS[(c6 >>> 4) & 15]);
                                write((int) IOUtils.DIGITS[c6 & 15]);
                            }
                        } else if ((c6 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c6] != 0) || (c6 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write(92);
                            if (IOUtils.specicalFlags_doubleQuotes[c6] == 4) {
                                write(117);
                                write((int) IOUtils.DIGITS[(c6 >>> 12) & 15]);
                                write((int) IOUtils.DIGITS[(c6 >>> 8) & 15]);
                                write((int) IOUtils.DIGITS[(c6 >>> 4) & 15]);
                                write((int) IOUtils.DIGITS[c6 & 15]);
                            } else {
                                write((int) IOUtils.replaceChars[c6]);
                            }
                        }
                        write((int) c6);
                    } else {
                        write(92);
                        write(117);
                        write((int) IOUtils.DIGITS[(c6 >>> 12) & 15]);
                        write((int) IOUtils.DIGITS[(c6 >>> 8) & 15]);
                        write((int) IOUtils.DIGITS[(c6 >>> 4) & 15]);
                        write((int) IOUtils.DIGITS[c6 & 15]);
                    }
                }
                write(34);
                if (c3 != 0) {
                    write((int) c3);
                    return;
                }
                return;
            }
            expandCapacity(i);
        }
        int i4 = this.count + 1;
        int i5 = length + i4;
        this.buf[this.count] = '\"';
        System.arraycopy(cArr2, 0, this.buf, i4, cArr2.length);
        this.count = i;
        int i6 = -1;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            for (int i7 = i4; i7 < i5; i7++) {
                char c7 = this.buf[i7];
                if (c7 == '\"' || c7 == '/' || c7 == '\\') {
                    i++;
                } else if (c7 == 8 || c7 == 12 || c7 == 10 || c7 == 13 || c7 == 9) {
                    i++;
                } else if (c7 < ' ') {
                    i += 5;
                } else if (c7 >= 127) {
                    i += 5;
                }
                i6 = i7;
            }
            if (i > this.buf.length) {
                expandCapacity(i);
            }
            this.count = i;
            while (i6 >= i4) {
                char c8 = this.buf[i6];
                if (c8 == c4 || c8 == c5 || c8 == 10 || c8 == 13 || c8 == 9) {
                    int i8 = i6 + 1;
                    System.arraycopy(this.buf, i8, this.buf, i6 + 2, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i8] = IOUtils.replaceChars[c8];
                    i5++;
                } else if (c8 == '\"' || c8 == '/' || c8 == '\\') {
                    int i9 = i6 + 1;
                    System.arraycopy(this.buf, i9, this.buf, i6 + 2, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i9] = c8;
                    i5++;
                } else if (c8 < ' ') {
                    int i10 = i6 + 1;
                    System.arraycopy(this.buf, i10, this.buf, i6 + 6, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i10] = 'u';
                    this.buf[i6 + 2] = '0';
                    this.buf[i6 + 3] = '0';
                    int i11 = c8 * 2;
                    this.buf[i6 + 4] = IOUtils.ASCII_CHARS[i11];
                    this.buf[i6 + 5] = IOUtils.ASCII_CHARS[i11 + 1];
                    i5 += 5;
                } else if (c8 >= 127) {
                    int i12 = i6 + 1;
                    System.arraycopy(this.buf, i12, this.buf, i6 + 6, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i12] = 'u';
                    this.buf[i6 + 2] = IOUtils.DIGITS[(c8 >>> 12) & 15];
                    this.buf[i6 + 3] = IOUtils.DIGITS[(c8 >>> 8) & 15];
                    this.buf[i6 + 4] = IOUtils.DIGITS[(c8 >>> 4) & 15];
                    this.buf[i6 + 5] = IOUtils.DIGITS[c8 & 15];
                    i5 += 5;
                }
                i6--;
                c4 = 8;
                c5 = 12;
            }
            if (c3 != 0) {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = c3;
                return;
            }
            this.buf[this.count - 1] = '\"';
            return;
        }
        int i13 = i;
        int i14 = i4;
        int i15 = -1;
        int i16 = 0;
        char c9 = 0;
        int i17 = -1;
        while (i14 < i5) {
            char c10 = this.buf[i14];
            if (c10 < ']') {
                if ((c10 < '@' && (this.sepcialBits & (1 << c10)) != 0) || c10 == '\\') {
                    i16++;
                    if (c10 == '(' || c10 == ')' || c10 == '<' || c10 == '>' || (c10 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c10] == 4)) {
                        i13 += 4;
                    }
                    if (i15 == -1) {
                        i15 = i14;
                        i17 = i15;
                    } else {
                        i17 = i14;
                    }
                    c9 = c10;
                    i14++;
                    i6 = -1;
                }
            } else if (c10 >= 127 && (c10 == 8232 || c10 == 8233 || c10 < 160)) {
                if (i15 == i6) {
                    i15 = i14;
                }
                i16++;
                i13 += 4;
                i17 = i14;
                c9 = c10;
            }
            i14++;
            i6 = -1;
        }
        if (i16 > 0) {
            int i18 = i13 + i16;
            if (i18 > this.buf.length) {
                expandCapacity(i18);
            }
            this.count = i18;
            if (i16 == 1) {
                if (c9 == 8232) {
                    int i19 = i17 + 1;
                    System.arraycopy(this.buf, i19, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i19] = 'u';
                    int i20 = i19 + 1;
                    this.buf[i20] = '2';
                    int i21 = i20 + 1;
                    this.buf[i21] = '0';
                    int i22 = i21 + 1;
                    this.buf[i22] = '2';
                    this.buf[i22 + 1] = '8';
                } else if (c9 == 8233) {
                    int i23 = i17 + 1;
                    System.arraycopy(this.buf, i23, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i23] = 'u';
                    int i24 = i23 + 1;
                    this.buf[i24] = '2';
                    int i25 = i24 + 1;
                    this.buf[i25] = '0';
                    int i26 = i25 + 1;
                    this.buf[i26] = '2';
                    this.buf[i26 + 1] = '9';
                } else if (c9 == '(' || c9 == ')' || c9 == '<' || c9 == '>') {
                    int i27 = i17 + 1;
                    System.arraycopy(this.buf, i27, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i27] = 'u';
                    int i28 = i27 + 1;
                    this.buf[i28] = IOUtils.DIGITS[(c9 >>> 12) & 15];
                    int i29 = i28 + 1;
                    this.buf[i29] = IOUtils.DIGITS[(c9 >>> 8) & 15];
                    int i30 = i29 + 1;
                    this.buf[i30] = IOUtils.DIGITS[(c9 >>> 4) & 15];
                    this.buf[i30 + 1] = IOUtils.DIGITS[c9 & 15];
                } else if (c9 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c9] != 4) {
                    int i31 = i17 + 1;
                    System.arraycopy(this.buf, i31, this.buf, i17 + 2, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i31] = IOUtils.replaceChars[c9];
                } else {
                    int i32 = i17 + 1;
                    System.arraycopy(this.buf, i32, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    int i33 = i32 + 1;
                    this.buf[i32] = 'u';
                    int i34 = i33 + 1;
                    this.buf[i33] = IOUtils.DIGITS[(c9 >>> 12) & 15];
                    int i35 = i34 + 1;
                    this.buf[i34] = IOUtils.DIGITS[(c9 >>> 8) & 15];
                    this.buf[i35] = IOUtils.DIGITS[(c9 >>> 4) & 15];
                    this.buf[i35 + 1] = IOUtils.DIGITS[c9 & 15];
                }
            } else if (i16 > 1) {
                for (int i36 = i15 - i4; i36 < cArr2.length; i36++) {
                    char c11 = cArr2[i36];
                    if (!this.browserSecure || !(c11 == '(' || c11 == ')' || c11 == '<' || c11 == '>')) {
                        if (c11 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c11] == 0) {
                            if (c11 != '/' || !isEnabled(SerializerFeature.WriteSlashAsSpecial)) {
                                if (c11 == 8232 || c11 == 8233) {
                                    int i37 = i15 + 1;
                                    this.buf[i15] = '\\';
                                    int i38 = i37 + 1;
                                    this.buf[i37] = 'u';
                                    int i39 = i38 + 1;
                                    this.buf[i38] = IOUtils.DIGITS[(c11 >>> 12) & 15];
                                    int i40 = i39 + 1;
                                    this.buf[i39] = IOUtils.DIGITS[(c11 >>> 8) & 15];
                                    int i41 = i40 + 1;
                                    this.buf[i40] = IOUtils.DIGITS[(c11 >>> 4) & 15];
                                    i15 = i41 + 1;
                                    this.buf[i41] = IOUtils.DIGITS[c11 & 15];
                                } else {
                                    this.buf[i15] = c11;
                                    i15++;
                                }
                            }
                        }
                        int i42 = i15 + 1;
                        this.buf[i15] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[c11] == 4) {
                            int i43 = i42 + 1;
                            this.buf[i42] = 'u';
                            int i44 = i43 + 1;
                            this.buf[i43] = IOUtils.DIGITS[(c11 >>> 12) & 15];
                            int i45 = i44 + 1;
                            this.buf[i44] = IOUtils.DIGITS[(c11 >>> 8) & 15];
                            int i46 = i45 + 1;
                            this.buf[i45] = IOUtils.DIGITS[(c11 >>> 4) & 15];
                            i15 = i46 + 1;
                            this.buf[i46] = IOUtils.DIGITS[c11 & 15];
                        } else {
                            i15 = i42 + 1;
                            this.buf[i42] = IOUtils.replaceChars[c11];
                        }
                    } else {
                        int i47 = i15 + 1;
                        this.buf[i15] = '\\';
                        int i48 = i47 + 1;
                        this.buf[i47] = 'u';
                        int i49 = i48 + 1;
                        this.buf[i48] = IOUtils.DIGITS[(c11 >>> 12) & 15];
                        int i50 = i49 + 1;
                        this.buf[i49] = IOUtils.DIGITS[(c11 >>> 8) & 15];
                        int i51 = i50 + 1;
                        this.buf[i50] = IOUtils.DIGITS[(c11 >>> 4) & 15];
                        i15 = i51 + 1;
                        this.buf[i51] = IOUtils.DIGITS[c11 & 15];
                    }
                }
            }
        }
        if (c3 != 0) {
            this.buf[this.count - 2] = '\"';
            this.buf[this.count - 1] = c3;
            return;
        }
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldNameDirect(String str) {
        int length = str.length();
        int i = this.count + length + 3;
        if (i > this.buf.length) {
            expandCapacity(i);
        }
        this.buf[this.count] = '\"';
        str.getChars(0, length, this.buf, this.count + 1);
        this.count = i;
        this.buf[this.count - 2] = '\"';
        this.buf[this.count - 1] = ':';
    }

    public void write(List<String> list) {
        boolean z;
        int i;
        if (list.isEmpty()) {
            write("[]");
            return;
        }
        int i2 = this.count;
        int size = list.size();
        int i3 = i2;
        int i4 = 0;
        while (i4 < size) {
            String str = list.get(i4);
            if (str == null) {
                z = true;
            } else {
                int length = str.length();
                z = false;
                for (int i5 = 0; i5 < length; i5++) {
                    char charAt = str.charAt(i5);
                    z = charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\\';
                    if (z) {
                        break;
                    }
                }
            }
            if (z) {
                this.count = i2;
                write(91);
                for (int i6 = 0; i6 < list.size(); i6++) {
                    String str2 = list.get(i6);
                    if (i6 != 0) {
                        write(44);
                    }
                    if (str2 == null) {
                        write("null");
                    } else {
                        writeStringWithDoubleQuote(str2, 0);
                    }
                }
                write(93);
                return;
            }
            int length2 = str.length() + i3 + 3;
            if (i4 == list.size() - 1) {
                length2++;
            }
            if (length2 > this.buf.length) {
                this.count = i3;
                expandCapacity(length2);
            }
            if (i4 == 0) {
                i = i3 + 1;
                this.buf[i3] = '[';
            } else {
                i = i3 + 1;
                this.buf[i3] = ',';
            }
            int i7 = i + 1;
            this.buf[i] = '\"';
            str.getChars(0, str.length(), this.buf, i7);
            int length3 = i7 + str.length();
            this.buf[length3] = '\"';
            i4++;
            i3 = length3 + 1;
        }
        this.buf[i3] = ']';
        this.count = i3 + 1;
    }

    public void writeFieldValue(char c2, String str, char c3) {
        write((int) c2);
        writeFieldName(str);
        if (c3 == 0) {
            writeString("\u0000");
        } else {
            writeString(Character.toString(c3));
        }
    }

    public void writeFieldValue(char c2, String str, boolean z) {
        if (!this.quoteFieldNames) {
            write((int) c2);
            writeFieldName(str);
            write(z);
            return;
        }
        int i = z ? 4 : 5;
        int length = str.length();
        int i2 = this.count + length + 4 + i;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write((int) c2);
                writeString(str);
                write(58);
                write(z);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count;
        this.count = i2;
        this.buf[i3] = c2;
        int i4 = i3 + length + 1;
        this.buf[i3 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i3 + 2);
        this.buf[i4 + 1] = this.keySeperator;
        if (z) {
            System.arraycopy(":true".toCharArray(), 0, this.buf, i4 + 2, 5);
        } else {
            System.arraycopy(":false".toCharArray(), 0, this.buf, i4 + 2, 6);
        }
    }

    public void write(boolean z) {
        if (z) {
            write(RequestConstant.TRUE);
        } else {
            write(RequestConstant.FALSE);
        }
    }

    public void writeFieldValue(char c2, String str, int i) {
        if (i == Integer.MIN_VALUE || !this.quoteFieldNames) {
            write((int) c2);
            writeFieldName(str);
            writeInt(i);
            return;
        }
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int length = str.length();
        int i2 = this.count + length + 4 + stringSize;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write((int) c2);
                writeFieldName(str);
                writeInt(i);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count;
        this.count = i2;
        this.buf[i3] = c2;
        int i4 = i3 + length + 1;
        this.buf[i3 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i3 + 2);
        this.buf[i4 + 1] = this.keySeperator;
        this.buf[i4 + 2] = ':';
        IOUtils.getChars(i, this.count, this.buf);
    }

    public void writeFieldValue(char c2, String str, long j) {
        if (j == Long.MIN_VALUE || !this.quoteFieldNames) {
            write((int) c2);
            writeFieldName(str);
            writeLong(j);
            return;
        }
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int length = str.length();
        int i = this.count + length + 4 + stringSize;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write((int) c2);
                writeFieldName(str);
                writeLong(j);
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count;
        this.count = i;
        this.buf[i2] = c2;
        int i3 = i2 + length + 1;
        this.buf[i2 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i2 + 2);
        this.buf[i3 + 1] = this.keySeperator;
        this.buf[i3 + 2] = ':';
        IOUtils.getChars(j, this.count, this.buf);
    }

    public void writeFieldValue(char c2, String str, float f) {
        write((int) c2);
        writeFieldName(str);
        writeFloat(f, false);
    }

    public void writeFieldValue(char c2, String str, double d) {
        write((int) c2);
        writeFieldName(str);
        writeDouble(d, false);
    }

    public void writeFieldValue(char c2, String str, String str2) {
        if (!this.quoteFieldNames) {
            write((int) c2);
            writeFieldName(str);
            if (str2 == null) {
                writeNull();
            } else {
                writeString(str2);
            }
        } else if (this.useSingleQuotes) {
            write((int) c2);
            writeFieldName(str);
            if (str2 == null) {
                writeNull();
            } else {
                writeString(str2);
            }
        } else if (isEnabled(SerializerFeature.BrowserCompatible)) {
            write((int) c2);
            writeStringWithDoubleQuote(str, ':');
            writeStringWithDoubleQuote(str2, 0);
        } else {
            writeFieldValueStringWithDoubleQuoteCheck(c2, str, str2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cf, code lost:
        if ((r0.sepcialBits & (1 << r5)) == 0) goto L_0x00d4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02e8  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0340  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0101  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeFieldValueStringWithDoubleQuoteCheck(char r20, java.lang.String r21, java.lang.String r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            r2 = r22
            int r3 = r21.length()
            int r4 = r0.count
            if (r2 != 0) goto L_0x0013
            int r6 = r3 + 8
            int r4 = r4 + r6
            r6 = 4
            goto L_0x001c
        L_0x0013:
            int r6 = r22.length()
            int r7 = r3 + r6
            int r7 = r7 + 6
            int r4 = r4 + r7
        L_0x001c:
            char[] r7 = r0.buf
            int r7 = r7.length
            r8 = 0
            if (r4 <= r7) goto L_0x0035
            java.io.Writer r7 = r0.writer
            if (r7 == 0) goto L_0x0032
            r19.write((int) r20)
            r3 = 58
            r0.writeStringWithDoubleQuote((java.lang.String) r1, (char) r3)
            r0.writeStringWithDoubleQuote((java.lang.String) r2, (char) r8)
            return
        L_0x0032:
            r0.expandCapacity(r4)
        L_0x0035:
            char[] r7 = r0.buf
            int r9 = r0.count
            r7[r9] = r20
            int r7 = r0.count
            int r7 = r7 + 2
            int r9 = r7 + r3
            char[] r10 = r0.buf
            int r11 = r0.count
            r12 = 1
            int r11 = r11 + r12
            r13 = 34
            r10[r11] = r13
            char[] r10 = r0.buf
            r1.getChars(r8, r3, r10, r7)
            r0.count = r4
            char[] r1 = r0.buf
            r1[r9] = r13
            int r9 = r9 + r12
            char[] r1 = r0.buf
            int r3 = r9 + 1
            r7 = 58
            r1[r9] = r7
            r1 = 117(0x75, float:1.64E-43)
            if (r2 != 0) goto L_0x0080
            char[] r2 = r0.buf
            int r4 = r3 + 1
            r5 = 110(0x6e, float:1.54E-43)
            r2[r3] = r5
            char[] r2 = r0.buf
            int r3 = r4 + 1
            r2[r4] = r1
            char[] r1 = r0.buf
            int r2 = r3 + 1
            r4 = 108(0x6c, float:1.51E-43)
            r1[r3] = r4
            char[] r1 = r0.buf
            r3 = 108(0x6c, float:1.51E-43)
            r1[r2] = r3
            return
        L_0x0080:
            char[] r7 = r0.buf
            int r9 = r3 + 1
            r7[r3] = r13
            int r3 = r9 + r6
            char[] r7 = r0.buf
            r2.getChars(r8, r6, r7, r9)
            r6 = -1
            r10 = r4
            r4 = r9
            r7 = 0
            r11 = 0
            r14 = -1
            r15 = -1
        L_0x0094:
            r13 = 8233(0x2029, float:1.1537E-41)
            r1 = 8232(0x2028, float:1.1535E-41)
            r12 = 92
            if (r4 >= r3) goto L_0x010c
            char[] r5 = r0.buf
            char r5 = r5[r4]
            r8 = 93
            if (r5 < r8) goto L_0x00bd
            r8 = 127(0x7f, float:1.78E-43)
            if (r5 < r8) goto L_0x00ba
            if (r5 == r1) goto L_0x00b0
            if (r5 == r13) goto L_0x00b0
            r1 = 160(0xa0, float:2.24E-43)
            if (r5 >= r1) goto L_0x00ba
        L_0x00b0:
            if (r14 != r6) goto L_0x00b3
            r14 = r4
        L_0x00b3:
            int r7 = r7 + 1
            int r10 = r10 + 4
        L_0x00b7:
            r15 = r4
        L_0x00b8:
            r11 = r5
            goto L_0x0104
        L_0x00ba:
            r16 = r7
            goto L_0x0102
        L_0x00bd:
            r1 = 64
            if (r5 >= r1) goto L_0x00d2
            r16 = r7
            long r6 = r0.sepcialBits
            r17 = 1
            long r17 = r17 << r5
            long r6 = r6 & r17
            r17 = 0
            int r1 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x00d6
            goto L_0x00d4
        L_0x00d2:
            r16 = r7
        L_0x00d4:
            if (r5 != r12) goto L_0x00d8
        L_0x00d6:
            r1 = 1
            goto L_0x00d9
        L_0x00d8:
            r1 = 0
        L_0x00d9:
            if (r1 == 0) goto L_0x0101
            int r7 = r16 + 1
            r1 = 40
            if (r5 == r1) goto L_0x00f9
            r1 = 41
            if (r5 == r1) goto L_0x00f9
            r1 = 60
            if (r5 == r1) goto L_0x00f9
            r1 = 62
            if (r5 == r1) goto L_0x00f9
            byte[] r1 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            int r1 = r1.length
            if (r5 >= r1) goto L_0x00fb
            byte[] r1 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            byte r1 = r1[r5]
            r6 = 4
            if (r1 != r6) goto L_0x00fb
        L_0x00f9:
            int r10 = r10 + 4
        L_0x00fb:
            r6 = -1
            if (r14 != r6) goto L_0x00b7
            r14 = r4
            r15 = r14
            goto L_0x00b8
        L_0x0101:
            r6 = -1
        L_0x0102:
            r7 = r16
        L_0x0104:
            int r4 = r4 + 1
            r1 = 117(0x75, float:1.64E-43)
            r12 = 1
            r13 = 34
            goto L_0x0094
        L_0x010c:
            r16 = r7
            if (r16 <= 0) goto L_0x0398
            int r10 = r10 + r16
            char[] r4 = r0.buf
            int r4 = r4.length
            if (r10 <= r4) goto L_0x011a
            r0.expandCapacity(r10)
        L_0x011a:
            r0.count = r10
            r7 = r16
            r4 = 1
            if (r7 != r4) goto L_0x0262
            r2 = 50
            if (r11 != r1) goto L_0x0156
            int r1 = r15 + 1
            int r5 = r15 + 6
            int r3 = r3 - r15
            int r3 = r3 - r4
            char[] r6 = r0.buf
            char[] r7 = r0.buf
            java.lang.System.arraycopy(r6, r1, r7, r5, r3)
            char[] r3 = r0.buf
            r3[r15] = r12
            char[] r3 = r0.buf
            r5 = 117(0x75, float:1.64E-43)
            r3[r1] = r5
            char[] r3 = r0.buf
            int r1 = r1 + r4
            r3[r1] = r2
            char[] r3 = r0.buf
            int r1 = r1 + r4
            r5 = 48
            r3[r1] = r5
            char[] r3 = r0.buf
            int r1 = r1 + r4
            r3[r1] = r2
            char[] r2 = r0.buf
            int r1 = r1 + r4
            r3 = 56
            r2[r1] = r3
            goto L_0x0398
        L_0x0156:
            if (r11 != r13) goto L_0x0189
            int r1 = r15 + 1
            int r5 = r15 + 6
            int r3 = r3 - r15
            int r3 = r3 - r4
            char[] r6 = r0.buf
            char[] r7 = r0.buf
            java.lang.System.arraycopy(r6, r1, r7, r5, r3)
            char[] r3 = r0.buf
            r3[r15] = r12
            char[] r3 = r0.buf
            r5 = 117(0x75, float:1.64E-43)
            r3[r1] = r5
            char[] r3 = r0.buf
            int r1 = r1 + r4
            r3[r1] = r2
            char[] r3 = r0.buf
            int r1 = r1 + r4
            r5 = 48
            r3[r1] = r5
            char[] r3 = r0.buf
            int r1 = r1 + r4
            r3[r1] = r2
            char[] r2 = r0.buf
            int r1 = r1 + r4
            r3 = 57
            r2[r1] = r3
            goto L_0x0398
        L_0x0189:
            r1 = 40
            if (r11 == r1) goto L_0x0212
            r1 = 41
            if (r11 == r1) goto L_0x0212
            r1 = 60
            if (r11 == r1) goto L_0x0212
            r1 = 62
            if (r11 != r1) goto L_0x019a
            goto L_0x0212
        L_0x019a:
            byte[] r1 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            int r1 = r1.length
            if (r11 >= r1) goto L_0x01f6
            byte[] r1 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            byte r1 = r1[r11]
            r2 = 4
            if (r1 != r2) goto L_0x01f6
            int r1 = r15 + 1
            int r2 = r15 + 6
            int r3 = r3 - r15
            r4 = 1
            int r3 = r3 - r4
            char[] r4 = r0.buf
            char[] r5 = r0.buf
            java.lang.System.arraycopy(r4, r1, r5, r2, r3)
            char[] r2 = r0.buf
            r2[r15] = r12
            char[] r2 = r0.buf
            int r3 = r1 + 1
            r4 = 117(0x75, float:1.64E-43)
            r2[r1] = r4
            char[] r1 = r0.buf
            int r2 = r3 + 1
            char[] r4 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r5 = r11 >>> 12
            r5 = r5 & 15
            char r4 = r4[r5]
            r1[r3] = r4
            char[] r1 = r0.buf
            int r3 = r2 + 1
            char[] r4 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r5 = r11 >>> 8
            r5 = r5 & 15
            char r4 = r4[r5]
            r1[r2] = r4
            char[] r1 = r0.buf
            int r2 = r3 + 1
            char[] r4 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r5 = r11 >>> 4
            r5 = r5 & 15
            char r4 = r4[r5]
            r1[r3] = r4
            char[] r1 = r0.buf
            char[] r3 = com.alibaba.fastjson.util.IOUtils.DIGITS
            r4 = r11 & 15
            char r3 = r3[r4]
            r1[r2] = r3
            goto L_0x0398
        L_0x01f6:
            int r1 = r15 + 1
            int r2 = r15 + 2
            int r3 = r3 - r15
            r4 = 1
            int r3 = r3 - r4
            char[] r4 = r0.buf
            char[] r5 = r0.buf
            java.lang.System.arraycopy(r4, r1, r5, r2, r3)
            char[] r2 = r0.buf
            r2[r15] = r12
            char[] r2 = r0.buf
            char[] r3 = com.alibaba.fastjson.util.IOUtils.replaceChars
            char r3 = r3[r11]
            r2[r1] = r3
            goto L_0x0398
        L_0x0212:
            int r1 = r15 + 1
            int r2 = r15 + 6
            int r3 = r3 - r15
            r4 = 1
            int r3 = r3 - r4
            char[] r4 = r0.buf
            char[] r5 = r0.buf
            java.lang.System.arraycopy(r4, r1, r5, r2, r3)
            char[] r2 = r0.buf
            r2[r15] = r12
            char[] r2 = r0.buf
            int r3 = r1 + 1
            r4 = 117(0x75, float:1.64E-43)
            r2[r1] = r4
            char[] r1 = r0.buf
            int r2 = r3 + 1
            char[] r4 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r5 = r11 >>> 12
            r5 = r5 & 15
            char r4 = r4[r5]
            r1[r3] = r4
            char[] r1 = r0.buf
            int r3 = r2 + 1
            char[] r4 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r5 = r11 >>> 8
            r5 = r5 & 15
            char r4 = r4[r5]
            r1[r2] = r4
            char[] r1 = r0.buf
            int r2 = r3 + 1
            char[] r4 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r5 = r11 >>> 4
            r5 = r5 & 15
            char r4 = r4[r5]
            r1[r3] = r4
            char[] r1 = r0.buf
            char[] r3 = com.alibaba.fastjson.util.IOUtils.DIGITS
            r4 = r11 & 15
            char r3 = r3[r4]
            r1[r2] = r3
            goto L_0x0398
        L_0x0262:
            r3 = 1
            if (r7 <= r3) goto L_0x0398
            int r3 = r14 - r9
        L_0x0267:
            int r4 = r22.length()
            if (r3 >= r4) goto L_0x0398
            char r4 = r2.charAt(r3)
            boolean r5 = r0.browserSecure
            if (r5 == 0) goto L_0x02cf
            r5 = 40
            if (r4 == r5) goto L_0x0285
            r6 = 41
            if (r4 == r6) goto L_0x0285
            r6 = 60
            if (r4 == r6) goto L_0x0285
            r6 = 62
            if (r4 != r6) goto L_0x02d1
        L_0x0285:
            char[] r6 = r0.buf
            int r7 = r14 + 1
            r6[r14] = r12
            char[] r6 = r0.buf
            int r8 = r7 + 1
            r9 = 117(0x75, float:1.64E-43)
            r6[r7] = r9
            char[] r6 = r0.buf
            int r7 = r8 + 1
            char[] r9 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r10 = r4 >>> 12
            r10 = r10 & 15
            char r9 = r9[r10]
            r6[r8] = r9
            char[] r6 = r0.buf
            int r8 = r7 + 1
            char[] r9 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r10 = r4 >>> 8
            r10 = r10 & 15
            char r9 = r9[r10]
            r6[r7] = r9
            char[] r6 = r0.buf
            int r7 = r8 + 1
            char[] r9 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r10 = r4 >>> 4
            r10 = r10 & 15
            char r9 = r9[r10]
            r6[r8] = r9
            char[] r6 = r0.buf
            int r8 = r7 + 1
            char[] r9 = com.alibaba.fastjson.util.IOUtils.DIGITS
            r4 = r4 & 15
            char r4 = r9[r4]
            r6[r7] = r4
            r14 = r8
            r8 = 4
        L_0x02cb:
            r10 = 117(0x75, float:1.64E-43)
            goto L_0x0394
        L_0x02cf:
            r5 = 40
        L_0x02d1:
            byte[] r6 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            int r6 = r6.length
            if (r4 >= r6) goto L_0x02dc
            byte[] r6 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            byte r6 = r6[r4]
            if (r6 != 0) goto L_0x02e8
        L_0x02dc:
            r6 = 47
            if (r4 != r6) goto L_0x0340
            com.alibaba.fastjson.serializer.SerializerFeature r6 = com.alibaba.fastjson.serializer.SerializerFeature.WriteSlashAsSpecial
            boolean r6 = r0.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r6)
            if (r6 == 0) goto L_0x0340
        L_0x02e8:
            char[] r6 = r0.buf
            int r7 = r14 + 1
            r6[r14] = r12
            byte[] r6 = com.alibaba.fastjson.util.IOUtils.specicalFlags_doubleQuotes
            byte r6 = r6[r4]
            r8 = 4
            if (r6 != r8) goto L_0x0335
            char[] r6 = r0.buf
            int r9 = r7 + 1
            r10 = 117(0x75, float:1.64E-43)
            r6[r7] = r10
            char[] r6 = r0.buf
            int r7 = r9 + 1
            char[] r10 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r11 = r4 >>> 12
            r11 = r11 & 15
            char r10 = r10[r11]
            r6[r9] = r10
            char[] r6 = r0.buf
            int r9 = r7 + 1
            char[] r10 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r11 = r4 >>> 8
            r11 = r11 & 15
            char r10 = r10[r11]
            r6[r7] = r10
            char[] r6 = r0.buf
            int r7 = r9 + 1
            char[] r10 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r11 = r4 >>> 4
            r11 = r11 & 15
            char r10 = r10[r11]
            r6[r9] = r10
            char[] r6 = r0.buf
            int r9 = r7 + 1
            char[] r10 = com.alibaba.fastjson.util.IOUtils.DIGITS
            r4 = r4 & 15
            char r4 = r10[r4]
            r6[r7] = r4
        L_0x0333:
            r14 = r9
            goto L_0x02cb
        L_0x0335:
            char[] r6 = r0.buf
            int r9 = r7 + 1
            char[] r10 = com.alibaba.fastjson.util.IOUtils.replaceChars
            char r4 = r10[r4]
            r6[r7] = r4
            goto L_0x0333
        L_0x0340:
            r8 = 4
            if (r4 == r1) goto L_0x034f
            if (r4 != r13) goto L_0x0346
            goto L_0x034f
        L_0x0346:
            char[] r6 = r0.buf
            int r7 = r14 + 1
            r6[r14] = r4
            r14 = r7
            goto L_0x02cb
        L_0x034f:
            char[] r6 = r0.buf
            int r7 = r14 + 1
            r6[r14] = r12
            char[] r6 = r0.buf
            int r9 = r7 + 1
            r10 = 117(0x75, float:1.64E-43)
            r6[r7] = r10
            char[] r6 = r0.buf
            int r7 = r9 + 1
            char[] r11 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r14 = r4 >>> 12
            r14 = r14 & 15
            char r11 = r11[r14]
            r6[r9] = r11
            char[] r6 = r0.buf
            int r9 = r7 + 1
            char[] r11 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r14 = r4 >>> 8
            r14 = r14 & 15
            char r11 = r11[r14]
            r6[r7] = r11
            char[] r6 = r0.buf
            int r7 = r9 + 1
            char[] r11 = com.alibaba.fastjson.util.IOUtils.DIGITS
            int r14 = r4 >>> 4
            r14 = r14 & 15
            char r11 = r11[r14]
            r6[r9] = r11
            char[] r6 = r0.buf
            int r9 = r7 + 1
            char[] r11 = com.alibaba.fastjson.util.IOUtils.DIGITS
            r4 = r4 & 15
            char r4 = r11[r4]
            r6[r7] = r4
            r14 = r9
        L_0x0394:
            int r3 = r3 + 1
            goto L_0x0267
        L_0x0398:
            char[] r1 = r0.buf
            int r2 = r0.count
            r3 = 1
            int r2 = r2 - r3
            r3 = 34
            r1[r2] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeWriter.writeFieldValueStringWithDoubleQuoteCheck(char, java.lang.String, java.lang.String):void");
    }

    public void writeFieldValueStringWithDoubleQuote(char c2, String str, String str2) {
        int length = str.length();
        int i = this.count;
        int length2 = str2.length();
        int i2 = i + length + length2 + 6;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write((int) c2);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, 0);
                return;
            }
            expandCapacity(i2);
        }
        this.buf[this.count] = c2;
        int i3 = this.count + 2;
        int i4 = i3 + length;
        this.buf[this.count + 1] = '\"';
        str.getChars(0, length, this.buf, i3);
        this.count = i2;
        this.buf[i4] = '\"';
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        this.buf[i5] = ':';
        this.buf[i6] = '\"';
        str2.getChars(0, length2, this.buf, i6 + 1);
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldValue(char c2, String str, Enum<?> enumR) {
        if (enumR == null) {
            write((int) c2);
            writeFieldName(str);
            writeNull();
        } else if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            writeEnumFieldValue(c2, str, enumR.name());
        } else if (this.writeEnumUsingToString) {
            writeEnumFieldValue(c2, str, enumR.toString());
        } else {
            writeFieldValue(c2, str, enumR.ordinal());
        }
    }

    private void writeEnumFieldValue(char c2, String str, String str2) {
        if (this.useSingleQuotes) {
            writeFieldValue(c2, str, str2);
        } else {
            writeFieldValueStringWithDoubleQuote(c2, str, str2);
        }
    }

    public void writeFieldValue(char c2, String str, BigDecimal bigDecimal) {
        write((int) c2);
        writeFieldName(str);
        if (bigDecimal == null) {
            writeNull();
        } else {
            write(bigDecimal.toString());
        }
    }

    public void writeString(String str, char c2) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
            write((int) c2);
            return;
        }
        writeStringWithDoubleQuote(str, c2);
    }

    public void writeString(String str) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, 0);
        }
    }

    public void writeString(char[] cArr) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(cArr);
        } else {
            writeStringWithDoubleQuote(new String(cArr), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void writeStringWithSingleQuote(String str) {
        int i = 0;
        if (str == null) {
            int i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        int length = str.length();
        int i3 = this.count + length + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt <= 13 || charAt == '\\' || charAt == '\'' || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write((int) IOUtils.replaceChars[charAt]);
                    } else {
                        write((int) charAt);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count + 1;
        int i5 = i4 + length;
        this.buf[this.count] = '\'';
        str.getChars(0, length, this.buf, i4);
        this.count = i3;
        int i6 = -1;
        char c2 = 0;
        for (int i7 = i4; i7 < i5; i7++) {
            char c3 = this.buf[i7];
            if (c3 <= 13 || c3 == '\\' || c3 == '\'' || (c3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i6 = i7;
                c2 = c3;
            }
        }
        int i8 = i3 + i;
        if (i8 > this.buf.length) {
            expandCapacity(i8);
        }
        this.count = i8;
        if (i == 1) {
            int i9 = i6 + 1;
            System.arraycopy(this.buf, i9, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i9] = IOUtils.replaceChars[c2];
        } else if (i > 1) {
            int i10 = i6 + 1;
            System.arraycopy(this.buf, i10, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i10] = IOUtils.replaceChars[c2];
            int i11 = i5 + 1;
            for (int i12 = i10 - 2; i12 >= i4; i12--) {
                char c4 = this.buf[i12];
                if (c4 <= 13 || c4 == '\\' || c4 == '\'' || (c4 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    int i13 = i12 + 1;
                    System.arraycopy(this.buf, i13, this.buf, i12 + 2, (i11 - i12) - 1);
                    this.buf[i12] = '\\';
                    this.buf[i13] = IOUtils.replaceChars[c4];
                    i11++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    /* access modifiers changed from: protected */
    public void writeStringWithSingleQuote(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            int i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        int length = cArr.length;
        int i3 = this.count + length + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < cArr.length) {
                    char c2 = cArr[i];
                    if (c2 <= 13 || c2 == '\\' || c2 == '\'' || (c2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write((int) IOUtils.replaceChars[c2]);
                    } else {
                        write((int) c2);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count + 1;
        int i5 = length + i4;
        this.buf[this.count] = '\'';
        System.arraycopy(cArr, 0, this.buf, i4, cArr.length);
        this.count = i3;
        int i6 = -1;
        char c3 = 0;
        for (int i7 = i4; i7 < i5; i7++) {
            char c4 = this.buf[i7];
            if (c4 <= 13 || c4 == '\\' || c4 == '\'' || (c4 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i6 = i7;
                c3 = c4;
            }
        }
        int i8 = i3 + i;
        if (i8 > this.buf.length) {
            expandCapacity(i8);
        }
        this.count = i8;
        if (i == 1) {
            int i9 = i6 + 1;
            System.arraycopy(this.buf, i9, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i9] = IOUtils.replaceChars[c3];
        } else if (i > 1) {
            int i10 = i6 + 1;
            System.arraycopy(this.buf, i10, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i10] = IOUtils.replaceChars[c3];
            int i11 = i5 + 1;
            for (int i12 = i10 - 2; i12 >= i4; i12--) {
                char c5 = this.buf[i12];
                if (c5 <= 13 || c5 == '\\' || c5 == '\'' || (c5 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    int i13 = i12 + 1;
                    System.arraycopy(this.buf, i13, this.buf, i12 + 2, (i11 - i12) - 1);
                    this.buf[i12] = '\\';
                    this.buf[i13] = IOUtils.replaceChars[c5];
                    i11++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String str) {
        writeFieldName(str, false);
    }

    public void writeFieldName(String str, boolean z) {
        if (str == null) {
            write("null:");
        } else if (this.useSingleQuotes) {
            if (this.quoteFieldNames) {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(str);
        } else if (this.quoteFieldNames) {
            writeStringWithDoubleQuote(str, ':');
        } else {
            boolean z2 = str.length() == 0;
            int i = 0;
            while (true) {
                if (i >= str.length()) {
                    break;
                }
                char charAt = str.charAt(i);
                if ((charAt < '@' && (this.sepcialBits & (1 << charAt)) != 0) || charAt == '\\') {
                    z2 = true;
                    break;
                }
                i++;
            }
            if (z2) {
                writeStringWithDoubleQuote(str, ':');
                return;
            }
            write(str);
            write(58);
        }
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        String str2 = str;
        byte[] bArr = IOUtils.specicalFlags_singleQuotes;
        int length = str.length();
        boolean z = true;
        int i = this.count + length + 1;
        int i2 = 0;
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else if (length == 0) {
                write(39);
                write(39);
                write(58);
                return;
            } else {
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        char charAt = str2.charAt(i3);
                        if (charAt < bArr.length && bArr[charAt] != 0) {
                            break;
                        }
                        i3++;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    write(39);
                }
                while (i2 < length) {
                    char charAt2 = str2.charAt(i2);
                    if (charAt2 >= bArr.length || bArr[charAt2] == 0) {
                        write((int) charAt2);
                    } else {
                        write(92);
                        write((int) IOUtils.replaceChars[charAt2]);
                    }
                    i2++;
                }
                if (z) {
                    write(39);
                }
                write(58);
                return;
            }
        }
        if (length == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            int i4 = this.count;
            this.count = i4 + 1;
            cArr[i4] = '\'';
            char[] cArr2 = this.buf;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr2[i5] = '\'';
            char[] cArr3 = this.buf;
            int i6 = this.count;
            this.count = i6 + 1;
            cArr3[i6] = ':';
            return;
        }
        int i7 = this.count;
        int i8 = i7 + length;
        str2.getChars(0, length, this.buf, i7);
        this.count = i;
        int i9 = i7;
        boolean z2 = false;
        while (i9 < i8) {
            char c2 = this.buf[i9];
            if (c2 < bArr.length && bArr[c2] != 0) {
                if (!z2) {
                    i += 3;
                    if (i > this.buf.length) {
                        expandCapacity(i);
                    }
                    this.count = i;
                    int i10 = i9 + 1;
                    System.arraycopy(this.buf, i10, this.buf, i9 + 3, (i8 - i9) - 1);
                    System.arraycopy(this.buf, i2, this.buf, 1, i9);
                    this.buf[i7] = '\'';
                    this.buf[i10] = '\\';
                    int i11 = i10 + 1;
                    this.buf[i11] = IOUtils.replaceChars[c2];
                    i8 += 2;
                    this.buf[this.count - 2] = '\'';
                    i9 = i11;
                    z2 = true;
                } else {
                    i++;
                    if (i > this.buf.length) {
                        expandCapacity(i);
                    }
                    this.count = i;
                    int i12 = i9 + 1;
                    System.arraycopy(this.buf, i12, this.buf, i9 + 2, i8 - i9);
                    this.buf[i9] = '\\';
                    this.buf[i12] = IOUtils.replaceChars[c2];
                    i8++;
                    i9 = i12;
                }
            }
            i9++;
            i2 = 0;
        }
        this.buf[i - 1] = ':';
    }

    public void flush() {
        if (this.writer != null) {
            try {
                this.writer.write(this.buf, 0, this.count);
                this.writer.flush();
                this.count = 0;
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
    }
}
