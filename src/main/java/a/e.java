package a;

import a.f;
import android.animation.Animator;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class e<T, K extends f> extends RecyclerView.Adapter<K> {

    /* renamed from: b  reason: collision with root package name */
    public static final int f30b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f31c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    protected static final String h = "e";
    public static final int m = 273;
    public static final int n = 546;
    public static final int o = 819;
    public static final int p = 1365;
    private boolean A;
    private Interpolator B;
    private int C;
    private int D;
    private b.b E;
    private b.b F;
    private LinearLayout G;
    private LinearLayout H;
    private FrameLayout I;
    private boolean J;
    private boolean K;
    private boolean L;
    private RecyclerView M;
    private boolean N;
    private boolean O;
    private i P;
    private int Q;
    private boolean R;
    private boolean S;
    /* access modifiers changed from: private */
    public h T;
    private j<T> U;
    private int V;

    /* renamed from: a  reason: collision with root package name */
    private boolean f32a;
    public b g;
    protected Context i;
    protected int j;
    protected LayoutInflater k;
    protected List<T> l;
    private boolean q;
    private boolean r;
    /* access modifiers changed from: private */
    public h s;
    /* access modifiers changed from: private */
    public g t;
    /* access modifiers changed from: private */
    public boolean u;
    private C0002e v;
    private f w;
    private c x;
    private d y;
    private boolean z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    public interface b<T> {
        void a(T t, int i);
    }

    public interface c {
        void onItemChildClick(e eVar, View view, int i);
    }

    public interface d {
        boolean a(e eVar, View view, int i);
    }

    /* renamed from: a.e$e  reason: collision with other inner class name */
    public interface C0002e {
        void onItemClick(e eVar, View view, int i);
    }

    public interface f {
        boolean a(e eVar, View view, int i);
    }

    public interface g {
        void l();
    }

    public interface h {
        int a(GridLayoutManager gridLayoutManager, int i);
    }

    public interface i {
        void a();
    }

    /* access modifiers changed from: protected */
    public abstract void a(K k2, T t2);

    public long getItemId(int i2) {
        return (long) i2;
    }

    /* access modifiers changed from: protected */
    public boolean h(int i2) {
        return i2 == 1365 || i2 == 273 || i2 == 819 || i2 == 546;
    }

    /* access modifiers changed from: protected */
    public RecyclerView a() {
        return this.M;
    }

    private void c(RecyclerView recyclerView) {
        this.M = recyclerView;
    }

    private void G() {
        if (a() == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    public void a(RecyclerView recyclerView) {
        if (a() == null) {
            c(recyclerView);
            a().setAdapter(this);
            return;
        }
        throw new RuntimeException("Don't bind twice");
    }

    @Deprecated
    public void a(g gVar) {
        b(gVar);
    }

    private void b(g gVar) {
        this.t = gVar;
        this.f32a = true;
        this.q = true;
        this.r = false;
    }

    public void a(g gVar, RecyclerView recyclerView) {
        b(gVar);
        if (a() == null) {
            c(recyclerView);
        }
    }

    public void b() {
        G();
        b(a());
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager;
        d(false);
        if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null) {
            if (layoutManager instanceof LinearLayoutManager) {
                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1 != e.this.getItemCount()) {
                            e.this.d(true);
                        }
                    }
                }, 50);
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
                        staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(iArr);
                        if (e.this.a(iArr) + 1 != e.this.getItemCount()) {
                            e.this.d(true);
                        }
                    }
                }, 50);
            }
        }
    }

    /* access modifiers changed from: private */
    public int a(int[] iArr) {
        int i2 = -1;
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    public void a(boolean z2) {
        this.N = z2;
    }

    public boolean c() {
        return this.N;
    }

    public void c(int i2) {
        this.Q = i2;
    }

    private void b(int i2) {
        if (c() && !d() && i2 <= this.Q && this.P != null) {
            this.P.a();
        }
    }

    public boolean d() {
        return this.O;
    }

    public void b(boolean z2) {
        this.O = z2;
    }

    public void a(i iVar) {
        this.P = iVar;
    }

    public void d(int i2) {
        this.D = i2;
    }

    public void a(h hVar) {
        this.s = hVar;
    }

    public int e() {
        if (this.t == null || !this.q) {
            return 0;
        }
        if ((this.f32a || !this.s.b()) && this.l.size() != 0) {
            return 1;
        }
        return 0;
    }

    public int f() {
        return o() + this.l.size() + p();
    }

    public boolean g() {
        return this.r;
    }

    public void h() {
        c(false);
    }

    public void c(boolean z2) {
        if (e() != 0) {
            this.r = false;
            this.f32a = false;
            this.s.a(z2);
            if (z2) {
                notifyItemRemoved(f());
                return;
            }
            this.s.a(4);
            notifyItemChanged(f());
        }
    }

    public void i() {
        if (e() != 0) {
            this.r = false;
            this.f32a = true;
            this.s.a(1);
            notifyItemChanged(f());
        }
    }

    public void j() {
        if (e() != 0) {
            this.r = false;
            this.s.a(3);
            notifyItemChanged(f());
        }
    }

    public void d(boolean z2) {
        int e2 = e();
        this.q = z2;
        int e3 = e();
        if (e2 == 1) {
            if (e3 == 0) {
                notifyItemRemoved(f());
            }
        } else if (e3 == 1) {
            this.s.a(1);
            notifyItemInserted(f());
        }
    }

    public boolean k() {
        return this.q;
    }

    public void e(int i2) {
        this.C = i2;
    }

    public e(@LayoutRes int i2, @Nullable List<T> list) {
        this.f32a = false;
        this.q = false;
        this.r = false;
        this.s = new k();
        this.u = false;
        this.z = true;
        this.A = false;
        this.B = new LinearInterpolator();
        this.C = 300;
        this.D = -1;
        this.F = new b.a();
        this.J = true;
        this.Q = 1;
        this.V = 1;
        this.l = list == null ? new ArrayList<>() : list;
        if (i2 != 0) {
            this.j = i2;
        }
    }

    public e(@Nullable List<T> list) {
        this(0, list);
    }

    public e(@LayoutRes int i2) {
        this(i2, (List) null);
    }

    public void a(@Nullable List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.l = list;
        if (this.t != null) {
            this.f32a = true;
            this.q = true;
            this.r = false;
            this.s.a(1);
        }
        this.D = -1;
        notifyDataSetChanged();
    }

    @Deprecated
    public void a(@IntRange(from = 0) int i2, @NonNull T t2) {
        b(i2, t2);
    }

    public void b(@IntRange(from = 0) int i2, @NonNull T t2) {
        this.l.add(i2, t2);
        notifyItemInserted(i2 + o());
        o(1);
    }

    public void a(@NonNull T t2) {
        this.l.add(t2);
        notifyItemInserted(this.l.size() + o());
        o(1);
    }

    public void f(@IntRange(from = 0) int i2) {
        this.l.remove(i2);
        int o2 = i2 + o();
        notifyItemRemoved(o2);
        o(0);
        notifyItemRangeChanged(o2, this.l.size() - o2);
    }

    public void c(@IntRange(from = 0) int i2, @NonNull T t2) {
        this.l.set(i2, t2);
        notifyItemChanged(i2 + o());
    }

    public void a(@IntRange(from = 0) int i2, @NonNull Collection<? extends T> collection) {
        this.l.addAll(i2, collection);
        notifyItemRangeInserted(i2 + o(), collection.size());
        o(collection.size());
    }

    public void a(@NonNull Collection<? extends T> collection) {
        this.l.addAll(collection);
        notifyItemRangeInserted((this.l.size() - collection.size()) + o(), collection.size());
        o(collection.size());
    }

    public void b(@NonNull Collection<? extends T> collection) {
        this.l.clear();
        if (collection != null && collection.size() > 0) {
            this.l.addAll(collection);
        }
        notifyDataSetChanged();
    }

    private void o(int i2) {
        if ((this.l == null ? 0 : this.l.size()) == i2) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    public List<T> l() {
        return this.l;
    }

    @Nullable
    public T g(@IntRange(from = 0) int i2) {
        if (i2 < this.l.size()) {
            return this.l.get(i2);
        }
        return null;
    }

    @Deprecated
    public int m() {
        return o();
    }

    @Deprecated
    public int n() {
        return p();
    }

    public int o() {
        return (this.G == null || this.G.getChildCount() == 0) ? 0 : 1;
    }

    public int p() {
        return (this.H == null || this.H.getChildCount() == 0) ? 0 : 1;
    }

    public int q() {
        if (this.I == null || this.I.getChildCount() == 0 || !this.J || this.l.size() != 0) {
            return 0;
        }
        return 1;
    }

    public int getItemCount() {
        int i2 = 1;
        if (q() == 1) {
            if (this.K && o() != 0) {
                i2 = 2;
            }
            if (!this.L || p() == 0) {
                return i2;
            }
            return i2 + 1;
        }
        return e() + o() + this.l.size() + p();
    }

    public int getItemViewType(int i2) {
        boolean z2 = true;
        if (q() == 1) {
            if (!this.K || o() == 0) {
                z2 = false;
            }
            switch (i2) {
                case 0:
                    if (z2) {
                        return m;
                    }
                    return p;
                case 1:
                    if (z2) {
                        return p;
                    }
                    return o;
                case 2:
                    return o;
                default:
                    return p;
            }
        } else {
            int o2 = o();
            if (i2 < o2) {
                return m;
            }
            int i3 = i2 - o2;
            int size = this.l.size();
            if (i3 < size) {
                return a(i3);
            }
            if (i3 - size < p()) {
                return o;
            }
            return n;
        }
    }

    /* access modifiers changed from: protected */
    public int a(int i2) {
        if (this.U != null) {
            return this.U.a(this.l, i2);
        }
        return super.getItemViewType(i2);
    }

    /* renamed from: b */
    public K onCreateViewHolder(ViewGroup viewGroup, int i2) {
        K k2;
        this.i = viewGroup.getContext();
        this.k = LayoutInflater.from(this.i);
        if (i2 == 273) {
            k2 = a((View) this.G);
        } else if (i2 == 546) {
            k2 = a(viewGroup);
        } else if (i2 == 819) {
            k2 = a((View) this.H);
        } else if (i2 != 1365) {
            k2 = a(viewGroup, i2);
            b((f) k2);
        } else {
            k2 = a((View) this.I);
        }
        k2.a(this);
        return k2;
    }

    private K a(ViewGroup viewGroup) {
        K a2 = a(b(this.s.d(), viewGroup));
        a2.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (e.this.s.a() == 3) {
                    e.this.r();
                }
                if (e.this.u && e.this.s.a() == 4) {
                    e.this.r();
                }
            }
        });
        return a2;
    }

    public void r() {
        if (this.s.a() != 2) {
            this.s.a(1);
            notifyItemChanged(f());
        }
    }

    public void e(boolean z2) {
        this.u = z2;
    }

    /* renamed from: a */
    public void onViewAttachedToWindow(K k2) {
        super.onViewAttachedToWindow(k2);
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
            a((RecyclerView.ViewHolder) k2);
        } else {
            b((RecyclerView.ViewHolder) k2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    int itemViewType = e.this.getItemViewType(i);
                    if (itemViewType == 273 && e.this.s()) {
                        return 1;
                    }
                    if (itemViewType == 819 && e.this.t()) {
                        return 1;
                    }
                    if (e.this.T != null) {
                        return e.this.h(itemViewType) ? gridLayoutManager.getSpanCount() : e.this.T.a(gridLayoutManager, i - e.this.o());
                    }
                    if (e.this.h(itemViewType)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public void f(boolean z2) {
        this.R = z2;
    }

    public boolean s() {
        return this.R;
    }

    public void g(boolean z2) {
        this.S = z2;
    }

    public boolean t() {
        return this.S;
    }

    public void a(h hVar) {
        this.T = hVar;
    }

    /* renamed from: a */
    public void onBindViewHolder(K k2, int i2) {
        b(i2);
        p(i2);
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 0) {
            a(k2, g(i2 - o()));
        } else if (itemViewType == 273) {
        } else {
            if (itemViewType == 546) {
                this.s.a((f) k2);
            } else if (itemViewType != 819 && itemViewType != 1365) {
                a(k2, g(i2 - o()));
            }
        }
    }

    private void b(final f fVar) {
        View view;
        if (fVar != null && (view = fVar.itemView) != null) {
            if (D() != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        e.this.D().onItemClick(e.this, view, fVar.getLayoutPosition() - e.this.o());
                    }
                });
            }
            if (C() != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return e.this.C().a(e.this, view, fVar.getLayoutPosition() - e.this.o());
                    }
                });
            }
        }
    }

    public void a(j<T> jVar) {
        this.U = jVar;
    }

    public j<T> u() {
        return this.U;
    }

    /* access modifiers changed from: protected */
    public K a(ViewGroup viewGroup, int i2) {
        int i3 = this.j;
        if (this.U != null) {
            i3 = this.U.a(i2);
        }
        return c(viewGroup, i3);
    }

    /* access modifiers changed from: protected */
    public K c(ViewGroup viewGroup, int i2) {
        return a(b(i2, viewGroup));
    }

    /* access modifiers changed from: protected */
    public K a(View view) {
        K k2;
        Class cls = getClass();
        Class cls2 = null;
        while (cls2 == null && cls != null) {
            cls2 = a(cls);
            cls = cls.getSuperclass();
        }
        if (cls2 == null) {
            k2 = new f(view);
        } else {
            k2 = a(cls2, view);
        }
        return k2 != null ? k2 : new f(view);
    }

    private K a(Class cls, View view) {
        try {
            if (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
                Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{View.class});
                declaredConstructor.setAccessible(true);
                return (f) declaredConstructor.newInstance(new Object[]{view});
            }
            Constructor declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{getClass(), View.class});
            declaredConstructor2.setAccessible(true);
            return (f) declaredConstructor2.newInstance(new Object[]{this, view});
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    private Class a(Class cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return null;
        }
        for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
            if (type instanceof Class) {
                Class cls2 = (Class) type;
                if (f.class.isAssignableFrom(cls2)) {
                    return cls2;
                }
            }
        }
        return null;
    }

    public LinearLayout v() {
        return this.G;
    }

    public LinearLayout w() {
        return this.H;
    }

    public int b(View view) {
        return a(view, -1);
    }

    public int a(View view, int i2) {
        return a(view, i2, 1);
    }

    public int a(View view, int i2, int i3) {
        int H2;
        if (this.G == null) {
            this.G = new LinearLayout(view.getContext());
            if (i3 == 1) {
                this.G.setOrientation(1);
                this.G.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.G.setOrientation(0);
                this.G.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.G.getChildCount();
        if (i2 < 0 || i2 > childCount) {
            i2 = childCount;
        }
        this.G.addView(view, i2);
        if (this.G.getChildCount() == 1 && (H2 = H()) != -1) {
            notifyItemInserted(H2);
        }
        return i2;
    }

    public int c(View view) {
        return b(view, 0, 1);
    }

    public int b(View view, int i2) {
        return b(view, i2, 1);
    }

    public int b(View view, int i2, int i3) {
        if (this.G == null || this.G.getChildCount() <= i2) {
            return a(view, i2, i3);
        }
        this.G.removeViewAt(i2);
        this.G.addView(view, i2);
        return i2;
    }

    public int d(View view) {
        return c(view, -1, 1);
    }

    public int c(View view, int i2) {
        return c(view, i2, 1);
    }

    public int c(View view, int i2, int i3) {
        int I2;
        if (this.H == null) {
            this.H = new LinearLayout(view.getContext());
            if (i3 == 1) {
                this.H.setOrientation(1);
                this.H.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.H.setOrientation(0);
                this.H.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.H.getChildCount();
        if (i2 < 0 || i2 > childCount) {
            i2 = childCount;
        }
        this.H.addView(view, i2);
        if (this.H.getChildCount() == 1 && (I2 = I()) != -1) {
            notifyItemInserted(I2);
        }
        return i2;
    }

    public int e(View view) {
        return d(view, 0, 1);
    }

    public int d(View view, int i2) {
        return d(view, i2, 1);
    }

    public int d(View view, int i2, int i3) {
        if (this.H == null || this.H.getChildCount() <= i2) {
            return c(view, i2, i3);
        }
        this.H.removeViewAt(i2);
        this.H.addView(view, i2);
        return i2;
    }

    public void f(View view) {
        int H2;
        if (o() != 0) {
            this.G.removeView(view);
            if (this.G.getChildCount() == 0 && (H2 = H()) != -1) {
                notifyItemRemoved(H2);
            }
        }
    }

    public void g(View view) {
        int I2;
        if (p() != 0) {
            this.H.removeView(view);
            if (this.H.getChildCount() == 0 && (I2 = I()) != -1) {
                notifyItemRemoved(I2);
            }
        }
    }

    public void x() {
        if (o() != 0) {
            this.G.removeAllViews();
            if (H() != -1) {
                notifyDataSetChanged();
            }
        }
    }

    public void y() {
        if (p() != 0) {
            this.H.removeAllViews();
            int I2 = I();
            if (I2 != -1) {
                notifyItemRemoved(I2);
            }
        }
    }

    private int H() {
        if (q() != 1 || this.K) {
            return 0;
        }
        return -1;
    }

    private int I() {
        int i2 = 1;
        if (q() != 1) {
            return o() + this.l.size();
        }
        if (this.K && o() != 0) {
            i2 = 2;
        }
        if (this.L) {
            return i2;
        }
        return -1;
    }

    public void a(int i2, ViewGroup viewGroup) {
        h(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
    }

    public void i(int i2) {
        G();
        a(i2, (ViewGroup) a());
    }

    public void h(View view) {
        boolean z2;
        int i2 = 0;
        if (this.I == null) {
            this.I = new FrameLayout(view.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams.width = layoutParams2.width;
                layoutParams.height = layoutParams2.height;
            }
            this.I.setLayoutParams(layoutParams);
            z2 = true;
        } else {
            z2 = false;
        }
        this.I.removeAllViews();
        this.I.addView(view);
        this.J = true;
        if (z2 && q() == 1) {
            if (this.K && o() != 0) {
                i2 = 1;
            }
            notifyItemInserted(i2);
        }
    }

    public void h(boolean z2) {
        a(z2, false);
    }

    public void a(boolean z2, boolean z3) {
        this.K = z2;
        this.L = z3;
    }

    public void i(boolean z2) {
        this.J = z2;
    }

    public View z() {
        return this.I;
    }

    @Deprecated
    public void j(int i2) {
        k(i2);
    }

    public void k(int i2) {
        if (i2 > 1) {
            this.V = i2;
        }
    }

    private void p(int i2) {
        if (e() != 0 && i2 >= getItemCount() - this.V && this.s.a() == 1) {
            this.s.a(2);
            if (!this.r) {
                this.r = true;
                if (a() != null) {
                    a().post(new Runnable() {
                        public void run() {
                            e.this.t.l();
                        }
                    });
                } else {
                    this.t.l();
                }
            }
        }
    }

    private void b(RecyclerView.ViewHolder viewHolder) {
        b.b bVar;
        if (!this.A) {
            return;
        }
        if (!this.z || viewHolder.getLayoutPosition() > this.D) {
            if (this.E != null) {
                bVar = this.E;
            } else {
                bVar = this.F;
            }
            for (Animator a2 : bVar.a(viewHolder.itemView)) {
                a(a2, viewHolder.getLayoutPosition());
            }
            this.D = viewHolder.getLayoutPosition();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Animator animator, int i2) {
        animator.setDuration((long) this.C).start();
        animator.setInterpolator(this.B);
    }

    /* access modifiers changed from: protected */
    public View b(@LayoutRes int i2, ViewGroup viewGroup) {
        return this.k.inflate(i2, viewGroup, false);
    }

    public void l(int i2) {
        this.A = true;
        this.E = null;
        switch (i2) {
            case 1:
                this.F = new b.a();
                return;
            case 2:
                this.F = new b.c();
                return;
            case 3:
                this.F = new b.d();
                return;
            case 4:
                this.F = new b.e();
                return;
            case 5:
                this.F = new b.f();
                return;
            default:
                return;
        }
    }

    public void a(b.b bVar) {
        this.A = true;
        this.E = bVar;
    }

    public void A() {
        this.A = true;
    }

    public void j(boolean z2) {
        this.z = z2;
    }

    @Nullable
    public View b(int i2, @IdRes int i3) {
        G();
        return a(a(), i2, i3);
    }

    @Nullable
    public View a(RecyclerView recyclerView, int i2, @IdRes int i3) {
        f fVar;
        if (recyclerView == null || (fVar = (f) recyclerView.findViewHolderForLayoutPosition(i2)) == null) {
            return null;
        }
        return fVar.e(i3);
    }

    private int a(int i2, @NonNull List list) {
        int size = (i2 + list.size()) - 1;
        int size2 = list.size() - 1;
        int i3 = 0;
        while (size2 >= 0) {
            if (list.get(size2) instanceof g) {
                g gVar = (g) list.get(size2);
                if (gVar.a() && a(gVar)) {
                    List b2 = gVar.b();
                    int i4 = size + 1;
                    this.l.addAll(i4, b2);
                    i3 += a(i4, b2);
                }
            }
            size2--;
            size--;
        }
        return i3;
    }

    public int a(@IntRange(from = 0) int i2, boolean z2, boolean z3) {
        int o2 = i2 - o();
        g r2 = r(o2);
        int i3 = 0;
        if (r2 == null) {
            return 0;
        }
        if (!a(r2)) {
            r2.a(false);
            return 0;
        }
        if (!r2.a()) {
            List b2 = r2.b();
            int i4 = o2 + 1;
            this.l.addAll(i4, b2);
            r2.a(true);
            i3 = a(i4, b2) + 0 + b2.size();
        }
        int o3 = o2 + o();
        if (z3) {
            if (z2) {
                notifyItemChanged(o3);
                notifyItemRangeInserted(o3 + 1, i3);
            } else {
                notifyDataSetChanged();
            }
        }
        return i3;
    }

    public int a(@IntRange(from = 0) int i2, boolean z2) {
        return a(i2, z2, true);
    }

    public int m(@IntRange(from = 0) int i2) {
        return a(i2, true, true);
    }

    public int b(int i2, boolean z2, boolean z3) {
        Object g2;
        int o2 = i2 - o();
        int i3 = o2 + 1;
        Object g3 = i3 < this.l.size() ? g(i3) : null;
        g r2 = r(o2);
        if (r2 == null || !a(r2)) {
            return 0;
        }
        int a2 = a(o() + o2, false, false);
        while (i3 < this.l.size() && (g2 = g(i3)) != g3) {
            if (b(g2)) {
                a2 += a(o() + i3, false, false);
            }
            i3++;
        }
        if (z3) {
            if (z2) {
                notifyItemRangeInserted(o2 + o() + 1, a2);
            } else {
                notifyDataSetChanged();
            }
        }
        return a2;
    }

    public int b(int i2, boolean z2) {
        return b(i2, true, !z2);
    }

    public void B() {
        for (int size = (this.l.size() - 1) + o(); size >= o(); size--) {
            b(size, false, false);
        }
    }

    private int q(@IntRange(from = 0) int i2) {
        Object g2 = g(i2);
        int i3 = 0;
        if (!b(g2)) {
            return 0;
        }
        g gVar = (g) g2;
        if (gVar.a()) {
            List b2 = gVar.b();
            for (int size = b2.size() - 1; size >= 0; size--) {
                Object obj = b2.get(size);
                int d2 = d(obj);
                if (d2 >= 0) {
                    if (obj instanceof g) {
                        i3 += q(d2);
                    }
                    this.l.remove(d2);
                    i3++;
                }
            }
        }
        return i3;
    }

    public int c(@IntRange(from = 0) int i2, boolean z2, boolean z3) {
        int o2 = i2 - o();
        g r2 = r(o2);
        if (r2 == null) {
            return 0;
        }
        int q2 = q(o2);
        r2.a(false);
        int o3 = o2 + o();
        if (z3) {
            if (z2) {
                notifyItemChanged(o3);
                notifyItemRangeRemoved(o3 + 1, q2);
            } else {
                notifyDataSetChanged();
            }
        }
        return q2;
    }

    public int n(@IntRange(from = 0) int i2) {
        return c(i2, true, true);
    }

    public int c(@IntRange(from = 0) int i2, boolean z2) {
        return c(i2, z2, true);
    }

    private int d(T t2) {
        if (t2 == null || this.l == null || this.l.isEmpty()) {
            return -1;
        }
        return this.l.indexOf(t2);
    }

    private boolean a(g gVar) {
        List b2;
        if (gVar == null || (b2 = gVar.b()) == null || b2.size() <= 0) {
            return false;
        }
        return true;
    }

    public boolean b(T t2) {
        return t2 != null && (t2 instanceof g);
    }

    private g r(int i2) {
        Object g2 = g(i2);
        if (b(g2)) {
            return (g) g2;
        }
        return null;
    }

    public int c(@NonNull T t2) {
        int d2 = d(t2);
        if (d2 == -1) {
            return -1;
        }
        int c2 = t2 instanceof g ? ((g) t2).c() : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        if (c2 == 0) {
            return d2;
        }
        if (c2 == -1) {
            return -1;
        }
        while (d2 >= 0) {
            T t3 = this.l.get(d2);
            if (t3 instanceof g) {
                g gVar = (g) t3;
                if (gVar.c() >= 0 && gVar.c() < c2) {
                    return d2;
                }
            }
            d2--;
        }
        return -1;
    }

    public void a(@Nullable C0002e eVar) {
        this.v = eVar;
    }

    public void a(c cVar) {
        this.x = cVar;
    }

    public void a(f fVar) {
        this.w = fVar;
    }

    public void a(d dVar) {
        this.y = dVar;
    }

    public final f C() {
        return this.w;
    }

    public final C0002e D() {
        return this.v;
    }

    @Nullable
    public final c E() {
        return this.x;
    }

    @Nullable
    public final d F() {
        return this.y;
    }

    public void a(b bVar) {
        this.g = bVar;
    }
}
