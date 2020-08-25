package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.util.l;
import com.stub.StubApp;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f710a = "\"&";

    /* renamed from: b  reason: collision with root package name */
    public static final String f711b = "&";

    /* renamed from: c  reason: collision with root package name */
    public static final String f712c = "bizcontext=\"";
    public static final String d = "bizcontext=";
    public static final String e = "\"";
    public static final String f = "appkey";
    public static final String g = "ty";
    public static final String h = "sv";
    public static final String i = "an";
    public static final String j = "setting";
    public static final String k = "av";
    public static final String l = "sdk_start_time";
    public static final String m = "UTF-8";
    private String n = "";
    private String o = "";
    private Context p = null;

    public a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.n = packageInfo.versionName;
            this.o = packageInfo.packageName;
            this.p = StubApp.getOrigApplicationContext(context.getApplicationContext());
        } catch (Exception unused) {
        }
    }

    public final String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (!str.contains(f710a)) {
            return c(str);
        }
        return d(str);
    }

    private static boolean b(String str) {
        return !str.contains(f710a);
    }

    private String c(String str) {
        try {
            String a2 = a(str, "&", d);
            if (TextUtils.isEmpty(a2)) {
                return str + "&" + b(d, "");
            }
            int indexOf = str.indexOf(a2);
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + a2.length());
            return substring + b(a2, d, "") + substring2;
        } catch (Throwable unused) {
            return str;
        }
    }

    private String d(String str) {
        try {
            String a2 = a(str, f710a, f712c);
            if (TextUtils.isEmpty(a2)) {
                return str + "&" + b(f712c, "\"");
            }
            if (!a2.endsWith("\"")) {
                a2 = a2 + "\"";
            }
            int indexOf = str.indexOf(a2);
            return str.substring(0, indexOf) + b(a2, f712c, "\"") + str.substring(indexOf + a2.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    private static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i2 = 0; i2 < split.length; i2++) {
            if (!TextUtils.isEmpty(split[i2]) && split[i2].startsWith(str3)) {
                return split[i2];
            }
        }
        return null;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        String a2 = a("", "");
        return str + a2 + str2;
    }

    public final String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", com.alipay.sdk.cons.a.d);
            jSONObject.put(g, "and_lite");
            jSONObject.put(h, com.alipay.sdk.cons.a.g);
            if (!this.o.contains(j) || !l.e(this.p)) {
                jSONObject.put(i, this.o);
            }
            jSONObject.put(k, this.n);
            jSONObject.put(l, System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    private String b(String str, String str2, String str3) throws JSONException, UnsupportedEncodingException {
        String substring = str.substring(str2.length());
        JSONObject jSONObject = new JSONObject(substring.substring(0, substring.length() - str3.length()));
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", com.alipay.sdk.cons.a.d);
        }
        if (!jSONObject.has(g)) {
            jSONObject.put(g, "and_lite");
        }
        if (!jSONObject.has(h)) {
            jSONObject.put(h, com.alipay.sdk.cons.a.g);
        }
        if (!jSONObject.has(i) && (!this.o.contains(j) || !l.e(this.p))) {
            jSONObject.put(i, this.o);
        }
        if (!jSONObject.has(k)) {
            jSONObject.put(k, this.n);
        }
        if (!jSONObject.has(l)) {
            jSONObject.put(l, System.currentTimeMillis());
        }
        String jSONObject2 = jSONObject.toString();
        return str2 + jSONObject2 + str3;
    }
}
