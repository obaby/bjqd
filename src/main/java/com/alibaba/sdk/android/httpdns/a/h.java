package com.alibaba.sdk.android.httpdns.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class h {

    /* renamed from: b  reason: collision with root package name */
    private final Handler f461b;
    private String o = "UNKNOWN";

    private class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                h.this.d((Context) message.obj);
            }
        }
    }

    h() {
        HandlerThread handlerThread = new HandlerThread("SpStatus daemon");
        handlerThread.start();
        this.f461b = new a(handlerThread.getLooper());
    }

    private static int a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return 0;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.isConnected()) {
                    if (activeNetworkInfo.getType() == 1) {
                        return 1;
                    }
                    if (activeNetworkInfo.getType() != 0) {
                        return 0;
                    }
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return 2;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 15:
                            return 3;
                        case 13:
                            return 4;
                        default:
                            return 0;
                    }
                }
            }
            return 255;
        } catch (Exception unused) {
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    private static String m3a(Context context) {
        try {
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            if (!TextUtils.isEmpty(simOperator)) {
                return simOperator;
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(0);
    }

    private static String b(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getSSID();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void d(Context context) {
        String str;
        int a2 = a(context);
        if (a2 != 255) {
            switch (a2) {
                case 1:
                    str = b(context);
                    break;
                case 2:
                case 3:
                case 4:
                    str = a(context);
                    break;
            }
            this.o = str;
        }
        if (TextUtils.isEmpty(this.o)) {
            this.o = "UNKNOWN";
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Context context) {
        if (context != null) {
            Message obtain = Message.obtain();
            obtain.obj = context;
            obtain.what = 0;
            this.f461b.sendMessage(obtain);
        }
    }

    /* access modifiers changed from: package-private */
    public String g() {
        return this.o;
    }
}
