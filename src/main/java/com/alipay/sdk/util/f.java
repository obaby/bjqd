package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay;

final class f implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f732a;

    f(e eVar) {
        this.f732a = eVar;
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        IAlixPay unused = this.f732a.f731c = null;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f732a.d) {
            IAlixPay unused = this.f732a.f731c = IAlixPay.Stub.asInterface(iBinder);
            this.f732a.d.notify();
        }
    }
}
