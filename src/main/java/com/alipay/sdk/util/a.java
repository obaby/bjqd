package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.stub.StubApp;

public final class a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f721b = "00:00:00:00:00:00";
    private static a e;

    /* renamed from: a  reason: collision with root package name */
    public String f722a;

    /* renamed from: c  reason: collision with root package name */
    private String f723c;
    private String d;

    public static a a(Context context) {
        if (e == null) {
            e = new a(context);
        }
        return e;
    }

    private a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("phone");
            b(telephonyManager.getDeviceId());
            String subscriberId = telephonyManager.getSubscriberId();
            if (subscriberId != null) {
                subscriberId = (subscriberId + "000000000000000").substring(0, 15);
            }
            this.f723c = subscriberId;
            this.f722a = ((WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (!TextUtils.isEmpty(this.f722a)) {
                return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!TextUtils.isEmpty(this.f722a)) {
                return;
            }
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.f722a)) {
                this.f722a = f721b;
            }
            throw th;
        }
        this.f722a = f721b;
    }

    public final String a() {
        if (TextUtils.isEmpty(this.f723c)) {
            this.f723c = "000000000000000";
        }
        return this.f723c;
    }

    public final String b() {
        if (TextUtils.isEmpty(this.d)) {
            this.d = "000000000000000";
        }
        return this.d;
    }

    private void a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.f723c = str;
    }

    private void b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 48 || bytes[i] > 57) {
                    bytes[i] = 48;
                }
            }
            String str2 = new String(bytes);
            str = (str2 + "000000000000000").substring(0, 15);
        }
        this.d = str;
    }

    private String c() {
        String str = b() + "|";
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            return str + "000000000000000";
        }
        return str + a2;
    }

    private String d() {
        return this.f722a;
    }

    public static d b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return d.a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return d.NONE;
            }
            return d.WIFI;
        } catch (Exception unused) {
            return d.NONE;
        }
    }

    public static String c(Context context) {
        String str;
        a a2 = a(context);
        String str2 = a2.b() + "|";
        String a3 = a2.a();
        if (TextUtils.isEmpty(a3)) {
            str = str2 + "000000000000000";
        } else {
            str = str2 + a3;
        }
        return str.substring(0, 8);
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
