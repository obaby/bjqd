package com.alibaba.sdk.android.httpdns;

class d {
    static String PROTOCOL = "http://";

    /* renamed from: a  reason: collision with root package name */
    static int f468a = 15000;

    /* renamed from: b  reason: collision with root package name */
    static String[] f469b = {"203.107.1.1"};

    /* renamed from: c  reason: collision with root package name */
    static String f470c;

    /* renamed from: c  reason: collision with other field name */
    static final String[] f6c = {"203.107.1.97", "203.107.1.100", "httpdns-sc.aliyuncs.com"};
    static String d = "80";

    /* renamed from: d  reason: collision with other field name */
    static final String[] f7d = new String[0];

    static synchronized boolean a(String[] strArr) {
        boolean z;
        synchronized (d.class) {
            if (strArr != null) {
                if (strArr.length != 0) {
                    f469b = strArr;
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    static synchronized void d(String str) {
        synchronized (d.class) {
            f470c = str;
        }
    }

    static synchronized void setHTTPSRequestEnabled(boolean z) {
        String str;
        synchronized (d.class) {
            if (z) {
                try {
                    PROTOCOL = "https://";
                    str = "443";
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                PROTOCOL = "http://";
                str = "80";
            }
            d = str;
        }
    }

    static synchronized void setTimeoutInterval(int i) {
        synchronized (d.class) {
            if (i > 0) {
                f468a = i;
            }
        }
    }
}
