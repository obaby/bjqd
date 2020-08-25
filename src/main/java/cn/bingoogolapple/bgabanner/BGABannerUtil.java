package cn.bingoogolapple.bgabanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import java.util.Collection;
import java.util.List;

public class BGABannerUtil {
    private BGABannerUtil() {
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public static ImageView getItemImageView(Context context, @DrawableRes int i, BGALocalImageSize bGALocalImageSize, ImageView.ScaleType scaleType) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(getScaledImageFromResource(context, i, bGALocalImageSize.getMaxWidth(), bGALocalImageSize.getMaxHeight(), bGALocalImageSize.getMinWidth(), bGALocalImageSize.getMinHeight()));
        imageView.setClickable(true);
        imageView.setScaleType(scaleType);
        return imageView;
    }

    public static void resetPageTransformer(List<? extends View> list) {
        if (list != null) {
            for (View view : list) {
                view.setVisibility(0);
                ViewCompat.setAlpha(view, 1.0f);
                ViewCompat.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
                ViewCompat.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setTranslationY(view, 0.0f);
                ViewCompat.setScaleX(view, 1.0f);
                ViewCompat.setScaleY(view, 1.0f);
                ViewCompat.setRotationX(view, 0.0f);
                ViewCompat.setRotationY(view, 0.0f);
                ViewCompat.setRotation(view, 0.0f);
            }
        }
    }

    public static boolean isIndexNotOutOfBounds(int i, Collection collection) {
        return isCollectionNotEmpty(collection, new Collection[0]) && i < collection.size();
    }

    public static boolean isCollectionEmpty(Collection collection, Collection... collectionArr) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        for (Collection collection2 : collectionArr) {
            if (collection2 == null || collection2.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCollectionNotEmpty(Collection collection, Collection... collectionArr) {
        return !isCollectionEmpty(collection, collectionArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000b  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getScaledImageFromResource(@android.support.annotation.NonNull android.content.Context r2, int r3, int r4, int r5, float r6, float r7) {
        /*
        L_0x0000:
            cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair r0 = getImageFromResource(r2, r3, r4, r5)
            if (r0 == 0) goto L_0x000b
            java.lang.Object r1 = r0.first
            if (r1 == 0) goto L_0x000b
            goto L_0x0021
        L_0x000b:
            int r4 = r4 / 2
            int r5 = r5 / 2
            if (r0 == 0) goto L_0x0021
            java.lang.Object r1 = r0.second
            boolean r1 = r1 instanceof java.lang.OutOfMemoryError
            if (r1 == 0) goto L_0x0021
            float r1 = (float) r4
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x0021
            float r1 = (float) r5
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 > 0) goto L_0x0000
        L_0x0021:
            if (r0 != 0) goto L_0x0025
            r2 = 0
            return r2
        L_0x0025:
            java.lang.Object r2 = r0.first
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.bingoogolapple.bgabanner.BGABannerUtil.getScaledImageFromResource(android.content.Context, int, int, int, float, float):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ab A[SYNTHETIC, Splitter:B:43:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c0 A[SYNTHETIC, Splitter:B:52:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c7 A[SYNTHETIC, Splitter:B:56:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:40:0x00a1=Splitter:B:40:0x00a1, B:49:0x00b6=Splitter:B:49:0x00b6} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.bingoogolapple.bgabanner.BGABannerUtil.LoadBitmapPair<java.lang.Throwable> getImageFromResource(@android.support.annotation.NonNull android.content.Context r8, int r9, int r10, int r11) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565
            r2 = 0
            if (r10 != 0) goto L_0x0029
            if (r11 != 0) goto L_0x0029
            r0.inPreferredConfig = r1     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            java.io.InputStream r8 = r8.openRawResource(r9)     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair r9 = new cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeStream(r8, r2, r0)     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r9.<init>(r10, r2)     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r8.close()     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            goto L_0x0087
        L_0x0023:
            r9 = move-exception
            goto L_0x00a1
        L_0x0026:
            r9 = move-exception
            goto L_0x00b6
        L_0x0029:
            r3 = 1
            r0.inJustDecodeBounds = r3     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            r0.inPreferredConfig = r1     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            android.content.res.Resources r4 = r8.getResources()     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            java.io.InputStream r4 = r4.openRawResource(r9)     // Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x009f, all -> 0x009c }
            android.graphics.BitmapFactory.decodeStream(r4, r2, r0)     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            r4.reset()     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            r4.close()     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            int r5 = r0.outWidth     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            int r6 = r0.outHeight     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            int r7 = getResizedDimension(r10, r11, r5, r6)     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            int r10 = getResizedDimension(r11, r10, r6, r5)     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            r11 = 0
            r0.inJustDecodeBounds = r11     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            int r11 = calculateInSampleSize(r0, r7, r10)     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            r0.inSampleSize = r11     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            r0.inPreferredConfig = r1     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            java.io.InputStream r8 = r8.openRawResource(r9)     // Catch:{ OutOfMemoryError -> 0x0099, Exception -> 0x0096, all -> 0x0093 }
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r8, r2, r0)     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r8.close()     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            if (r9 == 0) goto L_0x0081
            int r11 = r9.getWidth()     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            if (r11 > r7) goto L_0x0073
            int r11 = r9.getHeight()     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            if (r11 <= r10) goto L_0x0081
        L_0x0073:
            cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair r11 = new cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createScaledBitmap(r9, r7, r10, r3)     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r11.<init>(r10, r2)     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r9.recycle()     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r9 = r11
            goto L_0x0087
        L_0x0081:
            cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair r10 = new cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r10.<init>(r9, r2)     // Catch:{ OutOfMemoryError -> 0x0026, Exception -> 0x0023 }
            r9 = r10
        L_0x0087:
            if (r8 == 0) goto L_0x0091
            r8.close()     // Catch:{ IOException -> 0x008d }
            goto L_0x0091
        L_0x008d:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0091:
            r10 = r9
            goto L_0x00c3
        L_0x0093:
            r9 = move-exception
            r8 = r4
            goto L_0x00c5
        L_0x0096:
            r9 = move-exception
            r8 = r4
            goto L_0x00a1
        L_0x0099:
            r9 = move-exception
            r8 = r4
            goto L_0x00b6
        L_0x009c:
            r9 = move-exception
            r8 = r2
            goto L_0x00c5
        L_0x009f:
            r9 = move-exception
            r8 = r2
        L_0x00a1:
            r9.printStackTrace()     // Catch:{ all -> 0x00c4 }
            cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair r10 = new cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair     // Catch:{ all -> 0x00c4 }
            r10.<init>(r2, r9)     // Catch:{ all -> 0x00c4 }
            if (r8 == 0) goto L_0x00c3
            r8.close()     // Catch:{ IOException -> 0x00af }
            goto L_0x00c3
        L_0x00af:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x00c3
        L_0x00b4:
            r9 = move-exception
            r8 = r2
        L_0x00b6:
            r9.printStackTrace()     // Catch:{ all -> 0x00c4 }
            cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair r10 = new cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair     // Catch:{ all -> 0x00c4 }
            r10.<init>(r2, r9)     // Catch:{ all -> 0x00c4 }
            if (r8 == 0) goto L_0x00c3
            r8.close()     // Catch:{ IOException -> 0x00af }
        L_0x00c3:
            return r10
        L_0x00c4:
            r9 = move-exception
        L_0x00c5:
            if (r8 == 0) goto L_0x00cf
            r8.close()     // Catch:{ IOException -> 0x00cb }
            goto L_0x00cf
        L_0x00cb:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00cf:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.bingoogolapple.bgabanner.BGABannerUtil.getImageFromResource(android.content.Context, int, int, int):cn.bingoogolapple.bgabanner.BGABannerUtil$LoadBitmapPair");
    }

    private static class LoadBitmapPair<S extends Throwable> extends Pair<Bitmap, S> {
        LoadBitmapPair(@Nullable Bitmap bitmap, @Nullable S s) {
            super(bitmap, s);
        }
    }

    public static int getResizedDimension(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (i == 0) {
            double d = (double) i2;
            double d2 = (double) i4;
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = (double) i3;
            Double.isNaN(d3);
            return (int) (d3 * (d / d2));
        } else if (i2 == 0) {
            return i;
        } else {
            double d4 = (double) i4;
            double d5 = (double) i3;
            Double.isNaN(d4);
            Double.isNaN(d5);
            double d6 = d4 / d5;
            double d7 = (double) i;
            Double.isNaN(d7);
            double d8 = (double) i2;
            if (d7 * d6 <= d8) {
                return i;
            }
            Double.isNaN(d8);
            return (int) (d8 / d6);
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = 1;
        if (i == 0 || i2 == 0) {
            return 1;
        }
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            int i6 = i4 / 2;
            int i7 = i5 / 2;
            while (i6 / i3 >= i2 && i7 / i3 >= i) {
                i3 *= 2;
            }
        }
        return i3;
    }
}
