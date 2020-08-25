package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.security.mobile.module.a.a;
import com.alipay.security.mobile.module.a.a.c;
import com.alipay.security.mobile.module.c.e;

public final class g {
    public static synchronized String a(Context context, String str) {
        synchronized (g.class) {
            String a2 = e.a(context, "openapi_file_pri", "openApi" + str, "");
            if (a.a(a2)) {
                return "";
            }
            String b2 = c.b(c.a(), a2);
            return a.a(b2) ? "" : b2;
        }
    }

    public static synchronized void a() {
        synchronized (g.class) {
        }
    }

    public static synchronized void a(Context context) {
        synchronized (g.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.Class<com.alipay.apmobilesecuritysdk.e.g> r0 = com.alipay.apmobilesecuritysdk.e.g.class
            monitor-enter(r0)
            java.lang.String r1 = "openapi_file_pri"
            r2 = 0
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r1, r2)     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            if (r3 == 0) goto L_0x002c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            java.lang.String r2 = "openApi"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            r1.append(r4)     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            java.lang.String r4 = r1.toString()     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.c.a()     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            java.lang.String r5 = com.alipay.security.mobile.module.a.a.c.a((java.lang.String) r1, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            r3.putString(r4, r5)     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
            r3.commit()     // Catch:{ Throwable -> 0x0031, all -> 0x002e }
        L_0x002c:
            monitor-exit(r0)
            return
        L_0x002e:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x0031:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.e.g.a(android.content.Context, java.lang.String, java.lang.String):void");
    }
}
