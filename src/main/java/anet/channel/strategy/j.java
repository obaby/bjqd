package anet.channel.strategy;

import java.util.Comparator;

/* compiled from: Taobao */
class j implements Comparator<IPConnStrategy> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StrategyList f306a;

    j(StrategyList strategyList) {
        this.f306a = strategyList;
    }

    /* renamed from: a */
    public int compare(IPConnStrategy iPConnStrategy, IPConnStrategy iPConnStrategy2) {
        int a2 = ((ConnHistoryItem) this.f306a.f265b.get(Integer.valueOf(iPConnStrategy.getUniqueId()))).a();
        int a3 = ((ConnHistoryItem) this.f306a.f265b.get(Integer.valueOf(iPConnStrategy2.getUniqueId()))).a();
        if (a2 != a3) {
            return a2 - a3;
        }
        if (iPConnStrategy.f252a != iPConnStrategy2.f252a) {
            return iPConnStrategy.f252a - iPConnStrategy2.f252a;
        }
        return iPConnStrategy.protocol.isHttp - iPConnStrategy2.protocol.isHttp;
    }
}
