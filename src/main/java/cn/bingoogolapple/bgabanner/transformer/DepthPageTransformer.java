package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class DepthPageTransformer extends BGAPageTransformer {
    private float mMinScale = 0.8f;

    public DepthPageTransformer() {
    }

    public DepthPageTransformer(float f) {
        setMinScale(f);
    }

    public void handleInvisiblePage(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    public void handleLeftPage(View view, float f) {
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setScaleX(view, 1.0f);
        ViewCompat.setScaleY(view, 1.0f);
    }

    public void handleRightPage(View view, float f) {
        float f2 = 1.0f - f;
        ViewCompat.setAlpha(view, f2);
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        float f3 = this.mMinScale + ((1.0f - this.mMinScale) * f2);
        ViewCompat.setScaleX(view, f3);
        ViewCompat.setScaleY(view, f3);
    }

    public void setMinScale(float f) {
        if (f >= 0.6f && f <= 1.0f) {
            this.mMinScale = f;
        }
    }
}
