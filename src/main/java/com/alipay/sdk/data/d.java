package com.alipay.sdk.data;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

final class d implements Callable<String> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f681a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ HashMap f682b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ c f683c;

    d(c cVar, Context context, HashMap hashMap) {
        this.f683c = cVar;
        this.f681a = context;
        this.f682b = hashMap;
    }

    private String a() throws Exception {
        return c.a(this.f681a, this.f682b);
    }

    public final /* synthetic */ Object call() throws Exception {
        return c.a(this.f681a, this.f682b);
    }
}
