package com.alibaba.fastjson.util;

import android.support.v7.widget.ActivityChooserView;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Properties;

public class IOUtils {
    public static final char[] ASCII_CHARS = {'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    public static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final Properties DEFAULT_PROPERTIES = new Properties();
    public static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final char[] DigitOnes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    public static final String FASTJSON_COMPATIBLEWITHFIELDNAME = "fastjson.compatibleWithFieldName";
    public static final String FASTJSON_COMPATIBLEWITHJAVABEAN = "fastjson.compatibleWithJavaBean";
    public static final String FASTJSON_PROPERTIES = "fastjson.properties";
    public static final int[] IA = new int[256];
    public static final Charset UTF8 = Charset.forName("UTF-8");
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags = new boolean[256];
    public static final char[] replaceChars = new char[93];
    static final int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
    public static final byte[] specicalFlags_doubleQuotes = new byte[Opcodes.IF_ICMPLT];
    public static final boolean[] specicalFlags_doubleQuotesFlags = new boolean[Opcodes.IF_ICMPLT];
    public static final byte[] specicalFlags_singleQuotes = new byte[Opcodes.IF_ICMPLT];
    public static final boolean[] specicalFlags_singleQuotesFlags = new boolean[Opcodes.IF_ICMPLT];

    public static int stringSize(long j) {
        long j2 = 10;
        for (int i = 1; i < 19; i++) {
            if (j < j2) {
                return i;
            }
            j2 *= 10;
        }
        return 19;
    }

    static {
        for (char c2 = 0; c2 < firstIdentifierFlags.length; c2 = (char) (c2 + 1)) {
            if (c2 >= 'A' && c2 <= 'Z') {
                firstIdentifierFlags[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                firstIdentifierFlags[c2] = true;
            } else if (c2 == '_' || c2 == '$') {
                firstIdentifierFlags[c2] = true;
            }
        }
        for (char c3 = 0; c3 < identifierFlags.length; c3 = (char) (c3 + 1)) {
            if (c3 >= 'A' && c3 <= 'Z') {
                identifierFlags[c3] = true;
            } else if (c3 >= 'a' && c3 <= 'z') {
                identifierFlags[c3] = true;
            } else if (c3 == '_') {
                identifierFlags[c3] = true;
            } else if (c3 >= '0' && c3 <= '9') {
                identifierFlags[c3] = true;
            }
        }
        try {
            loadPropertiesFromFile();
        } catch (Throwable unused) {
        }
        specicalFlags_doubleQuotes[0] = 4;
        specicalFlags_doubleQuotes[1] = 4;
        specicalFlags_doubleQuotes[2] = 4;
        specicalFlags_doubleQuotes[3] = 4;
        specicalFlags_doubleQuotes[4] = 4;
        specicalFlags_doubleQuotes[5] = 4;
        specicalFlags_doubleQuotes[6] = 4;
        specicalFlags_doubleQuotes[7] = 4;
        specicalFlags_doubleQuotes[8] = 1;
        specicalFlags_doubleQuotes[9] = 1;
        specicalFlags_doubleQuotes[10] = 1;
        specicalFlags_doubleQuotes[11] = 4;
        specicalFlags_doubleQuotes[12] = 1;
        specicalFlags_doubleQuotes[13] = 1;
        specicalFlags_doubleQuotes[34] = 1;
        specicalFlags_doubleQuotes[92] = 1;
        specicalFlags_singleQuotes[0] = 4;
        specicalFlags_singleQuotes[1] = 4;
        specicalFlags_singleQuotes[2] = 4;
        specicalFlags_singleQuotes[3] = 4;
        specicalFlags_singleQuotes[4] = 4;
        specicalFlags_singleQuotes[5] = 4;
        specicalFlags_singleQuotes[6] = 4;
        specicalFlags_singleQuotes[7] = 4;
        specicalFlags_singleQuotes[8] = 1;
        specicalFlags_singleQuotes[9] = 1;
        specicalFlags_singleQuotes[10] = 1;
        specicalFlags_singleQuotes[11] = 4;
        specicalFlags_singleQuotes[12] = 1;
        specicalFlags_singleQuotes[13] = 1;
        specicalFlags_singleQuotes[92] = 1;
        specicalFlags_singleQuotes[39] = 1;
        for (int i = 14; i <= 31; i++) {
            specicalFlags_doubleQuotes[i] = 4;
            specicalFlags_singleQuotes[i] = 4;
        }
        for (int i2 = 127; i2 < 160; i2++) {
            specicalFlags_doubleQuotes[i2] = 4;
            specicalFlags_singleQuotes[i2] = 4;
        }
        for (int i3 = 0; i3 < 161; i3++) {
            specicalFlags_doubleQuotesFlags[i3] = specicalFlags_doubleQuotes[i3] != 0;
            specicalFlags_singleQuotesFlags[i3] = specicalFlags_singleQuotes[i3] != 0;
        }
        replaceChars[0] = '0';
        replaceChars[1] = '1';
        replaceChars[2] = '2';
        replaceChars[3] = '3';
        replaceChars[4] = '4';
        replaceChars[5] = '5';
        replaceChars[6] = '6';
        replaceChars[7] = '7';
        replaceChars[8] = 'b';
        replaceChars[9] = 't';
        replaceChars[10] = 'n';
        replaceChars[11] = 'v';
        replaceChars[12] = 'f';
        replaceChars[13] = 'r';
        replaceChars[34] = '\"';
        replaceChars[39] = '\'';
        replaceChars[47] = '/';
        replaceChars[92] = '\\';
        Arrays.fill(IA, -1);
        int length = CA.length;
        for (int i4 = 0; i4 < length; i4++) {
            IA[CA[i4]] = i4;
        }
        IA[61] = 0;
    }

    public static String getStringProperty(String str) {
        String str2;
        try {
            str2 = System.getProperty(str);
        } catch (SecurityException unused) {
            str2 = null;
        }
        return str2 == null ? DEFAULT_PROPERTIES.getProperty(str) : str2;
    }

    public static void loadPropertiesFromFile() {
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() {
            public InputStream run() {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null) {
                    return contextClassLoader.getResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
                }
                return ClassLoader.getSystemResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
            }
        });
        if (inputStream != null) {
            try {
                DEFAULT_PROPERTIES.load(inputStream);
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void getChars(long j, int i, char[] cArr) {
        char c2;
        if (j < 0) {
            c2 = '-';
            j = -j;
        } else {
            c2 = 0;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            int i2 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            int i3 = i - 1;
            cArr[i3] = DigitOnes[i2];
            i = i3 - 1;
            cArr[i] = DigitTens[i2];
            j = j2;
        }
        int i4 = (int) j;
        while (i4 >= 65536) {
            int i5 = i4 / 100;
            int i6 = i4 - (((i5 << 6) + (i5 << 5)) + (i5 << 2));
            int i7 = i - 1;
            cArr[i7] = DigitOnes[i6];
            i = i7 - 1;
            cArr[i] = DigitTens[i6];
            i4 = i5;
        }
        while (true) {
            int i8 = (52429 * i4) >>> 19;
            i--;
            cArr[i] = digits[i4 - ((i8 << 3) + (i8 << 1))];
            if (i8 == 0) {
                break;
            }
            i4 = i8;
        }
        if (c2 != 0) {
            cArr[i - 1] = c2;
        }
    }

    public static void getChars(int i, int i2, char[] cArr) {
        char c2;
        if (i < 0) {
            c2 = '-';
            i = -i;
        } else {
            c2 = 0;
        }
        while (i >= 65536) {
            int i3 = i / 100;
            int i4 = i - (((i3 << 6) + (i3 << 5)) + (i3 << 2));
            int i5 = i2 - 1;
            cArr[i5] = DigitOnes[i4];
            i2 = i5 - 1;
            cArr[i2] = DigitTens[i4];
            i = i3;
        }
        while (true) {
            int i6 = (52429 * i) >>> 19;
            i2--;
            cArr[i2] = digits[i - ((i6 << 3) + (i6 << 1))];
            if (i6 == 0) {
                break;
            }
            i = i6;
        }
        if (c2 != 0) {
            cArr[i2 - 1] = c2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void getChars(byte r4, int r5, char[] r6) {
        /*
            if (r4 >= 0) goto L_0x0006
            r0 = 45
            int r4 = -r4
            goto L_0x0007
        L_0x0006:
            r0 = 0
        L_0x0007:
            r1 = 52429(0xcccd, float:7.3469E-41)
            int r1 = r1 * r4
            int r1 = r1 >>> 19
            int r2 = r1 << 3
            int r3 = r1 << 1
            int r2 = r2 + r3
            int r4 = r4 - r2
            int r5 = r5 + -1
            char[] r2 = digits
            char r4 = r2[r4]
            r6[r5] = r4
            if (r1 != 0) goto L_0x0025
            if (r0 == 0) goto L_0x0024
            int r5 = r5 + -1
            r6[r5] = r0
        L_0x0024:
            return
        L_0x0025:
            r4 = r1
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.IOUtils.getChars(byte, int, char[]):void");
    }

    public static int stringSize(int i) {
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static void decode(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            CoderResult flush = charsetDecoder.flush(charBuffer);
            if (!flush.isUnderflow()) {
                flush.throwException();
            }
        } catch (CharacterCodingException e) {
            throw new JSONException("utf8 decode error, " + e.getMessage(), e);
        }
    }

    public static boolean firstIdentifier(char c2) {
        return c2 < firstIdentifierFlags.length && firstIdentifierFlags[c2];
    }

    public static boolean isIdent(char c2) {
        return c2 < identifierFlags.length && identifierFlags[c2];
    }

    public static byte[] decodeBase64(char[] cArr, int i, int i2) {
        int i3;
        int i4 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i5 = (i + i2) - 1;
        while (i < i5 && IA[cArr[i]] < 0) {
            i++;
        }
        while (i5 > 0 && IA[cArr[i5]] < 0) {
            i5--;
        }
        int i6 = cArr[i5] == '=' ? cArr[i5 + -1] == '=' ? 2 : 1 : 0;
        int i7 = (i5 - i) + 1;
        if (i2 > 76) {
            i3 = (cArr[76] == 13 ? i7 / 78 : 0) << 1;
        } else {
            i3 = 0;
        }
        int i8 = (((i7 - i3) * 6) >> 3) - i6;
        byte[] bArr = new byte[i8];
        int i9 = (i8 / 3) * 3;
        int i10 = i;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i9) {
            int i13 = i10 + 1;
            int i14 = i13 + 1;
            int i15 = i14 + 1;
            int i16 = i15 + 1;
            int i17 = (IA[cArr[i10]] << 18) | (IA[cArr[i13]] << 12) | (IA[cArr[i14]] << 6) | IA[cArr[i15]];
            int i18 = i11 + 1;
            bArr[i11] = (byte) (i17 >> 16);
            int i19 = i18 + 1;
            bArr[i18] = (byte) (i17 >> 8);
            int i20 = i19 + 1;
            bArr[i19] = (byte) i17;
            if (i3 <= 0 || (i12 = i12 + 1) != 19) {
                i10 = i16;
            } else {
                i10 = i16 + 2;
                i12 = 0;
            }
            i11 = i20;
        }
        if (i11 < i8) {
            int i21 = 0;
            while (i10 <= i5 - i6) {
                i4 |= IA[cArr[i10]] << (18 - (i21 * 6));
                i21++;
                i10++;
            }
            int i22 = 16;
            while (i11 < i8) {
                bArr[i11] = (byte) (i4 >> i22);
                i22 -= 8;
                i11++;
            }
        }
        return bArr;
    }

    public static byte[] decodeBase64(String str, int i, int i2) {
        int i3;
        int i4 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i5 = (i + i2) - 1;
        while (i < i5 && IA[str.charAt(i)] < 0) {
            i++;
        }
        while (i5 > 0 && IA[str.charAt(i5)] < 0) {
            i5--;
        }
        int i6 = str.charAt(i5) == '=' ? str.charAt(i5 + -1) == '=' ? 2 : 1 : 0;
        int i7 = (i5 - i) + 1;
        if (i2 > 76) {
            i3 = (str.charAt(76) == 13 ? i7 / 78 : 0) << 1;
        } else {
            i3 = 0;
        }
        int i8 = (((i7 - i3) * 6) >> 3) - i6;
        byte[] bArr = new byte[i8];
        int i9 = (i8 / 3) * 3;
        int i10 = i;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i9) {
            int i13 = i10 + 1;
            int i14 = i13 + 1;
            int i15 = i14 + 1;
            int i16 = i15 + 1;
            int i17 = (IA[str.charAt(i10)] << 18) | (IA[str.charAt(i13)] << 12) | (IA[str.charAt(i14)] << 6) | IA[str.charAt(i15)];
            int i18 = i11 + 1;
            bArr[i11] = (byte) (i17 >> 16);
            int i19 = i18 + 1;
            bArr[i18] = (byte) (i17 >> 8);
            int i20 = i19 + 1;
            bArr[i19] = (byte) i17;
            if (i3 <= 0 || (i12 = i12 + 1) != 19) {
                i10 = i16;
            } else {
                i10 = i16 + 2;
                i12 = 0;
            }
            i11 = i20;
        }
        if (i11 < i8) {
            int i21 = 0;
            while (i10 <= i5 - i6) {
                i4 |= IA[str.charAt(i10)] << (18 - (i21 * 6));
                i21++;
                i10++;
            }
            int i22 = 16;
            while (i11 < i8) {
                bArr[i11] = (byte) (i4 >> i22);
                i22 -= 8;
                i11++;
            }
        }
        return bArr;
    }

    public static byte[] decodeBase64(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i3 = length - 1;
        int i4 = 0;
        while (i4 < i3 && IA[str.charAt(i4) & 255] < 0) {
            i4++;
        }
        while (i3 > 0 && IA[str.charAt(i3) & 255] < 0) {
            i3--;
        }
        int i5 = str.charAt(i3) == '=' ? str.charAt(i3 + -1) == '=' ? 2 : 1 : 0;
        int i6 = (i3 - i4) + 1;
        if (length > 76) {
            i = (str.charAt(76) == 13 ? i6 / 78 : 0) << 1;
        } else {
            i = 0;
        }
        int i7 = (((i6 - i) * 6) >> 3) - i5;
        byte[] bArr = new byte[i7];
        int i8 = (i7 / 3) * 3;
        int i9 = i4;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            int i12 = i9 + 1;
            int i13 = i12 + 1;
            int i14 = i13 + 1;
            int i15 = i14 + 1;
            int i16 = (IA[str.charAt(i9)] << 18) | (IA[str.charAt(i12)] << 12) | (IA[str.charAt(i13)] << 6) | IA[str.charAt(i14)];
            int i17 = i10 + 1;
            bArr[i10] = (byte) (i16 >> 16);
            int i18 = i17 + 1;
            bArr[i17] = (byte) (i16 >> 8);
            int i19 = i18 + 1;
            bArr[i18] = (byte) i16;
            if (i <= 0 || (i11 = i11 + 1) != 19) {
                i9 = i15;
            } else {
                i9 = i15 + 2;
                i11 = 0;
            }
            i10 = i19;
        }
        if (i10 < i7) {
            int i20 = 0;
            while (i9 <= i3 - i5) {
                i2 |= IA[str.charAt(i9)] << (18 - (i20 * 6));
                i20++;
                i9++;
            }
            int i21 = 16;
            while (i10 < i7) {
                bArr[i10] = (byte) (i2 >> i21);
                i21 -= 8;
                i10++;
            }
        }
        return bArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v29, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int encodeUTF8(char[] r8, int r9, int r10, byte[] r11) {
        /*
            int r0 = r9 + r10
            int r1 = r11.length
            int r10 = java.lang.Math.min(r10, r1)
            r1 = 0
            int r10 = r10 + r1
        L_0x0009:
            r2 = 128(0x80, float:1.794E-43)
            if (r1 >= r10) goto L_0x001d
            char r3 = r8[r9]
            if (r3 >= r2) goto L_0x001d
            int r2 = r1 + 1
            int r3 = r9 + 1
            char r9 = r8[r9]
            byte r9 = (byte) r9
            r11[r1] = r9
            r1 = r2
            r9 = r3
            goto L_0x0009
        L_0x001d:
            if (r9 >= r0) goto L_0x00da
            int r10 = r9 + 1
            char r9 = r8[r9]
            if (r9 >= r2) goto L_0x002d
            int r3 = r1 + 1
            byte r9 = (byte) r9
            r11[r1] = r9
        L_0x002a:
            r9 = r10
            r1 = r3
            goto L_0x001d
        L_0x002d:
            r3 = 2048(0x800, float:2.87E-42)
            if (r9 >= r3) goto L_0x0044
            int r3 = r1 + 1
            int r4 = r9 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            byte r4 = (byte) r4
            r11[r1] = r4
            int r1 = r3 + 1
            r9 = r9 & 63
            r9 = r9 | r2
            byte r9 = (byte) r9
            r11[r3] = r9
        L_0x0042:
            r9 = r10
            goto L_0x001d
        L_0x0044:
            r3 = 55296(0xd800, float:7.7486E-41)
            r4 = 63
            if (r9 < r3) goto L_0x00be
            r3 = 57344(0xe000, float:8.0356E-41)
            if (r9 >= r3) goto L_0x00be
            int r3 = r10 + -1
            boolean r5 = java.lang.Character.isHighSurrogate(r9)
            r6 = 1
            if (r5 == 0) goto L_0x007c
            int r5 = r0 - r3
            r7 = 2
            if (r5 >= r7) goto L_0x0060
            r9 = -1
            goto L_0x0082
        L_0x0060:
            int r3 = r3 + 1
            char r3 = r8[r3]
            boolean r5 = java.lang.Character.isLowSurrogate(r3)
            if (r5 == 0) goto L_0x006f
            int r9 = java.lang.Character.toCodePoint(r9, r3)
            goto L_0x0082
        L_0x006f:
            com.alibaba.fastjson.JSONException r8 = new com.alibaba.fastjson.JSONException
            java.nio.charset.MalformedInputException r9 = new java.nio.charset.MalformedInputException
            r9.<init>(r6)
            java.lang.String r10 = "encodeUTF8 error"
            r8.<init>(r10, r9)
            throw r8
        L_0x007c:
            boolean r3 = java.lang.Character.isLowSurrogate(r9)
            if (r3 != 0) goto L_0x00b1
        L_0x0082:
            if (r9 >= 0) goto L_0x0089
            int r9 = r1 + 1
            r11[r1] = r4
            goto L_0x00af
        L_0x0089:
            int r3 = r1 + 1
            int r5 = r9 >> 18
            r5 = r5 | 240(0xf0, float:3.36E-43)
            byte r5 = (byte) r5
            r11[r1] = r5
            int r1 = r3 + 1
            int r5 = r9 >> 12
            r5 = r5 & r4
            r5 = r5 | r2
            byte r5 = (byte) r5
            r11[r3] = r5
            int r3 = r1 + 1
            int r5 = r9 >> 6
            r4 = r4 & r5
            r4 = r4 | r2
            byte r4 = (byte) r4
            r11[r1] = r4
            int r1 = r3 + 1
            r9 = r9 & 63
            r9 = r9 | r2
            byte r9 = (byte) r9
            r11[r3] = r9
            int r10 = r10 + 1
            r9 = r1
        L_0x00af:
            r1 = r9
            goto L_0x0042
        L_0x00b1:
            com.alibaba.fastjson.JSONException r8 = new com.alibaba.fastjson.JSONException
            java.nio.charset.MalformedInputException r9 = new java.nio.charset.MalformedInputException
            r9.<init>(r6)
            java.lang.String r10 = "encodeUTF8 error"
            r8.<init>(r10, r9)
            throw r8
        L_0x00be:
            int r3 = r1 + 1
            int r5 = r9 >> 12
            r5 = r5 | 224(0xe0, float:3.14E-43)
            byte r5 = (byte) r5
            r11[r1] = r5
            int r1 = r3 + 1
            int r5 = r9 >> 6
            r4 = r4 & r5
            r4 = r4 | r2
            byte r4 = (byte) r4
            r11[r3] = r4
            int r3 = r1 + 1
            r9 = r9 & 63
            r9 = r9 | r2
            byte r9 = (byte) r9
            r11[r1] = r9
            goto L_0x002a
        L_0x00da:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.IOUtils.encodeUTF8(char[], int, int, byte[]):int");
    }

    public static int decodeUTF8(byte[] bArr, int i, int i2, char[] cArr) {
        int i3;
        int i4;
        int i5 = i + i2;
        int min = Math.min(i2, cArr.length);
        int i6 = i;
        int i7 = 0;
        while (i3 < min && bArr[i4] >= 0) {
            cArr[i3] = (char) bArr[i4];
            i7 = i3 + 1;
            i6 = i4 + 1;
        }
        while (i4 < i5) {
            int i8 = i4 + 1;
            byte b2 = bArr[i4];
            if (b2 >= 0) {
                cArr[i3] = (char) b2;
                i4 = i8;
                i3++;
            } else if ((b2 >> 5) != -2 || (b2 & 30) == 0) {
                if ((b2 >> 4) == -2) {
                    int i9 = i8 + 1;
                    if (i9 >= i5) {
                        return -1;
                    }
                    byte b3 = bArr[i8];
                    int i10 = i9 + 1;
                    byte b4 = bArr[i9];
                    if ((b2 == -32 && (b3 & 224) == 128) || (b3 & 192) != 128 || (b4 & 192) != 128) {
                        return -1;
                    }
                    char c2 = (char) (((b3 << 6) ^ (b2 << 12)) ^ (-123008 ^ b4));
                    if (c2 >= 55296 && c2 < 57344) {
                        return -1;
                    }
                    cArr[i3] = c2;
                    i3++;
                    i4 = i10;
                } else if ((b2 >> 3) != -2 || i8 + 2 >= i5) {
                    return -1;
                } else {
                    int i11 = i8 + 1;
                    byte b5 = bArr[i8];
                    int i12 = i11 + 1;
                    byte b6 = bArr[i11];
                    int i13 = i12 + 1;
                    byte b7 = bArr[i12];
                    byte b8 = (((b2 << 18) ^ (b5 << 12)) ^ (b6 << 6)) ^ (3678080 ^ b7);
                    if ((b5 & 192) != 128 || (b6 & 192) != 128 || (b7 & 192) != 128 || !Character.isSupplementaryCodePoint(b8)) {
                        return -1;
                    }
                    int i14 = i3 + 1;
                    cArr[i3] = (char) ((b8 >>> 10) + 55232);
                    i3 = i14 + 1;
                    cArr[i14] = (char) ((b8 & 1023) + 56320);
                    i4 = i13;
                }
            } else if (i8 >= i5) {
                return -1;
            } else {
                int i15 = i8 + 1;
                byte b9 = bArr[i8];
                if ((b9 & 192) != 128) {
                    return -1;
                }
                cArr[i3] = (char) ((b9 ^ (b2 << 6)) ^ 3968);
                i4 = i15;
                i3++;
            }
        }
        return i3;
    }

    public static String readAll(Reader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            char[] cArr = new char[2048];
            while (true) {
                int read = reader.read(cArr, 0, cArr.length);
                if (read < 0) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } catch (Exception e) {
            throw new JSONException("read string from reader error", e);
        }
    }

    public static boolean isValidJsonpQueryParam(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '.' && !isIdent(charAt)) {
                return false;
            }
        }
        return true;
    }
}
