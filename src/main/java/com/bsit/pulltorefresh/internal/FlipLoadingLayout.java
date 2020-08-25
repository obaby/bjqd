package com.bsit.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.alibaba.fastjson.asm.Opcodes;
import com.bsit.pulltorefresh.PullToRefreshBase;
import com.bsit.pulltorefresh.R;

@SuppressLint({"ViewConstructor"})
public class FlipLoadingLayout extends LoadingLayout {
    static final int FLIP_ANIMATION_DURATION = 150;
    private final Animation mResetRotateAnimation;
    private final Animation mRotateAnimation;

    /* access modifiers changed from: protected */
    public void onLoadingDrawableSet(Drawable drawable) {
    }

    /* access modifiers changed from: protected */
    public void onPullImpl(float f) {
    }

    /* access modifiers changed from: protected */
    public void pullToRefreshImpl() {
    }

    /* access modifiers changed from: protected */
    public void releaseToRefreshImpl() {
    }

    public FlipLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        float f = (float) (mode == PullToRefreshBase.Mode.PULL_FROM_START ? -180 : Opcodes.GETFIELD);
        this.mRotateAnimation = new RotateAnimation(0.0f, f, 1, 0.5f, 1, 0.5f);
        this.mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        this.mRotateAnimation.setDuration(150);
        this.mRotateAnimation.setFillAfter(true);
        this.mResetRotateAnimation = new RotateAnimation(f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mResetRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        this.mResetRotateAnimation.setDuration(150);
        this.mResetRotateAnimation.setFillAfter(true);
    }

    /* access modifiers changed from: protected */
    public void refreshingImpl() {
        this.mHeaderProgress.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void resetImpl() {
        this.mHeaderProgress.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public int getDefaultDrawableResId() {
        return R.drawable.ic_pulltorefresh_arrow;
    }

    private float getDrawableRotationAngle() {
        switch (this.mMode) {
            case PULL_FROM_END:
                return this.mScrollDirection == PullToRefreshBase.Orientation.HORIZONTAL ? 90.0f : 180.0f;
            case PULL_FROM_START:
                if (this.mScrollDirection == PullToRefreshBase.Orientation.HORIZONTAL) {
                    return 270.0f;
                }
                break;
        }
        return 0.0f;
    }
}
