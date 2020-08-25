package cn.xports.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Scroller;
import java.util.ArrayList;

public class HVScrollView extends FrameLayout {
    static final int ANIMATED_SCROLL_GAP = 250;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private int mActivePointerId;
    private View mChildToScrollTo;
    private boolean mFillViewport;
    private boolean mFlingEnabled;
    private boolean mIsBeingDragged;
    private boolean mIsLayoutDirty;
    private float mLastMotionX;
    private float mLastMotionY;
    private long mLastScroll;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private OnScrollListener mOnScrollListener;
    private boolean mScrollViewMovedFocus;
    private Scroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public interface OnScrollListener {
        void OnScroll(int i, int i2);
    }

    private int clamp(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    public HVScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HVScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mFlingEnabled = true;
        initScrollView();
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (getScrollY() < verticalFadingEdgeLength) {
            return ((float) getScrollY()) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getLeftFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (getScrollX() < horizontalFadingEdgeLength) {
            return ((float) getScrollX()) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getRightFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int right = (getChildAt(0).getRight() - getScrollX()) - (getWidth() - getPaddingRight());
        if (right < horizontalFadingEdgeLength) {
            return ((float) right) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmountV() {
        return (int) (((float) (getBottom() - getTop())) * MAX_SCROLL_FACTOR);
    }

    public int getMaxScrollAmountH() {
        return (int) (((float) (getRight() - getLeft())) * MAX_SCROLL_FACTOR);
    }

    private void initScrollView() {
        this.mScroller = new Scroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    private boolean canScrollV() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (getHeight() < childAt.getHeight() + getPaddingTop() + getPaddingBottom()) {
            return true;
        }
        return false;
    }

    private boolean canScrollH() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return false;
        }
        if (getWidth() < childAt.getWidth() + getPaddingLeft() + getPaddingRight()) {
            return true;
        }
        return false;
    }

    public boolean isFillViewport() {
        return this.mFillViewport;
    }

    public void setFillViewport(boolean z) {
        if (z != this.mFillViewport) {
            this.mFillViewport = z;
            requestLayout();
        }
    }

    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.mSmoothScrollingEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mFillViewport) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i);
            if (!(mode == 0 && mode2 == 0) && getChildCount() > 0) {
                View childAt = getChildAt(0);
                int measuredHeight = getMeasuredHeight();
                int measuredWidth = getMeasuredWidth();
                if (childAt.getMeasuredHeight() < measuredHeight || childAt.getMeasuredWidth() < measuredWidth) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((measuredWidth - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((measuredHeight - getPaddingTop()) - getPaddingBottom(), 1073741824));
                }
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        this.mTempRect.setEmpty();
        if (keyEvent.getAction() == 0) {
            switch (keyEvent.getKeyCode()) {
                case 19:
                    if (canScrollV()) {
                        if (!keyEvent.isAltPressed()) {
                            return arrowScrollV(33);
                        }
                        return fullScrollV(33);
                    }
                    break;
                case 20:
                    if (canScrollV()) {
                        if (!keyEvent.isAltPressed()) {
                            return arrowScrollV(130);
                        }
                        return fullScrollV(130);
                    }
                    break;
                case 21:
                    if (canScrollH()) {
                        if (!keyEvent.isAltPressed()) {
                            return arrowScrollH(17);
                        }
                        return fullScrollH(17);
                    }
                    break;
                case 22:
                    if (canScrollH()) {
                        if (!keyEvent.isAltPressed()) {
                            return arrowScrollH(66);
                        }
                        return fullScrollH(66);
                    }
                    break;
            }
        }
        return false;
    }

    private boolean inChild(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        if (i2 < childAt.getTop() - scrollY || i2 >= childAt.getBottom() - scrollY || i < childAt.getLeft() - scrollX || i >= childAt.getRight() - scrollX) {
            return false;
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        int i = action & 255;
        if (i != 6) {
            switch (i) {
                case 0:
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (inChild((int) x, (int) y)) {
                        this.mLastMotionY = y;
                        this.mLastMotionX = x;
                        this.mActivePointerId = motionEvent.getPointerId(0);
                        this.mIsBeingDragged = !this.mScroller.isFinished();
                        break;
                    } else {
                        this.mIsBeingDragged = false;
                        break;
                    }
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    break;
                case 2:
                    int i2 = this.mActivePointerId;
                    if (i2 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i2);
                        float y2 = motionEvent.getY(findPointerIndex);
                        if (((int) Math.abs(y2 - this.mLastMotionY)) > this.mTouchSlop) {
                            this.mIsBeingDragged = true;
                            this.mLastMotionY = y2;
                        }
                        float x2 = motionEvent.getX(findPointerIndex);
                        if (((int) Math.abs(x2 - this.mLastMotionX)) > this.mTouchSlop) {
                            this.mIsBeingDragged = true;
                            this.mLastMotionX = x2;
                            break;
                        }
                    }
                    break;
            }
        } else {
            onSecondaryPointerUp(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 6) {
            switch (action) {
                case 0:
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    boolean inChild = inChild((int) x, (int) y);
                    this.mIsBeingDragged = inChild;
                    if (!inChild) {
                        return false;
                    }
                    if (!this.mScroller.isFinished()) {
                        this.mScroller.abortAnimation();
                    }
                    this.mLastMotionY = y;
                    this.mLastMotionX = x;
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    return true;
                case 1:
                    if (!this.mIsBeingDragged) {
                        return true;
                    }
                    if (this.mFlingEnabled) {
                        VelocityTracker velocityTracker = this.mVelocityTracker;
                        velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                        int xVelocity = (int) velocityTracker.getXVelocity();
                        int yVelocity = (int) velocityTracker.getYVelocity();
                        if (getChildCount() > 0 && Math.abs(xVelocity) <= xVelocity) {
                            Math.abs(yVelocity);
                            int i = this.mMinimumVelocity;
                        }
                    }
                    this.mActivePointerId = -1;
                    this.mIsBeingDragged = false;
                    if (this.mVelocityTracker == null) {
                        return true;
                    }
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    return true;
                case 2:
                    if (!this.mIsBeingDragged) {
                        return true;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    float y2 = motionEvent.getY(findPointerIndex);
                    int i2 = (int) (this.mLastMotionY - y2);
                    this.mLastMotionY = y2;
                    float x2 = motionEvent.getX(findPointerIndex);
                    int i3 = (int) (this.mLastMotionX - x2);
                    this.mLastMotionX = x2;
                    if (Math.abs(i3) > Math.abs(i2)) {
                        scrollBy(i3, 0);
                        if (this.mOnScrollListener == null) {
                            return true;
                        }
                        this.mOnScrollListener.OnScroll(i3, 0);
                        return true;
                    }
                    scrollBy(0, i2);
                    if (this.mOnScrollListener == null) {
                        return true;
                    }
                    this.mOnScrollListener.OnScroll(0, i2);
                    return true;
                case 3:
                    if (!this.mIsBeingDragged || getChildCount() <= 0) {
                        return true;
                    }
                    this.mActivePointerId = -1;
                    this.mIsBeingDragged = false;
                    if (this.mVelocityTracker == null) {
                        return true;
                    }
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    return true;
                default:
                    return true;
            }
        } else {
            onSecondaryPointerUp(motionEvent);
            return true;
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.mActivePointerId) {
            int i = action == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(i);
            this.mLastMotionY = motionEvent.getY(i);
            this.mActivePointerId = motionEvent.getPointerId(i);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private View findFocusableViewInBoundsV(boolean z, int i, int i2) {
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view2 = (View) focusables.get(i3);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i < bottom && top < i2) {
                boolean z3 = i < top && bottom < i2;
                if (view == null) {
                    view = view2;
                    z2 = z3;
                } else {
                    boolean z4 = (z && top < view.getTop()) || (!z && bottom > view.getBottom());
                    if (z2) {
                        if (z3) {
                            if (!z4) {
                            }
                        }
                    } else if (z3) {
                        view = view2;
                        z2 = true;
                    } else if (!z4) {
                    }
                    view = view2;
                }
            }
        }
        return view;
    }

    private View findFocusableViewInBoundsH(boolean z, int i, int i2) {
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view2 = (View) focusables.get(i3);
            int left = view2.getLeft();
            int right = view2.getRight();
            if (i < right && left < i2) {
                boolean z3 = i < left && right < i2;
                if (view == null) {
                    view = view2;
                    z2 = z3;
                } else {
                    boolean z4 = (z && left < view.getLeft()) || (!z && right > view.getRight());
                    if (z2) {
                        if (z3) {
                            if (!z4) {
                            }
                        }
                    } else if (z3) {
                        view = view2;
                        z2 = true;
                    } else if (!z4) {
                    }
                    view = view2;
                }
            }
        }
        return view;
    }

    public boolean fullScrollV(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            this.mTempRect.bottom = getChildAt(childCount - 1).getBottom();
            this.mTempRect.top = this.mTempRect.bottom - height;
        }
        return scrollAndFocusV(i, this.mTempRect.top, this.mTempRect.bottom);
    }

    public boolean fullScrollH(int i) {
        boolean z = i == 66;
        int width = getWidth();
        this.mTempRect.left = 0;
        this.mTempRect.right = width;
        if (z && getChildCount() > 0) {
            this.mTempRect.right = getChildAt(0).getRight();
            this.mTempRect.left = this.mTempRect.right - width;
        }
        return scrollAndFocusH(i, this.mTempRect.left, this.mTempRect.right);
    }

    private boolean scrollAndFocusV(int i, int i2, int i3) {
        boolean z;
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = height + scrollY;
        boolean z2 = i == 33;
        View findFocusableViewInBoundsV = findFocusableViewInBoundsV(z2, i2, i3);
        if (findFocusableViewInBoundsV == null) {
            findFocusableViewInBoundsV = this;
        }
        if (i2 < scrollY || i3 > i4) {
            doScrollY(z2 ? i2 - scrollY : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (findFocusableViewInBoundsV != findFocus() && findFocusableViewInBoundsV.requestFocus(i)) {
            this.mScrollViewMovedFocus = true;
            this.mScrollViewMovedFocus = false;
        }
        return z;
    }

    private boolean scrollAndFocusH(int i, int i2, int i3) {
        boolean z;
        int width = getWidth();
        int scrollX = getScrollX();
        int i4 = width + scrollX;
        boolean z2 = i == 17;
        View findFocusableViewInBoundsH = findFocusableViewInBoundsH(z2, i2, i3);
        if (findFocusableViewInBoundsH == null) {
            findFocusableViewInBoundsH = this;
        }
        if (i2 < scrollX || i3 > i4) {
            doScrollX(z2 ? i2 - scrollX : i3 - i4);
            z = true;
        } else {
            z = false;
        }
        if (findFocusableViewInBoundsH != findFocus() && findFocusableViewInBoundsH.requestFocus(i)) {
            this.mScrollViewMovedFocus = true;
            this.mScrollViewMovedFocus = false;
        }
        return z;
    }

    public boolean arrowScrollV(int i) {
        int bottom;
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmountV = getMaxScrollAmountV();
        if (findNextFocus == null || !isWithinDeltaOfScreenV(findNextFocus, maxScrollAmountV, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmountV) {
                maxScrollAmountV = getScrollY();
            } else if (i == 130 && getChildCount() > 0 && (bottom = getChildAt(0).getBottom() - (getScrollY() + getHeight())) < maxScrollAmountV) {
                maxScrollAmountV = bottom;
            }
            if (maxScrollAmountV == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmountV = -maxScrollAmountV;
            }
            doScrollY(maxScrollAmountV);
        } else {
            findNextFocus.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(findNextFocus, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreenV(this.mTempRect));
            findNextFocus.requestFocus(i);
        }
        if (findFocus == null || !findFocus.isFocused() || !isOffScreenV(findFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    public boolean arrowScrollH(int i) {
        int right;
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmountH = getMaxScrollAmountH();
        if (findNextFocus == null || !isWithinDeltaOfScreenH(findNextFocus, maxScrollAmountH)) {
            if (i == 17 && getScrollX() < maxScrollAmountH) {
                maxScrollAmountH = getScrollX();
            } else if (i == 66 && getChildCount() > 0 && (right = getChildAt(0).getRight() - (getScrollX() + getWidth())) < maxScrollAmountH) {
                maxScrollAmountH = right;
            }
            if (maxScrollAmountH == 0) {
                return false;
            }
            if (i != 66) {
                maxScrollAmountH = -maxScrollAmountH;
            }
            doScrollX(maxScrollAmountH);
        } else {
            findNextFocus.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(findNextFocus, this.mTempRect);
            doScrollX(computeScrollDeltaToGetChildRectOnScreenH(this.mTempRect));
            findNextFocus.requestFocus(i);
        }
        if (findFocus == null || !findFocus.isFocused() || !isOffScreenH(findFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    private boolean isOffScreenV(View view) {
        return !isWithinDeltaOfScreenV(view, 0, getHeight());
    }

    private boolean isOffScreenH(View view) {
        return !isWithinDeltaOfScreenH(view, 0);
    }

    private boolean isWithinDeltaOfScreenV(View view, int i, int i2) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return this.mTempRect.bottom + i >= getScrollY() && this.mTempRect.top - i <= getScrollY() + i2;
    }

    private boolean isWithinDeltaOfScreenH(View view, int i) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return this.mTempRect.right + i >= getScrollX() && this.mTempRect.left - i <= getScrollX() + getWidth();
    }

    private void doScrollY(int i) {
        if (i == 0) {
            return;
        }
        if (this.mSmoothScrollingEnabled) {
            smoothScrollBy(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    private void doScrollX(int i) {
        if (i == 0) {
            return;
        }
        if (this.mSmoothScrollingEnabled) {
            smoothScrollBy(i, 0);
        } else {
            scrollBy(i, 0);
        }
    }

    public void smoothScrollBy(int i, int i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250) {
                int max = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                int max2 = Math.max(0, getChildAt(0).getWidth() - ((getWidth() - getPaddingRight()) - getPaddingLeft()));
                int scrollX = getScrollX();
                int max3 = Math.max(0, Math.min(i + scrollX, max2)) - scrollX;
                this.mScroller.startScroll(scrollX, scrollY, max3, Math.max(0, Math.min(i2 + scrollY, max)) - scrollY);
                invalidate();
            } else {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.abortAnimation();
                }
                scrollBy(i, i2);
            }
            this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollTo(int i, int i2) {
        smoothScrollBy(i - getScrollX(), i2 - getScrollY());
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        return getChildAt(0).getBottom();
    }

    /* access modifiers changed from: protected */
    public int computeHorizontalScrollRange() {
        int childCount = getChildCount();
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        if (childCount == 0) {
            return width;
        }
        return getChildAt(0).getRight();
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    /* access modifiers changed from: protected */
    public int computeHorizontalScrollOffset() {
        return Math.max(0, super.computeHorizontalScrollOffset());
    }

    /* access modifiers changed from: protected */
    public void measureChild(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, 0), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                super.scrollTo(clamp(currX, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth()), clamp(currY, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight()));
            }
            awakenScrollBars();
            postInvalidate();
        }
    }

    private void scrollToChild(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        int computeScrollDeltaToGetChildRectOnScreenV = computeScrollDeltaToGetChildRectOnScreenV(this.mTempRect);
        int computeScrollDeltaToGetChildRectOnScreenH = computeScrollDeltaToGetChildRectOnScreenH(this.mTempRect);
        if (computeScrollDeltaToGetChildRectOnScreenH != 0 || computeScrollDeltaToGetChildRectOnScreenV != 0) {
            scrollBy(computeScrollDeltaToGetChildRectOnScreenH, computeScrollDeltaToGetChildRectOnScreenV);
        }
    }

    private boolean scrollToChildRect(Rect rect, boolean z) {
        int computeScrollDeltaToGetChildRectOnScreenV = computeScrollDeltaToGetChildRectOnScreenV(rect);
        int computeScrollDeltaToGetChildRectOnScreenH = computeScrollDeltaToGetChildRectOnScreenH(rect);
        boolean z2 = (computeScrollDeltaToGetChildRectOnScreenH == 0 && computeScrollDeltaToGetChildRectOnScreenV == 0) ? false : true;
        if (z2) {
            if (z) {
                scrollBy(computeScrollDeltaToGetChildRectOnScreenH, computeScrollDeltaToGetChildRectOnScreenV);
            } else {
                smoothScrollBy(computeScrollDeltaToGetChildRectOnScreenH, computeScrollDeltaToGetChildRectOnScreenV);
            }
        }
        return z2;
    }

    /* access modifiers changed from: protected */
    public int computeScrollDeltaToGetChildRectOnScreenV(Rect rect) {
        int i;
        int i2;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i3 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            i3 -= verticalFadingEdgeLength;
        }
        if (rect.bottom > i3 && rect.top > scrollY) {
            if (rect.height() > height) {
                i2 = (rect.top - scrollY) + 0;
            } else {
                i2 = (rect.bottom - i3) + 0;
            }
            return Math.min(i2, getChildAt(0).getBottom() - i3);
        } else if (rect.top >= scrollY || rect.bottom >= i3) {
            return 0;
        } else {
            if (rect.height() > height) {
                i = 0 - (i3 - rect.bottom);
            } else {
                i = 0 - (scrollY - rect.top);
            }
            return Math.max(i, -getScrollY());
        }
    }

    /* access modifiers changed from: protected */
    public int computeScrollDeltaToGetChildRectOnScreenH(Rect rect) {
        int i;
        int i2;
        if (getChildCount() == 0) {
            return 0;
        }
        int width = getWidth();
        int scrollX = getScrollX();
        int i3 = scrollX + width;
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (rect.left > 0) {
            scrollX += horizontalFadingEdgeLength;
        }
        if (rect.right < getChildAt(0).getWidth()) {
            i3 -= horizontalFadingEdgeLength;
        }
        if (rect.right > i3 && rect.left > scrollX) {
            if (rect.width() > width) {
                i2 = (rect.left - scrollX) + 0;
            } else {
                i2 = (rect.right - i3) + 0;
            }
            return Math.min(i2, getChildAt(0).getRight() - i3);
        } else if (rect.left >= scrollX || rect.right >= i3) {
            return 0;
        } else {
            if (rect.width() > width) {
                i = 0 - (i3 - rect.right);
            } else {
                i = 0 - (scrollX - rect.left);
            }
            return Math.max(i, -getScrollX());
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mScrollViewMovedFocus) {
            if (!this.mIsLayoutDirty) {
                scrollToChild(view2);
            } else {
                this.mChildToScrollTo = view2;
            }
        }
        super.requestChildFocus(view, view2);
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        View view;
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus(this, (View) null, i);
        } else {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        }
        if (view == null) {
            return false;
        }
        return view.requestFocus(i, rect);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return scrollToChildRect(rect, z);
    }

    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mIsLayoutDirty = false;
        if (this.mChildToScrollTo != null && isViewDescendantOf(this.mChildToScrollTo, this)) {
            scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        scrollTo(getScrollX(), getScrollY());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus) {
            if (isWithinDeltaOfScreenV(findFocus, 0, i4)) {
                findFocus.getDrawingRect(this.mTempRect);
                offsetDescendantRectToMyCoords(findFocus, this.mTempRect);
                doScrollY(computeScrollDeltaToGetChildRectOnScreenV(this.mTempRect));
            }
            if (isWithinDeltaOfScreenH(findFocus, getRight() - getLeft())) {
                findFocus.getDrawingRect(this.mTempRect);
                offsetDescendantRectToMyCoords(findFocus, this.mTempRect);
                doScrollX(computeScrollDeltaToGetChildRectOnScreenH(this.mTempRect));
            }
        }
    }

    private boolean isViewDescendantOf(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || !isViewDescendantOf((View) parent, view2)) {
            return false;
        }
        return true;
    }

    public void fling(int i, int i2) {
        if (getChildCount() > 0) {
            int width = getChildAt(0).getWidth();
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            int i3 = i;
            int i4 = i2;
            this.mScroller.fling(getScrollX(), getScrollY(), i3, i4, 0, Math.max(0, width - ((getWidth() - getPaddingRight()) - getPaddingLeft())), 0, Math.max(0, getChildAt(0).getHeight() - height));
            invalidate();
        }
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int clamp = clamp(i, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth());
            int clamp2 = clamp(i2, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight());
            if (clamp != getScrollX() || clamp2 != getScrollY()) {
                super.scrollTo(clamp, clamp2);
            }
        }
    }

    public boolean isFlingEnabled() {
        return this.mFlingEnabled;
    }

    public void setFlingEnabled(boolean z) {
        this.mFlingEnabled = z;
    }

    public OnScrollListener getOnScrollListener() {
        return this.mOnScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }
}
