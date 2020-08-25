package anet.channel.strategy.dispatch;

import android.os.Build;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import java.util.Map;
import java.util.Set;

/* compiled from: Taobao */
class e {
    public static final String TAG = "amdc.DispatchParamBuilder";

    e() {
    }

    public static Map a(Map<String, Object> map) {
        IAmdcSign b2 = a.b();
        if (b2 == null || TextUtils.isEmpty(b2.getAppkey())) {
            ALog.e(TAG, "amdc sign is null or appkey is empty", (String) null, new Object[0]);
            return null;
        }
        NetworkStatusHelper.NetworkStatus status = NetworkStatusHelper.getStatus();
        if (status == NetworkStatusHelper.NetworkStatus.NO) {
            ALog.e(TAG, "network is no", (String) null, new Object[0]);
            return null;
        }
        map.put("appkey", b2.getAppkey());
        map.put(DispatchConstants.VERSION, DispatchConstants.VER_CODE);
        map.put(DispatchConstants.PLATFORM, DispatchConstants.ANDROID);
        map.put(DispatchConstants.PLATFORM_VERSION, Build.VERSION.RELEASE);
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUserId())) {
            map.put(DispatchConstants.SID, GlobalAppRuntimeInfo.getUserId());
        }
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUtdid())) {
            map.put(DispatchConstants.DEVICEID, GlobalAppRuntimeInfo.getUtdid());
        }
        map.put(DispatchConstants.NET_TYPE, status.toString());
        if (status.isWifi()) {
            map.put(DispatchConstants.BSSID, NetworkStatusHelper.getWifiBSSID());
        }
        map.put(DispatchConstants.CARRIER, NetworkStatusHelper.getCarrier());
        map.put(DispatchConstants.MNC, NetworkStatusHelper.getSimOp());
        map.put(DispatchConstants.LATITUDE, String.valueOf(a.f285a));
        map.put(DispatchConstants.LONGTITUDE, String.valueOf(a.f286b));
        map.putAll(a.c());
        map.put(DispatchConstants.CHANNEL, a.f287c);
        map.put(DispatchConstants.APP_NAME, a.d);
        map.put(DispatchConstants.APP_VERSION, a.e);
        map.put(DispatchConstants.DOMAIN, b(map));
        map.put(DispatchConstants.SIGNTYPE, b2.useSecurityGuard() ? "sec" : "noSec");
        map.put(DispatchConstants.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        String a2 = a(b2, map);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        map.put(DispatchConstants.SIGN, a2);
        return map;
    }

    private static String b(Map map) {
        StringBuilder sb = new StringBuilder();
        for (String append : (Set) map.remove(DispatchConstants.HOSTS)) {
            sb.append(append);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    static String a(IAmdcSign iAmdcSign, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(c.c(map.get("appkey")));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.DOMAIN)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.APP_NAME)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.APP_VERSION)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.BSSID)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.CHANNEL)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.DEVICEID)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.LATITUDE)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.LONGTITUDE)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.MACHINE)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.NET_TYPE)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.OTHER)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.PLATFORM)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.PLATFORM_VERSION)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.PRE_IP)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.SID)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.TIMESTAMP)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.VERSION)));
        sb.append("&");
        sb.append(c.c(map.get(DispatchConstants.SIGNTYPE)));
        try {
            return iAmdcSign.sign(sb.toString());
        } catch (Exception e) {
            ALog.e(TAG, "get sign failed", (String) null, e, new Object[0]);
            return null;
        }
    }
}
