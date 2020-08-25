package com.alipay.a.a;

import com.alipay.a.b.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.alipay.b;

public final class f {

    /* renamed from: a  reason: collision with root package name */
    private static List<j> f499a;

    static {
        ArrayList arrayList = new ArrayList();
        f499a = arrayList;
        arrayList.add(new l());
        f499a.add(new d());
        f499a.add(new c());
        f499a.add(new h());
        f499a.add(new b());
        f499a.add(new a());
        f499a.add(new g());
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object b2 = b(obj);
        if (a.a(b2.getClass())) {
            return b.c(b2.toString());
        }
        if (Collection.class.isAssignableFrom(b2.getClass())) {
            return new org.json.alipay.a((List) b2).toString();
        }
        if (Map.class.isAssignableFrom(b2.getClass())) {
            return new b((Map) b2).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + b2.getClass());
    }

    public static Object b(Object obj) {
        Object a2;
        if (obj == null) {
            return null;
        }
        for (j next : f499a) {
            if (next.a(obj.getClass()) && (a2 = next.a(obj)) != null) {
                return a2;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
