package com.alipay.apmobilesecuritysdk.e;

import com.alipay.security.mobile.module.a.a;
import java.util.HashMap;
import java.util.Map;

public final class i {

    /* renamed from: a  reason: collision with root package name */
    private static String f574a = "";

    /* renamed from: b  reason: collision with root package name */
    private static String f575b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f576c = "";
    private static String d = "";
    private static String e = "";
    private static Map<String, String> f = new HashMap();

    public static synchronized String a(String str) {
        synchronized (i.class) {
            String str2 = "apdidTokenCache" + str;
            if (f.containsKey(str2)) {
                String str3 = f.get(str2);
                if (a.b(str3)) {
                    return str3;
                }
            }
            return "";
        }
    }

    public static synchronized void a() {
        synchronized (i.class) {
        }
    }

    public static synchronized void a(b bVar) {
        synchronized (i.class) {
            if (bVar != null) {
                f574a = bVar.a();
                f575b = bVar.b();
                f576c = bVar.c();
            }
        }
    }

    public static synchronized void a(c cVar) {
        synchronized (i.class) {
            if (cVar != null) {
                f574a = cVar.a();
                f575b = cVar.b();
                d = cVar.d();
                e = cVar.e();
                f576c = cVar.c();
            }
        }
    }

    public static synchronized void a(String str, String str2) {
        synchronized (i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f.containsKey(str3)) {
                f.remove(str3);
            }
            f.put(str3, str2);
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0015 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean a(android.content.Context r8, java.lang.String r9) {
        /*
            java.lang.Class<com.alipay.apmobilesecuritysdk.e.i> r0 = com.alipay.apmobilesecuritysdk.e.i.class
            monitor-enter(r0)
            r1 = 86400000(0x5265c00, double:4.2687272E-316)
            long r3 = com.alipay.apmobilesecuritysdk.e.h.a(r8)     // Catch:{ Throwable -> 0x0015 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x0011
            goto L_0x0015
        L_0x0011:
            r1 = r3
            goto L_0x0015
        L_0x0013:
            r8 = move-exception
            goto L_0x0030
        L_0x0015:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x002a }
            long r8 = com.alipay.apmobilesecuritysdk.e.h.h(r8, r9)     // Catch:{ Throwable -> 0x002a }
            r5 = 0
            long r3 = r3 - r8
            long r8 = java.lang.Math.abs(r3)     // Catch:{ Throwable -> 0x002a }
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x002e
            r8 = 1
        L_0x0028:
            monitor-exit(r0)
            return r8
        L_0x002a:
            r8 = move-exception
            com.alipay.apmobilesecuritysdk.c.a.a((java.lang.Throwable) r8)     // Catch:{ all -> 0x0013 }
        L_0x002e:
            r8 = 0
            goto L_0x0028
        L_0x0030:
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.e.i.a(android.content.Context, java.lang.String):boolean");
    }

    public static synchronized String b() {
        String str;
        synchronized (i.class) {
            str = f574a;
        }
        return str;
    }

    public static void b(String str) {
        f574a = str;
    }

    public static synchronized String c() {
        String str;
        synchronized (i.class) {
            str = f575b;
        }
        return str;
    }

    public static void c(String str) {
        f575b = str;
    }

    public static synchronized String d() {
        String str;
        synchronized (i.class) {
            str = d;
        }
        return str;
    }

    public static void d(String str) {
        f576c = str;
    }

    public static synchronized String e() {
        String str;
        synchronized (i.class) {
            str = e;
        }
        return str;
    }

    public static void e(String str) {
        d = str;
    }

    public static synchronized String f() {
        String str;
        synchronized (i.class) {
            str = f576c;
        }
        return str;
    }

    public static void f(String str) {
        e = str;
    }

    public static synchronized c g() {
        c cVar;
        synchronized (i.class) {
            cVar = new c(f574a, f575b, f576c, d, e);
        }
        return cVar;
    }

    public static void h() {
        f.clear();
        f574a = "";
        f575b = "";
        d = "";
        e = "";
        f576c = "";
    }
}
