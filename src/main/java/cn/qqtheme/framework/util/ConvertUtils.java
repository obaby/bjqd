package cn.qqtheme.framework.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import cn.xports.qd2.entity.K;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ConvertUtils {
    public static final long GB = 1073741824;
    public static final long KB = 1024;
    public static final long MB = 1048576;

    public static String toColorString(int i) {
        return toColorString(i, false);
    }

    public static String toColorString(int i, boolean z) {
        String hexString = Integer.toHexString(Color.alpha(i));
        String hexString2 = Integer.toHexString(Color.red(i));
        String hexString3 = Integer.toHexString(Color.green(i));
        String hexString4 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = K.k0 + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = K.k0 + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = K.k0 + hexString3;
        }
        if (hexString4.length() == 1) {
            hexString4 = K.k0 + hexString4;
        }
        if (z) {
            String str = hexString + hexString2 + hexString3 + hexString4;
            LogUtils.verbose(String.format(Locale.CHINA, "%d to color string is %s", new Object[]{Integer.valueOf(i), str}));
            return str;
        }
        String str2 = hexString2 + hexString3 + hexString4;
        LogUtils.verbose(String.format(Locale.CHINA, "%d to color string is %s%s%s%s, exclude alpha is %s", new Object[]{Integer.valueOf(i), hexString, hexString2, hexString3, hexString4, str2}));
        return str2;
    }

    public static String toDateString(Date date, String str) {
        return new SimpleDateFormat(str, Locale.CHINA).format(date);
    }

    public static String toDateString(String str) {
        return toDateString(Calendar.getInstance(Locale.CHINA).getTime(), str);
    }

    public static Date toDate(String str) {
        return DateUtils.parseDate(str);
    }

    public static long toTimemillis(String str) {
        return toDate(str).getTime();
    }

    public static String toSlashString(String str) {
        String str2 = "";
        for (char c2 : str.toCharArray()) {
            if (c2 == '\"' || c2 == '\'' || c2 == '\\') {
                str2 = str2 + "\\";
            }
            str2 = str2 + c2;
        }
        return str2;
    }

    public static <T> T[] toArray(List<T> list) {
        return list.toArray();
    }

    public static <T> List<T> toList(T[] tArr) {
        return Arrays.asList(tArr);
    }

    public static String toString(Object[] objArr) {
        return Arrays.deepToString(objArr);
    }

    public static String toString(Object[] objArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (Object append : objArr) {
            sb.append(append);
            sb.append(str);
        }
        return sb.toString();
    }

    public static byte[] toByteArray(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[100];
            while (true) {
                int read = inputStream.read(bArr, 0, 100);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArray;
                }
            }
        } catch (IOException e) {
            LogUtils.warn((Throwable) e);
            return null;
        }
    }

    public static byte[] toByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            LogUtils.warn((Throwable) e);
        }
        return byteArray;
    }

    public static Bitmap toBitmap(byte[] bArr, int i, int i2) {
        if (bArr.length != 0) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inDither = false;
                options.inPreferredConfig = null;
                if (i > 0 && i2 > 0) {
                    options.outWidth = i;
                    options.outHeight = i2;
                }
                return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            } catch (Exception e) {
                LogUtils.error((Throwable) e);
            }
        }
        return null;
    }

    public static Bitmap toBitmap(byte[] bArr) {
        return toBitmap(bArr, -1, -1);
    }

    @TargetApi(11)
    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof ColorDrawable) {
            Bitmap createBitmap = Bitmap.createBitmap(32, 32, Bitmap.Config.ARGB_8888);
            if (Build.VERSION.SDK_INT >= 11) {
                new Canvas(createBitmap).drawColor(((ColorDrawable) drawable).getColor());
            }
            return createBitmap;
        } else if (!(drawable instanceof NinePatchDrawable)) {
            return null;
        } else {
            Bitmap createBitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap2);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return createBitmap2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cb  */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toPath(android.content.Context r10, android.net.Uri r11) {
        /*
            if (r11 != 0) goto L_0x000a
            java.lang.String r10 = "uri is null"
            cn.qqtheme.framework.util.LogUtils.verbose(r10)
            java.lang.String r10 = ""
            return r10
        L_0x000a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "uri: "
            r0.append(r1)
            java.lang.String r1 = r11.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            cn.qqtheme.framework.util.LogUtils.verbose(r0)
            java.lang.String r0 = r11.getPath()
            java.lang.String r1 = r11.getScheme()
            java.lang.String r2 = r11.getAuthority()
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 19
            r5 = 0
            r6 = 1
            if (r3 < r4) goto L_0x0038
            r3 = 1
            goto L_0x0039
        L_0x0038:
            r3 = 0
        L_0x0039:
            r4 = 0
            if (r3 == 0) goto L_0x00ee
            boolean r3 = android.provider.DocumentsContract.isDocumentUri(r10, r11)
            if (r3 == 0) goto L_0x00ee
            java.lang.String r11 = android.provider.DocumentsContract.getDocumentId(r11)
            java.lang.String r1 = ":"
            java.lang.String[] r1 = r11.split(r1)
            r3 = r1[r5]
            r7 = -1
            int r8 = r2.hashCode()
            r9 = 320699453(0x131d7c3d, float:1.987744E-27)
            if (r8 == r9) goto L_0x0077
            r9 = 596745902(0x23919eae, float:1.5788135E-17)
            if (r8 == r9) goto L_0x006d
            r9 = 1734583286(0x6763a3f6, float:1.07500174E24)
            if (r8 == r9) goto L_0x0063
            goto L_0x0081
        L_0x0063:
            java.lang.String r8 = "com.android.providers.media.documents"
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0081
            r2 = 2
            goto L_0x0082
        L_0x006d:
            java.lang.String r8 = "com.android.externalstorage.documents"
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0081
            r2 = 0
            goto L_0x0082
        L_0x0077:
            java.lang.String r8 = "com.android.providers.downloads.documents"
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0081
            r2 = 1
            goto L_0x0082
        L_0x0081:
            r2 = -1
        L_0x0082:
            switch(r2) {
                case 0: goto L_0x00cb;
                case 1: goto L_0x00b4;
                case 2: goto L_0x0087;
                default: goto L_0x0085;
            }
        L_0x0085:
            goto L_0x0115
        L_0x0087:
            java.lang.String r11 = "image"
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x0092
            android.net.Uri r4 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x00a7
        L_0x0092:
            java.lang.String r11 = "video"
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x009d
            android.net.Uri r4 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x00a7
        L_0x009d:
            java.lang.String r11 = "audio"
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x00a7
            android.net.Uri r4 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L_0x00a7:
            java.lang.String r11 = "_id=?"
            java.lang.String[] r0 = new java.lang.String[r6]
            r1 = r1[r6]
            r0[r5] = r1
            java.lang.String r10 = _queryPathFromMediaStore(r10, r4, r11, r0)
            return r10
        L_0x00b4:
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            long r1 = r11.longValue()
            android.net.Uri r11 = android.content.ContentUris.withAppendedId(r0, r1)
            java.lang.String r10 = _queryPathFromMediaStore(r10, r11, r4, r4)
            return r10
        L_0x00cb:
            java.lang.String r10 = "primary"
            boolean r10 = r10.equalsIgnoreCase(r3)
            if (r10 == 0) goto L_0x0115
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.io.File r11 = android.os.Environment.getExternalStorageDirectory()
            r10.append(r11)
            java.lang.String r11 = "/"
            r10.append(r11)
            r11 = r1[r6]
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            return r10
        L_0x00ee:
            java.lang.String r3 = "content"
            boolean r3 = r3.equalsIgnoreCase(r1)
            if (r3 == 0) goto L_0x0108
            java.lang.String r0 = "com.google.android.apps.photos.content"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0103
            java.lang.String r10 = r11.getLastPathSegment()
            return r10
        L_0x0103:
            java.lang.String r10 = _queryPathFromMediaStore(r10, r11, r4, r4)
            return r10
        L_0x0108:
            java.lang.String r10 = "file"
            boolean r10 = r10.equalsIgnoreCase(r1)
            if (r10 == 0) goto L_0x0115
            java.lang.String r10 = r11.getPath()
            return r10
        L_0x0115:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "uri to path: "
            r10.append(r11)
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            cn.qqtheme.framework.util.LogUtils.verbose(r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.qqtheme.framework.util.ConvertUtils.toPath(android.content.Context, android.net.Uri):java.lang.String");
    }

    private static String _queryPathFromMediaStore(Context context, Uri uri, String str, String[] strArr) {
        String str2;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, (String) null);
            if (query == null) {
                return null;
            }
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
            query.moveToFirst();
            str2 = query.getString(columnIndexOrThrow);
            try {
                query.close();
                return str2;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            str2 = null;
            LogUtils.error((Throwable) e);
            return str2;
        }
    }

    public static Bitmap toBitmap(View view) {
        int i;
        int width = view.getWidth();
        int height = view.getHeight();
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            i = 0;
            for (int i2 = 0; i2 < listView.getChildCount(); i2++) {
                i += listView.getChildAt(i2).getHeight();
            }
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            int i3 = 0;
            for (int i4 = 0; i4 < scrollView.getChildCount(); i4++) {
                i3 = i + scrollView.getChildAt(i4).getHeight();
            }
        } else {
            i = height;
        }
        view.setDrawingCacheEnabled(true);
        view.clearFocus();
        view.setPressed(false);
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int drawingCacheBackgroundColor = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(-1);
        if (drawingCacheBackgroundColor != -1) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(drawingCache, 0.0f, 0.0f, (Paint) null);
        canvas.save(31);
        canvas.restore();
        if (!createBitmap.isRecycled()) {
            LogUtils.verbose("recycle bitmap: " + createBitmap.toString());
            createBitmap.recycle();
        }
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheBackgroundColor(drawingCacheBackgroundColor);
        return createBitmap;
    }

    public static Drawable toDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable((Resources) null, bitmap);
    }

    public static byte[] toByteArray(Drawable drawable) {
        return toByteArray(toBitmap(drawable));
    }

    public static Drawable toDrawable(byte[] bArr) {
        return toDrawable(toBitmap(bArr));
    }

    public static int toPx(Context context, float f) {
        int i = (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
        LogUtils.verbose(f + " dp == " + i + " px");
        return i;
    }

    public static int toPx(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int toDp(Context context, float f) {
        int i = (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
        LogUtils.verbose(f + " px == " + i + " dp");
        return i;
    }

    public static int toSp(Context context, float f) {
        int i = (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
        LogUtils.verbose(f + " px == " + i + " sp");
        return i;
    }

    public static String toGbk(String str) {
        try {
            return new String(str.getBytes("utf-8"), "gbk");
        } catch (UnsupportedEncodingException e) {
            LogUtils.warn(e.getMessage());
            return str;
        }
    }

    public static String toFileSizeString(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j < 1024) {
            return j + "B";
        } else if (j < 1048576) {
            StringBuilder sb = new StringBuilder();
            double d = (double) j;
            Double.isNaN(d);
            sb.append(decimalFormat.format(d / 1024.0d));
            sb.append("K");
            return sb.toString();
        } else if (j < GB) {
            StringBuilder sb2 = new StringBuilder();
            double d2 = (double) j;
            Double.isNaN(d2);
            sb2.append(decimalFormat.format(d2 / 1048576.0d));
            sb2.append("M");
            return sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            double d3 = (double) j;
            Double.isNaN(d3);
            sb3.append(decimalFormat.format(d3 / 1.073741824E9d));
            sb3.append("G");
            return sb3.toString();
        }
    }

    public static String toString(InputStream inputStream, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
                stringBuffer.append("\n");
            }
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            LogUtils.error((Throwable) e);
        }
        return stringBuffer.toString();
    }

    public static String toString(InputStream inputStream) {
        return toString(inputStream, "utf-8");
    }

    public static ShapeDrawable toRoundDrawable(@ColorInt int i, int i2) {
        float f = (float) i2;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, (RectF) null, (float[]) null));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    public static StateListDrawable toStateRoundDrawable(@ColorInt int i, int i2) {
        return toStateListDrawable((Drawable) new ColorDrawable(0), (Drawable) toRoundDrawable(i, i2));
    }

    public static StateListDrawable toStateRoundDrawable(@ColorInt int i, @ColorInt int i2, int i3) {
        return toStateListDrawable((Drawable) toRoundDrawable(i, i3), (Drawable) toRoundDrawable(i2, i3));
    }

    public static ColorStateList toColorStateList(@ColorInt int i, @ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        int[] iArr = {i2, i3, i, i3, i4, i};
        return new ColorStateList(new int[][]{new int[]{16842919, 16842910}, new int[]{16842910, 16842908}, new int[]{16842910}, new int[]{16842908}, new int[]{16842909}, new int[0]}, iArr);
    }

    public static ColorStateList toColorStateList(@ColorInt int i, @ColorInt int i2) {
        return toColorStateList(i, i2, i2, i);
    }

    public static StateListDrawable toStateListDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919, 16842910}, drawable2);
        stateListDrawable.addState(new int[]{16842910, 16842908}, drawable3);
        stateListDrawable.addState(new int[]{16842910}, drawable);
        stateListDrawable.addState(new int[]{16842908}, drawable3);
        stateListDrawable.addState(new int[]{16842909}, drawable4);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static StateListDrawable toStateListDrawable(@ColorInt int i, @ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        ColorDrawable colorDrawable = new ColorDrawable(i);
        ColorDrawable colorDrawable2 = new ColorDrawable(i2);
        ColorDrawable colorDrawable3 = new ColorDrawable(i3);
        ColorDrawable colorDrawable4 = new ColorDrawable(i4);
        stateListDrawable.addState(new int[]{16842919, 16842910}, colorDrawable2);
        stateListDrawable.addState(new int[]{16842910, 16842908}, colorDrawable3);
        stateListDrawable.addState(new int[]{16842910}, colorDrawable);
        stateListDrawable.addState(new int[]{16842908}, colorDrawable3);
        stateListDrawable.addState(new int[]{16842909}, colorDrawable4);
        stateListDrawable.addState(new int[0], colorDrawable);
        return stateListDrawable;
    }

    public static StateListDrawable toStateListDrawable(Drawable drawable, Drawable drawable2) {
        return toStateListDrawable(drawable, drawable2, drawable2, drawable);
    }

    public static StateListDrawable toStateListDrawable(@ColorInt int i, @ColorInt int i2) {
        return toStateListDrawable(i, i2, i2, i);
    }

    public static StateListDrawable toStateListDrawable(@ColorInt int i) {
        return toStateListDrawable(0, i);
    }
}
