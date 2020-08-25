package anet.channel.session;

import anet.channel.IAuth;
import anet.channel.entity.b;
import anet.channel.statist.SessionStatistic;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class i implements IAuth.AuthCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TnetSpdySession f241a;

    i(TnetSpdySession tnetSpdySession) {
        this.f241a = tnetSpdySession;
    }

    public void onAuthSuccess() {
        this.f241a.notifyStatus(4, (b) null);
        this.f241a.y = System.currentTimeMillis();
        if (this.f241a.C != null) {
            this.f241a.C.start(this.f241a);
        }
        this.f241a.p.ret = 1;
        ALog.d("awcn.TnetSpdySession", "spdyOnStreamResponse", this.f241a.o, "authTime", Long.valueOf(this.f241a.p.authTime));
        if (this.f241a.z > 0) {
            this.f241a.p.authTime = System.currentTimeMillis() - this.f241a.z;
        }
    }

    public void onAuthFail(int i, String str) {
        this.f241a.notifyStatus(5, (b) null);
        if (this.f241a.p != null) {
            SessionStatistic sessionStatistic = this.f241a.p;
            sessionStatistic.closeReason = "Accs_Auth_Fail:" + i;
            this.f241a.p.errorCode = (long) i;
        }
        this.f241a.close();
    }
}
