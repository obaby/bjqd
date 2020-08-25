package com.alipay.sdk.util;

import android.app.Activity;

final class n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f748a;

    n(Activity activity) {
        this.f748a = activity;
    }

    public final void run() {
        this.f748a.finish();
    }
}
