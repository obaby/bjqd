package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.sdk.util.h;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class o {

    /* renamed from: a  reason: collision with root package name */
    private static o f485a = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f486c = false;
    private static long d = 0;
    private static String h = "https://";
    private static String i;

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f9a;
    private int e;

    /* renamed from: e  reason: collision with other field name */
    private long f10e;
    private ExecutorService pool;

    private o() {
        this.e = 0;
        this.f9a = null;
        this.pool = null;
        this.f10e = 0;
        this.pool = Executors.newSingleThreadExecutor();
    }

    public static o a() {
        if (f485a == null) {
            synchronized (o.class) {
                if (f485a == null) {
                    f485a = new o();
                }
            }
        }
        return f485a;
    }

    private void d() {
        this.e = this.e < d.f6c.length + -1 ? this.e + 1 : 0;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Context context) {
        if (!f486c) {
            synchronized (o.class) {
                if (!f486c) {
                    if (context != null) {
                        this.f9a = context.getSharedPreferences("httpdns_config_cache", 0);
                    }
                    i = this.f9a.getString("httpdns_server_ips", (String) null);
                    if (i != null) {
                        d.a(i.split(h.f735b));
                    }
                    d = this.f9a.getLong("schedule_center_last_request_time", 0);
                    if (d == 0 || System.currentTimeMillis() - d >= 86400000) {
                        q.a().a(false);
                        c();
                    }
                    f486c = true;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(p pVar) {
        this.e = 0;
        HttpDns.switchDnsService(pVar.isEnabled());
        if (a(pVar.c())) {
            g.e("Scheduler center update success");
            this.f10e = System.currentTimeMillis();
            s.g();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean a(String[] strArr) {
        if (!d.a(strArr)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
            sb.append(h.f735b);
        }
        sb.deleteCharAt(sb.length() - 1);
        if (this.f9a == null) {
            return false;
        }
        SharedPreferences.Editor edit = this.f9a.edit();
        edit.putString("httpdns_server_ips", sb.toString());
        edit.putLong("schedule_center_last_request_time", System.currentTimeMillis());
        edit.commit();
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            d();
            if (this.e == 0) {
                this.f10e = System.currentTimeMillis();
                g.f("Scheduler center update failed");
                s.h();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void c() {
        if (System.currentTimeMillis() - this.f10e >= 300000) {
            g.e("update server ips from schedule center.");
            this.e = 0;
            this.pool.submit(new m(d.f6c.length - 1));
        } else {
            g.e("update server ips from schedule center too often, give up. ");
            s.h();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String f() {
        return h + d.f6c[this.e] + "/sc/httpdns_config?account_id=" + d.f470c + "&platform=android&sdk_version=" + "1.1.3.1";
    }
}
