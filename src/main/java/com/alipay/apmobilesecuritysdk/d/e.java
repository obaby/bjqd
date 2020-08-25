package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.security.mobile.module.a.a.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, String> f562a;

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f563b = {"AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    private static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            if (str2 == null) {
                str2 = "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(i == 0 ? "" : "&");
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            stringBuffer.append(sb.toString());
            i++;
        }
        return stringBuffer.toString();
    }

    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        Map<String, String> map2;
        synchronized (e.class) {
            if (f562a == null) {
                c(context, map);
            }
            f562a.putAll(d.a());
            map2 = f562a;
        }
        return map2;
    }

    public static synchronized void a() {
        synchronized (e.class) {
            f562a = null;
        }
    }

    public static synchronized String b(Context context, Map<String, String> map) {
        String a2;
        synchronized (e.class) {
            a(context, map);
            TreeMap treeMap = new TreeMap();
            for (String str : f563b) {
                if (f562a.containsKey(str)) {
                    treeMap.put(str, f562a.get(str));
                }
            }
            a2 = b.a(a(treeMap));
        }
        return a2;
    }

    private static synchronized void c(Context context, Map<String, String> map) {
        synchronized (e.class) {
            TreeMap treeMap = new TreeMap();
            f562a = treeMap;
            treeMap.putAll(b.a(context, map));
            f562a.putAll(d.a(context));
            f562a.putAll(c.a(context));
            f562a.putAll(a.a(context, map));
        }
    }
}
