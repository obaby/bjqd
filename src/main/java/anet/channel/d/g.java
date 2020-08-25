package anet.channel.d;

import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.k;

/* compiled from: Taobao */
final class g implements IConnStrategy {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k.e f170a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ConnProtocol f171b;

    public int getHeartbeat() {
        return 0;
    }

    public int getIpSource() {
        return 2;
    }

    public int getIpType() {
        return 1;
    }

    public int getRetryTimes() {
        return 0;
    }

    g(k.e eVar, ConnProtocol connProtocol) {
        this.f170a = eVar;
        this.f171b = connProtocol;
    }

    public String getIp() {
        return this.f170a.f318a;
    }

    public int getPort() {
        return this.f170a.f319b.f307a;
    }

    public ConnProtocol getProtocol() {
        return this.f171b;
    }

    public int getConnectionTimeout() {
        return this.f170a.f319b.f309c;
    }

    public int getReadTimeout() {
        return this.f170a.f319b.d;
    }
}
