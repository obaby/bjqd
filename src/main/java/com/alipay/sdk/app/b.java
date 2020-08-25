package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;

public final class b extends WebViewClient {
    /* access modifiers changed from: package-private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f605a;

    /* renamed from: b  reason: collision with root package name */
    Handler f606b;

    /* renamed from: c  reason: collision with root package name */
    boolean f607c;
    /* access modifiers changed from: private */
    public boolean d;
    private a e;
    private Runnable f = new f(this);

    public b(Activity activity) {
        this.f605a = activity;
        this.f606b = new Handler(this.f605a.getMainLooper());
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f607c = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        com.alipay.sdk.app.statistic.a.a(c.f625a, c.q, "证书错误");
        if (this.d) {
            sslErrorHandler.proceed();
            this.d = false;
            return;
        }
        this.f605a.runOnUiThread(new c(this, sslErrorHandler));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return l.a(webView, str, this.f605a);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.f606b != null) {
            if (this.e == null) {
                this.e = new a(this.f605a, a.f749a);
                this.e.e = true;
            }
            this.e.a();
            this.f606b.postDelayed(this.f, 30000);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        if (this.f606b != null) {
            b();
            this.f606b.removeCallbacks(this.f);
        }
    }

    private void a() {
        if (this.e == null) {
            this.e = new a(this.f605a, a.f749a);
            this.e.e = true;
        }
        this.e.a();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }

    private void c() {
        this.f606b = null;
        this.f605a = null;
    }

    private boolean d() {
        return this.f607c;
    }
}
