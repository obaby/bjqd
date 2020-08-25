package cn.xports.photo_lib.widget.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropUtil {
    public static int getExifRotation(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 0);
            if (attributeInt == 3) {
                return Opcodes.GETFIELD;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (IOException unused) {
            return 0;
        }
    }

    public static int calculateBitmapSampleSize(Context context, String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int maxImageSize = getMaxImageSize(context);
        while (true) {
            if (options.outHeight / i <= maxImageSize && options.outWidth / i <= maxImageSize) {
                return i;
            }
            i <<= 1;
        }
    }

    private static int getMaxImageSize(Context context) {
        int i;
        int i2;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
            i = point.x;
            i2 = point.y;
        } else {
            int width = defaultDisplay.getWidth();
            i2 = defaultDisplay.getHeight();
            i = width;
        }
        return (int) Math.sqrt(Math.pow((double) i, 2.0d) + Math.pow((double) i2, 2.0d));
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d2 A[SYNTHETIC, Splitter:B:42:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00df A[SYNTHETIC, Splitter:B:51:0x00df] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap decodeRegionCrop(android.content.Context r20, java.lang.String r21, android.graphics.Rect r22, int r23, int r24, int r25) {
        /*
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00dc, all -> 0x00cd }
            r5 = r21
            r4.<init>(r5)     // Catch:{ Exception -> 0x00dc, all -> 0x00cd }
            r5 = 0
            android.graphics.BitmapRegionDecoder r6 = android.graphics.BitmapRegionDecoder.newInstance(r4, r5)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r7 = r6.getWidth()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r8 = r6.getHeight()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            if (r2 == 0) goto L_0x005d
            android.graphics.Matrix r9 = new android.graphics.Matrix     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r9.<init>()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r10 = -r2
            float r10 = (float) r10     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r9.setRotate(r10)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            android.graphics.RectF r10 = new android.graphics.RectF     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r10.<init>()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            android.graphics.RectF r11 = new android.graphics.RectF     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r12 = r22
            r11.<init>(r12)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r9.mapRect(r10, r11)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r9 = r10.left     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r11 = 0
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x003f
            float r7 = (float) r7     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            goto L_0x0040
        L_0x003f:
            r7 = 0
        L_0x0040:
            float r9 = r10.top     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L_0x0047
            float r11 = (float) r8     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
        L_0x0047:
            r10.offset(r7, r11)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            android.graphics.Rect r7 = new android.graphics.Rect     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r8 = r10.left     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r8 = (int) r8     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r9 = r10.top     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r9 = (int) r9     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r11 = r10.right     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r11 = (int) r11     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r10 = r10.bottom     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r10 = (int) r10     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r7.<init>(r8, r9, r11, r10)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r12 = r7
            goto L_0x005f
        L_0x005d:
            r12 = r22
        L_0x005f:
            int r7 = getMaxImageSize(r20)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r8 = 1
            r9 = 1
        L_0x0065:
            int r10 = r12.width()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r10 = r10 / r9
            if (r10 > r7) goto L_0x00c7
            int r10 = r12.height()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r10 = r10 / r9
            if (r10 <= r7) goto L_0x0074
            goto L_0x00c7
        L_0x0074:
            android.graphics.BitmapFactory$Options r7 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r7.<init>()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r7.inSampleSize = r9     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            android.graphics.Bitmap r13 = r6.decodeRegion(r12, r7)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            android.graphics.Matrix r6 = new android.graphics.Matrix     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r6.<init>()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            if (r2 == 0) goto L_0x008b
            float r5 = (float) r2     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r6.postRotate(r5)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r5 = 1
        L_0x008b:
            if (r0 <= 0) goto L_0x00a6
            if (r1 <= 0) goto L_0x00a6
            cn.xports.photo_lib.widget.crop.RotateBitmap r5 = new cn.xports.photo_lib.widget.crop.RotateBitmap     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r5.<init>(r13, r2)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r0 = (float) r0     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r2 = r5.getWidth()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r2 = (float) r2     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r0 = r0 / r2
            float r1 = (float) r1     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r2 = r5.getHeight()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r2 = (float) r2     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            float r1 = r1 / r2
            r6.postScale(r0, r1)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r5 = 1
        L_0x00a6:
            if (r5 == 0) goto L_0x00bc
            r14 = 0
            r15 = 0
            int r16 = r13.getWidth()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            int r17 = r13.getHeight()     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r19 = 1
            r18 = r6
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r13, r14, r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x00dd, all -> 0x00ca }
            r3 = r0
            goto L_0x00bd
        L_0x00bc:
            r3 = r13
        L_0x00bd:
            r4.close()     // Catch:{ IOException -> 0x00c1 }
            goto L_0x00e2
        L_0x00c1:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
            goto L_0x00e2
        L_0x00c7:
            int r9 = r9 << 1
            goto L_0x0065
        L_0x00ca:
            r0 = move-exception
            r1 = r0
            goto L_0x00d0
        L_0x00cd:
            r0 = move-exception
            r1 = r0
            r4 = r3
        L_0x00d0:
            if (r4 == 0) goto L_0x00db
            r4.close()     // Catch:{ IOException -> 0x00d6 }
            goto L_0x00db
        L_0x00d6:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x00db:
            throw r1
        L_0x00dc:
            r4 = r3
        L_0x00dd:
            if (r4 == 0) goto L_0x00e2
            r4.close()     // Catch:{ IOException -> 0x00c1 }
        L_0x00e2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.photo_lib.widget.crop.CropUtil.decodeRegionCrop(android.content.Context, java.lang.String, android.graphics.Rect, int, int, int):android.graphics.Bitmap");
    }

    public static String saveBmp(Bitmap bitmap, String str, String str2) {
        if (bitmap == null) {
            return null;
        }
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(str, str2);
            if (file2.exists()) {
                file2.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            bitmap.recycle();
            System.gc();
            return file2.getAbsolutePath();
        } catch (Exception e) {
            Log.e("CropUtils", "saveBmp(): savePath = " + str + "\nsaveName = " + str2 + "\n保存图片失败：" + e.toString());
            return null;
        }
    }
}
