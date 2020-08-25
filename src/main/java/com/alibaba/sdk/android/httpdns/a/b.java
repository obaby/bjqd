package com.alibaba.sdk.android.httpdns.a;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static f f457a;

    /* renamed from: a  reason: collision with other field name */
    private static h f1a;
    private static boolean f;

    public static List<e> a() {
        ArrayList arrayList = new ArrayList();
        if (!f) {
            return arrayList;
        }
        arrayList.addAll(f457a.a());
        return arrayList;
    }

    public static void a(Context context) {
        a(context, (h) null);
    }

    public static void a(Context context, h hVar) {
        f457a = new a(context);
        f1a = hVar;
        if (f1a == null) {
            f1a = new h();
        }
    }

    public static void a(e eVar) {
        if (eVar != null) {
            f457a.a(eVar);
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static boolean m0a() {
        return f;
    }

    public static void b(Context context) {
        if (context != null) {
            f1a.c(context);
        }
    }

    public static void b(e eVar) {
        if (eVar != null) {
            f457a.b(eVar);
        }
    }

    public static void c(boolean z) {
        f = z;
    }

    public static String g() {
        return f1a.g();
    }
}
