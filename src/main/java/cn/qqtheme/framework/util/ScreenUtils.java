package cn.qqtheme.framework.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public final class ScreenUtils {
    private static boolean isFullScreen;

    public static DisplayMetrics displayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        LogUtils.verbose("screen width=" + displayMetrics.widthPixels + "px, screen height=" + displayMetrics.heightPixels + "px, densityDpi=" + displayMetrics.densityDpi + ", density=" + displayMetrics.density);
        return displayMetrics;
    }

    public static int widthPixels(Context context) {
        return displayMetrics(context).widthPixels;
    }

    public static int heightPixels(Context context) {
        return displayMetrics(context).heightPixels;
    }

    public static float density(Context context) {
        return displayMetrics(context).density;
    }

    public static int densityDpi(Context context) {
        return displayMetrics(context).densityDpi;
    }

    public static boolean isFullScreen() {
        return isFullScreen;
    }

    public static void toggleFullScreen(Activity activity) {
        Window window = activity.getWindow();
        if (isFullScreen) {
            window.clearFlags(1024);
            isFullScreen = false;
            return;
        }
        window.setFlags(1024, 1024);
        isFullScreen = true;
    }

    public static void keepBright(Activity activity) {
        activity.getWindow().setFlags(128, 128);
    }
}
