package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PathParser {
    private static final String LOGTAG = "PathParser";

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int length = fArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(createNodesFromPathData, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + str, e);
        }
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int nextStart = nextStart(str, i);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i = nextStart + 1;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            if (pathDataNodeArr[i].mType != pathDataNodeArr2[i].mType || pathDataNodeArr[i].mParams.length != pathDataNodeArr2[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].mType = pathDataNodeArr2[i].mType;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].mParams.length; i2++) {
                pathDataNodeArr[i].mParams[i2] = pathDataNodeArr2[i].mParams[i2];
            }
        }
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c2, float[] fArr) {
        arrayList.add(new PathDataNode(c2, fArr));
    }

    private static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i = 1;
            int i2 = 0;
            while (i < length) {
                extract(str, i, extractFloatResult);
                int i3 = extractFloatResult.mEndPosition;
                if (i < i3) {
                    fArr[i2] = Float.parseFloat(str.substring(i, i3));
                    i2++;
                }
                i = extractFloatResult.mEndWithNegOrDot ? i3 : i3 + 1;
            }
            return copyOfRange(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003a A[LOOP:0: B:1:0x0007->B:20:0x003a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extract(java.lang.String r8, int r9, android.support.v4.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0007:
            int r5 = r8.length()
            if (r1 >= r5) goto L_0x003d
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L_0x0035
            r6 = 69
            if (r5 == r6) goto L_0x0033
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x0033
            switch(r5) {
                case 44: goto L_0x0035;
                case 45: goto L_0x002a;
                case 46: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0031
        L_0x0022:
            if (r3 != 0) goto L_0x0027
            r2 = 0
            r3 = 1
            goto L_0x0037
        L_0x0027:
            r10.mEndWithNegOrDot = r7
            goto L_0x0035
        L_0x002a:
            if (r1 == r9) goto L_0x0031
            if (r2 != 0) goto L_0x0031
            r10.mEndWithNegOrDot = r7
            goto L_0x0035
        L_0x0031:
            r2 = 0
            goto L_0x0037
        L_0x0033:
            r2 = 1
            goto L_0x0037
        L_0x0035:
            r2 = 0
            r4 = 1
        L_0x0037:
            if (r4 == 0) goto L_0x003a
            goto L_0x003d
        L_0x003a:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x003d:
            r10.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.extract(java.lang.String, int, android.support.v4.graphics.PathParser$ExtractFloatResult):void");
    }

    public static class PathDataNode {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public float[] mParams;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public char mType;

        PathDataNode(char c2, float[] fArr) {
            this.mType = c2;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            this.mParams = PathParser.copyOfRange(pathDataNode.mParams, 0, pathDataNode.mParams.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c2 = 'm';
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                addCommand(path, fArr, c2, pathDataNodeArr[i].mType, pathDataNodeArr[i].mParams);
                c2 = pathDataNodeArr[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            for (int i = 0; i < pathDataNode.mParams.length; i++) {
                this.mParams[i] = (pathDataNode.mParams[i] * (1.0f - f)) + (pathDataNode2.mParams[i] * f);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
            r25 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x014f, code lost:
            r2 = r0;
            r3 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0232, code lost:
            r2 = r0;
            r3 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x02e6, code lost:
            r3 = r7;
            r2 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x02e8, code lost:
            r9 = r25 + r20;
            r0 = r30;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void addCommand(android.graphics.Path r27, float[] r28, char r29, char r30, float[] r31) {
            /*
                r10 = r27
                r13 = r31
                r14 = 0
                r0 = r28[r14]
                r15 = 1
                r1 = r28[r15]
                r16 = 2
                r2 = r28[r16]
                r17 = 3
                r3 = r28[r17]
                r18 = 4
                r4 = r28[r18]
                r19 = 5
                r5 = r28[r19]
                switch(r30) {
                    case 65: goto L_0x0035;
                    case 67: goto L_0x0031;
                    case 72: goto L_0x002e;
                    case 76: goto L_0x001d;
                    case 77: goto L_0x001d;
                    case 81: goto L_0x002b;
                    case 83: goto L_0x002b;
                    case 84: goto L_0x001d;
                    case 86: goto L_0x002e;
                    case 90: goto L_0x0020;
                    case 97: goto L_0x0035;
                    case 99: goto L_0x0031;
                    case 104: goto L_0x002e;
                    case 108: goto L_0x001d;
                    case 109: goto L_0x001d;
                    case 113: goto L_0x002b;
                    case 115: goto L_0x002b;
                    case 116: goto L_0x001d;
                    case 118: goto L_0x002e;
                    case 122: goto L_0x0020;
                    default: goto L_0x001d;
                }
            L_0x001d:
                r20 = 2
                goto L_0x0038
            L_0x0020:
                r27.close()
                r10.moveTo(r4, r5)
                r0 = r4
                r2 = r0
                r1 = r5
                r3 = r1
                goto L_0x001d
            L_0x002b:
                r20 = 4
                goto L_0x0038
            L_0x002e:
                r20 = 1
                goto L_0x0038
            L_0x0031:
                r6 = 6
                r20 = 6
                goto L_0x0038
            L_0x0035:
                r6 = 7
                r20 = 7
            L_0x0038:
                r8 = r0
                r7 = r1
                r21 = r4
                r22 = r5
                r9 = 0
                r0 = r29
            L_0x0041:
                int r1 = r13.length
                if (r9 >= r1) goto L_0x02f0
                r1 = 81
                r5 = 116(0x74, float:1.63E-43)
                r6 = 115(0x73, float:1.61E-43)
                r15 = 113(0x71, float:1.58E-43)
                r14 = 99
                r23 = 1073741824(0x40000000, float:2.0)
                r4 = 0
                switch(r30) {
                    case 65: goto L_0x02a4;
                    case 67: goto L_0x0279;
                    case 72: goto L_0x026b;
                    case 76: goto L_0x0258;
                    case 77: goto L_0x0236;
                    case 81: goto L_0x0215;
                    case 83: goto L_0x01db;
                    case 84: goto L_0x01b2;
                    case 86: goto L_0x01a4;
                    case 97: goto L_0x0153;
                    case 99: goto L_0x0126;
                    case 104: goto L_0x011a;
                    case 108: goto L_0x0107;
                    case 109: goto L_0x00e5;
                    case 113: goto L_0x00c5;
                    case 115: goto L_0x008c;
                    case 116: goto L_0x0065;
                    case 118: goto L_0x005a;
                    default: goto L_0x0054;
                }
            L_0x0054:
                r12 = r7
                r11 = r8
            L_0x0056:
                r25 = r9
                goto L_0x02e8
            L_0x005a:
                int r0 = r9 + 0
                r1 = r13[r0]
                r10.rLineTo(r4, r1)
                r0 = r13[r0]
                float r7 = r7 + r0
                goto L_0x0056
            L_0x0065:
                if (r0 == r15) goto L_0x0072
                if (r0 == r5) goto L_0x0072
                if (r0 == r1) goto L_0x0072
                r1 = 84
                if (r0 != r1) goto L_0x0070
                goto L_0x0072
            L_0x0070:
                r0 = 0
                goto L_0x0076
            L_0x0072:
                float r4 = r8 - r2
                float r0 = r7 - r3
            L_0x0076:
                int r1 = r9 + 0
                r2 = r13[r1]
                int r3 = r9 + 1
                r5 = r13[r3]
                r10.rQuadTo(r4, r0, r2, r5)
                float r4 = r4 + r8
                float r0 = r0 + r7
                r1 = r13[r1]
                float r8 = r8 + r1
                r1 = r13[r3]
                float r7 = r7 + r1
                r3 = r0
                r2 = r4
                goto L_0x0056
            L_0x008c:
                if (r0 == r14) goto L_0x009c
                if (r0 == r6) goto L_0x009c
                r1 = 67
                if (r0 == r1) goto L_0x009c
                r1 = 83
                if (r0 != r1) goto L_0x0099
                goto L_0x009c
            L_0x0099:
                r1 = 0
                r2 = 0
                goto L_0x00a2
            L_0x009c:
                float r0 = r8 - r2
                float r1 = r7 - r3
                r2 = r1
                r1 = r0
            L_0x00a2:
                int r14 = r9 + 0
                r3 = r13[r14]
                int r15 = r9 + 1
                r4 = r13[r15]
                int r23 = r9 + 2
                r5 = r13[r23]
                int r24 = r9 + 3
                r6 = r13[r24]
                r0 = r27
                r0.rCubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r13[r14]
                float r0 = r0 + r8
                r1 = r13[r15]
                float r1 = r1 + r7
                r2 = r13[r23]
                float r8 = r8 + r2
                r2 = r13[r24]
                float r7 = r7 + r2
                goto L_0x014f
            L_0x00c5:
                int r0 = r9 + 0
                r1 = r13[r0]
                int r2 = r9 + 1
                r3 = r13[r2]
                int r4 = r9 + 2
                r5 = r13[r4]
                int r6 = r9 + 3
                r14 = r13[r6]
                r10.rQuadTo(r1, r3, r5, r14)
                r0 = r13[r0]
                float r0 = r0 + r8
                r1 = r13[r2]
                float r1 = r1 + r7
                r2 = r13[r4]
                float r8 = r8 + r2
                r2 = r13[r6]
                float r7 = r7 + r2
                goto L_0x014f
            L_0x00e5:
                int r0 = r9 + 0
                r1 = r13[r0]
                float r8 = r8 + r1
                int r1 = r9 + 1
                r4 = r13[r1]
                float r7 = r7 + r4
                if (r9 <= 0) goto L_0x00fa
                r0 = r13[r0]
                r1 = r13[r1]
                r10.rLineTo(r0, r1)
                goto L_0x0056
            L_0x00fa:
                r0 = r13[r0]
                r1 = r13[r1]
                r10.rMoveTo(r0, r1)
                r22 = r7
                r21 = r8
                goto L_0x0056
            L_0x0107:
                int r0 = r9 + 0
                r1 = r13[r0]
                int r4 = r9 + 1
                r5 = r13[r4]
                r10.rLineTo(r1, r5)
                r0 = r13[r0]
                float r8 = r8 + r0
                r0 = r13[r4]
                float r7 = r7 + r0
                goto L_0x0056
            L_0x011a:
                int r0 = r9 + 0
                r1 = r13[r0]
                r10.rLineTo(r1, r4)
                r0 = r13[r0]
                float r8 = r8 + r0
                goto L_0x0056
            L_0x0126:
                int r0 = r9 + 0
                r1 = r13[r0]
                int r0 = r9 + 1
                r2 = r13[r0]
                int r14 = r9 + 2
                r3 = r13[r14]
                int r15 = r9 + 3
                r4 = r13[r15]
                int r23 = r9 + 4
                r5 = r13[r23]
                int r24 = r9 + 5
                r6 = r13[r24]
                r0 = r27
                r0.rCubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r13[r14]
                float r0 = r0 + r8
                r1 = r13[r15]
                float r1 = r1 + r7
                r2 = r13[r23]
                float r8 = r8 + r2
                r2 = r13[r24]
                float r7 = r7 + r2
            L_0x014f:
                r2 = r0
                r3 = r1
                goto L_0x0056
            L_0x0153:
                int r14 = r9 + 5
                r0 = r13[r14]
                float r3 = r0 + r8
                int r15 = r9 + 6
                r0 = r13[r15]
                float r5 = r0 + r7
                int r0 = r9 + 0
                r6 = r13[r0]
                int r0 = r9 + 1
                r23 = r13[r0]
                int r0 = r9 + 2
                r24 = r13[r0]
                int r0 = r9 + 3
                r0 = r13[r0]
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 == 0) goto L_0x0176
                r25 = 1
                goto L_0x0178
            L_0x0176:
                r25 = 0
            L_0x0178:
                int r0 = r9 + 4
                r0 = r13[r0]
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 == 0) goto L_0x0183
                r26 = 1
                goto L_0x0185
            L_0x0183:
                r26 = 0
            L_0x0185:
                r0 = r27
                r1 = r8
                r2 = r7
                r4 = r5
                r5 = r6
                r6 = r23
                r12 = r7
                r7 = r24
                r11 = r8
                r8 = r25
                r25 = r9
                r9 = r26
                drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r0 = r13[r14]
                float r8 = r11 + r0
                r0 = r13[r15]
                float r7 = r12 + r0
                goto L_0x02e6
            L_0x01a4:
                r11 = r8
                r25 = r9
                int r9 = r25 + 0
                r0 = r13[r9]
                r10.lineTo(r11, r0)
                r7 = r13[r9]
                goto L_0x02e8
            L_0x01b2:
                r12 = r7
                r11 = r8
                r25 = r9
                if (r0 == r15) goto L_0x01c0
                if (r0 == r5) goto L_0x01c0
                if (r0 == r1) goto L_0x01c0
                r1 = 84
                if (r0 != r1) goto L_0x01c8
            L_0x01c0:
                float r8 = r11 * r23
                float r8 = r8 - r2
                float r7 = r12 * r23
                float r7 = r7 - r3
                r12 = r7
                r11 = r8
            L_0x01c8:
                int r9 = r25 + 0
                r0 = r13[r9]
                int r1 = r25 + 1
                r2 = r13[r1]
                r10.quadTo(r11, r12, r0, r2)
                r8 = r13[r9]
                r7 = r13[r1]
                r2 = r11
                r3 = r12
                goto L_0x02e8
            L_0x01db:
                r12 = r7
                r11 = r8
                r25 = r9
                if (r0 == r14) goto L_0x01ef
                if (r0 == r6) goto L_0x01ef
                r1 = 67
                if (r0 == r1) goto L_0x01ef
                r1 = 83
                if (r0 != r1) goto L_0x01ec
                goto L_0x01ef
            L_0x01ec:
                r1 = r11
                r2 = r12
                goto L_0x01f7
            L_0x01ef:
                float r8 = r11 * r23
                float r8 = r8 - r2
                float r7 = r12 * r23
                float r7 = r7 - r3
                r2 = r7
                r1 = r8
            L_0x01f7:
                int r9 = r25 + 0
                r3 = r13[r9]
                int r7 = r25 + 1
                r4 = r13[r7]
                int r8 = r25 + 2
                r5 = r13[r8]
                int r11 = r25 + 3
                r6 = r13[r11]
                r0 = r27
                r0.cubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r13[r9]
                r1 = r13[r7]
                r8 = r13[r8]
                r7 = r13[r11]
                goto L_0x0232
            L_0x0215:
                r25 = r9
                int r9 = r25 + 0
                r0 = r13[r9]
                int r1 = r25 + 1
                r2 = r13[r1]
                int r3 = r25 + 2
                r4 = r13[r3]
                int r5 = r25 + 3
                r6 = r13[r5]
                r10.quadTo(r0, r2, r4, r6)
                r0 = r13[r9]
                r1 = r13[r1]
                r8 = r13[r3]
                r7 = r13[r5]
            L_0x0232:
                r2 = r0
                r3 = r1
                goto L_0x02e8
            L_0x0236:
                r25 = r9
                int r9 = r25 + 0
                r8 = r13[r9]
                int r0 = r25 + 1
                r7 = r13[r0]
                if (r25 <= 0) goto L_0x024b
                r1 = r13[r9]
                r0 = r13[r0]
                r10.lineTo(r1, r0)
                goto L_0x02e8
            L_0x024b:
                r1 = r13[r9]
                r0 = r13[r0]
                r10.moveTo(r1, r0)
                r22 = r7
                r21 = r8
                goto L_0x02e8
            L_0x0258:
                r25 = r9
                int r9 = r25 + 0
                r0 = r13[r9]
                int r1 = r25 + 1
                r4 = r13[r1]
                r10.lineTo(r0, r4)
                r8 = r13[r9]
                r7 = r13[r1]
                goto L_0x02e8
            L_0x026b:
                r12 = r7
                r25 = r9
                int r9 = r25 + 0
                r0 = r13[r9]
                r10.lineTo(r0, r12)
                r8 = r13[r9]
                goto L_0x02e8
            L_0x0279:
                r25 = r9
                int r9 = r25 + 0
                r1 = r13[r9]
                int r9 = r25 + 1
                r2 = r13[r9]
                int r9 = r25 + 2
                r3 = r13[r9]
                int r7 = r25 + 3
                r4 = r13[r7]
                int r8 = r25 + 4
                r5 = r13[r8]
                int r11 = r25 + 5
                r6 = r13[r11]
                r0 = r27
                r0.cubicTo(r1, r2, r3, r4, r5, r6)
                r8 = r13[r8]
                r0 = r13[r11]
                r1 = r13[r9]
                r2 = r13[r7]
                r7 = r0
                r3 = r2
                r2 = r1
                goto L_0x02e8
            L_0x02a4:
                r12 = r7
                r11 = r8
                r25 = r9
                int r14 = r25 + 5
                r3 = r13[r14]
                int r15 = r25 + 6
                r5 = r13[r15]
                int r9 = r25 + 0
                r6 = r13[r9]
                int r9 = r25 + 1
                r7 = r13[r9]
                int r9 = r25 + 2
                r8 = r13[r9]
                int r9 = r25 + 3
                r0 = r13[r9]
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 == 0) goto L_0x02c6
                r9 = 1
                goto L_0x02c7
            L_0x02c6:
                r9 = 0
            L_0x02c7:
                int r0 = r25 + 4
                r0 = r13[r0]
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 == 0) goto L_0x02d2
                r23 = 1
                goto L_0x02d4
            L_0x02d2:
                r23 = 0
            L_0x02d4:
                r0 = r27
                r1 = r11
                r2 = r12
                r4 = r5
                r5 = r6
                r6 = r7
                r7 = r8
                r8 = r9
                r9 = r23
                drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r8 = r13[r14]
                r7 = r13[r15]
            L_0x02e6:
                r3 = r7
                r2 = r8
            L_0x02e8:
                int r9 = r25 + r20
                r0 = r30
                r14 = 0
                r15 = 1
                goto L_0x0041
            L_0x02f0:
                r12 = r7
                r1 = 0
                r28[r1] = r8
                r1 = 1
                r28[r1] = r12
                r28[r16] = r2
                r28[r17] = r3
                r28[r18] = r21
                r28[r19] = r22
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.PathDataNode.addCommand(android.graphics.Path, float[], char, char, float[]):void");
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            float f8 = f;
            float f9 = f3;
            float f10 = f5;
            boolean z3 = z2;
            double radians = Math.toRadians((double) f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = (double) f8;
            Double.isNaN(d3);
            double d4 = d3 * cos;
            double d5 = d3;
            double d6 = (double) f2;
            Double.isNaN(d6);
            double d7 = (double) f10;
            Double.isNaN(d7);
            double d8 = (d4 + (d6 * sin)) / d7;
            double d9 = (double) (-f8);
            Double.isNaN(d9);
            Double.isNaN(d6);
            double d10 = d6;
            double d11 = (double) f6;
            Double.isNaN(d11);
            double d12 = (double) f9;
            Double.isNaN(d12);
            double d13 = ((d9 * sin) + (d6 * cos)) / d11;
            double d14 = (double) f4;
            Double.isNaN(d14);
            Double.isNaN(d7);
            double d15 = ((d12 * cos) + (d14 * sin)) / d7;
            double d16 = d7;
            double d17 = (double) (-f9);
            Double.isNaN(d17);
            Double.isNaN(d14);
            Double.isNaN(d11);
            double d18 = ((d17 * sin) + (d14 * cos)) / d11;
            double d19 = d8 - d15;
            double d20 = d13 - d18;
            double d21 = (d8 + d15) / 2.0d;
            double d22 = (d13 + d18) / 2.0d;
            double d23 = sin;
            double d24 = (d19 * d19) + (d20 * d20);
            if (d24 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d25 = (1.0d / d24) - 0.25d;
            if (d25 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d24);
                float sqrt = (float) (Math.sqrt(d24) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f10 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d25);
            double d26 = d19 * sqrt2;
            double d27 = sqrt2 * d20;
            boolean z4 = z2;
            if (z == z4) {
                d2 = d21 - d27;
                d = d22 + d26;
            } else {
                d2 = d21 + d27;
                d = d22 - d26;
            }
            double atan2 = Math.atan2(d13 - d, d8 - d2);
            double atan22 = Math.atan2(d18 - d, d15 - d2) - atan2;
            if (z4 != (atan22 >= 0.0d)) {
                atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            Double.isNaN(d16);
            double d28 = d2 * d16;
            Double.isNaN(d11);
            double d29 = d * d11;
            arcToBezier(path, (d28 * cos) - (d29 * d23), (d28 * d23) + (d29 * cos), d16, d11, d5, d10, radians, atan2, atan22);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            double d14 = d11 * sin;
            double d15 = d4 * cos;
            double d16 = (sin2 * d14) + (cos2 * d15);
            double d17 = (double) ceil;
            Double.isNaN(d17);
            double d18 = d9 / d17;
            int i = 0;
            double d19 = d6;
            double d20 = d16;
            double d21 = (d12 * sin2) - (d13 * cos2);
            double d22 = d5;
            double d23 = d8;
            while (i < ceil) {
                double d24 = d23 + d18;
                double sin3 = Math.sin(d24);
                double cos3 = Math.cos(d24);
                double d25 = d18;
                double d26 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                double d27 = d2 + (d10 * sin * cos3) + (d15 * sin3);
                double d28 = (d12 * sin3) - (d13 * cos3);
                double d29 = (sin3 * d14) + (cos3 * d15);
                double d30 = d24 - d23;
                double tan = Math.tan(d30 / 2.0d);
                double sin4 = (Math.sin(d30) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                double d31 = d15;
                double d32 = d14;
                path.rLineTo(0.0f, 0.0f);
                float f = (float) (d19 + (d20 * sin4));
                float f2 = (float) (d26 - (sin4 * d28));
                path.cubicTo((float) (d22 + (d21 * sin4)), f, f2, (float) (d27 - (sin4 * d29)), (float) d26, (float) d27);
                i++;
                d19 = d27;
                d22 = d26;
                d23 = d24;
                d20 = d29;
                d21 = d28;
                d18 = d25;
                d15 = d31;
                d14 = d32;
                ceil = ceil;
                cos = cos;
                sin = sin;
                d10 = d3;
            }
        }
    }
}
