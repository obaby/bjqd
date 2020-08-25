package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;

public abstract class a implements v {

    /* renamed from: a  reason: collision with root package name */
    protected Method f502a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f503b;

    /* renamed from: c  reason: collision with root package name */
    protected String f504c;
    protected int d;
    protected String e;
    protected boolean f;

    public a(Method method, int i, String str, byte[] bArr, String str2, boolean z) {
        this.f502a = method;
        this.d = i;
        this.f504c = str;
        this.f503b = bArr;
        this.e = str2;
        this.f = z;
    }
}
