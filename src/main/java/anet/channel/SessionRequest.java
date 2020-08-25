package anet.channel;

import android.content.Context;
import android.content.Intent;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.session.TnetSpdySession;
import anet.channel.session.d;
import anet.channel.statist.AlarmObject;
import anet.channel.statist.SessionConnStat;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import com.alipay.sdk.cons.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: Taobao */
class SessionRequest {

    /* renamed from: a  reason: collision with root package name */
    String f130a;

    /* renamed from: b  reason: collision with root package name */
    SessionCenter f131b;

    /* renamed from: c  reason: collision with root package name */
    d f132c;
    volatile boolean d = false;
    volatile Session e;
    volatile boolean f = false;
    SessionConnStat g = null;
    private String h;
    private SessionInfo i;
    private volatile Future j;
    private Object k = new Object();

    /* compiled from: Taobao */
    private interface IConnCb {
        void onDisConnect(Session session, long j, int i);

        void onFailed(Session session, long j, int i, int i2);

        void onSuccess(Session session, long j);
    }

    SessionRequest(String str, SessionCenter sessionCenter) {
        this.f130a = str;
        this.h = this.f130a.substring(this.f130a.indexOf(HttpConstant.SCHEME_SPLIT) + 3);
        this.f131b = sessionCenter;
        this.i = sessionCenter.g.b(this.h);
        this.f132c = sessionCenter.e;
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.f130a;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.d = z;
        if (!z) {
            if (this.j != null) {
                this.j.cancel(true);
                this.j = null;
            }
            this.e = null;
        }
    }

    /* compiled from: Taobao */
    private class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        String f136a = null;

        b(String str) {
            this.f136a = str;
        }

        public void run() {
            if (SessionRequest.this.d) {
                ALog.e("awcn.SessionRequest", "Connecting timeout!!! reset status!", this.f136a, new Object[0]);
                SessionRequest.this.g.ret = 2;
                SessionRequest.this.g.totalTime = System.currentTimeMillis() - SessionRequest.this.g.start;
                if (SessionRequest.this.e != null) {
                    SessionRequest.this.e.t = false;
                    SessionRequest.this.e.close();
                    SessionRequest.this.g.syncValueFromSession(SessionRequest.this.e);
                }
                AppMonitor.getInstance().commitStat(SessionRequest.this.g);
                SessionRequest.this.a(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:26|27|28|29|30) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00c8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r10, int r11, java.lang.String r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            anet.channel.d r0 = r9.f132c     // Catch:{ all -> 0x00f3 }
            anet.channel.Session r0 = r0.a((anet.channel.SessionRequest) r9, (int) r11)     // Catch:{ all -> 0x00f3 }
            r1 = 0
            if (r0 == 0) goto L_0x0015
            java.lang.String r10 = "awcn.SessionRequest"
            java.lang.String r11 = "Available Session exist!!!"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f3 }
            anet.channel.util.ALog.d(r10, r11, r12, r0)     // Catch:{ all -> 0x00f3 }
            monitor-exit(r9)
            return
        L_0x0015:
            boolean r0 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x00f3 }
            if (r0 == 0) goto L_0x0020
            r12 = 0
            java.lang.String r12 = anet.channel.util.e.a(r12)     // Catch:{ all -> 0x00f3 }
        L_0x0020:
            java.lang.String r0 = "awcn.SessionRequest"
            java.lang.String r2 = "SessionRequest start"
            r3 = 4
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00f3 }
            java.lang.String r5 = "host"
            r4[r1] = r5     // Catch:{ all -> 0x00f3 }
            java.lang.String r5 = r9.f130a     // Catch:{ all -> 0x00f3 }
            r6 = 1
            r4[r6] = r5     // Catch:{ all -> 0x00f3 }
            java.lang.String r5 = "type"
            r7 = 2
            r4[r7] = r5     // Catch:{ all -> 0x00f3 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00f3 }
            r8 = 3
            r4[r8] = r5     // Catch:{ all -> 0x00f3 }
            anet.channel.util.ALog.d(r0, r2, r12, r4)     // Catch:{ all -> 0x00f3 }
            boolean r0 = r9.d     // Catch:{ all -> 0x00f3 }
            if (r0 == 0) goto L_0x0058
            java.lang.String r10 = "awcn.SessionRequest"
            java.lang.String r11 = "session connecting"
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ all -> 0x00f3 }
            java.lang.String r2 = "host"
            r0[r1] = r2     // Catch:{ all -> 0x00f3 }
            java.lang.String r1 = r9.a()     // Catch:{ all -> 0x00f3 }
            r0[r6] = r1     // Catch:{ all -> 0x00f3 }
            anet.channel.util.ALog.d(r10, r11, r12, r0)     // Catch:{ all -> 0x00f3 }
            monitor-exit(r9)
            return
        L_0x0058:
            r9.a((boolean) r6)     // Catch:{ all -> 0x00f3 }
            anet.channel.SessionRequest$b r0 = new anet.channel.SessionRequest$b     // Catch:{ all -> 0x00f3 }
            r0.<init>(r12)     // Catch:{ all -> 0x00f3 }
            r4 = 45
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x00f3 }
            java.util.concurrent.Future r0 = anet.channel.thread.ThreadPoolExecutorFactory.submitScheduledTask(r0, r4, r2)     // Catch:{ all -> 0x00f3 }
            r9.j = r0     // Catch:{ all -> 0x00f3 }
            anet.channel.statist.SessionConnStat r0 = new anet.channel.statist.SessionConnStat     // Catch:{ all -> 0x00f3 }
            r0.<init>()     // Catch:{ all -> 0x00f3 }
            r9.g = r0     // Catch:{ all -> 0x00f3 }
            anet.channel.statist.SessionConnStat r0 = r9.g     // Catch:{ all -> 0x00f3 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00f3 }
            r0.start = r4     // Catch:{ all -> 0x00f3 }
            boolean r0 = anet.channel.status.NetworkStatusHelper.isConnected()     // Catch:{ all -> 0x00f3 }
            if (r0 != 0) goto L_0x00a7
            boolean r10 = anet.channel.util.ALog.isPrintLog(r6)     // Catch:{ all -> 0x00f3 }
            if (r10 == 0) goto L_0x009c
            java.lang.String r10 = "awcn.SessionRequest"
            java.lang.String r11 = "network is not available, can't create session"
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ all -> 0x00f3 }
            java.lang.String r2 = "isConnected"
            r0[r1] = r2     // Catch:{ all -> 0x00f3 }
            boolean r1 = anet.channel.status.NetworkStatusHelper.isConnected()     // Catch:{ all -> 0x00f3 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x00f3 }
            r0[r6] = r1     // Catch:{ all -> 0x00f3 }
            anet.channel.util.ALog.d(r10, r11, r12, r0)     // Catch:{ all -> 0x00f3 }
        L_0x009c:
            r9.c()     // Catch:{ all -> 0x00f3 }
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ all -> 0x00f3 }
            java.lang.String r11 = "no network"
            r10.<init>(r11)     // Catch:{ all -> 0x00f3 }
            throw r10     // Catch:{ all -> 0x00f3 }
        L_0x00a7:
            java.util.List r0 = r9.a((int) r11, (java.lang.String) r12)     // Catch:{ all -> 0x00f3 }
            boolean r2 = r0.isEmpty()     // Catch:{ all -> 0x00f3 }
            if (r2 != 0) goto L_0x00cd
            java.util.List r11 = r9.a((java.util.List<anet.channel.strategy.IConnStrategy>) r0, (java.lang.String) r12)     // Catch:{ all -> 0x00f3 }
            java.lang.Object r12 = r11.remove(r1)     // Catch:{ Throwable -> 0x00c8 }
            anet.channel.entity.a r12 = (anet.channel.entity.a) r12     // Catch:{ Throwable -> 0x00c8 }
            anet.channel.SessionRequest$a r0 = new anet.channel.SessionRequest$a     // Catch:{ Throwable -> 0x00c8 }
            r0.<init>(r10, r11, r12)     // Catch:{ Throwable -> 0x00c8 }
            java.lang.String r11 = r12.h()     // Catch:{ Throwable -> 0x00c8 }
            r9.a(r10, r12, r0, r11)     // Catch:{ Throwable -> 0x00c8 }
            goto L_0x00cb
        L_0x00c8:
            r9.c()     // Catch:{ all -> 0x00f3 }
        L_0x00cb:
            monitor-exit(r9)
            return
        L_0x00cd:
            java.lang.String r10 = "awcn.SessionRequest"
            java.lang.String r0 = "no avalible strategy, can't create session"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x00f3 }
            java.lang.String r3 = "host"
            r2[r1] = r3     // Catch:{ all -> 0x00f3 }
            java.lang.String r1 = r9.f130a     // Catch:{ all -> 0x00f3 }
            r2[r6] = r1     // Catch:{ all -> 0x00f3 }
            java.lang.String r1 = "type"
            r2[r7] = r1     // Catch:{ all -> 0x00f3 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00f3 }
            r2[r8] = r11     // Catch:{ all -> 0x00f3 }
            anet.channel.util.ALog.i(r10, r0, r12, r2)     // Catch:{ all -> 0x00f3 }
            r9.c()     // Catch:{ all -> 0x00f3 }
            anet.channel.NoAvailStrategyException r10 = new anet.channel.NoAvailStrategyException     // Catch:{ all -> 0x00f3 }
            java.lang.String r11 = "no avalible strategy"
            r10.<init>(r11)     // Catch:{ all -> 0x00f3 }
            throw r10     // Catch:{ all -> 0x00f3 }
        L_0x00f3:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.SessionRequest.a(android.content.Context, int, java.lang.String):void");
    }

    /* compiled from: Taobao */
    class a implements IConnCb {

        /* renamed from: a  reason: collision with root package name */
        boolean f133a = false;
        /* access modifiers changed from: private */

        /* renamed from: c  reason: collision with root package name */
        public Context f135c;
        private List<anet.channel.entity.a> d;
        private anet.channel.entity.a e;

        a(Context context, List<anet.channel.entity.a> list, anet.channel.entity.a aVar) {
            this.f135c = context;
            this.d = list;
            this.e = aVar;
        }

        public void onFailed(Session session, long j, int i, int i2) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "Connect failed", this.e.h(), "session", session, c.f, SessionRequest.this.a(), "isHandleFinish", Boolean.valueOf(this.f133a));
            }
            if (SessionRequest.this.f) {
                SessionRequest.this.f = false;
            } else if (!this.f133a) {
                this.f133a = true;
                SessionRequest.this.f132c.b(SessionRequest.this, session);
                if (!session.t || !NetworkStatusHelper.isConnected() || this.d.isEmpty()) {
                    SessionRequest.this.c();
                    if (256 == i && i2 != -2613 && i2 != -2601) {
                        AlarmObject alarmObject = new AlarmObject();
                        alarmObject.module = "networkPrefer";
                        alarmObject.modulePoint = "policy";
                        alarmObject.arg = SessionRequest.this.f130a;
                        alarmObject.errorCode = String.valueOf(i2);
                        alarmObject.isSuccess = false;
                        AppMonitor.getInstance().commitAlarm(alarmObject);
                        SessionRequest.this.g.ret = 0;
                        SessionRequest.this.g.appendErrorTrace(i2);
                        SessionRequest.this.g.errorCode = String.valueOf(i2);
                        SessionRequest.this.g.totalTime = System.currentTimeMillis() - SessionRequest.this.g.start;
                        SessionRequest.this.g.syncValueFromSession(session);
                        AppMonitor.getInstance().commitStat(SessionRequest.this.g);
                        return;
                    }
                    return;
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.SessionRequest", "use next connInfo to create session", this.e.h(), c.f, SessionRequest.this.a());
                }
                if (this.e.f176b == this.e.f177c && (i2 == -2003 || i2 == -2410)) {
                    ListIterator<anet.channel.entity.a> listIterator = this.d.listIterator();
                    while (listIterator.hasNext()) {
                        if (session.getIp().equals(listIterator.next().f175a.getIp())) {
                            listIterator.remove();
                        }
                    }
                }
                anet.channel.entity.a remove = this.d.remove(0);
                SessionRequest.this.a(this.f135c, remove, new a(this.f135c, this.d, remove), remove.h());
            }
        }

        public void onSuccess(Session session, long j) {
            ALog.d("awcn.SessionRequest", "Connect Success", this.e.h(), "session", session, c.f, SessionRequest.this.a());
            try {
                if (SessionRequest.this.f) {
                    SessionRequest.this.f = false;
                    session.close(false);
                    return;
                }
                SessionRequest.this.f132c.a(SessionRequest.this, session);
                AlarmObject alarmObject = new AlarmObject();
                alarmObject.module = "networkPrefer";
                alarmObject.modulePoint = "policy";
                alarmObject.arg = SessionRequest.this.f130a;
                alarmObject.isSuccess = true;
                AppMonitor.getInstance().commitAlarm(alarmObject);
                SessionRequest.this.g.syncValueFromSession(session);
                SessionRequest.this.g.ret = 1;
                SessionRequest.this.g.totalTime = System.currentTimeMillis() - SessionRequest.this.g.start;
                AppMonitor.getInstance().commitStat(SessionRequest.this.g);
                SessionRequest.this.c();
            } catch (Exception e2) {
                ALog.e("awcn.SessionRequest", "[onSuccess]:", this.e.h(), e2, new Object[0]);
            } finally {
                SessionRequest.this.c();
            }
        }

        public void onDisConnect(Session session, long j, int i) {
            boolean isAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            ALog.d("awcn.SessionRequest", "Connect Disconnect", this.e.h(), "session", session, c.f, SessionRequest.this.a(), "appIsBg", Boolean.valueOf(isAppBackground), "isHandleFinish", Boolean.valueOf(this.f133a));
            SessionRequest.this.f132c.b(SessionRequest.this, session);
            if (!this.f133a) {
                this.f133a = true;
                if (session.s) {
                    if (isAppBackground) {
                        ALog.e("awcn.SessionRequest", "[onDisConnect]app background, don't Recreate", this.e.h(), "session", session);
                    } else if (!NetworkStatusHelper.isConnected()) {
                        ALog.e("awcn.SessionRequest", "[onDisConnect]no network, don't Recreate", this.e.h(), "session", session);
                    } else {
                        try {
                            ALog.d("awcn.SessionRequest", "session disconnected, try to recreate session", this.e.h(), new Object[0]);
                            ThreadPoolExecutorFactory.submitScheduledTask(new g(this, session), (long) (Math.random() * 10.0d * 1000.0d), TimeUnit.MILLISECONDS);
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
    }

    private List<IConnStrategy> a(int i2, String str) {
        List<IConnStrategy> list;
        try {
            HttpUrl parse = HttpUrl.parse(a());
            if (parse == null) {
                return Collections.EMPTY_LIST;
            }
            list = StrategyCenter.getInstance().getConnStrategyListByHost(parse.host());
            try {
                if (!list.isEmpty()) {
                    boolean equalsIgnoreCase = "https".equalsIgnoreCase(parse.scheme());
                    ListIterator<IConnStrategy> listIterator = list.listIterator();
                    while (listIterator.hasNext()) {
                        ConnType valueOf = ConnType.valueOf(listIterator.next().getProtocol());
                        if (!(valueOf.isSSL() == equalsIgnoreCase && (i2 == anet.channel.entity.c.f183c || valueOf.getType() == i2))) {
                            listIterator.remove();
                        }
                    }
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.SessionRequest", "[getAvailStrategy]", str, "strategies", list);
                }
            } catch (Throwable th) {
                th = th;
                ALog.e("awcn.SessionRequest", "", str, th, new Object[0]);
                return list;
            }
            return list;
        } catch (Throwable th2) {
            th = th2;
            list = Collections.EMPTY_LIST;
            ALog.e("awcn.SessionRequest", "", str, th, new Object[0]);
            return list;
        }
    }

    private List<anet.channel.entity.a> a(List<IConnStrategy> list, String str) {
        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        while (i2 < list.size()) {
            IConnStrategy iConnStrategy = list.get(i2);
            int retryTimes = iConnStrategy.getRetryTimes();
            int i4 = i3;
            for (int i5 = 0; i5 <= retryTimes; i5++) {
                i4++;
                String a2 = a();
                anet.channel.entity.a aVar = new anet.channel.entity.a(a2, str + "_" + i4, iConnStrategy);
                aVar.f176b = i5;
                aVar.f177c = retryTimes;
                arrayList.add(aVar);
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(Context context, anet.channel.entity.a aVar, IConnCb iConnCb, String str) {
        ConnType c2 = aVar.c();
        if (context == null || c2.isHttpType()) {
            this.e = new d(context, aVar);
        } else {
            TnetSpdySession tnetSpdySession = new TnetSpdySession(context, aVar);
            tnetSpdySession.initConfig(this.f131b.d);
            tnetSpdySession.initSessionInfo(this.i);
            tnetSpdySession.setTnetPublicKey(this.f131b.g.c(this.h));
            this.e = tnetSpdySession;
        }
        ALog.i("awcn.SessionRequest", "create connection...", str, "Host", a(), "Type", aVar.c(), "IP", aVar.a(), "Port", Integer.valueOf(aVar.b()), "heartbeat", Integer.valueOf(aVar.g()), "session", this.e);
        a(this.e, iConnCb, System.currentTimeMillis());
        this.e.connect();
        this.g.retryTimes++;
        this.g.startConnect = System.currentTimeMillis();
    }

    private void a(Session session, IConnCb iConnCb, long j2) {
        if (iConnCb != null) {
            session.registerEventcb(EventType.ALL, new e(this, iConnCb, j2));
            session.registerEventcb(1792, new f(this, session));
        }
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        ALog.d("awcn.SessionRequest", "closeSessions", (String) null, c.f, this.f130a, "autoCreate", Boolean.valueOf(z));
        if (!z && this.e != null) {
            this.e.t = false;
            this.e.close(false);
        }
        List<Session> a2 = this.f132c.a(this);
        if (a2 != null) {
            for (Session next : a2) {
                if (next != null) {
                    next.close(z);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        ALog.d("awcn.SessionRequest", "reCreateSession", str, c.f, this.f130a);
        b(true);
    }

    /* access modifiers changed from: protected */
    public void a(long j2) throws InterruptedException, TimeoutException {
        ALog.d("awcn.SessionRequest", "[await]", (String) null, "timeoutMs", Long.valueOf(j2));
        if (j2 > 0) {
            synchronized (this.k) {
                long currentTimeMillis = System.currentTimeMillis() + j2;
                while (true) {
                    if (!this.d) {
                        break;
                    }
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 >= currentTimeMillis) {
                        break;
                    }
                    this.k.wait(currentTimeMillis - currentTimeMillis2);
                }
                if (this.d) {
                    throw new TimeoutException();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public int b() {
        Session session = this.e;
        if (session != null) {
            return session.i.getType();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        a(false);
        synchronized (this.k) {
            this.k.notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Session session, int i2, String str) {
        Context context = GlobalAppRuntimeInfo.getContext();
        if (context != null && this.i != null && this.i.isAccs) {
            try {
                Intent intent = new Intent("com.taobao.accs.intent.action.RECEIVE");
                intent.setPackage(context.getPackageName());
                intent.setClassName(context, "com.taobao.accs.data.MsgDistributeService");
                intent.putExtra("command", 103);
                intent.putExtra(c.f, session.getHost());
                intent.putExtra("is_center_host", true);
                boolean isAvailable = session.isAvailable();
                if (!isAvailable) {
                    intent.putExtra("errorCode", i2);
                    intent.putExtra("errorDetail", str);
                }
                intent.putExtra("connect_avail", isAvailable);
                intent.putExtra("type_inapp", true);
                context.startService(intent);
            } catch (Throwable th) {
                ALog.e("awcn.SessionRequest", "sendConnectInfoBroadCastToAccs", (String) null, th, new Object[0]);
            }
        }
    }
}
