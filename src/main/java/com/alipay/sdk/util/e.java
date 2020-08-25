package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.l;

public class e {

    /* renamed from: b  reason: collision with root package name */
    public static final String f729b = "failed";

    /* renamed from: a  reason: collision with root package name */
    public Activity f730a;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public IAlixPay f731c;
    /* access modifiers changed from: private */
    public final Object d = IAlixPay.class;
    private boolean e;
    /* access modifiers changed from: private */
    public a f;
    private ServiceConnection g = new f(this);
    private IRemoteServiceCallback h = new g(this);

    public interface a {
        void a();

        void b();
    }

    public e(Activity activity, a aVar) {
        this.f730a = activity;
        this.f = aVar;
    }

    public final String a(String str) {
        if (l.d(this.f730a)) {
            return f729b;
        }
        try {
            l.a a2 = l.a((Context) this.f730a);
            if (a2.a()) {
                return f729b;
            }
            if (a2 != null) {
                if (a2.f746b > 78) {
                    String a3 = l.a();
                    Intent intent = new Intent();
                    intent.setClassName(a3, "com.alipay.android.app.TransProcessPayActivity");
                    this.f730a.startActivity(intent);
                    Thread.sleep(200);
                }
            }
            return b(str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(c.f626b, c.C, th);
        }
    }

    private void a(l.a aVar) throws InterruptedException {
        if (aVar != null && aVar.f746b > 78) {
            String a2 = l.a();
            Intent intent = new Intent();
            intent.setClassName(a2, "com.alipay.android.app.TransProcessPayActivity");
            this.f730a.startActivity(intent);
            Thread.sleep(200);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:32|33|(1:35)|36|37|38|39|40|41|(1:44)|61) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:20|21|22|23|24|25|(1:30)|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ff, code lost:
        if (r7.f730a != null) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0101, code lost:
        r7.f730a.setRequestedOrientation(0);
        r7.e = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013b, code lost:
        if (r7.f730a != null) goto L_0x0101;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0098 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e2 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x011e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:64:0x0146 */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r8) {
        /*
            r7 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = com.alipay.sdk.util.l.a()
            r0.setPackage(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = ".IAlixPay"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.setAction(r1)
            android.app.Activity r1 = r7.f730a
            java.lang.String r1 = com.alipay.sdk.util.l.j(r1)
            android.app.Activity r2 = r7.f730a     // Catch:{ Throwable -> 0x0178 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ Throwable -> 0x0178 }
            android.content.Context r2 = com.stub.StubApp.getOrigApplicationContext(r2)     // Catch:{ Throwable -> 0x0178 }
            android.content.ServiceConnection r3 = r7.g     // Catch:{ Throwable -> 0x0178 }
            r4 = 1
            boolean r0 = r2.bindService(r0, r3, r4)     // Catch:{ Throwable -> 0x0178 }
            if (r0 == 0) goto L_0x0170
            java.lang.Object r0 = r7.d
            monitor-enter(r0)
            com.alipay.android.app.IAlixPay r2 = r7.f731c     // Catch:{ all -> 0x016d }
            if (r2 != 0) goto L_0x0057
            java.lang.Object r2 = r7.d     // Catch:{ InterruptedException -> 0x004f }
            com.alipay.sdk.data.a r3 = com.alipay.sdk.data.a.b()     // Catch:{ InterruptedException -> 0x004f }
            int r3 = r3.a()     // Catch:{ InterruptedException -> 0x004f }
            long r5 = (long) r3     // Catch:{ InterruptedException -> 0x004f }
            r2.wait(r5)     // Catch:{ InterruptedException -> 0x004f }
            goto L_0x0057
        L_0x004f:
            r2 = move-exception
            java.lang.String r3 = "biz"
            java.lang.String r5 = "BindWaitTimeoutEx"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r3, (java.lang.String) r5, (java.lang.Throwable) r2)     // Catch:{ all -> 0x016d }
        L_0x0057:
            monitor-exit(r0)     // Catch:{ all -> 0x016d }
            r0 = 0
            r2 = 0
            com.alipay.android.app.IAlixPay r3 = r7.f731c     // Catch:{ Throwable -> 0x010b }
            if (r3 != 0) goto L_0x00bf
            android.app.Activity r8 = r7.f730a     // Catch:{ Throwable -> 0x010b }
            java.lang.String r8 = com.alipay.sdk.util.l.j(r8)     // Catch:{ Throwable -> 0x010b }
            android.app.Activity r3 = r7.f730a     // Catch:{ Throwable -> 0x010b }
            java.lang.String r3 = com.alipay.sdk.util.l.k(r3)     // Catch:{ Throwable -> 0x010b }
            java.lang.String r4 = "biz"
            java.lang.String r5 = "ClientBindFailed"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x010b }
            r6.<init>()     // Catch:{ Throwable -> 0x010b }
            r6.append(r1)     // Catch:{ Throwable -> 0x010b }
            java.lang.String r1 = "|"
            r6.append(r1)     // Catch:{ Throwable -> 0x010b }
            r6.append(r8)     // Catch:{ Throwable -> 0x010b }
            java.lang.String r8 = "|"
            r6.append(r8)     // Catch:{ Throwable -> 0x010b }
            r6.append(r3)     // Catch:{ Throwable -> 0x010b }
            java.lang.String r8 = r6.toString()     // Catch:{ Throwable -> 0x010b }
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r8)     // Catch:{ Throwable -> 0x010b }
            java.lang.String r8 = "failed"
            com.alipay.android.app.IAlixPay r1 = r7.f731c     // Catch:{ Throwable -> 0x0098 }
            com.alipay.android.app.IRemoteServiceCallback r3 = r7.h     // Catch:{ Throwable -> 0x0098 }
            r1.unregisterCallback(r3)     // Catch:{ Throwable -> 0x0098 }
        L_0x0098:
            android.app.Activity r1 = r7.f730a     // Catch:{ Throwable -> 0x00a7 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Throwable -> 0x00a7 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ Throwable -> 0x00a7 }
            android.content.ServiceConnection r3 = r7.g     // Catch:{ Throwable -> 0x00a7 }
            r1.unbindService(r3)     // Catch:{ Throwable -> 0x00a7 }
        L_0x00a7:
            r7.f = r2
            r7.h = r2
            r7.g = r2
            r7.f731c = r2
            boolean r1 = r7.e
            if (r1 == 0) goto L_0x00be
            android.app.Activity r1 = r7.f730a
            if (r1 == 0) goto L_0x00be
            android.app.Activity r1 = r7.f730a
            r1.setRequestedOrientation(r0)
            r7.e = r0
        L_0x00be:
            return r8
        L_0x00bf:
            android.app.Activity r1 = r7.f730a     // Catch:{ Throwable -> 0x010b }
            int r1 = r1.getRequestedOrientation()     // Catch:{ Throwable -> 0x010b }
            if (r1 != 0) goto L_0x00ce
            android.app.Activity r1 = r7.f730a     // Catch:{ Throwable -> 0x010b }
            r1.setRequestedOrientation(r4)     // Catch:{ Throwable -> 0x010b }
            r7.e = r4     // Catch:{ Throwable -> 0x010b }
        L_0x00ce:
            com.alipay.android.app.IAlixPay r1 = r7.f731c     // Catch:{ Throwable -> 0x010b }
            com.alipay.android.app.IRemoteServiceCallback r3 = r7.h     // Catch:{ Throwable -> 0x010b }
            r1.registerCallback(r3)     // Catch:{ Throwable -> 0x010b }
            com.alipay.android.app.IAlixPay r1 = r7.f731c     // Catch:{ Throwable -> 0x010b }
            java.lang.String r8 = r1.Pay(r8)     // Catch:{ Throwable -> 0x010b }
            com.alipay.android.app.IAlixPay r1 = r7.f731c     // Catch:{ Throwable -> 0x00e2 }
            com.alipay.android.app.IRemoteServiceCallback r3 = r7.h     // Catch:{ Throwable -> 0x00e2 }
            r1.unregisterCallback(r3)     // Catch:{ Throwable -> 0x00e2 }
        L_0x00e2:
            android.app.Activity r1 = r7.f730a     // Catch:{ Throwable -> 0x00f1 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Throwable -> 0x00f1 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ Throwable -> 0x00f1 }
            android.content.ServiceConnection r3 = r7.g     // Catch:{ Throwable -> 0x00f1 }
            r1.unbindService(r3)     // Catch:{ Throwable -> 0x00f1 }
        L_0x00f1:
            r7.f = r2
            r7.h = r2
            r7.g = r2
            r7.f731c = r2
            boolean r1 = r7.e
            if (r1 == 0) goto L_0x013e
            android.app.Activity r1 = r7.f730a
            if (r1 == 0) goto L_0x013e
        L_0x0101:
            android.app.Activity r1 = r7.f730a
            r1.setRequestedOrientation(r0)
            r7.e = r0
            goto L_0x013e
        L_0x0109:
            r8 = move-exception
            goto L_0x013f
        L_0x010b:
            r8 = move-exception
            java.lang.String r1 = "biz"
            java.lang.String r3 = "ClientBindException"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r1, (java.lang.String) r3, (java.lang.Throwable) r8)     // Catch:{ all -> 0x0109 }
            java.lang.String r8 = com.alipay.sdk.app.i.a()     // Catch:{ all -> 0x0109 }
            com.alipay.android.app.IAlixPay r1 = r7.f731c     // Catch:{ Throwable -> 0x011e }
            com.alipay.android.app.IRemoteServiceCallback r3 = r7.h     // Catch:{ Throwable -> 0x011e }
            r1.unregisterCallback(r3)     // Catch:{ Throwable -> 0x011e }
        L_0x011e:
            android.app.Activity r1 = r7.f730a     // Catch:{ Throwable -> 0x012d }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Throwable -> 0x012d }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ Throwable -> 0x012d }
            android.content.ServiceConnection r3 = r7.g     // Catch:{ Throwable -> 0x012d }
            r1.unbindService(r3)     // Catch:{ Throwable -> 0x012d }
        L_0x012d:
            r7.f = r2
            r7.h = r2
            r7.g = r2
            r7.f731c = r2
            boolean r1 = r7.e
            if (r1 == 0) goto L_0x013e
            android.app.Activity r1 = r7.f730a
            if (r1 == 0) goto L_0x013e
            goto L_0x0101
        L_0x013e:
            return r8
        L_0x013f:
            com.alipay.android.app.IAlixPay r1 = r7.f731c     // Catch:{ Throwable -> 0x0146 }
            com.alipay.android.app.IRemoteServiceCallback r3 = r7.h     // Catch:{ Throwable -> 0x0146 }
            r1.unregisterCallback(r3)     // Catch:{ Throwable -> 0x0146 }
        L_0x0146:
            android.app.Activity r1 = r7.f730a     // Catch:{ Throwable -> 0x0155 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Throwable -> 0x0155 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ Throwable -> 0x0155 }
            android.content.ServiceConnection r3 = r7.g     // Catch:{ Throwable -> 0x0155 }
            r1.unbindService(r3)     // Catch:{ Throwable -> 0x0155 }
        L_0x0155:
            r7.f = r2
            r7.h = r2
            r7.g = r2
            r7.f731c = r2
            boolean r1 = r7.e
            if (r1 == 0) goto L_0x016c
            android.app.Activity r1 = r7.f730a
            if (r1 == 0) goto L_0x016c
            android.app.Activity r1 = r7.f730a
            r1.setRequestedOrientation(r0)
            r7.e = r0
        L_0x016c:
            throw r8
        L_0x016d:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x016d }
            throw r8
        L_0x0170:
            java.lang.Throwable r8 = new java.lang.Throwable     // Catch:{ Throwable -> 0x0178 }
            java.lang.String r0 = "bindService fail"
            r8.<init>(r0)     // Catch:{ Throwable -> 0x0178 }
            throw r8     // Catch:{ Throwable -> 0x0178 }
        L_0x0178:
            r8 = move-exception
            java.lang.String r0 = "biz"
            java.lang.String r1 = "ClientBindServiceFailed"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r8)
            java.lang.String r8 = "failed"
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.b(java.lang.String):java.lang.String");
    }

    private void a() {
        this.f730a = null;
    }
}
