package anetwork.channel.unified;

import anet.channel.bytes.ByteArray;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.cache.Cache;

/* compiled from: Taobao */
public class a implements IUnifiedTask {

    /* renamed from: a  reason: collision with root package name */
    private f f418a = null;

    /* renamed from: b  reason: collision with root package name */
    private Cache f419b = null;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f420c = false;

    public a(f fVar, Cache cache) {
        this.f418a = fVar;
        this.f419b = cache;
    }

    public void cancel() {
        this.f420c = true;
        this.f418a.f431a.f415b.ret = 2;
    }

    public void run() {
        if (!this.f420c) {
            RequestStatistic requestStatistic = this.f418a.f431a.f415b;
            if (this.f419b != null) {
                String g = this.f418a.f431a.g();
                long currentTimeMillis = System.currentTimeMillis();
                Cache.Entry entry = this.f419b.get(g);
                long currentTimeMillis2 = System.currentTimeMillis();
                requestStatistic.cacheTime = currentTimeMillis2 - currentTimeMillis;
                if (ALog.isPrintLog(2)) {
                    String str = this.f418a.f433c;
                    Object[] objArr = new Object[8];
                    objArr[0] = "hit";
                    objArr[1] = Boolean.valueOf(entry != null);
                    objArr[2] = "cost";
                    objArr[3] = Long.valueOf(requestStatistic.cacheTime);
                    objArr[4] = "length";
                    objArr[5] = Integer.valueOf(entry != null ? entry.data.length : 0);
                    objArr[6] = "key";
                    objArr[7] = g;
                    ALog.i("anet.CacheTask", "read cache", str, objArr);
                }
                if (entry == null || !entry.isFresh()) {
                    if (!this.f420c) {
                        c cVar = new c(this.f418a, this.f419b, entry);
                        this.f418a.e = cVar;
                        cVar.run();
                    }
                } else if (this.f418a.d.compareAndSet(false, true)) {
                    this.f418a.a();
                    requestStatistic.ret = 1;
                    requestStatistic.statusCode = 200;
                    requestStatistic.msg = "SUCCESS";
                    requestStatistic.protocolType = "cache";
                    requestStatistic.rspEnd = currentTimeMillis2;
                    requestStatistic.processTime = currentTimeMillis2 - requestStatistic.start;
                    if (ALog.isPrintLog(2)) {
                        ALog.i("anet.CacheTask", "hit fresh cache", this.f418a.f433c, "URL", this.f418a.f431a.f().urlString());
                    }
                    this.f418a.f432b.onResponseCode(200, entry.responseHeaders);
                    this.f418a.f432b.onDataReceiveSize(1, entry.data.length, ByteArray.wrap(entry.data));
                    this.f418a.f432b.onFinish(new DefaultFinishEvent(200, "SUCCESS", requestStatistic));
                }
            }
        }
    }
}
