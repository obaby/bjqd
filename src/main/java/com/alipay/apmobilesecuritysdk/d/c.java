package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.e;
import com.alipay.apmobilesecuritysdk.e.f;
import com.alipay.security.mobile.module.a.a;
import com.alipay.security.mobile.module.b.b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class c {
    public static Map<String, String> a(Context context) {
        b a2 = b.a();
        HashMap hashMap = new HashMap();
        f a3 = e.a(context);
        String a4 = b.a(context);
        String b2 = b.b(context);
        String k = b.k(context);
        String n = b.n(context);
        String m = b.m(context);
        if (a3 != null) {
            if (a.a(a4)) {
                a4 = a3.a();
            }
            if (a.a(b2)) {
                b2 = a3.b();
            }
            if (a.a(k)) {
                k = a3.c();
            }
            if (a.a(n)) {
                n = a3.d();
            }
            if (a.a(m)) {
                m = a3.e();
            }
        }
        f fVar = new f(a4, b2, k, n, m);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", fVar.a());
                jSONObject.put("imsi", fVar.b());
                jSONObject.put("mac", fVar.c());
                jSONObject.put("bluetoothmac", fVar.d());
                jSONObject.put("gsi", fVar.e());
                String jSONObject2 = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key", jSONObject2);
                com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject2);
            } catch (Exception e) {
                com.alipay.apmobilesecuritysdk.c.a.a((Throwable) e);
            }
        }
        hashMap.put("AD1", a4);
        hashMap.put("AD2", b2);
        hashMap.put("AD3", b.f(context));
        hashMap.put("AD5", b.h(context));
        hashMap.put("AD6", b.i(context));
        hashMap.put("AD7", b.j(context));
        hashMap.put("AD8", k);
        hashMap.put("AD9", b.l(context));
        hashMap.put("AD10", m);
        hashMap.put("AD11", b.e());
        hashMap.put("AD12", a2.f());
        hashMap.put("AD13", b.g());
        hashMap.put("AD14", b.i());
        hashMap.put("AD15", b.j());
        hashMap.put("AD16", b.k());
        hashMap.put("AD17", "");
        hashMap.put("AD18", n);
        hashMap.put("AD19", b.o(context));
        hashMap.put("AD20", b.l());
        hashMap.put("AD21", b.d());
        hashMap.put("AD22", "");
        hashMap.put("AD23", b.m());
        hashMap.put("AD24", a.g(b.g(context)));
        hashMap.put("AD26", b.e(context));
        hashMap.put("AD27", b.r());
        hashMap.put("AD28", b.t());
        hashMap.put("AD29", b.v());
        hashMap.put("AD30", b.s());
        hashMap.put("AD31", b.u());
        hashMap.put("AD32", b.p());
        hashMap.put("AD33", b.q());
        hashMap.put("AD34", b.r(context));
        hashMap.put("AD35", b.s(context));
        hashMap.put("AD36", b.q(context));
        hashMap.put("AD37", b.o());
        hashMap.put("AD38", b.n());
        hashMap.put("AD39", b.c(context));
        hashMap.put("AD40", b.d(context));
        hashMap.put("AD41", b.b());
        hashMap.put("AD42", b.c());
        hashMap.put("AL3", b.p(context));
        return hashMap;
    }
}
