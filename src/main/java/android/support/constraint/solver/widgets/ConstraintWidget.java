package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.widget.ActivityChooserView;
import java.util.ArrayList;

public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    protected ConstraintWidget[] mListNextVisibleWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    ConstraintWidget mParent;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    int[] mResolvedMatchConstraintDefault;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void resolve() {
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = -1.0f;
        this.mWeight[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        if (this.mResolutionWidth != null) {
            this.mResolutionWidth.reset();
        }
        if (this.mResolutionHeight != null) {
            this.mResolutionHeight.reset();
        }
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int i) {
        Optimizer.analyze(i, this);
    }

    public boolean isFullyResolved() {
        if (this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1) {
            return true;
        }
        return false;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mListNextVisibleWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mListNextVisibleWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget || parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        if (constraintWidget instanceof WidgetContainer) {
            return (WidgetContainer) constraintWidget;
        }
        return null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        if (this.mBaselineDistance > 0) {
            SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
            createObjectVariable5.setName(str + ".baseline");
        }
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(") wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public int getInternalDrawX() {
        return this.mDrawX;
    }

    /* access modifiers changed from: package-private */
    public int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i;
        int i2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i2);
        } else if (this.mMatchConstraintMinWidth > 0) {
            i = this.mMatchConstraintMinWidth;
            this.mWidth = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= i) ? i : this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int i;
        int i2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i2);
        } else if (this.mMatchConstraintMinHeight > 0) {
            i = this.mMatchConstraintMinHeight;
            this.mHeight = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= i) ? i : this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = i;
                return;
            case TOP:
                this.mTop.mGoneMargin = i;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = i;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = i;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = (this.mY + this.mHeight) - i2;
    }

    public void forceUpdateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = (this.mY + this.mHeight) - i2;
    }

    public void setDrawOrigin(int i, int i2) {
        this.mDrawX = i - this.mOffsetX;
        this.mDrawY = i2 - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int i) {
        this.mDrawX = i - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int i) {
        this.mDrawY = i - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int i) {
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int i) {
        this.mDrawHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x008e
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x008e
        L_0x000b:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0037
            int r6 = r2 + -1
            if (r3 >= r6) goto L_0x0037
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002c
            r1 = 0
            goto L_0x0035
        L_0x002c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0035
            r1 = 1
        L_0x0035:
            int r4 = r3 + 1
        L_0x0037:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L_0x0075
            int r2 = r2 - r5
            if (r3 >= r2) goto L_0x0075
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0084
            int r3 = r9.length()
            if (r3 <= 0) goto L_0x0084
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            if (r1 != r5) goto L_0x006f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x006f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0075:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0084
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r9 = 0
        L_0x0085:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008d
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r1
        L_0x008d:
            return
        L_0x008e:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = i2;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i5 < this.mWidth) {
            i5 = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i6 < this.mHeight) {
            i6 = this.mHeight;
        }
        this.mWidth = i5;
        this.mHeight = i6;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2) {
        connect(constraintAnchor, constraintAnchor2, i, ConstraintAnchor.Strength.STRONG, i2);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        connect(constraintAnchor, constraintAnchor2, i, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, ConstraintAnchor.Strength strength, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i, strength, i2);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        connect(type, constraintWidget, type2, i, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, ConstraintAnchor.Strength strength) {
        connect(type, constraintWidget, type2, i, strength, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, ConstraintAnchor.Strength strength, int i2) {
        boolean z;
        ConstraintAnchor.Type type3 = type;
        ConstraintWidget constraintWidget2 = constraintWidget;
        ConstraintAnchor.Type type4 = type2;
        int i3 = i2;
        int i4 = 0;
        if (type3 == ConstraintAnchor.Type.CENTER) {
            if (type4 == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    ConstraintWidget constraintWidget3 = constraintWidget;
                    ConstraintAnchor.Strength strength2 = strength;
                    int i5 = i2;
                    connect(ConstraintAnchor.Type.LEFT, constraintWidget3, ConstraintAnchor.Type.LEFT, 0, strength2, i5);
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget3, ConstraintAnchor.Type.RIGHT, 0, strength2, i5);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    ConstraintWidget constraintWidget4 = constraintWidget;
                    ConstraintAnchor.Strength strength3 = strength;
                    int i6 = i2;
                    connect(ConstraintAnchor.Type.TOP, constraintWidget4, ConstraintAnchor.Type.TOP, 0, strength3, i6);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget4, ConstraintAnchor.Type.BOTTOM, 0, strength3, i6);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.CENTER), 0, i3);
                } else if (z) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, i3);
                } else if (z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, i3);
                }
            } else if (type4 == ConstraintAnchor.Type.LEFT || type4 == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, constraintWidget, type2, 0, strength, i2);
                try {
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0, strength, i2);
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
                } catch (Throwable th) {
                    throw th;
                }
            } else if (type4 == ConstraintAnchor.Type.TOP || type4 == ConstraintAnchor.Type.BOTTOM) {
                ConstraintWidget constraintWidget5 = constraintWidget;
                ConstraintAnchor.Type type5 = type2;
                ConstraintAnchor.Strength strength4 = strength;
                int i7 = i2;
                connect(ConstraintAnchor.Type.TOP, constraintWidget5, type5, 0, strength4, i7);
                connect(ConstraintAnchor.Type.BOTTOM, constraintWidget5, type5, 0, strength4, i7);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            }
        } else if (type3 == ConstraintAnchor.Type.CENTER_X && (type4 == ConstraintAnchor.Type.LEFT || type4 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0, i3);
            anchor7.connect(anchor6, 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(anchor6, 0, i3);
        } else if (type3 == ConstraintAnchor.Type.CENTER_Y && (type4 == ConstraintAnchor.Type.TOP || type4 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(ConstraintAnchor.Type.TOP).connect(anchor8, 0, i3);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(anchor8, 0, i3);
        } else if (type3 == ConstraintAnchor.Type.CENTER_X && type4 == ConstraintAnchor.Type.CENTER_X) {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT), 0, i3);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT), 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else if (type3 == ConstraintAnchor.Type.CENTER_Y && type4 == ConstraintAnchor.Type.CENTER_Y) {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP), 0, i3);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM), 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                if (type3 == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                } else {
                    if (type3 == ConstraintAnchor.Type.TOP || type3 == ConstraintAnchor.Type.BOTTOM) {
                        ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.BASELINE);
                        if (anchor13 != null) {
                            anchor13.reset();
                        }
                        ConstraintAnchor anchor14 = getAnchor(ConstraintAnchor.Type.CENTER);
                        if (anchor14.getTarget() != anchor10) {
                            anchor14.reset();
                        }
                        ConstraintAnchor opposite = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor15 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                        if (anchor15.isConnected()) {
                            opposite.reset();
                            anchor15.reset();
                        }
                    } else if (type3 == ConstraintAnchor.Type.LEFT || type3 == ConstraintAnchor.Type.RIGHT) {
                        ConstraintAnchor anchor16 = getAnchor(ConstraintAnchor.Type.CENTER);
                        if (anchor16.getTarget() != anchor10) {
                            anchor16.reset();
                        }
                        ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor17 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                        if (anchor17.isConnected()) {
                            opposite2.reset();
                            anchor17.reset();
                        }
                    }
                    i4 = i;
                }
                anchor9.connect(anchor10, i4, strength, i3);
                anchor10.getOwner().connectedTo(anchor9.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT) {
                return;
            }
            if (getHeight() == getWrapHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getHeight() > getMinHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                this.mAnchors.get(i).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintAnchor constraintAnchor = this.mAnchors.get(i2);
                if (i == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0232, code lost:
        if (r15.mResolvedDimensionRatioSide == -1) goto L_0x0236;
     */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02c8  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02d9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x034e  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0354  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x035e  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0395  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x03be  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x03c8  */
    /* JADX WARNING: Removed duplicated region for block: B:200:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(android.support.constraint.solver.LinearSystem r39) {
        /*
            r38 = this;
            r15 = r38
            r14 = r39
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.SolverVariable r21 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            android.support.constraint.solver.SolverVariable r10 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mTop
            android.support.constraint.solver.SolverVariable r6 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBottom
            android.support.constraint.solver.SolverVariable r4 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.SolverVariable r3 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            r1 = 8
            r2 = 1
            r13 = 0
            if (r0 == 0) goto L_0x0124
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x003a
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r5) goto L_0x003a
            r0 = 1
            goto L_0x003b
        L_0x003a:
            r0 = 0
        L_0x003b:
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r15.mParent
            if (r5 == 0) goto L_0x004b
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r5.mListDimensionBehaviors
            r5 = r5[r2]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r7) goto L_0x004b
            r5 = 1
            goto L_0x004c
        L_0x004b:
            r5 = 0
        L_0x004c:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x0073
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mLeft
            if (r7 == r8) goto L_0x0073
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x0073
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mRight
            if (r7 != r8) goto L_0x0073
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r7 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r7
            r7.addChain(r15, r13)
        L_0x0073:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x0083
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mLeft
            if (r7 == r8) goto L_0x0093
        L_0x0083:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x0095
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mRight
            if (r7 != r8) goto L_0x0095
        L_0x0093:
            r7 = 1
            goto L_0x0096
        L_0x0095:
            r7 = 0
        L_0x0096:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mTop
            if (r8 == r9) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mBottom
            if (r8 != r9) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r8 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r8
            r8.addChain(r15, r2)
        L_0x00bd:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x00cd
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mTop
            if (r8 == r9) goto L_0x00dd
        L_0x00cd:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x00df
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mBottom
            if (r8 != r9) goto L_0x00df
        L_0x00dd:
            r8 = 1
            goto L_0x00e0
        L_0x00df:
            r8 = 0
        L_0x00e0:
            if (r0 == 0) goto L_0x00fd
            int r9 = r15.mVisibility
            if (r9 == r1) goto L_0x00fd
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x00fd
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x00fd
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mRight
            android.support.constraint.solver.SolverVariable r9 = r14.createObjectVariable(r9)
            r14.addGreaterThan(r9, r10, r13, r2)
        L_0x00fd:
            if (r5 == 0) goto L_0x011e
            int r9 = r15.mVisibility
            if (r9 == r1) goto L_0x011e
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x011e
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x011e
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r15.mBaseline
            if (r9 != 0) goto L_0x011e
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mBottom
            android.support.constraint.solver.SolverVariable r9 = r14.createObjectVariable(r9)
            r14.addGreaterThan(r9, r4, r13, r2)
        L_0x011e:
            r12 = r5
            r16 = r7
            r22 = r8
            goto L_0x012a
        L_0x0124:
            r0 = 0
            r12 = 0
            r16 = 0
            r22 = 0
        L_0x012a:
            int r5 = r15.mWidth
            int r7 = r15.mMinWidth
            if (r5 >= r7) goto L_0x0132
            int r5 = r15.mMinWidth
        L_0x0132:
            int r7 = r15.mHeight
            int r8 = r15.mMinHeight
            if (r7 >= r8) goto L_0x013a
            int r7 = r15.mMinHeight
        L_0x013a:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r15.mListDimensionBehaviors
            r8 = r8[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 == r9) goto L_0x0144
            r8 = 1
            goto L_0x0145
        L_0x0144:
            r8 = 0
        L_0x0145:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r15.mListDimensionBehaviors
            r9 = r9[r2]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 == r11) goto L_0x014f
            r9 = 1
            goto L_0x0150
        L_0x014f:
            r9 = 0
        L_0x0150:
            int r11 = r15.mDimensionRatioSide
            r15.mResolvedDimensionRatioSide = r11
            float r11 = r15.mDimensionRatio
            r15.mResolvedDimensionRatio = r11
            int r11 = r15.mMatchConstraintDefaultWidth
            int r2 = r15.mMatchConstraintDefaultHeight
            float r13 = r15.mDimensionRatio
            r17 = 0
            r18 = 4
            int r13 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r13 <= 0) goto L_0x0213
            int r13 = r15.mVisibility
            r1 = 8
            if (r13 == r1) goto L_0x0213
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r13 = 0
            r1 = r1[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r27 = r3
            r3 = 3
            if (r1 != r13) goto L_0x017b
            if (r11 != 0) goto L_0x017b
            r11 = 3
        L_0x017b:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r13 = 1
            r1 = r1[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r13) goto L_0x0187
            if (r2 != 0) goto L_0x0187
            r2 = 3
        L_0x0187:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r13 = 0
            r1 = r1[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r13) goto L_0x01a2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r13 = 1
            r1 = r1[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r13) goto L_0x01a2
            if (r11 != r3) goto L_0x01a2
            if (r2 != r3) goto L_0x01a2
            r15.setupDimensionRatio(r0, r12, r8, r9)
            goto L_0x0208
        L_0x01a2:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r8 = 0
            r1 = r1[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r9) goto L_0x01ce
            if (r11 != r3) goto L_0x01ce
            r15.mResolvedDimensionRatioSide = r8
            float r1 = r15.mResolvedDimensionRatio
            int r3 = r15.mHeight
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.mListDimensionBehaviors
            r8 = 1
            r3 = r3[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r5) goto L_0x01c9
            r29 = r1
            r25 = r2
            r30 = r7
            r20 = 4
            goto L_0x021d
        L_0x01c9:
            r29 = r1
            r25 = r2
            goto L_0x020c
        L_0x01ce:
            r8 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r1 = r1[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r9) goto L_0x0208
            if (r2 != r3) goto L_0x0208
            r15.mResolvedDimensionRatioSide = r8
            int r1 = r15.mDimensionRatioSide
            r3 = -1
            if (r1 != r3) goto L_0x01e7
            r1 = 1065353216(0x3f800000, float:1.0)
            float r3 = r15.mResolvedDimensionRatio
            float r1 = r1 / r3
            r15.mResolvedDimensionRatio = r1
        L_0x01e7:
            float r1 = r15.mResolvedDimensionRatio
            int r3 = r15.mWidth
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.mListDimensionBehaviors
            r7 = 0
            r3 = r3[r7]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r7) goto L_0x0201
            r30 = r1
            r29 = r5
            r20 = r11
            r25 = 4
            goto L_0x021d
        L_0x0201:
            r30 = r1
            r25 = r2
            r29 = r5
            goto L_0x020e
        L_0x0208:
            r25 = r2
            r29 = r5
        L_0x020c:
            r30 = r7
        L_0x020e:
            r20 = r11
            r28 = 1
            goto L_0x021f
        L_0x0213:
            r27 = r3
            r25 = r2
            r29 = r5
            r30 = r7
            r20 = r11
        L_0x021d:
            r28 = 0
        L_0x021f:
            int[] r1 = r15.mResolvedMatchConstraintDefault
            r2 = 0
            r1[r2] = r20
            int[] r1 = r15.mResolvedMatchConstraintDefault
            r2 = 1
            r1[r2] = r25
            if (r28 == 0) goto L_0x0239
            int r1 = r15.mResolvedDimensionRatioSide
            if (r1 == 0) goto L_0x0235
            int r1 = r15.mResolvedDimensionRatioSide
            r2 = -1
            if (r1 != r2) goto L_0x023a
            goto L_0x0236
        L_0x0235:
            r2 = -1
        L_0x0236:
            r26 = 1
            goto L_0x023c
        L_0x0239:
            r2 = -1
        L_0x023a:
            r26 = 0
        L_0x023c:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r3 = 0
            r1 = r1[r3]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r3) goto L_0x024c
            boolean r1 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r1 == 0) goto L_0x024c
            r31 = 1
            goto L_0x024e
        L_0x024c:
            r31 = 0
        L_0x024e:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mCenter
            boolean r1 = r1.isConnected()
            r3 = 1
            r23 = r1 ^ 1
            int r1 = r15.mHorizontalResolution
            r13 = 2
            r32 = 0
            if (r1 == r13) goto L_0x02c8
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r15.mParent
            if (r1 == 0) goto L_0x026d
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mRight
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r33 = r1
            goto L_0x026f
        L_0x026d:
            r33 = r32
        L_0x026f:
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r15.mParent
            if (r1 == 0) goto L_0x027e
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mLeft
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r34 = r1
            goto L_0x0280
        L_0x027e:
            r34 = r32
        L_0x0280:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r17 = 0
            r5 = r1[r17]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mRight
            int r9 = r15.mX
            int r11 = r15.mMinWidth
            int[] r1 = r15.mMaxDimension
            r1 = r1[r17]
            r24 = r12
            r12 = r1
            float r1 = r15.mHorizontalBiasPercent
            r13 = r1
            int r1 = r15.mMatchConstraintMinWidth
            r17 = r1
            int r1 = r15.mMatchConstraintMaxWidth
            r18 = r1
            float r1 = r15.mMatchConstraintPercentWidth
            r19 = r1
            r35 = r0
            r0 = r38
            r1 = r39
            r2 = r35
            r36 = r27
            r3 = r34
            r27 = r4
            r4 = r33
            r37 = r6
            r6 = r31
            r31 = r10
            r10 = r29
            r14 = r26
            r15 = r16
            r16 = r20
            r20 = r23
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            goto L_0x02d2
        L_0x02c8:
            r37 = r6
            r31 = r10
            r24 = r12
            r36 = r27
            r27 = r4
        L_0x02d2:
            r15 = r38
            int r0 = r15.mVerticalResolution
            r1 = 2
            if (r0 != r1) goto L_0x02da
            return
        L_0x02da:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r14 = 1
            r0 = r0[r14]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x02e9
            boolean r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x02e9
            r6 = 1
            goto L_0x02ea
        L_0x02e9:
            r6 = 0
        L_0x02ea:
            if (r28 == 0) goto L_0x02f8
            int r0 = r15.mResolvedDimensionRatioSide
            if (r0 == r14) goto L_0x02f5
            int r0 = r15.mResolvedDimensionRatioSide
            r1 = -1
            if (r0 != r1) goto L_0x02f8
        L_0x02f5:
            r16 = 1
            goto L_0x02fa
        L_0x02f8:
            r16 = 0
        L_0x02fa:
            int r0 = r15.mBaselineDistance
            if (r0 <= 0) goto L_0x0339
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
            int r0 = r0.state
            if (r0 != r14) goto L_0x0316
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.widgets.ResolutionAnchor r0 = r0.getResolutionNode()
            r10 = r39
            r0.addResolvedValue(r10)
            r4 = r37
            goto L_0x033d
        L_0x0316:
            r10 = r39
            int r0 = r38.getBaselineDistance()
            r1 = 6
            r2 = r36
            r4 = r37
            r10.addEquality(r2, r4, r0, r1)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x033d
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r3 = 0
            r10.addEquality(r2, r0, r3, r1)
            r20 = 0
            goto L_0x033f
        L_0x0339:
            r4 = r37
            r10 = r39
        L_0x033d:
            r20 = r23
        L_0x033f:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x034e
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            android.support.constraint.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r23 = r0
            goto L_0x0350
        L_0x034e:
            r23 = r32
        L_0x0350:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x035e
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTop
            android.support.constraint.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r3 = r0
            goto L_0x0360
        L_0x035e:
            r3 = r32
        L_0x0360:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r5 = r0[r14]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            int r9 = r15.mY
            int r11 = r15.mMinHeight
            int[] r0 = r15.mMaxDimension
            r12 = r0[r14]
            float r13 = r15.mVerticalBiasPercent
            int r0 = r15.mMatchConstraintMinHeight
            r17 = r0
            int r0 = r15.mMatchConstraintMaxHeight
            r18 = r0
            float r0 = r15.mMatchConstraintPercentHeight
            r19 = r0
            r0 = r38
            r1 = r39
            r2 = r24
            r24 = r4
            r4 = r23
            r10 = r30
            r14 = r16
            r15 = r22
            r16 = r25
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            if (r28 == 0) goto L_0x03be
            r6 = 6
            r7 = r38
            int r0 = r7.mResolvedDimensionRatioSide
            r1 = 1
            if (r0 != r1) goto L_0x03ae
            float r5 = r7.mResolvedDimensionRatio
            r6 = 6
            r0 = r39
            r1 = r27
            r2 = r24
            r3 = r31
            r4 = r21
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x03c0
        L_0x03ae:
            float r5 = r7.mResolvedDimensionRatio
            r0 = r39
            r1 = r31
            r2 = r21
            r3 = r27
            r4 = r24
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x03c0
        L_0x03be:
            r7 = r38
        L_0x03c0:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x03e8
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.getOwner()
            float r1 = r7.mCircleConstraintAngle
            r2 = 1119092736(0x42b40000, float:90.0)
            float r1 = r1 + r2
            double r1 = (double) r1
            double r1 = java.lang.Math.toRadians(r1)
            float r1 = (float) r1
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r7.mCenter
            int r2 = r2.getMargin()
            r3 = r39
            r3.addCenterPoint(r7, r0, r1, r2)
        L_0x03e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver(android.support.constraint.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && z && z2) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02e1  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02ee  */
    /* JADX WARNING: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(android.support.constraint.solver.LinearSystem r31, boolean r32, android.support.constraint.solver.SolverVariable r33, android.support.constraint.solver.SolverVariable r34, android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour r35, boolean r36, android.support.constraint.solver.widgets.ConstraintAnchor r37, android.support.constraint.solver.widgets.ConstraintAnchor r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, int r46, int r47, int r48, float r49, boolean r50) {
        /*
            r30 = this;
            r0 = r30
            r10 = r31
            r11 = r33
            r12 = r34
            r1 = r41
            r2 = r42
            r13 = r37
            android.support.constraint.solver.SolverVariable r9 = r10.createObjectVariable(r13)
            r8 = r38
            android.support.constraint.solver.SolverVariable r7 = r10.createObjectVariable(r8)
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r37.getTarget()
            android.support.constraint.solver.SolverVariable r6 = r10.createObjectVariable(r6)
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r38.getTarget()
            android.support.constraint.solver.SolverVariable r14 = r10.createObjectVariable(r14)
            boolean r8 = r10.graphOptimizer
            r15 = 1
            if (r8 == 0) goto L_0x0066
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r37.getResolutionNode()
            int r8 = r8.state
            r13 = 1
            if (r8 != r13) goto L_0x0066
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r38.getResolutionNode()
            int r8 = r8.state
            if (r8 != r13) goto L_0x0066
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.getMetrics()
            if (r1 == 0) goto L_0x004e
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.getMetrics()
            long r2 = r1.resolvedWidgets
            long r2 = r2 + r15
            r1.resolvedWidgets = r2
        L_0x004e:
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r37.getResolutionNode()
            r1.addResolvedValue(r10)
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r38.getResolutionNode()
            r1.addResolvedValue(r10)
            if (r45 != 0) goto L_0x0065
            if (r32 == 0) goto L_0x0065
            r1 = 0
            r2 = 6
            r10.addGreaterThan(r12, r7, r1, r2)
        L_0x0065:
            return
        L_0x0066:
            android.support.constraint.solver.Metrics r8 = android.support.constraint.solver.LinearSystem.getMetrics()
            if (r8 == 0) goto L_0x0078
            android.support.constraint.solver.Metrics r8 = android.support.constraint.solver.LinearSystem.getMetrics()
            r20 = r14
            long r13 = r8.nonresolvedWidgets
            long r13 = r13 + r15
            r8.nonresolvedWidgets = r13
            goto L_0x007a
        L_0x0078:
            r20 = r14
        L_0x007a:
            boolean r8 = r37.isConnected()
            boolean r13 = r38.isConnected()
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r0.mCenter
            boolean r21 = r14.isConnected()
            if (r8 == 0) goto L_0x008c
            r14 = 1
            goto L_0x008d
        L_0x008c:
            r14 = 0
        L_0x008d:
            if (r13 == 0) goto L_0x0091
            int r14 = r14 + 1
        L_0x0091:
            if (r21 == 0) goto L_0x0095
            int r14 = r14 + 1
        L_0x0095:
            if (r44 == 0) goto L_0x0099
            r11 = 3
            goto L_0x009b
        L_0x0099:
            r11 = r46
        L_0x009b:
            int[] r15 = android.support.constraint.solver.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintWidget$DimensionBehaviour
            int r16 = r35.ordinal()
            r15 = r15[r16]
            r12 = 4
            switch(r15) {
                case 1: goto L_0x00a7;
                case 2: goto L_0x00a7;
                case 3: goto L_0x00a7;
                case 4: goto L_0x00a9;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            r15 = 0
            goto L_0x00ad
        L_0x00a9:
            if (r11 != r12) goto L_0x00ac
            goto L_0x00a7
        L_0x00ac:
            r15 = 1
        L_0x00ad:
            int r12 = r0.mVisibility
            r22 = r14
            r14 = 8
            if (r12 != r14) goto L_0x00b8
            r12 = 0
            r15 = 0
            goto L_0x00ba
        L_0x00b8:
            r12 = r40
        L_0x00ba:
            if (r50 == 0) goto L_0x00d7
            if (r8 != 0) goto L_0x00c8
            if (r13 != 0) goto L_0x00c8
            if (r21 != 0) goto L_0x00c8
            r14 = r39
            r10.addEquality(r9, r14)
            goto L_0x00d7
        L_0x00c8:
            if (r8 == 0) goto L_0x00d7
            if (r13 != 0) goto L_0x00d7
            int r14 = r37.getMargin()
            r23 = r13
            r13 = 6
            r10.addEquality(r9, r6, r14, r13)
            goto L_0x00da
        L_0x00d7:
            r23 = r13
            r13 = 6
        L_0x00da:
            if (r15 != 0) goto L_0x010a
            if (r36 == 0) goto L_0x00f4
            r13 = 3
            r14 = 0
            r10.addEquality(r7, r9, r14, r13)
            if (r1 <= 0) goto L_0x00ea
            r14 = 6
            r10.addGreaterThan(r7, r9, r1, r14)
            goto L_0x00eb
        L_0x00ea:
            r14 = 6
        L_0x00eb:
            r12 = 2147483647(0x7fffffff, float:NaN)
            if (r2 >= r12) goto L_0x00f9
            r10.addLowerThan(r7, r9, r2, r14)
            goto L_0x00f9
        L_0x00f4:
            r13 = 3
            r14 = 6
            r10.addEquality(r7, r9, r12, r14)
        L_0x00f9:
            r13 = r47
            r2 = r48
            r24 = r6
            r27 = r11
            r6 = r20
            r0 = r22
            r11 = 2
            r20 = 4
            goto L_0x01e7
        L_0x010a:
            r13 = 3
            r2 = -2
            r14 = r47
            if (r14 != r2) goto L_0x0114
            r14 = r48
            r13 = r12
            goto L_0x0117
        L_0x0114:
            r13 = r14
            r14 = r48
        L_0x0117:
            if (r14 != r2) goto L_0x011b
            r2 = r12
            goto L_0x011c
        L_0x011b:
            r2 = r14
        L_0x011c:
            if (r13 <= 0) goto L_0x012e
            if (r32 == 0) goto L_0x0125
            r14 = 6
            r10.addGreaterThan(r7, r9, r13, r14)
            goto L_0x0129
        L_0x0125:
            r14 = 6
            r10.addGreaterThan(r7, r9, r13, r14)
        L_0x0129:
            int r12 = java.lang.Math.max(r12, r13)
            goto L_0x012f
        L_0x012e:
            r14 = 6
        L_0x012f:
            if (r2 <= 0) goto L_0x0140
            if (r32 == 0) goto L_0x0139
            r14 = 1
            r10.addLowerThan(r7, r9, r2, r14)
            r14 = 6
            goto L_0x013c
        L_0x0139:
            r10.addLowerThan(r7, r9, r2, r14)
        L_0x013c:
            int r12 = java.lang.Math.min(r12, r2)
        L_0x0140:
            r14 = 1
            if (r11 != r14) goto L_0x0159
            if (r32 == 0) goto L_0x014b
            r14 = 6
            r10.addEquality(r7, r9, r12, r14)
            goto L_0x01c7
        L_0x014b:
            if (r45 == 0) goto L_0x0153
            r14 = 4
            r10.addEquality(r7, r9, r12, r14)
            goto L_0x01c7
        L_0x0153:
            r14 = 1
            r10.addEquality(r7, r9, r12, r14)
            goto L_0x01c7
        L_0x0159:
            r14 = 2
            if (r11 != r14) goto L_0x01c7
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r14 = r37.getType()
            r24 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            if (r14 == r6) goto L_0x018e
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = r37.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r6 != r14) goto L_0x016f
            goto L_0x018e
        L_0x016f:
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r0.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.getAnchor(r14)
            android.support.constraint.solver.SolverVariable r6 = r10.createObjectVariable(r6)
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r0.mParent
            r25 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r14.getAnchor(r6)
            android.support.constraint.solver.SolverVariable r6 = r10.createObjectVariable(r6)
            r17 = r6
            r18 = r25
            goto L_0x01ac
        L_0x018e:
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r0.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.getAnchor(r14)
            android.support.constraint.solver.SolverVariable r6 = r10.createObjectVariable(r6)
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r0.mParent
            r26 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r14.getAnchor(r6)
            android.support.constraint.solver.SolverVariable r6 = r10.createObjectVariable(r6)
            r17 = r6
            r18 = r26
        L_0x01ac:
            android.support.constraint.solver.ArrayRow r14 = r31.createRow()
            r27 = r11
            r6 = r20
            r0 = r22
            r11 = 2
            r20 = 4
            r15 = r7
            r16 = r9
            r19 = r49
            android.support.constraint.solver.ArrayRow r14 = r14.createRowDimensionRatio(r15, r16, r17, r18, r19)
            r10.addConstraint(r14)
            r15 = 0
            goto L_0x01d2
        L_0x01c7:
            r24 = r6
            r27 = r11
            r6 = r20
            r0 = r22
            r11 = 2
            r20 = 4
        L_0x01d2:
            if (r15 == 0) goto L_0x01e7
            if (r0 == r11) goto L_0x01e7
            if (r44 != 0) goto L_0x01e7
            int r12 = java.lang.Math.max(r13, r12)
            if (r2 <= 0) goto L_0x01e2
            int r12 = java.lang.Math.min(r2, r12)
        L_0x01e2:
            r14 = 6
            r10.addEquality(r7, r9, r12, r14)
            r15 = 0
        L_0x01e7:
            if (r50 == 0) goto L_0x02f2
            if (r45 == 0) goto L_0x01ed
            goto L_0x02f2
        L_0x01ed:
            r0 = 5
            if (r8 != 0) goto L_0x0204
            if (r23 != 0) goto L_0x0204
            if (r21 != 0) goto L_0x0204
            if (r32 == 0) goto L_0x01fd
            r4 = 0
            r12 = r34
            r10.addGreaterThan(r12, r7, r4, r0)
            goto L_0x01ff
        L_0x01fd:
            r12 = r34
        L_0x01ff:
            r2 = r7
            r0 = 0
            r1 = 6
            goto L_0x02ec
        L_0x0204:
            r4 = 0
            r5 = 4
            r12 = r34
            if (r8 == 0) goto L_0x0212
            if (r23 != 0) goto L_0x0212
            if (r32 == 0) goto L_0x01ff
            r10.addGreaterThan(r12, r7, r4, r0)
            goto L_0x01ff
        L_0x0212:
            if (r8 != 0) goto L_0x0227
            if (r23 == 0) goto L_0x0227
            int r1 = r38.getMargin()
            int r1 = -r1
            r2 = 6
            r10.addEquality(r7, r6, r1, r2)
            if (r32 == 0) goto L_0x01ff
            r14 = r33
            r10.addGreaterThan(r9, r14, r4, r0)
            goto L_0x01ff
        L_0x0227:
            r11 = 3
            r14 = r33
            if (r8 == 0) goto L_0x01ff
            if (r23 == 0) goto L_0x01ff
            if (r15 == 0) goto L_0x0291
            if (r32 == 0) goto L_0x0238
            if (r1 != 0) goto L_0x0238
            r1 = 6
            r10.addGreaterThan(r7, r9, r4, r1)
        L_0x0238:
            if (r27 != 0) goto L_0x0260
            if (r2 > 0) goto L_0x0242
            if (r13 <= 0) goto L_0x023f
            goto L_0x0242
        L_0x023f:
            r1 = 0
            r5 = 6
            goto L_0x0243
        L_0x0242:
            r1 = 1
        L_0x0243:
            int r3 = r37.getMargin()
            r8 = r24
            r10.addEquality(r9, r8, r3, r5)
            int r3 = r38.getMargin()
            int r3 = -r3
            r10.addEquality(r7, r6, r3, r5)
            if (r2 > 0) goto L_0x025b
            if (r13 <= 0) goto L_0x0259
            goto L_0x025b
        L_0x0259:
            r13 = 0
            goto L_0x025c
        L_0x025b:
            r13 = 1
        L_0x025c:
            r15 = r1
            r11 = r30
            goto L_0x02a8
        L_0x0260:
            r8 = r24
            r1 = r27
            r13 = 1
            if (r1 != r13) goto L_0x026c
            r0 = 6
            r11 = r30
        L_0x026a:
            r15 = 1
            goto L_0x02a8
        L_0x026c:
            if (r1 != r11) goto L_0x028d
            if (r44 != 0) goto L_0x027b
            r11 = r30
            int r1 = r11.mResolvedDimensionRatioSide
            r3 = -1
            if (r1 == r3) goto L_0x027d
            if (r2 > 0) goto L_0x027d
            r5 = 6
            goto L_0x027d
        L_0x027b:
            r11 = r30
        L_0x027d:
            int r1 = r37.getMargin()
            r10.addEquality(r9, r8, r1, r5)
            int r1 = r38.getMargin()
            int r1 = -r1
            r10.addEquality(r7, r6, r1, r5)
            goto L_0x026a
        L_0x028d:
            r11 = r30
            r13 = 0
            goto L_0x02a7
        L_0x0291:
            r8 = r24
            r11 = r30
            r13 = 1
            if (r32 == 0) goto L_0x02a7
            int r1 = r37.getMargin()
            r10.addGreaterThan(r9, r8, r1, r0)
            int r1 = r38.getMargin()
            int r1 = -r1
            r10.addLowerThan(r7, r6, r1, r0)
        L_0x02a7:
            r15 = 0
        L_0x02a8:
            if (r13 == 0) goto L_0x02c4
            int r4 = r37.getMargin()
            int r13 = r38.getMargin()
            r1 = r31
            r2 = r9
            r3 = r8
            r5 = r43
            r28 = r6
            r29 = r7
            r11 = r8
            r8 = r13
            r13 = r9
            r9 = r0
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x02ca
        L_0x02c4:
            r28 = r6
            r29 = r7
            r11 = r8
            r13 = r9
        L_0x02ca:
            if (r15 == 0) goto L_0x02e1
            int r0 = r37.getMargin()
            r1 = 6
            r10.addGreaterThan(r13, r11, r0, r1)
            int r0 = r38.getMargin()
            int r0 = -r0
            r3 = r28
            r2 = r29
            r10.addLowerThan(r2, r3, r0, r1)
            goto L_0x02e4
        L_0x02e1:
            r2 = r29
            r1 = 6
        L_0x02e4:
            if (r32 == 0) goto L_0x02eb
            r0 = 0
            r10.addGreaterThan(r13, r14, r0, r1)
            goto L_0x02ec
        L_0x02eb:
            r0 = 0
        L_0x02ec:
            if (r32 == 0) goto L_0x02f1
            r10.addGreaterThan(r12, r2, r0, r1)
        L_0x02f1:
            return
        L_0x02f2:
            r3 = r0
            r2 = r7
            r13 = r9
            r0 = 0
            r1 = 6
            r12 = r34
            r14 = r33
            if (r3 >= r11) goto L_0x0305
            if (r32 == 0) goto L_0x0305
            r10.addGreaterThan(r13, r14, r0, r1)
            r10.addGreaterThan(r12, r2, r0, r1)
        L_0x0305:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.applyConstraints(android.support.constraint.solver.LinearSystem, boolean, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, android.support.constraint.solver.widgets.ConstraintAnchor, android.support.constraint.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, float, boolean):void");
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }
}
