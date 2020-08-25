package anet.channel.strategy;

import java.util.Comparator;

/* compiled from: Taobao */
final class n implements Comparator<StrategyCollection> {
    n() {
    }

    /* renamed from: a */
    public int compare(StrategyCollection strategyCollection, StrategyCollection strategyCollection2) {
        if (strategyCollection.f257c != strategyCollection2.f257c) {
            return (int) (strategyCollection.f257c - strategyCollection2.f257c);
        }
        return strategyCollection.f255a.compareTo(strategyCollection2.f255a);
    }
}
