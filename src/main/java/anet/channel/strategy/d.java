package anet.channel.strategy;

import anet.channel.util.ALog;
import java.io.File;

/* compiled from: Taobao */
class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f279a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ StrategyInfoHolder f280b;

    d(StrategyInfoHolder strategyInfoHolder, String str) {
        this.f280b = strategyInfoHolder;
        this.f279a = str;
    }

    public void run() {
        try {
            ALog.i("awcn.StrategyInfoHolder", "start loading strategy files", (String) null, new Object[0]);
            long currentTimeMillis = System.currentTimeMillis();
            File[] b2 = l.b();
            if (b2 != null) {
                int i = 0;
                for (int i2 = 0; i2 < b2.length && i < 2; i2++) {
                    File file = b2[i2];
                    if (!file.isDirectory()) {
                        String name = file.getName();
                        if (!name.equals(this.f279a) && !name.startsWith("StrategyConfig")) {
                            this.f280b.a(name, false);
                            i++;
                        }
                    }
                }
                ALog.i("awcn.StrategyInfoHolder", "end loading strategy files", (String) null, "total cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception unused) {
        }
    }
}
