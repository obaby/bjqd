package com.alibaba.sdk.android.utils;

import android.util.Log;
import java.lang.Thread;

/* compiled from: AlicloudUncaughtExceptionHandler */
public class b implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread thread, Throwable th) {
        Log.e("AlicloudUtils", "Catch an uncaught exception, " + thread.getName() + ", error message: " + th.getMessage());
        th.printStackTrace();
    }
}
