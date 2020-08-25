package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class h extends w {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f524a;

    public h(Context context) {
        this.f524a = context;
    }

    public final <T> T a(Class<T> cls, aa aaVar) {
        return new x(new i(this, aaVar)).a(cls);
    }
}
