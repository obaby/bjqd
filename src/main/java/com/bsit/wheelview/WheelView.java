package com.bsit.wheelview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

public class WheelView extends TosGallery {
    private static final int[] SHADOWS_COLORS = {0, 0, 0};
    private GradientDrawable mBottomShadow = null;
    private Rect mSelectorBound = new Rect();
    private Drawable mSelectorDrawable = null;
    private GradientDrawable mTopShadow = null;

    public WheelView(Context context) {
        super(context);
        initialize(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context);
    }

    private void initialize(Context context) {
        setVerticalScrollBarEnabled(false);
        setSlotInCenter(true);
        setOrientation(2);
        setGravity(1);
        setUnselectedAlpha(1.0f);
        setWillNotDraw(false);
        this.mSelectorDrawable = getContext().getResources().getDrawable(R.drawable.wheel_val);
        this.mTopShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
        this.mBottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
        setSoundEffectsEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawCenterRect(canvas);
        drawShadows(canvas);
    }

    public void setOrientation(int i) {
        if (1 != i) {
            super.setOrientation(i);
            return;
        }
        throw new IllegalArgumentException("The orientation must be VERTICAL");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int centerOfGallery = getCenterOfGallery();
        View childAt = getChildAt(0);
        int measuredHeight = childAt != null ? childAt.getMeasuredHeight() : 50;
        int i5 = centerOfGallery - (measuredHeight / 2);
        this.mSelectorBound.set(getPaddingLeft(), i5, getWidth() - getPaddingRight(), measuredHeight + i5);
    }

    /* access modifiers changed from: protected */
    public void selectionChanged() {
        super.selectionChanged();
        playSoundEffect(0);
    }

    private void drawCenterRect(Canvas canvas) {
        if (this.mSelectorDrawable != null) {
            this.mSelectorDrawable.setBounds(this.mSelectorBound);
            this.mSelectorDrawable.draw(canvas);
        }
    }

    private void drawShadows(Canvas canvas) {
        double height = (double) this.mSelectorBound.height();
        Double.isNaN(height);
        int i = (int) (height * 2.0d);
        this.mTopShadow.setBounds(0, 0, getWidth(), i);
        this.mTopShadow.draw(canvas);
        this.mBottomShadow.setBounds(0, getHeight() - i, getWidth(), getHeight());
        this.mBottomShadow.draw(canvas);
    }
}
