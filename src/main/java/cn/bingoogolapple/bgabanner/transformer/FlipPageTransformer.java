package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class FlipPageTransformer extends BGAPageTransformer {
    private static final float ROTATION = 180.0f;

    public void handleInvisiblePage(View view, float f) {
    }

    public void handleLeftPage(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setRotationY(view, ROTATION * f);
        if (((double) f) > -0.5d) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    public void handleRightPage(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
        ViewCompat.setRotationY(view, ROTATION * f);
        if (((double) f) < 0.5d) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }
}
