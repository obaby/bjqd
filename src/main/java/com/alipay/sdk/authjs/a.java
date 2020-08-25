package com.alipay.sdk.authjs;

import org.json.JSONException;
import org.json.JSONObject;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f651a = "CallInfo";

    /* renamed from: b  reason: collision with root package name */
    public static final String f652b = "call";

    /* renamed from: c  reason: collision with root package name */
    public static final String f653c = "callback";
    public static final String d = "bundleName";
    public static final String e = "clientId";
    public static final String f = "param";
    public static final String g = "func";
    public static final String h = "msgType";
    public String i;
    public String j;
    public String k;
    public String l;
    public JSONObject m;
    private boolean n = false;

    /* renamed from: com.alipay.sdk.authjs.a$a  reason: collision with other inner class name */
    public enum C0011a {
        ;

        static {
            f = new int[]{f654a, f655b, f656c, d, e};
        }

        public static int[] a() {
            return (int[]) f.clone();
        }
    }

    private static String a(int i2) {
        switch (b.f657a[i2 - 1]) {
            case 1:
                return "function not found";
            case 2:
                return "invalid parameter";
            case 3:
                return "runtime error";
            default:
                return "none";
        }
    }

    private boolean a() {
        return this.n;
    }

    private void a(boolean z) {
        this.n = z;
    }

    public a(String str) {
        this.l = str;
    }

    private String b() {
        return this.i;
    }

    private void a(String str) {
        this.i = str;
    }

    private String c() {
        return this.j;
    }

    private void b(String str) {
        this.j = str;
    }

    private String d() {
        return this.k;
    }

    private void c(String str) {
        this.k = str;
    }

    private String e() {
        return this.l;
    }

    private void d(String str) {
        this.l = str;
    }

    private JSONObject f() {
        return this.m;
    }

    private void a(JSONObject jSONObject) {
        this.m = jSONObject;
    }

    private String g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(e, this.i);
        jSONObject.put(g, this.k);
        jSONObject.put(f, this.m);
        jSONObject.put(h, this.l);
        return jSONObject.toString();
    }
}
