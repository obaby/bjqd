package anetwork.channel.entity;

import android.os.RemoteException;
import anetwork.channel.aidl.ParcelableHeader;
import anetwork.channel.aidl.ParcelableNetworkListener;
import java.util.Map;

/* compiled from: Taobao */
class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ParcelableNetworkListener f405a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f406b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Map f407c;
    final /* synthetic */ c d;

    d(c cVar, ParcelableNetworkListener parcelableNetworkListener, int i, Map map) {
        this.d = cVar;
        this.f405a = parcelableNetworkListener;
        this.f406b = i;
        this.f407c = map;
    }

    public void run() {
        try {
            this.f405a.onResponseCode(this.f406b, new ParcelableHeader(this.f406b, this.f407c));
        } catch (RemoteException unused) {
        }
    }
}
