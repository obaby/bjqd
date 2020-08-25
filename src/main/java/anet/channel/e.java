package anet.channel;

import anet.channel.SessionRequest;
import anet.channel.entity.EventCb;
import anet.channel.entity.b;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class e implements EventCb {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SessionRequest.IConnCb f172a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f173b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ SessionRequest f174c;

    e(SessionRequest sessionRequest, SessionRequest.IConnCb iConnCb, long j) {
        this.f174c = sessionRequest;
        this.f172a = iConnCb;
        this.f173b = j;
    }

    public void onEvent(Session session, int i, b bVar) {
        String str;
        Session session2 = session;
        int i2 = i;
        b bVar2 = bVar;
        if (session2 != null) {
            int i3 = bVar2 == null ? 0 : bVar2.f179b;
            if (bVar2 == null) {
                str = "";
            } else {
                str = bVar2.f180c;
            }
            if (i2 == 2) {
                ALog.d("awcn.SessionRequest", (String) null, session2 != null ? session2.o : null, "Session", session2, "EventType", Integer.valueOf(i), "Event", bVar2);
                this.f174c.a(session2, i3, str);
                if (this.f174c.f132c.c(this.f174c, session2)) {
                    this.f172a.onDisConnect(session2, this.f173b, i2);
                } else {
                    this.f172a.onFailed(session, this.f173b, i, i3);
                }
            } else if (i2 == 256) {
                ALog.d("awcn.SessionRequest", (String) null, session2 != null ? session2.o : null, "Session", session2, "EventType", Integer.valueOf(i), "Event", bVar2);
                this.f174c.a(session2, i3, str);
                this.f172a.onFailed(session, this.f173b, i, i3);
            } else if (i2 == 512) {
                ALog.d("awcn.SessionRequest", (String) null, session2 != null ? session2.o : null, "Session", session2, "EventType", Integer.valueOf(i), "Event", bVar2);
                this.f174c.a(session2, 0, (String) null);
                this.f172a.onSuccess(session2, this.f173b);
            }
        }
    }
}
