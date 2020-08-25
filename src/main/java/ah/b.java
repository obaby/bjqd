package ah;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.linewell.licence.R;
import com.linewell.licence.ui.zxing.ZxingActivity;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public final class b extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f90a = "b";

    /* renamed from: b  reason: collision with root package name */
    private final ZxingActivity f91b;

    /* renamed from: c  reason: collision with root package name */
    private final MultiFormatReader f92c = new MultiFormatReader();
    private boolean d = true;

    b(ZxingActivity zxingActivity, Map<DecodeHintType, Object> map) {
        this.f92c.setHints(map);
        this.f91b = zxingActivity;
    }

    public void handleMessage(Message message) {
        if (this.d) {
            if (message.what == R.id.decode) {
                a((byte[]) message.obj, message.arg1, message.arg2);
            } else if (message.what == R.id.quit) {
                this.d = false;
                Looper.myLooper().quit();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte[] r9, int r10, int r11) {
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
            com.linewell.licence.ui.zxing.ZxingActivity r9 = r8.f91b
            com.linewell.licence.ui.zxing.camera.CameraManager r9 = r9.k()
            com.google.zxing.PlanarYUVLuminanceSource r9 = r9.a(r2, r11, r10)
            if (r9 == 0) goto L_0x004f
            com.google.zxing.BinaryBitmap r10 = new com.google.zxing.BinaryBitmap
            com.google.zxing.common.HybridBinarizer r11 = new com.google.zxing.common.HybridBinarizer
            r11.<init>(r9)
            r10.<init>(r11)
            com.google.zxing.MultiFormatReader r11 = r8.f92c     // Catch:{ ReaderException -> 0x004a, all -> 0x0043 }
            com.google.zxing.Result r10 = r11.decodeWithState(r10)     // Catch:{ ReaderException -> 0x004a, all -> 0x0043 }
            com.google.zxing.MultiFormatReader r11 = r8.f92c
            r11.reset()
            goto L_0x0050
        L_0x0043:
            r9 = move-exception
            com.google.zxing.MultiFormatReader r10 = r8.f92c
            r10.reset()
            throw r9
        L_0x004a:
            com.google.zxing.MultiFormatReader r10 = r8.f92c
            r10.reset()
        L_0x004f:
            r10 = 0
        L_0x0050:
            com.linewell.licence.ui.zxing.ZxingActivity r11 = r8.f91b
            android.os.Handler r11 = r11.j()
            if (r10 == 0) goto L_0x008f
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = f90a
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
            if (r11 == 0) goto L_0x009a
            int r0 = com.linewell.licence.R.id.decode_succeeded
            android.os.Message r10 = android.os.Message.obtain(r11, r0, r10)
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
            a(r9, r11)
            r10.setData(r11)
            r10.sendToTarget()
            goto L_0x009a
        L_0x008f:
            if (r11 == 0) goto L_0x009a
            int r9 = com.linewell.licence.R.id.decode_failed
            android.os.Message r9 = android.os.Message.obtain(r11, r9)
            r9.sendToTarget()
        L_0x009a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ah.b.a(byte[], int, int):void");
    }

    private static void a(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] renderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap createBitmap = Bitmap.createBitmap(renderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", ((float) thumbnailWidth) / ((float) planarYUVLuminanceSource.getWidth()));
    }
}
