package anet.channel.strategy.dispatch;

import android.content.Context;
import anet.channel.util.ALog;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static volatile double f285a;

    /* renamed from: b  reason: collision with root package name */
    public static volatile double f286b;

    /* renamed from: c  reason: collision with root package name */
    public static volatile String f287c;
    public static volatile String d;
    public static volatile String e;
    private static volatile Context f;
    private static volatile int g;
    private static volatile long h;
    private static IAmdcSign i;
    private static Map<String, String> j;

    public static void a(int i2, int i3) {
        ALog.i("awcn.AmdcRuntimeInfo", "set amdc limit", (String) null, "level", Integer.valueOf(i2), "time", Integer.valueOf(i3));
        if (i2 >= 0 && i2 <= 3) {
            g = i2;
            h = System.currentTimeMillis() + (((long) i3) * 1000);
        }
    }

    public static int a() {
        if (g > 0 && System.currentTimeMillis() - h > 0) {
            h = 0;
            g = 0;
        }
        return g;
    }

    public static void a(Context context) {
        f = context;
    }

    public static void a(IAmdcSign iAmdcSign) {
        i = iAmdcSign;
    }

    public static IAmdcSign b() {
        return i;
    }

    public static void a(String str, String str2, String str3) {
        d = str;
        e = str2;
        f287c = str3;
    }

    public static synchronized Map<String, String> c() {
        synchronized (a.class) {
            if (j == null) {
                Map<String, String> map = Collections.EMPTY_MAP;
                return map;
            }
            HashMap hashMap = new HashMap(j);
            return hashMap;
        }
    }
}
