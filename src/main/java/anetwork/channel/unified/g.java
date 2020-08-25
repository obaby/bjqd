package anetwork.channel.unified;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.monitor.b;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpUrl;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.cache.Cache;
import anetwork.channel.cache.CacheManager;
import anetwork.channel.config.NetworkConfigCenter;
import anetwork.channel.entity.c;
import anetwork.channel.interceptor.Callback;
import anetwork.channel.interceptor.Interceptor;
import anetwork.channel.interceptor.InterceptorManager;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
class g {

    /* renamed from: a  reason: collision with root package name */
    protected f f434a;

    public g(anetwork.channel.entity.g gVar, c cVar) {
        cVar.a(gVar.e);
        this.f434a = new f(gVar, cVar);
    }

    /* compiled from: Taobao */
    class a implements Interceptor.Chain {

        /* renamed from: b  reason: collision with root package name */
        private int f436b = 0;

        /* renamed from: c  reason: collision with root package name */
        private Request f437c = null;
        private Callback d = null;

        a(int i, Request request, Callback callback) {
            this.f436b = i;
            this.f437c = request;
            this.d = callback;
        }

        public Request request() {
            return this.f437c;
        }

        public Callback callback() {
            return this.d;
        }

        public Future proceed(Request request, Callback callback) {
            if (g.this.f434a.d.get()) {
                ALog.i("anet.UnifiedRequestTask", "request canneled or timeout in processing interceptor", request.getSeq(), new Object[0]);
                return null;
            } else if (this.f436b < InterceptorManager.getSize()) {
                return InterceptorManager.getInterceptor(this.f436b).intercept(new a(this.f436b + 1, request, callback));
            } else {
                g.this.f434a.f431a.a(request);
                g.this.f434a.f432b = callback;
                Cache cache = (!NetworkConfigCenter.isHttpCacheEnable() || "no-cache".equals(request.getHeaders().get("Cache-Control"))) ? null : CacheManager.getCache(g.this.f434a.f431a.g(), g.this.f434a.f431a.h());
                g.this.f434a.e = cache != null ? new a(g.this.f434a, cache) : new c(g.this.f434a, (Cache) null, (Cache.Entry) null);
                g.this.f434a.e.run();
                g.this.c();
                return null;
            }
        }
    }

    public Future a() {
        this.f434a.f431a.f415b.start = System.currentTimeMillis();
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.UnifiedRequestTask", "request", this.f434a.f433c, "Url", this.f434a.f431a.g());
        }
        ThreadPoolExecutorFactory.submitPriorityTask(new h(this), ThreadPoolExecutorFactory.Priority.HIGH);
        return new b(this);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.f434a.f = ThreadPoolExecutorFactory.submitScheduledTask(new i(this), (long) this.f434a.f431a.b(), TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.f434a.d.compareAndSet(false, true)) {
            HttpUrl f = this.f434a.f431a.f();
            ALog.e("anet.UnifiedRequestTask", "task cancelled", this.f434a.f433c, "URL", f.simpleUrlString());
            RequestStatistic requestStatistic = this.f434a.f431a.f415b;
            if (requestStatistic.isDone.compareAndSet(false, true)) {
                requestStatistic.ret = 2;
                requestStatistic.statusCode = ErrorConstant.ERROR_REQUEST_CANCEL;
                requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_CANCEL);
                requestStatistic.rspEnd = System.currentTimeMillis();
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_REQUEST_CANCEL, (String) null, requestStatistic, (Throwable) null));
                if (requestStatistic.recDataSize > OSSConstants.MIN_PART_SIZE_LIMIT) {
                    b.a().a(requestStatistic.sendStart, requestStatistic.rspEnd, requestStatistic.recDataSize);
                }
            }
            this.f434a.b();
            this.f434a.a();
            this.f434a.f432b.onFinish(new DefaultFinishEvent(ErrorConstant.ERROR_REQUEST_CANCEL, (String) null, requestStatistic));
        }
    }
}
