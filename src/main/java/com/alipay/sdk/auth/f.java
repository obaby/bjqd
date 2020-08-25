package com.alipay.sdk.auth;

import android.content.DialogInterface;

final class f implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f643a;

    f(e eVar) {
        this.f643a = eVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = AuthActivity.this.g = true;
        this.f643a.f641a.proceed();
        dialogInterface.dismiss();
    }
}
