package cn.xports.photo_lib.widget.crop.gestures;

import android.content.Context;
import android.view.MotionEvent;

public class EclairGestureDetector extends CupcakeGestureDetector {
    protected static final int INVALID_POINTER_ID = -1;
    protected int mActivePointerId = -1;
    protected int mActivePointerIndex = 0;

    public EclairGestureDetector(Context context) {
        super(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 3
            r2 = -1
            r3 = 0
            if (r0 == r1) goto L_0x0041
            r1 = 6
            if (r0 == r1) goto L_0x0019
            switch(r0) {
                case 0: goto L_0x0012;
                case 1: goto L_0x0041;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0043
        L_0x0012:
            int r0 = r6.getPointerId(r3)
            r5.mActivePointerId = r0
            goto L_0x0043
        L_0x0019:
            int r0 = r6.getAction()
            int r0 = cn.xports.photo_lib.widget.crop.CropCompat.getPointerIndex(r0)
            int r1 = r6.getPointerId(r0)
            int r4 = r5.mActivePointerId
            if (r1 != r4) goto L_0x0043
            if (r0 != 0) goto L_0x002d
            r0 = 1
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            int r1 = r6.getPointerId(r0)
            r5.mActivePointerId = r1
            float r1 = r6.getX(r0)
            r5.mLastTouchX = r1
            float r0 = r6.getY(r0)
            r5.mLastTouchY = r0
            goto L_0x0043
        L_0x0041:
            r5.mActivePointerId = r2
        L_0x0043:
            int r0 = r5.mActivePointerId
            if (r0 == r2) goto L_0x0049
            int r3 = r5.mActivePointerId
        L_0x0049:
            int r0 = r6.findPointerIndex(r3)
            r5.mActivePointerIndex = r0
            boolean r6 = super.onTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.photo_lib.widget.crop.gestures.EclairGestureDetector.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    public float getActiveX(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    /* access modifiers changed from: package-private */
    public float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }
}
