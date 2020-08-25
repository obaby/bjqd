package cn.xports.qd2.circle;

import android.content.Context;

public class ScreenDistUtils {
    public static int dp2px(Context context, float f) {
        float f2 = context.getResources().getDisplayMetrics().density;
        return f < 0.0f ? -((int) (((-f) * f2) + 0.5f)) : (int) ((f * f2) + 0.5f);
    }
}
