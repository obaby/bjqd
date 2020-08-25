package anet.channel.strategy;

import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.strategy.k;
import anet.channel.util.ALog;
import com.alipay.sdk.cons.c;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* compiled from: Taobao */
class StrategyCollection implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    String f255a;

    /* renamed from: b  reason: collision with root package name */
    StrategyList f256b = null;

    /* renamed from: c  reason: collision with root package name */
    volatile long f257c = 0;
    volatile String d = null;
    boolean e = false;
    private transient long f = 0;

    public StrategyCollection() {
    }

    protected StrategyCollection(String str) {
        this.f255a = str;
        this.e = DispatchConstants.isAmdcServerDomain(str);
    }

    public void checkInit() {
        if (System.currentTimeMillis() - this.f257c > 259200000) {
            this.f256b = null;
        } else if (this.f256b != null) {
            this.f256b.checkInit();
        }
    }

    public synchronized List<IConnStrategy> queryStrategyList() {
        if (this.f256b == null) {
            return Collections.EMPTY_LIST;
        }
        return this.f256b.getStrategyList();
    }

    public synchronized void notifyConnEvent(IConnStrategy iConnStrategy, ConnEvent connEvent) {
        if (this.f256b != null) {
            this.f256b.notifyConnEvent(iConnStrategy, connEvent);
            if (!connEvent.isSuccess && this.f256b.shouldRefresh()) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.f > 60000) {
                    StrategyCenter.getInstance().forceRefreshStrategy(this.f255a);
                    this.f = currentTimeMillis;
                }
            }
        }
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.f257c;
    }

    public synchronized void update(k.b bVar) {
        this.f257c = System.currentTimeMillis() + (((long) bVar.f311b) * 1000);
        if (!bVar.f310a.equalsIgnoreCase(this.f255a)) {
            ALog.e("StrategyCollection", "update error!", (String) null, c.f, this.f255a, "dnsInfo.host", bVar.f310a);
            return;
        }
        this.d = bVar.d;
        if (bVar.f == null || bVar.f.length == 0 || bVar.h == null || bVar.h.length == 0) {
            if (bVar.i != null) {
                if (bVar.i.length == 0) {
                }
            }
            this.f256b = null;
            return;
        }
        if (this.f256b == null) {
            this.f256b = new StrategyList();
        }
        this.f256b.update(bVar);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("\nStrategyList = ");
        sb.append(this.f257c);
        if (this.f256b != null) {
            sb.append(this.f256b.toString());
        } else if (this.d != null) {
            sb.append('[');
            sb.append(this.f255a);
            sb.append("=>");
            sb.append(this.d);
            sb.append(']');
        } else {
            sb.append("[]");
        }
        return sb.toString();
    }
}
