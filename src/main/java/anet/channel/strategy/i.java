package anet.channel.strategy;

import anet.channel.strategy.StrategyList;
import anet.channel.strategy.k;

/* compiled from: Taobao */
class i implements StrategyList.Predicate<IPConnStrategy> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k.a f303a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f304b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ConnProtocol f305c;
    final /* synthetic */ StrategyList d;

    i(StrategyList strategyList, k.a aVar, String str, ConnProtocol connProtocol) {
        this.d = strategyList;
        this.f303a = aVar;
        this.f304b = str;
        this.f305c = connProtocol;
    }

    /* renamed from: a */
    public boolean apply(IPConnStrategy iPConnStrategy) {
        return iPConnStrategy.getPort() == this.f303a.f307a && iPConnStrategy.getIp().equals(this.f304b) && iPConnStrategy.protocol.equals(this.f305c);
    }
}
