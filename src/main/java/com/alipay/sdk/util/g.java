package com.alipay.sdk.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback;

final class g extends IRemoteServiceCallback.Stub {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f733a;

    public final boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    public final void payEnd(boolean z, String str) throws RemoteException {
    }

    g(e eVar) {
        this.f733a = eVar;
    }

    public final void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception unused) {
        }
        intent.setClassName(str, str2);
        if (this.f733a.f730a != null) {
            this.f733a.f730a.startActivity(intent);
        }
        this.f733a.f.b();
    }
}
