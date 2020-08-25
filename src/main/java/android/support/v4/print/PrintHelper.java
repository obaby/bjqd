package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    private final PrintHelperVersionImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    private @interface ColorMode {
    }

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException;

        void setColorMode(int i);

        void setOrientation(int i);

        void setScaleMode(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface ScaleMode {
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    private static final class PrintHelperStub implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        }

        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) {
        }

        private PrintHelperStub() {
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mOrientation = 1;
        }

        public void setScaleMode(int i) {
            this.mScaleMode = i;
        }

        public int getScaleMode() {
            return this.mScaleMode;
        }

        public int getColorMode() {
            return this.mColorMode;
        }

        public void setColorMode(int i) {
            this.mColorMode = i;
        }

        public void setOrientation(int i) {
            this.mOrientation = i;
        }

        public int getOrientation() {
            return this.mOrientation;
        }
    }

    @RequiresApi(19)
    private static class PrintHelperApi19 implements PrintHelperVersionImpl {
        private static final String LOG_TAG = "PrintHelperApi19";
        private static final int MAX_PRINT_SIZE = 3500;
        int mColorMode = 2;
        final Context mContext;
        BitmapFactory.Options mDecodeOptions = null;
        protected boolean mIsMinMarginsHandlingCorrect = true;
        /* access modifiers changed from: private */
        public final Object mLock = new Object();
        int mOrientation;
        protected boolean mPrintActivityRespectsOrientation = true;
        int mScaleMode = 2;

        PrintHelperApi19(Context context) {
            this.mContext = context;
        }

        public void setScaleMode(int i) {
            this.mScaleMode = i;
        }

        public int getScaleMode() {
            return this.mScaleMode;
        }

        public void setColorMode(int i) {
            this.mColorMode = i;
        }

        public void setOrientation(int i) {
            this.mOrientation = i;
        }

        public int getOrientation() {
            if (this.mOrientation == 0) {
                return 1;
            }
            return this.mOrientation;
        }

        public int getColorMode() {
            return this.mColorMode;
        }

        /* access modifiers changed from: private */
        public static boolean isPortrait(Bitmap bitmap) {
            return bitmap.getWidth() <= bitmap.getHeight();
        }

        /* access modifiers changed from: protected */
        public PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
            PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
            if (printAttributes.getColorMode() != 0) {
                minMargins.setColorMode(printAttributes.getColorMode());
            }
            return minMargins;
        }

        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            PrintAttributes.MediaSize mediaSize;
            if (bitmap != null) {
                final int i = this.mScaleMode;
                PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
                if (isPortrait(bitmap)) {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
                } else {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
                }
                final String str2 = str;
                final Bitmap bitmap2 = bitmap;
                final OnPrintFinishCallback onPrintFinishCallback2 = onPrintFinishCallback;
                printManager.print(str, new PrintDocumentAdapter() {
                    private PrintAttributes mAttributes;

                    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                        this.mAttributes = printAttributes2;
                        layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(str2).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
                    }

                    public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                        PrintHelperApi19.this.writeBitmap(this.mAttributes, i, bitmap2, parcelFileDescriptor, cancellationSignal, writeResultCallback);
                    }

                    public void onFinish() {
                        if (onPrintFinishCallback2 != null) {
                            onPrintFinishCallback2.onFinish();
                        }
                    }
                }, new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
            }
        }

        /* access modifiers changed from: private */
        public Matrix getMatrix(int i, int i2, RectF rectF, int i3) {
            float f;
            Matrix matrix = new Matrix();
            float f2 = (float) i;
            float width = rectF.width() / f2;
            if (i3 == 2) {
                f = Math.max(width, rectF.height() / ((float) i2));
            } else {
                f = Math.min(width, rectF.height() / ((float) i2));
            }
            matrix.postScale(f, f);
            matrix.postTranslate((rectF.width() - (f2 * f)) / 2.0f, (rectF.height() - (((float) i2) * f)) / 2.0f);
            return matrix;
        }

        /* access modifiers changed from: private */
        public void writeBitmap(PrintAttributes printAttributes, int i, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            final PrintAttributes printAttributes2;
            if (this.mIsMinMarginsHandlingCorrect) {
                printAttributes2 = printAttributes;
            } else {
                printAttributes2 = copyAttributes(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
            }
            final CancellationSignal cancellationSignal2 = cancellationSignal;
            final Bitmap bitmap2 = bitmap;
            final PrintAttributes printAttributes3 = printAttributes;
            final int i2 = i;
            final ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
            final PrintDocumentAdapter.WriteResultCallback writeResultCallback2 = writeResultCallback;
            new AsyncTask<Void, Void, Throwable>() {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00ab */
                /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00cd */
                /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x00e2 */
                /* JADX WARNING: Removed duplicated region for block: B:27:0x00af A[Catch:{ all -> 0x00d5, Throwable -> 0x00ea }] */
                /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1 A[Catch:{ all -> 0x00d5, Throwable -> 0x00ea }] */
                /* JADX WARNING: Removed duplicated region for block: B:49:0x00e6 A[Catch:{ all -> 0x00d5, Throwable -> 0x00ea }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Throwable doInBackground(java.lang.Void... r9) {
                    /*
                        r8 = this;
                        android.os.CancellationSignal r9 = r2     // Catch:{ Throwable -> 0x00ea }
                        boolean r9 = r9.isCanceled()     // Catch:{ Throwable -> 0x00ea }
                        r0 = 0
                        if (r9 == 0) goto L_0x000a
                        return r0
                    L_0x000a:
                        android.print.pdf.PrintedPdfDocument r9 = new android.print.pdf.PrintedPdfDocument     // Catch:{ Throwable -> 0x00ea }
                        android.support.v4.print.PrintHelper$PrintHelperApi19 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.this     // Catch:{ Throwable -> 0x00ea }
                        android.content.Context r1 = r1.mContext     // Catch:{ Throwable -> 0x00ea }
                        android.print.PrintAttributes r2 = r3     // Catch:{ Throwable -> 0x00ea }
                        r9.<init>(r1, r2)     // Catch:{ Throwable -> 0x00ea }
                        android.support.v4.print.PrintHelper$PrintHelperApi19 r1 = android.support.v4.print.PrintHelper.PrintHelperApi19.this     // Catch:{ Throwable -> 0x00ea }
                        android.graphics.Bitmap r2 = r4     // Catch:{ Throwable -> 0x00ea }
                        android.print.PrintAttributes r3 = r3     // Catch:{ Throwable -> 0x00ea }
                        int r3 = r3.getColorMode()     // Catch:{ Throwable -> 0x00ea }
                        android.graphics.Bitmap r1 = r1.convertBitmapForColorMode(r2, r3)     // Catch:{ Throwable -> 0x00ea }
                        android.os.CancellationSignal r2 = r2     // Catch:{ Throwable -> 0x00ea }
                        boolean r2 = r2.isCanceled()     // Catch:{ Throwable -> 0x00ea }
                        if (r2 == 0) goto L_0x002c
                        return r0
                    L_0x002c:
                        r2 = 1
                        android.graphics.pdf.PdfDocument$Page r3 = r9.startPage(r2)     // Catch:{ all -> 0x00d5 }
                        android.support.v4.print.PrintHelper$PrintHelperApi19 r4 = android.support.v4.print.PrintHelper.PrintHelperApi19.this     // Catch:{ all -> 0x00d5 }
                        boolean r4 = r4.mIsMinMarginsHandlingCorrect     // Catch:{ all -> 0x00d5 }
                        if (r4 == 0) goto L_0x0045
                        android.graphics.RectF r2 = new android.graphics.RectF     // Catch:{ all -> 0x00d5 }
                        android.graphics.pdf.PdfDocument$PageInfo r4 = r3.getInfo()     // Catch:{ all -> 0x00d5 }
                        android.graphics.Rect r4 = r4.getContentRect()     // Catch:{ all -> 0x00d5 }
                        r2.<init>(r4)     // Catch:{ all -> 0x00d5 }
                        goto L_0x0068
                    L_0x0045:
                        android.print.pdf.PrintedPdfDocument r4 = new android.print.pdf.PrintedPdfDocument     // Catch:{ all -> 0x00d5 }
                        android.support.v4.print.PrintHelper$PrintHelperApi19 r5 = android.support.v4.print.PrintHelper.PrintHelperApi19.this     // Catch:{ all -> 0x00d5 }
                        android.content.Context r5 = r5.mContext     // Catch:{ all -> 0x00d5 }
                        android.print.PrintAttributes r6 = r5     // Catch:{ all -> 0x00d5 }
                        r4.<init>(r5, r6)     // Catch:{ all -> 0x00d5 }
                        android.graphics.pdf.PdfDocument$Page r2 = r4.startPage(r2)     // Catch:{ all -> 0x00d5 }
                        android.graphics.RectF r5 = new android.graphics.RectF     // Catch:{ all -> 0x00d5 }
                        android.graphics.pdf.PdfDocument$PageInfo r6 = r2.getInfo()     // Catch:{ all -> 0x00d5 }
                        android.graphics.Rect r6 = r6.getContentRect()     // Catch:{ all -> 0x00d5 }
                        r5.<init>(r6)     // Catch:{ all -> 0x00d5 }
                        r4.finishPage(r2)     // Catch:{ all -> 0x00d5 }
                        r4.close()     // Catch:{ all -> 0x00d5 }
                        r2 = r5
                    L_0x0068:
                        android.support.v4.print.PrintHelper$PrintHelperApi19 r4 = android.support.v4.print.PrintHelper.PrintHelperApi19.this     // Catch:{ all -> 0x00d5 }
                        int r5 = r1.getWidth()     // Catch:{ all -> 0x00d5 }
                        int r6 = r1.getHeight()     // Catch:{ all -> 0x00d5 }
                        int r7 = r6     // Catch:{ all -> 0x00d5 }
                        android.graphics.Matrix r4 = r4.getMatrix(r5, r6, r2, r7)     // Catch:{ all -> 0x00d5 }
                        android.support.v4.print.PrintHelper$PrintHelperApi19 r5 = android.support.v4.print.PrintHelper.PrintHelperApi19.this     // Catch:{ all -> 0x00d5 }
                        boolean r5 = r5.mIsMinMarginsHandlingCorrect     // Catch:{ all -> 0x00d5 }
                        if (r5 == 0) goto L_0x007f
                        goto L_0x008d
                    L_0x007f:
                        float r5 = r2.left     // Catch:{ all -> 0x00d5 }
                        float r6 = r2.top     // Catch:{ all -> 0x00d5 }
                        r4.postTranslate(r5, r6)     // Catch:{ all -> 0x00d5 }
                        android.graphics.Canvas r5 = r3.getCanvas()     // Catch:{ all -> 0x00d5 }
                        r5.clipRect(r2)     // Catch:{ all -> 0x00d5 }
                    L_0x008d:
                        android.graphics.Canvas r2 = r3.getCanvas()     // Catch:{ all -> 0x00d5 }
                        r2.drawBitmap(r1, r4, r0)     // Catch:{ all -> 0x00d5 }
                        r9.finishPage(r3)     // Catch:{ all -> 0x00d5 }
                        android.os.CancellationSignal r2 = r2     // Catch:{ all -> 0x00d5 }
                        boolean r2 = r2.isCanceled()     // Catch:{ all -> 0x00d5 }
                        if (r2 == 0) goto L_0x00b3
                        r9.close()     // Catch:{ Throwable -> 0x00ea }
                        android.os.ParcelFileDescriptor r9 = r7     // Catch:{ Throwable -> 0x00ea }
                        if (r9 == 0) goto L_0x00ab
                        android.os.ParcelFileDescriptor r9 = r7     // Catch:{ IOException -> 0x00ab }
                        r9.close()     // Catch:{ IOException -> 0x00ab }
                    L_0x00ab:
                        android.graphics.Bitmap r9 = r4     // Catch:{ Throwable -> 0x00ea }
                        if (r1 == r9) goto L_0x00b2
                        r1.recycle()     // Catch:{ Throwable -> 0x00ea }
                    L_0x00b2:
                        return r0
                    L_0x00b3:
                        java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x00d5 }
                        android.os.ParcelFileDescriptor r3 = r7     // Catch:{ all -> 0x00d5 }
                        java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x00d5 }
                        r2.<init>(r3)     // Catch:{ all -> 0x00d5 }
                        r9.writeTo(r2)     // Catch:{ all -> 0x00d5 }
                        r9.close()     // Catch:{ Throwable -> 0x00ea }
                        android.os.ParcelFileDescriptor r9 = r7     // Catch:{ Throwable -> 0x00ea }
                        if (r9 == 0) goto L_0x00cd
                        android.os.ParcelFileDescriptor r9 = r7     // Catch:{ IOException -> 0x00cd }
                        r9.close()     // Catch:{ IOException -> 0x00cd }
                    L_0x00cd:
                        android.graphics.Bitmap r9 = r4     // Catch:{ Throwable -> 0x00ea }
                        if (r1 == r9) goto L_0x00d4
                        r1.recycle()     // Catch:{ Throwable -> 0x00ea }
                    L_0x00d4:
                        return r0
                    L_0x00d5:
                        r0 = move-exception
                        r9.close()     // Catch:{ Throwable -> 0x00ea }
                        android.os.ParcelFileDescriptor r9 = r7     // Catch:{ Throwable -> 0x00ea }
                        if (r9 == 0) goto L_0x00e2
                        android.os.ParcelFileDescriptor r9 = r7     // Catch:{ IOException -> 0x00e2 }
                        r9.close()     // Catch:{ IOException -> 0x00e2 }
                    L_0x00e2:
                        android.graphics.Bitmap r9 = r4     // Catch:{ Throwable -> 0x00ea }
                        if (r1 == r9) goto L_0x00e9
                        r1.recycle()     // Catch:{ Throwable -> 0x00ea }
                    L_0x00e9:
                        throw r0     // Catch:{ Throwable -> 0x00ea }
                    L_0x00ea:
                        r9 = move-exception
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.support.v4.print.PrintHelper.PrintHelperApi19.AnonymousClass2.doInBackground(java.lang.Void[]):java.lang.Throwable");
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Throwable th) {
                    if (cancellationSignal2.isCanceled()) {
                        writeResultCallback2.onWriteCancelled();
                    } else if (th == null) {
                        writeResultCallback2.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    } else {
                        Log.e(PrintHelperApi19.LOG_TAG, "Error writing printed content", th);
                        writeResultCallback2.onWriteFailed((CharSequence) null);
                    }
                }
            }.execute(new Void[0]);
        }

        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
            final int i = this.mScaleMode;
            final String str2 = str;
            final Uri uri2 = uri;
            final OnPrintFinishCallback onPrintFinishCallback2 = onPrintFinishCallback;
            AnonymousClass3 r0 = new PrintDocumentAdapter() {
                /* access modifiers changed from: private */
                public PrintAttributes mAttributes;
                Bitmap mBitmap = null;
                AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

                public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                    synchronized (this) {
                        this.mAttributes = printAttributes2;
                    }
                    if (cancellationSignal.isCanceled()) {
                        layoutResultCallback.onLayoutCancelled();
                    } else if (this.mBitmap != null) {
                        layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(str2).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
                    } else {
                        final CancellationSignal cancellationSignal2 = cancellationSignal;
                        final PrintAttributes printAttributes3 = printAttributes2;
                        final PrintAttributes printAttributes4 = printAttributes;
                        final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
                        this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() {
                            /* access modifiers changed from: protected */
                            public void onPreExecute() {
                                cancellationSignal2.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                                    public void onCancel() {
                                        AnonymousClass3.this.cancelLoad();
                                        AnonymousClass1.this.cancel(false);
                                    }
                                });
                            }

                            /* access modifiers changed from: protected */
                            public Bitmap doInBackground(Uri... uriArr) {
                                try {
                                    return PrintHelperApi19.this.loadConstrainedBitmap(uri2);
                                } catch (FileNotFoundException unused) {
                                    return null;
                                }
                            }

                            /* access modifiers changed from: protected */
                            public void onPostExecute(Bitmap bitmap) {
                                PrintAttributes.MediaSize mediaSize;
                                super.onPostExecute(bitmap);
                                if (bitmap != null && (!PrintHelperApi19.this.mPrintActivityRespectsOrientation || PrintHelperApi19.this.mOrientation == 0)) {
                                    synchronized (this) {
                                        mediaSize = AnonymousClass3.this.mAttributes.getMediaSize();
                                    }
                                    if (!(mediaSize == null || mediaSize.isPortrait() == PrintHelperApi19.isPortrait(bitmap))) {
                                        Matrix matrix = new Matrix();
                                        matrix.postRotate(90.0f);
                                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                    }
                                }
                                AnonymousClass3.this.mBitmap = bitmap;
                                if (bitmap != null) {
                                    layoutResultCallback2.onLayoutFinished(new PrintDocumentInfo.Builder(str2).setContentType(1).setPageCount(1).build(), true ^ printAttributes3.equals(printAttributes4));
                                } else {
                                    layoutResultCallback2.onLayoutFailed((CharSequence) null);
                                }
                                AnonymousClass3.this.mLoadBitmap = null;
                            }

                            /* access modifiers changed from: protected */
                            public void onCancelled(Bitmap bitmap) {
                                layoutResultCallback2.onLayoutCancelled();
                                AnonymousClass3.this.mLoadBitmap = null;
                            }
                        }.execute(new Uri[0]);
                    }
                }

                /* access modifiers changed from: private */
                public void cancelLoad() {
                    synchronized (PrintHelperApi19.this.mLock) {
                        if (PrintHelperApi19.this.mDecodeOptions != null) {
                            PrintHelperApi19.this.mDecodeOptions.requestCancelDecode();
                            PrintHelperApi19.this.mDecodeOptions = null;
                        }
                    }
                }

                public void onFinish() {
                    super.onFinish();
                    cancelLoad();
                    if (this.mLoadBitmap != null) {
                        this.mLoadBitmap.cancel(true);
                    }
                    if (onPrintFinishCallback2 != null) {
                        onPrintFinishCallback2.onFinish();
                    }
                    if (this.mBitmap != null) {
                        this.mBitmap.recycle();
                        this.mBitmap = null;
                    }
                }

                public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                    PrintHelperApi19.this.writeBitmap(this.mAttributes, i, this.mBitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
                }
            };
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
            }
            printManager.print(str, r0, builder.build());
        }

        /* access modifiers changed from: private */
        public Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
            BitmapFactory.Options options;
            if (uri == null || this.mContext == null) {
                throw new IllegalArgumentException("bad argument to getScaledBitmap");
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            loadBitmap(uri, options2);
            int i = options2.outWidth;
            int i2 = options2.outHeight;
            if (i <= 0 || i2 <= 0) {
                return null;
            }
            int max = Math.max(i, i2);
            int i3 = 1;
            while (max > 3500) {
                max >>>= 1;
                i3 <<= 1;
            }
            if (i3 <= 0 || Math.min(i, i2) / i3 <= 0) {
                return null;
            }
            synchronized (this.mLock) {
                this.mDecodeOptions = new BitmapFactory.Options();
                this.mDecodeOptions.inMutable = true;
                this.mDecodeOptions.inSampleSize = i3;
                options = this.mDecodeOptions;
            }
            try {
                Bitmap loadBitmap = loadBitmap(uri, options);
                synchronized (this.mLock) {
                    this.mDecodeOptions = null;
                }
                return loadBitmap;
            } catch (Throwable th) {
                synchronized (this.mLock) {
                    this.mDecodeOptions = null;
                    throw th;
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x002a A[SYNTHETIC, Splitter:B:18:0x002a] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.graphics.Bitmap loadBitmap(android.net.Uri r3, android.graphics.BitmapFactory.Options r4) throws java.io.FileNotFoundException {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x0037
                android.content.Context r0 = r2.mContext
                if (r0 == 0) goto L_0x0037
                r0 = 0
                android.content.Context r1 = r2.mContext     // Catch:{ all -> 0x0027 }
                android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ all -> 0x0027 }
                java.io.InputStream r3 = r1.openInputStream(r3)     // Catch:{ all -> 0x0027 }
                android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r3, r0, r4)     // Catch:{ all -> 0x0024 }
                if (r3 == 0) goto L_0x0023
                r3.close()     // Catch:{ IOException -> 0x001b }
                goto L_0x0023
            L_0x001b:
                r3 = move-exception
                java.lang.String r0 = "PrintHelperApi19"
                java.lang.String r1 = "close fail "
                android.util.Log.w(r0, r1, r3)
            L_0x0023:
                return r4
            L_0x0024:
                r4 = move-exception
                r0 = r3
                goto L_0x0028
            L_0x0027:
                r4 = move-exception
            L_0x0028:
                if (r0 == 0) goto L_0x0036
                r0.close()     // Catch:{ IOException -> 0x002e }
                goto L_0x0036
            L_0x002e:
                r3 = move-exception
                java.lang.String r0 = "PrintHelperApi19"
                java.lang.String r1 = "close fail "
                android.util.Log.w(r0, r1, r3)
            L_0x0036:
                throw r4
            L_0x0037:
                java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
                java.lang.String r4 = "bad argument to loadBitmap"
                r3.<init>(r4)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.print.PrintHelper.PrintHelperApi19.loadBitmap(android.net.Uri, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
        }

        /* access modifiers changed from: private */
        public Bitmap convertBitmapForColorMode(Bitmap bitmap, int i) {
            if (i != 1) {
                return bitmap;
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            canvas.setBitmap((Bitmap) null);
            return createBitmap;
        }
    }

    @RequiresApi(20)
    private static class PrintHelperApi20 extends PrintHelperApi19 {
        PrintHelperApi20(Context context) {
            super(context);
            this.mPrintActivityRespectsOrientation = false;
        }
    }

    @RequiresApi(23)
    private static class PrintHelperApi23 extends PrintHelperApi20 {
        /* access modifiers changed from: protected */
        public PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
            PrintAttributes.Builder copyAttributes = super.copyAttributes(printAttributes);
            if (printAttributes.getDuplexMode() != 0) {
                copyAttributes.setDuplexMode(printAttributes.getDuplexMode());
            }
            return copyAttributes;
        }

        PrintHelperApi23(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = false;
        }
    }

    @RequiresApi(24)
    private static class PrintHelperApi24 extends PrintHelperApi23 {
        PrintHelperApi24(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = true;
            this.mPrintActivityRespectsOrientation = true;
        }
    }

    public PrintHelper(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mImpl = new PrintHelperApi24(context);
        } else if (Build.VERSION.SDK_INT >= 23) {
            this.mImpl = new PrintHelperApi23(context);
        } else if (Build.VERSION.SDK_INT >= 20) {
            this.mImpl = new PrintHelperApi20(context);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mImpl = new PrintHelperApi19(context);
        } else {
            this.mImpl = new PrintHelperStub();
        }
    }

    public void setScaleMode(int i) {
        this.mImpl.setScaleMode(i);
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void setColorMode(int i) {
        this.mImpl.setColorMode(i);
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public void setOrientation(int i) {
        this.mImpl.setOrientation(i);
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public void printBitmap(String str, Bitmap bitmap) {
        this.mImpl.printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        this.mImpl.printBitmap(str, bitmap, onPrintFinishCallback);
    }

    public void printBitmap(String str, Uri uri) throws FileNotFoundException {
        this.mImpl.printBitmap(str, uri, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        this.mImpl.printBitmap(str, uri, onPrintFinishCallback);
    }
}
