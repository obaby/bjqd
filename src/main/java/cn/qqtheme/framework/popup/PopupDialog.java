package cn.qqtheme.framework.popup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import cn.qqtheme.framework.R;
import cn.qqtheme.framework.util.LogUtils;

class PopupDialog {
    private FrameLayout contentLayout;
    private Dialog dialog;

    PopupDialog(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.contentLayout = new FrameLayout(context);
        this.contentLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.contentLayout.setFocusable(true);
        this.contentLayout.setFocusableInTouchMode(true);
        int i = Build.VERSION.SDK_INT;
        this.dialog = new Dialog(context);
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.setCancelable(true);
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.Animation_Popup);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.requestFeature(1);
            window.setContentView(this.contentLayout);
        }
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.contentLayout.getContext();
    }

    /* access modifiers changed from: package-private */
    public void setAnimationStyle(@StyleRes int i) {
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isShowing() {
        return this.dialog.isShowing();
    }

    /* access modifiers changed from: package-private */
    @CallSuper
    public void show() {
        this.dialog.show();
    }

    /* access modifiers changed from: package-private */
    @CallSuper
    public void dismiss() {
        this.dialog.dismiss();
    }

    /* access modifiers changed from: package-private */
    public void setContentView(View view) {
        this.contentLayout.removeAllViews();
        this.contentLayout.addView(view);
    }

    /* access modifiers changed from: package-private */
    public View getContentView() {
        return this.contentLayout.getChildAt(0);
    }

    /* access modifiers changed from: package-private */
    public void setSize(int i, int i2) {
        LogUtils.verbose(String.format("will set popup width/height to: %s/%s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        ViewGroup.LayoutParams layoutParams = this.contentLayout.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(i, i2);
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        this.contentLayout.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.dialog.setOnDismissListener(onDismissListener);
    }

    /* access modifiers changed from: package-private */
    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        this.dialog.setOnKeyListener(onKeyListener);
    }

    /* access modifiers changed from: package-private */
    public Window getWindow() {
        return this.dialog.getWindow();
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getRootView() {
        return this.contentLayout;
    }
}
