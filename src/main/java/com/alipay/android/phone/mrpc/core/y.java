package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class y implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    protected g f551a;

    /* renamed from: b  reason: collision with root package name */
    protected Class<?> f552b;

    /* renamed from: c  reason: collision with root package name */
    protected z f553c;

    public y(g gVar, Class<?> cls, z zVar) {
        this.f551a = gVar;
        this.f552b = cls;
        this.f553c = zVar;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.f553c.a(method, objArr);
    }
}
