package cn.xports.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import cn.xports.baselib.App;

public final class BarUtils {
    private static final int KEY_OFFSET = -123;
    private static final String TAG_OFFSET = "TAG_OFFSET";
    private static final String TAG_STATUS_BAR = "TAG_STATUS_BAR";

    private BarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getStatusBarHeight() {
        Resources resources = App.getInstance().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID));
    }

    public static void setStatusBarVisibility(@NonNull Activity activity, boolean z) {
        setStatusBarVisibility(activity.getWindow(), z);
    }

    public static void setStatusBarVisibility(@NonNull Window window, boolean z) {
        if (z) {
            window.clearFlags(1024);
            showStatusBarView(window);
            addMarginTopEqualStatusBarHeight(window);
            return;
        }
        window.addFlags(1024);
        hideStatusBarView(window);
        subtractMarginTopEqualStatusBarHeight(window);
    }

    public static boolean isStatusBarVisible(@NonNull Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 0;
    }

    public static void setStatusBarLightMode(@NonNull Activity activity, boolean z) {
        setStatusBarLightMode(activity.getWindow(), z);
    }

    public static void setStatusBarLightMode(@NonNull Window window, boolean z) {
        View decorView;
        if (Build.VERSION.SDK_INT >= 23 && (decorView = window.getDecorView()) != null) {
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & -8193);
        }
    }

    public static boolean isStatusBarLightMode(@NonNull Activity activity) {
        return isStatusBarLightMode(activity.getWindow());
    }

    public static boolean isStatusBarLightMode(@NonNull Window window) {
        View decorView;
        if (Build.VERSION.SDK_INT < 23 || (decorView = window.getDecorView()) == null || (decorView.getSystemUiVisibility() & 8192) == 0) {
            return false;
        }
        return true;
    }

    public static void addMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setTag(TAG_OFFSET);
            Object tag = view.getTag(KEY_OFFSET);
            if (tag == null || !((Boolean) tag).booleanValue()) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(KEY_OFFSET, true);
            }
        }
    }

    public static void subtractMarginTopEqualStatusBarHeight(@NonNull View view) {
        Object tag;
        if (Build.VERSION.SDK_INT >= 19 && (tag = view.getTag(KEY_OFFSET)) != null && ((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(KEY_OFFSET, false);
        }
    }

    private static void addMarginTopEqualStatusBarHeight(Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET)) != null) {
            addMarginTopEqualStatusBarHeight(findViewWithTag);
        }
    }

    private static void subtractMarginTopEqualStatusBarHeight(Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET)) != null) {
            subtractMarginTopEqualStatusBarHeight(findViewWithTag);
        }
    }

    public static View setStatusBarColor(@NonNull Activity activity, @ColorInt int i) {
        return setStatusBarColor(activity, i, false);
    }

    public static View setStatusBarColor(@NonNull Activity activity, @ColorInt int i, boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        transparentStatusBar(activity);
        return applyStatusBarColor(activity, i, z);
    }

    public static void setStatusBarColor(@NonNull View view, @ColorInt int i) {
        Activity activityByView;
        if (Build.VERSION.SDK_INT >= 19 && (activityByView = getActivityByView(view)) != null) {
            transparentStatusBar(activityByView);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
            view.setBackgroundColor(i);
        }
    }

    public static void setStatusBarCustom(@NonNull View view) {
        Activity activityByView;
        if (Build.VERSION.SDK_INT >= 19 && (activityByView = getActivityByView(view)) != null) {
            transparentStatusBar(activityByView);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-1, getStatusBarHeight()));
                return;
            }
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
        }
    }

    public static void setStatusBarColor4Drawer(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i) {
        setStatusBarColor4Drawer(drawerLayout, view, i, false);
    }

    public static void setStatusBarColor4Drawer(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i, boolean z) {
        Activity activityByView;
        if (Build.VERSION.SDK_INT >= 19 && (activityByView = getActivityByView(view)) != null) {
            transparentStatusBar(activityByView);
            drawerLayout.setFitsSystemWindows(false);
            setStatusBarColor(view, i);
            int childCount = drawerLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                drawerLayout.getChildAt(i2).setFitsSystemWindows(false);
            }
            if (z) {
                hideStatusBarView(activityByView);
            } else {
                setStatusBarColor(activityByView, i, false);
            }
        }
    }

    private static View applyStatusBarColor(Activity activity, int i, boolean z) {
        ViewGroup viewGroup;
        if (z) {
            viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        } else {
            viewGroup = (ViewGroup) activity.findViewById(16908290);
        }
        View findViewWithTag = viewGroup.findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag != null) {
            if (findViewWithTag.getVisibility() == 8) {
                findViewWithTag.setVisibility(0);
            }
            findViewWithTag.setBackgroundColor(i);
            return findViewWithTag;
        }
        View createStatusBarView = createStatusBarView(activity, i);
        viewGroup.addView(createStatusBarView);
        return createStatusBarView;
    }

    private static void hideStatusBarView(Activity activity) {
        hideStatusBarView(activity.getWindow());
    }

    private static void hideStatusBarView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(8);
        }
    }

    private static void showStatusBarView(Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(0);
        }
    }

    private static View createStatusBarView(Activity activity, int i) {
        View view = new View(activity);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, getStatusBarHeight()));
        view.setBackgroundColor(i);
        view.setTag(TAG_STATUS_BAR);
        return view;
    }

    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= 21) {
                window.addFlags(Integer.MIN_VALUE);
                if (Build.VERSION.SDK_INT >= 23) {
                    window.getDecorView().setSystemUiVisibility(1280 | (window.getDecorView().getSystemUiVisibility() & 8192));
                } else {
                    window.getDecorView().setSystemUiVisibility(1280);
                }
                window.setStatusBarColor(0);
                return;
            }
            window.addFlags(67108864);
        }
    }

    public static int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (App.getInstance().getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, App.getInstance().getResources().getDisplayMetrics());
        }
        return 0;
    }

    @RequiresPermission("android.permission.EXPAND_STATUS_BAR")
    public static void setNotificationBarVisibility(boolean z) {
        String str;
        if (z) {
            str = Build.VERSION.SDK_INT <= 16 ? "expand" : "expandNotificationsPanel";
        } else {
            str = Build.VERSION.SDK_INT <= 16 ? "collapse" : "collapsePanels";
        }
        invokePanels(str);
    }

    private static void invokePanels(String str) {
        try {
            Class.forName("android.app.StatusBarManager").getMethod(str, new Class[0]).invoke(App.getInstance().getSystemService("statusbar"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNavBarHeight() {
        Resources resources = App.getInstance().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", DispatchConstants.ANDROID);
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setNavBarVisibility(@NonNull Activity activity, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            setNavBarVisibility(activity.getWindow(), z);
        }
    }

    public static void setNavBarVisibility(@NonNull Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                int id = childAt.getId();
                if (id != -1 && "navigationBarBackground".equals(App.getInstance().getResources().getResourceEntryName(id))) {
                    childAt.setVisibility(z ? 0 : 4);
                }
            }
            if (z) {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() & -4611);
            } else {
                viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() | 4610);
            }
        }
    }

    public static boolean isNavBarVisible(@NonNull Activity activity) {
        return isNavBarVisible(activity.getWindow());
    }

    public static boolean isNavBarVisible(@NonNull Window window) {
        boolean z;
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                z = false;
                break;
            }
            View childAt = viewGroup.getChildAt(i);
            int id = childAt.getId();
            if (id != -1 && "navigationBarBackground".equals(App.getInstance().getResources().getResourceEntryName(id)) && childAt.getVisibility() == 0) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return (viewGroup.getSystemUiVisibility() & 2) == 0;
        }
        return z;
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Activity activity, @ColorInt int i) {
        setNavBarColor(activity.getWindow(), i);
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Window window, @ColorInt int i) {
        window.setNavigationBarColor(i);
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Activity activity) {
        return getNavBarColor(activity.getWindow());
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Window window) {
        return window.getNavigationBarColor();
    }

    public static boolean isSupportNavBar() {
        if (Build.VERSION.SDK_INT >= 17) {
            WindowManager windowManager = (WindowManager) App.getInstance().getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y == point.y && point2.x == point.x) {
                return false;
            }
            return true;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(App.getInstance()).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    private static Activity getActivityByView(@NonNull View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        Log.e("BarUtils", "the view's Context is not an Activity.");
        return null;
    }
}
