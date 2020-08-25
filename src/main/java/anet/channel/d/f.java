package anet.channel.d;

import anet.channel.RequestCb;
import anet.channel.bytes.ByteArray;
import anet.channel.statist.HorseRaceStat;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
class f implements RequestCb {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f169a;

    public void onDataReceive(ByteArray byteArray, boolean z) {
    }

    f(e eVar) {
        this.f169a = eVar;
    }

    public void onResponseCode(int i, Map<String, List<String>> map) {
        this.f169a.f166a.reqErrorCode = i;
    }

    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        int i2 = 0;
        ALog.i("awcn.NetworkDetector", "LongLinkTask request finish", this.f169a.f168c, "statusCode", Integer.valueOf(i), "msg", str);
        if (this.f169a.f166a.reqErrorCode == 0) {
            this.f169a.f166a.reqErrorCode = i;
        } else {
            HorseRaceStat horseRaceStat = this.f169a.f166a;
            if (this.f169a.f166a.reqErrorCode == 200) {
                i2 = 1;
            }
            horseRaceStat.reqRet = i2;
        }
        this.f169a.f166a.reqTime = (System.currentTimeMillis() - this.f169a.f167b) + this.f169a.f166a.connTime;
        synchronized (this.f169a.f166a) {
            this.f169a.f166a.notify();
        }
    }
}
