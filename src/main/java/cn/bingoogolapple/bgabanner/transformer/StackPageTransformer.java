package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class StackPageTransformer extends BGAPageTransformer {
    public void handleInvisiblePage(View view, float f) {
    }

    public void handleLeftPage(View view, float f) {
    }

    public void handleRightPage(View view, float f) {
        ViewCompat.setTranslationX(view, ((float) (-view.getWidth())) * f);
    }
}
