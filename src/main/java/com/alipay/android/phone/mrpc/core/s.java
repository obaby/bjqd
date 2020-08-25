package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class s {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f544a;

    public static final boolean a(Context context) {
        if (f544a != null) {
            return f544a.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f544a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
