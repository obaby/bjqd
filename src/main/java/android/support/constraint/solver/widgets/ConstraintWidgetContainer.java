package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    private static final boolean USE_SNAPSHOT = true;
    int mDebugSolverPassCount = 0;
    private boolean mHeightMeasuredTooSmall = false;
    ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    int mHorizontalChainsSize = 0;
    private boolean mIsRtl = false;
    private int mOptimizationLevel = 3;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem = new LinearSystem();
    ChainHead[] mVerticalChainsArray = new ChainHead[4];
    int mVerticalChainsSize = 0;
    private boolean mWidthMeasuredTooSmall = false;

    public String getType() {
        return "ConstraintLayout";
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        if ((this.mOptimizationLevel & i) == i) {
            return USE_SNAPSHOT;
        }
        return false;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem);
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                constraintWidget.addToSolver(linearSystem);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 1);
        }
        return USE_SNAPSHOT;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.updateFromSolver(linearSystem);
            if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = USE_SNAPSHOT;
            }
            if (constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = USE_SNAPSHOT;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public void analyze(int i) {
        super.analyze(i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintWidget) this.mChildren.get(i2)).analyze(i);
        }
    }

    /* JADX WARNING: type inference failed for: r12v11, types: [boolean] */
    /* JADX WARNING: type inference failed for: r12v15 */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r17 = this;
            r1 = r17
            int r2 = r1.mX
            int r3 = r1.mY
            int r0 = r17.getWidth()
            r4 = 0
            int r5 = java.lang.Math.max(r4, r0)
            int r0 = r17.getHeight()
            int r6 = java.lang.Math.max(r4, r0)
            r1.mWidthMeasuredTooSmall = r4
            r1.mHeightMeasuredTooSmall = r4
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r1.mParent
            if (r0 == 0) goto L_0x0046
            android.support.constraint.solver.widgets.Snapshot r0 = r1.mSnapshot
            if (r0 != 0) goto L_0x002a
            android.support.constraint.solver.widgets.Snapshot r0 = new android.support.constraint.solver.widgets.Snapshot
            r0.<init>(r1)
            r1.mSnapshot = r0
        L_0x002a:
            android.support.constraint.solver.widgets.Snapshot r0 = r1.mSnapshot
            r0.updateFrom(r1)
            int r0 = r1.mPaddingLeft
            r1.setX(r0)
            int r0 = r1.mPaddingTop
            r1.setY(r0)
            r17.resetAnchors()
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            android.support.constraint.solver.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            goto L_0x004a
        L_0x0046:
            r1.mX = r4
            r1.mY = r4
        L_0x004a:
            int r0 = r1.mOptimizationLevel
            r7 = 8
            r8 = 1
            if (r0 == 0) goto L_0x0062
            boolean r0 = r1.optimizeFor(r7)
            if (r0 != 0) goto L_0x005a
            r17.optimizeReset()
        L_0x005a:
            r17.optimize()
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            r0.graphOptimizer = r8
            goto L_0x0066
        L_0x0062:
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            r0.graphOptimizer = r4
        L_0x0066:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r9 = r0[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r10 = r0[r4]
            r17.resetChains()
            java.util.ArrayList r0 = r1.mChildren
            int r11 = r0.size()
            r0 = 0
        L_0x0078:
            if (r0 >= r11) goto L_0x008e
            java.util.ArrayList r12 = r1.mChildren
            java.lang.Object r12 = r12.get(r0)
            android.support.constraint.solver.widgets.ConstraintWidget r12 = (android.support.constraint.solver.widgets.ConstraintWidget) r12
            boolean r13 = r12 instanceof android.support.constraint.solver.widgets.WidgetContainer
            if (r13 == 0) goto L_0x008b
            android.support.constraint.solver.widgets.WidgetContainer r12 = (android.support.constraint.solver.widgets.WidgetContainer) r12
            r12.layout()
        L_0x008b:
            int r0 = r0 + 1
            goto L_0x0078
        L_0x008e:
            r0 = 0
            r12 = 1
            r13 = 0
        L_0x0091:
            if (r12 == 0) goto L_0x021f
            int r14 = r0 + 1
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00c4 }
            r0.reset()     // Catch:{ Exception -> 0x00c4 }
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00c4 }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x00c4 }
            r0 = 0
        L_0x00a0:
            if (r0 >= r11) goto L_0x00b4
            java.util.ArrayList r15 = r1.mChildren     // Catch:{ Exception -> 0x00c4 }
            java.lang.Object r15 = r15.get(r0)     // Catch:{ Exception -> 0x00c4 }
            android.support.constraint.solver.widgets.ConstraintWidget r15 = (android.support.constraint.solver.widgets.ConstraintWidget) r15     // Catch:{ Exception -> 0x00c4 }
            android.support.constraint.solver.LinearSystem r7 = r1.mSystem     // Catch:{ Exception -> 0x00c4 }
            r15.createObjectVariables(r7)     // Catch:{ Exception -> 0x00c4 }
            int r0 = r0 + 1
            r7 = 8
            goto L_0x00a0
        L_0x00b4:
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00c4 }
            boolean r7 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x00c4 }
            if (r7 == 0) goto L_0x00df
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x00c2 }
            r0.minimize()     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00df
        L_0x00c2:
            r0 = move-exception
            goto L_0x00c6
        L_0x00c4:
            r0 = move-exception
            r7 = r12
        L_0x00c6:
            r0.printStackTrace()
            java.io.PrintStream r12 = java.lang.System.out
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r8 = "EXCEPTION : "
            r15.append(r8)
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            r12.println(r0)
        L_0x00df:
            r0 = 2
            if (r7 == 0) goto L_0x00ec
            android.support.constraint.solver.LinearSystem r7 = r1.mSystem
            boolean[] r8 = android.support.constraint.solver.widgets.Optimizer.flags
            r1.updateChildrenFromSolver(r7, r8)
        L_0x00e9:
            r4 = 8
            goto L_0x0130
        L_0x00ec:
            android.support.constraint.solver.LinearSystem r7 = r1.mSystem
            r1.updateFromSolver(r7)
            r7 = 0
        L_0x00f2:
            if (r7 >= r11) goto L_0x00e9
            java.util.ArrayList r8 = r1.mChildren
            java.lang.Object r8 = r8.get(r7)
            android.support.constraint.solver.widgets.ConstraintWidget r8 = (android.support.constraint.solver.widgets.ConstraintWidget) r8
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r12 = r8.mListDimensionBehaviors
            r12 = r12[r4]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r12 != r15) goto L_0x0114
            int r12 = r8.getWidth()
            int r15 = r8.getWrapWidth()
            if (r12 >= r15) goto L_0x0114
            boolean[] r7 = android.support.constraint.solver.widgets.Optimizer.flags
            r12 = 1
            r7[r0] = r12
            goto L_0x00e9
        L_0x0114:
            r12 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r15 = r8.mListDimensionBehaviors
            r15 = r15[r12]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r4) goto L_0x012c
            int r4 = r8.getHeight()
            int r8 = r8.getWrapHeight()
            if (r4 >= r8) goto L_0x012c
            boolean[] r4 = android.support.constraint.solver.widgets.Optimizer.flags
            r4[r0] = r12
            goto L_0x00e9
        L_0x012c:
            int r7 = r7 + 1
            r4 = 0
            goto L_0x00f2
        L_0x0130:
            if (r14 >= r4) goto L_0x019c
            boolean[] r7 = android.support.constraint.solver.widgets.Optimizer.flags
            boolean r0 = r7[r0]
            if (r0 == 0) goto L_0x019c
            r0 = 0
            r7 = 0
            r8 = 0
        L_0x013b:
            if (r0 >= r11) goto L_0x015f
            java.util.ArrayList r12 = r1.mChildren
            java.lang.Object r12 = r12.get(r0)
            android.support.constraint.solver.widgets.ConstraintWidget r12 = (android.support.constraint.solver.widgets.ConstraintWidget) r12
            int r15 = r12.mX
            int r16 = r12.getWidth()
            int r15 = r15 + r16
            int r7 = java.lang.Math.max(r7, r15)
            int r15 = r12.mY
            int r12 = r12.getHeight()
            int r15 = r15 + r12
            int r8 = java.lang.Math.max(r8, r15)
            int r0 = r0 + 1
            goto L_0x013b
        L_0x015f:
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r7)
            int r7 = r1.mMinHeight
            int r7 = java.lang.Math.max(r7, r8)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r10 != r8) goto L_0x0182
            int r8 = r17.getWidth()
            if (r8 >= r0) goto L_0x0182
            r1.setWidth(r0)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r12 = 0
            r0[r12] = r8
            r0 = 1
            r13 = 1
            goto L_0x0183
        L_0x0182:
            r0 = 0
        L_0x0183:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 != r8) goto L_0x019a
            int r8 = r17.getHeight()
            if (r8 >= r7) goto L_0x019a
            r1.setHeight(r7)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r8 = 1
            r0[r8] = r7
            r0 = 1
            r8 = 1
            goto L_0x019e
        L_0x019a:
            r8 = r13
            goto L_0x019e
        L_0x019c:
            r8 = r13
            r0 = 0
        L_0x019e:
            int r7 = r1.mMinWidth
            int r12 = r17.getWidth()
            int r7 = java.lang.Math.max(r7, r12)
            int r12 = r17.getWidth()
            if (r7 <= r12) goto L_0x01ba
            r1.setWidth(r7)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r8 = 0
            r0[r8] = r7
            r0 = 1
            r8 = 1
        L_0x01ba:
            int r7 = r1.mMinHeight
            int r12 = r17.getHeight()
            int r7 = java.lang.Math.max(r7, r12)
            int r12 = r17.getHeight()
            if (r7 <= r12) goto L_0x01d7
            r1.setHeight(r7)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r12 = 1
            r0[r12] = r7
            r0 = 1
            r8 = 1
            goto L_0x01d8
        L_0x01d7:
            r12 = 1
        L_0x01d8:
            if (r8 != 0) goto L_0x0216
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            r13 = 0
            r7 = r7[r13]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r15) goto L_0x01f8
            if (r5 <= 0) goto L_0x01f8
            int r7 = r17.getWidth()
            if (r7 <= r5) goto L_0x01f8
            r1.mWidthMeasuredTooSmall = r12
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r13] = r7
            r1.setWidth(r5)
            r0 = 1
            r8 = 1
        L_0x01f8:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            r7 = r7[r12]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r13) goto L_0x0216
            if (r6 <= 0) goto L_0x0216
            int r7 = r17.getHeight()
            if (r7 <= r6) goto L_0x0216
            r1.mHeightMeasuredTooSmall = r12
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r12] = r7
            r1.setHeight(r6)
            r12 = 1
            r13 = 1
            goto L_0x0218
        L_0x0216:
            r12 = r0
            r13 = r8
        L_0x0218:
            r0 = r14
            r4 = 0
            r7 = 8
            r8 = 1
            goto L_0x0091
        L_0x021f:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r1.mParent
            if (r0 == 0) goto L_0x024f
            int r0 = r1.mMinWidth
            int r2 = r17.getWidth()
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r1.mMinHeight
            int r3 = r17.getHeight()
            int r2 = java.lang.Math.max(r2, r3)
            android.support.constraint.solver.widgets.Snapshot r3 = r1.mSnapshot
            r3.applyTo(r1)
            int r3 = r1.mPaddingLeft
            int r0 = r0 + r3
            int r3 = r1.mPaddingRight
            int r0 = r0 + r3
            r1.setWidth(r0)
            int r0 = r1.mPaddingTop
            int r2 = r2 + r0
            int r0 = r1.mPaddingBottom
            int r2 = r2 + r0
            r1.setHeight(r2)
            goto L_0x0253
        L_0x024f:
            r1.mX = r2
            r1.mY = r3
        L_0x0253:
            if (r13 == 0) goto L_0x025f
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 0
            r0[r2] = r10
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 1
            r0[r2] = r9
        L_0x025f:
            android.support.constraint.solver.LinearSystem r0 = r1.mSystem
            android.support.constraint.solver.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r0 = r17.getRootConstraintContainer()
            if (r1 != r0) goto L_0x0271
            r17.updateDrawPosition()
        L_0x0271:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    public void preOptimize() {
        optimizeReset();
        analyze(this.mOptimizationLevel);
    }

    public void solveGraph() {
        ResolutionAnchor resolutionNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        resolutionNode.resolve((ResolutionAnchor) null, 0.0f);
        resolutionNode2.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void resetGraph() {
        ResolutionAnchor resolutionNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        resolutionNode.invalidateAnchors();
        resolutionNode2.invalidateAnchors();
        resolutionNode.resolve((ResolutionAnchor) null, 0.0f);
        resolutionNode2.resolve((ResolutionAnchor) null, 0.0f);
    }

    public void optimizeForDimensions(int i, int i2) {
        if (!(this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.mResolutionWidth == null)) {
            this.mResolutionWidth.resolve(i);
        }
        if (this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.mResolutionHeight != null) {
            this.mResolutionHeight.resolve(i2);
        }
    }

    public void optimizeReset() {
        int size = this.mChildren.size();
        resetResolutionNodes();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetResolutionNodes();
        }
    }

    public void optimize() {
        if (!optimizeFor(8)) {
            analyze(this.mOptimizationLevel);
        }
        solveGraph();
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }
}
