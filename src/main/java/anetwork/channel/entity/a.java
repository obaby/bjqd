package anetwork.channel.entity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Taobao */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final ExecutorService[] f400a = new ExecutorService[2];
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public static AtomicInteger f401b = new AtomicInteger(0);

    static {
        for (int i = 0; i < 2; i++) {
            f400a[i] = Executors.newSingleThreadExecutor(new b());
        }
    }

    public static void a(int i, Runnable runnable) {
        f400a[Math.abs(i % 2)].submit(runnable);
    }
}
