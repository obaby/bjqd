package anetwork.channel.cookie;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieSyncManager;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anetwork.channel.http.NetworkSdkSetting;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class CookieManager {
    public static final String TAG = "anet.CookieManager";

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f387a = false;

    /* renamed from: b  reason: collision with root package name */
    private static android.webkit.CookieManager f388b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f389c = true;

    public static synchronized void setup(Context context) {
        synchronized (CookieManager.class) {
            if (!f387a) {
                try {
                    if (Build.VERSION.SDK_INT < 21) {
                        CookieSyncManager.createInstance(context);
                    }
                    f388b = android.webkit.CookieManager.getInstance();
                    f388b.setAcceptCookie(true);
                    if (Build.VERSION.SDK_INT < 21) {
                        f388b.removeExpiredCookie();
                    }
                } catch (Throwable th) {
                    f389c = false;
                    ALog.e(TAG, "Cookie Manager setup failed!!!", (String) null, th, new Object[0]);
                }
                f387a = true;
            }
        }
    }

    private static boolean a() {
        if (!f387a && NetworkSdkSetting.getContext() != null) {
            setup(NetworkSdkSetting.getContext());
        }
        return f387a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void setCookie(java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.Class<anetwork.channel.cookie.CookieManager> r0 = anetwork.channel.cookie.CookieManager.class
            monitor-enter(r0)
            boolean r1 = a()     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x004c
            boolean r1 = f389c     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x000e
            goto L_0x004c
        L_0x000e:
            android.webkit.CookieManager r1 = f388b     // Catch:{ Throwable -> 0x0027 }
            r1.setCookie(r5, r6)     // Catch:{ Throwable -> 0x0027 }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0027 }
            r2 = 21
            if (r1 >= r2) goto L_0x0021
            android.webkit.CookieSyncManager r1 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ Throwable -> 0x0027 }
            r1.sync()     // Catch:{ Throwable -> 0x0027 }
            goto L_0x004a
        L_0x0021:
            android.webkit.CookieManager r1 = f388b     // Catch:{ Throwable -> 0x0027 }
            r1.flush()     // Catch:{ Throwable -> 0x0027 }
            goto L_0x004a
        L_0x0027:
            r1 = move-exception
            java.lang.String r2 = "anet.CookieManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r3.<init>()     // Catch:{ all -> 0x004e }
            java.lang.String r4 = "set cookie failed. url="
            r3.append(r4)     // Catch:{ all -> 0x004e }
            r3.append(r5)     // Catch:{ all -> 0x004e }
            java.lang.String r5 = " cookies="
            r3.append(r5)     // Catch:{ all -> 0x004e }
            r3.append(r6)     // Catch:{ all -> 0x004e }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x004e }
            r6 = 0
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x004e }
            anet.channel.util.ALog.e(r2, r5, r6, r1, r3)     // Catch:{ all -> 0x004e }
        L_0x004a:
            monitor-exit(r0)
            return
        L_0x004c:
            monitor-exit(r0)
            return
        L_0x004e:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.cookie.CookieManager.setCookie(java.lang.String, java.lang.String):void");
    }

    public static void setCookie(String str, Map<String, List<String>> map) {
        if (str != null && map != null) {
            try {
                for (Map.Entry next : map.entrySet()) {
                    String str2 = (String) next.getKey();
                    if (str2 != null && (str2.equalsIgnoreCase(HttpConstant.SET_COOKIE) || str2.equalsIgnoreCase(HttpConstant.SET_COOKIE2))) {
                        for (String cookie : (List) next.getValue()) {
                            setCookie(str, cookie);
                        }
                    }
                }
            } catch (Exception e) {
                ALog.e(TAG, "set cookie failed", (String) null, e, "url", str, "\nheaders", map);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String getCookie(java.lang.String r6) {
        /*
            java.lang.Class<anetwork.channel.cookie.CookieManager> r0 = anetwork.channel.cookie.CookieManager.class
            monitor-enter(r0)
            boolean r1 = a()     // Catch:{ all -> 0x0035 }
            r2 = 0
            if (r1 == 0) goto L_0x0033
            boolean r1 = f389c     // Catch:{ all -> 0x0035 }
            if (r1 != 0) goto L_0x000f
            goto L_0x0033
        L_0x000f:
            android.webkit.CookieManager r1 = f388b     // Catch:{ Throwable -> 0x0016 }
            java.lang.String r1 = r1.getCookie(r6)     // Catch:{ Throwable -> 0x0016 }
            goto L_0x0031
        L_0x0016:
            r1 = move-exception
            java.lang.String r3 = "anet.CookieManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r4.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "get cookie failed. url="
            r4.append(r5)     // Catch:{ all -> 0x0035 }
            r4.append(r6)     // Catch:{ all -> 0x0035 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0035 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0035 }
            anet.channel.util.ALog.e(r3, r6, r2, r1, r4)     // Catch:{ all -> 0x0035 }
            r1 = r2
        L_0x0031:
            monitor-exit(r0)
            return r1
        L_0x0033:
            monitor-exit(r0)
            return r2
        L_0x0035:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.cookie.CookieManager.getCookie(java.lang.String):java.lang.String");
    }
}
