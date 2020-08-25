package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c2, char c3, char c4, char c5, char c6, char c7, int i, int i2) {
        if (c2 < '1' || c2 > '3' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9' || c5 < '0' || c5 > '9') {
            return false;
        }
        if (c6 == '0') {
            if (c7 < '1' || c7 > '9') {
                return false;
            }
        } else if (c6 != '1') {
            return false;
        } else {
            if (!(c7 == '0' || c7 == '1' || c7 == '2')) {
                return false;
            }
        }
        if (i == 48) {
            return i2 >= 49 && i2 <= 57;
        }
        if (i == 49 || i == 50) {
            return i2 >= 48 && i2 <= 57;
        }
        if (i == 51) {
            return i2 == 48 || i2 == 49;
        }
        return false;
    }

    private boolean checkTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        if (c2 == '0') {
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        } else if (c2 == '1') {
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        } else if (c2 != '2' || c3 < '0' || c3 > '4') {
            return false;
        }
        if (c4 < '0' || c4 > '5') {
            if (!(c4 == '6' && c5 == '0')) {
                return false;
            }
        } else if (c5 < '0' || c5 > '9') {
            return false;
        }
        return (c6 < '0' || c6 > '5') ? c6 == '6' && c7 == '0' : c7 >= '0' && c7 <= '9';
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = this.text.length();
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    public final char charAt(int i) {
        if (i >= this.len) {
            return JSONLexer.EOI;
        }
        return this.text.charAt(i);
    }

    public final char next() {
        char c2;
        int i = this.bp + 1;
        this.bp = i;
        if (i >= this.len) {
            c2 = JSONLexer.EOI;
        } else {
            c2 = this.text.charAt(i);
        }
        this.ch = c2;
        return c2;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    /* access modifiers changed from: protected */
    public final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    public final int indexOf(char c2, int i) {
        return this.text.indexOf(c2, i);
    }

    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    public byte[] bytesValue() {
        if (this.token != 26) {
            return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
        }
        int i = this.np + 1;
        int i2 = this.sp;
        if (i2 % 2 == 0) {
            byte[] bArr = new byte[(i2 / 2)];
            for (int i3 = 0; i3 < bArr.length; i3++) {
                int i4 = (i3 * 2) + i;
                char charAt = this.text.charAt(i4);
                char charAt2 = this.text.charAt(i4 + 1);
                char c2 = '7';
                int i5 = charAt - (charAt <= '9' ? '0' : '7');
                if (charAt2 <= '9') {
                    c2 = '0';
                }
                bArr[i3] = (byte) ((i5 << 4) | (charAt2 - c2));
            }
            return bArr;
        }
        throw new JSONException("illegal state. " + i2);
    }

    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    public final String subString(int i, int i2) {
        if (!ASMUtils.IS_ANDROID) {
            return this.text.substring(i, i2 + i);
        }
        if (i2 < this.sbuf.length) {
            this.text.getChars(i, i + i2, this.sbuf, 0);
            return new String(this.sbuf, 0, i2);
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return new String(cArr);
    }

    public final char[] sub_chars(int i, int i2) {
        if (!ASMUtils.IS_ANDROID || i2 >= this.sbuf.length) {
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return cArr;
        }
        this.text.getChars(i, i2 + i, this.sbuf, 0);
        return this.sbuf;
    }

    public final String numberString() {
        char charAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    public final BigDecimal decimalValue() {
        char charAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x01f1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean scanISO8601DateIfMatch(boolean r35, int r36) {
        /*
            r34 = this;
            r9 = r34
            r11 = r36
            r12 = 8
            r13 = 0
            if (r11 >= r12) goto L_0x000a
            return r13
        L_0x000a:
            int r0 = r9.bp
            char r14 = r9.charAt(r0)
            int r0 = r9.bp
            r15 = 1
            int r0 = r0 + r15
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            r8 = 2
            int r1 = r1 + r8
            char r7 = r9.charAt(r1)
            int r1 = r9.bp
            r16 = 3
            int r1 = r1 + 3
            char r6 = r9.charAt(r1)
            int r1 = r9.bp
            int r1 = r1 + 4
            char r1 = r9.charAt(r1)
            int r2 = r9.bp
            r5 = 5
            int r2 = r2 + r5
            char r2 = r9.charAt(r2)
            int r3 = r9.bp
            r17 = 6
            int r3 = r3 + 6
            char r3 = r9.charAt(r3)
            int r4 = r9.bp
            int r4 = r4 + 7
            char r4 = r9.charAt(r4)
            r12 = 57
            r5 = 48
            if (r35 != 0) goto L_0x00c9
            r13 = 13
            if (r11 <= r13) goto L_0x00c9
            int r13 = r9.bp
            int r13 = r13 + r11
            int r13 = r13 - r15
            char r13 = r9.charAt(r13)
            int r15 = r9.bp
            int r15 = r15 + r11
            int r15 = r15 - r8
            char r15 = r9.charAt(r15)
            r8 = 47
            if (r14 != r8) goto L_0x00c9
            r8 = 68
            if (r0 != r8) goto L_0x00c9
            r8 = 97
            if (r7 != r8) goto L_0x00c9
            r8 = 116(0x74, float:1.63E-43)
            if (r6 != r8) goto L_0x00c9
            r8 = 101(0x65, float:1.42E-43)
            if (r1 != r8) goto L_0x00c9
            r8 = 40
            if (r2 != r8) goto L_0x00c9
            r8 = 47
            if (r13 != r8) goto L_0x00c9
            r8 = 41
            if (r15 != r8) goto L_0x00c9
            r0 = -1
            r0 = 6
            r1 = -1
        L_0x0089:
            if (r0 >= r11) goto L_0x00a0
            int r2 = r9.bp
            int r2 = r2 + r0
            char r2 = r9.charAt(r2)
            r3 = 43
            if (r2 != r3) goto L_0x0098
            r1 = r0
            goto L_0x009d
        L_0x0098:
            if (r2 < r5) goto L_0x00a0
            if (r2 <= r12) goto L_0x009d
            goto L_0x00a0
        L_0x009d:
            int r0 = r0 + 1
            goto L_0x0089
        L_0x00a0:
            r0 = -1
            if (r1 != r0) goto L_0x00a5
            r0 = 0
            return r0
        L_0x00a5:
            int r0 = r9.bp
            int r0 = r0 + 6
            int r2 = r9.bp
            int r2 = r2 + r1
            int r2 = r2 - r0
            java.lang.String r0 = r9.subString(r0, r2)
            long r0 = java.lang.Long.parseLong(r0)
            java.util.TimeZone r2 = r9.timeZone
            java.util.Locale r3 = r9.locale
            java.util.Calendar r2 = java.util.Calendar.getInstance(r2, r3)
            r9.calendar = r2
            java.util.Calendar r2 = r9.calendar
            r2.setTimeInMillis(r0)
            r8 = 5
            r9.token = r8
            r0 = 1
            return r0
        L_0x00c9:
            r8 = 5
            r12 = 9
            r13 = 14
            r15 = 45
            r19 = 10
            r5 = 8
            if (r11 == r5) goto L_0x04c9
            if (r11 == r13) goto L_0x04c9
            r5 = 16
            if (r11 != r5) goto L_0x00ec
            int r5 = r9.bp
            int r5 = r5 + 10
            char r5 = r9.charAt(r5)
            r8 = 84
            if (r5 == r8) goto L_0x04c9
            r8 = 32
            if (r5 == r8) goto L_0x04c9
        L_0x00ec:
            r5 = 17
            if (r11 != r5) goto L_0x00fc
            int r5 = r9.bp
            int r5 = r5 + 6
            char r5 = r9.charAt(r5)
            if (r5 == r15) goto L_0x00fc
            goto L_0x04c9
        L_0x00fc:
            if (r11 >= r12) goto L_0x0100
            r5 = 0
            return r5
        L_0x0100:
            int r5 = r9.bp
            r8 = 8
            int r5 = r5 + r8
            char r5 = r9.charAt(r5)
            int r8 = r9.bp
            int r8 = r8 + r12
            char r8 = r9.charAt(r8)
            if (r1 != r15) goto L_0x0114
            if (r4 == r15) goto L_0x011c
        L_0x0114:
            r12 = 47
            if (r1 != r12) goto L_0x0126
            r12 = 47
            if (r4 != r12) goto L_0x0126
        L_0x011c:
            r4 = r0
            r12 = r5
            r31 = r8
            r32 = 10
        L_0x0122:
            r5 = r2
            r8 = r3
            goto L_0x01db
        L_0x0126:
            if (r1 != r15) goto L_0x0143
            if (r3 != r15) goto L_0x0143
            r1 = 32
            if (r5 != r1) goto L_0x013a
            r8 = r2
            r31 = r4
            r5 = 48
            r12 = 48
            r32 = 8
        L_0x0137:
            r4 = r0
            goto L_0x01db
        L_0x013a:
            r8 = r2
            r12 = r4
            r31 = r5
            r5 = 48
            r32 = 9
            goto L_0x0137
        L_0x0143:
            r12 = 46
            if (r7 != r12) goto L_0x014b
            r12 = 46
            if (r2 == r12) goto L_0x014f
        L_0x014b:
            if (r7 != r15) goto L_0x015b
            if (r2 != r15) goto L_0x015b
        L_0x014f:
            r31 = r0
            r7 = r5
            r5 = r6
            r6 = r8
            r12 = r14
            r32 = 10
            r8 = r1
            r14 = r3
            goto L_0x01db
        L_0x015b:
            r12 = 24180(0x5e74, float:3.3883E-41)
            if (r1 == r12) goto L_0x0167
            r12 = 45380(0xb144, float:6.3591E-41)
            if (r1 != r12) goto L_0x0165
            goto L_0x0167
        L_0x0165:
            r1 = 0
            return r1
        L_0x0167:
            r1 = 26376(0x6708, float:3.696E-41)
            if (r4 == r1) goto L_0x01a4
            r1 = 50900(0xc6d4, float:7.1326E-41)
            if (r4 != r1) goto L_0x0171
            goto L_0x01a4
        L_0x0171:
            r1 = 26376(0x6708, float:3.696E-41)
            if (r3 == r1) goto L_0x017d
            r1 = 50900(0xc6d4, float:7.1326E-41)
            if (r3 != r1) goto L_0x017b
            goto L_0x017d
        L_0x017b:
            r1 = 0
            return r1
        L_0x017d:
            r1 = 26085(0x65e5, float:3.6553E-41)
            if (r5 == r1) goto L_0x019a
            r1 = 51068(0xc77c, float:7.1562E-41)
            if (r5 != r1) goto L_0x0187
            goto L_0x019a
        L_0x0187:
            r1 = 26085(0x65e5, float:3.6553E-41)
            if (r8 == r1) goto L_0x0193
            r1 = 51068(0xc77c, float:7.1562E-41)
            if (r8 != r1) goto L_0x0191
            goto L_0x0193
        L_0x0191:
            r1 = 0
            return r1
        L_0x0193:
            r8 = r2
            r12 = r4
            r31 = r5
            r5 = 48
            goto L_0x01a1
        L_0x019a:
            r8 = r2
            r31 = r4
            r5 = 48
            r12 = 48
        L_0x01a1:
            r32 = 10
            goto L_0x0137
        L_0x01a4:
            r1 = 26085(0x65e5, float:3.6553E-41)
            if (r8 == r1) goto L_0x01d2
            r1 = 51068(0xc77c, float:7.1562E-41)
            if (r8 != r1) goto L_0x01ae
            goto L_0x01d2
        L_0x01ae:
            int r1 = r9.bp
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            r4 = 26085(0x65e5, float:3.6553E-41)
            if (r1 == r4) goto L_0x01ca
            int r1 = r9.bp
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            r4 = 51068(0xc77c, float:7.1562E-41)
            if (r1 != r4) goto L_0x01c8
            goto L_0x01ca
        L_0x01c8:
            r1 = 0
            return r1
        L_0x01ca:
            r4 = r0
            r12 = r5
            r31 = r8
            r32 = 11
            goto L_0x0122
        L_0x01d2:
            r4 = r0
            r8 = r3
            r31 = r5
            r12 = 48
            r32 = 10
            r5 = r2
        L_0x01db:
            r23 = r14
            r24 = r4
            r25 = r7
            r26 = r6
            r27 = r5
            r28 = r8
            r29 = r12
            r30 = r31
            boolean r0 = checkDate(r23, r24, r25, r26, r27, r28, r29, r30)
            if (r0 != 0) goto L_0x01f3
            r0 = 0
            return r0
        L_0x01f3:
            r0 = r34
            r1 = r14
            r2 = r4
            r3 = r7
            r4 = r6
            r7 = 5
            r14 = 48
            r6 = r8
            r8 = 5
            r7 = r12
            r12 = 5
            r8 = r31
            r0.setCalendar(r1, r2, r3, r4, r5, r6, r7, r8)
            int r0 = r9.bp
            int r0 = r0 + r32
            char r7 = r9.charAt(r0)
            r0 = 84
            if (r7 == r0) goto L_0x02be
            r0 = 32
            if (r7 != r0) goto L_0x0219
            if (r35 != 0) goto L_0x0219
            goto L_0x02be
        L_0x0219:
            r0 = 34
            if (r7 == r0) goto L_0x0293
            r0 = 26
            if (r7 == r0) goto L_0x0293
            r0 = 26085(0x65e5, float:3.6553E-41)
            if (r7 == r0) goto L_0x0293
            r0 = 51068(0xc77c, float:7.1562E-41)
            if (r7 != r0) goto L_0x022b
            goto L_0x0293
        L_0x022b:
            r0 = 43
            if (r7 == r0) goto L_0x0234
            if (r7 != r15) goto L_0x0232
            goto L_0x0234
        L_0x0232:
            r0 = 0
            return r0
        L_0x0234:
            int r0 = r9.len
            int r1 = r32 + 6
            if (r0 != r1) goto L_0x0291
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 3
            char r0 = r9.charAt(r0)
            r1 = 58
            if (r0 != r1) goto L_0x028f
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            if (r0 != r14) goto L_0x028f
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + r12
            char r0 = r9.charAt(r0)
            if (r0 == r14) goto L_0x0260
            goto L_0x028f
        L_0x0260:
            r1 = 48
            r2 = 48
            r3 = 48
            r4 = 48
            r5 = 48
            r6 = 48
            r0 = r34
            r0.setTime(r1, r2, r3, r4, r5, r6)
            java.util.Calendar r0 = r9.calendar
            r1 = 0
            r0.set(r13, r1)
            int r0 = r9.bp
            int r0 = r0 + r32
            r1 = 1
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            int r2 = r9.bp
            int r2 = r2 + r32
            r8 = 2
            int r2 = r2 + r8
            char r2 = r9.charAt(r2)
            r9.setTimeZone(r7, r0, r2)
            return r1
        L_0x028f:
            r0 = 0
            return r0
        L_0x0291:
            r0 = 0
            return r0
        L_0x0293:
            r0 = 0
            java.util.Calendar r1 = r9.calendar
            r2 = 11
            r1.set(r2, r0)
            java.util.Calendar r1 = r9.calendar
            r2 = 12
            r1.set(r2, r0)
            java.util.Calendar r1 = r9.calendar
            r2 = 13
            r1.set(r2, r0)
            java.util.Calendar r1 = r9.calendar
            r1.set(r13, r0)
            int r0 = r9.bp
            int r0 = r0 + r32
            r9.bp = r0
            char r0 = r9.charAt(r0)
            r9.ch = r0
            r9.token = r12
            r0 = 1
            return r0
        L_0x02be:
            r8 = 2
            int r7 = r32 + 9
            if (r11 >= r7) goto L_0x02c5
            r0 = 0
            return r0
        L_0x02c5:
            r0 = 0
            int r1 = r9.bp
            int r1 = r1 + r32
            int r1 = r1 + 3
            char r1 = r9.charAt(r1)
            r2 = 58
            if (r1 == r2) goto L_0x02d5
            return r0
        L_0x02d5:
            int r1 = r9.bp
            int r1 = r1 + r32
            int r1 = r1 + 6
            char r1 = r9.charAt(r1)
            if (r1 == r2) goto L_0x02e2
            return r0
        L_0x02e2:
            int r0 = r9.bp
            int r0 = r0 + r32
            r1 = 1
            int r0 = r0 + r1
            char r10 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + r8
            char r20 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 4
            char r21 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + r12
            char r22 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 7
            char r23 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r32
            r1 = 8
            int r0 = r0 + r1
            char r18 = r9.charAt(r0)
            r0 = r34
            r1 = r10
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r18
            boolean r0 = r0.checkTime(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x0332
            r0 = 0
            return r0
        L_0x0332:
            r0 = r34
            r1 = r10
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r18
            r0.setTime(r1, r2, r3, r4, r5, r6)
            int r0 = r9.bp
            int r0 = r0 + r32
            r1 = 9
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            r1 = 46
            if (r0 != r1) goto L_0x0491
            int r0 = r32 + 11
            if (r11 >= r0) goto L_0x0357
            r1 = 0
            return r1
        L_0x0357:
            int r1 = r9.bp
            int r1 = r1 + r32
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            if (r1 < r14) goto L_0x048f
            r2 = 57
            if (r1 <= r2) goto L_0x0369
            goto L_0x048f
        L_0x0369:
            int r1 = r1 - r14
            if (r11 <= r0) goto L_0x0381
            int r0 = r9.bp
            int r0 = r0 + r32
            r3 = 11
            int r0 = r0 + r3
            char r0 = r9.charAt(r0)
            if (r0 < r14) goto L_0x0381
            if (r0 > r2) goto L_0x0381
            int r1 = r1 * 10
            int r0 = r0 - r14
            int r1 = r1 + r0
            r0 = 2
            goto L_0x0382
        L_0x0381:
            r0 = 1
        L_0x0382:
            if (r0 != r8) goto L_0x039a
            int r2 = r9.bp
            int r2 = r2 + r32
            int r2 = r2 + 12
            char r2 = r9.charAt(r2)
            if (r2 < r14) goto L_0x039a
            r3 = 57
            if (r2 > r3) goto L_0x039a
            int r1 = r1 * 10
            int r2 = r2 - r14
            int r1 = r1 + r2
            r6 = 3
            goto L_0x039b
        L_0x039a:
            r6 = r0
        L_0x039b:
            java.util.Calendar r0 = r9.calendar
            r0.set(r13, r1)
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 10
            int r0 = r0 + r6
            char r1 = r9.charAt(r0)
            r0 = 43
            if (r1 == r0) goto L_0x03db
            if (r1 != r15) goto L_0x03b2
            goto L_0x03db
        L_0x03b2:
            r0 = 90
            if (r1 != r0) goto L_0x03d8
            java.util.Calendar r0 = r9.calendar
            java.util.TimeZone r0 = r0.getTimeZone()
            int r0 = r0.getRawOffset()
            if (r0 == 0) goto L_0x03d5
            r0 = 0
            java.lang.String[] r1 = java.util.TimeZone.getAvailableIDs(r0)
            int r2 = r1.length
            if (r2 <= 0) goto L_0x03d5
            r1 = r1[r0]
            java.util.TimeZone r0 = java.util.TimeZone.getTimeZone(r1)
            java.util.Calendar r1 = r9.calendar
            r1.setTimeZone(r0)
        L_0x03d5:
            r13 = 1
            goto L_0x0463
        L_0x03d8:
            r13 = 0
            goto L_0x0463
        L_0x03db:
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 10
            int r0 = r0 + r6
            r2 = 1
            int r0 = r0 + r2
            char r2 = r9.charAt(r0)
            if (r2 < r14) goto L_0x048d
            r0 = 49
            if (r2 <= r0) goto L_0x03f0
            goto L_0x048d
        L_0x03f0:
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 10
            int r0 = r0 + r6
            int r0 = r0 + r8
            char r3 = r9.charAt(r0)
            if (r3 < r14) goto L_0x048b
            r0 = 57
            if (r3 <= r0) goto L_0x0404
            goto L_0x048b
        L_0x0404:
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 10
            int r0 = r0 + r6
            int r0 = r0 + 3
            char r0 = r9.charAt(r0)
            r4 = 58
            if (r0 != r4) goto L_0x043d
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 10
            int r0 = r0 + r6
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            if (r0 == r14) goto L_0x042a
            r4 = 51
            if (r0 == r4) goto L_0x042a
            r4 = 0
            return r4
        L_0x042a:
            r4 = 0
            int r5 = r9.bp
            int r5 = r5 + r32
            int r5 = r5 + 10
            int r5 = r5 + r6
            int r5 = r5 + r12
            char r5 = r9.charAt(r5)
            if (r5 == r14) goto L_0x043a
            return r4
        L_0x043a:
            r4 = r0
            r13 = 6
            goto L_0x045e
        L_0x043d:
            if (r0 != r14) goto L_0x0459
            int r0 = r9.bp
            int r0 = r0 + r32
            int r0 = r0 + 10
            int r0 = r0 + r6
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            if (r0 == r14) goto L_0x0454
            r4 = 51
            if (r0 == r4) goto L_0x0454
            r4 = 0
            return r4
        L_0x0454:
            r4 = r0
            r5 = 48
            r13 = 5
            goto L_0x045e
        L_0x0459:
            r4 = 48
            r5 = 48
            r13 = 3
        L_0x045e:
            r0 = r34
            r0.setTimeZone(r1, r2, r3, r4, r5)
        L_0x0463:
            int r0 = r9.bp
            int r32 = r32 + 10
            int r32 = r32 + r6
            int r32 = r32 + r13
            int r0 = r0 + r32
            char r0 = r9.charAt(r0)
            r1 = 26
            if (r0 == r1) goto L_0x047b
            r1 = 34
            if (r0 == r1) goto L_0x047b
            r1 = 0
            return r1
        L_0x047b:
            int r0 = r9.bp
            int r0 = r0 + r32
            r9.bp = r0
            char r0 = r9.charAt(r0)
            r9.ch = r0
            r9.token = r12
            r0 = 1
            return r0
        L_0x048b:
            r1 = 0
            return r1
        L_0x048d:
            r1 = 0
            return r1
        L_0x048f:
            r1 = 0
            return r1
        L_0x0491:
            r1 = 0
            java.util.Calendar r2 = r9.calendar
            r2.set(r13, r1)
            int r1 = r9.bp
            int r1 = r1 + r7
            r9.bp = r1
            char r1 = r9.charAt(r1)
            r9.ch = r1
            r9.token = r12
            r1 = 90
            if (r0 != r1) goto L_0x04c7
            java.util.Calendar r0 = r9.calendar
            java.util.TimeZone r0 = r0.getTimeZone()
            int r0 = r0.getRawOffset()
            if (r0 == 0) goto L_0x04c7
            r0 = 0
            java.lang.String[] r1 = java.util.TimeZone.getAvailableIDs(r0)
            int r2 = r1.length
            if (r2 <= 0) goto L_0x04c7
            r0 = r1[r0]
            java.util.TimeZone r0 = java.util.TimeZone.getTimeZone(r0)
            java.util.Calendar r1 = r9.calendar
            r1.setTimeZone(r0)
        L_0x04c7:
            r0 = 1
            return r0
        L_0x04c9:
            r8 = 48
            r12 = 5
            if (r35 == 0) goto L_0x04d0
            r5 = 0
            return r5
        L_0x04d0:
            int r5 = r9.bp
            r10 = 8
            int r5 = r5 + r10
            char r10 = r9.charAt(r5)
            if (r1 != r15) goto L_0x04df
            if (r4 != r15) goto L_0x04df
            r5 = 1
            goto L_0x04e0
        L_0x04df:
            r5 = 0
        L_0x04e0:
            if (r5 == 0) goto L_0x04e8
            r15 = 16
            if (r11 != r15) goto L_0x04e8
            r15 = 1
            goto L_0x04e9
        L_0x04e8:
            r15 = 0
        L_0x04e9:
            if (r5 == 0) goto L_0x04f2
            r5 = 17
            if (r11 != r5) goto L_0x04f2
            r16 = 1
            goto L_0x04f4
        L_0x04f2:
            r16 = 0
        L_0x04f4:
            if (r16 != 0) goto L_0x0502
            if (r15 == 0) goto L_0x04f9
            goto L_0x0502
        L_0x04f9:
            r17 = r1
            r20 = r2
            r21 = r3
            r22 = r4
            goto L_0x0513
        L_0x0502:
            int r1 = r9.bp
            r4 = 9
            int r1 = r1 + r4
            char r1 = r9.charAt(r1)
            r22 = r1
            r17 = r2
            r20 = r3
            r21 = r10
        L_0x0513:
            r1 = r14
            r2 = r0
            r3 = r7
            r4 = r6
            r5 = r17
            r23 = r6
            r6 = r20
            r24 = r7
            r7 = r21
            r12 = 48
            r8 = r22
            boolean r1 = checkDate(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r1 != 0) goto L_0x052d
            r1 = 0
            return r1
        L_0x052d:
            r2 = r0
            r0 = r34
            r1 = r14
            r3 = r24
            r4 = r23
            r5 = r17
            r6 = r20
            r7 = r21
            r8 = r22
            r0.setCalendar(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 8
            if (r11 == r0) goto L_0x0619
            int r0 = r9.bp
            r1 = 9
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            int r2 = r9.bp
            r3 = 11
            int r2 = r2 + r3
            char r2 = r9.charAt(r2)
            int r3 = r9.bp
            int r3 = r3 + 12
            char r3 = r9.charAt(r3)
            int r4 = r9.bp
            int r4 = r4 + 13
            char r4 = r9.charAt(r4)
            if (r16 == 0) goto L_0x0584
            r5 = 84
            if (r1 != r5) goto L_0x0584
            r5 = 58
            if (r4 != r5) goto L_0x0584
            int r5 = r9.bp
            int r5 = r5 + 16
            char r5 = r9.charAt(r5)
            r6 = 90
            if (r5 == r6) goto L_0x0592
        L_0x0584:
            if (r15 == 0) goto L_0x05aa
            r5 = 32
            if (r1 == r5) goto L_0x058e
            r5 = 84
            if (r1 != r5) goto L_0x05aa
        L_0x058e:
            r5 = 58
            if (r4 != r5) goto L_0x05aa
        L_0x0592:
            int r0 = r9.bp
            int r0 = r0 + r13
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            int r1 = r1 + 15
            char r1 = r9.charAt(r1)
            r8 = r0
            r14 = r1
            r10 = r2
            r7 = r3
            r15 = 48
            r17 = 48
            goto L_0x05b0
        L_0x05aa:
            r7 = r0
            r8 = r1
            r14 = r2
            r15 = r3
            r17 = r4
        L_0x05b0:
            r0 = r34
            r1 = r10
            r2 = r7
            r3 = r8
            r4 = r14
            r5 = r15
            r6 = r17
            boolean r0 = r0.checkTime(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x05c1
            r0 = 0
            return r0
        L_0x05c1:
            r0 = 17
            if (r11 != r0) goto L_0x05ff
            if (r16 != 0) goto L_0x05ff
            int r0 = r9.bp
            int r0 = r0 + r13
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            int r1 = r1 + 15
            char r1 = r9.charAt(r1)
            int r2 = r9.bp
            int r2 = r2 + 16
            char r2 = r9.charAt(r2)
            if (r0 < r12) goto L_0x05fd
            r3 = 57
            if (r0 <= r3) goto L_0x05e5
            goto L_0x05fd
        L_0x05e5:
            if (r1 < r12) goto L_0x05fb
            if (r1 <= r3) goto L_0x05ea
            goto L_0x05fb
        L_0x05ea:
            if (r2 < r12) goto L_0x05f9
            if (r2 <= r3) goto L_0x05ef
            goto L_0x05f9
        L_0x05ef:
            int r0 = r0 - r12
            int r0 = r0 * 100
            int r1 = r1 - r12
            int r1 = r1 * 10
            int r0 = r0 + r1
            int r2 = r2 - r12
            int r0 = r0 + r2
            goto L_0x0600
        L_0x05f9:
            r0 = 0
            return r0
        L_0x05fb:
            r0 = 0
            return r0
        L_0x05fd:
            r0 = 0
            return r0
        L_0x05ff:
            r0 = 0
        L_0x0600:
            int r10 = r10 - r12
            int r10 = r10 * 10
            int r7 = r7 - r12
            int r1 = r10 + r7
            int r8 = r8 - r12
            int r8 = r8 * 10
            int r14 = r14 - r12
            int r2 = r8 + r14
            int r15 = r15 - r12
            int r15 = r15 * 10
            int r17 = r17 + -48
            int r3 = r15 + r17
            r33 = r1
            r1 = r0
            r0 = r33
            goto L_0x061d
        L_0x0619:
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x061d:
            java.util.Calendar r4 = r9.calendar
            r5 = 11
            r4.set(r5, r0)
            java.util.Calendar r0 = r9.calendar
            r4 = 12
            r0.set(r4, r2)
            java.util.Calendar r0 = r9.calendar
            r2 = 13
            r0.set(r2, r3)
            java.util.Calendar r0 = r9.calendar
            r0.set(r13, r1)
            r0 = 5
            r9.token = r0
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    /* access modifiers changed from: protected */
    public void setTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        this.calendar.set(11, ((c2 - '0') * 10) + (c3 - '0'));
        this.calendar.set(12, ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(13, ((c6 - '0') * 10) + (c7 - '0'));
    }

    /* access modifiers changed from: protected */
    public void setTimeZone(char c2, char c3, char c4) {
        setTimeZone(c2, c3, c4, '0', '0');
    }

    /* access modifiers changed from: protected */
    public void setTimeZone(char c2, char c3, char c4, char c5, char c6) {
        int i = ((((c3 - '0') * 10) + (c4 - '0')) * 3600 * 1000) + ((((c5 - '0') * 10) + (c6 - '0')) * 60 * 1000);
        if (c2 == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    private void setCalendar(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(2, (((c6 - '0') * 10) + (c7 - '0')) - 1);
        this.calendar.set(5, ((c8 - '0') * 10) + (c9 - '0'));
    }

    public boolean isEOF() {
        if (this.bp != this.len) {
            return this.ch == 26 && this.bp + 1 == this.len;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scanFieldInt(char[] r14) {
        /*
            r13 = this;
            r0 = 0
            r13.matchStat = r0
            int r1 = r13.bp
            char r2 = r13.ch
            java.lang.String r3 = r13.text
            int r4 = r13.bp
            boolean r3 = charArrayCompare(r3, r4, r14)
            if (r3 != 0) goto L_0x0015
            r14 = -2
            r13.matchStat = r14
            return r0
        L_0x0015:
            int r3 = r13.bp
            int r14 = r14.length
            int r3 = r3 + r14
            int r14 = r3 + 1
            char r3 = r13.charAt(r3)
            r4 = 34
            r5 = 1
            if (r3 != r4) goto L_0x0026
            r6 = 1
            goto L_0x0027
        L_0x0026:
            r6 = 0
        L_0x0027:
            if (r6 == 0) goto L_0x0030
            int r3 = r14 + 1
            char r14 = r13.charAt(r14)
            goto L_0x0033
        L_0x0030:
            r12 = r3
            r3 = r14
            r14 = r12
        L_0x0033:
            r7 = 45
            if (r14 != r7) goto L_0x0039
            r7 = 1
            goto L_0x003a
        L_0x0039:
            r7 = 0
        L_0x003a:
            if (r7 == 0) goto L_0x0045
            int r14 = r3 + 1
            char r3 = r13.charAt(r3)
            r12 = r3
            r3 = r14
            r14 = r12
        L_0x0045:
            r8 = 48
            r9 = -1
            if (r14 < r8) goto L_0x0119
            r10 = 57
            if (r14 > r10) goto L_0x0119
            int r14 = r14 - r8
        L_0x004f:
            int r11 = r3 + 1
            char r3 = r13.charAt(r3)
            if (r3 < r8) goto L_0x0060
            if (r3 > r10) goto L_0x0060
            int r14 = r14 * 10
            int r3 = r3 + -48
            int r14 = r14 + r3
            r3 = r11
            goto L_0x004f
        L_0x0060:
            r8 = 46
            if (r3 != r8) goto L_0x0067
            r13.matchStat = r9
            return r0
        L_0x0067:
            if (r14 >= 0) goto L_0x006c
            r13.matchStat = r9
            return r0
        L_0x006c:
            if (r6 == 0) goto L_0x007b
            if (r3 == r4) goto L_0x0073
            r13.matchStat = r9
            return r0
        L_0x0073:
            int r3 = r11 + 1
            char r4 = r13.charAt(r11)
        L_0x0079:
            r11 = r3
            r3 = r4
        L_0x007b:
            r4 = 125(0x7d, float:1.75E-43)
            r6 = 44
            if (r3 == r6) goto L_0x0094
            if (r3 != r4) goto L_0x0084
            goto L_0x0094
        L_0x0084:
            boolean r3 = isWhitespace(r3)
            if (r3 == 0) goto L_0x0091
            int r3 = r11 + 1
            char r4 = r13.charAt(r11)
            goto L_0x0079
        L_0x0091:
            r13.matchStat = r9
            return r0
        L_0x0094:
            int r11 = r11 - r5
            r13.bp = r11
            r8 = 16
            if (r3 != r6) goto L_0x00af
            int r0 = r13.bp
            int r0 = r0 + r5
            r13.bp = r0
            char r0 = r13.charAt(r0)
            r13.ch = r0
            r0 = 3
            r13.matchStat = r0
            r13.token = r8
            if (r7 == 0) goto L_0x00ae
            int r14 = -r14
        L_0x00ae:
            return r14
        L_0x00af:
            if (r3 != r4) goto L_0x0115
            r13.bp = r11
            int r3 = r13.bp
            int r3 = r3 + r5
            r13.bp = r3
            char r3 = r13.charAt(r3)
        L_0x00bc:
            if (r3 != r6) goto L_0x00cc
            r13.token = r8
            int r0 = r13.bp
            int r0 = r0 + r5
            r13.bp = r0
            char r0 = r13.charAt(r0)
            r13.ch = r0
            goto L_0x00fa
        L_0x00cc:
            r10 = 93
            if (r3 != r10) goto L_0x00e0
            r0 = 15
            r13.token = r0
            int r0 = r13.bp
            int r0 = r0 + r5
            r13.bp = r0
            char r0 = r13.charAt(r0)
            r13.ch = r0
            goto L_0x00fa
        L_0x00e0:
            if (r3 != r4) goto L_0x00f2
            r0 = 13
            r13.token = r0
            int r0 = r13.bp
            int r0 = r0 + r5
            r13.bp = r0
            char r0 = r13.charAt(r0)
            r13.ch = r0
            goto L_0x00fa
        L_0x00f2:
            r10 = 26
            if (r3 != r10) goto L_0x00fe
            r0 = 20
            r13.token = r0
        L_0x00fa:
            r0 = 4
            r13.matchStat = r0
            goto L_0x0115
        L_0x00fe:
            boolean r3 = isWhitespace(r3)
            if (r3 == 0) goto L_0x010e
            int r3 = r13.bp
            int r3 = r3 + r5
            r13.bp = r3
            char r3 = r13.charAt(r3)
            goto L_0x00bc
        L_0x010e:
            r13.bp = r1
            r13.ch = r2
            r13.matchStat = r9
            return r0
        L_0x0115:
            if (r7 == 0) goto L_0x0118
            int r14 = -r14
        L_0x0118:
            return r14
        L_0x0119:
            r13.matchStat = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldInt(char[]):int");
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c2 = this.ch;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int indexOf = indexOf('\"', i2);
        if (indexOf != -1) {
            String subString = subString(i2, indexOf - i2);
            if (subString.indexOf(92) != -1) {
                while (true) {
                    int i3 = indexOf - 1;
                    int i4 = 0;
                    while (i3 >= 0 && charAt(i3) == '\\') {
                        i4++;
                        i3--;
                    }
                    if (i4 % 2 == 0) {
                        break;
                    }
                    indexOf = indexOf('\"', indexOf + 1);
                }
                int length2 = indexOf - ((this.bp + cArr.length) + 1);
                subString = readString(sub_chars(this.bp + cArr.length + 1, length2), length2);
            }
            char charAt = charAt(indexOf + 1);
            while (charAt != ',' && charAt != '}') {
                if (isWhitespace(charAt)) {
                    indexOf++;
                    charAt = charAt(indexOf + 1);
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
            }
            this.bp = indexOf + 1;
            this.ch = charAt;
            if (charAt == ',') {
                int i5 = this.bp + 1;
                this.bp = i5;
                this.ch = charAt(i5);
                this.matchStat = 3;
                return subString;
            }
            int i6 = this.bp + 1;
            this.bp = i6;
            char charAt2 = charAt(i6);
            if (charAt2 == ',') {
                this.token = 16;
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = charAt(i7);
            } else if (charAt2 == ']') {
                this.token = 15;
                int i8 = this.bp + 1;
                this.bp = i8;
                this.ch = charAt(i8);
            } else if (charAt2 == '}') {
                this.token = 13;
                int i9 = this.bp + 1;
                this.bp = i9;
                this.ch = charAt(i9);
            } else if (charAt2 == 26) {
                this.token = 20;
            } else {
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.matchStat = 4;
            return subString;
        }
        throw new JSONException("unclosed str");
    }

    public Date scanFieldDate(char[] cArr) {
        Date date;
        char c2;
        long j;
        int i;
        char[] cArr2 = cArr;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        char c3 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr2)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.bp + cArr2.length;
        int i3 = length + 1;
        char charAt = charAt(length);
        if (charAt == '\"') {
            int indexOf = indexOf('\"', i3);
            if (indexOf != -1) {
                this.bp = i3;
                if (scanISO8601DateIfMatch(false, indexOf - i3)) {
                    Date time = this.calendar.getTime();
                    char charAt2 = charAt(indexOf + 1);
                    this.bp = i2;
                    while (charAt2 != ',' && charAt2 != '}') {
                        if (isWhitespace(charAt2)) {
                            indexOf++;
                            charAt2 = charAt(indexOf + 1);
                        } else {
                            this.matchStat = -1;
                            return null;
                        }
                    }
                    this.bp = indexOf + 1;
                    this.ch = charAt2;
                    char c4 = charAt2;
                    date = time;
                    c2 = c4;
                } else {
                    this.bp = i2;
                    this.matchStat = -1;
                    return null;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            char c5 = '9';
            char c6 = '0';
            if (charAt == '-' || (charAt >= '0' && charAt <= '9')) {
                if (charAt == '-') {
                    charAt = charAt(i3);
                    i3++;
                    z = true;
                }
                if (charAt < '0' || charAt > '9') {
                    c2 = charAt;
                    j = 0;
                } else {
                    j = (long) (charAt - '0');
                    while (true) {
                        i = i3 + 1;
                        c2 = charAt(i3);
                        if (c2 >= c6 && c2 <= c5) {
                            j = (j * 10) + ((long) (c2 - '0'));
                            i3 = i;
                            c5 = '9';
                            c6 = '0';
                        } else if (c2 == ',' || c2 == '}') {
                            this.bp = i - 1;
                        }
                    }
                    this.bp = i - 1;
                }
                if (j < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                date = new Date(j);
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (c2 == ',') {
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i5 = this.bp + 1;
        this.bp = i5;
        char charAt3 = charAt(i5);
        if (charAt3 == ',') {
            this.token = 16;
            int i6 = this.bp + 1;
            this.bp = i6;
            this.ch = charAt(i6);
        } else if (charAt3 == ']') {
            this.token = 15;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (charAt3 == '}') {
            this.token = 13;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (charAt3 == 26) {
            this.token = 20;
        } else {
            this.bp = i2;
            this.ch = c3;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return 0;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char charAt = charAt(i);
            if (charAt == '\"') {
                this.bp = i2;
                char charAt2 = charAt(this.bp);
                this.ch = charAt2;
                while (charAt2 != ',') {
                    if (charAt2 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            this.ch = charAt(i3);
                        } else if (current == ']') {
                            this.token = 15;
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            this.ch = charAt(i4);
                        } else if (current == '}') {
                            this.token = 13;
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = charAt(i5);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0;
                        }
                        this.matchStat = 4;
                        return j;
                    } else if (isWhitespace(charAt2)) {
                        int i6 = this.bp + 1;
                        this.bp = i6;
                        charAt2 = charAt(i6);
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                }
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 3;
                return j;
            } else if (i2 > this.len) {
                this.matchStat = -1;
                return 0;
            } else {
                j = (j ^ ((long) charAt)) * 1099511628211L;
                i = i2;
            }
        }
    }

    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        char c2;
        int i;
        boolean z;
        int i2;
        char c3;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr2)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> newCollectionByType = newCollectionByType(cls);
        int length = this.bp + cArr2.length;
        int i3 = length + 1;
        if (charAt(length) == '[') {
            int i4 = i3 + 1;
            char charAt = charAt(i3);
            while (true) {
                if (charAt == '\"') {
                    int indexOf = indexOf('\"', i4);
                    if (indexOf != -1) {
                        String subString = subString(i4, indexOf - i4);
                        if (subString.indexOf(92) != -1) {
                            while (true) {
                                int i5 = indexOf - 1;
                                int i6 = 0;
                                while (i5 >= 0 && charAt(i5) == '\\') {
                                    i6++;
                                    i5--;
                                }
                                if (i6 % 2 == 0) {
                                    break;
                                }
                                indexOf = indexOf('\"', indexOf + 1);
                            }
                            int i7 = indexOf - i4;
                            subString = readString(sub_chars(i4, i7), i7);
                        }
                        int i8 = indexOf + 1;
                        i2 = i8 + 1;
                        c3 = charAt(i8);
                        newCollectionByType.add(subString);
                    } else {
                        throw new JSONException("unclosed str");
                    }
                } else if (charAt == 'n' && this.text.startsWith("ull", i4)) {
                    int i9 = i4 + 3;
                    int i10 = i9 + 1;
                    char charAt2 = charAt(i9);
                    newCollectionByType.add((Object) null);
                    char c4 = charAt2;
                    i2 = i10;
                    c3 = c4;
                } else if (charAt == ']' && newCollectionByType.size() == 0) {
                    i = i4 + 1;
                    c2 = charAt(i4);
                } else {
                    this.matchStat = -1;
                    return null;
                }
                if (c3 == ',') {
                    char charAt3 = charAt(i2);
                    i4 = i2 + 1;
                    charAt = charAt3;
                } else if (c3 == ']') {
                    i = i2 + 1;
                    c2 = charAt(i2);
                    while (isWhitespace(c2)) {
                        c2 = charAt(i);
                        i++;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        } else if (this.text.startsWith("ull", i3)) {
            int i11 = i3 + 3;
            c2 = charAt(i11);
            i = i11 + 1;
            newCollectionByType = null;
        } else {
            this.matchStat = -1;
            return null;
        }
        this.bp = i;
        if (c2 == ',') {
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return newCollectionByType;
        } else if (c2 == '}') {
            char charAt4 = charAt(this.bp);
            do {
                if (charAt4 == ',') {
                    this.token = 16;
                    int i12 = this.bp + 1;
                    this.bp = i12;
                    this.ch = charAt(i12);
                } else if (charAt4 == ']') {
                    this.token = 15;
                    int i13 = this.bp + 1;
                    this.bp = i13;
                    this.ch = charAt(i13);
                } else if (charAt4 == '}') {
                    this.token = 13;
                    int i14 = this.bp + 1;
                    this.bp = i14;
                    this.ch = charAt(i14);
                } else if (charAt4 == 26) {
                    this.token = 20;
                    this.ch = charAt4;
                } else {
                    z = false;
                    while (isWhitespace(charAt4)) {
                        int i15 = i + 1;
                        char charAt5 = charAt(i);
                        this.bp = i15;
                        z = true;
                        int i16 = i15;
                        charAt4 = charAt5;
                        i = i16;
                    }
                }
                this.matchStat = 4;
                return newCollectionByType;
            } while (z);
            this.matchStat = -1;
            return null;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public long scanFieldLong(char[] cArr) {
        int i;
        char c2;
        boolean z;
        int i2;
        char charAt;
        int i3;
        char c3;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        int i4 = this.bp;
        char c4 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr2)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.bp + cArr2.length;
        int i5 = length + 1;
        char charAt2 = charAt(length);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            i = i5 + 1;
            c2 = charAt(i5);
        } else {
            char c5 = charAt2;
            i = i5;
            c2 = c5;
        }
        if (c2 == '-') {
            z = true;
            char charAt3 = charAt(i);
            i++;
            c2 = charAt3;
        } else {
            z = false;
        }
        if (c2 >= '0') {
            char c6 = '9';
            if (c2 <= '9') {
                int i6 = i4;
                long j = (long) (c2 - '0');
                while (true) {
                    i2 = i + 1;
                    charAt = charAt(i);
                    if (charAt >= '0' && charAt <= c6) {
                        j = (j * 10) + ((long) (charAt - '0'));
                        i = i2;
                        c6 = '9';
                    }
                }
                if (charAt == '.') {
                    this.matchStat = -1;
                    return 0;
                }
                if (!z2) {
                    char c7 = charAt;
                    i3 = i2;
                    c3 = c7;
                } else if (charAt != '\"') {
                    this.matchStat = -1;
                    return 0;
                } else {
                    i3 = i2 + 1;
                    c3 = charAt(i2);
                }
                if (c3 == ',' || c3 == '}') {
                    this.bp = i3 - 1;
                }
                if (!(j >= 0 || (j == Long.MIN_VALUE && z))) {
                    this.bp = i6;
                    this.ch = c4;
                    this.matchStat = -1;
                    return 0;
                }
                int i7 = i6;
                while (c3 != ',') {
                    if (c3 == '}') {
                        int i8 = this.bp + 1;
                        this.bp = i8;
                        char charAt4 = charAt(i8);
                        while (true) {
                            if (charAt4 == ',') {
                                this.token = 16;
                                int i9 = this.bp + 1;
                                this.bp = i9;
                                this.ch = charAt(i9);
                                break;
                            } else if (charAt4 == ']') {
                                this.token = 15;
                                int i10 = this.bp + 1;
                                this.bp = i10;
                                this.ch = charAt(i10);
                                break;
                            } else if (charAt4 == '}') {
                                this.token = 13;
                                int i11 = this.bp + 1;
                                this.bp = i11;
                                this.ch = charAt(i11);
                                break;
                            } else if (charAt4 == 26) {
                                this.token = 20;
                                break;
                            } else if (isWhitespace(charAt4)) {
                                int i12 = this.bp + 1;
                                this.bp = i12;
                                charAt4 = charAt(i12);
                            } else {
                                this.bp = i7;
                                this.ch = c4;
                                this.matchStat = -1;
                                return 0;
                            }
                        }
                        this.matchStat = 4;
                        return z ? -j : j;
                    } else if (isWhitespace(c3)) {
                        this.bp = i3;
                        char charAt5 = charAt(i3);
                        i3++;
                        c3 = charAt5;
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                }
                int i13 = this.bp + 1;
                this.bp = i13;
                this.ch = charAt(i13);
                this.matchStat = 3;
                this.token = 16;
                return z ? -j : j;
            }
        }
        this.bp = i4;
        this.ch = c4;
        this.matchStat = -1;
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x00fe A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanFieldBoolean(char[] r11) {
        /*
            r10 = this;
            r0 = 0
            r10.matchStat = r0
            java.lang.String r1 = r10.text
            int r2 = r10.bp
            boolean r1 = charArrayCompare(r1, r2, r11)
            if (r1 != 0) goto L_0x0011
            r11 = -2
            r10.matchStat = r11
            return r0
        L_0x0011:
            int r1 = r10.bp
            int r2 = r10.bp
            int r11 = r11.length
            int r2 = r2 + r11
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            r3 = 34
            r4 = 1
            if (r2 != r3) goto L_0x0024
            r5 = 1
            goto L_0x0025
        L_0x0024:
            r5 = 0
        L_0x0025:
            if (r5 == 0) goto L_0x002e
            int r2 = r11 + 1
            char r11 = r10.charAt(r11)
            goto L_0x0031
        L_0x002e:
            r9 = r2
            r2 = r11
            r11 = r9
        L_0x0031:
            r6 = 116(0x74, float:1.63E-43)
            r7 = 101(0x65, float:1.42E-43)
            r8 = -1
            if (r11 != r6) goto L_0x0074
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            r6 = 114(0x72, float:1.6E-43)
            if (r2 == r6) goto L_0x0045
            r10.matchStat = r8
            return r0
        L_0x0045:
            int r2 = r11 + 1
            char r11 = r10.charAt(r11)
            r6 = 117(0x75, float:1.64E-43)
            if (r11 == r6) goto L_0x0052
            r10.matchStat = r8
            return r0
        L_0x0052:
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            if (r2 == r7) goto L_0x005d
            r10.matchStat = r8
            return r0
        L_0x005d:
            if (r5 == 0) goto L_0x006b
            int r2 = r11 + 1
            char r11 = r10.charAt(r11)
            if (r11 == r3) goto L_0x006a
            r10.matchStat = r8
            return r0
        L_0x006a:
            r11 = r2
        L_0x006b:
            r10.bp = r11
            int r11 = r10.bp
            char r11 = r10.charAt(r11)
            goto L_0x00db
        L_0x0074:
            r6 = 102(0x66, float:1.43E-43)
            if (r11 != r6) goto L_0x00c1
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            r6 = 97
            if (r2 == r6) goto L_0x0085
            r10.matchStat = r8
            return r0
        L_0x0085:
            int r2 = r11 + 1
            char r11 = r10.charAt(r11)
            r6 = 108(0x6c, float:1.51E-43)
            if (r11 == r6) goto L_0x0092
            r10.matchStat = r8
            return r0
        L_0x0092:
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            r6 = 115(0x73, float:1.61E-43)
            if (r2 == r6) goto L_0x009f
            r10.matchStat = r8
            return r0
        L_0x009f:
            int r2 = r11 + 1
            char r11 = r10.charAt(r11)
            if (r11 == r7) goto L_0x00aa
            r10.matchStat = r8
            return r0
        L_0x00aa:
            if (r5 == 0) goto L_0x00b7
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            if (r2 == r3) goto L_0x00b8
            r10.matchStat = r8
            return r0
        L_0x00b7:
            r11 = r2
        L_0x00b8:
            r10.bp = r11
            int r11 = r10.bp
            char r11 = r10.charAt(r11)
            goto L_0x00f7
        L_0x00c1:
            r6 = 49
            if (r11 != r6) goto L_0x00dd
            if (r5 == 0) goto L_0x00d2
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            if (r2 == r3) goto L_0x00d3
            r10.matchStat = r8
            return r0
        L_0x00d2:
            r11 = r2
        L_0x00d3:
            r10.bp = r11
            int r11 = r10.bp
            char r11 = r10.charAt(r11)
        L_0x00db:
            r2 = 1
            goto L_0x00f8
        L_0x00dd:
            r6 = 48
            if (r11 != r6) goto L_0x018c
            if (r5 == 0) goto L_0x00ee
            int r11 = r2 + 1
            char r2 = r10.charAt(r2)
            if (r2 == r3) goto L_0x00ef
            r10.matchStat = r8
            return r0
        L_0x00ee:
            r11 = r2
        L_0x00ef:
            r10.bp = r11
            int r11 = r10.bp
            char r11 = r10.charAt(r11)
        L_0x00f7:
            r2 = 0
        L_0x00f8:
            r3 = 16
            r5 = 44
            if (r11 != r5) goto L_0x010f
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
            r10.ch = r11
            r11 = 3
            r10.matchStat = r11
            r10.token = r3
            goto L_0x015d
        L_0x010f:
            r6 = 125(0x7d, float:1.75E-43)
            if (r11 != r6) goto L_0x0171
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
        L_0x011c:
            if (r11 != r5) goto L_0x012c
            r10.token = r3
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
            r10.ch = r11
            goto L_0x015a
        L_0x012c:
            r1 = 93
            if (r11 != r1) goto L_0x0140
            r11 = 15
            r10.token = r11
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
            r10.ch = r11
            goto L_0x015a
        L_0x0140:
            if (r11 != r6) goto L_0x0152
            r11 = 13
            r10.token = r11
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
            r10.ch = r11
            goto L_0x015a
        L_0x0152:
            r1 = 26
            if (r11 != r1) goto L_0x015e
            r11 = 20
            r10.token = r11
        L_0x015a:
            r11 = 4
            r10.matchStat = r11
        L_0x015d:
            return r2
        L_0x015e:
            boolean r11 = isWhitespace(r11)
            if (r11 == 0) goto L_0x016e
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
            goto L_0x011c
        L_0x016e:
            r10.matchStat = r8
            return r0
        L_0x0171:
            boolean r11 = isWhitespace(r11)
            if (r11 == 0) goto L_0x0182
            int r11 = r10.bp
            int r11 = r11 + r4
            r10.bp = r11
            char r11 = r10.charAt(r11)
            goto L_0x00f8
        L_0x0182:
            r10.bp = r1
            int r11 = r10.bp
            r10.charAt(r11)
            r10.matchStat = r8
            return r0
        L_0x018c:
            r10.matchStat = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldBoolean(char[]):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int scanInt(char r13) {
        /*
            r12 = this;
            r0 = 0
            r12.matchStat = r0
            int r1 = r12.bp
            int r2 = r1 + 1
            char r1 = r12.charAt(r1)
        L_0x000b:
            boolean r3 = isWhitespace(r1)
            if (r3 == 0) goto L_0x001b
            int r1 = r2 + 1
            char r2 = r12.charAt(r2)
            r11 = r2
            r2 = r1
            r1 = r11
            goto L_0x000b
        L_0x001b:
            r3 = 34
            r4 = 1
            if (r1 != r3) goto L_0x0022
            r5 = 1
            goto L_0x0023
        L_0x0022:
            r5 = 0
        L_0x0023:
            if (r5 == 0) goto L_0x002e
            int r1 = r2 + 1
            char r2 = r12.charAt(r2)
            r11 = r2
            r2 = r1
            r1 = r11
        L_0x002e:
            r6 = 45
            if (r1 != r6) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            r4 = 0
        L_0x0034:
            if (r4 == 0) goto L_0x003f
            int r1 = r2 + 1
            char r2 = r12.charAt(r2)
            r11 = r2
            r2 = r1
            r1 = r11
        L_0x003f:
            r6 = 16
            r7 = 48
            r8 = -1
            if (r1 < r7) goto L_0x00a1
            r9 = 57
            if (r1 > r9) goto L_0x00a1
            int r1 = r1 - r7
        L_0x004b:
            int r10 = r2 + 1
            char r2 = r12.charAt(r2)
            if (r2 < r7) goto L_0x005c
            if (r2 > r9) goto L_0x005c
            int r1 = r1 * 10
            int r2 = r2 + -48
            int r1 = r1 + r2
            r2 = r10
            goto L_0x004b
        L_0x005c:
            r7 = 46
            if (r2 != r7) goto L_0x0063
            r12.matchStat = r8
            return r0
        L_0x0063:
            if (r5 == 0) goto L_0x0071
            if (r2 == r3) goto L_0x006a
            r12.matchStat = r8
            return r0
        L_0x006a:
            int r2 = r10 + 1
            char r3 = r12.charAt(r10)
            goto L_0x0073
        L_0x0071:
            r3 = r2
            r2 = r10
        L_0x0073:
            if (r1 >= 0) goto L_0x0078
            r12.matchStat = r8
            return r0
        L_0x0078:
            if (r3 != r13) goto L_0x008d
            r12.bp = r2
            int r13 = r12.bp
            char r13 = r12.charAt(r13)
            r12.ch = r13
            r13 = 3
            r12.matchStat = r13
            r12.token = r6
            if (r4 == 0) goto L_0x008c
            int r1 = -r1
        L_0x008c:
            return r1
        L_0x008d:
            boolean r0 = isWhitespace(r3)
            if (r0 == 0) goto L_0x009b
            int r0 = r2 + 1
            char r3 = r12.charAt(r2)
            r2 = r0
            goto L_0x0078
        L_0x009b:
            r12.matchStat = r8
            if (r4 == 0) goto L_0x00a0
            int r1 = -r1
        L_0x00a0:
            return r1
        L_0x00a1:
            r13 = 110(0x6e, float:1.54E-43)
            if (r1 != r13) goto L_0x010f
            int r13 = r2 + 1
            char r1 = r12.charAt(r2)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x010f
            int r1 = r13 + 1
            char r13 = r12.charAt(r13)
            r2 = 108(0x6c, float:1.51E-43)
            if (r13 != r2) goto L_0x010f
            int r13 = r1 + 1
            char r1 = r12.charAt(r1)
            if (r1 != r2) goto L_0x010f
            r1 = 5
            r12.matchStat = r1
            int r2 = r13 + 1
            char r13 = r12.charAt(r13)
            if (r5 == 0) goto L_0x00d7
            if (r13 != r3) goto L_0x00d7
            int r13 = r2 + 1
            char r2 = r12.charAt(r2)
        L_0x00d4:
            r11 = r2
            r2 = r13
            r13 = r11
        L_0x00d7:
            r3 = 44
            if (r13 != r3) goto L_0x00ea
            r12.bp = r2
            int r13 = r12.bp
            char r13 = r12.charAt(r13)
            r12.ch = r13
            r12.matchStat = r1
            r12.token = r6
            return r0
        L_0x00ea:
            r3 = 93
            if (r13 != r3) goto L_0x00ff
            r12.bp = r2
            int r13 = r12.bp
            char r13 = r12.charAt(r13)
            r12.ch = r13
            r12.matchStat = r1
            r13 = 15
            r12.token = r13
            return r0
        L_0x00ff:
            boolean r13 = isWhitespace(r13)
            if (r13 == 0) goto L_0x010c
            int r13 = r2 + 1
            char r2 = r12.charAt(r2)
            goto L_0x00d4
        L_0x010c:
            r12.matchStat = r8
            return r0
        L_0x010f:
            r12.matchStat = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanInt(char):int");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public double scanDouble(char r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r3 = r2 + 1
            char r2 = r0.charAt(r2)
            r4 = 34
            if (r2 != r4) goto L_0x0013
            r6 = 1
            goto L_0x0014
        L_0x0013:
            r6 = 0
        L_0x0014:
            if (r6 == 0) goto L_0x0021
            int r2 = r3 + 1
            char r3 = r0.charAt(r3)
            r20 = r3
            r3 = r2
            r2 = r20
        L_0x0021:
            r7 = 45
            if (r2 != r7) goto L_0x0027
            r8 = 1
            goto L_0x0028
        L_0x0027:
            r8 = 0
        L_0x0028:
            if (r8 == 0) goto L_0x0035
            int r2 = r3 + 1
            char r3 = r0.charAt(r3)
            r20 = r3
            r3 = r2
            r2 = r20
        L_0x0035:
            r9 = 16
            r10 = 0
            r12 = -1
            r13 = 48
            if (r2 < r13) goto L_0x011e
            r14 = 57
            if (r2 > r14) goto L_0x011e
            int r2 = r2 - r13
            long r1 = (long) r2
        L_0x0044:
            int r15 = r3 + 1
            char r3 = r0.charAt(r3)
            r17 = 10
            if (r3 < r13) goto L_0x005a
            if (r3 > r14) goto L_0x005a
            long r1 = r1 * r17
            int r3 = r3 + -48
            long r4 = (long) r3
            long r1 = r1 + r4
            r3 = r15
            r4 = 34
            goto L_0x0044
        L_0x005a:
            r4 = 46
            if (r3 != r4) goto L_0x0060
            r4 = 1
            goto L_0x0061
        L_0x0060:
            r4 = 0
        L_0x0061:
            if (r4 == 0) goto L_0x0095
            int r3 = r15 + 1
            char r4 = r0.charAt(r15)
            if (r4 < r13) goto L_0x0092
            if (r4 > r14) goto L_0x0092
            long r1 = r1 * r17
            int r4 = r4 - r13
            long r4 = (long) r4
            long r1 = r1 + r4
            r4 = r17
        L_0x0074:
            int r15 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r13) goto L_0x008d
            if (r3 > r14) goto L_0x008d
            long r1 = r1 * r17
            int r3 = r3 + -48
            r19 = r15
            long r14 = (long) r3
            long r1 = r1 + r14
            long r4 = r4 * r17
            r3 = r19
            r14 = 57
            goto L_0x0074
        L_0x008d:
            r19 = r15
            r15 = r19
            goto L_0x0097
        L_0x0092:
            r0.matchStat = r12
            return r10
        L_0x0095:
            r4 = 1
        L_0x0097:
            r14 = 101(0x65, float:1.42E-43)
            if (r3 == r14) goto L_0x00a3
            r14 = 69
            if (r3 != r14) goto L_0x00a0
            goto L_0x00a3
        L_0x00a0:
            r16 = 0
            goto L_0x00a5
        L_0x00a3:
            r16 = 1
        L_0x00a5:
            if (r16 == 0) goto L_0x00cb
            int r3 = r15 + 1
            char r14 = r0.charAt(r15)
            r15 = 43
            if (r14 == r15) goto L_0x00b7
            if (r14 != r7) goto L_0x00b4
            goto L_0x00b7
        L_0x00b4:
            r15 = r3
            r3 = r14
            goto L_0x00be
        L_0x00b7:
            int r7 = r3 + 1
            char r3 = r0.charAt(r3)
            r15 = r7
        L_0x00be:
            if (r3 < r13) goto L_0x00cb
            r7 = 57
            if (r3 > r7) goto L_0x00cb
            int r3 = r15 + 1
            char r14 = r0.charAt(r15)
            goto L_0x00b4
        L_0x00cb:
            if (r6 == 0) goto L_0x00e5
            r6 = 34
            if (r3 == r6) goto L_0x00d4
            r0.matchStat = r12
            return r10
        L_0x00d4:
            int r3 = r15 + 1
            char r6 = r0.charAt(r15)
            int r7 = r0.bp
            r10 = 1
            int r7 = r7 + r10
            int r10 = r3 - r7
            int r10 = r10 + -2
            r15 = r3
            r3 = r6
            goto L_0x00ec
        L_0x00e5:
            r10 = 1
            int r7 = r0.bp
            int r6 = r15 - r7
            int r10 = r6 + -1
        L_0x00ec:
            if (r16 != 0) goto L_0x00ff
            r6 = 20
            if (r10 >= r6) goto L_0x00ff
            double r1 = (double) r1
            double r4 = (double) r4
            java.lang.Double.isNaN(r1)
            java.lang.Double.isNaN(r4)
            double r1 = r1 / r4
            if (r8 == 0) goto L_0x0107
            double r1 = -r1
            goto L_0x0107
        L_0x00ff:
            java.lang.String r1 = r0.subString(r7, r10)
            double r1 = java.lang.Double.parseDouble(r1)
        L_0x0107:
            r4 = r22
            if (r3 != r4) goto L_0x011b
            r0.bp = r15
            int r3 = r0.bp
            char r3 = r0.charAt(r3)
            r0.ch = r3
            r3 = 3
            r0.matchStat = r3
            r0.token = r9
            return r1
        L_0x011b:
            r0.matchStat = r12
            return r1
        L_0x011e:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x0190
            int r1 = r3 + 1
            char r2 = r0.charAt(r3)
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L_0x0190
            int r2 = r1 + 1
            char r1 = r0.charAt(r1)
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 != r3) goto L_0x0190
            int r1 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 != r3) goto L_0x0190
            r2 = 5
            r0.matchStat = r2
            int r3 = r1 + 1
            char r1 = r0.charAt(r1)
            if (r6 == 0) goto L_0x0158
            r4 = 34
            if (r1 != r4) goto L_0x0158
            int r1 = r3 + 1
            char r3 = r0.charAt(r3)
        L_0x0153:
            r20 = r3
            r3 = r1
            r1 = r20
        L_0x0158:
            r4 = 44
            if (r1 != r4) goto L_0x016b
            r0.bp = r3
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r2
            r0.token = r9
            return r10
        L_0x016b:
            r4 = 93
            if (r1 != r4) goto L_0x0180
            r0.bp = r3
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r2
            r1 = 15
            r0.token = r1
            return r10
        L_0x0180:
            boolean r1 = isWhitespace(r1)
            if (r1 == 0) goto L_0x018d
            int r1 = r3 + 1
            char r3 = r0.charAt(r3)
            goto L_0x0153
        L_0x018d:
            r0.matchStat = r12
            return r10
        L_0x0190:
            r0.matchStat = r12
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanDouble(char):double");
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long scanLong(char r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r3 = r2 + 1
            char r2 = r0.charAt(r2)
            r4 = 34
            r5 = 1
            if (r2 != r4) goto L_0x0014
            r6 = 1
            goto L_0x0015
        L_0x0014:
            r6 = 0
        L_0x0015:
            if (r6 == 0) goto L_0x0022
            int r2 = r3 + 1
            char r3 = r0.charAt(r3)
            r18 = r3
            r3 = r2
            r2 = r18
        L_0x0022:
            r7 = 45
            if (r2 != r7) goto L_0x0028
            r7 = 1
            goto L_0x0029
        L_0x0028:
            r7 = 0
        L_0x0029:
            if (r7 == 0) goto L_0x0036
            int r2 = r3 + 1
            char r3 = r0.charAt(r3)
            r18 = r3
            r3 = r2
            r2 = r18
        L_0x0036:
            r9 = 48
            r10 = -1
            r11 = 0
            if (r2 < r9) goto L_0x00b2
            r13 = 57
            if (r2 > r13) goto L_0x00b2
            int r2 = r2 - r9
            long r14 = (long) r2
        L_0x0043:
            int r2 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r9) goto L_0x0059
            if (r3 > r13) goto L_0x0059
            r16 = 10
            long r14 = r14 * r16
            int r3 = r3 + -48
            long r8 = (long) r3
            long r14 = r14 + r8
            r3 = r2
            r9 = 48
            goto L_0x0043
        L_0x0059:
            r8 = 46
            if (r3 != r8) goto L_0x0060
            r0.matchStat = r10
            return r11
        L_0x0060:
            if (r6 == 0) goto L_0x0072
            if (r3 == r4) goto L_0x0067
            r0.matchStat = r10
            return r11
        L_0x0067:
            int r3 = r2 + 1
            char r2 = r0.charAt(r2)
            r18 = r3
            r3 = r2
            r2 = r18
        L_0x0072:
            int r4 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r4 >= 0) goto L_0x007e
            r8 = -9223372036854775808
            int r4 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x007f
            if (r7 == 0) goto L_0x007f
        L_0x007e:
            r1 = 1
        L_0x007f:
            if (r1 != 0) goto L_0x0084
            r0.matchStat = r10
            return r11
        L_0x0084:
            r1 = r20
        L_0x0086:
            if (r3 != r1) goto L_0x009d
            r0.bp = r2
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r1 = 3
            r0.matchStat = r1
            r1 = 16
            r0.token = r1
            if (r7 == 0) goto L_0x009c
            long r14 = -r14
        L_0x009c:
            return r14
        L_0x009d:
            boolean r3 = isWhitespace(r3)
            if (r3 == 0) goto L_0x00af
            int r3 = r2 + 1
            char r2 = r0.charAt(r2)
            r18 = r3
            r3 = r2
            r2 = r18
            goto L_0x0086
        L_0x00af:
            r0.matchStat = r10
            return r14
        L_0x00b2:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x0126
            int r1 = r3 + 1
            char r2 = r0.charAt(r3)
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L_0x0126
            int r2 = r1 + 1
            char r1 = r0.charAt(r1)
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 != r3) goto L_0x0126
            int r1 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 != r3) goto L_0x0126
            r2 = 5
            r0.matchStat = r2
            int r3 = r1 + 1
            char r1 = r0.charAt(r1)
            if (r6 == 0) goto L_0x00ea
            if (r1 != r4) goto L_0x00ea
            int r1 = r3 + 1
            char r3 = r0.charAt(r3)
        L_0x00e5:
            r18 = r3
            r3 = r1
            r1 = r18
        L_0x00ea:
            r4 = 44
            if (r1 != r4) goto L_0x00ff
            r0.bp = r3
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r2
            r4 = 16
            r0.token = r4
            return r11
        L_0x00ff:
            r4 = 16
            r5 = 93
            if (r1 != r5) goto L_0x0116
            r0.bp = r3
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r2
            r1 = 15
            r0.token = r1
            return r11
        L_0x0116:
            boolean r1 = isWhitespace(r1)
            if (r1 == 0) goto L_0x0123
            int r1 = r3 + 1
            char r3 = r0.charAt(r3)
            goto L_0x00e5
        L_0x0123:
            r0.matchStat = r10
            return r11
        L_0x0126:
            r0.matchStat = r10
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanLong(char):long");
    }

    public Date scanDate(char c2) {
        char c3;
        Date date;
        boolean z;
        int i;
        long j;
        char c4;
        int i2;
        this.matchStat = 0;
        int i3 = this.bp;
        char c5 = this.ch;
        int i4 = this.bp;
        int i5 = i4 + 1;
        char charAt = charAt(i4);
        if (charAt == '\"') {
            int indexOf = indexOf('\"', i5);
            if (indexOf != -1) {
                this.bp = i5;
                if (scanISO8601DateIfMatch(false, indexOf - i5)) {
                    date = this.calendar.getTime();
                    c3 = charAt(indexOf + 1);
                    this.bp = i3;
                    while (c3 != ',' && c3 != ']') {
                        if (isWhitespace(c3)) {
                            indexOf++;
                            c3 = charAt(indexOf + 1);
                        } else {
                            this.bp = i3;
                            this.ch = c5;
                            this.matchStat = -1;
                            return null;
                        }
                    }
                    this.bp = indexOf + 1;
                    this.ch = c3;
                } else {
                    this.bp = i3;
                    this.ch = c5;
                    this.matchStat = -1;
                    return null;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            char c6 = '9';
            char c7 = '0';
            if (charAt == '-' || (charAt >= '0' && charAt <= '9')) {
                if (charAt == '-') {
                    i = i5 + 1;
                    charAt = charAt(i5);
                    z = true;
                } else {
                    i = i5;
                    z = false;
                }
                if (charAt < '0' || charAt > '9') {
                    c4 = charAt;
                    j = 0;
                } else {
                    j = (long) (charAt - '0');
                    while (true) {
                        i2 = i + 1;
                        c4 = charAt(i);
                        if (c4 >= c7 && c4 <= c6) {
                            j = (j * 10) + ((long) (c4 - '0'));
                            i = i2;
                            c6 = '9';
                            c7 = '0';
                        } else if (c4 == ',' || c4 == ']') {
                            this.bp = i2 - 1;
                        }
                    }
                    this.bp = i2 - 1;
                }
                if (j < 0) {
                    this.bp = i3;
                    this.ch = c5;
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                c3 = c4;
                date = new Date(j);
            } else {
                if (charAt == 'n') {
                    int i6 = i5 + 1;
                    if (charAt(i5) == 'u') {
                        int i7 = i6 + 1;
                        if (charAt(i6) == 'l') {
                            int i8 = i7 + 1;
                            if (charAt(i7) == 'l') {
                                c3 = charAt(i8);
                                this.bp = i8;
                                date = null;
                            }
                        }
                    }
                }
                this.bp = i3;
                this.ch = c5;
                this.matchStat = -1;
                return null;
            }
        }
        if (c3 == ',') {
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
            this.matchStat = 3;
            return date;
        }
        int i10 = this.bp + 1;
        this.bp = i10;
        char charAt2 = charAt(i10);
        if (charAt2 == ',') {
            this.token = 16;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (charAt2 == ']') {
            this.token = 15;
            int i12 = this.bp + 1;
            this.bp = i12;
            this.ch = charAt(i12);
        } else if (charAt2 == '}') {
            this.token = 13;
            int i13 = this.bp + 1;
            this.bp = i13;
            this.ch = charAt(i13);
        } else if (charAt2 == 26) {
            this.ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.bp = i3;
            this.ch = c5;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    /* access modifiers changed from: protected */
    public final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    public String info() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", json : ");
        if (this.text.length() < 65536) {
            str = this.text;
        } else {
            str = this.text.substring(0, 65536);
        }
        sb.append(str);
        return sb.toString();
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        char c2;
        int i2;
        int i3 = this.bp;
        char c3 = this.ch;
        while (isWhitespace(this.ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.bp + cArr.length;
            int i4 = length + 1;
            char charAt = this.text.charAt(length);
            while (isWhitespace(charAt)) {
                charAt = this.text.charAt(i4);
                i4++;
            }
            if (charAt == ':') {
                i2 = i4 + 1;
                c2 = this.text.charAt(i4);
                while (isWhitespace(c2)) {
                    c2 = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.bp + 1;
            c2 = this.ch;
        }
        if (c2 == '[') {
            this.bp = i2;
            this.ch = this.text.charAt(this.bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.ch)) {
                    next();
                } else if (this.ch != '\"') {
                    this.bp = i3;
                    this.ch = c3;
                    this.matchStat = -1;
                    return null;
                } else {
                    String scanSymbol = scanSymbol(symbolTable, '\"');
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[(strArr.length + (strArr.length >> 1) + 1)];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = scanSymbol;
                    while (isWhitespace(this.ch)) {
                        next();
                    }
                    if (this.ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.ch)) {
                            next();
                        }
                        if (this.ch == ']') {
                            next();
                            return strArr;
                        }
                        this.bp = i3;
                        this.ch = c3;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else if (c2 != 'n' || !this.text.startsWith("ull", this.bp + 1)) {
            this.matchStat = -1;
            return null;
        } else {
            this.bp += 4;
            this.ch = this.text.charAt(this.bp);
            return null;
        }
    }

    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char charAt = this.text.charAt(length);
        while (isWhitespace(charAt)) {
            charAt = this.text.charAt(i);
            i++;
        }
        if (charAt == ':') {
            this.bp = i;
            this.ch = charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }
}
