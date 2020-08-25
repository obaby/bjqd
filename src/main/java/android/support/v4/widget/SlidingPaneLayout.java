package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    static final SlidingPanelLayoutImpl IMPL;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList<DisableLayerRunnable> mPostedRunnables;
    boolean mPreservedOpenState;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    public interface PanelSlideListener {
        void onPanelClosed(@NonNull View view);

        void onPanelOpened(@NonNull View view);

        void onPanelSlide(@NonNull View view, float f);
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        public void onPanelClosed(View view) {
        }

        public void onPanelOpened(View view) {
        }

        public void onPanelSlide(View view, float f) {
        }
    }

    interface SlidingPanelLayoutImpl {
        void invalidateChildRegion(SlidingPaneLayout slidingPaneLayout, View view);
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            IMPL = new SlidingPanelLayoutImplJBMR1();
        } else if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new SlidingPanelLayoutImplJB();
        } else {
            IMPL = new SlidingPanelLayoutImplBase();
        }
    }

    public SlidingPaneLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSliderFadeColor = DEFAULT_FADE_COLOR;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList<>();
        float f = context.getResources().getDisplayMetrics().density;
        this.mOverhangSize = (int) ((32.0f * f) + 0.5f);
        setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewCompat.setImportantForAccessibility(this, 1);
        this.mDragHelper = ViewDragHelper.create(this, 0.5f, new DragHelperCallback());
        this.mDragHelper.setMinVelocity(f * 400.0f);
    }

    public void setParallaxDistance(int i) {
        this.mParallaxBy = i;
        requestLayout();
    }

    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    public void setSliderFadeColor(@ColorInt int i) {
        this.mSliderFadeColor = i;
    }

    @ColorInt
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    public void setCoveredFadeColor(@ColorInt int i) {
        this.mCoveredFadeColor = i;
    }

    @ColorInt
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public void setPanelSlideListener(@Nullable PanelSlideListener panelSlideListener) {
        this.mPanelSlideListener = panelSlideListener;
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelSlide(View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelSlide(view, this.mSlideOffset);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelOpened(View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelOpened(view);
        }
        sendAccessibilityEvent(32);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelClosed(View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelClosed(view);
        }
        sendAccessibilityEvent(32);
    }

    /* access modifiers changed from: package-private */
    public void updateObscuredViewsVisibility(View view) {
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        int i5;
        View view2 = view;
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        int width = isLayoutRtlSupport ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = isLayoutRtlSupport ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !viewIsOpaque(view)) {
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        } else {
            i4 = view.getLeft();
            i3 = view.getRight();
            i2 = view.getTop();
            i = view.getBottom();
        }
        int childCount = getChildCount();
        int i6 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            if (childAt != view2) {
                if (childAt.getVisibility() == 8) {
                    z = isLayoutRtlSupport;
                } else {
                    int max = Math.max(isLayoutRtlSupport ? paddingLeft : width, childAt.getLeft());
                    int max2 = Math.max(paddingTop, childAt.getTop());
                    if (isLayoutRtlSupport) {
                        z = isLayoutRtlSupport;
                        i5 = width;
                    } else {
                        z = isLayoutRtlSupport;
                        i5 = paddingLeft;
                    }
                    childAt.setVisibility((max < i4 || max2 < i2 || Math.min(i5, childAt.getRight()) > i3 || Math.min(height, childAt.getBottom()) > i) ? 0 : 4);
                }
                i6++;
                isLayoutRtlSupport = z;
                view2 = view;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllChildrenVisible() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    private static boolean viewIsOpaque(View view) {
        Drawable background;
        if (view.isOpaque()) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 18 && (background = view.getBackground()) != null && background.getOpacity() == -1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int size = this.mPostedRunnables.size();
        for (int i = 0; i < size; i++) {
            this.mPostedRunnables.get(i).run();
        }
        this.mPostedRunnables.clear();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x020d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r21, int r22) {
        /*
            r20 = this;
            r0 = r20
            int r1 = android.view.View.MeasureSpec.getMode(r21)
            int r2 = android.view.View.MeasureSpec.getSize(r21)
            int r3 = android.view.View.MeasureSpec.getMode(r22)
            int r4 = android.view.View.MeasureSpec.getSize(r22)
            r5 = 300(0x12c, float:4.2E-43)
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 1073741824(0x40000000, float:2.0)
            if (r1 == r7) goto L_0x0030
            boolean r8 = r20.isInEditMode()
            if (r8 == 0) goto L_0x0028
            if (r1 != r6) goto L_0x0023
            goto L_0x0047
        L_0x0023:
            if (r1 != 0) goto L_0x0047
            r2 = 300(0x12c, float:4.2E-43)
            goto L_0x0047
        L_0x0028:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Width must have an exact value or MATCH_PARENT"
            r1.<init>(r2)
            throw r1
        L_0x0030:
            if (r3 != 0) goto L_0x0047
            boolean r1 = r20.isInEditMode()
            if (r1 == 0) goto L_0x003f
            if (r3 != 0) goto L_0x0047
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 300(0x12c, float:4.2E-43)
            goto L_0x0047
        L_0x003f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Height must not be UNSPECIFIED"
            r1.<init>(r2)
            throw r1
        L_0x0047:
            r1 = 0
            if (r3 == r6) goto L_0x005b
            if (r3 == r7) goto L_0x004f
            r4 = 0
            r5 = 0
            goto L_0x0067
        L_0x004f:
            int r5 = r20.getPaddingTop()
            int r4 = r4 - r5
            int r5 = r20.getPaddingBottom()
            int r4 = r4 - r5
            r5 = r4
            goto L_0x0067
        L_0x005b:
            int r5 = r20.getPaddingTop()
            int r4 = r4 - r5
            int r5 = r20.getPaddingBottom()
            int r4 = r4 - r5
            r5 = r4
            r4 = 0
        L_0x0067:
            int r8 = r20.getPaddingLeft()
            int r8 = r2 - r8
            int r9 = r20.getPaddingRight()
            int r8 = r8 - r9
            int r9 = r20.getChildCount()
            r10 = 2
            if (r9 <= r10) goto L_0x0080
            java.lang.String r10 = "SlidingPaneLayout"
            java.lang.String r11 = "onMeasure: More than two child views are not supported."
            android.util.Log.e(r10, r11)
        L_0x0080:
            r10 = 0
            r0.mSlideableView = r10
            r13 = r4
            r14 = r8
            r4 = 0
            r11 = 0
            r12 = 0
        L_0x0088:
            r15 = 8
            r16 = 1
            if (r4 >= r9) goto L_0x012d
            android.view.View r6 = r0.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r18 = r6.getLayoutParams()
            r7 = r18
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r7 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r7
            int r10 = r6.getVisibility()
            if (r10 != r15) goto L_0x00a4
            r7.dimWhenOffset = r1
            goto L_0x0124
        L_0x00a4:
            float r10 = r7.weight
            r15 = 0
            int r10 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r10 <= 0) goto L_0x00b4
            float r10 = r7.weight
            float r12 = r12 + r10
            int r10 = r7.width
            if (r10 != 0) goto L_0x00b4
            goto L_0x0124
        L_0x00b4:
            int r10 = r7.leftMargin
            int r15 = r7.rightMargin
            int r10 = r10 + r15
            int r15 = r7.width
            r1 = -2
            if (r15 != r1) goto L_0x00c9
            int r1 = r8 - r10
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r10)
            r10 = 1073741824(0x40000000, float:2.0)
            goto L_0x00df
        L_0x00c9:
            int r1 = r7.width
            r15 = -1
            if (r1 != r15) goto L_0x00d7
            int r1 = r8 - r10
            r10 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r10)
            goto L_0x00df
        L_0x00d7:
            r10 = 1073741824(0x40000000, float:2.0)
            int r1 = r7.width
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r10)
        L_0x00df:
            int r15 = r7.height
            r10 = -2
            if (r15 != r10) goto L_0x00eb
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r10)
            goto L_0x00ff
        L_0x00eb:
            int r10 = r7.height
            r15 = -1
            if (r10 != r15) goto L_0x00f7
            r10 = 1073741824(0x40000000, float:2.0)
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r10)
            goto L_0x00ff
        L_0x00f7:
            r10 = 1073741824(0x40000000, float:2.0)
            int r15 = r7.height
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
        L_0x00ff:
            r6.measure(r1, r15)
            int r1 = r6.getMeasuredWidth()
            int r10 = r6.getMeasuredHeight()
            r15 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 != r15) goto L_0x0114
            if (r10 <= r13) goto L_0x0114
            int r13 = java.lang.Math.min(r10, r5)
        L_0x0114:
            int r14 = r14 - r1
            if (r14 >= 0) goto L_0x0119
            r1 = 1
            goto L_0x011a
        L_0x0119:
            r1 = 0
        L_0x011a:
            r7.slideable = r1
            r1 = r1 | r11
            boolean r7 = r7.slideable
            if (r7 == 0) goto L_0x0123
            r0.mSlideableView = r6
        L_0x0123:
            r11 = r1
        L_0x0124:
            int r4 = r4 + 1
            r1 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 1073741824(0x40000000, float:2.0)
            goto L_0x0088
        L_0x012d:
            if (r11 != 0) goto L_0x0134
            r1 = 0
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x022d
        L_0x0134:
            int r1 = r0.mOverhangSize
            int r1 = r8 - r1
            r3 = 0
        L_0x0139:
            if (r3 >= r9) goto L_0x022d
            android.view.View r4 = r0.getChildAt(r3)
            int r6 = r4.getVisibility()
            if (r6 != r15) goto L_0x014c
        L_0x0145:
            r19 = r1
        L_0x0147:
            r1 = 0
            r6 = 1073741824(0x40000000, float:2.0)
            goto L_0x0225
        L_0x014c:
            android.view.ViewGroup$LayoutParams r6 = r4.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r6 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r6
            int r7 = r4.getVisibility()
            if (r7 != r15) goto L_0x0159
            goto L_0x0145
        L_0x0159:
            int r7 = r6.width
            if (r7 != 0) goto L_0x0166
            float r7 = r6.weight
            r10 = 0
            int r7 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x0166
            r7 = 1
            goto L_0x0167
        L_0x0166:
            r7 = 0
        L_0x0167:
            if (r7 == 0) goto L_0x016b
            r10 = 0
            goto L_0x016f
        L_0x016b:
            int r10 = r4.getMeasuredWidth()
        L_0x016f:
            if (r11 == 0) goto L_0x01b9
            android.view.View r15 = r0.mSlideableView
            if (r4 == r15) goto L_0x01b9
            int r15 = r6.width
            if (r15 >= 0) goto L_0x0145
            if (r10 > r1) goto L_0x0182
            float r10 = r6.weight
            r15 = 0
            int r10 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r10 <= 0) goto L_0x0145
        L_0x0182:
            if (r7 == 0) goto L_0x01a7
            int r7 = r6.height
            r10 = -2
            if (r7 != r10) goto L_0x0192
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r7)
            r7 = 1073741824(0x40000000, float:2.0)
            goto L_0x01b1
        L_0x0192:
            int r7 = r6.height
            r10 = -1
            if (r7 != r10) goto L_0x019e
            r7 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r7)
            goto L_0x01b1
        L_0x019e:
            r7 = 1073741824(0x40000000, float:2.0)
            int r6 = r6.height
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r7)
            goto L_0x01b1
        L_0x01a7:
            r7 = 1073741824(0x40000000, float:2.0)
            int r6 = r4.getMeasuredHeight()
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r7)
        L_0x01b1:
            int r10 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r7)
            r4.measure(r10, r6)
            goto L_0x0145
        L_0x01b9:
            float r7 = r6.weight
            r15 = 0
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 <= 0) goto L_0x0145
            int r7 = r6.width
            if (r7 != 0) goto L_0x01e9
            int r7 = r6.height
            r15 = -2
            if (r7 != r15) goto L_0x01d4
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            int r17 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r7)
            r15 = r17
            r7 = 1073741824(0x40000000, float:2.0)
            goto L_0x01f5
        L_0x01d4:
            int r7 = r6.height
            r15 = -1
            if (r7 != r15) goto L_0x01e0
            r7 = 1073741824(0x40000000, float:2.0)
            int r17 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r7)
            goto L_0x01f3
        L_0x01e0:
            r7 = 1073741824(0x40000000, float:2.0)
            int r15 = r6.height
            int r17 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r7)
            goto L_0x01f3
        L_0x01e9:
            r7 = 1073741824(0x40000000, float:2.0)
            int r15 = r4.getMeasuredHeight()
            int r17 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r7)
        L_0x01f3:
            r15 = r17
        L_0x01f5:
            if (r11 == 0) goto L_0x020d
            int r7 = r6.leftMargin
            int r6 = r6.rightMargin
            int r7 = r7 + r6
            int r6 = r8 - r7
            r19 = r1
            r7 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r7)
            if (r10 == r6) goto L_0x0147
            r4.measure(r1, r15)
            goto L_0x0147
        L_0x020d:
            r19 = r1
            r1 = 0
            int r7 = java.lang.Math.max(r1, r14)
            float r6 = r6.weight
            float r7 = (float) r7
            float r6 = r6 * r7
            float r6 = r6 / r12
            int r6 = (int) r6
            int r10 = r10 + r6
            r6 = 1073741824(0x40000000, float:2.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r6)
            r4.measure(r7, r15)
        L_0x0225:
            int r3 = r3 + 1
            r1 = r19
            r15 = 8
            goto L_0x0139
        L_0x022d:
            int r1 = r20.getPaddingTop()
            int r13 = r13 + r1
            int r1 = r20.getPaddingBottom()
            int r13 = r13 + r1
            r0.setMeasuredDimension(r2, r13)
            r0.mCanSlide = r11
            android.support.v4.widget.ViewDragHelper r1 = r0.mDragHelper
            int r1 = r1.getViewDragState()
            if (r1 == 0) goto L_0x024b
            if (r11 != 0) goto L_0x024b
            android.support.v4.widget.ViewDragHelper r1 = r0.mDragHelper
            r1.abort()
        L_0x024b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            r18 = this;
            r0 = r18
            boolean r1 = r18.isLayoutRtlSupport()
            r2 = 1
            if (r1 == 0) goto L_0x0010
            android.support.v4.widget.ViewDragHelper r3 = r0.mDragHelper
            r4 = 2
            r3.setEdgeTrackingEnabled(r4)
            goto L_0x0015
        L_0x0010:
            android.support.v4.widget.ViewDragHelper r3 = r0.mDragHelper
            r3.setEdgeTrackingEnabled(r2)
        L_0x0015:
            int r3 = r22 - r20
            if (r1 == 0) goto L_0x001e
            int r4 = r18.getPaddingRight()
            goto L_0x0022
        L_0x001e:
            int r4 = r18.getPaddingLeft()
        L_0x0022:
            if (r1 == 0) goto L_0x0029
            int r5 = r18.getPaddingLeft()
            goto L_0x002d
        L_0x0029:
            int r5 = r18.getPaddingRight()
        L_0x002d:
            int r6 = r18.getPaddingTop()
            int r7 = r18.getChildCount()
            boolean r8 = r0.mFirstLayout
            if (r8 == 0) goto L_0x0047
            boolean r8 = r0.mCanSlide
            if (r8 == 0) goto L_0x0044
            boolean r8 = r0.mPreservedOpenState
            if (r8 == 0) goto L_0x0044
            r8 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0045
        L_0x0044:
            r8 = 0
        L_0x0045:
            r0.mSlideOffset = r8
        L_0x0047:
            r11 = r4
            r12 = r11
            r4 = 0
        L_0x004a:
            if (r4 >= r7) goto L_0x00df
            android.view.View r13 = r0.getChildAt(r4)
            int r14 = r13.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x005c
            r8 = 1065353216(0x3f800000, float:1.0)
            goto L_0x00da
        L_0x005c:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r14 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r14
            int r15 = r13.getMeasuredWidth()
            boolean r2 = r14.slideable
            if (r2 == 0) goto L_0x00a5
            int r2 = r14.leftMargin
            int r8 = r14.rightMargin
            int r2 = r2 + r8
            int r8 = r3 - r5
            int r10 = r0.mOverhangSize
            int r10 = r8 - r10
            int r10 = java.lang.Math.min(r11, r10)
            int r10 = r10 - r12
            int r10 = r10 - r2
            r0.mSlideRange = r10
            if (r1 == 0) goto L_0x0082
            int r2 = r14.rightMargin
            goto L_0x0084
        L_0x0082:
            int r2 = r14.leftMargin
        L_0x0084:
            int r16 = r12 + r2
            int r16 = r16 + r10
            int r17 = r15 / 2
            int r9 = r16 + r17
            if (r9 <= r8) goto L_0x0090
            r8 = 1
            goto L_0x0091
        L_0x0090:
            r8 = 0
        L_0x0091:
            r14.dimWhenOffset = r8
            float r8 = (float) r10
            float r9 = r0.mSlideOffset
            float r8 = r8 * r9
            int r8 = (int) r8
            int r2 = r2 + r8
            int r2 = r2 + r12
            float r8 = (float) r8
            int r9 = r0.mSlideRange
            float r9 = (float) r9
            float r8 = r8 / r9
            r0.mSlideOffset = r8
            r8 = 1065353216(0x3f800000, float:1.0)
            goto L_0x00bf
        L_0x00a5:
            boolean r2 = r0.mCanSlide
            if (r2 == 0) goto L_0x00bc
            int r2 = r0.mParallaxBy
            if (r2 == 0) goto L_0x00bc
            float r2 = r0.mSlideOffset
            r8 = 1065353216(0x3f800000, float:1.0)
            float r9 = r8 - r2
            int r2 = r0.mParallaxBy
            float r2 = (float) r2
            float r9 = r9 * r2
            int r2 = (int) r9
            r9 = r2
            r2 = r11
            goto L_0x00c0
        L_0x00bc:
            r8 = 1065353216(0x3f800000, float:1.0)
            r2 = r11
        L_0x00bf:
            r9 = 0
        L_0x00c0:
            if (r1 == 0) goto L_0x00c8
            int r10 = r3 - r2
            int r10 = r10 + r9
            int r9 = r10 - r15
            goto L_0x00cc
        L_0x00c8:
            int r9 = r2 - r9
            int r10 = r9 + r15
        L_0x00cc:
            int r12 = r13.getMeasuredHeight()
            int r12 = r12 + r6
            r13.layout(r9, r6, r10, r12)
            int r9 = r13.getWidth()
            int r11 = r11 + r9
            r12 = r2
        L_0x00da:
            int r4 = r4 + 1
            r2 = 1
            goto L_0x004a
        L_0x00df:
            boolean r1 = r0.mFirstLayout
            if (r1 == 0) goto L_0x011b
            boolean r1 = r0.mCanSlide
            if (r1 == 0) goto L_0x0106
            int r1 = r0.mParallaxBy
            if (r1 == 0) goto L_0x00f0
            float r1 = r0.mSlideOffset
            r0.parallaxOtherViews(r1)
        L_0x00f0:
            android.view.View r1 = r0.mSlideableView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r1 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r1
            boolean r1 = r1.dimWhenOffset
            if (r1 == 0) goto L_0x0116
            android.view.View r1 = r0.mSlideableView
            float r2 = r0.mSlideOffset
            int r3 = r0.mSliderFadeColor
            r0.dimChildView(r1, r2, r3)
            goto L_0x0116
        L_0x0106:
            r1 = 0
        L_0x0107:
            if (r1 >= r7) goto L_0x0116
            android.view.View r2 = r0.getChildAt(r1)
            int r3 = r0.mSliderFadeColor
            r4 = 0
            r0.dimChildView(r2, r4, r3)
            int r1 = r1 + 1
            goto L_0x0107
        L_0x0116:
            android.view.View r1 = r0.mSlideableView
            r0.updateObscuredViewsVisibility(r1)
        L_0x011b:
            r1 = 0
            r0.mFirstLayout = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.mFirstLayout = true;
        }
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = view == this.mSlideableView;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.mCanSlide && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.mPreservedOpenState = !this.mDragHelper.isViewUnder(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.mCanSlide || (this.mIsUnableToDrag && actionMasked != 0)) {
            this.mDragHelper.cancel();
            return super.onInterceptTouchEvent(motionEvent);
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.mDragHelper.cancel();
            return false;
        } else {
            if (actionMasked == 0) {
                this.mIsUnableToDrag = false;
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                if (this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) y) && isDimmed(this.mSlideableView)) {
                    z = true;
                    if (this.mDragHelper.shouldInterceptTouchEvent(motionEvent) && !z) {
                        return false;
                    }
                }
            } else if (actionMasked == 2) {
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                float abs = Math.abs(x2 - this.mInitialMotionX);
                float abs2 = Math.abs(y2 - this.mInitialMotionY);
                if (abs > ((float) this.mDragHelper.getTouchSlop()) && abs2 > abs) {
                    this.mDragHelper.cancel();
                    this.mIsUnableToDrag = true;
                    return false;
                }
            }
            z = false;
            return this.mDragHelper.shouldInterceptTouchEvent(motionEvent) ? true : true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mCanSlide) {
            return super.onTouchEvent(motionEvent);
        }
        this.mDragHelper.processTouchEvent(motionEvent);
        switch (motionEvent.getActionMasked()) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                break;
            case 1:
                if (isDimmed(this.mSlideableView)) {
                    float x2 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    float f = x2 - this.mInitialMotionX;
                    float f2 = y2 - this.mInitialMotionY;
                    int touchSlop = this.mDragHelper.getTouchSlop();
                    if ((f * f) + (f2 * f2) < ((float) (touchSlop * touchSlop)) && this.mDragHelper.isViewUnder(this.mSlideableView, (int) x2, (int) y2)) {
                        closePane(this.mSlideableView, 0);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private boolean closePane(View view, int i) {
        if (!this.mFirstLayout && !smoothSlideTo(0.0f, i)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    private boolean openPane(View view, int i) {
        if (!this.mFirstLayout && !smoothSlideTo(1.0f, i)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    @Deprecated
    public void smoothSlideOpen() {
        openPane();
    }

    public boolean openPane() {
        return openPane(this.mSlideableView, 0);
    }

    @Deprecated
    public void smoothSlideClosed() {
        closePane();
    }

    public boolean closePane() {
        return closePane(this.mSlideableView, 0);
    }

    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }

    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    /* access modifiers changed from: package-private */
    public void onPanelDragged(int i) {
        if (this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        int width = this.mSlideableView.getWidth();
        if (isLayoutRtlSupport) {
            i = (getWidth() - i) - width;
        }
        this.mSlideOffset = ((float) (i - ((isLayoutRtlSupport ? getPaddingRight() : getPaddingLeft()) + (isLayoutRtlSupport ? layoutParams.rightMargin : layoutParams.leftMargin)))) / ((float) this.mSlideRange);
        if (this.mParallaxBy != 0) {
            parallaxOtherViews(this.mSlideOffset);
        }
        if (layoutParams.dimWhenOffset) {
            dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        dispatchOnPanelSlide(this.mSlideableView);
    }

    private void dimChildView(View view, float f, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) (((float) ((-16777216 & i) >>> 24)) * f)) << 24) | (i & ViewCompat.MEASURED_SIZE_MASK);
            if (layoutParams.dimPaint == null) {
                layoutParams.dimPaint = new Paint();
            }
            layoutParams.dimPaint.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, layoutParams.dimPaint);
            }
            invalidateChildRegion(view);
        } else if (view.getLayerType() != 0) {
            if (layoutParams.dimPaint != null) {
                layoutParams.dimPaint.setColorFilter((ColorFilter) null);
            }
            DisableLayerRunnable disableLayerRunnable = new DisableLayerRunnable(view);
            this.mPostedRunnables.add(disableLayerRunnable);
            ViewCompat.postOnAnimation(this, disableLayerRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int save = canvas.save();
        if (this.mCanSlide && !layoutParams.slideable && this.mSlideableView != null) {
            canvas.getClipBounds(this.mTmpRect);
            if (isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            } else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas.clipRect(this.mTmpRect);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        return drawChild;
    }

    /* access modifiers changed from: package-private */
    public void invalidateChildRegion(View view) {
        IMPL.invalidateChildRegion(this, view);
    }

    /* access modifiers changed from: package-private */
    public boolean smoothSlideTo(float f, int i) {
        int i2;
        if (!this.mCanSlide) {
            return false;
        }
        boolean isLayoutRtlSupport = isLayoutRtlSupport();
        LayoutParams layoutParams = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (isLayoutRtlSupport) {
            i2 = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + layoutParams.rightMargin)) + (f * ((float) this.mSlideRange))) + ((float) this.mSlideableView.getWidth())));
        } else {
            i2 = (int) (((float) (getPaddingLeft() + layoutParams.leftMargin)) + (f * ((float) this.mSlideRange)));
        }
        if (!this.mDragHelper.smoothSlideViewTo(this.mSlideableView, i2, this.mSlideableView.getTop())) {
            return false;
        }
        setAllChildrenVisible();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (!this.mDragHelper.continueSettling(true)) {
            return;
        }
        if (!this.mCanSlide) {
            this.mDragHelper.abort();
        } else {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(@Nullable Drawable drawable) {
        this.mShadowDrawableLeft = drawable;
    }

    public void setShadowDrawableRight(@Nullable Drawable drawable) {
        this.mShadowDrawableRight = drawable;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), i));
    }

    public void setShadowResourceRight(int i) {
        setShadowDrawableRight(ContextCompat.getDrawable(getContext(), i));
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        int i;
        int i2;
        super.draw(canvas);
        if (isLayoutRtlSupport()) {
            drawable = this.mShadowDrawableRight;
        } else {
            drawable = this.mShadowDrawableLeft;
        }
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && drawable != null) {
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (isLayoutRtlSupport()) {
                i2 = childAt.getRight();
                i = intrinsicWidth + i2;
            } else {
                int left = childAt.getLeft();
                int i3 = left - intrinsicWidth;
                i = left;
                i2 = i3;
            }
            drawable.setBounds(i2, top, i, bottom);
            drawable.draw(canvas);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parallaxOtherViews(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.isLayoutRtlSupport()
            android.view.View r1 = r9.mSlideableView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r1 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r1
            boolean r2 = r1.dimWhenOffset
            r3 = 0
            if (r2 == 0) goto L_0x001c
            if (r0 == 0) goto L_0x0016
            int r1 = r1.rightMargin
            goto L_0x0018
        L_0x0016:
            int r1 = r1.leftMargin
        L_0x0018:
            if (r1 > 0) goto L_0x001c
            r1 = 1
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            int r2 = r9.getChildCount()
        L_0x0021:
            if (r3 >= r2) goto L_0x005d
            android.view.View r4 = r9.getChildAt(r3)
            android.view.View r5 = r9.mSlideableView
            if (r4 != r5) goto L_0x002c
            goto L_0x005a
        L_0x002c:
            float r5 = r9.mParallaxOffset
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r6 - r5
            int r7 = r9.mParallaxBy
            float r7 = (float) r7
            float r5 = r5 * r7
            int r5 = (int) r5
            r9.mParallaxOffset = r10
            float r7 = r6 - r10
            int r8 = r9.mParallaxBy
            float r8 = (float) r8
            float r7 = r7 * r8
            int r7 = (int) r7
            int r5 = r5 - r7
            if (r0 == 0) goto L_0x0046
            int r5 = -r5
        L_0x0046:
            r4.offsetLeftAndRight(r5)
            if (r1 == 0) goto L_0x005a
            if (r0 == 0) goto L_0x0051
            float r5 = r9.mParallaxOffset
            float r5 = r5 - r6
            goto L_0x0055
        L_0x0051:
            float r5 = r9.mParallaxOffset
            float r5 = r6 - r5
        L_0x0055:
            int r6 = r9.mCoveredFadeColor
            r9.dimChildView(r4, r5, r6)
        L_0x005a:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.parallaxOtherViews(float):void");
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        int i4;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i2 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i, i5 - childAt.getLeft(), i4 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z) {
            if (view.canScrollHorizontally(isLayoutRtlSupport() ? i : -i)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isDimmed(View view) {
        if (view == null) {
            return false;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!this.mCanSlide || !layoutParams.dimWhenOffset || this.mSlideOffset <= 0.0f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.isOpen = isSlideable() ? isOpen() : this.mPreservedOpenState;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.isOpen) {
            openPane();
        } else {
            closePane();
        }
        this.mPreservedOpenState = savedState.isOpen;
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        DragHelperCallback() {
        }

        public boolean tryCaptureView(View view, int i) {
            if (SlidingPaneLayout.this.mIsUnableToDrag) {
                return false;
            }
            return ((LayoutParams) view.getLayoutParams()).slideable;
        }

        public void onViewDragStateChanged(int i) {
            if (SlidingPaneLayout.this.mDragHelper.getViewDragState() != 0) {
                return;
            }
            if (SlidingPaneLayout.this.mSlideOffset == 0.0f) {
                SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.mPreservedOpenState = false;
                return;
            }
            SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
            SlidingPaneLayout.this.mPreservedOpenState = true;
        }

        public void onViewCaptured(View view, int i) {
            SlidingPaneLayout.this.setAllChildrenVisible();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            SlidingPaneLayout.this.onPanelDragged(i);
            SlidingPaneLayout.this.invalidate();
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin;
                if (f < 0.0f || (f == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.mSlideRange;
                }
                i = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.mSlideableView.getWidth();
            } else {
                i = layoutParams.leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f > 0.0f || (f == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    i += SlidingPaneLayout.this.mSlideRange;
                }
            }
            SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(i, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        public int getViewHorizontalDragRange(View view) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + layoutParams.rightMargin) + SlidingPaneLayout.this.mSlideableView.getWidth());
                return Math.max(Math.min(i, width), width - SlidingPaneLayout.this.mSlideRange);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + layoutParams.leftMargin;
            return Math.min(Math.max(i, paddingLeft), SlidingPaneLayout.this.mSlideRange + paddingLeft);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public void onEdgeDragStarted(int i, int i2) {
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, i2);
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] ATTRS = {16843137};
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(@NonNull ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(@NonNull LayoutParams layoutParams) {
            super(layoutParams);
            this.weight = layoutParams.weight;
        }

        public LayoutParams(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
            this.weight = obtainStyledAttributes.getFloat(0, 0.0f);
            obtainStyledAttributes.recycle();
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean isOpen;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isOpen = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isOpen ? 1 : 0);
        }
    }

    static class SlidingPanelLayoutImplBase implements SlidingPanelLayoutImpl {
        SlidingPanelLayoutImplBase() {
        }

        public void invalidateChildRegion(SlidingPaneLayout slidingPaneLayout, View view) {
            ViewCompat.postInvalidateOnAnimation(slidingPaneLayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    @RequiresApi(16)
    static class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase {
        private Method mGetDisplayList;
        private Field mRecreateDisplayList;

        SlidingPanelLayoutImplJB() {
            try {
                this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
            } catch (NoSuchMethodException e) {
                Log.e(SlidingPaneLayout.TAG, "Couldn't fetch getDisplayList method; dimming won't work right.", e);
            }
            try {
                this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                this.mRecreateDisplayList.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(SlidingPaneLayout.TAG, "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
            }
        }

        public void invalidateChildRegion(SlidingPaneLayout slidingPaneLayout, View view) {
            if (this.mGetDisplayList == null || this.mRecreateDisplayList == null) {
                view.invalidate();
                return;
            }
            try {
                this.mRecreateDisplayList.setBoolean(view, true);
                this.mGetDisplayList.invoke(view, (Object[]) null);
            } catch (Exception e) {
                Log.e(SlidingPaneLayout.TAG, "Error refreshing display list state", e);
            }
            super.invalidateChildRegion(slidingPaneLayout, view);
        }
    }

    @RequiresApi(17)
    static class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase {
        SlidingPanelLayoutImplJBMR1() {
        }

        public void invalidateChildRegion(SlidingPaneLayout slidingPaneLayout, View view) {
            ViewCompat.setLayerPaint(view, ((LayoutParams) view.getLayoutParams()).dimPaint);
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect = new Rect();

        AccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
            super.onInitializeAccessibilityNodeInfo(view, obtain);
            copyNodeInfoNoChildren(accessibilityNodeInfoCompat, obtain);
            obtain.recycle();
            accessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
            accessibilityNodeInfoCompat.setSource(view);
            ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(view);
            if (parentForAccessibility instanceof View) {
                accessibilityNodeInfoCompat.setParent((View) parentForAccessibility);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i);
                if (!filter(childAt) && childAt.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(childAt, 1);
                    accessibilityNodeInfoCompat.addChild(childAt);
                }
            }
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (!filter(view)) {
                return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        public boolean filter(View view) {
            return SlidingPaneLayout.this.isDimmed(view);
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.mTmpRect;
            accessibilityNodeInfoCompat2.getBoundsInParent(rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
            accessibilityNodeInfoCompat2.getBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
            accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
            accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
            accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
            accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
            accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
            accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
            accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
            accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
            accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
            accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
            accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
            accessibilityNodeInfoCompat.setMovementGranularities(accessibilityNodeInfoCompat2.getMovementGranularities());
        }
    }

    private class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(View view) {
            this.mChildView = view;
        }

        public void run() {
            if (this.mChildView.getParent() == SlidingPaneLayout.this) {
                this.mChildView.setLayerType(0, (Paint) null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }
}
