package com.alibaba.fastjson.parser;

import anetwork.channel.util.RequestConstant;
import cn.qqtheme.framework.BuildConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public abstract class JSONLexerBase implements JSONLexer, Closeable {
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    private static final ThreadLocal<char[]> SBUF_LOCAL = new ThreadLocal<>();
    protected static final int[] digits = new int[103];
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected int bp;
    protected Calendar calendar = null;
    protected char ch;
    protected int eofPos;
    protected int features;
    protected boolean hasSpecial;
    protected Locale locale = JSON.defaultLocale;
    public int matchStat = 0;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue = null;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected int token;

    public static boolean isWhitespace(char c2) {
        return c2 <= ' ' && (c2 == ' ' || c2 == 10 || c2 == 13 || c2 == 9 || c2 == 12 || c2 == 8);
    }

    public abstract String addSymbol(int i, int i2, int i3, SymbolTable symbolTable);

    /* access modifiers changed from: protected */
    public abstract void arrayCopy(int i, char[] cArr, int i2, int i3);

    public abstract byte[] bytesValue();

    /* access modifiers changed from: protected */
    public abstract boolean charArrayCompare(char[] cArr);

    public abstract char charAt(int i);

    /* access modifiers changed from: protected */
    public abstract void copyTo(int i, int i2, char[] cArr);

    public abstract BigDecimal decimalValue();

    public abstract int indexOf(char c2, int i);

    public String info() {
        return "";
    }

    public abstract boolean isEOF();

    public abstract char next();

    public abstract String numberString();

    public abstract String stringVal();

    public abstract String subString(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract char[] sub_chars(int i, int i2);

    /* access modifiers changed from: protected */
    public void lexError(String str, Object... objArr) {
        this.token = 1;
    }

    static {
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = (i3 - 65) + 10;
        }
    }

    public JSONLexerBase(int i) {
        this.features = i;
        if ((i & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        this.sbuf = SBUF_LOCAL.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
    }

    public final int matchStat() {
        return this.matchStat;
    }

    public void setToken(int i) {
        this.token = i;
    }

    public final void nextToken() {
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            if (this.ch == '/') {
                skipComment();
            } else if (this.ch == '\"') {
                scanString();
                return;
            } else if (this.ch == ',') {
                next();
                this.token = 16;
                return;
            } else if (this.ch >= '0' && this.ch <= '9') {
                scanNumber();
                return;
            } else if (this.ch == '-') {
                scanNumber();
                return;
            } else {
                switch (this.ch) {
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (isEnabled(Feature.AllowSingleQuotes)) {
                            scanStringSingleQuote();
                            return;
                        }
                        throw new JSONException("Feature.AllowSingleQuotes is false");
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case '+':
                        next();
                        scanNumber();
                        return;
                    case '.':
                        next();
                        this.token = 25;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case ';':
                        next();
                        this.token = 24;
                        return;
                    case 'N':
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        next();
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case 'n':
                        scanNullOrNew();
                        return;
                    case 't':
                        scanTrue();
                        return;
                    case 'x':
                        scanHex();
                        return;
                    case BuildConfig.VERSION_CODE /*123*/:
                        next();
                        this.token = 12;
                        return;
                    case '}':
                        next();
                        this.token = 13;
                        return;
                    default:
                        if (isEOF()) {
                            if (this.token != 20) {
                                this.token = 20;
                                int i = this.eofPos;
                                this.bp = i;
                                this.pos = i;
                                return;
                            }
                            throw new JSONException("EOF error");
                        } else if (this.ch <= 31 || this.ch == 127) {
                            next();
                            break;
                        } else {
                            lexError("illegal.char", String.valueOf(this.ch));
                            next();
                            return;
                        }
                }
            }
        }
    }

    public final void nextToken(int i) {
        this.sp = 0;
        while (true) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 12) {
                        if (i != 18) {
                            if (i != 20) {
                                switch (i) {
                                    case 14:
                                        if (this.ch == '[') {
                                            this.token = 14;
                                            next();
                                            return;
                                        } else if (this.ch == '{') {
                                            this.token = 12;
                                            next();
                                            return;
                                        }
                                        break;
                                    case 15:
                                        if (this.ch == ']') {
                                            this.token = 15;
                                            next();
                                            return;
                                        }
                                        break;
                                    case 16:
                                        if (this.ch == ',') {
                                            this.token = 16;
                                            next();
                                            return;
                                        } else if (this.ch == '}') {
                                            this.token = 13;
                                            next();
                                            return;
                                        } else if (this.ch == ']') {
                                            this.token = 15;
                                            next();
                                            return;
                                        } else if (this.ch == 26) {
                                            this.token = 20;
                                            return;
                                        }
                                        break;
                                }
                            }
                            if (this.ch == 26) {
                                this.token = 20;
                                return;
                            }
                        } else {
                            nextIdent();
                            return;
                        }
                    } else if (this.ch == '{') {
                        this.token = 12;
                        next();
                        return;
                    } else if (this.ch == '[') {
                        this.token = 14;
                        next();
                        return;
                    }
                } else if (this.ch == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (this.ch >= '0' && this.ch <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (this.ch == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (this.ch == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (this.ch >= '0' && this.ch <= '9') {
                this.pos = this.bp;
                scanNumber();
                return;
            } else if (this.ch == '\"') {
                this.pos = this.bp;
                scanString();
                return;
            } else if (this.ch == '[') {
                this.token = 14;
                next();
                return;
            } else if (this.ch == '{') {
                this.token = 12;
                next();
                return;
            }
            if (this.ch == ' ' || this.ch == 10 || this.ch == 13 || this.ch == 9 || this.ch == 12 || this.ch == 8) {
                next();
            } else {
                nextToken();
                return;
            }
        }
    }

    public final void nextIdent() {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (this.ch == '_' || this.ch == '$' || Character.isLetter(this.ch)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    public final void nextTokenWithColon() {
        nextTokenWithChar(':');
    }

    public final void nextTokenWithChar(char c2) {
        this.sp = 0;
        while (this.ch != c2) {
            if (this.ch == ' ' || this.ch == 10 || this.ch == 13 || this.ch == 9 || this.ch == 12 || this.ch == 8) {
                next();
            } else {
                throw new JSONException("not match " + c2 + " - " + this.ch + ", info : " + info());
            }
        }
        next();
        nextToken();
    }

    public final int token() {
        return this.token;
    }

    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    public final int pos() {
        return this.pos;
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    public final Number integerValue() throws NumberFormatException {
        long j;
        long j2;
        boolean z = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i = this.np;
        int i2 = this.np + this.sp;
        char c2 = ' ';
        char charAt = charAt(i2 - 1);
        if (charAt == 'B') {
            i2--;
            c2 = 'B';
        } else if (charAt == 'L') {
            i2--;
            c2 = 'L';
        } else if (charAt == 'S') {
            i2--;
            c2 = 'S';
        }
        if (charAt(this.np) == '-') {
            j = Long.MIN_VALUE;
            i++;
            z = true;
        } else {
            j = -9223372036854775807L;
        }
        long j3 = MULTMIN_RADIX_TEN;
        if (i < i2) {
            j2 = (long) (-(charAt(i) - '0'));
            i++;
        } else {
            j2 = 0;
        }
        while (i < i2) {
            int i3 = i + 1;
            int charAt2 = charAt(i) - '0';
            if (j2 < j3) {
                return new BigInteger(numberString());
            }
            long j4 = j2 * 10;
            long j5 = (long) charAt2;
            if (j4 < j + j5) {
                return new BigInteger(numberString());
            }
            j2 = j4 - j5;
            i = i3;
            j3 = MULTMIN_RADIX_TEN;
        }
        if (!z) {
            long j6 = -j2;
            if (j6 > 2147483647L || c2 == 'L') {
                return Long.valueOf(j6);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) ((int) j6));
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) ((int) j6));
            }
            return Integer.valueOf((int) j6);
        } else if (i <= this.np + 1) {
            throw new NumberFormatException(numberString());
        } else if (j2 < -2147483648L || c2 == 'L') {
            return Long.valueOf(j2);
        } else {
            if (c2 == 'S') {
                return Short.valueOf((short) ((int) j2));
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) ((int) j2));
            }
            return Integer.valueOf((int) j2);
        }
    }

    public final void nextTokenWithColon(int i) {
        nextTokenWithChar(':');
    }

    public float floatValue() {
        char charAt;
        String numberString = numberString();
        float parseFloat = Float.parseFloat(numberString);
        if ((parseFloat != 0.0f && parseFloat != Float.POSITIVE_INFINITY) || (charAt = numberString.charAt(0)) <= '0' || charAt > '9') {
            return parseFloat;
        }
        throw new JSONException("float overflow : " + numberString);
    }

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    public void config(Feature feature, boolean z) {
        this.features = Feature.config(this.features, feature, z);
        if ((this.features & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    public final boolean isEnabled(Feature feature) {
        return isEnabled(feature.mask);
    }

    public final boolean isEnabled(int i) {
        return (i & this.features) != 0;
    }

    public final boolean isEnabled(int i, int i2) {
        return ((this.features & i2) == 0 && (i & i2) == 0) ? false : true;
    }

    public final char getCurrent() {
        return this.ch;
    }

    /* access modifiers changed from: protected */
    public void skipComment() {
        next();
        if (this.ch == '/') {
            do {
                next();
                if (this.ch == 10) {
                    next();
                    return;
                }
            } while (this.ch != 26);
        } else if (this.ch == '*') {
            next();
            while (this.ch != 26) {
                if (this.ch == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        if (this.ch == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (this.ch == '\'') {
            if (isEnabled(Feature.AllowSingleQuotes)) {
                return scanSymbol(symbolTable, '\'');
            }
            throw new JSONException("syntax error");
        } else if (this.ch == '}') {
            next();
            this.token = 13;
            return null;
        } else if (this.ch == ',') {
            next();
            this.token = 16;
            return null;
        } else if (this.ch == 26) {
            this.token = 20;
            return null;
        } else if (isEnabled(Feature.AllowUnQuotedFieldNames)) {
            return scanSymbolUnQuoted(symbolTable);
        } else {
            throw new JSONException("syntax error");
        }
    }

    public final String scanSymbol(SymbolTable symbolTable, char c2) {
        String str;
        int i;
        this.np = this.bp;
        this.sp = 0;
        boolean z = false;
        int i2 = 0;
        while (true) {
            char next = next();
            if (next == c2) {
                this.token = 4;
                if (!z) {
                    if (this.np == -1) {
                        i = 0;
                    } else {
                        i = this.np + 1;
                    }
                    str = addSymbol(i, this.sp, i2, symbolTable);
                } else {
                    str = symbolTable.addSymbol(this.sbuf, 0, this.sp, i2);
                }
                this.sp = 0;
                next();
                return str;
            } else if (next == 26) {
                throw new JSONException("unclosed.str");
            } else if (next == '\\') {
                if (!z) {
                    if (this.sp >= this.sbuf.length) {
                        int length = this.sbuf.length * 2;
                        if (this.sp > length) {
                            length = this.sp;
                        }
                        char[] cArr = new char[length];
                        System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
                        this.sbuf = cArr;
                    }
                    arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                    z = true;
                }
                char next2 = next();
                switch (next2) {
                    case '/':
                        i2 = (i2 * 31) + 47;
                        putChar('/');
                        break;
                    case '0':
                        i2 = (i2 * 31) + next2;
                        putChar(0);
                        break;
                    case '1':
                        i2 = (i2 * 31) + next2;
                        putChar(1);
                        break;
                    case '2':
                        i2 = (i2 * 31) + next2;
                        putChar(2);
                        break;
                    case '3':
                        i2 = (i2 * 31) + next2;
                        putChar(3);
                        break;
                    case '4':
                        i2 = (i2 * 31) + next2;
                        putChar(4);
                        break;
                    case '5':
                        i2 = (i2 * 31) + next2;
                        putChar(5);
                        break;
                    case '6':
                        i2 = (i2 * 31) + next2;
                        putChar(6);
                        break;
                    case '7':
                        i2 = (i2 * 31) + next2;
                        putChar(7);
                        break;
                    default:
                        switch (next2) {
                            case 't':
                                i2 = (i2 * 31) + 9;
                                putChar(9);
                                break;
                            case 'u':
                                int parseInt = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                i2 = (i2 * 31) + parseInt;
                                putChar((char) parseInt);
                                break;
                            case 'v':
                                i2 = (i2 * 31) + 11;
                                putChar(11);
                                break;
                            default:
                                switch (next2) {
                                    case '\"':
                                        i2 = (i2 * 31) + 34;
                                        putChar('\"');
                                        break;
                                    case '\'':
                                        i2 = (i2 * 31) + 39;
                                        putChar('\'');
                                        break;
                                    case 'F':
                                    case 'f':
                                        i2 = (i2 * 31) + 12;
                                        putChar(12);
                                        break;
                                    case '\\':
                                        i2 = (i2 * 31) + 92;
                                        putChar('\\');
                                        break;
                                    case 'b':
                                        i2 = (i2 * 31) + 8;
                                        putChar(8);
                                        break;
                                    case 'n':
                                        i2 = (i2 * 31) + 10;
                                        putChar(10);
                                        break;
                                    case 'r':
                                        i2 = (i2 * 31) + 13;
                                        putChar(13);
                                        break;
                                    case 'x':
                                        char next3 = next();
                                        this.ch = next3;
                                        char next4 = next();
                                        this.ch = next4;
                                        char c3 = (char) ((digits[next3] * 16) + digits[next4]);
                                        i2 = (i2 * 31) + c3;
                                        putChar(c3);
                                        break;
                                    default:
                                        this.ch = next2;
                                        throw new JSONException("unclosed.str.lit");
                                }
                        }
                }
            } else {
                i2 = (i2 * 31) + next;
                if (!z) {
                    this.sp++;
                } else if (this.sp == this.sbuf.length) {
                    putChar(next);
                } else {
                    char[] cArr2 = this.sbuf;
                    int i3 = this.sp;
                    this.sp = i3 + 1;
                    cArr2[i3] = next;
                }
            }
        }
    }

    public final void resetStringPosition() {
        this.sp = 0;
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        boolean z = false;
        if (this.token == 1 && this.pos == 0 && this.bp == 1) {
            this.bp = 0;
        }
        boolean[] zArr = IOUtils.firstIdentifierFlags;
        int i = this.ch;
        if (this.ch >= zArr.length || zArr[i]) {
            z = true;
        }
        if (z) {
            boolean[] zArr2 = IOUtils.identifierFlags;
            this.np = this.bp;
            this.sp = 1;
            while (true) {
                char next = next();
                if (next < zArr2.length && !zArr2[next]) {
                    break;
                }
                i = (i * 31) + next;
                this.sp++;
            }
            this.ch = charAt(this.bp);
            this.token = 18;
            if (this.sp == 4 && i == 3392903 && charAt(this.np) == 'n' && charAt(this.np + 1) == 'u' && charAt(this.np + 2) == 'l' && charAt(this.np + 3) == 'l') {
                return null;
            }
            if (symbolTable == null) {
                return subString(this.np, this.sp);
            }
            return addSymbol(this.np, this.sp, i, symbolTable);
        }
        throw new JSONException("illegal identifier : " + this.ch + info());
    }

    public final void scanString() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\"') {
                this.token = 4;
                this.ch = next();
                return;
            } else if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed string : " + next);
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp >= this.sbuf.length) {
                        int length = this.sbuf.length * 2;
                        if (this.sp > length) {
                            length = this.sp;
                        }
                        char[] cArr = new char[length];
                        System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
                        this.sbuf = cArr;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                switch (next2) {
                    case '/':
                        putChar('/');
                        break;
                    case '0':
                        putChar(0);
                        break;
                    case '1':
                        putChar(1);
                        break;
                    case '2':
                        putChar(2);
                        break;
                    case '3':
                        putChar(3);
                        break;
                    case '4':
                        putChar(4);
                        break;
                    case '5':
                        putChar(5);
                        break;
                    case '6':
                        putChar(6);
                        break;
                    case '7':
                        putChar(7);
                        break;
                    default:
                        switch (next2) {
                            case 't':
                                putChar(9);
                                break;
                            case 'u':
                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                break;
                            case 'v':
                                putChar(11);
                                break;
                            default:
                                switch (next2) {
                                    case '\"':
                                        putChar('\"');
                                        break;
                                    case '\'':
                                        putChar('\'');
                                        break;
                                    case 'F':
                                    case 'f':
                                        putChar(12);
                                        break;
                                    case '\\':
                                        putChar('\\');
                                        break;
                                    case 'b':
                                        putChar(8);
                                        break;
                                    case 'n':
                                        putChar(10);
                                        break;
                                    case 'r':
                                        putChar(13);
                                        break;
                                    case 'x':
                                        putChar((char) ((digits[next()] * 16) + digits[next()]));
                                        break;
                                    default:
                                        this.ch = next2;
                                        throw new JSONException("unclosed string : " + next2);
                                }
                        }
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else if (this.sp == this.sbuf.length) {
                putChar(next);
            } else {
                char[] cArr2 = this.sbuf;
                int i = this.sp;
                this.sp = i + 1;
                cArr2[i] = next;
            }
        }
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone2) {
        this.timeZone = timeZone2;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public final int intValue() {
        int i;
        boolean z;
        int i2;
        int i3 = 0;
        if (this.np == -1) {
            this.np = 0;
        }
        int i4 = this.np;
        int i5 = this.np + this.sp;
        if (charAt(this.np) == '-') {
            i4++;
            z = true;
            i = Integer.MIN_VALUE;
        } else {
            z = false;
            i = -2147483647;
        }
        if (i4 < i5) {
            i3 = -(charAt(i4) - '0');
            i4++;
        }
        while (true) {
            if (i4 >= i5) {
                break;
            }
            i2 = i4 + 1;
            char charAt = charAt(i4);
            if (charAt == 'L' || charAt == 'S' || charAt == 'B') {
                i4 = i2;
            } else {
                int i6 = charAt - '0';
                if (((long) i3) >= -214748364) {
                    int i7 = i3 * 10;
                    if (i7 >= i + i6) {
                        i3 = i7 - i6;
                        i4 = i2;
                    } else {
                        throw new NumberFormatException(numberString());
                    }
                } else {
                    throw new NumberFormatException(numberString());
                }
            }
        }
        i4 = i2;
        if (!z) {
            return -i3;
        }
        if (i4 > this.np + 1) {
            return i3;
        }
        throw new NumberFormatException(numberString());
    }

    public void close() {
        if (this.sbuf.length <= 8192) {
            SBUF_LOCAL.set(this.sbuf);
        }
        this.sbuf = null;
    }

    public final boolean isRef() {
        if (this.sp == 4 && charAt(this.np + 1) == '$' && charAt(this.np + 2) == 'r' && charAt(this.np + 3) == 'e' && charAt(this.np + 4) == 'f') {
            return true;
        }
        return false;
    }

    public final int scanType(String str) {
        this.matchStat = 0;
        if (!charArrayCompare(typeFieldName)) {
            return -2;
        }
        int length = this.bp + typeFieldName.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            if (str.charAt(i) != charAt(length + i)) {
                return -1;
            }
        }
        int i2 = length + length2;
        if (charAt(i2) != '\"') {
            return -1;
        }
        int i3 = i2 + 1;
        this.ch = charAt(i3);
        if (this.ch == ',') {
            int i4 = i3 + 1;
            this.ch = charAt(i4);
            this.bp = i4;
            this.token = 16;
            return 3;
        }
        if (this.ch == '}') {
            i3++;
            this.ch = charAt(i3);
            if (this.ch == ',') {
                this.token = 16;
                i3++;
                this.ch = charAt(i3);
            } else if (this.ch == ']') {
                this.token = 15;
                i3++;
                this.ch = charAt(i3);
            } else if (this.ch == '}') {
                this.token = 13;
                i3++;
                this.ch = charAt(i3);
            } else if (this.ch != 26) {
                return -1;
            } else {
                this.token = 20;
            }
            this.matchStat = 4;
        }
        this.bp = i3;
        return this.matchStat;
    }

    public final boolean matchField(char[] cArr) {
        while (!charArrayCompare(cArr)) {
            if (!isWhitespace(this.ch)) {
                return false;
            }
            next();
        }
        this.bp += cArr.length;
        this.ch = charAt(this.bp);
        if (this.ch == '{') {
            next();
            this.token = 12;
        } else if (this.ch == '[') {
            next();
            this.token = 14;
        } else if (this.ch == 'S' && charAt(this.bp + 1) == 'e' && charAt(this.bp + 2) == 't' && charAt(this.bp + 3) == '[') {
            this.bp += 3;
            this.ch = charAt(this.bp);
            this.token = 21;
        } else {
            nextToken();
        }
        return true;
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return stringDefaultValue();
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int indexOf = indexOf('\"', this.bp + cArr.length + 1);
        if (indexOf != -1) {
            int length2 = this.bp + cArr.length + 1;
            String subString = subString(length2, indexOf - length2);
            if (subString.indexOf(92) != -1) {
                while (true) {
                    int i2 = indexOf - 1;
                    int i3 = 0;
                    while (i2 >= 0 && charAt(i2) == '\\') {
                        i3++;
                        i2--;
                    }
                    if (i3 % 2 == 0) {
                        break;
                    }
                    indexOf = indexOf('\"', indexOf + 1);
                }
                int length3 = indexOf - ((this.bp + cArr.length) + 1);
                subString = readString(sub_chars(this.bp + cArr.length + 1, length3), length3);
            }
            int length4 = i + (indexOf - ((this.bp + cArr.length) + 1)) + 1;
            int i4 = length4 + 1;
            char charAt = charAt(this.bp + length4);
            if (charAt == ',') {
                this.bp += i4;
                this.ch = charAt(this.bp);
                this.matchStat = 3;
                return subString;
            } else if (charAt == '}') {
                int i5 = i4 + 1;
                char charAt2 = charAt(this.bp + i4);
                if (charAt2 == ',') {
                    this.token = 16;
                    this.bp += i5;
                    this.ch = charAt(this.bp);
                } else if (charAt2 == ']') {
                    this.token = 15;
                    this.bp += i5;
                    this.ch = charAt(this.bp);
                } else if (charAt2 == '}') {
                    this.token = 13;
                    this.bp += i5;
                    this.ch = charAt(this.bp);
                } else if (charAt2 == 26) {
                    this.token = 20;
                    this.bp += i5 - 1;
                    this.ch = JSONLexer.EOI;
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
                this.matchStat = 4;
                return subString;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        } else {
            throw new JSONException("unclosed str");
        }
    }

    public String scanString(char c2) {
        this.matchStat = 0;
        char charAt = charAt(this.bp + 0);
        if (charAt != 'n') {
            int i = 1;
            while (charAt != '\"') {
                if (isWhitespace(charAt)) {
                    charAt = charAt(this.bp + i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
            }
            int i2 = this.bp + i;
            int indexOf = indexOf('\"', i2);
            if (indexOf != -1) {
                String subString = subString(this.bp + i, indexOf - i2);
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
                    int i5 = indexOf - i2;
                    subString = readString(sub_chars(this.bp + 1, i5), i5);
                }
                int i6 = i + (indexOf - i2) + 1;
                int i7 = i6 + 1;
                char charAt2 = charAt(this.bp + i6);
                while (charAt2 != c2) {
                    if (isWhitespace(charAt2)) {
                        charAt2 = charAt(this.bp + i7);
                        i7++;
                    } else {
                        this.matchStat = -1;
                        return subString;
                    }
                }
                this.bp += i7;
                this.ch = charAt(this.bp);
                this.matchStat = 3;
                return subString;
            }
            throw new JSONException("unclosed str");
        } else if (charAt(this.bp + 1) != 'u' || charAt(this.bp + 1 + 1) != 'l' || charAt(this.bp + 1 + 2) != 'l') {
            this.matchStat = -1;
            return null;
        } else if (charAt(this.bp + 4) == c2) {
            this.bp += 5;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return null;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return 0;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char charAt = charAt(this.bp + i);
            if (charAt == '\"') {
                int i3 = i2 + 1;
                char charAt2 = charAt(this.bp + i2);
                if (charAt2 == ',') {
                    this.bp += i3;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return j;
                } else if (charAt2 == '}') {
                    int i4 = i3 + 1;
                    char charAt3 = charAt(this.bp + i3);
                    if (charAt3 == ',') {
                        this.token = 16;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i4 - 1;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                    this.matchStat = 4;
                    return j;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            } else {
                j = (j ^ ((long) charAt)) * 1099511628211L;
                if (charAt == '\\') {
                    this.matchStat = -1;
                    return 0;
                }
                i = i2;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Enum<?> scanEnum(java.lang.Class<?> r1, com.alibaba.fastjson.parser.SymbolTable r2, char r3) {
        /*
            r0 = this;
            java.lang.String r2 = r0.scanSymbolWithSeperator(r2, r3)
            if (r2 != 0) goto L_0x0008
            r1 = 0
            return r1
        L_0x0008:
            java.lang.Enum r1 = java.lang.Enum.valueOf(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanEnum(java.lang.Class, com.alibaba.fastjson.parser.SymbolTable, char):java.lang.Enum");
    }

    public String scanSymbolWithSeperator(SymbolTable symbolTable, char c2) {
        this.matchStat = 0;
        char charAt = charAt(this.bp + 0);
        if (charAt == 'n') {
            if (charAt(this.bp + 1) != 'u' || charAt(this.bp + 1 + 1) != 'l' || charAt(this.bp + 1 + 2) != 'l') {
                this.matchStat = -1;
                return null;
            } else if (charAt(this.bp + 4) == c2) {
                this.bp += 5;
                this.ch = charAt(this.bp);
                this.matchStat = 3;
                return null;
            } else {
                this.matchStat = -1;
                return null;
            }
        } else if (charAt != '\"') {
            this.matchStat = -1;
            return null;
        } else {
            int i = 1;
            int i2 = 0;
            while (true) {
                int i3 = i + 1;
                char charAt2 = charAt(this.bp + i);
                if (charAt2 == '\"') {
                    int i4 = this.bp + 0 + 1;
                    String addSymbol = addSymbol(i4, ((this.bp + i3) - i4) - 1, i2, symbolTable);
                    int i5 = i3 + 1;
                    char charAt3 = charAt(this.bp + i3);
                    while (charAt3 != c2) {
                        if (isWhitespace(charAt3)) {
                            charAt3 = charAt(this.bp + i5);
                            i5++;
                        } else {
                            this.matchStat = -1;
                            return addSymbol;
                        }
                    }
                    this.bp += i5;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return addSymbol;
                }
                i2 = (i2 * 31) + charAt2;
                if (charAt2 == '\\') {
                    this.matchStat = -1;
                    return null;
                }
                i = i3;
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

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f9, code lost:
        if (r12 != ',') goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fb, code lost:
        r11.bp += r0;
        r11.ch = charAt(r11.bp);
        r11.matchStat = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x010b, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010e, code lost:
        if (r12 != '}') goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0110, code lost:
        r6 = r0 + 1;
        r12 = charAt(r11.bp + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0119, code lost:
        if (r12 != ',') goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011b, code lost:
        r11.token = 16;
        r11.bp += r6;
        r11.ch = charAt(r11.bp);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012d, code lost:
        if (r12 != ']') goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x012f, code lost:
        r11.token = 15;
        r11.bp += r6;
        r11.ch = charAt(r11.bp);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0141, code lost:
        if (r12 != '}') goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0143, code lost:
        r11.token = 13;
        r11.bp += r6;
        r11.ch = charAt(r11.bp);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0157, code lost:
        if (r12 != 26) goto L_0x016a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0159, code lost:
        r11.bp += r6 - 1;
        r11.token = 20;
        r11.ch = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0166, code lost:
        r11.matchStat = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0169, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016a, code lost:
        r11.matchStat = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x016d, code lost:
        r11.matchStat = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016f, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0170  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r12, java.lang.Class<?> r13) {
        /*
            r11 = this;
            r0 = 0
            r11.matchStat = r0
            boolean r1 = r11.charArrayCompare(r12)
            r2 = 0
            if (r1 != 0) goto L_0x000e
            r12 = -2
            r11.matchStat = r12
            return r2
        L_0x000e:
            java.util.Collection r13 = r11.newCollectionByType(r13)
            int r12 = r12.length
            int r1 = r11.bp
            int r3 = r12 + 1
            int r1 = r1 + r12
            char r12 = r11.charAt(r1)
            r1 = 91
            r4 = -1
            if (r12 == r1) goto L_0x0024
            r11.matchStat = r4
            return r2
        L_0x0024:
            int r12 = r11.bp
            int r1 = r3 + 1
            int r12 = r12 + r3
            char r12 = r11.charAt(r12)
        L_0x002d:
            r3 = 44
            r5 = 93
            r6 = 34
            if (r12 != r6) goto L_0x0097
            int r12 = r11.bp
            int r12 = r12 + r1
            int r12 = r11.indexOf(r6, r12)
            if (r12 == r4) goto L_0x008f
            int r7 = r11.bp
            int r7 = r7 + r1
            int r8 = r12 - r7
            java.lang.String r7 = r11.subString(r7, r8)
            r8 = 92
            int r9 = r7.indexOf(r8)
            if (r9 == r4) goto L_0x007b
        L_0x004f:
            int r7 = r12 + -1
            r9 = 0
        L_0x0052:
            if (r7 < 0) goto L_0x005f
            char r10 = r11.charAt(r7)
            if (r10 != r8) goto L_0x005f
            int r9 = r9 + 1
            int r7 = r7 + -1
            goto L_0x0052
        L_0x005f:
            int r9 = r9 % 2
            if (r9 != 0) goto L_0x0074
            int r6 = r11.bp
            int r6 = r6 + r1
            int r6 = r12 - r6
            int r7 = r11.bp
            int r7 = r7 + r1
            char[] r7 = r11.sub_chars(r7, r6)
            java.lang.String r7 = readString(r7, r6)
            goto L_0x007b
        L_0x0074:
            int r12 = r12 + 1
            int r12 = r11.indexOf(r6, r12)
            goto L_0x004f
        L_0x007b:
            int r6 = r11.bp
            int r6 = r6 + r1
            int r12 = r12 - r6
            int r12 = r12 + 1
            int r1 = r1 + r12
            int r12 = r11.bp
            int r6 = r1 + 1
            int r12 = r12 + r1
            char r12 = r11.charAt(r12)
            r13.add(r7)
            goto L_0x00cc
        L_0x008f:
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException
            java.lang.String r13 = "unclosed str"
            r12.<init>(r13)
            throw r12
        L_0x0097:
            r6 = 110(0x6e, float:1.54E-43)
            if (r12 != r6) goto L_0x00e8
            int r6 = r11.bp
            int r6 = r6 + r1
            char r6 = r11.charAt(r6)
            r7 = 117(0x75, float:1.64E-43)
            if (r6 != r7) goto L_0x00e8
            int r6 = r11.bp
            int r6 = r6 + r1
            int r6 = r6 + 1
            char r6 = r11.charAt(r6)
            r7 = 108(0x6c, float:1.51E-43)
            if (r6 != r7) goto L_0x00e8
            int r6 = r11.bp
            int r6 = r6 + r1
            int r6 = r6 + 2
            char r6 = r11.charAt(r6)
            if (r6 != r7) goto L_0x00e8
            int r1 = r1 + 3
            int r12 = r11.bp
            int r6 = r1 + 1
            int r12 = r12 + r1
            char r12 = r11.charAt(r12)
            r13.add(r2)
        L_0x00cc:
            if (r12 != r3) goto L_0x00d9
            int r12 = r11.bp
            int r1 = r6 + 1
            int r12 = r12 + r6
            char r12 = r11.charAt(r12)
            goto L_0x002d
        L_0x00d9:
            if (r12 != r5) goto L_0x00e5
            int r12 = r11.bp
            int r0 = r6 + 1
            int r12 = r12 + r6
            char r12 = r11.charAt(r12)
            goto L_0x00f9
        L_0x00e5:
            r11.matchStat = r4
            return r2
        L_0x00e8:
            if (r12 != r5) goto L_0x0170
            int r12 = r13.size()
            if (r12 != 0) goto L_0x0170
            int r12 = r11.bp
            int r0 = r1 + 1
            int r12 = r12 + r1
            char r12 = r11.charAt(r12)
        L_0x00f9:
            if (r12 != r3) goto L_0x010c
            int r12 = r11.bp
            int r12 = r12 + r0
            r11.bp = r12
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
            r11.ch = r12
            r12 = 3
            r11.matchStat = r12
            return r13
        L_0x010c:
            r1 = 125(0x7d, float:1.75E-43)
            if (r12 != r1) goto L_0x016d
            int r12 = r11.bp
            int r6 = r0 + 1
            int r12 = r12 + r0
            char r12 = r11.charAt(r12)
            if (r12 != r3) goto L_0x012d
            r12 = 16
            r11.token = r12
            int r12 = r11.bp
            int r12 = r12 + r6
            r11.bp = r12
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
            r11.ch = r12
            goto L_0x0166
        L_0x012d:
            if (r12 != r5) goto L_0x0141
            r12 = 15
            r11.token = r12
            int r12 = r11.bp
            int r12 = r12 + r6
            r11.bp = r12
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
            r11.ch = r12
            goto L_0x0166
        L_0x0141:
            if (r12 != r1) goto L_0x0155
            r12 = 13
            r11.token = r12
            int r12 = r11.bp
            int r12 = r12 + r6
            r11.bp = r12
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
            r11.ch = r12
            goto L_0x0166
        L_0x0155:
            r0 = 26
            if (r12 != r0) goto L_0x016a
            int r12 = r11.bp
            int r6 = r6 + -1
            int r12 = r12 + r6
            r11.bp = r12
            r12 = 20
            r11.token = r12
            r11.ch = r0
        L_0x0166:
            r12 = 4
            r11.matchStat = r12
            return r13
        L_0x016a:
            r11.matchStat = r4
            return r2
        L_0x016d:
            r11.matchStat = r4
            return r2
        L_0x0170:
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException
            java.lang.String r13 = "illega str"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    public void scanStringArray(Collection<String> collection, char c2) {
        int i;
        char c3;
        int i2;
        char c4;
        Collection<String> collection2 = collection;
        char c5 = c2;
        this.matchStat = 0;
        char charAt = charAt(this.bp + 0);
        char c6 = 'u';
        char c7 = 'n';
        if (charAt == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l' && charAt(this.bp + 1 + 3) == c5) {
            this.bp += 5;
            this.ch = charAt(this.bp);
            this.matchStat = 5;
        } else if (charAt != '[') {
            this.matchStat = -1;
        } else {
            char charAt2 = charAt(this.bp + 1);
            int i3 = 2;
            while (true) {
                if (charAt2 != c7 || charAt(this.bp + i3) != c6 || charAt(this.bp + i3 + 1) != 'l' || charAt(this.bp + i3 + 2) != 'l') {
                    if (charAt2 == ']' && collection.size() == 0) {
                        i = i3 + 1;
                        c3 = charAt(this.bp + i3);
                        break;
                    } else if (charAt2 != '\"') {
                        this.matchStat = -1;
                        return;
                    } else {
                        int i4 = this.bp + i3;
                        int indexOf = indexOf('\"', i4);
                        if (indexOf != -1) {
                            String subString = subString(this.bp + i3, indexOf - i4);
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
                                subString = readString(sub_chars(this.bp + i3, i7), i7);
                            }
                            int i8 = i3 + (indexOf - (this.bp + i3)) + 1;
                            i2 = i8 + 1;
                            c4 = charAt(this.bp + i8);
                            collection2.add(subString);
                        } else {
                            throw new JSONException("unclosed str");
                        }
                    }
                } else {
                    int i9 = i3 + 3;
                    i2 = i9 + 1;
                    c4 = charAt(this.bp + i9);
                    collection2.add((Object) null);
                }
                if (c4 == ',') {
                    i3 = i2 + 1;
                    charAt2 = charAt(this.bp + i2);
                    c6 = 'u';
                    c7 = 'n';
                } else if (c4 == ']') {
                    i = i2 + 1;
                    c3 = charAt(this.bp + i2);
                } else {
                    this.matchStat = -1;
                    return;
                }
            }
            if (c3 == c5) {
                this.bp += i;
                this.ch = charAt(this.bp);
                this.matchStat = 3;
                return;
            }
            this.matchStat = -1;
        }
    }

    public int scanFieldInt(char[] cArr) {
        int i;
        char charAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char charAt2 = charAt(this.bp + length);
        boolean z = charAt2 == '-';
        if (z) {
            charAt2 = charAt(this.bp + i2);
            i2++;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i3 = charAt2 - '0';
        while (true) {
            i = i2 + 1;
            charAt = charAt(this.bp + i2);
            if (charAt >= '0' && charAt <= '9') {
                i3 = (i3 * 10) + (charAt - '0');
                i2 = i;
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        } else if ((i3 < 0 || i > cArr.length + 14) && !(i3 == Integer.MIN_VALUE && i == 17 && z)) {
            this.matchStat = -1;
            return 0;
        } else if (charAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -i3 : i3;
        } else if (charAt == '}') {
            int i4 = i + 1;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (charAt3 == ']') {
                this.token = 15;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (charAt3 == '}') {
                this.token = 13;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -i3 : i3;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public final int[] scanFieldIntArray(char[] cArr) {
        int i;
        int i2;
        char c2;
        int[] iArr;
        boolean z;
        int i3;
        char charAt;
        char c3;
        int[] iArr2;
        this.matchStat = 0;
        int[] iArr3 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i4 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i5 = i4 + 1;
        char charAt2 = charAt(this.bp + i4);
        int[] iArr4 = new int[16];
        if (charAt2 == ']') {
            i = i5 + 1;
            c2 = charAt(this.bp + i5);
            i2 = 0;
        } else {
            int i6 = 0;
            while (true) {
                if (charAt2 == '-') {
                    charAt2 = charAt(this.bp + i5);
                    i5++;
                    z = true;
                } else {
                    z = false;
                }
                if (charAt2 < '0' || charAt2 > '9') {
                    int[] iArr5 = iArr3;
                    this.matchStat = -1;
                } else {
                    int i7 = charAt2 - '0';
                    while (true) {
                        i3 = i5 + 1;
                        charAt = charAt(this.bp + i5);
                        if (charAt >= '0' && charAt <= '9') {
                            i7 = (i7 * 10) + (charAt - '0');
                            i5 = i3;
                        }
                    }
                    if (i6 >= iArr4.length) {
                        int[] iArr6 = new int[((iArr4.length * 3) / 2)];
                        System.arraycopy(iArr4, 0, iArr6, 0, i6);
                        iArr4 = iArr6;
                    }
                    i2 = i6 + 1;
                    if (z) {
                        i7 = -i7;
                    }
                    iArr4[i6] = i7;
                    if (charAt == ',') {
                        i5 = i3 + 1;
                        c3 = charAt(this.bp + i3);
                        iArr2 = null;
                    } else if (charAt == ']') {
                        i = i3 + 1;
                        c2 = charAt(this.bp + i3);
                        break;
                    } else {
                        iArr2 = null;
                        c3 = charAt;
                        i5 = i3;
                    }
                    int i8 = i2;
                    iArr3 = iArr2;
                    charAt2 = c3;
                    i6 = i8;
                }
            }
            int[] iArr52 = iArr3;
            this.matchStat = -1;
            return iArr52;
        }
        if (i2 != iArr4.length) {
            iArr = new int[i2];
            System.arraycopy(iArr4, 0, iArr, 0, i2);
        } else {
            iArr = iArr4;
        }
        if (c2 == ',') {
            this.bp += i - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return iArr;
        } else if (c2 == '}') {
            int i9 = i + 1;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                this.bp += i9 - 1;
                next();
            } else if (charAt3 == ']') {
                this.token = 15;
                this.bp += i9 - 1;
                next();
            } else if (charAt3 == '}') {
                this.token = 13;
                this.bp += i9 - 1;
                next();
            } else if (charAt3 == 26) {
                this.bp += i9 - 1;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return iArr;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public boolean scanBoolean(char c2) {
        boolean z = false;
        this.matchStat = 0;
        char charAt = charAt(this.bp + 0);
        int i = 2;
        if (charAt == 't') {
            if (charAt(this.bp + 1) == 'r' && charAt(this.bp + 1 + 1) == 'u' && charAt(this.bp + 1 + 2) == 'e') {
                charAt = charAt(this.bp + 4);
                z = true;
                i = 5;
            } else {
                this.matchStat = -1;
                return false;
            }
        } else if (charAt == 'f') {
            if (charAt(this.bp + 1) == 'a' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 's' && charAt(this.bp + 1 + 3) == 'e') {
                charAt = charAt(this.bp + 5);
                i = 6;
            } else {
                this.matchStat = -1;
                return false;
            }
        } else if (charAt == '1') {
            charAt = charAt(this.bp + 1);
            z = true;
        } else if (charAt == '0') {
            charAt = charAt(this.bp + 1);
        } else {
            i = 1;
        }
        while (charAt != c2) {
            if (isWhitespace(charAt)) {
                charAt = charAt(this.bp + i);
                i++;
            } else {
                this.matchStat = -1;
                return z;
            }
        }
        this.bp += i;
        this.ch = charAt(this.bp);
        this.matchStat = 3;
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scanInt(char r14) {
        /*
            r13 = this;
            r0 = 0
            r13.matchStat = r0
            int r1 = r13.bp
            int r1 = r1 + r0
            char r1 = r13.charAt(r1)
            r2 = 34
            r3 = 1
            if (r1 != r2) goto L_0x0011
            r4 = 1
            goto L_0x0012
        L_0x0011:
            r4 = 0
        L_0x0012:
            r5 = 2
            if (r4 == 0) goto L_0x001e
            int r1 = r13.bp
            int r1 = r1 + r3
            char r1 = r13.charAt(r1)
            r6 = 2
            goto L_0x001f
        L_0x001e:
            r6 = 1
        L_0x001f:
            r7 = 45
            if (r1 != r7) goto L_0x0025
            r7 = 1
            goto L_0x0026
        L_0x0025:
            r7 = 0
        L_0x0026:
            if (r7 == 0) goto L_0x0032
            int r1 = r13.bp
            int r8 = r6 + 1
            int r1 = r1 + r6
            char r1 = r13.charAt(r1)
            r6 = r8
        L_0x0032:
            r8 = 16
            r9 = 3
            r10 = 48
            r11 = -1
            if (r1 < r10) goto L_0x008e
            r12 = 57
            if (r1 > r12) goto L_0x008e
            int r1 = r1 - r10
        L_0x003f:
            int r2 = r13.bp
            int r3 = r6 + 1
            int r2 = r2 + r6
            char r2 = r13.charAt(r2)
            if (r2 < r10) goto L_0x0053
            if (r2 > r12) goto L_0x0053
            int r1 = r1 * 10
            int r2 = r2 + -48
            int r1 = r1 + r2
            r6 = r3
            goto L_0x003f
        L_0x0053:
            r4 = 46
            if (r2 != r4) goto L_0x005a
            r13.matchStat = r11
            return r0
        L_0x005a:
            if (r1 >= 0) goto L_0x005f
            r13.matchStat = r11
            return r0
        L_0x005f:
            if (r2 != r14) goto L_0x0076
            int r14 = r13.bp
            int r14 = r14 + r3
            r13.bp = r14
            int r14 = r13.bp
            char r14 = r13.charAt(r14)
            r13.ch = r14
            r13.matchStat = r9
            r13.token = r8
            if (r7 == 0) goto L_0x0075
            int r1 = -r1
        L_0x0075:
            return r1
        L_0x0076:
            boolean r0 = isWhitespace(r2)
            if (r0 == 0) goto L_0x0088
            int r0 = r13.bp
            int r2 = r3 + 1
            int r0 = r0 + r3
            char r0 = r13.charAt(r0)
            r3 = r2
            r2 = r0
            goto L_0x005f
        L_0x0088:
            r13.matchStat = r11
            if (r7 == 0) goto L_0x008d
            int r1 = -r1
        L_0x008d:
            return r1
        L_0x008e:
            r14 = 110(0x6e, float:1.54E-43)
            if (r1 != r14) goto L_0x0110
            int r14 = r13.bp
            int r14 = r14 + r6
            char r14 = r13.charAt(r14)
            r1 = 117(0x75, float:1.64E-43)
            if (r14 != r1) goto L_0x0110
            int r14 = r13.bp
            int r14 = r14 + r6
            int r14 = r14 + r3
            char r14 = r13.charAt(r14)
            r1 = 108(0x6c, float:1.51E-43)
            if (r14 != r1) goto L_0x0110
            int r14 = r13.bp
            int r14 = r14 + r6
            int r14 = r14 + r5
            char r14 = r13.charAt(r14)
            if (r14 != r1) goto L_0x0110
            r14 = 5
            r13.matchStat = r14
            int r6 = r6 + r9
            int r1 = r13.bp
            int r3 = r6 + 1
            int r1 = r1 + r6
            char r1 = r13.charAt(r1)
            if (r4 == 0) goto L_0x00ce
            if (r1 != r2) goto L_0x00ce
            int r1 = r13.bp
            int r2 = r3 + 1
            int r1 = r1 + r3
            char r1 = r13.charAt(r1)
            goto L_0x00cf
        L_0x00ce:
            r2 = r3
        L_0x00cf:
            r3 = 44
            if (r1 != r3) goto L_0x00e5
            int r1 = r13.bp
            int r1 = r1 + r2
            r13.bp = r1
            int r1 = r13.bp
            char r1 = r13.charAt(r1)
            r13.ch = r1
            r13.matchStat = r14
            r13.token = r8
            return r0
        L_0x00e5:
            r3 = 93
            if (r1 != r3) goto L_0x00fd
            int r1 = r13.bp
            int r1 = r1 + r2
            r13.bp = r1
            int r1 = r13.bp
            char r1 = r13.charAt(r1)
            r13.ch = r1
            r13.matchStat = r14
            r14 = 15
            r13.token = r14
            return r0
        L_0x00fd:
            boolean r1 = isWhitespace(r1)
            if (r1 == 0) goto L_0x010d
            int r1 = r13.bp
            int r3 = r2 + 1
            int r1 = r1 + r2
            char r1 = r13.charAt(r1)
            goto L_0x00ce
        L_0x010d:
            r13.matchStat = r11
            return r0
        L_0x0110:
            r13.matchStat = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanInt(char):int");
    }

    public boolean scanFieldBoolean(char[] cArr) {
        boolean z;
        int i;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char charAt = charAt(this.bp + length);
        if (charAt == 't') {
            int i3 = i2 + 1;
            if (charAt(this.bp + i2) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i4 = i3 + 1;
            if (charAt(this.bp + i3) != 'u') {
                this.matchStat = -1;
                return false;
            }
            i = i4 + 1;
            if (charAt(this.bp + i4) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = true;
        } else if (charAt == 'f') {
            int i5 = i2 + 1;
            if (charAt(this.bp + i2) != 'a') {
                this.matchStat = -1;
                return false;
            }
            int i6 = i5 + 1;
            if (charAt(this.bp + i5) != 'l') {
                this.matchStat = -1;
                return false;
            }
            int i7 = i6 + 1;
            if (charAt(this.bp + i6) != 's') {
                this.matchStat = -1;
                return false;
            }
            int i8 = i7 + 1;
            if (charAt(this.bp + i7) != 'e') {
                this.matchStat = -1;
                return false;
            }
            i = i8;
            z = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        int i9 = i + 1;
        char charAt2 = charAt(this.bp + i);
        if (charAt2 == ',') {
            this.bp += i9;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z;
        } else if (charAt2 == '}') {
            int i10 = i9 + 1;
            char charAt3 = charAt(this.bp + i9);
            if (charAt3 == ',') {
                this.token = 16;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (charAt3 == ']') {
                this.token = 15;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (charAt3 == '}') {
                this.token = 13;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i10 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return false;
            }
            this.matchStat = 4;
            return z;
        } else {
            this.matchStat = -1;
            return false;
        }
    }

    public long scanFieldLong(char[] cArr) {
        boolean z;
        int i;
        int i2;
        char charAt;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr2.length;
        int i3 = length + 1;
        char charAt2 = charAt(this.bp + length);
        if (charAt2 == '-') {
            i = i3 + 1;
            charAt2 = charAt(this.bp + i3);
            z = true;
        } else {
            i = i3;
            z = false;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        long j = (long) (charAt2 - '0');
        while (true) {
            i2 = i + 1;
            charAt = charAt(this.bp + i);
            if (charAt >= '0' && charAt <= '9') {
                j = (j * 10) + ((long) (charAt - '0'));
                i = i2;
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (!(i2 - cArr2.length < 21 && (j >= 0 || (j == Long.MIN_VALUE && z)))) {
            this.matchStat = -1;
            return 0;
        } else if (charAt == ',') {
            this.bp += i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        } else if (charAt == '}') {
            int i4 = i2 + 1;
            char charAt3 = charAt(this.bp + i2);
            if (charAt3 == ',') {
                this.token = 16;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (charAt3 == ']') {
                this.token = 15;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (charAt3 == '}') {
                this.token = 13;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -j : j;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x0122  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long scanLong(char r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r2 = r2 + r1
            char r2 = r0.charAt(r2)
            r3 = 34
            r4 = 1
            if (r2 != r3) goto L_0x0013
            r5 = 1
            goto L_0x0014
        L_0x0013:
            r5 = 0
        L_0x0014:
            r6 = 2
            if (r5 == 0) goto L_0x0020
            int r2 = r0.bp
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r7 = 2
            goto L_0x0021
        L_0x0020:
            r7 = 1
        L_0x0021:
            r8 = 45
            if (r2 != r8) goto L_0x0027
            r8 = 1
            goto L_0x0028
        L_0x0027:
            r8 = 0
        L_0x0028:
            if (r8 == 0) goto L_0x0034
            int r2 = r0.bp
            int r9 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r7 = r9
        L_0x0034:
            r9 = 16
            r10 = 3
            r11 = 48
            r12 = -1
            r13 = 0
            if (r2 < r11) goto L_0x00cb
            r15 = 57
            if (r2 > r15) goto L_0x00cb
            int r2 = r2 - r11
            long r1 = (long) r2
        L_0x0044:
            int r6 = r0.bp
            int r17 = r7 + 1
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            if (r6 < r11) goto L_0x005c
            if (r6 > r15) goto L_0x005c
            r18 = 10
            long r1 = r1 * r18
            int r6 = r6 + -48
            long r6 = (long) r6
            long r1 = r1 + r6
            r7 = r17
            goto L_0x0044
        L_0x005c:
            r7 = 46
            if (r6 != r7) goto L_0x0063
            r0.matchStat = r12
            return r13
        L_0x0063:
            int r7 = (r1 > r13 ? 1 : (r1 == r13 ? 0 : -1))
            if (r7 >= 0) goto L_0x0073
            r18 = -9223372036854775808
            int r7 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1))
            if (r7 != 0) goto L_0x0070
            if (r8 == 0) goto L_0x0070
            goto L_0x0073
        L_0x0070:
            r16 = 0
            goto L_0x0075
        L_0x0073:
            r16 = 1
        L_0x0075:
            if (r16 == 0) goto L_0x00bd
            if (r5 == 0) goto L_0x008d
            if (r6 == r3) goto L_0x007e
            r0.matchStat = r12
            return r13
        L_0x007e:
            int r3 = r0.bp
            int r4 = r17 + 1
            int r3 = r3 + r17
            char r6 = r0.charAt(r3)
            r3 = r21
            r17 = r4
            goto L_0x008f
        L_0x008d:
            r3 = r21
        L_0x008f:
            if (r6 != r3) goto L_0x00a7
            int r3 = r0.bp
            int r3 = r3 + r17
            r0.bp = r3
            int r3 = r0.bp
            char r3 = r0.charAt(r3)
            r0.ch = r3
            r0.matchStat = r10
            r0.token = r9
            if (r8 == 0) goto L_0x00a6
            long r1 = -r1
        L_0x00a6:
            return r1
        L_0x00a7:
            boolean r4 = isWhitespace(r6)
            if (r4 == 0) goto L_0x00ba
            int r4 = r0.bp
            int r5 = r17 + 1
            int r4 = r4 + r17
            char r6 = r0.charAt(r4)
            r17 = r5
            goto L_0x008f
        L_0x00ba:
            r0.matchStat = r12
            return r1
        L_0x00bd:
            int r1 = r0.bp
            int r2 = r17 + -1
            java.lang.String r1 = r0.subString(r1, r2)
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            r2.<init>(r1)
            throw r2
        L_0x00cb:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x014d
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x014d
            int r1 = r0.bp
            int r1 = r1 + r7
            int r1 = r1 + r4
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x014d
            int r1 = r0.bp
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x014d
            r1 = 5
            r0.matchStat = r1
            int r7 = r7 + r10
            int r2 = r0.bp
            int r4 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            if (r5 == 0) goto L_0x010b
            if (r2 != r3) goto L_0x010b
            int r2 = r0.bp
            int r3 = r4 + 1
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            goto L_0x010c
        L_0x010b:
            r3 = r4
        L_0x010c:
            r4 = 44
            if (r2 != r4) goto L_0x0122
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r0.token = r9
            return r13
        L_0x0122:
            r4 = 93
            if (r2 != r4) goto L_0x013a
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 15
            r0.token = r1
            return r13
        L_0x013a:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x014a
            int r2 = r0.bp
            int r4 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            goto L_0x010b
        L_0x014a:
            r0.matchStat = r12
            return r13
        L_0x014d:
            r0.matchStat = r12
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanLong(char):long");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final float scanFieldFloat(char[] r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = 0
            r0.matchStat = r2
            boolean r3 = r19.charArrayCompare(r20)
            r4 = 0
            if (r3 != 0) goto L_0x0012
            r1 = -2
            r0.matchStat = r1
            return r4
        L_0x0012:
            int r3 = r1.length
            int r5 = r0.bp
            int r6 = r3 + 1
            int r5 = r5 + r3
            char r3 = r0.charAt(r5)
            r5 = 34
            r7 = 1
            if (r3 != r5) goto L_0x0023
            r8 = 1
            goto L_0x0024
        L_0x0023:
            r8 = 0
        L_0x0024:
            if (r8 == 0) goto L_0x0030
            int r3 = r0.bp
            int r9 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r6 = r9
        L_0x0030:
            r9 = 45
            if (r3 != r9) goto L_0x0036
            r10 = 1
            goto L_0x0037
        L_0x0036:
            r10 = 0
        L_0x0037:
            if (r10 == 0) goto L_0x0043
            int r3 = r0.bp
            int r11 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r6 = r11
        L_0x0043:
            r14 = -1
            r15 = 48
            if (r3 < r15) goto L_0x01a2
            r2 = 57
            if (r3 > r2) goto L_0x01a2
            int r3 = r3 - r15
        L_0x004d:
            int r11 = r0.bp
            int r17 = r6 + 1
            int r11 = r11 + r6
            char r6 = r0.charAt(r11)
            if (r6 < r15) goto L_0x0062
            if (r6 > r2) goto L_0x0062
            int r3 = r3 * 10
            int r6 = r6 + -48
            int r3 = r3 + r6
            r6 = r17
            goto L_0x004d
        L_0x0062:
            r11 = 46
            if (r6 != r11) goto L_0x0068
            r11 = 1
            goto L_0x0069
        L_0x0068:
            r11 = 0
        L_0x0069:
            if (r11 == 0) goto L_0x009f
            int r6 = r0.bp
            int r11 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            if (r6 < r15) goto L_0x009c
            if (r6 > r2) goto L_0x009c
            int r3 = r3 * 10
            int r6 = r6 - r15
            int r3 = r3 + r6
            r6 = 10
        L_0x007f:
            int r12 = r0.bp
            int r17 = r11 + 1
            int r12 = r12 + r11
            char r11 = r0.charAt(r12)
            if (r11 < r15) goto L_0x0096
            if (r11 > r2) goto L_0x0096
            int r3 = r3 * 10
            int r11 = r11 + -48
            int r3 = r3 + r11
            int r6 = r6 * 10
            r11 = r17
            goto L_0x007f
        L_0x0096:
            r18 = r11
            r11 = r6
            r6 = r18
            goto L_0x00a0
        L_0x009c:
            r0.matchStat = r14
            return r4
        L_0x009f:
            r11 = 1
        L_0x00a0:
            r12 = 101(0x65, float:1.42E-43)
            if (r6 == r12) goto L_0x00ac
            r12 = 69
            if (r6 != r12) goto L_0x00a9
            goto L_0x00ac
        L_0x00a9:
            r16 = 0
            goto L_0x00ae
        L_0x00ac:
            r16 = 1
        L_0x00ae:
            if (r16 == 0) goto L_0x00de
            int r6 = r0.bp
            int r12 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            r13 = 43
            if (r6 == r13) goto L_0x00c4
            if (r6 != r9) goto L_0x00c1
            goto L_0x00c4
        L_0x00c1:
            r17 = r12
            goto L_0x00cf
        L_0x00c4:
            int r6 = r0.bp
            int r9 = r12 + 1
            int r6 = r6 + r12
            char r6 = r0.charAt(r6)
        L_0x00cd:
            r17 = r9
        L_0x00cf:
            if (r6 < r15) goto L_0x00de
            if (r6 > r2) goto L_0x00de
            int r6 = r0.bp
            int r9 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            goto L_0x00cd
        L_0x00de:
            if (r8 == 0) goto L_0x00fd
            if (r6 == r5) goto L_0x00e5
            r0.matchStat = r14
            return r4
        L_0x00e5:
            int r2 = r0.bp
            int r5 = r17 + 1
            int r2 = r2 + r17
            char r6 = r0.charAt(r2)
            int r2 = r0.bp
            int r1 = r1.length
            int r2 = r2 + r1
            int r2 = r2 + r7
            int r1 = r0.bp
            int r1 = r1 + r5
            int r1 = r1 - r2
            int r1 = r1 + -2
            r17 = r5
            goto L_0x0107
        L_0x00fd:
            int r2 = r0.bp
            int r1 = r1.length
            int r2 = r2 + r1
            int r1 = r0.bp
            int r1 = r1 + r17
            int r1 = r1 - r2
            int r1 = r1 - r7
        L_0x0107:
            if (r16 != 0) goto L_0x0114
            r5 = 18
            if (r1 >= r5) goto L_0x0114
            float r1 = (float) r3
            float r2 = (float) r11
            float r1 = r1 / r2
            if (r10 == 0) goto L_0x011c
            float r1 = -r1
            goto L_0x011c
        L_0x0114:
            java.lang.String r1 = r0.subString(r2, r1)
            float r1 = java.lang.Float.parseFloat(r1)
        L_0x011c:
            r2 = 44
            if (r6 != r2) goto L_0x0136
            int r2 = r0.bp
            int r2 = r2 + r17
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r2 = 3
            r0.matchStat = r2
            r2 = 16
            r0.token = r2
            return r1
        L_0x0136:
            r2 = 16
            r3 = 125(0x7d, float:1.75E-43)
            if (r6 != r3) goto L_0x019f
            int r3 = r0.bp
            int r5 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            r6 = 44
            if (r3 != r6) goto L_0x015a
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x0198
        L_0x015a:
            r2 = 93
            if (r3 != r2) goto L_0x0170
            r2 = 15
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x0198
        L_0x0170:
            r2 = 125(0x7d, float:1.75E-43)
            if (r3 != r2) goto L_0x0186
            r2 = 13
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x0198
        L_0x0186:
            r2 = 26
            if (r3 != r2) goto L_0x019c
            int r2 = r0.bp
            int r5 = r5 - r7
            int r2 = r2 + r5
            r0.bp = r2
            r2 = 20
            r0.token = r2
            r2 = 26
            r0.ch = r2
        L_0x0198:
            r2 = 4
            r0.matchStat = r2
            return r1
        L_0x019c:
            r0.matchStat = r14
            return r4
        L_0x019f:
            r0.matchStat = r14
            return r4
        L_0x01a2:
            r1 = 110(0x6e, float:1.54E-43)
            if (r3 != r1) goto L_0x022a
            int r1 = r0.bp
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x022a
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x022a
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x022a
            r1 = 5
            r0.matchStat = r1
            int r6 = r6 + 3
            int r2 = r0.bp
            int r3 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            if (r8 == 0) goto L_0x01e4
            if (r2 != r5) goto L_0x01e4
            int r2 = r0.bp
            int r5 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r5
        L_0x01e4:
            r5 = 44
        L_0x01e6:
            if (r2 != r5) goto L_0x01fc
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r6 = 16
            r0.token = r6
            return r4
        L_0x01fc:
            r6 = 16
            r7 = 125(0x7d, float:1.75E-43)
            if (r2 != r7) goto L_0x0216
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 13
            r0.token = r1
            return r4
        L_0x0216:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x0227
            int r2 = r0.bp
            int r8 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r8
            goto L_0x01e6
        L_0x0227:
            r0.matchStat = r14
            return r4
        L_0x022a:
            r0.matchStat = r14
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloat(char[]):float");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final float scanFloat(char r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r2 = r2 + r1
            char r2 = r0.charAt(r2)
            r3 = 34
            r4 = 1
            if (r2 != r3) goto L_0x0013
            r5 = 1
            goto L_0x0014
        L_0x0013:
            r5 = 0
        L_0x0014:
            if (r5 == 0) goto L_0x001f
            int r2 = r0.bp
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r7 = 2
            goto L_0x0020
        L_0x001f:
            r7 = 1
        L_0x0020:
            r8 = 45
            if (r2 != r8) goto L_0x0026
            r9 = 1
            goto L_0x0027
        L_0x0026:
            r9 = 0
        L_0x0027:
            if (r9 == 0) goto L_0x0033
            int r2 = r0.bp
            int r10 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r7 = r10
        L_0x0033:
            r11 = 3
            r12 = 0
            r13 = -1
            r14 = 48
            if (r2 < r14) goto L_0x0132
            r15 = 57
            if (r2 > r15) goto L_0x0132
            int r2 = r2 - r14
            long r1 = (long) r2
        L_0x0040:
            int r10 = r0.bp
            int r17 = r7 + 1
            int r10 = r10 + r7
            char r7 = r0.charAt(r10)
            r18 = 10
            if (r7 < r14) goto L_0x0058
            if (r7 > r15) goto L_0x0058
            long r1 = r1 * r18
            int r7 = r7 + -48
            long r6 = (long) r7
            long r1 = r1 + r6
            r7 = r17
            goto L_0x0040
        L_0x0058:
            r20 = 1
            r6 = 46
            if (r7 != r6) goto L_0x0060
            r6 = 1
            goto L_0x0061
        L_0x0060:
            r6 = 0
        L_0x0061:
            if (r6 == 0) goto L_0x0095
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            if (r6 < r14) goto L_0x0092
            if (r6 > r15) goto L_0x0092
            long r1 = r1 * r18
            int r6 = r6 - r14
            long r3 = (long) r6
            long r1 = r1 + r3
            r20 = r18
        L_0x0078:
            int r3 = r0.bp
            int r4 = r7 + 1
            int r3 = r3 + r7
            char r7 = r0.charAt(r3)
            if (r7 < r14) goto L_0x008f
            if (r7 > r15) goto L_0x008f
            long r1 = r1 * r18
            int r7 = r7 + -48
            long r6 = (long) r7
            long r1 = r1 + r6
            long r20 = r20 * r18
            r7 = r4
            goto L_0x0078
        L_0x008f:
            r17 = r4
            goto L_0x0095
        L_0x0092:
            r0.matchStat = r13
            return r12
        L_0x0095:
            r3 = r20
            r6 = 101(0x65, float:1.42E-43)
            if (r7 == r6) goto L_0x00a3
            r6 = 69
            if (r7 != r6) goto L_0x00a0
            goto L_0x00a3
        L_0x00a0:
            r16 = 0
            goto L_0x00a5
        L_0x00a3:
            r16 = 1
        L_0x00a5:
            if (r16 == 0) goto L_0x00d7
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            r10 = 43
            if (r6 == r10) goto L_0x00bc
            if (r6 != r8) goto L_0x00b8
            goto L_0x00bc
        L_0x00b8:
            r17 = r7
            r7 = r6
            goto L_0x00c8
        L_0x00bc:
            int r6 = r0.bp
            int r8 = r7 + 1
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            r7 = r6
            r17 = r8
        L_0x00c8:
            if (r7 < r14) goto L_0x00d7
            if (r7 > r15) goto L_0x00d7
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            goto L_0x00b8
        L_0x00d7:
            if (r5 == 0) goto L_0x00f7
            r5 = 34
            if (r7 == r5) goto L_0x00e0
            r0.matchStat = r13
            return r12
        L_0x00e0:
            int r5 = r0.bp
            int r6 = r17 + 1
            int r5 = r5 + r17
            char r7 = r0.charAt(r5)
            int r5 = r0.bp
            r8 = 1
            int r5 = r5 + r8
            int r8 = r0.bp
            int r8 = r8 + r6
            int r8 = r8 - r5
            r10 = 2
            int r8 = r8 - r10
            r17 = r6
            goto L_0x0101
        L_0x00f7:
            r8 = 1
            int r5 = r0.bp
            int r6 = r0.bp
            int r6 = r6 + r17
            int r6 = r6 - r5
            int r8 = r6 + -1
        L_0x0101:
            if (r16 != 0) goto L_0x010e
            r6 = 20
            if (r8 >= r6) goto L_0x010e
            float r1 = (float) r1
            float r2 = (float) r3
            float r1 = r1 / r2
            if (r9 == 0) goto L_0x0116
            float r1 = -r1
            goto L_0x0116
        L_0x010e:
            java.lang.String r1 = r0.subString(r5, r8)
            float r1 = java.lang.Float.parseFloat(r1)
        L_0x0116:
            r2 = r23
            if (r7 != r2) goto L_0x012f
            int r2 = r0.bp
            int r2 = r2 + r17
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r11
            r2 = 16
            r0.token = r2
            return r1
        L_0x012f:
            r0.matchStat = r13
            return r1
        L_0x0132:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x01bc
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01bc
            int r1 = r0.bp
            int r1 = r1 + r7
            r2 = 1
            int r1 = r1 + r2
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01bc
            int r1 = r0.bp
            int r1 = r1 + r7
            r3 = 2
            int r1 = r1 + r3
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01bc
            r1 = 5
            r0.matchStat = r1
            int r7 = r7 + r11
            int r2 = r0.bp
            int r3 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            if (r5 == 0) goto L_0x0176
            r4 = 34
            if (r2 != r4) goto L_0x0176
            int r2 = r0.bp
            int r4 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r4
        L_0x0176:
            r4 = 44
            if (r2 != r4) goto L_0x018e
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r4 = 16
            r0.token = r4
            return r12
        L_0x018e:
            r4 = 16
            r5 = 93
            if (r2 != r5) goto L_0x01a8
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 15
            r0.token = r1
            return r12
        L_0x01a8:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01b9
            int r2 = r0.bp
            int r5 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r5
            goto L_0x0176
        L_0x01b9:
            r0.matchStat = r13
            return r12
        L_0x01bc:
            r0.matchStat = r13
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFloat(char):float");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public double scanDouble(char r24) {
        /*
            r23 = this;
            r0 = r23
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r2 = r2 + r1
            char r2 = r0.charAt(r2)
            r3 = 34
            r4 = 1
            if (r2 != r3) goto L_0x0013
            r5 = 1
            goto L_0x0014
        L_0x0013:
            r5 = 0
        L_0x0014:
            if (r5 == 0) goto L_0x001f
            int r2 = r0.bp
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r7 = 2
            goto L_0x0020
        L_0x001f:
            r7 = 1
        L_0x0020:
            r8 = 45
            if (r2 != r8) goto L_0x0026
            r9 = 1
            goto L_0x0027
        L_0x0026:
            r9 = 0
        L_0x0027:
            if (r9 == 0) goto L_0x0033
            int r2 = r0.bp
            int r10 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r7 = r10
        L_0x0033:
            r12 = 0
            r14 = -1
            r15 = 48
            if (r2 < r15) goto L_0x0146
            r1 = 57
            if (r2 > r1) goto L_0x0146
            int r2 = r2 - r15
            long r10 = (long) r2
        L_0x0040:
            int r2 = r0.bp
            int r17 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r18 = 10
            if (r2 < r15) goto L_0x0058
            if (r2 > r1) goto L_0x0058
            long r10 = r10 * r18
            int r2 = r2 + -48
            long r6 = (long) r2
            long r10 = r10 + r6
            r7 = r17
            goto L_0x0040
        L_0x0058:
            r6 = 46
            if (r2 != r6) goto L_0x005e
            r6 = 1
            goto L_0x005f
        L_0x005e:
            r6 = 0
        L_0x005f:
            if (r6 == 0) goto L_0x00a0
            int r2 = r0.bp
            int r6 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            if (r2 < r15) goto L_0x009d
            if (r2 > r1) goto L_0x009d
            long r10 = r10 * r18
            int r2 = r2 - r15
            long r3 = (long) r2
            long r10 = r10 + r3
            r2 = r18
        L_0x0076:
            int r4 = r0.bp
            int r7 = r6 + 1
            int r4 = r4 + r6
            char r4 = r0.charAt(r4)
            if (r4 < r15) goto L_0x0093
            if (r4 > r1) goto L_0x0093
            long r10 = r10 * r18
            int r4 = r4 + -48
            r20 = r9
            long r8 = (long) r4
            long r10 = r10 + r8
            long r2 = r2 * r18
            r6 = r7
            r9 = r20
            r8 = 45
            goto L_0x0076
        L_0x0093:
            r20 = r9
            r17 = r7
            r21 = r2
            r2 = r4
            r3 = r21
            goto L_0x00a4
        L_0x009d:
            r0.matchStat = r14
            return r12
        L_0x00a0:
            r20 = r9
            r3 = 1
        L_0x00a4:
            r6 = 101(0x65, float:1.42E-43)
            if (r2 == r6) goto L_0x00b0
            r6 = 69
            if (r2 != r6) goto L_0x00ad
            goto L_0x00b0
        L_0x00ad:
            r16 = 0
            goto L_0x00b2
        L_0x00b0:
            r16 = 1
        L_0x00b2:
            if (r16 == 0) goto L_0x00e4
            int r2 = r0.bp
            int r6 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            r7 = 43
            if (r2 == r7) goto L_0x00ca
            r7 = 45
            if (r2 != r7) goto L_0x00c7
            goto L_0x00ca
        L_0x00c7:
            r17 = r6
            goto L_0x00d5
        L_0x00ca:
            int r2 = r0.bp
            int r7 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            r17 = r7
        L_0x00d5:
            if (r2 < r15) goto L_0x00e4
            if (r2 > r1) goto L_0x00e4
            int r2 = r0.bp
            int r6 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            goto L_0x00c7
        L_0x00e4:
            if (r5 == 0) goto L_0x0102
            r1 = 34
            if (r2 == r1) goto L_0x00ed
            r0.matchStat = r14
            return r12
        L_0x00ed:
            int r1 = r0.bp
            int r2 = r17 + 1
            int r1 = r1 + r17
            char r1 = r0.charAt(r1)
            int r5 = r0.bp
            r6 = 1
            int r5 = r5 + r6
            int r6 = r0.bp
            int r6 = r6 + r2
            int r6 = r6 - r5
            r7 = 2
            int r6 = r6 - r7
            goto L_0x010f
        L_0x0102:
            r6 = 1
            int r5 = r0.bp
            int r1 = r0.bp
            int r1 = r1 + r17
            int r1 = r1 - r5
            int r6 = r1 + -1
            r1 = r2
            r2 = r17
        L_0x010f:
            if (r16 != 0) goto L_0x0122
            r7 = 20
            if (r6 >= r7) goto L_0x0122
            double r5 = (double) r10
            double r3 = (double) r3
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r3)
            double r5 = r5 / r3
            if (r20 == 0) goto L_0x012a
            double r5 = -r5
            goto L_0x012a
        L_0x0122:
            java.lang.String r3 = r0.subString(r5, r6)
            double r5 = java.lang.Double.parseDouble(r3)
        L_0x012a:
            r3 = r24
            if (r1 != r3) goto L_0x0143
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r1 = 3
            r0.matchStat = r1
            r1 = 16
            r0.token = r1
            return r5
        L_0x0143:
            r0.matchStat = r14
            return r5
        L_0x0146:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x01d1
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01d1
            int r1 = r0.bp
            int r1 = r1 + r7
            r2 = 1
            int r1 = r1 + r2
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01d1
            int r1 = r0.bp
            int r1 = r1 + r7
            r3 = 2
            int r1 = r1 + r3
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01d1
            r1 = 5
            r0.matchStat = r1
            r2 = 3
            int r7 = r7 + r2
            int r2 = r0.bp
            int r3 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            if (r5 == 0) goto L_0x018b
            r4 = 34
            if (r2 != r4) goto L_0x018b
            int r2 = r0.bp
            int r4 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r4
        L_0x018b:
            r4 = 44
            if (r2 != r4) goto L_0x01a3
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r4 = 16
            r0.token = r4
            return r12
        L_0x01a3:
            r4 = 16
            r5 = 93
            if (r2 != r5) goto L_0x01bd
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 15
            r0.token = r1
            return r12
        L_0x01bd:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01ce
            int r2 = r0.bp
            int r5 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r5
            goto L_0x018b
        L_0x01ce:
            r0.matchStat = r14
            return r12
        L_0x01d1:
            r0.matchStat = r14
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDouble(char):double");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public java.math.BigDecimal scanDecimal(char r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r2 = r2 + r1
            char r2 = r0.charAt(r2)
            r3 = 34
            r4 = 1
            if (r2 != r3) goto L_0x0013
            r5 = 1
            goto L_0x0014
        L_0x0013:
            r5 = 0
        L_0x0014:
            r6 = 2
            if (r5 == 0) goto L_0x0020
            int r2 = r0.bp
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r7 = 2
            goto L_0x0021
        L_0x0020:
            r7 = 1
        L_0x0021:
            r8 = 45
            if (r2 != r8) goto L_0x0027
            r9 = 1
            goto L_0x0028
        L_0x0027:
            r9 = 0
        L_0x0028:
            if (r9 == 0) goto L_0x0034
            int r2 = r0.bp
            int r9 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r7 = r9
        L_0x0034:
            r10 = 3
            r11 = 16
            r12 = 44
            r13 = 48
            r14 = -1
            r15 = 0
            if (r2 < r13) goto L_0x0167
            r1 = 57
            if (r2 > r1) goto L_0x0167
        L_0x0043:
            int r2 = r0.bp
            int r17 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            if (r2 < r13) goto L_0x0053
            if (r2 > r1) goto L_0x0053
            r7 = r17
            goto L_0x0043
        L_0x0053:
            r7 = 46
            if (r2 != r7) goto L_0x0059
            r7 = 1
            goto L_0x005a
        L_0x0059:
            r7 = 0
        L_0x005a:
            if (r7 == 0) goto L_0x007d
            int r2 = r0.bp
            int r7 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            if (r2 < r13) goto L_0x007a
            if (r2 > r1) goto L_0x007a
        L_0x006a:
            int r2 = r0.bp
            int r17 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            if (r2 < r13) goto L_0x007d
            if (r2 > r1) goto L_0x007d
            r7 = r17
            goto L_0x006a
        L_0x007a:
            r0.matchStat = r14
            return r15
        L_0x007d:
            r7 = 101(0x65, float:1.42E-43)
            if (r2 == r7) goto L_0x0089
            r7 = 69
            if (r2 != r7) goto L_0x0086
            goto L_0x0089
        L_0x0086:
            r16 = 0
            goto L_0x008b
        L_0x0089:
            r16 = 1
        L_0x008b:
            if (r16 == 0) goto L_0x00bb
            int r2 = r0.bp
            int r7 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            r9 = 43
            if (r2 == r9) goto L_0x00a1
            if (r2 != r8) goto L_0x009e
            goto L_0x00a1
        L_0x009e:
            r17 = r7
            goto L_0x00ac
        L_0x00a1:
            int r2 = r0.bp
            int r8 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r17 = r8
        L_0x00ac:
            if (r2 < r13) goto L_0x00bb
            if (r2 > r1) goto L_0x00bb
            int r2 = r0.bp
            int r7 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            goto L_0x009e
        L_0x00bb:
            if (r5 == 0) goto L_0x00d7
            if (r2 == r3) goto L_0x00c2
            r0.matchStat = r14
            return r15
        L_0x00c2:
            int r1 = r0.bp
            int r2 = r17 + 1
            int r1 = r1 + r17
            char r1 = r0.charAt(r1)
            int r3 = r0.bp
            int r3 = r3 + r4
            int r5 = r0.bp
            int r5 = r5 + r2
            int r5 = r5 - r3
            int r5 = r5 - r6
            r17 = r2
            goto L_0x00e1
        L_0x00d7:
            int r3 = r0.bp
            int r1 = r0.bp
            int r1 = r1 + r17
            int r1 = r1 - r3
            int r5 = r1 + -1
            r1 = r2
        L_0x00e1:
            char[] r2 = r0.sub_chars(r3, r5)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r3.<init>(r2)
            if (r1 != r12) goto L_0x00ff
            int r1 = r0.bp
            int r1 = r1 + r17
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r10
            r0.token = r11
            return r3
        L_0x00ff:
            r2 = 93
            if (r1 != r2) goto L_0x0164
            int r1 = r0.bp
            int r2 = r17 + 1
            int r1 = r1 + r17
            char r1 = r0.charAt(r1)
            if (r1 != r12) goto L_0x011f
            r0.token = r11
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x015d
        L_0x011f:
            r5 = 93
            if (r1 != r5) goto L_0x0135
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x015d
        L_0x0135:
            r5 = 125(0x7d, float:1.75E-43)
            if (r1 != r5) goto L_0x014b
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x015d
        L_0x014b:
            r5 = 26
            if (r1 != r5) goto L_0x0161
            r1 = 20
            r0.token = r1
            int r1 = r0.bp
            int r2 = r2 - r4
            int r1 = r1 + r2
            r0.bp = r1
            r1 = 26
            r0.ch = r1
        L_0x015d:
            r1 = 4
            r0.matchStat = r1
            return r3
        L_0x0161:
            r0.matchStat = r14
            return r15
        L_0x0164:
            r0.matchStat = r14
            return r15
        L_0x0167:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x01e8
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01e8
            int r1 = r0.bp
            int r1 = r1 + r7
            int r1 = r1 + r4
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01e8
            int r1 = r0.bp
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01e8
            r1 = 5
            r0.matchStat = r1
            int r7 = r7 + r10
            int r2 = r0.bp
            int r4 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            if (r5 == 0) goto L_0x01a7
            if (r2 != r3) goto L_0x01a7
            int r2 = r0.bp
            int r3 = r4 + 1
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            goto L_0x01a8
        L_0x01a7:
            r3 = r4
        L_0x01a8:
            if (r2 != r12) goto L_0x01bc
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r0.token = r11
            return r15
        L_0x01bc:
            r4 = 125(0x7d, float:1.75E-43)
            if (r2 != r4) goto L_0x01d4
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 13
            r0.token = r1
            return r15
        L_0x01d4:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01e5
            int r2 = r0.bp
            int r5 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r5
            goto L_0x01a8
        L_0x01e5:
            r0.matchStat = r14
            return r15
        L_0x01e8:
            r0.matchStat = r14
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDecimal(char):java.math.BigDecimal");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final float[] scanFieldFloatArray(char[] r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = 0
            r0.matchStat = r1
            boolean r2 = r18.charArrayCompare(r19)
            r3 = -2
            r4 = 0
            if (r2 != 0) goto L_0x0010
            r0.matchStat = r3
            return r4
        L_0x0010:
            r2 = r19
            int r2 = r2.length
            int r5 = r0.bp
            int r6 = r2 + 1
            int r5 = r5 + r2
            char r2 = r0.charAt(r5)
            r5 = 91
            if (r2 == r5) goto L_0x0023
            r0.matchStat = r3
            return r4
        L_0x0023:
            int r2 = r0.bp
            int r3 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            r5 = 16
            float[] r6 = new float[r5]
            r7 = r6
            r6 = 0
        L_0x0032:
            int r8 = r0.bp
            int r8 = r8 + r3
            r9 = 1
            int r8 = r8 - r9
            r10 = 45
            if (r2 != r10) goto L_0x003d
            r11 = 1
            goto L_0x003e
        L_0x003d:
            r11 = 0
        L_0x003e:
            if (r11 == 0) goto L_0x004a
            int r2 = r0.bp
            int r12 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r12
        L_0x004a:
            r12 = -1
            r13 = 48
            if (r2 < r13) goto L_0x01b3
            r14 = 57
            if (r2 > r14) goto L_0x01b3
            int r2 = r2 + -48
        L_0x0055:
            int r15 = r0.bp
            int r16 = r3 + 1
            int r15 = r15 + r3
            char r3 = r0.charAt(r15)
            if (r3 < r13) goto L_0x006a
            if (r3 > r14) goto L_0x006a
            int r2 = r2 * 10
            int r3 = r3 + -48
            int r2 = r2 + r3
            r3 = r16
            goto L_0x0055
        L_0x006a:
            r15 = 46
            if (r3 != r15) goto L_0x0070
            r15 = 1
            goto L_0x0071
        L_0x0070:
            r15 = 0
        L_0x0071:
            r5 = 10
            if (r15 == 0) goto L_0x00a4
            int r3 = r0.bp
            int r15 = r16 + 1
            int r3 = r3 + r16
            char r3 = r0.charAt(r3)
            if (r3 < r13) goto L_0x00a1
            if (r3 > r14) goto L_0x00a1
            int r2 = r2 * 10
            int r3 = r3 + -48
            int r2 = r2 + r3
            r3 = 10
        L_0x008a:
            int r1 = r0.bp
            int r16 = r15 + 1
            int r1 = r1 + r15
            char r1 = r0.charAt(r1)
            if (r1 < r13) goto L_0x00a6
            if (r1 > r14) goto L_0x00a6
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            int r3 = r3 * 10
            r15 = r16
            goto L_0x008a
        L_0x00a1:
            r0.matchStat = r12
            return r4
        L_0x00a4:
            r1 = r3
            r3 = 1
        L_0x00a6:
            r15 = 101(0x65, float:1.42E-43)
            if (r1 == r15) goto L_0x00b1
            r15 = 69
            if (r1 != r15) goto L_0x00af
            goto L_0x00b1
        L_0x00af:
            r15 = 0
            goto L_0x00b2
        L_0x00b1:
            r15 = 1
        L_0x00b2:
            if (r15 == 0) goto L_0x00e3
            int r1 = r0.bp
            int r17 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            r4 = 43
            if (r1 == r4) goto L_0x00c8
            if (r1 != r10) goto L_0x00c5
            goto L_0x00c8
        L_0x00c5:
            r16 = r17
            goto L_0x00d4
        L_0x00c8:
            int r1 = r0.bp
            int r4 = r17 + 1
            int r1 = r1 + r17
            char r1 = r0.charAt(r1)
        L_0x00d2:
            r16 = r4
        L_0x00d4:
            if (r1 < r13) goto L_0x00e3
            if (r1 > r14) goto L_0x00e3
            int r1 = r0.bp
            int r4 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            goto L_0x00d2
        L_0x00e3:
            int r4 = r0.bp
            int r4 = r4 + r16
            int r4 = r4 - r8
            int r4 = r4 - r9
            if (r15 != 0) goto L_0x00f4
            if (r4 >= r5) goto L_0x00f4
            float r2 = (float) r2
            float r3 = (float) r3
            float r2 = r2 / r3
            if (r11 == 0) goto L_0x00fc
            float r2 = -r2
            goto L_0x00fc
        L_0x00f4:
            java.lang.String r2 = r0.subString(r8, r4)
            float r2 = java.lang.Float.parseFloat(r2)
        L_0x00fc:
            int r3 = r7.length
            r4 = 3
            if (r6 < r3) goto L_0x010c
            int r3 = r7.length
            int r3 = r3 * 3
            int r3 = r3 / 2
            float[] r3 = new float[r3]
            r5 = 0
            java.lang.System.arraycopy(r7, r5, r3, r5, r6)
            r7 = r3
        L_0x010c:
            int r3 = r6 + 1
            r7[r6] = r2
            r2 = 44
            if (r1 != r2) goto L_0x0126
            int r1 = r0.bp
            int r2 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            r16 = r2
        L_0x0120:
            r2 = 0
            r4 = 16
            r10 = 0
            goto L_0x01a9
        L_0x0126:
            r5 = 93
            if (r1 != r5) goto L_0x0120
            int r1 = r0.bp
            int r6 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            int r8 = r7.length
            if (r3 == r8) goto L_0x013e
            float[] r8 = new float[r3]
            r10 = 0
            java.lang.System.arraycopy(r7, r10, r8, r10, r3)
            r7 = r8
        L_0x013e:
            if (r1 != r2) goto L_0x0150
            int r1 = r0.bp
            int r6 = r6 - r9
            int r1 = r1 + r6
            r0.bp = r1
            r18.next()
            r0.matchStat = r4
            r4 = 16
            r0.token = r4
            return r7
        L_0x0150:
            r4 = 16
            r3 = 125(0x7d, float:1.75E-43)
            if (r1 != r3) goto L_0x01a5
            int r1 = r0.bp
            int r8 = r6 + 1
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x016d
            r0.token = r4
            int r1 = r0.bp
            int r8 = r8 - r9
            int r1 = r1 + r8
            r0.bp = r1
            r18.next()
            goto L_0x019d
        L_0x016d:
            if (r1 != r5) goto L_0x017d
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r8 = r8 - r9
            int r1 = r1 + r8
            r0.bp = r1
            r18.next()
            goto L_0x019d
        L_0x017d:
            if (r1 != r3) goto L_0x018d
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r8 = r8 - r9
            int r1 = r1 + r8
            r0.bp = r1
            r18.next()
            goto L_0x019d
        L_0x018d:
            r2 = 26
            if (r1 != r2) goto L_0x01a1
            int r1 = r0.bp
            int r8 = r8 - r9
            int r1 = r1 + r8
            r0.bp = r1
            r1 = 20
            r0.token = r1
            r0.ch = r2
        L_0x019d:
            r1 = 4
            r0.matchStat = r1
            return r7
        L_0x01a1:
            r0.matchStat = r12
            r2 = 0
            return r2
        L_0x01a5:
            r2 = 0
            r0.matchStat = r12
            return r2
        L_0x01a9:
            r4 = r2
            r6 = r3
            r3 = r16
            r5 = 16
            r2 = r1
            r1 = 0
            goto L_0x0032
        L_0x01b3:
            r2 = r4
            r0.matchStat = r12
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray(char[]):float[]");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final float[][] scanFieldFloatArray2(char[] r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = 0
            r0.matchStat = r1
            boolean r2 = r19.charArrayCompare(r20)
            r3 = -2
            r4 = 0
            if (r2 != 0) goto L_0x0012
            r0.matchStat = r3
            float[][] r4 = (float[][]) r4
            return r4
        L_0x0012:
            r2 = r20
            int r2 = r2.length
            int r5 = r0.bp
            int r6 = r2 + 1
            int r5 = r5 + r2
            char r2 = r0.charAt(r5)
            r5 = 91
            if (r2 == r5) goto L_0x0027
            r0.matchStat = r3
            float[][] r4 = (float[][]) r4
            return r4
        L_0x0027:
            int r2 = r0.bp
            int r3 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            r6 = 16
            float[][] r7 = new float[r6][]
            r8 = r7
            r7 = 0
        L_0x0036:
            if (r2 != r5) goto L_0x022e
            int r2 = r0.bp
            int r9 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            float[] r3 = new float[r6]
            r10 = 0
        L_0x0044:
            int r11 = r0.bp
            int r11 = r11 + r9
            r12 = 1
            int r11 = r11 - r12
            r13 = 45
            if (r2 != r13) goto L_0x004f
            r14 = 1
            goto L_0x0050
        L_0x004f:
            r14 = 0
        L_0x0050:
            if (r14 == 0) goto L_0x005c
            int r2 = r0.bp
            int r15 = r9 + 1
            int r2 = r2 + r9
            char r2 = r0.charAt(r2)
            goto L_0x005d
        L_0x005c:
            r15 = r9
        L_0x005d:
            r9 = -1
            r5 = 48
            if (r2 < r5) goto L_0x0227
            r6 = 57
            if (r2 > r6) goto L_0x0227
            int r2 = r2 + -48
        L_0x0068:
            int r1 = r0.bp
            int r16 = r15 + 1
            int r1 = r1 + r15
            char r1 = r0.charAt(r1)
            if (r1 < r5) goto L_0x007d
            if (r1 > r6) goto L_0x007d
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            r15 = r16
            goto L_0x0068
        L_0x007d:
            r15 = 46
            if (r1 != r15) goto L_0x00b8
            int r1 = r0.bp
            int r15 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            if (r1 < r5) goto L_0x00b3
            if (r1 > r6) goto L_0x00b3
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            r1 = 10
        L_0x0096:
            int r12 = r0.bp
            int r16 = r15 + 1
            int r12 = r12 + r15
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x00ad
            if (r12 > r6) goto L_0x00ad
            int r2 = r2 * 10
            int r12 = r12 + -48
            int r2 = r2 + r12
            int r1 = r1 * 10
            r15 = r16
            goto L_0x0096
        L_0x00ad:
            r18 = r12
            r12 = r1
            r1 = r18
            goto L_0x00b9
        L_0x00b3:
            r0.matchStat = r9
            float[][] r4 = (float[][]) r4
            return r4
        L_0x00b8:
            r12 = 1
        L_0x00b9:
            r15 = 101(0x65, float:1.42E-43)
            if (r1 == r15) goto L_0x00c4
            r15 = 69
            if (r1 != r15) goto L_0x00c2
            goto L_0x00c4
        L_0x00c2:
            r15 = 0
            goto L_0x00c5
        L_0x00c4:
            r15 = 1
        L_0x00c5:
            if (r15 == 0) goto L_0x00f6
            int r1 = r0.bp
            int r17 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            r4 = 43
            if (r1 == r4) goto L_0x00db
            if (r1 != r13) goto L_0x00d8
            goto L_0x00db
        L_0x00d8:
            r16 = r17
            goto L_0x00e7
        L_0x00db:
            int r1 = r0.bp
            int r4 = r17 + 1
            int r1 = r1 + r17
            char r1 = r0.charAt(r1)
        L_0x00e5:
            r16 = r4
        L_0x00e7:
            if (r1 < r5) goto L_0x00f6
            if (r1 > r6) goto L_0x00f6
            int r1 = r0.bp
            int r4 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            goto L_0x00e5
        L_0x00f6:
            int r4 = r0.bp
            int r4 = r4 + r16
            int r4 = r4 - r11
            r5 = 1
            int r4 = r4 - r5
            if (r15 != 0) goto L_0x010a
            r5 = 10
            if (r4 >= r5) goto L_0x010a
            float r2 = (float) r2
            float r4 = (float) r12
            float r2 = r2 / r4
            if (r14 == 0) goto L_0x0112
            float r2 = -r2
            goto L_0x0112
        L_0x010a:
            java.lang.String r2 = r0.subString(r11, r4)
            float r2 = java.lang.Float.parseFloat(r2)
        L_0x0112:
            int r4 = r3.length
            r5 = 3
            if (r10 < r4) goto L_0x0122
            int r4 = r3.length
            int r4 = r4 * 3
            int r4 = r4 / 2
            float[] r4 = new float[r4]
            r6 = 0
            java.lang.System.arraycopy(r3, r6, r4, r6, r10)
            r3 = r4
        L_0x0122:
            int r4 = r10 + 1
            r3[r10] = r2
            r2 = 44
            if (r1 != r2) goto L_0x013c
            int r1 = r0.bp
            int r2 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            r9 = r2
            r5 = 16
            r6 = 0
            r11 = 0
            r2 = r1
            goto L_0x021e
        L_0x013c:
            r6 = 93
            if (r1 != r6) goto L_0x0217
            int r1 = r0.bp
            int r10 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            int r11 = r3.length
            if (r4 == r11) goto L_0x0155
            float[] r11 = new float[r4]
            r12 = 0
            java.lang.System.arraycopy(r3, r12, r11, r12, r4)
            r3 = r11
            goto L_0x0156
        L_0x0155:
            r12 = 0
        L_0x0156:
            int r11 = r8.length
            if (r7 < r11) goto L_0x0163
            int r8 = r8.length
            int r8 = r8 * 3
            int r8 = r8 / 2
            float[][] r8 = new float[r8][]
            java.lang.System.arraycopy(r3, r12, r8, r12, r4)
        L_0x0163:
            int r4 = r7 + 1
            r8[r7] = r3
            if (r1 != r2) goto L_0x017a
            int r1 = r0.bp
            int r2 = r10 + 1
            int r1 = r1 + r10
            char r1 = r0.charAt(r1)
            r3 = r2
            r5 = 16
            r6 = 0
            r11 = 0
            r2 = r1
            goto L_0x020e
        L_0x017a:
            if (r1 != r6) goto L_0x0208
            int r1 = r0.bp
            int r3 = r10 + 1
            int r1 = r1 + r10
            char r1 = r0.charAt(r1)
            int r7 = r8.length
            if (r4 == r7) goto L_0x018f
            float[][] r7 = new float[r4][]
            r11 = 0
            java.lang.System.arraycopy(r8, r11, r7, r11, r4)
            goto L_0x0190
        L_0x018f:
            r7 = r8
        L_0x0190:
            if (r1 != r2) goto L_0x01a3
            int r1 = r0.bp
            r2 = 1
            int r3 = r3 - r2
            int r1 = r1 + r3
            r0.bp = r1
            r19.next()
            r0.matchStat = r5
            r5 = 16
            r0.token = r5
            return r7
        L_0x01a3:
            r5 = 16
            r4 = 125(0x7d, float:1.75E-43)
            if (r1 != r4) goto L_0x0201
            int r1 = r0.bp
            int r4 = r3 + 1
            int r1 = r1 + r3
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01c1
            r0.token = r5
            int r1 = r0.bp
            r2 = 1
            int r4 = r4 - r2
            int r1 = r1 + r4
            r0.bp = r1
            r19.next()
            goto L_0x01f6
        L_0x01c1:
            r2 = 1
            if (r1 != r6) goto L_0x01d2
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r4 = r4 - r2
            int r1 = r1 + r4
            r0.bp = r1
            r19.next()
            goto L_0x01f6
        L_0x01d2:
            r3 = 125(0x7d, float:1.75E-43)
            if (r1 != r3) goto L_0x01e4
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r4 = r4 - r2
            int r1 = r1 + r4
            r0.bp = r1
            r19.next()
            goto L_0x01f6
        L_0x01e4:
            r3 = 26
            if (r1 != r3) goto L_0x01fa
            int r1 = r0.bp
            int r4 = r4 - r2
            int r1 = r1 + r4
            r0.bp = r1
            r1 = 20
            r0.token = r1
            r1 = 26
            r0.ch = r1
        L_0x01f6:
            r1 = 4
            r0.matchStat = r1
            return r7
        L_0x01fa:
            r0.matchStat = r9
            r6 = 0
            r4 = r6
            float[][] r4 = (float[][]) r4
            return r4
        L_0x0201:
            r6 = 0
            r0.matchStat = r9
            r4 = r6
            float[][] r4 = (float[][]) r4
            return r4
        L_0x0208:
            r5 = 16
            r6 = 0
            r11 = 0
            r2 = r1
            r3 = r10
        L_0x020e:
            r7 = r4
            r4 = r6
            r1 = 0
            r5 = 91
            r6 = 16
            goto L_0x0036
        L_0x0217:
            r5 = 16
            r6 = 0
            r11 = 0
            r2 = r1
            r9 = r16
        L_0x021e:
            r10 = r4
            r4 = r6
            r1 = 0
            r5 = 91
            r6 = 16
            goto L_0x0044
        L_0x0227:
            r6 = r4
            r0.matchStat = r9
            r4 = r6
            float[][] r4 = (float[][]) r4
            return r4
        L_0x022e:
            r5 = 91
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray2(char[]):float[][]");
    }

    public final double scanFieldDouble(char[] cArr) {
        long j;
        int i;
        char charAt;
        boolean z;
        int i2;
        long j2;
        char c2;
        int i3;
        int i4;
        char c3;
        int i5;
        double d;
        int i6;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0d;
        }
        int length = cArr2.length;
        int i7 = length + 1;
        char charAt2 = charAt(this.bp + length);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(this.bp + i7);
            i7++;
        }
        boolean z3 = charAt2 == '-';
        if (z3) {
            charAt2 = charAt(this.bp + i7);
            i7++;
        }
        char c4 = '0';
        if (charAt2 < '0' || charAt2 > '9') {
            boolean z4 = z2;
            if (charAt2 == 'n' && charAt(this.bp + i7) == 'u' && charAt(this.bp + i7 + 1) == 'l' && charAt(this.bp + i7 + 2) == 'l') {
                this.matchStat = 5;
                int i8 = i7 + 3;
                int i9 = i8 + 1;
                char charAt3 = charAt(this.bp + i8);
                if (z4 && charAt3 == '\"') {
                    charAt3 = charAt(this.bp + i9);
                    i9++;
                }
                while (charAt3 != ',') {
                    if (charAt3 == '}') {
                        this.bp += i9;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 13;
                        return 0.0d;
                    } else if (isWhitespace(charAt3)) {
                        charAt3 = charAt(this.bp + i9);
                        i9++;
                    } else {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                }
                this.bp += i9;
                this.ch = charAt(this.bp);
                this.matchStat = 5;
                this.token = 16;
                return 0.0d;
            }
            this.matchStat = -1;
            return 0.0d;
        }
        long j3 = (long) (charAt2 - '0');
        while (true) {
            i = i7 + 1;
            charAt = charAt(this.bp + i7);
            if (charAt >= '0' && charAt <= '9') {
                j3 = (j * 10) + ((long) (charAt - '0'));
                i7 = i;
            }
        }
        if (charAt == '.') {
            int i10 = i + 1;
            char charAt4 = charAt(this.bp + i);
            if (charAt4 < '0' || charAt4 > '9') {
                this.matchStat = -1;
                return 0.0d;
            }
            z = z2;
            j = (j * 10) + ((long) (charAt4 - '0'));
            j2 = 10;
            while (true) {
                i6 = i10 + 1;
                charAt = charAt(this.bp + i10);
                if (charAt < c4 || charAt > '9') {
                    i = i6;
                } else {
                    j = (j * 10) + ((long) (charAt - '0'));
                    j2 *= 10;
                    i10 = i6;
                    c4 = '0';
                }
            }
            i = i6;
        } else {
            z = z2;
            j2 = 1;
        }
        boolean z5 = c2 == 'e' || c2 == 'E';
        if (z5) {
            int i11 = i2 + 1;
            char charAt5 = charAt(this.bp + i2);
            if (charAt5 == '+' || charAt5 == '-') {
                int i12 = i11 + 1;
                c2 = charAt(this.bp + i11);
                i2 = i12;
            } else {
                i2 = i11;
                c2 = charAt5;
            }
            while (c2 >= '0' && c2 <= '9') {
                c2 = charAt(this.bp + i2);
                i2++;
            }
        }
        if (!z) {
            i3 = this.bp + cArr2.length;
            i5 = ((this.bp + i2) - i3) - 1;
            c3 = c2;
            i4 = i2;
        } else if (c2 != '\"') {
            this.matchStat = -1;
            return 0.0d;
        } else {
            i4 = i2 + 1;
            c3 = charAt(this.bp + i2);
            i3 = this.bp + cArr2.length + 1;
            i5 = ((this.bp + i4) - i3) - 2;
        }
        if (z5 || i5 >= 20) {
            d = Double.parseDouble(subString(i3, i5));
        } else {
            double d2 = (double) j;
            double d3 = (double) j2;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d = d2 / d3;
            if (z3) {
                d = -d;
            }
        }
        if (c3 == ',') {
            this.bp += i4;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return d;
        } else if (c3 == '}') {
            int i13 = i4 + 1;
            char charAt6 = charAt(this.bp + i4);
            if (charAt6 == ',') {
                this.token = 16;
                this.bp += i13;
                this.ch = charAt(this.bp);
            } else if (charAt6 == ']') {
                this.token = 15;
                this.bp += i13;
                this.ch = charAt(this.bp);
            } else if (charAt6 == '}') {
                this.token = 13;
                this.bp += i13;
                this.ch = charAt(this.bp);
            } else if (charAt6 == 26) {
                this.token = 20;
                this.bp += i13 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0.0d;
            }
            this.matchStat = 4;
            return d;
        } else {
            this.matchStat = -1;
            return 0.0d;
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public java.math.BigDecimal scanFieldDecimal(char[] r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 0
            r0.matchStat = r2
            boolean r3 = r18.charArrayCompare(r19)
            r4 = 0
            if (r3 != 0) goto L_0x0012
            r1 = -2
            r0.matchStat = r1
            return r4
        L_0x0012:
            int r3 = r1.length
            int r5 = r0.bp
            int r6 = r3 + 1
            int r5 = r5 + r3
            char r3 = r0.charAt(r5)
            r5 = 34
            r7 = 1
            if (r3 != r5) goto L_0x0023
            r8 = 1
            goto L_0x0024
        L_0x0023:
            r8 = 0
        L_0x0024:
            if (r8 == 0) goto L_0x0030
            int r3 = r0.bp
            int r9 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r6 = r9
        L_0x0030:
            r9 = 45
            if (r3 != r9) goto L_0x0036
            r10 = 1
            goto L_0x0037
        L_0x0036:
            r10 = 0
        L_0x0037:
            if (r10 == 0) goto L_0x0043
            int r3 = r0.bp
            int r10 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r6 = r10
        L_0x0043:
            r10 = 3
            r12 = 16
            r13 = 44
            r14 = 48
            r15 = -1
            if (r3 < r14) goto L_0x017a
            r2 = 57
            if (r3 > r2) goto L_0x017a
        L_0x0051:
            int r3 = r0.bp
            int r17 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            if (r3 < r14) goto L_0x0061
            if (r3 > r2) goto L_0x0061
            r6 = r17
            goto L_0x0051
        L_0x0061:
            r6 = 46
            if (r3 != r6) goto L_0x0067
            r6 = 1
            goto L_0x0068
        L_0x0067:
            r6 = 0
        L_0x0068:
            if (r6 == 0) goto L_0x008b
            int r3 = r0.bp
            int r6 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            if (r3 < r14) goto L_0x0088
            if (r3 > r2) goto L_0x0088
        L_0x0078:
            int r3 = r0.bp
            int r17 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            if (r3 < r14) goto L_0x008b
            if (r3 > r2) goto L_0x008b
            r6 = r17
            goto L_0x0078
        L_0x0088:
            r0.matchStat = r15
            return r4
        L_0x008b:
            r6 = 101(0x65, float:1.42E-43)
            if (r3 == r6) goto L_0x0097
            r6 = 69
            if (r3 != r6) goto L_0x0094
            goto L_0x0097
        L_0x0094:
            r16 = 0
            goto L_0x0099
        L_0x0097:
            r16 = 1
        L_0x0099:
            if (r16 == 0) goto L_0x00c9
            int r3 = r0.bp
            int r6 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            r11 = 43
            if (r3 == r11) goto L_0x00af
            if (r3 != r9) goto L_0x00ac
            goto L_0x00af
        L_0x00ac:
            r17 = r6
            goto L_0x00ba
        L_0x00af:
            int r3 = r0.bp
            int r9 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r17 = r9
        L_0x00ba:
            if (r3 < r14) goto L_0x00c9
            if (r3 > r2) goto L_0x00c9
            int r3 = r0.bp
            int r6 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            goto L_0x00ac
        L_0x00c9:
            if (r8 == 0) goto L_0x00e8
            if (r3 == r5) goto L_0x00d0
            r0.matchStat = r15
            return r4
        L_0x00d0:
            int r2 = r0.bp
            int r3 = r17 + 1
            int r2 = r2 + r17
            char r2 = r0.charAt(r2)
            int r5 = r0.bp
            int r1 = r1.length
            int r5 = r5 + r1
            int r5 = r5 + r7
            int r1 = r0.bp
            int r1 = r1 + r3
            int r1 = r1 - r5
            int r1 = r1 + -2
            r17 = r3
            goto L_0x00f4
        L_0x00e8:
            int r2 = r0.bp
            int r1 = r1.length
            int r5 = r2 + r1
            int r1 = r0.bp
            int r1 = r1 + r17
            int r1 = r1 - r5
            int r1 = r1 - r7
            r2 = r3
        L_0x00f4:
            char[] r1 = r0.sub_chars(r5, r1)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r3.<init>(r1)
            if (r2 != r13) goto L_0x0112
            int r1 = r0.bp
            int r1 = r1 + r17
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r10
            r0.token = r12
            return r3
        L_0x0112:
            r1 = 125(0x7d, float:1.75E-43)
            if (r2 != r1) goto L_0x0177
            int r1 = r0.bp
            int r2 = r17 + 1
            int r1 = r1 + r17
            char r1 = r0.charAt(r1)
            if (r1 != r13) goto L_0x0132
            r0.token = r12
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0170
        L_0x0132:
            r5 = 93
            if (r1 != r5) goto L_0x0148
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0170
        L_0x0148:
            r5 = 125(0x7d, float:1.75E-43)
            if (r1 != r5) goto L_0x015e
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0170
        L_0x015e:
            r5 = 26
            if (r1 != r5) goto L_0x0174
            r1 = 20
            r0.token = r1
            int r1 = r0.bp
            int r2 = r2 - r7
            int r1 = r1 + r2
            r0.bp = r1
            r1 = 26
            r0.ch = r1
        L_0x0170:
            r1 = 4
            r0.matchStat = r1
            return r3
        L_0x0174:
            r0.matchStat = r15
            return r4
        L_0x0177:
            r0.matchStat = r15
            return r4
        L_0x017a:
            r1 = 110(0x6e, float:1.54E-43)
            if (r3 != r1) goto L_0x01fb
            int r1 = r0.bp
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01fb
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01fb
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01fb
            r1 = 5
            r0.matchStat = r1
            int r6 = r6 + r10
            int r2 = r0.bp
            int r3 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            if (r8 == 0) goto L_0x01bb
            if (r2 != r5) goto L_0x01bb
            int r2 = r0.bp
            int r5 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r5
        L_0x01bb:
            if (r2 != r13) goto L_0x01cf
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r0.token = r12
            return r4
        L_0x01cf:
            r5 = 125(0x7d, float:1.75E-43)
            if (r2 != r5) goto L_0x01e7
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 13
            r0.token = r1
            return r4
        L_0x01e7:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01f8
            int r2 = r0.bp
            int r6 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r6
            goto L_0x01bb
        L_0x01f8:
            r0.matchStat = r15
            return r4
        L_0x01fb:
            r0.matchStat = r15
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldDecimal(char[]):java.math.BigDecimal");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.math.BigInteger scanFieldBigInteger(char[] r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = 0
            r0.matchStat = r2
            boolean r3 = r19.charArrayCompare(r20)
            r4 = 0
            if (r3 != 0) goto L_0x0012
            r1 = -2
            r0.matchStat = r1
            return r4
        L_0x0012:
            int r3 = r1.length
            int r5 = r0.bp
            int r6 = r3 + 1
            int r5 = r5 + r3
            char r3 = r0.charAt(r5)
            r5 = 34
            r7 = 1
            if (r3 != r5) goto L_0x0023
            r8 = 1
            goto L_0x0024
        L_0x0023:
            r8 = 0
        L_0x0024:
            if (r8 == 0) goto L_0x0030
            int r3 = r0.bp
            int r9 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r6 = r9
        L_0x0030:
            r9 = 45
            if (r3 != r9) goto L_0x0035
            r2 = 1
        L_0x0035:
            if (r2 == 0) goto L_0x0041
            int r3 = r0.bp
            int r9 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r6 = r9
        L_0x0041:
            r9 = 3
            r13 = 48
            r14 = -1
            if (r3 < r13) goto L_0x0130
            r15 = 57
            if (r3 > r15) goto L_0x0130
            int r3 = r3 - r13
            long r10 = (long) r3
        L_0x004d:
            int r3 = r0.bp
            int r16 = r6 + 1
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            if (r3 < r13) goto L_0x0067
            if (r3 > r15) goto L_0x0067
            r17 = 10
            long r10 = r10 * r17
            int r3 = r3 + -48
            long r12 = (long) r3
            long r10 = r10 + r12
            r6 = r16
            r13 = 48
            goto L_0x004d
        L_0x0067:
            if (r8 == 0) goto L_0x0086
            if (r3 == r5) goto L_0x006e
            r0.matchStat = r14
            return r4
        L_0x006e:
            int r3 = r0.bp
            int r5 = r16 + 1
            int r3 = r3 + r16
            char r3 = r0.charAt(r3)
            int r6 = r0.bp
            int r1 = r1.length
            int r6 = r6 + r1
            int r6 = r6 + r7
            int r1 = r0.bp
            int r1 = r1 + r5
            int r1 = r1 - r6
            int r1 = r1 + -2
            r16 = r5
            goto L_0x0091
        L_0x0086:
            int r5 = r0.bp
            int r1 = r1.length
            int r6 = r5 + r1
            int r1 = r0.bp
            int r1 = r1 + r16
            int r1 = r1 - r6
            int r1 = r1 - r7
        L_0x0091:
            r5 = 20
            if (r1 < r5) goto L_0x00a6
            if (r2 == 0) goto L_0x009c
            r8 = 21
            if (r1 >= r8) goto L_0x009c
            goto L_0x00a6
        L_0x009c:
            java.lang.String r1 = r0.subString(r6, r1)
            java.math.BigInteger r2 = new java.math.BigInteger
            r2.<init>(r1)
            goto L_0x00ad
        L_0x00a6:
            if (r2 == 0) goto L_0x00a9
            long r10 = -r10
        L_0x00a9:
            java.math.BigInteger r2 = java.math.BigInteger.valueOf(r10)
        L_0x00ad:
            r1 = 44
            if (r3 != r1) goto L_0x00c6
            int r1 = r0.bp
            int r1 = r1 + r16
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r9
            r1 = 16
            r0.token = r1
            return r2
        L_0x00c6:
            r1 = 16
            r6 = 125(0x7d, float:1.75E-43)
            if (r3 != r6) goto L_0x012d
            int r3 = r0.bp
            int r6 = r16 + 1
            int r3 = r3 + r16
            char r3 = r0.charAt(r3)
            r8 = 44
            if (r3 != r8) goto L_0x00ea
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r6
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0126
        L_0x00ea:
            r1 = 93
            if (r3 != r1) goto L_0x0100
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r6
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0126
        L_0x0100:
            r1 = 125(0x7d, float:1.75E-43)
            if (r3 != r1) goto L_0x0116
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r6
            r0.bp = r1
            int r1 = r0.bp
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0126
        L_0x0116:
            r1 = 26
            if (r3 != r1) goto L_0x012a
            r0.token = r5
            int r1 = r0.bp
            int r6 = r6 - r7
            int r1 = r1 + r6
            r0.bp = r1
            r1 = 26
            r0.ch = r1
        L_0x0126:
            r1 = 4
            r0.matchStat = r1
            return r2
        L_0x012a:
            r0.matchStat = r14
            return r4
        L_0x012d:
            r0.matchStat = r14
            return r4
        L_0x0130:
            r1 = 110(0x6e, float:1.54E-43)
            if (r3 != r1) goto L_0x01b7
            int r1 = r0.bp
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01b7
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01b7
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01b7
            r1 = 5
            r0.matchStat = r1
            int r6 = r6 + r9
            int r2 = r0.bp
            int r3 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            if (r8 == 0) goto L_0x0171
            if (r2 != r5) goto L_0x0171
            int r2 = r0.bp
            int r5 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r5
        L_0x0171:
            r5 = 44
        L_0x0173:
            if (r2 != r5) goto L_0x0189
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r6 = 16
            r0.token = r6
            return r4
        L_0x0189:
            r6 = 16
            r7 = 125(0x7d, float:1.75E-43)
            if (r2 != r7) goto L_0x01a3
            int r2 = r0.bp
            int r2 = r2 + r3
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 13
            r0.token = r1
            return r4
        L_0x01a3:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01b4
            int r2 = r0.bp
            int r8 = r3 + 1
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = r8
            goto L_0x0173
        L_0x01b4:
            r0.matchStat = r14
            return r4
        L_0x01b7:
            r0.matchStat = r14
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldBigInteger(char[]):java.math.BigInteger");
    }

    public Date scanFieldDate(char[] cArr) {
        char c2;
        int i;
        Date date;
        long j;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char charAt = charAt(this.bp + length);
        if (charAt == '\"') {
            int indexOf = indexOf('\"', this.bp + cArr.length + 1);
            if (indexOf != -1) {
                int length2 = this.bp + cArr.length + 1;
                String subString = subString(length2, indexOf - length2);
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
                    int length3 = indexOf - ((this.bp + cArr.length) + 1);
                    subString = readString(sub_chars(this.bp + cArr.length + 1, length3), length3);
                }
                int length4 = i2 + (indexOf - ((this.bp + cArr.length) + 1)) + 1;
                i = length4 + 1;
                c2 = charAt(this.bp + length4);
                JSONScanner jSONScanner = new JSONScanner(subString);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch(false)) {
                        date = jSONScanner.getCalendar().getTime();
                    } else {
                        this.matchStat = -1;
                        jSONScanner.close();
                        return null;
                    }
                } finally {
                    jSONScanner.close();
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else if (charAt == '-' || (charAt >= '0' && charAt <= '9')) {
            if (charAt == '-') {
                charAt = charAt(this.bp + i2);
                i2++;
                z = true;
            }
            if (charAt >= '0' && charAt <= '9') {
                j = (long) (charAt - '0');
                while (true) {
                    i = i2 + 1;
                    c2 = charAt(this.bp + i2);
                    if (c2 < '0' || c2 > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (c2 - '0'));
                    i2 = i;
                }
            } else {
                c2 = charAt;
                i = i2;
                j = 0;
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
        if (c2 == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return date;
        } else if (c2 == '}') {
            int i5 = i + 1;
            char charAt2 = charAt(this.bp + i);
            if (charAt2 == ',') {
                this.token = 16;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (charAt2 == ']') {
                this.token = 15;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (charAt2 == '}') {
                this.token = 13;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (charAt2 == 26) {
                this.token = 20;
                this.bp += i5 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    public Date scanDate(char c2) {
        int i;
        Date date;
        long j;
        boolean z = false;
        this.matchStat = 0;
        char charAt = charAt(this.bp + 0);
        if (charAt == '\"') {
            int indexOf = indexOf('\"', this.bp + 1);
            if (indexOf != -1) {
                int i2 = this.bp + 1;
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
                    int i5 = indexOf - (this.bp + 1);
                    subString = readString(sub_chars(this.bp + 1, i5), i5);
                }
                int i6 = (indexOf - (this.bp + 1)) + 1 + 1;
                int i7 = i6 + 1;
                charAt = charAt(this.bp + i6);
                JSONScanner jSONScanner = new JSONScanner(subString);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch(false)) {
                        date = jSONScanner.getCalendar().getTime();
                        jSONScanner.close();
                        i = i7;
                    } else {
                        this.matchStat = -1;
                        jSONScanner.close();
                        return null;
                    }
                } catch (Throwable th) {
                    jSONScanner.close();
                    throw th;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            int i8 = 2;
            char c3 = '9';
            char c4 = '0';
            if (charAt == '-' || (charAt >= '0' && charAt <= '9')) {
                if (charAt == '-') {
                    charAt = charAt(this.bp + 1);
                    z = true;
                } else {
                    i8 = 1;
                }
                if (charAt >= '0' && charAt <= '9') {
                    j = (long) (charAt - '0');
                    while (true) {
                        i = i8 + 1;
                        charAt = charAt(this.bp + i8);
                        if (charAt < c4 || charAt > c3) {
                            break;
                        }
                        j = (j * 10) + ((long) (charAt - '0'));
                        i8 = i;
                        c3 = '9';
                        c4 = '0';
                    }
                } else {
                    j = 0;
                    i = i8;
                }
                if (j < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                date = new Date(j);
            } else if (charAt == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                this.matchStat = 5;
                charAt = charAt(this.bp + 4);
                date = null;
                i = 5;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (charAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return date;
        } else if (charAt == ']') {
            int i9 = i + 1;
            char charAt2 = charAt(this.bp + i);
            if (charAt2 == ',') {
                this.token = 16;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (charAt2 == ']') {
                this.token = 15;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (charAt2 == '}') {
                this.token = 13;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (charAt2 == 26) {
                this.token = 20;
                this.bp += i9 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public UUID scanFieldUUID(char[] cArr) {
        int i;
        UUID uuid;
        char c2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr2.length;
        int i9 = length + 1;
        char charAt = charAt(this.bp + length);
        char c3 = 4;
        if (charAt == '\"') {
            int indexOf = indexOf('\"', this.bp + cArr2.length + 1);
            if (indexOf != -1) {
                int length2 = this.bp + cArr2.length + 1;
                int i10 = indexOf - length2;
                char c4 = 'F';
                char c5 = 'f';
                char c6 = 'A';
                char c7 = 'a';
                char c8 = '0';
                if (i10 == 36) {
                    int i11 = 0;
                    long j = 0;
                    while (i11 < 8) {
                        char charAt2 = charAt(length2 + i11);
                        if (charAt2 >= '0' && charAt2 <= '9') {
                            i8 = charAt2 - '0';
                        } else if (charAt2 >= 'a' && charAt2 <= 'f') {
                            i8 = (charAt2 - 'a') + 10;
                        } else if (charAt2 < c6 || charAt2 > c4) {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i8 = (charAt2 - 'A') + 10;
                        }
                        j = (j << 4) | ((long) i8);
                        i11++;
                        c6 = 'A';
                        c4 = 'F';
                    }
                    int i12 = 9;
                    while (i12 < 13) {
                        char charAt3 = charAt(length2 + i12);
                        if (charAt3 >= '0' && charAt3 <= '9') {
                            i7 = charAt3 - '0';
                        } else if (charAt3 >= 'a' && charAt3 <= c5) {
                            i7 = (charAt3 - 'a') + 10;
                        } else if (charAt3 < 'A' || charAt3 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i7 = (charAt3 - 'A') + 10;
                        }
                        j = (j << 4) | ((long) i7);
                        i12++;
                        indexOf = indexOf;
                        c5 = 'f';
                    }
                    int i13 = indexOf;
                    long j2 = j;
                    for (int i14 = 14; i14 < 18; i14++) {
                        char charAt4 = charAt(length2 + i14);
                        if (charAt4 >= '0' && charAt4 <= '9') {
                            i6 = charAt4 - '0';
                        } else if (charAt4 >= 'a' && charAt4 <= 'f') {
                            i6 = (charAt4 - 'a') + 10;
                        } else if (charAt4 < 'A' || charAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i6 = (charAt4 - 'A') + 10;
                        }
                        j2 = (j2 << 4) | ((long) i6);
                    }
                    int i15 = 19;
                    long j3 = 0;
                    while (i15 < 23) {
                        char charAt5 = charAt(length2 + i15);
                        if (charAt5 >= '0' && charAt5 <= '9') {
                            i5 = charAt5 - '0';
                        } else if (charAt5 >= 'a' && charAt5 <= 'f') {
                            i5 = (charAt5 - 'a') + 10;
                        } else if (charAt5 < 'A' || charAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i5 = (charAt5 - 'A') + 10;
                        }
                        j3 = (j3 << c3) | ((long) i5);
                        i15++;
                        j2 = j2;
                        c3 = 4;
                    }
                    long j4 = j2;
                    long j5 = j3;
                    for (int i16 = 24; i16 < 36; i16++) {
                        char charAt6 = charAt(length2 + i16);
                        if (charAt6 >= '0' && charAt6 <= '9') {
                            i4 = charAt6 - '0';
                        } else if (charAt6 >= 'a' && charAt6 <= 'f') {
                            i4 = (charAt6 - 'a') + 10;
                        } else if (charAt6 < 'A' || charAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i4 = (charAt6 - 'A') + 10;
                        }
                        j5 = (j5 << 4) | ((long) i4);
                    }
                    uuid = new UUID(j4, j5);
                    int length3 = i9 + (i13 - ((this.bp + cArr2.length) + 1)) + 1;
                    i = length3 + 1;
                    c2 = charAt(this.bp + length3);
                } else {
                    int i17 = indexOf;
                    if (i10 == 32) {
                        int i18 = 0;
                        long j6 = 0;
                        for (int i19 = 16; i18 < i19; i19 = 16) {
                            char charAt7 = charAt(length2 + i18);
                            if (charAt7 >= '0' && charAt7 <= '9') {
                                i3 = charAt7 - '0';
                            } else if (charAt7 >= 'a' && charAt7 <= 'f') {
                                i3 = (charAt7 - 'a') + 10;
                            } else if (charAt7 < 'A' || charAt7 > 'F') {
                                this.matchStat = -2;
                                return null;
                            } else {
                                i3 = (charAt7 - 'A') + 10;
                            }
                            j6 = (j6 << 4) | ((long) i3);
                            i18++;
                        }
                        int i20 = 16;
                        long j7 = 0;
                        while (i20 < 32) {
                            char charAt8 = charAt(length2 + i20);
                            if (charAt8 < c8 || charAt8 > '9') {
                                if (charAt8 >= c7) {
                                    if (charAt8 <= 'f') {
                                        i2 = (charAt8 - 'a') + 10;
                                    }
                                }
                                if (charAt8 < 'A' || charAt8 > 'F') {
                                    this.matchStat = -2;
                                    return null;
                                }
                                i2 = (charAt8 - 'A') + 10;
                            } else {
                                i2 = charAt8 - '0';
                            }
                            j7 = (j7 << 4) | ((long) i2);
                            i20++;
                            c8 = '0';
                            c7 = 'a';
                        }
                        uuid = new UUID(j6, j7);
                        int length4 = i9 + (i17 - ((this.bp + cArr2.length) + 1)) + 1;
                        i = length4 + 1;
                        c2 = charAt(this.bp + length4);
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            if (charAt == 'n') {
                int i21 = i9 + 1;
                if (charAt(this.bp + i9) == 'u') {
                    int i22 = i21 + 1;
                    if (charAt(this.bp + i21) == 'l') {
                        int i23 = i22 + 1;
                        if (charAt(this.bp + i22) == 'l') {
                            i = i23 + 1;
                            c2 = charAt(this.bp + i23);
                            uuid = null;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return null;
        }
        if (c2 == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        } else if (c2 == '}') {
            int i24 = i + 1;
            char charAt9 = charAt(this.bp + i);
            if (charAt9 == ',') {
                this.token = 16;
                this.bp += i24;
                this.ch = charAt(this.bp);
            } else if (charAt9 == ']') {
                this.token = 15;
                this.bp += i24;
                this.ch = charAt(this.bp);
            } else if (charAt9 == '}') {
                this.token = 13;
                this.bp += i24;
                this.ch = charAt(this.bp);
            } else if (charAt9 == 26) {
                this.token = 20;
                this.bp += i24 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:137:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x01f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.UUID scanUUID(char r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            int r2 = r2 + r1
            char r2 = r0.charAt(r2)
            r3 = 16
            r4 = -1
            r5 = 4
            r6 = 0
            r7 = 1
            r8 = 34
            if (r2 != r8) goto L_0x021a
            int r2 = r0.bp
            int r2 = r2 + r7
            r8 = 34
            int r2 = r0.indexOf(r8, r2)
            if (r2 == r4) goto L_0x0212
            int r8 = r0.bp
            int r8 = r8 + r7
            int r9 = r2 - r8
            r10 = 36
            r13 = -2
            r14 = 70
            r15 = 102(0x66, float:1.43E-43)
            r1 = 57
            r11 = 65
            r12 = 97
            r4 = 48
            if (r9 != r10) goto L_0x016d
            r9 = 0
            r19 = 0
        L_0x003a:
            r10 = 8
            if (r9 >= r10) goto L_0x006b
            int r10 = r8 + r9
            char r10 = r0.charAt(r10)
            if (r10 < r4) goto L_0x004b
            if (r10 > r1) goto L_0x004b
            int r10 = r10 + -48
            goto L_0x005c
        L_0x004b:
            if (r10 < r12) goto L_0x0054
            if (r10 > r15) goto L_0x0054
            int r10 = r10 + -97
            int r10 = r10 + 10
            goto L_0x005c
        L_0x0054:
            if (r10 < r11) goto L_0x0068
            if (r10 > r14) goto L_0x0068
            int r10 = r10 + -65
            int r10 = r10 + 10
        L_0x005c:
            long r19 = r19 << r5
            long r14 = (long) r10
            long r19 = r19 | r14
            int r9 = r9 + 1
            r14 = 70
            r15 = 102(0x66, float:1.43E-43)
            goto L_0x003a
        L_0x0068:
            r0.matchStat = r13
            return r6
        L_0x006b:
            r9 = 9
        L_0x006d:
            r10 = 13
            if (r9 >= r10) goto L_0x00a2
            int r10 = r8 + r9
            char r10 = r0.charAt(r10)
            if (r10 < r4) goto L_0x007e
            if (r10 > r1) goto L_0x007e
            int r10 = r10 + -48
            goto L_0x0093
        L_0x007e:
            if (r10 < r12) goto L_0x0089
            r14 = 102(0x66, float:1.43E-43)
            if (r10 > r14) goto L_0x0089
            int r10 = r10 + -97
            int r10 = r10 + 10
            goto L_0x0093
        L_0x0089:
            if (r10 < r11) goto L_0x009f
            r14 = 70
            if (r10 > r14) goto L_0x009f
            int r10 = r10 + -65
            int r10 = r10 + 10
        L_0x0093:
            long r14 = r19 << r5
            long r11 = (long) r10
            long r19 = r14 | r11
            int r9 = r9 + 1
            r11 = 65
            r12 = 97
            goto L_0x006d
        L_0x009f:
            r0.matchStat = r13
            return r6
        L_0x00a2:
            r9 = 14
            r10 = r19
        L_0x00a6:
            r12 = 18
            if (r9 >= r12) goto L_0x00d9
            int r12 = r8 + r9
            char r12 = r0.charAt(r12)
            if (r12 < r4) goto L_0x00b7
            if (r12 > r1) goto L_0x00b7
            int r12 = r12 + -48
            goto L_0x00d0
        L_0x00b7:
            r14 = 97
            if (r12 < r14) goto L_0x00c4
            r14 = 102(0x66, float:1.43E-43)
            if (r12 > r14) goto L_0x00c4
            int r12 = r12 + -97
            int r12 = r12 + 10
            goto L_0x00d0
        L_0x00c4:
            r14 = 65
            if (r12 < r14) goto L_0x00d6
            r14 = 70
            if (r12 > r14) goto L_0x00d6
            int r12 = r12 + -65
            int r12 = r12 + 10
        L_0x00d0:
            long r10 = r10 << r5
            long r14 = (long) r12
            long r10 = r10 | r14
            int r9 = r9 + 1
            goto L_0x00a6
        L_0x00d6:
            r0.matchStat = r13
            return r6
        L_0x00d9:
            r9 = 19
            r17 = 0
        L_0x00dd:
            r12 = 23
            if (r9 >= r12) goto L_0x0118
            int r12 = r8 + r9
            char r12 = r0.charAt(r12)
            if (r12 < r4) goto L_0x00ee
            if (r12 > r1) goto L_0x00ee
            int r12 = r12 + -48
            goto L_0x0107
        L_0x00ee:
            r14 = 97
            if (r12 < r14) goto L_0x00fb
            r14 = 102(0x66, float:1.43E-43)
            if (r12 > r14) goto L_0x00fb
            int r12 = r12 + -97
            int r12 = r12 + 10
            goto L_0x0107
        L_0x00fb:
            r14 = 65
            if (r12 < r14) goto L_0x0115
            r14 = 70
            if (r12 > r14) goto L_0x0115
            int r12 = r12 + -65
            int r12 = r12 + 10
        L_0x0107:
            long r14 = r17 << r5
            r21 = r2
            long r1 = (long) r12
            long r17 = r14 | r1
            int r9 = r9 + 1
            r2 = r21
            r1 = 57
            goto L_0x00dd
        L_0x0115:
            r0.matchStat = r13
            return r6
        L_0x0118:
            r21 = r2
            r1 = 24
            r14 = r17
        L_0x011e:
            r2 = 36
            if (r1 >= r2) goto L_0x0156
            int r2 = r8 + r1
            char r2 = r0.charAt(r2)
            if (r2 < r4) goto L_0x0131
            r9 = 57
            if (r2 > r9) goto L_0x0131
            int r2 = r2 + -48
            goto L_0x014a
        L_0x0131:
            r9 = 97
            if (r2 < r9) goto L_0x013e
            r9 = 102(0x66, float:1.43E-43)
            if (r2 > r9) goto L_0x013e
            int r2 = r2 + -97
            int r2 = r2 + 10
            goto L_0x014a
        L_0x013e:
            r9 = 65
            if (r2 < r9) goto L_0x0153
            r9 = 70
            if (r2 > r9) goto L_0x0153
            int r2 = r2 + -65
            int r2 = r2 + 10
        L_0x014a:
            long r14 = r14 << r5
            long r4 = (long) r2
            long r14 = r14 | r4
            int r1 = r1 + 1
            r4 = 48
            r5 = 4
            goto L_0x011e
        L_0x0153:
            r0.matchStat = r13
            return r6
        L_0x0156:
            java.util.UUID r1 = new java.util.UUID
            r1.<init>(r10, r14)
            int r2 = r0.bp
            int r2 = r2 + r7
            int r2 = r21 - r2
            int r2 = r2 + r7
            int r2 = r2 + r7
            int r4 = r0.bp
            int r5 = r2 + 1
            int r4 = r4 + r2
            char r2 = r0.charAt(r4)
            goto L_0x024b
        L_0x016d:
            r21 = r2
            r1 = 32
            if (r9 != r1) goto L_0x020e
            r1 = 0
            r4 = 0
        L_0x0176:
            if (r1 >= r3) goto L_0x01ad
            int r2 = r8 + r1
            char r2 = r0.charAt(r2)
            r9 = 48
            if (r2 < r9) goto L_0x018a
            r9 = 57
            if (r2 > r9) goto L_0x018a
            int r2 = r2 + -48
        L_0x0188:
            r9 = 4
            goto L_0x01a4
        L_0x018a:
            r9 = 97
            if (r2 < r9) goto L_0x0197
            r9 = 102(0x66, float:1.43E-43)
            if (r2 > r9) goto L_0x0197
            int r2 = r2 + -97
            int r2 = r2 + 10
            goto L_0x0188
        L_0x0197:
            r9 = 65
            if (r2 < r9) goto L_0x01aa
            r9 = 70
            if (r2 > r9) goto L_0x01aa
            int r2 = r2 + -65
            int r2 = r2 + 10
            goto L_0x0188
        L_0x01a4:
            long r4 = r4 << r9
            long r9 = (long) r2
            long r4 = r4 | r9
            int r1 = r1 + 1
            goto L_0x0176
        L_0x01aa:
            r0.matchStat = r13
            return r6
        L_0x01ad:
            r1 = 16
            r9 = 0
        L_0x01b1:
            r2 = 32
            if (r1 >= r2) goto L_0x01f8
            int r2 = r8 + r1
            char r2 = r0.charAt(r2)
            r11 = 48
            if (r2 < r11) goto L_0x01ce
            r12 = 57
            if (r2 > r12) goto L_0x01d0
            int r2 = r2 + -48
            r11 = 70
            r14 = 97
            r15 = 102(0x66, float:1.43E-43)
        L_0x01cb:
            r16 = 4
            goto L_0x01ee
        L_0x01ce:
            r12 = 57
        L_0x01d0:
            r14 = 97
            if (r2 < r14) goto L_0x01df
            r15 = 102(0x66, float:1.43E-43)
            if (r2 > r15) goto L_0x01e1
            int r2 = r2 + -97
            int r2 = r2 + 10
            r11 = 70
            goto L_0x01cb
        L_0x01df:
            r15 = 102(0x66, float:1.43E-43)
        L_0x01e1:
            r11 = 65
            if (r2 < r11) goto L_0x01f5
            r11 = 70
            if (r2 > r11) goto L_0x01f5
            int r2 = r2 + -65
            int r2 = r2 + 10
            goto L_0x01cb
        L_0x01ee:
            long r9 = r9 << r16
            long r11 = (long) r2
            long r9 = r9 | r11
            int r1 = r1 + 1
            goto L_0x01b1
        L_0x01f5:
            r0.matchStat = r13
            return r6
        L_0x01f8:
            java.util.UUID r1 = new java.util.UUID
            r1.<init>(r4, r9)
            int r2 = r0.bp
            int r2 = r2 + r7
            int r2 = r21 - r2
            int r2 = r2 + r7
            int r2 = r2 + r7
            int r4 = r0.bp
            int r5 = r2 + 1
            int r4 = r4 + r2
            char r2 = r0.charAt(r4)
            goto L_0x024b
        L_0x020e:
            r1 = -1
            r0.matchStat = r1
            return r6
        L_0x0212:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException
            java.lang.String r2 = "unclosed str"
            r1.<init>(r2)
            throw r1
        L_0x021a:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x02cc
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x02cc
            int r1 = r0.bp
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x02cc
            int r1 = r0.bp
            int r1 = r1 + 3
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x02cc
            int r1 = r0.bp
            r5 = 5
            r2 = 4
            int r1 = r1 + r2
            char r2 = r0.charAt(r1)
            r1 = r6
        L_0x024b:
            r4 = 44
            if (r2 != r4) goto L_0x0260
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r2 = 3
            r0.matchStat = r2
            return r1
        L_0x0260:
            r4 = 93
            if (r2 != r4) goto L_0x02c8
            int r2 = r0.bp
            int r4 = r5 + 1
            int r2 = r2 + r5
            char r2 = r0.charAt(r2)
            r5 = 44
            if (r2 != r5) goto L_0x0282
            r0.token = r3
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
        L_0x0280:
            r2 = 4
            goto L_0x02c1
        L_0x0282:
            r3 = 93
            if (r2 != r3) goto L_0x0298
            r2 = 15
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x0280
        L_0x0298:
            r3 = 125(0x7d, float:1.75E-43)
            if (r2 != r3) goto L_0x02ae
            r2 = 13
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x0280
        L_0x02ae:
            r3 = 26
            if (r2 != r3) goto L_0x02c4
            r2 = 20
            r0.token = r2
            int r2 = r0.bp
            int r4 = r4 - r7
            int r2 = r2 + r4
            r0.bp = r2
            r2 = 26
            r0.ch = r2
            goto L_0x0280
        L_0x02c1:
            r0.matchStat = r2
            return r1
        L_0x02c4:
            r1 = -1
            r0.matchStat = r1
            return r6
        L_0x02c8:
            r1 = -1
            r0.matchStat = r1
            return r6
        L_0x02cc:
            r1 = -1
            r0.matchStat = r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanUUID(char):java.util.UUID");
    }

    public final void scanTrue() {
        if (this.ch == 't') {
            next();
            if (this.ch == 'r') {
                next();
                if (this.ch == 'u') {
                    next();
                    if (this.ch == 'e') {
                        next();
                        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == 10 || this.ch == 13 || this.ch == 9 || this.ch == 26 || this.ch == 12 || this.ch == 8 || this.ch == ':' || this.ch == '/') {
                            this.token = 6;
                            return;
                        }
                        throw new JSONException("scan true error");
                    }
                    throw new JSONException("error parse true");
                }
                throw new JSONException("error parse true");
            }
            throw new JSONException("error parse true");
        }
        throw new JSONException("error parse true");
    }

    public final void scanNullOrNew() {
        if (this.ch == 'n') {
            next();
            if (this.ch == 'u') {
                next();
                if (this.ch == 'l') {
                    next();
                    if (this.ch == 'l') {
                        next();
                        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == 10 || this.ch == 13 || this.ch == 9 || this.ch == 26 || this.ch == 12 || this.ch == 8) {
                            this.token = 8;
                            return;
                        }
                        throw new JSONException("scan null error");
                    }
                    throw new JSONException("error parse null");
                }
                throw new JSONException("error parse null");
            } else if (this.ch == 'e') {
                next();
                if (this.ch == 'w') {
                    next();
                    if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == 10 || this.ch == 13 || this.ch == 9 || this.ch == 26 || this.ch == 12 || this.ch == 8) {
                        this.token = 9;
                        return;
                    }
                    throw new JSONException("scan new error");
                }
                throw new JSONException("error parse new");
            } else {
                throw new JSONException("error parse new");
            }
        } else {
            throw new JSONException("error parse null or new");
        }
    }

    public final void scanFalse() {
        if (this.ch == 'f') {
            next();
            if (this.ch == 'a') {
                next();
                if (this.ch == 'l') {
                    next();
                    if (this.ch == 's') {
                        next();
                        if (this.ch == 'e') {
                            next();
                            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == 10 || this.ch == 13 || this.ch == 9 || this.ch == 26 || this.ch == 12 || this.ch == 8 || this.ch == ':' || this.ch == '/') {
                                this.token = 7;
                                return;
                            }
                            throw new JSONException("scan false error");
                        }
                        throw new JSONException("error parse false");
                    }
                    throw new JSONException("error parse false");
                }
                throw new JSONException("error parse false");
            }
            throw new JSONException("error parse false");
        }
        throw new JSONException("error parse false");
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String stringVal = stringVal();
        if ("null".equalsIgnoreCase(stringVal)) {
            this.token = 8;
        } else if ("new".equals(stringVal)) {
            this.token = 9;
        } else if (RequestConstant.TRUE.equals(stringVal)) {
            this.token = 6;
        } else if (RequestConstant.FALSE.equals(stringVal)) {
            this.token = 7;
        } else if ("undefined".equals(stringVal)) {
            this.token = 23;
        } else if ("Set".equals(stringVal)) {
            this.token = 21;
        } else if ("TreeSet".equals(stringVal)) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public static String readString(char[] cArr, int i) {
        int i2;
        int i3;
        char[] cArr2 = new char[i];
        int i4 = 0;
        int i5 = 0;
        while (i2 < i) {
            char c2 = cArr[i2];
            if (c2 != '\\') {
                cArr2[i5] = c2;
                i5++;
            } else {
                i2++;
                char c3 = cArr[i2];
                switch (c3) {
                    case '/':
                        i3 = i5 + 1;
                        cArr2[i5] = '/';
                        break;
                    case '0':
                        i3 = i5 + 1;
                        cArr2[i5] = 0;
                        break;
                    case '1':
                        i3 = i5 + 1;
                        cArr2[i5] = 1;
                        break;
                    case '2':
                        i3 = i5 + 1;
                        cArr2[i5] = 2;
                        break;
                    case '3':
                        i3 = i5 + 1;
                        cArr2[i5] = 3;
                        break;
                    case '4':
                        i3 = i5 + 1;
                        cArr2[i5] = 4;
                        break;
                    case '5':
                        i3 = i5 + 1;
                        cArr2[i5] = 5;
                        break;
                    case '6':
                        i3 = i5 + 1;
                        cArr2[i5] = 6;
                        break;
                    case '7':
                        i3 = i5 + 1;
                        cArr2[i5] = 7;
                        break;
                    default:
                        switch (c3) {
                            case 't':
                                i3 = i5 + 1;
                                cArr2[i5] = 9;
                                break;
                            case 'u':
                                i3 = i5 + 1;
                                int i6 = i2 + 1;
                                int i7 = i6 + 1;
                                int i8 = i7 + 1;
                                i2 = i8 + 1;
                                cArr2[i5] = (char) Integer.parseInt(new String(new char[]{cArr[i6], cArr[i7], cArr[i8], cArr[i2]}), 16);
                                break;
                            case 'v':
                                i3 = i5 + 1;
                                cArr2[i5] = 11;
                                break;
                            default:
                                switch (c3) {
                                    case '\"':
                                        i3 = i5 + 1;
                                        cArr2[i5] = '\"';
                                        break;
                                    case '\'':
                                        i3 = i5 + 1;
                                        cArr2[i5] = '\'';
                                        break;
                                    case 'F':
                                    case 'f':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 12;
                                        break;
                                    case '\\':
                                        i3 = i5 + 1;
                                        cArr2[i5] = '\\';
                                        break;
                                    case 'b':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 8;
                                        break;
                                    case 'n':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 10;
                                        break;
                                    case 'r':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 13;
                                        break;
                                    case 'x':
                                        i3 = i5 + 1;
                                        int i9 = i2 + 1;
                                        i2 = i9 + 1;
                                        cArr2[i5] = (char) ((digits[cArr[i9]] * 16) + digits[cArr[i2]]);
                                        break;
                                    default:
                                        throw new JSONException("unclosed.str.lit");
                                }
                        }
                }
                i5 = i3;
            }
            i4 = i2 + 1;
        }
        return new String(cArr2, 0, i5);
    }

    public boolean isBlankInput() {
        int i = 0;
        while (true) {
            char charAt = charAt(i);
            if (charAt == 26) {
                this.token = 20;
                return true;
            } else if (!isWhitespace(charAt)) {
                return false;
            } else {
                i++;
            }
        }
    }

    public final void skipWhitespace() {
        while (this.ch <= '/') {
            if (this.ch == ' ' || this.ch == 13 || this.ch == 10 || this.ch == 9 || this.ch == 12 || this.ch == 8) {
                next();
            } else if (this.ch == '/') {
                skipComment();
            } else {
                return;
            }
        }
    }

    private void scanStringSingleQuote() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\'') {
                this.token = 4;
                next();
                return;
            } else if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed single-quote string");
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp > this.sbuf.length) {
                        char[] cArr = new char[(this.sp * 2)];
                        System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
                        this.sbuf = cArr;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                switch (next2) {
                    case '/':
                        putChar('/');
                        break;
                    case '0':
                        putChar(0);
                        break;
                    case '1':
                        putChar(1);
                        break;
                    case '2':
                        putChar(2);
                        break;
                    case '3':
                        putChar(3);
                        break;
                    case '4':
                        putChar(4);
                        break;
                    case '5':
                        putChar(5);
                        break;
                    case '6':
                        putChar(6);
                        break;
                    case '7':
                        putChar(7);
                        break;
                    default:
                        switch (next2) {
                            case 't':
                                putChar(9);
                                break;
                            case 'u':
                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                break;
                            case 'v':
                                putChar(11);
                                break;
                            default:
                                switch (next2) {
                                    case '\"':
                                        putChar('\"');
                                        break;
                                    case '\'':
                                        putChar('\'');
                                        break;
                                    case 'F':
                                    case 'f':
                                        putChar(12);
                                        break;
                                    case '\\':
                                        putChar('\\');
                                        break;
                                    case 'b':
                                        putChar(8);
                                        break;
                                    case 'n':
                                        putChar(10);
                                        break;
                                    case 'r':
                                        putChar(13);
                                        break;
                                    case 'x':
                                        putChar((char) ((digits[next()] * 16) + digits[next()]));
                                        break;
                                    default:
                                        this.ch = next2;
                                        throw new JSONException("unclosed single-quote string");
                                }
                        }
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else if (this.sp == this.sbuf.length) {
                putChar(next);
            } else {
                char[] cArr2 = this.sbuf;
                int i = this.sp;
                this.sp = i + 1;
                cArr2[i] = next;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void putChar(char c2) {
        if (this.sp == this.sbuf.length) {
            char[] cArr = new char[(this.sbuf.length * 2)];
            System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
            this.sbuf = cArr;
        }
        char[] cArr2 = this.sbuf;
        int i = this.sp;
        this.sp = i + 1;
        cArr2[i] = c2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scanHex() {
        /*
            r4 = this;
            char r0 = r4.ch
            r1 = 120(0x78, float:1.68E-43)
            if (r0 != r1) goto L_0x0071
            r4.next()
            char r0 = r4.ch
            r1 = 39
            if (r0 != r1) goto L_0x0058
            int r0 = r4.bp
            r4.np = r0
            r4.next()
        L_0x0016:
            char r0 = r4.next()
            r2 = 48
            if (r0 < r2) goto L_0x0022
            r2 = 57
            if (r0 <= r2) goto L_0x002a
        L_0x0022:
            r2 = 65
            if (r0 < r2) goto L_0x0031
            r2 = 70
            if (r0 > r2) goto L_0x0031
        L_0x002a:
            int r0 = r4.sp
            int r0 = r0 + 1
            r4.sp = r0
            goto L_0x0016
        L_0x0031:
            if (r0 != r1) goto L_0x0041
            int r0 = r4.sp
            int r0 = r0 + 1
            r4.sp = r0
            r4.next()
            r0 = 26
            r4.token = r0
            return
        L_0x0041:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "illegal state. "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0058:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "illegal state. "
            r1.append(r2)
            char r2 = r4.ch
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0071:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "illegal state. "
            r1.append(r2)
            char r2 = r4.ch
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanHex():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scanNumber() {
        /*
            r9 = this;
            int r0 = r9.bp
            r9.np = r0
            char r0 = r9.ch
            r1 = 45
            r2 = 1
            if (r0 != r1) goto L_0x0013
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
        L_0x0013:
            char r0 = r9.ch
            r3 = 57
            r4 = 48
            if (r0 < r4) goto L_0x0028
            char r0 = r9.ch
            if (r0 > r3) goto L_0x0028
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
            goto L_0x0013
        L_0x0028:
            r0 = 0
            char r5 = r9.ch
            r6 = 46
            if (r5 != r6) goto L_0x0049
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
        L_0x0037:
            char r0 = r9.ch
            if (r0 < r4) goto L_0x0048
            char r0 = r9.ch
            if (r0 > r3) goto L_0x0048
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
            goto L_0x0037
        L_0x0048:
            r0 = 1
        L_0x0049:
            char r5 = r9.ch
            r6 = 76
            if (r5 != r6) goto L_0x0058
            int r1 = r9.sp
            int r1 = r1 + r2
            r9.sp = r1
            r9.next()
            goto L_0x00a1
        L_0x0058:
            char r5 = r9.ch
            r6 = 83
            if (r5 != r6) goto L_0x0067
            int r1 = r9.sp
            int r1 = r1 + r2
            r9.sp = r1
            r9.next()
            goto L_0x00a1
        L_0x0067:
            char r5 = r9.ch
            r6 = 66
            if (r5 != r6) goto L_0x0076
            int r1 = r9.sp
            int r1 = r1 + r2
            r9.sp = r1
            r9.next()
            goto L_0x00a1
        L_0x0076:
            char r5 = r9.ch
            r6 = 70
            if (r5 != r6) goto L_0x0085
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
            goto L_0x00de
        L_0x0085:
            char r5 = r9.ch
            r7 = 68
            if (r5 != r7) goto L_0x0094
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
            goto L_0x00de
        L_0x0094:
            char r5 = r9.ch
            r8 = 101(0x65, float:1.42E-43)
            if (r5 == r8) goto L_0x00a3
            char r5 = r9.ch
            r8 = 69
            if (r5 != r8) goto L_0x00a1
            goto L_0x00a3
        L_0x00a1:
            r2 = r0
            goto L_0x00de
        L_0x00a3:
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
            char r0 = r9.ch
            r5 = 43
            if (r0 == r5) goto L_0x00b5
            char r0 = r9.ch
            if (r0 != r1) goto L_0x00bd
        L_0x00b5:
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
        L_0x00bd:
            char r0 = r9.ch
            if (r0 < r4) goto L_0x00ce
            char r0 = r9.ch
            if (r0 > r3) goto L_0x00ce
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
            goto L_0x00bd
        L_0x00ce:
            char r0 = r9.ch
            if (r0 == r7) goto L_0x00d6
            char r0 = r9.ch
            if (r0 != r6) goto L_0x00de
        L_0x00d6:
            int r0 = r9.sp
            int r0 = r0 + r2
            r9.sp = r0
            r9.next()
        L_0x00de:
            if (r2 == 0) goto L_0x00e4
            r0 = 3
            r9.token = r0
            goto L_0x00e7
        L_0x00e4:
            r0 = 2
            r9.token = r0
        L_0x00e7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanNumber():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long longValue() throws java.lang.NumberFormatException {
        /*
            r15 = this;
            int r0 = r15.np
            r1 = 0
            r2 = -1
            if (r0 != r2) goto L_0x0008
            r15.np = r1
        L_0x0008:
            int r0 = r15.np
            int r2 = r15.np
            int r3 = r15.sp
            int r2 = r2 + r3
            int r3 = r15.np
            char r3 = r15.charAt(r3)
            r4 = 45
            r5 = 1
            if (r3 != r4) goto L_0x0020
            r3 = -9223372036854775808
            int r0 = r0 + 1
            r1 = 1
            goto L_0x0025
        L_0x0020:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0025:
            r6 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            if (r0 >= r2) goto L_0x0038
            int r8 = r0 + 1
            char r0 = r15.charAt(r0)
            int r0 = r0 + -48
            int r0 = -r0
            long r9 = (long) r0
        L_0x0036:
            r0 = r8
            goto L_0x003a
        L_0x0038:
            r9 = 0
        L_0x003a:
            if (r0 >= r2) goto L_0x0077
            int r8 = r0 + 1
            char r0 = r15.charAt(r0)
            r11 = 76
            if (r0 == r11) goto L_0x0076
            r11 = 83
            if (r0 == r11) goto L_0x0076
            r11 = 66
            if (r0 != r11) goto L_0x004f
            goto L_0x0076
        L_0x004f:
            int r0 = r0 + -48
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 < 0) goto L_0x006c
            r11 = 10
            long r9 = r9 * r11
            long r11 = (long) r0
            long r13 = r3 + r11
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 < 0) goto L_0x0062
            long r9 = r9 - r11
            goto L_0x0036
        L_0x0062:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L_0x0076:
            r0 = r8
        L_0x0077:
            if (r1 == 0) goto L_0x0089
            int r1 = r15.np
            int r1 = r1 + r5
            if (r0 <= r1) goto L_0x007f
            return r9
        L_0x007f:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.numberString()
            r0.<init>(r1)
            throw r0
        L_0x0089:
            long r0 = -r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.longValue():long");
    }

    public final Number decimalValue(boolean z) {
        char charAt = charAt((this.np + this.sp) - 1);
        if (charAt == 'F') {
            try {
                return Float.valueOf(Float.parseFloat(numberString()));
            } catch (NumberFormatException e) {
                throw new JSONException(e.getMessage() + ", " + info());
            }
        } else if (charAt == 'D') {
            return Double.valueOf(Double.parseDouble(numberString()));
        } else {
            if (z) {
                return decimalValue();
            }
            return Double.valueOf(doubleValue());
        }
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    public boolean matchField2(char[] cArr) {
        throw new UnsupportedOperationException();
    }

    public int getFeatures() {
        return this.features;
    }
}
