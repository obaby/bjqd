package com.bsit.zxing.camera.open;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;

public final class OpenCameraInterface {
    private static final String TAG = "com.bsit.zxing.camera.open.OpenCameraInterface";

    private OpenCameraInterface() {
    }

    @TargetApi(9)
    public static Camera open(int i) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Log.w(TAG, "No cameras!");
            return null;
        }
        boolean z = i >= 0;
        if (!z) {
            i = 0;
            while (i < numberOfCameras) {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == 0) {
                    break;
                }
                i++;
            }
        }
        if (i < numberOfCameras) {
            String str = TAG;
            Log.i(str, "Opening camera #" + i);
            return Camera.open(i);
        } else if (z) {
            String str2 = TAG;
            Log.w(str2, "Requested camera does not exist: " + i);
            return null;
        } else {
            Log.i(TAG, "No camera facing back; returning camera #0");
            return Camera.open(0);
        }
    }

    @TargetApi(9)
    public static Camera open() {
        if (Build.VERSION.SDK_INT >= 9) {
            return open(-1);
        }
        Camera open = Camera.open();
        if (open != null) {
            return open;
        }
        Log.w(TAG, "No cameras!");
        return null;
    }
}
