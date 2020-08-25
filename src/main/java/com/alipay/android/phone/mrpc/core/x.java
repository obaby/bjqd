package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

public final class x {

    /* renamed from: a  reason: collision with root package name */
    private g f549a;

    /* renamed from: b  reason: collision with root package name */
    private z f550b = new z(this);

    public x(g gVar) {
        this.f549a = gVar;
    }

    public final g a() {
        return this.f549a;
    }

    public final <T> T a(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new y(this.f549a, cls, this.f550b));
    }
}
