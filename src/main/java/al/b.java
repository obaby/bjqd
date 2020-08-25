package al;

public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    public static final int f104a = 9;

    /* renamed from: b  reason: collision with root package name */
    private static final int f105b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f106c;
    private int d;

    public b() {
        this(0, 9);
    }

    public b(int i, int i2) {
        this.f106c = i;
        this.d = i2;
    }

    public Object a(int i) {
        if (i < 0 || i >= a()) {
            return 0;
        }
        return Integer.valueOf(this.f106c + i);
    }

    public int a() {
        return (this.d - this.f106c) + 1;
    }

    public int a(Object obj) {
        try {
            return ((Integer) obj).intValue() - this.f106c;
        } catch (Exception unused) {
            return -1;
        }
    }
}
