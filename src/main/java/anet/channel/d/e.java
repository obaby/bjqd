package anet.channel.d;

import anet.channel.Session;
import anet.channel.entity.EventCb;
import anet.channel.entity.b;
import anet.channel.request.Request;
import anet.channel.session.TnetSpdySession;
import anet.channel.statist.HorseRaceStat;
import anet.channel.strategy.k;
import anet.channel.util.ALog;
import anet.channel.util.HttpUrl;

/* compiled from: Taobao */
final class e implements EventCb {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ HorseRaceStat f166a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f167b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f168c;
    final /* synthetic */ k.e d;
    final /* synthetic */ TnetSpdySession e;

    e(HorseRaceStat horseRaceStat, long j, String str, k.e eVar, TnetSpdySession tnetSpdySession) {
        this.f166a = horseRaceStat;
        this.f167b = j;
        this.f168c = str;
        this.d = eVar;
        this.e = tnetSpdySession;
    }

    public void onEvent(Session session, int i, b bVar) {
        if (this.f166a.connTime == 0) {
            this.f166a.connTime = System.currentTimeMillis() - this.f167b;
            if (i == 1) {
                ALog.i("awcn.NetworkDetector", "tnetSpdySession connect success", this.f168c, new Object[0]);
                this.f166a.connRet = 1;
                HttpUrl parse = HttpUrl.parse(session.getHost() + this.d.f320c);
                if (parse != null) {
                    this.e.request(new Request.Builder().setUrl(parse).setReadTimeout(this.d.f319b.d).setRedirectEnable(false).setSeq(this.f168c).build(), new f(this));
                    return;
                }
                return;
            }
            this.f166a.connErrorCode = bVar.f179b;
            synchronized (this.f166a) {
                this.f166a.notify();
            }
        }
    }
}
