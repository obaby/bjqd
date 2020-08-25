package com.alipay.sdk.app;

import com.alipay.sdk.util.h;

public final class i {

    /* renamed from: a  reason: collision with root package name */
    public static String f617a;

    private static void a(String str) {
        f617a = str;
    }

    private static String b() {
        return f617a;
    }

    public static String a() {
        j a2 = j.a(j.CANCELED.h);
        return a(a2.h, a2.i, "");
    }

    private static String c() {
        j a2 = j.a(j.DOUBLE_REQUEST.h);
        return a(a2.h, a2.i, "");
    }

    private static String d() {
        j a2 = j.a(j.PARAMS_ERROR.h);
        return a(a2.h, a2.i, "");
    }

    public static String a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + h.d;
    }
}
