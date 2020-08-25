package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AuthTask {

    /* renamed from: a  reason: collision with root package name */
    static final Object f593a = e.class;

    /* renamed from: b  reason: collision with root package name */
    private static final int f594b = 73;

    /* renamed from: c  reason: collision with root package name */
    private Activity f595c;
    private a d;

    public AuthTask(Activity activity) {
        this.f595c = activity;
        b a2 = b.a();
        Activity activity2 = this.f595c;
        c.a();
        a2.a((Context) activity2);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.d = new a(activity, a.f751c);
    }

    private e.a a() {
        return new a(this);
    }

    private void b() {
        if (this.d != null) {
            this.d.a();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.d != null) {
            this.d.b();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return j.a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String a2;
        Activity activity;
        String str2;
        if (z) {
            try {
                b();
            } catch (Exception unused) {
                com.alipay.sdk.data.a.b().a((Context) this.f595c);
                c();
                activity = this.f595c;
            } catch (Throwable th) {
                throw th;
            }
        }
        b a3 = b.a();
        Activity activity2 = this.f595c;
        c.a();
        a3.a((Context) activity2);
        a2 = i.a();
        Activity activity3 = this.f595c;
        String a4 = new com.alipay.sdk.sys.a(this.f595c).a(str);
        if (a((Context) activity3)) {
            String a5 = new e(activity3, new a(this)).a(a4);
            if (!TextUtils.equals(a5, e.f729b)) {
                if (TextUtils.isEmpty(a5)) {
                    str2 = i.a();
                    a2 = str2;
                    com.alipay.sdk.data.a.b().a((Context) this.f595c);
                    c();
                    activity = this.f595c;
                    com.alipay.sdk.app.statistic.a.a((Context) activity, str);
                } else {
                    a2 = a5;
                    com.alipay.sdk.data.a.b().a((Context) this.f595c);
                    c();
                    activity = this.f595c;
                    com.alipay.sdk.app.statistic.a.a((Context) activity, str);
                }
            }
        }
        str2 = b(activity3, a4);
        a2 = str2;
        com.alipay.sdk.data.a.b().a((Context) this.f595c);
        c();
        activity = this.f595c;
        com.alipay.sdk.app.statistic.a.a((Context) activity, str);
        return a2;
    }

    private String a(Activity activity, String str) {
        String a2 = new com.alipay.sdk.sys.a(this.f595c).a(str);
        if (!a((Context) activity)) {
            return b(activity, a2);
        }
        String a3 = new e(activity, new a(this)).a(a2);
        if (TextUtils.equals(a3, e.f729b)) {
            return b(activity, a2);
        }
        return TextUtils.isEmpty(a3) ? i.a() : a3;
    }

    private String b(Activity activity, String str) {
        j jVar;
        b();
        try {
            List<com.alipay.sdk.protocol.b> a2 = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a((Context) activity, str).a().optJSONObject(com.alipay.sdk.cons.c.f672c).optJSONObject(com.alipay.sdk.cons.c.d));
            c();
            for (int i = 0; i < a2.size(); i++) {
                if (a2.get(i).f707a == com.alipay.sdk.protocol.a.WapPay) {
                    String a3 = a(a2.get(i));
                    c();
                    return a3;
                }
            }
        } catch (IOException e) {
            j a4 = j.a(j.NETWORK_ERROR.h);
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.f625a, (Throwable) e);
            c();
            jVar = a4;
        } catch (Throwable th) {
            c();
            throw th;
        }
        c();
        jVar = null;
        if (jVar == null) {
            jVar = j.a(j.FAILED.h);
        }
        return i.a(jVar.h, jVar.i, "");
    }

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(l.a(), 128);
            if (packageInfo != null && packageInfo.versionCode >= 73) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0 = com.alipay.sdk.app.i.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return com.alipay.sdk.app.i.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0029, code lost:
        r4 = com.alipay.sdk.app.i.f617a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002f, code lost:
        if (android.text.TextUtils.isEmpty(r4) == false) goto L_?;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(com.alipay.sdk.protocol.b r4) {
        /*
            r3 = this;
            java.lang.String[] r4 = r4.f708b
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r1 = 0
            r4 = r4[r1]
            java.lang.String r1 = "url"
            r0.putString(r1, r4)
            android.content.Intent r4 = new android.content.Intent
            android.app.Activity r1 = r3.f595c
            java.lang.Class<com.alipay.sdk.app.H5AuthActivity> r2 = com.alipay.sdk.app.H5AuthActivity.class
            r4.<init>(r1, r2)
            r4.putExtras(r0)
            android.app.Activity r0 = r3.f595c
            r0.startActivity(r4)
            java.lang.Object r4 = f593a
            monitor-enter(r4)
            java.lang.Object r0 = f593a     // Catch:{ InterruptedException -> 0x0038 }
            r0.wait()     // Catch:{ InterruptedException -> 0x0038 }
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            java.lang.String r4 = com.alipay.sdk.app.i.f617a
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0035
            java.lang.String r4 = com.alipay.sdk.app.i.a()
        L_0x0035:
            return r4
        L_0x0036:
            r0 = move-exception
            goto L_0x003e
        L_0x0038:
            java.lang.String r0 = com.alipay.sdk.app.i.a()     // Catch:{ all -> 0x0036 }
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            return r0
        L_0x003e:
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.a(com.alipay.sdk.protocol.b):java.lang.String");
    }
}
