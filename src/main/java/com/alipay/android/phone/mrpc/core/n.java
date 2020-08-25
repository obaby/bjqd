package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class n implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private final AtomicInteger f537a = new AtomicInteger(1);

    n() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f537a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
