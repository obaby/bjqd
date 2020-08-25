package com.amap.api.location;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.loc.di;

public class APSService extends Service {

    /* renamed from: a  reason: collision with root package name */
    APSServiceBase f817a;

    /* renamed from: b  reason: collision with root package name */
    int f818b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f819c = false;

    public IBinder onBind(Intent intent) {
        try {
            return this.f817a.onBind(intent);
        } catch (Throwable th) {
            di.a(th, "APSService", "onBind");
            return null;
        }
    }

    public void onCreate() {
        onCreate(this);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0033 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037 A[Catch:{ Throwable -> 0x0044 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.content.Context r8) {
        /*
            r7 = this;
            boolean r0 = com.loc.do.d(r8)     // Catch:{ Throwable -> 0x0033 }
            if (r0 == 0) goto L_0x0029
            com.loc.u r2 = com.loc.di.b()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r0 = "AY29tLmFtYXAuYXBpLmxvY2F0aW9uLkFQU1NlcnZpY2VXcmFwcGVy"
            java.lang.String r3 = com.loc.v.c(r0)     // Catch:{ Throwable -> 0x0033 }
            java.lang.Class<com.loc.f> r4 = com.loc.f.class
            r0 = 1
            java.lang.Class[] r5 = new java.lang.Class[r0]     // Catch:{ Throwable -> 0x0033 }
            java.lang.Class<android.content.Context> r1 = android.content.Context.class
            r6 = 0
            r5[r6] = r1     // Catch:{ Throwable -> 0x0033 }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0033 }
            r0[r6] = r8     // Catch:{ Throwable -> 0x0033 }
            r1 = r8
            r6 = r0
            java.lang.Object r0 = com.loc.au.a(r1, r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x0033 }
            com.amap.api.location.APSServiceBase r0 = (com.amap.api.location.APSServiceBase) r0     // Catch:{ Throwable -> 0x0033 }
        L_0x0026:
            r7.f817a = r0     // Catch:{ Throwable -> 0x0033 }
            goto L_0x0033
        L_0x0029:
            com.amap.api.location.APSServiceBase r0 = r7.f817a     // Catch:{ Throwable -> 0x0033 }
            if (r0 != 0) goto L_0x0033
            com.loc.f r0 = new com.loc.f     // Catch:{ Throwable -> 0x0033 }
            r0.<init>(r8)     // Catch:{ Throwable -> 0x0033 }
            goto L_0x0026
        L_0x0033:
            com.amap.api.location.APSServiceBase r0 = r7.f817a     // Catch:{ Throwable -> 0x0044 }
            if (r0 != 0) goto L_0x003e
            com.loc.f r0 = new com.loc.f     // Catch:{ Throwable -> 0x0044 }
            r0.<init>(r8)     // Catch:{ Throwable -> 0x0044 }
            r7.f817a = r0     // Catch:{ Throwable -> 0x0044 }
        L_0x003e:
            com.amap.api.location.APSServiceBase r8 = r7.f817a     // Catch:{ Throwable -> 0x0044 }
            r8.onCreate()     // Catch:{ Throwable -> 0x0044 }
            goto L_0x004c
        L_0x0044:
            r8 = move-exception
            java.lang.String r0 = "APSService"
            java.lang.String r1 = "onCreate"
            com.loc.di.a(r8, r0, r1)
        L_0x004c:
            super.onCreate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.APSService.onCreate(android.content.Context):void");
    }

    public void onDestroy() {
        try {
            this.f817a.onDestroy();
            if (this.f819c) {
                stopForeground(true);
            }
        } catch (Throwable th) {
            di.a(th, "APSService", "onDestroy");
        }
        super.onDestroy();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:(4:1|2|3|(2:5|(1:8))(2:9|(4:11|(1:15)|16|(1:18)(1:19))))|20|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        com.loc.di.a(r0, "APSService", "onStartCommand");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
        return super.onStartCommand(r5, r6, r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r5, int r6, int r7) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x004a
            java.lang.String r0 = "g"
            r1 = 0
            int r0 = r5.getIntExtra(r0, r1)     // Catch:{ Throwable -> 0x004a }
            r2 = 1
            if (r0 != r2) goto L_0x0029
            java.lang.String r0 = "i"
            int r0 = r5.getIntExtra(r0, r1)     // Catch:{ Throwable -> 0x004a }
            java.lang.String r1 = "h"
            android.os.Parcelable r1 = r5.getParcelableExtra(r1)     // Catch:{ Throwable -> 0x004a }
            android.app.Notification r1 = (android.app.Notification) r1     // Catch:{ Throwable -> 0x004a }
            if (r0 == 0) goto L_0x004a
            if (r1 == 0) goto L_0x004a
            r4.startForeground(r0, r1)     // Catch:{ Throwable -> 0x004a }
            r4.f819c = r2     // Catch:{ Throwable -> 0x004a }
            int r0 = r4.f818b     // Catch:{ Throwable -> 0x004a }
            int r0 = r0 + r2
            r4.f818b = r0     // Catch:{ Throwable -> 0x004a }
            goto L_0x004a
        L_0x0029:
            r3 = 2
            if (r0 != r3) goto L_0x004a
            java.lang.String r0 = "j"
            boolean r0 = r5.getBooleanExtra(r0, r2)     // Catch:{ Throwable -> 0x004a }
            if (r0 == 0) goto L_0x003d
            int r0 = r4.f818b     // Catch:{ Throwable -> 0x004a }
            if (r0 <= 0) goto L_0x003d
            int r0 = r4.f818b     // Catch:{ Throwable -> 0x004a }
            int r0 = r0 - r2
            r4.f818b = r0     // Catch:{ Throwable -> 0x004a }
        L_0x003d:
            int r0 = r4.f818b     // Catch:{ Throwable -> 0x004a }
            if (r0 > 0) goto L_0x0047
            r4.stopForeground(r2)     // Catch:{ Throwable -> 0x004a }
            r4.f819c = r1     // Catch:{ Throwable -> 0x004a }
            goto L_0x004a
        L_0x0047:
            r4.stopForeground(r1)     // Catch:{ Throwable -> 0x004a }
        L_0x004a:
            com.amap.api.location.APSServiceBase r0 = r4.f817a     // Catch:{ Throwable -> 0x0051 }
            int r0 = r0.onStartCommand(r5, r6, r7)     // Catch:{ Throwable -> 0x0051 }
            return r0
        L_0x0051:
            r0 = move-exception
            java.lang.String r1 = "APSService"
            java.lang.String r2 = "onStartCommand"
            com.loc.di.a(r0, r1, r2)
            int r5 = super.onStartCommand(r5, r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.APSService.onStartCommand(android.content.Intent, int, int):int");
    }
}
