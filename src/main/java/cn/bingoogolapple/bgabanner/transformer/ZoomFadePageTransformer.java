package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class ZoomFadePageTransformer extends BGAPageTransformer {
    public void handleInvisiblePage(View view, float f) {
    }

    public void handleLeftPage(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setPivotX(view, ((float) view.getWidth()) * 0.5f);
        ViewCompat.setPivotY(view, ((float) view.getHeight()) * 0.5f);
        float f2 = f + 1.0f;
        ViewCompat.setScaleX(view, f2);
        ViewCompat.setScaleY(view, f2);
        ViewCompat.setAlpha(view, f2);
    }

    public void handleRightPage(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setPivotX(view, ((float) view.getWidth()) * 0.5f);
        ViewCompat.setPivotY(view, ((float) view.getHeight()) * 0.5f);
        float f2 = 1.0f - f;
        ViewCompat.setScaleX(view, f2);
        ViewCompat.setScaleY(view, f2);
        ViewCompat.setAlpha(view, f2);
    }
}
