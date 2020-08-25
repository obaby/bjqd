package com.alipay.sdk.util;

import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.app.statistic.c;
import java.util.HashMap;
import java.util.Map;

public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static final String f738a = "resultStatus";

    /* renamed from: b  reason: collision with root package name */
    public static final String f739b = "memo";

    /* renamed from: c  reason: collision with root package name */
    public static final String f740c = "result";

    private static Map<String, String> a() {
        com.alipay.sdk.app.j a2 = com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.CANCELED.h);
        HashMap hashMap = new HashMap();
        hashMap.put(f738a, Integer.toString(a2.h));
        hashMap.put(f739b, a2.i);
        hashMap.put(f740c, "");
        return hashMap;
    }

    private static Map<String, String> b(String str) {
        String[] split = str.split(h.f735b);
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            String str3 = substring + "={";
            hashMap.put(substring, str2.substring(str2.indexOf(str3) + str3.length(), str2.lastIndexOf(h.d)));
        }
        return hashMap;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(h.d));
    }

    public static Map<String, String> a(String str) {
        com.alipay.sdk.app.j a2 = com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.CANCELED.h);
        HashMap hashMap = new HashMap();
        hashMap.put(f738a, Integer.toString(a2.h));
        hashMap.put(f739b, a2.i);
        hashMap.put(f740c, "");
        try {
            return b(str);
        } catch (Throwable th) {
            a.a(c.f626b, c.f, th);
            return hashMap;
        }
    }
}
