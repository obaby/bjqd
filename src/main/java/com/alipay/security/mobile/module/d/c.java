package com.alipay.security.mobile.module.d;

public final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f769a;

    public c(b bVar) {
        this.f769a = bVar;
    }

    public final void run() {
        try {
            this.f769a.a();
        } catch (Exception e) {
            d.a((Throwable) e);
        }
    }
}
