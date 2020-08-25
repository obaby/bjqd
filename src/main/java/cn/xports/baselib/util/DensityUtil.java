package cn.xports.baselib.util;

import cn.xports.baselib.App;

public class DensityUtil {
    public static int dp2px(float f) {
        return (int) ((f * App.getInstance().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(float f) {
        return (int) ((f / App.getInstance().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth() {
        return App.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return App.getInstance().getResources().getDisplayMetrics().heightPixels;
    }
}
