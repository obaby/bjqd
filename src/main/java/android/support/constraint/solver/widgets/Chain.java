package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i2 = i5;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i3, chainHead)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        if (r2.mVerticalChainStyle == 2) goto L_0x0037;
     */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x03ae  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x03b1  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03b7  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x047d  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x04d7  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x04dc  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x04e2  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x04e7  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x04eb  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x04fd  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x019f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r47, android.support.constraint.solver.LinearSystem r48, int r49, int r50, android.support.constraint.solver.widgets.ChainHead r51) {
        /*
            r0 = r47
            r9 = r48
            r1 = r51
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r1.mFirst
            android.support.constraint.solver.widgets.ConstraintWidget r12 = r1.mLast
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r1.mFirstVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r14 = r1.mLastVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mHead
            float r3 = r1.mTotalWeight
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mFirstMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mLastMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r49]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r4 != r5) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r5 = 2
            if (r49 != 0) goto L_0x0042
            int r8 = r2.mHorizontalChainStyle
            if (r8 != 0) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            int r6 = r2.mHorizontalChainStyle
            if (r6 != r7) goto L_0x0032
            r6 = 1
            goto L_0x0033
        L_0x0032:
            r6 = 0
        L_0x0033:
            int r7 = r2.mHorizontalChainStyle
            if (r7 != r5) goto L_0x0039
        L_0x0037:
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            r7 = r5
            r18 = r6
            r17 = r8
            r6 = r11
            r5 = 0
            goto L_0x0056
        L_0x0042:
            int r6 = r2.mVerticalChainStyle
            if (r6 != 0) goto L_0x0048
            r8 = 1
            goto L_0x0049
        L_0x0048:
            r8 = 0
        L_0x0049:
            int r6 = r2.mVerticalChainStyle
            r7 = 1
            if (r6 != r7) goto L_0x0050
            r6 = 1
            goto L_0x0051
        L_0x0050:
            r6 = 0
        L_0x0051:
            int r7 = r2.mVerticalChainStyle
            if (r7 != r5) goto L_0x0039
            goto L_0x0037
        L_0x0056:
            r21 = 0
            if (r5 != 0) goto L_0x0135
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r6.mListAnchors
            r8 = r8[r50]
            if (r4 != 0) goto L_0x0066
            if (r7 == 0) goto L_0x0063
            goto L_0x0066
        L_0x0063:
            r23 = 4
            goto L_0x0068
        L_0x0066:
            r23 = 1
        L_0x0068:
            int r24 = r8.getMargin()
            r25 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTarget
            if (r3 == 0) goto L_0x007c
            if (r6 == r11) goto L_0x007c
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTarget
            int r3 = r3.getMargin()
            int r24 = r24 + r3
        L_0x007c:
            r3 = r24
            if (r7 == 0) goto L_0x008a
            if (r6 == r11) goto L_0x008a
            if (r6 == r13) goto L_0x008a
            r27 = r2
            r26 = r5
            r5 = 6
            goto L_0x009a
        L_0x008a:
            if (r17 == 0) goto L_0x0094
            if (r4 == 0) goto L_0x0094
            r27 = r2
            r26 = r5
            r5 = 4
            goto L_0x009a
        L_0x0094:
            r27 = r2
            r26 = r5
            r5 = r23
        L_0x009a:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mTarget
            if (r2 == 0) goto L_0x00c7
            if (r6 != r13) goto L_0x00af
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            r28 = r11
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r8.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r29 = r7
            r7 = 5
            r9.addGreaterThan(r2, r11, r3, r7)
            goto L_0x00bd
        L_0x00af:
            r29 = r7
            r28 = r11
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r8.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r11 = 6
            r9.addGreaterThan(r2, r7, r3, r11)
        L_0x00bd:
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r8.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r9.addEquality(r2, r7, r3, r5)
            goto L_0x00cb
        L_0x00c7:
            r29 = r7
            r28 = r11
        L_0x00cb:
            if (r4 == 0) goto L_0x0102
            int r2 = r6.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x00f1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r6.mListDimensionBehaviors
            r2 = r2[r49]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x00f1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r6.mListAnchors
            int r3 = r50 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r6.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 0
            r7 = 5
            r9.addGreaterThan(r2, r3, r5, r7)
            goto L_0x00f2
        L_0x00f1:
            r5 = 0
        L_0x00f2:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r6.mListAnchors
            r2 = r2[r50]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r7 = 6
            r9.addGreaterThan(r2, r3, r5, r7)
        L_0x0102:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r6.mListAnchors
            int r3 = r50 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0123
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0123
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r6) goto L_0x0121
            goto L_0x0123
        L_0x0121:
            r21 = r2
        L_0x0123:
            if (r21 == 0) goto L_0x012a
            r6 = r21
            r5 = r26
            goto L_0x012b
        L_0x012a:
            r5 = 1
        L_0x012b:
            r3 = r25
            r2 = r27
            r11 = r28
            r7 = r29
            goto L_0x0056
        L_0x0135:
            r27 = r2
            r25 = r3
            r29 = r7
            r28 = r11
            if (r14 == 0) goto L_0x0161
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            int r3 = r50 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0161
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r5 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r12.mListAnchors
            r3 = r6[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r8 = 5
            r9.addLowerThan(r5, r3, r2, r8)
            goto L_0x0162
        L_0x0161:
            r8 = 5
        L_0x0162:
            if (r4 == 0) goto L_0x017e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r2 = r50 + 1
            r0 = r0[r2]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r3 = r3[r2]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 6
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x017e:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r0 = r1.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x0236
            int r2 = r0.size()
            r7 = 1
            if (r2 <= r7) goto L_0x0236
            boolean r3 = r1.mHasUndefinedWeights
            if (r3 == 0) goto L_0x0195
            boolean r3 = r1.mHasComplexMatchWeights
            if (r3 != 0) goto L_0x0195
            int r3 = r1.mWidgetsMatchCount
            float r3 = (float) r3
            goto L_0x0197
        L_0x0195:
            r3 = r25
        L_0x0197:
            r4 = 0
            r6 = r21
            r5 = 0
            r31 = 0
        L_0x019d:
            if (r5 >= r2) goto L_0x0236
            java.lang.Object r11 = r0.get(r5)
            android.support.constraint.solver.widgets.ConstraintWidget r11 = (android.support.constraint.solver.widgets.ConstraintWidget) r11
            float[] r7 = r11.mWeight
            r7 = r7[r49]
            int r16 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r16 >= 0) goto L_0x01cb
            boolean r7 = r1.mHasComplexMatchWeights
            if (r7 == 0) goto L_0x01c7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            int r16 = r50 + 1
            r7 = r7[r16]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r11 = r11.mListAnchors
            r11 = r11[r50]
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r4 = 4
            r8 = 0
            r9.addEquality(r7, r11, r8, r4)
            r4 = 0
            r8 = 6
            goto L_0x01e4
        L_0x01c7:
            r4 = 4
            r7 = 1065353216(0x3f800000, float:1.0)
            goto L_0x01cc
        L_0x01cb:
            r4 = 4
        L_0x01cc:
            r8 = 0
            int r16 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r16 != 0) goto L_0x01e9
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            int r16 = r50 + 1
            r7 = r7[r16]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r11 = r11.mListAnchors
            r11 = r11[r50]
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r4 = 0
            r8 = 6
            r9.addEquality(r7, r11, r4, r8)
        L_0x01e4:
            r39 = r0
            r40 = r2
            goto L_0x022b
        L_0x01e9:
            r4 = 0
            r8 = 6
            if (r6 == 0) goto L_0x0224
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r6.mListAnchors
            r4 = r4[r50]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r6.mListAnchors
            int r15 = r50 + 1
            r6 = r6[r15]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r11.mListAnchors
            r8 = r8[r50]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r39 = r0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r11.mListAnchors
            r0 = r0[r15]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r40 = r2
            android.support.constraint.solver.ArrayRow r2 = r48.createRow()
            r30 = r2
            r32 = r3
            r33 = r7
            r34 = r4
            r35 = r6
            r36 = r8
            r37 = r0
            r30.createRowEqualMatchDimensions(r31, r32, r33, r34, r35, r36, r37)
            r9.addConstraint(r2)
            goto L_0x0228
        L_0x0224:
            r39 = r0
            r40 = r2
        L_0x0228:
            r31 = r7
            r6 = r11
        L_0x022b:
            int r5 = r5 + 1
            r0 = r39
            r2 = r40
            r4 = 0
            r7 = 1
            r8 = 5
            goto L_0x019d
        L_0x0236:
            if (r13 == 0) goto L_0x02a3
            if (r13 == r14) goto L_0x023c
            if (r29 == 0) goto L_0x02a3
        L_0x023c:
            r11 = r28
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r11.mListAnchors
            r0 = r0[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            int r2 = r50 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0259
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x025b
        L_0x0259:
            r3 = r21
        L_0x025b:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x026d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x026f
        L_0x026d:
            r5 = r21
        L_0x026f:
            if (r13 != r14) goto L_0x0279
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r2]
        L_0x0279:
            if (r3 == 0) goto L_0x04c3
            if (r5 == 0) goto L_0x04c3
            if (r49 != 0) goto L_0x0285
            r2 = r27
            float r2 = r2.mHorizontalBiasPercent
        L_0x0283:
            r4 = r2
            goto L_0x028a
        L_0x0285:
            r2 = r27
            float r2 = r2.mVerticalBiasPercent
            goto L_0x0283
        L_0x028a:
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r48
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04c3
        L_0x02a3:
            r11 = r28
            if (r17 == 0) goto L_0x039c
            if (r13 == 0) goto L_0x039c
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02b6
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02b6
            r38 = 1
            goto L_0x02b8
        L_0x02b6:
            r38 = 0
        L_0x02b8:
            r0 = r13
            r8 = r0
        L_0x02ba:
            if (r8 == 0) goto L_0x04c3
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mListNextVisibleWidget
            r7 = r1[r49]
            if (r7 != 0) goto L_0x02ce
            if (r8 != r14) goto L_0x02c5
            goto L_0x02ce
        L_0x02c5:
            r20 = r7
            r15 = r8
        L_0x02c8:
            r16 = 6
            r22 = 4
            goto L_0x0397
        L_0x02ce:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r1 = r1[r50]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x02dd
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02df
        L_0x02dd:
            r3 = r21
        L_0x02df:
            if (r0 == r8) goto L_0x02ea
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            int r4 = r50 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0301
        L_0x02ea:
            if (r8 != r13) goto L_0x0301
            if (r0 != r8) goto L_0x0301
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02ff
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0301
        L_0x02ff:
            r3 = r21
        L_0x0301:
            int r1 = r1.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r8.mListAnchors
            int r5 = r50 + 1
            r4 = r4[r5]
            int r4 = r4.getMargin()
            if (r7 == 0) goto L_0x0322
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r7.mListAnchors
            r6 = r6[r50]
            r41 = r7
            android.support.constraint.solver.SolverVariable r7 = r6.mSolverVariable
            r42 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r5]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            goto L_0x033d
        L_0x0322:
            r41 = r7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r12.mListAnchors
            r6 = r6[r5]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x0331
            android.support.constraint.solver.SolverVariable r7 = r6.mSolverVariable
            r43 = r6
            goto L_0x0335
        L_0x0331:
            r43 = r6
            r7 = r21
        L_0x0335:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r5]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r42 = r43
        L_0x033d:
            if (r42 == 0) goto L_0x0344
            int r15 = r42.getMargin()
            int r4 = r4 + r15
        L_0x0344:
            if (r0 == 0) goto L_0x034f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            r0 = r0[r5]
            int r0 = r0.getMargin()
            int r1 = r1 + r0
        L_0x034f:
            if (r2 == 0) goto L_0x0392
            if (r3 == 0) goto L_0x0392
            if (r7 == 0) goto L_0x0392
            if (r6 == 0) goto L_0x0392
            if (r8 != r13) goto L_0x0363
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r50]
            int r0 = r0.getMargin()
            r15 = r0
            goto L_0x0364
        L_0x0363:
            r15 = r1
        L_0x0364:
            if (r8 != r14) goto L_0x0371
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r5]
            int r0 = r0.getMargin()
            r16 = r0
            goto L_0x0373
        L_0x0371:
            r16 = r4
        L_0x0373:
            if (r38 == 0) goto L_0x0378
            r19 = 6
            goto L_0x037a
        L_0x0378:
            r19 = 4
        L_0x037a:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r48
            r1 = r2
            r2 = r3
            r3 = r15
            r15 = 4
            r5 = r7
            r20 = r41
            r7 = r16
            r15 = r8
            r16 = 6
            r22 = 4
            r8 = r19
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0397
        L_0x0392:
            r15 = r8
            r20 = r41
            goto L_0x02c8
        L_0x0397:
            r0 = r15
            r8 = r20
            goto L_0x02ba
        L_0x039c:
            r16 = 6
            r22 = 4
            if (r18 == 0) goto L_0x04c3
            if (r13 == 0) goto L_0x04c3
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03b1
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03b1
            r38 = 1
            goto L_0x03b3
        L_0x03b1:
            r38 = 0
        L_0x03b3:
            r0 = r13
            r8 = r0
        L_0x03b5:
            if (r8 == 0) goto L_0x0464
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mListNextVisibleWidget
            r1 = r1[r49]
            if (r8 == r13) goto L_0x045c
            if (r8 == r14) goto L_0x045c
            if (r1 == 0) goto L_0x045c
            if (r1 != r14) goto L_0x03c6
            r7 = r21
            goto L_0x03c7
        L_0x03c6:
            r7 = r1
        L_0x03c7:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r1 = r1[r50]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x03d5
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
        L_0x03d5:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            int r4 = r50 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r1 = r1.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            if (r7 == 0) goto L_0x0404
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r7.mListAnchors
            r6 = r6[r50]
            r44 = r7
            android.support.constraint.solver.SolverVariable r7 = r6.mSolverVariable
            r45 = r7
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            if (r7 == 0) goto L_0x03fe
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            goto L_0x0400
        L_0x03fe:
            r7 = r21
        L_0x0400:
            r46 = r6
            r6 = r7
            goto L_0x041f
        L_0x0404:
            r44 = r7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x0413
            android.support.constraint.solver.SolverVariable r7 = r6.mSolverVariable
            r46 = r6
            goto L_0x0417
        L_0x0413:
            r46 = r6
            r7 = r21
        L_0x0417:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r4]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r45 = r7
        L_0x041f:
            if (r46 == 0) goto L_0x0426
            int r7 = r46.getMargin()
            int r5 = r5 + r7
        L_0x0426:
            r7 = r5
            if (r0 == 0) goto L_0x0432
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            int r1 = r1 + r0
        L_0x0432:
            r4 = r1
            if (r38 == 0) goto L_0x0437
            r15 = 6
            goto L_0x0438
        L_0x0437:
            r15 = 4
        L_0x0438:
            if (r2 == 0) goto L_0x0454
            if (r3 == 0) goto L_0x0454
            if (r45 == 0) goto L_0x0454
            if (r6 == 0) goto L_0x0454
            r5 = 1056964608(0x3f000000, float:0.5)
            r0 = r48
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r45
            r19 = r44
            r20 = r8
            r10 = 5
            r8 = r15
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0459
        L_0x0454:
            r20 = r8
            r19 = r44
            r10 = 5
        L_0x0459:
            r8 = r19
            goto L_0x0460
        L_0x045c:
            r20 = r8
            r10 = 5
            r8 = r1
        L_0x0460:
            r0 = r20
            goto L_0x03b5
        L_0x0464:
            r10 = 5
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r1 = r1[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r50 + 1
            r11 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r2.mTarget
            if (r1 == 0) goto L_0x04b1
            if (r13 == r14) goto L_0x048b
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r9.addEquality(r2, r1, r0, r10)
            goto L_0x04b1
        L_0x048b:
            if (r8 == 0) goto L_0x04b1
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r6 = r11.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r8.mSolverVariable
            int r15 = r11.getMargin()
            r16 = 5
            r0 = r48
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r15
            r10 = r8
            r8 = r16
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04b2
        L_0x04b1:
            r10 = r8
        L_0x04b2:
            if (r10 == 0) goto L_0x04c3
            if (r13 == r14) goto L_0x04c3
            android.support.constraint.solver.SolverVariable r0 = r11.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r10.mSolverVariable
            int r2 = r11.getMargin()
            int r2 = -r2
            r3 = 5
            r9.addEquality(r0, r1, r2, r3)
        L_0x04c3:
            if (r17 != 0) goto L_0x04c7
            if (r18 == 0) goto L_0x052a
        L_0x04c7:
            if (r13 == 0) goto L_0x052a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r2 = r50 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x04dc
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x04de
        L_0x04dc:
            r3 = r21
        L_0x04de:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x04e7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x04e9
        L_0x04e7:
            r4 = r21
        L_0x04e9:
            if (r12 == r14) goto L_0x04fa
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x04f8
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x04fa
        L_0x04f8:
            r4 = r21
        L_0x04fa:
            r5 = r4
            if (r13 != r14) goto L_0x0505
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r50]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r2]
        L_0x0505:
            if (r3 == 0) goto L_0x052a
            if (r5 == 0) goto L_0x052a
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r14 != 0) goto L_0x0512
            goto L_0x0513
        L_0x0512:
            r12 = r14
        L_0x0513:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r12.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r48
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x052a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
