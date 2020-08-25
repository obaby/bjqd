package anet.channel;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventCb;
import anet.channel.entity.b;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.statist.SessionStatistic;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpHelper;
import anet.channel.util.StringUtils;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

/* compiled from: Taobao */
public abstract class Session implements Comparable<Session> {
    static ExecutorService u = Executors.newSingleThreadExecutor();

    /* renamed from: a  reason: collision with root package name */
    public Context f121a;

    /* renamed from: b  reason: collision with root package name */
    Map<EventCb, Integer> f122b = new LinkedHashMap();

    /* renamed from: c  reason: collision with root package name */
    public String f123c;
    public String d;
    public String e;
    public int f;
    public String g;
    public int h;
    public ConnType i;
    public IConnStrategy j;
    public String k;
    public boolean l;
    public int m;
    protected Runnable n;
    public final String o;
    public final SessionStatistic p;
    public int q;
    public int r;
    public boolean s;
    protected boolean t;
    private boolean v;
    private Future<?> w;
    private List<Long> x;
    private long y;

    public abstract void close();

    public void connect() {
    }

    public abstract Runnable getRecvTimeOutRunnable();

    public abstract boolean isAvailable();

    public void onDisconnect() {
    }

    public void ping(boolean z) {
    }

    public abstract Cancelable request(Request request, RequestCb requestCb);

    public void sendCustomFrame(int i2, byte[] bArr, int i3) {
    }

    /* compiled from: Taobao */
    public static class a {
        public static final int AUTHING = 3;
        public static final int AUTH_FAIL = 5;
        public static final int AUTH_SUCC = 4;
        public static final int CONNECTED = 0;
        public static final int CONNECTING = 1;
        public static final int CONNETFAIL = 2;
        public static final int DISCONNECTED = 6;
        public static final int DISCONNECTING = 7;

        /* renamed from: a  reason: collision with root package name */
        static final String[] f124a = {"CONNECTED", "CONNECTING", "CONNETFAIL", "AUTHING", "AUTH_SUCC", "AUTH_FAIL", "DISCONNECTED", "DISCONNECTING"};

        static String a(int i) {
            return f124a[i];
        }
    }

    public int compareTo(Session session) {
        return ConnType.compare(this.i, session.i);
    }

    public Session(Context context, anet.channel.entity.a aVar) {
        boolean z = false;
        this.v = false;
        this.k = null;
        this.l = false;
        this.m = 6;
        this.s = false;
        this.t = true;
        this.x = null;
        this.y = 0;
        this.f121a = context;
        this.e = aVar.a();
        this.f = aVar.b();
        this.i = aVar.c();
        this.f123c = aVar.f();
        this.d = this.f123c.substring(this.f123c.indexOf(HttpConstant.SCHEME_SPLIT) + 3);
        this.r = aVar.e();
        this.q = aVar.d();
        this.j = aVar.f175a;
        if (this.j != null && this.j.getIpType() == -1) {
            z = true;
        }
        this.l = z;
        this.o = aVar.h();
        this.p = new SessionStatistic(aVar);
        this.p.host = this.d;
    }

    public void checkAvailable() {
        ping(true);
    }

    public static void configTnetALog(Context context, String str, int i2, int i3) {
        SpdyAgent instance = SpdyAgent.getInstance(context, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
        if (instance == null || !SpdyAgent.checkLoadSucc()) {
            ALog.e("agent null or configTnetALog load so fail!!!", (String) null, "loadso", Boolean.valueOf(SpdyAgent.checkLoadSucc()));
            return;
        }
        instance.configLogFile(str, i2, i3);
    }

    public void close(boolean z) {
        this.s = z;
        close();
    }

    public void registerEventcb(int i2, EventCb eventCb) {
        if (this.f122b != null) {
            this.f122b.put(eventCb, Integer.valueOf(i2));
        }
    }

    public String getIp() {
        return this.e;
    }

    public int getPort() {
        return this.f;
    }

    public ConnType getConnType() {
        return this.i;
    }

    public String getHost() {
        return this.f123c;
    }

    public String getRealHost() {
        return this.d;
    }

    public IConnStrategy getConnStrategy() {
        return this.j;
    }

    public String getUnit() {
        return this.k;
    }

    public void handleCallbacks(int i2, b bVar) {
        u.submit(new a(this, i2, bVar));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void notifyStatus(int r9, anet.channel.entity.b r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "awcn.Session"
            java.lang.String r1 = "notifyStatus"
            java.lang.String r2 = r8.o     // Catch:{ all -> 0x0060 }
            r3 = 2
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0060 }
            java.lang.String r5 = "status"
            r6 = 0
            r4[r6] = r5     // Catch:{ all -> 0x0060 }
            java.lang.String r5 = anet.channel.Session.a.a(r9)     // Catch:{ all -> 0x0060 }
            r7 = 1
            r4[r7] = r5     // Catch:{ all -> 0x0060 }
            anet.channel.util.ALog.e(r0, r1, r2, r4)     // Catch:{ all -> 0x0060 }
            int r0 = r8.m     // Catch:{ all -> 0x0060 }
            if (r9 != r0) goto L_0x002a
            java.lang.String r9 = "awcn.Session"
            java.lang.String r10 = "ignore notifyStatus"
            java.lang.String r0 = r8.o     // Catch:{ all -> 0x0060 }
            java.lang.Object[] r1 = new java.lang.Object[r6]     // Catch:{ all -> 0x0060 }
            anet.channel.util.ALog.i(r9, r10, r0, r1)     // Catch:{ all -> 0x0060 }
            monitor-exit(r8)
            return
        L_0x002a:
            r8.m = r9     // Catch:{ all -> 0x0060 }
            int r9 = r8.m     // Catch:{ all -> 0x0060 }
            switch(r9) {
                case 0: goto L_0x005b;
                case 1: goto L_0x005e;
                case 2: goto L_0x0055;
                case 3: goto L_0x005e;
                case 4: goto L_0x0043;
                case 5: goto L_0x003d;
                case 6: goto L_0x0032;
                case 7: goto L_0x005e;
                default: goto L_0x0031;
            }     // Catch:{ all -> 0x0060 }
        L_0x0031:
            goto L_0x005e
        L_0x0032:
            r8.onDisconnect()     // Catch:{ all -> 0x0060 }
            boolean r9 = r8.v     // Catch:{ all -> 0x0060 }
            if (r9 != 0) goto L_0x005e
            r8.handleCallbacks(r3, r10)     // Catch:{ all -> 0x0060 }
            goto L_0x005e
        L_0x003d:
            r9 = 1024(0x400, float:1.435E-42)
            r8.handleCallbacks(r9, r10)     // Catch:{ all -> 0x0060 }
            goto L_0x005e
        L_0x0043:
            anet.channel.strategy.IStrategyInstance r9 = anet.channel.strategy.StrategyCenter.getInstance()     // Catch:{ all -> 0x0060 }
            java.lang.String r0 = r8.d     // Catch:{ all -> 0x0060 }
            java.lang.String r9 = r9.getUnitByHost(r0)     // Catch:{ all -> 0x0060 }
            r8.k = r9     // Catch:{ all -> 0x0060 }
            r9 = 512(0x200, float:7.175E-43)
            r8.handleCallbacks(r9, r10)     // Catch:{ all -> 0x0060 }
            goto L_0x005e
        L_0x0055:
            r9 = 256(0x100, float:3.59E-43)
            r8.handleCallbacks(r9, r10)     // Catch:{ all -> 0x0060 }
            goto L_0x005e
        L_0x005b:
            r8.handleCallbacks(r7, r10)     // Catch:{ all -> 0x0060 }
        L_0x005e:
            monitor-exit(r8)
            return
        L_0x0060:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.Session.notifyStatus(int, anet.channel.entity.b):void");
    }

    public void setPingTimeout() {
        if (this.n == null) {
            this.n = getRecvTimeOutRunnable();
        }
        a();
        if (this.n != null) {
            this.w = ThreadPoolExecutorFactory.submitScheduledTask(this.n, 40000, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.n != null && this.w != null) {
            this.w.cancel(true);
        }
    }

    public String toString() {
        return "Session@[" + this.o + '|' + this.i + ']';
    }

    public void handleResponseCode(Request request, int i2) {
        if (request.getHeaders().containsKey(HttpConstant.X_PV) && i2 >= 500 && i2 < 600) {
            synchronized (this) {
                if (this.x == null) {
                    this.x = new LinkedList();
                }
                if (this.x.size() < 5) {
                    this.x.add(Long.valueOf(System.currentTimeMillis()));
                } else {
                    long longValue = this.x.remove(0).longValue();
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - longValue <= 60000) {
                        StrategyCenter.getInstance().forceRefreshStrategy(request.getHost());
                        this.x.clear();
                    } else {
                        this.x.add(Long.valueOf(currentTimeMillis));
                    }
                }
            }
        }
    }

    public void handleResponseHeaders(Request request, Map<String, List<String>> map) {
        try {
            if (map.containsKey(HttpConstant.X_SWITCH_UNIT)) {
                String singleHeaderFieldByKey = HttpHelper.getSingleHeaderFieldByKey(map, HttpConstant.X_SWITCH_UNIT);
                if (TextUtils.isEmpty(singleHeaderFieldByKey)) {
                    singleHeaderFieldByKey = null;
                }
                if (!StringUtils.isStringEqual(this.k, singleHeaderFieldByKey)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.y > 60000) {
                        StrategyCenter.getInstance().forceRefreshStrategy(request.getHost());
                        this.y = currentTimeMillis;
                    }
                }
            }
        } catch (Exception unused) {
        }
    }
}
