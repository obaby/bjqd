package anetwork.channel.entity;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.ParcelableNetworkListener;
import anetwork.channel.stat.NetworkStat;

/* compiled from: Taobao */
class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DefaultFinishEvent f411a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ParcelableNetworkListener f412b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ c f413c;

    f(c cVar, DefaultFinishEvent defaultFinishEvent, ParcelableNetworkListener parcelableNetworkListener) {
        this.f413c = cVar;
        this.f411a = defaultFinishEvent;
        this.f412b = parcelableNetworkListener;
    }

    public void run() {
        if (this.f411a != null) {
            this.f411a.setContext((Object) null);
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            RequestStatistic requestStatistic = this.f411a.rs;
            if (requestStatistic != null) {
                requestStatistic.lastProcessTime = currentTimeMillis - requestStatistic.rspEnd;
                requestStatistic.oneWayTime = requestStatistic.retryCostTime + (currentTimeMillis - requestStatistic.start);
                this.f411a.getStatisticData().filledBy(requestStatistic);
            }
            this.f412b.onFinished(this.f411a);
            if (this.f413c.f404c != null) {
                this.f413c.f404c.writeEnd();
            }
            if (requestStatistic != null) {
                requestStatistic.callbackTime = System.currentTimeMillis() - currentTimeMillis;
                if (ALog.isPrintLog(2)) {
                    ALog.i("anet.Repeater", requestStatistic.toString(), this.f413c.f403b, new Object[0]);
                }
                AppMonitor.getInstance().commitStat(requestStatistic);
                NetworkStat.getNetworkStat().put(this.f413c.e.g(), this.f411a.getStatisticData());
            }
        } catch (Throwable unused) {
        }
    }
}
