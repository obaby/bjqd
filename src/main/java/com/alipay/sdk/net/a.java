package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.stub.StubApp;
import java.net.URL;
import org.apache.http.HttpHost;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f689a = "application/octet-stream;binary/octet-stream";

    /* renamed from: b  reason: collision with root package name */
    public String f690b;

    /* renamed from: c  reason: collision with root package name */
    private Context f691c;

    private a(Context context) {
        this(context, (String) null);
    }

    public a(Context context, String str) {
        if (context != null) {
            this.f691c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        } else {
            this.f691c = context;
        }
        this.f690b = str;
    }

    private void a(String str) {
        this.f690b = str;
    }

    private String a() {
        return this.f690b;
    }

    private URL b() {
        try {
            return new URL(this.f690b);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e A[Catch:{ Throwable -> 0x010d, Throwable -> 0x011d }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085 A[Catch:{ Throwable -> 0x010d, Throwable -> 0x011d }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf A[Catch:{ Throwable -> 0x010d, Throwable -> 0x011d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.apache.http.HttpResponse a(byte[] r7, java.util.List<org.apache.http.Header> r8) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "requestUrl : "
            r0.<init>(r1)
            java.lang.String r1 = r6.f690b
            r0.append(r1)
            com.alipay.sdk.net.b r0 = com.alipay.sdk.net.b.a()
            r1 = 0
            if (r0 != 0) goto L_0x0014
            return r1
        L_0x0014:
            org.apache.http.impl.client.DefaultHttpClient r2 = r0.f694c     // Catch:{ Throwable -> 0x010d }
            org.apache.http.params.HttpParams r2 = r2.getParams()     // Catch:{ Throwable -> 0x010d }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x010d }
            r4 = 11
            if (r3 < r4) goto L_0x005b
            java.lang.String r3 = r6.g()     // Catch:{ Throwable -> 0x010d }
            if (r3 == 0) goto L_0x0030
            java.lang.String r4 = "wap"
            boolean r3 = r3.contains(r4)     // Catch:{ Throwable -> 0x010d }
            if (r3 != 0) goto L_0x0030
        L_0x002e:
            r5 = r1
            goto L_0x007c
        L_0x0030:
            java.net.URL r3 = r6.b()     // Catch:{ Throwable -> 0x010d }
            if (r3 == 0) goto L_0x002e
            java.lang.String r3 = r3.getProtocol()     // Catch:{ Throwable -> 0x010d }
            java.lang.String r4 = "https"
            r4.equalsIgnoreCase(r3)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r3 = "https.proxyHost"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r4 = "https.proxyPort"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch:{ Throwable -> 0x010d }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x010d }
            if (r5 != 0) goto L_0x002e
            org.apache.http.HttpHost r5 = new org.apache.http.HttpHost     // Catch:{ Throwable -> 0x010d }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Throwable -> 0x010d }
            r5.<init>(r3, r4)     // Catch:{ Throwable -> 0x010d }
            goto L_0x007c
        L_0x005b:
            android.net.NetworkInfo r3 = r6.f()     // Catch:{ Throwable -> 0x010d }
            if (r3 == 0) goto L_0x002e
            boolean r4 = r3.isAvailable()     // Catch:{ Throwable -> 0x010d }
            if (r4 == 0) goto L_0x002e
            int r3 = r3.getType()     // Catch:{ Throwable -> 0x010d }
            if (r3 != 0) goto L_0x002e
            java.lang.String r3 = android.net.Proxy.getDefaultHost()     // Catch:{ Throwable -> 0x010d }
            int r4 = android.net.Proxy.getDefaultPort()     // Catch:{ Throwable -> 0x010d }
            if (r3 == 0) goto L_0x002e
            org.apache.http.HttpHost r5 = new org.apache.http.HttpHost     // Catch:{ Throwable -> 0x010d }
            r5.<init>(r3, r4)     // Catch:{ Throwable -> 0x010d }
        L_0x007c:
            if (r5 == 0) goto L_0x0083
            java.lang.String r3 = "http.route.default-proxy"
            r2.setParameter(r3, r5)     // Catch:{ Throwable -> 0x010d }
        L_0x0083:
            if (r7 == 0) goto L_0x00b6
            int r2 = r7.length     // Catch:{ Throwable -> 0x010d }
            if (r2 != 0) goto L_0x0089
            goto L_0x00b6
        L_0x0089:
            org.apache.http.client.methods.HttpPost r2 = new org.apache.http.client.methods.HttpPost     // Catch:{ Throwable -> 0x010d }
            java.lang.String r3 = r6.f690b     // Catch:{ Throwable -> 0x010d }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x010d }
            org.apache.http.entity.ByteArrayEntity r3 = new org.apache.http.entity.ByteArrayEntity     // Catch:{ Throwable -> 0x010d }
            r3.<init>(r7)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r7 = "application/octet-stream;binary/octet-stream"
            r3.setContentType(r7)     // Catch:{ Throwable -> 0x010d }
            r7 = r2
            org.apache.http.client.methods.HttpPost r7 = (org.apache.http.client.methods.HttpPost) r7     // Catch:{ Throwable -> 0x010d }
            r7.setEntity(r3)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r7 = "Accept-Charset"
            java.lang.String r3 = "UTF-8"
            r2.addHeader(r7, r3)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r7 = "Connection"
            java.lang.String r3 = "Keep-Alive"
            r2.addHeader(r7, r3)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r7 = "Keep-Alive"
            java.lang.String r3 = "timeout=180, max=100"
            r2.addHeader(r7, r3)     // Catch:{ Throwable -> 0x010d }
            goto L_0x00bd
        L_0x00b6:
            org.apache.http.client.methods.HttpGet r2 = new org.apache.http.client.methods.HttpGet     // Catch:{ Throwable -> 0x010d }
            java.lang.String r7 = r6.f690b     // Catch:{ Throwable -> 0x010d }
            r2.<init>(r7)     // Catch:{ Throwable -> 0x010d }
        L_0x00bd:
            if (r8 == 0) goto L_0x00d3
            java.util.Iterator r7 = r8.iterator()     // Catch:{ Throwable -> 0x010d }
        L_0x00c3:
            boolean r8 = r7.hasNext()     // Catch:{ Throwable -> 0x010d }
            if (r8 == 0) goto L_0x00d3
            java.lang.Object r8 = r7.next()     // Catch:{ Throwable -> 0x010d }
            org.apache.http.Header r8 = (org.apache.http.Header) r8     // Catch:{ Throwable -> 0x010d }
            r2.addHeader(r8)     // Catch:{ Throwable -> 0x010d }
            goto L_0x00c3
        L_0x00d3:
            org.apache.http.HttpResponse r7 = r0.a(r2)     // Catch:{ Throwable -> 0x010d }
            java.lang.String r8 = "X-Hostname"
            org.apache.http.Header[] r8 = r7.getHeaders(r8)     // Catch:{ Throwable -> 0x010d }
            r2 = 0
            if (r8 == 0) goto L_0x00f2
            int r3 = r8.length     // Catch:{ Throwable -> 0x010d }
            if (r3 <= 0) goto L_0x00f2
            r8 = r8[r2]     // Catch:{ Throwable -> 0x010d }
            if (r8 == 0) goto L_0x00f2
            java.lang.String r8 = "X-Hostname"
            org.apache.http.Header[] r8 = r7.getHeaders(r8)     // Catch:{ Throwable -> 0x010d }
            r8 = r8[r2]     // Catch:{ Throwable -> 0x010d }
            r8.toString()     // Catch:{ Throwable -> 0x010d }
        L_0x00f2:
            java.lang.String r8 = "X-ExecuteTime"
            org.apache.http.Header[] r8 = r7.getHeaders(r8)     // Catch:{ Throwable -> 0x010d }
            if (r8 == 0) goto L_0x010c
            int r3 = r8.length     // Catch:{ Throwable -> 0x010d }
            if (r3 <= 0) goto L_0x010c
            r8 = r8[r2]     // Catch:{ Throwable -> 0x010d }
            if (r8 == 0) goto L_0x010c
            java.lang.String r8 = "X-ExecuteTime"
            org.apache.http.Header[] r8 = r7.getHeaders(r8)     // Catch:{ Throwable -> 0x010d }
            r8 = r8[r2]     // Catch:{ Throwable -> 0x010d }
            r8.toString()     // Catch:{ Throwable -> 0x010d }
        L_0x010c:
            return r7
        L_0x010d:
            r7 = move-exception
            if (r0 == 0) goto L_0x011d
            org.apache.http.impl.client.DefaultHttpClient r8 = r0.f694c     // Catch:{ Throwable -> 0x011d }
            org.apache.http.conn.ClientConnectionManager r8 = r8.getConnectionManager()     // Catch:{ Throwable -> 0x011d }
            if (r8 == 0) goto L_0x011d
            r8.shutdown()     // Catch:{ Throwable -> 0x011d }
            com.alipay.sdk.net.b.f693b = r1     // Catch:{ Throwable -> 0x011d }
        L_0x011d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.a.a(byte[], java.util.List):org.apache.http.HttpResponse");
    }

    private HttpHost c() {
        URL b2;
        if (Build.VERSION.SDK_INT >= 11) {
            String g = g();
            if ((g != null && !g.contains("wap")) || (b2 = b()) == null) {
                return null;
            }
            "https".equalsIgnoreCase(b2.getProtocol());
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (!TextUtils.isEmpty(property)) {
                return new HttpHost(property, Integer.parseInt(property2));
            }
            return null;
        }
        NetworkInfo f = f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultHost != null) {
            return new HttpHost(defaultHost, defaultPort);
        }
        return null;
    }

    private HttpHost d() {
        NetworkInfo f = f();
        if (f != null && f.isAvailable() && f.getType() == 0) {
            String defaultHost = Proxy.getDefaultHost();
            int defaultPort = Proxy.getDefaultPort();
            if (defaultHost != null) {
                return new HttpHost(defaultHost, defaultPort);
            }
        }
        return null;
    }

    private HttpHost e() {
        URL b2;
        String g = g();
        if ((g != null && !g.contains("wap")) || (b2 = b()) == null) {
            return null;
        }
        "https".equalsIgnoreCase(b2.getProtocol());
        String property = System.getProperty("https.proxyHost");
        String property2 = System.getProperty("https.proxyPort");
        if (!TextUtils.isEmpty(property)) {
            return new HttpHost(property, Integer.parseInt(property2));
        }
        return null;
    }

    private NetworkInfo f() {
        try {
            return ((ConnectivityManager) this.f691c.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    private String g() {
        try {
            NetworkInfo f = f();
            if (f == null || !f.isAvailable()) {
                return "none";
            }
            if (f.getType() == 1) {
                return "wifi";
            }
            return f.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return "none";
        }
    }
}
