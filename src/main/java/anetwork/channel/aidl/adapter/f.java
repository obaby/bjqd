package anetwork.channel.aidl.adapter;

import anet.channel.util.ALog;

/* compiled from: Taobao */
final class f implements Runnable {
    f() {
    }

    public void run() {
        if (d.f375c) {
            d.f375c = false;
            ALog.w("anet.RemoteGetter", "binding service timeout. reset status!", (String) null, new Object[0]);
        }
    }
}
