package com.alipay.apmobilesecuritysdk.f;

import java.util.LinkedList;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static b f577a = new b();
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public Thread f578b = null;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public LinkedList<Runnable> f579c = new LinkedList<>();

    public static b a() {
        return f577a;
    }

    public final synchronized void a(Runnable runnable) {
        this.f579c.add(runnable);
        if (this.f578b == null) {
            this.f578b = new Thread(new c(this));
            this.f578b.start();
        }
    }
}
