package cn.xports.photo_lib.widget.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import cn.xports.photo_lib.widget.crop.gestures.GestureDetector;
import cn.xports.photo_lib.widget.crop.gestures.OnGestureListener;
import cn.xports.photo_lib.widget.crop.gestures.VersionedGestureDetector;
import cn.xports.photo_lib.widget.crop.scrollerproxy.ScrollerProxy;

public class CropView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener, OnGestureListener {
    private static final float DEFAULT_MAX_SCALE = 6.0f;
    private static final float DEFAULT_MID_SCALE = 3.0f;
    private static final float DEFAULT_MIN_SCALE = 1.0f;
    private static final long DEFAULT_ZOOM_DURATION = 200;
    private static final float OUTLINE_DP = 2.0f;
    private int highlightColor;
    private int mAspectX;
    private int mAspectY;
    private final Matrix mBaseMatrix;
    private RotateBitmap mBitmapDisplayed;
    /* access modifiers changed from: private */
    public RectF mCropRect;
    private FlingRunnable mCurrentFlingRunnable;
    private final RectF mDisplayRect;
    private GestureDetector mDragScaleDetector;
    private final Matrix mDrawMatrix;
    private android.view.GestureDetector mGestureDetector;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private final float[] mMatrixValues;
    /* access modifiers changed from: private */
    public float mMaxScale;
    /* access modifiers changed from: private */
    public float mMidScale;
    /* access modifiers changed from: private */
    public float mMinScale;
    private String mOriginPath;
    private int mOutputX;
    private int mOutputY;
    private int mSampleSize;
    /* access modifiers changed from: private */
    public final Matrix mSuppMatrix;
    /* access modifiers changed from: private */
    public long mZoomDuration;
    private final Paint outlinePaint;
    private final Paint outsidePaint;
    private Path path;
    /* access modifiers changed from: private */
    public Interpolator sInterpolator;
    private Rect viewDrawingRect;

    public CropView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CropView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaseMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.outlinePaint = new Paint();
        this.outsidePaint = new Paint();
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mMidScale = DEFAULT_MID_SCALE;
        this.mMaxScale = DEFAULT_MAX_SCALE;
        this.mZoomDuration = DEFAULT_ZOOM_DURATION;
        this.sInterpolator = new AccelerateDecelerateInterpolator();
        this.mBitmapDisplayed = new RotateBitmap((Bitmap) null, 0);
        this.highlightColor = -1;
        this.path = new Path();
        this.viewDrawingRect = new Rect();
        this.mAspectX = 1;
        this.mAspectY = 1;
        setScaleType(ImageView.ScaleType.MATRIX);
        this.mDragScaleDetector = VersionedGestureDetector.newInstance(context, this);
        this.mGestureDetector = new android.view.GestureDetector(context, new GestureDetector.SimpleOnGestureListener());
        this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener());
        this.outlinePaint.setAntiAlias(true);
        this.outlinePaint.setColor(this.highlightColor);
        this.outlinePaint.setStyle(Paint.Style.STROKE);
        this.outlinePaint.setStrokeWidth(dpToPx(OUTLINE_DP));
        this.outsidePaint.setARGB(125, 50, 50, 50);
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    public CropView load(String str) {
        this.mOriginPath = str;
        return this;
    }

    public CropView setAspect(int i, int i2) {
        this.mAspectX = i;
        this.mAspectY = i2;
        return this;
    }

    public CropView setOutputSize(int i, int i2) {
        this.mOutputX = i;
        this.mOutputY = i2;
        return this;
    }

    public void start(Context context) {
        if (!TextUtils.isEmpty(this.mOriginPath)) {
            this.mSampleSize = CropUtil.calculateBitmapSampleSize(context, this.mOriginPath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = this.mSampleSize;
            setImageRotateBitmap(new RotateBitmap(BitmapFactory.decodeFile(this.mOriginPath, options), CropUtil.getExifRotation(this.mOriginPath)));
        }
    }

    public Bitmap getOutput() {
        if (getDrawable() == null || this.mCropRect == null) {
            return null;
        }
        Matrix drawMatrix = getDrawMatrix();
        RectF displayRect = getDisplayRect(drawMatrix);
        float f = this.mCropRect.left - displayRect.left;
        float f2 = this.mCropRect.top - displayRect.top;
        float sqrt = (float) Math.sqrt((double) (((float) Math.pow((double) getValue(drawMatrix, 0), 2.0d)) + ((float) Math.pow((double) getValue(drawMatrix, 3), 2.0d))));
        return CropUtil.decodeRegionCrop(getContext(), this.mOriginPath, new Rect((int) ((f / sqrt) * ((float) this.mSampleSize)), (int) ((f2 / sqrt) * ((float) this.mSampleSize)), (int) (((f + this.mCropRect.width()) / sqrt) * ((float) this.mSampleSize)), (int) (((f2 + this.mCropRect.height()) / sqrt) * ((float) this.mSampleSize))), this.mOutputX, this.mOutputY, this.mBitmapDisplayed.getRotation());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        cleanup();
        super.onDetachedFromWindow();
    }

    private void cleanup() {
        cancelFling();
        if (this.mGestureDetector != null) {
            this.mGestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) null);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
        setImageBitmap((Bitmap) null);
        this.mBitmapDisplayed.recycle();
    }

    private int getCropViewWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getCropViewHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void onGlobalLayout() {
        int top = getTop();
        int bottom = getBottom();
        int left = getLeft();
        int right = getRight();
        if (top != this.mIvTop || bottom != this.mIvBottom || left != this.mIvLeft || right != this.mIvRight) {
            updateBaseMatrix();
            this.mIvTop = top;
            this.mIvBottom = bottom;
            this.mIvLeft = left;
            this.mIvRight = right;
        }
    }

    private void updateBaseMatrix() {
        float f;
        if (this.mBitmapDisplayed.getBitmap() != null) {
            float cropViewWidth = (float) getCropViewWidth();
            float cropViewHeight = (float) getCropViewHeight();
            float width = (float) this.mBitmapDisplayed.getWidth();
            float height = (float) this.mBitmapDisplayed.getHeight();
            this.mBaseMatrix.reset();
            float min = Math.min(Math.min(cropViewWidth / width, DEFAULT_MID_SCALE), Math.min(cropViewHeight / height, DEFAULT_MID_SCALE));
            float min2 = ((Math.min(width, height) * 4.0f) / 5.0f) * min;
            if (this.mAspectX == 0 || this.mAspectY == 0) {
                f = min2;
            } else if (this.mAspectX > this.mAspectY) {
                f = (((float) this.mAspectY) * min2) / ((float) this.mAspectX);
            } else {
                f = min2;
                min2 = (((float) this.mAspectX) * min2) / ((float) this.mAspectY);
            }
            float min3 = Math.min((cropViewWidth / min2) * 0.6f, (cropViewHeight / f) * 0.6f);
            if (min3 > DEFAULT_MIN_SCALE) {
                min *= min3;
                min2 *= min3;
                f *= min3;
            }
            float f2 = (cropViewWidth - min2) / OUTLINE_DP;
            float f3 = (cropViewHeight - f) / OUTLINE_DP;
            this.mCropRect = new RectF(f2, f3, min2 + f2, f + f3);
            this.mBaseMatrix.postConcat(this.mBitmapDisplayed.getRotateMatrix());
            this.mBaseMatrix.postScale(min, min);
            this.mBaseMatrix.postTranslate((cropViewWidth - (width * min)) / OUTLINE_DP, (cropViewHeight - (height * min)) / OUTLINE_DP);
            this.mSuppMatrix.reset();
            setImageMatrix(getDrawMatrix());
            RectF displayRect = getDisplayRect(this.mBaseMatrix);
            this.mMinScale = Math.max(this.mCropRect.width() / displayRect.width(), this.mCropRect.height() / displayRect.height());
        }
    }

    private void setImageRotateBitmap(RotateBitmap rotateBitmap) {
        Bitmap bitmap = this.mBitmapDisplayed.getBitmap();
        this.mBitmapDisplayed = rotateBitmap;
        setImageBitmap(rotateBitmap.getBitmap());
        if (bitmap != null) {
            bitmap.recycle();
        }
        updateBaseMatrix();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (getDrawable() == null || this.mCropRect == null) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            cancelFling();
        }
        if (this.mDragScaleDetector != null) {
            z = this.mDragScaleDetector.onTouchEvent(motionEvent);
        }
        if (this.mGestureDetector == null || !this.mGestureDetector.onTouchEvent(motionEvent)) {
            return z;
        }
        return true;
    }

    public void onDrag(float f, float f2) {
        if (!this.mDragScaleDetector.isScaling()) {
            this.mSuppMatrix.postTranslate(f, f2);
            checkAndDisplayMatrix();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4 >= r0) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001d, code lost:
        if (r4 <= r0) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScale(float r4, float r5, float r6) {
        /*
            r3 = this;
            float r0 = r3.getScale()
            r1 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r2 <= 0) goto L_0x0013
            float r1 = r3.mMaxScale
            float r0 = r1 / r0
            int r1 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r1 < 0) goto L_0x0020
            goto L_0x001f
        L_0x0013:
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x0020
            float r1 = r3.mMinScale
            float r0 = r1 / r0
            int r1 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r1 > 0) goto L_0x0020
        L_0x001f:
            r4 = r0
        L_0x0020:
            android.graphics.Matrix r0 = r3.mSuppMatrix
            r0.postScale(r4, r4, r5, r6)
            r3.checkAndDisplayMatrix()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.photo_lib.widget.crop.CropView.onScale(float, float, float):void");
    }

    public void onFling(float f, float f2, float f3, float f4) {
        this.mCurrentFlingRunnable = new FlingRunnable(getContext());
        this.mCurrentFlingRunnable.fling((int) f3, (int) f4);
        post(this.mCurrentFlingRunnable);
    }

    private void cancelFling() {
        if (this.mCurrentFlingRunnable != null) {
            this.mCurrentFlingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageMatrix(getDrawMatrix());
        }
    }

    private boolean checkMatrixBounds() {
        float f;
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        float f2 = 0.0f;
        if (displayRect.top >= this.mCropRect.top) {
            f = (-displayRect.top) + this.mCropRect.top;
        } else {
            f = displayRect.bottom <= this.mCropRect.bottom ? this.mCropRect.bottom - displayRect.bottom : 0.0f;
        }
        if (displayRect.left >= this.mCropRect.left) {
            f2 = (-displayRect.left) + this.mCropRect.left;
        } else if (displayRect.right <= this.mCropRect.right) {
            f2 = this.mCropRect.right - displayRect.right;
        }
        this.mSuppMatrix.postTranslate(f2, f);
        return true;
    }

    /* access modifiers changed from: private */
    public RectF getDisplayRect(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        this.mDisplayRect.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    /* access modifiers changed from: private */
    public Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    /* access modifiers changed from: private */
    public float getScale() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow((double) getValue(this.mSuppMatrix, 3), 2.0d))));
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    /* access modifiers changed from: private */
    public void setScale(float f, float f2, float f3, boolean z) {
        if (f >= this.mMinScale && f <= this.mMaxScale) {
            if (z) {
                post(new AnimatedZoomRunnable(getScale(), f, f2, f3));
                return;
            }
            this.mSuppMatrix.setScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mCropRect != null) {
            this.path.reset();
            this.path.addRect(this.mCropRect.left, this.mCropRect.top, this.mCropRect.right, this.mCropRect.bottom, Path.Direction.CW);
            if (isClipPathSupported(canvas)) {
                getDrawingRect(this.viewDrawingRect);
                canvas.clipPath(this.path, Region.Op.DIFFERENCE);
                canvas.drawRect(this.viewDrawingRect, this.outsidePaint);
            } else {
                drawOutsideFallback(canvas);
            }
            canvas.drawPath(this.path, this.outlinePaint);
        }
    }

    private boolean isClipPathSupported(Canvas canvas) {
        if (Build.VERSION.SDK_INT == 17) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14 || Build.VERSION.SDK_INT > 15) {
            return true;
        }
        return !canvas.isHardwareAccelerated();
    }

    private void drawOutsideFallback(Canvas canvas) {
        Canvas canvas2 = canvas;
        canvas2.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), this.mCropRect.top, this.outsidePaint);
        canvas2.drawRect(0.0f, this.mCropRect.bottom, (float) canvas.getWidth(), (float) canvas.getHeight(), this.outsidePaint);
        canvas2.drawRect(0.0f, this.mCropRect.top, this.mCropRect.left, this.mCropRect.bottom, this.outsidePaint);
        canvas2.drawRect(this.mCropRect.right, this.mCropRect.top, (float) canvas.getWidth(), this.mCropRect.bottom, this.outsidePaint);
    }

    private float dpToPx(float f) {
        return f * getContext().getResources().getDisplayMetrics().density;
    }

    private class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;

        public FlingRunnable(Context context) {
            this.mScroller = ScrollerProxy.getScroller(context);
        }

        public void cancelFling() {
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2) {
            RectF access$200 = CropView.this.getDisplayRect(CropView.this.getDrawMatrix());
            if (access$200 != null) {
                int round = Math.round(CropView.this.mCropRect.left - access$200.left);
                int round2 = Math.round(CropView.this.mCropRect.top - access$200.top);
                int round3 = Math.round(access$200.width() - CropView.this.mCropRect.width());
                int round4 = Math.round(access$200.height() - CropView.this.mCropRect.height());
                this.mCurrentX = round;
                this.mCurrentY = round2;
                this.mScroller.fling(round, round2, i, i2, 0, round3, 0, round4, 0, 0);
            }
        }

        public void run() {
            if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                CropView.this.mSuppMatrix.postTranslate((float) (this.mCurrentX - currX), (float) (this.mCurrentY - currY));
                CropView.this.setImageMatrix(CropView.this.getDrawMatrix());
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                CropCompat.postOnAnimation(CropView.this, this);
            }
        }
    }

    private class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return false;
        }

        private DefaultOnDoubleTapListener() {
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            try {
                float access$500 = CropView.this.getScale();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (access$500 < CropView.this.mMidScale) {
                    CropView.this.setScale(CropView.this.mMidScale, x, y, true);
                } else if (access$500 < CropView.this.mMidScale || access$500 >= CropView.this.mMaxScale) {
                    CropView.this.setScale(CropView.this.mMinScale, x, y, true);
                } else {
                    CropView.this.setScale(CropView.this.mMaxScale, x, y, true);
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
            return true;
        }
    }

    private class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;

        public AnimatedZoomRunnable(float f, float f2, float f3, float f4) {
            this.mZoomStart = f;
            this.mZoomEnd = f2;
            this.mFocalX = f3;
            this.mFocalY = f4;
        }

        public void run() {
            float interpolate = interpolate();
            CropView.this.onScale((this.mZoomStart + ((this.mZoomEnd - this.mZoomStart) * interpolate)) / CropView.this.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < CropView.DEFAULT_MIN_SCALE) {
                CropCompat.postOnAnimation(CropView.this, this);
            }
        }

        private float interpolate() {
            return CropView.this.sInterpolator.getInterpolation(Math.min(CropView.DEFAULT_MIN_SCALE, (((float) (System.currentTimeMillis() - this.mStartTime)) * CropView.DEFAULT_MIN_SCALE) / ((float) CropView.this.mZoomDuration)));
        }
    }
}
