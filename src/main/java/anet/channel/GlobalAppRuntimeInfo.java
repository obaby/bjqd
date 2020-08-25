package anet.channel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.strategy.dispatch.a;
import anet.channel.util.ALog;
import anet.channel.util.Utils;

/* compiled from: Taobao */
public class GlobalAppRuntimeInfo {

    /* renamed from: a  reason: collision with root package name */
    private static Context f118a;

    /* renamed from: b  reason: collision with root package name */
    private static ENV f119b = ENV.ONLINE;

    /* renamed from: c  reason: collision with root package name */
    private static String f120c = "";
    private static String d = "";
    private static String e;
    private static String f;
    private static String g;
    private static volatile boolean h = true;
    private static SharedPreferences i = null;

    public static void setContext(Context context) {
        f118a = context;
        if (context != null) {
            if (TextUtils.isEmpty(d)) {
                d = Utils.getProcessName(context, Process.myPid());
            }
            if (TextUtils.isEmpty(f120c)) {
                f120c = Utils.getMainProcessName(context);
            }
            if (i == null) {
                i = PreferenceManager.getDefaultSharedPreferences(context);
                f = i.getString("UserId", (String) null);
            }
            ALog.e("awcn.GlobalAppRuntimeInfo", "", (String) null, "CurrentProcess", d, "TargetProcess", f120c);
        }
    }

    public static Context getContext() {
        return f118a;
    }

    public static void setTargetProcess(String str) {
        f120c = str;
    }

    public static boolean isTargetProcess() {
        if (TextUtils.isEmpty(f120c) || TextUtils.isEmpty(d)) {
            return true;
        }
        return f120c.equalsIgnoreCase(d);
    }

    public static String getCurrentProcess() {
        return d;
    }

    public static void setCurrentProcess(String str) {
        d = str;
    }

    public static void setEnv(ENV env) {
        f119b = env;
    }

    public static ENV getEnv() {
        return f119b;
    }

    public static void setTtid(String str) {
        e = str;
        try {
            if (!TextUtils.isEmpty(str)) {
                int indexOf = str.indexOf("@");
                String str2 = null;
                String substring = indexOf != -1 ? str.substring(0, indexOf) : null;
                String substring2 = str.substring(indexOf + 1);
                int lastIndexOf = substring2.lastIndexOf("_");
                if (lastIndexOf != -1) {
                    String substring3 = substring2.substring(0, lastIndexOf);
                    str2 = substring2.substring(lastIndexOf + 1);
                    substring2 = substring3;
                }
                a.a(substring2, str2, substring);
            }
        } catch (Exception unused) {
        }
    }

    public static String getTtid() {
        return e;
    }

    public static void setUserId(String str) {
        if (f == null || !f.equals(str)) {
            f = str;
            StrategyCenter.getInstance().forceRefreshStrategy(DispatchConstants.getAmdcServerDomain());
            if (i != null) {
                i.edit().putString("UserId", str).apply();
            }
        }
    }

    public static String getUserId() {
        return f;
    }

    public static String getUtdid() {
        if (g == null && f118a != null) {
            g = Utils.getDeviceId(f118a);
        }
        return g;
    }

    public static void setBackground(boolean z) {
        h = z;
    }

    public static boolean isAppBackground() {
        if (f118a == null) {
            return true;
        }
        return h;
    }
}
