package com.alipay.sdk.app;

import android.content.DialogInterface;

final class e implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f611a;

    e(c cVar) {
        this.f611a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f611a.f608a.cancel();
        boolean unused = this.f611a.f609b.d = false;
        i.f617a = i.a();
        this.f611a.f609b.f605a.finish();
    }
}
