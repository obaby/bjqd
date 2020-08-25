package com.bsit.wheelview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.Scroller;
import coband.bsit.com.integral.BuildConfig;
import com.bsit.wheelview.TosAdapterView;

@SuppressLint({"RtlHardcoded"})
public class TosGallery extends TosAbsSpinner implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    public static final int HORIZONTAL = 1;
    private static final int SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT = 250;
    private static final String TAG = "Gallery";
    public static final int VERTICAL = 2;
    private static final boolean localLOGV = false;
    /* access modifiers changed from: private */
    public int mAnimationDuration;
    private TosAdapterView.AdapterContextMenuInfo mContextMenuInfo;
    private Runnable mDisableSuppressSelectionChangedRunnable;
    /* access modifiers changed from: private */
    public int mDownTouchPosition;
    private View mDownTouchView;
    private int mFirstChildOffset;
    private FlingRunnable mFlingRunnable;
    private GestureDetector mGestureDetector;
    private int mGravity;
    private boolean mIsDisableScroll;
    private boolean mIsFirstScroll;
    private boolean mIsScrollCycle;
    private boolean mIsScrollCycleTemp;
    private boolean mIsSlotCenter;
    private int mLeftMost;
    private OnEndFlingListener mOnEndFlingListener;
    private int mOrientation;
    private boolean mReceivedInvokeKeyDown;
    private int mRightMost;
    private int mScrollBarBottomMargin;
    private int mScrollBarSize;
    /* access modifiers changed from: private */
    public boolean mScrolling;
    private View mSelectedChild;
    private boolean mShouldCallbackDuringFling;
    private boolean mShouldCallbackOnUnselectedItemClick;
    /* access modifiers changed from: private */
    public boolean mShouldStopFling;
    private int mSpacing;
    /* access modifiers changed from: private */
    public boolean mSuppressSelectionChanged;
    private float mUnselectedAlpha;
    private float mVelocityRatio;

    public interface OnEndFlingListener {
        void onEndFling(TosGallery tosGallery);
    }

    /* access modifiers changed from: protected */
    public void Log(String str) {
    }

    public void dispatchSetSelected(boolean z) {
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    /* access modifiers changed from: protected */
    public float onStopFlingPosRatio() {
        return 0.0f;
    }

    public TosGallery(Context context) {
        this(context, (AttributeSet) null);
    }

    public TosGallery(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.galleryStyle);
    }

    public TosGallery(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSpacing = 0;
        this.mAnimationDuration = BuildConfig.VERSION_CODE;
        this.mFlingRunnable = new FlingRunnable();
        this.mDisableSuppressSelectionChangedRunnable = new Runnable() {
            public void run() {
                boolean unused = TosGallery.this.mSuppressSelectionChanged = false;
                TosGallery.this.selectionChanged();
            }
        };
        this.mShouldCallbackDuringFling = true;
        this.mShouldCallbackOnUnselectedItemClick = true;
        this.mIsDisableScroll = false;
        this.mScrolling = false;
        this.mFirstChildOffset = 0;
        this.mScrollBarBottomMargin = 0;
        this.mScrollBarSize = 5;
        this.mVelocityRatio = 1.0f;
        this.mIsScrollCycle = false;
        this.mIsScrollCycleTemp = true;
        this.mIsSlotCenter = false;
        this.mOrientation = 1;
        this.mOnEndFlingListener = null;
        this.mGestureDetector = new GestureDetector(context, this);
        this.mGestureDetector.setIsLongpressEnabled(true);
        setSpacing(0);
        setUnselectedAlpha(0.5f);
        setChildrenDrawingOrderEnabled(true);
        setStaticTransformationsEnabled(true);
        this.mScrollBarSize = ViewConfiguration.get(context).getScaledScrollBarSize();
        if (isOrientationVertical()) {
            this.mGravity = 1;
        } else {
            this.mGravity = 16;
        }
    }

    public void setCallbackDuringFling(boolean z) {
        this.mShouldCallbackDuringFling = z;
    }

    public void setCallbackOnUnselectedItemClick(boolean z) {
        this.mShouldCallbackOnUnselectedItemClick = z;
    }

    public void setAnimationDuration(int i) {
        this.mAnimationDuration = i;
    }

    public void setSpacing(int i) {
        this.mSpacing = i;
    }

    public void setUnselectedAlpha(float f) {
        this.mUnselectedAlpha = f;
    }

    /* access modifiers changed from: protected */
    public boolean getChildStaticTransformation(View view, Transformation transformation) {
        transformation.clear();
        transformation.setAlpha(view == this.mSelectedChild ? 1.0f : this.mUnselectedAlpha);
        Log(" getChildStaticTransformation   mSelectedPosition =  " + this.mSelectedPosition + "   mFirstPosition = " + this.mFirstPosition + "     mSelectedChild = " + this.mSelectedChild);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDrawHorizontalScrollBar(Canvas canvas, Drawable drawable, int i, int i2, int i3, int i4) {
        int i5 = i4 - this.mScrollBarBottomMargin;
        drawable.setBounds(i, i5 - this.mScrollBarSize, i3, i5);
        drawable.draw(canvas);
    }

    public void invalidate(int i, int i2, int i3, int i4) {
        super.invalidate(i, i2 - (this.mScrollBarSize + this.mScrollBarBottomMargin), i3, i4);
    }

    /* access modifiers changed from: protected */
    public int computeHorizontalScrollExtent() {
        int childCount = getChildCount();
        boolean z = false;
        if (childCount <= 0) {
            return 0;
        }
        int i = childCount * 100;
        View childAt = getChildAt(0);
        int left = childAt.getLeft();
        int width = childAt.getWidth();
        if (width > 0) {
            if (!(this.mFirstPosition == 0) || left <= 0) {
                i += (left * 100) / width;
            }
        }
        View childAt2 = getChildAt(childCount - 1);
        int right = childAt2.getRight();
        int width2 = childAt2.getWidth();
        if (width2 <= 0) {
            return i;
        }
        if (this.mFirstPosition + childCount == this.mItemCount) {
            z = true;
        }
        return (!z || right >= getWidth()) ? i - (((right - getWidth()) * 100) / width2) : i;
    }

    /* access modifiers changed from: protected */
    public int computeHorizontalScrollOffset() {
        if (this.mFirstPosition >= 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            int left = childAt.getLeft();
            int width = childAt.getWidth();
            if (width > 0) {
                return Math.max((this.mFirstPosition * 100) - ((left * 100) / width), 0);
            }
        }
        return this.mSelectedPosition;
    }

    /* access modifiers changed from: protected */
    public int computeHorizontalScrollRange() {
        return Math.max((((this.mItemCount + 1) - 1) / 1) * 100, 0);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mInLayout = true;
        layout(0, false);
        this.mInLayout = false;
    }

    /* access modifiers changed from: package-private */
    public int getChildHeight(View view) {
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trackMotionScroll(int r6) {
        /*
            r5 = this;
            int r0 = r5.getChildCount()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 1
            r1 = 0
            if (r6 >= 0) goto L_0x000d
            r2 = 1
            goto L_0x000e
        L_0x000d:
            r2 = 0
        L_0x000e:
            boolean r3 = r5.isSlotInCenter()
            if (r3 == 0) goto L_0x004b
            boolean r0 = r5.isScrollCycle()
            if (r0 == 0) goto L_0x0022
            int r0 = r5.getChildCount()
            int r3 = r5.mItemCount
            if (r0 < r3) goto L_0x0030
        L_0x0022:
            int r0 = r5.getLimitedMotionScrollAmount(r2, r6)
            if (r0 == r6) goto L_0x0030
            com.bsit.wheelview.TosGallery$FlingRunnable r0 = r5.mFlingRunnable
            r0.endFling(r1)
            r5.onFinishedMovement()
        L_0x0030:
            r5.offsetChildrenLeftAndRight(r6)
            r5.detachOffScreenChildren(r2)
            if (r2 == 0) goto L_0x003c
            r5.fillToGalleryRight()
            goto L_0x003f
        L_0x003c:
            r5.fillToGalleryLeft()
        L_0x003f:
            com.bsit.wheelview.TosAbsSpinner$RecycleBin r6 = r5.mRecycler
            r6.clear()
            r5.setSelectionToCenterChild()
            r5.invalidate()
            return
        L_0x004b:
            if (r2 == 0) goto L_0x006f
            int r3 = r5.getChildCount()
            int r3 = r3 - r0
            android.view.View r0 = r5.getChildAt(r3)
            if (r0 == 0) goto L_0x0092
            int r0 = r0.getRight()
            float r0 = (float) r0
            float r3 = r5.getStopFlingPosition()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0092
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            if (r6 == 0) goto L_0x0091
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            r6.stop(r1)
            goto L_0x0091
        L_0x006f:
            android.view.View r0 = r5.getChildAt(r1)
            if (r0 == 0) goto L_0x0092
            int r0 = r0.getLeft()
            float r0 = (float) r0
            int r3 = r5.getWidth()
            float r3 = (float) r3
            float r4 = r5.getStopFlingPosition()
            float r3 = r3 - r4
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x0092
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            if (r6 == 0) goto L_0x0091
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            r6.stop(r1)
        L_0x0091:
            r6 = 0
        L_0x0092:
            r5.offsetChildrenLeftAndRight(r6)
            r5.detachOffScreenChildren(r2)
            if (r2 == 0) goto L_0x009e
            r5.fillToGalleryRight()
            goto L_0x00a1
        L_0x009e:
            r5.fillToGalleryLeft()
        L_0x00a1:
            com.bsit.wheelview.TosAbsSpinner$RecycleBin r6 = r5.mRecycler
            r6.clear()
            r5.setSelectionToCenterChild()
            r5.awakenScrollBars()
            r5.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.wheelview.TosGallery.trackMotionScroll(int):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trackMotionScrollVertical(int r6) {
        /*
            r5 = this;
            int r0 = r5.getChildCount()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 1
            r1 = 0
            if (r6 >= 0) goto L_0x000d
            r2 = 1
            goto L_0x000e
        L_0x000d:
            r2 = 0
        L_0x000e:
            boolean r3 = r5.isSlotInCenter()
            if (r3 == 0) goto L_0x004b
            boolean r0 = r5.isScrollCycle()
            if (r0 == 0) goto L_0x0022
            int r0 = r5.getChildCount()
            int r3 = r5.mItemCount
            if (r0 < r3) goto L_0x0030
        L_0x0022:
            int r0 = r5.getLimitedMotionScrollAmount(r2, r6)
            if (r0 == r6) goto L_0x0030
            com.bsit.wheelview.TosGallery$FlingRunnable r0 = r5.mFlingRunnable
            r0.endFling(r1)
            r5.onFinishedMovement()
        L_0x0030:
            r5.offsetChildrenTopAndBottom(r6)
            r5.detachOffScreenChildrenVertical(r2)
            if (r2 == 0) goto L_0x003c
            r5.fillToGalleryBottom()
            goto L_0x003f
        L_0x003c:
            r5.fillToGalleryTop()
        L_0x003f:
            com.bsit.wheelview.TosAbsSpinner$RecycleBin r6 = r5.mRecycler
            r6.clear()
            r5.setSelectionToCenterChildVertical()
            r5.invalidate()
            return
        L_0x004b:
            if (r2 == 0) goto L_0x006f
            int r3 = r5.getChildCount()
            int r3 = r3 - r0
            android.view.View r0 = r5.getChildAt(r3)
            if (r0 == 0) goto L_0x0092
            int r0 = r0.getRight()
            float r0 = (float) r0
            float r3 = r5.getStopFlingPosition()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0092
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            if (r6 == 0) goto L_0x0091
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            r6.stop(r1)
            goto L_0x0091
        L_0x006f:
            android.view.View r0 = r5.getChildAt(r1)
            if (r0 == 0) goto L_0x0092
            int r0 = r0.getLeft()
            float r0 = (float) r0
            int r3 = r5.getWidth()
            float r3 = (float) r3
            float r4 = r5.getStopFlingPosition()
            float r3 = r3 - r4
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x0092
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            if (r6 == 0) goto L_0x0091
            com.bsit.wheelview.TosGallery$FlingRunnable r6 = r5.mFlingRunnable
            r6.stop(r1)
        L_0x0091:
            r6 = 0
        L_0x0092:
            r5.offsetChildrenTopAndBottom(r6)
            r5.detachOffScreenChildrenVertical(r2)
            if (r2 == 0) goto L_0x009e
            r5.fillToGalleryBottom()
            goto L_0x00a1
        L_0x009e:
            r5.fillToGalleryTop()
        L_0x00a1:
            com.bsit.wheelview.TosAbsSpinner$RecycleBin r6 = r5.mRecycler
            r6.clear()
            r5.setSelectionToCenterChild()
            r5.awakenScrollBars()
            r5.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.wheelview.TosGallery.trackMotionScrollVertical(int):void");
    }

    /* access modifiers changed from: package-private */
    public int getLimitedMotionScrollAmount(boolean z, int i) {
        View childAt = getChildAt((z ? this.mItemCount - 1 : 0) - this.mFirstPosition);
        if (childAt == null) {
            return i;
        }
        int centerOfView = getCenterOfView(childAt);
        int centerOfGallery = getCenterOfGallery();
        if (z) {
            if (centerOfView <= centerOfGallery) {
                return 0;
            }
        } else if (centerOfView >= centerOfGallery) {
            return 0;
        }
        int i2 = centerOfGallery - centerOfView;
        return z ? Math.max(i2, i) : Math.min(i2, i);
    }

    private void offsetChildrenLeftAndRight(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            getChildAt(childCount).offsetLeftAndRight(i);
        }
    }

    private void offsetChildrenTopAndBottom(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            getChildAt(childCount).offsetTopAndBottom(i);
        }
    }

    /* access modifiers changed from: protected */
    public int getCenterOfGallery() {
        if (isOrientationVertical()) {
            return (((getHeight() - getPaddingTop()) - getPaddingBottom()) / 2) + getPaddingTop();
        }
        return (((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2) + getPaddingLeft();
    }

    private float getStopFlingPosition() {
        if (isOrientationVertical()) {
            return (((float) ((getHeight() - getPaddingTop()) - getPaddingBottom())) * onStopFlingPosRatio()) + ((float) getPaddingTop());
        }
        return (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * onStopFlingPosRatio()) + ((float) getPaddingLeft());
    }

    private int getCenterOfView(View view) {
        if (isOrientationVertical()) {
            return view.getTop() + (view.getHeight() / 2);
        }
        return view.getLeft() + (view.getWidth() / 2);
    }

    private void detachOffScreenChildren(boolean z) {
        int i;
        int childCount = getChildCount();
        int i2 = this.mFirstPosition;
        int i3 = 0;
        if (z) {
            int paddingLeft = getPaddingLeft();
            i = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getRight() >= paddingLeft) {
                    break;
                }
                i++;
                this.mRecycler.put(i2 + i4, childAt);
            }
            if (i == childCount) {
                i--;
            }
        } else {
            int width = getWidth() - getPaddingRight();
            int i5 = childCount - 1;
            int i6 = 0;
            int i7 = 0;
            while (i5 >= 0) {
                View childAt2 = getChildAt(i5);
                if (childAt2.getLeft() <= width) {
                    break;
                }
                i7 = i + 1;
                this.mRecycler.put(i2 + i5, childAt2);
                int i8 = i5;
                i5--;
                i6 = i8;
            }
            i3 = i6 == 0 ? i6 + 1 : i6;
        }
        detachViewsFromParent(i3, i);
        if (z) {
            this.mFirstPosition += i;
            if (isScrollCycle()) {
                this.mFirstPosition %= this.mItemCount;
            }
        }
    }

    private void detachOffScreenChildrenVertical(boolean z) {
        int i;
        int childCount = getChildCount();
        int i2 = this.mFirstPosition;
        int i3 = 0;
        if (z) {
            int paddingTop = getPaddingTop();
            i = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getBottom() >= paddingTop) {
                    break;
                }
                i++;
                this.mRecycler.put(i2 + i4, childAt);
            }
            if (i == childCount) {
                i--;
            }
        } else {
            int height = getHeight() - getPaddingBottom();
            int i5 = childCount - 1;
            int i6 = 0;
            int i7 = 0;
            while (i5 >= 0) {
                View childAt2 = getChildAt(i5);
                if (childAt2.getTop() <= height) {
                    break;
                }
                i7 = i + 1;
                this.mRecycler.put(i2 + i5, childAt2);
                int i8 = i5;
                i5--;
                i6 = i8;
            }
            i3 = i6 == 0 ? i6 + 1 : i6;
        }
        detachViewsFromParent(i3, i);
        if (z) {
            this.mFirstPosition += i;
            if (isScrollCycle()) {
                this.mFirstPosition %= this.mItemCount;
            }
        }
    }

    public void scrollIntoSlots() {
        int width;
        if (isOrientationVertical()) {
            scrollIntoSlotsVertical();
        } else if (isSlotInCenter()) {
            if (getChildCount() != 0 && this.mSelectedChild != null) {
                int centerOfGallery = getCenterOfGallery() - getCenterOfView(this.mSelectedChild);
                if (centerOfGallery != 0) {
                    this.mFlingRunnable.startUsingDistance(centerOfGallery);
                } else {
                    onFinishedMovement();
                }
            }
        } else if (getChildCount() != 0) {
            int i = 0;
            if (this.mFirstPosition == 0) {
                View childAt = getChildAt(0);
                if (childAt.getLeft() >= 0) {
                    i = getPaddingLeft() - childAt.getLeft();
                } else {
                    View childAt2 = getChildAt(getChildCount() - 1);
                    if (childAt2.getRight() - childAt.getLeft() < getRight() - getPaddingRight()) {
                        width = getPaddingLeft() - this.mFirstChildOffset;
                    } else if (childAt2.getRight() < getRight() - getPaddingRight()) {
                        width = (getWidth() - getPaddingRight()) - childAt2.getRight();
                    }
                    i = width;
                }
            } else if (this.mFirstPosition + getChildCount() == this.mItemCount) {
                View childAt3 = getChildAt(getChildCount() - 1);
                if (childAt3.getRight() < getRight() - getPaddingRight()) {
                    i = (getWidth() - getPaddingRight()) - childAt3.getRight();
                }
            }
            if (i != 0) {
                this.mFlingRunnable.startUsingDistance(i);
            } else {
                onFinishedMovement();
            }
        }
    }

    private void scrollIntoSlotsVertical() {
        int height;
        if (isSlotInCenter()) {
            if (getChildCount() != 0 && this.mSelectedChild != null) {
                int centerOfGallery = getCenterOfGallery() - getCenterOfView(this.mSelectedChild);
                if (centerOfGallery != 0) {
                    this.mFlingRunnable.startUsingDistance(centerOfGallery);
                } else {
                    onFinishedMovement();
                }
            }
        } else if (getChildCount() != 0) {
            int i = 0;
            if (this.mFirstPosition == 0) {
                View childAt = getChildAt(0);
                if (childAt.getTop() >= 0) {
                    i = getPaddingTop() - childAt.getTop();
                } else {
                    View childAt2 = getChildAt(getChildCount() - 1);
                    if (childAt2.getBottom() - childAt.getTop() < getBottom() - getPaddingBottom()) {
                        height = getPaddingLeft() - this.mFirstChildOffset;
                    } else if (childAt2.getBottom() < getBottom() - getPaddingBottom()) {
                        height = (getHeight() - getPaddingBottom()) - childAt2.getBottom();
                    }
                    i = height;
                }
            } else if (this.mFirstPosition + getChildCount() == this.mItemCount) {
                View childAt3 = getChildAt(getChildCount() - 1);
                if (childAt3.getBottom() < getBottom() - getPaddingBottom()) {
                    i = (getHeight() - getPaddingBottom()) - childAt3.getBottom();
                }
            }
            if (i != 0) {
                this.mFlingRunnable.startUsingDistance(i);
            } else {
                onFinishedMovement();
            }
        }
    }

    private void onFinishedMovement() {
        if (this.mSuppressSelectionChanged) {
            this.mSuppressSelectionChanged = false;
            super.selectionChanged();
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void selectionChanged() {
        if (!this.mSuppressSelectionChanged) {
            super.selectionChanged();
        }
    }

    private void setSelectionToCenterChild() {
        View view = this.mSelectedChild;
        if (this.mSelectedChild != null) {
            int centerOfGallery = getCenterOfGallery();
            if (view.getLeft() > centerOfGallery || view.getRight() < centerOfGallery) {
                int i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                int i2 = 0;
                int childCount = getChildCount() - 1;
                while (true) {
                    if (childCount < 0) {
                        break;
                    }
                    View childAt = getChildAt(childCount);
                    if (childAt.getLeft() <= centerOfGallery && childAt.getRight() >= centerOfGallery) {
                        i2 = childCount;
                        break;
                    }
                    int min = Math.min(Math.abs(childAt.getLeft() - centerOfGallery), Math.abs(childAt.getRight() - centerOfGallery));
                    if (min < i) {
                        i2 = childCount;
                        i = min;
                    }
                    childCount--;
                }
                int i3 = this.mFirstPosition + i2;
                if (isScrollCycle()) {
                    i3 %= this.mItemCount;
                }
                if (i3 != this.mSelectedPosition) {
                    setSelectedPositionInt(i3);
                    setNextSelectedPositionInt(i3);
                    checkSelectionChanged();
                }
            }
        }
    }

    private void setSelectionToCenterChildVertical() {
        View view = this.mSelectedChild;
        if (this.mSelectedChild != null) {
            int centerOfGallery = getCenterOfGallery();
            if (view.getTop() > centerOfGallery || view.getBottom() < centerOfGallery) {
                int i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                int i2 = 0;
                int childCount = getChildCount() - 1;
                while (true) {
                    if (childCount < 0) {
                        break;
                    }
                    View childAt = getChildAt(childCount);
                    if (childAt.getTop() <= centerOfGallery && childAt.getBottom() >= centerOfGallery) {
                        i2 = childCount;
                        break;
                    }
                    int min = Math.min(Math.abs(childAt.getTop() - centerOfGallery), Math.abs(childAt.getBottom() - centerOfGallery));
                    if (min < i) {
                        i2 = childCount;
                        i = min;
                    }
                    childCount--;
                }
                int i3 = this.mFirstPosition + i2;
                if (isScrollCycle()) {
                    i3 %= this.mItemCount;
                }
                if (i3 != this.mSelectedPosition) {
                    setSelectedPositionInt(i3);
                    setNextSelectedPositionInt(i3);
                    checkSelectionChanged();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void layout(int i, boolean z) {
        if (isOrientationVertical()) {
            layoutVertical(i, z);
            return;
        }
        int i2 = this.mSpinnerPadding.left + this.mFirstChildOffset;
        if (this.mDataChanged) {
            handleDataChanged();
        }
        if (this.mItemCount == 0) {
            resetList();
            return;
        }
        if (this.mNextSelectedPosition >= 0) {
            setSelectedPositionInt(this.mNextSelectedPosition);
        }
        recycleAllViews();
        detachAllViewsFromParent();
        boolean z2 = false;
        this.mRightMost = 0;
        this.mLeftMost = 0;
        this.mFirstPosition = this.mSelectedPosition;
        View makeAndAddView = makeAndAddView(this.mFirstPosition, 0, 0, true);
        int i3 = this.mSpacing + i2;
        if (isSlotInCenter()) {
            i3 = (i2 + ((((getRight() - getLeft()) - this.mSpinnerPadding.left) - this.mSpinnerPadding.right) / 2)) - (makeAndAddView.getWidth() / 2);
        }
        makeAndAddView.offsetLeftAndRight(i3);
        fillToGalleryRight();
        fillToGalleryLeft();
        this.mRecycler.clear();
        invalidate();
        this.mDataChanged = false;
        this.mNeedSync = false;
        setNextSelectedPositionInt(this.mSelectedPosition);
        updateSelectedItemMetadata();
        if (getChildCount() < this.mItemCount) {
            z2 = true;
        }
        this.mIsScrollCycleTemp = z2;
    }

    /* access modifiers changed from: package-private */
    public void layoutVertical(int i, boolean z) {
        int i2 = this.mSpinnerPadding.top + this.mFirstChildOffset;
        if (this.mDataChanged) {
            handleDataChanged();
        }
        if (this.mItemCount == 0) {
            resetList();
            return;
        }
        if (this.mNextSelectedPosition >= 0) {
            setSelectedPositionInt(this.mNextSelectedPosition);
        }
        recycleAllViews();
        detachAllViewsFromParent();
        boolean z2 = false;
        this.mRightMost = 0;
        this.mLeftMost = 0;
        this.mFirstPosition = this.mSelectedPosition;
        View makeAndAddViewVertical = makeAndAddViewVertical(this.mFirstPosition, 0, 0, true);
        int i3 = this.mSpacing + i2;
        if (isSlotInCenter()) {
            i3 = (i2 + ((((getBottom() - getTop()) - this.mSpinnerPadding.top) - this.mSpinnerPadding.bottom) / 2)) - (makeAndAddViewVertical.getHeight() / 2);
        }
        makeAndAddViewVertical.offsetTopAndBottom(i3);
        fillToGalleryBottom();
        fillToGalleryTop();
        this.mRecycler.clear();
        invalidate();
        this.mDataChanged = false;
        this.mNeedSync = false;
        setNextSelectedPositionInt(this.mSelectedPosition);
        updateSelectedItemMetadata();
        if (getChildCount() < this.mItemCount) {
            z2 = true;
        }
        this.mIsScrollCycleTemp = z2;
    }

    private void fillToGalleryLeft() {
        int i;
        int i2;
        if (isScrollCycle()) {
            fillToGalleryLeftCycle();
            return;
        }
        int i3 = this.mSpacing;
        int paddingLeft = getPaddingLeft();
        View childAt = getChildAt(0);
        if (childAt != null) {
            i = this.mFirstPosition - 1;
            i2 = childAt.getLeft() - i3;
        } else {
            i2 = (getRight() - getLeft()) - getPaddingRight();
            this.mShouldStopFling = true;
            i = 0;
        }
        while (i2 > paddingLeft && i >= 0) {
            View makeAndAddView = makeAndAddView(i, i - this.mSelectedPosition, i2, false);
            this.mFirstPosition = i;
            i2 = makeAndAddView.getLeft() - i3;
            i--;
        }
    }

    private void fillToGalleryTop() {
        int i;
        int i2;
        if (isScrollCycle()) {
            fillToGalleryTopCycle();
            return;
        }
        int i3 = this.mSpacing;
        int paddingTop = getPaddingTop();
        View childAt = getChildAt(0);
        if (childAt != null) {
            i = this.mFirstPosition - 1;
            i2 = childAt.getTop() - i3;
        } else {
            i2 = (getBottom() - getTop()) - getPaddingBottom();
            this.mShouldStopFling = true;
            i = 0;
        }
        while (i2 > paddingTop && i >= 0) {
            View makeAndAddViewVertical = makeAndAddViewVertical(i, i - this.mSelectedPosition, i2, false);
            this.mFirstPosition = i;
            i2 = makeAndAddViewVertical.getTop() - i3;
            i--;
        }
    }

    private void fillToGalleryRight() {
        int i;
        int i2;
        if (isScrollCycle()) {
            fillToGalleryRightCycle();
            return;
        }
        int i3 = this.mSpacing;
        int right = (getRight() - getLeft()) - getPaddingRight();
        int childCount = getChildCount();
        int i4 = this.mItemCount;
        View childAt = getChildAt(childCount - 1);
        if (childAt != null) {
            i = this.mFirstPosition + childCount;
            i2 = childAt.getRight() + i3;
        } else {
            i = this.mItemCount - 1;
            this.mFirstPosition = i;
            i2 = getPaddingLeft();
            this.mShouldStopFling = true;
        }
        while (i2 < right && i < i4) {
            i2 = makeAndAddView(i, i - this.mSelectedPosition, i2, true).getRight() + i3;
            i++;
        }
    }

    private void fillToGalleryBottom() {
        int i;
        int i2;
        if (isScrollCycle()) {
            fillToGalleryBottomCycle();
            return;
        }
        int i3 = this.mSpacing;
        int bottom = (getBottom() - getTop()) - getPaddingRight();
        int childCount = getChildCount();
        int i4 = this.mItemCount;
        View childAt = getChildAt(childCount - 1);
        if (childAt != null) {
            i = this.mFirstPosition + childCount;
            i2 = childAt.getBottom() + i3;
        } else {
            i = this.mItemCount - 1;
            this.mFirstPosition = i;
            i2 = getPaddingTop();
            this.mShouldStopFling = true;
        }
        while (i2 < bottom && i < i4) {
            i2 = makeAndAddViewVertical(i, i - this.mSelectedPosition, i2, true).getBottom() + i3;
            i++;
        }
    }

    private View makeAndAddView(int i, int i2, int i3, boolean z) {
        View view;
        if (this.mDataChanged || (view = this.mRecycler.get(i)) == null) {
            View view2 = this.mAdapter.getView(i, (View) null, this);
            setUpChild(view2, i2, i3, z);
            return view2;
        }
        int left = view.getLeft();
        this.mRightMost = Math.max(this.mRightMost, view.getMeasuredWidth() + left);
        this.mLeftMost = Math.min(this.mLeftMost, left);
        setUpChild(view, i2, i3, z);
        return view;
    }

    private View makeAndAddViewVertical(int i, int i2, int i3, boolean z) {
        View view;
        if (this.mDataChanged || (view = this.mRecycler.get(i)) == null) {
            View view2 = this.mAdapter.getView(i, (View) null, this);
            setUpChildVertical(view2, i2, i3, z);
            return view2;
        }
        int top = view.getTop();
        this.mRightMost = Math.max(this.mRightMost, view.getMeasuredHeight() + top);
        this.mLeftMost = Math.min(this.mLeftMost, top);
        setUpChildVertical(view, i2, i3, z);
        return view;
    }

    private void setUpChild(View view, int i, int i2, boolean z) {
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = (LayoutParams) generateDefaultLayoutParams();
        }
        boolean z2 = false;
        addViewInLayout(view, z ? -1 : 0, layoutParams);
        if (i == 0) {
            z2 = true;
        }
        view.setSelected(z2);
        view.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mSpinnerPadding.left + this.mSpinnerPadding.right, layoutParams.width), ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, this.mSpinnerPadding.top + this.mSpinnerPadding.bottom, layoutParams.height));
        int calculateTop = calculateTop(view, true);
        int measuredHeight = view.getMeasuredHeight() + calculateTop;
        int measuredWidth = view.getMeasuredWidth();
        if (z) {
            i3 = i2 + measuredWidth;
        } else {
            i3 = i2;
            i2 -= measuredWidth;
        }
        view.layout(i2, calculateTop, i3, measuredHeight);
    }

    private void setUpChildVertical(View view, int i, int i2, boolean z) {
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = (LayoutParams) generateDefaultLayoutParams();
        }
        boolean z2 = false;
        addViewInLayout(view, z ? -1 : 0, layoutParams);
        if (i == 0) {
            z2 = true;
        }
        view.setSelected(z2);
        view.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mSpinnerPadding.left + this.mSpinnerPadding.right, layoutParams.width), ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, this.mSpinnerPadding.top + this.mSpinnerPadding.bottom, layoutParams.height));
        int calculateLeft = calculateLeft(view, true);
        int measuredWidth = view.getMeasuredWidth() + calculateLeft;
        int measuredHeight = view.getMeasuredHeight();
        if (z) {
            i3 = i2 + measuredHeight;
        } else {
            i3 = i2;
            i2 -= measuredHeight;
        }
        view.layout(calculateLeft, i2, measuredWidth, i3);
    }

    private int calculateTop(View view, boolean z) {
        int i;
        int measuredHeight = z ? getMeasuredHeight() : getHeight();
        if (z) {
            i = view.getMeasuredHeight();
        } else {
            i = view.getHeight();
        }
        int i2 = this.mGravity;
        if (i2 == 16) {
            return this.mSpinnerPadding.top + ((((measuredHeight - this.mSpinnerPadding.bottom) - this.mSpinnerPadding.top) - i) / 2);
        } else if (i2 == 48) {
            return this.mSpinnerPadding.top;
        } else {
            if (i2 != 80) {
                return 0;
            }
            return (measuredHeight - this.mSpinnerPadding.bottom) - i;
        }
    }

    private int calculateLeft(View view, boolean z) {
        int i;
        int measuredWidth = z ? getMeasuredWidth() : getWidth();
        if (z) {
            i = view.getMeasuredWidth();
        } else {
            i = view.getWidth();
        }
        int i2 = this.mGravity;
        if (i2 == 1) {
            return this.mSpinnerPadding.left + ((((measuredWidth - this.mSpinnerPadding.right) - this.mSpinnerPadding.left) - i) / 2);
        } else if (i2 == 3) {
            return this.mSpinnerPadding.left;
        } else {
            if (i2 != 5) {
                return 0;
            }
            return (measuredWidth - this.mSpinnerPadding.right) - i;
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1) {
            onUp();
        } else if (action == 3) {
            onCancel();
        }
        return onTouchEvent;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.mDownTouchPosition < 0) {
            return false;
        }
        if (isScrollCycle()) {
            this.mDownTouchPosition %= getCount();
        }
        if (isSlotInCenter()) {
            scrollToChild(this.mDownTouchPosition - this.mFirstPosition);
        }
        performItemSelect(this.mDownTouchPosition);
        if (!this.mShouldCallbackOnUnselectedItemClick && this.mDownTouchPosition != this.mSelectedPosition) {
            return true;
        }
        performItemClick(this.mDownTouchView, this.mDownTouchPosition, this.mAdapter.getItemId(this.mDownTouchPosition));
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (shouldDisableScroll()) {
            return true;
        }
        if (!this.mShouldCallbackDuringFling) {
            removeCallbacks(this.mDisableSuppressSelectionChangedRunnable);
            if (!this.mSuppressSelectionChanged) {
                this.mSuppressSelectionChanged = true;
            }
        }
        if (isOrientationVertical()) {
            this.mFlingRunnable.startUsingVelocity((int) (-(f2 * getVelocityRatio())));
        } else {
            this.mFlingRunnable.startUsingVelocity((int) (-(f * getVelocityRatio())));
        }
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (shouldDisableScroll()) {
            return true;
        }
        this.mScrolling = true;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (!this.mShouldCallbackDuringFling) {
            if (this.mIsFirstScroll) {
                if (!this.mSuppressSelectionChanged) {
                    this.mSuppressSelectionChanged = true;
                }
                postDelayed(this.mDisableSuppressSelectionChangedRunnable, 250);
            }
        } else if (this.mSuppressSelectionChanged) {
            this.mSuppressSelectionChanged = false;
        }
        if (isOrientationVertical()) {
            trackMotionScrollVertical(((int) f2) * -1);
        } else {
            trackMotionScroll(((int) f) * -1);
        }
        this.mIsFirstScroll = false;
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.mFlingRunnable.stop(false);
        this.mDownTouchPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        if (this.mDownTouchPosition >= 0) {
            this.mDownTouchView = getChildAt(this.mDownTouchPosition - this.mFirstPosition);
            this.mDownTouchView.setPressed(true);
        }
        this.mIsFirstScroll = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onUp() {
        if (this.mFlingRunnable.mScroller.isFinished()) {
            scrollIntoSlots();
        }
        dispatchUnpress();
    }

    /* access modifiers changed from: package-private */
    public void onCancel() {
        onUp();
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.mDownTouchPosition >= 0) {
            performHapticFeedback(0);
            dispatchLongPress(this.mDownTouchView, this.mDownTouchPosition, getItemIdAtPosition(this.mDownTouchPosition));
        }
    }

    private void dispatchPress(View view) {
        if (view != null) {
            view.setPressed(true);
        }
        setPressed(true);
    }

    /* access modifiers changed from: protected */
    public void dispatchUnpress() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                getChildAt(childCount).setPressed(false);
            } else {
                setPressed(false);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean z) {
        if (this.mSelectedChild != null) {
            this.mSelectedChild.setPressed(z);
        }
    }

    /* access modifiers changed from: protected */
    public ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return this.mContextMenuInfo;
    }

    public boolean showContextMenuForChild(View view) {
        int positionForView = getPositionForView(view);
        if (positionForView < 0) {
            return false;
        }
        return dispatchLongPress(view, positionForView, this.mAdapter.getItemId(positionForView));
    }

    public boolean showContextMenu() {
        if (!isPressed() || this.mSelectedPosition < 0) {
            return false;
        }
        return dispatchLongPress(getChildAt(this.mSelectedPosition - this.mFirstPosition), this.mSelectedPosition, this.mSelectedRowId);
    }

    private boolean dispatchLongPress(View view, int i, long j) {
        boolean z;
        if (this.mOnItemLongClickListener != null) {
            z = this.mOnItemLongClickListener.onItemLongClick(this, this.mDownTouchView, this.mDownTouchPosition, j);
        } else {
            z = false;
        }
        if (!z) {
            this.mContextMenuInfo = new TosAdapterView.AdapterContextMenuInfo(view, i, j);
            z = super.showContextMenuForChild(this);
        }
        if (z) {
            performHapticFeedback(0);
        }
        return z;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.dispatch(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 66) {
            switch (i) {
                case 21:
                    if (movePrevious()) {
                        playSoundEffect(1);
                    }
                    return true;
                case 22:
                    if (moveNext()) {
                        playSoundEffect(3);
                    }
                    return true;
                case 23:
                    break;
            }
        }
        this.mReceivedInvokeKeyDown = true;
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 23 && i != 66) {
            return super.onKeyUp(i, keyEvent);
        }
        if (this.mReceivedInvokeKeyDown && this.mItemCount > 0) {
            dispatchPress(this.mSelectedChild);
            postDelayed(new Runnable() {
                public void run() {
                    TosGallery.this.dispatchUnpress();
                }
            }, (long) ViewConfiguration.getPressedStateDuration());
            performItemClick(getChildAt(this.mSelectedPosition - this.mFirstPosition), this.mSelectedPosition, this.mAdapter.getItemId(this.mSelectedPosition));
        }
        this.mReceivedInvokeKeyDown = false;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean movePrevious() {
        return this.mItemCount > 0 && this.mSelectedPosition > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean moveNext() {
        return this.mItemCount > 0 && this.mSelectedPosition < this.mItemCount - 1;
    }

    private boolean scrollToChild(int i) {
        View childAt = getChildAt(i);
        if (childAt == null) {
            return false;
        }
        this.mFlingRunnable.startUsingDistance(getCenterOfGallery() - getCenterOfView(childAt));
        return true;
    }

    /* access modifiers changed from: protected */
    public void setSelectedPositionInt(int i) {
        super.setSelectedPositionInt(i);
        updateSelectedItemMetadata();
    }

    private void updateSelectedItemMetadata() {
        View view = this.mSelectedChild;
        Log(" updateSelectedItemMetadata   mSelectedPosition =  " + this.mSelectedPosition + "   mFirstPosition = " + this.mFirstPosition);
        int i = this.mSelectedPosition - this.mFirstPosition;
        if (isScrollCycle() && this.mFirstPosition > this.mSelectedPosition) {
            i = (this.mItemCount - this.mFirstPosition) + this.mSelectedPosition;
        }
        View childAt = getChildAt(i);
        this.mSelectedChild = childAt;
        if (childAt != null) {
            childAt.setSelected(true);
            childAt.setFocusable(true);
            if (hasFocus()) {
                childAt.requestFocus();
            }
            if (view != null) {
                view.setSelected(false);
                view.setFocusable(false);
            }
        }
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            this.mGravity = i;
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        int i3 = this.mSelectedPosition - this.mFirstPosition;
        if (i3 < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return i3;
        }
        return i2 >= i3 ? i2 + 1 : i2;
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && this.mSelectedChild != null) {
            this.mSelectedChild.requestFocus(i);
        }
    }

    private class FlingRunnable implements Runnable {
        private int mLastFlingX;
        private int mLastFlingY;
        /* access modifiers changed from: private */
        public Scroller mScroller;

        public FlingRunnable() {
            this.mScroller = new Scroller(TosGallery.this.getContext());
        }

        private void startCommon() {
            TosGallery.this.removeCallbacks(this);
        }

        public void startUsingVelocity(int i) {
            if (i != 0) {
                startCommon();
                int i2 = 0;
                if (TosGallery.this.isOrientationVertical()) {
                    int i3 = i < 0 ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : 0;
                    this.mLastFlingY = i3;
                    this.mScroller.fling(0, i3, 0, i, 0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    TosGallery.this.post(this);
                    return;
                }
                if (i < 0) {
                    i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                }
                this.mLastFlingX = i2;
                this.mScroller.fling(i2, 0, i, 0, 0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                TosGallery.this.post(this);
            }
        }

        public void startUsingDistance(int i) {
            if (i != 0) {
                if (TosGallery.this.isOrientationVertical()) {
                    startCommon();
                    boolean unused = TosGallery.this.mScrolling = true;
                    this.mLastFlingY = 0;
                    this.mScroller.startScroll(0, 0, 0, -i, TosGallery.this.mAnimationDuration);
                    TosGallery.this.post(this);
                    return;
                }
                startCommon();
                boolean unused2 = TosGallery.this.mScrolling = true;
                this.mLastFlingX = 0;
                this.mScroller.startScroll(0, 0, -i, 0, TosGallery.this.mAnimationDuration);
                TosGallery.this.post(this);
            }
        }

        public void stop(boolean z) {
            TosGallery.this.removeCallbacks(this);
            endFling(z);
        }

        /* access modifiers changed from: private */
        public void endFling(boolean z) {
            boolean unused = TosGallery.this.mScrolling = false;
            this.mScroller.forceFinished(true);
            if (z) {
                TosGallery.this.scrollIntoSlots();
            }
            TosGallery.this.onEndFling();
        }

        public void run() {
            int i;
            if (TosGallery.this.isOrientationVertical()) {
                runVertical();
            } else if (TosGallery.this.mItemCount == 0) {
                endFling(true);
            } else {
                boolean unused = TosGallery.this.mShouldStopFling = false;
                Scroller scroller = this.mScroller;
                boolean computeScrollOffset = scroller.computeScrollOffset();
                int currX = scroller.getCurrX();
                int i2 = this.mLastFlingX - currX;
                if (i2 > 0) {
                    int unused2 = TosGallery.this.mDownTouchPosition = TosGallery.this.mFirstPosition;
                    i = Math.min(((TosGallery.this.getWidth() - TosGallery.this.getPaddingLeft()) - TosGallery.this.getPaddingRight()) - 1, i2);
                } else {
                    int unused3 = TosGallery.this.mDownTouchPosition = TosGallery.this.mFirstPosition + (TosGallery.this.getChildCount() - 1);
                    i = Math.max(-(((TosGallery.this.getWidth() - TosGallery.this.getPaddingRight()) - TosGallery.this.getPaddingLeft()) - 1), i2);
                }
                TosGallery.this.trackMotionScroll(i);
                if (!computeScrollOffset || TosGallery.this.mShouldStopFling) {
                    endFling(true);
                    return;
                }
                this.mLastFlingX = currX;
                TosGallery.this.post(this);
            }
        }

        public void runVertical() {
            int i;
            if (TosGallery.this.mItemCount == 0) {
                endFling(true);
                return;
            }
            boolean unused = TosGallery.this.mShouldStopFling = false;
            Scroller scroller = this.mScroller;
            boolean computeScrollOffset = scroller.computeScrollOffset();
            int currY = scroller.getCurrY();
            int i2 = this.mLastFlingY - currY;
            if (i2 > 0) {
                int unused2 = TosGallery.this.mDownTouchPosition = TosGallery.this.mFirstPosition;
                i = Math.min(((TosGallery.this.getHeight() - TosGallery.this.getPaddingTop()) - TosGallery.this.getPaddingBottom()) - 1, i2);
            } else {
                int unused3 = TosGallery.this.mDownTouchPosition = TosGallery.this.mFirstPosition + (TosGallery.this.getChildCount() - 1);
                i = Math.max(-(((TosGallery.this.getHeight() - TosGallery.this.getPaddingBottom()) - TosGallery.this.getPaddingTop()) - 1), i2);
            }
            TosGallery.this.trackMotionScrollVertical(i);
            if (!computeScrollOffset || TosGallery.this.mShouldStopFling) {
                endFling(true);
                return;
            }
            this.mLastFlingY = currY;
            TosGallery.this.post(this);
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    private boolean performItemSelect(int i) {
        if (i == this.mSelectedPosition) {
            return false;
        }
        setSelectedPositionInt(i);
        setNextSelectedPositionInt(i);
        checkSelectionChanged();
        return true;
    }

    public void setFirstChildOffset(int i) {
        this.mFirstChildOffset = i;
    }

    public void setFirstPosition(int i) {
        this.mFirstPosition = i;
    }

    public void setSlotInCenter(boolean z) {
        this.mIsSlotCenter = z;
    }

    public boolean isSlotInCenter() {
        return this.mIsSlotCenter;
    }

    /* access modifiers changed from: private */
    public boolean isOrientationVertical() {
        return this.mOrientation == 2;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public void setOnEndFlingListener(OnEndFlingListener onEndFlingListener) {
        this.mOnEndFlingListener = onEndFlingListener;
    }

    public void setDisableScroll(boolean z) {
        this.mIsDisableScroll = z;
    }

    public void setScrollBarBottomMargin(int i) {
        this.mScrollBarBottomMargin = i;
    }

    public void setScrollBarSize(int i) {
        this.mScrollBarSize = i;
    }

    public int getFirstPosition() {
        return this.mFirstPosition;
    }

    public int getSpacing() {
        return this.mSpacing;
    }

    public boolean isScrolling() {
        return this.mScrolling;
    }

    public int scrollGalleryItems(boolean z) {
        int i;
        if (getChildCount() == 0) {
            return 0;
        }
        if (z) {
            View childAt = getChildAt(getChildCount() - 1);
            i = Math.max((childAt.getRight() - getRight()) + getPaddingRight(), 0);
            if (i == 0 && this.mFirstPosition + getChildCount() != this.mItemCount) {
                i += childAt.getWidth();
            }
        } else {
            View childAt2 = getChildAt(0);
            i = Math.min(childAt2.getLeft() - getPaddingLeft(), 0);
            if (i == 0 && this.mFirstPosition != 0) {
                i -= childAt2.getWidth();
            }
        }
        if (!(i == 0 || this.mFlingRunnable == null)) {
            this.mFlingRunnable.startUsingDistance(i * -1);
        }
        return i * -1;
    }

    public int scrollGalleryItems(int i) {
        if (this.mFlingRunnable != null) {
            this.mFlingRunnable.startUsingDistance(i * -1);
        }
        return i * -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getItemIndexFromPoint(android.graphics.Point r7) {
        /*
            r6 = this;
            int r0 = r6.getChildCount()
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r6.getDrawingRect(r1)
            int r2 = r7.x
            int r3 = r7.y
            boolean r2 = r1.contains(r2, r3)
            r3 = -1
            if (r2 == 0) goto L_0x002f
            r2 = 0
        L_0x0018:
            if (r2 >= r0) goto L_0x002f
            android.view.View r4 = r6.getChildAt(r2)
            r4.getHitRect(r1)
            int r4 = r7.x
            int r5 = r7.y
            boolean r4 = r1.contains(r4, r5)
            if (r4 == 0) goto L_0x002c
            goto L_0x0030
        L_0x002c:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x002f:
            r2 = -1
        L_0x0030:
            if (r2 < 0) goto L_0x0036
            int r7 = r6.mFirstPosition
            int r3 = r2 + r7
        L_0x0036:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.wheelview.TosGallery.getItemIndexFromPoint(android.graphics.Point):int");
    }

    /* access modifiers changed from: protected */
    public void onEndFling() {
        if (this.mOnEndFlingListener != null) {
            this.mOnEndFlingListener.onEndFling(this);
        }
    }

    public float getVelocityRatio() {
        return this.mVelocityRatio;
    }

    public void setVelocityRatio(float f) {
        this.mVelocityRatio = f;
        if (this.mVelocityRatio < 0.5f) {
            this.mVelocityRatio = 0.5f;
        } else if (this.mVelocityRatio > 1.5f) {
            this.mVelocityRatio = 1.5f;
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldDisableScroll() {
        if (!this.mIsDisableScroll || getChildCount() < this.mItemCount) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getLeft() < getLeft()) {
            return false;
        }
        View childAt2 = getChildAt(getChildCount() - 1);
        if (childAt2 == null || childAt2.getRight() <= getRight()) {
            return true;
        }
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        if (1 != motionEvent.getAction() || this.mDownTouchPosition < 0) {
            return false;
        }
        if (this.mShouldCallbackOnUnselectedItemClick || this.mDownTouchPosition == this.mSelectedPosition) {
            performItemDoubleClick(this.mDownTouchView, this.mDownTouchPosition, this.mAdapter.getItemId(this.mDownTouchPosition));
        }
        return true;
    }

    public boolean isPointInChild(float f, float f2) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (f >= ((float) childAt.getLeft()) && f <= ((float) childAt.getRight()) && f2 >= ((float) childAt.getTop()) && f2 <= ((float) childAt.getBottom())) {
                return true;
            }
        }
        return false;
    }

    public void setScrollCycle(boolean z) {
        this.mIsScrollCycle = z;
    }

    public boolean isScrollCycle() {
        return this.mIsScrollCycle && this.mIsScrollCycleTemp;
    }

    private void fillToGalleryLeftCycle() {
        int i;
        int i2;
        int i3 = this.mSpacing;
        int paddingLeft = getPaddingLeft();
        View childAt = getChildAt(0);
        if (childAt != null) {
            i = this.mFirstPosition - 1;
            i2 = childAt.getLeft() - i3;
        } else {
            i2 = (getRight() - getLeft()) - getPaddingRight();
            this.mShouldStopFling = true;
            i = 0;
        }
        while (i2 > paddingLeft && i >= 0) {
            View makeAndAddView = makeAndAddView(i, i - this.mSelectedPosition, i2, false);
            this.mFirstPosition = i;
            i2 = makeAndAddView.getLeft() - i3;
            i--;
        }
        int i4 = this.mItemCount - 1;
        while (i2 > paddingLeft && getChildCount() < this.mItemCount) {
            View makeAndAddView2 = makeAndAddView(i4, i4 - this.mSelectedPosition, i2, false);
            this.mFirstPosition = i4;
            i2 = makeAndAddView2.getLeft() - i3;
            i4--;
        }
    }

    private void fillToGalleryTopCycle() {
        int i;
        int i2;
        int i3 = this.mSpacing;
        int paddingTop = getPaddingTop();
        View childAt = getChildAt(0);
        if (childAt != null) {
            i = this.mFirstPosition - 1;
            i2 = childAt.getTop() - i3;
        } else {
            i2 = (getBottom() - getTop()) - getPaddingBottom();
            this.mShouldStopFling = true;
            i = 0;
        }
        while (i2 > paddingTop && i >= 0) {
            View makeAndAddViewVertical = makeAndAddViewVertical(i, i - this.mSelectedPosition, i2, false);
            this.mFirstPosition = i;
            i2 = makeAndAddViewVertical.getTop() - i3;
            i--;
        }
        int i4 = this.mItemCount - 1;
        while (i2 > paddingTop && getChildCount() < this.mItemCount) {
            View makeAndAddViewVertical2 = makeAndAddViewVertical(i4, i4 - this.mSelectedPosition, i2, false);
            this.mFirstPosition = i4;
            i2 = makeAndAddViewVertical2.getTop() - i3;
            i4--;
        }
    }

    private void fillToGalleryRightCycle() {
        int i;
        int i2;
        int i3 = this.mSpacing;
        int right = (getRight() - getLeft()) - getPaddingRight();
        int childCount = getChildCount();
        int i4 = this.mItemCount;
        View childAt = getChildAt(childCount - 1);
        Log("  fillToGalleryRightCycle mFirstPosition = " + this.mFirstPosition);
        if (childAt != null) {
            i = this.mFirstPosition + childCount;
            i2 = childAt.getRight() + i3;
        } else {
            i = this.mItemCount - 1;
            this.mFirstPosition = i;
            i2 = getPaddingLeft();
            this.mShouldStopFling = true;
        }
        while (i2 < right && i < i4) {
            i2 = makeAndAddView(i, i - this.mSelectedPosition, i2, true).getRight() + i3;
            i++;
        }
        int i5 = i % i4;
        while (i2 <= right && getChildCount() < this.mItemCount) {
            i2 = makeAndAddView(i5, i5 - this.mSelectedPosition, i2, true).getRight() + i3;
            i5++;
        }
    }

    private void fillToGalleryBottomCycle() {
        int i;
        int i2;
        int i3 = this.mSpacing;
        int bottom = (getBottom() - getTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i4 = this.mItemCount;
        View childAt = getChildAt(childCount - 1);
        Log("  fillToGalleryRightCycle mFirstPosition = " + this.mFirstPosition);
        if (childAt != null) {
            i = this.mFirstPosition + childCount;
            i2 = childAt.getBottom() + i3;
        } else {
            i = this.mItemCount - 1;
            this.mFirstPosition = i;
            i2 = getPaddingTop();
            this.mShouldStopFling = true;
        }
        while (i2 < bottom && i < i4) {
            i2 = makeAndAddViewVertical(i, i - this.mSelectedPosition, i2, true).getBottom() + i3;
            i++;
        }
        int i5 = i % i4;
        while (i2 <= bottom && getChildCount() < this.mItemCount) {
            i2 = makeAndAddViewVertical(i5, i5 - this.mSelectedPosition, i2, true).getBottom() + i3;
            i5++;
        }
    }
}
