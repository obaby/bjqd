package a;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import java.util.List;

public abstract class j<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f53a = -255;

    /* renamed from: b  reason: collision with root package name */
    private SparseIntArray f54b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f55c;
    private boolean d;

    /* access modifiers changed from: protected */
    public abstract int a(T t);

    public j(SparseIntArray sparseIntArray) {
        this.f54b = sparseIntArray;
    }

    public j() {
    }

    public final int a(List<T> list, int i) {
        T t = list.get(i);
        return t != null ? a(t) : f53a;
    }

    public final int a(int i) {
        return this.f54b.get(i, -404);
    }

    private void b(int i, @LayoutRes int i2) {
        if (this.f54b == null) {
            this.f54b = new SparseIntArray();
        }
        this.f54b.put(i, i2);
    }

    public j a(@LayoutRes int... iArr) {
        this.f55c = true;
        a(this.d);
        for (int i = 0; i < iArr.length; i++) {
            b(i, iArr[i]);
        }
        return this;
    }

    public j a(int i, @LayoutRes int i2) {
        this.d = true;
        a(this.f55c);
        b(i, i2);
        return this;
    }

    private void a(boolean z) {
        if (z) {
            throw new RuntimeException("Don't mess two register mode");
        }
    }
}
