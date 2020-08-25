package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.stub.StubApp;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class PayTask {

    /* renamed from: a  reason: collision with root package name */
    static final Object f598a = e.class;
    private static final long h = 3000;
    private static long i = -1;

    /* renamed from: b  reason: collision with root package name */
    private Activity f599b;

    /* renamed from: c  reason: collision with root package name */
    private com.alipay.sdk.widget.a f600c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, a> g = new HashMap();

    public String getVersion() {
        return com.alipay.sdk.cons.a.f;
    }

    public PayTask(Activity activity) {
        this.f599b = activity;
        b a2 = b.a();
        Activity activity2 = this.f599b;
        c.a();
        a2.a((Context) activity2);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.f600c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.f750b);
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return j.a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return i.b(StubApp.getOrigApplicationContext(this.f599b.getApplicationContext()), h.f734a, "");
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            new Thread(new g(this, fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c6, code lost:
        if (r0.startsWith("http://" + r9.e) != false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0157, code lost:
        if (r0.startsWith("http://" + r9.f) != false) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0035, code lost:
        if (r0.startsWith("http://" + r9.d) != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String fetchOrderInfoFromH5PayUrl(java.lang.String r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r0 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Throwable -> 0x0335 }
            if (r0 != 0) goto L_0x0335
            java.lang.String r0 = r10.trim()     // Catch:{ Throwable -> 0x0335 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "https://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.d     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r0.startsWith(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 != 0) goto L_0x0037
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "http://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.d     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r0.startsWith(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 == 0) goto L_0x009c
        L_0x0037:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "(http|https)://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.d     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "\\?"
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = ""
            java.lang.String r1 = r0.replaceFirst(r1, r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0335 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r2 != 0) goto L_0x009c
            java.util.Map r10 = com.alipay.sdk.util.l.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "req_data"
            java.lang.Object r10 = r10.get(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "<request_token>"
            java.lang.String r1 = "</request_token>"
            java.lang.String r10 = com.alipay.sdk.util.l.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "_input_charset=\"utf-8\"&ordertoken=\""
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0335 }
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\""
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            com.alipay.sdk.sys.a r10 = new com.alipay.sdk.sys.a     // Catch:{ Throwable -> 0x0335 }
            android.app.Activity r1 = r9.f599b     // Catch:{ Throwable -> 0x0335 }
            r10.<init>(r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "sc"
            java.lang.String r2 = "h5tonative"
            java.lang.String r10 = r10.a(r1, r2)     // Catch:{ Throwable -> 0x0335 }
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = "\""
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = r0.toString()     // Catch:{ Throwable -> 0x0335 }
            monitor-exit(r9)
            return r10
        L_0x009c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "https://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.e     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r0.startsWith(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 != 0) goto L_0x00c8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "http://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.e     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r0.startsWith(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 == 0) goto L_0x012d
        L_0x00c8:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "(http|https)://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.e     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "\\?"
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = ""
            java.lang.String r1 = r0.replaceFirst(r1, r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0335 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r2 != 0) goto L_0x012d
            java.util.Map r10 = com.alipay.sdk.util.l.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "req_data"
            java.lang.Object r10 = r10.get(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "<request_token>"
            java.lang.String r1 = "</request_token>"
            java.lang.String r10 = com.alipay.sdk.util.l.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "_input_charset=\"utf-8\"&ordertoken=\""
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0335 }
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\""
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            com.alipay.sdk.sys.a r10 = new com.alipay.sdk.sys.a     // Catch:{ Throwable -> 0x0335 }
            android.app.Activity r1 = r9.f599b     // Catch:{ Throwable -> 0x0335 }
            r10.<init>(r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "sc"
            java.lang.String r2 = "h5tonative"
            java.lang.String r10 = r10.a(r1, r2)     // Catch:{ Throwable -> 0x0335 }
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = "\""
            r0.append(r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = r0.toString()     // Catch:{ Throwable -> 0x0335 }
            monitor-exit(r9)
            return r10
        L_0x012d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "https://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.f     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r0.startsWith(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 != 0) goto L_0x0159
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "http://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.f     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r0.startsWith(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 == 0) goto L_0x01c0
        L_0x0159:
            java.lang.String r1 = "alipay.wap.create.direct.pay.by.user"
            boolean r1 = r0.contains(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 != 0) goto L_0x0169
            java.lang.String r1 = "create_forex_trade_wap"
            boolean r1 = r0.contains(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 == 0) goto L_0x01c0
        L_0x0169:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "(http|https)://"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = r9.f     // Catch:{ Throwable -> 0x0335 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "\\?"
            r1.append(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = ""
            java.lang.String r1 = r0.replaceFirst(r1, r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0335 }
            if (r1 != 0) goto L_0x01c0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01c0 }
            r1.<init>()     // Catch:{ Throwable -> 0x01c0 }
            java.lang.String r2 = "url"
            r1.put(r2, r10)     // Catch:{ Throwable -> 0x01c0 }
            java.lang.String r2 = "bizcontext"
            com.alipay.sdk.sys.a r3 = new com.alipay.sdk.sys.a     // Catch:{ Throwable -> 0x01c0 }
            android.app.Activity r4 = r9.f599b     // Catch:{ Throwable -> 0x01c0 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x01c0 }
            java.lang.String r4 = "sc"
            java.lang.String r5 = "h5tonative"
            java.lang.String r3 = r3.a(r4, r5)     // Catch:{ Throwable -> 0x01c0 }
            r1.put(r2, r3)     // Catch:{ Throwable -> 0x01c0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01c0 }
            java.lang.String r3 = "new_external_info=="
            r2.<init>(r3)     // Catch:{ Throwable -> 0x01c0 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x01c0 }
            r2.append(r1)     // Catch:{ Throwable -> 0x01c0 }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x01c0 }
            monitor-exit(r9)
            return r1
        L_0x01c0:
            java.lang.String r1 = "^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)"
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch:{ Throwable -> 0x0335 }
            java.util.regex.Matcher r1 = r1.matcher(r10)     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = r1.find()     // Catch:{ Throwable -> 0x0335 }
            r2 = 0
            if (r1 == 0) goto L_0x02ee
            java.lang.String r1 = "?"
            java.lang.String r3 = ""
            java.lang.String r10 = com.alipay.sdk.util.l.a((java.lang.String) r1, (java.lang.String) r3, (java.lang.String) r10)     // Catch:{ Throwable -> 0x0335 }
            boolean r1 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Throwable -> 0x0335 }
            if (r1 != 0) goto L_0x02ee
            java.util.Map r10 = com.alipay.sdk.util.l.a((java.lang.String) r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            r1.<init>()     // Catch:{ Throwable -> 0x0335 }
            r3 = 0
            r4 = 1
            java.lang.String r5 = "trade_no"
            java.lang.String r6 = "trade_no"
            java.lang.String r7 = "alipay_trade_no"
            java.lang.String[] r8 = new java.lang.String[]{r6, r7}     // Catch:{ Throwable -> 0x0335 }
            r6 = r1
            r7 = r10
            boolean r3 = a(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0335 }
            if (r3 == 0) goto L_0x02ee
            r3 = 1
            r4 = 0
            java.lang.String r5 = "pay_phase_id"
            java.lang.String r0 = "payPhaseId"
            java.lang.String r6 = "pay_phase_id"
            java.lang.String r7 = "out_relation_id"
            java.lang.String[] r8 = new java.lang.String[]{r0, r6, r7}     // Catch:{ Throwable -> 0x0335 }
            r6 = r1
            r7 = r10
            a(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "&biz_sub_type=\"TRADE\""
            r1.append(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "&biz_type=\"trade\""
            r1.append(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "app_name"
            java.lang.Object r0 = r10.get(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0335 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0335 }
            if (r3 == 0) goto L_0x0238
            java.lang.String r3 = "cid"
            java.lang.Object r3 = r10.get(r3)     // Catch:{ Throwable -> 0x0335 }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ Throwable -> 0x0335 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0335 }
            if (r3 != 0) goto L_0x0238
            java.lang.String r0 = "ali1688"
            goto L_0x025c
        L_0x0238:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0335 }
            if (r3 == 0) goto L_0x025c
            java.lang.String r3 = "sid"
            java.lang.Object r3 = r10.get(r3)     // Catch:{ Throwable -> 0x0335 }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ Throwable -> 0x0335 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0335 }
            if (r3 == 0) goto L_0x025a
            java.lang.String r3 = "s_id"
            java.lang.Object r3 = r10.get(r3)     // Catch:{ Throwable -> 0x0335 }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ Throwable -> 0x0335 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x0335 }
            if (r3 != 0) goto L_0x025c
        L_0x025a:
            java.lang.String r0 = "tb"
        L_0x025c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r4 = "&app_name=\""
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0335 }
            r3.append(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "\""
            r3.append(r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x0335 }
            r1.append(r0)     // Catch:{ Throwable -> 0x0335 }
            r3 = 1
            r4 = 1
            java.lang.String r5 = "extern_token"
            java.lang.String r0 = "extern_token"
            java.lang.String r6 = "cid"
            java.lang.String r7 = "sid"
            java.lang.String r8 = "s_id"
            java.lang.String[] r8 = new java.lang.String[]{r0, r6, r7, r8}     // Catch:{ Throwable -> 0x0335 }
            r6 = r1
            r7 = r10
            boolean r0 = a(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0335 }
            if (r0 != 0) goto L_0x028e
            java.lang.String r10 = ""
            monitor-exit(r9)
            return r10
        L_0x028e:
            r3 = 1
            r4 = 0
            java.lang.String r5 = "appenv"
            java.lang.String r0 = "appenv"
            java.lang.String[] r8 = new java.lang.String[]{r0}     // Catch:{ Throwable -> 0x0335 }
            r6 = r1
            r7 = r10
            a(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "&pay_channel_id=\"alipay_sdk\""
            r1.append(r0)     // Catch:{ Throwable -> 0x0335 }
            com.alipay.sdk.app.PayTask$a r0 = new com.alipay.sdk.app.PayTask$a     // Catch:{ Throwable -> 0x0335 }
            r0.<init>(r9, r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "return_url"
            java.lang.Object r2 = r10.get(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x0335 }
            r0.f601a = r2     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "pay_order_id"
            java.lang.Object r10 = r10.get(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Throwable -> 0x0335 }
            r0.f602b = r10     // Catch:{ Throwable -> 0x0335 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0335 }
            r10.<init>()     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            r10.append(r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "&bizcontext=\""
            r10.append(r1)     // Catch:{ Throwable -> 0x0335 }
            com.alipay.sdk.sys.a r1 = new com.alipay.sdk.sys.a     // Catch:{ Throwable -> 0x0335 }
            android.app.Activity r2 = r9.f599b     // Catch:{ Throwable -> 0x0335 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r2 = "sc"
            java.lang.String r3 = "h5tonative"
            java.lang.String r1 = r1.a(r2, r3)     // Catch:{ Throwable -> 0x0335 }
            r10.append(r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "\""
            r10.append(r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x0335 }
            java.util.Map<java.lang.String, com.alipay.sdk.app.PayTask$a> r1 = r9.g     // Catch:{ Throwable -> 0x0335 }
            r1.put(r10, r0)     // Catch:{ Throwable -> 0x0335 }
            monitor-exit(r9)
            return r10
        L_0x02ee:
            java.lang.String r10 = "mclient.alipay.com/cashier/mobilepay.htm"
            boolean r10 = r0.contains(r10)     // Catch:{ Throwable -> 0x0335 }
            if (r10 != 0) goto L_0x0304
            boolean r10 = com.alipay.sdk.app.EnvUtils.isSandBox()     // Catch:{ Throwable -> 0x0335 }
            if (r10 == 0) goto L_0x0335
            java.lang.String r10 = "mobileclientgw.alipaydev.com/cashier/mobilepay.htm"
            boolean r10 = r0.contains(r10)     // Catch:{ Throwable -> 0x0335 }
            if (r10 == 0) goto L_0x0335
        L_0x0304:
            com.alipay.sdk.sys.a r10 = new com.alipay.sdk.sys.a     // Catch:{ Throwable -> 0x0335 }
            android.app.Activity r1 = r9.f599b     // Catch:{ Throwable -> 0x0335 }
            r10.<init>(r1)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = "sc"
            java.lang.String r3 = "h5tonative"
            java.lang.String r10 = r10.a(r1, r3)     // Catch:{ Throwable -> 0x0335 }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0335 }
            r1.<init>()     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r3 = "url"
            r1.put(r3, r0)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r0 = "bizcontext"
            r1.put(r0, r10)     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = "new_external_info==%s"
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0335 }
            r0[r2] = r1     // Catch:{ Throwable -> 0x0335 }
            java.lang.String r10 = java.lang.String.format(r10, r0)     // Catch:{ Throwable -> 0x0335 }
            monitor-exit(r9)
            return r10
        L_0x0333:
            r10 = move-exception
            goto L_0x0339
        L_0x0335:
            java.lang.String r10 = ""
            monitor-exit(r9)
            return r10
        L_0x0339:
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.fetchOrderInfoFromH5PayUrl(java.lang.String):java.lang.String");
    }

    private class a {

        /* renamed from: a  reason: collision with root package name */
        String f601a;

        /* renamed from: b  reason: collision with root package name */
        String f602b;

        private a() {
            this.f601a = "";
            this.f602b = "";
        }

        /* synthetic */ a(PayTask payTask, byte b2) {
            this();
        }

        private String a() {
            return this.f601a;
        }

        private void a(String str) {
            this.f601a = str;
        }

        private String b() {
            return this.f602b;
        }

        private void b(String str) {
            this.f602b = str;
        }
    }

    private static boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2 = "";
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
            return true;
        } else if (z) {
            sb.append("&");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        } else {
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        h5PayResultModel = new H5PayResultModel();
        try {
            str.trim();
            String[] split = pay(str, z).split(h.f735b);
            HashMap hashMap = new HashMap();
            for (String str2 : split) {
                String substring = str2.substring(0, str2.indexOf("={"));
                String str3 = substring + "={";
                hashMap.put(substring, str2.substring(str2.indexOf(str3) + str3.length(), str2.lastIndexOf(h.d)));
            }
            if (hashMap.containsKey(j.f738a)) {
                h5PayResultModel.setResultCode((String) hashMap.get(j.f738a));
            }
            if (hashMap.containsKey("callBackUrl")) {
                h5PayResultModel.setReturnUrl((String) hashMap.get("callBackUrl"));
            } else if (hashMap.containsKey(j.f740c)) {
                String str4 = (String) hashMap.get(j.f740c);
                if (str4.length() > 15) {
                    a aVar = this.g.get(str);
                    if (aVar != null) {
                        if (TextUtils.isEmpty(aVar.f602b)) {
                            h5PayResultModel.setReturnUrl(aVar.f601a);
                        } else {
                            h5PayResultModel.setReturnUrl(com.alipay.sdk.data.a.b().j.replace("$OrderId$", aVar.f602b));
                        }
                        this.g.remove(str);
                        return h5PayResultModel;
                    }
                    String a2 = l.a("&callBackUrl=\"", "\"", str4);
                    if (TextUtils.isEmpty(a2)) {
                        a2 = l.a("&call_back_url=\"", "\"", str4);
                        if (TextUtils.isEmpty(a2)) {
                            a2 = l.a(com.alipay.sdk.cons.a.o, "\"", str4);
                            if (TextUtils.isEmpty(a2)) {
                                a2 = URLDecoder.decode(l.a(com.alipay.sdk.cons.a.p, "&", str4), "utf-8");
                                if (TextUtils.isEmpty(a2)) {
                                    a2 = URLDecoder.decode(l.a("&callBackUrl=", "&", str4), "utf-8");
                                }
                            }
                        }
                    }
                    if (TextUtils.isEmpty(a2) && !TextUtils.isEmpty(str4) && str4.contains("call_back_url")) {
                        a2 = l.b("call_back_url=\"", "\"", str4);
                    }
                    if (TextUtils.isEmpty(a2)) {
                        a2 = com.alipay.sdk.data.a.b().j;
                    }
                    h5PayResultModel.setReturnUrl(a2);
                } else {
                    a aVar2 = this.g.get(str);
                    if (aVar2 != null) {
                        h5PayResultModel.setReturnUrl(aVar2.f601a);
                        this.g.remove(str);
                        return h5PayResultModel;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return h5PayResultModel;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(h.d));
    }

    private e.a a() {
        return new h(this);
    }

    private void b() {
        if (this.f600c != null) {
            this.f600c.a();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f600c != null) {
            this.f600c.b();
            this.f600c = null;
        }
    }

    private String a(String str) {
        String a2 = new com.alipay.sdk.sys.a(this.f599b).a(str);
        if (a2.contains("paymethod=\"expressGateway\"")) {
            return b(a2);
        }
        if (!l.c((Context) this.f599b)) {
            return b(a2);
        }
        e eVar = new e(this.f599b, new h(this));
        String a3 = eVar.a(a2);
        eVar.f730a = null;
        if (TextUtils.equals(a3, e.f729b)) {
            return b(a2);
        }
        return TextUtils.isEmpty(a3) ? i.a() : a3;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0095 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r9) {
        /*
            r8 = this;
            r8.b()
            com.alipay.sdk.packet.impl.d r0 = new com.alipay.sdk.packet.impl.d     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r0.<init>()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            android.app.Activity r1 = r8.f599b     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.packet.b r9 = r0.a((android.content.Context) r1, (java.lang.String) r9)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            org.json.JSONObject r9 = r9.a()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            java.lang.String r0 = "form"
            org.json.JSONObject r9 = r9.optJSONObject(r0)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            java.lang.String r0 = "onload"
            org.json.JSONObject r9 = r9.optJSONObject(r0)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            java.util.List r9 = com.alipay.sdk.protocol.b.a((org.json.JSONObject) r9)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r0 = 0
            r1 = 0
        L_0x002c:
            int r2 = r9.size()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r1 >= r2) goto L_0x00a1
            java.lang.Object r2 = r9.get(r1)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.b r2 = (com.alipay.sdk.protocol.b) r2     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.a r2 = r2.f707a     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.a r3 = com.alipay.sdk.protocol.a.Update     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r2 != r3) goto L_0x009e
            java.lang.Object r2 = r9.get(r1)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.b r2 = (com.alipay.sdk.protocol.b) r2     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            java.lang.String[] r2 = r2.f708b     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            int r3 = r2.length     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r4 = 3
            if (r3 != r4) goto L_0x009e
            java.lang.String r3 = "tid"
            r4 = r2[r0]     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r3 == 0) goto L_0x009e
            com.alipay.sdk.sys.b r3 = com.alipay.sdk.sys.b.a()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            android.content.Context r3 = r3.f714a     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.tid.b r4 = com.alipay.sdk.tid.b.a()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r5 = 1
            r6 = r2[r5]     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r6 != 0) goto L_0x009e
            r6 = 2
            r7 = r2[r6]     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r7 == 0) goto L_0x0071
            goto L_0x009e
        L_0x0071:
            r5 = r2[r5]     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r4.f719a = r5     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r2 = r2[r6]     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r4.f720b = r2     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.tid.a r2 = new com.alipay.sdk.tid.a     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.util.a r5 = com.alipay.sdk.util.a.a((android.content.Context) r3)     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
            java.lang.String r5 = r5.a()     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
            com.alipay.sdk.util.a r3 = com.alipay.sdk.util.a.a((android.content.Context) r3)     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
            java.lang.String r3 = r3.b()     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
            java.lang.String r6 = r4.f719a     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
            java.lang.String r4 = r4.f720b     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
            r2.a(r5, r3, r6, r4)     // Catch:{ Exception -> 0x0095, all -> 0x0099 }
        L_0x0095:
            r2.close()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            goto L_0x009e
        L_0x0099:
            r9 = move-exception
            r2.close()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            throw r9     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
        L_0x009e:
            int r1 = r1 + 1
            goto L_0x002c
        L_0x00a1:
            r8.c()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
        L_0x00a4:
            int r1 = r9.size()     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r0 >= r1) goto L_0x00d1
            java.lang.Object r1 = r9.get(r0)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.b r1 = (com.alipay.sdk.protocol.b) r1     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.a r1 = r1.f707a     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.a r2 = com.alipay.sdk.protocol.a.WapPay     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            if (r1 != r2) goto L_0x00c4
            java.lang.Object r9 = r9.get(r0)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            com.alipay.sdk.protocol.b r9 = (com.alipay.sdk.protocol.b) r9     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            java.lang.String r9 = r8.a((com.alipay.sdk.protocol.b) r9)     // Catch:{ IOException -> 0x00d6, Throwable -> 0x00c9 }
            r8.c()
            return r9
        L_0x00c4:
            int r0 = r0 + 1
            goto L_0x00a4
        L_0x00c7:
            r9 = move-exception
            goto L_0x00fd
        L_0x00c9:
            r9 = move-exception
            java.lang.String r0 = "biz"
            java.lang.String r1 = "H5PayDataAnalysisError"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r9)     // Catch:{ all -> 0x00c7 }
        L_0x00d1:
            r8.c()
            r9 = 0
            goto L_0x00e8
        L_0x00d6:
            r9 = move-exception
            com.alipay.sdk.app.j r0 = com.alipay.sdk.app.j.NETWORK_ERROR     // Catch:{ all -> 0x00c7 }
            int r0 = r0.h     // Catch:{ all -> 0x00c7 }
            com.alipay.sdk.app.j r0 = com.alipay.sdk.app.j.a((int) r0)     // Catch:{ all -> 0x00c7 }
            java.lang.String r1 = "net"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r1, (java.lang.Throwable) r9)     // Catch:{ all -> 0x00c7 }
            r8.c()
            r9 = r0
        L_0x00e8:
            if (r9 != 0) goto L_0x00f2
            com.alipay.sdk.app.j r9 = com.alipay.sdk.app.j.FAILED
            int r9 = r9.h
            com.alipay.sdk.app.j r9 = com.alipay.sdk.app.j.a((int) r9)
        L_0x00f2:
            int r0 = r9.h
            java.lang.String r9 = r9.i
            java.lang.String r1 = ""
            java.lang.String r9 = com.alipay.sdk.app.i.a(r0, r9, r1)
            return r9
        L_0x00fd:
            r8.c()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.b(java.lang.String):java.lang.String");
    }

    private static boolean d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - i < h) {
            return true;
        }
        i = elapsedRealtime;
        return false;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:72:0x016f */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00be A[Catch:{ Throwable -> 0x0143, Throwable -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c0 A[Catch:{ Throwable -> 0x0143, Throwable -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x013d A[Catch:{ Throwable -> 0x0143, Throwable -> 0x016f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String pay(java.lang.String r11, boolean r12) {
        /*
            r10 = this;
            monitor-enter(r10)
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x01b6 }
            long r2 = i     // Catch:{ all -> 0x01b6 }
            r4 = 0
            long r2 = r0 - r2
            r4 = 3000(0xbb8, double:1.482E-320)
            r6 = 0
            r7 = 1
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 < 0) goto L_0x0016
            i = r0     // Catch:{ all -> 0x01b6 }
            r0 = 0
            goto L_0x0017
        L_0x0016:
            r0 = 1
        L_0x0017:
            if (r0 == 0) goto L_0x002d
            com.alipay.sdk.app.j r11 = com.alipay.sdk.app.j.DOUBLE_REQUEST     // Catch:{ all -> 0x01b6 }
            int r11 = r11.h     // Catch:{ all -> 0x01b6 }
            com.alipay.sdk.app.j r11 = com.alipay.sdk.app.j.a((int) r11)     // Catch:{ all -> 0x01b6 }
            int r12 = r11.h     // Catch:{ all -> 0x01b6 }
            java.lang.String r11 = r11.i     // Catch:{ all -> 0x01b6 }
            java.lang.String r0 = ""
            java.lang.String r11 = com.alipay.sdk.app.i.a(r12, r11, r0)     // Catch:{ all -> 0x01b6 }
            monitor-exit(r10)
            return r11
        L_0x002d:
            if (r12 == 0) goto L_0x0032
            r10.b()     // Catch:{ all -> 0x01b6 }
        L_0x0032:
            java.lang.String r12 = "service=alipay.acquire.mr.ord.createandpay"
            boolean r12 = r11.contains(r12)     // Catch:{ all -> 0x01b6 }
            if (r12 == 0) goto L_0x003c
            com.alipay.sdk.cons.a.r = r7     // Catch:{ all -> 0x01b6 }
        L_0x003c:
            boolean r12 = com.alipay.sdk.cons.a.r     // Catch:{ all -> 0x01b6 }
            if (r12 == 0) goto L_0x0069
            java.lang.String r12 = "https://wappaygw.alipay.com/home/exterfaceAssign.htm?"
            boolean r12 = r11.startsWith(r12)     // Catch:{ all -> 0x01b6 }
            if (r12 == 0) goto L_0x0055
            java.lang.String r12 = "https://wappaygw.alipay.com/home/exterfaceAssign.htm?"
            int r12 = r11.indexOf(r12)     // Catch:{ all -> 0x01b6 }
            int r12 = r12 + 53
            java.lang.String r11 = r11.substring(r12)     // Catch:{ all -> 0x01b6 }
            goto L_0x0069
        L_0x0055:
            java.lang.String r12 = "https://mclient.alipay.com/home/exterfaceAssign.htm?"
            boolean r12 = r11.startsWith(r12)     // Catch:{ all -> 0x01b6 }
            if (r12 == 0) goto L_0x0069
            java.lang.String r12 = "https://mclient.alipay.com/home/exterfaceAssign.htm?"
            int r12 = r11.indexOf(r12)     // Catch:{ all -> 0x01b6 }
            int r12 = r12 + 52
            java.lang.String r11 = r11.substring(r12)     // Catch:{ all -> 0x01b6 }
        L_0x0069:
            com.alipay.sdk.sys.a r12 = new com.alipay.sdk.sys.a     // Catch:{ Throwable -> 0x016f }
            android.app.Activity r0 = r10.f599b     // Catch:{ Throwable -> 0x016f }
            r12.<init>(r0)     // Catch:{ Throwable -> 0x016f }
            java.lang.String r12 = r12.a(r11)     // Catch:{ Throwable -> 0x016f }
            java.lang.String r0 = "paymethod=\"expressGateway\""
            boolean r0 = r12.contains(r0)     // Catch:{ Throwable -> 0x016f }
            r1 = 0
            if (r0 != 0) goto L_0x00aa
            android.app.Activity r0 = r10.f599b     // Catch:{ Throwable -> 0x016f }
            boolean r0 = com.alipay.sdk.util.l.c((android.content.Context) r0)     // Catch:{ Throwable -> 0x016f }
            if (r0 == 0) goto L_0x00aa
            com.alipay.sdk.util.e r0 = new com.alipay.sdk.util.e     // Catch:{ Throwable -> 0x016f }
            android.app.Activity r2 = r10.f599b     // Catch:{ Throwable -> 0x016f }
            com.alipay.sdk.app.h r3 = new com.alipay.sdk.app.h     // Catch:{ Throwable -> 0x016f }
            r3.<init>(r10)     // Catch:{ Throwable -> 0x016f }
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x016f }
            java.lang.String r2 = r0.a((java.lang.String) r12)     // Catch:{ Throwable -> 0x016f }
            r0.f730a = r1     // Catch:{ Throwable -> 0x016f }
            java.lang.String r0 = "failed"
            boolean r0 = android.text.TextUtils.equals(r2, r0)     // Catch:{ Throwable -> 0x016f }
            if (r0 != 0) goto L_0x00aa
            boolean r12 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x016f }
            if (r12 == 0) goto L_0x00ae
            java.lang.String r2 = com.alipay.sdk.app.i.a()     // Catch:{ Throwable -> 0x016f }
            goto L_0x00ae
        L_0x00aa:
            java.lang.String r2 = r10.b(r12)     // Catch:{ Throwable -> 0x016f }
        L_0x00ae:
            android.app.Activity r12 = r10.f599b     // Catch:{ Throwable -> 0x016f }
            android.content.Context r12 = r12.getApplicationContext()     // Catch:{ Throwable -> 0x016f }
            android.content.Context r12 = com.stub.StubApp.getOrigApplicationContext(r12)     // Catch:{ Throwable -> 0x016f }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x0143 }
            if (r0 == 0) goto L_0x00c0
            goto L_0x0137
        L_0x00c0:
            java.lang.String r0 = ";"
            java.lang.String[] r0 = r2.split(r0)     // Catch:{ Throwable -> 0x0143 }
            r3 = r1
            r1 = 0
        L_0x00c8:
            int r4 = r0.length     // Catch:{ Throwable -> 0x0143 }
            if (r1 >= r4) goto L_0x0136
            r4 = r0[r1]     // Catch:{ Throwable -> 0x0143 }
            java.lang.String r5 = "result={"
            boolean r4 = r4.startsWith(r5)     // Catch:{ Throwable -> 0x0143 }
            if (r4 == 0) goto L_0x0133
            r4 = r0[r1]     // Catch:{ Throwable -> 0x0143 }
            java.lang.String r5 = "}"
            boolean r4 = r4.endsWith(r5)     // Catch:{ Throwable -> 0x0143 }
            if (r4 == 0) goto L_0x0133
            r4 = r0[r1]     // Catch:{ Throwable -> 0x0143 }
            r5 = 8
            r8 = r0[r1]     // Catch:{ Throwable -> 0x0143 }
            int r8 = r8.length()     // Catch:{ Throwable -> 0x0143 }
            int r8 = r8 - r7
            java.lang.String r4 = r4.substring(r5, r8)     // Catch:{ Throwable -> 0x0143 }
            java.lang.String r5 = "&"
            java.lang.String[] r4 = r4.split(r5)     // Catch:{ Throwable -> 0x0143 }
            r5 = 0
        L_0x00f6:
            int r8 = r4.length     // Catch:{ Throwable -> 0x0143 }
            if (r5 >= r8) goto L_0x0133
            r8 = r4[r5]     // Catch:{ Throwable -> 0x0143 }
            java.lang.String r9 = "trade_token=\""
            boolean r8 = r8.startsWith(r9)     // Catch:{ Throwable -> 0x0143 }
            if (r8 == 0) goto L_0x011d
            r8 = r4[r5]     // Catch:{ Throwable -> 0x0143 }
            java.lang.String r9 = "\""
            boolean r8 = r8.endsWith(r9)     // Catch:{ Throwable -> 0x0143 }
            if (r8 == 0) goto L_0x011d
            r3 = r4[r5]     // Catch:{ Throwable -> 0x0143 }
            r8 = 13
            r4 = r4[r5]     // Catch:{ Throwable -> 0x0143 }
            int r4 = r4.length()     // Catch:{ Throwable -> 0x0143 }
            int r4 = r4 - r7
            java.lang.String r3 = r3.substring(r8, r4)     // Catch:{ Throwable -> 0x0143 }
            goto L_0x0133
        L_0x011d:
            r8 = r4[r5]     // Catch:{ Throwable -> 0x0143 }
            java.lang.String r9 = "trade_token="
            boolean r8 = r8.startsWith(r9)     // Catch:{ Throwable -> 0x0143 }
            if (r8 == 0) goto L_0x0130
            r3 = r4[r5]     // Catch:{ Throwable -> 0x0143 }
            r4 = 12
            java.lang.String r3 = r3.substring(r4)     // Catch:{ Throwable -> 0x0143 }
            goto L_0x0133
        L_0x0130:
            int r5 = r5 + 1
            goto L_0x00f6
        L_0x0133:
            int r1 = r1 + 1
            goto L_0x00c8
        L_0x0136:
            r1 = r3
        L_0x0137:
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0143 }
            if (r0 != 0) goto L_0x014b
            java.lang.String r0 = "pref_trade_token"
            com.alipay.sdk.util.i.a(r12, r0, r1)     // Catch:{ Throwable -> 0x0143 }
            goto L_0x014b
        L_0x0143:
            r12 = move-exception
            java.lang.String r0 = "biz"
            java.lang.String r1 = "SaveTradeTokenError"
            com.alipay.sdk.app.statistic.a.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r12)     // Catch:{ Throwable -> 0x016f }
        L_0x014b:
            com.alipay.sdk.data.a r12 = com.alipay.sdk.data.a.b()     // Catch:{ all -> 0x01b6 }
            android.app.Activity r0 = r10.f599b     // Catch:{ all -> 0x01b6 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ all -> 0x01b6 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ all -> 0x01b6 }
            r12.a((android.content.Context) r0)     // Catch:{ all -> 0x01b6 }
            r10.c()     // Catch:{ all -> 0x01b6 }
            android.app.Activity r12 = r10.f599b     // Catch:{ all -> 0x01b6 }
            android.content.Context r12 = r12.getApplicationContext()     // Catch:{ all -> 0x01b6 }
            android.content.Context r12 = com.stub.StubApp.getOrigApplicationContext(r12)     // Catch:{ all -> 0x01b6 }
        L_0x0169:
            com.alipay.sdk.app.statistic.a.a((android.content.Context) r12, (java.lang.String) r11)     // Catch:{ all -> 0x01b6 }
            goto L_0x0192
        L_0x016d:
            r12 = move-exception
            goto L_0x0194
        L_0x016f:
            java.lang.String r2 = com.alipay.sdk.app.i.a()     // Catch:{ all -> 0x016d }
            com.alipay.sdk.data.a r12 = com.alipay.sdk.data.a.b()     // Catch:{ all -> 0x01b6 }
            android.app.Activity r0 = r10.f599b     // Catch:{ all -> 0x01b6 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ all -> 0x01b6 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ all -> 0x01b6 }
            r12.a((android.content.Context) r0)     // Catch:{ all -> 0x01b6 }
            r10.c()     // Catch:{ all -> 0x01b6 }
            android.app.Activity r12 = r10.f599b     // Catch:{ all -> 0x01b6 }
            android.content.Context r12 = r12.getApplicationContext()     // Catch:{ all -> 0x01b6 }
            android.content.Context r12 = com.stub.StubApp.getOrigApplicationContext(r12)     // Catch:{ all -> 0x01b6 }
            goto L_0x0169
        L_0x0192:
            monitor-exit(r10)
            return r2
        L_0x0194:
            com.alipay.sdk.data.a r0 = com.alipay.sdk.data.a.b()     // Catch:{ all -> 0x01b6 }
            android.app.Activity r1 = r10.f599b     // Catch:{ all -> 0x01b6 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ all -> 0x01b6 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ all -> 0x01b6 }
            r0.a((android.content.Context) r1)     // Catch:{ all -> 0x01b6 }
            r10.c()     // Catch:{ all -> 0x01b6 }
            android.app.Activity r0 = r10.f599b     // Catch:{ all -> 0x01b6 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ all -> 0x01b6 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ all -> 0x01b6 }
            com.alipay.sdk.app.statistic.a.a((android.content.Context) r0, (java.lang.String) r11)     // Catch:{ all -> 0x01b6 }
            throw r12     // Catch:{ all -> 0x01b6 }
        L_0x01b6:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.pay(java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:13|14|15|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003b, code lost:
        if (android.text.TextUtils.isEmpty(r5) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0 = com.alipay.sdk.app.i.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return com.alipay.sdk.app.i.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        r5 = com.alipay.sdk.app.i.f617a;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0044 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(com.alipay.sdk.protocol.b r5) {
        /*
            r4 = this;
            java.lang.String[] r5 = r5.f708b
            android.content.Intent r0 = new android.content.Intent
            android.app.Activity r1 = r4.f599b
            java.lang.Class<com.alipay.sdk.app.H5PayActivity> r2 = com.alipay.sdk.app.H5PayActivity.class
            r0.<init>(r1, r2)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r2 = 0
            r2 = r5[r2]
            java.lang.String r3 = "url"
            r1.putString(r3, r2)
            int r2 = r5.length
            r3 = 2
            if (r2 != r3) goto L_0x0024
            r2 = 1
            r5 = r5[r2]
            java.lang.String r2 = "cookie"
            r1.putString(r2, r5)
        L_0x0024:
            r0.putExtras(r1)
            android.app.Activity r5 = r4.f599b
            r5.startActivity(r0)
            java.lang.Object r5 = f598a
            monitor-enter(r5)
            java.lang.Object r0 = f598a     // Catch:{ InterruptedException -> 0x0044 }
            r0.wait()     // Catch:{ InterruptedException -> 0x0044 }
            monitor-exit(r5)     // Catch:{ all -> 0x0042 }
            java.lang.String r5 = com.alipay.sdk.app.i.f617a
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x0041
            java.lang.String r5 = com.alipay.sdk.app.i.a()
        L_0x0041:
            return r5
        L_0x0042:
            r0 = move-exception
            goto L_0x004a
        L_0x0044:
            java.lang.String r0 = com.alipay.sdk.app.i.a()     // Catch:{ all -> 0x0042 }
            monitor-exit(r5)     // Catch:{ all -> 0x0042 }
            return r0
        L_0x004a:
            monitor-exit(r5)     // Catch:{ all -> 0x0042 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(com.alipay.sdk.protocol.b):java.lang.String");
    }
}
