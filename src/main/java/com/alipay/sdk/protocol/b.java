package com.alipay.sdk.protocol;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.tid.a;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    public a f707a;

    /* renamed from: b  reason: collision with root package name */
    public String[] f708b;

    /* renamed from: c  reason: collision with root package name */
    private String f709c;

    private b(String str) {
        this.f709c = str;
    }

    private b(String str, a aVar) {
        this.f709c = str;
        this.f707a = aVar;
    }

    public static List<b> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String optString = jSONObject.optString(c.e, "");
        String[] strArr = null;
        if (!TextUtils.isEmpty(optString)) {
            strArr = optString.split(h.f735b);
        }
        for (int i = 0; i < strArr.length; i++) {
            a a2 = a.a(strArr[i]);
            if (a2 != a.None) {
                b bVar = new b(strArr[i], a2);
                bVar.f708b = a(strArr[i]);
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private static String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        String[] split = str.substring(indexOf + 1, lastIndexOf).split(",");
        if (split != null) {
            for (int i = 0; i < split.length; i++) {
                if (!TextUtils.isEmpty(split[i])) {
                    arrayList.add(split[i].trim().replaceAll("'", "").replaceAll("\"", ""));
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static String[] b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(h.f735b);
        }
        return null;
    }

    private String a() {
        return this.f709c;
    }

    private a b() {
        return this.f707a;
    }

    private String[] c() {
        return this.f708b;
    }

    private static void a(b bVar) {
        String[] strArr = bVar.f708b;
        if (strArr.length == 3 && TextUtils.equals(com.alipay.sdk.cons.b.f669c, strArr[0])) {
            Context context = com.alipay.sdk.sys.b.a().f714a;
            com.alipay.sdk.tid.b a2 = com.alipay.sdk.tid.b.a();
            if (!TextUtils.isEmpty(strArr[1]) && !TextUtils.isEmpty(strArr[2])) {
                a2.f719a = strArr[1];
                a2.f720b = strArr[2];
                a aVar = new a(context);
                try {
                    aVar.a(com.alipay.sdk.util.a.a(context).a(), com.alipay.sdk.util.a.a(context).b(), a2.f719a, a2.f720b);
                } catch (Exception unused) {
                } finally {
                    aVar.close();
                }
            }
        }
    }
}
