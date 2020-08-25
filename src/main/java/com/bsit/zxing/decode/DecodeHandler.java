package com.bsit.zxing.decode;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bsit.zxing.CaptureActivity;
import com.bsit.zxing.R;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import java.io.ByteArrayOutputStream;
import java.util.Map;

final class DecodeHandler extends Handler {
    private static final String TAG = "DecodeHandler";
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();
    private boolean running = true;

    DecodeHandler(CaptureActivity captureActivity, Map<DecodeHintType, Object> map) {
        this.multiFormatReader.setHints(map);
        this.activity = captureActivity;
    }

    public void handleMessage(Message message) {
        if (this.running) {
            if (message.what == R.id.zxing_decode) {
                decode((byte[]) message.obj, message.arg1, message.arg2);
            } else if (message.what == R.id.zxing_quit) {
                this.running = false;
                Looper.myLooper().quit();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decode(byte[] r9, int r10, int r11) {
        /*
            r8 = this;
            long r0 = java.lang.System.currentTimeMillis()
            int r2 = r9.length
            byte[] r2 = new byte[r2]
            r3 = 0
            r4 = 0
        L_0x0009:
            if (r4 >= r11) goto L_0x0021
            r5 = 0
        L_0x000c:
            if (r5 >= r10) goto L_0x001e
            int r6 = r5 * r11
            int r6 = r6 + r11
            int r6 = r6 - r4
            int r6 = r6 + -1
            int r7 = r4 * r10
            int r7 = r7 + r5
            byte r7 = r9[r7]
            r2[r6] = r7
            int r5 = r5 + 1
            goto L_0x000c
        L_0x001e:
            int r4 = r4 + 1
            goto L_0x0009
        L_0x0021:
            com.google.zxing.PlanarYUVLuminanceSource r9 = r8.buildLuminanceSource(r2, r11, r10)
            if (r9 == 0) goto L_0x0049
            com.google.zxing.BinaryBitmap r10 = new com.google.zxing.BinaryBitmap
            com.google.zxing.common.HybridBinarizer r11 = new com.google.zxing.common.HybridBinarizer
            r11.<init>(r9)
            r10.<init>(r11)
            com.google.zxing.MultiFormatReader r11 = r8.multiFormatReader     // Catch:{ ReaderException -> 0x0044, all -> 0x003d }
            com.google.zxing.Result r10 = r11.decodeWithState(r10)     // Catch:{ ReaderException -> 0x0044, all -> 0x003d }
            com.google.zxing.MultiFormatReader r11 = r8.multiFormatReader
            r11.reset()
            goto L_0x004a
        L_0x003d:
            r9 = move-exception
            com.google.zxing.MultiFormatReader r10 = r8.multiFormatReader
            r10.reset()
            throw r9
        L_0x0044:
            com.google.zxing.MultiFormatReader r10 = r8.multiFormatReader
            r10.reset()
        L_0x0049:
            r10 = 0
        L_0x004a:
            com.bsit.zxing.CaptureActivity r11 = r8.activity
            android.os.Handler r11 = r11.getHandler()
            if (r10 == 0) goto L_0x0089
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Found barcode in "
            r5.append(r6)
            long r2 = r2 - r0
            r5.append(r2)
            java.lang.String r0 = " ms"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            android.util.Log.d(r4, r0)
            if (r11 == 0) goto L_0x0094
            int r0 = com.bsit.zxing.R.id.zxing_decode_succeeded
            android.os.Message r10 = android.os.Message.obtain(r11, r0, r10)
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
            bundleThumbnail(r9, r11)
            r10.setData(r11)
            r10.sendToTarget()
            goto L_0x0094
        L_0x0089:
            if (r11 == 0) goto L_0x0094
            int r9 = com.bsit.zxing.R.id.zxing_decode_failed
            android.os.Message r9 = android.os.Message.obtain(r11, r9)
            r9.sendToTarget()
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.zxing.decode.DecodeHandler.decode(byte[], int, int):void");
    }

    private static void bundleThumbnail(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] renderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap createBitmap = Bitmap.createBitmap(renderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", ((float) thumbnailWidth) / ((float) planarYUVLuminanceSource.getWidth()));
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect cropRect = this.activity.getCropRect();
        if (cropRect == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(bArr, i, i2, cropRect.left, cropRect.top, cropRect.width(), cropRect.height(), false);
    }
}
