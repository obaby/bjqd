package anet.channel.monitor;

import anet.channel.status.NetworkStatusHelper;

/* compiled from: Taobao */
class c implements NetworkStatusHelper.INetworkStatusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f201a;

    c(b bVar) {
        this.f201a = bVar;
    }

    public void onNetworkStatusChanged(NetworkStatusHelper.NetworkStatus networkStatus) {
        this.f201a.n.a();
        b.f = 0;
        this.f201a.d();
    }
}
