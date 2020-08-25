package anet.channel;

import anet.channel.SessionRequest;
import anet.channel.util.e;

/* compiled from: Taobao */
class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Session f186a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ SessionRequest.a f187b;

    g(SessionRequest.a aVar, Session session) {
        this.f187b = aVar;
        this.f186a = session;
    }

    public void run() {
        try {
            SessionRequest.this.a(this.f187b.f135c, this.f186a.getConnType().getType(), e.a(SessionRequest.this.f131b.f127c));
        } catch (Exception unused) {
        }
    }
}
