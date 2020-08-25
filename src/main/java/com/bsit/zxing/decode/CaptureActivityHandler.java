package com.bsit.zxing.decode;

import android.os.Handler;
import android.os.Message;
import com.bsit.zxing.CaptureActivity;
import com.bsit.zxing.R;
import com.bsit.zxing.camera.CameraManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivityHandler extends Handler {
    private static final String TAG = "CaptureActivityHandler";
    private final CaptureActivity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private State state = State.SUCCESS;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureActivity captureActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, CameraManager cameraManager2) {
        this.activity = captureActivity;
        this.decodeThread = new DecodeThread(captureActivity, collection, map, str, (ResultPointCallback) null);
        this.decodeThread.start();
        this.cameraManager = cameraManager2;
        cameraManager2.startPreview();
        restartPreviewAndDecode();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r6) {
        /*
            r5 = this;
            int r0 = r6.what
            int r1 = com.bsit.zxing.R.id.zxing_restart_preview
            if (r0 != r1) goto L_0x0012
            java.lang.String r6 = TAG
            java.lang.String r0 = "Got restart preview message"
            android.util.Log.d(r6, r0)
            r5.restartPreviewAndDecode()
            goto L_0x0113
        L_0x0012:
            int r0 = r6.what
            int r1 = com.bsit.zxing.R.id.zxing_decode_succeeded
            r2 = 0
            if (r0 != r1) goto L_0x0053
            java.lang.String r0 = TAG
            java.lang.String r1 = "Got decode succeeded message"
            android.util.Log.d(r0, r1)
            com.bsit.zxing.decode.CaptureActivityHandler$State r0 = com.bsit.zxing.decode.CaptureActivityHandler.State.SUCCESS
            r5.state = r0
            android.os.Bundle r0 = r6.getData()
            r1 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L_0x0048
            java.lang.String r1 = "barcode_bitmap"
            byte[] r1 = r0.getByteArray(r1)
            if (r1 == 0) goto L_0x0042
            r3 = 0
            int r4 = r1.length
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r3, r4, r2)
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.ARGB_8888
            r3 = 1
            android.graphics.Bitmap r1 = r1.copy(r2, r3)
            r2 = r1
        L_0x0042:
            java.lang.String r1 = "barcode_scaled_factor"
            float r1 = r0.getFloat(r1)
        L_0x0048:
            com.bsit.zxing.CaptureActivity r0 = r5.activity
            java.lang.Object r6 = r6.obj
            com.google.zxing.Result r6 = (com.google.zxing.Result) r6
            r0.handleDecode(r6, r2, r1)
            goto L_0x0113
        L_0x0053:
            int r0 = r6.what
            int r1 = com.bsit.zxing.R.id.zxing_decode_failed
            if (r0 != r1) goto L_0x006c
            com.bsit.zxing.decode.CaptureActivityHandler$State r6 = com.bsit.zxing.decode.CaptureActivityHandler.State.PREVIEW
            r5.state = r6
            com.bsit.zxing.camera.CameraManager r6 = r5.cameraManager
            com.bsit.zxing.decode.DecodeThread r0 = r5.decodeThread
            android.os.Handler r0 = r0.getHandler()
            int r1 = com.bsit.zxing.R.id.zxing_decode
            r6.requestPreviewFrame(r0, r1)
            goto L_0x0113
        L_0x006c:
            int r0 = r6.what
            int r1 = com.bsit.zxing.R.id.zxing_return_scan_result
            if (r0 != r1) goto L_0x008a
            java.lang.String r0 = TAG
            java.lang.String r1 = "Got return scan result message"
            android.util.Log.d(r0, r1)
            com.bsit.zxing.CaptureActivity r0 = r5.activity
            r1 = -1
            java.lang.Object r6 = r6.obj
            android.content.Intent r6 = (android.content.Intent) r6
            r0.setResult(r1, r6)
            com.bsit.zxing.CaptureActivity r6 = r5.activity
            r6.finish()
            goto L_0x0113
        L_0x008a:
            int r0 = r6.what
            int r1 = com.bsit.zxing.R.id.zxing_launch_product_query
            if (r0 != r1) goto L_0x0113
            java.lang.String r0 = TAG
            java.lang.String r1 = "Got product query message"
            android.util.Log.d(r0, r1)
            java.lang.Object r6 = r6.obj
            java.lang.String r6 = (java.lang.String) r6
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.intent.action.VIEW"
            r0.<init>(r1)
            r1 = 524288(0x80000, float:7.34684E-40)
            r0.addFlags(r1)
            android.net.Uri r1 = android.net.Uri.parse(r6)
            r0.setData(r1)
            com.bsit.zxing.CaptureActivity r1 = r5.activity
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            r3 = 65536(0x10000, float:9.18355E-41)
            android.content.pm.ResolveInfo r1 = r1.resolveActivity(r0, r3)
            if (r1 == 0) goto L_0x00da
            android.content.pm.ActivityInfo r3 = r1.activityInfo
            if (r3 == 0) goto L_0x00da
            android.content.pm.ActivityInfo r1 = r1.activityInfo
            java.lang.String r2 = r1.packageName
            java.lang.String r1 = TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Using browser in package "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r1, r3)
        L_0x00da:
            java.lang.String r1 = "com.android.browser"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00ea
            java.lang.String r1 = "com.android.chrome"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00f7
        L_0x00ea:
            r0.setPackage(r2)
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r0.addFlags(r1)
            java.lang.String r1 = "com.android.browser.application_id"
            r0.putExtra(r1, r2)
        L_0x00f7:
            com.bsit.zxing.CaptureActivity r1 = r5.activity     // Catch:{ ActivityNotFoundException -> 0x00fd }
            r1.startActivity(r0)     // Catch:{ ActivityNotFoundException -> 0x00fd }
            goto L_0x0113
        L_0x00fd:
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Can't find anything to handle VIEW of URI "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            android.util.Log.w(r0, r6)
        L_0x0113:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.zxing.decode.CaptureActivityHandler.handleMessage(android.os.Message):void");
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), R.id.zxing_quit).sendToTarget();
        try {
            this.decodeThread.join(500);
        } catch (InterruptedException unused) {
        }
        removeMessages(R.id.zxing_decode_succeeded);
        removeMessages(R.id.zxing_decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), R.id.zxing_decode);
        }
    }
}
