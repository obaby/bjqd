package com.bsit.zxing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import com.bsit.zxing.common.Runnable;

final class InactivityTimer {
    private static final long INACTIVITY_DELAY_MS = 300000;
    /* access modifiers changed from: private */
    public static final String TAG = "InactivityTimer";
    /* access modifiers changed from: private */
    public final Activity activity;
    private AsyncTask<?, ?, ?> inactivityTask;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver();
    private boolean registered = false;

    InactivityTimer(Activity activity2) {
        this.activity = activity2;
        onActivity();
    }

    /* access modifiers changed from: package-private */
    public synchronized void onActivity() {
        cancel();
        this.inactivityTask = new InactivityAsyncTask();
        Runnable.execAsync(this.inactivityTask);
    }

    public synchronized void onPause() {
        cancel();
        if (this.registered) {
            this.activity.unregisterReceiver(this.powerStatusReceiver);
            this.registered = false;
        } else {
            Log.w(TAG, "PowerStatusReceiver was never registered?");
        }
    }

    public synchronized void onResume() {
        if (this.registered) {
            Log.w(TAG, "PowerStatusReceiver was already registered?");
        } else {
            this.activity.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.registered = true;
        }
        onActivity();
    }

    /* access modifiers changed from: private */
    public synchronized void cancel() {
        AsyncTask<?, ?, ?> asyncTask = this.inactivityTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.inactivityTask = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        cancel();
    }

    private final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if (intent.getIntExtra("plugged", -1) <= 0) {
                    InactivityTimer.this.onActivity();
                } else {
                    InactivityTimer.this.cancel();
                }
            }
        }
    }

    private final class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        private InactivityAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(InactivityTimer.INACTIVITY_DELAY_MS);
                Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
                InactivityTimer.this.activity.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }
}
