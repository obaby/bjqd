package anet.channel.strategy;

import anet.channel.statist.StrategyStatObject;
import anet.channel.strategy.StrategyInfoHolder;
import java.io.Serializable;
import java.util.Map;

/* compiled from: Taobao */
class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Map.Entry f297a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ StrategyInfoHolder.LruStrategyMap f298b;

    f(StrategyInfoHolder.LruStrategyMap lruStrategyMap, Map.Entry entry) {
        this.f298b = lruStrategyMap;
        this.f297a = entry;
    }

    public void run() {
        StrategyTable strategyTable = (StrategyTable) this.f297a.getValue();
        if (strategyTable.f269c) {
            StrategyStatObject strategyStatObject = new StrategyStatObject(1);
            strategyStatObject.writeStrategyFileId = strategyTable.f267a;
            l.a((Serializable) this.f297a.getValue(), strategyTable.f267a, strategyStatObject);
            strategyTable.f269c = false;
        }
    }
}
