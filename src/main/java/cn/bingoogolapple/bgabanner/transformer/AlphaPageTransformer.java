package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class AlphaPageTransformer extends BGAPageTransformer {
    private float mMinScale = 0.4f;

    public AlphaPageTransformer() {
    }

    public AlphaPageTransformer(float f) {
        setMinScale(f);
    }

    public void handleInvisiblePage(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    public void handleLeftPage(View view, float f) {
        ViewCompat.setAlpha(view, this.mMinScale + ((1.0f - this.mMinScale) * (f + 1.0f)));
    }

    public void handleRightPage(View view, float f) {
        ViewCompat.setAlpha(view, this.mMinScale + ((1.0f - this.mMinScale) * (1.0f - f)));
    }

    public void setMinScale(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.mMinScale = f;
        }
    }
}
