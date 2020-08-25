package cn.xports.photo_lib.widget.crop.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class CupcakeGestureDetector implements GestureDetector {
    protected static final String TAG = "CupcakeGestureDetector";
    protected boolean mIsDragging;
    protected float mLastTouchX;
    protected float mLastTouchY;
    protected OnGestureListener mListener;
    protected final float mMinimumVelocity;
    protected final float mTouchSlop;
    protected VelocityTracker mVelocityTracker;

    public boolean isScaling() {
        return false;
    }

    public CupcakeGestureDetector(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = (float) viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getAction()) {
            case 0:
                this.mVelocityTracker = VelocityTracker.obtain();
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.addMovement(motionEvent);
                }
                this.mLastTouchX = getActiveX(motionEvent);
                this.mLastTouchY = getActiveY(motionEvent);
                this.mIsDragging = false;
                break;
            case 1:
                if (this.mIsDragging && this.mVelocityTracker != null) {
                    this.mLastTouchX = getActiveX(motionEvent);
                    this.mLastTouchY = getActiveY(motionEvent);
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = this.mVelocityTracker.getXVelocity();
                    float yVelocity = this.mVelocityTracker.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.mMinimumVelocity) {
                        this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -xVelocity, -yVelocity);
                    }
                }
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            case 2:
                float activeX = getActiveX(motionEvent);
                float activeY = getActiveY(motionEvent);
                float f = activeX - this.mLastTouchX;
                float f2 = activeY - this.mLastTouchY;
                if (!this.mIsDragging) {
                    if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.mTouchSlop)) {
                        z = true;
                    }
                    this.mIsDragging = z;
                }
                if (this.mIsDragging) {
                    this.mListener.onDrag(f, f2);
                    this.mLastTouchX = activeX;
                    this.mLastTouchY = activeY;
                    if (this.mVelocityTracker != null) {
                        this.mVelocityTracker.addMovement(motionEvent);
                        break;
                    }
                }
                break;
            case 3:
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
        }
        return true;
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public void setOnGestureListener(OnGestureListener onGestureListener) {
        this.mListener = onGestureListener;
    }

    /* access modifiers changed from: package-private */
    public float getActiveX(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    /* access modifiers changed from: package-private */
    public float getActiveY(MotionEvent motionEvent) {
        return motionEvent.getY();
    }
}
