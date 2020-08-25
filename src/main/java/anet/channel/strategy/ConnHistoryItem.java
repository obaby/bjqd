package anet.channel.strategy;

import java.io.Serializable;

/* compiled from: Taobao */
class ConnHistoryItem implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    byte f249a = 0;

    /* renamed from: b  reason: collision with root package name */
    long f250b = 0;

    /* renamed from: c  reason: collision with root package name */
    long f251c = 0;

    ConnHistoryItem() {
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - (z ? this.f250b : this.f251c) > 10000) {
            this.f249a = (this.f249a << 1) | (z ^ true) ? (byte) 1 : 0;
            if (z) {
                this.f250b = currentTimeMillis;
            } else {
                this.f251c = currentTimeMillis;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int a() {
        int i = 0;
        for (int i2 = this.f249a & 255; i2 > 0; i2 >>= 1) {
            i += i2 & 1;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return (this.f249a & 1) == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        if (a() >= 3 && System.currentTimeMillis() - this.f251c <= 300000) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        long j = this.f250b > this.f251c ? this.f250b : this.f251c;
        return j != 0 && System.currentTimeMillis() - j > 86400000;
    }
}
