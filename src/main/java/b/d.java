package b;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class d implements b {
    public Animator[] a(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) view.getMeasuredHeight(), 0.0f})};
    }
}
