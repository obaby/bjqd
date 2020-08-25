package com.alipay.sdk.app;

final class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f613a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f614b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ H5PayCallback f615c;
    final /* synthetic */ PayTask d;

    g(PayTask payTask, String str, boolean z, H5PayCallback h5PayCallback) {
        this.d = payTask;
        this.f613a = str;
        this.f614b = z;
        this.f615c = h5PayCallback;
    }

    public final void run() {
        this.f615c.onPayResult(this.d.h5Pay(this.f613a, this.f614b));
    }
}
