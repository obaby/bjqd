package b;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final float f442a = 0.0f;

    /* renamed from: b  reason: collision with root package name */
    private final float f443b;

    public a() {
        this(0.0f);
    }

    public a(float f) {
        this.f443b = f;
    }

    public Animator[] a(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{this.f443b, 1.0f})};
    }
}
