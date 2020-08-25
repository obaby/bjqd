package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.entity.b;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f138a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ b f139b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Session f140c;

    a(Session session, int i, b bVar) {
        this.f140c = session;
        this.f138a = i;
        this.f139b = bVar;
    }

    public void run() {
        try {
            if (this.f140c.f122b != null) {
                for (EventCb next : this.f140c.f122b.keySet()) {
                    if (!(next == null || (this.f140c.f122b.get(next).intValue() & this.f138a) == 0)) {
                        try {
                            next.onEvent(this.f140c, this.f138a, this.f139b);
                        } catch (Exception e) {
                            ALog.e("awcn.Session", e.toString(), this.f140c.o, new Object[0]);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            ALog.e("awcn.Session", "handleCallbacks", this.f140c.o, e2, new Object[0]);
        }
    }
}
