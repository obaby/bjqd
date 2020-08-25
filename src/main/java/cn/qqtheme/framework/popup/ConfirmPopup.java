package cn.qqtheme.framework.popup;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.qqtheme.framework.util.ConvertUtils;

public abstract class ConfirmPopup<V extends View> extends BasicPopup<View> {
    protected CharSequence cancelText = "";
    protected int cancelTextColor = ViewCompat.MEASURED_STATE_MASK;
    protected int cancelTextSize = 0;
    protected boolean cancelVisible = true;
    protected CharSequence submitText = "";
    protected int submitTextColor = ViewCompat.MEASURED_STATE_MASK;
    protected int submitTextSize = 0;
    protected CharSequence titleText = "";
    protected int titleTextColor = ViewCompat.MEASURED_STATE_MASK;
    protected int titleTextSize = 0;
    protected int topBackgroundColor = -1;
    protected int topHeight = 40;
    protected int topLineColor = -2236963;
    protected boolean topLineVisible = true;

    /* access modifiers changed from: protected */
    @NonNull
    public abstract V makeCenterView();

    /* access modifiers changed from: protected */
    @Nullable
    public View makeFooterView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
    }

    /* access modifiers changed from: protected */
    public void onSubmit() {
    }

    public ConfirmPopup(Activity activity) {
        super(activity);
        this.cancelText = activity.getString(17039360);
        this.submitText = activity.getString(17039370);
    }

    public void setTopLineColor(@ColorInt int i) {
        this.topLineColor = i;
    }

    public void setTopBackgroundColor(@ColorInt int i) {
        this.topBackgroundColor = i;
    }

    public void setTopHeight(@IntRange(from = 10, to = 80) int i) {
        this.topHeight = i;
    }

    public void setTopLineVisible(boolean z) {
        this.topLineVisible = z;
    }

    public void setCancelVisible(boolean z) {
        this.cancelVisible = z;
    }

    public void setCancelText(CharSequence charSequence) {
        this.cancelText = charSequence;
    }

    public void setCancelText(@StringRes int i) {
        this.cancelText = this.activity.getString(i);
    }

    public void setSubmitText(CharSequence charSequence) {
        this.submitText = charSequence;
    }

    public void setSubmitText(@StringRes int i) {
        this.submitText = this.activity.getString(i);
    }

    public void setTitleText(CharSequence charSequence) {
        this.titleText = charSequence;
    }

    public void setTitleText(@StringRes int i) {
        this.titleText = this.activity.getString(i);
    }

    public void setCancelTextColor(@ColorInt int i) {
        this.cancelTextColor = i;
    }

    public void setSubmitTextColor(@ColorInt int i) {
        this.submitTextColor = i;
    }

    public void setTitleTextColor(@ColorInt int i) {
        this.titleTextColor = i;
    }

    public void setCancelTextSize(@IntRange(from = 10, to = 40) int i) {
        this.cancelTextSize = i;
    }

    public void setSubmitTextSize(@IntRange(from = 10, to = 40) int i) {
        this.submitTextSize = i;
    }

    public void setTitleTextSize(@IntRange(from = 10, to = 40) int i) {
        this.titleTextSize = i;
    }

    /* access modifiers changed from: protected */
    public final View makeContentView() {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setClipToPadding(false);
        View makeHeaderView = makeHeaderView();
        if (makeHeaderView != null) {
            linearLayout.addView(makeHeaderView);
        }
        if (this.topLineVisible) {
            View view = new View(this.activity);
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            view.setBackgroundColor(this.topLineColor);
            linearLayout.addView(view);
        }
        linearLayout.addView(makeCenterView(), new LinearLayout.LayoutParams(-1, 0, 1.0f));
        View makeFooterView = makeFooterView();
        if (makeFooterView != null) {
            linearLayout.addView(makeFooterView);
        }
        return linearLayout;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public View makeHeaderView() {
        RelativeLayout relativeLayout = new RelativeLayout(this.activity);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, ConvertUtils.toPx(this.activity, (float) this.topHeight)));
        relativeLayout.setBackgroundColor(this.topBackgroundColor);
        relativeLayout.setGravity(16);
        Button button = new Button(this.activity);
        button.setVisibility(this.cancelVisible ? 0 : 8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(0);
        button.setGravity(17);
        if (!TextUtils.isEmpty(this.cancelText)) {
            button.setText(this.cancelText);
        }
        button.setTextColor(this.cancelTextColor);
        if (this.cancelTextSize != 0) {
            button.setTextSize((float) this.cancelTextSize);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfirmPopup.this.dismiss();
                ConfirmPopup.this.onCancel();
            }
        });
        relativeLayout.addView(button);
        TextView textView = new TextView(this.activity);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        int px = ConvertUtils.toPx(this.activity, 20.0f);
        layoutParams2.leftMargin = px;
        layoutParams2.rightMargin = px;
        layoutParams2.addRule(14, -1);
        layoutParams2.addRule(15, -1);
        textView.setLayoutParams(layoutParams2);
        textView.setGravity(17);
        if (!TextUtils.isEmpty(this.titleText)) {
            textView.setText(this.titleText);
        }
        textView.setTextColor(this.titleTextColor);
        if (this.titleTextSize != 0) {
            textView.setTextSize((float) this.titleTextSize);
        }
        relativeLayout.addView(textView);
        Button button2 = new Button(this.activity);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        button2.setLayoutParams(layoutParams3);
        button2.setBackgroundColor(0);
        button2.setGravity(17);
        if (!TextUtils.isEmpty(this.submitText)) {
            button2.setText(this.submitText);
        }
        button2.setTextColor(this.submitTextColor);
        if (this.submitTextSize != 0) {
            button2.setTextSize((float) this.submitTextSize);
        }
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfirmPopup.this.dismiss();
                ConfirmPopup.this.onSubmit();
            }
        });
        relativeLayout.addView(button2);
        return relativeLayout;
    }
}
