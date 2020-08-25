package com.bsit.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import com.google.zxing.PlanarYUVLuminanceSource;

public final class CameraManager {
    private static final int MAX_FRAME_HEIGHT = 675;
    private static final int MAX_FRAME_WIDTH = 1200;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    private static final String TAG = "CameraManager";
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedCameraId = -1;
    private int requestedFramingRectHeight;
    private int requestedFramingRectWidth;

    public CameraManager(Context context2) {
        this.context = context2;
        this.configManager = new CameraConfigurationManager(context2);
        this.previewCallback = new PreviewCallback(this.configManager);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0054 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void openDriver(android.view.SurfaceHolder r6) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            android.hardware.Camera r0 = r5.camera     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x001f
            int r0 = r5.requestedCameraId     // Catch:{ all -> 0x008c }
            if (r0 < 0) goto L_0x0010
            int r0 = r5.requestedCameraId     // Catch:{ all -> 0x008c }
            android.hardware.Camera r0 = com.bsit.zxing.camera.open.OpenCameraInterface.open(r0)     // Catch:{ all -> 0x008c }
            goto L_0x0014
        L_0x0010:
            android.hardware.Camera r0 = com.bsit.zxing.camera.open.OpenCameraInterface.open()     // Catch:{ all -> 0x008c }
        L_0x0014:
            if (r0 == 0) goto L_0x0019
            r5.camera = r0     // Catch:{ all -> 0x008c }
            goto L_0x001f
        L_0x0019:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x008c }
            r6.<init>()     // Catch:{ all -> 0x008c }
            throw r6     // Catch:{ all -> 0x008c }
        L_0x001f:
            r0.setPreviewDisplay(r6)     // Catch:{ all -> 0x008c }
            boolean r6 = r5.initialized     // Catch:{ all -> 0x008c }
            r1 = 1
            r2 = 0
            if (r6 != 0) goto L_0x0042
            r5.initialized = r1     // Catch:{ all -> 0x008c }
            com.bsit.zxing.camera.CameraConfigurationManager r6 = r5.configManager     // Catch:{ all -> 0x008c }
            r6.initFromCameraParameters(r0)     // Catch:{ all -> 0x008c }
            int r6 = r5.requestedFramingRectWidth     // Catch:{ all -> 0x008c }
            if (r6 <= 0) goto L_0x0042
            int r6 = r5.requestedFramingRectHeight     // Catch:{ all -> 0x008c }
            if (r6 <= 0) goto L_0x0042
            int r6 = r5.requestedFramingRectWidth     // Catch:{ all -> 0x008c }
            int r3 = r5.requestedFramingRectHeight     // Catch:{ all -> 0x008c }
            r5.setManualFramingRect(r6, r3)     // Catch:{ all -> 0x008c }
            r5.requestedFramingRectWidth = r2     // Catch:{ all -> 0x008c }
            r5.requestedFramingRectHeight = r2     // Catch:{ all -> 0x008c }
        L_0x0042:
            android.hardware.Camera$Parameters r6 = r0.getParameters()     // Catch:{ all -> 0x008c }
            if (r6 != 0) goto L_0x004a
            r6 = 0
            goto L_0x004e
        L_0x004a:
            java.lang.String r6 = r6.flatten()     // Catch:{ all -> 0x008c }
        L_0x004e:
            com.bsit.zxing.camera.CameraConfigurationManager r3 = r5.configManager     // Catch:{ RuntimeException -> 0x0054 }
            r3.setDesiredCameraParameters(r0, r2)     // Catch:{ RuntimeException -> 0x0054 }
            goto L_0x008a
        L_0x0054:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "Camera rejected parameters. Setting only minimal safe-mode parameters"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x008c }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r3.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r4 = "Resetting to saved camera params: "
            r3.append(r4)     // Catch:{ all -> 0x008c }
            r3.append(r6)     // Catch:{ all -> 0x008c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x008c }
            android.util.Log.i(r2, r3)     // Catch:{ all -> 0x008c }
            if (r6 == 0) goto L_0x008a
            android.hardware.Camera$Parameters r2 = r0.getParameters()     // Catch:{ all -> 0x008c }
            r2.unflatten(r6)     // Catch:{ all -> 0x008c }
            r0.setParameters(r2)     // Catch:{ RuntimeException -> 0x0083 }
            com.bsit.zxing.camera.CameraConfigurationManager r6 = r5.configManager     // Catch:{ RuntimeException -> 0x0083 }
            r6.setDesiredCameraParameters(r0, r1)     // Catch:{ RuntimeException -> 0x0083 }
            goto L_0x008a
        L_0x0083:
            java.lang.String r6 = TAG     // Catch:{ all -> 0x008c }
            java.lang.String r0 = "Camera rejected even safe-mode parameters! No configuration"
            android.util.Log.w(r6, r0)     // Catch:{ all -> 0x008c }
        L_0x008a:
            monitor-exit(r5)
            return
        L_0x008c:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.zxing.camera.CameraManager.openDriver(android.view.SurfaceHolder):void");
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void closeDriver() {
        if (this.camera != null) {
            this.camera.release();
            this.camera = null;
            this.framingRect = null;
            this.framingRectInPreview = null;
        }
    }

    public synchronized void startPreview() {
        Camera camera2 = this.camera;
        if (camera2 != null && !this.previewing) {
            camera2.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, this.camera);
        }
    }

    public synchronized void stopPreview() {
        if (this.autoFocusManager != null) {
            this.autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        if (this.camera != null && this.previewing) {
            this.camera.stopPreview();
            this.previewCallback.setHandler((Handler) null, 0);
            this.previewing = false;
        }
    }

    public synchronized void setTorch(boolean z) {
        if (!(z == this.configManager.getTorchState(this.camera) || this.camera == null)) {
            if (this.autoFocusManager != null) {
                this.autoFocusManager.stop();
            }
            this.configManager.setTorch(this.camera, z);
            if (this.autoFocusManager != null) {
                this.autoFocusManager.start();
            }
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int i) {
        Camera camera2 = this.camera;
        if (camera2 != null && this.previewing) {
            this.previewCallback.setHandler(handler, i);
            camera2.setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized Rect getFramingRect() {
        if (this.framingRect == null) {
            if (this.camera == null) {
                return null;
            }
            Point screenResolution = this.configManager.getScreenResolution();
            if (screenResolution == null) {
                return null;
            }
            int findDesiredDimensionInRange = findDesiredDimensionInRange(screenResolution.x, 240, MAX_FRAME_WIDTH);
            int i = (screenResolution.x - findDesiredDimensionInRange) / 2;
            int i2 = (screenResolution.y - findDesiredDimensionInRange) / 2;
            this.framingRect = new Rect(i, i2, i + findDesiredDimensionInRange, findDesiredDimensionInRange + i2);
            String str = TAG;
            Log.d(str, "Calculated framing rect: " + this.framingRect);
        }
        return this.framingRect;
    }

    private static int findDesiredDimensionInRange(int i, int i2, int i3) {
        int i4 = (i * 5) / 8;
        if (i4 < i2) {
            return i2;
        }
        return i4 > i3 ? i3 : i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0098, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Rect getFramingRectInPreview() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.graphics.Rect r0 = r5.framingRectInPreview     // Catch:{ all -> 0x009d }
            if (r0 != 0) goto L_0x0099
            android.graphics.Rect r0 = r5.getFramingRect()     // Catch:{ all -> 0x009d }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r5)
            return r1
        L_0x000e:
            android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ all -> 0x009d }
            r2.<init>(r0)     // Catch:{ all -> 0x009d }
            com.bsit.zxing.camera.CameraConfigurationManager r0 = r5.configManager     // Catch:{ all -> 0x009d }
            android.graphics.Point r0 = r0.getCameraResolution()     // Catch:{ all -> 0x009d }
            com.bsit.zxing.camera.CameraConfigurationManager r3 = r5.configManager     // Catch:{ all -> 0x009d }
            android.graphics.Point r3 = r3.getScreenResolution()     // Catch:{ all -> 0x009d }
            if (r0 == 0) goto L_0x0097
            if (r3 != 0) goto L_0x0024
            goto L_0x0097
        L_0x0024:
            int r1 = r2.left     // Catch:{ all -> 0x009d }
            int r4 = r0.y     // Catch:{ all -> 0x009d }
            int r1 = r1 * r4
            int r4 = r3.x     // Catch:{ all -> 0x009d }
            int r1 = r1 / r4
            r2.left = r1     // Catch:{ all -> 0x009d }
            int r1 = r2.right     // Catch:{ all -> 0x009d }
            int r4 = r0.y     // Catch:{ all -> 0x009d }
            int r1 = r1 * r4
            int r4 = r3.x     // Catch:{ all -> 0x009d }
            int r1 = r1 / r4
            r2.right = r1     // Catch:{ all -> 0x009d }
            int r1 = r2.top     // Catch:{ all -> 0x009d }
            int r4 = r0.x     // Catch:{ all -> 0x009d }
            int r1 = r1 * r4
            int r4 = r3.y     // Catch:{ all -> 0x009d }
            int r1 = r1 / r4
            r2.top = r1     // Catch:{ all -> 0x009d }
            int r1 = r2.bottom     // Catch:{ all -> 0x009d }
            int r4 = r0.x     // Catch:{ all -> 0x009d }
            int r1 = r1 * r4
            int r4 = r3.y     // Catch:{ all -> 0x009d }
            int r1 = r1 / r4
            r2.bottom = r1     // Catch:{ all -> 0x009d }
            r5.framingRectInPreview = r2     // Catch:{ all -> 0x009d }
            java.lang.String r1 = TAG     // Catch:{ all -> 0x009d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x009d }
            r2.<init>()     // Catch:{ all -> 0x009d }
            java.lang.String r4 = "Calculated framingRectInPreview rect: "
            r2.append(r4)     // Catch:{ all -> 0x009d }
            android.graphics.Rect r4 = r5.framingRectInPreview     // Catch:{ all -> 0x009d }
            r2.append(r4)     // Catch:{ all -> 0x009d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x009d }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x009d }
            java.lang.String r1 = TAG     // Catch:{ all -> 0x009d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x009d }
            r2.<init>()     // Catch:{ all -> 0x009d }
            java.lang.String r4 = "cameraResolution: "
            r2.append(r4)     // Catch:{ all -> 0x009d }
            r2.append(r0)     // Catch:{ all -> 0x009d }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x009d }
            android.util.Log.d(r1, r0)     // Catch:{ all -> 0x009d }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x009d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009d }
            r1.<init>()     // Catch:{ all -> 0x009d }
            java.lang.String r2 = "screenResolution: "
            r1.append(r2)     // Catch:{ all -> 0x009d }
            r1.append(r3)     // Catch:{ all -> 0x009d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x009d }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x009d }
            goto L_0x0099
        L_0x0097:
            monitor-exit(r5)
            return r1
        L_0x0099:
            android.graphics.Rect r0 = r5.framingRectInPreview     // Catch:{ all -> 0x009d }
            monitor-exit(r5)
            return r0
        L_0x009d:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.zxing.camera.CameraManager.getFramingRectInPreview():android.graphics.Rect");
    }

    public synchronized void setManualCameraId(int i) {
        this.requestedCameraId = i;
    }

    public synchronized void setManualFramingRect(int i, int i2) {
        if (this.initialized) {
            Point screenResolution = this.configManager.getScreenResolution();
            if (i > screenResolution.x) {
                i = screenResolution.x;
            }
            if (i2 > screenResolution.y) {
                i2 = screenResolution.y;
            }
            int i3 = (screenResolution.x - i) / 2;
            int i4 = (screenResolution.y - i2) / 2;
            this.framingRect = new Rect(i3, i4, i + i3, i2 + i4);
            String str = TAG;
            Log.d(str, "Calculated manual framing rect: " + this.framingRect);
            this.framingRectInPreview = null;
        } else {
            this.requestedFramingRectWidth = i;
            this.requestedFramingRectHeight = i2;
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect framingRectInPreview2 = getFramingRectInPreview();
        if (framingRectInPreview2 == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview2.left, framingRectInPreview2.top, framingRectInPreview2.width(), framingRectInPreview2.height(), false);
    }

    public Point getCameraResolution() {
        return this.configManager.getCameraResolution();
    }

    public boolean isSupportFlash() {
        return this.configManager.isSupportFlash(this.camera);
    }
}
