package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.b;
import com.alipay.sdk.packet.impl.a;
import java.util.List;

final class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f648a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ StringBuilder f649b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ APAuthInfo f650c;

    i(Activity activity, StringBuilder sb, APAuthInfo aPAuthInfo) {
        this.f648a = activity;
        this.f649b = sb;
        this.f650c = aPAuthInfo;
    }

    public final void run() {
        b bVar;
        try {
            try {
                bVar = new a().a((Context) this.f648a, this.f649b.toString());
            } catch (Throwable unused) {
                bVar = null;
            }
            if (h.f647c != null) {
                h.f647c.b();
                com.alipay.sdk.widget.a unused2 = h.f647c = null;
            }
            if (bVar == null) {
                String unused3 = h.d = this.f650c.getRedirectUri() + "?resultCode=202";
                h.a(this.f648a, h.d);
                if (h.f647c != null) {
                    h.f647c.b();
                    return;
                }
                return;
            }
            List<com.alipay.sdk.protocol.b> a2 = com.alipay.sdk.protocol.b.a(bVar.a().optJSONObject(c.f672c).optJSONObject(c.d));
            int i = 0;
            while (true) {
                if (i >= a2.size()) {
                    break;
                } else if (a2.get(i).f707a == com.alipay.sdk.protocol.a.WapPay) {
                    String unused4 = h.d = a2.get(i).f708b[0];
                    break;
                } else {
                    i++;
                }
            }
            if (TextUtils.isEmpty(h.d)) {
                String unused5 = h.d = this.f650c.getRedirectUri() + "?resultCode=202";
                h.a(this.f648a, h.d);
                if (h.f647c != null) {
                    h.f647c.b();
                    return;
                }
                return;
            }
            Intent intent = new Intent(this.f648a, AuthActivity.class);
            intent.putExtra("params", h.d);
            intent.putExtra("redirectUri", this.f650c.getRedirectUri());
            this.f648a.startActivity(intent);
            if (h.f647c == null) {
                return;
            }
            h.f647c.b();
        } catch (Exception unused6) {
            if (h.f647c == null) {
            }
        } catch (Throwable th) {
            if (h.f647c != null) {
                h.f647c.b();
            }
            throw th;
        }
    }
}
