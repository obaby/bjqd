package anet.channel;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;
import anet.channel.Config;
import anet.channel.entity.ConnType;
import anet.channel.entity.ENV;
import anet.channel.entity.c;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.IStrategyListener;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.dispatch.IAmdcSign;
import anet.channel.strategy.k;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.StringUtils;
import anet.channel.util.Utils;
import anet.channel.util.e;
import anetwork.channel.util.RequestConstant;
import com.stub.StubApp;
import java.net.ConnectException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

/* compiled from: Taobao */
public class SessionCenter {
    public static final String TAG = "awcn.SessionCenter";

    /* renamed from: a  reason: collision with root package name */
    static Map<Config, SessionCenter> f125a = new HashMap();
    /* access modifiers changed from: private */
    public static boolean j = false;

    /* renamed from: b  reason: collision with root package name */
    Context f126b = GlobalAppRuntimeInfo.getContext();

    /* renamed from: c  reason: collision with root package name */
    String f127c;
    Config d;
    final d e = new d();
    final LruCache<String, SessionRequest> f = new LruCache<>(32);
    final b g = new b();
    final AccsSessionManager h;
    final a i = new a(this, (c) null);

    public static synchronized void init(Context context) {
        synchronized (SessionCenter.class) {
            if (context != null) {
                GlobalAppRuntimeInfo.setContext(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                if (!j) {
                    f125a.put(Config.DEFAULT_CONFIG, new SessionCenter(Config.DEFAULT_CONFIG));
                    AppLifecycle.initialize();
                    StrategyCenter.getInstance().initialize(GlobalAppRuntimeInfo.getContext());
                    if (GlobalAppRuntimeInfo.isTargetProcess()) {
                        anet.channel.d.a.a();
                    }
                    j = true;
                }
            } else {
                ALog.e(TAG, "context is null!", (String) null, new Object[0]);
                throw new NullPointerException("init failed. context is null");
            }
        }
    }

    @Deprecated
    public static synchronized void init(Context context, String str) {
        synchronized (SessionCenter.class) {
            init(context, str, GlobalAppRuntimeInfo.getEnv());
        }
    }

    public static synchronized void init(Context context, String str, ENV env) {
        synchronized (SessionCenter.class) {
            if (context != null) {
                Config config = Config.getConfig(str, env);
                if (config == null) {
                    config = new Config.Builder().setAppkey(str).setEnv(env).build();
                }
                init(context, config);
            } else {
                ALog.e(TAG, "context is null!", (String) null, new Object[0]);
                throw new NullPointerException("init failed. context is null");
            }
        }
    }

    public static synchronized void init(Context context, Config config) {
        synchronized (SessionCenter.class) {
            if (context == null) {
                ALog.e(TAG, "context is null!", (String) null, new Object[0]);
                throw new NullPointerException("init failed. context is null");
            } else if (config != null) {
                init(context);
                if (!f125a.containsKey(config)) {
                    f125a.put(config, new SessionCenter(config));
                }
            } else {
                ALog.e(TAG, "paramter config is null!", (String) null, new Object[0]);
                throw new NullPointerException("init failed. config is null");
            }
        }
    }

    private SessionCenter(Config config) {
        this.d = config;
        this.f127c = config.getAppkey();
        this.i.a();
        this.h = new AccsSessionManager(this);
        if (!config.getAppkey().equals("[default]")) {
            anet.channel.strategy.dispatch.a.a((IAmdcSign) new c(this, config.getAppkey(), config.getSecurity()));
        }
    }

    @Deprecated
    public synchronized void switchEnv(ENV env) {
        switchEnvironment(env);
    }

    public static synchronized void switchEnvironment(ENV env) {
        synchronized (SessionCenter.class) {
            try {
                if (GlobalAppRuntimeInfo.getEnv() != env) {
                    ALog.i(TAG, "switch env", (String) null, "old", GlobalAppRuntimeInfo.getEnv(), "new", env);
                    GlobalAppRuntimeInfo.setEnv(env);
                    StrategyCenter.getInstance().switchEnv();
                    SpdyAgent.getInstance(GlobalAppRuntimeInfo.getContext(), SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION).switchAccsServer(env == ENV.TEST ? 0 : 1);
                }
                Iterator<Map.Entry<Config, SessionCenter>> it = f125a.entrySet().iterator();
                while (it.hasNext()) {
                    SessionCenter sessionCenter = (SessionCenter) it.next().getValue();
                    if (sessionCenter.d.getEnv() != env) {
                        ALog.i(TAG, "remove instance", sessionCenter.f127c, RequestConstant.ENVIRONMENT, sessionCenter.d.getEnv());
                        sessionCenter.h.forceCloseSession(false);
                        sessionCenter.i.b();
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                ALog.e(TAG, "switch env error.", (String) null, th, new Object[0]);
            }
        }
        return;
    }

    public static synchronized SessionCenter getInstance(String str) {
        SessionCenter instance;
        synchronized (SessionCenter.class) {
            Config configByTag = Config.getConfigByTag(str);
            if (configByTag != null) {
                instance = getInstance(configByTag);
            } else {
                throw new RuntimeException("tag not exist!");
            }
        }
        return instance;
    }

    public static synchronized SessionCenter getInstance(Config config) {
        SessionCenter sessionCenter;
        Context appContext;
        synchronized (SessionCenter.class) {
            if (config != null) {
                if (!j && (appContext = Utils.getAppContext()) != null) {
                    init(appContext);
                }
                sessionCenter = f125a.get(config);
                if (sessionCenter == null) {
                    sessionCenter = new SessionCenter(config);
                    f125a.put(config, sessionCenter);
                }
            } else {
                throw new NullPointerException("config is null!");
            }
        }
        return sessionCenter;
    }

    @Deprecated
    public static synchronized SessionCenter getInstance() {
        Context appContext;
        synchronized (SessionCenter.class) {
            if (!j && (appContext = Utils.getAppContext()) != null) {
                init(appContext);
            }
            SessionCenter sessionCenter = null;
            for (Map.Entry next : f125a.entrySet()) {
                SessionCenter sessionCenter2 = (SessionCenter) next.getValue();
                if (next.getKey() != Config.DEFAULT_CONFIG) {
                    return sessionCenter2;
                }
                sessionCenter = sessionCenter2;
            }
            return sessionCenter;
        }
    }

    public Session getThrowsException(String str, long j2) throws Exception {
        return a(HttpUrl.parse(str), c.f183c, j2);
    }

    @Deprecated
    public Session getThrowsException(String str, ConnType.TypeLevel typeLevel, long j2) throws Exception {
        return a(HttpUrl.parse(str), typeLevel == ConnType.TypeLevel.SPDY ? c.f181a : c.f182b, j2);
    }

    public Session getThrowsException(HttpUrl httpUrl, int i2, long j2) throws Exception {
        return a(httpUrl, i2, j2);
    }

    @Deprecated
    public Session getThrowsException(HttpUrl httpUrl, ConnType.TypeLevel typeLevel, long j2) throws Exception {
        return a(httpUrl, typeLevel == ConnType.TypeLevel.SPDY ? c.f181a : c.f182b, j2);
    }

    public Session get(String str, long j2) {
        return get(HttpUrl.parse(str), c.f183c, j2);
    }

    @Deprecated
    public Session get(String str, ConnType.TypeLevel typeLevel, long j2) {
        return get(HttpUrl.parse(str), typeLevel == ConnType.TypeLevel.SPDY ? c.f181a : c.f182b, j2);
    }

    @Deprecated
    public Session get(HttpUrl httpUrl, ConnType.TypeLevel typeLevel, long j2) {
        return get(httpUrl, typeLevel == ConnType.TypeLevel.SPDY ? c.f181a : c.f182b, j2);
    }

    public Session get(HttpUrl httpUrl, int i2, long j2) {
        try {
            return a(httpUrl, i2, j2);
        } catch (InvalidParameterException e2) {
            ALog.e(TAG, "[Get]param url is invaild", this.f127c, e2, "url", httpUrl.urlString());
            return null;
        } catch (TimeoutException e3) {
            ALog.e(TAG, "[Get]timeout exception", this.f127c, e3, "url", httpUrl.urlString());
            return null;
        } catch (ConnectException e4) {
            ALog.e(TAG, "[Get]connect exception", this.f127c, "errMsg", e4.getMessage(), "url", httpUrl.urlString());
            return null;
        } catch (NoAvailStrategyException e5) {
            ALog.i(TAG, "[Get]" + e5.getMessage(), this.f127c, null, "url", httpUrl.urlString());
            return null;
        } catch (Exception e6) {
            ALog.e(TAG, "[Get]" + e6.getMessage(), this.f127c, (Throwable) null, "url", httpUrl.urlString());
            return null;
        }
    }

    public void registerSessionInfo(SessionInfo sessionInfo) {
        this.g.a(sessionInfo);
        if (sessionInfo.isKeepAlive) {
            this.h.checkAndStartSession();
        }
    }

    public void unregisterSessionInfo(String str) {
        SessionInfo a2 = this.g.a(str);
        if (a2 != null && a2.isKeepAlive) {
            this.h.checkAndStartSession();
        }
    }

    public void registerPublicKey(String str, int i2) {
        this.g.a(str, i2);
    }

    public static void checkAndStartAccsSession() {
        for (SessionCenter sessionCenter : f125a.values()) {
            sessionCenter.h.checkAndStartSession();
        }
    }

    public void forceRecreateAccsSession() {
        this.h.forceCloseSession(true);
    }

    /* access modifiers changed from: protected */
    public Session a(HttpUrl httpUrl, int i2, long j2) throws Exception {
        SessionInfo b2;
        if (!j) {
            ALog.e(TAG, "getInternal not inited!", this.f127c, new Object[0]);
            return null;
        } else if (httpUrl == null) {
            return null;
        } else {
            ALog.d(TAG, "getInternal", this.f127c, "u", httpUrl.urlString(), "sessionType", Integer.valueOf(i2), com.alipay.sdk.data.a.f, Long.valueOf(j2));
            String cNameByHost = StrategyCenter.getInstance().getCNameByHost(httpUrl.host());
            if (cNameByHost == null) {
                cNameByHost = httpUrl.host();
            }
            String scheme = httpUrl.scheme();
            if (!httpUrl.isSchemeLocked()) {
                scheme = StrategyCenter.getInstance().getSchemeByHost(cNameByHost, scheme);
            }
            SessionRequest a2 = a(StringUtils.concatString(scheme, HttpConstant.SCHEME_SPLIT, cNameByHost));
            Session a3 = this.e.a(a2, i2);
            if (a3 != null) {
                ALog.d(TAG, "get internal hit cache session", this.f127c, "session", a3);
            } else if (this.d == Config.DEFAULT_CONFIG && i2 != c.f182b) {
                return null;
            } else {
                if (!GlobalAppRuntimeInfo.isAppBackground() || i2 != c.f181a || !AwcnConfig.isAccsSessionCreateForbiddenInBg() || (b2 = this.g.b(httpUrl.host())) == null || !b2.isAccs) {
                    a2.a(this.f126b, i2, e.a(this.f127c));
                    if (j2 > 0 && a2.b() == i2) {
                        a2.a(j2);
                        a3 = this.e.a(a2, i2);
                        if (a3 == null) {
                            throw new ConnectException("session connecting failed or timeout");
                        }
                    }
                } else {
                    ALog.w(TAG, "app background, forbid to create accs session", this.f127c, new Object[0]);
                    throw new ConnectException("accs session connecting forbidden in background");
                }
            }
            return a3;
        }
    }

    @Deprecated
    public void enterBackground() {
        AppLifecycle.onBackground();
    }

    @Deprecated
    public void enterForeground() {
        AppLifecycle.onForeground();
    }

    /* access modifiers changed from: private */
    public void a(k.d dVar) {
        try {
            k.b[] bVarArr = dVar.f316b;
            for (k.b bVar : bVarArr) {
                if (bVar.k) {
                    b(bVar);
                }
                if (bVar.e != null) {
                    a(bVar);
                }
            }
        } catch (Exception e2) {
            ALog.e(TAG, "checkStrategy failed", this.f127c, e2, new Object[0]);
        }
    }

    private void a(k.b bVar) {
        for (Session next : this.e.a(a(StringUtils.buildKey(bVar.f312c, bVar.f310a)))) {
            if (!StringUtils.isStringEqual(next.k, bVar.e)) {
                ALog.i(TAG, "unit change", next.o, "session unit", next.k, "unit", bVar.e);
                next.close(true);
            }
        }
    }

    private void b(k.b bVar) {
        boolean z;
        boolean z2;
        ALog.i(TAG, "find effectNow", this.f127c, com.alipay.sdk.cons.c.f, bVar.f310a);
        k.a[] aVarArr = bVar.h;
        String[] strArr = bVar.f;
        for (Session next : this.e.a(a(StringUtils.buildKey(bVar.f312c, bVar.f310a)))) {
            if (!next.getConnType().isHttpType()) {
                int i2 = 0;
                while (true) {
                    if (i2 >= strArr.length) {
                        z = false;
                        break;
                    } else if (next.getIp().equals(strArr[i2])) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    if (ALog.isPrintLog(2)) {
                        ALog.i(TAG, "ip not match", next.o, "session ip", next.getIp(), "ips", Arrays.toString(strArr));
                    }
                    next.close(true);
                } else {
                    int i3 = 0;
                    while (true) {
                        if (i3 < aVarArr.length) {
                            if (next.getPort() == aVarArr[i3].f307a && next.getConnType().equals(ConnType.valueOf(ConnProtocol.valueOf(aVarArr[i3])))) {
                                z2 = true;
                                break;
                            }
                            i3++;
                        } else {
                            z2 = false;
                            break;
                        }
                    }
                    if (!z2) {
                        if (ALog.isPrintLog(2)) {
                            ALog.i(TAG, "aisle not match", next.o, "port", Integer.valueOf(next.getPort()), "connType", next.getConnType(), "aisle", Arrays.toString(aVarArr));
                        }
                        next.close(true);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public SessionRequest a(String str) {
        SessionRequest sessionRequest;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.f) {
            sessionRequest = this.f.get(str);
            if (sessionRequest == null) {
                sessionRequest = new SessionRequest(str, this);
                this.f.put(str, sessionRequest);
            }
        }
        return sessionRequest;
    }

    /* compiled from: Taobao */
    private class a implements NetworkStatusHelper.INetworkStatusChangeListener, IStrategyListener, AppLifecycle.AppLifecycleListener {

        /* renamed from: a  reason: collision with root package name */
        boolean f128a;

        private a() {
            this.f128a = false;
        }

        /* synthetic */ a(SessionCenter sessionCenter, c cVar) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            AppLifecycle.registerLifecycleListener(this);
            NetworkStatusHelper.addStatusChangeListener(this);
            StrategyCenter.getInstance().registerListener(this);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            StrategyCenter.getInstance().unregisterListener(this);
            AppLifecycle.unregisterLifecycleListener(this);
            NetworkStatusHelper.removeStatusChangeListener(this);
        }

        public void onNetworkStatusChanged(NetworkStatusHelper.NetworkStatus networkStatus) {
            ALog.e(SessionCenter.TAG, "onNetworkStatusChanged.", SessionCenter.this.f127c, "networkStatus", networkStatus);
            List<SessionRequest> a2 = SessionCenter.this.e.a();
            if (!a2.isEmpty()) {
                for (SessionRequest a3 : a2) {
                    ALog.d(SessionCenter.TAG, "network change, try recreate session", SessionCenter.this.f127c, new Object[0]);
                    a3.a((String) null);
                }
            }
            SessionCenter.this.h.checkAndStartSession();
        }

        public void onStrategyUpdated(k.d dVar) {
            SessionCenter.this.a(dVar);
            SessionCenter.this.h.checkAndStartSession();
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0057 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void forground() {
            /*
                r7 = this;
                java.lang.String r0 = "awcn.SessionCenter"
                java.lang.String r1 = "[forground]"
                anet.channel.SessionCenter r2 = anet.channel.SessionCenter.this
                java.lang.String r2 = r2.f127c
                r3 = 0
                java.lang.Object[] r4 = new java.lang.Object[r3]
                anet.channel.util.ALog.i(r0, r1, r2, r4)
                anet.channel.SessionCenter r0 = anet.channel.SessionCenter.this
                android.content.Context r0 = r0.f126b
                if (r0 != 0) goto L_0x0015
                return
            L_0x0015:
                boolean r0 = r7.f128a
                if (r0 == 0) goto L_0x001a
                return
            L_0x001a:
                r0 = 1
                r7.f128a = r0
                boolean r1 = anet.channel.SessionCenter.j
                if (r1 != 0) goto L_0x0031
                java.lang.String r0 = "awcn.SessionCenter"
                java.lang.String r1 = "forground not inited!"
                anet.channel.SessionCenter r2 = anet.channel.SessionCenter.this
                java.lang.String r2 = r2.f127c
                java.lang.Object[] r3 = new java.lang.Object[r3]
                anet.channel.util.ALog.e(r0, r1, r2, r3)
                return
            L_0x0031:
                long r1 = anet.channel.util.AppLifecycle.lastEnterBackgroundTime     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                r4 = 0
                int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r6 == 0) goto L_0x0050
                long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                long r4 = anet.channel.util.AppLifecycle.lastEnterBackgroundTime     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                r6 = 0
                long r1 = r1 - r4
                r4 = 60000(0xea60, double:2.9644E-319)
                int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r6 <= 0) goto L_0x0050
                anet.channel.SessionCenter r1 = anet.channel.SessionCenter.this     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                anet.channel.AccsSessionManager r1 = r1.h     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                r1.forceCloseSession(r0)     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                goto L_0x0057
            L_0x0050:
                anet.channel.SessionCenter r0 = anet.channel.SessionCenter.this     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                anet.channel.AccsSessionManager r0 = r0.h     // Catch:{ Exception -> 0x0057, all -> 0x005a }
                r0.checkAndStartSession()     // Catch:{ Exception -> 0x0057, all -> 0x005a }
            L_0x0057:
                r7.f128a = r3     // Catch:{ Exception -> 0x005e }
                goto L_0x005e
            L_0x005a:
                r0 = move-exception
                r7.f128a = r3     // Catch:{ Exception -> 0x005e }
                throw r0     // Catch:{ Exception -> 0x005e }
            L_0x005e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: anet.channel.SessionCenter.a.forground():void");
        }

        public void background() {
            ALog.i(SessionCenter.TAG, "[background]", SessionCenter.this.f127c, new Object[0]);
            if (!SessionCenter.j) {
                ALog.e(SessionCenter.TAG, "background not inited!", SessionCenter.this.f127c, new Object[0]);
                return;
            }
            try {
                StrategyCenter.getInstance().saveData();
                if ("OPPO".equalsIgnoreCase(Build.BRAND)) {
                    ALog.i(SessionCenter.TAG, "close session for OPPO", SessionCenter.this.f127c, new Object[0]);
                    SessionCenter.this.h.forceCloseSession(false);
                }
            } catch (Exception unused) {
            }
        }
    }
}
