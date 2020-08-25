package com.bsit.zxing.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.bsit.zxing.config.Config;

final class CameraConfigurationManager {
    private static final String TAG = "CameraConfiguration";
    private Point cameraResolution;
    private final Context context;
    private Point screenResolution;

    CameraConfigurationManager(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: package-private */
    public void initFromCameraParameters(Camera camera) {
        Point point;
        Camera.Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        new Point();
        this.screenResolution = getDisplaySize(defaultDisplay);
        Log.i(TAG, "Screen resolution: " + this.screenResolution);
        if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            point = new Point(defaultDisplay.getHeight(), defaultDisplay.getWidth());
        } else {
            point = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
        this.cameraResolution = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, point);
        Log.i(TAG, "Camera resolution: " + this.cameraResolution);
    }

    @TargetApi(13)
    private Point getDisplaySize(Display display) {
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(point);
        } else {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }

    /* access modifiers changed from: package-private */
    public void setDesiredCameraParameters(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        initializeTorch(parameters, defaultSharedPreferences, z);
        CameraConfigurationUtils.setFocus(parameters, defaultSharedPreferences.getBoolean(Config.KEY_AUTO_FOCUS, true), defaultSharedPreferences.getBoolean(Config.KEY_DISABLE_CONTINUOUS_FOCUS, true), z);
        if (!z) {
            if (defaultSharedPreferences.getBoolean(Config.KEY_INVERT_SCAN, false)) {
                CameraConfigurationUtils.setInvertColor(parameters);
            }
            if (!defaultSharedPreferences.getBoolean(Config.KEY_DISABLE_BARCODE_SCENE_MODE, true)) {
                CameraConfigurationUtils.setBarcodeSceneMode(parameters);
            }
            if (!defaultSharedPreferences.getBoolean(Config.KEY_DISABLE_METERING, true)) {
                CameraConfigurationUtils.setVideoStabilization(parameters);
                CameraConfigurationUtils.setFocusArea(parameters);
                CameraConfigurationUtils.setMetering(parameters);
            }
        }
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        Log.i(TAG, "Final camera parameters: " + parameters.flatten());
        camera.setParameters(parameters);
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (!(previewSize == null || (this.cameraResolution.x == previewSize.width && this.cameraResolution.y == previewSize.height))) {
            Log.w(TAG, "Camera said it supported preview size " + this.cameraResolution.x + 'x' + this.cameraResolution.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            this.cameraResolution.x = previewSize.width;
            this.cameraResolution.y = previewSize.height;
        }
        camera.setDisplayOrientation(90);
    }

    /* access modifiers changed from: package-private */
    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    /* access modifiers changed from: package-private */
    public Point getScreenResolution() {
        return this.screenResolution;
    }

    /* access modifiers changed from: package-private */
    public boolean getTorchState(Camera camera) {
        Camera.Parameters parameters;
        String flashMode;
        if (camera == null || (parameters = camera.getParameters()) == null || (flashMode = parameters.getFlashMode()) == null) {
            return false;
        }
        if ("on".equals(flashMode) || "torch".equals(flashMode)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isSupportFlash(Camera camera) {
        Camera.Parameters parameters;
        if (camera == null || (parameters = camera.getParameters()) == null || parameters.getFlashMode() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setTorch(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        doSetTorch(parameters, z, false);
        camera.setParameters(parameters);
    }

    private void initializeTorch(Camera.Parameters parameters, SharedPreferences sharedPreferences, boolean z) {
        doSetTorch(parameters, FrontLightMode.readPref(sharedPreferences) == FrontLightMode.ON, z);
    }

    private void doSetTorch(Camera.Parameters parameters, boolean z, boolean z2) {
        CameraConfigurationUtils.setTorch(parameters, z);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        if (!z2 && !defaultSharedPreferences.getBoolean(Config.KEY_DISABLE_EXPOSURE, true)) {
            CameraConfigurationUtils.setBestExposure(parameters, z);
        }
    }
}
