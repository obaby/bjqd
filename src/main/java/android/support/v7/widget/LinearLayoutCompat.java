package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int i;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i2))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                i = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                i = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i3))) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    i2 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    i2 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, i2);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    i = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    i = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                i = getPaddingLeft();
            } else {
                i = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() > this.mBaselineAlignedChildIndex) {
            View childAt = getChildAt(this.mBaselineAlignedChildIndex);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i2 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
                    if (i == 16) {
                        i2 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    } else if (i == 80) {
                        i2 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return i2 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                return true;
            }
            return false;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0340  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0342  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureVertical(int r41, int r42) {
        /*
            r40 = this;
            r7 = r40
            r8 = r41
            r9 = r42
            r10 = 0
            r7.mTotalLength = r10
            int r11 = r40.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r41)
            int r13 = android.view.View.MeasureSpec.getMode(r42)
            int r14 = r7.mBaselineAlignedChildIndex
            boolean r15 = r7.mUseLargestChild
            r16 = 0
            r17 = 1
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r18 = 0
            r19 = 1
            r20 = 0
        L_0x002a:
            r10 = 8
            r22 = r4
            if (r6 >= r11) goto L_0x019d
            android.view.View r4 = r7.getVirtualChildAt(r6)
            if (r4 != 0) goto L_0x0047
            int r4 = r7.mTotalLength
            int r10 = r7.measureNullChild(r6)
            int r4 = r4 + r10
            r7.mTotalLength = r4
            r32 = r11
            r31 = r13
            r4 = r22
            goto L_0x0191
        L_0x0047:
            r24 = r1
            int r1 = r4.getVisibility()
            if (r1 != r10) goto L_0x005e
            int r1 = r7.getChildrenSkipCount(r4, r6)
            int r6 = r6 + r1
            r32 = r11
            r31 = r13
            r4 = r22
            r1 = r24
            goto L_0x0191
        L_0x005e:
            boolean r1 = r7.hasDividerBeforeChildAt(r6)
            if (r1 == 0) goto L_0x006b
            int r1 = r7.mTotalLength
            int r10 = r7.mDividerHeight
            int r1 = r1 + r10
            r7.mTotalLength = r1
        L_0x006b:
            android.view.ViewGroup$LayoutParams r1 = r4.getLayoutParams()
            r10 = r1
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10
            float r1 = r10.weight
            float r25 = r0 + r1
            r1 = 1073741824(0x40000000, float:2.0)
            if (r13 != r1) goto L_0x00a7
            int r0 = r10.height
            if (r0 != 0) goto L_0x00a7
            float r0 = r10.weight
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a7
            int r0 = r7.mTotalLength
            int r1 = r10.topMargin
            int r1 = r1 + r0
            r26 = r2
            int r2 = r10.bottomMargin
            int r1 = r1 + r2
            int r0 = java.lang.Math.max(r0, r1)
            r7.mTotalLength = r0
            r0 = r3
            r3 = r4
            r33 = r5
            r32 = r11
            r31 = r13
            r13 = r22
            r8 = r24
            r29 = r26
            r18 = 1
            r11 = r6
            goto L_0x0117
        L_0x00a7:
            r26 = r2
            int r0 = r10.height
            if (r0 != 0) goto L_0x00b8
            float r0 = r10.weight
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x00b8
            r0 = -2
            r10.height = r0
            r2 = 0
            goto L_0x00ba
        L_0x00b8:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00ba:
            r23 = 0
            int r0 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x00c5
            int r0 = r7.mTotalLength
            r27 = r0
            goto L_0x00c7
        L_0x00c5:
            r27 = 0
        L_0x00c7:
            r0 = r40
            r8 = r24
            r24 = 1073741824(0x40000000, float:2.0)
            r1 = r4
            r28 = r2
            r29 = r26
            r2 = r6
            r9 = r3
            r3 = r41
            r30 = r4
            r32 = r11
            r31 = r13
            r13 = r22
            r11 = 1073741824(0x40000000, float:2.0)
            r4 = r23
            r33 = r5
            r5 = r42
            r11 = r6
            r6 = r27
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r0 = r28
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r1) goto L_0x00f4
            r10.height = r0
        L_0x00f4:
            int r0 = r30.getMeasuredHeight()
            int r1 = r7.mTotalLength
            int r2 = r1 + r0
            int r3 = r10.topMargin
            int r2 = r2 + r3
            int r3 = r10.bottomMargin
            int r2 = r2 + r3
            r3 = r30
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.mTotalLength = r1
            if (r15 == 0) goto L_0x0116
            int r0 = java.lang.Math.max(r0, r9)
            goto L_0x0117
        L_0x0116:
            r0 = r9
        L_0x0117:
            if (r14 < 0) goto L_0x0121
            int r6 = r11 + 1
            if (r14 != r6) goto L_0x0121
            int r1 = r7.mTotalLength
            r7.mBaselineChildTop = r1
        L_0x0121:
            if (r11 >= r14) goto L_0x0132
            float r1 = r10.weight
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 > 0) goto L_0x012a
            goto L_0x0132
        L_0x012a:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex."
            r0.<init>(r1)
            throw r0
        L_0x0132:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r12 == r1) goto L_0x013f
            int r1 = r10.width
            r2 = -1
            if (r1 != r2) goto L_0x013f
            r1 = 1
            r20 = 1
            goto L_0x0140
        L_0x013f:
            r1 = 0
        L_0x0140:
            int r2 = r10.leftMargin
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredWidth()
            int r4 = r4 + r2
            r5 = r29
            int r5 = java.lang.Math.max(r5, r4)
            int r6 = r3.getMeasuredState()
            int r6 = android.view.View.combineMeasuredStates(r8, r6)
            if (r19 == 0) goto L_0x0161
            int r8 = r10.width
            r9 = -1
            if (r8 != r9) goto L_0x0161
            r8 = 1
            goto L_0x0162
        L_0x0161:
            r8 = 0
        L_0x0162:
            float r9 = r10.weight
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 <= 0) goto L_0x0174
            if (r1 == 0) goto L_0x016b
            goto L_0x016c
        L_0x016b:
            r2 = r4
        L_0x016c:
            int r4 = java.lang.Math.max(r13, r2)
            r13 = r4
            r1 = r33
            goto L_0x017f
        L_0x0174:
            if (r1 == 0) goto L_0x0179
        L_0x0176:
            r1 = r33
            goto L_0x017b
        L_0x0179:
            r2 = r4
            goto L_0x0176
        L_0x017b:
            int r1 = java.lang.Math.max(r1, r2)
        L_0x017f:
            int r2 = r7.getChildrenSkipCount(r3, r11)
            int r2 = r2 + r11
            r3 = r0
            r19 = r8
            r4 = r13
            r0 = r25
            r39 = r5
            r5 = r1
            r1 = r6
            r6 = r2
            r2 = r39
        L_0x0191:
            int r6 = r6 + 1
            r13 = r31
            r11 = r32
            r8 = r41
            r9 = r42
            goto L_0x002a
        L_0x019d:
            r8 = r1
            r9 = r3
            r1 = r5
            r32 = r11
            r31 = r13
            r13 = r22
            r5 = r2
            int r2 = r7.mTotalLength
            if (r2 <= 0) goto L_0x01bb
            r2 = r32
            boolean r3 = r7.hasDividerBeforeChildAt(r2)
            if (r3 == 0) goto L_0x01bd
            int r3 = r7.mTotalLength
            int r4 = r7.mDividerHeight
            int r3 = r3 + r4
            r7.mTotalLength = r3
            goto L_0x01bd
        L_0x01bb:
            r2 = r32
        L_0x01bd:
            if (r15 == 0) goto L_0x020c
            r3 = r31
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L_0x01c7
            if (r3 != 0) goto L_0x020e
        L_0x01c7:
            r4 = 0
            r7.mTotalLength = r4
            r4 = 0
        L_0x01cb:
            if (r4 >= r2) goto L_0x020e
            android.view.View r6 = r7.getVirtualChildAt(r4)
            if (r6 != 0) goto L_0x01dd
            int r6 = r7.mTotalLength
            int r11 = r7.measureNullChild(r4)
            int r6 = r6 + r11
            r7.mTotalLength = r6
            goto L_0x0207
        L_0x01dd:
            int r11 = r6.getVisibility()
            if (r11 != r10) goto L_0x01e9
            int r6 = r7.getChildrenSkipCount(r6, r4)
            int r4 = r4 + r6
            goto L_0x0207
        L_0x01e9:
            android.view.ViewGroup$LayoutParams r11 = r6.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r11 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r11
            int r14 = r7.mTotalLength
            int r21 = r14 + r9
            int r10 = r11.topMargin
            int r21 = r21 + r10
            int r10 = r11.bottomMargin
            int r21 = r21 + r10
            int r6 = r7.getNextLocationOffset(r6)
            int r6 = r21 + r6
            int r6 = java.lang.Math.max(r14, r6)
            r7.mTotalLength = r6
        L_0x0207:
            int r4 = r4 + 1
            r10 = 8
            goto L_0x01cb
        L_0x020c:
            r3 = r31
        L_0x020e:
            int r4 = r7.mTotalLength
            int r6 = r40.getPaddingTop()
            int r10 = r40.getPaddingBottom()
            int r6 = r6 + r10
            int r4 = r4 + r6
            r7.mTotalLength = r4
            int r4 = r7.mTotalLength
            int r6 = r40.getSuggestedMinimumHeight()
            int r4 = java.lang.Math.max(r4, r6)
            r10 = r9
            r6 = r42
            r9 = 0
            int r4 = android.view.View.resolveSizeAndState(r4, r6, r9)
            r9 = 16777215(0xffffff, float:2.3509886E-38)
            r9 = r9 & r4
            int r11 = r7.mTotalLength
            int r9 = r9 - r11
            if (r18 != 0) goto L_0x027f
            if (r9 == 0) goto L_0x023e
            int r11 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r11 <= 0) goto L_0x023e
            goto L_0x027f
        L_0x023e:
            int r0 = java.lang.Math.max(r1, r13)
            if (r15 == 0) goto L_0x027a
            r1 = 1073741824(0x40000000, float:2.0)
            if (r3 == r1) goto L_0x027a
            r1 = 0
        L_0x0249:
            if (r1 >= r2) goto L_0x027a
            android.view.View r3 = r7.getVirtualChildAt(r1)
            if (r3 == 0) goto L_0x0277
            int r9 = r3.getVisibility()
            r11 = 8
            if (r9 != r11) goto L_0x025a
            goto L_0x0277
        L_0x025a:
            android.view.ViewGroup$LayoutParams r9 = r3.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r9 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r9
            float r9 = r9.weight
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 <= 0) goto L_0x0277
            int r9 = r3.getMeasuredWidth()
            r11 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r11)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            r3.measure(r9, r13)
        L_0x0277:
            int r1 = r1 + 1
            goto L_0x0249
        L_0x027a:
            r1 = r8
            r11 = r41
            goto L_0x037d
        L_0x027f:
            float r10 = r7.mWeightSum
            int r10 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r10 <= 0) goto L_0x0287
            float r0 = r7.mWeightSum
        L_0x0287:
            r10 = 0
            r7.mTotalLength = r10
            r11 = r0
            r0 = 0
            r39 = r8
            r8 = r1
            r1 = r39
        L_0x0291:
            if (r0 >= r2) goto L_0x036c
            android.view.View r13 = r7.getVirtualChildAt(r0)
            int r14 = r13.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x02a6
            r38 = r3
            r10 = r11
            r11 = r41
            goto L_0x0364
        L_0x02a6:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r14 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r14
            float r10 = r14.weight
            int r18 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r18 <= 0) goto L_0x030b
            float r15 = (float) r9
            float r15 = r15 * r10
            float r15 = r15 / r11
            int r15 = (int) r15
            float r11 = r11 - r10
            int r9 = r9 - r15
            int r10 = r40.getPaddingLeft()
            int r18 = r40.getPaddingRight()
            int r10 = r10 + r18
            r34 = r9
            int r9 = r14.leftMargin
            int r10 = r10 + r9
            int r9 = r14.rightMargin
            int r10 = r10 + r9
            int r9 = r14.width
            r35 = r11
            r11 = r41
            int r9 = getChildMeasureSpec(r11, r10, r9)
            int r10 = r14.height
            if (r10 != 0) goto L_0x02ea
            r10 = 1073741824(0x40000000, float:2.0)
            if (r3 == r10) goto L_0x02de
            goto L_0x02ec
        L_0x02de:
            if (r15 <= 0) goto L_0x02e1
            goto L_0x02e2
        L_0x02e1:
            r15 = 0
        L_0x02e2:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r9, r15)
            goto L_0x02fc
        L_0x02ea:
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x02ec:
            int r18 = r13.getMeasuredHeight()
            int r15 = r18 + r15
            if (r15 >= 0) goto L_0x02f5
            r15 = 0
        L_0x02f5:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r9, r15)
        L_0x02fc:
            int r9 = r13.getMeasuredState()
            r9 = r9 & -256(0xffffffffffffff00, float:NaN)
            int r1 = android.view.View.combineMeasuredStates(r1, r9)
            r9 = r34
            r10 = r35
            goto L_0x030e
        L_0x030b:
            r10 = r11
            r11 = r41
        L_0x030e:
            int r15 = r14.leftMargin
            r36 = r1
            int r1 = r14.rightMargin
            int r15 = r15 + r1
            int r1 = r13.getMeasuredWidth()
            int r1 = r1 + r15
            int r5 = java.lang.Math.max(r5, r1)
            r37 = r1
            r1 = 1073741824(0x40000000, float:2.0)
            if (r12 == r1) goto L_0x032d
            int r1 = r14.width
            r38 = r3
            r3 = -1
            if (r1 != r3) goto L_0x0330
            r1 = 1
            goto L_0x0331
        L_0x032d:
            r38 = r3
            r3 = -1
        L_0x0330:
            r1 = 0
        L_0x0331:
            if (r1 == 0) goto L_0x0334
            goto L_0x0336
        L_0x0334:
            r15 = r37
        L_0x0336:
            int r1 = java.lang.Math.max(r8, r15)
            if (r19 == 0) goto L_0x0342
            int r8 = r14.width
            if (r8 != r3) goto L_0x0342
            r8 = 1
            goto L_0x0343
        L_0x0342:
            r8 = 0
        L_0x0343:
            int r15 = r7.mTotalLength
            int r18 = r13.getMeasuredHeight()
            int r18 = r15 + r18
            int r3 = r14.topMargin
            int r18 = r18 + r3
            int r3 = r14.bottomMargin
            int r18 = r18 + r3
            int r3 = r7.getNextLocationOffset(r13)
            int r3 = r18 + r3
            int r3 = java.lang.Math.max(r15, r3)
            r7.mTotalLength = r3
            r19 = r8
            r8 = r1
            r1 = r36
        L_0x0364:
            int r0 = r0 + 1
            r11 = r10
            r3 = r38
            r10 = 0
            goto L_0x0291
        L_0x036c:
            r11 = r41
            int r0 = r7.mTotalLength
            int r3 = r40.getPaddingTop()
            int r9 = r40.getPaddingBottom()
            int r3 = r3 + r9
            int r0 = r0 + r3
            r7.mTotalLength = r0
            r0 = r8
        L_0x037d:
            if (r19 != 0) goto L_0x0384
            r3 = 1073741824(0x40000000, float:2.0)
            if (r12 == r3) goto L_0x0384
            goto L_0x0385
        L_0x0384:
            r0 = r5
        L_0x0385:
            int r3 = r40.getPaddingLeft()
            int r5 = r40.getPaddingRight()
            int r3 = r3 + r5
            int r0 = r0 + r3
            int r3 = r40.getSuggestedMinimumWidth()
            int r0 = java.lang.Math.max(r0, r3)
            int r0 = android.view.View.resolveSizeAndState(r0, r11, r1)
            r7.setMeasuredDimension(r0, r4)
            if (r20 == 0) goto L_0x03a3
            r7.forceUniformWidth(r2, r6)
        L_0x03a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0453  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0478  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureHorizontal(int r45, int r46) {
        /*
            r44 = this;
            r7 = r44
            r8 = r45
            r9 = r46
            r10 = 0
            r7.mTotalLength = r10
            int r11 = r44.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r45)
            int r13 = android.view.View.MeasureSpec.getMode(r46)
            int[] r0 = r7.mMaxAscent
            r14 = 4
            if (r0 == 0) goto L_0x001e
            int[] r0 = r7.mMaxDescent
            if (r0 != 0) goto L_0x0026
        L_0x001e:
            int[] r0 = new int[r14]
            r7.mMaxAscent = r0
            int[] r0 = new int[r14]
            r7.mMaxDescent = r0
        L_0x0026:
            int[] r15 = r7.mMaxAscent
            int[] r6 = r7.mMaxDescent
            r16 = 3
            r5 = -1
            r15[r16] = r5
            r17 = 2
            r15[r17] = r5
            r18 = 1
            r15[r18] = r5
            r15[r10] = r5
            r6[r16] = r5
            r6[r17] = r5
            r6[r18] = r5
            r6[r10] = r5
            boolean r4 = r7.mBaselineAligned
            boolean r3 = r7.mUseLargestChild
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 != r2) goto L_0x004c
            r19 = 1
            goto L_0x004e
        L_0x004c:
            r19 = 0
        L_0x004e:
            r20 = 0
            r0 = 0
            r1 = 0
            r14 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r26 = 0
            r27 = 1
            r28 = 0
        L_0x0061:
            r29 = r6
            r5 = 8
            if (r1 >= r11) goto L_0x0205
            android.view.View r6 = r7.getVirtualChildAt(r1)
            if (r6 != 0) goto L_0x007c
            int r5 = r7.mTotalLength
            int r6 = r7.measureNullChild(r1)
            int r5 = r5 + r6
            r7.mTotalLength = r5
        L_0x0076:
            r32 = r3
            r36 = r4
            goto L_0x01f5
        L_0x007c:
            int r10 = r6.getVisibility()
            if (r10 != r5) goto L_0x0088
            int r5 = r7.getChildrenSkipCount(r6, r1)
            int r1 = r1 + r5
            goto L_0x0076
        L_0x0088:
            boolean r5 = r7.hasDividerBeforeChildAt(r1)
            if (r5 == 0) goto L_0x0095
            int r5 = r7.mTotalLength
            int r10 = r7.mDividerWidth
            int r5 = r5 + r10
            r7.mTotalLength = r5
        L_0x0095:
            android.view.ViewGroup$LayoutParams r5 = r6.getLayoutParams()
            r10 = r5
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10
            float r5 = r10.weight
            float r31 = r0 + r5
            if (r12 != r2) goto L_0x00eb
            int r0 = r10.width
            if (r0 != 0) goto L_0x00eb
            float r0 = r10.weight
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00eb
            if (r19 == 0) goto L_0x00b9
            int r0 = r7.mTotalLength
            int r5 = r10.leftMargin
            int r2 = r10.rightMargin
            int r5 = r5 + r2
            int r0 = r0 + r5
            r7.mTotalLength = r0
            goto L_0x00c7
        L_0x00b9:
            int r0 = r7.mTotalLength
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r5 = r10.rightMargin
            int r2 = r2 + r5
            int r0 = java.lang.Math.max(r0, r2)
            r7.mTotalLength = r0
        L_0x00c7:
            if (r4 == 0) goto L_0x00dc
            r0 = 0
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r6.measure(r2, r2)
            r34 = r1
            r32 = r3
            r36 = r4
            r3 = r6
            r30 = -2
            goto L_0x0165
        L_0x00dc:
            r34 = r1
            r32 = r3
            r36 = r4
            r3 = r6
            r1 = 1073741824(0x40000000, float:2.0)
            r22 = 1
            r30 = -2
            goto L_0x0167
        L_0x00eb:
            int r0 = r10.width
            if (r0 != 0) goto L_0x00fa
            float r0 = r10.weight
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00fa
            r5 = -2
            r10.width = r5
            r2 = 0
            goto L_0x00fd
        L_0x00fa:
            r5 = -2
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00fd:
            int r0 = (r31 > r20 ? 1 : (r31 == r20 ? 0 : -1))
            if (r0 != 0) goto L_0x0106
            int r0 = r7.mTotalLength
            r30 = r0
            goto L_0x0108
        L_0x0106:
            r30 = 0
        L_0x0108:
            r33 = 0
            r0 = r44
            r34 = r1
            r1 = r6
            r35 = r2
            r2 = r34
            r32 = r3
            r3 = r45
            r36 = r4
            r4 = r30
            r9 = -1
            r30 = -2
            r5 = r46
            r37 = r6
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r33
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r0 = r35
            if (r0 == r9) goto L_0x012f
            r10.width = r0
        L_0x012f:
            int r0 = r37.getMeasuredWidth()
            if (r19 == 0) goto L_0x0148
            int r1 = r7.mTotalLength
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r3 = r10.rightMargin
            int r2 = r2 + r3
            r3 = r37
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = r1 + r2
            r7.mTotalLength = r1
            goto L_0x015f
        L_0x0148:
            r3 = r37
            int r1 = r7.mTotalLength
            int r2 = r1 + r0
            int r4 = r10.leftMargin
            int r2 = r2 + r4
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.mTotalLength = r1
        L_0x015f:
            if (r32 == 0) goto L_0x0165
            int r14 = java.lang.Math.max(r0, r14)
        L_0x0165:
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x0167:
            if (r13 == r1) goto L_0x0172
            int r0 = r10.height
            r2 = -1
            if (r0 != r2) goto L_0x0172
            r0 = 1
            r28 = 1
            goto L_0x0173
        L_0x0172:
            r0 = 0
        L_0x0173:
            int r2 = r10.topMargin
            int r4 = r10.bottomMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredHeight()
            int r4 = r4 + r2
            int r5 = r3.getMeasuredState()
            r6 = r26
            int r5 = android.view.View.combineMeasuredStates(r6, r5)
            if (r36 == 0) goto L_0x01b5
            int r6 = r3.getBaseline()
            r9 = -1
            if (r6 == r9) goto L_0x01b5
            int r9 = r10.gravity
            if (r9 >= 0) goto L_0x0197
            int r9 = r7.mGravity
            goto L_0x0199
        L_0x0197:
            int r9 = r10.gravity
        L_0x0199:
            r9 = r9 & 112(0x70, float:1.57E-43)
            r25 = 4
            int r9 = r9 >> 4
            r9 = r9 & -2
            int r9 = r9 >> 1
            r1 = r15[r9]
            int r1 = java.lang.Math.max(r1, r6)
            r15[r9] = r1
            r1 = r29[r9]
            int r6 = r4 - r6
            int r1 = java.lang.Math.max(r1, r6)
            r29[r9] = r1
        L_0x01b5:
            r1 = r21
            int r1 = java.lang.Math.max(r1, r4)
            if (r27 == 0) goto L_0x01c4
            int r6 = r10.height
            r9 = -1
            if (r6 != r9) goto L_0x01c4
            r6 = 1
            goto L_0x01c5
        L_0x01c4:
            r6 = 0
        L_0x01c5:
            float r9 = r10.weight
            int r9 = (r9 > r20 ? 1 : (r9 == r20 ? 0 : -1))
            if (r9 <= 0) goto L_0x01d9
            if (r0 == 0) goto L_0x01d0
        L_0x01cd:
            r10 = r24
            goto L_0x01d2
        L_0x01d0:
            r2 = r4
            goto L_0x01cd
        L_0x01d2:
            int r24 = java.lang.Math.max(r10, r2)
        L_0x01d6:
            r10 = r34
            goto L_0x01e7
        L_0x01d9:
            r10 = r24
            if (r0 == 0) goto L_0x01de
            r4 = r2
        L_0x01de:
            r2 = r23
            int r23 = java.lang.Math.max(r2, r4)
            r24 = r10
            goto L_0x01d6
        L_0x01e7:
            int r0 = r7.getChildrenSkipCount(r3, r10)
            int r0 = r0 + r10
            r21 = r1
            r26 = r5
            r27 = r6
            r1 = r0
            r0 = r31
        L_0x01f5:
            int r1 = r1 + 1
            r6 = r29
            r3 = r32
            r4 = r36
            r2 = 1073741824(0x40000000, float:2.0)
            r5 = -1
            r9 = r46
            r10 = 0
            goto L_0x0061
        L_0x0205:
            r32 = r3
            r36 = r4
            r1 = r21
            r2 = r23
            r10 = r24
            r6 = r26
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r30 = -2
            int r3 = r7.mTotalLength
            if (r3 <= 0) goto L_0x0226
            boolean r3 = r7.hasDividerBeforeChildAt(r11)
            if (r3 == 0) goto L_0x0226
            int r3 = r7.mTotalLength
            int r4 = r7.mDividerWidth
            int r3 = r3 + r4
            r7.mTotalLength = r3
        L_0x0226:
            r3 = r15[r18]
            r4 = -1
            if (r3 != r4) goto L_0x023c
            r3 = 0
            r5 = r15[r3]
            if (r5 != r4) goto L_0x023c
            r3 = r15[r17]
            if (r3 != r4) goto L_0x023c
            r3 = r15[r16]
            if (r3 == r4) goto L_0x0239
            goto L_0x023c
        L_0x0239:
            r38 = r6
            goto L_0x026f
        L_0x023c:
            r3 = r15[r16]
            r4 = 0
            r5 = r15[r4]
            r9 = r15[r18]
            r4 = r15[r17]
            int r4 = java.lang.Math.max(r9, r4)
            int r4 = java.lang.Math.max(r5, r4)
            int r3 = java.lang.Math.max(r3, r4)
            r4 = r29[r16]
            r5 = 0
            r9 = r29[r5]
            r5 = r29[r18]
            r38 = r6
            r6 = r29[r17]
            int r5 = java.lang.Math.max(r5, r6)
            int r5 = java.lang.Math.max(r9, r5)
            int r4 = java.lang.Math.max(r4, r5)
            int r3 = r3 + r4
            int r21 = java.lang.Math.max(r1, r3)
            r1 = r21
        L_0x026f:
            if (r32 == 0) goto L_0x02d2
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 == r3) goto L_0x0277
            if (r12 != 0) goto L_0x02d2
        L_0x0277:
            r3 = 0
            r7.mTotalLength = r3
            r3 = 0
        L_0x027b:
            if (r3 >= r11) goto L_0x02d2
            android.view.View r4 = r7.getVirtualChildAt(r3)
            if (r4 != 0) goto L_0x028d
            int r4 = r7.mTotalLength
            int r5 = r7.measureNullChild(r3)
            int r4 = r4 + r5
            r7.mTotalLength = r4
            goto L_0x029a
        L_0x028d:
            int r5 = r4.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x029d
            int r4 = r7.getChildrenSkipCount(r4, r3)
            int r3 = r3 + r4
        L_0x029a:
            r39 = r1
            goto L_0x02cd
        L_0x029d:
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            if (r19 == 0) goto L_0x02b6
            int r6 = r7.mTotalLength
            int r9 = r5.leftMargin
            int r9 = r9 + r14
            int r5 = r5.rightMargin
            int r9 = r9 + r5
            int r4 = r7.getNextLocationOffset(r4)
            int r9 = r9 + r4
            int r6 = r6 + r9
            r7.mTotalLength = r6
            goto L_0x029a
        L_0x02b6:
            int r6 = r7.mTotalLength
            int r9 = r6 + r14
            r39 = r1
            int r1 = r5.leftMargin
            int r9 = r9 + r1
            int r1 = r5.rightMargin
            int r9 = r9 + r1
            int r1 = r7.getNextLocationOffset(r4)
            int r9 = r9 + r1
            int r1 = java.lang.Math.max(r6, r9)
            r7.mTotalLength = r1
        L_0x02cd:
            int r3 = r3 + 1
            r1 = r39
            goto L_0x027b
        L_0x02d2:
            r39 = r1
            int r1 = r7.mTotalLength
            int r3 = r44.getPaddingLeft()
            int r4 = r44.getPaddingRight()
            int r3 = r3 + r4
            int r1 = r1 + r3
            r7.mTotalLength = r1
            int r1 = r7.mTotalLength
            int r3 = r44.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r1, r3)
            r3 = 0
            int r1 = android.view.View.resolveSizeAndState(r1, r8, r3)
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r3 = r3 & r1
            int r4 = r7.mTotalLength
            int r3 = r3 - r4
            if (r22 != 0) goto L_0x0343
            if (r3 == 0) goto L_0x0301
            int r5 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x0301
            goto L_0x0343
        L_0x0301:
            int r0 = java.lang.Math.max(r2, r10)
            if (r32 == 0) goto L_0x033d
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 == r2) goto L_0x033d
            r2 = 0
        L_0x030c:
            if (r2 >= r11) goto L_0x033d
            android.view.View r3 = r7.getVirtualChildAt(r2)
            if (r3 == 0) goto L_0x033a
            int r5 = r3.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x031d
            goto L_0x033a
        L_0x031d:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            float r5 = r5.weight
            int r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x033a
            r5 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r5)
            int r9 = r3.getMeasuredHeight()
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r5)
            r3.measure(r6, r9)
        L_0x033a:
            int r2 = r2 + 1
            goto L_0x030c
        L_0x033d:
            r42 = r11
            r3 = r46
            goto L_0x04eb
        L_0x0343:
            float r5 = r7.mWeightSum
            int r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x034b
            float r0 = r7.mWeightSum
        L_0x034b:
            r5 = -1
            r15[r16] = r5
            r15[r17] = r5
            r15[r18] = r5
            r6 = 0
            r15[r6] = r5
            r29[r16] = r5
            r29[r17] = r5
            r29[r18] = r5
            r29[r6] = r5
            r7.mTotalLength = r6
            r10 = r2
            r9 = r38
            r6 = -1
            r2 = r0
            r0 = 0
        L_0x0365:
            if (r0 >= r11) goto L_0x0491
            android.view.View r14 = r7.getVirtualChildAt(r0)
            if (r14 == 0) goto L_0x0480
            int r5 = r14.getVisibility()
            r4 = 8
            if (r5 != r4) goto L_0x0377
            goto L_0x0480
        L_0x0377:
            android.view.ViewGroup$LayoutParams r5 = r14.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            float r4 = r5.weight
            int r21 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r21 <= 0) goto L_0x03e0
            float r8 = (float) r3
            float r8 = r8 * r4
            float r8 = r8 / r2
            int r8 = (int) r8
            float r2 = r2 - r4
            int r3 = r3 - r8
            int r4 = r44.getPaddingTop()
            int r21 = r44.getPaddingBottom()
            int r4 = r4 + r21
            r40 = r2
            int r2 = r5.topMargin
            int r4 = r4 + r2
            int r2 = r5.bottomMargin
            int r4 = r4 + r2
            int r2 = r5.height
            r41 = r3
            r42 = r11
            r3 = r46
            r11 = -1
            int r2 = getChildMeasureSpec(r3, r4, r2)
            int r4 = r5.width
            if (r4 != 0) goto L_0x03be
            r4 = 1073741824(0x40000000, float:2.0)
            if (r12 == r4) goto L_0x03b2
            goto L_0x03c0
        L_0x03b2:
            if (r8 <= 0) goto L_0x03b5
            goto L_0x03b6
        L_0x03b5:
            r8 = 0
        L_0x03b6:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4)
            r14.measure(r8, r2)
            goto L_0x03d0
        L_0x03be:
            r4 = 1073741824(0x40000000, float:2.0)
        L_0x03c0:
            int r21 = r14.getMeasuredWidth()
            int r8 = r21 + r8
            if (r8 >= 0) goto L_0x03c9
            r8 = 0
        L_0x03c9:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4)
            r14.measure(r8, r2)
        L_0x03d0:
            int r2 = r14.getMeasuredState()
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r2 & r4
            int r9 = android.view.View.combineMeasuredStates(r9, r2)
            r2 = r40
            r4 = r41
            goto L_0x03e6
        L_0x03e0:
            r4 = r3
            r42 = r11
            r3 = r46
            r11 = -1
        L_0x03e6:
            if (r19 == 0) goto L_0x0405
            int r8 = r7.mTotalLength
            int r21 = r14.getMeasuredWidth()
            int r11 = r5.leftMargin
            int r21 = r21 + r11
            int r11 = r5.rightMargin
            int r21 = r21 + r11
            int r11 = r7.getNextLocationOffset(r14)
            int r21 = r21 + r11
            int r8 = r8 + r21
            r7.mTotalLength = r8
            r43 = r2
        L_0x0402:
            r2 = 1073741824(0x40000000, float:2.0)
            goto L_0x0420
        L_0x0405:
            int r8 = r7.mTotalLength
            int r11 = r14.getMeasuredWidth()
            int r11 = r11 + r8
            r43 = r2
            int r2 = r5.leftMargin
            int r11 = r11 + r2
            int r2 = r5.rightMargin
            int r11 = r11 + r2
            int r2 = r7.getNextLocationOffset(r14)
            int r11 = r11 + r2
            int r2 = java.lang.Math.max(r8, r11)
            r7.mTotalLength = r2
            goto L_0x0402
        L_0x0420:
            if (r13 == r2) goto L_0x0429
            int r2 = r5.height
            r8 = -1
            if (r2 != r8) goto L_0x0429
            r2 = 1
            goto L_0x042a
        L_0x0429:
            r2 = 0
        L_0x042a:
            int r8 = r5.topMargin
            int r11 = r5.bottomMargin
            int r8 = r8 + r11
            int r11 = r14.getMeasuredHeight()
            int r11 = r11 + r8
            int r6 = java.lang.Math.max(r6, r11)
            if (r2 == 0) goto L_0x043b
            goto L_0x043c
        L_0x043b:
            r8 = r11
        L_0x043c:
            int r2 = java.lang.Math.max(r10, r8)
            if (r27 == 0) goto L_0x0449
            int r8 = r5.height
            r10 = -1
            if (r8 != r10) goto L_0x044a
            r8 = 1
            goto L_0x044b
        L_0x0449:
            r10 = -1
        L_0x044a:
            r8 = 0
        L_0x044b:
            if (r36 == 0) goto L_0x0478
            int r14 = r14.getBaseline()
            if (r14 == r10) goto L_0x0478
            int r10 = r5.gravity
            if (r10 >= 0) goto L_0x045a
            int r5 = r7.mGravity
            goto L_0x045c
        L_0x045a:
            int r5 = r5.gravity
        L_0x045c:
            r5 = r5 & 112(0x70, float:1.57E-43)
            r21 = 4
            int r5 = r5 >> 4
            r5 = r5 & -2
            int r5 = r5 >> 1
            r10 = r15[r5]
            int r10 = java.lang.Math.max(r10, r14)
            r15[r5] = r10
            r10 = r29[r5]
            int r11 = r11 - r14
            int r10 = java.lang.Math.max(r10, r11)
            r29[r5] = r10
            goto L_0x047a
        L_0x0478:
            r21 = 4
        L_0x047a:
            r10 = r2
            r27 = r8
            r2 = r43
            goto L_0x0487
        L_0x0480:
            r4 = r3
            r42 = r11
            r3 = r46
            r21 = 4
        L_0x0487:
            int r0 = r0 + 1
            r3 = r4
            r11 = r42
            r5 = -1
            r8 = r45
            goto L_0x0365
        L_0x0491:
            r42 = r11
            r3 = r46
            int r0 = r7.mTotalLength
            int r2 = r44.getPaddingLeft()
            int r4 = r44.getPaddingRight()
            int r2 = r2 + r4
            int r0 = r0 + r2
            r7.mTotalLength = r0
            r0 = r15[r18]
            r2 = -1
            if (r0 != r2) goto L_0x04b8
            r0 = 0
            r4 = r15[r0]
            if (r4 != r2) goto L_0x04b8
            r0 = r15[r17]
            if (r0 != r2) goto L_0x04b8
            r0 = r15[r16]
            if (r0 == r2) goto L_0x04b6
            goto L_0x04b8
        L_0x04b6:
            r0 = r6
            goto L_0x04e6
        L_0x04b8:
            r0 = r15[r16]
            r2 = 0
            r4 = r15[r2]
            r5 = r15[r18]
            r8 = r15[r17]
            int r5 = java.lang.Math.max(r5, r8)
            int r4 = java.lang.Math.max(r4, r5)
            int r0 = java.lang.Math.max(r0, r4)
            r4 = r29[r16]
            r2 = r29[r2]
            r5 = r29[r18]
            r8 = r29[r17]
            int r5 = java.lang.Math.max(r5, r8)
            int r2 = java.lang.Math.max(r2, r5)
            int r2 = java.lang.Math.max(r4, r2)
            int r0 = r0 + r2
            int r0 = java.lang.Math.max(r6, r0)
        L_0x04e6:
            r39 = r0
            r38 = r9
            r0 = r10
        L_0x04eb:
            if (r27 != 0) goto L_0x04f3
            r2 = 1073741824(0x40000000, float:2.0)
            if (r13 == r2) goto L_0x04f3
            r39 = r0
        L_0x04f3:
            int r0 = r44.getPaddingTop()
            int r2 = r44.getPaddingBottom()
            int r0 = r0 + r2
            int r0 = r39 + r0
            int r2 = r44.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r0, r2)
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r38 & r2
            r1 = r1 | r2
            int r2 = r38 << 16
            int r0 = android.view.View.resolveSizeAndState(r0, r3, r2)
            r7.setMeasuredDimension(r1, r0)
            if (r28 == 0) goto L_0x051d
            r1 = r42
            r0 = r45
            r7.forceUniformHeight(r1, r0)
        L_0x051d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutVertical(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.mGravity & 112;
        int i9 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i8 == 16) {
            i5 = (((i4 - i2) - this.mTotalLength) / 2) + getPaddingTop();
        } else if (i8 != 80) {
            i5 = getPaddingTop();
        } else {
            i5 = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        }
        int i10 = 0;
        while (i10 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i10);
            if (virtualChildAt == null) {
                i5 += measureNullChild(i10);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i11 = layoutParams.gravity;
                if (i11 < 0) {
                    i11 = i9;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i11, ViewCompat.getLayoutDirection(this)) & 7;
                if (absoluteGravity == 1) {
                    i6 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (absoluteGravity != 5) {
                    i6 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i6 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                int i12 = i6;
                if (hasDividerBeforeChildAt(i10)) {
                    i5 += this.mDividerHeight;
                }
                int i13 = i5 + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i12, i13 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i10 += getChildrenSkipCount(virtualChildAt, i10);
                i5 = i13 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
            }
            i10++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutHorizontal(int r28, int r29, int r30, int r31) {
        /*
            r27 = this;
            r6 = r27
            boolean r2 = android.support.v7.widget.ViewUtils.isLayoutRtl(r27)
            int r7 = r27.getPaddingTop()
            int r3 = r31 - r29
            int r4 = r27.getPaddingBottom()
            int r8 = r3 - r4
            int r3 = r3 - r7
            int r4 = r27.getPaddingBottom()
            int r9 = r3 - r4
            int r10 = r27.getVirtualChildCount()
            int r3 = r6.mGravity
            r4 = 8388615(0x800007, float:1.1754953E-38)
            r3 = r3 & r4
            int r4 = r6.mGravity
            r11 = r4 & 112(0x70, float:1.57E-43)
            boolean r12 = r6.mBaselineAligned
            int[] r13 = r6.mMaxAscent
            int[] r14 = r6.mMaxDescent
            int r4 = android.support.v4.view.ViewCompat.getLayoutDirection(r27)
            int r3 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r3, r4)
            r15 = 2
            r5 = 1
            if (r3 == r5) goto L_0x004e
            r4 = 5
            if (r3 == r4) goto L_0x0041
            int r0 = r27.getPaddingLeft()
            goto L_0x0059
        L_0x0041:
            int r3 = r27.getPaddingLeft()
            int r3 = r3 + r30
            int r3 = r3 - r28
            int r0 = r6.mTotalLength
            int r0 = r3 - r0
            goto L_0x0059
        L_0x004e:
            int r3 = r27.getPaddingLeft()
            int r0 = r30 - r28
            int r1 = r6.mTotalLength
            int r0 = r0 - r1
            int r0 = r0 / r15
            int r0 = r0 + r3
        L_0x0059:
            r1 = 0
            if (r2 == 0) goto L_0x0063
            int r2 = r10 + -1
            r16 = r2
            r17 = -1
            goto L_0x0067
        L_0x0063:
            r16 = 0
            r17 = 1
        L_0x0067:
            r3 = 0
        L_0x0068:
            if (r3 >= r10) goto L_0x014b
            int r1 = r17 * r3
            int r2 = r16 + r1
            android.view.View r1 = r6.getVirtualChildAt(r2)
            if (r1 != 0) goto L_0x0085
            int r1 = r6.measureNullChild(r2)
            int r0 = r0 + r1
        L_0x0079:
            r26 = r7
            r23 = r10
            r24 = r11
            r18 = 1
            r20 = -1
            goto L_0x013f
        L_0x0085:
            int r5 = r1.getVisibility()
            r15 = 8
            if (r5 == r15) goto L_0x013b
            int r15 = r1.getMeasuredWidth()
            int r5 = r1.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r20 = r1.getLayoutParams()
            r4 = r20
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r4 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r4
            if (r12 == 0) goto L_0x00ad
            r22 = r3
            int r3 = r4.height
            r23 = r10
            r10 = -1
            if (r3 == r10) goto L_0x00b1
            int r3 = r1.getBaseline()
            goto L_0x00b2
        L_0x00ad:
            r22 = r3
            r23 = r10
        L_0x00b1:
            r3 = -1
        L_0x00b2:
            int r10 = r4.gravity
            if (r10 >= 0) goto L_0x00b7
            r10 = r11
        L_0x00b7:
            r10 = r10 & 112(0x70, float:1.57E-43)
            r24 = r11
            r11 = 16
            if (r10 == r11) goto L_0x00f6
            r11 = 48
            if (r10 == r11) goto L_0x00e3
            r11 = 80
            if (r10 == r11) goto L_0x00cc
            r3 = r7
            r11 = -1
        L_0x00c9:
            r18 = 1
            goto L_0x0104
        L_0x00cc:
            int r10 = r8 - r5
            int r11 = r4.bottomMargin
            int r10 = r10 - r11
            r11 = -1
            if (r3 == r11) goto L_0x00e1
            int r20 = r1.getMeasuredHeight()
            int r20 = r20 - r3
            r3 = 2
            r21 = r14[r3]
            int r21 = r21 - r20
            int r10 = r10 - r21
        L_0x00e1:
            r3 = r10
            goto L_0x00c9
        L_0x00e3:
            r11 = -1
            int r10 = r4.topMargin
            int r10 = r10 + r7
            if (r3 == r11) goto L_0x00f2
            r18 = 1
            r20 = r13[r18]
            int r20 = r20 - r3
            int r10 = r10 + r20
            goto L_0x00f4
        L_0x00f2:
            r18 = 1
        L_0x00f4:
            r3 = r10
            goto L_0x0104
        L_0x00f6:
            r11 = -1
            r18 = 1
            int r3 = r9 - r5
            r10 = 2
            int r3 = r3 / r10
            int r3 = r3 + r7
            int r10 = r4.topMargin
            int r3 = r3 + r10
            int r10 = r4.bottomMargin
            int r3 = r3 - r10
        L_0x0104:
            boolean r10 = r6.hasDividerBeforeChildAt(r2)
            if (r10 == 0) goto L_0x010d
            int r10 = r6.mDividerWidth
            int r0 = r0 + r10
        L_0x010d:
            int r10 = r4.leftMargin
            int r10 = r10 + r0
            int r0 = r6.getLocationOffset(r1)
            int r19 = r10 + r0
            r0 = r27
            r25 = r1
            r11 = r2
            r2 = r19
            r19 = r22
            r26 = r7
            r20 = -1
            r7 = r4
            r4 = r15
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r0 = r7.rightMargin
            int r15 = r15 + r0
            r0 = r25
            int r1 = r6.getNextLocationOffset(r0)
            int r15 = r15 + r1
            int r10 = r10 + r15
            int r0 = r6.getChildrenSkipCount(r0, r11)
            int r3 = r19 + r0
            r0 = r10
            goto L_0x013f
        L_0x013b:
            r19 = r3
            goto L_0x0079
        L_0x013f:
            int r3 = r3 + 1
            r10 = r23
            r11 = r24
            r7 = r26
            r5 = 1
            r15 = 2
            goto L_0x0068
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((8388615 & this.mGravity) != i2) {
            this.mGravity = i2 | (this.mGravity & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        if ((this.mGravity & 112) != i2) {
            this.mGravity = i2 | (this.mGravity & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }
}
