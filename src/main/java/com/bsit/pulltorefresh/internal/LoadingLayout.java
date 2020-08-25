package com.bsit.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bsit.pulltorefresh.ILoadingLayout;
import com.bsit.pulltorefresh.PullToRefreshBase;
import com.bsit.pulltorefresh.R;

@SuppressLint({"ViewConstructor"})
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";
    protected final ProgressBar mHeaderProgress = ((ProgressBar) this.mInnerLayout.findViewById(R.id.pull_to_refresh_progress));
    private final TextView mHeaderText = ((TextView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_text));
    private FrameLayout mInnerLayout = ((FrameLayout) findViewById(R.id.fl_inner));
    protected final PullToRefreshBase.Mode mMode;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final PullToRefreshBase.Orientation mScrollDirection;
    private final TextView mSubHeaderText = ((TextView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text));
    private boolean mUseIntrinsicAnimation;

    /* access modifiers changed from: protected */
    public abstract int getDefaultDrawableResId();

    /* access modifiers changed from: protected */
    public abstract void onLoadingDrawableSet(Drawable drawable);

    /* access modifiers changed from: protected */
    public abstract void onPullImpl(float f);

    /* access modifiers changed from: protected */
    public abstract void pullToRefreshImpl();

    /* access modifiers changed from: protected */
    public abstract void refreshingImpl();

    /* access modifiers changed from: protected */
    public abstract void releaseToRefreshImpl();

    /* access modifiers changed from: protected */
    public abstract void resetImpl();

    public LoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context);
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        Drawable drawable;
        this.mMode = mode;
        this.mScrollDirection = orientation;
        int i = AnonymousClass1.$SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation[orientation.ordinal()];
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical, this);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mInnerLayout.getLayoutParams();
        if (AnonymousClass1.$SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Mode[mode.ordinal()] != 1) {
            layoutParams.gravity = orientation == PullToRefreshBase.Orientation.VERTICAL ? 80 : 5;
            this.mPullLabel = context.getString(R.string.pull_to_refresh_pull_label);
            this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
            this.mReleaseLabel = context.getString(R.string.pull_to_refresh_release_label);
        } else {
            layoutParams.gravity = orientation == PullToRefreshBase.Orientation.VERTICAL ? 48 : 3;
            this.mPullLabel = context.getString(R.string.pull_to_refresh_pull_label);
            this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
            this.mReleaseLabel = context.getString(R.string.pull_to_refresh_release_label);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderBackground) && (drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrHeaderBackground)) != null) {
            ViewCompat.setBackground(this, drawable);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance, typedValue);
            setTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance)) {
            TypedValue typedValue2 = new TypedValue();
            typedArray.getValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance, typedValue2);
            setSubTextAppearance(typedValue2.data);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderTextColor) && (colorStateList2 = typedArray.getColorStateList(R.styleable.PullToRefresh_ptrHeaderTextColor)) != null) {
            setTextColor(colorStateList2);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderSubTextColor) && (colorStateList = typedArray.getColorStateList(R.styleable.PullToRefresh_ptrHeaderSubTextColor)) != null) {
            setSubTextColor(colorStateList);
        }
        Drawable drawable2 = typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawable) ? typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawable) : null;
        if (AnonymousClass1.$SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Mode[mode.ordinal()] != 1) {
            if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableStart)) {
                drawable2 = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableStart);
            } else if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableTop)) {
                Utils.warnDeprecation("ptrDrawableTop", "ptrDrawableStart");
                drawable2 = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableTop);
            }
        } else if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableEnd)) {
            drawable2 = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableEnd);
        } else if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableBottom)) {
            Utils.warnDeprecation("ptrDrawableBottom", "ptrDrawableEnd");
            drawable2 = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableBottom);
        }
        setLoadingDrawable(drawable2 == null ? context.getResources().getDrawable(getDefaultDrawableResId()) : drawable2);
        reset();
    }

    /* renamed from: com.bsit.pulltorefresh.internal.LoadingLayout$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Mode = new int[PullToRefreshBase.Mode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation = new int[PullToRefreshBase.Orientation.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|(2:5|6)|7|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        static {
            /*
                com.bsit.pulltorefresh.PullToRefreshBase$Mode[] r0 = com.bsit.pulltorefresh.PullToRefreshBase.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Mode = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bsit.pulltorefresh.PullToRefreshBase$Mode r2 = com.bsit.pulltorefresh.PullToRefreshBase.Mode.PULL_FROM_END     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Mode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bsit.pulltorefresh.PullToRefreshBase$Mode r3 = com.bsit.pulltorefresh.PullToRefreshBase.Mode.PULL_FROM_START     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                com.bsit.pulltorefresh.PullToRefreshBase$Orientation[] r2 = com.bsit.pulltorefresh.PullToRefreshBase.Orientation.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation = r2
                int[] r2 = $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation     // Catch:{ NoSuchFieldError -> 0x0032 }
                com.bsit.pulltorefresh.PullToRefreshBase$Orientation r3 = com.bsit.pulltorefresh.PullToRefreshBase.Orientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                int[] r0 = $SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation     // Catch:{ NoSuchFieldError -> 0x003c }
                com.bsit.pulltorefresh.PullToRefreshBase$Orientation r2 = com.bsit.pulltorefresh.PullToRefreshBase.Orientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x003c }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x003c }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x003c }
            L_0x003c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bsit.pulltorefresh.internal.LoadingLayout.AnonymousClass1.<clinit>():void");
        }
    }

    public final void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }

    public final void setWidth(int i) {
        getLayoutParams().width = i;
        requestLayout();
    }

    public final int getContentSize() {
        if (AnonymousClass1.$SwitchMap$com$bsit$pulltorefresh$PullToRefreshBase$Orientation[this.mScrollDirection.ordinal()] != 2) {
            return this.mInnerLayout.getHeight();
        }
        return this.mInnerLayout.getWidth();
    }

    public final void hideAllViews() {
        if (this.mHeaderText.getVisibility() == 0) {
            this.mHeaderText.setVisibility(4);
        }
        if (this.mHeaderProgress.getVisibility() == 0) {
            this.mHeaderProgress.setVisibility(4);
        }
        if (this.mSubHeaderText.getVisibility() == 0) {
            this.mSubHeaderText.setVisibility(4);
        }
    }

    public final void onPull(float f) {
        if (!this.mUseIntrinsicAnimation) {
            onPullImpl(f);
        }
    }

    public final void pullToRefresh() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mPullLabel);
        }
        pullToRefreshImpl();
    }

    public final void refreshing() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mRefreshingLabel);
        }
        if (!this.mUseIntrinsicAnimation) {
            refreshingImpl();
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setVisibility(8);
        }
    }

    public final void releaseToRefresh() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mReleaseLabel);
        }
        releaseToRefreshImpl();
    }

    public final void reset() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mPullLabel);
        }
        if (!this.mUseIntrinsicAnimation) {
            resetImpl();
        }
        if (this.mSubHeaderText == null) {
            return;
        }
        if (TextUtils.isEmpty(this.mSubHeaderText.getText())) {
            this.mSubHeaderText.setVisibility(8);
        } else {
            this.mSubHeaderText.setVisibility(0);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        setSubHeaderText(charSequence);
    }

    public final void setLoadingDrawable(Drawable drawable) {
        this.mUseIntrinsicAnimation = drawable instanceof AnimationDrawable;
        onLoadingDrawableSet(drawable);
    }

    public void setPullLabel(CharSequence charSequence) {
        this.mPullLabel = charSequence;
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        this.mRefreshingLabel = charSequence;
    }

    public void setReleaseLabel(CharSequence charSequence) {
        this.mReleaseLabel = charSequence;
    }

    public void setTextTypeface(Typeface typeface) {
        this.mHeaderText.setTypeface(typeface);
    }

    public final void showInvisibleViews() {
        if (4 == this.mHeaderText.getVisibility()) {
            this.mHeaderText.setVisibility(0);
        }
        if (4 == this.mHeaderProgress.getVisibility()) {
            this.mHeaderProgress.setVisibility(0);
        }
        if (4 == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(0);
        }
    }

    private void setSubHeaderText(CharSequence charSequence) {
        if (this.mSubHeaderText == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.mSubHeaderText.setVisibility(8);
            return;
        }
        this.mSubHeaderText.setText(charSequence);
        if (8 == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(0);
        }
    }

    private void setSubTextAppearance(int i) {
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextAppearance(getContext(), i);
        }
    }

    private void setSubTextColor(ColorStateList colorStateList) {
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextColor(colorStateList);
        }
    }

    private void setTextAppearance(int i) {
        if (this.mHeaderText != null) {
            this.mHeaderText.setTextAppearance(getContext(), i);
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextAppearance(getContext(), i);
        }
    }

    private void setTextColor(ColorStateList colorStateList) {
        if (this.mHeaderText != null) {
            this.mHeaderText.setTextColor(colorStateList);
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextColor(colorStateList);
        }
    }
}
