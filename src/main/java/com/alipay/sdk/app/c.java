package com.alipay.sdk.app;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.widget.e;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SslErrorHandler f608a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ b f609b;

    c(b bVar, SslErrorHandler sslErrorHandler) {
        this.f609b = bVar;
        this.f608a = sslErrorHandler;
    }

    public final void run() {
        e.a(this.f609b.f605a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？", "继续", new d(this), "退出", new e(this));
    }
}
