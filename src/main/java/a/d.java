package a;

import a.f;
import a.i;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import java.util.List;

public abstract class d<T extends i, K extends f> extends e<T, K> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f29a = -404;
    private static final int r = -255;
    private SparseIntArray q;

    public d(List<T> list) {
        super(list);
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        Object obj = this.l.get(i);
        return obj instanceof i ? ((i) obj).a() : r;
    }

    /* access modifiers changed from: protected */
    public void b(@LayoutRes int i) {
        a((int) r, i);
    }

    /* access modifiers changed from: protected */
    public K a(ViewGroup viewGroup, int i) {
        return c(viewGroup, o(i));
    }

    private int o(int i) {
        return this.q.get(i, -404);
    }

    /* access modifiers changed from: protected */
    public void a(int i, @LayoutRes int i2) {
        if (this.q == null) {
            this.q = new SparseIntArray();
        }
        this.q.put(i, i2);
    }
}
