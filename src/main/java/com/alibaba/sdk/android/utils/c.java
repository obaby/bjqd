package com.alibaba.sdk.android.utils;

import android.util.Log;

/* compiled from: Logger */
public class c {
    private String TAG;

    /* renamed from: b  reason: collision with root package name */
    private boolean f496b = false;

    public c(String str) {
        if (!d.a(str)) {
            this.TAG = str;
        }
    }

    public void setLogEnabled(boolean z) {
        this.f496b = z;
    }

    public void a(String str) {
        if (this.f496b) {
            Log.d(this.TAG, str);
        }
    }

    public void b(String str) {
        if (this.f496b) {
            Log.i(this.TAG, str);
        }
    }

    public void c(String str) {
        if (this.f496b) {
            Log.e(this.TAG, str);
        }
    }

    public void a(Throwable th) {
        if (this.f496b && th != null) {
            Log.e(this.TAG, th.toString(), th);
        }
    }
}
