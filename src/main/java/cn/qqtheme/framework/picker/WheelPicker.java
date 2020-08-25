package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.view.View;
import cn.qqtheme.framework.popup.ConfirmPopup;
import cn.qqtheme.framework.widget.WheelView;

public abstract class WheelPicker extends ConfirmPopup<View> {
    protected int lineColor = WheelView.LINE_COLOR;
    protected boolean lineVisible = true;
    protected int offset = 1;
    protected int textColorFocus = WheelView.TEXT_COLOR_FOCUS;
    protected int textColorNormal = WheelView.TEXT_COLOR_NORMAL;
    protected int textSize = 20;

    public WheelPicker(Activity activity) {
        super(activity);
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    public void setTextColor(@ColorInt int i, @ColorInt int i2) {
        this.textColorFocus = i;
        this.textColorNormal = i2;
    }

    public void setTextColor(@ColorInt int i) {
        this.textColorFocus = i;
    }

    public void setLineVisible(boolean z) {
        this.lineVisible = z;
    }

    public void setLineColor(@ColorInt int i) {
        this.lineColor = i;
    }

    public void setOffset(@IntRange(from = 1, to = 4) int i) {
        this.offset = i;
    }
}
