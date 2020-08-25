package com.bsit.pulltorefresh.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.bsit.pulltorefresh.PullToRefreshBase;
import com.bsit.pulltorefresh.R;

public class RotateLoadingLayout extends LoadingLayout {
    static final int ROTATION_ANIMATION_DURATION = 1200;
    private final Matrix mHeaderImageMatrix = new Matrix();
    private final Animation mRotateAnimation = new RotateAnimation(0.0f, 720.0f, 1, 0.5f, 1, 0.5f);
    private final boolean mRotateDrawableWhilePulling;
    private float mRotationPivotX;
    private float mRotationPivotY;

    /* access modifiers changed from: protected */
    public void pullToRefreshImpl() {
    }

    /* access modifiers changed from: protected */
    public void refreshingImpl() {
    }

    /* access modifiers changed from: protected */
    public void releaseToRefreshImpl() {
    }

    public RotateLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        this.mRotateDrawableWhilePulling = typedArray.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, true);
        this.mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        this.mRotateAnimation.setDuration(1200);
        this.mRotateAnimation.setRepeatCount(-1);
        this.mRotateAnimation.setRepeatMode(1);
    }

    public void onLoadingDrawableSet(Drawable drawable) {
        if (drawable != null) {
            this.mRotationPivotX = (float) Math.round(((float) drawable.getIntrinsicWidth()) / 2.0f);
            this.mRotationPivotY = (float) Math.round(((float) drawable.getIntrinsicHeight()) / 2.0f);
        }
    }

    /* access modifiers changed from: protected */
    public void onPullImpl(float f) {
        float f2;
        if (this.mRotateDrawableWhilePulling) {
            f2 = f * 90.0f;
        } else {
            f2 = Math.max(0.0f, Math.min(180.0f, (f * 360.0f) - 180.0f));
        }
        this.mHeaderImageMatrix.setRotate(f2, this.mRotationPivotX, this.mRotationPivotY);
    }

    /* access modifiers changed from: protected */
    public void resetImpl() {
        resetImageRotation();
    }

    private void resetImageRotation() {
        if (this.mHeaderImageMatrix != null) {
            this.mHeaderImageMatrix.reset();
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }
}
