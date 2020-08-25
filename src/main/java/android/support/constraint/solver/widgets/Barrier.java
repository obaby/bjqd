package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Barrier extends Helper {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private boolean mAllowsGoneWidget = true;
    private int mBarrierType = 0;
    private ArrayList<ResolutionAnchor> mNodes = new ArrayList<>(4);

    public boolean allowedInBarrier() {
        return true;
    }

    public void setBarrierType(int i) {
        this.mBarrierType = i;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    public void resetResolutionNodes() {
        super.resetResolutionNodes();
        this.mNodes.clear();
    }

    public void analyze(int i) {
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        if (this.mParent != null && ((ConstraintWidgetContainer) this.mParent).optimizeFor(2)) {
            switch (this.mBarrierType) {
                case 0:
                    resolutionAnchor = this.mLeft.getResolutionNode();
                    break;
                case 1:
                    resolutionAnchor = this.mRight.getResolutionNode();
                    break;
                case 2:
                    resolutionAnchor = this.mTop.getResolutionNode();
                    break;
                case 3:
                    resolutionAnchor = this.mBottom.getResolutionNode();
                    break;
                default:
                    return;
            }
            resolutionAnchor.setType(5);
            if (this.mBarrierType == 0 || this.mBarrierType == 1) {
                this.mTop.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
                this.mBottom.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
            } else {
                this.mLeft.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
                this.mRight.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
            }
            this.mNodes.clear();
            for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
                ConstraintWidget constraintWidget = this.mWidgets[i2];
                if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
                    switch (this.mBarrierType) {
                        case 0:
                            resolutionAnchor2 = constraintWidget.mLeft.getResolutionNode();
                            break;
                        case 1:
                            resolutionAnchor2 = constraintWidget.mRight.getResolutionNode();
                            break;
                        case 2:
                            resolutionAnchor2 = constraintWidget.mTop.getResolutionNode();
                            break;
                        case 3:
                            resolutionAnchor2 = constraintWidget.mBottom.getResolutionNode();
                            break;
                        default:
                            resolutionAnchor2 = null;
                            break;
                    }
                    if (resolutionAnchor2 != null) {
                        this.mNodes.add(resolutionAnchor2);
                        resolutionAnchor2.addDependent(resolutionAnchor);
                    }
                }
            }
        }
    }

    public void resolve() {
        ResolutionAnchor resolutionAnchor;
        float f = Float.MAX_VALUE;
        switch (this.mBarrierType) {
            case 0:
                resolutionAnchor = this.mLeft.getResolutionNode();
                break;
            case 1:
                resolutionAnchor = this.mRight.getResolutionNode();
                break;
            case 2:
                resolutionAnchor = this.mTop.getResolutionNode();
                break;
            case 3:
                resolutionAnchor = this.mBottom.getResolutionNode();
                break;
            default:
                return;
        }
        f = 0.0f;
        int size = this.mNodes.size();
        ResolutionAnchor resolutionAnchor2 = null;
        int i = 0;
        while (i < size) {
            ResolutionAnchor resolutionAnchor3 = this.mNodes.get(i);
            if (resolutionAnchor3.state == 1) {
                if (this.mBarrierType == 0 || this.mBarrierType == 2) {
                    if (resolutionAnchor3.resolvedOffset < f) {
                        f = resolutionAnchor3.resolvedOffset;
                        resolutionAnchor2 = resolutionAnchor3.resolvedTarget;
                    }
                } else if (resolutionAnchor3.resolvedOffset > f) {
                    f = resolutionAnchor3.resolvedOffset;
                    resolutionAnchor2 = resolutionAnchor3.resolvedTarget;
                }
                i++;
            } else {
                return;
            }
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().barrierConnectionResolved++;
        }
        resolutionAnchor.resolvedTarget = resolutionAnchor2;
        resolutionAnchor.resolvedOffset = f;
        resolutionAnchor.didResolve();
        switch (this.mBarrierType) {
            case 0:
                this.mRight.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            case 1:
                this.mLeft.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            case 2:
                this.mBottom.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            case 3:
                this.mTop.getResolutionNode().resolve(resolutionAnchor2, f);
                return;
            default:
                return;
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        this.mListAnchors[0] = this.mLeft;
        this.mListAnchors[2] = this.mTop;
        this.mListAnchors[1] = this.mRight;
        this.mListAnchors[3] = this.mBottom;
        for (int i = 0; i < this.mListAnchors.length; i++) {
            this.mListAnchors[i].mSolverVariable = linearSystem.createObjectVariable(this.mListAnchors[i]);
        }
        if (this.mBarrierType >= 0 && this.mBarrierType < 4) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[this.mBarrierType];
            int i2 = 0;
            while (true) {
                if (i2 >= this.mWidgetsCount) {
                    z = false;
                    break;
                }
                ConstraintWidget constraintWidget = this.mWidgets[i2];
                if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && (((this.mBarrierType == 0 || this.mBarrierType == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) || ((this.mBarrierType == 2 || this.mBarrierType == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))) {
                    z = true;
                } else {
                    i2++;
                }
            }
            z = true;
            if (this.mBarrierType == 0 || this.mBarrierType == 1 ? getParent().getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT : getParent().getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                z = false;
            }
            for (int i3 = 0; i3 < this.mWidgetsCount; i3++) {
                ConstraintWidget constraintWidget2 = this.mWidgets[i3];
                if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                    SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                    constraintWidget2.mListAnchors[this.mBarrierType].mSolverVariable = createObjectVariable;
                    if (this.mBarrierType == 0 || this.mBarrierType == 2) {
                        linearSystem.addLowerBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                    } else {
                        linearSystem.addGreaterBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                    }
                }
            }
            if (this.mBarrierType == 0) {
                linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 5);
                }
            } else if (this.mBarrierType == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 5);
                }
            } else if (this.mBarrierType == 2) {
                linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 5);
                }
            } else if (this.mBarrierType == 3) {
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 5);
                }
            }
        }
    }
}
