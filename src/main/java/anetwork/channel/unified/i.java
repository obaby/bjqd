package anetwork.channel.unified;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.DefaultFinishEvent;

/* compiled from: Taobao */
class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f439a;

    i(g gVar) {
        this.f439a = gVar;
    }

    public void run() {
        if (this.f439a.f434a.d.compareAndSet(false, true)) {
            RequestStatistic requestStatistic = this.f439a.f434a.f431a.f415b;
            if (requestStatistic.isDone.compareAndSet(false, true)) {
                requestStatistic.statusCode = ErrorConstant.ERROR_REQUEST_TIME_OUT;
                requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_TIME_OUT);
                requestStatistic.rspEnd = System.currentTimeMillis();
                ALog.e("anet.UnifiedRequestTask", "task time out", this.f439a.f434a.f433c, "rs", requestStatistic);
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_REQUEST_TIME_OUT, (String) null, requestStatistic, (Throwable) null));
            }
            this.f439a.f434a.b();
            this.f439a.f434a.f432b.onFinish(new DefaultFinishEvent(ErrorConstant.ERROR_REQUEST_TIME_OUT, (String) null, requestStatistic));
        }
    }
}
