package anet.channel.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import java.lang.reflect.Method;
import java.util.Locale;

/* compiled from: Taobao */
class b {

    /* renamed from: a  reason: collision with root package name */
    static Context f244a;

    /* renamed from: b  reason: collision with root package name */
    static volatile NetworkStatusHelper.NetworkStatus f245b = NetworkStatusHelper.NetworkStatus.NONE;

    /* renamed from: c  reason: collision with root package name */
    static volatile String f246c = EnvironmentCompat.MEDIA_UNKNOWN;
    static volatile String d = "";
    static volatile String e = "";
    static volatile String f = "";
    static volatile String g = EnvironmentCompat.MEDIA_UNKNOWN;
    static volatile String h = "";
    static volatile Pair<String, Integer> i = null;
    static volatile boolean j = false;
    private static volatile boolean k = false;
    private static ConnectivityManager l = null;
    private static TelephonyManager m = null;
    private static WifiManager n = null;
    private static SubscriptionManager o = null;
    private static Method p;
    private static BroadcastReceiver q = new NetworkStatusMonitor$1();

    b() {
    }

    static void a() {
        if (!k && f244a != null) {
            synchronized (f244a) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                try {
                    f244a.registerReceiver(q, intentFilter);
                } catch (Exception unused) {
                    ALog.e("awcn.NetworkStatusMonitor", "registerReceiver failed", (String) null, new Object[0]);
                }
            }
            a(f244a);
        }
    }

    static void b() {
        if (f244a != null) {
            f244a.unregisterReceiver(q);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e0 A[Catch:{ Exception -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(android.content.Context r12) {
        /*
            java.lang.String r0 = "awcn.NetworkStatusMonitor"
            java.lang.String r1 = "[checkNetworkStatus]"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            anet.channel.util.ALog.d(r0, r1, r4, r3)
            anet.channel.status.NetworkStatusHelper$NetworkStatus r0 = f245b
            java.lang.String r1 = d
            java.lang.String r3 = e
            if (r12 == 0) goto L_0x00f9
            android.net.NetworkInfo r12 = c()     // Catch:{ Exception -> 0x00ef }
            r5 = 2
            r6 = 1
            if (r12 == 0) goto L_0x00b8
            boolean r7 = r12.isConnected()     // Catch:{ Exception -> 0x00ef }
            if (r7 != 0) goto L_0x0023
            goto L_0x00b8
        L_0x0023:
            java.lang.String r7 = "awcn.NetworkStatusMonitor"
            java.lang.String r8 = "checkNetworkStatus"
            r9 = 4
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Exception -> 0x00ef }
            java.lang.String r10 = "info.isConnected"
            r9[r2] = r10     // Catch:{ Exception -> 0x00ef }
            boolean r10 = r12.isConnected()     // Catch:{ Exception -> 0x00ef }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ Exception -> 0x00ef }
            r9[r6] = r10     // Catch:{ Exception -> 0x00ef }
            java.lang.String r10 = "info.isAvailable"
            r9[r5] = r10     // Catch:{ Exception -> 0x00ef }
            r10 = 3
            boolean r11 = r12.isAvailable()     // Catch:{ Exception -> 0x00ef }
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)     // Catch:{ Exception -> 0x00ef }
            r9[r10] = r11     // Catch:{ Exception -> 0x00ef }
            anet.channel.util.ALog.i(r7, r8, r4, r9)     // Catch:{ Exception -> 0x00ef }
            int r7 = r12.getType()     // Catch:{ Exception -> 0x00ef }
            if (r7 != 0) goto L_0x007e
            java.lang.String r6 = r12.getSubtypeName()     // Catch:{ Exception -> 0x00ef }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x00ef }
            if (r7 != 0) goto L_0x0063
            java.lang.String r7 = " "
            java.lang.String r8 = ""
            java.lang.String r6 = r6.replace(r7, r8)     // Catch:{ Exception -> 0x00ef }
            goto L_0x0065
        L_0x0063:
            java.lang.String r6 = ""
        L_0x0065:
            int r7 = r12.getSubtype()     // Catch:{ Exception -> 0x00ef }
            anet.channel.status.NetworkStatusHelper$NetworkStatus r7 = a((int) r7, (java.lang.String) r6)     // Catch:{ Exception -> 0x00ef }
            a((anet.channel.status.NetworkStatusHelper.NetworkStatus) r7, (java.lang.String) r6)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r6 = r12.getExtraInfo()     // Catch:{ Exception -> 0x00ef }
            java.lang.String r6 = a((java.lang.String) r6)     // Catch:{ Exception -> 0x00ef }
            d = r6     // Catch:{ Exception -> 0x00ef }
            d()     // Catch:{ Exception -> 0x00ef }
            goto L_0x00b1
        L_0x007e:
            int r7 = r12.getType()     // Catch:{ Exception -> 0x00ef }
            if (r7 != r6) goto L_0x00aa
            anet.channel.status.NetworkStatusHelper$NetworkStatus r6 = anet.channel.status.NetworkStatusHelper.NetworkStatus.WIFI     // Catch:{ Exception -> 0x00ef }
            java.lang.String r7 = "wifi"
            a((anet.channel.status.NetworkStatusHelper.NetworkStatus) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x00ef }
            android.net.wifi.WifiInfo r6 = e()     // Catch:{ Exception -> 0x00ef }
            if (r6 == 0) goto L_0x009d
            java.lang.String r7 = r6.getBSSID()     // Catch:{ Exception -> 0x00ef }
            f = r7     // Catch:{ Exception -> 0x00ef }
            java.lang.String r6 = r6.getSSID()     // Catch:{ Exception -> 0x00ef }
            e = r6     // Catch:{ Exception -> 0x00ef }
        L_0x009d:
            java.lang.String r6 = "wifi"
            g = r6     // Catch:{ Exception -> 0x00ef }
            h = r6     // Catch:{ Exception -> 0x00ef }
            android.util.Pair r6 = f()     // Catch:{ Exception -> 0x00ef }
            i = r6     // Catch:{ Exception -> 0x00ef }
            goto L_0x00b1
        L_0x00aa:
            anet.channel.status.NetworkStatusHelper$NetworkStatus r6 = anet.channel.status.NetworkStatusHelper.NetworkStatus.NONE     // Catch:{ Exception -> 0x00ef }
            java.lang.String r7 = "unknown"
            a((anet.channel.status.NetworkStatusHelper.NetworkStatus) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x00ef }
        L_0x00b1:
            boolean r12 = r12.isRoaming()     // Catch:{ Exception -> 0x00ef }
            j = r12     // Catch:{ Exception -> 0x00ef }
            goto L_0x00cc
        L_0x00b8:
            anet.channel.status.NetworkStatusHelper$NetworkStatus r12 = anet.channel.status.NetworkStatusHelper.NetworkStatus.NO     // Catch:{ Exception -> 0x00ef }
            java.lang.String r7 = "no network"
            a((anet.channel.status.NetworkStatusHelper.NetworkStatus) r12, (java.lang.String) r7)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r12 = "awcn.NetworkStatusMonitor"
            java.lang.String r7 = "checkNetworkStatus"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x00ef }
            java.lang.String r8 = "NO NETWORK"
            r6[r2] = r8     // Catch:{ Exception -> 0x00ef }
            anet.channel.util.ALog.i(r12, r7, r4, r6)     // Catch:{ Exception -> 0x00ef }
        L_0x00cc:
            anet.channel.status.NetworkStatusHelper$NetworkStatus r12 = f245b     // Catch:{ Exception -> 0x00ef }
            if (r12 != r0) goto L_0x00e0
            java.lang.String r12 = d     // Catch:{ Exception -> 0x00ef }
            boolean r12 = r12.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x00ef }
            if (r12 == 0) goto L_0x00e0
            java.lang.String r12 = e     // Catch:{ Exception -> 0x00ef }
            boolean r12 = r12.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x00ef }
            if (r12 != 0) goto L_0x00f9
        L_0x00e0:
            boolean r12 = anet.channel.util.ALog.isPrintLog(r5)     // Catch:{ Exception -> 0x00ef }
            if (r12 == 0) goto L_0x00e9
            anet.channel.status.NetworkStatusHelper.printNetworkDetail()     // Catch:{ Exception -> 0x00ef }
        L_0x00e9:
            anet.channel.status.NetworkStatusHelper$NetworkStatus r12 = f245b     // Catch:{ Exception -> 0x00ef }
            anet.channel.status.NetworkStatusHelper.notifyStatusChanged(r12)     // Catch:{ Exception -> 0x00ef }
            goto L_0x00f9
        L_0x00ef:
            r12 = move-exception
            java.lang.String r0 = "awcn.NetworkStatusMonitor"
            java.lang.String r1 = "checkNetworkStatus"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            anet.channel.util.ALog.e(r0, r1, r4, r12, r2)
        L_0x00f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.status.b.a(android.content.Context):void");
    }

    private static void a(NetworkStatusHelper.NetworkStatus networkStatus, String str) {
        f245b = networkStatus;
        f246c = str;
        d = "";
        e = "";
        f = "";
        i = null;
        g = "";
        h = "";
    }

    private static NetworkStatusHelper.NetworkStatus a(int i2, String str) {
        switch (i2) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkStatusHelper.NetworkStatus.G2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkStatusHelper.NetworkStatus.G3;
            case 13:
            case 18:
            case 19:
                return NetworkStatusHelper.NetworkStatus.G4;
            default:
                if (str.equalsIgnoreCase("TD-SCDMA") || str.equalsIgnoreCase("WCDMA") || str.equalsIgnoreCase("CDMA2000")) {
                    return NetworkStatusHelper.NetworkStatus.G3;
                }
                return NetworkStatusHelper.NetworkStatus.NONE;
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (lowerCase.contains("cmwap")) {
            return "cmwap";
        }
        if (lowerCase.contains("uniwap")) {
            return "uniwap";
        }
        if (lowerCase.contains("3gwap")) {
            return "3gwap";
        }
        if (lowerCase.contains("ctwap")) {
            return "ctwap";
        }
        if (lowerCase.contains("cmnet")) {
            return "cmnet";
        }
        if (lowerCase.contains("uninet")) {
            return "uninet";
        }
        if (lowerCase.contains("3gnet")) {
            return "3gnet";
        }
        return lowerCase.contains("ctnet") ? "ctnet" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    private static void d() {
        try {
            if (m == null) {
                m = (TelephonyManager) f244a.getSystemService("phone");
            }
            h = m.getSimOperator();
            if (Build.VERSION.SDK_INT >= 22) {
                if (o == null) {
                    o = SubscriptionManager.from(f244a);
                    p = o.getClass().getDeclaredMethod("getDefaultDataSubscriptionInfo", new Class[0]);
                }
                if (p != null) {
                    g = ((SubscriptionInfo) p.invoke(o, new Object[0])).getCarrierName().toString();
                }
            }
        } catch (Exception unused) {
        }
    }

    static NetworkInfo c() {
        try {
            if (l == null) {
                l = (ConnectivityManager) f244a.getSystemService("connectivity");
            }
            return l.getActiveNetworkInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getNetworkInfo", (String) null, th, new Object[0]);
            return null;
        }
    }

    private static WifiInfo e() {
        try {
            if (n == null) {
                n = (WifiManager) f244a.getSystemService("wifi");
            }
            return n.getConnectionInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getWifiInfo", (String) null, th, new Object[0]);
            return null;
        }
    }

    private static Pair<String, Integer> f() {
        try {
            String property = System.getProperty("http.proxyHost");
            if (!TextUtils.isEmpty(property)) {
                return Pair.create(property, Integer.valueOf(Integer.parseInt(System.getProperty("http.proxyPort"))));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
