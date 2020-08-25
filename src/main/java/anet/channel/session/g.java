package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.bytes.ByteArray;
import anet.channel.entity.b;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.HttpHelper;
import java.util.List;
import java.util.Map;

/* compiled from: Taobao */
class g implements RequestCb {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f239a;

    g(f fVar) {
        this.f239a = fVar;
    }

    public void onResponseCode(int i, Map<String, List<String>> map) {
        ALog.i("awcn.HttpSession", "", this.f239a.f236a.getSeq(), "httpStatusCode", Integer.valueOf(i));
        ALog.i("awcn.HttpSession", "", this.f239a.f236a.getSeq(), "response headers", map);
        this.f239a.f237b.onResponseCode(i, map);
        this.f239a.f238c.serverRT = HttpHelper.parseServerRT(map);
        this.f239a.d.handleResponseCode(this.f239a.f236a, i);
        this.f239a.d.handleResponseHeaders(this.f239a.f236a, map);
    }

    public void onDataReceive(ByteArray byteArray, boolean z) {
        this.f239a.f237b.onDataReceive(byteArray, z);
    }

    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        if (i <= 0) {
            this.f239a.d.handleCallbacks(2, new b(2, 0, "Http connect fail"));
        }
        this.f239a.f237b.onFinish(i, str, requestStatistic);
    }
}
