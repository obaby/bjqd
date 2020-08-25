package cn.qqtheme.framework.popup;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import cn.qqtheme.framework.util.LogUtils;
import cn.qqtheme.framework.util.ScreenUtils;

public abstract class BasicPopup<V extends View> implements DialogInterface.OnKeyListener {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    protected Activity activity;
    private int gravity = 80;
    private int height = 0;
    private boolean isFillScreen = false;
    private boolean isHalfScreen = false;
    private boolean isPrepared = false;
    private PopupDialog popupDialog;
    protected int screenHeightPixels;
    protected int screenWidthPixels;
    private int width = 0;

    /* access modifiers changed from: protected */
    public abstract V makeContentView();

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void setContentViewAfter(V v) {
    }

    /* access modifiers changed from: protected */
    public void setContentViewBefore() {
    }

    public BasicPopup(Activity activity2) {
        this.activity = activity2;
        DisplayMetrics displayMetrics = ScreenUtils.displayMetrics(activity2);
        this.screenWidthPixels = displayMetrics.widthPixels;
        this.screenHeightPixels = displayMetrics.heightPixels;
        this.popupDialog = new PopupDialog(activity2);
        this.popupDialog.setOnKeyListener(this);
    }

    private void onShowPrepare() {
        if (!this.isPrepared) {
            this.popupDialog.getWindow().setGravity(this.gravity);
            setContentViewBefore();
            View makeContentView = makeContentView();
            this.popupDialog.setContentView(makeContentView);
            setContentViewAfter(makeContentView);
            LogUtils.verbose("do something before popup show");
            if (this.width == 0 && this.height == 0) {
                this.width = this.screenWidthPixels;
                if (this.isFillScreen) {
                    this.height = -1;
                } else if (this.isHalfScreen) {
                    this.height = this.screenHeightPixels / 2;
                } else {
                    this.height = -2;
                }
            } else if (this.width == 0) {
                this.width = this.screenWidthPixels;
            } else if (this.height == 0) {
                this.height = -2;
            }
            this.popupDialog.setSize(this.width, this.height);
            this.isPrepared = true;
        }
    }

    public void setFillScreen(boolean z) {
        this.isFillScreen = z;
    }

    public void setHalfScreen(boolean z) {
        this.isHalfScreen = z;
    }

    public void setGravity(int i) {
        this.gravity = i;
        if (i == 17) {
            double d = (double) this.screenWidthPixels;
            Double.isNaN(d);
            setWidth((int) (d * 0.7d));
        }
    }

    public void setAnimationStyle(@StyleRes int i) {
        this.popupDialog.setAnimationStyle(i);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.popupDialog.setOnDismissListener(onDismissListener);
        LogUtils.verbose("popup setOnDismissListener");
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public boolean isShowing() {
        return this.popupDialog.isShowing();
    }

    @CallSuper
    public void show() {
        onShowPrepare();
        this.popupDialog.show();
        LogUtils.verbose("popup show");
    }

    public void dismiss() {
        this.popupDialog.dismiss();
        LogUtils.verbose("popup dismiss");
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            return onKeyDown(i, keyEvent);
        }
        return false;
    }

    public Context getContext() {
        return this.popupDialog.getContext();
    }

    public Window getWindow() {
        return this.popupDialog.getWindow();
    }

    public View getContentView() {
        return this.popupDialog.getContentView();
    }

    public ViewGroup getRootView() {
        return this.popupDialog.getRootView();
    }
}
