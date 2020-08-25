package anet.channel.d;

import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;

/* compiled from: Taobao */
final class d implements AppLifecycle.AppLifecycleListener {
    public void forground() {
    }

    d() {
    }

    public void background() {
        ALog.i("awcn.NetworkDetector", "background", (String) null, new Object[0]);
        a.f165c.lock();
        try {
            a.d.signal();
        } finally {
            a.f165c.unlock();
        }
    }
}
