package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.request.Request;
import anet.channel.session.b;

/* compiled from: Taobao */
class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Request f234a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ d f235b;

    e(d dVar, Request request) {
        this.f235b = dVar;
        this.f234a = request;
    }

    public void run() {
        b.a a2 = b.a(this.f234a, (RequestCb) null);
        if (a2.f230a > 0) {
            this.f235b.notifyStatus(4, new anet.channel.entity.b(1));
        } else {
            this.f235b.handleCallbacks(256, new anet.channel.entity.b(256, a2.f230a, "Http connect fail"));
        }
    }
}
