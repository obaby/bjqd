package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class RotatePageTransformer extends BGAPageTransformer {
    private float mMaxRotation = 15.0f;

    public RotatePageTransformer() {
    }

    public RotatePageTransformer(float f) {
        setMaxRotation(f);
    }

    public void handleInvisiblePage(View view, float f) {
        ViewCompat.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
        ViewCompat.setPivotY(view, (float) view.getMeasuredHeight());
        ViewCompat.setRotation(view, 0.0f);
    }

    public void handleLeftPage(View view, float f) {
        float f2 = this.mMaxRotation * f;
        ViewCompat.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
        ViewCompat.setPivotY(view, (float) view.getMeasuredHeight());
        ViewCompat.setRotation(view, f2);
    }

    public void handleRightPage(View view, float f) {
        handleLeftPage(view, f);
    }

    public void setMaxRotation(float f) {
        if (f >= 0.0f && f <= 40.0f) {
            this.mMaxRotation = f;
        }
    }
}
