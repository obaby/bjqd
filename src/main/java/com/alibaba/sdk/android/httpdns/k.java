package com.alibaba.sdk.android.httpdns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alibaba.sdk.android.httpdns.a.b;
import java.util.ArrayList;

public class k {

    /* renamed from: a  reason: collision with root package name */
    private static Context f476a;

    /* renamed from: b  reason: collision with root package name */
    static boolean f477b;
    /* access modifiers changed from: private */
    public static String f;

    /* access modifiers changed from: private */
    public static String c() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) f476a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return "None_Network";
        }
        String typeName = activeNetworkInfo.getTypeName();
        g.e("[detectCurrentNetwork] - Network name:" + typeName + " subType name: " + activeNetworkInfo.getSubtypeName());
        return typeName == null ? "None_Network" : typeName;
    }

    public static void setContext(Context context) {
        if (context != null) {
            f476a = context;
            AnonymousClass1 r2 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    try {
                        if (!isInitialStickyBroadcast() && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                            b.b(context);
                            String d = k.c();
                            if (d != "None_Network" && !d.equalsIgnoreCase(k.f)) {
                                g.e("[BroadcastReceiver.onReceive] - Network state changed");
                                ArrayList a2 = b.a().a();
                                b.a().clear();
                                b.a().a();
                                if (k.f477b && HttpDns.instance != null) {
                                    g.e("[BroadcastReceiver.onReceive] - refresh host");
                                    HttpDns.instance.setPreResolveHosts(a2);
                                }
                            }
                            String unused = k.f = d;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            f476a.registerReceiver(r2, intentFilter);
            return;
        }
        throw new IllegalStateException("Context can't be null");
    }
}
