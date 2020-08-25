package cn.bingoogolapple.qrcode.core;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "CameraPreview";
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean z, Camera camera) {
            CameraPreview.this.postDelayed(CameraPreview.this.doAutoFocus, 1000);
        }
    };
    /* access modifiers changed from: private */
    public Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (CameraPreview.this.mCamera != null && CameraPreview.this.mPreviewing && CameraPreview.this.mAutoFocus && CameraPreview.this.mSurfaceCreated) {
                try {
                    CameraPreview.this.mCamera.autoFocus(CameraPreview.this.autoFocusCB);
                } catch (Exception unused) {
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mAutoFocus = true;
    /* access modifiers changed from: private */
    public Camera mCamera;
    private CameraConfigurationManager mCameraConfigurationManager;
    /* access modifiers changed from: private */
    public boolean mPreviewing = true;
    /* access modifiers changed from: private */
    public boolean mSurfaceCreated = false;

    public CameraPreview(Context context) {
        super(context);
    }

    public void setCamera(Camera camera) {
        this.mCamera = camera;
        if (this.mCamera != null) {
            this.mCameraConfigurationManager = new CameraConfigurationManager(getContext());
            this.mCameraConfigurationManager.initFromCameraParameters(this.mCamera);
            getHolder().addCallback(this);
            if (this.mPreviewing) {
                requestLayout();
            } else {
                showCameraPreview();
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = true;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (surfaceHolder.getSurface() != null) {
            stopCameraPreview();
            post(new Runnable() {
                public void run() {
                    CameraPreview.this.showCameraPreview();
                }
            });
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = false;
        stopCameraPreview();
    }

    public void showCameraPreview() {
        if (this.mCamera != null) {
            try {
                this.mPreviewing = true;
                this.mCamera.setPreviewDisplay(getHolder());
                this.mCameraConfigurationManager.setDesiredCameraParameters(this.mCamera);
                this.mCamera.startPreview();
                if (this.mAutoFocus) {
                    this.mCamera.autoFocus(this.autoFocusCB);
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString(), e);
            }
        }
    }

    public void stopCameraPreview() {
        if (this.mCamera != null) {
            try {
                removeCallbacks(this.doAutoFocus);
                this.mPreviewing = false;
                this.mCamera.cancelAutoFocus();
                this.mCamera.setOneShotPreviewCallback((Camera.PreviewCallback) null);
                this.mCamera.stopPreview();
            } catch (Exception e) {
                Log.e(TAG, e.toString(), e);
            }
        }
    }

    public void openFlashlight() {
        if (flashLightAvailable()) {
            this.mCameraConfigurationManager.openFlashlight(this.mCamera);
        }
    }

    public void closeFlashlight() {
        if (flashLightAvailable()) {
            this.mCameraConfigurationManager.closeFlashlight(this.mCamera);
        }
    }

    public void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(getSuggestedMinimumWidth(), i);
        int defaultSize2 = getDefaultSize(getSuggestedMinimumHeight(), i2);
        if (!(this.mCameraConfigurationManager == null || this.mCameraConfigurationManager.getCameraResolution() == null)) {
            Point cameraResolution = this.mCameraConfigurationManager.getCameraResolution();
            float f = (float) defaultSize;
            float f2 = (float) defaultSize2;
            float f3 = (float) cameraResolution.y;
            float f4 = (float) cameraResolution.x;
            float f5 = (f3 * 1.0f) / f4;
            if ((f * 1.0f) / f2 < f5) {
                defaultSize = (int) ((f2 / ((f4 * 1.0f) / f3)) + 0.5f);
            } else {
                defaultSize2 = (int) ((f / f5) + 0.5f);
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(defaultSize, 1073741824), View.MeasureSpec.makeMeasureSpec(defaultSize2, 1073741824));
    }

    private boolean flashLightAvailable() {
        return this.mCamera != null && this.mPreviewing && this.mSurfaceCreated && getContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }
}
