package com.bsit.zxing.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import anet.channel.entity.ConnType;
import com.bsit.zxing.common.Runnable;
import com.bsit.zxing.config.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

final class AutoFocusManager implements Camera.AutoFocusCallback {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF = new ArrayList(2);
    private static final String TAG = "AutoFocusManager";
    private final Camera camera;
    private boolean focusing;
    private AsyncTask<?, ?, ?> outstandingTask;
    private boolean stopped;
    private final boolean useAutoFocus;

    static {
        FOCUS_MODES_CALLING_AF.add(ConnType.PK_AUTO);
        FOCUS_MODES_CALLING_AF.add("macro");
    }

    AutoFocusManager(Context context, Camera camera2) {
        this.camera = camera2;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera2.getParameters().getFocusMode();
        boolean z = true;
        this.useAutoFocus = (!defaultSharedPreferences.getBoolean(Config.KEY_AUTO_FOCUS, true) || !FOCUS_MODES_CALLING_AF.contains(focusMode)) ? false : z;
        String str = TAG;
        Log.i(str, "Current focus mode '" + focusMode + "'; use auto focus? " + this.useAutoFocus);
        start();
    }

    public synchronized void onAutoFocus(boolean z, Camera camera2) {
        this.focusing = false;
        autoFocusAgainLater();
    }

    private synchronized void autoFocusAgainLater() {
        if (!this.stopped && this.outstandingTask == null) {
            AutoFocusTask autoFocusTask = new AutoFocusTask();
            try {
                this.outstandingTask = autoFocusTask;
                Runnable.execAsync(autoFocusTask);
            } catch (RejectedExecutionException e) {
                Log.w(TAG, "Could not request auto focus", e);
                this.outstandingTask = null;
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public synchronized void start() {
        if (this.useAutoFocus) {
            this.outstandingTask = null;
            if (!this.stopped && !this.focusing) {
                try {
                    this.camera.autoFocus(this);
                    this.focusing = true;
                } catch (RuntimeException e) {
                    Log.w(TAG, "Unexpected exception while focusing", e);
                    autoFocusAgainLater();
                }
            }
        }
        return;
    }

    private synchronized void cancelOutstandingTask() {
        if (this.outstandingTask != null) {
            if (this.outstandingTask.getStatus() != AsyncTask.Status.FINISHED) {
                this.outstandingTask.cancel(true);
            }
            this.outstandingTask = null;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void stop() {
        this.stopped = true;
        if (this.useAutoFocus) {
            cancelOutstandingTask();
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
        return;
    }

    private final class AutoFocusTask extends AsyncTask<Object, Object, Object> {
        private AutoFocusTask() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(AutoFocusManager.AUTO_FOCUS_INTERVAL_MS);
            } catch (InterruptedException unused) {
            }
            AutoFocusManager.this.start();
            return null;
        }
    }
}
