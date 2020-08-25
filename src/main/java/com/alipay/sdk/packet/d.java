package com.alipay.sdk.packet;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alipay.sdk.data.c;
import com.alipay.sdk.net.a;
import com.alipay.sdk.tid.b;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.k;
import com.alipay.sdk.util.l;
import com.bumptech.glide.BuildConfig;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    public static final String f699a = "msp-gzip";

    /* renamed from: b  reason: collision with root package name */
    public static final String f700b = "Msp-Param";

    /* renamed from: c  reason: collision with root package name */
    public static final String f701c = "Operation-Type";
    public static final String d = "content-type";
    public static final String e = "Version";
    public static final String f = "AppId";
    public static final String g = "des-mode";
    public static final String h = "namespace";
    public static final String i = "api_name";
    public static final String j = "api_version";
    public static final String k = "data";
    public static final String l = "params";
    public static final String m = "public_key";
    public static final String n = "device";
    public static final String o = "action";
    public static final String p = "type";
    public static final String q = "method";
    private static a t;
    protected boolean r = true;
    protected boolean s = true;

    public abstract JSONObject a() throws JSONException;

    public String b() {
        return BuildConfig.VERSION_NAME;
    }

    public List<Header> a(boolean z, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicHeader(f699a, String.valueOf(z)));
        arrayList.add(new BasicHeader(f701c, "alipay.msp.cashier.dispatch.bytes"));
        arrayList.add(new BasicHeader(d, OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE));
        arrayList.add(new BasicHeader(e, "2.0"));
        arrayList.add(new BasicHeader(f, "TAOBAO"));
        arrayList.add(new BasicHeader(f700b, a.a(str)));
        arrayList.add(new BasicHeader(g, "CBC"));
        return arrayList;
    }

    public String c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(n, Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put(i, "com.alipay.mcpay");
        hashMap.put(j, b());
        return a((HashMap<String, String>) hashMap, (HashMap<String, String>) new HashMap());
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(p, str);
        jSONObject2.put(q, str2);
        jSONObject.put(o, jSONObject2);
        return jSONObject;
    }

    public String a(String str, JSONObject jSONObject) {
        JSONObject jSONObject2;
        String str2;
        b bVar;
        String str3;
        com.alipay.sdk.sys.b a2 = com.alipay.sdk.sys.b.a();
        b a3 = b.a();
        JSONObject a4 = com.alipay.sdk.util.b.a(new JSONObject(), jSONObject);
        try {
            a4.put(com.alipay.sdk.cons.b.f669c, a3.f719a);
            c a5 = c.a();
            Context context = com.alipay.sdk.sys.b.a().f714a;
            com.alipay.sdk.util.a a6 = com.alipay.sdk.util.a.a(context);
            if (TextUtils.isEmpty(a5.f678a)) {
                String b2 = l.b();
                String c2 = l.c();
                String g2 = l.g(context);
                String i2 = l.i(context);
                String h2 = l.h(context);
                String f2 = Float.toString(new TextView(context).getTextSize());
                a5.f678a = "Msp/15.5.5" + " (" + b2 + h.f735b + c2 + h.f735b + g2 + h.f735b + i2 + h.f735b + h2 + h.f735b + f2;
            }
            String str4 = com.alipay.sdk.util.a.b(context).p;
            String d2 = l.d();
            String a7 = a6.a();
            String b3 = a6.b();
            Context context2 = com.alipay.sdk.sys.b.a().f714a;
            SharedPreferences sharedPreferences = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            String string = sharedPreferences.getString("virtual_imsi", (String) null);
            if (TextUtils.isEmpty(string)) {
                if (TextUtils.isEmpty(b.a().f719a)) {
                    String c3 = com.alipay.sdk.sys.b.a().c();
                    if (TextUtils.isEmpty(c3)) {
                        str3 = c.b();
                    } else {
                        str3 = c3.substring(3, 18);
                    }
                } else {
                    str3 = com.alipay.sdk.util.a.a(context2).a();
                }
                string = str3;
                sharedPreferences.edit().putString("virtual_imsi", string).commit();
            }
            Context context3 = com.alipay.sdk.sys.b.a().f714a;
            SharedPreferences sharedPreferences2 = context3.getSharedPreferences("virtualImeiAndImsi", 0);
            com.alipay.sdk.sys.b bVar2 = a2;
            String string2 = sharedPreferences2.getString("virtual_imei", (String) null);
            if (TextUtils.isEmpty(string2)) {
                if (TextUtils.isEmpty(b.a().f719a)) {
                    string2 = c.b();
                } else {
                    string2 = com.alipay.sdk.util.a.a(context3).b();
                }
                sharedPreferences2.edit().putString("virtual_imei", string2).commit();
            }
            if (a3 != null) {
                a5.f680c = a3.f720b;
            }
            String replace = Build.MANUFACTURER.replace(h.f735b, " ");
            JSONObject jSONObject3 = a4;
            try {
                String replace2 = Build.MODEL.replace(h.f735b, " ");
                boolean b4 = com.alipay.sdk.sys.b.b();
                String str5 = a6.f722a;
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                String str6 = com.alipay.sdk.cons.b.f668b;
                WifiInfo connectionInfo = ((WifiManager) origApplicationContext.getSystemService("wifi")).getConnectionInfo();
                String ssid = connectionInfo != null ? connectionInfo.getSSID() : "-1";
                Context context4 = context;
                WifiInfo connectionInfo2 = ((WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")).getConnectionInfo();
                if (connectionInfo2 != null) {
                    str2 = connectionInfo2.getBSSID();
                } else {
                    str2 = "00";
                }
                StringBuilder sb = new StringBuilder();
                b bVar3 = a3;
                sb.append(a5.f678a);
                sb.append(h.f735b);
                sb.append(str4);
                sb.append(h.f735b);
                sb.append(d2);
                sb.append(h.f735b);
                sb.append("1");
                sb.append(h.f735b);
                sb.append(a7);
                sb.append(h.f735b);
                sb.append(b3);
                sb.append(h.f735b);
                sb.append(a5.f680c);
                sb.append(h.f735b);
                sb.append(replace);
                sb.append(h.f735b);
                sb.append(replace2);
                sb.append(h.f735b);
                sb.append(b4);
                sb.append(h.f735b);
                sb.append(str5);
                sb.append(";-1;-1;");
                sb.append(a5.f679b);
                sb.append(h.f735b);
                sb.append(string);
                sb.append(h.f735b);
                sb.append(string2);
                sb.append(h.f735b);
                sb.append(ssid);
                sb.append(h.f735b);
                sb.append(str2);
                if (bVar3 != null) {
                    HashMap hashMap = new HashMap();
                    bVar = bVar3;
                    hashMap.put(com.alipay.sdk.cons.b.f669c, bVar.f719a);
                    hashMap.put(com.alipay.sdk.cons.b.g, com.alipay.sdk.sys.b.a().c());
                    String b5 = a5.b(context4, hashMap);
                    if (!TextUtils.isEmpty(b5)) {
                        sb.append(h.f735b);
                        sb.append(b5);
                    }
                } else {
                    bVar = bVar3;
                }
                sb.append(")");
                jSONObject2 = jSONObject3;
                try {
                    jSONObject2.put(str6, sb.toString());
                    com.alipay.sdk.sys.b bVar4 = bVar2;
                    jSONObject2.put(com.alipay.sdk.cons.b.e, l.c(bVar4.f714a));
                    jSONObject2.put(com.alipay.sdk.cons.b.f, l.b(bVar4.f714a));
                    jSONObject2.put(com.alipay.sdk.cons.b.d, str);
                    jSONObject2.put(com.alipay.sdk.cons.b.h, com.alipay.sdk.cons.a.d);
                    jSONObject2.put(com.alipay.sdk.cons.b.g, bVar4.c());
                    jSONObject2.put(com.alipay.sdk.cons.b.j, bVar.f720b);
                    c.a();
                    jSONObject2.put(com.alipay.sdk.cons.b.k, c.a(bVar4.f714a));
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                jSONObject2 = jSONObject3;
            }
        } catch (Throwable unused3) {
            jSONObject2 = a4;
        }
        return jSONObject2.toString();
    }

    private static String a(HttpResponse httpResponse, String str) {
        Header[] allHeaders;
        String name;
        if (httpResponse == null || (allHeaders = httpResponse.getAllHeaders()) == null || allHeaders.length <= 0) {
            return null;
        }
        for (Header header : allHeaders) {
            if (header != null && (name = header.getName()) != null && name.equalsIgnoreCase(str)) {
                return header.getValue();
            }
        }
        return null;
    }

    public static String a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry next : hashMap.entrySet()) {
            jSONObject2.put((String) next.getKey(), next.getValue());
        }
        JSONObject jSONObject3 = new JSONObject();
        for (Map.Entry next2 : hashMap2.entrySet()) {
            jSONObject3.put((String) next2.getKey(), next2.getValue());
        }
        jSONObject2.put("params", jSONObject3);
        jSONObject.put(k, jSONObject2);
        return jSONObject.toString();
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(k);
            if (!jSONObject.has("params")) {
                return false;
            }
            String optString = jSONObject.getJSONObject("params").optString(m, (String) null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            com.alipay.sdk.sys.b.a();
            c.a().a(optString);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    private static a b(Context context, String str) {
        if (t == null) {
            t = new a(context, str);
        } else if (!TextUtils.equals(str, t.f690b)) {
            t.f690b = str;
        }
        return t;
    }

    private b a(Context context) throws Throwable {
        return a(context, "", k.a(context), true);
    }

    public b a(Context context, String str) throws Throwable {
        return a(context, str, k.a(context), true);
    }

    private b a(Context context, String str, String str2) throws Throwable {
        return a(context, str, str2, true);
    }

    public final b a(Context context, String str, String str2, boolean z) throws Throwable {
        String name;
        e eVar = new e(this.s);
        c a2 = eVar.a(new b(c(), a(str, a())), this.r);
        if (t == null) {
            t = new a(context, str2);
        } else if (!TextUtils.equals(str2, t.f690b)) {
            t.f690b = str2;
        }
        HttpResponse a3 = t.a(a2.f698b, a(a2.f697a, str));
        String str3 = null;
        if (a3 != null) {
            Header[] allHeaders = a3.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                int length = allHeaders.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        Header header = allHeaders[i2];
                        if (header != null && (name = header.getName()) != null && name.equalsIgnoreCase(f699a)) {
                            str3 = header.getValue();
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
        }
        b a4 = eVar.a(new c(Boolean.valueOf(str3).booleanValue(), b(a3)));
        return (a4 == null || !a(a4.f695a) || !z) ? a4 : a(context, str, str2, false);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:3|4|(3:5|6|(1:8)(1:31))|9|(2:11|12)|13|14|15) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0027 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0034 A[SYNTHETIC, Splitter:B:23:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003b A[SYNTHETIC, Splitter:B:27:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(org.apache.http.HttpResponse r4) throws java.lang.IllegalStateException, java.io.IOException {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            org.apache.http.HttpEntity r4 = r4.getEntity()     // Catch:{ all -> 0x0030 }
            java.io.InputStream r4 = r4.getContent()     // Catch:{ all -> 0x0030 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x002e }
            r2.<init>()     // Catch:{ all -> 0x002e }
        L_0x0012:
            int r1 = r4.read(r0)     // Catch:{ all -> 0x002b }
            r3 = -1
            if (r1 == r3) goto L_0x001e
            r3 = 0
            r2.write(r0, r3, r1)     // Catch:{ all -> 0x002b }
            goto L_0x0012
        L_0x001e:
            byte[] r0 = r2.toByteArray()     // Catch:{ all -> 0x002b }
            if (r4 == 0) goto L_0x0027
            r4.close()     // Catch:{ Exception -> 0x0027 }
        L_0x0027:
            r2.close()     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            return r0
        L_0x002b:
            r0 = move-exception
            r1 = r2
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            goto L_0x0032
        L_0x0030:
            r0 = move-exception
            r4 = r1
        L_0x0032:
            if (r4 == 0) goto L_0x0039
            r4.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x0039
        L_0x0038:
        L_0x0039:
            if (r1 == 0) goto L_0x003e
            r1.close()     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.d.b(org.apache.http.HttpResponse):byte[]");
    }

    private static boolean a(HttpResponse httpResponse) {
        Header[] allHeaders;
        String name;
        String str = null;
        if (httpResponse != null && (allHeaders = httpResponse.getAllHeaders()) != null && allHeaders.length > 0) {
            int length = allHeaders.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    Header header = allHeaders[i2];
                    if (header != null && (name = header.getName()) != null && name.equalsIgnoreCase(f699a)) {
                        str = header.getValue();
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        }
        return Boolean.valueOf(str).booleanValue();
    }
}
