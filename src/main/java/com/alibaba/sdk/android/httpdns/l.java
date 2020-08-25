package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import java.util.concurrent.Callable;

class l implements Callable<String[]> {

    /* renamed from: a  reason: collision with root package name */
    private static Context f478a;
    private static b hostManager = b.a();

    /* renamed from: a  reason: collision with other field name */
    private n f8a;

    /* renamed from: b  reason: collision with root package name */
    private String f479b;
    private int d = 1;
    private String[] e = d.f7d;
    private String g = null;

    l(String str, n nVar) {
        this.f479b = str;
        this.f8a = nVar;
    }

    static void setContext(Context context) {
        f478a = context;
    }

    public void a(int i) {
        if (i >= 0) {
            this.d = i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x021e A[Catch:{ all -> 0x0247 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x022e A[SYNTHETIC, Splitter:B:75:0x022e] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0236 A[Catch:{ IOException -> 0x0232 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x024a  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x024f A[SYNTHETIC, Splitter:B:88:0x024f] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0257 A[Catch:{ IOException -> 0x0253 }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] call() {
        /*
            r7 = this;
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2 = 14
            if (r1 < r2) goto L_0x000d
            r1 = 40965(0xa005, float:5.7404E-41)
            android.net.TrafficStats.setThreadStatsTag(r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
        L_0x000d:
            com.alibaba.sdk.android.httpdns.b r1 = hostManager     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = r7.f479b     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.b(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            com.alibaba.sdk.android.httpdns.n r1 = r7.f8a     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.s.a((com.alibaba.sdk.android.httpdns.n) r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r7.g = r1     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r1 = r7.g     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            if (r1 != 0) goto L_0x003a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.<init>()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = "serverIp is null, give up query for hostname:"
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = r7.f479b     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            com.alibaba.sdk.android.httpdns.g.e(r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2 = r0
            r3 = r2
            goto L_0x01e5
        L_0x003a:
            boolean r1 = com.alibaba.sdk.android.httpdns.a.a()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            if (r1 == 0) goto L_0x0096
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.a.getTimestamp()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.<init>()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = com.alibaba.sdk.android.httpdns.d.PROTOCOL     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = r7.g     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = com.alibaba.sdk.android.httpdns.d.d     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = "/"
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = com.alibaba.sdk.android.httpdns.d.f470c     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = "/sign_d?host="
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = r7.f479b     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = "&sdk=android_"
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = "1.1.3.1"
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = "&t="
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = "&s="
            r2.append(r3)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r3 = r7.f479b     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.a.a(r3, r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.append(r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            goto L_0x00d1
        L_0x0096:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.<init>()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = com.alibaba.sdk.android.httpdns.d.PROTOCOL     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = r7.g     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = ":"
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = com.alibaba.sdk.android.httpdns.d.d     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = com.alibaba.sdk.android.httpdns.d.f470c     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = "/d?host="
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = r7.f479b     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = "&sdk=android_"
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r2 = "1.1.3.1"
            r1.append(r2)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
        L_0x00d1:
            java.net.URL r2 = new java.net.URL     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.net.URLConnection r1 = r2.openConnection()     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Throwable -> 0x020d, all -> 0x0209 }
            int r2 = com.alibaba.sdk.android.httpdns.d.f468a     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            r1.setConnectTimeout(r2)     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            int r2 = com.alibaba.sdk.android.httpdns.d.f468a     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            r1.setReadTimeout(r2)     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            boolean r2 = r1 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            if (r2 == 0) goto L_0x00f5
            r2 = r1
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            com.alibaba.sdk.android.httpdns.l$1 r3 = new com.alibaba.sdk.android.httpdns.l$1     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            r3.<init>()     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            r2.setHostnameVerifier(r3)     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
        L_0x00f5:
            int r2 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L_0x0175
            java.io.InputStream r2 = r1.getErrorStream()     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r2, r5)     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r0.<init>()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
        L_0x0112:
            java.lang.String r4 = r3.readLine()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            if (r4 == 0) goto L_0x011c
            r0.append(r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            goto L_0x0112
        L_0x011c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.<init>()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = "response code is "
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            int r5 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = " expect 200. response body is "
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.g.f(r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.e r4 = new com.alibaba.sdk.android.httpdns.e     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            int r5 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.<init>(r5, r0)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.f r0 = new com.alibaba.sdk.android.httpdns.f     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            int r5 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r4 = r4.a()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r0.<init>(r5, r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            throw r0     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
        L_0x015b:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0248
        L_0x0161:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0210
        L_0x0167:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r1
            r1 = r6
            goto L_0x0248
        L_0x016e:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r1
            r1 = r6
            goto L_0x0210
        L_0x0175:
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ Throwable -> 0x0203, all -> 0x01fd }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r2, r5)     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x016e, all -> 0x0167 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r0.<init>()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
        L_0x018a:
            java.lang.String r4 = r3.readLine()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            if (r4 == 0) goto L_0x0194
            r0.append(r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            goto L_0x018a
        L_0x0194:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.<init>()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = "resolve host: "
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = r7.f479b     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = ", return: "
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.append(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.g.e(r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.c r4 = new com.alibaba.sdk.android.httpdns.c     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r4.<init>((java.lang.String) r0)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.b r0 = hostManager     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            int r0 = r0.a()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r5 = 100
            if (r0 >= r5) goto L_0x01f5
            com.alibaba.sdk.android.httpdns.b r0 = hostManager     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = r7.f479b     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r0.a(r5, r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r0 = r7.f479b     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = r7.g     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.s.a(r0, r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            com.alibaba.sdk.android.httpdns.b r0 = hostManager     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r5 = r7.f479b     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r0.c(r5)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String[] r0 = r4.a()     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r7.e = r0     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            r0 = r1
        L_0x01e5:
            if (r0 == 0) goto L_0x01ea
            r0.disconnect()
        L_0x01ea:
            if (r2 == 0) goto L_0x01ef
            r2.close()     // Catch:{ IOException -> 0x0232 }
        L_0x01ef:
            if (r3 == 0) goto L_0x023d
            r3.close()     // Catch:{ IOException -> 0x0232 }
            goto L_0x023d
        L_0x01f5:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            java.lang.String r4 = "the total number of hosts is exceed 100"
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
            throw r0     // Catch:{ Throwable -> 0x0161, all -> 0x015b }
        L_0x01fd:
            r2 = move-exception
            r3 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L_0x0248
        L_0x0203:
            r2 = move-exception
            r3 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L_0x0210
        L_0x0209:
            r1 = move-exception
            r2 = r0
            r3 = r2
            goto L_0x0248
        L_0x020d:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L_0x0210:
            com.alibaba.sdk.android.httpdns.g.a(r1)     // Catch:{ all -> 0x0247 }
            java.lang.String r4 = r7.f479b     // Catch:{ all -> 0x0247 }
            java.lang.String r5 = r7.g     // Catch:{ all -> 0x0247 }
            com.alibaba.sdk.android.httpdns.s.a(r4, r5, r1)     // Catch:{ all -> 0x0247 }
            int r1 = r7.d     // Catch:{ all -> 0x0247 }
            if (r1 <= 0) goto L_0x0227
            int r1 = r7.d     // Catch:{ all -> 0x0247 }
            int r1 = r1 + -1
            r7.d = r1     // Catch:{ all -> 0x0247 }
            r7.call()     // Catch:{ all -> 0x0247 }
        L_0x0227:
            if (r0 == 0) goto L_0x022c
            r0.disconnect()
        L_0x022c:
            if (r2 == 0) goto L_0x0234
            r2.close()     // Catch:{ IOException -> 0x0232 }
            goto L_0x0234
        L_0x0232:
            r0 = move-exception
            goto L_0x023a
        L_0x0234:
            if (r3 == 0) goto L_0x023d
            r3.close()     // Catch:{ IOException -> 0x0232 }
            goto L_0x023d
        L_0x023a:
            com.alibaba.sdk.android.httpdns.g.a(r0)
        L_0x023d:
            com.alibaba.sdk.android.httpdns.b r0 = hostManager
            java.lang.String r1 = r7.f479b
            r0.c(r1)
            java.lang.String[] r0 = r7.e
            return r0
        L_0x0247:
            r1 = move-exception
        L_0x0248:
            if (r0 == 0) goto L_0x024d
            r0.disconnect()
        L_0x024d:
            if (r2 == 0) goto L_0x0255
            r2.close()     // Catch:{ IOException -> 0x0253 }
            goto L_0x0255
        L_0x0253:
            r0 = move-exception
            goto L_0x025b
        L_0x0255:
            if (r3 == 0) goto L_0x025e
            r3.close()     // Catch:{ IOException -> 0x0253 }
            goto L_0x025e
        L_0x025b:
            com.alibaba.sdk.android.httpdns.g.a(r0)
        L_0x025e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.l.call():java.lang.String[]");
    }
}
