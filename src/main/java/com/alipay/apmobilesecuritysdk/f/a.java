package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import com.alipay.security.mobile.module.a.a.c;
import com.alipay.security.mobile.module.c.b;
import com.alipay.security.mobile.module.c.e;
import java.util.HashMap;
import org.json.JSONObject;

public class a {
    public static String a(Context context, String str, String str2) {
        if (context == null || com.alipay.security.mobile.module.a.a.a(str) || com.alipay.security.mobile.module.a.a.a(str2)) {
            return null;
        }
        try {
            String a2 = e.a(context, str, str2, "");
            if (com.alipay.security.mobile.module.a.a.a(a2)) {
                return null;
            }
            return c.b(c.a(), a2);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(String str, String str2) {
        synchronized (a.class) {
            if (com.alipay.security.mobile.module.a.a.a(str) || com.alipay.security.mobile.module.a.a.a(str2)) {
                return null;
            }
            try {
                String a2 = b.a(str);
                if (com.alipay.security.mobile.module.a.a.a(a2)) {
                    return null;
                }
                String string = new JSONObject(a2).getString(str2);
                if (com.alipay.security.mobile.module.a.a.a(string)) {
                    return null;
                }
                String b2 = c.b(c.a(), string);
                return b2;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.security.mobile.module.a.a.a(str) && !com.alipay.security.mobile.module.a.a.a(str2) && context != null) {
            try {
                String a2 = c.a(c.a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a2);
                e.a(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:7|8|(4:10|11|12|13)|14|15|16|17|18|(4:20|21|22|(2:24|(1:28)))|29|30|31) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0075, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0025 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0072 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041 A[Catch:{  }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.Class<com.alipay.apmobilesecuritysdk.f.a> r0 = com.alipay.apmobilesecuritysdk.f.a.class
            monitor-enter(r0)
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r4)     // Catch:{ all -> 0x0076 }
            if (r1 != 0) goto L_0x0074
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r5)     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x0010
            goto L_0x0074
        L_0x0010:
            java.lang.String r1 = com.alipay.security.mobile.module.c.b.a(r4)     // Catch:{  }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{  }
            r2.<init>()     // Catch:{  }
            boolean r3 = com.alipay.security.mobile.module.a.a.b(r1)     // Catch:{  }
            if (r3 == 0) goto L_0x002a
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0025 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0025 }
            goto L_0x002a
        L_0x0025:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{  }
            r2.<init>()     // Catch:{  }
        L_0x002a:
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.c.a()     // Catch:{  }
            java.lang.String r6 = com.alipay.security.mobile.module.a.a.c.a((java.lang.String) r1, (java.lang.String) r6)     // Catch:{  }
            r2.put(r5, r6)     // Catch:{  }
            r2.toString()     // Catch:{  }
            java.lang.System.clearProperty(r4)     // Catch:{ Throwable -> 0x003b }
        L_0x003b:
            boolean r5 = com.alipay.security.mobile.module.c.c.a()     // Catch:{  }
            if (r5 == 0) goto L_0x0072
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{  }
            java.lang.String r6 = ".SystemConfig"
            r5.<init>(r6)     // Catch:{  }
            java.lang.String r6 = java.io.File.separator     // Catch:{  }
            r5.append(r6)     // Catch:{  }
            r5.append(r4)     // Catch:{  }
            java.lang.String r4 = r5.toString()     // Catch:{  }
            boolean r5 = com.alipay.security.mobile.module.c.c.a()     // Catch:{ Throwable -> 0x0072 }
            if (r5 == 0) goto L_0x0072
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x0072 }
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Throwable -> 0x0072 }
            r5.<init>(r6, r4)     // Catch:{ Throwable -> 0x0072 }
            boolean r4 = r5.exists()     // Catch:{ Throwable -> 0x0072 }
            if (r4 == 0) goto L_0x0072
            boolean r4 = r5.isFile()     // Catch:{ Throwable -> 0x0072 }
            if (r4 == 0) goto L_0x0072
            r5.delete()     // Catch:{ Throwable -> 0x0072 }
        L_0x0072:
            monitor-exit(r0)     // Catch:{ all -> 0x0076 }
            return
        L_0x0074:
            monitor-exit(r0)     // Catch:{ all -> 0x0076 }
            return
        L_0x0076:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.f.a.a(java.lang.String, java.lang.String, java.lang.String):void");
    }
}
