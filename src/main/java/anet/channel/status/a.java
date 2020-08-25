package anet.channel.status;

import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import java.util.Iterator;

/* compiled from: Taobao */
final class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ NetworkStatusHelper.NetworkStatus f243a;

    a(NetworkStatusHelper.NetworkStatus networkStatus) {
        this.f243a = networkStatus;
    }

    public void run() {
        try {
            Iterator<NetworkStatusHelper.INetworkStatusChangeListener> it = NetworkStatusHelper.listeners.iterator();
            while (it.hasNext()) {
                NetworkStatusHelper.INetworkStatusChangeListener next = it.next();
                long currentTimeMillis = System.currentTimeMillis();
                next.onNetworkStatusChanged(this.f243a);
                if (System.currentTimeMillis() - currentTimeMillis > 500) {
                    ALog.e("awcn.NetworkStatusHelper", "call back cost too much time", (String) null, "listener", next);
                }
            }
        } catch (Exception unused) {
        }
    }
}
