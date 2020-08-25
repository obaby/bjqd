package anet.channel.d;

import anet.channel.AwcnConfig;
import anet.channel.strategy.IStrategyListener;
import anet.channel.strategy.k;
import anet.channel.util.ALog;

/* compiled from: Taobao */
final class c implements IStrategyListener {
    c() {
    }

    public void onStrategyUpdated(k.d dVar) {
        ALog.i("awcn.NetworkDetector", "onStrategyUpdated", (String) null, new Object[0]);
        if (AwcnConfig.isHorseRaceEnable() && dVar.f317c != null && dVar.f317c.length != 0) {
            if (a.f == null) {
                Thread unused = a.f = new Thread(a.g);
                a.f.setName("AWCN HR");
                a.f.start();
                ALog.i("awcn.NetworkDetector", "start horse race thread", (String) null, new Object[0]);
            }
            synchronized (a.f163a) {
                for (k.c cVar : dVar.f317c) {
                    a.f163a.put(cVar.f313a, cVar);
                }
            }
            a.f165c.lock();
            try {
                a.e.signal();
            } finally {
                a.f165c.unlock();
            }
        }
    }
}
