package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import cn.xports.qd2.entity.K;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    private static boolean isColorType(int i) {
        return i >= 28 && i <= 31;
    }

    public static Animator loadAnimator(Context context, @AnimatorRes int i) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 24) {
            return AnimatorInflater.loadAnimator(context, i);
        }
        return loadAnimator(context, context.getResources(), context.getTheme(), i);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int i) throws Resources.NotFoundException {
        return loadAnimator(context, resources, theme, i, 1.0f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.animation.Animator loadAnimator(android.content.Context r2, android.content.res.Resources r3, android.content.res.Resources.Theme r4, @android.support.annotation.AnimatorRes int r5, float r6) throws android.content.res.Resources.NotFoundException {
        /*
            r0 = 0
            android.content.res.XmlResourceParser r1 = r3.getAnimation(r5)     // Catch:{ XmlPullParserException -> 0x0039, IOException -> 0x001a }
            android.animation.Animator r2 = createAnimatorFromXml(r2, r3, r4, r1, r6)     // Catch:{ XmlPullParserException -> 0x0015, IOException -> 0x0012, all -> 0x000f }
            if (r1 == 0) goto L_0x000e
            r1.close()
        L_0x000e:
            return r2
        L_0x000f:
            r2 = move-exception
            r0 = r1
            goto L_0x0058
        L_0x0012:
            r2 = move-exception
            r0 = r1
            goto L_0x001b
        L_0x0015:
            r2 = move-exception
            r0 = r1
            goto L_0x003a
        L_0x0018:
            r2 = move-exception
            goto L_0x0058
        L_0x001a:
            r2 = move-exception
        L_0x001b:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0018 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
            r4.<init>()     // Catch:{ all -> 0x0018 }
            java.lang.String r6 = "Can't load animation resource ID #0x"
            r4.append(r6)     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = java.lang.Integer.toHexString(r5)     // Catch:{ all -> 0x0018 }
            r4.append(r5)     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0018 }
            r3.<init>(r4)     // Catch:{ all -> 0x0018 }
            r3.initCause(r2)     // Catch:{ all -> 0x0018 }
            throw r3     // Catch:{ all -> 0x0018 }
        L_0x0039:
            r2 = move-exception
        L_0x003a:
            android.content.res.Resources$NotFoundException r3 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x0018 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
            r4.<init>()     // Catch:{ all -> 0x0018 }
            java.lang.String r6 = "Can't load animation resource ID #0x"
            r4.append(r6)     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = java.lang.Integer.toHexString(r5)     // Catch:{ all -> 0x0018 }
            r4.append(r5)     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0018 }
            r3.<init>(r4)     // Catch:{ all -> 0x0018 }
            r3.initCause(r2)     // Catch:{ all -> 0x0018 }
            throw r3     // Catch:{ all -> 0x0018 }
        L_0x0058:
            if (r0 == 0) goto L_0x005d
            r0.close()
        L_0x005d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.loadAnimator(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, int, float):android.animation.Animator");
    }

    private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mNodeArray;

        private PathDataEvaluator() {
        }

        PathDataEvaluator(PathParser.PathDataNode[] pathDataNodeArr) {
            this.mNodeArray = pathDataNodeArr;
        }

        public PathParser.PathDataNode[] evaluate(float f, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
            if (PathParser.canMorph(pathDataNodeArr, pathDataNodeArr2)) {
                if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, pathDataNodeArr)) {
                    this.mNodeArray = PathParser.deepCopyNodes(pathDataNodeArr);
                }
                for (int i = 0; i < pathDataNodeArr.length; i++) {
                    this.mNodeArray[i].interpolatePathDataNode(pathDataNodeArr[i], pathDataNodeArr2[i], f);
                }
                return this.mNodeArray;
            }
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder getPVH(android.content.res.TypedArray r11, int r12, int r13, int r14, java.lang.String r15) {
        /*
            android.util.TypedValue r0 = r11.peekValue(r13)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x000a
            r3 = 1
            goto L_0x000b
        L_0x000a:
            r3 = 0
        L_0x000b:
            if (r3 == 0) goto L_0x0010
            int r0 = r0.type
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            android.util.TypedValue r4 = r11.peekValue(r14)
            if (r4 == 0) goto L_0x0019
            r5 = 1
            goto L_0x001a
        L_0x0019:
            r5 = 0
        L_0x001a:
            if (r5 == 0) goto L_0x001f
            int r4 = r4.type
            goto L_0x0020
        L_0x001f:
            r4 = 0
        L_0x0020:
            r6 = 4
            r7 = 3
            if (r12 != r6) goto L_0x0037
            if (r3 == 0) goto L_0x002c
            boolean r12 = isColorType(r0)
            if (r12 != 0) goto L_0x0034
        L_0x002c:
            if (r5 == 0) goto L_0x0036
            boolean r12 = isColorType(r4)
            if (r12 == 0) goto L_0x0036
        L_0x0034:
            r12 = 3
            goto L_0x0037
        L_0x0036:
            r12 = 0
        L_0x0037:
            if (r12 != 0) goto L_0x003b
            r6 = 1
            goto L_0x003c
        L_0x003b:
            r6 = 0
        L_0x003c:
            r8 = 2
            r9 = 0
            if (r12 != r8) goto L_0x00a9
            java.lang.String r12 = r11.getString(r13)
            java.lang.String r11 = r11.getString(r14)
            android.support.v4.graphics.PathParser$PathDataNode[] r13 = android.support.v4.graphics.PathParser.createNodesFromPathData(r12)
            android.support.v4.graphics.PathParser$PathDataNode[] r14 = android.support.v4.graphics.PathParser.createNodesFromPathData(r11)
            if (r13 != 0) goto L_0x0054
            if (r14 == 0) goto L_0x0167
        L_0x0054:
            if (r13 == 0) goto L_0x0098
            android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator r0 = new android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator
            r0.<init>()
            if (r14 == 0) goto L_0x008d
            boolean r3 = android.support.v4.graphics.PathParser.canMorph(r13, r14)
            if (r3 == 0) goto L_0x006e
            java.lang.Object[] r11 = new java.lang.Object[r8]
            r11[r2] = r13
            r11[r1] = r14
            android.animation.PropertyValuesHolder r11 = android.animation.PropertyValuesHolder.ofObject(r15, r0, r11)
            goto L_0x0095
        L_0x006e:
            android.view.InflateException r13 = new android.view.InflateException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = " Can't morph from "
            r14.append(r15)
            r14.append(r12)
            java.lang.String r12 = " to "
            r14.append(r12)
            r14.append(r11)
            java.lang.String r11 = r14.toString()
            r13.<init>(r11)
            throw r13
        L_0x008d:
            java.lang.Object[] r11 = new java.lang.Object[r1]
            r11[r2] = r13
            android.animation.PropertyValuesHolder r11 = android.animation.PropertyValuesHolder.ofObject(r15, r0, r11)
        L_0x0095:
            r9 = r11
            goto L_0x0167
        L_0x0098:
            if (r14 == 0) goto L_0x0167
            android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator r11 = new android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator
            r11.<init>()
            java.lang.Object[] r12 = new java.lang.Object[r1]
            r12[r2] = r14
            android.animation.PropertyValuesHolder r9 = android.animation.PropertyValuesHolder.ofObject(r15, r11, r12)
            goto L_0x0167
        L_0x00a9:
            if (r12 != r7) goto L_0x00b0
            android.support.graphics.drawable.ArgbEvaluator r12 = android.support.graphics.drawable.ArgbEvaluator.getInstance()
            goto L_0x00b1
        L_0x00b0:
            r12 = r9
        L_0x00b1:
            r7 = 5
            r10 = 0
            if (r6 == 0) goto L_0x00f9
            if (r3 == 0) goto L_0x00e3
            if (r0 != r7) goto L_0x00be
            float r13 = r11.getDimension(r13, r10)
            goto L_0x00c2
        L_0x00be:
            float r13 = r11.getFloat(r13, r10)
        L_0x00c2:
            if (r5 == 0) goto L_0x00da
            if (r4 != r7) goto L_0x00cb
            float r11 = r11.getDimension(r14, r10)
            goto L_0x00cf
        L_0x00cb:
            float r11 = r11.getFloat(r14, r10)
        L_0x00cf:
            float[] r14 = new float[r8]
            r14[r2] = r13
            r14[r1] = r11
            android.animation.PropertyValuesHolder r11 = android.animation.PropertyValuesHolder.ofFloat(r15, r14)
            goto L_0x00f6
        L_0x00da:
            float[] r11 = new float[r1]
            r11[r2] = r13
            android.animation.PropertyValuesHolder r11 = android.animation.PropertyValuesHolder.ofFloat(r15, r11)
            goto L_0x00f6
        L_0x00e3:
            if (r4 != r7) goto L_0x00ea
            float r11 = r11.getDimension(r14, r10)
            goto L_0x00ee
        L_0x00ea:
            float r11 = r11.getFloat(r14, r10)
        L_0x00ee:
            float[] r13 = new float[r1]
            r13[r2] = r11
            android.animation.PropertyValuesHolder r11 = android.animation.PropertyValuesHolder.ofFloat(r15, r13)
        L_0x00f6:
            r9 = r11
            goto L_0x0160
        L_0x00f9:
            if (r3 == 0) goto L_0x013f
            if (r0 != r7) goto L_0x0103
            float r13 = r11.getDimension(r13, r10)
            int r13 = (int) r13
            goto L_0x0112
        L_0x0103:
            boolean r0 = isColorType(r0)
            if (r0 == 0) goto L_0x010e
            int r13 = r11.getColor(r13, r2)
            goto L_0x0112
        L_0x010e:
            int r13 = r11.getInt(r13, r2)
        L_0x0112:
            if (r5 == 0) goto L_0x0136
            if (r4 != r7) goto L_0x011c
            float r11 = r11.getDimension(r14, r10)
            int r11 = (int) r11
            goto L_0x012b
        L_0x011c:
            boolean r0 = isColorType(r4)
            if (r0 == 0) goto L_0x0127
            int r11 = r11.getColor(r14, r2)
            goto L_0x012b
        L_0x0127:
            int r11 = r11.getInt(r14, r2)
        L_0x012b:
            int[] r14 = new int[r8]
            r14[r2] = r13
            r14[r1] = r11
            android.animation.PropertyValuesHolder r9 = android.animation.PropertyValuesHolder.ofInt(r15, r14)
            goto L_0x0160
        L_0x0136:
            int[] r11 = new int[r1]
            r11[r2] = r13
            android.animation.PropertyValuesHolder r9 = android.animation.PropertyValuesHolder.ofInt(r15, r11)
            goto L_0x0160
        L_0x013f:
            if (r5 == 0) goto L_0x0160
            if (r4 != r7) goto L_0x0149
            float r11 = r11.getDimension(r14, r10)
            int r11 = (int) r11
            goto L_0x0158
        L_0x0149:
            boolean r13 = isColorType(r4)
            if (r13 == 0) goto L_0x0154
            int r11 = r11.getColor(r14, r2)
            goto L_0x0158
        L_0x0154:
            int r11 = r11.getInt(r14, r2)
        L_0x0158:
            int[] r13 = new int[r1]
            r13[r2] = r11
            android.animation.PropertyValuesHolder r9 = android.animation.PropertyValuesHolder.ofInt(r15, r13)
        L_0x0160:
            if (r9 == 0) goto L_0x0167
            if (r12 == 0) goto L_0x0167
            r9.setEvaluator(r12)
        L_0x0167:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.getPVH(android.content.res.TypedArray, int, int, int, java.lang.String):android.animation.PropertyValuesHolder");
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f, XmlPullParser xmlPullParser) {
        long namedInt = (long) TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "duration", 1, 300);
        long namedInt2 = (long) TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "startOffset", 2, 0);
        int namedInt3 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, K.valueType, 7, 4);
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "valueFrom") && TypedArrayUtils.hasAttribute(xmlPullParser, "valueTo")) {
            if (namedInt3 == 4) {
                namedInt3 = inferValueTypeFromValues(typedArray, 5, 6);
            }
            PropertyValuesHolder pvh = getPVH(typedArray, namedInt3, 5, 6, "");
            if (pvh != null) {
                valueAnimator.setValues(new PropertyValuesHolder[]{pvh});
            }
        }
        valueAnimator.setDuration(namedInt);
        valueAnimator.setStartDelay(namedInt2);
        valueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            setupObjectAnimator(valueAnimator, typedArray2, namedInt3, f, xmlPullParser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator valueAnimator, TypedArray typedArray, int i, float f, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        String namedString = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 1);
        if (namedString != null) {
            String namedString2 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyXName", 2);
            String namedString3 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyYName", 3);
            if (i != 2) {
            }
            if (namedString2 == null && namedString3 == null) {
                throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            setupPathMotion(PathParser.createPathFromPathData(namedString), objectAnimator, f * 0.5f, namedString2, namedString3);
            return;
        }
        objectAnimator.setPropertyName(TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyName", 0));
    }

    private static void setupPathMotion(Path path, ObjectAnimator objectAnimator, float f, String str, String str2) {
        PropertyValuesHolder propertyValuesHolder;
        Path path2 = path;
        ObjectAnimator objectAnimator2 = objectAnimator;
        String str3 = str;
        String str4 = str2;
        char c2 = 0;
        PathMeasure pathMeasure = new PathMeasure(path2, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(0.0f));
        float f2 = 0.0f;
        do {
            f2 += pathMeasure.getLength();
            arrayList.add(Float.valueOf(f2));
        } while (pathMeasure.nextContour());
        PathMeasure pathMeasure2 = new PathMeasure(path2, false);
        int min = Math.min(100, ((int) (f2 / f)) + 1);
        float[] fArr = new float[min];
        float[] fArr2 = new float[min];
        float[] fArr3 = new float[2];
        float f3 = f2 / ((float) (min - 1));
        int i = 0;
        float f4 = 0.0f;
        int i2 = 0;
        while (true) {
            propertyValuesHolder = null;
            if (i >= min) {
                break;
            }
            pathMeasure2.getPosTan(f4, fArr3, (float[]) null);
            fArr[i] = fArr3[c2];
            fArr2[i] = fArr3[1];
            f4 += f3;
            int i3 = i2 + 1;
            if (i3 < arrayList.size() && f4 > ((Float) arrayList.get(i3)).floatValue()) {
                f4 -= ((Float) arrayList.get(i3)).floatValue();
                pathMeasure2.nextContour();
                i2 = i3;
            }
            i++;
            c2 = 0;
        }
        PropertyValuesHolder ofFloat = str3 != null ? PropertyValuesHolder.ofFloat(str3, fArr) : null;
        if (str4 != null) {
            propertyValuesHolder = PropertyValuesHolder.ofFloat(str4, fArr2);
        }
        if (ofFloat == null) {
            objectAnimator2.setValues(new PropertyValuesHolder[]{propertyValuesHolder});
        } else if (propertyValuesHolder == null) {
            objectAnimator2.setValues(new PropertyValuesHolder[]{ofFloat});
        } else {
            objectAnimator2.setValues(new PropertyValuesHolder[]{ofFloat, propertyValuesHolder});
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, float f) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), (AnimatorSet) null, 0, f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.Animator createAnimatorFromXml(android.content.Context r18, android.content.res.Resources r19, android.content.res.Resources.Theme r20, org.xmlpull.v1.XmlPullParser r21, android.util.AttributeSet r22, android.animation.AnimatorSet r23, int r24, float r25) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r8 = r19
            r9 = r20
            r10 = r21
            r11 = r23
            int r12 = r21.getDepth()
            r0 = 0
            r13 = r0
        L_0x000e:
            int r1 = r21.next()
            r2 = 3
            r14 = 0
            if (r1 != r2) goto L_0x001c
            int r2 = r21.getDepth()
            if (r2 <= r12) goto L_0x00e1
        L_0x001c:
            r2 = 1
            if (r1 == r2) goto L_0x00e1
            r3 = 2
            if (r1 == r3) goto L_0x0023
            goto L_0x000e
        L_0x0023:
            java.lang.String r1 = r21.getName()
            java.lang.String r3 = "objectAnimator"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0043
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r22
            r4 = r25
            r5 = r21
            android.animation.ObjectAnimator r0 = loadObjectAnimator(r0, r1, r2, r3, r4, r5)
        L_0x003f:
            r3 = r18
            goto L_0x00b6
        L_0x0043:
            java.lang.String r3 = "animator"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x005d
            r4 = 0
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r22
            r5 = r25
            r6 = r21
            android.animation.ValueAnimator r0 = loadAnimator(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x003f
        L_0x005d:
            java.lang.String r3 = "set"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0095
            android.animation.AnimatorSet r15 = new android.animation.AnimatorSet
            r15.<init>()
            int[] r0 = android.support.graphics.drawable.AndroidResources.STYLEABLE_ANIMATOR_SET
            r7 = r22
            android.content.res.TypedArray r6 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r8, r9, r7, r0)
            java.lang.String r0 = "ordering"
            int r16 = android.support.v4.content.res.TypedArrayUtils.getNamedInt(r6, r10, r0, r14, r14)
            r5 = r15
            android.animation.AnimatorSet r5 = (android.animation.AnimatorSet) r5
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r17 = r6
            r6 = r16
            r7 = r25
            createAnimatorFromXml(r0, r1, r2, r3, r4, r5, r6, r7)
            r17.recycle()
            r3 = r18
            r0 = r15
            goto L_0x00b6
        L_0x0095:
            java.lang.String r3 = "propertyValuesHolder"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00c6
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r21)
            r3 = r18
            android.animation.PropertyValuesHolder[] r1 = loadValues(r3, r8, r9, r10, r1)
            if (r1 == 0) goto L_0x00b5
            if (r0 == 0) goto L_0x00b5
            boolean r4 = r0 instanceof android.animation.ValueAnimator
            if (r4 == 0) goto L_0x00b5
            r4 = r0
            android.animation.ValueAnimator r4 = (android.animation.ValueAnimator) r4
            r4.setValues(r1)
        L_0x00b5:
            r14 = 1
        L_0x00b6:
            if (r11 == 0) goto L_0x000e
            if (r14 != 0) goto L_0x000e
            if (r13 != 0) goto L_0x00c1
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
        L_0x00c1:
            r13.add(r0)
            goto L_0x000e
        L_0x00c6:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown animator name: "
            r1.append(r2)
            java.lang.String r2 = r21.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00e1:
            if (r11 == 0) goto L_0x010a
            if (r13 == 0) goto L_0x010a
            int r1 = r13.size()
            android.animation.Animator[] r1 = new android.animation.Animator[r1]
            java.util.Iterator r2 = r13.iterator()
        L_0x00ef:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0101
            java.lang.Object r3 = r2.next()
            android.animation.Animator r3 = (android.animation.Animator) r3
            int r4 = r14 + 1
            r1[r14] = r3
            r14 = r4
            goto L_0x00ef
        L_0x0101:
            if (r24 != 0) goto L_0x0107
            r11.playTogether(r1)
            goto L_0x010a
        L_0x0107:
            r11.playSequentially(r1)
        L_0x010a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder[] loadValues(android.content.Context r17, android.content.res.Resources r18, android.content.res.Resources.Theme r19, org.xmlpull.v1.XmlPullParser r20, android.util.AttributeSet r21) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r6 = r20
            r7 = 0
            r8 = r7
        L_0x0004:
            int r0 = r20.getEventType()
            r9 = 0
            r1 = 3
            if (r0 == r1) goto L_0x006c
            r10 = 1
            if (r0 == r10) goto L_0x006c
            r2 = 2
            if (r0 == r2) goto L_0x0016
            r20.next()
            goto L_0x0004
        L_0x0016:
            java.lang.String r0 = r20.getName()
            java.lang.String r3 = "propertyValuesHolder"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0062
            int[] r0 = android.support.graphics.drawable.AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER
            r11 = r18
            r12 = r19
            r13 = r21
            android.content.res.TypedArray r14 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r11, r12, r13, r0)
            java.lang.String r0 = "propertyName"
            java.lang.String r15 = android.support.v4.content.res.TypedArrayUtils.getNamedString(r14, r6, r0, r1)
            java.lang.String r0 = "valueType"
            r1 = 4
            int r5 = android.support.v4.content.res.TypedArrayUtils.getNamedInt(r14, r6, r0, r2, r1)
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r15
            r16 = r5
            android.animation.PropertyValuesHolder r0 = loadPvh(r0, r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0052
            r1 = r16
            android.animation.PropertyValuesHolder r0 = getPVH(r14, r1, r9, r10, r15)
        L_0x0052:
            if (r0 == 0) goto L_0x005e
            if (r8 != 0) goto L_0x005b
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
        L_0x005b:
            r8.add(r0)
        L_0x005e:
            r14.recycle()
            goto L_0x0068
        L_0x0062:
            r11 = r18
            r12 = r19
            r13 = r21
        L_0x0068:
            r20.next()
            goto L_0x0004
        L_0x006c:
            if (r8 == 0) goto L_0x0081
            int r0 = r8.size()
            android.animation.PropertyValuesHolder[] r7 = new android.animation.PropertyValuesHolder[r0]
        L_0x0074:
            if (r9 >= r0) goto L_0x0081
            java.lang.Object r1 = r8.get(r9)
            android.animation.PropertyValuesHolder r1 = (android.animation.PropertyValuesHolder) r1
            r7[r9] = r1
            int r9 = r9 + 1
            goto L_0x0074
        L_0x0081:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.loadValues(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet):android.animation.PropertyValuesHolder[]");
    }

    private static int inferValueTypeOfKeyframe(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        int i = 0;
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(obtainAttributes, xmlPullParser, "value", 0);
        if ((peekNamedValue != null) && isColorType(peekNamedValue.type)) {
            i = 3;
        }
        obtainAttributes.recycle();
        return i;
    }

    private static int inferValueTypeFromValues(TypedArray typedArray, int i, int i2) {
        TypedValue peekValue = typedArray.peekValue(i);
        boolean z = true;
        boolean z2 = peekValue != null;
        int i3 = z2 ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i2);
        if (peekValue2 == null) {
            z = false;
        }
        int i4 = z ? peekValue2.type : 0;
        if ((!z2 || !isColorType(i3)) && (!z || !isColorType(i4))) {
            return 0;
        }
        return 3;
    }

    private static void dumpKeyframes(Object[] objArr, String str) {
        if (objArr != null && objArr.length != 0) {
            Log.d(TAG, str);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                Keyframe keyframe = objArr[i];
                StringBuilder sb = new StringBuilder();
                sb.append("Keyframe ");
                sb.append(i);
                sb.append(": fraction ");
                sb.append(keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction()));
                sb.append(", ");
                sb.append(", value : ");
                sb.append(keyframe.hasValue() ? keyframe.getValue() : "null");
                Log.d(TAG, sb.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder loadPvh(android.content.Context r9, android.content.res.Resources r10, android.content.res.Resources.Theme r11, org.xmlpull.v1.XmlPullParser r12, java.lang.String r13, int r14) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r0 = 0
            r1 = r14
            r14 = r0
        L_0x0003:
            int r2 = r12.next()
            r3 = 3
            if (r2 == r3) goto L_0x0041
            r4 = 1
            if (r2 == r4) goto L_0x0041
            java.lang.String r2 = r12.getName()
            java.lang.String r3 = "keyframe"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0003
            r2 = 4
            if (r1 != r2) goto L_0x0024
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r12)
            int r1 = inferValueTypeOfKeyframe(r10, r11, r1, r12)
        L_0x0024:
            android.util.AttributeSet r5 = android.util.Xml.asAttributeSet(r12)
            r2 = r9
            r3 = r10
            r4 = r11
            r6 = r1
            r7 = r12
            android.animation.Keyframe r2 = loadKeyframe(r2, r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x003d
            if (r14 != 0) goto L_0x003a
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
        L_0x003a:
            r14.add(r2)
        L_0x003d:
            r12.next()
            goto L_0x0003
        L_0x0041:
            if (r14 == 0) goto L_0x00ea
            int r9 = r14.size()
            if (r9 <= 0) goto L_0x00ea
            r10 = 0
            java.lang.Object r11 = r14.get(r10)
            android.animation.Keyframe r11 = (android.animation.Keyframe) r11
            int r12 = r9 + -1
            java.lang.Object r12 = r14.get(r12)
            android.animation.Keyframe r12 = (android.animation.Keyframe) r12
            float r0 = r12.getFraction()
            r2 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x0078
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x006b
            r12.setFraction(r2)
            goto L_0x0078
        L_0x006b:
            int r0 = r14.size()
            android.animation.Keyframe r12 = createNewKeyframe(r12, r2)
            r14.add(r0, r12)
            int r9 = r9 + 1
        L_0x0078:
            float r12 = r11.getFraction()
            int r0 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0091
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 >= 0) goto L_0x0088
            r11.setFraction(r4)
            goto L_0x0091
        L_0x0088:
            android.animation.Keyframe r11 = createNewKeyframe(r11, r4)
            r14.add(r10, r11)
            int r9 = r9 + 1
        L_0x0091:
            android.animation.Keyframe[] r11 = new android.animation.Keyframe[r9]
            r14.toArray(r11)
        L_0x0096:
            if (r10 >= r9) goto L_0x00dd
            r12 = r11[r10]
            float r14 = r12.getFraction()
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 >= 0) goto L_0x00da
            if (r10 != 0) goto L_0x00a8
            r12.setFraction(r4)
            goto L_0x00da
        L_0x00a8:
            int r14 = r9 + -1
            if (r10 != r14) goto L_0x00b0
            r12.setFraction(r2)
            goto L_0x00da
        L_0x00b0:
            int r12 = r10 + 1
            r0 = r10
        L_0x00b3:
            if (r12 >= r14) goto L_0x00c6
            r5 = r11[r12]
            float r5 = r5.getFraction()
            int r5 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r5 < 0) goto L_0x00c0
            goto L_0x00c6
        L_0x00c0:
            int r0 = r12 + 1
            r8 = r0
            r0 = r12
            r12 = r8
            goto L_0x00b3
        L_0x00c6:
            int r12 = r0 + 1
            r12 = r11[r12]
            float r12 = r12.getFraction()
            int r14 = r10 + -1
            r14 = r11[r14]
            float r14 = r14.getFraction()
            float r12 = r12 - r14
            distributeKeyframes(r11, r12, r10, r0)
        L_0x00da:
            int r10 = r10 + 1
            goto L_0x0096
        L_0x00dd:
            android.animation.PropertyValuesHolder r0 = android.animation.PropertyValuesHolder.ofKeyframe(r13, r11)
            if (r1 != r3) goto L_0x00ea
            android.support.graphics.drawable.ArgbEvaluator r9 = android.support.graphics.drawable.ArgbEvaluator.getInstance()
            r0.setEvaluator(r9)
        L_0x00ea:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.loadPvh(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, java.lang.String, int):android.animation.PropertyValuesHolder");
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f);
        }
        if (keyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(f);
        }
        return Keyframe.ofObject(f);
    }

    private static void distributeKeyframes(Keyframe[] keyframeArr, float f, int i, int i2) {
        float f2 = f / ((float) ((i2 - i) + 2));
        while (i <= i2) {
            keyframeArr[i].setFraction(keyframeArr[i - 1].getFraction() + f2);
            i++;
        }
    }

    private static Keyframe loadKeyframe(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, int i, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Keyframe keyframe;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(obtainAttributes, xmlPullParser, "value", 0);
        boolean z = peekNamedValue != null;
        if (i == 4) {
            i = (!z || !isColorType(peekNamedValue.type)) ? 0 : 3;
        }
        if (z) {
            if (i != 3) {
                switch (i) {
                    case 0:
                        keyframe = Keyframe.ofFloat(namedFloat, TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "value", 0, 0.0f));
                        break;
                    case 1:
                        break;
                    default:
                        keyframe = null;
                        break;
                }
            }
            keyframe = Keyframe.ofInt(namedFloat, TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "value", 0, 0));
        } else if (i == 0) {
            keyframe = Keyframe.ofFloat(namedFloat);
        } else {
            keyframe = Keyframe.ofInt(namedFloat);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 1, 0);
        if (namedResourceId > 0) {
            keyframe.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, namedResourceId));
        }
        obtainAttributes.recycle();
        return keyframe;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, float f, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(context, resources, theme, attributeSet, objectAnimator, f, xmlPullParser);
        return objectAnimator;
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray obtainAttributes2 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(valueAnimator, obtainAttributes, obtainAttributes2, f, xmlPullParser);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            valueAnimator.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, namedResourceId));
        }
        obtainAttributes.recycle();
        if (obtainAttributes2 != null) {
            obtainAttributes2.recycle();
        }
        return valueAnimator;
    }
}
