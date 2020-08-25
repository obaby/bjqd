package anet.channel.status;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Pair;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.c;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: Taobao */
public class NetworkStatusHelper {
    private static final String TAG = "awcn.NetworkStatusHelper";
    static CopyOnWriteArraySet<INetworkStatusChangeListener> listeners = new CopyOnWriteArraySet<>();

    /* compiled from: Taobao */
    public interface INetworkStatusChangeListener {
        void onNetworkStatusChanged(NetworkStatus networkStatus);
    }

    /* compiled from: Taobao */
    public enum NetworkStatus {
        NONE,
        NO,
        G2,
        G3,
        G4,
        WIFI;

        public boolean isMobile() {
            return this == G2 || this == G3 || this == G4;
        }

        public boolean isWifi() {
            return this == WIFI;
        }

        public String getType() {
            if (this == G2) {
                return "2G";
            }
            if (this == G3) {
                return "3G";
            }
            if (this == G4) {
                return "4G";
            }
            return toString();
        }
    }

    public static synchronized void startListener(Context context) {
        synchronized (NetworkStatusHelper.class) {
            b.f244a = context;
            b.a();
        }
    }

    public void stopListener(Context context) {
        b.b();
    }

    public static void addStatusChangeListener(INetworkStatusChangeListener iNetworkStatusChangeListener) {
        listeners.add(iNetworkStatusChangeListener);
    }

    public static void removeStatusChangeListener(INetworkStatusChangeListener iNetworkStatusChangeListener) {
        listeners.remove(iNetworkStatusChangeListener);
    }

    static void notifyStatusChanged(NetworkStatus networkStatus) {
        ThreadPoolExecutorFactory.submitScheduledTask(new a(networkStatus));
    }

    public static NetworkStatus getStatus() {
        return b.f245b;
    }

    public static String getNetworkSubType() {
        return b.f246c;
    }

    public static String getApn() {
        return b.d;
    }

    public static String getCarrier() {
        return b.g;
    }

    public static String getSimOp() {
        return b.h;
    }

    public static boolean isRoaming() {
        return b.j;
    }

    public static String getWifiBSSID() {
        return b.f;
    }

    public static String getWifiSSID() {
        return b.e;
    }

    public static boolean isConnected() {
        if (b.f245b != NetworkStatus.NO) {
            return true;
        }
        NetworkInfo c2 = b.c();
        if (c2 == null || !c2.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isProxy() {
        NetworkStatus networkStatus = b.f245b;
        String str = b.d;
        if (networkStatus == NetworkStatus.WIFI && getWifiProxy() != null) {
            return true;
        }
        if (networkStatus.isMobile()) {
            return str.contains("wap") || c.a() != null;
        }
        return false;
    }

    public static String getProxyType() {
        NetworkStatus networkStatus = b.f245b;
        if (networkStatus == NetworkStatus.WIFI && getWifiProxy() != null) {
            return "proxy";
        }
        if (!networkStatus.isMobile() || !b.d.contains("wap")) {
            return (!networkStatus.isMobile() || c.a() == null) ? "" : com.alipay.sdk.app.statistic.c.d;
        }
        return "wap";
    }

    public static Pair<String, Integer> getWifiProxy() {
        if (b.f245b != NetworkStatus.WIFI) {
            return null;
        }
        return b.i;
    }

    public static void printNetworkDetail() {
        try {
            NetworkStatus status = getStatus();
            StringBuilder sb = new StringBuilder(128);
            sb.append("\nNetwork detail*******************************\n");
            sb.append("Status: ");
            sb.append(status.getType());
            sb.append(10);
            sb.append("Subtype: ");
            sb.append(getNetworkSubType());
            sb.append(10);
            if (status != NetworkStatus.NO) {
                if (status.isMobile()) {
                    sb.append("Apn: ");
                    sb.append(getApn());
                    sb.append(10);
                    sb.append("Carrier: ");
                    sb.append(getCarrier());
                    sb.append(10);
                } else {
                    sb.append("BSSID: ");
                    sb.append(getWifiBSSID());
                    sb.append(10);
                    sb.append("SSID: ");
                    sb.append(getWifiSSID());
                    sb.append(10);
                }
            }
            if (isProxy()) {
                sb.append("Proxy: ");
                sb.append(getProxyType());
                sb.append(10);
                Pair<String, Integer> wifiProxy = getWifiProxy();
                if (wifiProxy != null) {
                    sb.append("ProxyHost: ");
                    sb.append((String) wifiProxy.first);
                    sb.append(10);
                    sb.append("ProxyPort: ");
                    sb.append(wifiProxy.second);
                    sb.append(10);
                }
            }
            sb.append("*********************************************");
            ALog.i(TAG, sb.toString(), (String) null, new Object[0]);
        } catch (Exception unused) {
        }
    }
}
