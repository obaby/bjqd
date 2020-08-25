package com.alipay.sdk.auth;

import android.webkit.WebView;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f638a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ AuthActivity f639b;

    c(AuthActivity authActivity, String str) {
        this.f639b = authActivity;
        this.f638a = str;
    }

    public final void run() {
        try {
            WebView h = this.f639b.f633c;
            h.loadUrl("javascript:" + this.f638a);
        } catch (Exception unused) {
        }
    }
}
