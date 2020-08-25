package com.alipay.sdk.auth;

final class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AuthActivity f640a;

    d(AuthActivity authActivity) {
        this.f640a = authActivity;
    }

    public final void run() {
        AuthActivity.g(this.f640a);
    }
}
