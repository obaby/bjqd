package anet.channel.strategy;

import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.util.ALog;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
public class k {
    public static d a(JSONObject jSONObject) {
        try {
            return new d(jSONObject);
        } catch (Exception e2) {
            ALog.e("StrategyResultParser", "Parse HttpDns response failed.", (String) null, e2, "JSON Content", jSONObject.toString());
            return null;
        }
    }

    /* compiled from: Taobao */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final String f318a;

        /* renamed from: b  reason: collision with root package name */
        public final a f319b;

        /* renamed from: c  reason: collision with root package name */
        public final String f320c;

        public e(JSONObject jSONObject) {
            this.f318a = jSONObject.optString("ip");
            this.f320c = jSONObject.optString("path");
            this.f319b = new a(jSONObject);
        }
    }

    /* compiled from: Taobao */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f307a;

        /* renamed from: b  reason: collision with root package name */
        public final String f308b;

        /* renamed from: c  reason: collision with root package name */
        public final int f309c;
        public final int d;
        public final int e;
        public final int f;
        public final String g;
        public final String h;

        public a(JSONObject jSONObject) {
            this.f307a = jSONObject.optInt("port");
            this.f308b = jSONObject.optString("protocol");
            this.f309c = jSONObject.optInt("cto");
            this.d = jSONObject.optInt("rto");
            this.e = jSONObject.optInt("retry");
            this.f = jSONObject.optInt("heartbeat");
            this.g = jSONObject.optString("rtt", "");
            this.h = jSONObject.optString("publickey");
        }
    }

    /* compiled from: Taobao */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f313a;

        /* renamed from: b  reason: collision with root package name */
        public final e[] f314b;

        public c(JSONObject jSONObject) {
            this.f313a = jSONObject.optString(com.alipay.sdk.cons.c.f);
            JSONArray optJSONArray = jSONObject.optJSONArray("strategies");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.f314b = new e[length];
                for (int i = 0; i < length; i++) {
                    this.f314b[i] = new e(optJSONArray.optJSONObject(i));
                }
                return;
            }
            this.f314b = null;
        }
    }

    /* compiled from: Taobao */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final String f310a;

        /* renamed from: b  reason: collision with root package name */
        public final int f311b;

        /* renamed from: c  reason: collision with root package name */
        public final String f312c;
        public final String d;
        public final String e;
        public final String[] f;
        public final String[] g;
        public final a[] h;
        public final e[] i;
        public final boolean j;
        public final boolean k;

        public b(JSONObject jSONObject) {
            this.f310a = jSONObject.optString(com.alipay.sdk.cons.c.f);
            this.f311b = jSONObject.optInt("ttl");
            this.f312c = jSONObject.optString("safeAisles");
            this.d = jSONObject.optString("cname", (String) null);
            this.e = jSONObject.optString("unit", (String) null);
            this.j = jSONObject.optInt("clear") != 1 ? false : true;
            this.k = jSONObject.optBoolean("effectNow");
            JSONArray optJSONArray = jSONObject.optJSONArray("ips");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.f = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    this.f[i2] = optJSONArray.optString(i2);
                }
            } else {
                this.f = null;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("sips");
            if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                this.g = null;
            } else {
                int length2 = optJSONArray2.length();
                this.g = new String[length2];
                for (int i3 = 0; i3 < length2; i3++) {
                    this.g[i3] = optJSONArray2.optString(i3);
                }
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("aisles");
            if (optJSONArray3 != null) {
                int length3 = optJSONArray3.length();
                this.h = new a[length3];
                for (int i4 = 0; i4 < length3; i4++) {
                    this.h[i4] = new a(optJSONArray3.optJSONObject(i4));
                }
            } else {
                this.h = null;
            }
            JSONArray optJSONArray4 = jSONObject.optJSONArray("strategies");
            if (optJSONArray4 == null || optJSONArray4.length() <= 0) {
                this.i = null;
                return;
            }
            int length4 = optJSONArray4.length();
            this.i = new e[length4];
            for (int i5 = 0; i5 < length4; i5++) {
                this.i[i5] = new e(optJSONArray4.optJSONObject(i5));
            }
        }
    }

    /* compiled from: Taobao */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final String f315a;

        /* renamed from: b  reason: collision with root package name */
        public final b[] f316b;

        /* renamed from: c  reason: collision with root package name */
        public final c[] f317c;
        public final String d;
        public final String e;
        public final int f;
        public final int g;
        public final int h;

        public d(JSONObject jSONObject) {
            this.f315a = jSONObject.optString("ip");
            this.d = jSONObject.optString("uid", (String) null);
            this.e = jSONObject.optString(com.alipay.sdk.cons.b.g, (String) null);
            this.f = jSONObject.optInt(DispatchConstants.CONFIG_VERSION);
            this.g = jSONObject.optInt("fcl");
            this.h = jSONObject.optInt("fct");
            JSONArray optJSONArray = jSONObject.optJSONArray("dns");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.f316b = new b[length];
                for (int i = 0; i < length; i++) {
                    this.f316b[i] = new b(optJSONArray.optJSONObject(i));
                }
            } else {
                this.f316b = null;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("hrTask");
            if (optJSONArray2 != null) {
                int length2 = optJSONArray2.length();
                this.f317c = new c[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    this.f317c[i2] = new c(optJSONArray2.optJSONObject(i2));
                }
                return;
            }
            this.f317c = null;
        }
    }
}
