package anetwork.channel.entity;

import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.ParcelableNetworkListener;
import anetwork.channel.aidl.adapter.ParcelableInputStreamImpl;
import anetwork.channel.interceptor.Callback;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
public class c implements Callback {

    /* renamed from: a  reason: collision with root package name */
    private ParcelableNetworkListener f402a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public String f403b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public ParcelableInputStreamImpl f404c = null;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public g e = null;

    public c(ParcelableNetworkListener parcelableNetworkListener, g gVar) {
        this.f402a = parcelableNetworkListener;
        this.e = gVar;
        if (parcelableNetworkListener != null) {
            try {
                if ((parcelableNetworkListener.getListenerState() & 8) != 0) {
                    this.d = true;
                }
            } catch (RemoteException unused) {
            }
        }
    }

    public void onResponseCode(int i, Map<String, List<String>> map) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.Repeater", "[onResponseCode]", this.f403b, new Object[0]);
        }
        if (this.f402a != null) {
            a((Runnable) new d(this, this.f402a, i, map));
        }
    }

    public void onDataReceiveSize(int i, int i2, ByteArray byteArray) {
        if (this.f402a != null) {
            a((Runnable) new e(this, i, byteArray, i2, this.f402a));
        }
    }

    public void onFinish(DefaultFinishEvent defaultFinishEvent) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.Repeater", "[onFinish] ", this.f403b, new Object[0]);
        }
        if (this.f402a != null) {
            a((Runnable) new f(this, defaultFinishEvent, this.f402a));
        }
        this.f402a = null;
    }

    private void a(Runnable runnable) {
        if (this.e.c()) {
            runnable.run();
        } else {
            a.a(this.f403b != null ? this.f403b.hashCode() : hashCode(), runnable);
        }
    }

    public void a(String str) {
        this.f403b = str;
    }
}
