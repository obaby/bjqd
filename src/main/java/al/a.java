package al;

import java.util.List;

public class a<T> implements c {

    /* renamed from: a  reason: collision with root package name */
    public static final int f101a = 4;

    /* renamed from: b  reason: collision with root package name */
    private List<T> f102b;

    /* renamed from: c  reason: collision with root package name */
    private int f103c;

    public a(List<T> list, int i) {
        this.f102b = list;
        this.f103c = i;
    }

    public a(List<T> list) {
        this(list, 4);
    }

    public Object a(int i) {
        return (i < 0 || i >= this.f102b.size()) ? "" : this.f102b.get(i);
    }

    public int a() {
        return this.f102b.size();
    }

    public int a(Object obj) {
        return this.f102b.indexOf(obj);
    }
}
