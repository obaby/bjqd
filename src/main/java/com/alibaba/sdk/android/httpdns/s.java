package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import java.net.SocketTimeoutException;

public class s {

    /* renamed from: a  reason: collision with root package name */
    private static SharedPreferences f489a;

    /* renamed from: a  reason: collision with other field name */
    private static a f11a = a.ENABLE;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f490c;
    private static long d;
    private static boolean e;
    private static int f;
    private static int g;

    enum a {
        ENABLE,
        PRE_DISABLE,
        DISABLE
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0019, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.lang.String a(com.alibaba.sdk.android.httpdns.n r4) {
        /*
            java.lang.Class<com.alibaba.sdk.android.httpdns.s> r0 = com.alibaba.sdk.android.httpdns.s.class
            monitor-enter(r0)
            com.alibaba.sdk.android.httpdns.n r1 = com.alibaba.sdk.android.httpdns.n.QUERY_HOST     // Catch:{ all -> 0x003d }
            r2 = 0
            if (r4 == r1) goto L_0x001a
            com.alibaba.sdk.android.httpdns.n r1 = com.alibaba.sdk.android.httpdns.n.SNIFF_HOST     // Catch:{ all -> 0x003d }
            if (r4 != r1) goto L_0x000d
            goto L_0x001a
        L_0x000d:
            com.alibaba.sdk.android.httpdns.n r1 = com.alibaba.sdk.android.httpdns.n.QUERY_SCHEDULE_CENTER     // Catch:{ all -> 0x003d }
            if (r4 == r1) goto L_0x0018
            com.alibaba.sdk.android.httpdns.n r1 = com.alibaba.sdk.android.httpdns.n.SNIFF_SCHEDULE_CENTER     // Catch:{ all -> 0x003d }
            if (r4 != r1) goto L_0x0016
            goto L_0x0018
        L_0x0016:
            monitor-exit(r0)
            return r2
        L_0x0018:
            monitor-exit(r0)
            return r2
        L_0x001a:
            com.alibaba.sdk.android.httpdns.s$a r1 = f11a     // Catch:{ all -> 0x003d }
            com.alibaba.sdk.android.httpdns.s$a r3 = com.alibaba.sdk.android.httpdns.s.a.ENABLE     // Catch:{ all -> 0x003d }
            if (r1 == r3) goto L_0x0035
            com.alibaba.sdk.android.httpdns.s$a r1 = f11a     // Catch:{ all -> 0x003d }
            com.alibaba.sdk.android.httpdns.s$a r3 = com.alibaba.sdk.android.httpdns.s.a.PRE_DISABLE     // Catch:{ all -> 0x003d }
            if (r1 != r3) goto L_0x0027
            goto L_0x0035
        L_0x0027:
            com.alibaba.sdk.android.httpdns.n r1 = com.alibaba.sdk.android.httpdns.n.QUERY_HOST     // Catch:{ all -> 0x003d }
            if (r4 != r1) goto L_0x002d
            monitor-exit(r0)
            return r2
        L_0x002d:
            java.lang.String[] r4 = com.alibaba.sdk.android.httpdns.d.f469b     // Catch:{ all -> 0x003d }
            int r1 = f     // Catch:{ all -> 0x003d }
            r4 = r4[r1]     // Catch:{ all -> 0x003d }
            monitor-exit(r0)
            return r4
        L_0x0035:
            java.lang.String[] r4 = com.alibaba.sdk.android.httpdns.d.f469b     // Catch:{ all -> 0x003d }
            int r1 = f     // Catch:{ all -> 0x003d }
            r4 = r4[r1]     // Catch:{ all -> 0x003d }
            monitor-exit(r0)
            return r4
        L_0x003d:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.s.a(com.alibaba.sdk.android.httpdns.n):java.lang.String");
    }

    static synchronized void a(Context context) {
        synchronized (s.class) {
            if (!f490c) {
                synchronized (s.class) {
                    if (!f490c) {
                        if (context != null) {
                            f489a = context.getSharedPreferences("httpdns_config_cache", 0);
                        }
                        e = f489a.getBoolean("status", false);
                        f = f489a.getInt("activiate_ip_index", 0);
                        g = f;
                        d = f489a.getLong("disable_modified_time", 0);
                        if (System.currentTimeMillis() - d >= 86400000) {
                            b(false);
                        }
                        f11a = e ? a.DISABLE : a.ENABLE;
                        f490c = true;
                    }
                }
            }
        }
    }

    static synchronized void a(String str, String str2) {
        synchronized (s.class) {
            if (!(f11a == a.ENABLE || str2 == null || !str2.equals(d.f469b[f]))) {
                StringBuilder sb = new StringBuilder();
                sb.append(f11a == a.DISABLE ? "Disable " : "Pre_disable ");
                sb.append("mode finished. Enter enable mode.");
                g.f(sb.toString());
                f11a = a.ENABLE;
                b(false);
                q.a().e();
                g = f;
            }
        }
    }

    static synchronized void a(String str, String str2, Throwable th) {
        synchronized (s.class) {
            if (a(th) && str2 != null && str2.equals(d.f469b[f])) {
                f();
                if (g == f) {
                    q.a().a(false);
                    o.a().c();
                }
                if (f11a == a.ENABLE) {
                    f11a = a.PRE_DISABLE;
                    g.f("enter pre_disable mode");
                } else if (f11a == a.PRE_DISABLE) {
                    f11a = a.DISABLE;
                    g.f("enter disable mode");
                    b(true);
                    q.a().g(str);
                }
            }
        }
    }

    private static boolean a(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return true;
        }
        if (th instanceof f) {
            f fVar = (f) th;
            return fVar.getErrorCode() == 403 && fVar.getMessage().equals("ServiceLevelDeny");
        }
    }

    static void b(int i) {
        if (f489a != null && i >= 0 && i < d.f469b.length) {
            f = i;
            SharedPreferences.Editor edit = f489a.edit();
            edit.putInt("activiate_ip_index", i);
            edit.putLong("activiated_ip_index_modified_time", System.currentTimeMillis());
            edit.commit();
        }
    }

    static synchronized void b(boolean z) {
        synchronized (s.class) {
            if (e != z) {
                e = z;
                if (f489a != null) {
                    SharedPreferences.Editor edit = f489a.edit();
                    edit.putBoolean("status", e);
                    edit.putLong("disable_modified_time", System.currentTimeMillis());
                    edit.commit();
                }
            }
        }
    }

    static synchronized boolean d() {
        boolean z;
        synchronized (s.class) {
            z = e;
        }
        return z;
    }

    private static void f() {
        f = f == d.f469b.length + -1 ? 0 : f + 1;
        b(f);
    }

    static synchronized void g() {
        synchronized (s.class) {
            b(0);
            g = f;
            q.a().a(true);
        }
    }

    static synchronized void h() {
        synchronized (s.class) {
            q.a().a(true);
        }
    }
}
