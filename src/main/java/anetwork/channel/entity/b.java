package anetwork.channel.entity;

import java.util.concurrent.ThreadFactory;

/* compiled from: Taobao */
final class b implements ThreadFactory {
    b() {
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, String.format("RepeaterThread:%d", new Object[]{Integer.valueOf(a.f401b.getAndIncrement())}));
    }
}
