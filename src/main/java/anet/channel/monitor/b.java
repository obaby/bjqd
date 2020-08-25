package anet.channel.monitor;

import anet.channel.status.NetworkStatusHelper;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;

/* compiled from: Taobao */
public class b {

    /* renamed from: a  reason: collision with root package name */
    static int f197a = 0;

    /* renamed from: b  reason: collision with root package name */
    static long f198b = 0;

    /* renamed from: c  reason: collision with root package name */
    static long f199c = 0;
    static long d = 0;
    static long e = 0;
    static long f = 0;
    static double g = 0.0d;
    static double h = 0.0d;
    static double i = 0.0d;
    static double j = 40.0d;
    /* access modifiers changed from: private */
    public static volatile boolean k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public e n;

    /* synthetic */ b(c cVar) {
        this();
    }

    static /* synthetic */ int b(b bVar) {
        int i2 = bVar.m;
        bVar.m = i2 + 1;
        return i2;
    }

    /* compiled from: Taobao */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        static b f200a = new b((c) null);

        a() {
        }
    }

    public static b a() {
        return a.f200a;
    }

    private b() {
        this.l = 5;
        this.m = 0;
        this.n = new e();
        NetworkStatusHelper.addStatusChangeListener(new c(this));
    }

    public int b() {
        if (NetworkStatusHelper.getStatus() == NetworkStatusHelper.NetworkStatus.G2) {
            return 1;
        }
        return this.l;
    }

    public double c() {
        return i;
    }

    public synchronized void d() {
        try {
            ALog.i("awcn.BandWidthSampler", "[startNetworkMeter]", (String) null, "NetworkStatus", NetworkStatusHelper.getStatus());
            if (NetworkStatusHelper.getStatus() == NetworkStatusHelper.NetworkStatus.G2) {
                k = false;
                return;
            }
            k = true;
        } catch (Exception e2) {
            ALog.w("awcn.BandWidthSampler", "startNetworkMeter fail.", (String) null, e2, new Object[0]);
        }
        return;
    }

    public void e() {
        k = false;
    }

    public void a(long j2, long j3, long j4) {
        if (k) {
            ThreadPoolExecutorFactory.submitScheduledTask(new d(this, j2, j3, j4));
        }
    }
}
