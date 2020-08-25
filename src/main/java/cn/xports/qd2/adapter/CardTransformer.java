package cn.xports.qd2.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

public class CardTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 0.875f;

    public void transformPage(View view, float f) {
        if (f < 1.0f) {
            view.setScaleY(((1.0f - Math.abs(f)) * 0.125f) + MIN_SCALE);
        } else {
            view.setScaleY(MIN_SCALE);
        }
    }
}
