package anet.channel;

import anet.channel.b.a;
import anetwork.channel.cache.CacheManager;

/* compiled from: Taobao */
final class h implements Runnable {
    h() {
    }

    public void run() {
        try {
            a aVar = new a();
            aVar.a();
            CacheManager.addCache(aVar, new i(this), 1);
        } catch (Exception unused) {
        }
    }
}
