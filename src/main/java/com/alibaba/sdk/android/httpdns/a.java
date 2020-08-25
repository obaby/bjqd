package com.alibaba.sdk.android.httpdns;

import android.text.TextUtils;
import java.security.NoSuchAlgorithmException;

class a {

    /* renamed from: a  reason: collision with root package name */
    private static long f455a;

    /* renamed from: a  reason: collision with other field name */
    private static String f0a;

    static String a(String str, String str2) {
        if (!j.b(str)) {
            return "";
        }
        try {
            return j.a(str + "-" + f0a + "-" + str2);
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    static void a(String str) {
        f0a = str;
    }

    static boolean a() {
        return !TextUtils.isEmpty(f0a);
    }

    static String getTimestamp() {
        return String.valueOf((System.currentTimeMillis() / 1000) + f455a + 600);
    }

    static void setAuthCurrentTime(long j) {
        f455a = j - (System.currentTimeMillis() / 1000);
    }
}
