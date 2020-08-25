package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class CubePageTransformer extends BGAPageTransformer {
    private float mMaxRotation = 90.0f;

    public CubePageTransformer() {
    }

    public CubePageTransformer(float f) {
        setMaxRotation(f);
    }

    public void handleInvisiblePage(View view, float f) {
        ViewCompat.setPivotX(view, (float) view.getMeasuredWidth());
        ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
        ViewCompat.setRotationY(view, 0.0f);
    }

    public void handleLeftPage(View view, float f) {
        ViewCompat.setPivotX(view, (float) view.getMeasuredWidth());
        ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
        ViewCompat.setRotationY(view, this.mMaxRotation * f);
    }

    public void handleRightPage(View view, float f) {
        ViewCompat.setPivotX(view, 0.0f);
        ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
        ViewCompat.setRotationY(view, this.mMaxRotation * f);
    }

    public void setMaxRotation(float f) {
        if (f >= 0.0f && f <= 90.0f) {
            this.mMaxRotation = f;
        }
    }
}
