package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.math.MathUtils;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int mActivePointerId;
    private BottomSheetCallback mCallback;
    private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() {
        public boolean tryCaptureView(View view, int i) {
            View view2;
            if (BottomSheetBehavior.this.mState == 1 || BottomSheetBehavior.this.mTouchingScrollingChild) {
                return false;
            }
            if ((BottomSheetBehavior.this.mState != 3 || BottomSheetBehavior.this.mActivePointerId != i || (view2 = (View) BottomSheetBehavior.this.mNestedScrollingChildRef.get()) == null || !view2.canScrollVertically(-1)) && BottomSheetBehavior.this.mViewRef != null && BottomSheetBehavior.this.mViewRef.get() == view) {
                return true;
            }
            return false;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.dispatchOnSlide(i2);
        }

        public void onViewDragStateChanged(int i) {
            if (i == 1) {
                BottomSheetBehavior.this.setStateInternal(1);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            int i2;
            int i3 = 3;
            if (f2 < 0.0f) {
                i = BottomSheetBehavior.this.mMinOffset;
            } else if (!BottomSheetBehavior.this.mHideable || !BottomSheetBehavior.this.shouldHide(view, f2)) {
                if (f2 == 0.0f) {
                    int top = view.getTop();
                    if (Math.abs(top - BottomSheetBehavior.this.mMinOffset) < Math.abs(top - BottomSheetBehavior.this.mMaxOffset)) {
                        i = BottomSheetBehavior.this.mMinOffset;
                    } else {
                        i2 = BottomSheetBehavior.this.mMaxOffset;
                    }
                } else {
                    i2 = BottomSheetBehavior.this.mMaxOffset;
                }
                i = i2;
                i3 = 4;
            } else {
                i = BottomSheetBehavior.this.mParentHeight;
                i3 = 5;
            }
            if (BottomSheetBehavior.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), i)) {
                BottomSheetBehavior.this.setStateInternal(2);
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, i3));
                return;
            }
            BottomSheetBehavior.this.setStateInternal(i3);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return MathUtils.clamp(i, BottomSheetBehavior.this.mMinOffset, BottomSheetBehavior.this.mHideable ? BottomSheetBehavior.this.mParentHeight : BottomSheetBehavior.this.mMaxOffset);
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return view.getLeft();
        }

        public int getViewVerticalDragRange(View view) {
            if (BottomSheetBehavior.this.mHideable) {
                return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
            }
            return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
        }
    };
    boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastNestedScrollDy;
    int mMaxOffset;
    private float mMaximumVelocity;
    int mMinOffset;
    private boolean mNestedScrolled;
    WeakReference<View> mNestedScrollingChildRef;
    int mParentHeight;
    private int mPeekHeight;
    private boolean mPeekHeightAuto;
    private int mPeekHeightMin;
    private boolean mSkipCollapsed;
    int mState = 4;
    boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    ViewDragHelper mViewDragHelper;
    WeakReference<V> mViewRef;

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public BottomSheetBehavior() {
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        TypedValue peekValue = obtainStyledAttributes.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue == null || peekValue.data != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            setPeekHeight(peekValue.data);
        }
        setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        obtainStyledAttributes.recycle();
        this.mMaximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.mState);
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state == 1 || savedState.state == 2) {
            this.mState = 4;
        } else {
            this.mState = savedState.state;
        }
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        int i2;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            ViewCompat.setFitsSystemWindows(v, true);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.mParentHeight = coordinatorLayout.getHeight();
        if (this.mPeekHeightAuto) {
            if (this.mPeekHeightMin == 0) {
                this.mPeekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            }
            i2 = Math.max(this.mPeekHeightMin, this.mParentHeight - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            i2 = this.mPeekHeight;
        }
        this.mMinOffset = Math.max(0, this.mParentHeight - v.getHeight());
        this.mMaxOffset = Math.max(this.mParentHeight - i2, this.mMinOffset);
        if (this.mState == 3) {
            ViewCompat.offsetTopAndBottom(v, this.mMinOffset);
        } else if (this.mHideable && this.mState == 5) {
            ViewCompat.offsetTopAndBottom(v, this.mParentHeight);
        } else if (this.mState == 4) {
            ViewCompat.offsetTopAndBottom(v, this.mMaxOffset);
        } else if (this.mState == 1 || this.mState == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference<>(v);
        this.mNestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            this.mIgnoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (actionMasked != 3) {
            switch (actionMasked) {
                case 0:
                    int x = (int) motionEvent.getX();
                    this.mInitialY = (int) motionEvent.getY();
                    View view = this.mNestedScrollingChildRef != null ? (View) this.mNestedScrollingChildRef.get() : null;
                    if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.mInitialY)) {
                        this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.mTouchingScrollingChild = true;
                    }
                    this.mIgnoreEvents = this.mActivePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.mInitialY);
                    break;
                case 1:
                    break;
            }
        }
        this.mTouchingScrollingChild = false;
        this.mActivePointerId = -1;
        if (this.mIgnoreEvents) {
            this.mIgnoreEvents = false;
            return false;
        }
        if (!this.mIgnoreEvents && this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        View view2 = (View) this.mNestedScrollingChildRef.get();
        if (actionMasked != 2 || view2 == null || this.mIgnoreEvents || this.mState == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || Math.abs(((float) this.mInitialY) - motionEvent.getY()) <= ((float) this.mViewDragHelper.getTouchSlop())) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.mState == 1 && actionMasked == 0) {
            return true;
        }
        if (this.mViewDragHelper != null) {
            this.mViewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (actionMasked == 2 && !this.mIgnoreEvents && Math.abs(((float) this.mInitialY) - motionEvent.getY()) > ((float) this.mViewDragHelper.getTouchSlop())) {
            this.mViewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.mIgnoreEvents;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        this.mLastNestedScrollDy = 0;
        this.mNestedScrolled = false;
        if ((i & 2) != 0) {
            return true;
        }
        return false;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        if (view == ((View) this.mNestedScrollingChildRef.get())) {
            int top = v.getTop();
            int i3 = top - i2;
            if (i2 > 0) {
                if (i3 < this.mMinOffset) {
                    iArr[1] = top - this.mMinOffset;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    setStateInternal(3);
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                }
            } else if (i2 < 0 && !view.canScrollVertically(-1)) {
                if (i3 <= this.mMaxOffset || this.mHideable) {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                } else {
                    iArr[1] = top - this.mMaxOffset;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    setStateInternal(4);
                }
            }
            dispatchOnSlide(v.getTop());
            this.mLastNestedScrollDy = i2;
            this.mNestedScrolled = true;
        }
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        int i;
        int i2 = 3;
        if (v.getTop() == this.mMinOffset) {
            setStateInternal(3);
        } else if (this.mNestedScrollingChildRef != null && view == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
            if (this.mLastNestedScrollDy > 0) {
                i = this.mMinOffset;
            } else if (!this.mHideable || !shouldHide(v, getYVelocity())) {
                if (this.mLastNestedScrollDy == 0) {
                    int top = v.getTop();
                    if (Math.abs(top - this.mMinOffset) < Math.abs(top - this.mMaxOffset)) {
                        i = this.mMinOffset;
                    } else {
                        i = this.mMaxOffset;
                    }
                } else {
                    i = this.mMaxOffset;
                }
                i2 = 4;
            } else {
                i = this.mParentHeight;
                i2 = 5;
            }
            if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), i)) {
                setStateInternal(2);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, i2));
            } else {
                setStateInternal(i2);
            }
            this.mNestedScrolled = false;
        }
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.mNestedScrollingChildRef.get() && (this.mState != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f, f2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setPeekHeight(int r4) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            r2 = -1
            if (r4 != r2) goto L_0x000c
            boolean r4 = r3.mPeekHeightAuto
            if (r4 != 0) goto L_0x0015
            r3.mPeekHeightAuto = r0
            goto L_0x0024
        L_0x000c:
            boolean r2 = r3.mPeekHeightAuto
            if (r2 != 0) goto L_0x0017
            int r2 = r3.mPeekHeight
            if (r2 == r4) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r0 = 0
            goto L_0x0024
        L_0x0017:
            r3.mPeekHeightAuto = r1
            int r1 = java.lang.Math.max(r1, r4)
            r3.mPeekHeight = r1
            int r1 = r3.mParentHeight
            int r1 = r1 - r4
            r3.mMaxOffset = r1
        L_0x0024:
            if (r0 == 0) goto L_0x003c
            int r4 = r3.mState
            r0 = 4
            if (r4 != r0) goto L_0x003c
            java.lang.ref.WeakReference<V> r4 = r3.mViewRef
            if (r4 == 0) goto L_0x003c
            java.lang.ref.WeakReference<V> r4 = r3.mViewRef
            java.lang.Object r4 = r4.get()
            android.view.View r4 = (android.view.View) r4
            if (r4 == 0) goto L_0x003c
            r4.requestLayout()
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.setPeekHeight(int):void");
    }

    public final int getPeekHeight() {
        if (this.mPeekHeightAuto) {
            return -1;
        }
        return this.mPeekHeight;
    }

    public void setHideable(boolean z) {
        this.mHideable = z;
    }

    public boolean isHideable() {
        return this.mHideable;
    }

    public void setSkipCollapsed(boolean z) {
        this.mSkipCollapsed = z;
    }

    public boolean getSkipCollapsed() {
        return this.mSkipCollapsed;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.mCallback = bottomSheetCallback;
    }

    public final void setState(final int i) {
        if (i != this.mState) {
            if (this.mViewRef != null) {
                final View view = (View) this.mViewRef.get();
                if (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view)) {
                        startSettlingAnimation(view, i);
                    } else {
                        view.post(new Runnable() {
                            public void run() {
                                BottomSheetBehavior.this.startSettlingAnimation(view, i);
                            }
                        });
                    }
                }
            } else if (i == 4 || i == 3 || (this.mHideable && i == 5)) {
                this.mState = i;
            }
        }
    }

    public final int getState() {
        return this.mState;
    }

    /* access modifiers changed from: package-private */
    public void setStateInternal(int i) {
        if (this.mState != i) {
            this.mState = i;
            View view = (View) this.mViewRef.get();
            if (view != null && this.mCallback != null) {
                this.mCallback.onStateChanged(view, i);
            }
        }
    }

    private void reset() {
        this.mActivePointerId = -1;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldHide(View view, float f) {
        if (this.mSkipCollapsed) {
            return true;
        }
        if (view.getTop() >= this.mMaxOffset && Math.abs((((float) view.getTop()) + (f * HIDE_FRICTION)) - ((float) this.mMaxOffset)) / ((float) this.mPeekHeight) > HIDE_THRESHOLD) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
            if (findScrollingChild != null) {
                return findScrollingChild;
            }
        }
        return null;
    }

    private float getYVelocity() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        return this.mVelocityTracker.getYVelocity(this.mActivePointerId);
    }

    /* access modifiers changed from: package-private */
    public void startSettlingAnimation(View view, int i) {
        int i2;
        if (i == 4) {
            i2 = this.mMaxOffset;
        } else if (i == 3) {
            i2 = this.mMinOffset;
        } else if (!this.mHideable || i != 5) {
            throw new IllegalArgumentException("Illegal state argument: " + i);
        } else {
            i2 = this.mParentHeight;
        }
        if (this.mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2)) {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
            return;
        }
        setStateInternal(i);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnSlide(int i) {
        View view = (View) this.mViewRef.get();
        if (view != null && this.mCallback != null) {
            if (i > this.mMaxOffset) {
                this.mCallback.onSlide(view, ((float) (this.mMaxOffset - i)) / ((float) (this.mParentHeight - this.mMaxOffset)));
            } else {
                this.mCallback.onSlide(view, ((float) (this.mMaxOffset - i)) / ((float) (this.mMaxOffset - this.mMinOffset)));
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int getPeekHeightMin() {
        return this.mPeekHeightMin;
    }

    private class SettleRunnable implements Runnable {
        private final int mTargetState;
        private final View mView;

        SettleRunnable(View view, int i) {
            this.mView = view;
            this.mTargetState = i;
        }

        public void run() {
            if (BottomSheetBehavior.this.mViewDragHelper == null || !BottomSheetBehavior.this.mViewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.setStateInternal(this.mTargetState);
            } else {
                ViewCompat.postOnAnimation(this.mView, this);
            }
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
}
