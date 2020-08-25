package anet.channel.thread;

import anet.channel.util.ALog;
import com.alipay.sdk.cons.c;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Taobao */
public class ThreadPoolExecutorFactory {

    /* renamed from: a  reason: collision with root package name */
    private static ScheduledExecutorService f327a = new ScheduledThreadPoolExecutor(1, new b("AWCN Scheduler"));

    /* renamed from: b  reason: collision with root package name */
    private static ThreadPoolExecutor f328b = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new b("AWCN Worker(H)"));

    /* renamed from: c  reason: collision with root package name */
    private static ThreadPoolExecutor f329c = new a(6, 6, 60, TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("AWCN Worker(M)"));
    private static ThreadPoolExecutor d = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new b("AWCN Worker(M)"));

    /* compiled from: Taobao */
    public static class Priority {
        public static int HIGH = 0;
        public static int LOW = 9;
        public static int NORMAL = 1;
    }

    static {
        f328b.allowCoreThreadTimeOut(true);
        f329c.allowCoreThreadTimeOut(true);
        d.allowCoreThreadTimeOut(true);
    }

    /* compiled from: Taobao */
    private static class b implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        AtomicInteger f333a = new AtomicInteger(0);

        /* renamed from: b  reason: collision with root package name */
        String f334b;

        b(String str) {
            this.f334b = str;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f334b + this.f333a.incrementAndGet());
            ALog.i("awcn.ThreadPoolExecutorFactory", "thread created!", (String) null, c.e, thread.getName());
            thread.setPriority(5);
            return thread;
        }
    }

    public static Future<?> submitScheduledTask(Runnable runnable) {
        return f327a.submit(runnable);
    }

    public static Future<?> submitScheduledTask(Runnable runnable, long j, TimeUnit timeUnit) {
        return f327a.schedule(runnable, j, timeUnit);
    }

    public static Future<?> submitPriorityTask(Runnable runnable, int i) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.ThreadPoolExecutorFactory", "submit priority task", (String) null, "priority", Integer.valueOf(i));
        }
        if (i < Priority.HIGH || i > Priority.LOW) {
            i = Priority.LOW;
        }
        if (i == Priority.HIGH) {
            return f328b.submit(runnable);
        }
        if (i == Priority.LOW) {
            return d.submit(runnable);
        }
        return f329c.submit(new a(runnable, i));
    }

    /* compiled from: Taobao */
    static class a implements Comparable<a>, Runnable {

        /* renamed from: a  reason: collision with root package name */
        Runnable f330a = null;

        /* renamed from: b  reason: collision with root package name */
        int f331b = 0;

        /* renamed from: c  reason: collision with root package name */
        long f332c = System.currentTimeMillis();

        public a(Runnable runnable, int i) {
            this.f330a = runnable;
            this.f331b = i;
            this.f332c = System.currentTimeMillis();
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            if (this.f331b != aVar.f331b) {
                return this.f331b - aVar.f331b;
            }
            return (int) (aVar.f332c - this.f332c);
        }

        public void run() {
            this.f330a.run();
        }
    }
}
