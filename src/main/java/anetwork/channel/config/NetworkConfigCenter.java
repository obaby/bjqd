package anetwork.channel.config;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpUrl;
import anet.channel.util.b;
import anetwork.channel.cache.CacheManager;
import anetwork.channel.http.NetworkSdkSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Taobao */
public class NetworkConfigCenter {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f384a = true;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f385b = true;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f386c = true;
    private static volatile int d = 5;
    private static volatile boolean e = true;
    private static volatile boolean f = true;
    private static volatile boolean g;
    private static volatile long h;
    private static volatile boolean i;
    private static volatile ConcurrentHashMap<String, List<String>> j;
    private static final List<String> k = new ArrayList();
    private static volatile int l = 10000;
    private static volatile IRemoteConfig m;

    public static void init() {
        h = PreferenceManager.getDefaultSharedPreferences(NetworkSdkSetting.getContext()).getLong("Cache.Flag", 0);
    }

    public static void setSSLEnabled(boolean z) {
        f384a = z;
    }

    public static boolean isSSLEnabled() {
        return f384a;
    }

    public static void setSpdyEnabled(boolean z) {
        f385b = z;
    }

    public static boolean isSpdyEnabled() {
        return f385b;
    }

    public static void setHttpsValidationEnabled(boolean z) {
        if (!z) {
            b.a(b.ALLOW_ALL_HOSTNAME_VERIFIER);
            b.a(b.TRUST_ALL_SSL_SOCKET_FACTORY);
            return;
        }
        b.a((HostnameVerifier) null);
        b.a((SSLSocketFactory) null);
    }

    public static void setServiceBindWaitTime(int i2) {
        d = i2;
    }

    public static int getServiceBindWaitTime() {
        return d;
    }

    public static void setRemoteNetworkServiceEnable(boolean z) {
        f386c = z;
    }

    public static boolean isRemoteNetworkServiceEnable() {
        return f386c;
    }

    public static void setRemoteConfig(IRemoteConfig iRemoteConfig) {
        if (m != null) {
            m.unRegister();
        }
        if (iRemoteConfig != null) {
            iRemoteConfig.register();
        }
        m = iRemoteConfig;
    }

    public static boolean isHttpSessionEnable() {
        return e;
    }

    public static void setHttpSessionEnable(boolean z) {
        e = z;
    }

    public static boolean isAllowHttpIpRetry() {
        return e && g;
    }

    public static void setAllowHttpIpRetry(boolean z) {
        g = z;
    }

    public static boolean isHttpCacheEnable() {
        return f;
    }

    public static void setHttpCacheEnable(boolean z) {
        f = z;
    }

    public static void setCacheFlag(long j2) {
        if (j2 != h) {
            ALog.i("anet.NetworkConfigCenter", "set cache flag", (String) null, "old", Long.valueOf(h), "new", Long.valueOf(j2));
            h = j2;
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(NetworkSdkSetting.getContext()).edit();
            edit.putLong("Cache.Flag", h);
            edit.apply();
            CacheManager.clearAllCache();
        }
    }

    public static void updateWhiteListMap(String str) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkConfigCenter", "updateWhiteUrlList", (String) null, "White List", str);
        }
        if (TextUtils.isEmpty(str)) {
            j = null;
            return;
        }
        ConcurrentHashMap<String, List<String>> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                try {
                    if ("*".equals(obj)) {
                        concurrentHashMap.put(next, k);
                    } else if (obj instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) obj;
                        int length = jSONArray.length();
                        ArrayList arrayList = new ArrayList(length);
                        for (int i2 = 0; i2 < length; i2++) {
                            Object obj2 = jSONArray.get(i2);
                            if (obj2 instanceof String) {
                                arrayList.add((String) obj2);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            concurrentHashMap.put(next, arrayList);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        } catch (JSONException e2) {
            ALog.e("anet.NetworkConfigCenter", "parse jsonObject failed", (String) null, e2, new Object[0]);
        }
        j = concurrentHashMap;
    }

    public static boolean isUrlInWhiteList(HttpUrl httpUrl) {
        ConcurrentHashMap<String, List<String>> concurrentHashMap;
        List<String> list;
        if (httpUrl == null || (concurrentHashMap = j) == null || (list = concurrentHashMap.get(httpUrl.host())) == null) {
            return false;
        }
        if (list == k) {
            return true;
        }
        for (String startsWith : list) {
            if (httpUrl.path().startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static int getRequestStatisticSampleRate() {
        return l;
    }

    public static void setRequestStatisticSampleRate(int i2) {
        l = i2;
    }

    public static boolean isBgRequestForbidden() {
        return i;
    }

    public static void setBgRequestForbidden(boolean z) {
        i = z;
    }

    public static void setAmdcPresetHosts(String str) {
        if (GlobalAppRuntimeInfo.isTargetProcess()) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                ArrayList arrayList = new ArrayList(length);
                for (int i2 = 0; i2 < length; i2++) {
                    String string = jSONArray.getString(i2);
                    if (c.b(string)) {
                        arrayList.add(string);
                    }
                }
                HttpDispatcher.getInstance().addHosts(arrayList);
            } catch (JSONException e2) {
                ALog.e("anet.NetworkConfigCenter", "parse hosts failed", (String) null, e2, new Object[0]);
            }
        }
    }
}
