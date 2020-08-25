package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 3;
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidgetContainer.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i, width);
        }
        if (constraintWidgetContainer.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.mTop.mMargin;
            int height = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i2);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, height);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i2);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i2, height);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget r4, int r5) {
        /*
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r4.mListDimensionBehaviors
            r0 = r0[r5]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            float r0 = r4.mDimensionRatio
            r1 = 0
            r3 = 1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0020
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r4.mListDimensionBehaviors
            if (r5 != 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = 0
        L_0x0018:
            r4 = r4[r3]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r5) goto L_0x001f
            return r2
        L_0x001f:
            return r2
        L_0x0020:
            if (r5 != 0) goto L_0x0030
            int r5 = r4.mMatchConstraintDefaultWidth
            if (r5 == 0) goto L_0x0027
            return r2
        L_0x0027:
            int r5 = r4.mMatchConstraintMinWidth
            if (r5 != 0) goto L_0x002f
            int r4 = r4.mMatchConstraintMaxWidth
            if (r4 == 0) goto L_0x003e
        L_0x002f:
            return r2
        L_0x0030:
            int r5 = r4.mMatchConstraintDefaultHeight
            if (r5 == 0) goto L_0x0035
            return r2
        L_0x0035:
            int r5 = r4.mMatchConstraintMinHeight
            if (r5 != 0) goto L_0x003f
            int r4 = r4.mMatchConstraintMaxHeight
            if (r4 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            return r3
        L_0x003f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget, int):boolean");
    }

    static void analyze(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget constraintWidget2 = constraintWidget;
        constraintWidget.updateResolutionNodes();
        ResolutionAnchor resolutionNode = constraintWidget2.mLeft.getResolutionNode();
        ResolutionAnchor resolutionNode2 = constraintWidget2.mTop.getResolutionNode();
        ResolutionAnchor resolutionNode3 = constraintWidget2.mRight.getResolutionNode();
        ResolutionAnchor resolutionNode4 = constraintWidget2.mBottom.getResolutionNode();
        boolean z = (i & 8) == 8;
        boolean z2 = constraintWidget2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget2, 0);
        if (!(resolutionNode.type == 4 || resolutionNode3.type == 4)) {
            if (constraintWidget2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || (z2 && constraintWidget.getVisibility() == 8)) {
                if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget2.mLeft.mTarget != null && constraintWidget2.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget != null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    }
                } else if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null)) {
                    resolutionNode.setType(2);
                    resolutionNode3.setType(2);
                    if (z) {
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                        resolutionNode.setOpposite(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                        resolutionNode3.setOpposite(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.setOpposite(resolutionNode3, (float) (-constraintWidget.getWidth()));
                        resolutionNode3.setOpposite(resolutionNode, (float) constraintWidget.getWidth());
                    }
                }
            } else if (z2) {
                int width = constraintWidget.getWidth();
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget == null) {
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, width);
                    }
                } else if (constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget != null) {
                    if (constraintWidget2.mLeft.mTarget != null || constraintWidget2.mRight.mTarget == null) {
                        if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null)) {
                            if (z) {
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                            }
                            if (constraintWidget2.mDimensionRatio == 0.0f) {
                                resolutionNode.setType(3);
                                resolutionNode3.setType(3);
                                resolutionNode.setOpposite(resolutionNode3, 0.0f);
                                resolutionNode3.setOpposite(resolutionNode, 0.0f);
                            } else {
                                resolutionNode.setType(2);
                                resolutionNode3.setType(2);
                                resolutionNode.setOpposite(resolutionNode3, (float) (-width));
                                resolutionNode3.setOpposite(resolutionNode, (float) width);
                                constraintWidget2.setWidth(width);
                            }
                        }
                    } else if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -width);
                    }
                } else if (z) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, width);
                }
            }
        }
        boolean z3 = constraintWidget2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget2, 1);
        if (resolutionNode2.type != 4 && resolutionNode4.type != 4) {
            if (constraintWidget2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || (z3 && constraintWidget.getVisibility() == 8)) {
                if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaseline.mTarget != null) {
                        constraintWidget2.mBaseline.getResolutionNode().setType(1);
                        resolutionNode2.dependsOn(1, constraintWidget2.mBaseline.getResolutionNode(), -constraintWidget2.mBaselineDistance);
                    }
                } else if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaselineDistance > 0) {
                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                    }
                } else if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget != null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaselineDistance > 0) {
                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                    }
                } else if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget != null) {
                    resolutionNode2.setType(2);
                    resolutionNode4.setType(2);
                    if (z) {
                        resolutionNode2.setOpposite(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                        resolutionNode4.setOpposite(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                        constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                    } else {
                        resolutionNode2.setOpposite(resolutionNode4, (float) (-constraintWidget.getHeight()));
                        resolutionNode4.setOpposite(resolutionNode2, (float) constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaselineDistance > 0) {
                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                    }
                }
            } else if (z3) {
                int height = constraintWidget.getHeight();
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget == null) {
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, height);
                    }
                } else if (constraintWidget2.mTop.mTarget == null || constraintWidget2.mBottom.mTarget != null) {
                    if (constraintWidget2.mTop.mTarget != null || constraintWidget2.mBottom.mTarget == null) {
                        if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget != null) {
                            if (z) {
                                constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                            }
                            if (constraintWidget2.mDimensionRatio == 0.0f) {
                                resolutionNode2.setType(3);
                                resolutionNode4.setType(3);
                                resolutionNode2.setOpposite(resolutionNode4, 0.0f);
                                resolutionNode4.setOpposite(resolutionNode2, 0.0f);
                                return;
                            }
                            resolutionNode2.setType(2);
                            resolutionNode4.setType(2);
                            resolutionNode2.setOpposite(resolutionNode4, (float) (-height));
                            resolutionNode4.setOpposite(resolutionNode2, (float) height);
                            constraintWidget2.setHeight(height);
                            if (constraintWidget2.mBaselineDistance > 0) {
                                constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                            }
                        }
                    } else if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -height);
                    }
                } else if (z) {
                    resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode4.dependsOn(resolutionNode2, height);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r6.mHorizontalChainStyle == 2) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        if (r6.mVerticalChainStyle == 2) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer r20, android.support.constraint.solver.LinearSystem r21, int r22, int r23, android.support.constraint.solver.widgets.ChainHead r24) {
        /*
            r0 = r21
            r1 = r24
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mFirst
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r1.mLast
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mFirstVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r1.mLastVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r1.mHead
            float r7 = r1.mTotalWeight
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r1.mFirstMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r1.mLastMatchConstraintWidget
            r8 = r20
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r8.mListDimensionBehaviors
            r1 = r1[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r1 = 2
            r9 = 1
            if (r22 != 0) goto L_0x0036
            int r10 = r6.mHorizontalChainStyle
            if (r10 != 0) goto L_0x0026
            r10 = 1
            goto L_0x0027
        L_0x0026:
            r10 = 0
        L_0x0027:
            int r11 = r6.mHorizontalChainStyle
            if (r11 != r9) goto L_0x002d
            r11 = 1
            goto L_0x002e
        L_0x002d:
            r11 = 0
        L_0x002e:
            int r6 = r6.mHorizontalChainStyle
            if (r6 != r1) goto L_0x0034
        L_0x0032:
            r1 = 1
            goto L_0x0049
        L_0x0034:
            r1 = 0
            goto L_0x0049
        L_0x0036:
            int r10 = r6.mVerticalChainStyle
            if (r10 != 0) goto L_0x003c
            r10 = 1
            goto L_0x003d
        L_0x003c:
            r10 = 0
        L_0x003d:
            int r11 = r6.mVerticalChainStyle
            if (r11 != r9) goto L_0x0043
            r11 = 1
            goto L_0x0044
        L_0x0043:
            r11 = 0
        L_0x0044:
            int r6 = r6.mVerticalChainStyle
            if (r6 != r1) goto L_0x0034
            goto L_0x0032
        L_0x0049:
            r13 = r2
            r6 = 0
            r9 = 0
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x004f:
            if (r12 != 0) goto L_0x00f2
            int r8 = r13.getVisibility()
            r16 = r12
            r12 = 8
            if (r8 == r12) goto L_0x008e
            int r6 = r6 + 1
            if (r22 != 0) goto L_0x0066
            int r8 = r13.getWidth()
            float r8 = (float) r8
            float r14 = r14 + r8
            goto L_0x006c
        L_0x0066:
            int r8 = r13.getHeight()
            float r8 = (float) r8
            float r14 = r14 + r8
        L_0x006c:
            if (r13 == r4) goto L_0x0078
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r13.mListAnchors
            r8 = r8[r23]
            int r8 = r8.getMargin()
            float r8 = (float) r8
            float r14 = r14 + r8
        L_0x0078:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r13.mListAnchors
            r8 = r8[r23]
            int r8 = r8.getMargin()
            float r8 = (float) r8
            float r15 = r15 + r8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r13.mListAnchors
            int r17 = r23 + 1
            r8 = r8[r17]
            int r8 = r8.getMargin()
            float r8 = (float) r8
            float r15 = r15 + r8
        L_0x008e:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r13.mListAnchors
            r8 = r8[r23]
            int r8 = r13.getVisibility()
            if (r8 == r12) goto L_0x00c3
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r8 = r13.mListDimensionBehaviors
            r8 = r8[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r12) goto L_0x00c3
            int r9 = r9 + 1
            if (r22 != 0) goto L_0x00b4
            int r8 = r13.mMatchConstraintDefaultWidth
            if (r8 == 0) goto L_0x00aa
            r8 = 0
            return r8
        L_0x00aa:
            r8 = 0
            int r12 = r13.mMatchConstraintMinWidth
            if (r12 != 0) goto L_0x00b3
            int r12 = r13.mMatchConstraintMaxWidth
            if (r12 == 0) goto L_0x00c3
        L_0x00b3:
            return r8
        L_0x00b4:
            r8 = 0
            int r12 = r13.mMatchConstraintDefaultHeight
            if (r12 == 0) goto L_0x00ba
            return r8
        L_0x00ba:
            int r12 = r13.mMatchConstraintMinHeight
            if (r12 != 0) goto L_0x00c2
            int r12 = r13.mMatchConstraintMaxHeight
            if (r12 == 0) goto L_0x00c3
        L_0x00c2:
            return r8
        L_0x00c3:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r13.mListAnchors
            int r12 = r23 + 1
            r8 = r8[r12]
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x00e5
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r8.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r8.mListAnchors
            r12 = r12[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x00e5
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r8.mListAnchors
            r12 = r12[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r12 = r12.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r12 = r12.mOwner
            if (r12 == r13) goto L_0x00e2
            goto L_0x00e5
        L_0x00e2:
            r18 = r8
            goto L_0x00e7
        L_0x00e5:
            r18 = 0
        L_0x00e7:
            if (r18 == 0) goto L_0x00ef
            r12 = r16
            r13 = r18
            goto L_0x004f
        L_0x00ef:
            r12 = 1
            goto L_0x004f
        L_0x00f2:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r2.mListAnchors
            r8 = r8[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r3.mListAnchors
            int r12 = r23 + 1
            r3 = r3[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            r19 = r2
            android.support.constraint.solver.widgets.ResolutionAnchor r2 = r8.target
            if (r2 == 0) goto L_0x0352
            android.support.constraint.solver.widgets.ResolutionAnchor r2 = r3.target
            if (r2 != 0) goto L_0x0110
            goto L_0x0352
        L_0x0110:
            android.support.constraint.solver.widgets.ResolutionAnchor r2 = r8.target
            int r2 = r2.state
            r0 = 1
            if (r2 == r0) goto L_0x011f
            android.support.constraint.solver.widgets.ResolutionAnchor r2 = r3.target
            int r2 = r2.state
            if (r2 == r0) goto L_0x011f
            r0 = 0
            return r0
        L_0x011f:
            r0 = 0
            if (r9 <= 0) goto L_0x0125
            if (r9 == r6) goto L_0x0125
            return r0
        L_0x0125:
            if (r1 != 0) goto L_0x012e
            if (r10 != 0) goto L_0x012e
            if (r11 == 0) goto L_0x012c
            goto L_0x012e
        L_0x012c:
            r0 = 0
            goto L_0x0147
        L_0x012e:
            if (r4 == 0) goto L_0x013a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r4.mListAnchors
            r0 = r0[r23]
            int r0 = r0.getMargin()
            float r0 = (float) r0
            goto L_0x013b
        L_0x013a:
            r0 = 0
        L_0x013b:
            if (r5 == 0) goto L_0x0147
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r5.mListAnchors
            r2 = r2[r12]
            int r2 = r2.getMargin()
            float r2 = (float) r2
            float r0 = r0 + r2
        L_0x0147:
            android.support.constraint.solver.widgets.ResolutionAnchor r2 = r8.target
            float r2 = r2.resolvedOffset
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.target
            float r3 = r3.resolvedOffset
            int r16 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r16 >= 0) goto L_0x0156
            float r3 = r3 - r2
            float r3 = r3 - r14
            goto L_0x0159
        L_0x0156:
            float r3 = r2 - r3
            float r3 = r3 - r14
        L_0x0159:
            r16 = 1
            if (r9 <= 0) goto L_0x021b
            if (r9 != r6) goto L_0x021b
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r13.getParent()
            if (r1 == 0) goto L_0x0173
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r13.getParent()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r1.mListDimensionBehaviors
            r1 = r1[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r6) goto L_0x0173
            r1 = 0
            return r1
        L_0x0173:
            float r3 = r3 + r14
            float r3 = r3 - r15
            if (r10 == 0) goto L_0x0179
            float r15 = r15 - r0
            float r3 = r3 - r15
        L_0x0179:
            if (r10 == 0) goto L_0x0195
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r4.mListAnchors
            r0 = r0[r12]
            int r0 = r0.getMargin()
            float r0 = (float) r0
            float r2 = r2 + r0
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r4.mListNextVisibleWidget
            r0 = r0[r22]
            if (r0 == 0) goto L_0x0195
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            r0 = r0[r23]
            int r0 = r0.getMargin()
            float r0 = (float) r0
            float r2 = r2 + r0
        L_0x0195:
            if (r4 == 0) goto L_0x0219
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r0 == 0) goto L_0x01b3
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            long r10 = r0.nonresolvedWidgets
            long r10 = r10 - r16
            r0.nonresolvedWidgets = r10
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            long r10 = r0.resolvedWidgets
            long r10 = r10 + r16
            r0.resolvedWidgets = r10
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            long r10 = r0.chainConnectionResolved
            long r10 = r10 + r16
            r0.chainConnectionResolved = r10
        L_0x01b3:
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r4.mListNextVisibleWidget
            r0 = r0[r22]
            if (r0 != 0) goto L_0x01c0
            if (r4 != r5) goto L_0x01bc
            goto L_0x01c0
        L_0x01bc:
            r6 = 0
            r13 = r21
            goto L_0x0216
        L_0x01c0:
            float r1 = (float) r9
            float r1 = r3 / r1
            r6 = 0
            int r10 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x01cf
            float[] r1 = r4.mWeight
            r1 = r1[r22]
            float r1 = r1 * r3
            float r1 = r1 / r7
        L_0x01cf:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r10 = r4.mListAnchors
            r10 = r10[r23]
            int r10 = r10.getMargin()
            float r10 = (float) r10
            float r2 = r2 + r10
            android.support.constraint.solver.widgets.ConstraintAnchor[] r10 = r4.mListAnchors
            r10 = r10[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r10 = r10.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r11 = r8.resolvedTarget
            r10.resolve(r11, r2)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r10 = r4.mListAnchors
            r10 = r10[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r10 = r10.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r11 = r8.resolvedTarget
            float r2 = r2 + r1
            r10.resolve(r11, r2)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r13 = r21
            r1.addResolvedValue(r13)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r1.addResolvedValue(r13)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r12]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r2 = r2 + r1
        L_0x0216:
            r4 = r0
            goto L_0x0195
        L_0x0219:
            r0 = 1
            return r0
        L_0x021b:
            r13 = r21
            int r7 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r7 >= 0) goto L_0x0223
            r7 = 0
            return r7
        L_0x0223:
            if (r1 == 0) goto L_0x02aa
            float r3 = r3 - r0
            float r0 = r19.getHorizontalBiasPercent()
            float r3 = r3 * r0
            float r2 = r2 + r3
        L_0x022d:
            if (r4 == 0) goto L_0x02a7
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r0 == 0) goto L_0x024b
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r0.nonresolvedWidgets
            long r6 = r6 - r16
            r0.nonresolvedWidgets = r6
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r0.resolvedWidgets
            long r6 = r6 + r16
            r0.resolvedWidgets = r6
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r0.chainConnectionResolved
            long r6 = r6 + r16
            r0.chainConnectionResolved = r6
        L_0x024b:
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r4.mListNextVisibleWidget
            r0 = r0[r22]
            if (r0 != 0) goto L_0x0253
            if (r4 != r5) goto L_0x02a5
        L_0x0253:
            if (r22 != 0) goto L_0x025b
            int r1 = r4.getWidth()
            float r1 = (float) r1
            goto L_0x0260
        L_0x025b:
            int r1 = r4.getHeight()
            float r1 = (float) r1
        L_0x0260:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r4.mListAnchors
            r3 = r3[r23]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            float r2 = r2 + r3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r4.mListAnchors
            r3 = r3[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r8.resolvedTarget
            r3.resolve(r6, r2)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r4.mListAnchors
            r3 = r3[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r8.resolvedTarget
            float r2 = r2 + r1
            r3.resolve(r6, r2)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r1.addResolvedValue(r13)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r1.addResolvedValue(r13)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r12]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r2 = r2 + r1
        L_0x02a5:
            r4 = r0
            goto L_0x022d
        L_0x02a7:
            r0 = 1
            goto L_0x0351
        L_0x02aa:
            if (r10 != 0) goto L_0x02ae
            if (r11 == 0) goto L_0x02a7
        L_0x02ae:
            if (r10 == 0) goto L_0x02b2
            float r3 = r3 - r0
            goto L_0x02b5
        L_0x02b2:
            if (r11 == 0) goto L_0x02b5
            float r3 = r3 - r0
        L_0x02b5:
            int r0 = r6 + 1
            float r0 = (float) r0
            float r0 = r3 / r0
            if (r11 == 0) goto L_0x02c9
            r1 = 1
            if (r6 <= r1) goto L_0x02c5
            int r0 = r6 + -1
            float r0 = (float) r0
            float r0 = r3 / r0
            goto L_0x02c9
        L_0x02c5:
            r0 = 1073741824(0x40000000, float:2.0)
            float r0 = r3 / r0
        L_0x02c9:
            float r1 = r2 + r0
            if (r11 == 0) goto L_0x02da
            r3 = 1
            if (r6 <= r3) goto L_0x02da
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r23]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r1 = r1 + r2
        L_0x02da:
            if (r10 == 0) goto L_0x02e8
            if (r4 == 0) goto L_0x02e8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r4.mListAnchors
            r2 = r2[r23]
            int r2 = r2.getMargin()
            float r2 = (float) r2
            float r1 = r1 + r2
        L_0x02e8:
            if (r4 == 0) goto L_0x02a7
            android.support.constraint.solver.Metrics r2 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r2 == 0) goto L_0x0306
            android.support.constraint.solver.Metrics r2 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r2.nonresolvedWidgets
            long r6 = r6 - r16
            r2.nonresolvedWidgets = r6
            android.support.constraint.solver.Metrics r2 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r2.resolvedWidgets
            long r6 = r6 + r16
            r2.resolvedWidgets = r6
            android.support.constraint.solver.Metrics r2 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r2.chainConnectionResolved
            long r6 = r6 + r16
            r2.chainConnectionResolved = r6
        L_0x0306:
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r4.mListNextVisibleWidget
            r2 = r2[r22]
            if (r2 != 0) goto L_0x030e
            if (r4 != r5) goto L_0x034f
        L_0x030e:
            if (r22 != 0) goto L_0x0316
            int r3 = r4.getWidth()
            float r3 = (float) r3
            goto L_0x031b
        L_0x0316:
            int r3 = r4.getHeight()
            float r3 = (float) r3
        L_0x031b:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r6.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r8.resolvedTarget
            r6.resolve(r7, r1)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r6.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r8.resolvedTarget
            float r9 = r1 + r3
            r6.resolve(r7, r9)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r6.getResolutionNode()
            r6.addResolvedValue(r13)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r4.mListAnchors
            r4 = r4[r12]
            android.support.constraint.solver.widgets.ResolutionAnchor r4 = r4.getResolutionNode()
            r4.addResolvedValue(r13)
            float r3 = r3 + r0
            float r1 = r1 + r3
        L_0x034f:
            r4 = r2
            goto L_0x02e8
        L_0x0351:
            return r0
        L_0x0352:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):boolean");
    }
}
