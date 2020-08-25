package com.alibaba.sdk.android.httpdns;

import android.util.Log;

class g {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f473a = false;

    /* renamed from: c  reason: collision with root package name */
    private static int f474c = -1;

    static void a(Throwable th) {
        if (f473a && th != null) {
            th.printStackTrace();
        }
    }

    private static String b() {
        if (f474c == -1) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (stackTrace[i].getMethodName().equals("getTraceInfo")) {
                    f474c = i2 + 1;
                    break;
                } else {
                    i2++;
                    i++;
                }
            }
        }
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[f474c + 1];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " - [" + stackTraceElement.getMethodName() + "]";
    }

    static void e(String str) {
        if (f473a && str != null) {
            Log.d("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
    }

    static void f(String str) {
        if (f473a && str != null) {
            Log.e("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
    }

    static synchronized void setLogEnabled(boolean z) {
        synchronized (g.class) {
            f473a = z;
        }
    }
}
