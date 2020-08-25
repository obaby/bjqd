package anet.channel.status;

import android.content.Context;

/* compiled from: Taobao */
class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f247a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ NetworkStatusMonitor$1 f248b;

    c(NetworkStatusMonitor$1 networkStatusMonitor$1, Context context) {
        this.f248b = networkStatusMonitor$1;
        this.f247a = context;
    }

    public void run() {
        b.a(this.f247a);
    }
}
