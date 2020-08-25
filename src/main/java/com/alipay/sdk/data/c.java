package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.cons.a;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.l;
import com.stub.StubApp;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class c {
    private static final String d = "virtualImeiAndImsi";
    private static final String e = "virtual_imei";
    private static final String f = "virtual_imsi";
    private static c g;

    /* renamed from: a  reason: collision with root package name */
    public String f678a;

    /* renamed from: b  reason: collision with root package name */
    public String f679b = "sdk-and-lite";

    /* renamed from: c  reason: collision with root package name */
    public String f680c;

    private static String d() {
        return "1";
    }

    private static String e() {
        return "-1;-1";
    }

    private String c() {
        return this.f680c;
    }

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (g == null) {
                g = new c();
            }
            cVar = g;
        }
        return cVar;
    }

    public final synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(b.a().f714a).edit().putString(com.alipay.sdk.cons.b.i, str).commit();
            a.f666c = str;
        }
    }

    private static String b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    private String a(com.alipay.sdk.tid.b bVar) {
        String str;
        String b2;
        String str2;
        com.alipay.sdk.tid.b bVar2 = bVar;
        Context context = b.a().f714a;
        com.alipay.sdk.util.a a2 = com.alipay.sdk.util.a.a(context);
        if (TextUtils.isEmpty(this.f678a)) {
            String b3 = l.b();
            String c2 = l.c();
            String g2 = l.g(context);
            String i = l.i(context);
            String h = l.h(context);
            String f2 = Float.toString(new TextView(context).getTextSize());
            this.f678a = "Msp/15.5.5" + " (" + b3 + h.f735b + c2 + h.f735b + g2 + h.f735b + i + h.f735b + h + h.f735b + f2;
        }
        String str3 = com.alipay.sdk.util.a.b(context).p;
        String d2 = l.d();
        String a3 = a2.a();
        String b4 = a2.b();
        Context context2 = b.a().f714a;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(f, (String) null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().f719a)) {
                String c3 = b.a().c();
                if (TextUtils.isEmpty(c3)) {
                    str2 = b();
                } else {
                    str2 = c3.substring(3, 18);
                }
            } else {
                str2 = com.alipay.sdk.util.a.a(context2).a();
            }
            string = str2;
            sharedPreferences.edit().putString(f, string).commit();
        }
        Context context3 = b.a().f714a;
        SharedPreferences sharedPreferences2 = context3.getSharedPreferences(d, 0);
        String string2 = sharedPreferences2.getString(e, (String) null);
        if (TextUtils.isEmpty(string2)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().f719a)) {
                b2 = b();
            } else {
                b2 = com.alipay.sdk.util.a.a(context3).b();
            }
            string2 = b2;
            sharedPreferences2.edit().putString(e, string2).commit();
        }
        if (bVar2 != null) {
            this.f680c = bVar2.f720b;
        }
        String replace = Build.MANUFACTURER.replace(h.f735b, " ");
        String replace2 = Build.MODEL.replace(h.f735b, " ");
        boolean b5 = b.b();
        String str4 = a2.f722a;
        WifiInfo connectionInfo = ((WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")).getConnectionInfo();
        String ssid = connectionInfo != null ? connectionInfo.getSSID() : "-1";
        Context context4 = context;
        WifiInfo connectionInfo2 = ((WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo2 != null) {
            str = connectionInfo2.getBSSID();
        } else {
            str = "00";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f678a);
        sb.append(h.f735b);
        sb.append(str3);
        sb.append(h.f735b);
        sb.append(d2);
        sb.append(h.f735b);
        sb.append("1");
        sb.append(h.f735b);
        sb.append(a3);
        sb.append(h.f735b);
        sb.append(b4);
        sb.append(h.f735b);
        sb.append(this.f680c);
        sb.append(h.f735b);
        sb.append(replace);
        sb.append(h.f735b);
        sb.append(replace2);
        sb.append(h.f735b);
        sb.append(b5);
        sb.append(h.f735b);
        sb.append(str4);
        sb.append(";-1;-1;");
        sb.append(this.f679b);
        sb.append(h.f735b);
        sb.append(string);
        sb.append(h.f735b);
        sb.append(string2);
        sb.append(h.f735b);
        sb.append(ssid);
        sb.append(h.f735b);
        sb.append(str);
        com.alipay.sdk.tid.b bVar3 = bVar;
        if (bVar3 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(com.alipay.sdk.cons.b.f669c, bVar3.f719a);
            hashMap.put(com.alipay.sdk.cons.b.g, b.a().c());
            String b6 = b(context4, hashMap);
            if (!TextUtils.isEmpty(b6)) {
                sb.append(h.f735b);
                sb.append(b6);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    private static String f() {
        String b2;
        Context context = b.a().f714a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(e, (String) null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().f719a)) {
                b2 = b();
            } else {
                b2 = com.alipay.sdk.util.a.a(context).b();
            }
            string = b2;
            sharedPreferences.edit().putString(e, string).commit();
        }
        return string;
    }

    private static String g() {
        String str;
        Context context = b.a().f714a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(f, (String) null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().f719a)) {
            String c2 = b.a().c();
            if (TextUtils.isEmpty(c2)) {
                str = b();
            } else {
                str = c2.substring(3, 18);
            }
        } else {
            str = com.alipay.sdk.util.a.a(context).a();
        }
        String str2 = str;
        sharedPreferences.edit().putString(f, str2).commit();
        return str2;
    }

    public static String b() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(9000) + 1000);
    }

    private static String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : "-1";
    }

    private static String d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "00";
    }

    /* access modifiers changed from: package-private */
    public static String a(Context context, HashMap<String, String> hashMap) {
        String str;
        try {
            str = SecurityClientMobile.GetApdid(context, hashMap);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.g, th);
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.h, "apdid == null");
        }
        return str;
    }

    public final String b(Context context, HashMap<String, String> hashMap) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new d(this, context, hashMap)).get(3000, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.i, th);
            return "";
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append("(");
            sb.append(packageName);
            sb.append(h.f735b);
            sb.append(packageInfo.versionCode);
            sb.append(")");
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
