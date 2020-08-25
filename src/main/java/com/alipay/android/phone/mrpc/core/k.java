package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static final Pattern f527a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f528b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        int f529a;

        /* renamed from: b  reason: collision with root package name */
        int f530b;

        /* renamed from: c  reason: collision with root package name */
        int f531c;

        a(int i, int i2, int i3) {
            this.f529a = i;
            this.f530b = i2;
            this.f531c = i3;
        }
    }

    public static long a(String str) {
        int i;
        int i2;
        int i3;
        a aVar;
        int i4;
        int i5;
        int i6;
        Matcher matcher = f527a.matcher(str);
        if (matcher.find()) {
            int b2 = b(matcher.group(1));
            int c2 = c(matcher.group(2));
            int d = d(matcher.group(3));
            aVar = e(matcher.group(4));
            i2 = c2;
            i3 = b2;
            i = d;
        } else {
            Matcher matcher2 = f528b.matcher(str);
            if (matcher2.find()) {
                int c3 = c(matcher2.group(1));
                int b3 = b(matcher2.group(2));
                a e = e(matcher2.group(3));
                i = d(matcher2.group(4));
                i2 = c3;
                i3 = b3;
                aVar = e;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (i >= 2038) {
            i6 = 1;
            i5 = 0;
            i4 = 2038;
        } else {
            i4 = i;
            i6 = i3;
            i5 = i2;
        }
        Time time = new Time("UTC");
        time.set(aVar.f531c, aVar.f530b, aVar.f529a, i6, i5, i4);
        return time.toMillis(false);
    }

    private static int b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    private static int c(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase == 22) {
            return 0;
        }
        if (lowerCase == 26) {
            return 7;
        }
        if (lowerCase == 29) {
            return 2;
        }
        if (lowerCase == 32) {
            return 3;
        }
        if (lowerCase == 40) {
            return 6;
        }
        if (lowerCase == 42) {
            return 5;
        }
        if (lowerCase == 48) {
            return 10;
        }
        switch (lowerCase) {
            case 9:
                return 11;
            case 10:
                return 1;
            default:
                switch (lowerCase) {
                    case 35:
                        return 9;
                    case 36:
                        return 4;
                    case 37:
                        return 8;
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }

    private static int d(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            return charAt >= 70 ? charAt + 1900 : charAt + 2000;
        } else if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + 1900;
        } else {
            if (str.length() == 4) {
                return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
            }
            return 1970;
        }
    }

    private static a e(String str) {
        int i;
        int charAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - '0');
        } else {
            i = 1;
        }
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = i3 + 1 + 1;
        return new a(charAt, ((str.charAt(i2) - '0') * 10) + (str.charAt(i3) - '0'), ((str.charAt(i4) - '0') * 10) + (str.charAt(i4 + 1) - '0'));
    }
}
