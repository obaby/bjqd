package com.bsit.zxing.common;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

public class Runnable {
    @TargetApi(11)
    public static void execAsync(AsyncTask<?, ?, ?> asyncTask) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } else {
            asyncTask.execute(new Object[0]);
        }
    }
}
