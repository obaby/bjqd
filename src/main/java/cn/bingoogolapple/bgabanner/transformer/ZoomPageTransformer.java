package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class ZoomPageTransformer extends BGAPageTransformer {
    private float mMinAlpha = 0.65f;
    private float mMinScale = 0.85f;

    public ZoomPageTransformer() {
    }

    public ZoomPageTransformer(float f, float f2) {
        setMinAlpha(f);
        setMinScale(f2);
    }

    public void handleInvisiblePage(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    public void handleLeftPage(View view, float f) {
        float max = Math.max(this.mMinScale, f + 1.0f);
        float f2 = 1.0f - max;
        ViewCompat.setTranslationX(view, ((((float) view.getWidth()) * f2) / 2.0f) - (((((float) view.getHeight()) * f2) / 2.0f) / 2.0f));
        ViewCompat.setScaleX(view, max);
        ViewCompat.setScaleY(view, max);
        ViewCompat.setAlpha(view, this.mMinAlpha + (((max - this.mMinScale) / (1.0f - this.mMinScale)) * (1.0f - this.mMinAlpha)));
    }

    public void handleRightPage(View view, float f) {
        float max = Math.max(this.mMinScale, 1.0f - f);
        float f2 = 1.0f - max;
        ViewCompat.setTranslationX(view, (-((((float) view.getWidth()) * f2) / 2.0f)) + (((((float) view.getHeight()) * f2) / 2.0f) / 2.0f));
        ViewCompat.setScaleX(view, max);
        ViewCompat.setScaleY(view, max);
        ViewCompat.setAlpha(view, this.mMinAlpha + (((max - this.mMinScale) / (1.0f - this.mMinScale)) * (1.0f - this.mMinAlpha)));
    }

    public void setMinAlpha(float f) {
        if (f >= 0.6f && f <= 1.0f) {
            this.mMinAlpha = f;
        }
    }

    public void setMinScale(float f) {
        if (f >= 0.6f && f <= 1.0f) {
            this.mMinScale = f;
        }
    }
}
