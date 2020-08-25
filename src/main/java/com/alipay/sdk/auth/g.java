package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.DialogInterface;

final class g implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f644a;

    g(e eVar) {
        this.f644a = eVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f644a.f641a.cancel();
        boolean unused = AuthActivity.this.g = false;
        h.a((Activity) AuthActivity.this, AuthActivity.this.d + "?resultCode=150");
        AuthActivity.this.finish();
    }
}
