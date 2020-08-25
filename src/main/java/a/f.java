package a;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class f extends RecyclerView.ViewHolder {
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public View f45a;

    /* renamed from: b  reason: collision with root package name */
    Object f46b;

    /* renamed from: c  reason: collision with root package name */
    private final SparseArray<View> f47c = new SparseArray<>();
    private final HashSet<Integer> d = new HashSet<>();
    private final LinkedHashSet<Integer> e = new LinkedHashSet<>();
    private final LinkedHashSet<Integer> f = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public e g;

    public Set<Integer> a() {
        return this.d;
    }

    public f(View view) {
        super(view);
        this.f45a = view;
    }

    public int b() {
        return getLayoutPosition() - this.g.o();
    }

    public HashSet<Integer> c() {
        return this.f;
    }

    public HashSet<Integer> d() {
        return this.e;
    }

    @Deprecated
    public View e() {
        return this.f45a;
    }

    public f a(@IdRes int i, CharSequence charSequence) {
        ((TextView) e(i)).setText(charSequence);
        return this;
    }

    public f a(@IdRes int i, @StringRes int i2) {
        ((TextView) e(i)).setText(i2);
        return this;
    }

    public f b(@IdRes int i, @DrawableRes int i2) {
        ((ImageView) e(i)).setImageResource(i2);
        return this;
    }

    public f c(@IdRes int i, @ColorInt int i2) {
        e(i).setBackgroundColor(i2);
        return this;
    }

    public f d(@IdRes int i, @DrawableRes int i2) {
        e(i).setBackgroundResource(i2);
        return this;
    }

    public f e(@IdRes int i, @ColorInt int i2) {
        ((TextView) e(i)).setTextColor(i2);
        return this;
    }

    public f a(@IdRes int i, Drawable drawable) {
        ((ImageView) e(i)).setImageDrawable(drawable);
        return this;
    }

    public f a(@IdRes int i, Bitmap bitmap) {
        ((ImageView) e(i)).setImageBitmap(bitmap);
        return this;
    }

    public f a(@IdRes int i, float f2) {
        if (Build.VERSION.SDK_INT >= 11) {
            e(i).setAlpha(f2);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f2);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            e(i).startAnimation(alphaAnimation);
        }
        return this;
    }

    public f a(@IdRes int i, boolean z) {
        e(i).setVisibility(z ? 0 : 8);
        return this;
    }

    public f b(@IdRes int i, boolean z) {
        e(i).setVisibility(z ? 0 : 4);
        return this;
    }

    public f a(@IdRes int i) {
        Linkify.addLinks((TextView) e(i), 15);
        return this;
    }

    public f a(@IdRes int i, Typeface typeface) {
        TextView textView = (TextView) e(i);
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | 128);
        return this;
    }

    public f a(Typeface typeface, int... iArr) {
        for (int e2 : iArr) {
            TextView textView = (TextView) e(e2);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 128);
        }
        return this;
    }

    public f f(@IdRes int i, int i2) {
        ((ProgressBar) e(i)).setProgress(i2);
        return this;
    }

    public f a(@IdRes int i, int i2, int i3) {
        ProgressBar progressBar = (ProgressBar) e(i);
        progressBar.setMax(i3);
        progressBar.setProgress(i2);
        return this;
    }

    public f g(@IdRes int i, int i2) {
        ((ProgressBar) e(i)).setMax(i2);
        return this;
    }

    public f b(@IdRes int i, float f2) {
        ((RatingBar) e(i)).setRating(f2);
        return this;
    }

    public f a(@IdRes int i, float f2, int i2) {
        RatingBar ratingBar = (RatingBar) e(i);
        ratingBar.setMax(i2);
        ratingBar.setRating(f2);
        return this;
    }

    @Deprecated
    public f a(@IdRes int i, View.OnClickListener onClickListener) {
        e(i).setOnClickListener(onClickListener);
        return this;
    }

    public f b(@IdRes int i) {
        this.e.add(Integer.valueOf(i));
        View e2 = e(i);
        if (e2 != null) {
            if (!e2.isClickable()) {
                e2.setClickable(true);
            }
            e2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (f.this.g.E() != null) {
                        f.this.g.E().onItemChildClick(f.this.g, view, f.this.b());
                    }
                }
            });
        }
        return this;
    }

    public f c(@IdRes int i) {
        b(i);
        d(i);
        this.d.add(Integer.valueOf(i));
        return this;
    }

    public f d(@IdRes int i) {
        this.f.add(Integer.valueOf(i));
        View e2 = e(i);
        if (e2 != null) {
            if (!e2.isLongClickable()) {
                e2.setLongClickable(true);
            }
            e2.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return f.this.g.F() != null && f.this.g.F().a(f.this.g, view, f.this.b());
                }
            });
        }
        return this;
    }

    @Deprecated
    public f a(@IdRes int i, View.OnTouchListener onTouchListener) {
        e(i).setOnTouchListener(onTouchListener);
        return this;
    }

    @Deprecated
    public f a(@IdRes int i, View.OnLongClickListener onLongClickListener) {
        e(i).setOnLongClickListener(onLongClickListener);
        return this;
    }

    @Deprecated
    public f a(@IdRes int i, AdapterView.OnItemClickListener onItemClickListener) {
        ((AdapterView) e(i)).setOnItemClickListener(onItemClickListener);
        return this;
    }

    public f a(@IdRes int i, AdapterView.OnItemLongClickListener onItemLongClickListener) {
        ((AdapterView) e(i)).setOnItemLongClickListener(onItemLongClickListener);
        return this;
    }

    public f a(@IdRes int i, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        ((AdapterView) e(i)).setOnItemSelectedListener(onItemSelectedListener);
        return this;
    }

    public f a(@IdRes int i, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        ((CompoundButton) e(i)).setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

    public f a(@IdRes int i, Object obj) {
        e(i).setTag(obj);
        return this;
    }

    public f a(@IdRes int i, int i2, Object obj) {
        e(i).setTag(i2, obj);
        return this;
    }

    public f c(@IdRes int i, boolean z) {
        View e2 = e(i);
        if (e2 instanceof Checkable) {
            ((Checkable) e2).setChecked(z);
        }
        return this;
    }

    public f a(@IdRes int i, Adapter adapter) {
        ((AdapterView) e(i)).setAdapter(adapter);
        return this;
    }

    /* access modifiers changed from: protected */
    public f a(e eVar) {
        this.g = eVar;
        return this;
    }

    public <T extends View> T e(@IdRes int i) {
        T t = (View) this.f47c.get(i);
        if (t != null) {
            return t;
        }
        T findViewById = this.itemView.findViewById(i);
        this.f47c.put(i, findViewById);
        return findViewById;
    }

    public Object f() {
        return this.f46b;
    }

    public void a(Object obj) {
        this.f46b = obj;
    }
}
