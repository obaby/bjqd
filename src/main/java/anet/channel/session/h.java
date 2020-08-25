package anet.channel.session;

import anet.channel.entity.b;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TnetSpdySession f240a;

    h(TnetSpdySession tnetSpdySession) {
        this.f240a = tnetSpdySession;
    }

    public void run() {
        if (this.f240a.x) {
            ALog.e("awcn.TnetSpdySession", "send msg time out!", this.f240a.o, "pingUnRcv:", Boolean.valueOf(this.f240a.x));
            try {
                this.f240a.handleCallbacks(2048, (b) null);
                if (this.f240a.p != null) {
                    this.f240a.p.closeReason = "ping time out";
                }
                this.f240a.close();
            } catch (Exception unused) {
            }
        }
    }
}
