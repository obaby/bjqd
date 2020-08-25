package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.entity.b;
import anet.channel.strategy.ConnEvent;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class f implements EventCb {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Session f184a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ SessionRequest f185b;

    f(SessionRequest sessionRequest, Session session) {
        this.f185b = sessionRequest;
        this.f184a = session;
    }

    public void onEvent(Session session, int i, b bVar) {
        ALog.d("awcn.SessionRequest", "Receive session event", (String) null, "eventType", Integer.valueOf(i));
        ConnEvent connEvent = new ConnEvent();
        if (i == 512) {
            connEvent.isSuccess = true;
        }
        StrategyCenter.getInstance().notifyConnEvent(this.f184a.getRealHost(), this.f184a.getConnStrategy(), connEvent);
    }
}
