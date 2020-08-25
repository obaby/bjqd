package com.alibaba.sdk.android.httpdns;

import android.util.Log;
import java.lang.Thread;

public class i implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread thread, Throwable th) {
        Log.e("HttpDnsSDK", "Catch an uncaught exception, " + thread.getName() + ", error message: " + th.getMessage());
        th.printStackTrace();
    }
}
