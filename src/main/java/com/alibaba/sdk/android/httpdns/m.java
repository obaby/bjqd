package com.alibaba.sdk.android.httpdns;

import java.util.concurrent.Callable;

public class m implements Callable<String[]> {
    private int d;

    public m(int i) {
        this.d = i;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v6, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e8 A[Catch:{ all -> 0x0104 }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f8 A[SYNTHETIC, Splitter:B:57:0x00f8] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fd A[Catch:{ IOException -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x010f A[SYNTHETIC, Splitter:B:69:0x010f] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0117 A[Catch:{ IOException -> 0x0113 }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] call() {
        /*
            r7 = this;
            r0 = 0
            com.alibaba.sdk.android.httpdns.o r1 = com.alibaba.sdk.android.httpdns.o.a()     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            java.lang.String r1 = r1.f()     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            if (r1 == 0) goto L_0x00ba
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            java.net.URLConnection r1 = r2.openConnection()     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x00d7, all -> 0x00d3 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r1.setConnectTimeout(r2)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r1.setReadTimeout(r2)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            boolean r2 = r1 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            if (r2 == 0) goto L_0x002d
            r2 = r1
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            com.alibaba.sdk.android.httpdns.m$1 r3 = new com.alibaba.sdk.android.httpdns.m$1     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r3.<init>()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r2.setHostnameVerifier(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
        L_0x002d:
            int r2 = r1.getResponseCode()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L_0x0067
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r2.<init>()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            java.lang.String r3 = "response code is "
            r2.append(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            int r3 = r1.getResponseCode()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r2.append(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            java.lang.String r3 = " expect 200"
            r2.append(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            com.alibaba.sdk.android.httpdns.g.f(r2)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            com.alibaba.sdk.android.httpdns.o r2 = com.alibaba.sdk.android.httpdns.o.a()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            com.alibaba.sdk.android.httpdns.f r3 = new com.alibaba.sdk.android.httpdns.f     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            int r4 = r1.getResponseCode()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            java.lang.String r5 = ""
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r2.b(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            r3 = r0
            goto L_0x00bc
        L_0x0067:
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00aa, all -> 0x00a3 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00aa, all -> 0x00a3 }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r2, r5)     // Catch:{ Exception -> 0x00aa, all -> 0x00a3 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00aa, all -> 0x00a3 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            r0.<init>()     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
        L_0x007c:
            java.lang.String r4 = r3.readLine()     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            if (r4 == 0) goto L_0x0086
            r0.append(r4)     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            goto L_0x007c
        L_0x0086:
            com.alibaba.sdk.android.httpdns.p r4 = new com.alibaba.sdk.android.httpdns.p     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            com.alibaba.sdk.android.httpdns.o r0 = com.alibaba.sdk.android.httpdns.o.a()     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            r0.a((com.alibaba.sdk.android.httpdns.p) r4)     // Catch:{ Exception -> 0x009d, all -> 0x0098 }
            r0 = r2
            goto L_0x00bc
        L_0x0098:
            r0 = move-exception
            r6 = r2
            r2 = r0
            goto L_0x0107
        L_0x009d:
            r0 = move-exception
            r6 = r2
            r2 = r0
            r0 = r1
            r1 = r6
            goto L_0x00da
        L_0x00a3:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r2
            r2 = r6
            goto L_0x0108
        L_0x00aa:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r1
            r1 = r2
            r2 = r6
            goto L_0x00da
        L_0x00b1:
            r2 = move-exception
            r3 = r0
            goto L_0x0108
        L_0x00b5:
            r2 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
            goto L_0x00da
        L_0x00ba:
            r1 = r0
            r3 = r1
        L_0x00bc:
            if (r1 == 0) goto L_0x00c1
            r1.disconnect()
        L_0x00c1:
            if (r0 == 0) goto L_0x00c9
            r0.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x00c9
        L_0x00c7:
            r0 = move-exception
            goto L_0x00cf
        L_0x00c9:
            if (r3 == 0) goto L_0x0100
            r3.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x0100
        L_0x00cf:
            com.alibaba.sdk.android.httpdns.g.a(r0)
            goto L_0x0100
        L_0x00d3:
            r2 = move-exception
            r1 = r0
            r3 = r1
            goto L_0x0108
        L_0x00d7:
            r2 = move-exception
            r1 = r0
            r3 = r1
        L_0x00da:
            com.alibaba.sdk.android.httpdns.g.a(r2)     // Catch:{ all -> 0x0104 }
            com.alibaba.sdk.android.httpdns.o r4 = com.alibaba.sdk.android.httpdns.o.a()     // Catch:{ all -> 0x0104 }
            r4.b(r2)     // Catch:{ all -> 0x0104 }
            int r2 = r7.d     // Catch:{ all -> 0x0104 }
            if (r2 <= 0) goto L_0x00f1
            int r2 = r7.d     // Catch:{ all -> 0x0104 }
            int r2 = r2 + -1
            r7.d = r2     // Catch:{ all -> 0x0104 }
            r7.call()     // Catch:{ all -> 0x0104 }
        L_0x00f1:
            if (r0 == 0) goto L_0x00f6
            r0.disconnect()
        L_0x00f6:
            if (r1 == 0) goto L_0x00fb
            r1.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x00fb:
            if (r3 == 0) goto L_0x0100
            r3.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x0100:
            r0 = 0
            java.lang.String[] r0 = new java.lang.String[r0]
            return r0
        L_0x0104:
            r2 = move-exception
            r6 = r1
            r1 = r0
        L_0x0107:
            r0 = r6
        L_0x0108:
            if (r1 == 0) goto L_0x010d
            r1.disconnect()
        L_0x010d:
            if (r0 == 0) goto L_0x0115
            r0.close()     // Catch:{ IOException -> 0x0113 }
            goto L_0x0115
        L_0x0113:
            r0 = move-exception
            goto L_0x011b
        L_0x0115:
            if (r3 == 0) goto L_0x011e
            r3.close()     // Catch:{ IOException -> 0x0113 }
            goto L_0x011e
        L_0x011b:
            com.alibaba.sdk.android.httpdns.g.a(r0)
        L_0x011e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.m.call():java.lang.String[]");
    }
}
