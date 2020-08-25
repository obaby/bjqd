package anet.channel.monitor;

import anet.channel.util.ALog;

/* compiled from: Taobao */
class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f202a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f203b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f204c;
    final /* synthetic */ b d;

    d(b bVar, long j, long j2, long j3) {
        this.d = bVar;
        this.f202a = j;
        this.f203b = j2;
        this.f204c = j3;
    }

    public void run() {
        int i = 5;
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.BandWidthSampler", "onDataReceived", (String) null, "mRequestStartTime", Long.valueOf(this.f202a), "mRequestFinishedTime", Long.valueOf(this.f203b), "mRequestDataSize", Long.valueOf(this.f204c));
        }
        if (b.k && this.f204c > 3000 && this.f202a < this.f203b) {
            b.f197a++;
            b.e += this.f204c;
            if (b.f197a == 1) {
                b.d = this.f203b - this.f202a;
            }
            if (b.f197a >= 2 && b.f197a <= 3) {
                if (this.f202a >= b.f199c) {
                    b.d += this.f203b - this.f202a;
                } else if (this.f202a < b.f199c && this.f203b >= b.f199c) {
                    b.d += this.f203b - this.f202a;
                    b.d -= b.f199c - this.f202a;
                }
            }
            b.f198b = this.f202a;
            b.f199c = this.f203b;
            if (b.f197a == 3) {
                b.i = (double) ((long) this.d.n.a((double) b.e, (double) b.d));
                b.f++;
                b.b(this.d);
                if (b.f > 30) {
                    this.d.n.a();
                    b.f = 3;
                }
                double d2 = (b.i * 0.68d) + (b.h * 0.27d) + (b.g * 0.05d);
                b.g = b.h;
                b.h = b.i;
                if (b.i < b.g * 0.65d || b.i > b.g * 2.0d) {
                    b.i = d2;
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.BandWidthSampler", "NetworkSpeed", (String) null, "mKalmanDataSize", Long.valueOf(b.e), "mKalmanTimeUsed", Long.valueOf(b.d), "speed", Double.valueOf(b.i), "mSpeedKalmanCount", Long.valueOf(b.f));
                }
                if (this.d.m > 5 || b.f == 2) {
                    a.a().a(b.i);
                    int unused = this.d.m = 0;
                    b bVar = this.d;
                    if (b.i < b.j) {
                        i = 1;
                    }
                    int unused2 = bVar.l = i;
                    ALog.i("awcn.BandWidthSampler", "NetworkSpeed notification!", (String) null, "Send Network quality notification.");
                }
                b.d = 0;
                b.e = 0;
                b.f197a = 0;
            }
        }
    }
}
