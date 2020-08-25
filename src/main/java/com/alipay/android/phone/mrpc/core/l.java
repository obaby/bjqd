package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class l implements ab {

    /* renamed from: b  reason: collision with root package name */
    private static l f532b;
    private static final ThreadFactory i = new n();

    /* renamed from: a  reason: collision with root package name */
    Context f533a;

    /* renamed from: c  reason: collision with root package name */
    private ThreadPoolExecutor f534c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), i, new ThreadPoolExecutor.CallerRunsPolicy());
    private b d = b.a(DispatchConstants.ANDROID);
    private long e;
    private long f;
    private long g;
    private int h;

    private l(Context context) {
        this.f533a = context;
        try {
            this.f534c.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        CookieSyncManager.createInstance(this.f533a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public static final l a(Context context) {
        return f532b != null ? f532b : b(context);
    }

    private static final synchronized l b(Context context) {
        synchronized (l.class) {
            if (f532b != null) {
                l lVar = f532b;
                return lVar;
            }
            l lVar2 = new l(context);
            f532b = lVar2;
            return lVar2;
        }
    }

    public final b a() {
        return this.d;
    }

    public final Future<u> a(t tVar) {
        if (s.a(this.f533a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.f534c.getActiveCount());
            objArr[1] = Long.valueOf(this.f534c.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.f534c.getTaskCount());
            long j = 0;
            objArr[3] = Long.valueOf(this.g == 0 ? 0 : ((this.e * 1000) / this.g) >> 10);
            if (this.h != 0) {
                j = this.f / ((long) this.h);
            }
            objArr[4] = Long.valueOf(j);
            objArr[5] = Long.valueOf(this.e);
            objArr[6] = Long.valueOf(this.f);
            objArr[7] = Long.valueOf(this.g);
            objArr[8] = Integer.valueOf(this.h);
            String.format(str, objArr);
        }
        q qVar = new q(this, (o) tVar);
        m mVar = new m(this, qVar, qVar);
        this.f534c.execute(mVar);
        return mVar;
    }

    public final void a(long j) {
        this.e += j;
    }

    public final void b(long j) {
        this.f += j;
        this.h++;
    }

    public final void c(long j) {
        this.g += j;
    }
}
