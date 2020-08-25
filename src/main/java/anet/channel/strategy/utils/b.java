package anet.channel.strategy.utils;

import anet.channel.util.ALog;
import com.alipay.sdk.cons.c;
import java.util.concurrent.ThreadFactory;

/* compiled from: Taobao */
final class b implements ThreadFactory {
    b() {
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AMDC" + a.f325a.incrementAndGet());
        ALog.i(b.TAG, "thread created!", (String) null, c.e, thread.getName());
        thread.setPriority(5);
        return thread;
    }
}
