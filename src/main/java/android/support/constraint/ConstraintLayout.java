package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.2";
    SparseArray<View> mChildrenByIds = new SparseArray<>();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    private boolean mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int mMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 3;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>(100);

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public Object getDesignInformation(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if (this.mDesignIds == null || !this.mDesignIds.containsKey(str)) {
            return null;
        }
        return this.mDesignIds.get(str);
    }

    public ConstraintLayout(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attributeSet) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), resourceId);
                    } catch (Resources.NotFoundException unused) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = USE_CONSTRAINTS_HELPER;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.mLayoutWidget.remove(viewWidget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(viewWidget);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void setMinWidth(int i) {
        if (i != this.mMinWidth) {
            this.mMinWidth = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.mMinHeight) {
            this.mMinHeight = i;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int i) {
        if (i != this.mMaxWidth) {
            this.mMaxWidth = i;
            requestLayout();
        }
    }

    public void setMaxHeight(int i) {
        if (i != this.mMaxHeight) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                z = USE_CONSTRAINTS_HELPER;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void setChildrenConstraints() {
        int i;
        int i2;
        int i3;
        float f;
        int i4;
        ConstraintWidget targetWidget;
        ConstraintWidget targetWidget2;
        ConstraintWidget targetWidget3;
        ConstraintWidget targetWidget4;
        int i5;
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        boolean z = false;
        if (isInEditMode) {
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = getChildAt(i6);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).setDebugName(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            ConstraintWidget viewWidget = getViewWidget(getChildAt(i7));
            if (viewWidget != null) {
                viewWidget.reset();
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt2 = getChildAt(i8);
                if (childAt2.getId() == this.mConstraintSetId && (childAt2 instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        if (this.mConstraintSet != null) {
            this.mConstraintSet.applyToInternal(this);
        }
        this.mLayoutWidget.removeAllChildren();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i9 = 0; i9 < size; i9++) {
                this.mConstraintHelpers.get(i9).updatePreLayout(this);
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt3 = getChildAt(i10);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).updatePreLayout(this);
            }
        }
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt4 = getChildAt(i11);
            ConstraintWidget viewWidget2 = getViewWidget(childAt4);
            if (viewWidget2 != null) {
                LayoutParams layoutParams = (LayoutParams) childAt4.getLayoutParams();
                layoutParams.validate();
                if (layoutParams.helped) {
                    layoutParams.helped = z;
                } else if (isInEditMode) {
                    try {
                        String resourceName2 = getResources().getResourceName(childAt4.getId());
                        setDesignInformation(z ? 1 : 0, resourceName2, Integer.valueOf(childAt4.getId()));
                        getTargetWidget(childAt4.getId()).setDebugName(resourceName2.substring(resourceName2.indexOf("id/") + 3));
                    } catch (Resources.NotFoundException unused2) {
                    }
                }
                viewWidget2.setVisibility(childAt4.getVisibility());
                if (layoutParams.isInPlaceholder) {
                    viewWidget2.setVisibility(8);
                }
                viewWidget2.setCompanionWidget(childAt4);
                this.mLayoutWidget.add(viewWidget2);
                if (!layoutParams.verticalDimensionFixed || !layoutParams.horizontalDimensionFixed) {
                    this.mVariableDimensionsWidgets.add(viewWidget2);
                }
                if (layoutParams.isGuideline) {
                    Guideline guideline = (Guideline) viewWidget2;
                    int i12 = layoutParams.resolvedGuideBegin;
                    int i13 = layoutParams.resolvedGuideEnd;
                    float f2 = layoutParams.resolvedGuidePercent;
                    if (Build.VERSION.SDK_INT < 17) {
                        i12 = layoutParams.guideBegin;
                        i13 = layoutParams.guideEnd;
                        f2 = layoutParams.guidePercent;
                    }
                    if (f2 != -1.0f) {
                        guideline.setGuidePercent(f2);
                    } else if (i12 != -1) {
                        guideline.setGuideBegin(i12);
                    } else if (i13 != -1) {
                        guideline.setGuideEnd(i13);
                    }
                } else if (layoutParams.leftToLeft != -1 || layoutParams.leftToRight != -1 || layoutParams.rightToLeft != -1 || layoutParams.rightToRight != -1 || layoutParams.startToStart != -1 || layoutParams.startToEnd != -1 || layoutParams.endToStart != -1 || layoutParams.endToEnd != -1 || layoutParams.topToTop != -1 || layoutParams.topToBottom != -1 || layoutParams.bottomToTop != -1 || layoutParams.bottomToBottom != -1 || layoutParams.baselineToBaseline != -1 || layoutParams.editorAbsoluteX != -1 || layoutParams.editorAbsoluteY != -1 || layoutParams.circleConstraint != -1 || layoutParams.width == -1 || layoutParams.height == -1) {
                    int i14 = layoutParams.resolvedLeftToLeft;
                    int i15 = layoutParams.resolvedLeftToRight;
                    int i16 = layoutParams.resolvedRightToLeft;
                    int i17 = layoutParams.resolvedRightToRight;
                    int i18 = layoutParams.resolveGoneLeftMargin;
                    int i19 = layoutParams.resolveGoneRightMargin;
                    float f3 = layoutParams.resolvedHorizontalBias;
                    if (Build.VERSION.SDK_INT < 17) {
                        int i20 = layoutParams.leftToLeft;
                        int i21 = layoutParams.leftToRight;
                        i16 = layoutParams.rightToLeft;
                        i17 = layoutParams.rightToRight;
                        int i22 = layoutParams.goneLeftMargin;
                        int i23 = layoutParams.goneRightMargin;
                        f3 = layoutParams.horizontalBias;
                        if (i20 == -1 && i21 == -1) {
                            if (layoutParams.startToStart != -1) {
                                i20 = layoutParams.startToStart;
                            } else if (layoutParams.startToEnd != -1) {
                                i21 = layoutParams.startToEnd;
                            }
                        }
                        int i24 = i21;
                        i14 = i20;
                        i3 = i24;
                        if (i16 == -1 && i17 == -1) {
                            if (layoutParams.endToStart != -1) {
                                i16 = layoutParams.endToStart;
                            } else if (layoutParams.endToEnd != -1) {
                                i17 = layoutParams.endToEnd;
                            }
                        }
                        i2 = i22;
                        i = i23;
                    } else {
                        i3 = i15;
                        i = i19;
                        i2 = i18;
                    }
                    int i25 = i17;
                    float f4 = f3;
                    int i26 = i16;
                    if (layoutParams.circleConstraint != -1) {
                        ConstraintWidget targetWidget5 = getTargetWidget(layoutParams.circleConstraint);
                        if (targetWidget5 != null) {
                            viewWidget2.connectCircularConstraint(targetWidget5, layoutParams.circleAngle, layoutParams.circleRadius);
                        }
                    } else {
                        if (i14 != -1) {
                            ConstraintWidget targetWidget6 = getTargetWidget(i14);
                            if (targetWidget6 != null) {
                                ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                                f = f4;
                                ConstraintAnchor.Type type2 = ConstraintAnchor.Type.LEFT;
                                i5 = i25;
                                viewWidget2.immediateConnect(type, targetWidget6, type2, layoutParams.leftMargin, i2);
                            } else {
                                f = f4;
                                i5 = i25;
                            }
                            i4 = i5;
                        } else {
                            f = f4;
                            i4 = i25;
                            if (!(i3 == -1 || (targetWidget4 = getTargetWidget(i3)) == null)) {
                                viewWidget2.immediateConnect(ConstraintAnchor.Type.LEFT, targetWidget4, ConstraintAnchor.Type.RIGHT, layoutParams.leftMargin, i2);
                            }
                        }
                        if (i26 != -1) {
                            ConstraintWidget targetWidget7 = getTargetWidget(i26);
                            if (targetWidget7 != null) {
                                viewWidget2.immediateConnect(ConstraintAnchor.Type.RIGHT, targetWidget7, ConstraintAnchor.Type.LEFT, layoutParams.rightMargin, i);
                            }
                        } else if (!(i4 == -1 || (targetWidget3 = getTargetWidget(i4)) == null)) {
                            viewWidget2.immediateConnect(ConstraintAnchor.Type.RIGHT, targetWidget3, ConstraintAnchor.Type.RIGHT, layoutParams.rightMargin, i);
                        }
                        if (layoutParams.topToTop != -1) {
                            ConstraintWidget targetWidget8 = getTargetWidget(layoutParams.topToTop);
                            if (targetWidget8 != null) {
                                viewWidget2.immediateConnect(ConstraintAnchor.Type.TOP, targetWidget8, ConstraintAnchor.Type.TOP, layoutParams.topMargin, layoutParams.goneTopMargin);
                            }
                        } else if (!(layoutParams.topToBottom == -1 || (targetWidget2 = getTargetWidget(layoutParams.topToBottom)) == null)) {
                            viewWidget2.immediateConnect(ConstraintAnchor.Type.TOP, targetWidget2, ConstraintAnchor.Type.BOTTOM, layoutParams.topMargin, layoutParams.goneTopMargin);
                        }
                        if (layoutParams.bottomToTop != -1) {
                            ConstraintWidget targetWidget9 = getTargetWidget(layoutParams.bottomToTop);
                            if (targetWidget9 != null) {
                                viewWidget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, targetWidget9, ConstraintAnchor.Type.TOP, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                            }
                        } else if (!(layoutParams.bottomToBottom == -1 || (targetWidget = getTargetWidget(layoutParams.bottomToBottom)) == null)) {
                            viewWidget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, targetWidget, ConstraintAnchor.Type.BOTTOM, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                        }
                        if (layoutParams.baselineToBaseline != -1) {
                            View view = this.mChildrenByIds.get(layoutParams.baselineToBaseline);
                            ConstraintWidget targetWidget10 = getTargetWidget(layoutParams.baselineToBaseline);
                            if (!(targetWidget10 == null || view == null || !(view.getLayoutParams() instanceof LayoutParams))) {
                                layoutParams.needsBaseline = USE_CONSTRAINTS_HELPER;
                                ((LayoutParams) view.getLayoutParams()).needsBaseline = USE_CONSTRAINTS_HELPER;
                                viewWidget2.getAnchor(ConstraintAnchor.Type.BASELINE).connect(targetWidget10.getAnchor(ConstraintAnchor.Type.BASELINE), 0, -1, ConstraintAnchor.Strength.STRONG, 0, USE_CONSTRAINTS_HELPER);
                                viewWidget2.getAnchor(ConstraintAnchor.Type.TOP).reset();
                                viewWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
                            }
                        }
                        float f5 = f;
                        if (f5 >= 0.0f && f5 != 0.5f) {
                            viewWidget2.setHorizontalBiasPercent(f5);
                        }
                        if (layoutParams.verticalBias >= 0.0f && layoutParams.verticalBias != 0.5f) {
                            viewWidget2.setVerticalBiasPercent(layoutParams.verticalBias);
                        }
                    }
                    if (isInEditMode && !(layoutParams.editorAbsoluteX == -1 && layoutParams.editorAbsoluteY == -1)) {
                        viewWidget2.setOrigin(layoutParams.editorAbsoluteX, layoutParams.editorAbsoluteY);
                    }
                    if (layoutParams.horizontalDimensionFixed) {
                        viewWidget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                        viewWidget2.setWidth(layoutParams.width);
                    } else if (layoutParams.width == -1) {
                        viewWidget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        viewWidget2.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = layoutParams.leftMargin;
                        viewWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = layoutParams.rightMargin;
                    } else {
                        viewWidget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        viewWidget2.setWidth(0);
                    }
                    if (layoutParams.verticalDimensionFixed) {
                        z = false;
                        viewWidget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                        viewWidget2.setHeight(layoutParams.height);
                    } else if (layoutParams.height == -1) {
                        viewWidget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        viewWidget2.getAnchor(ConstraintAnchor.Type.TOP).mMargin = layoutParams.topMargin;
                        viewWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = layoutParams.bottomMargin;
                        z = false;
                    } else {
                        viewWidget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        z = false;
                        viewWidget2.setHeight(0);
                    }
                    if (layoutParams.dimensionRatio != null) {
                        viewWidget2.setDimensionRatio(layoutParams.dimensionRatio);
                    }
                    viewWidget2.setHorizontalWeight(layoutParams.horizontalWeight);
                    viewWidget2.setVerticalWeight(layoutParams.verticalWeight);
                    viewWidget2.setHorizontalChainStyle(layoutParams.horizontalChainStyle);
                    viewWidget2.setVerticalChainStyle(layoutParams.verticalChainStyle);
                    viewWidget2.setHorizontalMatchStyle(layoutParams.matchConstraintDefaultWidth, layoutParams.matchConstraintMinWidth, layoutParams.matchConstraintMaxWidth, layoutParams.matchConstraintPercentWidth);
                    viewWidget2.setVerticalMatchStyle(layoutParams.matchConstraintDefaultHeight, layoutParams.matchConstraintMinHeight, layoutParams.matchConstraintMaxHeight, layoutParams.matchConstraintPercentHeight);
                }
            }
        }
    }

    private final ConstraintWidget getTargetWidget(int i) {
        if (i == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(i);
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    private void internalMeasureChildren(int i, int i2) {
        boolean z;
        boolean z2;
        int baseline;
        int i3;
        int i4;
        int i5 = i;
        int i6 = i2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.widget;
                if (!layoutParams.isGuideline && !layoutParams.isHelper) {
                    constraintWidget.setVisibility(childAt.getVisibility());
                    int i8 = layoutParams.width;
                    int i9 = layoutParams.height;
                    if ((layoutParams.horizontalDimensionFixed || layoutParams.verticalDimensionFixed || (!layoutParams.horizontalDimensionFixed && layoutParams.matchConstraintDefaultWidth == 1) || layoutParams.width == -1 || (!layoutParams.verticalDimensionFixed && (layoutParams.matchConstraintDefaultHeight == 1 || layoutParams.height == -1))) ? USE_CONSTRAINTS_HELPER : false) {
                        if (i8 == 0) {
                            i3 = getChildMeasureSpec(i5, paddingLeft, -2);
                            z2 = USE_CONSTRAINTS_HELPER;
                        } else if (i8 == -1) {
                            i3 = getChildMeasureSpec(i5, paddingLeft, -1);
                            z2 = false;
                        } else {
                            z2 = i8 == -2 ? USE_CONSTRAINTS_HELPER : false;
                            i3 = getChildMeasureSpec(i5, paddingLeft, i8);
                        }
                        if (i9 == 0) {
                            i4 = getChildMeasureSpec(i6, paddingTop, -2);
                            z = USE_CONSTRAINTS_HELPER;
                        } else if (i9 == -1) {
                            i4 = getChildMeasureSpec(i6, paddingTop, -1);
                            z = false;
                        } else {
                            z = i9 == -2 ? USE_CONSTRAINTS_HELPER : false;
                            i4 = getChildMeasureSpec(i6, paddingTop, i9);
                        }
                        childAt.measure(i3, i4);
                        if (this.mMetrics != null) {
                            this.mMetrics.measures++;
                        }
                        constraintWidget.setWidthWrapContent(i8 == -2 ? USE_CONSTRAINTS_HELPER : false);
                        constraintWidget.setHeightWrapContent(i9 == -2 ? USE_CONSTRAINTS_HELPER : false);
                        i8 = childAt.getMeasuredWidth();
                        i9 = childAt.getMeasuredHeight();
                    } else {
                        z2 = false;
                        z = false;
                    }
                    constraintWidget.setWidth(i8);
                    constraintWidget.setHeight(i9);
                    if (z2) {
                        constraintWidget.setWrapWidth(i8);
                    }
                    if (z) {
                        constraintWidget.setWrapHeight(i9);
                    }
                    if (layoutParams.needsBaseline && (baseline = childAt.getBaseline()) != -1) {
                        constraintWidget.setBaselineDistance(baseline);
                    }
                }
            }
        }
    }

    private void updatePostMeasures() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).updatePostMeasure(this);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.mConstraintHelpers.get(i2).updatePostMeasure(this);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0277  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02d6  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void internalMeasureDimensions(int r25, int r26) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            int r3 = r24.getPaddingTop()
            int r4 = r24.getPaddingBottom()
            int r3 = r3 + r4
            int r4 = r24.getPaddingLeft()
            int r5 = r24.getPaddingRight()
            int r4 = r4 + r5
            int r5 = r24.getChildCount()
            r7 = 0
        L_0x001d:
            r8 = 1
            r10 = 8
            r12 = -2
            if (r7 >= r5) goto L_0x00e3
            android.view.View r14 = r0.getChildAt(r7)
            int r15 = r14.getVisibility()
            if (r15 != r10) goto L_0x0032
        L_0x002e:
            r18 = r3
            goto L_0x00db
        L_0x0032:
            android.view.ViewGroup$LayoutParams r10 = r14.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r10 = (android.support.constraint.ConstraintLayout.LayoutParams) r10
            android.support.constraint.solver.widgets.ConstraintWidget r15 = r10.widget
            boolean r6 = r10.isGuideline
            if (r6 != 0) goto L_0x002e
            boolean r6 = r10.isHelper
            if (r6 == 0) goto L_0x0043
            goto L_0x002e
        L_0x0043:
            int r6 = r14.getVisibility()
            r15.setVisibility(r6)
            int r6 = r10.width
            int r13 = r10.height
            if (r6 == 0) goto L_0x00cb
            if (r13 != 0) goto L_0x0054
            goto L_0x00cb
        L_0x0054:
            if (r6 != r12) goto L_0x0059
            r16 = 1
            goto L_0x005b
        L_0x0059:
            r16 = 0
        L_0x005b:
            int r11 = getChildMeasureSpec(r1, r4, r6)
            if (r13 != r12) goto L_0x0064
            r17 = 1
            goto L_0x0066
        L_0x0064:
            r17 = 0
        L_0x0066:
            int r12 = getChildMeasureSpec(r2, r3, r13)
            r14.measure(r11, r12)
            android.support.constraint.solver.Metrics r11 = r0.mMetrics
            if (r11 == 0) goto L_0x007b
            android.support.constraint.solver.Metrics r11 = r0.mMetrics
            r18 = r3
            long r2 = r11.measures
            long r2 = r2 + r8
            r11.measures = r2
            goto L_0x007d
        L_0x007b:
            r18 = r3
        L_0x007d:
            r2 = -2
            if (r6 != r2) goto L_0x0082
            r3 = 1
            goto L_0x0083
        L_0x0082:
            r3 = 0
        L_0x0083:
            r15.setWidthWrapContent(r3)
            if (r13 != r2) goto L_0x008a
            r2 = 1
            goto L_0x008b
        L_0x008a:
            r2 = 0
        L_0x008b:
            r15.setHeightWrapContent(r2)
            int r2 = r14.getMeasuredWidth()
            int r3 = r14.getMeasuredHeight()
            r15.setWidth(r2)
            r15.setHeight(r3)
            if (r16 == 0) goto L_0x00a1
            r15.setWrapWidth(r2)
        L_0x00a1:
            if (r17 == 0) goto L_0x00a6
            r15.setWrapHeight(r3)
        L_0x00a6:
            boolean r6 = r10.needsBaseline
            if (r6 == 0) goto L_0x00b4
            int r6 = r14.getBaseline()
            r8 = -1
            if (r6 == r8) goto L_0x00b4
            r15.setBaselineDistance(r6)
        L_0x00b4:
            boolean r6 = r10.horizontalDimensionFixed
            if (r6 == 0) goto L_0x00db
            boolean r6 = r10.verticalDimensionFixed
            if (r6 == 0) goto L_0x00db
            android.support.constraint.solver.widgets.ResolutionDimension r6 = r15.getResolutionWidth()
            r6.resolve(r2)
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r15.getResolutionHeight()
            r2.resolve(r3)
            goto L_0x00db
        L_0x00cb:
            r18 = r3
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r15.getResolutionWidth()
            r2.invalidate()
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r15.getResolutionHeight()
            r2.invalidate()
        L_0x00db:
            int r7 = r7 + 1
            r3 = r18
            r2 = r26
            goto L_0x001d
        L_0x00e3:
            r18 = r3
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r2 = r0.mLayoutWidget
            r2.solveGraph()
            r2 = 0
        L_0x00eb:
            if (r2 >= r5) goto L_0x02ee
            android.view.View r3 = r0.getChildAt(r2)
            int r6 = r3.getVisibility()
            if (r6 != r10) goto L_0x0105
        L_0x00f7:
            r22 = r2
            r21 = r5
            r19 = r8
            r9 = r18
            r3 = -1
            r8 = r26
            r13 = -2
            goto L_0x02e2
        L_0x0105:
            android.view.ViewGroup$LayoutParams r6 = r3.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r6 = (android.support.constraint.ConstraintLayout.LayoutParams) r6
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r6.widget
            boolean r11 = r6.isGuideline
            if (r11 != 0) goto L_0x00f7
            boolean r11 = r6.isHelper
            if (r11 == 0) goto L_0x0116
            goto L_0x00f7
        L_0x0116:
            int r11 = r3.getVisibility()
            r7.setVisibility(r11)
            int r11 = r6.width
            int r12 = r6.height
            if (r11 == 0) goto L_0x0126
            if (r12 == 0) goto L_0x0126
            goto L_0x00f7
        L_0x0126:
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r13 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r13 = r7.getAnchor(r13)
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r13.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r7.getAnchor(r14)
            android.support.constraint.solver.widgets.ResolutionAnchor r14 = r14.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r15 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r7.getAnchor(r15)
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r15.getTarget()
            if (r15 == 0) goto L_0x0154
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r15 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r7.getAnchor(r15)
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r15.getTarget()
            if (r15 == 0) goto L_0x0154
            r15 = 1
            goto L_0x0155
        L_0x0154:
            r15 = 0
        L_0x0155:
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r7.getAnchor(r10)
            android.support.constraint.solver.widgets.ResolutionAnchor r10 = r10.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r7.getAnchor(r8)
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r9 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r7.getAnchor(r9)
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.getTarget()
            if (r9 == 0) goto L_0x0183
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r9 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r7.getAnchor(r9)
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.getTarget()
            if (r9 == 0) goto L_0x0183
            r9 = 1
            goto L_0x0184
        L_0x0183:
            r9 = 0
        L_0x0184:
            if (r11 != 0) goto L_0x019a
            if (r12 != 0) goto L_0x019a
            if (r15 == 0) goto L_0x019a
            if (r9 == 0) goto L_0x019a
            r22 = r2
            r21 = r5
            r9 = r18
            r3 = -1
            r8 = r26
            r13 = -2
            r19 = 1
            goto L_0x02e2
        L_0x019a:
            r21 = r5
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r5 = r0.mLayoutWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = r5.getHorizontalDimensionBehaviour()
            r22 = r2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 == r2) goto L_0x01aa
            r2 = 1
            goto L_0x01ab
        L_0x01aa:
            r2 = 0
        L_0x01ab:
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r5 = r0.mLayoutWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = r5.getVerticalDimensionBehaviour()
            r23 = r6
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 == r6) goto L_0x01b9
            r6 = 1
            goto L_0x01ba
        L_0x01b9:
            r6 = 0
        L_0x01ba:
            if (r2 != 0) goto L_0x01c3
            android.support.constraint.solver.widgets.ResolutionDimension r5 = r7.getResolutionWidth()
            r5.invalidate()
        L_0x01c3:
            if (r6 != 0) goto L_0x01cc
            android.support.constraint.solver.widgets.ResolutionDimension r5 = r7.getResolutionHeight()
            r5.invalidate()
        L_0x01cc:
            if (r11 != 0) goto L_0x0204
            if (r2 == 0) goto L_0x01fb
            boolean r5 = r7.isSpreadWidth()
            if (r5 == 0) goto L_0x01fb
            if (r15 == 0) goto L_0x01fb
            boolean r5 = r13.isResolved()
            if (r5 == 0) goto L_0x01fb
            boolean r5 = r14.isResolved()
            if (r5 == 0) goto L_0x01fb
            float r5 = r14.getResolvedValue()
            float r11 = r13.getResolvedValue()
            float r5 = r5 - r11
            int r11 = (int) r5
            android.support.constraint.solver.widgets.ResolutionDimension r5 = r7.getResolutionWidth()
            r5.resolve(r11)
            int r5 = getChildMeasureSpec(r1, r4, r11)
            r13 = r5
            goto L_0x020d
        L_0x01fb:
            r5 = -2
            int r2 = getChildMeasureSpec(r1, r4, r5)
            r13 = r2
            r2 = 0
            r5 = 1
            goto L_0x0218
        L_0x0204:
            r5 = -2
            r13 = -1
            if (r11 != r13) goto L_0x020f
            int r14 = getChildMeasureSpec(r1, r4, r13)
            r13 = r14
        L_0x020d:
            r5 = 0
            goto L_0x0218
        L_0x020f:
            if (r11 != r5) goto L_0x0213
            r5 = 1
            goto L_0x0214
        L_0x0213:
            r5 = 0
        L_0x0214:
            int r13 = getChildMeasureSpec(r1, r4, r11)
        L_0x0218:
            if (r12 != 0) goto L_0x0258
            if (r6 == 0) goto L_0x024b
            boolean r14 = r7.isSpreadHeight()
            if (r14 == 0) goto L_0x024b
            if (r9 == 0) goto L_0x024b
            boolean r9 = r10.isResolved()
            if (r9 == 0) goto L_0x024b
            boolean r9 = r8.isResolved()
            if (r9 == 0) goto L_0x024b
            float r8 = r8.getResolvedValue()
            float r9 = r10.getResolvedValue()
            float r8 = r8 - r9
            int r12 = (int) r8
            android.support.constraint.solver.widgets.ResolutionDimension r8 = r7.getResolutionHeight()
            r8.resolve(r12)
            r9 = r18
            r8 = r26
            int r10 = getChildMeasureSpec(r8, r9, r12)
            r14 = r10
            goto L_0x0265
        L_0x024b:
            r9 = r18
            r8 = r26
            r10 = -2
            int r6 = getChildMeasureSpec(r8, r9, r10)
            r14 = r6
            r6 = 0
            r10 = 1
            goto L_0x0270
        L_0x0258:
            r9 = r18
            r8 = r26
            r10 = -2
            r14 = -1
            if (r12 != r14) goto L_0x0267
            int r15 = getChildMeasureSpec(r8, r9, r14)
            r14 = r15
        L_0x0265:
            r10 = 0
            goto L_0x0270
        L_0x0267:
            if (r12 != r10) goto L_0x026b
            r10 = 1
            goto L_0x026c
        L_0x026b:
            r10 = 0
        L_0x026c:
            int r14 = getChildMeasureSpec(r8, r9, r12)
        L_0x0270:
            r3.measure(r13, r14)
            android.support.constraint.solver.Metrics r13 = r0.mMetrics
            if (r13 == 0) goto L_0x0282
            android.support.constraint.solver.Metrics r13 = r0.mMetrics
            long r14 = r13.measures
            r19 = 1
            long r14 = r14 + r19
            r13.measures = r14
            goto L_0x0284
        L_0x0282:
            r19 = 1
        L_0x0284:
            r13 = -2
            if (r11 != r13) goto L_0x0289
            r11 = 1
            goto L_0x028a
        L_0x0289:
            r11 = 0
        L_0x028a:
            r7.setWidthWrapContent(r11)
            if (r12 != r13) goto L_0x0291
            r11 = 1
            goto L_0x0292
        L_0x0291:
            r11 = 0
        L_0x0292:
            r7.setHeightWrapContent(r11)
            int r11 = r3.getMeasuredWidth()
            int r12 = r3.getMeasuredHeight()
            r7.setWidth(r11)
            r7.setHeight(r12)
            if (r5 == 0) goto L_0x02a8
            r7.setWrapWidth(r11)
        L_0x02a8:
            if (r10 == 0) goto L_0x02ad
            r7.setWrapHeight(r12)
        L_0x02ad:
            if (r2 == 0) goto L_0x02b7
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r7.getResolutionWidth()
            r2.resolve(r11)
            goto L_0x02be
        L_0x02b7:
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r7.getResolutionWidth()
            r2.remove()
        L_0x02be:
            if (r6 == 0) goto L_0x02ca
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r7.getResolutionHeight()
            r2.resolve(r12)
        L_0x02c7:
            r6 = r23
            goto L_0x02d2
        L_0x02ca:
            android.support.constraint.solver.widgets.ResolutionDimension r2 = r7.getResolutionHeight()
            r2.remove()
            goto L_0x02c7
        L_0x02d2:
            boolean r2 = r6.needsBaseline
            if (r2 == 0) goto L_0x02e1
            int r2 = r3.getBaseline()
            r3 = -1
            if (r2 == r3) goto L_0x02e2
            r7.setBaselineDistance(r2)
            goto L_0x02e2
        L_0x02e1:
            r3 = -1
        L_0x02e2:
            int r2 = r22 + 1
            r18 = r9
            r8 = r19
            r5 = r21
            r10 = 8
            goto L_0x00eb
        L_0x02ee:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.internalMeasureDimensions(int, int):void");
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        boolean z;
        boolean z2;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = i;
        int i11 = i2;
        System.currentTimeMillis();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.mLastMeasureWidth != -1) {
            int i12 = this.mLastMeasureHeight;
        }
        if (mode == 1073741824 && mode2 == 1073741824 && size == this.mLastMeasureWidth) {
            int i13 = this.mLastMeasureHeight;
        }
        boolean z3 = (mode == this.mLastMeasureWidthMode && mode2 == this.mLastMeasureHeightMode) ? USE_CONSTRAINTS_HELPER : false;
        if (z3 && size == this.mLastMeasureWidthSize) {
            int i14 = this.mLastMeasureHeightSize;
        }
        if (z3 && mode == Integer.MIN_VALUE && mode2 == 1073741824 && size >= this.mLastMeasureWidth) {
            int i15 = this.mLastMeasureHeight;
        }
        if (z3 && mode == 1073741824 && mode2 == Integer.MIN_VALUE && size == this.mLastMeasureWidth) {
            int i16 = this.mLastMeasureHeight;
        }
        this.mLastMeasureWidthMode = mode;
        this.mLastMeasureHeightMode = mode2;
        this.mLastMeasureWidthSize = size;
        this.mLastMeasureHeightSize = size2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1 ? USE_CONSTRAINTS_HELPER : false);
        }
        setSelfDimensionBehaviour(i, i2);
        int width = this.mLayoutWidget.getWidth();
        int height = this.mLayoutWidget.getHeight();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
        }
        boolean z4 = (this.mOptimizationLevel & 8) == 8 ? USE_CONSTRAINTS_HELPER : false;
        if (z4) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(width, height);
            internalMeasureDimensions(i, i2);
        } else {
            internalMeasureChildren(i, i2);
        }
        updatePostMeasures();
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int size3 = this.mVariableDimensionsWidgets.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size3 > 0) {
            boolean z5 = this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            boolean z6 = this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            int max = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            int max2 = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
            int i17 = max;
            int i18 = 0;
            boolean z7 = false;
            int i19 = 0;
            while (i18 < size3) {
                ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(i18);
                View view = (View) constraintWidget.getCompanionWidget();
                if (view == null) {
                    i4 = width;
                    i5 = height;
                    i6 = size3;
                } else {
                    i6 = size3;
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    i5 = height;
                    if (layoutParams.isHelper || layoutParams.isGuideline) {
                        i4 = width;
                    } else {
                        i4 = width;
                        if (view.getVisibility() != 8 && (!z4 || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved())) {
                            if (layoutParams.width != -2 || !layoutParams.horizontalDimensionFixed) {
                                i7 = View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
                            } else {
                                i7 = getChildMeasureSpec(i10, paddingRight, layoutParams.width);
                            }
                            if (layoutParams.height != -2 || !layoutParams.verticalDimensionFixed) {
                                i8 = View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
                            } else {
                                i8 = getChildMeasureSpec(i11, paddingBottom, layoutParams.height);
                            }
                            view.measure(i7, i8);
                            if (this.mMetrics != null) {
                                this.mMetrics.additionalMeasures++;
                            }
                            int measuredWidth = view.getMeasuredWidth();
                            int measuredHeight = view.getMeasuredHeight();
                            if (measuredWidth != constraintWidget.getWidth()) {
                                constraintWidget.setWidth(measuredWidth);
                                if (z4) {
                                    constraintWidget.getResolutionWidth().resolve(measuredWidth);
                                }
                                if (z5 && constraintWidget.getRight() > i17) {
                                    i17 = Math.max(i17, constraintWidget.getRight() + constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                z7 = USE_CONSTRAINTS_HELPER;
                            }
                            if (measuredHeight != constraintWidget.getHeight()) {
                                constraintWidget.setHeight(measuredHeight);
                                if (z4) {
                                    constraintWidget.getResolutionHeight().resolve(measuredHeight);
                                }
                                if (z6) {
                                    i9 = max2;
                                    if (constraintWidget.getBottom() > i9) {
                                        max2 = Math.max(i9, constraintWidget.getBottom() + constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                        z7 = USE_CONSTRAINTS_HELPER;
                                    }
                                } else {
                                    i9 = max2;
                                }
                                max2 = i9;
                                z7 = USE_CONSTRAINTS_HELPER;
                            } else {
                                int i20 = max2;
                            }
                            if (layoutParams.needsBaseline) {
                                int baseline = view.getBaseline();
                                if (!(baseline == -1 || baseline == constraintWidget.getBaselineDistance())) {
                                    constraintWidget.setBaselineDistance(baseline);
                                    z7 = USE_CONSTRAINTS_HELPER;
                                }
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                i19 = combineMeasuredStates(i19, view.getMeasuredState());
                            } else {
                                int i21 = i19;
                            }
                            i18++;
                            size3 = i6;
                            height = i5;
                            width = i4;
                            i10 = i;
                        }
                    }
                }
                max2 = max2;
                i19 = i19;
                i18++;
                size3 = i6;
                height = i5;
                width = i4;
                i10 = i;
            }
            int i22 = width;
            int i23 = height;
            int i24 = size3;
            int i25 = max2;
            i3 = i19;
            if (z7) {
                this.mLayoutWidget.setWidth(i22);
                this.mLayoutWidget.setHeight(i23);
                if (z4) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                if (this.mLayoutWidget.getWidth() < i17) {
                    this.mLayoutWidget.setWidth(i17);
                    z = USE_CONSTRAINTS_HELPER;
                } else {
                    z = false;
                }
                if (this.mLayoutWidget.getHeight() < i25) {
                    this.mLayoutWidget.setHeight(i25);
                    z2 = USE_CONSTRAINTS_HELPER;
                } else {
                    z2 = z;
                }
                if (z2) {
                    solveLinearSystem("3rd pass");
                }
            }
            int i26 = i24;
            for (int i27 = 0; i27 < i26; i27++) {
                ConstraintWidget constraintWidget2 = this.mVariableDimensionsWidgets.get(i27);
                View view2 = (View) constraintWidget2.getCompanionWidget();
                if (view2 != null && (view2.getMeasuredWidth() != constraintWidget2.getWidth() || view2.getMeasuredHeight() != constraintWidget2.getHeight())) {
                    if (constraintWidget2.getVisibility() != 8) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), 1073741824));
                        if (this.mMetrics != null) {
                            this.mMetrics.additionalMeasures++;
                        }
                    }
                }
            }
        } else {
            i3 = 0;
        }
        int width2 = this.mLayoutWidget.getWidth() + paddingRight;
        int height2 = this.mLayoutWidget.getHeight() + paddingBottom;
        if (Build.VERSION.SDK_INT >= 11) {
            int resolveSizeAndState = resolveSizeAndState(width2, i, i3);
            int resolveSizeAndState2 = resolveSizeAndState(height2, i11, i3 << 16);
            int i28 = resolveSizeAndState & ViewCompat.MEASURED_SIZE_MASK;
            int i29 = resolveSizeAndState2 & ViewCompat.MEASURED_SIZE_MASK;
            int min = Math.min(this.mMaxWidth, i28);
            int min2 = Math.min(this.mMaxHeight, i29);
            if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                min |= 16777216;
            }
            if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                min2 |= 16777216;
            }
            setMeasuredDimension(min, min2);
            this.mLastMeasureWidth = min;
            this.mLastMeasureHeight = min2;
            return;
        }
        setMeasuredDimension(width2, height2);
        this.mLastMeasureWidth = width2;
        this.mLastMeasureHeight = height2;
    }

    private void setSelfDimensionBehaviour(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode == 1073741824) {
                size = Math.min(this.mMaxWidth, size) - paddingLeft;
            }
            size = 0;
        } else {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode2 == 1073741824) {
                size2 = Math.min(this.mMaxHeight, size2) - paddingTop;
            }
            size2 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.mLayoutWidget.setWidth(size);
        this.mLayoutWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
        this.mLayoutWidget.setHeight(size2);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void solveLinearSystem(String str) {
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            this.mMetrics.resolutions++;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if ((childAt.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || isInEditMode) && !layoutParams.isInPlaceholder) {
                int drawX = constraintWidget.getDrawX();
                int drawY = constraintWidget.getDrawY();
                int width = constraintWidget.getWidth() + drawX;
                int height = constraintWidget.getHeight() + drawY;
                childAt.layout(drawX, drawY, width, height);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(drawX, drawY, width, height);
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                this.mConstraintHelpers.get(i6).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int i) {
        this.mLayoutWidget.setOptimizationLevel(i);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public View getViewById(int i) {
        return this.mChildrenByIds.get(i);
    }

    public void dispatchDraw(Canvas canvas) {
        Object tag;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (!(childAt.getVisibility() == 8 || (tag = childAt.getTag()) == null || !(tag instanceof String))) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i2 = (int) ((((float) parseInt) / 1080.0f) * width);
                        int i3 = (int) ((((float) parseInt2) / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(SupportMenu.CATEGORY_MASK);
                        float f = (float) i2;
                        float f2 = (float) (i2 + ((int) ((((float) parseInt3) / 1080.0f) * width)));
                        Canvas canvas2 = canvas;
                        float f3 = (float) i3;
                        float f4 = f;
                        float f5 = f;
                        float f6 = f3;
                        Paint paint2 = paint;
                        float f7 = f2;
                        Paint paint3 = paint2;
                        canvas2.drawLine(f4, f6, f7, f3, paint3);
                        float parseInt4 = (float) (i3 + ((int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height)));
                        float f8 = f2;
                        float f9 = parseInt4;
                        canvas2.drawLine(f8, f6, f7, f9, paint3);
                        float f10 = parseInt4;
                        float f11 = f5;
                        canvas2.drawLine(f8, f10, f11, f9, paint3);
                        float f12 = f5;
                        canvas2.drawLine(f12, f10, f11, f3, paint3);
                        Paint paint4 = paint2;
                        paint4.setColor(-16711936);
                        Paint paint5 = paint4;
                        float f13 = f2;
                        Paint paint6 = paint5;
                        canvas2.drawLine(f12, f3, f13, parseInt4, paint6);
                        canvas2.drawLine(f12, parseInt4, f13, f3, paint6);
                    }
                }
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        int dimensionRatioSide = 1;
        float dimensionRatioValue = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean helped = false;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        boolean horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
        public float horizontalWeight = -1.0f;
        boolean isGuideline = false;
        boolean isHelper = false;
        boolean isInPlaceholder = false;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        boolean needsBaseline = false;
        public int orientation = -1;
        int resolveGoneLeftMargin = -1;
        int resolveGoneRightMargin = -1;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias = 0.5f;
        int resolvedLeftToLeft = -1;
        int resolvedLeftToRight = -1;
        int resolvedRightToLeft = -1;
        int resolvedRightToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        boolean verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
        public float verticalWeight = -1.0f;
        ConstraintWidget widget = new ConstraintWidget();

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.dimensionRatioValue = layoutParams.dimensionRatioValue;
            this.dimensionRatioSide = layoutParams.dimensionRatioSide;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.horizontalDimensionFixed = layoutParams.horizontalDimensionFixed;
            this.verticalDimensionFixed = layoutParams.verticalDimensionFixed;
            this.needsBaseline = layoutParams.needsBaseline;
            this.isGuideline = layoutParams.isGuideline;
            this.resolvedLeftToLeft = layoutParams.resolvedLeftToLeft;
            this.resolvedLeftToRight = layoutParams.resolvedLeftToRight;
            this.resolvedRightToLeft = layoutParams.resolvedRightToLeft;
            this.resolvedRightToRight = layoutParams.resolvedRightToRight;
            this.resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
            this.resolvedHorizontalBias = layoutParams.resolvedHorizontalBias;
            this.widget = layoutParams.widget;
        }

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            int i;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (Table.map.get(index)) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 2:
                        this.circleConstraint = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        if (this.circleConstraint != -1) {
                            break;
                        } else {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        break;
                    case 4:
                        this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        if (this.circleAngle >= 0.0f) {
                            break;
                        } else {
                            this.circleAngle = (360.0f - this.circleAngle) % 360.0f;
                            break;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 8:
                        this.leftToLeft = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        if (this.leftToLeft != -1) {
                            break;
                        } else {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 9:
                        this.leftToRight = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        if (this.leftToRight != -1) {
                            break;
                        } else {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 10:
                        this.rightToLeft = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        if (this.rightToLeft != -1) {
                            break;
                        } else {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 11:
                        this.rightToRight = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        if (this.rightToRight != -1) {
                            break;
                        } else {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 12:
                        this.topToTop = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        if (this.topToTop != -1) {
                            break;
                        } else {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 13:
                        this.topToBottom = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        if (this.topToBottom != -1) {
                            break;
                        } else {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 14:
                        this.bottomToTop = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        if (this.bottomToTop != -1) {
                            break;
                        } else {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 15:
                        this.bottomToBottom = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        if (this.bottomToBottom != -1) {
                            break;
                        } else {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 16:
                        this.baselineToBaseline = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        if (this.baselineToBaseline != -1) {
                            break;
                        } else {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 17:
                        this.startToEnd = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        if (this.startToEnd != -1) {
                            break;
                        } else {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 18:
                        this.startToStart = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        if (this.startToStart != -1) {
                            break;
                        } else {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 19:
                        this.endToStart = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        if (this.endToStart != -1) {
                            break;
                        } else {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 20:
                        this.endToEnd = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        if (this.endToEnd != -1) {
                            break;
                        } else {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 31:
                        this.matchConstraintDefaultWidth = obtainStyledAttributes.getInt(index, 0);
                        if (this.matchConstraintDefaultWidth != 1) {
                            break;
                        } else {
                            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        }
                    case 32:
                        this.matchConstraintDefaultHeight = obtainStyledAttributes.getInt(index, 0);
                        if (this.matchConstraintDefaultHeight != 1) {
                            break;
                        } else {
                            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        }
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinWidth = -2;
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxWidth = -2;
                                break;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinHeight = -2;
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxHeight = -2;
                                break;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        break;
                    case 44:
                        this.dimensionRatio = obtainStyledAttributes.getString(index);
                        this.dimensionRatioValue = Float.NaN;
                        this.dimensionRatioSide = -1;
                        if (this.dimensionRatio == null) {
                            break;
                        } else {
                            int length = this.dimensionRatio.length();
                            int indexOf = this.dimensionRatio.indexOf(44);
                            if (indexOf <= 0 || indexOf >= length - 1) {
                                i = 0;
                            } else {
                                String substring = this.dimensionRatio.substring(0, indexOf);
                                if (substring.equalsIgnoreCase("W")) {
                                    this.dimensionRatioSide = 0;
                                } else if (substring.equalsIgnoreCase("H")) {
                                    this.dimensionRatioSide = 1;
                                }
                                i = indexOf + 1;
                            }
                            int indexOf2 = this.dimensionRatio.indexOf(58);
                            if (indexOf2 >= 0 && indexOf2 < length - 1) {
                                String substring2 = this.dimensionRatio.substring(i, indexOf2);
                                String substring3 = this.dimensionRatio.substring(indexOf2 + 1);
                                if (substring2.length() > 0 && substring3.length() > 0) {
                                    try {
                                        float parseFloat = Float.parseFloat(substring2);
                                        float parseFloat2 = Float.parseFloat(substring3);
                                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                            if (this.dimensionRatioSide != 1) {
                                                this.dimensionRatioValue = Math.abs(parseFloat / parseFloat2);
                                                break;
                                            } else {
                                                this.dimensionRatioValue = Math.abs(parseFloat2 / parseFloat);
                                                break;
                                            }
                                        }
                                    } catch (NumberFormatException unused5) {
                                        break;
                                    }
                                }
                            } else {
                                String substring4 = this.dimensionRatio.substring(i);
                                if (substring4.length() <= 0) {
                                    break;
                                } else {
                                    this.dimensionRatioValue = Float.parseFloat(substring4);
                                    break;
                                }
                            }
                        }
                        break;
                    case 45:
                        this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                        break;
                    case 46:
                        this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                        break;
                    case 47:
                        this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 48:
                        this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 49:
                        this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                        break;
                    case 50:
                        this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0050  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0092  */
        @android.annotation.TargetApi(17)
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void resolveLayoutDirection(int r6) {
            /*
                r5 = this;
                int r0 = r5.leftMargin
                int r1 = r5.rightMargin
                super.resolveLayoutDirection(r6)
                r6 = -1
                r5.resolvedRightToLeft = r6
                r5.resolvedRightToRight = r6
                r5.resolvedLeftToLeft = r6
                r5.resolvedLeftToRight = r6
                r5.resolveGoneLeftMargin = r6
                r5.resolveGoneRightMargin = r6
                int r2 = r5.goneLeftMargin
                r5.resolveGoneLeftMargin = r2
                int r2 = r5.goneRightMargin
                r5.resolveGoneRightMargin = r2
                float r2 = r5.horizontalBias
                r5.resolvedHorizontalBias = r2
                int r2 = r5.guideBegin
                r5.resolvedGuideBegin = r2
                int r2 = r5.guideEnd
                r5.resolvedGuideEnd = r2
                float r2 = r5.guidePercent
                r5.resolvedGuidePercent = r2
                int r2 = r5.getLayoutDirection()
                r3 = 0
                r4 = 1
                if (r4 != r2) goto L_0x0036
                r2 = 1
                goto L_0x0037
            L_0x0036:
                r2 = 0
            L_0x0037:
                if (r2 == 0) goto L_0x00ac
                int r2 = r5.startToEnd
                if (r2 == r6) goto L_0x0043
                int r2 = r5.startToEnd
                r5.resolvedRightToLeft = r2
            L_0x0041:
                r3 = 1
                goto L_0x004c
            L_0x0043:
                int r2 = r5.startToStart
                if (r2 == r6) goto L_0x004c
                int r2 = r5.startToStart
                r5.resolvedRightToRight = r2
                goto L_0x0041
            L_0x004c:
                int r2 = r5.endToStart
                if (r2 == r6) goto L_0x0055
                int r2 = r5.endToStart
                r5.resolvedLeftToRight = r2
                r3 = 1
            L_0x0055:
                int r2 = r5.endToEnd
                if (r2 == r6) goto L_0x005e
                int r2 = r5.endToEnd
                r5.resolvedLeftToLeft = r2
                r3 = 1
            L_0x005e:
                int r2 = r5.goneStartMargin
                if (r2 == r6) goto L_0x0066
                int r2 = r5.goneStartMargin
                r5.resolveGoneRightMargin = r2
            L_0x0066:
                int r2 = r5.goneEndMargin
                if (r2 == r6) goto L_0x006e
                int r2 = r5.goneEndMargin
                r5.resolveGoneLeftMargin = r2
            L_0x006e:
                r2 = 1065353216(0x3f800000, float:1.0)
                if (r3 == 0) goto L_0x0078
                float r3 = r5.horizontalBias
                float r3 = r2 - r3
                r5.resolvedHorizontalBias = r3
            L_0x0078:
                boolean r3 = r5.isGuideline
                if (r3 == 0) goto L_0x00dc
                int r3 = r5.orientation
                if (r3 != r4) goto L_0x00dc
                float r3 = r5.guidePercent
                r4 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r3 == 0) goto L_0x0092
                float r3 = r5.guidePercent
                float r2 = r2 - r3
                r5.resolvedGuidePercent = r2
                r5.resolvedGuideBegin = r6
                r5.resolvedGuideEnd = r6
                goto L_0x00dc
            L_0x0092:
                int r2 = r5.guideBegin
                if (r2 == r6) goto L_0x009f
                int r2 = r5.guideBegin
                r5.resolvedGuideEnd = r2
                r5.resolvedGuideBegin = r6
                r5.resolvedGuidePercent = r4
                goto L_0x00dc
            L_0x009f:
                int r2 = r5.guideEnd
                if (r2 == r6) goto L_0x00dc
                int r2 = r5.guideEnd
                r5.resolvedGuideBegin = r2
                r5.resolvedGuideEnd = r6
                r5.resolvedGuidePercent = r4
                goto L_0x00dc
            L_0x00ac:
                int r2 = r5.startToEnd
                if (r2 == r6) goto L_0x00b4
                int r2 = r5.startToEnd
                r5.resolvedLeftToRight = r2
            L_0x00b4:
                int r2 = r5.startToStart
                if (r2 == r6) goto L_0x00bc
                int r2 = r5.startToStart
                r5.resolvedLeftToLeft = r2
            L_0x00bc:
                int r2 = r5.endToStart
                if (r2 == r6) goto L_0x00c4
                int r2 = r5.endToStart
                r5.resolvedRightToLeft = r2
            L_0x00c4:
                int r2 = r5.endToEnd
                if (r2 == r6) goto L_0x00cc
                int r2 = r5.endToEnd
                r5.resolvedRightToRight = r2
            L_0x00cc:
                int r2 = r5.goneStartMargin
                if (r2 == r6) goto L_0x00d4
                int r2 = r5.goneStartMargin
                r5.resolveGoneLeftMargin = r2
            L_0x00d4:
                int r2 = r5.goneEndMargin
                if (r2 == r6) goto L_0x00dc
                int r2 = r5.goneEndMargin
                r5.resolveGoneRightMargin = r2
            L_0x00dc:
                int r2 = r5.endToStart
                if (r2 != r6) goto L_0x012e
                int r2 = r5.endToEnd
                if (r2 != r6) goto L_0x012e
                int r2 = r5.startToStart
                if (r2 != r6) goto L_0x012e
                int r2 = r5.startToEnd
                if (r2 != r6) goto L_0x012e
                int r2 = r5.rightToLeft
                if (r2 == r6) goto L_0x00fd
                int r2 = r5.rightToLeft
                r5.resolvedRightToLeft = r2
                int r2 = r5.rightMargin
                if (r2 > 0) goto L_0x010d
                if (r1 <= 0) goto L_0x010d
                r5.rightMargin = r1
                goto L_0x010d
            L_0x00fd:
                int r2 = r5.rightToRight
                if (r2 == r6) goto L_0x010d
                int r2 = r5.rightToRight
                r5.resolvedRightToRight = r2
                int r2 = r5.rightMargin
                if (r2 > 0) goto L_0x010d
                if (r1 <= 0) goto L_0x010d
                r5.rightMargin = r1
            L_0x010d:
                int r1 = r5.leftToLeft
                if (r1 == r6) goto L_0x011e
                int r6 = r5.leftToLeft
                r5.resolvedLeftToLeft = r6
                int r6 = r5.leftMargin
                if (r6 > 0) goto L_0x012e
                if (r0 <= 0) goto L_0x012e
                r5.leftMargin = r0
                goto L_0x012e
            L_0x011e:
                int r1 = r5.leftToRight
                if (r1 == r6) goto L_0x012e
                int r6 = r5.leftToRight
                r5.resolvedLeftToRight = r6
                int r6 = r5.leftMargin
                if (r6 > 0) goto L_0x012e
                if (r0 <= 0) goto L_0x012e
                r5.leftMargin = r0
            L_0x012e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }
}
