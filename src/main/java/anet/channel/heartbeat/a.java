package anet.channel.heartbeat;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
class a implements IHeartbeat, Runnable {

    /* renamed from: a  reason: collision with root package name */
    private Session f188a;

    /* renamed from: b  reason: collision with root package name */
    private volatile long f189b = 0;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f190c = false;
    private int d = 0;
    private long e = 0;

    a() {
    }

    public void start(Session session) {
        if (session != null) {
            this.f188a = session;
            this.e = (long) session.getConnStrategy().getHeartbeat();
            if (this.e <= 0) {
                this.e = 45000;
            }
            ALog.i("awcn.DefaultHeartbeatImpl", "heartbeat start", session.o, "session", session, "interval", Long.valueOf(this.e));
            a(this.e);
            return;
        }
        throw new NullPointerException("session is null");
    }

    public void stop() {
        if (this.f188a != null) {
            ALog.i("awcn.DefaultHeartbeatImpl", "heartbeat stop", this.f188a.o, "session", this.f188a);
            this.f190c = true;
        }
    }

    public void reSchedule() {
        long currentTimeMillis = System.currentTimeMillis() + this.e;
        if (this.f189b + 1000 < currentTimeMillis) {
            this.f189b = currentTimeMillis;
        }
    }

    public void run() {
        if (!this.f190c) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.f189b) {
                a(this.f189b - currentTimeMillis);
                return;
            }
            boolean isAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            int i = 0;
            if (!isAppBackground) {
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.DefaultHeartbeatImpl", "heartbeat", this.f188a.o, "session", this.f188a);
                }
                this.f188a.ping(true);
                if (isAppBackground) {
                    i = this.d + 1;
                }
                this.d = i;
                a(this.e);
                return;
            }
            ALog.e("awcn.DefaultHeartbeatImpl", "close session in background", this.f188a.o, "session", this.f188a);
            this.f188a.close(false);
        }
    }

    private void a(long j) {
        try {
            this.f189b = System.currentTimeMillis() + j;
            ThreadPoolExecutorFactory.submitScheduledTask(this, j + 50, TimeUnit.MILLISECONDS);
        } catch (Exception e2) {
            ALog.e("awcn.DefaultHeartbeatImpl", "Submit heartbeat task failed.", this.f188a.o, e2, new Object[0]);
        }
    }
}
