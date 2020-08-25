package com.alipay.sdk.sys;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.data.c;
import com.stub.StubApp;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

public final class b {

    /* renamed from: b  reason: collision with root package name */
    private static b f713b;

    /* renamed from: a  reason: collision with root package name */
    public Context f714a;

    private b() {
    }

    public static b a() {
        if (f713b == null) {
            f713b = new b();
        }
        return f713b;
    }

    private Context d() {
        return this.f714a;
    }

    public final void a(Context context) {
        this.f714a = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    private static c e() {
        return c.a();
    }

    public static boolean b() {
        String[] strArr = {"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                String str = strArr[i] + "su";
                if (new File(str).exists()) {
                    String a2 = a(new String[]{"ls", "-l", str});
                    if (TextUtils.isEmpty(a2) || a2.indexOf("root") == a2.lastIndexOf("root")) {
                        return false;
                    }
                    return true;
                }
                i++;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0035, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        r2 = "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0031 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0035 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String[] r4) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.ProcessBuilder r2 = new java.lang.ProcessBuilder     // Catch:{ Throwable -> 0x003f, all -> 0x0039 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0039 }
            r4 = 0
            r2.redirectErrorStream(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0039 }
            java.lang.Process r4 = r2.start()     // Catch:{ Throwable -> 0x003f, all -> 0x0039 }
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            java.io.OutputStream r2 = r4.getOutputStream()     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            java.io.InputStream r3 = r4.getInputStream()     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            java.lang.String r2 = r2.readLine()     // Catch:{ Throwable -> 0x0037, all -> 0x0035 }
            java.lang.String r0 = "exit\n"
            r1.writeBytes(r0)     // Catch:{ Throwable -> 0x0031, all -> 0x0035 }
            r1.flush()     // Catch:{ Throwable -> 0x0031, all -> 0x0035 }
            r4.waitFor()     // Catch:{ Throwable -> 0x0031, all -> 0x0035 }
        L_0x0031:
            r4.destroy()     // Catch:{ Exception -> 0x0042 }
            goto L_0x0042
        L_0x0035:
            r0 = move-exception
            goto L_0x003b
        L_0x0037:
            r2 = r0
            goto L_0x0031
        L_0x0039:
            r0 = move-exception
            r4 = r1
        L_0x003b:
            r4.destroy()     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            throw r0
        L_0x003f:
            r2 = r0
            r4 = r1
            goto L_0x0031
        L_0x0042:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.sys.b.a(java.lang.String[]):java.lang.String");
    }

    public final String c() {
        try {
            return UTDevice.getUtdid(this.f714a);
        } catch (Throwable th) {
            a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.j, th);
            return "";
        }
    }
}
