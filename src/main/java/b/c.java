package b;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final float f444a = 0.5f;

    /* renamed from: b  reason: collision with root package name */
    private final float f445b;

    public c() {
        this(f444a);
    }

    public c(float f) {
        this.f445b = f;
    }

    public Animator[] a(View view) {
        return new ObjectAnimator[]{ObjectAnimator.ofFloat(view, "scaleX", new float[]{this.f445b, 1.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{this.f445b, 1.0f})};
    }
}
