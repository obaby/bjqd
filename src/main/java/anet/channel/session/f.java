package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.request.Request;
import anet.channel.statist.RequestStatistic;

/* compiled from: Taobao */
class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Request f236a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ RequestCb f237b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ RequestStatistic f238c;
    final /* synthetic */ d d;

    f(d dVar, Request request, RequestCb requestCb, RequestStatistic requestStatistic) {
        this.d = dVar;
        this.f236a = request;
        this.f237b = requestCb;
        this.f238c = requestStatistic;
    }

    public void run() {
        this.f236a.f211a.sendBeforeTime = System.currentTimeMillis() - this.f236a.f211a.reqStart;
        b.a(this.f236a, (RequestCb) new g(this));
    }
}
