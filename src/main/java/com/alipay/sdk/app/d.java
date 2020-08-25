package com.alipay.sdk.app;

import android.content.DialogInterface;

final class d implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f610a;

    d(c cVar) {
        this.f610a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = this.f610a.f609b.d = true;
        this.f610a.f608a.proceed();
        dialogInterface.dismiss();
    }
}
