package com.alipay.sdk.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.util.l;
import com.stub.StubApp;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public class AuthActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    static final String f631a = "params";

    /* renamed from: b  reason: collision with root package name */
    static final String f632b = "redirectUri";
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public WebView f633c;
    /* access modifiers changed from: private */
    public String d;
    private com.alipay.sdk.widget.a e;
    /* access modifiers changed from: private */
    public Handler f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public Runnable i = new d(this);

    static {
        StubApp.interface11(5310);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onBackPressed() {
        if (!this.f633c.canGoBack()) {
            h.a((Activity) this, this.d + "?resultCode=150");
            finish();
        } else if (this.h) {
            h.a((Activity) this, this.d + "?resultCode=150");
            finish();
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        /* synthetic */ b(AuthActivity authActivity, byte b2) {
            this();
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            boolean unused = AuthActivity.this.h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (AuthActivity.this.g) {
                sslErrorHandler.proceed();
                boolean unused = AuthActivity.this.g = false;
                return;
            }
            AuthActivity.this.runOnUiThread(new e(this, sslErrorHandler));
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.i.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.j.toLowerCase())) {
                try {
                    l.a a2 = l.a((Context) AuthActivity.this);
                    if (a2 != null) {
                        if (!a2.a()) {
                            if (str.startsWith("intent://platformapi/startapp")) {
                                str = str.replaceFirst(com.alipay.sdk.cons.a.j, com.alipay.sdk.cons.a.i);
                            }
                            AuthActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                            return true;
                        }
                    }
                    return true;
                } catch (Throwable unused) {
                }
            } else if (!AuthActivity.a(AuthActivity.this, str)) {
                return super.shouldOverrideUrlLoading(webView, str);
            } else {
                webView.stopLoading();
                return true;
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthActivity.d(AuthActivity.this);
            AuthActivity.this.f.postDelayed(AuthActivity.this.i, 30000);
            super.onPageStarted(webView, str, bitmap);
        }

        public final void onPageFinished(WebView webView, String str) {
            AuthActivity.g(AuthActivity.this);
            AuthActivity.this.f.removeCallbacks(AuthActivity.this.i);
        }
    }

    private class a extends WebChromeClient {
        private a() {
        }

        /* synthetic */ a(AuthActivity authActivity, byte b2) {
            this();
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (TextUtils.isEmpty(message)) {
                return super.onConsoleMessage(consoleMessage);
            }
            String str = null;
            if (message.startsWith("h5container.message: ")) {
                str = message.replaceFirst("h5container.message: ", "");
            }
            if (TextUtils.isEmpty(str)) {
                return super.onConsoleMessage(consoleMessage);
            }
            AuthActivity.b(AuthActivity.this, str);
            return super.onConsoleMessage(consoleMessage);
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.d)) {
                str = str + "?resultCode=150";
            }
            h.a((Activity) this, str);
        }
        finish();
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0058 A[SYNTHETIC, Splitter:B:17:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.lang.String r7) {
        /*
            r6 = this;
            com.alipay.sdk.authjs.d r0 = new com.alipay.sdk.authjs.d
            android.content.Context r1 = r6.getApplicationContext()
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)
            com.alipay.sdk.auth.b r2 = new com.alipay.sdk.auth.b
            r2.<init>(r6)
            r0.<init>(r1, r2)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0051 }
            r2.<init>(r7)     // Catch:{ Exception -> 0x0051 }
            java.lang.String r7 = "clientId"
            java.lang.String r7 = r2.getString(r7)     // Catch:{ Exception -> 0x0051 }
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x0052 }
            if (r3 == 0) goto L_0x0025
            return
        L_0x0025:
            java.lang.String r3 = "param"
            org.json.JSONObject r3 = r2.getJSONObject(r3)     // Catch:{ Exception -> 0x0052 }
            boolean r4 = r3 instanceof org.json.JSONObject     // Catch:{ Exception -> 0x0052 }
            if (r4 == 0) goto L_0x0032
            r1 = r3
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ Exception -> 0x0052 }
        L_0x0032:
            java.lang.String r3 = "func"
            java.lang.String r3 = r2.getString(r3)     // Catch:{ Exception -> 0x0052 }
            java.lang.String r4 = "bundleName"
            java.lang.String r2 = r2.getString(r4)     // Catch:{ Exception -> 0x0052 }
            com.alipay.sdk.authjs.a r4 = new com.alipay.sdk.authjs.a     // Catch:{ Exception -> 0x0052 }
            java.lang.String r5 = "call"
            r4.<init>(r5)     // Catch:{ Exception -> 0x0052 }
            r4.j = r2     // Catch:{ Exception -> 0x0052 }
            r4.k = r3     // Catch:{ Exception -> 0x0052 }
            r4.m = r1     // Catch:{ Exception -> 0x0052 }
            r4.i = r7     // Catch:{ Exception -> 0x0052 }
            r0.a((com.alipay.sdk.authjs.a) r4)     // Catch:{ Exception -> 0x0052 }
            return
        L_0x0051:
            r7 = r1
        L_0x0052:
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L_0x005e
            int r1 = com.alipay.sdk.authjs.a.C0011a.d     // Catch:{ JSONException -> 0x005e }
            r0.a((java.lang.String) r7, (int) r1)     // Catch:{ JSONException -> 0x005e }
            return
        L_0x005e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.AuthActivity.b(java.lang.String):void");
    }

    private void a(com.alipay.sdk.authjs.a aVar) {
        if (this.f633c != null && aVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.alipay.sdk.authjs.a.e, aVar.i);
                jSONObject.put(com.alipay.sdk.authjs.a.g, aVar.k);
                jSONObject.put(com.alipay.sdk.authjs.a.f, aVar.m);
                jSONObject.put(com.alipay.sdk.authjs.a.h, aVar.l);
                runOnUiThread(new c(this, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{jSONObject.toString()})));
            } catch (JSONException unused) {
            }
        }
    }

    private void a() {
        try {
            if (this.e == null) {
                this.e = new com.alipay.sdk.widget.a(this, com.alipay.sdk.widget.a.f749a);
            }
            this.e.a();
        } catch (Exception unused) {
            this.e = null;
        }
    }

    private void b() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f633c != null) {
            this.f633c.removeAllViews();
            try {
                this.f633c.destroy();
            } catch (Throwable unused) {
            }
            this.f633c = null;
        }
    }

    static /* synthetic */ boolean a(AuthActivity authActivity, String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, authActivity.d)) {
                str = str + "?resultCode=150";
            }
            h.a((Activity) authActivity, str);
        }
        authActivity.finish();
        return true;
    }

    static /* synthetic */ void d(AuthActivity authActivity) {
        try {
            if (authActivity.e == null) {
                authActivity.e = new com.alipay.sdk.widget.a(authActivity, com.alipay.sdk.widget.a.f749a);
            }
            authActivity.e.a();
        } catch (Exception unused) {
            authActivity.e = null;
        }
    }

    static /* synthetic */ void g(AuthActivity authActivity) {
        if (authActivity.e != null) {
            authActivity.e.b();
        }
        authActivity.e = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: org.json.JSONObject} */
    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0058 A[SYNTHETIC, Splitter:B:17:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void b(com.alipay.sdk.auth.AuthActivity r5, java.lang.String r6) {
        /*
            com.alipay.sdk.authjs.d r0 = new com.alipay.sdk.authjs.d
            android.content.Context r1 = r5.getApplicationContext()
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)
            com.alipay.sdk.auth.b r2 = new com.alipay.sdk.auth.b
            r2.<init>(r5)
            r0.<init>(r1, r2)
            r5 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0052 }
            r1.<init>(r6)     // Catch:{ Exception -> 0x0052 }
            java.lang.String r6 = "clientId"
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x0052 }
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0051 }
            if (r2 == 0) goto L_0x0025
            return
        L_0x0025:
            java.lang.String r2 = "param"
            org.json.JSONObject r2 = r1.getJSONObject(r2)     // Catch:{ Exception -> 0x0051 }
            boolean r3 = r2 instanceof org.json.JSONObject     // Catch:{ Exception -> 0x0051 }
            if (r3 == 0) goto L_0x0032
            r5 = r2
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ Exception -> 0x0051 }
        L_0x0032:
            java.lang.String r2 = "func"
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x0051 }
            java.lang.String r3 = "bundleName"
            java.lang.String r1 = r1.getString(r3)     // Catch:{ Exception -> 0x0051 }
            com.alipay.sdk.authjs.a r3 = new com.alipay.sdk.authjs.a     // Catch:{ Exception -> 0x0051 }
            java.lang.String r4 = "call"
            r3.<init>(r4)     // Catch:{ Exception -> 0x0051 }
            r3.j = r1     // Catch:{ Exception -> 0x0051 }
            r3.k = r2     // Catch:{ Exception -> 0x0051 }
            r3.m = r5     // Catch:{ Exception -> 0x0051 }
            r3.i = r6     // Catch:{ Exception -> 0x0051 }
            r0.a((com.alipay.sdk.authjs.a) r3)     // Catch:{ Exception -> 0x0051 }
            return
        L_0x0051:
            r5 = r6
        L_0x0052:
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x005e
            int r6 = com.alipay.sdk.authjs.a.C0011a.d     // Catch:{ JSONException -> 0x005e }
            r0.a((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x005e }
            return
        L_0x005e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.AuthActivity.b(com.alipay.sdk.auth.AuthActivity, java.lang.String):void");
    }

    static /* synthetic */ void a(AuthActivity authActivity, com.alipay.sdk.authjs.a aVar) {
        if (authActivity.f633c != null && aVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.alipay.sdk.authjs.a.e, aVar.i);
                jSONObject.put(com.alipay.sdk.authjs.a.g, aVar.k);
                jSONObject.put(com.alipay.sdk.authjs.a.f, aVar.m);
                jSONObject.put(com.alipay.sdk.authjs.a.h, aVar.l);
                authActivity.runOnUiThread(new c(authActivity, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{jSONObject.toString()})));
            } catch (JSONException unused) {
            }
        }
    }
}
