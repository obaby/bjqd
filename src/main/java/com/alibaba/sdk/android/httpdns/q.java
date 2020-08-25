package com.alibaba.sdk.android.httpdns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class q {

    /* renamed from: a  reason: collision with root package name */
    private static q f487a;

    /* renamed from: b  reason: collision with root package name */
    private String f488b;
    private boolean d;
    private long f;
    private ExecutorService pool;

    private q() {
        this.f = 0;
        this.d = true;
        this.f488b = null;
        this.pool = null;
        this.pool = Executors.newSingleThreadExecutor();
    }

    public static q a() {
        if (f487a == null) {
            synchronized (q.class) {
                if (f487a == null) {
                    f487a = new q();
                }
            }
        }
        return f487a;
    }

    private boolean c() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f != 0 && currentTimeMillis - this.f < 30000) {
            return false;
        }
        this.f = currentTimeMillis;
        return true;
    }

    public synchronized void a(boolean z) {
        this.d = z;
    }

    public synchronized void e() {
        this.f = 0;
    }

    public synchronized void g(String str) {
        if (str != null) {
            try {
                this.f488b = str;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!this.d || !c() || this.f488b == null) {
            g.e("hostname is null or sniff too often or sniffer is turned off");
        } else {
            g.e("launch a sniff task");
            l lVar = new l(this.f488b, n.SNIFF_HOST);
            lVar.a(0);
            this.pool.submit(lVar);
            this.f488b = null;
        }
    }
}
