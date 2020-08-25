package anetwork.channel.unified;

import android.text.TextUtils;
import anet.channel.Config;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.NoAvailStrategyException;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ENV;
import anet.channel.entity.a;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.session.d;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.StringUtils;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.cache.Cache;
import anetwork.channel.config.NetworkConfigCenter;
import anetwork.channel.cookie.CookieManager;
import anetwork.channel.http.NetworkSdkSetting;
import anetwork.channel.util.RequestConstant;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Taobao */
class c implements IUnifiedTask {
    public static final String TAG = "anet.NetworkTask";

    /* renamed from: a  reason: collision with root package name */
    f f423a;

    /* renamed from: b  reason: collision with root package name */
    Cache f424b = null;

    /* renamed from: c  reason: collision with root package name */
    Cache.Entry f425c = null;
    ByteArrayOutputStream d = null;
    String e = DispatchConstants.OTHER;
    volatile Cancelable f = null;
    volatile boolean g = false;
    volatile AtomicBoolean h = null;
    int i = 0;
    int j = 0;

    c(f fVar, Cache cache, Cache.Entry entry) {
        this.f423a = fVar;
        this.h = fVar.d;
        this.f424b = cache;
        this.f425c = entry;
        this.e = fVar.f431a.h().get(HttpConstant.F_REFER);
    }

    public void cancel() {
        this.g = true;
        if (this.f != null) {
            this.f.cancel();
        }
    }

    public void run() {
        if (!this.g) {
            RequestStatistic requestStatistic = this.f423a.f431a.f415b;
            if (!NetworkStatusHelper.isConnected()) {
                if (ALog.isPrintLog(2)) {
                    ALog.i(TAG, "network unavailable", this.f423a.f433c, "NetworkStatus", NetworkStatusHelper.getStatus());
                }
                this.h.set(true);
                this.f423a.a();
                requestStatistic.isDone.set(true);
                requestStatistic.statusCode = ErrorConstant.ERROR_NO_NETWORK;
                requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_NO_NETWORK);
                requestStatistic.rspEnd = System.currentTimeMillis();
                this.f423a.f432b.onFinish(new DefaultFinishEvent(ErrorConstant.ERROR_NO_NETWORK, (String) null, requestStatistic));
            } else if (!NetworkConfigCenter.isBgRequestForbidden() || !GlobalAppRuntimeInfo.isAppBackground() || AppLifecycle.lastEnterBackgroundTime <= 0 || System.currentTimeMillis() - AppLifecycle.lastEnterBackgroundTime <= 60000 || NetworkConfigCenter.isUrlInWhiteList(this.f423a.f431a.f())) {
                if (ALog.isPrintLog(2)) {
                    ALog.i(TAG, "exec request", this.f423a.f433c, "retryTimes", Integer.valueOf(this.f423a.f431a.f414a));
                }
                try {
                    Session b2 = b();
                    if (b2 != null) {
                        a(b2, this.f423a.f431a.a());
                    }
                } catch (Exception e2) {
                    ALog.e(TAG, "send request failed.", this.f423a.f433c, e2, new Object[0]);
                }
            } else {
                this.h.set(true);
                this.f423a.a();
                if (ALog.isPrintLog(2)) {
                    ALog.i(TAG, "request forbidden in background", this.f423a.f433c, "url", this.f423a.f431a.f());
                }
                requestStatistic.isDone.set(true);
                requestStatistic.statusCode = ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG;
                requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG);
                requestStatistic.rspEnd = System.currentTimeMillis();
                this.f423a.f432b.onFinish(new DefaultFinishEvent(ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG, (String) null, requestStatistic));
                ExceptionStatistic exceptionStatistic = new ExceptionStatistic(ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG, (String) null, "rt");
                exceptionStatistic.host = this.f423a.f431a.f().host();
                exceptionStatistic.url = this.f423a.f431a.g();
                AppMonitor.getInstance().commitStat(exceptionStatistic);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0016, code lost:
        r0 = anet.channel.util.HttpUrl.parse(r4.urlString().replaceFirst(r4.host(), r0));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private anet.channel.util.HttpUrl a(anet.channel.util.HttpUrl r4) {
        /*
            r3 = this;
            anetwork.channel.unified.f r0 = r3.f423a
            anetwork.channel.entity.g r0 = r0.f431a
            java.util.Map r0 = r0.h()
            java.lang.String r1 = "x-host-cname"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0029
            java.lang.String r1 = r4.urlString()
            java.lang.String r2 = r4.host()
            java.lang.String r0 = r1.replaceFirst(r2, r0)
            anet.channel.util.HttpUrl r0 = anet.channel.util.HttpUrl.parse(r0)
            if (r0 == 0) goto L_0x0029
            r4 = r0
        L_0x0029:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.unified.c.a(anet.channel.util.HttpUrl):anet.channel.util.HttpUrl");
    }

    private SessionCenter a() {
        String a2 = this.f423a.f431a.a(RequestConstant.APPKEY);
        if (TextUtils.isEmpty(a2)) {
            return SessionCenter.getInstance();
        }
        ENV env = ENV.ONLINE;
        String a3 = this.f423a.f431a.a(RequestConstant.ENVIRONMENT);
        if (RequestConstant.ENV_PRE.equalsIgnoreCase(a3)) {
            env = ENV.PREPARE;
        } else if (RequestConstant.ENV_TEST.equalsIgnoreCase(a3)) {
            env = ENV.TEST;
        }
        if (env != NetworkSdkSetting.CURRENT_ENV) {
            NetworkSdkSetting.CURRENT_ENV = env;
            SessionCenter.switchEnvironment(env);
        }
        Config config = Config.getConfig(a2, env);
        if (config == null) {
            config = new Config.Builder().setAppkey(a2).setEnv(env).setAuthCode(this.f423a.f431a.a(RequestConstant.AUTH_CODE)).build();
        }
        return SessionCenter.getInstance(config);
    }

    private Session b() {
        Session session;
        SessionCenter a2 = a();
        HttpUrl f2 = this.f423a.f431a.f();
        boolean containsNonDefaultPort = f2.containsNonDefaultPort();
        RequestStatistic requestStatistic = this.f423a.f431a.f415b;
        if (this.f423a.f431a.f != 1 || !NetworkConfigCenter.isSpdyEnabled() || this.f423a.f431a.f414a != 0 || containsNonDefaultPort) {
            session = null;
        } else {
            HttpUrl a3 = a(f2);
            try {
                session = a2.getThrowsException(a3, anet.channel.entity.c.f181a, 0);
            } catch (NoAvailStrategyException unused) {
                return a((Session) null, a2, f2, containsNonDefaultPort);
            } catch (Exception unused2) {
                session = null;
            }
            if (session == null) {
                ThreadPoolExecutorFactory.submitPriorityTask(new d(this, a2, a3, requestStatistic, f2, containsNonDefaultPort), ThreadPoolExecutorFactory.Priority.NORMAL);
                return null;
            }
            requestStatistic.spdyRequestSend = true;
        }
        return a(session, a2, f2, containsNonDefaultPort);
    }

    /* access modifiers changed from: private */
    public Session a(d dVar, SessionCenter sessionCenter, HttpUrl httpUrl, boolean z) {
        RequestStatistic requestStatistic = this.f423a.f431a.f415b;
        if (dVar == null && this.f423a.f431a.e() && !z && !NetworkStatusHelper.isProxy()) {
            dVar = sessionCenter.get(httpUrl, anet.channel.entity.c.f182b, 0);
        }
        if (dVar == null) {
            ALog.i(TAG, "create HttpSession with local DNS", this.f423a.f433c, new Object[0]);
            dVar = new d(GlobalAppRuntimeInfo.getContext(), new a(StringUtils.concatString(httpUrl.scheme(), HttpConstant.SCHEME_SPLIT, httpUrl.host()), this.f423a.f433c, (IConnStrategy) null));
        }
        if (requestStatistic.spdyRequestSend) {
            requestStatistic.degraded = 1;
        }
        ALog.i(TAG, "tryGetSession", this.f423a.f433c, "Session", dVar);
        return dVar;
    }

    /* access modifiers changed from: private */
    public void a(Session session, Request request) {
        if (session != null && !this.g) {
            Request.Builder builder = null;
            if (this.f423a.f431a.i()) {
                String cookie = CookieManager.getCookie(this.f423a.f431a.g());
                if (!TextUtils.isEmpty(cookie)) {
                    builder = request.newBuilder();
                    String str = request.getHeaders().get(HttpConstant.COOKIE);
                    if (!TextUtils.isEmpty(str)) {
                        cookie = StringUtils.concatString(str, "; ", cookie);
                    }
                    builder.addHeader(HttpConstant.COOKIE, cookie);
                }
            }
            if (this.f425c != null) {
                if (builder == null) {
                    builder = request.newBuilder();
                }
                if (this.f425c.etag != null) {
                    builder.addHeader("If-None-Match", this.f425c.etag);
                }
                if (this.f425c.lastModified > 0) {
                    builder.addHeader("If-Modified-Since", anetwork.channel.cache.a.a(this.f425c.lastModified));
                }
            }
            if (builder != null) {
                request = builder.build();
            }
            this.f423a.f431a.f415b.reqStart = System.currentTimeMillis();
            this.f = session.request(request, new e(this, request));
        }
    }
}
