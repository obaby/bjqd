package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.c;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private Context f557a;

    /* renamed from: b  reason: collision with root package name */
    private com.alipay.apmobilesecuritysdk.b.a f558b = com.alipay.apmobilesecuritysdk.b.a.a();

    /* renamed from: c  reason: collision with root package name */
    private int f559c = 4;

    public a(Context context) {
        this.f557a = context;
    }

    public static String a(Context context) {
        String b2 = b(context);
        return com.alipay.security.mobile.module.a.a.a(b2) ? h.f(context) : b2;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String a2 = i.a(str);
            if (!com.alipay.security.mobile.module.a.a.a(a2)) {
                return a2;
            }
            String a3 = g.a(context, str);
            i.a(str, a3);
            return !com.alipay.security.mobile.module.a.a.a(a3) ? a3 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        int i = 0;
        while (i < 3) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
                i++;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static String b(Context context) {
        try {
            String b2 = i.b();
            if (!com.alipay.security.mobile.module.a.a.a(b2)) {
                return b2;
            }
            c b3 = d.b(context);
            if (b3 != null) {
                i.a(b3);
                String a2 = b3.a();
                if (com.alipay.security.mobile.module.a.a.b(a2)) {
                    return a2;
                }
            }
            b b4 = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (b4 == null) {
                return "";
            }
            i.a(b4);
            String a3 = b4.a();
            return com.alipay.security.mobile.module.a.a.b(a3) ? a3 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory, ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e0 A[Catch:{ Exception -> 0x0278 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0231 A[Catch:{ Exception -> 0x0278 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r9) {
        /*
            r8 = this;
            android.content.Context r0 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = "tid"
            java.lang.String r2 = ""
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.a(r9, r1, r2)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r2 = "utdid"
            java.lang.String r3 = ""
            java.lang.String r2 = com.alipay.security.mobile.module.a.a.a(r9, r2, r3)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r3 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r3 = a((android.content.Context) r3)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.c.a.a(r0, r1, r2, r3)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r0 = "appName"
            java.lang.String r1 = ""
            java.lang.String r0 = com.alipay.security.mobile.module.a.a.a(r9, r0, r1)     // Catch:{ Exception -> 0x0278 }
            b()     // Catch:{ Exception -> 0x0278 }
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            b((android.content.Context) r1)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            a(r1, r0)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.i.a()     // Catch:{ Exception -> 0x0278 }
            boolean r1 = a()     // Catch:{ Exception -> 0x0278 }
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x00af
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.apmobilesecuritysdk.common.a.a((android.content.Context) r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x0045
            goto L_0x00af
        L_0x0045:
            com.alipay.apmobilesecuritysdk.d.e.a()     // Catch:{ Exception -> 0x0278 }
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.d.e.b(r1, r9)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.e.i.c()     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r1, r4)     // Catch:{ Exception -> 0x0278 }
            r1 = r1 ^ r3
            if (r1 == 0) goto L_0x005a
            goto L_0x00bb
        L_0x005a:
            java.lang.String r1 = "tid"
            java.lang.String r4 = ""
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.a(r9, r1, r4)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = "utdid"
            java.lang.String r5 = ""
            java.lang.String r4 = com.alipay.security.mobile.module.a.a.a(r9, r4, r5)     // Catch:{ Exception -> 0x0278 }
            boolean r5 = com.alipay.security.mobile.module.a.a.b(r1)     // Catch:{ Exception -> 0x0278 }
            if (r5 == 0) goto L_0x007b
            java.lang.String r5 = com.alipay.apmobilesecuritysdk.e.i.d()     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r1, r5)     // Catch:{ Exception -> 0x0278 }
            if (r1 != 0) goto L_0x007b
            goto L_0x00bb
        L_0x007b:
            boolean r1 = com.alipay.security.mobile.module.a.a.b(r4)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x008c
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.e.i.e()     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r4, r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 != 0) goto L_0x008c
            goto L_0x00bb
        L_0x008c:
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.apmobilesecuritysdk.e.i.a((android.content.Context) r1, (java.lang.String) r0)     // Catch:{ Exception -> 0x0278 }
            if (r1 != 0) goto L_0x0095
            goto L_0x00bb
        L_0x0095:
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = a(r1, r0)     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x00a2
            goto L_0x00bb
        L_0x00a2:
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = b((android.content.Context) r1)     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x00ca
            goto L_0x00bb
        L_0x00af:
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = a(r1, r0)     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x00bd
        L_0x00bb:
            r1 = 1
            goto L_0x00cb
        L_0x00bd:
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = b((android.content.Context) r1)     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x00ca
            goto L_0x00bb
        L_0x00ca:
            r1 = 0
        L_0x00cb:
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            com.alipay.security.mobile.module.b.b.a()     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = com.alipay.security.mobile.module.b.b.p()     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.b(r4, r5)     // Catch:{ Exception -> 0x0278 }
            if (r1 != 0) goto L_0x00e0
        L_0x00dd:
            r9 = 0
            goto L_0x0216
        L_0x00e0:
            com.alipay.apmobilesecuritysdk.c.b r1 = new com.alipay.apmobilesecuritysdk.c.b     // Catch:{ Exception -> 0x0278 }
            r1.<init>()     // Catch:{ Exception -> 0x0278 }
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.b.a r4 = com.alipay.apmobilesecuritysdk.b.a.a()     // Catch:{ Exception -> 0x0278 }
            int r4 = r4.b()     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper.startUmidTaskSync(r1, r4)     // Catch:{ Exception -> 0x0278 }
            com.alipay.security.mobile.module.http.model.c r1 = r8.b((java.util.Map<java.lang.String, java.lang.String>) r9)     // Catch:{ Exception -> 0x0278 }
            r4 = 3
            r5 = 2
            if (r1 == 0) goto L_0x0113
            boolean r6 = r1.f780a     // Catch:{ Exception -> 0x0278 }
            if (r6 == 0) goto L_0x0108
            java.lang.String r6 = r1.h     // Catch:{ Exception -> 0x0278 }
            boolean r6 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0278 }
            if (r6 != 0) goto L_0x0113
            r5 = 1
            goto L_0x0113
        L_0x0108:
            java.lang.String r6 = "APPKEY_ERROR"
            java.lang.String r7 = r1.f781b     // Catch:{ Exception -> 0x0278 }
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x0278 }
            if (r6 == 0) goto L_0x0113
            r5 = 3
        L_0x0113:
            if (r5 == r3) goto L_0x0142
            if (r5 == r4) goto L_0x013f
            if (r1 == 0) goto L_0x012d
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = "Server error, result:"
            r9.<init>(r4)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = r1.f781b     // Catch:{ Exception -> 0x0278 }
            r9.append(r1)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0278 }
        L_0x0129:
            com.alipay.apmobilesecuritysdk.c.a.a((java.lang.String) r9)     // Catch:{ Exception -> 0x0278 }
            goto L_0x0130
        L_0x012d:
            java.lang.String r9 = "Server error, returned null"
            goto L_0x0129
        L_0x0130:
            android.content.Context r9 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r9 = a(r9, r0)     // Catch:{ Exception -> 0x0278 }
            boolean r9 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r9)     // Catch:{ Exception -> 0x0278 }
            if (r9 == 0) goto L_0x00dd
            r9 = 4
            goto L_0x0216
        L_0x013f:
            r9 = 1
            goto L_0x0216
        L_0x0142:
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = "1"
            java.lang.String r6 = r1.j     // Catch:{ Exception -> 0x0278 }
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.a((android.content.Context) r4, (boolean) r5)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = r1.k     // Catch:{ Exception -> 0x0278 }
            if (r5 != 0) goto L_0x0158
            java.lang.String r5 = "0"
            goto L_0x015a
        L_0x0158:
            java.lang.String r5 = r1.k     // Catch:{ Exception -> 0x0278 }
        L_0x015a:
            com.alipay.apmobilesecuritysdk.e.h.d(r4, r5)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = r1.l     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.e(r4, r5)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = r1.m     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.a((android.content.Context) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = r1.n     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.f(r4, r5)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = r1.p     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.g(r4, r5)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r4 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.d.e.b(r4, r9)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.i.c(r4)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = r1.i     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.i.a((java.lang.String) r0, (java.lang.String) r4)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = r1.h     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.i.b(r4)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = r1.o     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.i.d(r1)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = "tid"
            java.lang.String r4 = ""
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.a(r9, r1, r4)     // Catch:{ Exception -> 0x0278 }
            boolean r4 = com.alipay.security.mobile.module.a.a.b(r1)     // Catch:{ Exception -> 0x0278 }
            if (r4 == 0) goto L_0x01ad
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.e.i.d()     // Catch:{ Exception -> 0x0278 }
            boolean r4 = com.alipay.security.mobile.module.a.a.a(r1, r4)     // Catch:{ Exception -> 0x0278 }
            if (r4 != 0) goto L_0x01ad
            com.alipay.apmobilesecuritysdk.e.i.e(r1)     // Catch:{ Exception -> 0x0278 }
            goto L_0x01b1
        L_0x01ad:
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.e.i.d()     // Catch:{ Exception -> 0x0278 }
        L_0x01b1:
            com.alipay.apmobilesecuritysdk.e.i.e(r1)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r1 = "utdid"
            java.lang.String r4 = ""
            java.lang.String r9 = com.alipay.security.mobile.module.a.a.a(r9, r1, r4)     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.b(r9)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x01d0
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.e.i.e()     // Catch:{ Exception -> 0x0278 }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r9, r1)     // Catch:{ Exception -> 0x0278 }
            if (r1 != 0) goto L_0x01d0
            com.alipay.apmobilesecuritysdk.e.i.f(r9)     // Catch:{ Exception -> 0x0278 }
            goto L_0x01d4
        L_0x01d0:
            java.lang.String r9 = com.alipay.apmobilesecuritysdk.e.i.e()     // Catch:{ Exception -> 0x0278 }
        L_0x01d4:
            com.alipay.apmobilesecuritysdk.e.i.f(r9)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.i.a()     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.c r9 = com.alipay.apmobilesecuritysdk.e.i.g()     // Catch:{ Exception -> 0x0278 }
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.d.a(r1, r9)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.d.a()     // Catch:{ Exception -> 0x0278 }
            android.content.Context r9 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.b r1 = new com.alipay.apmobilesecuritysdk.e.b     // Catch:{ Exception -> 0x0278 }
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.e.i.b()     // Catch:{ Exception -> 0x0278 }
            java.lang.String r5 = com.alipay.apmobilesecuritysdk.e.i.c()     // Catch:{ Exception -> 0x0278 }
            java.lang.String r6 = com.alipay.apmobilesecuritysdk.e.i.f()     // Catch:{ Exception -> 0x0278 }
            r1.<init>(r4, r5, r6)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.a.a(r9, r1)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.a.a()     // Catch:{ Exception -> 0x0278 }
            java.lang.String r9 = com.alipay.apmobilesecuritysdk.e.i.a((java.lang.String) r0)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r1 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.g.a(r1, r0, r9)     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.g.a()     // Catch:{ Exception -> 0x0278 }
            android.content.Context r9 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.e.h.a((android.content.Context) r9, (java.lang.String) r0, (long) r4)     // Catch:{ Exception -> 0x0278 }
            goto L_0x00dd
        L_0x0216:
            r8.f559c = r9     // Catch:{ Exception -> 0x0278 }
            android.content.Context r9 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            com.alipay.apmobilesecuritysdk.b.a r0 = r8.f558b     // Catch:{ Exception -> 0x0278 }
            java.lang.String r0 = r0.c()     // Catch:{ Exception -> 0x0278 }
            com.alipay.security.mobile.module.http.v2.a r9 = com.alipay.security.mobile.module.http.d.a(r9, r0)     // Catch:{ Exception -> 0x0278 }
            android.content.Context r0 = r8.f557a     // Catch:{ Exception -> 0x0278 }
            r1 = 0
            java.lang.String r4 = "connectivity"
            java.lang.Object r4 = r0.getSystemService(r4)     // Catch:{ Exception -> 0x0278 }
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch:{ Exception -> 0x0278 }
            if (r4 == 0) goto L_0x0235
            android.net.NetworkInfo r1 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x0278 }
        L_0x0235:
            if (r1 == 0) goto L_0x0244
            boolean r4 = r1.isConnected()     // Catch:{ Exception -> 0x0278 }
            if (r4 == 0) goto L_0x0244
            int r1 = r1.getType()     // Catch:{ Exception -> 0x0278 }
            if (r1 != r3) goto L_0x0244
            r2 = 1
        L_0x0244:
            if (r2 == 0) goto L_0x027c
            boolean r1 = com.alipay.apmobilesecuritysdk.e.h.c(r0)     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x027c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0278 }
            r1.<init>()     // Catch:{ Exception -> 0x0278 }
            java.io.File r0 = r0.getFilesDir()     // Catch:{ Exception -> 0x0278 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x0278 }
            r1.append(r0)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r0 = "/log/ap"
            r1.append(r0)     // Catch:{ Exception -> 0x0278 }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x0278 }
            com.alipay.security.mobile.module.d.b r1 = new com.alipay.security.mobile.module.d.b     // Catch:{ Exception -> 0x0278 }
            r1.<init>(r0, r9)     // Catch:{ Exception -> 0x0278 }
            java.lang.Thread r9 = new java.lang.Thread     // Catch:{ Exception -> 0x0278 }
            com.alipay.security.mobile.module.d.c r0 = new com.alipay.security.mobile.module.d.c     // Catch:{ Exception -> 0x0278 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0278 }
            r9.<init>(r0)     // Catch:{ Exception -> 0x0278 }
            r9.start()     // Catch:{ Exception -> 0x0278 }
            goto L_0x027c
        L_0x0278:
            r9 = move-exception
            com.alipay.apmobilesecuritysdk.c.a.a((java.lang.Throwable) r9)
        L_0x027c:
            int r9 = r8.f559c
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }

    private com.alipay.security.mobile.module.http.model.c b(Map<String, String> map) {
        b b2;
        b c2;
        try {
            Context context = this.f557a;
            com.alipay.security.mobile.module.http.model.d dVar = new com.alipay.security.mobile.module.http.model.d();
            String a2 = com.alipay.security.mobile.module.a.a.a(map, DispatchConstants.APP_NAME, "");
            String a3 = com.alipay.security.mobile.module.a.a.a(map, "sessionId", "");
            String a4 = com.alipay.security.mobile.module.a.a.a(map, "rpcVersion", "");
            String a5 = a(context, a2);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = h.d(context);
            if (com.alipay.security.mobile.module.a.a.b(a3)) {
                dVar.f785c = a3;
            } else {
                dVar.f785c = a5;
            }
            dVar.d = securityToken;
            dVar.e = d;
            dVar.f783a = DispatchConstants.ANDROID;
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            c c3 = d.c(context);
            if (c3 != null) {
                str2 = c3.a();
                str3 = c3.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(str2) && (c2 = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str2 = c2.a();
                str3 = c2.c();
            }
            c b3 = d.b();
            if (b3 != null) {
                str = b3.a();
                str4 = b3.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(str) && (b2 = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str = b2.a();
                str4 = b2.c();
            }
            dVar.h = str2;
            dVar.g = str;
            dVar.j = a4;
            if (com.alipay.security.mobile.module.a.a.a(str2)) {
                dVar.f784b = str;
                dVar.i = str4;
            } else {
                dVar.f784b = str2;
                dVar.i = str3;
            }
            dVar.f = e.a(context, map);
            return com.alipay.security.mobile.module.http.d.a(this.f557a, this.f558b.c()).a(dVar);
        } catch (Throwable th) {
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }
}
