package com.alipay.sdk.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class d extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f755a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    d(a aVar, Looper looper) {
        super(looper);
        this.f755a = aVar;
    }

    public final void dispatchMessage(Message message) {
        this.f755a.b();
    }
}
