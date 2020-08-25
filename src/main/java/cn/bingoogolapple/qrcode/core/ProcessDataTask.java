package cn.bingoogolapple.qrcode.core;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;

public class ProcessDataTask extends AsyncTask<Void, Void, String> {
    private Camera mCamera;
    private byte[] mData;
    private Delegate mDelegate;

    public interface Delegate {
        String processData(byte[] bArr, int i, int i2, boolean z);
    }

    public ProcessDataTask(Camera camera, byte[] bArr, Delegate delegate) {
        this.mCamera = camera;
        this.mData = bArr;
        this.mDelegate = delegate;
    }

    public ProcessDataTask perform() {
        if (Build.VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            execute(new Void[0]);
        }
        return this;
    }

    public void cancelTask() {
        if (getStatus() != AsyncTask.Status.FINISHED) {
            cancel(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        this.mDelegate = null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        return r9.mDelegate.processData(r1, r10, r0, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String doInBackground(java.lang.Void... r10) {
        /*
            r9 = this;
            android.hardware.Camera r10 = r9.mCamera
            android.hardware.Camera$Parameters r10 = r10.getParameters()
            android.hardware.Camera$Size r10 = r10.getPreviewSize()
            int r0 = r10.width
            int r10 = r10.height
            byte[] r1 = r9.mData
            int r1 = r1.length
            byte[] r1 = new byte[r1]
            r2 = 0
            r3 = 0
        L_0x0015:
            r4 = 1
            if (r3 >= r10) goto L_0x002f
            r5 = 0
        L_0x0019:
            if (r5 >= r0) goto L_0x002c
            int r6 = r5 * r10
            int r6 = r6 + r10
            int r6 = r6 - r3
            int r6 = r6 - r4
            byte[] r7 = r9.mData
            int r8 = r3 * r0
            int r8 = r8 + r5
            byte r7 = r7[r8]
            r1[r6] = r7
            int r5 = r5 + 1
            goto L_0x0019
        L_0x002c:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x002f:
            r3 = 0
            cn.bingoogolapple.qrcode.core.ProcessDataTask$Delegate r5 = r9.mDelegate     // Catch:{ Exception -> 0x003c }
            if (r5 != 0) goto L_0x0035
            return r3
        L_0x0035:
            cn.bingoogolapple.qrcode.core.ProcessDataTask$Delegate r5 = r9.mDelegate     // Catch:{ Exception -> 0x003c }
            java.lang.String r2 = r5.processData(r1, r10, r0, r2)     // Catch:{ Exception -> 0x003c }
            return r2
        L_0x003c:
            cn.bingoogolapple.qrcode.core.ProcessDataTask$Delegate r2 = r9.mDelegate     // Catch:{ Exception -> 0x0043 }
            java.lang.String r10 = r2.processData(r1, r10, r0, r4)     // Catch:{ Exception -> 0x0043 }
            return r10
        L_0x0043:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.bingoogolapple.qrcode.core.ProcessDataTask.doInBackground(java.lang.Void[]):java.lang.String");
    }
}
