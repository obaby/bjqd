package anet.channel.strategy.utils;

import anet.channel.util.ALog;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Taobao */
public class a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static AtomicInteger f325a = new AtomicInteger(0);

    /* renamed from: b  reason: collision with root package name */
    private static ScheduledThreadPoolExecutor f326b = null;

    static ScheduledThreadPoolExecutor a() {
        if (f326b == null) {
            synchronized (a.class) {
                if (f326b == null) {
                    f326b = new ScheduledThreadPoolExecutor(2, new b());
                    f326b.setKeepAliveTime(60, TimeUnit.SECONDS);
                    f326b.allowCoreThreadTimeOut(true);
                }
            }
        }
        return f326b;
    }

    public static void a(Runnable runnable) {
        try {
            a().submit(runnable);
        } catch (Exception e) {
            ALog.e(b.TAG, "submit task failed", (String) null, e, new Object[0]);
        }
    }

    public static void a(Runnable runnable, long j) {
        try {
            a().schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            ALog.e(b.TAG, "schedule task failed", (String) null, e, new Object[0]);
        }
    }
}
