package anet.channel.d;

import anet.channel.AwcnConfig;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.strategy.k;
import anet.channel.util.ALog;
import java.util.Map;

/* compiled from: Taobao */
final class b implements Runnable {
    b() {
    }

    public void run() {
        Map.Entry entry;
        ALog.e("awcn.NetworkDetector", "network detect thread start", (String) null, new Object[0]);
        while (true) {
            try {
                a.f165c.lock();
                if (!GlobalAppRuntimeInfo.isAppBackground()) {
                    a.d.await();
                }
                if (a.f163a.isEmpty()) {
                    a.e.await();
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                a.f165c.unlock();
                throw th;
            }
            a.f165c.unlock();
            while (GlobalAppRuntimeInfo.isAppBackground()) {
                synchronized (a.f163a) {
                    if (!AwcnConfig.isHorseRaceEnable()) {
                        a.f163a.clear();
                        entry = null;
                    } else {
                        entry = a.f163a.pollFirstEntry();
                    }
                }
                if (entry == null) {
                    break;
                }
                try {
                    a.b((k.c) entry.getValue());
                } catch (Exception e) {
                    ALog.e("awcn.NetworkDetector", "start hr task failed", (String) null, e, new Object[0]);
                }
            }
        }
    }
}
