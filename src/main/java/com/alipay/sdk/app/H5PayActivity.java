package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.stub.StubApp;

public class H5PayActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private WebView f596a;

    /* renamed from: b  reason: collision with root package name */
    private WebViewClient f597b;

    static {
        StubApp.interface11(5289);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable unused) {
        }
    }

    public void onBackPressed() {
        if (!this.f596a.canGoBack()) {
            i.f617a = i.a();
            finish();
        } else if (((b) this.f597b).f607c) {
            j a2 = j.a(j.NETWORK_ERROR.h);
            i.f617a = i.a(a2.h, a2.i, "");
            finish();
        }
    }

    public void finish() {
        a();
        super.finish();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r2 = this;
            java.lang.Object r0 = com.alipay.sdk.app.PayTask.f598a
            monitor-enter(r0)
            r0.notify()     // Catch:{ Exception -> 0x0009 }
            goto L_0x0009
        L_0x0007:
            r1 = move-exception
            goto L_0x000b
        L_0x0009:
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x000b:
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.H5PayActivity.a():void");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f596a != null) {
            this.f596a.removeAllViews();
            ((ViewGroup) this.f596a.getParent()).removeAllViews();
            try {
                this.f596a.destroy();
            } catch (Throwable unused) {
            }
            this.f596a = null;
        }
        if (this.f597b != null) {
            b bVar = (b) this.f597b;
            bVar.f606b = null;
            bVar.f605a = null;
        }
    }
}
