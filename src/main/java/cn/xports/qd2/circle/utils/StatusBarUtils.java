package cn.xports.qd2.circle.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import anet.channel.strategy.dispatch.DispatchConstants;

public class StatusBarUtils {
    private Activity mActivity;
    private int mColor = -1;
    private int mContentResourseIdInDrawer;
    private Drawable mDrawable;
    private boolean mIsActionBar;
    private boolean mIsDrawerLayout;

    public StatusBarUtils(Activity activity) {
        this.mActivity = activity;
    }

    public static StatusBarUtils with(Activity activity) {
        return new StatusBarUtils(activity);
    }

    public int getColor() {
        return this.mColor;
    }

    public StatusBarUtils setColor(int i) {
        this.mColor = i;
        return this;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public StatusBarUtils setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
        return this;
    }

    public boolean isDrawerLayout() {
        return this.mIsDrawerLayout;
    }

    public boolean isActionBar() {
        return this.mIsActionBar;
    }

    public StatusBarUtils setIsActionBar(boolean z) {
        this.mIsActionBar = z;
        return this;
    }

    public StatusBarUtils setDrawerLayoutContentId(boolean z, int i) {
        this.mIsDrawerLayout = z;
        this.mContentResourseIdInDrawer = i;
        return this;
    }

    public void init() {
        fullScreen(this.mActivity);
        if (this.mColor != -1) {
            addStatusViewWithColor(this.mActivity, this.mColor);
        }
        if (this.mDrawable != null) {
            addStatusViewWithDrawble(this.mActivity, this.mDrawable);
        }
        if (isDrawerLayout()) {
            fitsSystemWindows(this.mActivity);
        }
        if (isActionBar() && Build.VERSION.SDK_INT >= 19) {
            ((ViewGroup) this.mActivity.getWindow().getDecorView().findViewById(16908290)).setPadding(0, getStatusBarHeight(this.mActivity) + getActionBarHeight(this.mActivity), 0, 0);
        }
    }

    public StatusBarUtils clearActionBarShadow() {
        ActionBar supportActionBar;
        if (Build.VERSION.SDK_INT >= 21 && (supportActionBar = ((AppCompatActivity) this.mActivity).getSupportActionBar()) != null) {
            supportActionBar.setElevation(0.0f);
        }
        return this;
    }

    private void fitsSystemWindows(Activity activity) {
        View childAt = ((ViewGroup) activity.findViewById(16908290)).getChildAt(0);
        if (childAt != null && Build.VERSION.SDK_INT >= 14) {
            childAt.setFitsSystemWindows(true);
            if (childAt instanceof DrawerLayout) {
                ((DrawerLayout) childAt).setClipToPadding(false);
            }
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID);
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        Log.e("getStatusBarHeight", dimensionPixelSize + "");
        return dimensionPixelSize;
    }

    public static int getActionBarHeight(Context context) {
        if (Build.VERSION.SDK_INT < 14) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843499, typedValue, true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    private void addStatusViewWithColor(Activity activity, int i) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (isDrawerLayout()) {
            View childAt = ((ViewGroup) activity.findViewById(16908290)).getChildAt(0);
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(1);
            View view = new View(activity);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, getStatusBarHeight(activity));
            view.setBackgroundColor(i);
            linearLayout.addView(view, layoutParams);
            DrawerLayout drawerLayout = (DrawerLayout) childAt;
            View findViewById = activity.findViewById(this.mContentResourseIdInDrawer);
            drawerLayout.removeView(findViewById);
            linearLayout.addView(findViewById, findViewById.getLayoutParams());
            drawerLayout.addView(linearLayout, 0);
            return;
        }
        ((ViewGroup) this.mActivity.getWindow().getDecorView().findViewById(16908290)).setPadding(0, getStatusBarHeight(this.mActivity), 0, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(i);
            return;
        }
        View view2 = new View(activity);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, getStatusBarHeight(activity));
        view2.setBackgroundColor(i);
        ((ViewGroup) this.mActivity.getWindow().getDecorView()).addView(view2, layoutParams2);
    }

    private void addStatusViewWithDrawble(Activity activity, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            View view = new View(activity);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, getStatusBarHeight(activity));
            if (Build.VERSION.SDK_INT >= 16) {
                view.setBackground(drawable);
            } else {
                view.setBackgroundDrawable(drawable);
            }
            if (isDrawerLayout()) {
                View childAt = ((ViewGroup) activity.findViewById(16908290)).getChildAt(0);
                LinearLayout linearLayout = new LinearLayout(activity);
                linearLayout.setOrientation(1);
                linearLayout.addView(view, layoutParams);
                DrawerLayout drawerLayout = (DrawerLayout) childAt;
                View findViewById = activity.findViewById(this.mContentResourseIdInDrawer);
                drawerLayout.removeView(findViewById);
                linearLayout.addView(findViewById, findViewById.getLayoutParams());
                drawerLayout.addView(linearLayout, 0);
                return;
            }
            ((ViewGroup) this.mActivity.getWindow().getDecorView()).addView(view, layoutParams);
            ((ViewGroup) this.mActivity.getWindow().getDecorView().findViewById(16908290)).setPadding(0, getStatusBarHeight(this.mActivity), 0, 0);
        }
    }

    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            window.clearFlags(67108864);
            decorView.setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            return;
        }
        Window window2 = activity.getWindow();
        WindowManager.LayoutParams attributes = window2.getAttributes();
        attributes.flags |= 67108864;
        window2.setAttributes(attributes);
    }

    public static void setStatusTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags |= 134217728;
            window.setAttributes(attributes);
            window.getDecorView().setSystemUiVisibility(1792);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
            return;
        }
        Window window2 = activity.getWindow();
        WindowManager.LayoutParams attributes2 = window2.getAttributes();
        attributes2.flags = 67108864 | attributes2.flags;
        attributes2.flags |= 134217728;
        window2.setAttributes(attributes2);
    }
}
