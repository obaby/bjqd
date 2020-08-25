package anetwork.channel.unified;

import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.entity.c;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.HttpUrl;

/* compiled from: Taobao */
class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SessionCenter f426a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ HttpUrl f427b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ RequestStatistic f428c;
    final /* synthetic */ HttpUrl d;
    final /* synthetic */ boolean e;
    final /* synthetic */ c f;

    d(c cVar, SessionCenter sessionCenter, HttpUrl httpUrl, RequestStatistic requestStatistic, HttpUrl httpUrl2, boolean z) {
        this.f = cVar;
        this.f426a = sessionCenter;
        this.f427b = httpUrl;
        this.f428c = requestStatistic;
        this.d = httpUrl2;
        this.e = z;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        Session session = this.f426a.get(this.f427b, c.f181a, 3000);
        this.f428c.connWaitTime = System.currentTimeMillis() - currentTimeMillis;
        this.f428c.spdyRequestSend = session != null;
        this.f.a(this.f.a(session, this.f426a, this.d, this.e), this.f.f423a.f431a.a());
    }
}
