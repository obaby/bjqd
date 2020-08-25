package cn.bingoogolapple.qrcode.zxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import coband.bsit.com.integral.BuildConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeDecoder {
    public static final Map<DecodeHintType, Object> HINTS = new EnumMap(DecodeHintType.class);

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BarcodeFormat.AZTEC);
        arrayList.add(BarcodeFormat.CODABAR);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        arrayList.add(BarcodeFormat.EAN_8);
        arrayList.add(BarcodeFormat.EAN_13);
        arrayList.add(BarcodeFormat.ITF);
        arrayList.add(BarcodeFormat.MAXICODE);
        arrayList.add(BarcodeFormat.PDF_417);
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.RSS_14);
        arrayList.add(BarcodeFormat.RSS_EXPANDED);
        arrayList.add(BarcodeFormat.UPC_A);
        arrayList.add(BarcodeFormat.UPC_E);
        arrayList.add(BarcodeFormat.UPC_EAN_EXTENSION);
        HINTS.put(DecodeHintType.TRY_HARDER, BarcodeFormat.QR_CODE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, arrayList);
        HINTS.put(DecodeHintType.CHARACTER_SET, "utf-8");
    }

    private QRCodeDecoder() {
    }

    public static String syncDecodeQRCode(String str) {
        return syncDecodeQRCode(getDecodeAbleBitmap(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[SYNTHETIC, Splitter:B:11:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String syncDecodeQRCode(android.graphics.Bitmap r12) {
        /*
            r0 = 0
            int r9 = r12.getWidth()     // Catch:{ Exception -> 0x0039 }
            int r10 = r12.getHeight()     // Catch:{ Exception -> 0x0039 }
            int r1 = r9 * r10
            int[] r11 = new int[r1]     // Catch:{ Exception -> 0x0039 }
            r3 = 0
            r5 = 0
            r6 = 0
            r1 = r12
            r2 = r11
            r4 = r9
            r7 = r9
            r8 = r10
            r1.getPixels(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0039 }
            com.google.zxing.RGBLuminanceSource r12 = new com.google.zxing.RGBLuminanceSource     // Catch:{ Exception -> 0x0039 }
            r12.<init>(r9, r10, r11)     // Catch:{ Exception -> 0x0039 }
            com.google.zxing.MultiFormatReader r1 = new com.google.zxing.MultiFormatReader     // Catch:{ Exception -> 0x0037 }
            r1.<init>()     // Catch:{ Exception -> 0x0037 }
            com.google.zxing.BinaryBitmap r2 = new com.google.zxing.BinaryBitmap     // Catch:{ Exception -> 0x0037 }
            com.google.zxing.common.HybridBinarizer r3 = new com.google.zxing.common.HybridBinarizer     // Catch:{ Exception -> 0x0037 }
            r3.<init>(r12)     // Catch:{ Exception -> 0x0037 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0037 }
            java.util.Map<com.google.zxing.DecodeHintType, java.lang.Object> r3 = HINTS     // Catch:{ Exception -> 0x0037 }
            com.google.zxing.Result r1 = r1.decode(r2, r3)     // Catch:{ Exception -> 0x0037 }
            java.lang.String r1 = r1.getText()     // Catch:{ Exception -> 0x0037 }
            return r1
        L_0x0037:
            r1 = move-exception
            goto L_0x003b
        L_0x0039:
            r1 = move-exception
            r12 = r0
        L_0x003b:
            r1.printStackTrace()
            if (r12 == 0) goto L_0x005e
            com.google.zxing.MultiFormatReader r1 = new com.google.zxing.MultiFormatReader     // Catch:{ Throwable -> 0x005a }
            r1.<init>()     // Catch:{ Throwable -> 0x005a }
            com.google.zxing.BinaryBitmap r2 = new com.google.zxing.BinaryBitmap     // Catch:{ Throwable -> 0x005a }
            com.google.zxing.common.GlobalHistogramBinarizer r3 = new com.google.zxing.common.GlobalHistogramBinarizer     // Catch:{ Throwable -> 0x005a }
            r3.<init>(r12)     // Catch:{ Throwable -> 0x005a }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x005a }
            java.util.Map<com.google.zxing.DecodeHintType, java.lang.Object> r12 = HINTS     // Catch:{ Throwable -> 0x005a }
            com.google.zxing.Result r12 = r1.decode(r2, r12)     // Catch:{ Throwable -> 0x005a }
            java.lang.String r12 = r12.getText()     // Catch:{ Throwable -> 0x005a }
            return r12
        L_0x005a:
            r12 = move-exception
            r12.printStackTrace()
        L_0x005e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.bingoogolapple.qrcode.zxing.QRCodeDecoder.syncDecodeQRCode(android.graphics.Bitmap):java.lang.String");
    }

    private static Bitmap getDecodeAbleBitmap(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i2 = options.outHeight / BuildConfig.VERSION_CODE;
            if (i2 > 0) {
                i = i2;
            }
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception unused) {
            return null;
        }
    }
}
