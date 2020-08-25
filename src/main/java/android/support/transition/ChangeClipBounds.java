package android.support.transition;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class ChangeClipBounds extends Transition {
    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String[] sTransitionProperties = {PROPNAME_CLIP};

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() != 8) {
            Rect clipBounds = ViewCompat.getClipBounds(view);
            transitionValues.values.put(PROPNAME_CLIP, clipBounds);
            if (clipBounds == null) {
                transitionValues.values.put(PROPNAME_BOUNDS, new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.graphics.Rect} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.graphics.Rect} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator createAnimator(@android.support.annotation.NonNull android.view.ViewGroup r8, android.support.transition.TransitionValues r9, android.support.transition.TransitionValues r10) {
        /*
            r7 = this;
            r8 = 0
            if (r9 == 0) goto L_0x0087
            if (r10 == 0) goto L_0x0087
            java.util.Map<java.lang.String, java.lang.Object> r0 = r9.values
            java.lang.String r1 = "android:clipBounds:clip"
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x0087
            java.util.Map<java.lang.String, java.lang.Object> r0 = r10.values
            java.lang.String r1 = "android:clipBounds:clip"
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x001a
            goto L_0x0087
        L_0x001a:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r9.values
            java.lang.String r1 = "android:clipBounds:clip"
            java.lang.Object r0 = r0.get(r1)
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            java.util.Map<java.lang.String, java.lang.Object> r1 = r10.values
            java.lang.String r2 = "android:clipBounds:clip"
            java.lang.Object r1 = r1.get(r2)
            android.graphics.Rect r1 = (android.graphics.Rect) r1
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = 0
        L_0x0035:
            if (r0 != 0) goto L_0x003a
            if (r1 != 0) goto L_0x003a
            return r8
        L_0x003a:
            if (r0 != 0) goto L_0x0048
            java.util.Map<java.lang.String, java.lang.Object> r9 = r9.values
            java.lang.String r0 = "android:clipBounds:bounds"
            java.lang.Object r9 = r9.get(r0)
            r0 = r9
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            goto L_0x0055
        L_0x0048:
            if (r1 != 0) goto L_0x0055
            java.util.Map<java.lang.String, java.lang.Object> r9 = r10.values
            java.lang.String r1 = "android:clipBounds:bounds"
            java.lang.Object r9 = r9.get(r1)
            r1 = r9
            android.graphics.Rect r1 = (android.graphics.Rect) r1
        L_0x0055:
            boolean r9 = r0.equals(r1)
            if (r9 == 0) goto L_0x005c
            return r8
        L_0x005c:
            android.view.View r8 = r10.view
            android.support.v4.view.ViewCompat.setClipBounds(r8, r0)
            android.support.transition.RectEvaluator r8 = new android.support.transition.RectEvaluator
            android.graphics.Rect r9 = new android.graphics.Rect
            r9.<init>()
            r8.<init>(r9)
            android.view.View r9 = r10.view
            android.util.Property<android.view.View, android.graphics.Rect> r5 = android.support.transition.ViewUtils.CLIP_BOUNDS
            r6 = 2
            android.graphics.Rect[] r6 = new android.graphics.Rect[r6]
            r6[r2] = r0
            r6[r3] = r1
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofObject(r9, r5, r8, r6)
            if (r4 == 0) goto L_0x0086
            android.view.View r9 = r10.view
            android.support.transition.ChangeClipBounds$1 r10 = new android.support.transition.ChangeClipBounds$1
            r10.<init>(r9)
            r8.addListener(r10)
        L_0x0086:
            return r8
        L_0x0087:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ChangeClipBounds.createAnimator(android.view.ViewGroup, android.support.transition.TransitionValues, android.support.transition.TransitionValues):android.animation.Animator");
    }
}
