package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.android.phone.mrpc.core.a.d;
import com.alipay.android.phone.mrpc.core.a.e;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class z {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<Object> f554a = new ThreadLocal<>();

    /* renamed from: b  reason: collision with root package name */
    private static final ThreadLocal<Map<String, Object>> f555b = new ThreadLocal<>();

    /* renamed from: c  reason: collision with root package name */
    private byte f556c = 0;
    private AtomicInteger d;
    private x e;

    public z(x xVar) {
        this.e = xVar;
        this.d = new AtomicInteger();
    }

    public final Object a(Method method, Object[] objArr) {
        if (!(Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper())) {
            OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
            boolean z = method.getAnnotation(ResetCookie.class) != null;
            Type genericReturnType = method.getGenericReturnType();
            method.getAnnotations();
            f554a.set((Object) null);
            f555b.set((Object) null);
            if (operationType != null) {
                String value = operationType.value();
                int incrementAndGet = this.d.incrementAndGet();
                try {
                    if (this.f556c == 0) {
                        e eVar = new e(incrementAndGet, value, objArr);
                        if (f555b.get() != null) {
                            eVar.a(f555b.get());
                        }
                        byte[] a2 = eVar.a();
                        f555b.set((Object) null);
                        Object a3 = new d(genericReturnType, (byte[]) new j(this.e.a(), method, incrementAndGet, value, a2, z).a()).a();
                        if (genericReturnType != Void.TYPE) {
                            f554a.set(a3);
                        }
                    }
                    return f554a.get();
                } catch (RpcException e2) {
                    e2.setOperationType(value);
                    throw e2;
                }
            } else {
                throw new IllegalStateException("OperationType must be set.");
            }
        } else {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
    }
}
