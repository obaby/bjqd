package com.alipay.a.a;

import com.alipay.a.b.a;
import com.alipay.sdk.util.h;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.b;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    static List<i> f498a;

    static {
        ArrayList arrayList = new ArrayList();
        f498a = arrayList;
        arrayList.add(new l());
        f498a.add(new d());
        f498a.add(new c());
        f498a.add(new h());
        f498a.add(new k());
        f498a.add(new b());
        f498a.add(new a());
        f498a.add(new g());
    }

    public static final <T> T a(Object obj, Type type) {
        T a2;
        for (i next : f498a) {
            if (next.a(a.a(type)) && (a2 = next.a(obj, type)) != null) {
                return a2;
            }
        }
        return null;
    }

    public static final Object a(String str, Type type) {
        org.json.alipay.a bVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("[") && trim.endsWith("]")) {
            bVar = new org.json.alipay.a(trim);
        } else if (!trim.startsWith("{") || !trim.endsWith(h.d)) {
            return a((Object) trim, type);
        } else {
            bVar = new b(trim);
        }
        return a((Object) bVar, type);
    }
}
