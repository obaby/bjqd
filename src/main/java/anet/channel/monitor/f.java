package anet.channel.monitor;

/* compiled from: Taobao */
public class f {

    /* renamed from: a  reason: collision with root package name */
    boolean f208a = false;

    /* renamed from: b  reason: collision with root package name */
    protected long f209b;

    /* renamed from: c  reason: collision with root package name */
    private final double f210c = 40.0d;
    private boolean d = true;

    public int a() {
        return 0;
    }

    public boolean a(double d2) {
        return d2 < 40.0d;
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        if (!this.d) {
            return false;
        }
        if (System.currentTimeMillis() - this.f209b <= ((long) (a() * 1000))) {
            return true;
        }
        this.d = false;
        return false;
    }
}
