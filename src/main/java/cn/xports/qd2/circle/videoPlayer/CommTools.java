package cn.xports.qd2.circle.videoPlayer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CommTools {
    public static String LongToHms(long j) {
        return new SimpleDateFormat("mm:ss").format(Long.valueOf(j));
    }

    public static String LongToPoint(long j) {
        float parseFloat = Float.parseFloat(String.valueOf(j));
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        float f = (parseFloat / 1024.0f) / 1024.0f;
        if (f > 500.0f) {
            return decimalFormat.format((double) (f / 1024.0f)) + " G";
        }
        return decimalFormat.format((double) f) + " M";
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap createVideoThumbnail(java.lang.String r1, int r2, int r3) {
        /*
            android.media.MediaMetadataRetriever r2 = new android.media.MediaMetadataRetriever
            r2.<init>()
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ IllegalArgumentException | RuntimeException -> 0x0024, all -> 0x001f }
            r0 = 14
            if (r3 < r0) goto L_0x0014
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ IllegalArgumentException | RuntimeException -> 0x0024, all -> 0x001f }
            r3.<init>()     // Catch:{ IllegalArgumentException | RuntimeException -> 0x0024, all -> 0x001f }
            r2.setDataSource(r1, r3)     // Catch:{ IllegalArgumentException | RuntimeException -> 0x0024, all -> 0x001f }
            goto L_0x0017
        L_0x0014:
            r2.setDataSource(r1)     // Catch:{ IllegalArgumentException | RuntimeException -> 0x0024, all -> 0x001f }
        L_0x0017:
            android.graphics.Bitmap r1 = r2.getFrameAtTime()     // Catch:{ IllegalArgumentException | RuntimeException -> 0x0024, all -> 0x001f }
            r2.release()     // Catch:{ RuntimeException -> 0x0028 }
            goto L_0x0028
        L_0x001f:
            r1 = move-exception
            r2.release()     // Catch:{ RuntimeException -> 0x0023 }
        L_0x0023:
            throw r1
        L_0x0024:
            r2.release()     // Catch:{ RuntimeException -> 0x0027 }
        L_0x0027:
            r1 = 0
        L_0x0028:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.circle.videoPlayer.CommTools.createVideoThumbnail(java.lang.String, int, int):android.graphics.Bitmap");
    }
}
