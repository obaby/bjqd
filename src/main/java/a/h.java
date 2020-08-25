package a;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    public static final int f50a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f51b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final int f52c = 3;
    public static final int d = 4;
    private int e = 1;
    private boolean f = false;

    @LayoutRes
    public abstract int d();

    /* access modifiers changed from: protected */
    @IdRes
    public abstract int e();

    /* access modifiers changed from: protected */
    @IdRes
    public abstract int f();

    /* access modifiers changed from: protected */
    @IdRes
    public abstract int g();

    public void a(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public void a(f fVar) {
        switch (this.e) {
            case 1:
                a(fVar, false);
                b(fVar, false);
                c(fVar, false);
                return;
            case 2:
                a(fVar, true);
                b(fVar, false);
                c(fVar, false);
                return;
            case 3:
                a(fVar, false);
                b(fVar, true);
                c(fVar, false);
                return;
            case 4:
                a(fVar, false);
                b(fVar, false);
                c(fVar, true);
                return;
            default:
                return;
        }
    }

    private void a(f fVar, boolean z) {
        fVar.b(e(), z);
    }

    private void b(f fVar, boolean z) {
        fVar.b(f(), z);
    }

    private void c(f fVar, boolean z) {
        int g = g();
        if (g != 0) {
            fVar.b(g, z);
        }
    }

    public final void a(boolean z) {
        this.f = z;
    }

    public final boolean b() {
        if (g() == 0) {
            return true;
        }
        return this.f;
    }

    @Deprecated
    public boolean c() {
        return this.f;
    }
}
